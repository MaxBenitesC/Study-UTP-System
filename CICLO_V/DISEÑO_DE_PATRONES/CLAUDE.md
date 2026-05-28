# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Scope

This is the **DISEÑO_DE_PATRONES** course folder (UTP, CICLO_V). The parent `../../CLAUDE.md` covers the full UTP coursework tree and the general Java build conventions — this file only documents what is specific to working *inside* this course.

## Weekly layout

Each `SEMANA_N/` folder typically contains three layers in parallel:

1. **Professor's source bundle** — `.zip` or `.rar` of the original IDE project (kept untouched as a reference).
2. **Unzipped project** — the same code extracted into a working folder. For SEMANA 6+ the unzipped folder mirrors the archive's exact filename, so it may contain mojibake (see encoding gotcha below).
3. **Markdown notes** — `SEMANA_N_RESUMEN.md` (at the course root) and/or topic-specific analyses inside the SEMANA folder (e.g. `SEMANA_6/Analisis_Ejercicios_Clase.md`).

Course progression:

| SEMANA | Topic | Has runnable code? |
|---|---|---|
| 1 | SRP | yes (NetBeans `S02-SRP/`) |
| 2 | OCP, LSP | yes (NetBeans `S0s2.s2-PrincipiosSLiskovyOpenClose/`) |
| 3 | ISP, DIP + intro patterns | yes (IntelliJ `SEGREGACION_INTERFACE/`, `INVERSION_DEPENDENCIA/`, `SEMANA_3_DP/`; NetBeans `S03-Diseño_de_Patrones/`) |
| 4 | Factory Method + Singleton | yes — 6 student exercises (`Ejercicio_1` … `Ejercicio_6`) plus 2 reference patterns (`PatronFactoryMethod/`, `PatronSingleton/`), all IntelliJ |
| 6 | Singleton + Prototype (creational) | yes (NetBeans `Sesion6/` and `S06_s1-PatronSingletonyPrototype/`) |
| 7 | Factory + Builder (creational) | docs only — `.zip`/`.rar` present, not extracted |
| 8 | Adapter + Facade (structural) | docs only |
| 9 | Decorator + Composite (structural) | docs only |

The parent CLAUDE.md only documents up through SEMANA 4 — SEMANAs 6–9 are newer and only described here.

## Running the code

The general build-system rules in the parent CLAUDE.md apply (NetBeans Ant vs IntelliJ `.iml` vs standalone `javac`). This course's specifics:

**SEMANA_4 `Ejercicio_N/`** — each is an independent IntelliJ project with sources under `src/Ejercicio_<topic>/`. From a single exercise:

```bash
cd SEMANA_4/Ejercicio_1
javac -d out src/Ejercicio_Vehiculo/*.java
java -cp out Ejercicio_Vehiculo.main
```

Note that the main class is lowercase `main` (not `Main`) in the SEMANA_4 exercises — the package + class capitalization matters when invoking `java`.

**SEMANA_6 NetBeans projects** — use Ant:

```bash
cd SEMANA_6/Sesion6/Sesion6
ant -f build.xml run
```

The second `Sesion6` folder (the project root with `build.xml`) is the one Ant expects, not the outer wrapper.

## Naming conventions used in the exercises

Code across SEMANA_3 and SEMANA_4 follows a consistent shape — preserve it when adding new exercises:

- **Factory Method exercises** (SEMANA_4 Ejercicios 1–6): one abstract product (e.g. `Vehiculo`, `Pagos`, `Notificacion`), one creator interface suffixed `Factory` (e.g. `VehiculoFactory`), and one `<Concrete>Factory` per concrete product (`AutoFactory`, `MotoFactory`, `CamionFactory`). The driver class is `main.java` (lowercase) inside a package named `Ejercicio_<topic>`.
- **Singleton exercises**: lazy initialization with a private constructor and static `getInstance()`. The reference implementation is `SEMANA_4/PatronSingleton/src/Patrones/ConfiguracionApp.java`.
- **Prototype exercises** (SEMANA_6): a `Prototype` interface with `clone()` returning the interface type; concrete classes cast on the caller side. See `SEMANA_6/Sesion6/Sesion6/src/prototype/` for the canonical pattern.

When the user asks to "add another product" or "add a new factory", match this layout rather than introducing generics, registries, or reflection-based factories — the course is teaching the classical GoF shape.

## Encoding gotcha

The SEMANA_6 folder `S06_s1-PatronSingletonyPrototype/S06.s1-Patr+�nSingletonyPrototype/` contains mojibake from a CP437/UTF-8 unzip mismatch (the `+�` is a corrupted `ó`). Several files inside use the same corrupted character in their package and filename (`patr+�nsingletonyprototype/`, `S06S1Patr+�nSingletonyPrototype.java`). Quote the path or use tab-completion; do not try to "fix" the names — `build.xml`, `manifest.mf`, and the package declarations all reference the corrupted form, so renaming would break the build. If you need a clean copy, re-extract the original `S06_s1-PatronSingletonyPrototype.zip` with `unzip -O UTF-8`.

## What lives where (when looking for something)

- **Concept summaries / quiz prep**: `SEMANA_N_RESUMEN.md` at the course root, `QUIZ_DISENO_PATRONES.html` (SEMANAs 1–4) and `QUIZ_SEMANAS_6_A_9.html` (SEMANAs 6–9).
- **Per-week deep analyses**: inside each SEMANA folder (e.g. `Analisis_Ejercicios_Clase.md`, `Ejercicios_Resueltos_Patrones_Adapter_Facade.md`, `Ejercicios_Propuestos.md`). These are the most reliable explanation of *why* a given exercise is structured the way it is.
- **Professor's slides**: `S0N_s1-*.pdf` (and the matching `.md` when one exists) inside each SEMANA folder.

## Estructura de Datos y Algoritmos (ED&A)

Para las sesiones de Estructura de Datos, se aplica la metodología de **"Modo Combate"**:
1. El sistema proporciona un enunciado basado en casos de negocio (ej. gestión de parqueo).
2. El usuario diseña la estructura (Clases Nodo, Lista, Objeto de Datos).
3. Implementación desde cero enfocada en la lógica de punteros y algoritmos.

### Convenciones de Listas Enlazadas
- **Clase Nodo**: Contiene el objeto de datos (ej. `Vehiculo`) y un puntero `siguiente`.
- **Clase Lista**: Atributo `inicio` (head) privado. Los punteros auxiliares (`p`, `q`, `nuevo`) deben ser **locales** a los métodos para evitar errores de estado.
- **Inserción**: Manejar siempre el caso de `lista vacía (inicio == null)` y `lista con elementos`.
- **Recorrido**: Usar `while (p != null)` para procesar todos los nodos. Evitar `p.getSgte().getSgte()` para prevenir `NullPointerException`.
- **Aleatoriedad**: Uso de `(int) (Math.random() * (max - min + 1) + min)` para simular datos de entrada.

## Publishing to GitHub Pages

The course content is mirrored to **https://github.com/MaxBenitesC/Study-UTP-System** (public). There is no local clone of that repo under `~/Documentos/UTP/` — this directory is the user's local working tree, kept in sync manually. To publish a change:

1. Clone the repo to a working directory: `git clone https://github.com/MaxBenitesC/Study-UTP-System.git /tmp/study-utp-system`
2. Copy the file to the **remote-style path** (see naming mismatch below).
3. Commit (the user's commit style is short, ALL-CAPS-ish prefixes — e.g. `"Diseno de Patrones: Quiz interactivo Semanas 6 a 9 ..."`).
4. `git push origin main`. HTTPS credentials are cached on this machine; no token prompt needed.

**Local ↔ remote naming mismatch** (load-bearing — do not "fix" either side):
- Local: `CICLO_V/DISEÑO_DE_PATRONES/` (Roman, underscores, ñ)
- Remote: `CICLO 5/DISENO DE PATRONES/` (Arabic, spaces, ASCII)

URL pattern for Pages: `https://maxbenitesc.github.io/Study-UTP-System/CICLO%205/DISENO%20DE%20PATRONES/<file>` — spaces become `%20`.

**Pages needs `.nojekyll` at the repo root.** Without it, GitHub Pages tries to build the repo with Jekyll and fails (the .md files with YAML frontmatter and the mojibake folders break the build), which silently keeps serving an old snapshot. The `.nojekyll` was added in commit `8c56349` on 2026-05-25 — if a future Pages deploy starts failing again, verify that file is still present before debugging anything else.
