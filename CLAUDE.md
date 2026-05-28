# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

This is a university coursework folder for UTP (Universidad Tecnológica del Perú). Projects are organized by cycle (CICLO_IV, CICLO_V) and course. CICLO_IV has Java code; CICLO_V has Java (AED, Diseño de Patrones), HTML/CSS (Web), Excel/Python (Herramientas), and study notes (BD2, SO, Redes).

## Running Java Code

Projects use three different build systems depending on where they live:

**Standalone files (e.g., `SEMANA_9/JAVA_NVIM/`, root-level `Main.java`)** — no package declarations, compile and run from the file's directory:
```bash
cd CICLO_IV/ANALISIS_DISEÑO_ALGORITMOS/SEMANA_9/JAVA_NVIM
javac BusquedaBloquesRAF.java Main.java
java Main
```

**NetBeans Ant projects** (have `build.xml` + `nbproject/`) — open in NetBeans or IntelliJ, or use Ant:
```bash
ant -f build.xml run
```

**IntelliJ IDEA projects** (have `*.iml` + `.idea/`) — open the project root in IntelliJ and run. One project (`AED_09Abril_ordenamiento`) uses Maven:
```bash
mvn compile exec:java -Dexec.mainClass="com.utp.aed_09abril_ordenamiento.AED_09Abril_ordenamiento"
```

**Runtime note**: The system JDK is OpenJDK 25. IntelliJ projects were configured for JDK 17 (`languageLevel="JDK_17"`); avoid using Java 21+ features for compatibility.

## Course Structure

### CICLO_IV

**ANALISIS_DISEÑO_ALGORITMOS** — sorting and external search algorithms:
- Root-level `Main.java` / `AlgoritmoDeOrdenamiento.java`: in-memory sorting (Selection, Bubble, Insertion, Direct Merge, Natural Merge)
- `SEMANA_5/MARTES/Ordenamiento_Interno/`: same algorithms as a NetBeans project
- `SEMANA_5/MARTES/AED_09Abril_ordenamiento/`: Maven project, adds external merge sort (`cIntercalacion`)
- `SEMANA_9/`: external file search algorithms — four variants of the same program at increasing completeness:
  - `JAVA_NVIM/` — standalone flat files, partially implemented (only `BusquedaBloquesRAF` is done)
  - `BusquedaExterna/` — first complete version, split into `src/algoritmos/` and `src/modelos/`
  - `BusquedaExternaAvanzada_SinglePackage/` — polished version, single `busqueda` package, interactive menu, auto-comparison
  - `Busqueda/` — intermediate IntelliJ + NetBeans dual project

**PROGRAMACION_ORIENTADA_OBJETOS** — `SEMANA_6/`: geometric figure class hierarchy (NetBeans + IntelliJ):
- Abstract base: `Figeom` (color, relleno, fechacrea)
- 2D shapes: `Circulo`, `Cuadrado`, `Rectangulo`, `Triangulo`
- 3D solids extend 2D shapes: `Cilindro` (from Circulo), `Cubo` (from Cuadrado), `PrismaRectangular` (from Rectangulo), `PrismaTriangular` (from Triangulo)

### CICLO_V

**ALGORITMO_Y_ESTRUCTURA_DE_DATOS** — data structures in Java (4 semanas, NetBeans Ant projects):
- SEMANA_1: `Ejemplo1LSU/` — 1D arrays (`cArreglo`), intro to data structures
- SEMANA_2: `EjemploArreglos/` — linear array operations
- SEMANA_3: `AED_27Ago_Copia/` — matrices, array fusion/cloning; `Ejemplo_S03/` — comparison and fusion
- SEMANA_4: `Ejemplo_Semana4/` — 2D arrays/matrices (NetBeans); `src/main.java` standalone (IntelliJ)
- Study aid: `repaso_quiz.html`

**BASE_DE_DATOS_2** — PostgreSQL, SQL Server, Oracle (5 semanas, no code — notes + slides):
- Markdown notes per semana (`REPASO_S*.md`), PowerPoint slides
- Topics: PostgreSQL intro → structure → comparison with SQL Server → Oracle → BD familiar case
- Study aid: `GUIA_PASO_A_PASO_BD.md`, `QUIZ_40_PREGUNTAS.html`

**DISEÑO_DE_PATRONES** — SOLID principles + design patterns (4 semanas, IntelliJ + NetBeans):
- SEMANA_1: SRP (Single Responsibility) — NetBeans `S02-SRP/`
- SEMANA_2: OCP, LSP principles — NetBeans `S0s2.s2-PrincipiosSLiskovyOpenClose/` (Aves, Notificaciones)
- SEMANA_3: ISP, DIP — IntelliJ `SEGREGACION_INTERFACE/`, `INVERSION_DEPENDENCIA/`; NetBeans `S03-Diseño_de_Patrones/`
- SEMANA_4: Factory Method + Singleton — IntelliJ `PatronFactoryMethod/`, `PatronSingleton/`; 6 exercises: Vehiculo, Cafeteria, Pagos, Notificacion, Documento, Logistica
- Markdown summaries per semana (`SEMANA_*_RESUMEN.md`); study aid: `QUIZ_DISENO_PATRONES.html`

**HERRAMIENTA_PARA_LA_TOMA_DE_DECISIONES** — Excel decision tools (6 semanas, no Java):
- Python helper: `excel_tool.py`; prompt reference: `Prompts_Referencia.md`
- Topics: Excel fundamentals → data validation/audit → decision functions → BUSCARV/BUSCARH/COINCIDIR
- SEMANA_6: PA3 (graded activity, `PA3_CALIFICADO.pdf`)

**REDES_Y_COMUNICACION_DE_DATOS** — networking (1 semana, very sparse):
- `SEMANA 1/sistemas_numericos.html` — number systems
- `SEMANA 1/codio_pagina_web/` — web page exercise
- Packet Tracer task (docx + pdf)

**SISTEMAS_OPERATIVOS** — OS concepts (6 semanas, no code — docs/slides):
- VirtualBox/Ubuntu installation guide (docx + pdf)
- SEMANA_1–2: Markdown summaries + PDFs/PPT
- SEMANA_5: PC1 (parcial exam, docx + pdf)
- SEMANA_6: Interbloqueo (deadlocks) — `RESUMEN_Interbloqueo.md`

**TALLER_DE_PROGRAMACION_WEB** — HTML/CSS web development (6 semanas + project):
- SEMANA_1: HTML/CSS intro (PDF only)
- SEMANA_2: Basic HTML pages (`prueba.html`, `tarea1.html`, `Tarea.html`)
- SEMANA_3: Forms and multimedia (`index.html`, `index2.html`)
- SEMANA_4: Tables (`demo.html` + `css/estilos.css`)
- SEMANA_5: `ChapaTuPromo/` — multi-page website (index, login, formulario, menu, ofertas, pedidos, registrar + CSS)
- SEMANA_6: CSS customization (`REPASO_SEMANA6_CSS.md`)
- `MaxFinance/index.html` — standalone financial app project

## External Search Data Format

The search projects share a consistent data model:

- `clientes.txt`: pipe-delimited text — `ID|Nombre|Ciudad|Tipo|Monto` (e.g., `001|Juan Perez|Lima|Cliente|100`)
- `clientes.dat`: fixed-length binary records, each 80 bytes (space-padded), read via `RandomAccessFile`
- `indice.bin`: binary index mapping IDs to byte offsets in `clientes.dat`
- `cubetas/hash0.txt`, `hash1.txt`, ...: bucket files for hash-based search (hash = `Integer.parseInt(id) % numCubetas`)
- `resultados.txt`: CSV log of search results — `method,id,diskAccesses,timeMs`

The most complete implementation is `BusquedaExternaAvanzada_SinglePackage` — use it as the reference when working on search algorithm tasks.
