---
universidad: UTP
curso: Sistemas Operativos
tema: Direcciones de memoria, memoria virtual, paginación y algoritmos de reemplazo/asignación
semana: 8
sesion: 1
unidad: "Unidad de aprendizaje 2: Gestión de memoria y gestión de archivos"
tipo_documento: diapositivas
paginas: 45
fuente_pdf: S08_s1 Material.pdf
---

# Sistemas Operativos — Semana 08, Sesión 01
**Unidad 2:** Gestión de memoria y gestión de archivos.

## Inventario
- Archivo: S08_s1 Material.pdf | Páginas: 45 | Tipo: diapositivas | OCR: nítido
- Contiene: varios diagramas de traducción de direcciones y paginación, 2 fórmulas, pseudocódigo (First/Best Fit), tablas comparativas.
- **Nota:** la parte final (algoritmos de reemplazo, planificación y asignación) coincide con la sesión S08_s2; aquí se amplía con direcciones de memoria, memoria virtual y paginación.

## Logro / Temario / Conocimientos previos / Utilidad
- **Logro:** administrar la memoria y los archivos que componen un SO.
- **Temario:** Gestión de Memoria; Administración básica de memoria; Asignación de memoria contigua.
- **Conocimientos previos:** Algoritmos.
- **Utilidad:** ejecución de programas cuyos requerimientos de memoria exceden la memoria física.

---

## Direcciones de Memoria
**¿Qué son?** Una dirección de memoria es un **identificador único** que especifica la ubicación de un byte específico en la memoria del sistema.

**Tipos de direcciones:**
- **Lógicas (Virtuales):** generadas por el procesador.
- **Físicas:** ubicación real en la RAM.
- **Relativas:** relativas a un punto base.

**[DIAGRAMA — Traducción]** `Dirección Lógica 0x1000` → **MMU (Translation)** → `Dirección Física 0x5000`. La **MMU (Memory Management Unit)** se encarga de traducir direcciones lógicas a físicas.

Ideas clave (recuadros):
- La RAM puede verse como un conjunto finito de celdas que almacenan datos e instrucciones.
- Cada celda tiene una dirección identificada con un número único en **hexadecimal**.
- El SO asigna uno o varios rangos de direcciones a los procesos de modo que no se traslapen.
- Los programas usan direcciones lógicas que deben convertirse a físicas; la **MMU** (hardware) hace esa conversión.
- Las celdas de RAM (acceso aleatorio) se pueden "leer y escribir"; rango ejemplo `0000`–`00A0`.

---

## Memoria Virtual
**¿Qué es?** Técnica que permite que los programas usen **más memoria de la que físicamente está disponible**, creando la ilusión de un espacio de memoria más grande.

- **¿Qué permite?** Ejecutar procesos **parcialmente cargados** en memoria principal mientras el resto está en disco (referenciados fácilmente).
- **¿Cómo funciona?** Usa el disco como almacén secundario de procesos, liberando al programador de la preocupación de que sus programas quepan o no en memoria. El SO selecciona automáticamente qué fragmentos del proceso residen en memoria. Cuando el programa referencia una parte que no está en memoria física, el SO recibe una **alerta (fallo de página)** para buscar la parte faltante y volver a ejecutar la instrucción que falló. (Mucho del código maneja errores poco comunes que casi nunca se ejecutan, y se reserva más memoria de la necesaria.)

**Ventajas principales:**
1. **Abstracción:** los programas no necesitan saber la ubicación física real.
2. **Protección:** cada proceso tiene su propio espacio virtual.
3. **Multiprogramación:** múltiples programas pueden ejecutarse simultáneamente.
4. **Intercambio:** las páginas pueden moverse entre RAM y disco.

> **Ejemplo práctico:** si tienes 4 GB de RAM pero ejecutas programas que en total requieren 6 GB (Proceso A 2GB + B 3GB + C 1GB), la memoria virtual permite que funcionen usando el disco como extensión de la RAM (RAM física 4 GB total).

---

## Paginación
Esquema de gestión de memoria que divide la **memoria lógica y física en bloques de tamaño fijo** llamados **páginas** (lógica) y **marcos de página / frames** (física), respectivamente.

**Componentes clave:**
- **Páginas:** bloques de memoria lógica (ej.: 4 KB).
- **Marcos:** bloques de memoria física del mismo tamaño.
- **Tabla de Páginas:** mapea páginas lógicas a marcos físicos.
- **TLB:** Translation Lookaside Buffer (caché de traducción).

### Fórmula de traducción ⭐
$$
\text{Dirección Física} = (\text{Número de Marco} \times \text{Tamaño de Página}) + \text{Desplazamiento}
$$

**[DIAGRAMA]** Memoria Lógica (Página 0, 1, 2) ⟷ **Tabla de Páginas** (Traducción) ⟷ Memoria Física (Marco 3, Marco 1, Marco 7).

La dirección se divide en dos campos: **Número de página (p)** y **Desplazamiento (d)** (donde la parte de página es `m − n` bits y el desplazamiento `n` bits).

**[DIAGRAMA — Hardware de paginación]** CPU genera `[p | d]` (dirección lógica) → con `p` se indexa la **tabla de páginas** que devuelve el marco `f` → se forma `[f | d]` (dirección física) → memoria física. La MMU envía direcciones físicas a la memoria.

> **Ejemplo 1:** Memoria lógica con page 0,1,2,3 y tabla de páginas: page0→marco 1, page1→marco 4, page2→marco 3, page3→marco 7. La página se ubica en su marco correspondiente en memoria física.

### Tipos de Paginación
- **Simple:** una sola tabla de páginas por proceso; traducción directa; apta para espacios de direcciones pequeños.
- **Multinivel:** múltiples niveles de tablas; reduce el espacio para tablas grandes (ej.: 2 o 3 niveles).
- **Tabla de Páginas Invertida:** una entrada por **marco físico** (no por página lógica); ahorra espacio significativo; usada en PowerPC, IA-64.

| Tipo | Ventajas | Desventajas | Uso típico |
|------|----------|-------------|------------|
| Simple | Implementación sencilla | Tabla muy grande | Sistemas pequeños |
| Multinivel | Ahorra espacio | Múltiples accesos | Sistemas modernos |
| Invertida | Muy eficiente en espacio | Búsqueda compleja | Sistemas especiales |

---

## Algoritmos de Reemplazo de páginas
**¿Por qué?** Cuando la memoria física se llena y llega una nueva página, ¿cuál reemplazar para minimizar los fallos futuros?

**Objetivos:** minimizar fallos de página, maximizar rendimiento, eficiencia computacional (bajo overhead), justicia.

**Métricas:**
$$\text{Tasa de Fallos} = \frac{\text{Nº Fallos de Página}}{\text{Total de Referencias}} \times 100\%$$
$$\text{Tiempo Efectivo de Acceso} = (1-p)\times\text{Tiempo\_RAM} + p\times\text{Tiempo\_Disco}$$

- **FIFO:** reemplaza la página más antigua en memoria. Simple; puede sufrir la **Anomalía de Belady**. (Ejemplo con secuencia 7,0,1,2,0,3,0,4 y 3 marcos.)
- **LRU (Least Recently Used):** reemplaza la que lleva más tiempo sin usarse. Buen rendimiento; mayor overhead.
- **Clock (Segunda Oportunidad):** aproxima LRU con bit de referencia (lista circular: si bit=1 → poner 0 y avanzar; si bit=0 → reemplazar).
- **Óptimo (OPT/MIN):** reemplaza la que se usará más tarde en el futuro. Teóricamente óptimo, imposible de implementar; sirve de benchmark.

| Algoritmo | Complejidad | Rendimiento | Implementación |
|-----------|-------------|-------------|----------------|
| FIFO | O(1) | Regular | Muy simple |
| LRU | O(1) – O(n) | Bueno | Compleja |
| Clock | O(1) | Bueno | Simple |
| Óptimo | – | Perfecto | Imposible |

---

## Planificación y Algoritmos de Asignación
**Planificación de memoria:** conjunto de algoritmos que determinan cómo asignar la memoria disponible, optimizando el uso y minimizando la fragmentación. Objetivos: minimizar fragmentación, maximizar utilización, tiempo de respuesta, justicia.
- **Fragmentación externa:** espacios libres muy pequeños entre procesos asignados.
- **Fragmentación interna:** espacio desperdiciado dentro de bloques asignados.

**Algoritmos clásicos de asignación:**
- **First Fit:** primer bloque libre suficientemente grande. Rápido; puede crear fragmentación al inicio.
```text
for each free_block in memory_list:
    if free_block.size >= requested_size:
        allocate(free_block, requested_size); return success
return failure
```
- **Best Fit:** bloque libre más pequeño que satisfaga la solicitud. Minimiza desperdicio; más lento, crea muchos fragmentos pequeños.
```text
best_block = null; smallest_size = infinity
for each free_block in memory_list:
    if (free_block.size >= requested_size AND free_block.size < smallest_size):
        best_block = free_block; smallest_size = free_block.size
if best_block != null: allocate(best_block, requested_size)
```
- **Worst Fit:** bloque libre más grande disponible. Deja fragmentos grandes útiles; desperdicia memoria, performance similar a Best Fit.

---

## Cierre
- ¿Qué aprendiste en esta sesión? Comparte tus conclusiones en clase.

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras/Diagramas | ~8 | Traducción MMU, mapas página↔marco, hardware de paginación (p|d→f|d), ejemplos |
| Tablas   | 3        | Tipos de paginación, comparación de reemplazo, (campos p/d) |
| Fórmulas | 3        | Dirección física, tasa de fallos, tiempo efectivo de acceso |
| Código   | 2        | Pseudocódigo First Fit y Best Fit |
| Ejercicios | 0      | (ejemplos ilustrativos de paginación y FIFO) |
