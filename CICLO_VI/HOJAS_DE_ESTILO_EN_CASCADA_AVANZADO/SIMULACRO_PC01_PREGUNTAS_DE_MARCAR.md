# Simulacro PC-01: preguntas de marcar

Simulacro original basado únicamente en los temas publicados en las semanas
1–3: diseño responsivo, media queries, Flexbox y CSS Grid. No contiene
preguntas de la evaluación vigente.

## Cómo utilizarlo

- Tiempo sugerido: 20 minutos.
- Marca una sola alternativa por pregunta.
- No revises la clave hasta terminar.
- Puedes responder en el chat con este formato:
  `1B, 2C, 3A, ...`

## Estructura HTML de práctica

Cuando el profesor dice que deben considerar una “estructura básica de HTML”,
se refiere a observar las etiquetas, clases y relaciones padre–hijo. Las
preguntas pedirán decidir qué selector o propiedad CSS controla cada parte.

Usaremos esta estructura ficticia:

```html
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Portal académico</title>
  <link rel="stylesheet" href="estilos.css">
</head>
<body>
  <header class="cabecera">
    <h1>Portal académico</h1>
    <nav class="navegacion">
      <ul class="enlaces">
        <li><a href="#">Inicio</a></li>
        <li><a href="#">Cursos</a></li>
        <li><a href="#">Tareas</a></li>
        <li><a href="#">Contacto</a></li>
      </ul>
    </nav>
  </header>

  <main class="distribucion">
    <aside class="lateral">Menú lateral</aside>

    <section class="contenido">
      <article class="tarjeta">Curso 1</article>
      <article class="tarjeta">Curso 2</article>
      <article class="tarjeta">Curso 3</article>
    </section>

    <aside class="complementario">Avisos</aside>
  </main>

  <footer class="pie">UTP 2026</footer>
</body>
</html>
```

## Preguntas

### 1. Diseño responsivo

¿Qué línea hace que el ancho visible de la página coincida con el ancho del
dispositivo?

A. `<meta charset="UTF-8">`  
B. `<meta name="viewport" content="width=device-width, initial-scale=1.0">`  
C. `<title>Portal académico</title>`  
D. `<link rel="stylesheet" href="estilos.css">`

### 2. Archivo CSS

¿Qué elemento conecta el documento HTML con la hoja de estilos?

A. `<style src="estilos.css">`  
B. `<script href="estilos.css">`  
C. `<link rel="stylesheet" href="estilos.css">`  
D. `<css>estilos.css</css>`

### 3. Contenedor Flexbox

¿Qué regla convierte `.distribucion` en un contenedor Flexbox?

A. `.distribucion { position: flex; }`  
B. `.distribucion { flex: display; }`  
C. `.distribucion { display: flex; }`  
D. `.distribucion { layout: flex; }`

### 4. Flex items

Si `.distribucion` tiene `display: flex`, ¿cuáles son sus flex items directos?

A. Todos los elementos del documento  
B. Los tres elementos `article`  
C. `.lateral`, `.contenido` y `.complementario`  
D. Solamente `.contenido`

### 5. Dirección principal

¿Qué propiedad coloca los hijos de `.distribucion` horizontalmente de
izquierda a derecha?

A. `flex-direction: row`  
B. `align-items: row`  
C. `justify-content: horizontal`  
D. `flex-wrap: row`

### 6. Distribución horizontal

Con `flex-direction: row`, ¿qué regla coloca el primer elemento al inicio, el
último al final y distribuye el espacio sobrante entre ellos?

A. `align-items: center`  
B. `justify-content: space-between`  
C. `justify-content: baseline`  
D. `align-content: space-evenly`

### 7. Eje transversal

Con `flex-direction: row`, ¿qué propiedad centra los elementos verticalmente?

A. `justify-content: center`  
B. `text-align: center`  
C. `align-items: center`  
D. `flex-direction: center`

### 8. Salto de línea

¿Qué propiedad permite que los flex items pasen a una línea nueva cuando no
entran en el ancho disponible?

A. `flex-wrap: wrap`  
B. `flex-direction: column`  
C. `overflow: flex`  
D. `white-space: wrap`

### 9. Crecimiento de la sección central

¿Qué regla permite que `.contenido` aproveche el espacio disponible entre los
dos `aside`?

A. `.contenido { flex: 1; }`  
B. `.contenido { position: center; }`  
C. `.contenido { width: auto-fit; }`  
D. `.contenido { align-content: grow; }`

### 10. Media query

¿Cuál es la sintaxis correcta para aplicar estilos cuando la pantalla mide
`768px` o menos?

A. `@media width < 768px { }`  
B. `@media (max-width: 768px) { }`  
C. `@responsive (768px) { }`  
D. `@media screen = 768px { }`

### 11. Diseño móvil

¿Qué regla apila los tres hijos de `.distribucion` cuando la pantalla es
pequeña?

A. `.distribucion { flex-wrap: none; }`  
B. `.distribucion { align-items: column; }`  
C. `.distribucion { flex-direction: column; }`  
D. `.distribucion { display: block-flex; }`

### 12. Menú horizontal

¿Qué regla convierte la lista `.enlaces` en un menú horizontal?

A. `.enlaces { display: flex; }`  
B. `.enlaces { display: grid-column; }`  
C. `.enlaces { position: horizontal; }`  
D. `.enlaces { list-style: flex; }`

### 13. Menú móvil

¿Qué código vuelve vertical el menú cuando el ancho es menor o igual a
`480px`?

A.

```css
@media (min-width: 480px) {
  .enlaces { flex-direction: row; }
}
```

B.

```css
@media (max-width: 480px) {
  .enlaces { flex-direction: column; }
}
```

C.

```css
@media (max-height: 480px) {
  .enlaces { align-items: vertical; }
}
```

D.

```css
.enlaces {
  media-direction: column;
}
```

### 14. Imagen adaptable

¿Qué reglas evitan que una imagen desborde su contenedor y conservan su
proporción?

A. `width: 1000px; height: 500px`  
B. `max-width: 100%; height: auto`  
C. `min-width: 100%; height: 100%`  
D. `display: flex; width: auto-fit`

### 15. Cuadrícula de tarjetas

Si `.contenido` debe mostrar tres columnas iguales, ¿qué opción es correcta?

A.

```css
.contenido {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
}
```

B.

```css
.contenido {
  display: flex;
  grid-columns: 3;
}
```

C.

```css
.contenido {
  grid-template-rows: 3fr;
}
```

D.

```css
.contenido {
  display: table;
  columns: equal;
}
```

### 16. Separación en Grid

¿Qué propiedad añade una separación de `16px` entre filas y columnas?

A. `padding-items: 16px`  
B. `margin-grid: 16px`  
C. `gap: 16px`  
D. `space-between: 16px`

### 17. Líneas de Grid

¿Qué significa esta regla?

```css
.tarjeta:first-child {
  grid-column: 1 / 3;
}
```

A. La tarjeta ocupa únicamente la columna 3  
B. La tarjeta ocupa desde la línea 1 hasta la línea 3: dos columnas  
C. Se crean tres columnas nuevas  
D. La tarjeta ocupa tres filas

### 18. Flexbox frente a Grid

¿Cuál afirmación es correcta?

A. Flexbox y Grid no pueden utilizarse en la misma página  
B. Flexbox trabaja principalmente en una dimensión y Grid en dos  
C. Grid solo sirve para menús  
D. Flexbox siempre requiere media queries

### 19. Unidades relativas

¿Con respecto a qué se calcula normalmente `1rem`?

A. Al ancho del viewport  
B. Al tamaño de fuente del elemento padre inmediato  
C. Al tamaño de fuente del elemento raíz `html`  
D. A la altura de la imagen más cercana

### 20. Cascada responsiva

Observa:

```css
body {
  background-color: white;
}

@media (max-width: 600px) {
  body {
    background-color: blue;
  }
}
```

¿Qué color tendrá el fondo en una pantalla de `500px`?

A. Blanco  
B. Transparente  
C. Azul  
D. Depende de Flexbox

---

## Clave y explicación

1. **B**. Configura el viewport con el ancho real del dispositivo.
2. **C**. `link` con `rel="stylesheet"` enlaza el CSS externo.
3. **C**. Flexbox se activa mediante `display: flex`.
4. **C**. Solo los hijos directos se convierten en flex items.
5. **A**. `row` es la dirección horizontal predeterminada.
6. **B**. `space-between` deja el espacio entre los elementos.
7. **C**. En una fila, el eje transversal es vertical.
8. **A**. `wrap` permite formar líneas adicionales.
9. **A**. `flex: 1` permite crecer y ocupar el espacio disponible.
10. **B**. `max-width` incluye todo ancho igual o menor al indicado.
11. **C**. `column` cambia el eje principal a vertical.
12. **A**. Los `li` se convierten en flex items de la lista.
13. **B**. Combina `max-width` con `flex-direction: column`.
14. **B**. Limita el ancho y conserva la relación de aspecto.
15. **A**. `repeat(3, 1fr)` crea tres columnas iguales.
16. **C**. `gap` separa los tracks de Grid y también funciona en Flexbox.
17. **B**. Los números representan líneas; el elemento abarca dos tracks.
18. **B**. Flexbox es principalmente unidimensional y Grid bidimensional.
19. **C**. `rem` toma como referencia el tamaño de fuente raíz.
20. **C**. A `500px` se cumple la condición `max-width: 600px`.

## Regla mental para el examen

- Primero identifica el elemento padre.
- Decide si el problema es de una dimensión (Flexbox) o dos (Grid).
- Determina el eje principal antes de elegir `justify-content`.
- Lee cuidadosamente `min-width` frente a `max-width`.
- En Grid, diferencia columnas o filas de las líneas que las delimitan.
