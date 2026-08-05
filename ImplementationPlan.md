# Plan de Implementación - Práctica Sesión 3 (Resumen)

- **Paso 1: Creación del Proyecto y Dependencias**
  - Crear un proyecto Maven utilizando el arquetipo de aplicación web (`maven-archetype-webapp`).
  - Modificar el archivo `pom.xml` para agregar las siguientes dependencias:
    - `jakarta.servlet-api` (con scope provided)
    - `spring-context`
    - `spring-web`

- **Paso 2: Estructura de Directorios (Arquetipo Web App modificado)**
  - Configurar las carpetas de código fuente (Java) que el arquetipo web no incluye por defecto. El esquema es el siguiente:
    ```text
    tu-proyecto-maven
    ├── pom.xml
    └── src
        └── main
            ├── java
            │   └── com
            │       └── compunet
            │           ├── model/
            │           ├── repository/
            │           ├── service/
            │           ├── servlet/
            │           └── Main.java
            ├── resources
            │   └── applicationContext.xml
            └── webapp
                └── WEB-INF
                    └── web.xml
    ```

- **Paso 3: Capa de Modelo (Model)**
  - Crear la clase `Estudiante` dentro del paquete `model`.
  - Definir los atributos: `id`, `nombre` y `correo`.
  - Agregar constructor, getters y setters.

- **Paso 4: Capa de Acceso a Datos (Repository)**
  - Plantear la interfaz `EstudianteRepository` con los métodos: `obtenerTodos()` y `guardar(Estudiante estudiante)`.
  - Crear la implementación de la interfaz en la misma capa.
  - En la implementación, definir una lista de estudiantes inicializada en el constructor.
  - Añadir 3 estudiantes por defecto dentro del constructor.
  - Sobreescribir los métodos `obtenerTodos` y `guardar` para interactuar con la lista.

- **Paso 5: Capa de Lógica de Negocio (Service)**
  - Crear la interfaz `EstudianteService` con los métodos: `listarEstudiantes()` y `registrarEstudiante(Estudiante estudiante)`.
  - Crear **dos implementaciones** de esta interfaz (o una que muestre ambas, según prefieras) para demostrar:
    - **Inyección por constructor:** `EstudianteServiceImpl` (o `...Constructor`) donde el repositorio se inyecta mediante el método constructor.
    - **Inyección por setter:** Otra implementación (o `...Setter`) donde la dependencia del repositorio se inyecta a través de un método setter.

- **Paso 6: Wiring de Beans en XML**
  - Crear el archivo de configuración `applicationContext.xml` (en `src/main/resources`).
  - Definir el bean del `EstudianteRepository`.
  - Hacer el wiring de los beans del servicio:
    - Configurar un bean usando la inyección por constructor (`<constructor-arg>`).
    - Configurar otro bean usando la inyección por setter (`<property>`).

- **Paso 7: Ejecución y Comprobación (Main)**
  - En la clase `Main`, instanciar el contexto de Spring utilizando `ClassPathXmlApplicationContext`.
  - Obtener el bean del servicio desde el contexto inicializado.
  - Obtener la lista de estudiantes invocando el método correspondiente del servicio.
  - Imprimir los estudiantes por consola para evidenciar que el flujo y la inyección funcionan correctamente mostrando los 3 registros por defecto.