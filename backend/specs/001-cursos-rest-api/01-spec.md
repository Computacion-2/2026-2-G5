# 01. Especificación Funcional (`01-spec.md`) — 001-cursos-rest-api: API REST de Cursos con JWT

> **Regla de Oro de Spec-Kit:** En este documento definimos exclusivamente el **QUÉ** y el **POR QUÉ** desde la perspectiva del dominio y del usuario. Se evita prescribir código Java o detalles internos de implementación (eso pertenece a `02-plan.md`).

---

## 1. Contexto y Objetivo de Negocio

- **Problema actual:** Actualmente, la gestión de cursos solo se encuentra disponible mediante vistas renderizadas en el servidor (MVC/Thymeleaf) o a través de controladores de prueba incompletos. No existe una interfaz de programación RESTful formal, versionada y asegurada con JWT que permita a clientes externos, aplicaciones móviles o frontends desacoplados (SPAs) gestionar los cursos académicos de la Universidad Icesi.
- **Valor esperado:** Disponer de una API REST protegida y estandarizada bajo `/api/v1/cursos` que permita realizar operaciones de consulta (general, por ID, filtrado flexible por departamento y filtrado por profesor/docente) y operaciones de administración (creación, edición y eliminación) bajo control estricto de accesos basado en roles (RBAC) y tokens JWT.

---

## 2. Historias de Usuario (User Stories)

- **HU-01 (Listar Cursos y Filtrar por Departamento):** Como **Usuario Autenticado** (Estudiante, Profesor o Administrador con permiso `COURSE_READ`), quiero listar todos los cursos académicos o filtrarlos opcionalmente por departamento mediante un parámetro de consulta (`?departamento=...`), para conocer la oferta curricular general o de un área temática específica sin distinción de mayúsculas o minúsculas.
- **HU-02 (Consultar Curso por ID):** Como **Usuario Autenticado**, quiero consultar el detalle de un curso específico mediante su identificador numérico, para visualizar sus créditos, departamento y profesor asignado.
- **HU-03 (Consultar Cursos por Docente):** Como **Usuario Autenticado**, quiero obtener el listado de cursos asignados a un docente a partir del ID de la entidad Profesor (`profesor.id`), para conocer su carga lectiva o elegir cursos impartidos por él.
- **HU-04 (Crear Curso):** Como **Administrador o Profesor** (con permiso `COURSE_WRITE`), quiero registrar un nuevo curso especificando nombre, créditos (>0), departamento y el ID del profesor responsable, para enriquecer la oferta académica institucional.
- **HU-05 (Editar Curso):** Como **Administrador o Profesor** (con permiso `COURSE_WRITE`), quiero actualizar los datos básicos y/o la reasignación de profesor de un curso existente, para mantener el catálogo curricular al día.
- **HU-06 (Eliminar Curso de Manera Segura):** Como **Administrador** (exclusivamente con rol `ADMINISTRADOR`), quiero eliminar un curso del catálogo cuando ya no sea ofertado, asegurando que no se eliminen cursos con estudiantes matriculados activos para preservar la integridad del historial académico.

---

## 3. Decisiones de Clarificación y Casos Borde (Fase Clarify)

A partir de la clarificación técnica con el desarrollador, se formalizan las siguientes reglas inmutables:

1. **Integridad Referencial en Eliminación:**
   - **Bloqueo con `409 Conflict`:** Si un curso tiene registros vinculados en la tabla `matricula`, el sistema **rechaza de forma estricta su eliminación** con código `409 Conflict` y un mensaje descriptivo: `"No se puede eliminar el curso porque tiene estudiantes matriculados asociados"`.
   - Queda prohibida la eliminación en cascada destructiva del historial de matrículas.
2. **Autorización Estricta de Eliminación (RBAC):**
   - Aunque los roles `ADMINISTRADOR` y `PROFESOR` comparten el permiso `COURSE_WRITE`, la operación `DELETE /api/v1/cursos/{id}` está **reservada exclusivamente para `hasRole('ADMINISTRADOR')`**. Un usuario con rol `PROFESOR` no puede eliminar cursos.
3. **Estrategia de Filtrado por Departamento:**
   - Unificado en el endpoint base con parámetro de consulta opcional: `GET /api/v1/cursos?departamento={dept}`.
   - La búsqueda debe ser **insensible a mayúsculas/minúsculas** (`case-insensitive`, ej. "tic", "TIC", "Tic" deben arrojar los mismos resultados).
4. **Identificador del Docente:**
   - En `GET /api/v1/cursos/profesor/{profesorId}`, el identificador `{profesorId}` corresponde inequívocamente a la clave primaria de la entidad `Profesor` (`profesor.id`).
5. **Validación de Datos:**
   - Nombre: obligatorio, no vacío.
   - Créditos: entero positivo mayor a 0 (ej. min: 1, max: 10).
   - Departamento: obligatorio, no vacío.
   - ProfesorId: obligatorio; debe corresponder a un `Profesor` existente en la base de datos.

---

## 4. Reglas de Negocio y Matriz de Acceso (RBAC)

| Operación de Dominio | Método HTTP & Endpoint | Roles / Permisos Autorizados | Regla de Negocio / Restricción |
| :--- | :--- | :--- | :--- |
| **Listar todos los cursos** | `GET /api/v1/cursos` | `hasAuthority('COURSE_READ')` | Retorna lista completa de cursos; `[]` si no hay registros. |
| **Filtrar por departamento** | `GET /api/v1/cursos?departamento={dept}` | `hasAuthority('COURSE_READ')` | Búsqueda insensible a mayúsculas/minúsculas sobre el campo `departamento`. |
| **Consultar curso por ID** | `GET /api/v1/cursos/{id}` | `hasAuthority('COURSE_READ')` | Retorna `404 Not Found` si el ID del curso no existe. |
| **Consultar cursos por profesor** | `GET /api/v1/cursos/profesor/{profesorId}` | `hasAuthority('COURSE_READ')` | Retorna cursos asignados al `profesor.id`. Si el profesor no existe en el sistema, retorna `404 Not Found`. |
| **Registrar nuevo curso** | `POST /api/v1/cursos` | `hasAuthority('COURSE_WRITE')` | Campos obligatorios: nombre, créditos (>0), departamento, profesorId. Valida existencia del profesor. Retorna `201 Created` con header `Location`. |
| **Actualizar curso** | `PUT /api/v1/cursos/{id}` | `hasAuthority('COURSE_WRITE')` | Valida existencia del curso (`404`) y del profesor (`404`/`400`). Retorna `200 OK`. |
| **Eliminar curso** | `DELETE /api/v1/cursos/{id}` | **`hasRole('ADMINISTRADOR')`** | Prohibido para profesores y estudiantes (`403`). Si tiene matrículas activas, retorna `409 Conflict`. Retorna `204 No Content` si se elimina con éxito. |

---

## 5. Escenarios de Aceptación (Given / When / Then)

### Escenario 1: Listado general exitoso de cursos
- **DADO (Given):** Un usuario autenticado con token JWT válido que posee la autoridad `COURSE_READ`.
- **CUANDO (When):** Envía una solicitud `GET` a `/api/v1/cursos`.
- **ENTONCES (Then):** El sistema responde con código HTTP `200 OK` y una colección JSON de cursos en formato de respuesta DTO, sin exponer datos sensibles del profesor ni colecciones circulares.

### Escenario 2: Filtrado de cursos por departamento (case-insensitive)
- **DADO (Given):** Un usuario autenticado con autoridad `COURSE_READ`, y existen cursos registrados en el departamento `"TIC"`.
- **CUANDO (When):** Envía una solicitud `GET` a `/api/v1/cursos?departamento=tic`.
- **ENTONCES (Then):** El sistema responde con `200 OK` retornando los cursos cuyo departamento coincida de forma insensible a mayúsculas/minúsculas.

### Escenario 3: Consulta de curso por ID existente
- **DADO (Given):** Un usuario autenticado con token JWT válido y autoridad `COURSE_READ`, y existe un curso con ID `1`.
- **CUANDO (When):** Envía una solicitud `GET` a `/api/v1/cursos/1`.
- **ENTONCES (Then):** El sistema retorna `200 OK` con los datos del curso (nombre, créditos, departamento, id y nombre del docente).

### Escenario 4: Consulta de curso por ID inexistente
- **DADO (Given):** Un usuario autenticado con autoridad `COURSE_READ`, y no existe ningún curso con ID `999`.
- **CUANDO (When):** Envía una solicitud `GET` a `/api/v1/cursos/999`.
- **ENTONCES (Then):** El sistema responde con código HTTP `404 Not Found` y un mensaje de error descriptivo en JSON.

### Escenario 5: Consulta de cursos por ID de docente existente
- **DADO (Given):** Un usuario autenticado con autoridad `COURSE_READ`, y existe un profesor registrado con ID `2` con cursos asignados.
- **CUANDO (When):** Envía una solicitud `GET` a `/api/v1/cursos/profesor/2`.
- **ENTONCES (Then):** El sistema retorna `200 OK` con la lista de cursos a cargo del docente con ID `2`.

### Escenario 6: Consulta de cursos por ID de docente inexistente
- **DADO (Given):** Un usuario autenticado con autoridad `COURSE_READ`, y no existe un profesor con ID `999`.
- **CUANDO (When):** Envía una solicitud `GET` a `/api/v1/cursos/profesor/999`.
- **ENTONCES (Then):** El sistema responde con código HTTP `404 Not Found` indicando que el docente especificado no existe.

### Escenario 7: Creación exitosa de curso
- **DADO (Given):** Un usuario autenticado con autoridad `COURSE_WRITE` (ej. Administrador o Profesor) y un profesor con ID `3` existente.
- **CUANDO (When):** Envía una solicitud `POST` a `/api/v1/cursos` con payload válido:
  ```json
  {
    "nombre": "Estructuras de Datos",
    "creditos": 3,
    "departamento": "TIC",
    "profesorId": 3
  }
  ```
- **ENTONCES (Then):** El sistema persiste el curso, responde con código `201 Created`, cabecera `Location` apuntando a `/api/v1/cursos/{idGenerado}` y el payload `CursoResponseDTO` en el cuerpo.

### Escenario 8: Creación de curso con profesor inexistente
- **DADO (Given):** Un usuario con autoridad `COURSE_WRITE`, enviando una solicitud `POST` con `"profesorId": 8888` (inexistente).
- **CUANDO (When):** El sistema procesa la solicitud.
- **ENTONCES (Then):** El sistema rechaza la creación con código HTTP `404 Not Found` (o `400 Bad Request`) indicando que el profesor asignado no existe.

### Escenario 9: Actualización de curso existente
- **DADO (Given):** Un curso existente con ID `1` y un usuario con autoridad `COURSE_WRITE`.
- **CUANDO (When):** Envía una solicitud `PUT` a `/api/v1/cursos/1` con datos modificados válidos.
- **ENTONCES (Then):** El sistema actualiza el registro y responde con `200 OK` y la representación actualizada `CursoResponseDTO`.

### Escenario 10: Eliminación exitosa de curso sin matrículas
- **DADO (Given):** Un curso con ID `5` sin estudiantes matriculados y un usuario con rol `ADMINISTRADOR`.
- **CUANDO (When):** Envía una solicitud `DELETE` a `/api/v1/cursos/5`.
- **ENTONCES (Then):** El sistema elimina el curso y responde con código HTTP `204 No Content` sin cuerpo.

### Escenario 11: Rechazo de eliminación de curso con matrículas asociadas (Integridad Referencial)
- **DADO (Given):** Un curso con ID `2` que cuenta con uno o más estudiantes matriculados en la entidad `Matricula`, y un usuario con rol `ADMINISTRADOR`.
- **CUANDO (When):** Envía una solicitud `DELETE` a `/api/v1/cursos/2`.
- **ENTONCES (Then):** El sistema **bloquea la eliminación** y responde con código HTTP `409 Conflict` (o `400 Bad Request`) indicando que el curso no puede eliminarse debido a matrículas activas.

### Escenario 12: Intento de eliminación por parte de un Profesor (Violación RBAC)
- **DADO (Given):** Un usuario autenticado con rol `PROFESOR` (que posee `COURSE_WRITE` pero NO `ADMINISTRADOR`).
- **CUANDO (When):** Envía una solicitud `DELETE` a `/api/v1/cursos/1`.
- **ENTONCES (Then):** El sistema bloquea inmediatamente la petición con código HTTP `403 Forbidden`.

### Escenario 13: Petición sin token JWT
- **DADO (Given):** Un cliente HTTP que no envía el encabezado `Authorization: Bearer <token>`.
- **CUANDO (When):** Solicita cualquier endpoint bajo `/api/v1/cursos/**`.
- **ENTONCES (Then):** La cadena `@Order(1)` responde inmediatamente con código `401 Unauthorized` en formato JSON (`{"error": "No autenticado: Token JWT ausente, invalido o expirado"}`).

---

## 6. Contratos de Entrada y Salida (DTOs)

### Payload de Entrada: `CursoRequestDTO`
```json
{
  "nombre": "Redes y Comunicaciones",
  "creditos": 3,
  "departamento": "TIC",
  "profesorId": 2
}
```

### Payload de Salida: `CursoResponseDTO` (Aplanado / Sin fugas de dominio)
```json
{
  "id": 1,
  "nombre": "Redes y Comunicaciones",
  "creditos": 3,
  "departamento": "TIC",
  "profesorId": 2,
  "profesorNombre": "Domiciano Rincon",
  "profesorDepartamento": "TIC"
}
```

---

## 7. Fuera de Alcance (Out of Scope)

1. **Vistas Web (MVC/Thymeleaf):** Este módulo cubre exclusivamente la API REST stateless bajo `/api/v1/cursos`; las plantillas HTML en `templates/cursos/` no se alteran.
2. **Inscripción y Calificación:** La creación de matrículas se gestiona en su propio módulo.
3. **Mallas Curriculares Complejas:** No se modelan prerrequisitos ni correquisitos entre cursos.
4. **Modificación de la configuración de seguridad global:** No se modifica la arquitectura dual de `SecurityConfig.java`.
