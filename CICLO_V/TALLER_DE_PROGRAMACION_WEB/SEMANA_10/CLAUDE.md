# CLAUDE.md — Proyecto APF2 ChapaTuPromo

Este archivo documenta todo el contexto y decisiones del Avance de Proyecto Final 2 del curso **Taller de Programación Web** (UTP, Ciclo V, Sección 21003).

---

## 1. Contexto del proyecto

**Nombre:** ChapaTuPromo
**Estudiante:** Max Benites Corazón (código U24217839)
**Docente:** Jesamin Melissa Zevallos Quispe
**Fecha entrega:** Semana 10 del Ciclo V (2026)
**Objetivo del sitio:** plataforma web que permite a tiendas vender productos próximos a vencer con descuentos, reduciendo el desperdicio de alimentos.

**Ruta del proyecto:**
```
/home/ilkay/Documentos/UTP/CICLO_V/TALLER_DE_PROGRAMACION_WEB/SEMANA_10/ChapaTuPromo/
```

---

## 2. Requisitos del APF2 (lo que pide la profesora)

El APF2 requiere los siguientes 7 puntos:

1. HTML + CSS puro (sin JavaScript ni frameworks)
2. Aplicación de estilos CSS para mejorar la presentación visual
3. Organización visual adecuada del contenido
4. Uso de **Flexbox**
5. Uso de **Grid Layout en cada página**
6. **Animaciones o transiciones CSS en cada página**
7. **Diseño responsive con Media Queries**

Entrega: informe (Word/PDF) con carátula, descripción, capturas de páginas con CSS, capturas del código de animaciones por página, link al repositorio.

Exposición individual: 3 minutos.

---

## 3. Estructura de archivos del proyecto

```
SEMANA_10/
├── APF1_ChapaTuPromo.pdf            (informe APF1 original)
├── APF1_ChapaTuPromo.md             (transcripción del APF1)
├── EXPOSICION_APF2.md               (guión de exposición 3 min)
├── CLAUDE.md                        (este archivo)
└── ChapaTuPromo/
    ├── css/
    │   └── estilos_chapatupromo.css (753 líneas, 20 secciones)
    ├── logo/
    │   └── chapatupromo.png
    ├── index.html
    ├── login_chapatupromo.html
    ├── registrar_chapatupromo.html
    ├── menu_chapatupromo.html
    ├── formulario_chapatupromo.html
    ├── pedidos_chapatupromo.html
    └── ofertas_chapatupromo.html
```

**Naming intencional:** mantener `*_chapatupromo.html` (no renombrar a los nombres genéricos del enunciado). Da identidad de marca.

---

## 4. Restricciones del temario (CSS permitido)

**Lo que la profesora enseñó en las semanas 6 a 9 según los .md de clase:**

### SEMANA 6 — Fundamentos CSS
- Formas de usar CSS (inline, interno, externo)
- Tipografía: `font-family`, `font-size`, `font-style`, `font-weight`, `font` (shortcut)
- Texto: `text-wrap`, `text-shadow`
- Modelo de cajas (margin / border / padding / content)
- Dimensiones: `width`, `height`, `min/max-width`, `min/max-height`
- `margin` / `padding` shorthand (1, 2, 3, 4 valores)
- Imágenes: `object-fit: fill | contain | cover`
- FontAwesome via CDN

### SEMANA 7 — Animaciones, Transiciones, Transformaciones
- `transition`
- `transform` (con `translate`, `rotate`)
- `@keyframes` con `from` / `to`
- `animation` con `infinite`
- Pseudoclases: `:hover`, `:active`, `:focus`

### SEMANA 8 — Flexbox y Grid Layout
- `display: flex`
- `flex-direction: row | row-reverse | column | column-reverse`
- `flex-wrap: nowrap | wrap | wrap-reverse`
- `justify-content` (todos los valores)
- `align-items` (todos los valores)
- `display: grid`
- `grid-template-columns` con `1fr`

### SEMANA 9 — Diseño Responsivo
- `@media (max-width: ...)`
- `gap`
- `grid-template-rows`
- `grid-template-areas`
- `grid-area`
- `border-radius`
- Patrones de combinación Flexbox + Media Queries

### Propiedades usadas que NO aparecen literal en los .md
El usuario verificó y confirmó que la profesora SÍ las mencionó verbalmente. Estas pueden usarse:
- `transform: scale()` y `translateY()`
- `border-collapse: separate`, `border-spacing: 0`
- `border-top-left-radius` / `border-top-right-radius`
- `box-sizing: border-box`
- `min-height: 100vh`
- `auto` en `grid-template-rows`
- `cursor: pointer`
- `outline: none`
- `text-transform: uppercase`
- `text-decoration: none/underline`
- `list-style-position: inside`
- `justify-items: center`
- `border-left: Xpx solid`
- Google Fonts via `<link>`

### Lo que NO se puede usar (no enseñado)
- JavaScript (cualquier forma)
- Frameworks CSS (Bootstrap, Tailwind, etc.)
- Variables CSS (`--var`)
- Preprocesadores (SASS, LESS)
- Pseudoclases avanzadas (`:nth-child`, `:first-child`, `:last-child`)
- Pseudoelementos (`::before`, `::after`)
- `position: absolute/fixed/sticky`
- `vh`, `vw` (excepto `100vh` en body)
- `calc()`
- `box-shadow`

---

## 5. Paleta de colores actual

Última actualización: el usuario pidió púrpura mucho más oscuro + efecto inverse estilo redes sociales.

```
PRIMARIOS:
  #4a0e7a  púrpura oscuro       (header, h2, botones, primario)
  #2e0854  púrpura aún más oscuro (nav, hover botones)
  #15022e  púrpura casi negro    (footer)
  #2a0744  sombras
  #ffd700  dorado vibrante      (acentos, hover inverse)
  #e6c200  dorado oscuro        (hover de dorado)

LILAS:
  #c39bd3  lila vibrante
  #d2b4de  lila claro
  #e9d4f5  lila muy claro (bordes/hover suave)
  #faf5ff  lila clarísimo (fondo de td)
  #f4eef8  body con tinte lila

SEMÁNTICOS (ofertas - sistema semáforo):
  #c0392b  rojo  → "Vencen hoy" (con animación latido)
  #e67e22  naranja → "Vencen mañana"
  #4a0e7a  púrpura → "Vencen en 2 días"

NEUTROS:
  #333333  texto principal
  #555555  texto secundario
  #888888  texto sutil
  #aaaaaa, #cccccc, #dddddd, #eeeeee, #f0f0f0, #fafafa
```

**Importante:** No usar `#6c3483` (era el morado anterior, ya migrado).

---

## 6. Arquitectura CSS

### Una sola hoja global: `css/estilos_chapatupromo.css`

Organizada en **20 secciones numeradas** con comentarios estilo `/* 1.BODY: */` (simples, sin separadores `====`).

```
1.BODY        — body con Grid maestro 3 filas (auto 1fr auto)
2.HEADER      — flexbox + logo con animación flotar
3.NAV         — flexbox + hover inverse dorado
4.SECTIONS    — h2, h3, max-width centrado
5.TABLAS      — border-radius, hover inverse, sin grilla cuadrada
6.FORMS       — inputs con box-sizing border-box
7.BOTONES     — submit + enlaces globales
8.FOOTER      — flexbox + redes sociales
9.HERO        — index, .btn-hero con scale al hover
10.SECCION-INFO — cajita blanca con borde lateral
11.PASOS-GRID — 5 columnas, cards con hover inverse
12.SECCION-TIENDAS
13.MENU       — 1 columna, cards con border-radius
14.FORMULARIO — productos en 2 columnas, focus dorado
15.PEDIDOS    — 3 stat cards con hover inverse
16.OFERTAS    — 3 badges con sistema semáforo + latido
17.LOGIN      — grid centrado, button con translateY
18.REGISTRAR  — 2 columnas con cards
19.ANIMACIONES — @keyframes flotar + latido
20.RESPONSIVE — @media 900px (tablet) + 600px (móvil)
```

### Grid maestro en `<body>`
```css
body {
  display: grid;
  grid-template-rows: auto 1fr auto;  /* header / contenido / footer */
  min-height: 100vh;
}
```
Aplicado a las 7 páginas. Garantiza footer pegado abajo siempre.

### Grids internos por página
| Página | Clase grid | Columnas |
|---|---|---|
| index | `.pasos-grid` | 5 cols (desktop) → 2 (tablet) → 1 (móvil) |
| menu | `.menu-grid` | 1 col (categorías apiladas) |
| formulario | `.productos-grid` | 2 cols → 1 (móvil) |
| pedidos | `.pedidos-stats` | 3 cols → 1 (móvil) |
| ofertas | `.ofertas-resumen` | 3 cols → 1 (móvil) |
| login | `.login-grid` | 1 col centrado con `justify-items: center` |
| registrar | `.registro-columnas` | 2 cols → 1 (tablet+móvil) |

### Animaciones @keyframes
```css
@keyframes flotar {       /* logo flota arriba/abajo */
  from { transform: translateY(0); }
  to   { transform: translateY(-6px); }
}

@keyframes latido {       /* badge "vencen hoy" pulsa */
  from { transform: scale(1); }
  to   { transform: scale(1.05); }
}

header img { animation: flotar 1.5s infinite alternate; }
.resumen-hoy { animation: latido 1s infinite alternate; }
```

### Efecto hover INVERSE (estilo redes sociales del footer)
Aplicado en: nav links, `.pasos-grid li`, `.stat-card`, `tr:hover td`.
Patrón: fondo claro/texto oscuro → fondo púrpura/texto blanco/acento dorado.

### Media Queries
- **Tablet** (`max-width: 900px`): grids colapsan a 2 cols, nav vertical
- **Móvil** (`max-width: 600px`): grids a 1 col, hero apilado, tablas con letra chica

---

## 7. Estilo de código exigido

### Comentarios estilo junior

**Reglas (importantísimas):**
- En español casual, no formal
- Cortos (idealmente 1 línea, máximo 1 frase)
- **SIN emoticones / emojis**
- **SIN separadores tipo `========`** (parece IA, el usuario lo detecta)
- Formato de header de sección: `/* 1.BODY: */`, no `/* ===== 1. BODY ===== */`
- Comentarios in-line solo donde aporten valor real (no explicar lo obvio)
- Tono junior: a veces dudas, frases simples, español peruano coloquial OK

**Ejemplos válidos:**
```css
/* 5.TABLAS: */
/* separate permite que los bordes sean curvos */
/* logo que flota arriba y abajo */
/* pa que el padding cuente dentro del ancho */
/* tablet */
/* celular */
```

**Ejemplos PROHIBIDOS:**
```css
/* ===================== 5. TABLAS ===================== */     ← parece IA
/* Estilos para las tablas del proyecto */                       ← redundante
/* Esto le da color al header de la tabla con padding... */     ← muy largo
/* :) */                                                          ← emoji
```

### HTML
- Sin comentarios HTML (el usuario no los pidió)
- Indentación 2 espacios
- Etiquetas semánticas: `<header>`, `<nav>`, `<section>`, `<footer>`, `<table>`

---

## 8. Decisiones tomadas y consolidadas

| # | Decisión | Por qué |
|---|---|---|
| 1 | Mantener naming `*_chapatupromo.html` | Identidad de marca |
| 2 | Una sola hoja CSS global | Más simple para evaluación |
| 3 | Mantener Poppins + Open Sans (Google Fonts) | Identidad visual ya establecida |
| 4 | Grid maestro en body + Grid interno por página | Cumple "Grid en cada página" del APF2 |
| 5 | Transiciones específicas por página | Cada captura del informe será distinta |
| 6 | Breakpoints 900px + 600px | Los mismos del .md de SEMANA 9 |
| 7 | Inputs de contraseña con `type="password"` | Lo básico que la profe puede esperar |
| 8 | Forms con `action="..."` apuntan a páginas útiles (no `#`) | Simula flujo realista |
| 9 | Nav unificado con 6 enlaces en las 7 páginas | Navegación coherente desde cualquier lugar |
| 10 | Mantener colores semánticos rojo/naranja en ofertas | Sistema de semáforo visual |
| 11 | @keyframes en logo (todas las páginas) + badge "vencen hoy" | Cumple requisito de animaciones |
| 12 | Paleta púrpura muy oscuro (#4a0e7a en lugar de #6c3483) | Pedido del usuario, más identidad |
| 13 | Hover inverse en nav, cards y tablas | Replicar efecto redes sociales del footer |

---

## 9. Flujo de navegación

```
            ┌─────────────────────────────────────┐
            │                                     │
registrar ──┴──► login ──► menu ──► formulario ──► pedidos
   ↑              ↑          ↑           ↑           ↑
   └──────────────┴──────────┴───────────┴───────────┘
                  (todas conectadas al index)
```

- `registrar.action` → `login_chapatupromo.html`
- `login.action` → `menu_chapatupromo.html`
- `formulario.action` → `pedidos_chapatupromo.html`

---

## 10. Estado de cumplimiento APF2 (8.5/10)

| Requisito | Estado | Evidencia |
|---|:---:|---|
| HTML + CSS puro | ✅ | Sin JS ni frameworks |
| Estilos CSS visuales | ✅ | 753 líneas, paleta coherente |
| Organización visual | ✅ | Jerarquía clara, identidad fuerte |
| **Flexbox** | ✅ | header, nav, hero-botones, footer-redes |
| **Grid Layout en cada página** | ✅ | body (3 filas) + grid interno por página |
| **Animaciones/transiciones en cada página** | ✅ | `transition` global + `@keyframes flotar` (universal) + `@keyframes latido` (ofertas) |
| **Media Queries** | ✅ | 900px tablet + 600px móvil |

---

## 11. Documentos relacionados

- `SEMANA_10/EXPOSICION_APF2.md` — guión de exposición de 3 minutos con tiempos, frases para decir, código a mostrar, y respuestas a posibles preguntas de la profesora.
- `SEMANA_10/APF1_ChapaTuPromo.md` — transcripción del informe APF1 (semana 5, sin CSS).
- `/home/ilkay/.claude/projects/-home-ilkay-Documentos-UTP/memory/` — memorias persistentes de Claude sobre el usuario y el curso.

---

## 12. Tareas pendientes (al cierre de esta sesión)

1. **Tomar las 14-15 capturas:**
   - 7 capturas del navegador (una por página HTML con CSS aplicado)
   - 7-8 capturas del código CSS (una por página, abriendo `estilos_chapatupromo.css` en editor)
   - Opcional: 2 capturas responsive (tablet + móvil)

2. **Armar el HTML del informe APF2:**
   - Archivo único `INFORME_APF2.html` con CSS embebido
   - Estilo similar al APF1 (sobrio académico, no usar paleta morado/dorado del proyecto en el informe)
   - Secciones: carátula, descripción, objetivo, funcionalidades, estructura de páginas, tecnologías, capturas del resultado, capturas de animaciones, responsive, link repositorio, observaciones

3. **Exportar a PDF:**
   - Abrir el HTML en Firefox/Chrome
   - Ctrl+P → "Guardar como PDF"
   - Verificar paginación

---

## 13. Reglas para futuras sesiones de Claude

Al continuar este proyecto:

1. **NUNCA agregar JavaScript** ni frameworks. Solo HTML + CSS puros del temario semanas 6-9.
2. **Comentarios estilo junior** — cortos, español casual, sin emoticones, sin separadores `====`.
3. **Verificar contra el temario** antes de proponer propiedades CSS nuevas (sección 4 de este archivo).
4. **Paleta actual** es la de la sección 5. No volver al morado `#6c3483` viejo.
5. **El usuario es Max Benites**, estudiante junior. El código debe verse como hecho por él, no por una IA: simple, directo, con dudas humanas en comentarios.
6. **Mantener naming `*_chapatupromo.html`** — no renombrar a nombres genéricos.
7. **Antes de hacer cambios grandes**, preguntar al usuario con `AskUserQuestion` y previews cuando aplique.
8. **Si una propiedad CSS no aparece en los .md de S6-S9**, pero el usuario ya confirmó que la profesora la mencionó, está OK usarla.
