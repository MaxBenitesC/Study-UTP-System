# Principios de Flexbox y Grid Layout
**Semana 08 - Sesión 08**

**TALLER DE PROGRAMACIÓN WEB**
M.Sc. Jesamin Melissa Zevallos Quispe
c29741@utp.edu.pe

---

## Dudas de la clase anterior
* Estilos CSS
* Animaciones
* Transiciones

---

## Conocimientos previos
*(Sin contenido adicional)*

---

## Logro de aprendizaje
Al finalizar la sesión, el estudiante:
Comprende los principios de Flexbox y Grid Layout en CSS, y es capaz de construir una página web con estructura moderna y adaptable empleando estas técnicas.

---

## Utilidad
**¿Para qué sirve esto en la vida real?**
* Son ampliamente utilizados en el desarrollo profesional de sitios web, aplicaciones web y sistemas de administración.
* Flexbox y Grid permiten distribuir y alinear elementos en una página web de forma limpia y eficiente.
* Se usan en la creación de diseños modernos, responsivos y escalables.

---

## Contenido
* Flexbox
* Grid Layout
* Práctica

---

## ¿Qué es Flexbox?
Flexbox (Flexible Box Layout) es un modelo de diseño en CSS que permite organizar y alinear elementos dentro de un contenedor de forma flexible y eficiente, tanto en dirección horizontal como vertical.

Con Flexbox puedes:
* Centrar elementos fácilmente.
* Distribuir espacio entre ellos.
* Hacer que los elementos se ajusten automáticamente al tamaño del contenedor.

---

## Estructura
La estructura de Flexbox se compone de contenedores padre e hijos (Contenedor-Flex y Elementos-Flex respectivamente).
El Contenedor-Flex es nuestro contenedor padre, y es él quién va a contener a todos los elementos hijos a los cuáles queremos posicionar.
El Elemento-Flex es nuestro elemento hijo, el cual utilizará todo el espacio disponible para ubicarse de acuerdo a las propiedades a las cuales este sometido junto a los demás Elementos-Flex contenidos.

---

## Propiedades
Si queremos utilizar las propiedades de Flexbox tendremos que definirlo mediante la propiedad `display` y su valor `flex` dentro del selector que nosotros definamos que es nuestro elemento padre o Contenedor-Flex.

```css
.contenedor {
    display: flex;
}
```

```html
<div class="contenedor">
    <div class="item">Caja 1</div>
    <div class="item">Caja 2</div>
    <div class="item">Caja 3</div>
    <div class="item">Caja 4</div>
    <div class="item">Caja 5</div>
</div>
```

```css
body {
    background: skyblue;
}

.contenedor {
    padding: 2px;
    width: 100%;
}

.item {
    background-color: beige;
    border: 1px solid rgb(70, 69, 69);
    height: 100px;
    width: 100px;
    margin: 5px;
    font-weight: bold;
    font-size: large;
    line-height: 100px;
    text-align: center;
}
```

---

## Flex-direction
Es la encargada de definir el eje principal y secundario de nuestros elementos hijos. Estos ejes como ya se mencionó pueden ser verticales, formando columnas y horizontales formando filas.

```css
.contenedor {
    padding: 2px;
    width: 100%;
    display: flex;
    flex-direction: row;
}
```

Valores posibles:
`flex-direction: row | row-reverse | column | column-reverse`

* `flex-direction: column`
* `flex-direction: column-reverse`
* `flex-direction: row-reverse`

---

## Flex-wrap
Por defecto flex, trata de disponer de los elementos en una misma línea, y puedes ordenar los elementos en más de una fila o columna.

```css
.contenedor {
    padding: 2px;
    width: 100%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
}
```

Valores posibles:
`flex-wrap: nowrap | wrap | wrap-reverse`

* `flex-wrap: nowrap`
* `flex-wrap: wrap-reverse`

---

## Justify-content
Cuando nombramos el eje principal de un elemento padre, es decir el eje horizontal, se destaca esta propiedad para dar direccionamiento a los elementos hijos.

```css
.contenedor {
    padding: 2px;
    width: 100%;
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    justify-content: flex-start;
}
```

Valores posibles:
`justify-content: flex-start | flex-end | center | space-between | space-around | space-evenly`

* `justify-content: flex-start`
* `justify-content: flex-end`
* `justify-content: center`
* `justify-content: space-between`
* `justify-content: space-evenly`
* `justify-content: space-around`

---

## Align-items
Al igual que `justify-content` esta propiedad te permite distribuir los elementos en un eje, pero esta vez en el eje vertical.

```css
.contenedor {
   border: 1px solid rgb(70, 69, 69);
   padding: 2px;
   width: 100%;
   height: 200px;
   display: flex;
   flex-direction: row;
   flex-wrap: wrap;
   justify-content: center;
   align-items: flex-start;
}
```

Valores posibles:
`align-items: flex-start | flex-end | center | baseline`

* `align-items: flex-end`
* `align-items: center`

---

## ¿Qué es Grid Layout?
CSS Grid es un sistema de diseño bidimensional para la web.
* Permite organizar el contenido en filas y columnas.
* Ideal para crear layouts complejos de forma sencilla y controlada.

```css
.container {
   display: grid;
}
```

---

## Activar Grid y crear columnas/filas
Se activa con `display: grid` en el contenedor padre.
Se definen filas y columnas con:

```css
.grid-container {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr; /* tres columnas iguales */
}
```

```html
<div class="grid-container">
    <div class="caja">A</div>
    <div class="caja">B</div>
    <div class="caja">C</div>
</div>
```

---

## Preguntas
*Preguntas y dudas generales*

---

## Práctica
Integrar Flexbox y Grid Layout en su proyecto.

---

## Resumen de la sesión
**¿Qué hemos aprendido hoy?**
* Flexbox
**¿Para qué sirve Flexbox y Grid Layout?**

---

## ¡Gracias!

TALLER DE PROGRAMACIÓN WEB
M.Sc. Jesamin Melissa Zevallos Quispe
c29741@utp.edu.pe
