---
universidad: UTP
curso: Sistemas Operativos
tema: Gestión de la memoria — RAM, memoria virtual, intercambio, paginación, segmentación, swapping
semana: 7
sesion: 1
tipo_documento: capítulo de libro / lectura
paginas: 11
fuente_pdf: S07_s1 Material-Gestionde la memoria.pdf
---

# Gestión de la Memoria (Capítulo 3)

**En esta unidad aprenderemos a:** interpretar y diferenciar las técnicas de gestión de memoria.
**Y estudiaremos:** la memoria RAM y su estructura; la forma de almacenar los procesos en memoria.

## 1. Memoria RAM y memoria virtual
La memoria central o principal es limitada y, en grandes sistemas, insuficiente. Al principio se usaron técnicas como dividir el programa en **capas**: cada capa se cargaba en memoria según fuera necesario, accediendo al disco para traer la siguiente.

**Fotheringam** diseñó en 1961 una técnica revolucionaria, la **memoria virtual**: mantener en memoria solo la parte del programa que se está ejecutando y dejar el resto en disco. Considera el espacio libre de disco como si fuese RAM. Si se necesita una parte que está en disco, pasa a RAM (y la que estaba puede volver a disco). Para sistemas Microsoft se recomienda asignar 2,5 % de la RAM en disco para memoria virtual (5 % máximo). En Windows hay un problema de **fragmentación** del archivo de intercambio que en Linux no ocurre.

**[FIGURA 3.1 — Gestión de memoria virtual]** Cadena de bloques conectados bidireccionalmente: **Memoria Virtual** ↔ Controlador de Memoria Virtual ↔ **Memoria Principal** ↔ Controlador de Memoria RAM ↔ **Memoria Caché** ↔ **UCP** (CPU). Muestra la jerarquía desde el disco (memoria virtual) hasta la CPU.

## 2. Intercambio
La parte del SO que administra la memoria es el **administrador de memoria**: lleva registro de las partes usadas y libres, reserva espacio para nuevos procesos y libera el de los que finalizan. También gestiona el intercambio de datos entre memoria y disco cuando los procesos no caben de una vez.

- **Zona de intercambio (vocabulario):** zona del disco duro usada para almacenar procesos que actualmente no están en ejecución, y así dejar RAM libre para los que sí.
- Para gestionar memoria en multitarea, esta se divide en **particiones fijas**; el **planificador** considera los requerimientos de cada proceso (almacenados en el **BCP**) y las particiones disponibles.
- **Dificultad de particiones fijas:** elegir bien los tamaños; mala elección → desaprovechamiento o **fragmentación**:
  - **Fragmentación interna:** la parte de la memoria que no se usa está *dentro* de una partición asignada.
  - **Fragmentación externa:** una partición disponible no se emplea porque es muy pequeña para los procesos que esperan.
- Con procesos dinámicos no hay particiones fijas adecuadas → **particiones variables** (registro de particiones libres/ocupadas). Sigue habiendo fragmentación externa; una solución es permitir memoria no contigua mediante **paginación**, con traducción de direcciones lógicas a físicas vía **tabla de páginas** (cuidar su tamaño y el tiempo de asignación).

**[FIGURA 3.2 — Gestión básica de memoria]** Dos esquemas de un mapa de RAM (de 0 KB a Máximo KB): a la izquierda, SO (Monitor) + procesos + programas de usuario; a la derecha, SO (Monitor) + único proceso de usuario + controladores de dispositivos.

**[FIGURA 3.3 — Gestión de particiones]** Ejemplo con RAM de 4 MB dividida en particiones fijas de 512, 1024 y 2048 KB (más los primeros 512 KB para el SO).
- **Técnica A:** varias colas de procesos (una por tamaño de partición). P1=350KB, P2=50KB, P3=400KB van a la cola de su tamaño.
- **Técnica B:** una sola cola; los procesos (P1=900KB, P2=350KB, P3=3075KB) se alojan en la primera partición libre que los acomode.

## 3. Paginación, segmentación y swapping
Permiten ejecutar programas de tamaño superior a la RAM usando el disco como "ampliación". Ventaja: ejecutar cualquier programa; inconveniente: pérdida de rendimiento.

### 3.1 Paginación
Divide la RAM en zonas iguales llamadas **frames** (marcos) y los programas en partes del mismo tamaño llamadas **páginas**. El SO busca frames libres; el tamaño del frame lo define el hardware. Las páginas de un proceso pueden ubicarse en frames **no contiguos**.
- **Tabla de páginas:** la UCP asigna las direcciones físicas de los frames a las páginas del programa.
- **Vocabulario — marco de página:** división de la memoria en zonas del mismo tamaño para intercambiar procesos con el almacenamiento.
- Ejemplo histórico **DOS / memoria expandida**: divide la memoria extendida en páginas de 64 KB.
- La traducción de direcciones lógicas a físicas la realiza la **MMU (Memory Management Unit)**.

**[FIGURA 3.4 — Paginación]** Ejemplo: la UCP genera la **dirección lógica `003 7FF`** (campo 1 = página 003, campo 2 = desplazamiento 7FF). En la **tabla de páginas**, la entrada 003 apunta al frame **102**. Resultado: **dirección física `102 7FF`** (frame 102 + desplazamiento 7FF). Se muestra también el mapa de RAM con el frame 102 resaltado.
> **Ejemplo (texto):** un proceso de 5 páginas (000–004); cada dirección lógica tiene dos campos (página y desplazamiento). Para `003 7FF` se accede a la página 003 → frame 102 → desplazarse 7FF → dirección física `102 7FF`.

### 3.2 Segmentación
Técnica similar a la paginación pero con bloques de **tamaño variable** (segmentos), de 0 a un máximo. Útil para ubicar estructuras de datos (tablas, pilas/**stacks**) que crecen o menguan. **Diferencia clave:** la paginación usa bloques de tamaño **fijo**; la segmentación, **variable**. La segmentación no produce fragmentación interna pero **sí externa**.

### 3.3 Swapping
Técnica similar a la memoria virtual. Cuando varios usuarios ejecutan procesos, el SO los carga en RAM; según el estado, la memoria de un proceso pasa a la zona de **swap** (**swap-out**). Si el usuario vuelve a solicitar el proceso, se hace **swap-in** (de swap a RAM). La zona de swap es un espacio físico del disco; se recomienda ~20 % del disco o el doble de la RAM.
- **Diferencia memoria virtual vs swapping:** en memoria virtual el disco puede llenarse y dificultar la gestión (comparte espacio con SO/apps/datos); en swapping la zona está siempre reservada y disponible, normalmente en un dispositivo físico distinto.

## 4. Programas según el uso de memoria
- **A. Reubicables:** una vez en RAM pueden cambiar de posición porque su espacio puede ser necesario para otro proceso.
- **B. Reentrantes:** si no se están ejecutando, dejan la memoria libre para otros; se gestionan vía memoria virtual.
- **C. Residentes:** permanecen en memoria hasta apagar el equipo; no cambian de ubicación (antivirus, monitores, "centinelas").
- **D. Reutilizables:** usados por varios usuarios a la vez en memoria, mejorando el aprovechamiento.

---

## Síntesis (mapa conceptual)
**[DIAGRAMA — Mapa mental "Síntesis"]** Ramas principales:
- **Procesos:** procesos y flujos (un proceso referencia un programa en ejecución); hilos/hebras (punto de ejecución de un proceso); estados (ejecución, espera/pausado, bloqueado); transición; **Bloque de control de procesos** (estado actual, identificador, prioridad, ubicación en memoria, recursos utilizados, información de control); algoritmos de planificación (**Round Robin** = rueda que asigna tiempos de CPU rotativos; **FIFO/FCFS** = por orden de llegada).
- **Memoria:** intercambio de memoria → **Paginación** (páginas del mismo tamaño para RAM y programas), **Segmentación** (páginas de diferente tamaño), **Swapping** (intercambio en zona de disco exclusiva); tipos de programas (reubicables, reentrantes, residentes, reutilizables).
- **Periféricos:** tipos (bloque / carácter); comunicación (interfaz texto / gráfica); clasificación (entrada / salida / entrada-salida).
- **Información:** tipos de archivos (regulares, directorios, especiales).

---

## Test de repaso (con soluciones)
1. Proceso bloqueado que pasa a ejecución: **c)** No directamente, debe pasar por en espera o preparado.
2. Un proceso está preparado para ser ejecutado: **a)** si espera el turno para usar su intervalo de CPU.
3. Trabajos que llegan a una impresora (multiusuario): **d)** son correctas a, b y c (spool, FIFO, prioridades).
4. Técnica de swapping, el programa en ejecución que pasa a la zona de swap: **b)** Swap-out.
5. La paginación se diferencia de la segmentación en: **d)** Todas son ciertas.
6. La paginación divide la memoria en zonas denominadas: **b)** Frames.
7. Los directorios son: **c)** archivos que contienen referencias a otros archivos regulares o directorios.
8. ¿Cuál afirmación es falsa?: **a)** "La paginación utiliza segmentos de tamaño fijo y la segmentación marcos de tamaño variable" (es falsa: están invertidos).
9. El cambio de contexto puede producirse: **d)** son correctas a y b (entre procesos y entre hilos).
10. Los programas residentes: **b)** una vez cargados permanecen en memoria hasta apagar el equipo.

> **Soluciones oficiales:** 1:c, 2:a, 3:d, 4:b, 5:d, 6:b, 7:c, 8:a, 9:d, 10:b.

---

## Comprueba tu aprendizaje (actividades)
1. Rellenar tabla de estados de procesos y transiciones (P1 Ejecución→Bloqueado; P2 Pausado, transición C; P3 Ejecución→Pausado; P4 Bloqueado, transición D), justificando con las transiciones A/B/C/D.
2. ¿Qué contiene el BCP (Bloque de Control de Procesos)?
3. ¿Todo proceso tiene una entrada en el BCP?
4. ¿Qué algoritmo es mejor para ejecución de procesos en un sistema multiusuario?
5. Rellenar tabla: para Paginación / Segmentación / Swapping → división de la memoria, gestión de disco, fragmentación.
6. ¿Cómo se denominan los programas usados por varios usuarios y cargados una sola vez en memoria? (Reutilizables.)
7. Comentar características de: archivos regulares, directorios, archivos especiales.
8. Rellenar tabla de periféricos (escáner, pizarra digital, HDVD/DVD, impresora, discos duros, monitor, router) clasificando: entrada/salida, interno/externo, rápido/lento.

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras  | 4        | Fig 3.1 memoria virtual, 3.2 gestión básica, 3.3 particiones, 3.4 paginación (003 7FF→102 7FF) |
| Tablas   | 4        | Estados de procesos, gestión de memoria, periféricos, (preguntas con tabla) |
| Diagramas| 1        | Mapa mental de Síntesis |
| Fórmulas | 0        | (dirección = página/segmento + desplazamiento) |
| Código   | 0        | — |
| Ejercicios | 10 + 8 | Test de repaso (con respuestas) + Comprueba tu aprendizaje |
