---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Punteros, Nodos y Listas Enlazadas
semana: 6
tipo_documento: Diapositivas de clase (convertidas de PPTX)
paginas: 20
fuente_pdf: S06_s1 - Punteros - Listas enlazadas.pdf (convertido de S06_s1 - Punteros - Listas enlazadas.pptx)
---

# Inventario del documento

- **Archivo:** S06_s1 - Punteros - Listas enlazadas.pdf (convertido con LibreOffice desde `.pptx`)
- **Páginas:** 20 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos · **Semana:** 6 (S06_s1)
- **Tema:** Punteros, Nodos y Listas Enlazadas
- **Tipo:** Diapositivas de clase (PowerPoint UTP)
- **Estado OCR:** Limpio (texto vectorial; algún globo de texto se superpone a diagramas)
- **Contiene:** Figuras/diagramas ✔ (punteros, nodos, listas) · Tablas ✘ · Fórmulas ✘ · Código ✘ · Ejercicios/preguntas ✔
- **Nota:** Este deck es **más completo** que el de SEMANA_7 (`S07_s1_Punteros_Listas_enlazadas`): incluye además la teoría de **Punteros** y **Nodo** (operación `q = p`) antes de las operaciones de listas. Las diapositivas de operaciones (inserción/eliminación) son las mismas que en SEMANA_7 → ver [[S07_s1_Punteros_Listas_enlazadas]].

---

# Algoritmos y Estructuras de Datos
## Listas Enlazadas

*(Portada.)*

---

## Logro de aprendizaje

> Al finalizar la sesión el estudiante utilizará adecuadamente las operaciones con Listas Enlazadas para desarrollar soluciones algorítmicas en java.

---

## Dudas sobre la clase anterior

Arreglos:

- Para que sirve la función `b.length`
- Sea el arreglo a, que se obtiene con la sentencia `a[0].length`?
- Sea la matriz a, `a.length` → numero de filas, `a[0].length` → cant de columnas
- `C[0].length()`

---

## Conocimientos previos: Apuntadores

- Cuando hay un objeto lejos del lugar donde ustedes se encuentran, como hacen referencia a ese objeto?
- En la carretera hay paneles que indican a que distancia se encuentra un pueblo, ciudad

---

## Utilidad del tema: Punteros Listas enlazadas

Porque es importante:

- Permiten referenciar a un objeto
- Genera una relación dinámica de valores

---

## Punteros

- El **puntero** es una variable cuyo valor es una posición de memoria
- Al valor **Estructura** se accede mediante el puntero

[FIGURA 1: Una caja "Puntero" que contiene el valor "320"; un globo señala que ese valor es una "dirección de memoria". Una flecha sale del puntero hacia otra caja "Estructura" ubicada en la dirección 320. Aparte, un recuadro aclara: "Diferencia: la variable nombre almacena el valor" (contrapone puntero —guarda dirección— vs. variable normal —guarda el valor).]

---

## Nodo (definición)

- Es una posición de memoria que almacena valores.
- Tiene 2 partes:
  - Una parte almacena información (puede almacenar uno ó varios tipos de datos)
  - La otra parte almacena un puntero que [hace referencia a otra dirección de me...]

[FIGURA 2: Un nodo dibujado como un rectángulo azul dividido en dos partes; un globo "Información" señala la parte izquierda y un globo "puntero" señala la parte derecha. (El texto de la diapositiva se superpone parcialmente al dibujo.)]

---

## Nodo (a considerar)

A considerar:

- Todo nodo es apuntado por uno o varios punteros.
- Un puntero apunta a un solo nodo.
- El nodo toma el nombre del puntero.
- El puntero apunta al nodo y no alguna parte al interior del nodo.

[FIGURA 3: Dos punteros, "p" y "q", ambos con flechas apuntando al mismo nodo "Dato 1" (que a su vez tiene un puntero saliente a la derecha). Ilustra que un nodo puede ser apuntado por varios punteros.]

---

## Nodo (operación q = p)

[FIGURA 4: Estado inicial — dos nodos independientes: el puntero "p" apunta al nodo "Dato 1" y el puntero "q" apunta al nodo "Dato 2"; cada nodo tiene su puntero saliente.

Recuadro explicativo: "Si realizamos la operación `q = p` significa que el puntero **q** va a apuntar al lugar apuntado por el puntero **p**".

Estado resultante — tanto "p" como "q" apuntan ahora al nodo "Dato 1"; el nodo "Dato 2" queda abajo sin que ningún puntero lo señale. Texto: "El nodo que contiene el dato 2 se queda sin apuntador y no puede ser accesado".]

---

## Listas Enlazadas

- Una lista enlazada, es una colección de nodos. Es una alternativa a los arreglos.
- Los punteros permiten el enlace entre nodos.

[FIGURA 5: Diagrama de lista enlazada. "inicio" apunta al primer nodo. Cuatro nodos amarillos (dato + puntero) enlazados: Dato 1 → Dato 2 → Dato 3 → Dato 4 → (flecha al vacío).

Representación con direcciones de memoria: celda "500" (a la que apunta inicio); "Dato 1 | 300" en dir 500; "Dato 2 | 700" en dir 300; "Dato 3 | 200" en dir 700; "Dato 4 | null" en dir 200. El último nodo guarda null (fin de lista).]

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

[FIGURA 6: Dos estados. Antes: "inicio" apunta a Dato 1; lista Dato 1 → Dato 2 → … → Dato N → nil; puntero "nuevo" apunta a un nodo suelto "Dato X". Después: "inicio" apunta a "Dato X", y "Dato X" enlaza al antiguo Dato 1. Resultado: inicio → Dato X → Dato 1 → … → Dato N → null.]

---

## Operaciones con listas enlazadas — Inserción al medio

[FIGURA 7: Dos estados. Antes: el enlace Dato 1 → Dato 2 está resaltado en rojo; "nuevo" apunta a "Dato X" suelto. Después: Dato 1 apunta a Dato X (rojo) y Dato X apunta a Dato 2. Resultado: inicio → Dato 1 → Dato X → Dato 2 → … → Dato N → nil.]

---

## Operaciones con listas enlazadas — Inserción al final

[FIGURA 8: Dos estados. Antes: el puntero de Dato N a nil resaltado en rojo; "nuevo" apunta a "Dato X" suelto. Después: Dato N apunta a Dato X (rojo) y Dato X apunta a nil. Resultado: inicio → Dato 1 → … → Dato N → Dato X → nil.]

---

## Operaciones con listas enlazadas — Eliminación del primer nodo

[FIGURA 9: Dos estados. Antes: "inicio" (rojo) apunta a Dato 1; lista Dato 1 → Dato 2 → … → Dato N → nil. Después: "inicio" se reapunta a Dato 2, saltándose Dato 1. Resultado recorrido: Dato 2 → … → Dato N → nil (Dato 1 queda desconectado).]

---

## Operaciones con listas enlazadas — Eliminación de un nodo central

[FIGURA 10: Dos estados. Antes: el enlace Dato 2 → Dato 3 resaltado en rojo (se eliminará Dato 3). Después: el puntero de Dato 2 (rojo, salta por encima de Dato 3) se reapunta a Dato N. Resultado: Dato 1 → Dato 2 → Dato N → nil (Dato 3 desenlazado).]

---

## Operaciones con listas enlazadas — Eliminación del nodo final

[FIGURA 11: Dos estados. Antes: el enlace Dato 3 → Dato N resaltado en rojo (se eliminará Dato N). Después: el puntero de Dato 3 (rojo) apunta a nil, quedando como nuevo último nodo. Resultado: Dato 1 → Dato 2 → Dato 3 → nil (Dato N desconectado a la derecha).]

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

*(Diapositiva de cierre.)*

---

# Resumen estructural

| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | 11 | Puntero (dir. de memoria), nodo (2 partes), nodos p/q, operación q=p, lista enlazada con direcciones, y 6 diagramas de operaciones (inserción inicio/medio/final, eliminación primer/central/final nodo). |
| Tablas     | 0 | — |
| Fórmulas   | 0 | — |
| Código     | 0 | — |
| Diagramas  | 11 | (Contabilizados como figuras.) |
| Ejercicios/Preguntas | 4 | 2 dudas previas + 2 de cierre. |

**Observaciones:** Deck convertido de PPTX, OCR limpio. **Material núcleo del examen de semana 6**: concepto de puntero (variable que guarda dirección de memoria), nodo (información + puntero), aliasing de punteros (`q = p` deja un nodo inaccesible → fuga), y lista enlazada con sus 6 operaciones (inserción y eliminación en inicio/medio/final). Es la versión ampliada del deck de SEMANA_7. Se conservó la redacción original con erratas ("esta compuesta", "accesado"). En la diapositiva "Nodo (definición)" el texto se superpone al dibujo y una frase queda parcialmente tapada.
