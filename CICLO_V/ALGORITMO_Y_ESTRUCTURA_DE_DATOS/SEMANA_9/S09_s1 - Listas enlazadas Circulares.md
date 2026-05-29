---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Listas Enlazadas Circulares
semana: 9
tipo_documento: Diapositivas de clase (convertidas de PPTX)
paginas: 12
fuente_pdf: S09_s1 - Listas enlazadas Circulares.pdf (convertido de .pptx)
---

# Inventario del documento

- **Archivo:** S09_s1 - Listas enlazadas Circulares.pdf (convertido con LibreOffice desde `.pptx`)
- **Páginas:** 12 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos · **Semana:** 9 (S09_s1)
- **Tema:** Listas Enlazadas Circulares
- **Tipo:** Diapositivas de clase (PowerPoint UTP)
- **Estado OCR:** Limpio (texto vectorial; algún texto se superpone al diagrama)
- **Contiene:** Figuras/diagramas ✔ · Tablas ✘ · Fórmulas ✘ · Código ✘ · Ejercicios/preguntas ✔

---

# Algoritmos y Estructuras de Datos
## Listas Enlazadas Circulares

*(Portada.)*

---

## Logro de aprendizaje

> Al finalizar la sesión el estudiante utilizará adecuadamente las operaciones con Listas Enlazadas Circulares para desarrollar soluciones algorítmicas en java.

---

## Dudas sobre la clase anterior

Listas Doblemente enlazadas:

- Un puntero puede apuntar a 2 nodos a la vez?
- Un puntero puede apuntar a 2 nodos en diferentes momentos?

---

## Conocimientos previos: Listas Enlazadas Circulares

- Indique un ejemplo de una lista de elementos de tal manera que el ultimo elemento se relaciona con el primer elemento

---

## Utilidad del tema: Listas Enlazadas Circulares

Porque es importante:

- Permiten generar listas de elementos en forma secuencial que no tienen fin

---

## Listas enlazadas circulares (definición y tipos)

- Es un conjunto de nodos enlazados, cuyo último nodo está enlazado al primer nodo de la lista.
- Tipos:
  - Lista circular simple
  - Lista circular doblemente enlazada

[FIGURA 1: Lista circular simple. "inicio" apunta al primer nodo. Nodos Dato 1 → Dato 2 → Dato 3 → Dato 4, enlazados secuencialmente; el puntero del último nodo (Dato 4) retorna con una flecha hacia el primer nodo (Dato 1), cerrando el anillo. No hay nodo que apunte a nil.]

---

## Listas enlazadas circulares (características)

En una lista circular:

- No existe algún elemento que apunte a vacío
- Su estructura es tipo anillo
- Se pueden llegar a crear recorridos en bucles infinitos.

[FIGURA 2: Dos diagramas en forma de anillo.
- "Simplemente enzalada" (simplemente enlazada): cinco nodos dispuestos en círculo con flechas en un solo sentido (cada nodo apunta al siguiente y el último al primero); "Inicio" marca el nodo superior.
- "Doblemente enzalada" (doblemente enlazada): cinco nodos en círculo con flechas dobles (cada nodo enlaza con el siguiente y el anterior); "Inicio" marca el nodo superior.]

---

## Operaciones con listas enlazadas circulares

- **Básicas:**
  - Insertar un elemento
  - Eliminar un elemento
  - Recorrer la lista

---

## Listas enlazadas Circulares (doblemente enlazada)

- En esta lista, cada elemento contiene un puntero (referencias), además del valor almacenado y la característica es que el ultimo nodo apunta al primer nodo de la misma lista
- La Figura muestra una lista doblemente enlazada y un nodo de dicha lista

[FIGURA 3: Arriba, un nodo de una lista doblemente enlazada: rectángulo "Dato 1" con un puntero a la izquierda etiquetado "ant" (anterior) y uno a la derecha etiquetado "sig" (siguiente), con flechas en ambos sentidos.

Abajo, una lista doblemente enlazada: "inicio" apunta a Dato 1; nodos Dato 1 ⇄ Dato 2 ⇄ Dato 3 ⇄ Dato 4, cada uno con enlaces dobles (al anterior y al siguiente). El extremo izquierdo (ant de Dato 1) y el extremo derecho (sig de Dato 4) apuntan a "nil". (Nota: este diagrama ilustra la doblemente enlazada lineal; el texto indica que en la versión circular el último nodo apuntaría al primero en vez de a nil.)]

---

## Resumiendo

- Las listas enlazadas circulares permiten un recorrido infinito.
- Las listas circulares, permiten un recorrido continuo y circular
- La base para este tipo de listas es la lista enlazada simple.

---

## Cierre de la clase

Responder lo siguiente:

- Tipo de lista donde el ultimo nodo apunta al primer nodo
- Tipo de lista donde el puntero del ultimo nodo apunta a null

---

## Preguntas

*(Diapositiva de cierre.)*

---

# Resumen estructural

| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | 3 | Lista circular simple (lineal con retorno al inicio), anillos simple/doblemente enlazado, y nodo doble (ant/sig) con lista doblemente enlazada. |
| Tablas     | 0 | — |
| Fórmulas   | 0 | — |
| Código     | 0 | — |
| Diagramas  | 3 | (Contabilizados como figuras.) |
| Ejercicios/Preguntas | 4 | 2 dudas previas + 2 de cierre. |

**Observaciones:** Deck convertido de PPTX, OCR limpio. **Material núcleo del examen de semana 9**: lista enlazada circular (el último nodo apunta al primero → estructura de anillo, sin nil, permite recorrido continuo/infinito), tipos (circular simple vs. circular doblemente enlazada con punteros ant/sig). Se conservó la redacción original con erratas ("ultimo", "enzalada", "anull"). Algunos textos se superponen a los diagramas en la conversión PPTX→PDF.
