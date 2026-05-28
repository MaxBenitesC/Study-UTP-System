# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

> Course-scoped notes for **Base de Datos II** (UTP, CICLO_V). The parent `/home/ilkay/Documentos/UTP/CLAUDE.md` covers the broader repo; this file overrides its (outdated) BD2 section.

## What lives here

No buildable code — only study material. The folder is a per-week archive of:

- **`SEMANA_N/REPASO_SN.md`** — markdown summaries of each session's slide deck. These are the primary work product; new sessions usually mean "read the new PPT/PDF and produce a `REPASO_SN.md` in the same style as the existing ones".
- **`SEMANA_N/*.pptx` / `*.pdf`** — source slide decks and lab handouts (read-only inputs).
- **Top-level study aids**:
  - `GUIA_PASO_A_PASO_BD.md` — 10-step methodology for going from a problem statement to a SQL schema (Entidades → Atributos → Relaciones → Cardinalidad → tablas intermedias N:M → MER → DDL → INSERT).
  - `QUIZ_40_PREGUNTAS.html` — standalone self-graded quiz (open in a browser, no server).

## Week-by-week topic map

| Semana | Tema | Notas |
|--------|------|-------|
| 1 | Introducción a PostgreSQL | `REPASO_S1.md` |
| 2 | Estructura de funcionamiento (PostgreSQL) | `REPASO_S2.md` + `tienda.png` |
| 3 | PostgreSQL vs SQL Server + MER (caso veterinaria) | `REPASO_S3.md` + `S3_s2_veterinaria_bd.pdf` |
| 4 | Introducción a Oracle | `REPASO_S4.md` |
| 5 | Estructura Oracle + **caso BD familiar** | `REPASO_S5.md`, `CASO_BD_FAMILIAR.md` |
| 6 | Laboratorio Oracle/SQL Server — consultas y funciones | PDFs + `Guia_SQL_Oracle_Practica.docx` (no `REPASO_S6.md` yet) |
| 7 | Diseño de Bases de Datos Distribuidas | slides only |
| 8 | Fragmentación vertical y horizontal en PostgreSQL | slides + práctica PDF |
| 9 | Transacciones y control de concurrencia (distribuidas) | slides + práctica PDF |

Unit 1 (relacionales: PostgreSQL/SQL Server/Oracle) runs S1–S6; Unit 2 (distribuidas: diseño/fragmentación/concurrencia) runs S7–S9.

## Conventions when editing notes

- `REPASO_SN.md` files share a consistent header: `# Repaso Semana N - <tema>` + a `**Curso:** Base de Datos II | **Unidad:** X | **Sesión:** Y` line + `## Logro de la Sesión`. Match that scaffold when adding a new week.
- Use markdown tables for comparisons (e.g. PostgreSQL vs SQL Server in S3, Ventajas/Desventajas blocks). The existing notes are dense with tables — keep that style.
- Spanish throughout; SQL keywords stay uppercase; product names in bold on first mention.
- `CASO_BD_FAMILIAR.md` is an applied worked example of `GUIA_PASO_A_PASO_BD.md` — any new "caso práctico" should follow the same 10-step structure so the methodology stays the canonical source.

## Things NOT in this folder

- No SQL scripts, no DDL files, no database dumps — schemas appear inline inside markdown code fences only.
- No build system, no tests, no CI. `/init`-style commands (build/lint/test) do not apply.
