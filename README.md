# Intro Spring

Proyecto Java Maven con Spring que ejecuta una aplicación de consola a través de `Main.java`.

## Contexto de la aplicación

Este proyecto es una introducción a Spring y a la inyección de dependencias usando configuración XML.
La implementación muestra cómo Spring arma los objetos y sus dependencias mediante:

- inyección por constructor
- inyección por setter

La configuración se define en `intro_spring/src/main/resources/applicationContext.xml` y Spring crea los beans necesarios para la aplicación.

## Requisitos

- Java 17 instalado
- Maven instalado

## Estructura relevante

- `intro_spring/pom.xml` - archivo de compilación Maven
- `intro_spring/src/main/java/com/compunet/Main.java` - clase `main`
- `intro_spring/src/main/resources/applicationContext.xml` - configuración Spring XML

## Estructura por capas

La aplicación está organizada en capas clásicas de un proyecto Spring simple:

- `model` - entidades del dominio (`Estudiante`)
- `repository` - acceso a datos e implementación en memoria
- `service` - lógica de negocio y definición de servicios
- `Main` - punto de entrada que carga el contexto Spring y usa los servicios

Esta arquitectura permite separar responsabilidades y mostrar cómo Spring inyecta dependencias en cada capa.

## Compilar el proyecto

Desde la carpeta `intro_spring` del repositorio:

```bash
cd intro_spring
mvn clean package
```

Este comando compila el código y genera el artefacto `intro_spring.war` en `intro_spring/target/`.

## Ejecutar el `Main` directamente con Maven

Desde la carpeta `intro_spring`:

```bash
cd intro_spring
mvn compile exec:java -Dexec.mainClass="com.compunet.Main"
```

> Nota: la clase principal está definida en el paquete `com.compunet` y se encuentra en `src/main/java/com/compunet/Main.java`.

## Ejecución alternativa usando la carpeta de clases compiladas

Después de compilar, también puede ejecutar la clase directamente con Java:

```bash
cd intro_spring
mvn compile
java -cp target/classes;target/dependency/* com.compunet.Main
```

> Si necesita dependencias externas en el classpath, use `mvn dependency:copy-dependencies` antes de ejecutar con `java`.

## Observaciones

El `Main` carga el contexto Spring desde `applicationContext.xml` y obtiene un bean `estudianteServiceSetterBean` para listar estudiantes.
