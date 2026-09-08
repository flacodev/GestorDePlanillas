# Gestor de Planillas — Procesadora de Jeans

Aplicación de escritorio para gestionar planillas de producción (lavado, cloro, pintura) de una fábrica de jeans. Permite crear, buscar, editar y eliminar planillas, y exportarlas a PDF.

## Tecnologías

- **Java 21** + **JavaFX** (interfaz gráfica)
- **SQLite** (base de datos local, embebida)
- **Apache PDFBox** (generación de reportes en PDF)
- **Maven** (gestión de dependencias y build)

## Requisitos

- **JDK 21 o superior** instalado ([Eclipse Temurin](https://adoptium.net/) es una buena opción gratuita).
- **Maven** (opcional si usás IntelliJ, que lo trae integrado).
- No hace falta instalar JavaFX por separado: se descarga automáticamente como dependencia de Maven.

## Cómo ejecutar el proyecto

### ⚠️ Importante: no uses el botón ▶️ / 🐛 normal de IntelliJ sobre `Main.java`

JavaFX necesita cargarse como **módulo** de Java (`--module-path`), y el botón de Run/Debug estándar de IntelliJ no configura eso automáticamente. Si lo usás, vas a ver el error:

```
Error: JavaFX runtime components are missing, and are required to run this application
```

Esto **no es un bug del proyecto** — es un comportamiento esperado de JavaFX en cualquier proyecto que lo use. La forma correcta de correrlo es una de estas dos:

### Opción A: desde la terminal

```bash
mvn clean javafx:run
```

### Opción B: desde IntelliJ, usando el panel de Maven

1. Abrí el panel **Maven** (pestaña vertical en el borde derecho de la ventana).
2. Desplegá `Proyecto-Jeans` → `Plugins` → `javafx`.
3. Doble click en `javafx:run` para ejecutar, o click derecho → **Debug** si necesitás poner breakpoints.

Cualquiera de las dos opciones configura correctamente los módulos de JavaFX y la app va a abrir sin problema.

## Estructura del proyecto

```
src/main/java/org/example/
├── Main.java                  # Punto de entrada de la aplicación
├── SistemaPlanilla.java       # Lógica de negocio y acceso a datos (queries a SQLite)
├── Database.java              # Conexión a la base de datos SQLite
├── Planilla.java              # Modelo de datos de una planilla
├── PlanillaPDF.java           # Generación de reportes en PDF
└── controllers/                # Controladores de cada pantalla (JavaFX + FXML)
```

## Notas

- La base de datos SQLite (`Data/dbPlanillas.db`) viaja junto con el proyecto y ya trae la tabla `Planillas` creada.
- Al correr desde una carpeta distinta a la raíz del proyecto, la ruta a la base de datos podría no encontrarse — ejecutá siempre desde la carpeta raíz del repositorio.
