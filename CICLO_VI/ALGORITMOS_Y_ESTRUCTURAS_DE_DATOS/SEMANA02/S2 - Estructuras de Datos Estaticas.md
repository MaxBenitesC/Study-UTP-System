# S2 - Estructuras de Datos Estáticas

Curso: Algoritmos y Estructuras de Datos  
Semana: 02  
PDF fuente: `S2 - Estructuras de Datos Estaticas.pdf`

## Lectura general

La sesión desarrolla estructuras de datos estáticas, con énfasis en arreglos unidimensionales. El objetivo práctico es implementar operaciones básicas sobre arreglos:

- inserción;
- actualización;
- eliminación;
- recorrido;
- copia.

La idea técnica principal es que un arreglo estático tiene tamaño fijo. Por eso, operaciones como “insertar” o “eliminar” no cambian directamente el tamaño interno del arreglo original; se simulan desplazando elementos o creando un nuevo arreglo.

## Contenido completo interpretado por lámina

### Lámina 1: Portada

Título: Algoritmos y Estructuras de Datos.

Visual: portada institucional de UTP con fondo abstracto geométrico en tonos rojos, morados, azules y naranjas. Es decorativa y no contiene información técnica.

Fuente visual indicada en el PDF: `https://www.pixelstalk.net/wp-content/uploads/images6/Abstract-Wallpaper-HD-Free-download.png`

### Lámina 2: Tema de la semana

Título: Estructuras de Datos Estáticas.  
Semana: 02.

La sesión se enfoca en estructuras cuyo tamaño se define al crearse y no cambia durante la ejecución.

### Lámina 3: Dudas de la clase anterior

Título: Dudas de la clase anterior.  
Tema recordado: Introducción a las Estructuras de Datos.

Interpretación:

La lámina conecta la sesión 2 con la semana 1, que introdujo la clasificación de estructuras de datos.

Visual: persona estudiando con laptop y libros debajo de una nube de pensamiento. Representa repaso y resolución de dudas.

Fuente visual indicada: `https://knowledgeone.ca/wp-content/uploads/2018/11/memories.jpg`

### Lámina 4: Logro de aprendizaje

Al finalizar la sesión, el estudiante implementará operaciones con arreglos unidimensionales, incluyendo inserción, actualización, eliminación, recorrido y copia, utilizando métodos adecuados para cada operación.

Interpretación:

Esta semana ya pasa de la clasificación conceptual a la implementación. El estudiante debe poder escribir métodos que modifiquen, recorran o dupliquen arreglos.

Visual: persona sobre libros con una bandera, asociada a logro académico.

Fuente visual indicada: `https://img.freepik.com/free-vector/clever-man-student-standing-books-stack-with-flag-self-learning-personal-improvement-knowledge-obtaining-educational-achievement_335657-3461.jpg`

### Lámina 5: Conocimientos previos

Preguntas planteadas:

- ¿Cómo se declara un arreglo unidimensional en tu lenguaje de programación preferido?
- ¿Cómo accederías al tercer elemento de un arreglo unidimensional?
- ¿Qué operación utilizarías para recorrer todos los elementos de un arreglo unidimensional?

Interpretación:

Para entender la clase se requiere saber:

- declarar arreglos;
- acceder por índice;
- usar bucles para recorrer elementos.

En lenguajes con índice base cero, el tercer elemento está en la posición `2`.

Ejemplo en Java:

```java
int[] numeros = {10, 20, 30};
int tercerElemento = numeros[2];
```

### Lámina 6: Utilidad

Preguntas planteadas:

- ¿Cómo pueden los arreglos unidimensionales ayudarte a almacenar y gestionar una lista de datos homogéneos, como una lista de calificaciones de estudiantes?
- ¿En qué situaciones es más eficiente usar un arreglo unidimensional en lugar de una lista enlazada?
- ¿Cómo puedes utilizar un arreglo unidimensional para realizar operaciones matemáticas simples, como sumar todos los elementos del arreglo?

Texto desarrollado:

Los arreglos unidimensionales son útiles para almacenar y gestionar listas de datos homogéneos, como una lista de calificaciones de estudiantes, de manera eficiente. Son especialmente beneficiosos cuando se requiere acceso rápido a los elementos mediante índices, ya que permiten acceder a cualquier posición en tiempo constante. A diferencia de las listas enlazadas, los arreglos ocupan menos memoria y su estructura contigua permite un mejor rendimiento en términos de cacheo de CPU. Además, facilitan la realización de operaciones matemáticas simples, como sumar todos los elementos del arreglo, ya que permiten recorrer sus elementos de manera secuencial de forma sencilla.

Interpretación:

Un arreglo es útil cuando:

- todos los datos son del mismo tipo;
- se conoce o controla la cantidad de elementos;
- se necesita acceder rápidamente a posiciones específicas;
- se harán recorridos completos, como sumar notas o buscar máximos.

La ventaja de cacheo viene de que los elementos están ubicados de forma contigua en memoria, lo que favorece lecturas secuenciales eficientes.

### Lámina 7: Estructuras de Datos Estáticas

Las estructuras de datos estáticas son aquellas cuyo tamaño se define en el momento de su creación y no puede cambiar durante la ejecución del programa. Un ejemplo típico son los arreglos unidimensionales, también conocidos como vectores.

Operaciones comunes revisadas:

- inserción;
- actualización;
- eliminación;
- recorrido;
- copia.

Interpretación:

Aunque el arreglo tenga tamaño fijo, se pueden programar operaciones que aparentan modificar su contenido. Si se necesita cambiar la cantidad de elementos, normalmente se crea un nuevo arreglo con el tamaño requerido y se copian los datos necesarios.

### Lámina 8: Inserción

En un arreglo estático, la inserción de un nuevo elemento no es posible directamente debido a la limitación del tamaño fijo. Sin embargo, se puede simular la inserción desplazando elementos a la derecha y ubicando el nuevo valor en la posición deseada, siempre que haya espacio disponible.

Interpretación:

Insertar significa colocar un nuevo valor en una posición. En un arreglo fijo hay dos escenarios:

- si se mantiene el mismo tamaño, se desplazan elementos a la derecha y se pierde o se sobrescribe el último espacio;
- si se quiere conservar todo, se debe crear un arreglo nuevo con mayor capacidad.

Ejemplo conceptual:

```text
Arreglo original:
[10, 20, 30, 40]

Insertar 99 en índice 1, manteniendo tamaño 4:
[10, 99, 20, 30]

El valor 40 queda fuera porque no hay espacio adicional.
```

### Lámina 9: Código de inserción

Código mostrado en la lámina, transcrito desde la imagen:

```java
public static int[] insertarEnArreglo(int[] arreglo, int indice, int valor) {
    if (indice < 0 || indice >= arreglo.length) {
        System.out.println("Índice fuera de rango.");
        return arreglo;
    }

    int[] nuevoArreglo = new int[arreglo.length];
    System.arraycopy(arreglo, 0, nuevoArreglo, 0, indice);
    nuevoArreglo[indice] = valor;
    System.arraycopy(arreglo, indice, nuevoArreglo, indice + 1, arreglo.length - indice - 1);
    return nuevoArreglo;
}
```

Interpretación del código:

- Valida que el índice esté dentro del rango del arreglo.
- Crea un nuevo arreglo del mismo tamaño.
- Copia los elementos anteriores al índice.
- Inserta el nuevo valor en la posición indicada.
- Copia los elementos desde el índice original una posición a la derecha.
- Como el tamaño no cambia, el último elemento original queda descartado.

Observación técnica:

El método se llama “insertar”, pero no aumenta la capacidad. Simula una inserción dentro del mismo tamaño. Si se quisiera conservar todos los elementos, el nuevo arreglo debería tener tamaño `arreglo.length + 1`.

### Lámina 10: Actualización

Actualizar un elemento en un arreglo es bastante sencillo: se accede al índice deseado y se cambia el valor.

Interpretación:

Actualizar no requiere mover elementos. Solo se reemplaza el contenido de una posición existente.

Ejemplo conceptual:

```text
Arreglo original:
[10, 20, 30]

Actualizar índice 1 con valor 99:
[10, 99, 30]
```

### Lámina 11: Código de actualización

Código mostrado en la lámina, transcrito desde la imagen:

```java
public static void actualizarArreglo(int[] arreglo, int indice, int valor) {
    if (indice < 0 || indice >= arreglo.length) {
        System.out.println("Índice fuera de rango.");
        return;
    }
    arreglo[indice] = valor;
}
```

Interpretación del código:

- Recibe el arreglo, el índice y el nuevo valor.
- Verifica que el índice exista.
- Si el índice no existe, muestra un mensaje y termina.
- Si el índice es válido, reemplaza el valor en esa posición.

Complejidad:

- Tiempo: constante, `O(1)`, porque accede directamente por índice.
- Memoria adicional: constante, `O(1)`.

### Lámina 12: Eliminación

La eliminación de un elemento en un arreglo estático también se simula desplazando los elementos a la izquierda para cubrir el espacio del elemento eliminado. Esto no cambia el tamaño del arreglo original.

Interpretación:

Eliminar en un arreglo fijo no borra físicamente una posición intermedia. Para representar la eliminación, se desplazan los elementos posteriores a la izquierda.

Ejemplo conceptual:

```text
Arreglo original:
[10, 20, 30, 40]

Eliminar índice 1:
[10, 30, 40]
```

Si se exige mantener el mismo tamaño físico, podría quedar una posición sobrante al final. El código de la lámina opta por crear un arreglo nuevo de tamaño menor.

### Lámina 13: Código de eliminación

Código mostrado en la lámina, transcrito desde la imagen:

```java
public static int[] eliminarDeArreglo(int[] arreglo, int indice) {
    if (indice < 0 || indice >= arreglo.length) {
        System.out.println("Índice fuera de rango.");
        return arreglo;
    }

    int[] nuevoArreglo = new int[arreglo.length - 1];
    System.arraycopy(arreglo, 0, nuevoArreglo, 0, indice);
    System.arraycopy(arreglo, indice + 1, nuevoArreglo, indice, arreglo.length - indice - 1);
    return nuevoArreglo;
}
```

Interpretación del código:

- Valida que el índice sea correcto.
- Crea un nuevo arreglo con un elemento menos.
- Copia los elementos anteriores al índice eliminado.
- Copia los elementos posteriores al índice eliminado en la posición previa.
- Devuelve el nuevo arreglo.

Observación técnica:

Aquí sí cambia el tamaño del arreglo retornado. El arreglo original no cambia su tamaño; lo que se hace es devolver una nueva estructura con menor longitud.

### Lámina 14: Recorrido

Recorrer un arreglo implica acceder a cada uno de sus elementos, generalmente usando un bucle.

Interpretación:

El recorrido permite ejecutar una acción sobre todos los elementos:

- imprimirlos;
- sumarlos;
- buscar un valor;
- encontrar el máximo o mínimo;
- validar condiciones.

Ejemplo conceptual:

```text
[10, 20, 30]

Recorrido:
visitar 10
visitar 20
visitar 30
```

### Lámina 15: Código de recorrido

Código mostrado en la lámina, transcrito desde la imagen:

```java
public static void recorrerArreglo(int[] arreglo) {
    for (int elemento : arreglo) {
        System.out.println(elemento);
    }
}
```

Interpretación del código:

- Usa un bucle `for-each`.
- En cada iteración, la variable `elemento` toma el valor de una posición del arreglo.
- Imprime cada elemento.

Complejidad:

- Tiempo: lineal, `O(n)`, porque visita todos los elementos.
- Memoria adicional: constante, `O(1)`.

### Lámina 16: Copia

Copiar un arreglo es crear un nuevo arreglo con los mismos elementos que el arreglo original.

Interpretación:

Copiar evita que dos variables dependan del mismo arreglo. En Java, si solo se asigna una variable a otra, ambas pueden apuntar al mismo arreglo. Con una copia, se crea un arreglo distinto con los mismos valores.

Ejemplo:

```java
int[] original = {10, 20, 30};
int[] referencia = original; // no copia; apunta al mismo arreglo
int[] copia = original.clone(); // copia superficial del arreglo
```

### Lámina 17: Código de copia

Código mostrado en la lámina, transcrito desde la imagen:

```java
public static int[] copiarArreglo(int[] arreglo) {
    return arreglo.clone();
}
```

Interpretación del código:

- `clone()` crea otro arreglo con los mismos elementos.
- Para un arreglo de enteros, esto es suficiente porque los valores son primitivos.
- Si fuera un arreglo de objetos, `clone()` copiaría las referencias, no necesariamente los objetos internos.

Complejidad:

- Tiempo: lineal, `O(n)`, porque debe copiar todos los elementos.
- Memoria adicional: lineal, `O(n)`, porque crea un nuevo arreglo.

### Lámina 18: Práctica

Indicación: Trata de aplicar lo aprendido a tu proyecto.

Interpretación:

La práctica puede consistir en crear métodos para administrar un arreglo de datos homogéneos. Ejemplos:

- notas de estudiantes;
- edades;
- códigos de productos;
- cantidades de stock;
- puntajes.

Operaciones sugeridas:

- insertar una nota en una posición;
- actualizar una nota;
- eliminar una nota;
- recorrer todas las notas para mostrarlas;
- copiar el arreglo antes de modificarlo.

### Lámina 19: Cierre

Preguntas de cierre:

- ¿Por qué los arreglos unidimensionales son eficientes para acceder a datos mediante índices?
- ¿Cómo ayuda la estructura contigua de los arreglos unidimensionales al cacheo de CPU?
- ¿En qué escenarios específicos es más ventajoso utilizar arreglos unidimensionales frente a otras estructuras de datos?

Interpretación:

Respuestas guía:

- Son eficientes porque la posición de cualquier elemento se calcula directamente con base en el índice.
- La memoria contigua mejora el cacheo porque el procesador puede cargar bloques cercanos y recorrerlos con menos saltos de memoria.
- Son ventajosos cuando el tamaño es conocido, los datos son homogéneos y se requiere acceso rápido por posición.

Visual: ilustración de checklist con persona señalando tareas. Representa evaluación de cierre.

Fuente visual indicada: `https://dpemfoco.com.br/wp-content/uploads/2019/08/Departamento-de-Pessoal-em-Foco-Checklist-Check-list-Checklists.png`

### Lámina 20: Cierre institucional

La última lámina muestra únicamente el logotipo de UTP sobre fondo blanco con patrón de puntos. No contiene contenido técnico adicional.

## Operaciones sobre arreglos unidimensionales

| Operación | Qué hace | Cambia tamaño original | Estrategia usual | Costo aproximado |
|---|---|---:|---|---:|
| Inserción | Coloca un valor en una posición | No directamente | desplazar a la derecha o crear nuevo arreglo | `O(n)` |
| Actualización | Reemplaza un valor existente | No | asignar por índice | `O(1)` |
| Eliminación | Quita un elemento lógico | No directamente | desplazar a la izquierda o crear nuevo arreglo | `O(n)` |
| Recorrido | Visita todos los elementos | No | bucle | `O(n)` |
| Copia | Duplica el arreglo | Crea otro arreglo | `clone()` o copia manual | `O(n)` |

## Código consolidado de la sesión

```java
public static int[] insertarEnArreglo(int[] arreglo, int indice, int valor) {
    if (indice < 0 || indice >= arreglo.length) {
        System.out.println("Índice fuera de rango.");
        return arreglo;
    }

    int[] nuevoArreglo = new int[arreglo.length];
    System.arraycopy(arreglo, 0, nuevoArreglo, 0, indice);
    nuevoArreglo[indice] = valor;
    System.arraycopy(arreglo, indice, nuevoArreglo, indice + 1, arreglo.length - indice - 1);
    return nuevoArreglo;
}

public static void actualizarArreglo(int[] arreglo, int indice, int valor) {
    if (indice < 0 || indice >= arreglo.length) {
        System.out.println("Índice fuera de rango.");
        return;
    }
    arreglo[indice] = valor;
}

public static int[] eliminarDeArreglo(int[] arreglo, int indice) {
    if (indice < 0 || indice >= arreglo.length) {
        System.out.println("Índice fuera de rango.");
        return arreglo;
    }

    int[] nuevoArreglo = new int[arreglo.length - 1];
    System.arraycopy(arreglo, 0, nuevoArreglo, 0, indice);
    System.arraycopy(arreglo, indice + 1, nuevoArreglo, indice, arreglo.length - indice - 1);
    return nuevoArreglo;
}

public static void recorrerArreglo(int[] arreglo) {
    for (int elemento : arreglo) {
        System.out.println(elemento);
    }
}

public static int[] copiarArreglo(int[] arreglo) {
    return arreglo.clone();
}
```

## Puntos técnicos importantes

- En Java, los índices empiezan en `0`.
- El último índice válido es `arreglo.length - 1`.
- Acceder con un índice inválido produce error; por eso los métodos validan rango.
- `System.arraycopy` permite copiar bloques de un arreglo a otro.
- La actualización es rápida porque no mueve datos.
- La inserción y eliminación son más costosas porque implican copias o desplazamientos.
- `clone()` crea una copia del arreglo; en arreglos de tipos primitivos copia los valores.

## Ejemplo completo de uso

```java
public class DemoArreglos {
    public static void main(String[] args) {
        int[] datos = {10, 20, 30, 40};

        datos = insertarEnArreglo(datos, 1, 99);
        recorrerArreglo(datos); // 10, 99, 20, 30

        actualizarArreglo(datos, 2, 88);
        recorrerArreglo(datos); // 10, 99, 88, 30

        datos = eliminarDeArreglo(datos, 1);
        recorrerArreglo(datos); // 10, 88, 30

        int[] copia = copiarArreglo(datos);
        recorrerArreglo(copia);
    }

    public static int[] insertarEnArreglo(int[] arreglo, int indice, int valor) {
        if (indice < 0 || indice >= arreglo.length) {
            System.out.println("Índice fuera de rango.");
            return arreglo;
        }

        int[] nuevoArreglo = new int[arreglo.length];
        System.arraycopy(arreglo, 0, nuevoArreglo, 0, indice);
        nuevoArreglo[indice] = valor;
        System.arraycopy(arreglo, indice, nuevoArreglo, indice + 1, arreglo.length - indice - 1);
        return nuevoArreglo;
    }

    public static void actualizarArreglo(int[] arreglo, int indice, int valor) {
        if (indice < 0 || indice >= arreglo.length) {
            System.out.println("Índice fuera de rango.");
            return;
        }
        arreglo[indice] = valor;
    }

    public static int[] eliminarDeArreglo(int[] arreglo, int indice) {
        if (indice < 0 || indice >= arreglo.length) {
            System.out.println("Índice fuera de rango.");
            return arreglo;
        }

        int[] nuevoArreglo = new int[arreglo.length - 1];
        System.arraycopy(arreglo, 0, nuevoArreglo, 0, indice);
        System.arraycopy(arreglo, indice + 1, nuevoArreglo, indice, arreglo.length - indice - 1);
        return nuevoArreglo;
    }

    public static void recorrerArreglo(int[] arreglo) {
        for (int elemento : arreglo) {
            System.out.println(elemento);
        }
    }

    public static int[] copiarArreglo(int[] arreglo) {
        return arreglo.clone();
    }
}
```

