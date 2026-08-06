# Intro Spring

Proyecto Java Maven que sirve como introducción a Spring y a la inyección de dependencias con configuración XML.

## Contexto de la aplicación

Esta aplicación demuestra cómo Spring administra beans y dependencias usando un archivo XML.
El proyecto integra:

- inyección por constructor (`EstudianteServiceImpl`)
- inyección por setter (`EstudianteServiceSetterImpl`)
- configuración global de Spring para aplicaciones web mediante `web.xml` y `ContextLoaderListener`
- uso de `WebApplicationContext` dentro de un servlet (`EstudianteServlet`)
- control de scope de beans desde `applicationContext.xml` (`prototype` en `matriculaBean`)
- ciclo de vida de beans con métodos `init-method` y `destroy-method` en `EstudianteRepositoryInMemory`
- una entidad adicional `Matricula` que muestra un bean de alcance `prototype`

## Requisitos

- Java 17 instalado
- Maven instalado
- Servlet container compatible con Jakarta EE / Tomcat para la parte web

## Estructura relevante

- `intro_spring/pom.xml` - archivo de compilación Maven
- `intro_spring/src/main/java/com/compunet/Main.java` - punto de entrada de consola
- `intro_spring/src/main/resources/applicationContext.xml` - definición de beans y scopes
- `intro_spring/src/main/webapp/WEB-INF/web.xml` - configuración global de Spring para la aplicación web
- `intro_spring/src/main/java/com/compunet/servlets/EstudianteServlet.java` - servlet que usa `WebApplicationContext`

## Estructura por capas

La implementación sigue una arquitectura por capas sencilla:

- `model` - entidades del dominio (`Estudiante`, `Matricula`)
- `repository` - acceso a datos en memoria (`EstudianteRepositoryInMemory`)
- `service` - lógica del negocio y contratos (`EstudianteService`, `EstudianteServiceImpl`, `EstudianteServiceSetterImpl`)
- `servlets` - capa web que consume los servicios con Spring
- `Main` - prueba de consola que carga el contexto Spring desde XML

Esta separación facilita mostrar cómo Spring inyecta dependencias entre capas y cómo se usan distintas estrategias de wiring.

## Beans y ciclo de vida

- `estudianteRepositoryBean` usa la clase `com.compunet.repository.EstudianteRepositoryInMemory`.
- Se configuran `init-method="metodoInicial"` y `destroy-method="metodoFinal"` para evidenciar el ciclo de vida del bean.
- `estudianteServiceBean` se construye con inyección por constructor.
- `estudianteServiceSetterBean` recibe la dependencia por setter.
- `matriculaBean` está configurado como `scope="prototype"` para crear instancias nuevas en cada petición.

## Compilar el proyecto

Desde la carpeta `intro_spring` del repositorio:

```bash
cd intro_spring
mvn clean package
```

Este comando compila el proyecto y genera el WAR `intro_spring.war` en `intro_spring/target/`.

## Ejecutar el `Main` directamente con Maven

Para ejecutar el ejemplo de consola que carga Spring desde XML:

```bash
cd intro_spring
mvn compile exec:java -Dexec.mainClass="com.compunet.Main"
```

> El `Main` carga `applicationContext.xml` y obtiene `estudianteServiceSetterBean` para listar estudiantes.

## Ejecutar la aplicación web

La aplicación web se configura con Spring a través de `web.xml` y `ContextLoaderListener`.
Después de compilar, despliegue `intro_spring.war` en un servlet container compatible (por ejemplo, Tomcat).

El servlet `EstudianteServlet` se mapea a `/estudiante` y utiliza `WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext())` para obtener el contexto de Spring.

## Ejecución alternativa usando la carpeta de clases compiladas

```bash
cd intro_spring
mvn compile
java -cp target/classes;target/dependency/* com.compunet.Main
```

> Si necesita dependencias externas en el classpath, use `mvn dependency:copy-dependencies` antes de ejecutar con `java`.

## Observaciones

- Este proyecto es útil para entender cómo Spring carga beans desde XML, maneja scope y ciclo de vida, y habilita una aplicación web con servlet.
- El servicio web `EstudianteServlet` demuestra la integración entre Spring y la capa HTTP.
- `Matricula` es un ejemplo de bean `prototype` y se instancia cada vez que se solicita.
