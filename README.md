# Servidor Web Simple en Java

Este proyecto implementa un servidor web HTTP básico en Java que permite servir archivos estáticos desde el directorio donde se ejecuta.

## Requisitos

- Java JDK 8 o superior
- Terminal (Linux, WSL, macOS o Windows)

Verificar la instalación de Java:

```bash
java -version
javac -version
```

## Estructura del proyecto

```text
serverSimple/
├── ServidorWebSimple.java
├── ServidorWebSimple.class
└── index.html
```

## Compilar

Si aún no se ha compilado:

```bash
cd serverSimple
javac ServidorWebSimple.java
```

Esto generará el archivo:

```
ServidorWebSimple.class
```

## Ejecutar el servidor

Ubícate en la carpeta base del proyecto y ejecuta:

```bash
java servidorSimple/ServidorWebSimple
```

Si el servidor inicia correctamente, mostrará un mensaje indicando el puerto en el que está escuchando (por ejemplo, el puerto **6789**).

## Probar el servidor

Desde un navegador web accede al archivo de prueba:

```
http://localhost:6789/serverSimple/index.html
```

También puedes probarlo desde la terminal con:

```bash
curl http://localhost:6789/serverSimple/index.html
```

## Detener el servidor

Presiona:

```text
Ctrl + C
```

en la terminal donde está ejecutándose.

## Notas

- El servidor atiende solicitudes HTTP desde el directorio actual.
- Si modificas el código fuente (`ServidorWebSimple.java`), deberás recompilar antes de ejecutarlo nuevamente:

```bash
javac ServidorWebSimple.java
java ServidorWebSimple
```