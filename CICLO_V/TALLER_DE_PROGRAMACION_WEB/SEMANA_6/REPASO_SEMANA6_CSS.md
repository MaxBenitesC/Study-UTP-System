# Repaso Semana 6 - Personalización del Sitio Web con CSS

---

## ¿Qué es CSS?

**CSS** (Cascading Style Sheets) = Hojas de estilo en cascada.
Sirve para aplicar estilos (colores, formas, márgenes, tipografías) a documentos HTML.
Se lee y aplica **de arriba hacia abajo** (en cascada).

**Separación de responsabilidades:**
- HTML → contenido y estructura
- CSS → diseño y apariencia

---

## Formas de usar CSS

| Tipo | Como se usa | Ejemplo |
|---|---|---|
| **En línea** | Atributo `style` en la etiqueta | `<body style="color: tomato;">` |
| **Interno** | Etiqueta `<style>` en el `<head>` | `<style> body { color: tomato; } </style>` |
| **Externo** | Archivo `.css` separado enlazado con `<link>` | `<link rel="stylesheet" href="style.css">` |

---

## Sintaxis CSS

```css
p {
    color: red;
}
```

- `p` → **Selector** (a quién aplica el estilo)
- `color` → **Propiedad** (qué se cambia)
- `red` → **Valor** (cómo se cambia)
- `color: red;` → **Declaración**

---

## Tipografía en CSS

### `font-family` — Tipo de letra

```css
.container {
    font-family: Verdana;
    /* Con alternativas (si la primera no carga, usa la siguiente) */
    font-family: 'PT Sans', Arial, sans-serif;
}
```

### `font-size` — Tamaño de letra

```css
p {
    font-size: 16px;   /* pixeles */
    font-size: 1rem;   /* relativo al tamaño raiz */
    font-size: large;  /* tamaño absoluto predefinido */
}
```

### `font-style` — Estilo de letra

```css
p { font-style: normal; }   /* normal */
p { font-style: italic; }   /* cursiva */
p { font-style: oblique; }  /* oblicua (inclinacion artificial) */
```

### `font-weight` — Grosor de letra

```css
p { font-weight: normal; }  /* = 400 */
p { font-weight: bold; }    /* = 700 */
p { font-weight: 300; }     /* numero del 100 al 800 */
p { font-weight: bolder; }  /* mas grueso que el padre */
```

### Atajo `font` — Todo en una linea

```css
.container {
    /* font: estilo variante peso tamaño/lineheight familia */
    font: italic normal 400 16px Arial, sans-serif;
}
```

---

## Decoracion de Texto

### `text-wrap` — Ajuste de texto

```css
p { text-wrap: wrap; }    /* el texto se parte en varias lineas (por defecto) */
p { text-wrap: nowrap; }  /* el texto NO se parte, se sale del contenedor */
```

---

## Modelo de Cajas

Todo elemento HTML es una "caja" con 4 capas:

```
┌─────────────────────────────┐  ← margen (margin)
│  ┌───────────────────────┐  │  ← borde (border)
│  │  ┌─────────────────┐  │  │  ← relleno (padding)
│  │  │   CONTENIDO     │  │  │
│  │  └─────────────────┘  │  │
│  └───────────────────────┘  │
└─────────────────────────────┘
```

---

## Dimensiones

```css
div {
    width: 100%;         /* ancho */
    height: 200px;       /* alto */
    min-width: 300px;    /* ancho minimo */
    max-width: 800px;    /* ancho maximo */
    min-height: 100px;   /* alto minimo */
    max-height: 500px;   /* alto maximo */
}
```

---

## Margenes y Rellenos (Atajo)

```css
.ejemplo {
    margin: 15px;              /* los 4 lados iguales */
    margin: 20px 10px;         /* arriba/abajo  izq/der */
    margin: 20px 10px 5px;     /* arriba  izq/der  abajo */
    margin: 20px 10px 5px 8px; /* arriba  derecha  abajo  izquierda */
    /* padding funciona igual */
}
```

---

## Imagenes con CSS

```css
img {
    width: 300px;
    height: 300px;
    object-fit: fill;     /* estira la imagen para llenar */
    object-fit: contain;  /* la imagen cabe sin recortarse */
    object-fit: cover;    /* rellena recortando los bordes */
}
```

---

## Iconos con FontAwesome

```html
<!-- Enlazar la libreria en el head -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

<!-- Usar el icono -->
<i class="fas fa-camera" style="font-size: 40px; color: blue;"></i>
```

```css
/* Agregar sombra al icono */
i {
    text-shadow: 5px 5px 10px green;
}
```

---

## Resumen de propiedades nuevas de Semana 6

| Propiedad | Para que sirve |
|---|---|
| `font-family` | Tipo de letra |
| `font-size` | Tamaño de letra |
| `font-style` | Normal / cursiva / oblicua |
| `font-weight` | Grosor de letra |
| `font` | Atajo para todas las propiedades de fuente |
| `text-wrap` | Controla si el texto se parte o no |
| `width` / `height` | Ancho y alto del elemento |
| `min-width` / `max-width` | Rango de ancho |
| `object-fit` | Como se ajusta una imagen a su contenedor |
| `text-shadow` | Sombra en texto o iconos |
