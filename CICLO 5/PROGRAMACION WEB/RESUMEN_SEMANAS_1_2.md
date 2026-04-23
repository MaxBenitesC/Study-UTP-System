# Taller de Programación Web — Resumen Semanas 1 y 2

**Docente:** M.Sc. Jesamin Melissa Zevallos Quispe  
**Curso:** Taller de Programación Web

---

## Semana 1 — HTML y CSS (Sesión 01)

### Logro de aprendizaje

Al finalizar la sesión, el estudiante:
- Comprende los conceptos necesarios en desarrollo web y programación relacionada a sitios web.
- Comprende la estructura básica de un documento HTML.
- Conoce y construye una página web simple combinando HTML y CSS.

---

### 1. HTML (HyperText Markup Language)

HTML es un **lenguaje de etiquetas** utilizado para el desarrollo de páginas web. Sus siglas corresponden a **HyperText Markup Language** ("Lenguaje de Marcas de Hipertexto").

#### Anatomía de un elemento HTML

```
<h1> Taller de Programación Web </h1>
 ↑                                  ↑
Etiqueta de apertura         Etiqueta de cierre
         └──────── Contenido ──────────┘
              = Elemento h1
```

#### Utilidad de HTML
- Es la **base de cualquier página web**.
- Sirve para estructurar la información en internet.
- Permite desarrollar aplicaciones web, e-commerces, blogs, portafolios y tiendas en línea.

---

### 2. Editor HTML: Visual Studio Code

Un **editor HTML** es un software para editar y crear código HTML para sitios web u otros documentos web.

**Visual Studio Code** es un editor de código fuente que incluye:
- Soporte para depuración
- Control integrado de Git
- Resaltado de sintaxis
- Finalización inteligente de código (IntelliSense)
- Fragmentos de código (snippets)
- Refactorización de código

**Extensión recomendada:** Prettier - Code Formatter

---

### 3. Estructura Básica de una Página HTML

```html
<html>
  <head>
    <!-- Información técnica para el navegador -->
  </head>
  <body>
    Contenido que aparecerá en la página Web
  </body>
</html>
```

| Etiqueta | Descripción |
|----------|-------------|
| `<html>` | Elemento raíz que envuelve todo el documento |
| `<head>` | Contiene metadatos e información técnica para el navegador |
| `<body>` | Contiene el contenido visible en la página |

---

### 4. Meta Tags

Los **meta tags** van dentro del `<head>` y proporcionan información técnica al navegador:

```html
<head>
  <meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
  <title>Primera página</title>
</head>
```

---

### 5. Etiquetas de Texto

#### Párrafos y saltos de línea

| Etiqueta | Función |
|----------|---------|
| `<p>` | Define un párrafo |
| `<br>` | Agrega un salto de línea (self-closing) |

```html
<html>
  <head>
    <title>Primera página</title>
  </head>
  <body>
    <p>Esto es un párrafo.</p>
    <p>Esto es otro párrafo.</p>
    <p>Esto es otro párrafo con un <br>salto de línea.</p>
  </body>
</html>
```

---

### 6. Formato de Texto

La etiqueta `<pre>` muestra texto respetando el formato original (espacios y saltos de línea).

| Etiqueta | Efecto |
|----------|--------|
| `<strong>` | **Negrita** |
| `<em>` | *Cursiva* |
| `<del>` | ~~Tachado~~ |
| `<big>` | Texto grande |
| `<small>` | Texto pequeño |
| `<sub>` | Subíndice |
| `<sup>` | Superíndice |

```html
<body>
  <pre>
    Ejemplo básico: Uso de etiquetas de formato
    <strong>Negrita</strong>
    <em>Cursiva</em>
    <del>Subrayado</del>
    <big>Grande</big>
    <small>Pequeño</small>
    Esto es un <sub>subíndice</sub>
    Y esto un <sup>superíndice</sup>
  </pre>
</body>
```

---

### 7. Comentarios en HTML

Los comentarios no se muestran en el navegador y sirven para documentar el código:

```html
<!-- Esto es un comentario -->
```

Ejemplo en uso:

```html
<em>Cursiva</em> <!-- Esto es un comentario -->
```

---

### 8. Enlaces

La etiqueta `<a>` crea un enlace a otras páginas de internet, archivos o ubicaciones dentro de la misma página.  
El atributo **`href`** especifica el destino del enlace.

```html
<a href="https://www.ejemplo.com">Ir a Ejemplo</a>
```

---

### 9. CSS (Cascading Style Sheet)

CSS es un **lenguaje de diseño gráfico** que define la apariencia de los sitios web. Se integra con el código HTML.

#### Anatomía de una regla CSS

```css
p {
  color: red;
}
```

| Componente | Descripción | Ejemplo |
|------------|-------------|---------|
| **Selector** | Elemento al que se aplica el estilo | `p` |
| **Propiedad** | Característica que se modifica | `color` |
| **Valor** | Valor asignado a la propiedad | `red` |
| **Declaración** | Par propiedad + valor | `color: red;` |

#### Utilidad de CSS
- Es fundamental para mejorar la presentación y experiencia de usuario en cualquier web.
- Sirve para personalizar y estructurar contenido visualmente.

#### Ejemplo de estilo CSS en línea (inline)

```html
<html>
  <head>
    <title>Primera página</title>
  </head>
  <body style="background-color: powderblue;">
    <h1>Tamaño H1</h1>
    <p>Esto es un párrafo</p>
  </body>
</html>
```

---

### Práctica Semana 1

- Implementar "Hello World"
- Abrir el editor de HTML (VS Code)
- Crear la estructura básica de HTML
- Escribir: nombres completos, carrera, curso
- Agregar estilos:
  - Cambiar el color de fondo del body
  - Agregar estilo inline

---

## Semana 2 — Estructura de una Página Web (Sesión 02)

### Logro de aprendizaje

Al finalizar la sesión, el estudiante:
- Comprende la estructura básica de un documento HTML.
- Entiende cómo crear páginas web y cómo estructurar la información para su representación visual.

---

### Utilidad de la estructura de una página Web

- Sirve para personalizar y estructurar contenido en plataformas como blogs, portafolios o tiendas en línea.
- Permite organizar el contenido de manera adecuada.
- Todo profesional debe conocer cómo está estructurado una página web.

---

### 1. Repaso: HTML (HyperText Markup Language)

```
<h1> Taller de Programación Web </h1>
 ↑                                  ↑
Etiqueta de apertura         Etiqueta de cierre
         └──────── Contenido ──────────┘
```

---

### 2. Estructura Completa de una Página Web

```html
<html>
  <head>
    Información técnica para el navegador
  </head>
  <body>
    Contenido que aparecerá en la página Web
  </body>
</html>
```

---

### 3. La Sección `<head>`

```html
<head>
  <meta charset="UTF-8">
  <title>Mi Primera Página</title>
  <style>
    body { }
    footer { }
  </style>
</head>
```

| Etiqueta | Función |
|----------|---------|
| `<title>` | Define el título que aparece en la pestaña del navegador |
| `<meta>` | Define metadatos (ej. codificación de caracteres `charset="UTF-8"`) |
| `<link>` | Para importar hojas de estilo CSS externas |
| `<style>` | Para escribir CSS interno directamente en el HTML |

---

### 4. La Sección `<body>`

El `<body>` contiene todo el contenido visible de la página. Se organiza en secciones semánticas:

```html
<body>
  <header>
    <h1>Bienvenido a Mi Sitio Web</h1>
  </header>
  <section>
    <p>Este es el contenido principal.</p>
  </section>
  <footer>
    <p>&copy; 2025 Mi Sitio Web</p>
  </footer>
</body>
```

---

### 5. Etiquetas Semánticas del Body

#### `<header>` — Encabezado

```html
<header>
  <h1>Mi Sitio Web</h1>
</header>
```

- Se usa para colocar el **título o logo** del sitio.
- Puede incluir un `<nav>` para la navegación.

```html
<nav>
  <ul>
    <li><a href="#">Inicio</a></li>
    <li><a href="#">Servicios</a></li>
    <li><a href="#">Contacto</a></li>
  </ul>
</nav>
```

---

#### `<section>` — Sección de contenido

```html
<section>
  <h2>Sobre Nosotros</h2>
  <p>Información de la empresa o del sitio.</p>
</section>
```

- Organiza el contenido en **bloques temáticos**.

---

#### `<aside>` — Contenido lateral

```html
<aside>
  <h3>Noticias</h3>
  <p>Contenido adicional relacionado.</p>
</aside>
```

- Se usa para **barras laterales** o información extra complementaria.

---

#### `<footer>` — Pie de página

```html
<footer>
  <p>&copy; 2025 Mi Sitio Web</p>
</footer>
```

- Contiene información de contacto o **derechos de autor**.
- Debe ubicarse al final de la página.

---

### 6. Resumen de Etiquetas Semánticas

| Etiqueta | Ubicación | Función |
|----------|-----------|---------|
| `<header>` | Inicio del body | Título, logo, navegación |
| `<nav>` | Dentro del header | Menú de navegación con enlaces |
| `<section>` | Cuerpo principal | Bloques temáticos de contenido |
| `<aside>` | Lateral | Contenido secundario o complementario |
| `<footer>` | Final del body | Derechos de autor, contacto |

---

### 7. CSS — Repaso y Aplicación a la Estructura

```css
p {
  color: red;
}
```

#### Ejemplo completo: HTML + CSS

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Página Completa</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }
    header,
    footer {
      background: #797676;
      color: white;
      text-align: center;
      padding: 10px;
    }
    nav ul {
      list-style: none;
      padding: 0;
    }
    nav ul li {
      display: inline;
      margin: 10px;
    }
    section {
      padding: 20px;
    }
    aside {
      background: #f4f4f4;
      padding: 10px;
    }
    footer {
      margin-top: auto;
    }
  </style>
</head>
<body>
  <header>
    <h1>Mi Sitio Web</h1>
    <nav>
      <ul>
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Servicios</a></li>
        <li><a href="#">Contacto</a></li>
      </ul>
    </nav>
  </header>
  <section>
    <h2>Bienvenido</h2>
    <p>Ejemplo de organización en HTML.</p>
  </section>
  <aside>
    <h3>Noticias Recientes</h3>
    <p>Contenido adicional.</p>
  </aside>
  <footer>
    <p>&copy; 2025 Mi Sitio Web</p>
  </footer>
</body>
</html>
```

#### Propiedades CSS usadas en el ejemplo

| Propiedad | Valor usado | Efecto |
|-----------|-------------|--------|
| `font-family` | `Arial, sans-serif` | Tipografía del documento |
| `margin` | `0` | Elimina márgenes por defecto |
| `padding` | `0` / `10px` / `20px` | Espaciado interno |
| `display` | `flex` | Activa Flexbox en el body |
| `flex-direction` | `column` | Apila elementos verticalmente |
| `min-height` | `100vh` | Altura mínima de toda la ventana |
| `background` | `#797676` | Color de fondo (hexadecimal) |
| `color` | `white` | Color del texto |
| `text-align` | `center` | Alineación del texto |
| `list-style` | `none` | Quita los puntos de la lista `<ul>` |
| `display` (li) | `inline` | Muestra los ítems del nav en línea |
| `margin-top` | `auto` | Empuja el footer al fondo |

---

### Práctica Semana 2

- Replicar el formato de la página **VS Code for the Web**.

---

## Resumen General

| Tema | Semana 1 | Semana 2 |
|------|----------|----------|
| Definición de HTML | ✅ | ✅ (repaso) |
| Estructura básica `<html>/<head>/<body>` | ✅ | ✅ |
| Meta tags | ✅ | ✅ |
| Etiquetas de texto (`<p>`, `<br>`) | ✅ | — |
| Formato de texto (`<strong>`, `<em>`, etc.) | ✅ | — |
| Comentarios HTML | ✅ | — |
| Enlaces `<a href>` | ✅ | — |
| Definición de CSS | ✅ | ✅ (repaso) |
| Estilos inline | ✅ | — |
| Etiquetas semánticas (`<header>`, `<section>`, `<footer>`, `<aside>`, `<nav>`) | — | ✅ |
| CSS interno con `<style>` | — | ✅ |
| Propiedades CSS (Flexbox, colores, tipografía) | — | ✅ |

---

## Bibliografía

- Jon Duckett (2021). *HTML & CSS: Design and Build Websites.*
- Javi Agenjo (2023). *Introduction to web technologies: HTML + CSS + Javascript.*
- Geek For Geeks (2024). *How to create a slideshow with HTML and CSS?*
