# Repaso para la PC-01: semanas 1–3

Preparado el 2 de septiembre de 2026 a partir de los materiales oficiales de
UTP+Class y las prácticas anteriores disponibles en el curso.

## Situación de la semana 4

La plataforma muestra dos actividades diferentes:

- `S04.s1 PC1`: tarea no calificada, vencida el 6 de septiembre de 2024. Es
  material antiguo y puede utilizarse como práctica.
- `PC-01`: evaluación calificada del ciclo actual, programada para el 2 de
  septiembre de 2026 de 18:35 a 20:05. Indica 20 preguntas, 20 puntos, un
  intento y 90 minutos.

Este repaso no contiene preguntas de la evaluación actual.

## Qué estudiar primero

1. Media queries y comportamiento responsivo.
2. Ejes y propiedades de Flexbox.
3. Estructura y propiedades principales de CSS Grid.
4. Diferencias entre Flexbox y Grid.
5. Aplicación conjunta en una página con `header`, navegación, contenido y
   formulario.

## Semana 1: diseño web responsivo

### Objetivo

Construir páginas que se adapten a computadoras, tabletas y móviles mediante
el `viewport`, medidas flexibles, multimedia adaptable y media queries.

### Conceptos indispensables

- El `viewport` es el área visible de la página en el dispositivo.
- Una media query aplica reglas solo cuando se cumple una condición.
- `max-width` significa “aplicar hasta este ancho”.
- Imágenes y videos no deben desbordar su contenedor.
- `em` y `rem` permiten tamaños de texto relativos.
- El diseño debe probarse en diferentes anchos y orientaciones.

### Viewport correcto

```html
<meta name="viewport" content="width=device-width, initial-scale=1.0">
```

Sin esta declaración, un navegador móvil puede simular un ancho de escritorio
y las media queries no se comportarán como se espera.

### Imagen adaptable

```css
img {
  max-width: 100%;
  height: auto;
}
```

`max-width: 100%` evita que la imagen supere el ancho disponible y
`height: auto` conserva su proporción.

### Media query básica

```css
body {
  font-size: 16px;
}

@media (max-width: 768px) {
  body {
    font-size: 14px;
  }
}
```

La regla interior se aplica cuando el viewport mide `768px` o menos.

### Patrón estudiado en la guía de la semana 1

El ejercicio oficial parte de un contenedor de `960px`, dos columnas de
`700px` y `260px`, y luego lo adapta:

```css
#contenedor {
  width: 960px;
  margin: 0 auto;
}

@media (max-width: 959px) {
  #contenedor { width: 100%; }
  #columna-izq { width: 70%; }
  #columna-der { width: 30%; }
}

@media (max-width: 740px) {
  #columna-izq,
  #columna-der {
    width: 100%;
  }
}
```

La idea examinable es el cambio de columnas laterales a bloques apilados.

## Semana 2: Flexbox

### Objetivo

Distribuir y alinear elementos dentro de un contenedor flexible y adaptar su
dirección o distribución a distintos tamaños de pantalla.

### Relación padre–hijo

```css
.contenedor {
  display: flex;
}
```

El elemento con `display: flex` es el **flex container**. Sus hijos directos se
convierten en **flex items**.

### Los dos ejes

- Eje principal: lo determina `flex-direction`.
- Eje transversal: es perpendicular al principal.
- `justify-content` trabaja sobre el eje principal.
- `align-items` trabaja sobre el eje transversal.

Si `flex-direction: row`, el eje principal es horizontal. Si se cambia a
`column`, el eje principal pasa a ser vertical.

### Propiedades del contenedor

| Propiedad | Función | Valores importantes |
|---|---|---|
| `display` | Activa Flexbox | `flex`, `inline-flex` |
| `flex-direction` | Define dirección y eje principal | `row`, `row-reverse`, `column`, `column-reverse` |
| `flex-wrap` | Decide si los elementos pasan a otra línea | `nowrap`, `wrap`, `wrap-reverse` |
| `flex-flow` | Abrevia dirección y ajuste | `row wrap` |
| `justify-content` | Alinea sobre el eje principal | `flex-start`, `center`, `space-between`, `space-around`, `space-evenly` |
| `align-items` | Alinea items sobre el eje transversal | `stretch`, `flex-start`, `center`, `flex-end`, `baseline` |
| `align-content` | Alinea varias líneas flexibles | Requiere espacio y normalmente `flex-wrap` |
| `gap` | Define separación entre elementos | Por ejemplo, `1rem` |

### Propiedades de los elementos

| Propiedad | Función |
|---|---|
| `flex-grow` | Indica cuánto puede crecer un item |
| `flex-shrink` | Indica cuánto puede reducirse |
| `flex-basis` | Define su tamaño inicial |
| `flex` | Abrevia `grow`, `shrink` y `basis` |
| `align-self` | Cambia la alineación de un item concreto |
| `order` | Cambia el orden visual |

Ejemplo:

```css
.contenedor {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
}

.elemento {
  flex: 1 1 300px;
}
```

### Navegación responsiva

```css
nav ul {
  display: flex;
  list-style: none;
  gap: 1rem;
}

@media (max-width: 480px) {
  nav ul {
    flex-direction: column;
  }
}
```

### Confusiones frecuentes

- `justify-content` no significa siempre alineación horizontal; depende de
  `flex-direction`.
- `align-content` no reemplaza a `align-items`: trabaja con varias líneas.
- Las propiedades `flex-grow`, `flex-shrink` y `flex-basis` pertenecen a los
  hijos, no al contenedor.
- Solo los hijos directos del contenedor son flex items.

## Semana 3: CSS Grid

### Objetivo

Construir diseños bidimensionales controlando filas y columnas, y adaptarlos
mediante media queries.

### Conceptos fundamentales

- Grid es bidimensional: trabaja simultáneamente con filas y columnas.
- El elemento con `display: grid` es el grid container.
- Sus hijos directos son grid items.
- Un **track** es una fila o columna.
- Una **línea** delimita los tracks.
- Una **celda** es la intersección entre una fila y una columna.
- `gap` define el espacio entre filas y columnas.

### Creación de una cuadrícula

```css
.galeria {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 1rem;
}
```

`1fr` representa una fracción del espacio disponible. Con
`repeat(3, 1fr)` se crean tres columnas iguales.

### Columnas adaptables

```css
.galeria {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 1rem;
}
```

Este patrón crea tantas columnas como entren, respetando un mínimo de
`220px`.

### Posicionamiento de elementos

```css
.destacado {
  grid-column: 1 / 3;
  grid-row: 1 / 2;
}
```

Los números se refieren a líneas de la cuadrícula. `1 / 3` ocupa el espacio
comprendido entre las líneas 1 y 3, es decir, dos columnas.

### Grid responsivo con media query

```css
.contenedor {
  display: grid;
  grid-template-columns: 220px 1fr 220px;
  gap: 1rem;
}

@media (max-width: 768px) {
  .contenedor {
    grid-template-columns: 1fr;
  }
}
```

En escritorio hay tres columnas; en pantallas pequeñas todos los elementos se
apilan en una sola.

## Flexbox frente a Grid

| Flexbox | Grid |
|---|---|
| Principalmente unidimensional | Bidimensional |
| Organiza una fila o una columna | Organiza filas y columnas |
| Excelente para menús y alineación interna | Excelente para la estructura general de la página |
| El contenido influye mucho en la distribución | El diseño define explícitamente la cuadrícula |

No son competidores: normalmente se usa Grid para el layout general y Flexbox
para alinear componentes internos.

## Práctica antigua de la semana 4

La tarea `S04.s1 PC1` de 2024 solicita combinar exactamente los conceptos de
las semanas anteriores:

- Estructura con `header`, `main` y `footer`.
- Flexbox en fila para escritorio y columna para pantallas menores.
- Fondo azul por debajo de `600px`.
- Menú horizontal que se vuelve vertical por debajo de `480px`.
- Formulario con tres campos y un botón.
- Encabezados con tamaños relativos en móviles.

Patrón de práctica:

```css
.contenido,
.formulario,
nav ul {
  display: flex;
  gap: 1rem;
}

@media (max-width: 768px) {
  .contenido,
  .formulario {
    flex-direction: column;
  }

  h1 { font-size: 1.8rem; }
  h2 { font-size: 1.4rem; }
}

@media (max-width: 600px) {
  body {
    background-color: blue;
  }
}

@media (max-width: 480px) {
  nav ul {
    flex-direction: column;
  }
}
```

## Autoevaluación rápida

1. ¿Qué etiqueta permite que el viewport coincida con el ancho del dispositivo?
2. ¿Qué significa `@media (max-width: 768px)`?
3. ¿Por qué se usa `height: auto` en una imagen responsiva?
4. ¿Qué elemento se convierte en flex container?
5. ¿Qué diferencia existe entre `justify-content` y `align-items`?
6. ¿Qué sucede con los ejes al usar `flex-direction: column`?
7. ¿Qué diferencia existe entre `nowrap` y `wrap`?
8. ¿Qué representa `flex: 1 1 300px`?
9. ¿Cuándo conviene utilizar `align-content`?
10. ¿Cuál es la diferencia central entre Flexbox y Grid?
11. ¿Qué crea `grid-template-columns: repeat(3, 1fr)`?
12. ¿Qué significa `grid-column: 1 / 3`?
13. ¿Para qué sirve `gap`?
14. ¿Cómo convertirías tres columnas en una para móvil?
15. ¿Puede utilizarse Grid junto con Flexbox?

### Respuestas breves

1. `<meta name="viewport" content="width=device-width, initial-scale=1.0">`.
2. Las reglas se aplican cuando el viewport mide 768 píxeles o menos.
3. Para mantener la proporción original de la imagen.
4. El elemento que recibe `display: flex`.
5. El primero alinea sobre el eje principal y el segundo sobre el transversal.
6. El eje principal se vuelve vertical y el transversal horizontal.
7. `nowrap` mantiene una línea; `wrap` permite crear varias.
8. Crecimiento 1, reducción 1 y tamaño base de 300 píxeles.
9. Cuando existen varias líneas flexibles y hay espacio transversal disponible.
10. Flexbox organiza principalmente una dimensión; Grid controla dos.
11. Tres columnas del mismo tamaño.
12. El elemento ocupa desde la línea 1 hasta la 3: dos columnas.
13. Para separar filas, columnas o elementos sin usar márgenes individuales.
14. Dentro de una media query, usando `grid-template-columns: 1fr`.
15. Sí; Grid puede estructurar la página y Flexbox alinear sus componentes.

## Material que no debes priorizar

- `SEMANA02/MATERIALES/S02_s1_LF.pptx` trata SQL y bases de datos; fue
  publicado por error dentro del curso.
- `SEMANA03/MATERIALES/S03_s1_Material.pptx` es una plantilla genérica.
- `SEMANA04/MATERIALES/S04_s1_Material.pdf` también es una plantilla genérica
  incompleta y no aporta contenido CSS útil.

## Archivos que sí debes revisar

- `SEMANA01/MATERIALES/S01_s1_semana_01_HE.pptx`
- `SEMANA01/MATERIALES/S01_Guia.docx`
- `SEMANA01/MATERIALES/S01_Ejercicio_de_clase_01.docx`
- `SEMANA02/MATERIALES/S02_s1_semana_02_HE.pptx`
- `SEMANA03/MATERIALES/S03_s1_Material_VFF.pptx`
- `SEMANA03/TAREA FLEXBOX.pdf`
- `SEMANA03/index.html`
- `SEMANA03/estilos.css`
- `SEMANA04/ENUNCIADO_S04_PC1.md`, solo como práctica antigua.

No se inició ningún intento ni se realizó ninguna publicación en UTP+Class.
