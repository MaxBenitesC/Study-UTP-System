---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Listas Enlazadas - Búsqueda y Ordenamiento
semana: 8
tipo_documento: Diapositivas de clase (convertidas de PPTX)
paginas: 13
fuente_pdf: S08_s1 -Listas enlazadas - Busqueda - Ordenamiento.pdf (convertido de .pptx)
---

# Inventario del documento

- **Archivo:** S08_s1 -Listas enlazadas - Busqueda - Ordenamiento.pdf (convertido con LibreOffice desde `.pptx`)
- **Páginas:** 13 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos · **Semana:** 8 (S08_s1)
- **Tema:** Listas Enlazadas — Búsqueda y Ordenamiento
- **Tipo:** Diapositivas de clase (PowerPoint UTP)
- **Estado OCR:** Limpio (texto vectorial; algún texto se superpone al diagrama)
- **Contiene:** Figuras/diagramas ✔ · Tablas ✘ · Fórmulas ✘ · Código ✘ · Ejercicios/preguntas ✔

---

# Algoritmos y Estructuras de Datos
## Listas Enlazadas — Búsqueda - Ordenamiento

*(Portada.)*

---

## Logro de aprendizaje

> Al finalizar la sesión el estudiante utilizará adecuadamente las operaciones de búsqueda con Listas Enlazadas para desarrollar soluciones algorítmicas en java.

---

## Dudas sobre la clase anterior

Punteros:

- Un puntero puede apuntar a 2 nodos a la vez?
- Un puntero puede apuntar a 2 nodos en diferentes momentos?

---

## Conocimientos previos: Búsquedas secuenciales - Listas Enlazadas

- Indique un ejemplo de una búsqueda secuencial (uno a uno) de un valor u objeto cualquiera

---

## Utilidad del tema: Búsqueda - Listas enlazadas

Porque es importante:

- Permiten generar listas de elementos en forma secuencial
- Permiten generar listas de elementos de manera dinámica

---

## Búsqueda en Listas Enlazadas (concepto)

- La operación búsqueda de un elemento en una lista enlazada, consiste en recorrer la lista hasta encontrar el nodo con el valor buscado.
- Una vez encontrado el nodo, devolver la referencia a ese nodo (en caso negativo, devuelve null).
- Otro forma es que el método devuelve true si encuentra el nodo con el elemento y false si no está en la lista.

---

## Búsqueda en Listas Enlazadas (método)

El método es el siguiente:

- Usar una referencia (p) para recorrer la lista, nodo a nodo.
- El bucle de búsqueda inicializa p al nodo inicio y compara el nodo referenciado por p con el elemento buscado; si coincide la búsqueda termina, en caso contrario; p avanza al siguiente nodo.
- La búsqueda termina cuando se encuentra el nodo, o bien cuando se ha recorrido la lista, y entonces indice toma el valor n[ull] *(texto cortado por el diagrama)*.

[FIGURA 1: Lista enlazada recorrida por la referencia p. "inicio" apunta al primer nodo; nodos Dato 1 → Dato 2 → Dato 3 → Dato N → nil. El enlace Dato 2 → Dato 3 está resaltado en rojo (indicando el avance de p nodo a nodo durante la búsqueda).]

---

## Búsqueda en Listas Enlazadas (tipos)

- Búsqueda en una lista con datos no ordenados
- Búsqueda en una lista con datos ordenados

---

## Listas Enlazadas Ordenadas (concepto)

- Los elementos de una lista tienen la propiedad de estar ordenados de forma lineal según las posiciones que ocupan en la misma.
- Es posible mantener una lista enlazada ordenada según el dato asociado a cada nodo. La forma de insertar un elemento en una lista ordenada es determinar, en primer lugar, la posición de inserción y luego realizar los enlaces correspondientes.

---

## Listas Enlazadas Ordenadas (inserción ordenada)

- La forma de insertar nodos con valores ordenados consiste en buscar la ubicación donde insertar el nodo.
- Por ejemplo, para insertar el nodo con el valor de X,

[FIGURA 2: Inserción ordenada. "inicio" apunta a Dato 1; lista Dato 1 → Dato 2 → … → Dato N → nil. El puntero "nuevo" apunta al nodo "Dato X". Una flecha roja muestra que el puntero de Dato 1 se redirige hacia Dato X, y Dato X enlaza hacia Dato 2. Es decir, X se inserta en su posición ordenada entre Dato 1 y Dato 2, ajustando los enlaces.]

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
| Figuras    | 2 | Recorrido de búsqueda con referencia p (enlace resaltado) e inserción ordenada del nodo Dato X. |
| Tablas     | 0 | — |
| Fórmulas   | 0 | — |
| Código     | 0 | — |
| Diagramas  | 2 | (Contabilizados como figuras.) |
| Ejercicios/Preguntas | 4 | 2 dudas previas + 2 de cierre. |

**Observaciones:** Deck convertido de PPTX, OCR limpio. **Material núcleo del examen de semana 8**: búsqueda secuencial en listas enlazadas (recorrer con referencia p, devolver nodo/null o true/false), distinción entre lista no ordenada vs. ordenada, e inserción manteniendo el orden (localizar posición + reenlazar). En la diapositiva "Búsqueda (método)" la última frase queda cortada por el diagrama ("indice toma el valor n…" → null). Se conservó la redacción original con erratas ("Otro forma", "esta compuesta", "indice").
