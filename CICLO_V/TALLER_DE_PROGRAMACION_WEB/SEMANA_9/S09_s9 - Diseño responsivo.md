# Diseño responsivo
**Semana 09 - Sesión 09**

**TALLER DE PROGRAMACIÓN WEB**
M.Sc. Jesamin Zevallos Quispe
c29741@utp.edu.pe

---

## Dudas de la clase anterior
* Estilos CSS
* Flexbox
* Grid

---

## Conocimientos previos
* Los estudiantes usan diferentes dispositivos para navegar en internet (smartphones, laptops, tablets, pantallas grandes) y notan que algunas páginas se ven bien y otras no.
* Han experimentado interfaz desordenada o texto fuera de lugar cuando abren sitios desde sus celulares.

---

## Logro de aprendizaje
Al finalizar la sesión, el estudiante:
Comprende el diseño responsivpara adaptar el diseño de una web a distintos tamaños de pantalla.

---

## Utilidad
**¿Para qué sirve esto en la vida real?**
* Permite que las páginas web se adapten automáticamente a cualquier dispositivo sin perder usabilidad ni estética.
* Es un requisito básico en el desarrollo web moderno: los usuarios acceden a sitios desde celulares más que desde computadoras.
* Favorece el posicionamiento en buscadores y mejora la experiencia del usuario.
* Es esencial en proyectos reales como: portales educativos, tiendas virtuales, sistemas de información y aplicaciones web.

---

## Contenido
* Diseño responsivo
* Práctica

---

## ¿Qué es un Diseño Responsivo?
Diseño Web Responsivo (Responsive Web Design) es un concepto de desarrollo web que se centra en hacer que los sitios se vean y se comporten de manera óptima en todos los dispositivos informáticos personales, desde el escritorio hasta el móvil.

---

## Importancia del diseño responsivo
* Adaptabilidad multiplataforma
* Mejora la experiencia del usuario
* Mayor alcance y accesibilidad
* Ocupar un lugar más alto en los resultados de búsqueda

---

## Diseño responsivo
* Permite que el contenido llene el contenedor y defina restricciones mínimas y máximas.
* Se recomienda usar unidades relativas para especificar la posición y el tamaño del texto y los elementos multimedia.

**Técnicas:**
* Media Queries
* Grid layout
* Flexbox

---

## Media Queries
* Permiten aplicar estilos basados ​​en las características de un dispositivo.
* Media Queries son esenciales para crear páginas web responsivas.
* Utilizaremos la regla `@media` para agregar condiciones a nuestra hoja de estilo.

---

## Ejemplos (Ejemplo 1)

```html
<div class="caja">Hola Mundo</div>
```

```css
.caja {
    width: 300px;
    height: 300px;
    background: blue;
}

@media (max-width: 600px) {
    .caja {
        background: red;
    }
}
```

---

## Ejemplos (Ejemplo 2)

```html
<div class="container">
    <div class="box">1</div>
    <div class="box">2</div>
    <div class="box">3</div>
</div>
```

```css
.container {
    display: flex;
    gap: 20px;
}

.box {
    width: 200px;
    height: 200px;
    background: lightblue;
    font-weight: bold;
    font-size: large;
    line-height: 200px;
    text-align: center;
}

@media (max-width: 700px) {
    .container {
        flex-direction: column;
    }
}
```

---

## Ejemplos (Ejemplo 3: Grid Layout)

```html
<div class="container">
    <div class="header">HEADER</div>
    <div class="menu">MENU</div>
    <div class="content">CONTENT</div>
    <div class="extra">EXTRA</div>
    <div class="footer">FOOTER</div>
</div>
```

```css
.container {
    display: grid;
    gap: 10px;
    height: 100%;
    padding: 10px;
    grid-template-columns: 1fr 2fr 1fr;
    grid-template-rows:
        1fr
        3fr
        1fr;
    grid-template-areas:
        "header header header"
        "menu content extra"
        "footer footer footer";
}

.header {
    grid-area: header;
    background: crimson;
}

.menu {
    grid-area: menu;
    background: royalblue;
}

.content {
    grid-area: content;
    background: seagreen;
}

.extra {
    grid-area: extra;
    background: orange;
}

.footer {
    grid-area: footer;
    background: gray;
}

.container div {
    color: white;
    font-size: 30px;
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 10px;
}

/* Tablet */
@media (max-width: 900px) {
    .container {
        grid-template-columns: 1fr 2fr;
        grid-template-rows:
            100px
            1fr
            100px
            100px;
        grid-template-areas:
            "header header"
            "menu content"
            "extra extra"
            "footer footer";
    }
}

/* Móvil */
@media (max-width: 600px) {
    .container {
        grid-template-columns: 1fr;
        grid-template-rows:
            80px
            80px
            1fr
            80px
            80px;
        grid-template-areas:
            "header"
            "menu"
            "content"
            "extra"
            "footer";
    }
}
```

---

## Preguntas
*Preguntas y dudas generales*

---

## Práctica
Avanzar con la integración de Responsive en su proyecto.

---

## Resumen de la sesión
**¿Qué hemos aprendido hoy?**
* Diseño responsivo

---

## ¡Gracias!

TALLER DE PROGRAMACIÓN WEB
M.Sc. Jesamin Zevallos Quispe
c29741@utp.edu.pe
