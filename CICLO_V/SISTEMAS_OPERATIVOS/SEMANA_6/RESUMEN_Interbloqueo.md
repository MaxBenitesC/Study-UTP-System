# Tema 2: El Interbloqueo (Deadlock)
> Sistemas Operativos II

---

## 1. Introducción

El **interbloqueo** (*deadlock*, abrazo mortal, bloqueo mutuo) ocurre cuando un conjunto de procesos queda bloqueado permanentemente porque cada uno espera un recurso que retiene otro proceso del mismo conjunto.

> _"Un conjunto de procesos se encuentra en estado de interbloqueo cuando cada uno de ellos espera un suceso que sólo puede originar otro proceso del mismo conjunto."_

---

## 2. Recursos

Los recursos del sistema pueden ser:

| Tipo | Ejemplos |
|------|----------|
| **Físicos** | CPU, memoria, impresoras, cintas |
| **Lógicos** | Ficheros, semáforos, tablas del SO |

### Clases de recursos
- **Expropiable**: se puede quitar sin causar fallo (CPU, memoria).
- **No expropiable**: quitarlo causa fallo (impresora). Los interbloqueos suelen involucrar estos.

### Secuencia de uso de un recurso
1. **Solicitud** → 2. **Utilización** → 3. **Liberación**

---

## 3. Condiciones para que se produzca Interbloqueo

Deben cumplirse **simultáneamente** las cuatro condiciones de Coffman:

| # | Condición | Descripción |
|---|-----------|-------------|
| 1 | **Exclusión mutua** | Cada recurso solo lo usa un proceso a la vez |
| 2 | **Retención y espera** | Un proceso retiene recursos mientras espera otros |
| 3 | **No expropiación** | Los recursos solo se liberan voluntariamente |
| 4 | **Espera circular** | Cadena circular de procesos esperándose mutuamente |

> La condición 4 implica a la 2, por lo que no son completamente independientes.

---

## 4. Modelado del Interbloqueo

Se usa un **grafo de asignación de recursos** G(N, A) bipartito y dirigido:

- **Nodos proceso** (círculos): P = {P1, P2, ..., Pn}
- **Nodos recurso** (rectángulos con puntos): R = {R1, R2, ..., Rn}
- **Arco de solicitud**: Pi → Rj (proceso espera recurso)
- **Arco de asignación**: Rj → Pi (recurso asignado a proceso)

### Regla clave
- **Sin ciclos** → No hay interbloqueo
- **Con ciclo y 1 ejemplar por recurso** → Interbloqueo seguro
- **Con ciclo y varios ejemplares** → Puede o no haber interbloqueo

---

## 5. Métodos para el tratamiento del interbloqueo

| Estrategia | Descripción |
|------------|-------------|
| **Ignorar** (Algoritmo del Avestruz) | Asumir que ocurre tan raramente que no vale la pena tratarlo (ej: UNIX) |
| **Prevención** | Negar al menos una de las 4 condiciones |
| **Evitación** | Asignar recursos solo si el estado resultante es seguro |
| **Detección + Recuperación** | Permitir interbloqueos y luego romperlos |

---

## 6. Prevención del Interbloqueo

Negar al menos una condición de Coffman:

### 6.1 Exclusión mutua
- Usar **spooling** para simular compartición (ej: cola de impresión).
- No aplicable a todos los recursos.

### 6.2 Retención y espera
- Variante 1: el proceso pide **todos** los recursos antes de iniciar.
- Variante 2: libera **todos** sus recursos antes de pedir uno nuevo.
- Problema: baja utilización de recursos, posible **inanición**.

### 6.3 No expropiación
- Permitir que el SO quite recursos a procesos bloqueados.
- Solo viable cuando el estado puede guardarse y restaurarse (CPU, memoria).

### 6.4 Espera circular
- Asignar un **orden numérico** a los tipos de recursos.
- Los procesos solo pueden pedir recursos en orden **estrictamente creciente**.
- Elimina ciclos, verificable en tiempo de compilación.

---

## 7. Evitación del Interbloqueo

Conceder peticiones **solo si** el estado resultante es **seguro**.

### 7.1 Estado seguro vs inseguro

```
[ Estados seguros ] ⊃ [ Estados inseguros ] ⊃ [ Estados de interbloqueo ]
```

- **Estado seguro**: existe una secuencia \<P1, P2, ..., Pn\> en que todos los procesos pueden terminar.
- **Estado inseguro**: no garantiza terminar sin interbloqueo, pero no implica que ya haya uno.

### 7.2 Algoritmo del Banquero (Dijkstra, 1965)

Estructuras de datos necesarias (n procesos, m recursos):

| Estructura | Descripción |
|-----------|-------------|
| `Disponible[m]` | Unidades libres de cada recurso |
| `Max[n][m]` | Máxima demanda de cada proceso |
| `Asignación[n][m]` | Recursos actualmente asignados |
| `Necesidad[n][m]` | `Max - Asignación` |

**Pasos al recibir Solicitud_i:**
1. Si `Solicitud_i > Necesidad_i` → Error
2. Si `Solicitud_i > Disponible` → Esperar
3. Simular asignación temporalmente
4. Ejecutar **algoritmo de seguridad**:
   - Si seguro → confirmar asignación
   - Si inseguro → revertir y hacer esperar

**Algoritmo de seguridad:**
1. `Trabajo = Disponible`, `Fin[i] = falso` para todo i
2. Buscar Pi con `Fin[i]=falso` y `Necesidad_i ≤ Trabajo`
3. `Trabajo += Asignación_i`, `Fin[i] = verdadero` → repetir paso 2
4. Si todos `Fin[i] = verdadero` → estado seguro

**Limitaciones:** Los procesos rara vez conocen sus necesidades máximas de antemano; el número de procesos no es fijo.

### 7.3 Ejemplo resuelto (ejercicio de clase)

**Datos:**

| Vector de Recursos | R1 | R2 | R3 |
|-------------------|----|----|-----|
| Total             | 9  | 3  | 6  |

| Demanda (Max) | R1 | R2 | R3 |   | Asignación | R1 | R2 | R3 |
|---------------|----|----|-----|---|------------|----|----|-----|
| P1            | 3  | 2  | 2  |   | P1         | 1  | 0  | 0  |
| P2            | 6  | 1  | 3  |   | P2         | 6  | 1  | 2  |
| P3            | 3  | 1  | 4  |   | P3         | 2  | 1  | 1  |
| P4            | 4  | 2  | 2  |   | P4         | 0  | 0  | 2  |

**Paso 1 — calcular Necesidad = Demanda − Asignación:**

| Necesidad | R1 | R2 | R3 |
|-----------|----|----|-----|
| P1        | 2  | 2  | 2  |
| P2        | 0  | 0  | 1  |
| P3        | 1  | 0  | 3  |
| P4        | 4  | 2  | 0  |

**Paso 2 — calcular Disponible = Recursos − Σ Asignación:**

Σ Asignación = R1:9, R2:2, R3:5 → **Disponible = [0, 1, 1]**

**Paso 3 — algoritmo de seguridad (Trabajo inicial = [0, 1, 1]):**

| Iteración | Trabajo antes | Pi  | Necesidad_i | ¿Cabe? | Trabajo después |
|-----------|--------------|-----|-------------|--------|----------------|
| 1         | [0, 1, 1]    | P2  | [0, 0, 1]   | ✓      | [6, 2, 3]      |
| 2         | [6, 2, 3]    | P1  | [2, 2, 2]   | ✓      | [7, 2, 3]      |
| 3         | [7, 2, 3]    | P3  | [1, 0, 3]   | ✓      | [9, 3, 4]      |
| 4         | [9, 3, 4]    | P4  | [4, 2, 0]   | ✓      | [9, 3, 6]      |

Todos `Fin[i] = verdadero` → **ESTADO SEGURO**

**Secuencia segura: P2 → P1 → P3 → P4**

---

## 8. Detección del Interbloqueo

El sistema concede recursos libremente y luego **comprueba periódicamente** si hay interbloqueo.

### Algoritmo de detección
Similar al de seguridad, pero usa la matriz `Solicitud` (peticiones pendientes) en lugar de `Necesidad`:

1. `Trabajo = Disponible`; `Fin[i] = falso` si `Asignación_i ≠ 0`, sino `verdadero`
2. Buscar Pi con `Fin[i]=falso` y `Solicitud_i ≤ Trabajo`
3. `Trabajo += Asignación_i`, `Fin[i] = verdadero` → volver al paso 2
4. Si `Fin[i] = falso` → Pi está interbloqueado

### Frecuencia de detección
| Frecuencia alta | Frecuencia baja |
|----------------|----------------|
| Detecta rápido, mayor coste de CPU | Menor coste, interbloqueos prolongados |

---

## 9. Recuperación del Interbloqueo

### 9.1 Terminación de procesos
- **Abortar todos** los procesos interbloqueados → costoso, se pierde trabajo.
- **Abortar uno a uno** hasta romper el ciclo → requiere re-ejecutar detección tras cada aborto.

Factores para elegir la víctima:
1. Prioridad del proceso
2. Tiempo ya ejecutado / tiempo restante
3. Recursos que usa y necesita
4. Número de procesos a terminar
5. Tipo: interactivo vs por lotes

### 9.2 Expropiación de recursos
Quitar recursos a procesos y reasignarlos. Tres problemas a resolver:

| Aspecto | Descripción |
|---------|-------------|
| **Selección de víctima** | Minimizar costo de expropiación |
| **Rollback** | Revertir proceso a un estado seguro anterior |
| **Postergación indefinida** | Evitar elegir siempre al mismo proceso (inanición) |

---

## 10. Estrategia Combinada

Ningún método es óptimo por sí solo. Se dividen los recursos en **clases** y se aplica la estrategia más adecuada a cada una:

| Clase | Estrategia recomendada |
|-------|----------------------|
| **Recursos internos** (tabla de procesos) | Prevención por ordenación |
| **Memoria principal** | Prevención por expropiación |
| **Recursos de procesos** (impresoras, ficheros) | Evitación |
| **Espacio de intercambio** | Prevención (adquisición anticipada) |

---

## Resumen comparativo de estrategias

| Estrategia | Ventaja | Desventaja |
|------------|---------|------------|
| **Ignorar** | Sin sobrecarga | Riesgo de interbloqueos |
| **Prevención** | Garantiza no interbloqueo | Baja utilización, inanición posible |
| **Evitación** | Mayor concurrencia que prevención | Necesita conocer necesidades máximas |
| **Detección + Recuperación** | Mayor concurrencia | Sobrecarga al recuperar, pérdida de trabajo |

---

## ¿Qué entendí yo de todo esto?

Imaginemos que hay dos personas en una cocina pequeña: una tiene el cuchillo y necesita la tabla de picar, y la otra tiene la tabla y necesita el cuchillo. Ninguna suelta lo que tiene esperando que la otra lo haga primero. Eso es exactamente un interbloqueo: nadie avanza, nadie cede, y todo se paraliza.

Eso mismo pasa en un sistema operativo cuando varios procesos compiten por recursos. El sistema tiene que manejar esa situación de alguna forma, y básicamente tiene cuatro caminos:

**Ignorarlo.** Puede sonar irresponsable, pero tiene sentido cuando los interbloqueos son tan raros que arreglarlo costaría más de lo que el problema vale. Es como no instalar un seguro especial en una puerta porque en 20 años nadie la ha forzado. UNIX lo hace así.

**Prevenirlo.** Aquí la idea es atacar las causas antes de que el problema exista. Si eliminas alguna de las cuatro condiciones necesarias, el interbloqueo simplemente no puede ocurrir. Por ejemplo, si obligas a que cada proceso pida todos sus recursos al inicio, ya no puede estar reteniendo unos mientras espera otros. El problema es que eso desperdicia recursos, porque un proceso reserva cosas que quizás no use hasta el final.

**Evitarlo.** Esto es más inteligente: el sistema no prohíbe nada de entrada, pero cada vez que un proceso pide un recurso, el sistema simula mentalmente "¿qué pasaría si le doy esto?" y solo lo concede si la respuesta es segura. El Algoritmo del Banquero funciona así, como un banco que antes de prestarte dinero verifica que, aun dándotelo, le quedaría suficiente para responder a todos sus otros clientes. El problema es que en la realidad los procesos rara vez saben de antemano cuánto van a necesitar.

**Detectarlo y recuperarse.** El sistema deja que los procesos pidan lo que quieran y de vez en cuando revisa si cayó en interbloqueo. Si detecta uno, tiene que romperlo: o mata procesos (con todo el trabajo perdido que eso implica) o les quita recursos a la fuerza y los regresa a un estado anterior. Es como dejar que se forme un embotellamiento de tráfico y luego mandar grúas a sacar autos.

Lo más importante que me llevo es que **no existe una solución perfecta**. Cada estrategia tiene un precio: rendimiento, complejidad, desperdicio de recursos o pérdida de trabajo. Por eso los sistemas reales combinan varias según el tipo de recurso que manejan. No es un problema que se resuelve de una sola forma; se administra.

Y la razón por la que los interbloqueos son tan complicados es que las cuatro condiciones que los generan son, por separado, cosas completamente normales y necesarias en cualquier sistema: que los recursos sean exclusivos, que los procesos pidan más de uno, que nadie quite recursos a la fuerza, y que haya varios procesos corriendo al mismo tiempo. El interbloqueo no es un error de programación: es una consecuencia natural de que muchos procesos compartan recursos limitados.
