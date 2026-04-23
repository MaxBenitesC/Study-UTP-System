# Taller de Programación Web — Resumen Semanas 3 y 4

**Docente:** M.Sc. Jesamin Melissa Zevallos Quispe  
**Curso:** Taller de Programación Web

---

## Semana 3 — Formularios y Archivos Multimedia (Sesión 03)

### Logro de aprendizaje

Al finalizar la sesión, el estudiante:
- Diseña e implementa una página web con un formulario funcional.
- Comprende los elementos multimedia (imagen, audio y video) dentro de HTML.
- Aplica etiquetas semánticas y personalización con CSS.

### Utilidad

- Permite crear páginas interactivas para **recibir datos de los usuarios**.
- Facilita la inclusión de **contenido multimedia** que enriquece la experiencia de usuario.
- Conocimiento clave para construir sitios web profesionales: portafolios, landing pages, etc.

---

### 1. Etiquetas HTML — Repaso con `<div>` e `<img>`

La etiqueta `<div>` agrupa elementos en bloque. La etiqueta `<img>` muestra imágenes. Los atributos le dan comportamiento a las etiquetas.

```html
<body>
  <div class="warning">
    <img src="/images/html_css.png" alt="HTML and CSS." />
    <p>The power of HTML</p>
  </div>
</body>
```

**Sin estilos:** los elementos se apilan sin formato visual.  
**Con estilos CSS:**

```css
<style>
  .warning {
    border: 10px ridge #f00;
    background-color: #ff0;
    padding: 0.5rem;
    display: flex;
    flex-direction: column;
  }
  .warning img {
    width: 100%;
  }
  .warning p {
    font: small-caps bold 1.2rem sans-serif;
    text-align: center;
  }
</style>
```

| Propiedad CSS | Valor | Efecto |
|---------------|-------|--------|
| `border` | `10px ridge #f00` | Borde rojo con efecto ridge |
| `background-color` | `#ff0` | Fondo amarillo |
| `padding` | `0.5rem` | Espaciado interno |
| `display` | `flex` | Activa Flexbox |
| `flex-direction` | `column` | Elementos apilados verticalmente |
| `width` | `100%` | Imagen ocupa todo el ancho del contenedor |
| `font` | `small-caps bold 1.2rem sans-serif` | Tipografía en versalitas, negrita |
| `text-align` | `center` | Texto centrado |

---

### 2. Tablas (introducción en Semana 3)

Las tablas se definen con la etiqueta `<table>`.

| Etiqueta | Función |
|----------|---------|
| `<table>` | Define la tabla |
| `<tr>` | Define una fila |
| `<th>` | Celda de encabezado (negrita por defecto) |
| `<td>` | Celda de datos |

```html
<table border="1">
  <tr>
    <th>Company</th>
    <th>Contact</th>
    <th>Country</th>
  </tr>
  <tr>
    <td>Centro comercial Moctezuma</td>
    <td>Francisco Chang</td>
    <td>Mexico</td>
  </tr>
  <tr>
    <td>Ernst Handel</td>
    <td>Roland Mendel</td>
    <td>Austria</td>
  </tr>
</table>
```

---

### 3. Formularios HTML

Los formularios permiten recibir información del usuario.

#### Etiqueta `<form>`

```html
<form action="#" method="post">
  ...
</form>
```

| Atributo | Descripción |
|----------|-------------|
| `action` | URL o destino al que se envían los datos |
| `method` | Método HTTP: `get` o `post` |

---

#### Etiqueta `<input>`

```html
<input type="text" id="nombre" name="nombre" placeholder="Ingrese su Nombre" size="32" required />
```

| Atributo | Descripción |
|----------|-------------|
| `type` | Tipo de campo: `text`, `date`, `checkbox`, `submit` |
| `id` | Identificador único del campo |
| `name` | Nombre con el que se envía el dato al servidor |
| `placeholder` | Texto de ayuda visible dentro del campo |
| `size` | Ancho del campo en caracteres |
| `required` | Campo obligatorio; no permite enviar si está vacío |

---

#### Etiqueta `<label>`

```html
<label for="nombre">Nombre</label>
```

Asocia un texto descriptivo a un campo mediante el atributo `for`, que debe coincidir con el `id` del `<input>`.

---

#### Etiqueta `<select>` — Lista desplegable

```html
<select id="documento" name="documento">
  <option value="dni">DNI</option>
  <option value="pasaporte">Pasaporte</option>
</select>
```

---

#### Etiqueta `<button>`

```html
<button type="submit">Registrarse</button>
<button type="submit" disabled>Registrarse</button>
```

El atributo `disabled` desactiva el botón.

---

#### Formulario completo de ejemplo

```html
<body>
  <div class="form-container">
    <h2>Formulario de Registro</h2>
    <form action="#" method="post">

      <label for="nombre">Nombre:</label>
      <input type="text" id="nombre" name="nombre" required />

      <label for="apellido">Apellido:</label>
      <input type="text" id="apellido" name="apellido" required />

      <label for="direccion">Dirección:</label>
      <input type="text" id="direccion" name="direccion" />

      <label for="documento">Tipo de Documento:</label>
      <select id="documento" name="documento">
        <option value="dni">DNI</option>
        <option value="pasaporte">Pasaporte</option>
      </select>

      <label for="nacimiento">Fecha de nacimiento:</label>
      <input type="date" id="nacimiento" name="nacimiento" />

      <div class="checkbox-container">
        <label>
          <input type="checkbox" name="terminos" required />
          Acepto los términos y condiciones
        </label>
      </div>

      <input type="submit" value="Registrarse" />
    </form>
  </div>
</body>
```

---

### 4. Archivos Multimedia

#### Imagen — `<img>`

```html
<img src="https://ruta-de-imagen.jpg" alt="Descripción de la imagen" />
```

| Atributo | Descripción |
|----------|-------------|
| `src` | Ruta o URL de la imagen |
| `alt` | Texto alternativo (accesibilidad y SEO) |

---

#### Video — `<video>`

```html
<video controls>
  <source src="https://www.w3schools.com/html/mov_bbb.mp4" type="video/mp4" />
  Tu navegador no soporta la etiqueta de video.
</video>
```

| Atributo | Descripción |
|----------|-------------|
| `controls` | Muestra los controles de reproducción |
| `src` (en `<source>`) | Ruta o URL del video |
| `type` | Tipo MIME del archivo (`video/mp4`) |

---

#### Audio — `<audio>`

```html
<audio controls>
  <source src="https://www.w3schools.com/html/horse.mp3" type="audio/mpeg" />
  Tu navegador no soporta la etiqueta de audio.
</audio>
```

| Atributo | Descripción |
|----------|-------------|
| `controls` | Muestra los controles de reproducción |
| `src` (en `<source>`) | Ruta o URL del audio |
| `type` | Tipo MIME del archivo (`audio/mpeg`) |

---

#### Ejemplo completo con imagen, video y audio

```html
<body>
  <h1>Ejemplo de Imagen, Video y Audio</h1>
  <div class="multimedia">

    <!-- Imagen -->
    <div>
      <img src="https://ruta.jpg" alt="Ejemplo de Imagen" />
      <div class="caption">Esta es una imagen de ejemplo.</div>
    </div>

    <!-- Video -->
    <div>
      <video controls>
        <source src="video.mp4" type="video/mp4" />
        Tu navegador no soporta la etiqueta de video.
      </video>
      <div class="caption">Video demostrativo.</div>
    </div>

    <!-- Audio -->
    <div>
      <audio controls>
        <source src="audio.mp3" type="audio/mpeg" />
        Tu navegador no soporta la etiqueta de audio.
      </audio>
      <div class="caption">Audio de ejemplo.</div>
    </div>

  </div>
</body>
```

---

### Resumen Semana 3

| Concepto | Descripción |
|----------|-------------|
| `<form>` | Contenedor del formulario; define `action` y `method` |
| `<input>` | Campo de entrada; tipos: `text`, `date`, `checkbox`, `submit` |
| `<label>` | Etiqueta descriptiva vinculada a un campo con `for` |
| `<select>` / `<option>` | Lista desplegable de opciones |
| `<button>` | Botón de acción; puede desactivarse con `disabled` |
| `<img>` | Embebe imágenes con `src` y `alt` |
| `<video>` | Embebe videos con `controls` y `<source>` |
| `<audio>` | Embebe audio con `controls` y `<source>` |

---

## Semana 4 — Tablas: Estructura, Elementos y Personalización con CSS (Sesión 04)

### Logro de aprendizaje

Al finalizar la sesión, el estudiante:
- Crea tablas HTML con múltiples filas y columnas.
- Incorpora elementos como encabezados y combinaciones de celdas.
- Aplica estilos CSS para mejorar su presentación visual.
- Conoce los conceptos y la estructura de tablas, frames e iframes.

### Utilidad

Las tablas son esenciales para presentar información estructurada: reportes, dashboards, catálogos, precios, datos científicos, etc. Son una herramienta clave para desarrollar interfaces claras y funcionales en sistemas web.

---

### 1. Etiqueta `<table>` — Estructura básica

```html
<body>
  <table>
    <tr>
      <td>1,1</td>
      <td>1,2 ok</td>
    </tr>
    <tr>
      <td>2,1 real wide</td>
      <td>2,2</td>
    </tr>
  </table>
</body>
```

| Etiqueta | Función |
|----------|---------|
| `<table>` | Define la tabla general |
| `<tr>` | Define una fila (table row) |
| `<td>` | Celda de datos (table data) |

> **Nota importante:** No usar tablas para el diseño (layout) de páginas web. Las tablas tienen semántica y deben usarse solo para representar datos tabulares reales. Para diseño usar `div`, `width`, `margin`, Flexbox, Grid, etc.

---

### 2. Headers y Caption

```html
<body>
  <table>
    <caption>My important data</caption>
    <tr>
      <th>Column 1</th>
      <th>Column 2</th>
    </tr>
    <tr>
      <td>1,1</td>
      <td>1,2 okay</td>
    </tr>
    <tr>
      <td>2,1 real wide</td>
      <td>2,2</td>
    </tr>
  </table>
</body>
```

| Etiqueta | Función |
|----------|---------|
| `<th>` | Celda de encabezado; aparece en **negrita** por defecto |
| `<caption>` | Descripción o título de la tabla |

---

### 3. Estilos CSS para Tablas

```css
<style>
  table {
    border: 2px solid black;
    caption-side: bottom;
    width: 50%;
  }
  tr {
    font-style: italic;
  }
  td {
    background-color: yellow;
    text-align: center;
  }
</style>
```

| Propiedad | Valor | Efecto |
|-----------|-------|--------|
| `border` | `2px solid black` | Borde de la tabla |
| `caption-side` | `bottom` | Mueve el caption debajo de la tabla |
| `width` | `50%` | Ancho de la tabla |
| `font-style` | `italic` | Texto en cursiva para las filas |
| `background-color` | `yellow` | Color de fondo de celdas |
| `text-align` | `center` | Centra el texto en las celdas |

---

### 4. Atributos `colspan` y `rowspan`

Permiten que una celda ocupe más de una columna o fila.

```html
<table>
  <tr>
    <th>Column 1</th>
    <th>Column 2</th>
    <th>Column 3</th>
  </tr>
  <tr>
    <td colspan="2">1,1-1,2</td>       <!-- ocupa 2 columnas -->
    <td rowspan="3">1,3-3,3</td>        <!-- ocupa 3 filas -->
  </tr>
  <tr>
    <td>2,1</td>
    <td>2,2</td>
  </tr>
  <tr>
    <td>3,1</td>
    <td>3,2</td>
  </tr>
</table>
```

```css
<style>
  table, td, th {
    border: 1px solid black;
  }
  table {
    border-collapse: collapse;
  }
</style>
```

| Atributo / Propiedad | Descripción |
|----------------------|-------------|
| `colspan="n"` | La celda ocupa `n` columnas |
| `rowspan="n"` | La celda ocupa `n` filas |
| `border-collapse: collapse` | Elimina el espacio entre bordes de celdas |
| `text-align` | Alineación horizontal del contenido de la celda |
| `vertical-align` | Alineación vertical del contenido de la celda |

---

### 5. Tamaño de la Tabla

```html
<table width="500" height="250">
  <tr>
    <th>Column 1</th>
    <th>Column 2</th>
    <th>Column 3</th>
  </tr>
</table>
```

El tamaño se puede especificar en **píxeles** o como **porcentaje** del área de visualización.

---

### 6. Frames (obsoleto — referencia histórica)

Los **frames** se remontan a 1996 (introducidos por Netscape). Permitían dividir la página en secciones independientes, cargando solo partes dinámicas para ahorrar ancho de banda en conexiones lentas.

```html
<html>
  <head>
    <title>Frame columnas</title>
  </head>
  <frameset cols="30%,20%,50%">
    <frame src="files.html">
    <frame src="index.html">
    <frame src="forms.html">
  </frameset>
</html>
```

| Etiqueta | Función |
|----------|---------|
| `<frameset>` | Define la división de la ventana |
| `<frame>` | Cada sección independiente |
| `cols` | División por columnas (porcentaje o píxeles) |
| `rows` | División por filas |

**Tipos de divisiones:**
- Divisiones a nivel de columnas
- Divisiones a nivel de filas
- Divisiones combinadas

> **Estado actual:** Obsoleto. Ya no se usa en HTML moderno.

---

### 7. IFrame (Inline Frame)

Un `<iframe>` permite **incrustar otro documento HTML** dentro de la página actual. Se usa para mostrar: videos, mapas, anuncios, feeds de redes sociales, etc.

```html
<head>
  <style>
    iframe {
      border: 1px solid black;
      width: 100%;
    }
  </style>
</head>
<body>
  <iframe
    id="inlineFrameExample"
    title="Inline Frame Example"
    height="700"
    src="https://www.openstreetmap.org/export/embed.html?...">
  </iframe>
</body>
```

#### Ejemplo: Incrustar video de YouTube

```html
<!DOCTYPE html>
<html>
  <body>
    <iframe width="600" height="345"
      src="https://www.youtube.com/embed/tgbNymZ7vqY">
    </iframe>
  </body>
</html>
```

| Atributo | Descripción |
|----------|-------------|
| `src` | URL del contenido a incrustar |
| `width` / `height` | Dimensiones del iframe |
| `title` | Descripción accesible del contenido |
| `id` | Identificador único del elemento |

---

### 8. Marquee (texto en movimiento)

```html
<!DOCTYPE html>
<html>
  <body>
    <marquee><h1>Hello World</h1></marquee>
  </body>
</html>
```

La etiqueta `<marquee>` inserta un área de texto en movimiento (también conocida como **marquesina**).

> **Estado actual:** En desuso. No se recomienda su uso en HTML moderno.

---

### Resumen Semana 4

| Concepto | Etiqueta / Propiedad | Descripción |
|----------|----------------------|-------------|
| Tabla | `<table>` | Contenedor principal |
| Fila | `<tr>` | Fila de la tabla |
| Celda datos | `<td>` | Celda con datos |
| Celda encabezado | `<th>` | Celda en negrita para encabezados |
| Descripción tabla | `<caption>` | Título/descripción de la tabla |
| Fusión columnas | `colspan="n"` | Una celda ocupa n columnas |
| Fusión filas | `rowspan="n"` | Una celda ocupa n filas |
| Borde unificado | `border-collapse: collapse` | Elimina doble borde entre celdas |
| Tamaño tabla | `width` / `height` | En px o porcentaje |
| Frames | `<frameset>` / `<frame>` | Obsoleto — división de ventana |
| Inline Frame | `<iframe>` | Incrusta contenido externo en la página |
| Marquesina | `<marquee>` | Texto en movimiento (en desuso) |

---

## Resumen General — Semanas 3 y 4

| Tema | Semana 3 | Semana 4 |
|------|----------|----------|
| `<div>` como contenedor de bloque | ✅ | — |
| Etiqueta `<img>` con `src` y `alt` | ✅ | — |
| CSS: Flexbox, border, font, padding | ✅ | — |
| Formularios: `<form>`, `<input>`, `<label>` | ✅ | — |
| `<select>` / `<option>` | ✅ | — |
| `<button>` con `disabled` | ✅ | — |
| Tipos de `<input>`: text, date, checkbox, submit | ✅ | — |
| `<video>` con `controls` y `<source>` | ✅ | — |
| `<audio>` con `controls` y `<source>` | ✅ | — |
| Tablas: `<table>`, `<tr>`, `<td>`, `<th>` | ✅ (intro) | ✅ |
| `<caption>` en tablas | — | ✅ |
| `colspan` y `rowspan` | — | ✅ |
| CSS para tablas: `border-collapse`, `caption-side` | — | ✅ |
| Tamaño de tabla con `width` / `height` | — | ✅ |
| Frames `<frameset>` (obsoleto) | — | ✅ |
| `<iframe>` para contenido embebido | — | ✅ |
| `<marquee>` (en desuso) | — | ✅ |

---

## Bibliografía

- Jon Duckett (2021). *HTML & CSS: Design and Build Websites.*
- Javi Agenjo (2023). *Introduction to web technologies: HTML + CSS + Javascript.*
- Geek For Geeks (2024). *How to create a slideshow with HTML and CSS?*
