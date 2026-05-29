---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Punteros - Listas Enlazadas
semana: 7
tipo_documento: Diapositivas de clase
paginas: 16
fuente_pdf: S07_s1_Punteros_Listas_enlazadas.pdf
---

# Inventario del documento

- **Archivo:** S07_s1_Punteros_Listas_enlazadas.pdf
- **Páginas:** 16 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos
- **Semana:** 7 (Sesión S07_s1)
- **Tema:** Punteros y Listas Enlazadas
- **Tipo:** Diapositivas de clase (PowerPoint UTP)
- **Estado OCR:** Limpio (PDF digital, texto nítido)
- **Tipo de PDF:** Digital con figuras vectoriales (diagramas de nodos)
- **Contiene:** Figuras/diagramas ✔ · Tablas ✘ · Fórmulas ✘ · Código ✘ · Ejercicios/preguntas ✔

---

# Algoritmos y Estructuras de Datos
## Listas Enlazadas

*(Diapositiva de portada — título centrado.)*

---

## Logro de aprendizaje

> Al finalizar la sesión el estudiante utilizará adecuadamente las operaciones con Listas Enlazadas para desarrollar soluciones algorítmicas en java.

---

## Dudas sobre la clase anterior

Punteros:

- Un puntero puede apuntar a 2 nodos a la vez?
- Un puntero puede apuntar a 2 nodos en diferentes momentos?

---

## Conocimientos previos: Listas Enlazadas

- Indique un ejemplo de una lista de elementos que se relacionan uno con el otro elemento

---

## Utilidad del tema: Listas enlazadas

Porque es importante:

- Permiten generar listas de elementos en forma secuencial
- Permiten generar listas de elementos de manera dinámica

---

## Listas Enlazadas

- Una lista enlazada, es una colección de nodos. Es una alternativa a los arreglos.
- Los punteros permiten el enlace entre nodos.

[FIGURA 1: Diagrama de una lista enlazada. Un puntero etiquetado "inicio" apunta (flecha) al primer nodo. Hay cuatro nodos en fila, cada uno dibujado como un rectángulo amarillo dividido en dos partes: la parte izquierda contiene el dato y la parte derecha contiene un punto (puntero) que enlaza con una flecha al siguiente nodo. Secuencia: "Dato 1" → "Dato 2" → "Dato 3" → "Dato 4" → (flecha final que sale al vacío).

Debajo, una representación equivalente que muestra las direcciones de memoria: una celda inicial con valor "500" (la dirección a la que apunta inicio); luego las celdas:
- "Dato 1 | 300" ubicada en la dirección 500
- "Dato 2 | 700" ubicada en la dirección 300
- "Dato 3 | 200" ubicada en la dirección 700
- "Dato 4 | null" ubicada en la dirección 200
Cada celda guarda un dato y la dirección de memoria del siguiente nodo; el último (Dato 4) guarda "null" indicando fin de la lista. Las direcciones 500, 300, 700, 200 aparecen rotuladas bajo cada celda respectivamente.]

---

## Operaciones con listas enlazadas

- **Básicas:**
  - Insertar un elemento
  - Eliminar un elemento
- **Otras operaciones**
  - Recorrer la lista
  - Ordenar una lista
  - Combinar dos listas en una
  - Dividir una lista en varias sublistas

---

## Operaciones con listas enlazadas — Inserción al inicio

[FIGURA 2: Diagrama en dos estados (antes y después) de la inserción de un nodo al inicio.

Estado superior (antes): el puntero "inicio" (flecha roja) apunta al nodo "Dato 1". La lista existente es: Dato 1 → Dato 2 → …. → Dato N → nil. Aparte, un puntero "nuevo" apunta a un nodo "Dato X" que aún no está enlazado a la lista.

Estado inferior (después): el puntero "inicio" (flecha roja) ahora apunta al nodo "Dato X". El nodo "Dato X" (apuntado también por "nuevo") tiene una flecha que lo enlaza al antiguo primer nodo "Dato 1", quedando: nuevo/inicio → Dato X → Dato 1 → Dato 2 → …. → Dato N → null. Es decir, el nuevo nodo se inserta delante de la cabeza y "inicio" se reapunta a él.]

---

## Operaciones con listas enlazadas — Inserción al medio

[FIGURA 3: Diagrama en dos estados de la inserción de un nodo en una posición intermedia.

Estado superior (antes): "inicio" apunta a "Dato 1". El enlace de "Dato 1" hacia "Dato 2" está resaltado en rojo. Lista: Dato 1 → Dato 2 → …. → Dato N → nil. Un puntero "nuevo" apunta a un nodo suelto "Dato X".

Estado inferior (después): el puntero del nodo "Dato 1" (flecha roja) ahora apunta a "Dato X", y "Dato X" enlaza (flecha) hacia "Dato 2". Resultado: inicio → Dato 1 → Dato X → Dato 2 → …. → Dato N → nil. El nodo nuevo queda intercalado entre Dato 1 y Dato 2; se redirige el enlace de Dato 1 hacia el nuevo nodo y el del nuevo nodo hacia Dato 2.]

---

## Operaciones con listas enlazadas — Inserción al final

[FIGURA 4: Diagrama en dos estados de la inserción de un nodo al final.

Estado superior (antes): "inicio" apunta a "Dato 1". Lista: Dato 1 → Dato 2 → …. → Dato N. El puntero del último nodo "Dato N", que apuntaba a "nil", está resaltado en rojo. Un puntero "nuevo" apunta a un nodo suelto "Dato X" cuyo puntero está vacío.

Estado inferior (después): el último nodo "Dato N" (flecha roja descendente) ahora apunta a "Dato X". El nodo "Dato X" (apuntado por "nuevo") tiene su puntero hacia "nil". Resultado: inicio → Dato 1 → Dato 2 → …. → Dato N → Dato X → nil. El nuevo nodo se enlaza al final y pasa a apuntar a nil.]

---

## Operaciones con listas enlazadas — Eliminación del primer nodo

[FIGURA 5: Diagrama en dos estados de la eliminación del primer nodo.

Estado superior (antes): "inicio" (flecha roja) apunta a "Dato 1". Lista: Dato 1 → Dato 2 → …. → Dato N → nil.

Estado inferior (después): el puntero "inicio" (flecha roja) se reapunta directamente a "Dato 2", saltándose "Dato 1". La lista resultante recorrida desde inicio es: Dato 2 → …. → Dato N → nil. El nodo "Dato 1" queda desconectado (aún dibujado pero ya no alcanzable desde inicio).]

---

## Operaciones con listas enlazadas — Eliminación de un nodo central

[FIGURA 6: Diagrama en dos estados de la eliminación de un nodo intermedio.

Estado superior (antes): "inicio" apunta a "Dato 1". Lista: Dato 1 → Dato 2 → Dato 3 → Dato N → nil. El enlace de "Dato 2" hacia "Dato 3" está resaltado en rojo (el nodo "Dato 3" será eliminado).

Estado inferior (después): el puntero de "Dato 2" (flecha roja que baja y vuelve a subir, saltando por encima de Dato 3) se reapunta a "Dato N", omitiendo a "Dato 3". Resultado del recorrido: Dato 1 → Dato 2 → Dato N → nil. El nodo "Dato 3" queda desenlazado.]

---

## Operaciones con listas enlazadas — Eliminación del nodo final

[FIGURA 7: Diagrama en dos estados de la eliminación del último nodo.

Estado superior (antes): "inicio" apunta a "Dato 1". Lista: Dato 1 → Dato 2 → Dato 3 → Dato N → nil. El enlace de "Dato 3" hacia "Dato N" está resaltado en rojo (Dato N será eliminado).

Estado inferior (después): el puntero de "Dato 3" (flecha roja ascendente) ahora apunta a "nil", convirtiéndose en el nuevo último nodo. El nodo "Dato N" (que conserva su propio puntero a nil) queda desconectado a la derecha. Resultado: Dato 1 → Dato 2 → Dato 3 → nil.]

---

## Resumiendo

- Los punteros almacenan direcciones de memoria que hacen referencia a un valor.
- Los nodos son estructuras que contienen uno o varios valores y uno o varios punteros.
- Las listas enlazadas, esta compuesta de uno o mas nodos.
- La lista enlazada puede estar vacía, significa que no tiene nodos

---

## Cierre de la clase

Responder lo siguiente:

- ¿El puntero puede contener un nombre? ____________
- ¿El nodo puede almacenar a un puntero? ____________

---

## Preguntas

*(Diapositiva de cierre — título "Preguntas" centrado.)*

---

# Resumen estructural

| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | 7 | Diagramas de listas enlazadas: 1 estructura general (con direcciones de memoria) + 6 de operaciones (inserción inicio/medio/final, eliminación primer/central/final nodo). Todos con nodos amarillos dato+puntero y flechas; los cambios se resaltan en rojo. |
| Tablas     | 0 | — |
| Fórmulas   | 0 | — |
| Código     | 0 | Se mencionan soluciones en Java pero no hay código fuente. |
| Diagramas  | 7 | (Contabilizados como figuras; son diagramas de estructura de datos.) |
| Ejercicios/Preguntas | 5 | 2 dudas de punteros + 1 conocimiento previo + 2 preguntas de cierre. |

**Observaciones:** Documento de diapositivas con OCR limpio. El contenido textual es escaso; el valor está en los 7 diagramas de listas enlazadas, descritos íntegramente en texto para NotebookLM. Las marcas de agua/logo UTP y números de página se omitieron por ser ruido repetitivo (regla 2.14). Se conserva la redacción original tal cual (incluyendo posibles errores de tildes/concordancia del original, p.ej. "esta compuesta", "Porque es importante").
