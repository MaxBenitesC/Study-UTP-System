---
universidad: UTP
curso: Sistemas Operativos
tema: Bloqueos mutuos (interbloqueos) — modelo, caracterización, tratamiento
semana: 6
tipo_documento: lectura/apunte
paginas: 2
fuente_pdf: Interbloqueo.pdf
---

# Sistemas Operativos — S06: Bloqueos Mutuos

## Modelo del sistema
El sistema tiene un número finito de recursos que se distribuyen entre varios procesos que compiten por ellos. Los recursos pueden ser:
- **Físicos:** CPU, memoria, dispositivos de E/S, etc.
- **Lógicos:** archivos, tablas del sistema, semáforos, etc.
- **Apropiativos (expropiables):** se pueden tomar del proceso que lo posee sin efectos dañinos.
- **No apropiativos (no expropiables):** no se pueden tomar de su poseedor activo sin provocar un fallo de cálculo.

Secuencia normal de uso de un recurso: **Solicitarlo → Utilizarlo → Liberarlo.**

## Interbloqueos
El **bloqueo mutuo** (interbloqueo, traba mortal, *deadlock* o abrazo mortal) es el **bloqueo permanente de un conjunto de procesos o hilos** en un sistema concurrente que compiten por recursos o se comunican entre ellos. A diferencia de otros problemas de concurrencia, **no existe una solución general** para los interbloqueos.

En multiprogramación, compartir recursos es un objetivo principal del SO; cada proceso mantiene control exclusivo sobre ciertos recursos asignados.

## Caracterización de bloqueos mutuos
En interbloqueo, los procesos nunca terminan de ejecutarse y los recursos quedan ocupados, impidiendo el inicio de otros trabajos. Para que exista interbloqueo deben cumplirse **simultáneamente las cuatro condiciones (de Coffman)**:
- **Exclusión mutua:** solo un proceso puede utilizar un recurso en cada momento.
- **Retención y espera:** el proceso tiene recursos asignados mientras espera otros.
- **No expropiación:** un recurso solo es liberado voluntariamente por el proceso que lo retiene.
- **Espera circular:** existe una lista cerrada de procesos donde cada uno posee al menos un recurso necesitado por el siguiente.

## Grafo de asignación de recursos
Se usa para representar el estado de un sistema de asignación de recursos.
- Los **nodos** representan cada **proceso** (círculo) y cada **recurso** (rectángulo).
- En el interior de un nodo de recurso se muestra **un punto por cada instancia/ejemplar** de ese recurso.
- Muestra qué recursos están asignados y a qué proceso, y qué procesos están bloqueados y por cuáles recursos.

**[FIGURA — Convenciones del grafo]**
- (a) Un proceso **bloqueado** "espera" un recurso: arco que va del proceso (círculo) al recurso (rectángulo con puntos = ejemplares). Ej.: proceso A espera recurso R; recurso con dos ejemplares (dos puntos).
- (b) Un recurso **asignado** a un proceso: arco que va del recurso al proceso. Ej.: recurso S asignado al proceso B.
- (c) Caso con ciclo: procesos D y C y recursos T, U conectados formando un ciclo (asignaciones y esperas cruzadas).

**[FIGURA — Ejemplo de interbloqueo]** Grafo con recursos R1, R2, R3, R4 y procesos P1, P2, P3, P4. Las flechas de asignación/solicitud entre P1, P2, P3 y los recursos R1, R2, R3 forman un **ciclo cerrado**. Conclusión rotulada: **"P1, P2 y P3 están en interbloqueo"** (P4 queda fuera del ciclo).

## Tratamiento del interbloqueo
**Garantizar que nunca ocurran:**
- **Prevención:** diseñar el sistema de modo que nunca se cumpla alguna de las cuatro condiciones.
- **Evitación:** tratar de no caer nunca en un estado de interbloqueo.

**Permitir su aparición y recuperarse:** se necesita un sistema de **detección** y un mecanismo de **recuperación**.

**No tratar el problema:** si hay interbloqueos, el usuario debe intervenir.

### Prevención de bloqueos mutuos
Elimina la aparición de alguna de las cuatro condiciones necesarias. Dos tipos de métodos:
- **Indirectos:** impedir alguna de las tres primeras condiciones.
- **Directos:** evitar el círculo vicioso de espera (la cuarta condición).

**Exclusión mutua:** depende de la naturaleza del recurso; **no siempre se puede eliminar** (p. ej. archivos: múltiples lecturas pero solo un proceso escribe a la vez). Evitar asignar un recurso si no es absolutamente necesario.

**Retener y esperar:** garantizar que un proceso no quede bloqueado reteniendo recursos:
- El proceso pide **todos** sus recursos de una vez (p. ej. antes de ejecutarse). *Efecto negativo:* muchos recursos retenidos pero no usados.
- Un proceso solo puede solicitar recursos cuando **no tiene ninguno** asignado. *Efecto negativo:* puede tener que liberar y volver a pedir.
- En ambos casos puede ocurrir **inanición** (que un proceso nunca se ejecute).

### Recuperación de bloqueos mutuos
Cuando el algoritmo de detección determina un interbloqueo, el sistema recupera automáticamente. Dos opciones para romperlo:
- **Terminación de procesos:** interrumpir uno o más procesos para romper la espera circular.
  - Terminar **todos** los implicados (muy drástico), o terminar **uno** según: prioridad, el que más recursos libere, el que menos tiempo lleve en ejecución.
  - **Rollback** (retroceder la ejecución): complicado, requiere que el programa esté diseñado para retroceder.
- **Apropiación de recursos:** desalojar sucesivamente recursos y reasignarlos hasta romper el ciclo.
  - *Selección de la víctima:* ¿qué recursos y de qué procesos se expropian?
  - *Retroceso:* si expropiamos un recurso, ¿qué hacemos con ese proceso?

### Algoritmos de evasión (evitación) de bloqueos
Los bloqueos pueden evitarse conociendo cierta información antes de asignar recursos. Para cada petición, el sistema verifica si conceder el pedido lleva a un **estado inseguro**; solo satisface el pedido si queda en **estado seguro**. Requiere conocer por adelantado el número y tipo de todos los recursos en existencia, disponibles y requeridos. Algoritmos:
- **Algoritmo del Banquero** (introducido por Dijkstra).
- Algoritmo de grafo de asignación de recursos.
- Algoritmo de Seguridad.
- Algoritmo de solicitud de recursos.

## Conclusiones
- Los SO usan métodos de **prevención y evitación** para evitar los interbloqueos.
- Un sistema con interbloqueo necesita la **intervención del usuario** para salir de esa situación.

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras  | 2        | Grafos de asignación de recursos (convenciones y ejemplo de ciclo P1-P2-P3) |
| Tablas   | 0        | — |
| Fórmulas | 0        | — |
| Código   | 0        | — |
| Diagramas| 2        | Grafos de asignación de recursos |
| Ejercicios | 0      | — |
