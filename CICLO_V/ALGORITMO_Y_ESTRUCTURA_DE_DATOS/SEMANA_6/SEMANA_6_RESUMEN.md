# Semana 6 — Estructuras de Datos Dinámicas y TAD

**Curso:** Algoritmos y Estructura de Datos — UTP  
**Tema:** Estructura de Datos Dinámicas + Tipo Abstracto de Datos (TAD)  
**Archivos fuente:** `S06_s1 TAD_Estructuras_Dinamicas K.pdf`, `S06_s2 TAD_Estructuras_Dinamicas K.pdf`, `S06_s1 - TAD.pptx`, `S06_s1 - Punteros - Listas enlazadas.pptx`

---

## Logro de Aprendizaje

Al finalizar la sesión, los estudiantes utilizan los conceptos e identifican los tipos de estructura dinámicas y su importancia en la programación para aplicarlos en la solución de ejercicios prácticos.

---

## 1. Introducción a las Estructuras de Datos Dinámicas

A diferencia de los arreglos (tamaño fijo en tiempo de compilación), las estructuras dinámicas crecen o encogen en tiempo de ejecución según la demanda.

### Por qué importan

| Característica | Detalle |
|---|---|
| **Flexibilidad y adaptación** | Crecen o reducen durante la ejecución sin pérdida de eficiencia |
| **Eficiencia en memoria** | Solo usan la memoria que necesitan, a diferencia de los arreglos estáticos |
| **Base para estructuras complejas** | Árboles, grafos y colas prioritarias se construyen sobre ellas |
| **Modularidad y mantenimiento** | Separan el comportamiento de la implementación física |

### Clasificación

```
Estructuras de Datos
├── Estáticas          → arreglos, matrices (tamaño fijo)
└── Dinámicas
    ├── Lineales       → listas enlazadas, pilas, colas
    └── No lineales    → árboles, grafos
```

---

## 2. Tipo Abstracto de Datos (TAD)

### Definición

Un **TAD** define **qué valores** maneja y **qué operaciones** soporta, sin revelar cómo se implementa internamente. Es un contrato entre el usuario de la estructura y quien la implementa.

### Características principales

- **Modelo conceptual:** define el comportamiento deseado, no la implementación.
- **Ocultación de información:** separa el *qué* del *cómo*; protege la estructura interna y facilita la reutilización.
- **Tipos de operaciones:**
  - **Constructoras:** crean nuevas instancias del TAD.
  - **Modificadoras:** cambian el estado (`push`, `pop`).
  - **Observadoras:** consultan sin modificar (`peek`, `isEmpty`, `size`).
- **Marco matemático:** permite razonar formalmente y garantizar invariantes de corrección.

---

## 3. Especificación de un TAD

### 3.1 Especificación Informal

Usa **lenguaje natural** y ejemplos para describir el comportamiento. Sin formalismos matemáticos.

**Ventajas:** fácil de leer, ideal para documentar, enseñar y colaborar en equipo.

**Ejemplo — TAD Cola:**
- `enqueue(x)`: agrega x al final de la cola.
- `dequeue()`: elimina y devuelve el elemento del frente.
- `front()`: devuelve el elemento del frente sin eliminarlo.

### 3.2 Especificación Formal

Usa **axiomas y lenguaje matemático** para eliminar ambigüedades. Define precondiciones, postcondiciones e invariantes.

**Ventajas:** precisa, verificable automáticamente, imprescindible en software crítico (financiero, médico, aeronáutico).

**Ejemplo — TAD Stack\<T\> (formal breve):**

```
Observadores : isEmpty(): boolean   size(): int   peek(): T
Modificadores : push(T x): void     pop(): T

Axiomas informales:
  • isEmpty() después de crear              → true
  • push(x) seguido de peek()              → devuelve x
  • push(x) seguido de pop()               → devuelve x; pila queda como antes del push
  • size() aumenta +1 con push             → disminuye -1 con pop (si !isEmpty)

Precondiciones:
  • peek() y pop() requieren !isEmpty()    → si no, lanzar excepción
```

---

## 4. Implementación de un TAD en Java

### 4.1 Estructura de archivos

Para implementar el TAD Pila se usan 3 archivos:

```
Stack.java          ← interfaz (el contrato / TAD)
ArrayStack.java     ← implementación con array
MainPlatos.java     ← programa de prueba con menú
```

Compilar y ejecutar:
```bash
javac Stack.java ArrayStack.java MainPlatos.java
java MainPlatos
```

### 4.2 Interfaz `Stack<T>` (el contrato)

```java
public interface Stack<T> {
    void push(T x);
    T pop();
    T peek();
    boolean isEmpty();
    int size();
}
```

### 4.3 Implementación con Array — `ArrayStack<T>`

Capacidad fija, sin punteros. Fácil para empezar.

```java
// Implementación simple con array (capacidad fija)
public class ArrayStack<T> implements Stack<T> {
    private Object[] data;   // almacenamos como Object y casteamos al regresar
    private int top;         // apunta al próximo lugar libre (= tamaño actual)

    public ArrayStack(int capacidad) {
        data = new Object[capacidad];
        top = 0;
    }
    // ... push, pop, peek, isEmpty, size
}
```

**Metáfora — Pila de platos en la cocina:**
- Llega un plato limpio → lo ponemos arriba → `push`
- Necesitamos un plato → sacamos el de arriba → `pop`
- Queremos mirar cuál está arriba sin sacarlo → `peek`
- Preguntar si hay platos / cuántos hay → `isEmpty`, `size`

### 4.4 Implementación con Nodos Enlazados — `LinkedStack<T>`

Sin límite de capacidad. Muestra la naturaleza dinámica real.

```java
// Nodo interno
class Node<T> {
    T data;
    Node<T> next;
    Node(T data) { this.data = data; }
}

public class LinkedStack<T> implements Stack<T> {
    private Node<T> head;
    private int size;
    // ...
}
```

**Ventaja clave del TAD:** puedes cambiar `ArrayStack<T>` por `LinkedStack<T>` en la línea de construcción y el menú sigue funcionando exactamente igual. Eso demuestra el poder de la abstracción.

### 4.5 Programa con menú — `MainPlatos.java`

```java
import java.util.Locale;
import java.util.Scanner;

public class MainPlatos {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // Crear pila de "platos" (Strings) con capacidad 5
        Stack<String> pilaDePlatos = new ArrayStack<>(5);

        int opcion;
        do {
            System.out.println("\n=== Pila de Platos ===");
            System.out.println("1) Agregar un plato (push)");
            System.out.println("2) Sacar un plato (pop)");
            System.out.println("3) Ver plato de arriba (peek)");
            System.out.println("4) ¿Está vacía?");
            System.out.println("5) ¿Cuántos platos hay? (size)");
            System.out.println("0) Salir");
            System.out.print("Elige: ");
            opcion = sc.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del plato: ");
                        String plato = sc.nextLine().trim();
                        if (plato.isEmpty()) {
                            System.out.println("Debe ingresar un nombre.");
                        } else {
                            pilaDePlatos.push(plato);
                            System.out.println("Se agregó: " + plato);
                        }
                        break;
                    // ... otros casos
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}
```

---

## 5. Ejercicio de Aplicación — Expresión Balanceada

**Problema:** verificar si una expresión con paréntesis/llaves/corchetes está balanceada.
- `(()[{}])` → válida
- `([)]` → no válida

**Solución:**
- TAD elegido: **Pila (Stack)**
- Implementación: **dinámica con nodos enlazados** (sin límite fijo)
- Función: `balanceado(String expr)` que emplea la pila

**Algoritmo:**
1. Recorrer cada carácter de la expresión.
2. Si es apertura (`(`, `[`, `{`), hacer `push`.
3. Si es cierre, verificar que el tope coincide con el par; hacer `pop`.
4. Al final, si la pila está vacía → expresión balanceada.

---

## 6. Ejercicios de Clase

Dar ejemplos de cada categoría:

| Tipo | Ejemplos |
|---|---|
| Estructura estática | Arreglo (`int[]`), matriz (`int[][]`) |
| Dinámica lineal | Lista enlazada, Pila, Cola |
| Dinámica no lineal | Árbol binario, Grafo |

---

## 7. Resumen y Preguntas de Repaso

1. **¿Qué es una estructura dinámica?**  
   Aquella cuyo tamaño cambia en tiempo de ejecución, adaptándose a la cantidad de datos sin desperdiciar memoria ni desbordarse.

2. **Clasifica las siguientes estructuras en estáticas, dinámicas lineales y no lineales:**  
   `int[]`, `ArrayList`, `LinkedList`, `Stack`, `Queue`, `BinaryTree`, `HashMap`, `Graph`

3. **¿Qué diferencia hay entre especificación informal y formal de un TAD?**  
   La informal usa lenguaje natural (fácil de entender, puede ser ambigua); la formal usa axiomas matemáticos (precisa, verificable automáticamente).

4. **¿Por qué el TAD es poderoso?**  
   Porque permite cambiar la implementación (`ArrayStack` → `LinkedStack`) sin modificar el código que usa la estructura, si respeta el contrato (interfaz).

5. **¿Cuándo `pop()` lanza excepción?**  
   Cuando la pila está vacía (`isEmpty() == true`). La precondición del TAD exige `!isEmpty()`.

---

## 8. Punteros y Listas Enlazadas (S06_s1 - Punteros)

> Tema complementario cubierto en el PPTX `S06_s1 - Punteros - Listas enlazadas.pptx`

### Concepto de puntero/referencia en Java

En Java no hay punteros explícitos, pero cada objeto es una **referencia**. Un nodo enlazado aprovecha esto:

```java
class Nodo<T> {
    T dato;
    Nodo<T> siguiente;  // referencia al siguiente nodo (null si es el último)
}
```

### Lista Simplemente Enlazada (LSU)

```
[dato|→] → [dato|→] → [dato|null]
  head                    tail
```

- **Insertar al inicio:** O(1)
- **Insertar al final:** O(n) sin referencia a tail, O(1) con ella
- **Buscar:** O(n)
- **Eliminar:** O(n) para buscar + O(1) para desenlazar

### Comparación Array vs Lista Enlazada

| Operación | Array | Lista Enlazada |
|---|---|---|
| Acceso por índice | O(1) | O(n) |
| Insertar al inicio | O(n) | O(1) |
| Insertar al final | O(1) amortizado | O(1) con tail |
| Memoria | Fija, continua | Dinámica, dispersa |
| Sobrecarga | Ninguna | 1 referencia extra por nodo |
