# CLAUDE.md — Proyecto VoIP Call Center Multisede (UTP)

This file provides guidance to Claude Code when working in this repository.
Última actualización: 2026-05-23.

## Qué es este directorio

Artículo académico para el curso **Redes y Comunicación de Datos I** (UTP, ciclo V, Ing. de Sistemas e Informática). El entregable es un único paper en PDF proponiendo una **arquitectura de red IPv4 convergente para optimizar la infraestructura VoIP** de un call center peruano con 4 sedes en Lima.

**Título oficial del proyecto** (NO modificar sin pedirlo):
> "Diseño e implementación de arquitectura de red IPv4 convergente para la optimización de infraestructura VoIP en un call center multisede"

**Equipo** (4 integrantes):
- Vega Jauregui Diego Joaquín — U21205723
- Benites Corazón Max Anderson — U24217839
- Quispe Arias Kevin Antonio — U19213082
- Tasayco Magallanes Giordano Martin — U22216715

**Docente**: Ing. Gerald Paul Medina Perez.
**Entrega**: mayo del 2026 (NO 2025 — el documento original venía con la fecha desactualizada).

## Caso de estudio

Call center peruano:
- **4 sedes en Lima**: Bellavista (hub — 3 servidores, switch MikroTik gestionable, doble ISP 75 Mbps empresarial + 600 Mbps Claro), Magdalena, Los Olivos, Independencia (spokes — 1 servidor cada una, ISP propio).
- **100–140 agentes simultáneos** sobre ViciDial v11.
- **2–4 incidencias semanales** de degradación de audio o caídas, sin correlación con horas pico → descarta saturación de ancho de banda como causa raíz, apunta a configuración.
- Inversión adicional estimada del proyecto: **< S/ 1,100** (reutiliza MikroTik existentes).

## Solución propuesta (6 componentes + SOP)

1. VPN site-to-site WireGuard sobre MikroTik (malla 4 sedes)
2. Segmentación VLAN 802.1Q (10 voz / 20 datos / 30 gestión)
3. QoS EF (DSCP 46) para RTP/SIP
4. Failover automático dual-ISP en Bellavista (Check Gateway + Netwatch)
5. ViciDial maestro-réplica con alta disponibilidad ≥ 99.5 %
6. Monitoreo activo con Zabbix vía SNMP
7. SOP organizacional con 4 niveles (N1–N4) y flujo cronometrado (T+0 a T+24 h)

## Compromiso del equipo — Escenario C (decisión 2026-05-23)

El equipo **SÍ va al cliente real**, captura línea base con Wireshark/Torch/logs ViciDial, ejecuta simulación completa en Packet Tracer + GNS3, realiza piloto controlado en Bellavista y obtiene **acta firmada por la gerencia** del call center.

Las cuantificaciones (latencia <150 ms, jitter <30 ms, failover <10 s, disponibilidad ≥99.5 %) provienen de literatura citada (ITU-T G.114, Thangam et al., Simanjuntak et al.) — **no son inventadas**, son las metas que el piloto debe alcanzar.

**Si en una sesión futura se sugiere suavizar objetivos o quitar evidencias**, validar primero contra esta decisión. No rebajar el alcance por iniciativa propia.

## Estado del documento (33 páginas PDF LaTeX)

Estructura final:
- Portada (logo UTP, título nuevo, autores, docente, mayo 2026) — usa `portada_utp.tex`
- Abstract en inglés + Keywords
- I. Introducción
- II. Definición del problema (con Tabla 0 — placeholders esperando línea base real)
- III. Objetivos — 1 general + **12 específicos** ordenados como "gradas" del general:
  - 1–3: diagnóstico
  - 4–5: diseño
  - 6–9: implementación técnica
  - 10: procesos organizacionales (SOP)
  - 11–12: validación (simulación + piloto + acta cliente)
- IV. Marco teórico — **16 subsecciones**: 4.1 VoIP, 4.2 QoS, 4.3 VLANs, 4.4 VPN, 4.5 Failover, 4.6 ViciDial, 4.7 Plan IP, 4.8 Seguridad, 4.9 Cálculo BW, 4.10 Arquitectura+balanceo, 4.11 HA+escalabilidad, 4.12 Firewall, 4.13 Monitoreo SNMP/Zabbix, 4.14 Simulación PT/GNS3, 4.15 ITSM/ITIL, 4.16 MikroTik RouterOS
- V. Diagnóstico (5.7 inventario + 5.8 metodología investigación campo + 5.9 metodología medición Tabla 5)
- VI. Propuesta — 6 componentes en formato **Diagnóstico → Análisis → Justificación → Solución** + 6.7 SOP (Tablas 6 niveles N1–N4 y 7 flujo T+N)
- VII. Diseño de red (topología + Tabla 2 plan IP 4 sedes)
- VIII. Comparativa Antes vs. Después (Tabla 3, celdas pendientes de captura)
- IX. Resultados (9.1 métricas esperadas + 9.2 plan simulación Tabla 8 + 9.3 validación cliente)
- X. Conclusiones
- XI. Referencias APA 7 (5 papers: Álvarez 2023, Aung & Thein 2020, Pudelko 2020, Simanjuntak 2023, Thangam 2024)
- Anexo A — Índice/checklist de las 20 evidencias

## 20 evidencias EV-XX (3 ondas de captura)

- **Onda 1 — Línea base pre-implementación**: EV-01 Wireshark RTP latencia/jitter, EV-02 pérdida, EV-03 Torch BW, EV-04 bitácora ViciDial, EV-05 fotos rack 4 sedes, EV-06 export config MikroTik, EV-07 acta entrevista admin red, EV-08 contratos ISP, EV-09 diagrama red actual.
- **Onda 2 — Diseño y simulación**: EV-10 diagrama propuesto, EV-11 topología Packet Tracer, EV-12 VLANs GNS3, EV-13 VPN WireGuard GNS3, EV-14 QoS DSCP MikroTik, EV-15 prueba caídas, EV-16 prueba failover cronometrado, EV-17 prueba latencia bajo carga, EV-18 prueba carga Iperf3.
- **Onda 3 — Piloto y validación**: EV-19 dashboard Zabbix antes/después, EV-20 acta firmada por gerencia del call center.

Cada bloque en el `.md` es un blockquote con instrucciones de qué capturar, herramienta, responsable y fecha. **No contienen datos inventados** — son espacios reservados con líneas en blanco (`____________`) para que el equipo los llene.

## Decisiones de redacción aplicadas en esta sesión

- **Citas convertidas de IEEE [1]-[5] a APA 7** `(Autor et al., Año)` en texto y entradas alfabéticas con DOI en §XI.
- **Símbolo `§` reemplazado por palabra "sección X.Y"** (67 ocurrencias). El usuario no lo conocía y prefirió legibilidad sobre convención académica. Aplicar mismo criterio con otros símbolos esotéricos.
- **Fecha 2025 → 2026** en portada y placeholders EV-XX.
- **Tipografía LaTeX**: DejaVu Serif 11pt, interlineado 1.15, color institucional rojo UTP `#A6192E`.
- **Saltos de página**: cada apartado romano (I, II, III…) inicia en página nueva mediante contador `h2count > 2` en `latex_header.tex` (las primeras 2 H2 — Abstract y Keywords — comparten página tras la portada).
- **Callouts EV-XX**: blockquotes que se renderizan como `tcolorbox` amarillos con borde naranja en LaTeX; cajas amarillas con CSS en HTML.
- **Tablas anchas**: filtro `fit-tables.lua` fuerza columnas a ancho `1/N \linewidth` para que pandoc genere `p{}` con wrap automático (sin esto, longtable se desborda).

## Archivos del workspace

| Archivo | Propósito |
|---|---|
| `Articulo_Universitario_claude.md` | **Fuente única editable** del artículo |
| `Articulo_Universitario_claude_latex.pdf` | **Entregable final** (33 pp, ~175 KB) — generado con xelatex |
| `Articulo_Universitario_claude.html` / `.pdf` | Versión Chromium (más colorida, alternativa) |
| `portada_utp.tex` | Portada LaTeX nativa (titlepage con logo, título, autores, fecha) |
| `latex_header.tex` | Header custom: colores UTP, callouts tcolorbox, saltos por sección, tipografía |
| `fit-tables.lua` | Filtro pandoc para wrap de tablas anchas |
| `styles.css` | Estilo HTML para versión Chromium |
| `Utplogonuevo.svg.png` | Logo institucional UTP |
| `RETROALIMENTACION.md` | **Rúbrica efectiva** — leer antes de cualquier edición sustantiva |
| `Articulo_Universitario.md` | Original del equipo (referencia histórica, no editar) |
| `Articulo_Universitario_gemini.md` | Versión de comparación de otra IA — **no usar**, contiene métricas inventadas |

## Comandos de regeneración

**PDF LaTeX (entregable final):**
```bash
pandoc Articulo_Universitario_claude.md \
  --from=markdown \
  --pdf-engine=xelatex \
  --top-level-division=section \
  --include-in-header=latex_header.tex \
  --lua-filter=fit-tables.lua \
  --variable geometry:margin=2.5cm \
  --variable geometry:a4paper \
  --variable mainfont="DejaVu Serif" \
  --variable sansfont="DejaVu Sans" \
  --variable monofont="DejaVu Sans Mono" \
  --variable fontsize=11pt \
  --variable lang=es \
  --variable linestretch=1.15 \
  --variable secnumdepth=0 \
  --output Articulo_Universitario_claude_latex.pdf
```

**PDF Chromium (alternativa colorida):**
```bash
pandoc Articulo_Universitario_claude.md --from=gfm --to=html5 --standalone \
  --css=styles.css --metadata lang=es \
  --output Articulo_Universitario_claude.html
chromium --headless --disable-gpu --no-sandbox \
  --print-to-pdf=Articulo_Universitario_claude.pdf --no-pdf-header-footer \
  file://$(pwd)/Articulo_Universitario_claude.html
```

## Reglas de trabajo (NO inventar)

El usuario verifica activamente si se inventa contenido. Reglas firmes:

1. **Nunca fabricar métricas, mediciones o resultados** que no estén en el original ni hayan sido capturados por el equipo. Si una celda requiere un dato no medido, usar marcadores tipo `_Por capturar — ver EV-XX_` o líneas en blanco.
2. **Citar umbrales solo si vienen de fuentes verificables** (ITU-T G.114, los 5 papers APA listados). Esos no son invención.
3. **Separar explícitamente** lo que está en el documento del equipo vs. lo que se amplía aquí. Al ampliar algo, decir claramente *"esto lo agregué porque…"*. Nunca presentar añadidos como si vinieran del original.
4. **Mantener la estructura I–XI** y las 16 subsecciones del marco teórico. Si se propone agregar/quitar, validar contra los 12 objetivos y la retroalimentación.
5. **Antes de cualquier edición sustantiva, leer `RETROALIMENTACION.md`** — sigue siendo el rubric efectivo.

## Próximos pasos (al cierre de esta sesión, sin elegir)

El documento queda listo para que el equipo empiece el levantamiento de campo. Tres caminos posibles cuando se retome:

1. **Revisar otra sección del documento** (Diagnóstico §V, Propuesta §VI, Diseño §VII, Resultados §IX)
2. **Empezar a capturar evidencias** — EV-05 a EV-09 son las más rápidas (fotos rack + export config + entrevista + diagrama actual)
3. **Continuar con el cliente** y volver cuando haya evidencias capturadas que insertar

División sugerida del equipo (4 integrantes):
- Integrante 1: EV-01, EV-02, EV-03, EV-19 (mediciones Wireshark + Torch + Zabbix)
- Integrante 2: EV-05, EV-06, EV-07, EV-08 (campo + documentación)
- Integrante 3: EV-09, EV-10, EV-11, EV-12, EV-13 (diagramas + simulación)
- Integrante 4: EV-04, EV-14, EV-15, EV-16, EV-17, EV-18, EV-20 (logs + pruebas + acta)
