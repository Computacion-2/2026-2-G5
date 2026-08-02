# Servidor Web Multihilos

Este proyecto contiene dos versiones de un servidor web multihilos:

- Parte 1: recibe la solicitud HTTP y muestra los headers.
- Parte 2: además intenta servir archivos estáticos desde el directorio de trabajo actual.

## Requisitos

- Tener instalado el JDK (Java Development Kit).
- Abrir la terminal desde la raíz del proyecto.

## Estructura del proyecto

- [Parte1/](Parte1/): código de la Parte 1.
- [Parte2/](Parte2/): código de la Parte 2 y el archivo de ejemplo [Parte2/test.html](Parte2/test.html).

## 1. Compilar y ejecutar la Parte 1

Desde la raíz del proyecto, compila:

```bash
javac -d out Parte1/ServidorWebMultihilosParte1.java
```

Luego ejecuta:

```bash
java -cp out Parte1.ServidorWebMultihilosParte1
```

El servidor quedará escuchando en el puerto 6789.

Para probarlo, abre en el navegador:

```text
http://localhost:6789/
```

O con curl:

```bash
curl http://localhost:6789/
```

## 2. Compilar y ejecutar la Parte 2

Desde la raíz del proyecto, compila:

```bash
javac -d out Parte2/ServidorWebMultihilosParte2.java
```

Luego ejecuta:

```bash
java -cp out Parte2.ServidorWebMultihilosParte2
```

El servidor también escuchará en el puerto 6789.

Para probar un archivo estático, usa la siguiente URL desde la raíz del proyecto:

```text
http://localhost:6789/Parte2/test.html
```

También puedes probarlo con:

```bash
curl http://localhost:6789/Parte2/test.html
```

## Compilar ambas partes en una sola vez

Si deseas compilar ambas versiones de forma rápida desde la raíz del proyecto:

```bash
javac -d out Parte1/ServidorWebMultihilosParte1.java Parte2/ServidorWebMultihilosParte2.java
```

Luego puedes ejecutar cada una por separado:

```bash
java -cp out Parte1.ServidorWebMultihilosParte1
```

```bash
java -cp out Parte2.ServidorWebMultihilosParte2
```

## Detener el servidor

Para detenerlo, presiona:

```text
Ctrl + C
```
