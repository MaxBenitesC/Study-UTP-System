---
universidad: UTP
curso: Sistemas Operativos
tema: Gestión de memoria y gestión de archivos — Algoritmos de reemplazo y asignación
semana: 8
sesion: 2
unidad: "Unidad de aprendizaje 2: Gestión de memoria y gestión de archivos"
tipo_documento: diapositivas
paginas: 22
fuente_pdf: S08_s2 Material.pdf
---

# Sistemas Operativos — Semana 08, Sesión 02

**Unidad de aprendizaje 2:** Gestión de memoria y gestión de archivos.

## Inventario del documento
- **Archivo:** S08_s2 Material.pdf
- **Páginas:** 22
- **Curso:** Sistemas Operativos
- **Semana / Sesión:** 08 / 02
- **Tema:** Algoritmos de reemplazo de página y algoritmos de asignación de memoria
- **Tipo:** Diapositivas (PDF digital)
- **Estado OCR:** texto digital nítido
- **Contiene:** figuras decorativas, recuadros informativos, 1 tabla comparativa, fórmulas, pseudocódigo, 1 ejemplo paso a paso

---

## Logro de aprendizaje
Al finalizar la sesión el estudiante logra:
- **Administrar la memoria y los archivos que componen un sistema operativo.**

## Temario
- Gestión de Memoria.
- Administración básica de memoria.
- Asignación de memoria contigua.

## Conocimientos previos
- Algoritmos

## Utilidad
- Ejecución de programas cuyos requerimientos de memoria exceden a la memoria física.

---

## Algoritmos de reemplazo

### ¿Por qué necesitamos algoritmos de reemplazo?
Cuando la memoria física se llena y llega una nueva página, ¿cuál página existente debemos reemplazar para minimizar los fallos de página futuros?

### Objetivos de los algoritmos
- **Minimizar fallos de página:** Reducir accesos a disco.
- **Maximizar el rendimiento:** Mejorar tiempo de respuesta.
- **Eficiencia computacional:** Bajo overhead del algoritmo.
- **Justicia:** No penalizar procesos específicos.

### Ejemplo y métricas
> **Ejemplo de Fallo de Página:** Si un proceso solicita la página 7, pero solo hay marcos para 4 páginas y todos están ocupados, el SO debe decidir cuál página reemplazar.

> El algoritmo óptimo minimizaría los fallos de página, pero requiere conocimiento del futuro.

**Métricas de Evaluación:**

$$
\text{Tasa de Fallos} = \frac{\text{Número de Fallos de Página}}{\text{Total de Referencias}} \times 100\%
$$

$$
\text{Tiempo Efectivo de Acceso} = (1 - p) \times \text{Tiempo\_RAM} + p \times \text{Tiempo\_Disco}
$$

(donde *p* = probabilidad de fallo de página.)

---

## Algoritmos FIFO y LRU

### Algoritmo FIFO (First In, First Out)
- **Principio:** La página que ha estado más tiempo en memoria es la primera en ser reemplazada.
- **Implementación:** Cola simple que mantiene el orden de llegada.
- **Ventajas:** Muy simple de implementar y entender.
- **Desventajas:** Puede sufrir de la **Anomalía de Belady**.

**[FIGURA — Ejemplo FIFO]** Secuencia de referencias: 7, 0, 1, 2, 0, 3, 0, 4 (con 3 marcos). Se muestra el llenado paso a paso de los marcos:
- Paso 1: marcos `[7][-][-]` → Fallo
- Paso 2: marcos `[7][0][-]` → Fallo
- Paso 3: marcos `[7][0][1]` → Fallo

### LRU (Least Recently Used)
- **Principio:** Reemplaza la página que no ha sido usada por más tiempo.
- **Implementación:** Pila o contador de tiempo para cada página.
- **Ventajas:** Buen rendimiento, aproxima el comportamiento óptimo.
- **Desventajas:** Mayor overhead computacional.

---

## Algoritmos Clock y Óptimo

### Algoritmo Clock (Segunda Oportunidad)
- **Principio:** Aproximación a LRU usando un bit de referencia.
- **Funcionamiento:**
  1. Mantiene páginas en lista circular.
  2. Cada página tiene un bit de referencia.
  3. Si bit = 1: cambiar a 0 y continuar.
  4. Si bit = 0: seleccionar para reemplazo.
- **Ventajas:** Eficiente y buen rendimiento.

**[DIAGRAMA — Lista circular del algoritmo Clock]** Secuencia de páginas conectadas por flechas: `Página A (bit=1)` → `Página B (bit=0)` → `Página C (bit=1)` → `Puntero Clock` (recuadro rojo que avanza por la lista circular).

### Algoritmo Óptimo (OPT/MIN)
- **Principio:** Reemplaza la página que será referenciada más tarde en el futuro.
- **Características:**
  - Teóricamente óptimo (mínimos fallos de página).
  - Imposible de implementar en la práctica.
  - Usado como referencia para comparar otros algoritmos.
- **Utilidad:** Benchmark para evaluar algoritmos prácticos.

### Tabla comparativa de algoritmos de reemplazo
| Algoritmo | Complejidad | Rendimiento | Implementación |
|-----------|-------------|-------------|----------------|
| FIFO      | O(1)        | Regular     | Muy simple     |
| LRU       | O(1) – O(n) | Bueno       | Compleja       |
| Clock     | O(1)        | Bueno       | Simple         |
| Óptimo    | –           | Perfecto    | Imposible      |

---

## Planificación de memoria

### ¿Qué es la Planificación de Memoria?
Conjunto de algoritmos que determinan cómo asignar la memoria disponible a los procesos que la solicitan, optimizando el uso y minimizando la fragmentación.

### Objetivos principales
- **Minimizar fragmentación:** Reducir espacio desperdiciado.
- **Maximizar utilización:** Usar eficientemente la memoria.
- **Tiempo de respuesta:** Asignación rápida de memoria.
- **Justicia:** Acceso equitativo a la memoria.

### Tipos de Fragmentación
- **Fragmentación Externa:** Espacios libres muy pequeños entre procesos asignados.
- **Fragmentación Interna:** Espacio desperdiciado dentro de bloques asignados.

---

## Algoritmos de asignación (Algoritmos Clásicos de Asignación)

### 1. First Fit (Primer Ajuste)
- **Principio:** Asigna el primer bloque libre que sea suficientemente grande.
- **Algoritmo (pseudocódigo):**
```text
for each free_block in memory_list:
    if free_block.size >= requested_size:
        allocate(free_block, requested_size)
        return success
return failure
```
- **Ventajas:** Rápido, simple de implementar.
- **Desventajas:** Puede crear fragmentación al inicio.

### 2. Best Fit (Mejor Ajuste)
- **Principio:** Encuentra el bloque libre más pequeño que satisfaga la solicitud.
- **Algoritmo (pseudocódigo):**
```text
best_block = null
smallest_size = infinity
for each free_block in memory_list:
    if (free_block.size >= requested_size AND free_block.size < smallest_size):
        best_block = free_block
        smallest_size = free_block.size
if best_block != null:
    allocate(best_block, requested_size)
```
- **Ventajas:** Minimiza el desperdicio de memoria.
- **Desventajas:** Más lento, puede crear muchos fragmentos pequeños.

### 3. Worst Fit (Peor Ajuste)
- **Principio:** Asigna el bloque libre más grande disponible.
- **Ventajas:** Deja fragmentos grandes que pueden ser útiles.
- **Desventajas:** Desperdicia memoria, performance similar a Best Fit.

---

## Conclusiones
- La abstracción básica para la gestión de memoria es la **"dirección de memoria"**.
- El direccionamiento de memoria permite utilizar el concepto de **memoria virtual**, mecanismo que permite ejecutar programas cuyos requerimientos de memoria exceden a la memoria física.
- La **paginación** es un método que hace uso del concepto de memoria virtual. Los procesos utilizan **"páginas"** de memoria virtual, las cuales se corresponden con sus respectivos **"marcos de página"** de memoria física.

## Cierre
- ¿Qué aprendiste en esta sesión?
- Te invitamos a compartir tus conclusiones en clase.

---

## Resumen estructural
| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | ~8       | Mayormente decorativas (logo UTP, íconos); 1 informativa (ejemplo FIFO paso a paso) |
| Tablas     | 1        | Comparativa FIFO/LRU/Clock/Óptimo |
| Fórmulas   | 2        | Tasa de fallos y Tiempo efectivo de acceso |
| Código     | 2        | Pseudocódigo First Fit y Best Fit |
| Diagramas  | 1        | Lista circular del algoritmo Clock |
| Ejercicios | 0        | — |
