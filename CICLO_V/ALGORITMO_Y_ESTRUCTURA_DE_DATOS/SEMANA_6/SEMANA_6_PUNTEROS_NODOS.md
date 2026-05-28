# Semana 6 — Punteros, Nodos y Lista Enlazada

**Curso:** Algoritmos y Estructura de Datos — UTP  
**Tema:** Punteros, Nodos, Lista Simplemente Enlazada y sus operaciones

---

## 1. ¿Qué es un Nodo?

Un nodo es una **caja con dos compartimentos**:

```
┌──────────┬──────────┐
│  dato    │  sgte    │
│ (String) │ (Nodo)   │
└──────────┴──────────┘
```

- **dato** → lo que guardas (el valor, la información)
- **sgte** → el puntero, guarda la dirección de memoria del siguiente nodo

En código se representa con dos clases:

```java
class Nodo {
    private String dato;
    private Nodo sgte;
}

class Lista {
    private Nodo nuevo;
    private Nodo inicio;
}
```

---

## 2. ¿Qué es un Puntero?

Un puntero es una **dirección de memoria**. No guarda el dato en sí, guarda el número que indica **dónde vive ese dato** en la memoria.

### Analogía

- El libro = el nodo (la caja con información)
- La dirección de la biblioteca = el puntero

No fotocopias el libro entero. Solo anotas dónde está. Con esa dirección puedes llegar a él, y desde él llegar al siguiente, y así toda la cadena.

### Lo que realmente guarda sgte

```
┌──────────┬──────────┐
│    B     │   600    │   ← 600 es la dirección de memoria, no el dato "A"
└──────────┴──────────┘
```

No dice "el siguiente es A". Dice "el siguiente está en la dirección 600". Con solo ese número tienes acceso a todo lo que viene después.

---

## 3. ¿Qué es inicio?

`inicio` **no es un nodo**. Es solo un puntero, una variable que guarda la dirección del primer nodo de la lista.

```
inicio = 600   ← solo un número, una dirección
```

```
inicio
  |
  → [dato | →] → [dato | →] → [dato | null]
       600            150           200
```

- Sin `inicio` no puedes encontrar ningún nodo de la lista
- Si `inicio == null` la lista está vacía
- `null` en el último nodo significa "aquí termina la lista"

---

## 4. Lista Enlazada

Una lista enlazada es una **cadena de nodos** donde cada uno apunta al siguiente.

```
inicio → [A | →] → [B | →] → [C | null]
```

### Comparación con arreglo

| | Arreglo | Lista Enlazada |
|---|---|---|
| Tamaño | Fijo desde el inicio | Crece o encoge libremente |
| Memoria | Todo junto y pegado | Disperso por cualquier dirección |
| Agregar al inicio | Mueves todo → O(n) | Solo cambias punteros → O(1) |
| Acceso por índice | Directo → O(1) | Tienes que recorrer → O(n) |
| Sobrecarga | Ninguna | 1 puntero extra por nodo |

---

## 5. Operaciones

### Regla de oro antes de operar

> **Nunca sueltes un puntero sin antes haberlo guardado en otro lado.**  
> Si lo sueltas, pierdes todo lo que había después. No hay forma de recuperarlo.

---

### 5.1 Insertar al inicio

```
if (inicio == null)
    inicio = nuevo
else
    nuevo.sgte = inicio
    inicio = nuevo
```

**Caso 1 — Lista vacía:**

```
nuevo → [A | null]
inicio == null → TRUE
inicio = nuevo
─────────────────────────
inicio → [A | null]
```

**Caso 2 — Ya hay nodos:**

Tienes la lista:
```
inicio → [A | null]
nuevo  → [B | null]
```

**Primero:** `nuevo.sgte = inicio`
> El compartimento puntero de nuevo guarda la dirección donde está inicio

```
nuevo → [B | 600] ──→ [A | null]
                  ↑
          guardó la dirección de inicio (600)
```

**Segundo:** `inicio = nuevo`
> Inicio se mueve al nuevo nodo

```
inicio → [B | →] → [A | null]
```

**¿Por qué ese orden y no al revés?**

Si primero haces `inicio = nuevo`:
- inicio ya apunta al nuevo
- `nuevo.sgte = inicio` haría que el nuevo apunte a sí mismo → bucle infinito

Primero enganchas hacia adelante, luego mueves inicio.

**Resultado con 4 nodos insertando siempre al inicio:**

```
Agrego A:  inicio → [A | null]
Agrego B:  inicio → [B | →] → [A | null]
Agrego C:  inicio → [C | →] → [B | →] → [A | null]
Agrego D:  inicio → [D | →] → [C | →] → [B | →] → [A | null]
```

Los nodos se van apilando al frente. El último en entrar queda primero.

---

### 5.2 Insertar en medio

Quieres insertar un nodo nuevo entre el nodo 2 y el nodo 3 de esta lista:

```
inicio → [1|→] → [2|→] → [3|→] → [4|→] → [5|null]
```

Necesitas un **puntero auxiliar** que llegue al nodo anterior al punto de inserción (nodo 2).

**Paso 1 — aux llega al nodo 2**

```
inicio → [1|→] → [2|→] → [3|→] → [4|→] → [5|null]
                   ↑
                  aux
```

**Paso 2 — `nuevo.sgte = aux.sgte`**

El nuevo apunta hacia donde apunta el nodo 2 (es decir, al nodo 3).

```
nuevo → [X | →] ───────────→ [3|→] → [4|→] → [5|null]
                 ↑
         guardó la dirección del nodo 3
```

**Paso 3 — `aux.sgte = nuevo`**

El nodo 2 ahora apunta al nuevo en vez del 3.

```
inicio → [1|→] → [2|→] → [X|→] → [3|→] → [4|→] → [5|null]
```

**¿Por qué ese orden?**

Si primero haces `aux.sgte = nuevo`:
- El nodo 2 ya apunta al nuevo
- Perdiste la dirección del nodo 3 para siempre, ya no puedes conectar el nuevo con él

Siempre primero enganchas el nuevo hacia adelante, luego cortas la conexión anterior.

---

### 5.3 Eliminar un nodo en medio

Quieres eliminar el nodo 3 de esta lista:

```
inicio → [1|→] → [2|→] → [3|→] → [4|→] → [5|null]
```

**Paso 1 — aux llega al nodo anterior (nodo 2)**

```
inicio → [1|→] → [2|→] → [3|→] → [4|→] → [5|null]
                   ↑
                  aux
```

**Paso 2 — `aux.sgte = aux.sgte.sgte`**

El nodo 2 salta por encima del nodo 3 y apunta directo al nodo 4.

```
inicio → [1|→] → [2|→] → [4|→] → [5|null]
                            ↑
                    saltó el nodo 3
```

El nodo 3 queda sin que nadie lo apunte. Desaparece de la lista.

---

### 5.4 Eliminar al inicio

```
inicio = inicio.sgte
```

Mueves inicio al segundo nodo. El primero queda sin referencia y desaparece.

```
Antes:  inicio → [A|→] → [B|→] → [C|null]
Después: inicio → [B|→] → [C|null]
```

---

## 6. Resumen visual de todo

```
NODO
┌──────────┬──────────┐
│  dato    │  sgte    │  ← sgte guarda dirección, no dato
└──────────┴──────────┘

PUNTERO = solo una dirección de memoria (un número)

inicio = solo puntero, no es nodo, apunta al primero

LISTA ENLAZADA
inicio → [1|→] → [2|→] → [3|→] → ... → [n|null]
                                              ↑
                                         null = fin

INSERTAR AL INICIO       INSERTAR EN MEDIO         ELIMINAR EN MEDIO
nuevo.sgte = inicio      nuevo.sgte = aux.sgte      aux.sgte = aux.sgte.sgte
inicio = nuevo           aux.sgte = nuevo
```

---

## 7. Preguntas de repaso

1. ¿Cuáles son las dos partes de un nodo?
2. ¿Qué guarda realmente el puntero `sgte`? ¿El dato o la dirección?
3. ¿`inicio` es un nodo o un puntero? ¿Qué diferencia tiene eso?
4. ¿Qué significa que `inicio == null`?
5. Al insertar al inicio, ¿por qué primero haces `nuevo.sgte = inicio` y no al revés?
6. Al insertar en medio, ¿a qué nodo tiene que llegar `aux` antes de insertar?
7. ¿Qué pasa si sueltas un puntero sin guardarlo antes?
