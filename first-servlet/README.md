# Proyecto Servlet Simple

Este proyecto es un servlet simple en Java que responde a peticiones `GET` mediante el método `doGet`.

## Requisitos

- Java JDK 17 o superior
- Apache Maven
- Apache Tomcat 10.1.x (recomendado, compatible con Jakarta Servlet)

## Compilar el proyecto

Desde la raíz del proyecto, ejecuta:

```bash
mvn clean package
```

Esto generará el archivo WAR en:

```bash
target/first-servlet.war
```

## Ejecutar en Tomcat

1. Inicia Tomcat:

   - En Linux/macOS:

   ```bash
   ./bin/startup.sh
   ```

   - En Windows:

   ```bat
   bin\startup.bat
   ```

2. Despliega la aplicación copiando el WAR a la carpeta `webapps` de Tomcat:

```bash
cp target/first-servlet.war $TOMCAT_HOME/webapps/
```

O simplemente copia el archivo manualmente a:

```text
<TOMCAT_HOME>/webapps/
```

3. Espera unos segundos y abre en el navegador:

```text
http://localhost:8080/first-servlet/saludos
```

## Probar el servlet

Puedes enviar un parámetro `nombre` en la URL:

```text
http://localhost:8080/first-servlet/saludos?nombre=Juan
```

Esto responderá con un mensaje de saludo generado por el servlet.

## Estructura importante

- El servlet está definido en `src/main/java/HolaServlet.java`
- El mapping del servlet se encuentra en la anotación `@WebServlet("/saludos")`
