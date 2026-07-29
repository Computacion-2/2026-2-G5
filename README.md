# Servidor Web Multihilos

Este proyecto contiene dos versiones de un servidor web multihilos:

- Parte 1: solo recibe la solicitud HTTP y muestra los headers.
- Parte 2: además intenta servir archivos estáticos desde el directorio de trabajo actual.

## Requisitos

- Tener instalado el JDK (Java Development Kit).
- Tener abierta una terminal en la raíz del proyecto.

## Estructura del proyecto

- [Parte1/](Parte1/): código de la Parte 1.
- [Parte2/](Parte2/): código de la Parte 2 y el archivo de ejemplo [Parte2/test.html](Parte2/test.html).

## 1. Compilar y ejecutar la Parte 1

1. Crear una carpeta para los archivos compilados:

```bash
mkdir -p out
```

2. Compilar la Parte 1:

```bash
javac -d out Parte1/ServidorWebMultihilosParte1.java
```

3. Ejecutar el servidor:

```bash
java -cp out Parte1.ServidorWebMultihilosParte1
```

4. El servidor quedará escuchando en el puerto 6789.

5. Para probarlo, puedes abrir en el navegador:

```text
http://localhost:6789
```

O también usar una herramienta como `curl`:

```bash
curl http://localhost:6789
```

> La Parte 1 no sirve archivos HTML ni recursos estáticos; solo muestra la solicitud HTTP recibida.

## 2. Compilar y ejecutar la Parte 2

1. Compilar la Parte 2:

```bash
javac -d out Parte2/ServidorWebMultihilosParte2.java
```

2. Ejecutar el servidor:

```bash
java -cp out Parte2.ServidorWebMultihilosParte2
```

3. El servidor también escuchará en el puerto 6789.

4. Para probarlo, puedes solicitar un recurso como:

```bash
curl http://localhost:6789/test.html
```

## Importante sobre los recursos en la Parte 2

La Parte 2 intenta abrir archivos usando una ruta relativa al directorio actual desde donde se ejecuta el programa. Por eso, el acceso a los recursos depende de dónde se inicie el servidor.

### Opción recomendada

Ejecutar el servidor desde la carpeta [Parte2/](Parte2/) para que el archivo [Parte2/test.html](Parte2/test.html) sea encontrado correctamente:

```bash
cd Parte2
javac -d ../out ServidorWebMultihilosParte2.java
java -cp ../out Parte2.ServidorWebMultihilosParte2
```

En este caso, al solicitar:

```bash
curl http://localhost:6789/test.html
```

el servidor buscará el archivo `./test.html` dentro de [Parte2/](Parte2/).

### Si lo ejecutas desde la raíz del proyecto

Si inicias el servidor desde la raíz del repositorio, el programa buscará los archivos en la raíz, no dentro de [Parte2/](Parte2/). Por lo tanto, para que funcione con [Parte2/test.html](Parte2/test.html), deberías:

- copiar o mover el archivo a la raíz, o
- ejecutar el servidor desde la carpeta [Parte2/](Parte2/).

## Compilar ambas partes en una sola vez

Si deseas compilar ambas versiones de forma rápida:

```bash
mkdir -p out
javac -d out Parte1/ServidorWebMultihilosParte1.java Parte2/ServidorWebMultihilosParte2.java
```

Luego puedes correr cada una por separado:

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
