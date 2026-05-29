---
curso: Algoritmos y Estructuras de Datos
tipo: Plan de estudios para examen
cobertura: Semanas 6 a 9 (TAD, Punteros/Nodos, Listas Enlazadas, Búsqueda/Ordenamiento, Listas Circulares)
fuente: Generado por NotebookLM (cuaderno AED_Examen_S6-S9) a partir de las diapositivas y código .java de clase
fecha: 2026-05-29
---

# Plan de estudios ultra-específico — Examen AED (Semanas 6–9)

> Generado con NotebookLM sobre las fuentes transcritas (semanas 6-9) + código Java de clase.
> Las referencias [n] corresponden a las fuentes del cuaderno. Material 100% basado en las diapositivas de clase.

---

## 📘 BLOQUE 1: Semana 6 — TAD, Punteros y Nodos

### Tema 1.1: Tipos Abstractos de Datos (TAD) y Estructuras Dinámicas
- **(a) Objetivo:** Reconocer los TAD para desarrollar soluciones algorítmicas y entender la flexibilidad de las estructuras dinámicas frente a los arreglos estáticos.
- **(b) Conceptos a memorizar:**
  - **Ecuación TAD:** "TAD = Representación (datos) + Operaciones (funciones y procedimientos)".
  - **Especificación vs. Implementación:** la abstracción separa "el qué del cómo". La especificación es la interfaz pública; la implementación son las estructuras ocultas privadas.
  - **Estructura Dinámica:** "crecen o reducen durante la ejecución, adaptándose a cambios en tiempo real".
- **(c) Diagramas a dibujar:** "Implementación de un TAD" → el módulo cliente sólo ve la "Vista pública" (interfaz) y se le oculta la "Vista privada" (algoritmos y estructuras).
- **(d) Errores comunes:** usar operaciones modificadoras (`pop`) sin validar precondiciones (lanzar excepción si la pila está vacía → `!isEmpty()`).

### Tema 1.2: Punteros y Nodos
- **(a) Objetivo:** Comprender cómo se relacionan los valores en la memoria dinámica mediante referencias.
- **(b) Conceptos a memorizar:**
  - **Puntero:** "es una variable cuyo valor es una posición de memoria".
  - **Nodo:** "Es una posición de memoria que almacena valores. Tiene 2 partes: una almacena información; la otra almacena un puntero".
  - **inicio:** no es un nodo; es sólo un puntero que guarda la dirección del primer nodo.
- **(c) Diagramas a dibujar:**
  - Nodo dividido en "Información" (izquierda) y "Puntero" (derecha).
  - **Operación `q = p`:** dos punteros a nodos distintos; tras `q = p`, ambos apuntan al nodo 1 y el nodo 2 queda huérfano.
- **(d) Errores comunes:** creer que el puntero guarda el dato (guarda la dirección). Hacer `q = p` sin cuidado: el nodo apuntado por `q` "se queda sin apuntador y no puede ser accesado" (fuga de memoria).

---

## 📗 BLOQUE 2: Semana 7 — Listas Enlazadas y Operaciones

### Tema 2.1: Operaciones en Listas Enlazadas Simples (LES)
- **(a) Objetivo:** Utilizar adecuadamente las operaciones con Listas Enlazadas en Java.
- **(b) Conceptos a memorizar:**
  - **Definición:** "Una lista enlazada es una colección de nodos. Es una alternativa a los arreglos".
  - **Regla de oro:** nunca sueltes un puntero sin antes haberlo guardado en otro lado; si lo sueltas, pierdes todo lo que había después.
- **(c) Operaciones a dibujar paso a paso:**
  - **Insertar al inicio:** 1º `nuevo.sgte = inicio`, 2º `inicio = nuevo`.
  - **Insertar en medio:** 1º `nuevo.sgte = aux.sgte`, 2º `aux.sgte = nuevo`.
  - **Insertar al final:** `Dato N` pasa de apuntar a null a apuntar a `nuevo`; `nuevo` apunta a null.
  - **Eliminar al inicio:** `inicio = inicio.sgte` (salta a Dato 2).
  - **Eliminar al centro:** `aux.sgte = aux.sgte.sgte` (salta sobre el nodo a eliminar).
  - **Eliminar al final:** el penúltimo nodo pasa a apuntar a `nil`.
- **(d) Errores comunes:** alterar el orden al insertar. Al inicio, si haces `inicio = nuevo` primero, el nuevo nodo apunta a sí mismo (bucle infinito). En medio, si haces `aux.sgte = nuevo` primero, pierdes la dirección del resto de la lista.

---

## 📙 BLOQUE 3: Semana 8 — Búsqueda y Ordenamiento

### Tema 3.1: Búsqueda y Listas Ordenadas
- **(a) Objetivo:** Utilizar adecuadamente las operaciones de búsqueda con Listas Enlazadas en Java.
- **(b) Conceptos a memorizar:**
  - **Búsqueda:** "consiste en recorrer la lista hasta encontrar el nodo con el valor buscado". Puede devolver la referencia al nodo, `null` (si no existe), o `true/false`.
  - **Listas Ordenadas:** para insertar, la clave es "buscar la ubicación donde insertar el nodo" manteniendo el orden lineal.
- **(c) Diagramas a dibujar:** recorrido con un puntero `p` saltando nodo a nodo (sin usar `inicio`, para no perder la cabeza) hasta llegar al dato o a `null`. Inserción ordenada reubicando punteros entre el anterior y el siguiente.
- **(d) Errores comunes:** usar el puntero `inicio` para recorrer en lugar de un auxiliar `p` (perderías la lista entera).

---

## 📕 BLOQUE 4: Semana 9 — Listas Enlazadas Circulares

### Tema 4.1: Listas Circulares (Simples y Dobles)
- **(a) Objetivo:** Utilizar operaciones con Listas Enlazadas Circulares en Java.
- **(b) Conceptos a memorizar:**
  - **Definición:** "Es un conjunto de nodos enlazados, cuyo último nodo está enlazado al primer nodo de la lista".
  - **Característica principal:** "No existe algún elemento que apunte a vacío".
- **(c) Diagramas a dibujar:**
  - **Anillo circular simple:** nodos en una dirección; la flecha del último retorna a `inicio`.
  - **Anillo circular doble:** cada nodo con enlaces adelante y atrás (`ant` y `sig`/`sgte`) formando un anillo.
- **(d) Errores comunes:** generar "recorridos en bucles infinitos" porque la condición de parada clásica (`p != null`) nunca se cumple en una lista circular.

---

## ⏱️ Tabla de tiempo sugerido (priorizado)

| Tema | Prioridad examen | Tiempo sugerido | Enfoque principal |
|------|:---:|------|------|
| **S7: Operaciones con LES** | 🔥 ALTA | 3.0 h (2 sesiones) | Dibujar paso a paso inserciones/eliminaciones; memorizar el orden de las líneas. |
| **S6: Punteros y Nodos** | 🔥 ALTA | 2.0 h (1 sesión) | Memoria, referencias y la regla de oro (no soltar punteros). |
| **S9: Listas Circulares** | ⭐ MEDIA | 1.5 h (1 sesión) | Dibujar diferencia estructural (anillos, sin apuntador a null). |
| **S6: Teoría TAD** | ⭐ MEDIA | 1.0 h (1 sesión) | Ecuación TAD, abstracción, informal vs. formal. |
| **S8: Búsqueda y Orden** | ⚡ BAJA | 1.0 h (1 sesión) | Bucle de recorrido con puntero temporal `p`. |
| **TOTAL** | | **8.5 h** | |

---

## 📝 Preguntas tipo examen

### Teoría (definiciones, TAD, punteros)
1. Define matemáticamente qué es un TAD basándote en la ecuación vista en clase.
2. ¿Qué guarda realmente el puntero `sgte`? ¿Un dato, un nombre o una dirección?
3. ¿El puntero `inicio` es un nodo? ¿Qué significa si `inicio == null`?
4. Explica la diferencia entre Especificación e Implementación de un TAD. ¿Cuál es la "Vista pública" para el módulo cliente?
5. Nombra dos características que diferencian una Lista Circular de una Lista Enlazada Simple.

### Práctica (diagramas y lógica de nodos/listas)
6. **[Dibujo]** Lista `inicio -> Dato 1 -> Dato 2 -> Dato 3 -> null`. Muestra el antes/después de borrar el nodo central (`Dato 2`). ¿A qué nodo se conecta `Dato 1`?
7. **[Diagrama]** `p` apunta a A, `q` apunta a B; ejecutas `q = p`. Dibuja qué ocurre en memoria y qué pasa con el nodo B.
8. **[Código Java]** Con base en el código de clase, escribe cómo se declara `cNodo` para una lista **doblemente enlazada** vs **simplemente enlazada** (¿qué atributo privado extra lleva?).
9. **[Lógica]** Para insertar al medio el orden estricto es `1) nuevo.sgte = aux.sgte` y `2) aux.sgte = nuevo`. ¿Qué sucede si inviertes el orden? Explícalo.

---

> **Consejo del tutor:** enfócate en dibujar las operaciones en papel — el ~80% de los errores en estos exámenes ocurren por cruzar mal las flechas de los punteros.
