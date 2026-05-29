# Plan de Estudios — Examen Final de Sistemas Operativos (Semanas 6–9)

> Generado con NotebookLM a partir de las fuentes del cuaderno **Examen_Final_SO_Max** (semanas 6 a 9). Fecha: 2026-05-29.

## 1) Mapa de temas por semana

| Semana | Tema principal | Conceptos clave |
|---|---|---|
| **6** | **Interbloqueos (Deadlocks)** | Definición; recursos expropiables/no expropiables; **4 condiciones de Coffman** (exclusión mutua, retención y espera, no expropiación, espera circular); grafo de asignación de recursos (nodos, arcos, ciclos); estrategias: Avestruz, Prevención, Evitación (**Algoritmo del Banquero**), Detección y Recuperación. |
| **7–8** | **Gestión de memoria y memoria virtual** | Fragmentación interna y externa; asignación contigua y compactación; ubicación *First/Best/Worst Fit*; **swapping** (`swapon`, `swapoff`); direcciones lógicas vs físicas; paginación (páginas, marcos, tabla de páginas, TLB); algoritmos de reemplazo **FIFO, LRU, Clock, Óptimo**. |
| **9** | **Segmentación y gestión de archivos/usuarios** | Segmentación (división lógica de tamaño variable) y tabla de segmentos; paginación vs segmentación; segmentación paginada; administración Linux: usuarios y grupos (`adduser`, `usermod`, `deluser`, `passwd`, `addgroup`, `groupmod`, `whoami`, `groups`). |

## 2) Objetivos, dificultad y tiempo sugerido

| Tema | Objetivos (qué debo saber hacer) | Dificultad | Tiempo |
|---|---|---|---|
| **Interbloqueos** | Explicar las 4 condiciones de Coffman y cómo prevenirlas; evaluar estados seguros/inseguros calculando matrices (`Necesidad`, `Disponible`) y ejecutar el **Algoritmo del Banquero** paso a paso; dibujar e interpretar grafos de asignación. | Alta | 4 h |
| **Gestión de memoria** | Diferenciar fragmentación interna vs externa; simular asignación con *First/Best/Worst Fit* con ventajas/desventajas; explicar el *swapping*. | Media | 2.5 h |
| **Paginación y reemplazo** | Traducción de direcciones virtuales→físicas con MMU y tabla de páginas; aplicar FIFO/LRU/Clock/Óptimo para minimizar fallos de página. | Alta | 3.5 h |
| **Segmentación** | Diferencias paginación (tamaño fijo) vs segmentación (variable, lógica); traducción en segmentación paginada. | Media | 2 h |
| **Gestión de usuarios (Linux)** | Sintaxis y función de comandos de usuarios (`adduser`, `deluser`, `passwd`, `usermod`) y grupos (`addgroup`, `groupmod`, `gpasswd`). | Baja | 2 h |

## 3) Cronograma de 7 días (~2–2.5 h/día)

- **Día 1 — Teoría de interbloqueos y grafos:** 4 condiciones de Coffman, prevención, grafos de asignación. Resolver ejercicio de grafos.
- **Día 2 — Evitación y Algoritmo del Banquero:** resolver problemas del algoritmo de seguridad y el Banquero; calcular matriz *Necesidad* y simular asignaciones.
- **Día 3 — Memoria principal y asignación contigua:** fragmentación (interna/externa); simular en papel *First/Best/Worst Fit*; repasar *swapping*.
- **Día 4 — Memoria virtual y paginación:** marcos vs páginas; traducción de direcciones virtuales→físicas.
- **Día 5 — Reemplazo y segmentación:** practicar FIFO, LRU, Clock, Óptimo; teoría de segmentación y segmentación paginada.
- **Día 6 — Linux y laboratorio:** comandos de gestión de usuarios/grupos (`adduser`, `usermod`, `deluser`, `groups`, …).
- **Día 7 — Repaso y autoevaluación:** simulacro — un ejercicio del Banquero, una simulación de reemplazo (LRU), un *Best Fit*, y la secuencia de comandos para crear un usuario y meterlo en un grupo.

## 4) Imprescindibles para memorizar

**Definiciones centrales**
- **Condiciones de Coffman:** deben cumplirse simultáneamente (1) exclusión mutua, (2) retención y espera, (3) no expropiación, (4) espera circular.
- **Fragmentación interna:** espacio desperdiciado *dentro* de una partición asignada.
- **Fragmentación externa:** huecos libres pequeños y dispersos no contiguos, aunque sumen lo necesario.
- **Paginación:** memoria física en *marcos* de tamaño fijo; proceso en *páginas* del mismo tamaño.
- **Segmentación:** bloques lógicos de tamaño *variable* (segmentos) según la estructura del programa.
- **Fallo de página:** se pide una página que no está en RAM y hay que traerla de disco; los algoritmos de reemplazo lo minimizan.

**Lógica del Algoritmo del Banquero**
- Matriz **Necesidad** = `Max − Asignación`.
- **Algoritmo de seguridad:** si `Necesidad[i] ≤ Disponible`, entonces `Trabajo = Trabajo + Asignación[i]` y el proceso termina.

**Fórmulas de reemplazo (de la S8)**
- Tasa de fallos = (Nº de fallos de página / Total de referencias) × 100%.
- Tiempo efectivo de acceso = (1 − p) × Tiempo_RAM + p × Tiempo_Disco.

**Algoritmos a simular**
- Ubicación: *First Fit* (primer hueco suficiente), *Best Fit* (hueco más pequeño que alcance), *Worst Fit* (hueco más grande).
- Reemplazo: **FIFO** (más antigua), **LRU** (más tiempo sin usarse), **Clock** (segunda oportunidad con bit de uso), **Óptimo** (la que no se usará por más tiempo — teórico).

## 5) Top 10 temas más probables en el examen

1. **Algoritmo del Banquero y estado seguro** (práctico — ejercicio central de la S6).
2. **Las 4 condiciones de Coffman** (teoría más repetida; saber prevenir negando una).
3. **Algoritmos de reemplazo de páginas** (FIFO/LRU/Clock/Óptimo — simular dada una cadena de referencias).
4. **Asignación contigua First/Best/Worst Fit** (ubicar procesos y calcular fragmentación).
5. **Paginación vs segmentación** (por qué una causa fragmentación interna y la otra externa).
6. **Fragmentación interna vs externa.**
7. **Comandos de gestión de usuarios/grupos en Linux** (crear usuario, agregarlo a grupo, contraseña: `adduser`, `usermod -aG`, `passwd`, `groups`).
8. **Grafo de asignación de recursos** (construirlo; los ciclos causan el bloqueo).
9. **Concepto de memoria virtual.**
10. **Swapping** (qué es; `swapon`, `swapoff`, `vmstat`).
