---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Arreglos unidimensionales
semana: 1
tipo_documento: Diapositivas de clase
paginas: 29
fuente_pdf: S04_s1 - Material - Arreglos unidimensionales.pdf
---

# Inventario del documento

- **Archivo:** S04_s1 - Material - Arreglos unidimensionales.pdf
- **Páginas:** 29 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos · **Semana:** 1 (S04_s1)
- **Tema:** Estructuras de datos — ARREGLOS (unidimensionales)
- **Tipo:** Diapositivas de clase (PowerPoint UTP)
- **Estado OCR:** Limpio (PDF digital)
- **Contiene:** Figuras/diagramas ✔ · Tablas ✔ (dígito verificador) · Fórmulas ✔ · Código ✔ (Java) · Ejercicios/preguntas ✔

---

# Algoritmos y Estructuras de Datos
## Tema: Estructuras de datos — ARREGLOS

*(Portada con logo UTP.)*

---

## LOGRO

> Al finalizar la sesión, el estudiante utilizará adecuadamente los arreglos para desarrollar soluciones algoritmicas en java.

---

## Dudas sobre la clase anterior

- Diferencias entre el ordenamiento interno y el externo
- Diferencias entre la búsqueda lineal y la búsqueda binaria

---

## Conocimientos previos: Arreglos

- Comentario sobre algún lugar donde guardar artículos en la casa.
- Comentario sobre un lugar donde guardar libros
- Comentario sobre un lugar donde guardar zapatos

[FIGURA 1: Dos fotografías de estanterías/casilleros de madera con compartimentos cuadrados: una con libros y otra con zapatos/zapatillas, ilustrando la idea de almacenamiento ordenado por casillas (analogía de un arreglo).]

---

## VIDEO

[FIGURA 2: Ilustración estilo pictograma de una persona empujando un carrito de compras frente a una estantería de supermercado con productos; un globo muestra el precio "5.43°". Junto aparece la etiqueta "VIDEO" y una URL recortada ("https://www..."). El enlace del video no es legible completo.]

---

## Utilidad del tema: Arreglos

Porque es importante:

- Permite almacenar en memoria una cantidad de valores, utilizando un solo nombre.
- Porque permite accesar a un valor de acuerdo a una ubicación

---

## Contenido General

**Tema:** Estructuras de datos.

[FIGURA 3: Diagrama de barras horizontales apiladas con íconos:
- Estructura de datos
- Arreglos
- Arreglos unidimensionales
- Errores comunes en arreglos
- Ejercicios]

---

## ESTRUCTURAS DE DATOS

[FIGURA 4: Tres círculos con íconos (idéntica a la de la sesión anterior):
- (rojo) SON MODELO MATEMÁTICO O LÓGICO PARA ORGANIZAR LOS DATOS
- (dorado) AGRUPAR CIERTOS TIPOS DE DATOS EN CATEGORÍAS O EN ESTRUCTURAS.
- (verde) TENEMOS ESTRUCTURA DE DATOS ESTÁTICAS Y ESTRUCTURA DE DATOS DINÁMICAS.]

---

## Estructura de datos (clasificación)

[DIAGRAMA DE ÁRBOL: Clasificación de estructuras de datos.
- **Estáticos**: Fundamentales | Compuestos → (String, arreglos)
- **Dinámicos**: Lineales → (Listas enlazadas, Pilas, Colas) | No Lineales → (Arboles, Grafos)

Bajo el diagrama, dos barras horizontales indican agrupaciones transversales: una barra "Objetos" abarca desde Compuestos hacia la derecha (con "primitivos" marcado a la izquierda en verde) y una barra "colecciones" abarca la parte de estructuras dinámicas. Es decir, primitivos vs. Objetos, y las estructuras dinámicas como colecciones.]

---

## Arreglos — definición

- Es una colección de valores del mismo tipo. Se referencia por un nombre común y se accesa por medio de índices.
- El arreglo tiene dos partes: índices y componentes.
- Los componentes hacen referencia a los valores que se almacenan en el arreglo.
- Los índices hacen referencia a las ubicaciones de los componentes del arreglo en forma individual.
- Estos índices son las direcciones de cada elemento dentro del arreglo

[FIGURA 5: Diagrama de un arreglo lineal vacío. Una fila de 9 celdas etiquetadas con índices 0 a 8 (fila "Indice") y una fila de celdas vacías debajo (fila "Elementos").]

---

## ARREGLOS

- Un array o arreglo es una estructura de datos que contiene una colección de datos del mismo tipo y almacenados consecutivamente en memoria.
- Tienen un nombre en común.
- Los elementos se identifican por un índice.
- Puede manejar tipos primitivo y objetos

[FIGURA 6: Conjunto de pequeños esquemas numerados (1 a 12) que muestran distintas formas/tamaños de arreglos dibujados como rejillas de celdas (unidimensionales y rectangulares).]

---

## Características

- Es una colección finita, homogénea y ordenada de elementos.
- **Finita.-** Porque tiene un límite. Se debe determinar el máximo número de elementos del arreglo.
- **Homogénea.-** Porque todos los elementos de un arreglo son del mismo tipo. No puede haber una combinación de diferentes tipos de datos.
- **Ordenada.-** Porque se puede identificar el primer elemento, segundo,.. hasta el ultimo elemento

---

## CARACTERÍSTICAS DE UN ARREGLO

[FIGURA 7: Tres flechas/chevrones en secuencia: "Es finito" → "Homogéneo" → "Ordenado".]

**Tipos de Arreglos:** dos bloques → Unidimensionales | Multidimensional

---

## Tipos de arreglos (detalle)

- Los arreglos pueden ser de los siguientes tipos:
  - De una dimensión (vectores).
  - De dos dimensiones (matrices).
  - De tres o más dimensiones.
- Desde el punto de vista del programa, un arreglo es una zona de almacenamiento contiguo, que contiene una serie de elementos del mismo tipo.
- Desde el punto de vista lógico podemos considerarlas como un conjunto de elementos ordenados en fila.

---

## Cálculo de dirección de memoria de un elemento

Para calcular la dirección de memoria de un elemento dentro de un arreglo se usa la siguiente formula:

$$D_i = B + [(i - li) \times w]$$

donde:

- $D_i$ = Dirección de inicio del elemento i
- $B$ = Dirección de inicio del arreglo
- $i$ = Índice del elemento
- $li$ = Límite inferior del arreglo
- $w$ = Número de bytes de cada elemento

---

## Ejemplo — cálculo de dirección

- Calcular la dirección del elemento 4 del arreglo A (0:10).
- Cada elemento tiene 3 bytes y el índice cero es el primer elemento del arreglo.
- Además el arreglo empieza en la dirección 100

Usando la formula: $D_i = B + [(i - li) \times w]$

**Resultado:** Dirección = 100 + (4-0) × 3 = **112**

---

## Arreglos Unidimensionales

[FIGURA 8: Diagrama de un arreglo unidimensional llamado "A" con 7 celdas (índices [0] a [6]) que contienen los valores: 10, 50, 145, 2, 456, 20, -1. La celda [1] (valor 50) está resaltada en amarillo. Globos señalan: "Nombre" → la etiqueta A; "Índice" → los corchetes [0]…[6]; "Elemento" → el valor 50.]

Tamaño: cantidad de elementos del arreglo
*¿Cual es el tamaño de este arreglo?*

---

## OPERACIONES CON ARREGLOS

- **Declaración de un arreglo:**
  - `tipo[] nombre`
  - `tipo nombre[]`
- **Creación de arreglo unidimensionales:**
  - `nombre = new tipo[tamaño]`
- **Acceso a un elemento:**
  - `nombre[índice]`

---

## DECLARACIÓN Y CREACIÓN DE UN ARREGLO

```java
tipo nombreArray[] = new tipo[tamaño]
tipo[] nombreArray = new tipo[tamaño]

// Ejemplo
tipo nombreArray[] = new tipo[7]
tipo[] nombreArray = new tipo[7]
```

[FIGURA: arreglo A con celdas [0]..[6] = 10,50,145,2,456,20,-1; celda [1] resaltada.]

---

## ERRORES COMUNES

- Uso de un índice fuera de rango valido.
- Error de uno menos. (el primer elemento se almacena en la posición 0)
- Confundir el índice de un elemento del arreglo con el mismo elemento
- Usar `=` para COPIAR un arreglo
- Usar `==` para COMPARAR dos arreglos

---

## Ejemplo para Recorrer un arreglo

Desarrollar un programa en java para ingresar las horas trabajadas de 5 trabajadores y obtener el promedio de horas trabajadas

---

## Ejemplo de arreglos Paralelos

Desarrollar un programa en java para ingresar el nombre y sueldo de 10 trabajadores y registrarlo en arreglo los nombres y en otro el sueldo. Luego mostrar el nombre del trabajador que tenga el sueldo mayor

---

## Digito verificador

Un método para calcular el carácter verificador del DNI se realiza aplicando el método modulo23, el cual se desarrolla de la siguiente manera, obtener el resto de dividir el nro. DNI entre 23, según este resultado se obtiene la letra equivalente. Por lo que se desea desarrollar un programa en java que calcule el digito verificador utilizando arreglos

[TABLA — Equivalencia residuo módulo 23 → letra (arreglo de índices 0 a 22):]

| Índice | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19 | 20 | 21 | 22 |
|--------|---|---|---|---|---|---|---|---|---|---|----|----|----|----|----|----|----|----|----|----|----|----|----|
| Letra  | T | R | W | A | G | M | Y | F | P | D | X  | B  | N  | J  | Z  | S  | Q  | V  | H  | L  | C  | K  | E  |

---

## Ejemplo de arreglo con valores aleatorios (notas)

Llenar un arreglo que registra las notas de una clase de 40 alumnos, llenar el arreglo con valores aleatorios entre 0 y 20, listar los alumnos que están aprobados, es decir tienen nota mayor o igual a 12.

---

## VALORES ALEATORIOS QUE NO SE REPITAN

Llenar un arreglo de 10 elementos entre 1 y 15 con números aleatorios que no se repitan.

```java
public class ArrayAleatoriosSinRepetirse {
    public static void main(String[] args) {
        int numeros[] = new int[10];
        int t, i = 0;
        while (i < numeros.length) {
            t = (int) (1 + Math.random() * 15);
            if (verificarNoExiste(t, numeros, i))
            {
                numeros[i] = t;
                i++;
            }
        }
        for (int numero : numeros) {
            System.out.print(numero + " ");
        }
    }

    static boolean verificarNoExiste(int n, int[] arreglo, int j) {
        boolean salir = true;
        for (int i = 0; i < j; i++) {
            if (arreglo[i] == n) {
                salir = false;
                break;
            }
        }
        return salir;
    }
}
```

---

## Ejemplo de arreglo con valores aleatorios (taxista)

Un taxista almacena en un vector de 30 posiciones el número de carreras que ha realizado en cada uno de los días del mes. El dueño del taxi ha decidido pagarle por cada día trabajado, de la siguiente manera: Si en el día hizo menos de 10 carreras, le pagará a S/10 por carrera, Si en el día hizo entre 11 y 30 carreras, le pagará a S/.15 cada carrera, Si en el día hizo más de 30 carreras, cada carrera se la pagará a razón de S/20.

Dado esto, escriba un programa que realice las siguientes tareas:
- generando de forma aleatoria los ingresos diarios y guardarlos en el arreglo
- Calcular el total de dinero que hizo el taxista en el mes,
- Determinar el promedio de carreras hechas por el taxista.

---

## Cierre de la clase

**Arreglos**

- Permiten almacenar un conjunto de valores del mismo tipo de dato.
- A estos valores se accede por medio de índices.
- Se recorren los valores del arreglo por medio de procesos repetitivos

---

## RESUMIENDO

Responder lo siguiente:

1. Para que sirve el atributo length de un arreglo?
2. Cuando crea e inicializa una variable tipo arreglo indicando sus datos, debe encerrar esos datos entre corchetes o llaves?
3. Cual es la relación entre el numero de elementos de un arreglo y el máximo índice del mismo?

---

## GRACIAS

*(Diapositiva de cierre con logo UTP.)*

---

# Resumen estructural

| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | 8 | Fotos de estanterías, pictograma de video, barras de contenido, círculos, esquemas de arreglos, diagrama de arreglo A con índices. |
| Tablas     | 1 | Equivalencia módulo 23 → letra del DNI (índices 0-22). |
| Fórmulas   | 1 | Dirección de memoria $D_i = B + [(i - li) \times w]$. |
| Código     | 1 | Java: arreglo de aleatorios sin repetir (clase ArrayAleatoriosSinRepetirse). |
| Diagramas  | 1 | Árbol de clasificación de estructuras (con bandas primitivos/Objetos/colecciones). |
| Ejercicios/Preguntas | 8 | 6 ejemplos prácticos + 3 preguntas de resumen (cuenta combinada). |

**Observaciones:** OCR limpio. Se preservó la redacción original con sus erratas ("accesar", "algoritmicas", "numero de carrera"). El video tiene URL ilegible (recortada). Logos y números de página omitidos por ruido repetitivo.
