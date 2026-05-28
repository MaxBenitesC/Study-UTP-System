# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this directory is

Course folder for **Redes y Comunicación de Datos I** (UTP, CICLO_V). Two kinds of content live side by side:

1. **`SEMANA*/` folders** — almost entirely course-issued PDFs (`PPT_SESIÓN_*.pdf`). No code, no build system. Treat these as read-only reference material.
2. **`PROYECTO/`** — the active deliverable: a LaTeX/Pandoc academic article on a multisede VoIP call center. **This is where almost all real work happens.** It has its own `PROYECTO/CLAUDE.md` with full context (team, decisions, regeneration commands, "do not invent" rules). **Read it before doing anything inside `PROYECTO/`.**

The parent `/home/ilkay/Documentos/UTP/CLAUDE.md` documents the broader UTP repo but its REDES section is outdated — trust this file and `PROYECTO/CLAUDE.md` over it for anything in this course.

## SEMANA folder contents (no commands needed)

- `SEMANA 1/` — note the **space** in the folder name (only one that uses it; quote it in shell commands). Contains:
  - `sistemas_numericos.html` and `codio_pagina_web` (also an HTML file, no extension) — standalone study-aid pages, open in a browser
  - `TAREA PACKET TRACER_1.docx` / `.pdf` — Packet Tracer assignment statement
- `SEMANA_5/` through `SEMANA_9/` — slide decks only (`PPT_SESIÓN_*.pdf`)

There is no SEMANA 2/3/4 in this folder.

## Working with the PPT PDFs

If asked to summarize, transcribe, or extract content from any `PPT_SESIÓN_*.pdf`, use the `transcribir_PDF_to_MD` skill — it converts academic PDFs to structured Markdown and writes a `.md` next to the source. Don't roll a custom extraction.

## Project work conventions (carried from `PROYECTO/CLAUDE.md`)

When operating inside `PROYECTO/`, the rules in `PROYECTO/CLAUDE.md` apply in full. The two that bite hardest if forgotten:

- **Never fabricate metrics, measurements, or results.** Empty cells stay empty (`____________` or `_Por capturar — ver EV-XX_`). The user actively checks for invented numbers.
- **Project deadline year is 2026, not 2025.** The original document had a stale year; any new placeholder dates should be 2026.

For everything else inside `PROYECTO/` (regeneration commands for the PDF, the EV-XX evidence taxonomy, the 12-objective structure, the team roster, etc.), defer to `PROYECTO/CLAUDE.md` rather than re-deriving it.
