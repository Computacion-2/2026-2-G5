# 03. Lista de Tareas Atómicas (`03-tasks.md`) — 001-cursos-rest-api: API REST de Cursos con JWT

> **Regla de Oro de Spec-Kit:** Divide el plan técnico `02-plan.md` en unidades de trabajo atómicas, estrictamente secuenciales y verificables. Se aplica la **Single-Task Rule**: el agente de IA **solo** ejecuta una tarea pendiente a la vez (`[ ]`), compila con `./mvnw test-compile`, la marca como completada (`[x]`) y solicita validación al desarrollador.

---

## Checklist de Progreso

- [x] **Tarea T1:** Creación de DTOs de Entrada y Salida (`CursoRequestDTO` y `CursoResponseDTO`) en `dtos.Cursos`.
- [x] **Tarea T2:** Extensión de consultas derivadas en `CursoRepository` y `MatriculaRepository`.
- [x] **Tarea T3:** Extensión de lógica de negocio y blindaje de integridad en `CursoService`.
- [x] **Tarea T4:** Creación de la interfaz de mapeo `CursoMapper` con MapStruct en el paquete `mappers`.
- [x] **Tarea T5:** Implementación del controlador REST `CursoRestController` en `controller.api` con `@PreAuthorize` y semántica HTTP completa.
- [x] **Tarea T6:** Pruebas unitarias de la lógica de cursos y verificación final de compilación.

---

## Detalle de Tareas Atómicas

### Tarea T1: Creación de DTOs de Entrada y Salida
- **Objetivo:** Definir las clases de transferencia desacopladas del modelo JPA para peticiones (`CursoRequestDTO`) y respuestas aplanadas (`CursoResponseDTO`).
- **Archivos a crear:**
  - `src/main/java/com/compunet/springboot/dtos/Cursos/CursoRequestDTO.java`
  - `src/main/java/com/compunet/springboot/dtos/Cursos/CursoResponseDTO.java`
- **Dependencias:** Ninguna (Fase inicial).
- **Criterio de completitud:** DTOs con anotaciones Lombok (`@Data`, `@Builder`, `@NoArgsConstructor`, `@AllArgsConstructor`), sin anotaciones ni dependencias JPA.
- **Comando de verificación:** `./mvnw test-compile`

---

### Tarea T2: Extensión de la Capa de Acceso a Datos (Repositorios)
- **Objetivo:** Agregar las consultas derivadas requeridas para la búsqueda por departamento (case-insensitive), búsqueda por docente y validación de existencia de matrículas vinculadas.
- **Archivos a modificar:**
  - `src/main/java/com/compunet/springboot/repository/CursoRepository.java`
  - `src/main/java/com/compunet/springboot/repository/MatriculaRepository.java`
- **Métodos a declarar:**
  - En `CursoRepository`: `List<Curso> findByDepartamentoIgnoreCase(String departamento);` y `List<Curso> findByProfesorId(Long profesorId);`
  - En `MatriculaRepository`: `boolean existsByIdCursoId(Long cursoId);`
- **Dependencias:** Ninguna.
- **Criterio de completitud:** Métodos de Spring Data JPA declarados con nombres conformes a las propiedades de las entidades.
- **Comando de verificación:** `./mvnw test-compile`

---

### Tarea T3: Extensión de la Capa de Negocio (`CursoService`)
- **Objetivo:** Implementar los métodos de consulta filtrada y aplicar la regla de integridad referencial que bloquea la eliminación si existen matrículas activas.
- **Archivos a modificar:**
  - `src/main/java/com/compunet/springboot/service/CursoService.java`
- **Lógica a incorporar:**
  - Inyección de `MatriculaRepository` mediante el constructor (`@RequiredArgsConstructor`).
  - `listarPorDepartamento(String departamento)`: Invoca `cursoRepository.findByDepartamentoIgnoreCase(departamento)`.
  - `listarPorProfesorId(Long profesorId)`: Verifica que el profesor exista con `profesorRepository.existsById(profesorId)` (lanza `NoSuchElementException` si no existe) y retorna `cursoRepository.findByProfesorId(profesorId)`.
  - `eliminarCurso(Long id)`: Lanza `NoSuchElementException` si no existe el curso, y lanza `IllegalStateException` ("No se puede eliminar el curso porque tiene estudiantes matriculados asociados.") si `matriculaRepository.existsByIdCursoId(id)` es verdadero.
- **Dependencias:** Requiere T2.
- **Criterio de completitud:** Métodos anotados con `@Transactional` (o de sólo lectura) compilando limpiamente sin acoplamiento a paquetes web HTTP.
- **Comando de verificación:** `./mvnw test-compile`

---

### Tarea T4: Creación de Mapper con MapStruct (`CursoMapper`)
- **Objetivo:** Implementar la interfaz de mapeo para transformar entre entidades `Curso` y los DTOs `CursoRequestDTO` y `CursoResponseDTO`, aplanando los datos del profesor.
- **Archivos a crear:**
  - `src/main/java/com/compunet/springboot/mappers/CursoMapper.java`
- **Reglas de mapeo:**
  - `@Mapping(target = "profesorId", source = "profesor.id")`
  - `@Mapping(target = "profesorNombre", source = "profesor.usuario.nombre")`
  - `@Mapping(target = "profesorDepartamento", source = "profesor.departamento")`
  - Métodos: `toCursoResponseDTO(Curso)`, `toListCursoResponseDTO(List<Curso>)` y `toCurso(CursoRequestDTO)` (ignorando `id`, `profesor`, `matriculas`).
- **Dependencias:** Requiere T1.
- **Criterio de completitud:** El procesador de anotaciones de MapStruct genera automáticamente `CursoMapperImpl` en `target/generated-sources/annotations`.
- **Comando de verificación:** `./mvnw test-compile`

---

### Tarea T5: Implementación del Controlador REST (`CursoRestController`)
- **Objetivo:** Exponer los endpoints HTTP en `/api/v1/cursos` protegidos con JWT y `@PreAuthorize`, manejando respuestas semánticas (`200`, `201` con header `Location`, `204`, `400`, `404`, `409`).
- **Archivos a crear:**
  - `src/main/java/com/compunet/springboot/controller/api/CursoRestController.java`
- **Endpoints a implementar:**
  - `GET /api/v1/cursos?departamento=...` ➔ `@PreAuthorize("hasAuthority('COURSE_READ')")`
  - `GET /api/v1/cursos/{id}` ➔ `@PreAuthorize("hasAuthority('COURSE_READ')")`
  - `GET /api/v1/cursos/profesor/{profesorId}` ➔ `@PreAuthorize("hasAuthority('COURSE_READ')")`
  - `POST /api/v1/cursos` ➔ `@PreAuthorize("hasAuthority('COURSE_WRITE')")`
  - `PUT /api/v1/cursos/{id}` ➔ `@PreAuthorize("hasAuthority('COURSE_WRITE')")`
  - `DELETE /api/v1/cursos/{id}` ➔ `@PreAuthorize("hasRole('ADMINISTRADOR')")` (mapeando `IllegalStateException` a `409 Conflict`)
- **Dependencias:** Requiere T1, T3 y T4.
- **Criterio de completitud:** Cumple el 100% de la matriz de endpoints sin exponer entidades JPA, con inyección por constructor y reglas de seguridad declarativa.
- **Comando de verificación:** `./mvnw test-compile`

---

### Tarea T6: Pruebas Unitarias y Verificación Final
- **Objetivo:** Desarrollar pruebas unitarias con JUnit 5 y Mockito para validar las nuevas reglas de negocio del servicio (búsqueda por departamento, búsqueda por docente, y bloqueo 409 al intentar eliminar con matrículas asociadas).
- **Archivos a crear/modificar:**
  - `src/test/java/com/compunet/springboot/service/CursoServiceTest.java`
- **Dependencias:** Requiere T5.
- **Criterio de completitud:** Pruebas unitarias ejecutadas y en verde validando los criterios Gherkin de `01-spec.md`.
- **Comando de verificación:** `./mvnw test -Dtest=CursoServiceTest`
