---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Estructuras de Datos Dinámicas y Tipos Abstractos de Datos (TAD)
semana: 6
tipo_documento: Diapositivas de clase
paginas: 27
fuente_pdf: S06_s1 TAD_Estructuras_Dinamicas K.pdf
---

# Inventario del documento

- **Archivo:** S06_s1 TAD_Estructuras_Dinamicas K.pdf
- **Páginas:** 27 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos · **Semana:** 6 (S06_s1)
- **Tema:** Estructuras de Datos Dinámicas y Tipos Abstractos de Datos (TAD)
- **Tipo:** Diapositivas de clase (PowerPoint UTP)
- **Estado OCR:** Limpio, pero **varias diapositivas con código Java desbordan el cuadro de texto** (el contenido se corta en el borde de la diapositiva).
- **Contiene:** Figuras/diagramas ✔ · Tablas ✘ · Fórmulas ✘ · Código ✔ (Java, parcialmente cortado) · Ejercicios/preguntas ✔

---

# Algoritmos y Estructura de Datos
## Estructura de Datos Dinamicas

*(Portada con logo UTP.)*

---

## Introducción

[FIGURA: Cita motivacional sobre fondo turquesa: "Si quieres llegar *rápido*, camina *solo*. Si quieres llegar *lejos*, camina en *grupo*". — Proverbio Africano.]

---

## Recordando

- Las operaciones con arreglos lineales son …………… …
- Las operación con Arreglos Bidimensionales son ………

---

## Agenda

- ESTRUCTURAS DE DATOS DINÁMICAS:
- Tipo Abstracto de Datos (TAD). Conceptos, Especificación informal de un TAD, Especificación formal de un TAD. Implementación del TAD
- Ejercicios
- Resumen

---

## CONOCIMIENTOS ADQUIRIDOS EN LA PLATAFORMA SOBRE EL TEMA.

*(Diapositiva de sección.)*

---

## APORTES DEL TEMA A SU FORMACION PROFESIONAL Y/O PERSONAL

*(Diapositiva de sección.)*

---

## Logro de Aprendizaje

> AL FINALIZAR LA SESION LOS ESTUDIANTES UTILIZAN LOS CONCEPTOS E IDENTIFICAN LOS TIPOS DE ESTRUCTURA DINAMICAS Y SU IMPORTANCIA EN LA PROGRAMACIÓN PARA APLICARLOS EN LA SOLUCION DE EJERCICIOS PRACTICOS.

---

## Estructuras de Datos Dinámicas y Tipos Abstractos de Datos (TAD)

*Fundamentos para organizar y gestionar información eficientemente*

[FIGURA: Imagen decorativa de muchos alfileres de colores con cabezas esféricas.]

---

## Introducción a las Estructuras de Datos Dinámicas

*(Diapositiva de sección — solo título sobre fondo amarillo.)*

---

## Concepto y relevancia de las estructuras dinámicas

- **Flexibilidad y Adaptación:** Las estructuras dinámicas crecen o reducen durante la ejecución, adaptándose a cambios en tiempo real sin pérdida de eficiencia.
- **Eficiencia en memoria:** Permiten una administración eficiente de memoria comparadas con arreglos estáticos, mejorando rendimiento y escalabilidad.
- **Bases para estructuras complejas:** Sirven de base para árboles, grafos y colas prioritarias, esenciales en algoritmos de búsqueda y optimización.
- **Modularidad y mantenimiento:** Facilitan soluciones limpias y modulares, reduciendo acoplamiento y separando comportamiento de implementación física.

[FIGURA: Imagen de documentos digitales conectados por líneas (estilo circuito azul brillante).]

---

## Tipo Abstracto de Datos (TAD): Fundamentos

*(Diapositiva de sección.)*

---

## Definición y características principales

- **Modelo Conceptual de TAD:** El TAD define valores y operaciones sin detallar la implementación interna, enfocándose en el comportamiento deseado.
- **Ocultación de Información:** Separa el qué del cómo, protegiendo la estructura interna y facilitando la reutilización y el mantenimiento del código.
- **Operaciones de un TAD:** Incluye operaciones constructoras, modificadoras y observadoras, como push, pop y peek en un TAD Pila.
- **Marco Matemático y Contrato Lógico:** Los TAD permiten razonar sobre algoritmos y asegurar invariantes que garantizan consistencia y funcionamiento correcto.

[FIGURA: Cubo formado por bloques de colores con dígitos 0/1 (representación de datos binarios) y dos flechas (verde y roja) apuntando en sentidos opuestos.]

---

## Especificación de un TAD

*(Diapositiva de sección.)*

---

## Especificación informal de un TAD

- **Comunicación accesible:** La especificación informal usa lenguaje natural y ejemplos para facilitar la comprensión de las operaciones del TAD sin formalismos.
- **Operaciones del TAD Cola:** Describe operaciones típicas como enqueue, dequeue y front, y cómo se comportan bajo condiciones normales.
- **Importancia y uso:** Ideal para documentar, enseñar y colaborar, estableciendo expectativas claras antes de especificaciones formales.

[FIGURA: Mano dibujando con marcador un diagrama de organigrama/árbol de cajas.]

---

## Especificación formal de un TAD

*(Diapositiva de sección.)*

---

## Uso de axiomas y estados para definir comportamiento

- **Especificación formal precisa:** El uso de axiomas y lenguaje matemático elimina ambigüedades al definir comportamiento exacto de TADs.
- **Definición de condiciones y dominios:** Se establecen precondiciones, postcondiciones e invariantes que aseguran validez y correcto funcionamiento.
- **Aplicaciones en software crítico:** La formalización garantiza confiabilidad en sistemas financieros, médicos y aeronáuticos, evitando errores graves.
- **Pruebas y verificación automática:** Permite generar modelos verificables que aseguran la implementación respeta el contrato definido por el TAD.

[FIGURA: Imagen de fórmulas matemáticas y símbolos sobre fondo oscuro azulado.]

---

## Implementación de un TAD con ejemplos en Java

*(Diapositiva de sección.)*

---

## Ejemplo práctico: implementación de un TAD Pila en Java

- **Definición de la Interfaz Pila:** La interfaz Pila define métodos esenciales para manejar una pila sin especificar su estructura interna.
- **Implementación con ArrayList:** La implementación con ArrayList permite un acceso eficiente al final de la lista para operaciones de pila.
- **Implementación con Nodos Enlazados:** La estructura de nodos enlazados muestra la naturaleza dinámica y flexible de la pila en memoria.
- **Concepto de TAD y Abstracción:** Distintas implementaciones pueden cumplir el TAD si respetan la especificación abstracta del comportamiento.

---

## Solución (TAD Pila — contrato y código)

> **Problema:** Verificar si una expresión con paréntesis/llaves/corchetes está **balanceada** (p.ej. `(()[{}])` es válida; `([)]` no).
> **TAD elegido:** Pila (Stack).
> **Implementación:** dinámica con nodos enlazados (sin límite fijo).
> **Uso:** Función `balanceado(String expr)` que emplea la pila.

**1) Contrato (especificación informal → formal breve) — TAD `Stack<T>`**

- **Observadores:** `isEmpty(): boolean`, `size(): int`, `peek(): T`
- **Modificadores:** `push(T x): void`, `pop(): T`
- **Axiomas (informales):**
  - `isEmpty()` después de crear → `true`.
  - `push(x)` seguido de `peek()` → devuelve `x`.
  - `push(x)` seguido de `pop()` → devuelve `x` y deja la pila como antes del `push`.
  - `size()` aumenta en +1 con `push` y disminuye en −1 con `pop` (si no está vacía).
- **Precondiciones:** `peek()` y `pop()` **requieren** `!isEmpty()` (si no, lanzar excepción).

**2) Código completo en un solo archivo (listo para compilar)**

Guarda como `DemoTAD.java` y compila con `javac DemoTAD.java`; ejecuta con `java DemoTAD`.

```java
import java.util.NoSuchElementException;
import java.util.Locale;

/**
 * DemoTAD
 * -----------------------------------------
 * Ejemplo práctico de TAD Pila (Stack) con implementación dinámica (nodos enlazados)
 [CÓDIGO CORTADO — la diapositiva recorta el resto del archivo DemoTAD.java; el código fuente completo no es visible en el PDF]
 */
```

[NOTA: En esta y las siguientes diapositivas el código Java desborda el cuadro de texto de la diapositiva y aparece **cortado** en el borde. Solo se transcribe lo visible.]

---

## Ejemplo didáctico (metáfora de la pila de platos)

**Objetivo didáctico**
- Entender qué es un TAD: *un contrato (qué hace) sin decir cómo lo hace*.
- Ver operaciones básicas: `push`, `pop`, `peek`, `isEmpty`, `size`.
- Separar la interfaz (contrato) de la implementación.
- Practicar arrays, condicionales y bucles.

**Historia (metáfora)** — Imaginemos una **pila de platos** en la cocina:
- Cuando llega un plato limpio, lo **ponemos arriba** (`push`).
- Cuando necesitamos un plato, **sacamos el de arriba** (`pop`).
- Si queremos **mirar** cuál está arriba sin sacarlo: `peek`.
- Podemos preguntar si **está vacía** o cuántos platos hay: `isEmpty`, `size`.

**Implementación sencilla (para principiantes)** — Para que sea **fácil de entender**, implementamos la pila con un **array** interno y un **índice** que apunta al tope. Así evitamos punteros y listas enlazadas por ahora.

**2) Clase `ArrayStack<T>` (implementación con array)**

```java
import java.util.NoSuchElementException;

// Implementación simple con array (capacidad fija) — fácil para empezar
public class ArrayStack<T> implements Stack<T> {
    private Object[] data; // almacenamos como Object y casteamos al regresar
    private int top;        // apunta al próximo lugar libre (también = tamaño actual)

    public ArrayStack(int capacidad) {
        [CÓDIGO CORTADO — el resto de la clase ArrayStack desborda la diapositiva]
```

---

## EJERCICIO (programa con menú interactivo)

**Programa con menú (usando String = "platos")** — Ahora un `main` con menú por consola para que el alumno pruebe `push/pop/peek` interactivo.

**3) `MainPlatos.java` (menú de consola)**

```java
import java.util.Locale;
import java.util.Scanner;

public class MainPlatos {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // 1) Creamos la pila de "platos" (Strings) con capacidad 5
        Stack<String> pilaDePlatos = new ArrayStack<>(5);

        // 2) Menú simple
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
            opcion = leerEntero(sc);

            try {
                switch (opcion) {
                    case 1:
                        System.out.print("Nombre del plato (p.ej., 'plato azul'): ");
                        String plato = sc.nextLine().trim();
                        if (plato.isEmpty()) {
                            System.out.println("Debe ingresar un nombre.");
                        } else {
                            pilaDePlatos.push(plato);
                            System.out.println("✔ Se agregó: " + plato);
                        }
                        break;
                    [CÓDIGO CORTADO — el resto del switch/menú desborda la diapositiva]
```

---

## EJERCICIOS (cómo compilar y qué se aprende)

**Cómo compilar y ejecutar**
```bash
# 1) Guardar 3 archivos en la misma carpeta:
#    Stack.java, ArrayStack.java, MainPlatos.java
javac Stack.java ArrayStack.java MainPlatos.java
java MainPlatos
```

**¿Qué aprende el estudiante con este ejemplo?**
1. TAD como contrato (`Stack<T>`) → define **qué** operaciones existen.
2. Implementación intercambiable (`ArrayStack<T>`) → **cómo** se hacen internamente.
3. Uso aplicado (menú) → volver concreta la abstracción con una metáfora: *platos*.
4. Manejo de errores: casos de pila vacía y llena.
5. Tipos genéricos y arrays.

Luego puedes **evolucionar** este ejemplo: cambiar `ArrayStack<T>` por `LinkedStack<T>` (nodos) **sin tocar** el menú; solo cambias la **línea de construcción** y el resto sigue funcionando. Eso demuestra el **poder del TAD**.

**Variante (si quieres reforzar "memoria limitada")**
- Deja capacidad chica (por ejemplo, 3) para que vivan el **overflow** y entiendan que con *array* la capacidad es finita.
- Después presentas la **versión dinámica** con **nodos**, donde no hay límite fijo (más avanzada, pero el contrato es el mismo).

---

## EGERCICIOS

*(Diapositiva con imagen de un niño jugando con un dinosaurio de madera — sin texto adicional.)*

---

## Preguntas

*(Diapositiva de sección.)*

---

## Ejercicio

- De ejemplos de estructura de datos estática:
- De ejemplos de estructura de datos dinamica lineal:
- De ejemplos de estructura de datos dinamica no lineal:

---

## Resumen

1. Defina qué es una estructura dinámica?
2. Clasifique ejemplos en estáticas, dinámicas lineales y no lineales.

---

*(Diapositiva final: solo logo UTP.)*

---

# Resumen estructural

| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | 6 | Cita motivacional, alfileres, cubo de datos binarios, mano dibujando organigrama, fórmulas matemáticas, foto de programadora + niño con dinosaurio. |
| Tablas     | 0 | — |
| Fórmulas   | 0 | (Se menciona el uso de axiomas/lenguaje matemático, sin fórmulas explícitas legibles.) |
| Código     | 3 fragmentos Java | DemoTAD.java (contrato Stack + balanceo de paréntesis), ArrayStack<T>, MainPlatos.java (menú). **Todos cortados** por desborde de la diapositiva. |
| Diagramas  | 0 | — |
| Ejercicios/Preguntas | 5 | menú interactivo + 3 ejemplos + 2 preguntas de resumen. |

**Observaciones importantes:**
- ⚠️ **Código Java incompleto:** las diapositivas de "Solución", "Ejemplo didáctico", "EJERCICIO" y "EJERCICIOS" contienen código fuente que **desborda el área de la diapositiva y queda recortado**. El código fuente completo (DemoTAD.java, Stack.java, ArrayStack.java, MainPlatos.java) NO es recuperable desde este PDF. Para NotebookLM conviene tener el `.java` real si existe.
- Conceptualmente, este deck cubre: estructuras dinámicas (flexibilidad, eficiencia de memoria), TAD (definición, ocultación de información, observadores/modificadores/constructores), especificación informal vs. formal (axiomas, pre/postcondiciones, invariantes) e implementación de un TAD Pila (con array vs. nodos enlazados) — **material núcleo del examen de semana 6**.
- Se conservó la redacción original con erratas ("Dinamicas", "EGERCI", "dinamica"). Logos UTP y números de página omitidos por ruido repetitivo.
