# Sistema de artículos

Proyecto de preentrega para el cursado de **Backend con Java de Talento Tech**. Es una aplicación de consola para administrar artículos y categorías, desarrollada como práctica de programación orientada a objetos.

## Objetivos

- Aplicar herencia y polimorfismo mediante una clase base abstracta `Articulo`.
- Representar categorías y distintos tipos de artículos con clases específicas.
- Sobrescribir métodos como `getTipoArticulo()`, `getDetalleEspecifico()` y `toString()`.
- Practicar operaciones básicas de alta, consulta, modificación y eliminación.

## Funcionalidades

El menú principal permite:

1. Ingresar un artículo.
2. Listar los artículos cargados.
3. Consultar un artículo por código.
4. Modificar un artículo.
5. Eliminar un artículo.
6. Listar las categorías disponibles.

El sistema incluye estos tipos de artículos:

| Tipo | Dato específico |
| --- | --- |
| Electrónico | Garantía en meses |
| Periférico | Tipo de conexión |
| Alimenticio | Días para el vencimiento |
| Limpieza | Tipo de producto |

Las categorías se precargan al iniciar la aplicación. Los artículos y sus cambios se mantienen en memoria durante la ejecución; no se utiliza una base de datos.

## Requisitos

- JDK 25, según la configuración actual del proyecto.
- IntelliJ IDEA u otro entorno compatible con proyectos Java.

## Ejecución

En IntelliJ IDEA, abre el proyecto y ejecuta la clase principal `com.techlab.articulo.App`.

También puedes compilarlo y ejecutarlo desde PowerShell, ubicado en la raíz del proyecto:

```powershell
$sources = Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $sources
java -cp out com.techlab.articulo.App
```

## Estructura

```text
src/
└── com/techlab/articulo/
    ├── App.java
    └── model/
        ├── Articulo.java
        ├── ArticuloAlimenticio.java
        ├── ArticuloElectronico.java
        ├── ArticuloLimpieza.java
        ├── ArticuloPeriferico.java
        └── Categoria.java
```

## Alcance de esta entrega

Esta versión está orientada a los contenidos iniciales del cursado: modelado con clases, encapsulamiento, herencia, polimorfismo y representación de objetos. La información no persiste al cerrar el programa.
