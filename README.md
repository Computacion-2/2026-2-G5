# Intro Spring

Proyecto Java Maven que sirve como introducción a Spring y a la inyección de dependencias mediante anotaciones.

## Contexto de la aplicación

Esta aplicación demuestra cómo Spring administra beans y dependencias usando configuración basada en anotaciones. La clase `AppConfig.java`, marcada con `@Configuration`, es el punto central del contexto de Spring.
El proyecto integra:

- inyección por constructor (`EstudianteServiceImpl`)
- inyección por setter (`EstudianteServiceSetterImpl`)
- detección automática de componentes mediante `@ComponentScan("com.compunet")`
- definición explícita de beans mediante `@Bean` en `AppConfig.java`
- inyección de dependencias mediante `@Autowired` y parámetros de métodos `@Bean`
- configuración global de Spring para aplicaciones web mediante `web.xml` y `ContextLoaderListener`
- uso de `WebApplicationContext` dentro de un servlet (`EstudianteServlet`)
- control de scope de beans mediante anotaciones (`@Scope("prototype")` en `Matricula`)
- ciclo de vida de beans con anotaciones en `EstudianteRepositoryInMemory`
- una entidad adicional `Matricula` que muestra un bean de alcance `prototype`
- lectura de propiedades externas desde `application.properties` mediante `@PropertySource`
- expresiones Spring Expression Language (SpEL) para cálculos, referencias a beans, operadores lógicos, operadores ternarios y métodos estáticos

## Requisitos

- Java 17 instalado
- Maven instalado
- Servlet container compatible con Jakarta EE / Tomcat para la parte web

## Estructura relevante

- `intro_spring/pom.xml` - archivo de compilación Maven
- `intro_spring/src/main/java/com/compunet/Main.java` - punto de entrada de consola
- `intro_spring/src/main/java/com/compunet/config/AppConfig.java` - configuración de Spring, escaneo de componentes, definición de beans y carga de propiedades
- `intro_spring/src/main/java/com/compunet/service/SistemaConfigService.java` - configuración institucional y financiera inyectada con `@Value`
- `intro_spring/src/main/java/com/compunet/service/LiquidacionMatriculaService.java` - ejemplos de expresiones SpEL para la liquidación de matrícula
- `intro_spring/src/main/resources/application.properties` - propiedades de la aplicación
- `intro_spring/src/main/webapp/WEB-INF/web.xml` - configuración global de Spring para la aplicación web
- `intro_spring/src/main/java/com/compunet/servlets/EstudianteServlet.java` - servlet que usa `WebApplicationContext`

## Estructura por capas

La implementación sigue una arquitectura por capas sencilla:

- `model` - entidades del dominio (`Estudiante`, `Matricula`)
- `repository` - acceso a datos en memoria (`EstudianteRepositoryInMemory`)
- `service` - lógica del negocio y contratos (`EstudianteService`, `EstudianteServiceImpl`, `EstudianteServiceSetterImpl`)
- `servlets` - capa web que consume los servicios con Spring
- `Main` - prueba de consola que carga el contexto Spring desde `AppConfig.java` y muestra la institución y el departamento configurados

Esta separación facilita mostrar cómo Spring inyecta dependencias entre capas y cómo se usan distintas estrategias de wiring.

## Beans, inyección y ciclo de vida

- `EstudianteRepositoryInMemory` se registra como componente mediante `@Repository` y sus métodos de ciclo de vida se indican con `@PostConstruct` y `@PreDestroy`.
- `EstudianteServiceImpl` se registra mediante `@Service` y recibe `EstudianteRepository` por inyección de constructor con `@Autowired`.
- `EstudianteServiceSetterImpl` se crea desde el método `@Bean estudianteServiceSetterImpl` de `AppConfig.java`, que recibe el repositorio y lo inyecta mediante setter.
- `nombreAplicacion` es un bean definido explícitamente con `@Bean` en `AppConfig.java`.
- `Matricula` usa `@Scope("prototype")` para crear una instancia nueva cada vez que se solicita.

Spring descubre automáticamente los componentes del paquete `com.compunet` gracias a `@ComponentScan`. Los beans que requieren una configuración específica se declaran con métodos `@Bean` dentro de `AppConfig.java`; de esta forma, ya no es necesario definirlos manualmente en un archivo XML.

## Configuración externa y SpEL

`AppConfig` carga `application.properties` mediante `@PropertySource("classpath:application.properties")`. Esto permite resolver expresiones como `${app.matricula.habilitada}` usadas por `SistemaConfigService`.

`LiquidacionMatriculaService` contiene ejemplos de SpEL, entre ellos:

- operaciones aritméticas y cálculos con valores de configuración
- referencias a otros beans, por ejemplo `sistemaConfigService.costoCredito`
- llamadas a métodos estáticos como `T(java.lang.Math).round(...)`
- generación de un identificador con `T(java.util.UUID)`
- condiciones ternarias y operadores lógicos para determinar el estado operativo

## Compilar el proyecto

Desde la carpeta `intro_spring` del repositorio:

```bash
cd intro_spring
mvn clean package
```

Este comando compila el proyecto y genera el WAR `intro_spring.war` en `intro_spring/target/`.

## Ejecutar el `Main`

Desde el IDE, ejecute la clase `com.compunet.Main`. El programa carga el contexto Spring desde la configuración basada en anotaciones y muestra los valores institucionales configurados.

También puede compilar el proyecto con Maven:

```bash
cd intro_spring
mvn clean package
```

> El `pom.xml` contiene actualmente `com.example.Main` como clase principal del `exec-maven-plugin`; por eso la ejecución de `mvn exec:java` requiere corregir primero ese valor a `com.compunet.Main`.

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

- Este proyecto es útil para entender cómo Spring descubre y configura beans mediante anotaciones, maneja scope y ciclo de vida, y habilita una aplicación web con servlet.
- El servicio web `EstudianteServlet` demuestra la integración entre Spring y la capa HTTP.
- `Matricula` es un ejemplo de bean `prototype` y se instancia cada vez que se solicita.
