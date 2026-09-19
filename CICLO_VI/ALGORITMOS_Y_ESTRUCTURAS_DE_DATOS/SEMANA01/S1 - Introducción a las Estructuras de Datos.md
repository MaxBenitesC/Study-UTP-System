# S1 - Introducción a las Estructuras de Datos

Curso: Algoritmos y Estructuras de Datos  
Semana: 01  
PDF fuente: `S1 - Introducción a las Estructuras de Datos.pdf`

## Lectura general

La sesión introduce qué es una estructura de datos, por qué importa en programación y cómo se clasifican las estructuras según dos criterios principales:

- si su tamaño es fijo o puede cambiar durante la ejecución;
- si sus elementos están organizados linealmente o mediante relaciones más complejas.

La idea central es que una estructura de datos no es solo “guardar información”, sino organizarla para que operaciones como insertar, eliminar, buscar, actualizar o recorrer datos se puedan hacer de forma eficiente.

## Contenido completo interpretado por lámina

### Lámina 1: Portada

Título: Algoritmos y Estructuras de Datos.

Visualmente muestra una portada institucional de UTP con una imagen abstracta geométrica de colores rojo, morado, azul y naranja. La imagen funciona como fondo decorativo; no contiene información técnica.

Fuente visual indicada en el PDF: `https://www.pixelstalk.net/wp-content/uploads/images6/Abstract-Wallpaper-HD-Free-download.png`

### Lámina 2: Tema de la semana

Título: Introducción a las Estructuras de Datos.  
Semana: 01.

La lámina presenta el tema específico de la sesión: una introducción conceptual al área de estructuras de datos.

Visualmente mantiene el mismo fondo abstracto de portada.

### Lámina 3: Dudas de la clase anterior

Título: Dudas de la clase anterior.

La lámina abre un espacio para resolver preguntas previas. En el contexto de una primera semana, sirve como transición o activación inicial.

Visual: aparece una ilustración de una persona estudiando con laptop y libros, con una nube de pensamiento. El mensaje visual sugiere repaso, memoria y preguntas pendientes.

Fuente visual indicada: `https://knowledgeone.ca/wp-content/uploads/2018/11/memories.jpg`

### Lámina 4: Logro de aprendizaje

Al finalizar la sesión, el estudiante explica y clasifica los diferentes tipos de estructuras de datos y su funcionamiento en el almacenamiento de datos en las computadoras, considerando sus características y diferencias entre estructuras estáticas, dinámicas lineales y dinámicas no lineales.

Interpretación:

- El objetivo no es todavía implementar estructuras complejas.
- El foco es comprender el mapa conceptual: qué tipos existen, cómo almacenan información y en qué se diferencian.
- La clasificación clave de la sesión es:
  - estructuras estáticas;
  - estructuras dinámicas lineales;
  - estructuras dinámicas no lineales.

Visual: ilustración de una persona sobre una pila de libros con una bandera. Representa aprendizaje, logro académico y construcción de conocimiento.

Fuente visual indicada: `https://img.freepik.com/free-vector/clever-man-student-standing-books-stack-with-flag-self-learning-personal-improvement-knowledge-obtaining-educational-achievement_335657-3461.jpg`

### Lámina 5: Conocimientos previos

Preguntas planteadas:

- ¿Qué es un bit y un byte, y cómo se utilizan en el almacenamiento de datos en las computadoras?
- ¿Qué es un índice en un array y cómo se usa para acceder a los elementos de un array?
- ¿Qué significa que una estructura de datos sea LIFO o FIFO, y puedes dar un ejemplo de cada una?

Interpretación:

- Bit y byte son la base física/lógica del almacenamiento.
- Índice es la posición usada para acceder a un elemento en una estructura como un arreglo.
- LIFO y FIFO anticipan estructuras dinámicas lineales:
  - LIFO: último en entrar, primero en salir; ejemplo: pila.
  - FIFO: primero en entrar, primero en salir; ejemplo: cola.

### Lámina 6: Utilidad

Preguntas planteadas:

- ¿Cómo puedes determinar qué estructura de datos es la más eficiente para un problema específico?
- ¿Cómo mejoran las estructuras de datos la velocidad y la eficiencia de las operaciones de búsqueda y manipulación de datos en un programa?
- ¿De qué manera las estructuras de datos no lineales, como árboles y grafos, facilitan la resolución de problemas complejos en comparación con las estructuras lineales?

Texto desarrollado:

Las estructuras de datos son fundamentales para determinar la eficiencia de un programa al permitir una gestión óptima del almacenamiento y manipulación de datos. La elección de la estructura adecuada, ya sea lineal o no lineal, está condicionada por las necesidades específicas del problema, afectando directamente la velocidad y eficacia de operaciones como búsquedas e inserciones. Estructuras no lineales como árboles y grafos son especialmente útiles para resolver problemas complejos, proporcionando una organización jerárquica o interconectada que facilita el acceso y procesamiento de la información en comparación con las estructuras lineales.

Interpretación:

La estructura correcta depende del problema. Si se necesita acceso directo por posición, un arreglo puede ser adecuado. Si se necesita insertar y eliminar con frecuencia, una lista enlazada puede ser mejor. Si se necesita representar jerarquías, se usan árboles. Si se necesita representar redes de relaciones, se usan grafos.

### Lámina 7: ¿Qué es una estructura de datos?

Una estructura de datos es una forma particular de organizar y almacenar datos en una computadora para que puedan ser usados de manera eficiente. La idea principal es estructurar los datos de tal manera que se puedan realizar operaciones específicas, como inserciones, eliminaciones, búsquedas y actualizaciones, de forma rápida y eficiente.

Interpretación:

La estructura de datos define:

- cómo se guardan los datos;
- cómo se accede a ellos;
- qué operaciones son fáciles o costosas;
- qué restricciones existen.

Ejemplo: un arreglo permite acceso rápido por índice, pero insertar en medio puede requerir desplazar elementos. Una lista enlazada permite insertar con enlaces, pero acceder al elemento número 100 requiere recorrer nodos.

### Lámina 8: ¿Cómo almacenan datos las computadoras?

Las computadoras almacenan datos en su memoria utilizando una variedad de técnicas. A nivel básico, los datos se almacenan en bits y bytes en la memoria RAM, discos duros u otros dispositivos de almacenamiento. Las estructuras de datos permiten que estos bits y bytes se organicen de maneras específicas para optimizar el acceso y la manipulación de la información.

Interpretación:

La computadora no entiende “listas”, “pilas” o “árboles” como conceptos abstractos. Finalmente todo se representa como bits y bytes. Las estructuras de datos son modelos de organización que el programa usa encima de esa memoria física.

### Lámina 9: Tipos de estructuras de datos

Las estructuras de datos se pueden clasificar en varias categorías, siendo las más comunes las estáticas y dinámicas. Estas a su vez se dividen en lineales y no lineales.

Interpretación:

Clasificación principal:

- Estáticas: tamaño definido y fijo.
- Dinámicas: tamaño modificable durante la ejecución.
- Lineales: elementos organizados en secuencia.
- No lineales: elementos organizados con relaciones jerárquicas o de red.

### Lámina 10: Estructuras de datos estáticas

Las estructuras de datos estáticas son aquellas en las que el tamaño y la estructura de la organización de los datos se define en el momento de la compilación y no puede cambiar durante la ejecución del programa. Ejemplos comunes incluyen los arreglos y las matrices.

Interpretación:

Una estructura estática reserva una cantidad fija de espacio. Esto puede hacerla eficiente, pero menos flexible. Si se necesita más capacidad de la prevista, no se puede ampliar directamente sin crear otra estructura.

### Lámina 11: Estructuras de datos estáticas: arrays y matrices

Ejemplos:

- Arrays o arreglos: colección de elementos del mismo tipo, donde cada elemento se identifica mediante un índice o una clave.
- Matrices: extensión de los arrays en más de una dimensión, típicamente dos dimensiones, aunque pueden tener más.

Interpretación:

Un arreglo unidimensional puede verse como una fila de datos:

```text
índice:  0   1   2   3
valor:  10  20  30  40
```

Una matriz bidimensional puede verse como una tabla:

```text
fila/col   0   1   2
0         10  20  30
1         40  50  60
```

### Lámina 12: Estructuras de datos dinámicas lineales

Las estructuras de datos dinámicas permiten que el tamaño y la organización cambien durante la ejecución del programa. Las estructuras dinámicas lineales son aquellas en las que los elementos se organizan secuencialmente uno tras otro.

Interpretación:

Son útiles cuando no se conoce de antemano cuántos elementos se necesitarán. “Lineales” significa que se puede pensar en los elementos como una cadena o secuencia.

### Lámina 13: Estructuras dinámicas lineales: listas, pilas y colas

Ejemplos:

- Listas enlazadas: colección de nodos donde cada nodo contiene un dato y una referencia o enlace al siguiente nodo.
- Pilas: colección de elementos que sigue el principio LIFO, donde el último elemento añadido es el primero en ser removido.
- Colas: colección de elementos que sigue el principio FIFO, donde el primer elemento añadido es el primero en ser removido.

Interpretación:

Representación conceptual:

```text
Lista enlazada:
[dato | sig] -> [dato | sig] -> [dato | null]

Pila LIFO:
push A, push B, push C
pop devuelve C

Cola FIFO:
enqueue A, enqueue B, enqueue C
dequeue devuelve A
```

### Lámina 14: Estructuras de datos dinámicas no lineales: árboles

Las estructuras de datos dinámicas no lineales no se organizan en una secuencia lineal.

Ejemplo:

- Árboles: estructura jerárquica en la que cada nodo tiene un valor y una lista de referencias a otros nodos, llamados hijos. Un caso especial es el árbol binario, donde cada nodo tiene como máximo dos hijos.

Interpretación:

Un árbol modela jerarquías. Ejemplos comunes:

- carpetas y subcarpetas;
- organigramas;
- expresiones matemáticas;
- árboles de búsqueda.

Representación:

```text
        raíz
       /    \
   hijo A  hijo B
           /    \
       hijo C  hijo D
```

### Lámina 15: Estructuras de datos dinámicas no lineales: grafos y tablas hash

Ejemplos:

- Grafos: colección de nodos o vértices y arcos o aristas que conectan pares de nodos. Pueden ser dirigidos o no dirigidos, dependiendo de si las conexiones tienen dirección.
- Tablas hash: estructura que asocia claves a valores y utiliza una función hash para determinar la posición donde debe almacenar cada par clave-valor.

Interpretación:

Un grafo representa relaciones de muchos a muchos:

```text
A ---- B
|    / |
C ---- D
```

Casos de uso:

- redes sociales;
- rutas entre ciudades;
- dependencias entre tareas;
- conexiones de red.

Una tabla hash permite búsquedas rápidas por clave:

```text
clave: "codigo" -> función hash -> posición interna -> valor
```

Ejemplo conceptual:

```text
"DNI123" -> "Juan Pérez"
"DNI456" -> "Ana Torres"
```

### Lámina 16: Práctica

Indicación: Trata de aplicar lo aprendido a tu proyecto.

Interpretación:

La práctica sugerida es identificar qué datos maneja un proyecto y elegir una estructura adecuada. Por ejemplo:

- lista de alumnos: arreglo o lista;
- historial de operaciones: pila;
- atención por orden de llegada: cola;
- categorías y subcategorías: árbol;
- mapa de conexiones: grafo;
- búsqueda por código: tabla hash.

### Lámina 17: Cierre

Preguntas de cierre:

- ¿Qué es una estructura de datos y por qué es importante en la programación?
- ¿Cómo difieren las estructuras de datos estáticas de las dinámicas en términos de almacenamiento y manipulación?
- ¿Cuáles son las ventajas de usar estructuras de datos no lineales como árboles y grafos en comparación con las estructuras lineales?

Interpretación:

Respuestas guía:

- Una estructura de datos organiza información para que el programa pueda operar con ella de forma eficiente.
- Las estáticas tienen tamaño fijo; las dinámicas pueden crecer o reducirse durante la ejecución.
- Las no lineales permiten representar jerarquías y relaciones complejas que no caben bien en una secuencia simple.

Visual: ilustración de checklist con una persona señalando una lista. Representa evaluación o cierre de puntos aprendidos.

Fuente visual indicada: `https://dpemfoco.com.br/wp-content/uploads/2019/08/Departamento-de-Pessoal-em-Foco-Checklist-Check-list-Checklists.png`

### Lámina 18: Cierre institucional

La última lámina muestra únicamente el logotipo de UTP sobre fondo blanco con patrón de puntos. No contiene contenido técnico adicional.

## Clasificación consolidada

| Tipo | Tamaño | Organización | Ejemplos | Uso típico |
|---|---:|---|---|---|
| Estática | Fijo | Generalmente lineal o tabular | Arreglos, matrices | Cuando se conoce la cantidad de datos |
| Dinámica lineal | Variable | Secuencial | Listas enlazadas, pilas, colas | Cuando se requiere crecer/reducir y mantener orden |
| Dinámica no lineal | Variable | Jerárquica o conectada | Árboles, grafos, tablas hash | Cuando se representan relaciones complejas o búsquedas por clave |

## Conceptos clave

- Bit: unidad mínima de información, con valor 0 o 1.
- Byte: conjunto de 8 bits.
- Índice: posición usada para acceder a un elemento, usualmente comenzando en 0.
- Array/arreglo: colección de elementos del mismo tipo accesibles por índice.
- Matriz: arreglo de dos o más dimensiones.
- LIFO: último en entrar, primero en salir.
- FIFO: primero en entrar, primero en salir.
- Nodo: unidad de información en estructuras como listas, árboles o grafos.
- Arista: conexión entre nodos en un grafo.
- Función hash: función que transforma una clave en una posición o código para ubicar datos.

## Ideas para estudiar

1. No memorizar solo nombres: asociar cada estructura con el problema que resuelve.
2. Comparar operaciones:
   - acceso;
   - búsqueda;
   - inserción;
   - eliminación;
   - recorrido.
3. Pensar en el costo de mover datos o recorrer enlaces.
4. Elegir estructuras según restricciones reales: tamaño de datos, frecuencia de cambios y tipo de consulta.

