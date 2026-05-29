# 📘 Guía de Estudio — Examen de Sistemas Operativos (Semanas 6–9)

> Apuntes de las clases con tu profe. Pensado para leer en el celular. Incluye teoría, ejemplos resueltos y código Java de los algoritmos de reemplazo.

---

# CLASE 1 — Interbloqueos (Semana 6)

## ¿Qué es un interbloqueo (deadlock)?
Bloqueo **permanente** de un conjunto de procesos donde cada uno **retiene** un recurso y **espera** otro que tiene otro proceso del grupo. Analogía: 4 autos trabados en un cruce, cada uno espera al de al lado.

## Las 4 condiciones de Coffman ⭐ (memorizar)
Deben cumplirse **las 4 a la vez** para que haya deadlock:
1. **Exclusión mutua** — el recurso solo lo usa un proceso a la vez.
2. **Retención y espera** — retiene recursos mientras espera otros.
3. **No expropiación** — el recurso solo se libera voluntariamente.
4. **Espera circular** — cadena cerrada P1→P2→…→P1.

> **Romper 1 sola = prevención.** La más efectiva: **ordenar los recursos** (rompe la espera circular).

## Estrategias de manejo
- **Prevención:** diseñar para que no se cumpla una condición.
- **Evitación:** Algoritmo del Banquero (mira al futuro).
- **Detección + Recuperación:** dejar que ocurra, detectarlo (grafo/ciclos) y romperlo (matar proceso, expropiar, rollback).
- **Avestruz:** ignorarlo.

## 🏦 Algoritmo del Banquero (evitación) ⭐⭐
Solo concede un recurso si el sistema queda en **estado seguro** (existe un orden en que TODOS terminan).

**Matrices:**
- **Max:** lo máximo que pediría cada proceso.
- **Asignación:** lo que tiene ahora.
- **Necesidad = Max − Asignación** ← fórmula clave.
- **Disponible:** lo libre del sistema.

**Algoritmo de seguridad:**
1. `Trabajo = Disponible`.
2. Busca un proceso no terminado con `Necesidad ≤ Trabajo`.
3. Corre y termina → `Trabajo = Trabajo + su Asignación` (devuelve lo suyo).
4. Repite. Si terminan todos → **SEGURO** (orden = secuencia segura). Si ninguno cabe → **INSEGURO**.

**Ejemplo resuelto** (1 recurso, 10 instancias):

| Proceso | Max | Asignación | Necesidad |
|---|---|---|---|
| P1 | 7 | 2 | 5 |
| P2 | 4 | 1 | 3 |
| P3 | 9 | 3 | 6 |

Disponible = 10 − (2+1+3) = **4**.
- Trabajo=4 → P2 (Nec 3 ≤ 4) ✓ → Trabajo = 4+1 = **5**
- Trabajo=5 → P1 (Nec 5 ≤ 5) ✓ → Trabajo = 5+2 = **7**
- Trabajo=7 → P3 (Nec 6 ≤ 7) ✓ → Trabajo = 7+3 = **10**
- ✅ **SEGURO** — Secuencia segura: **P2 → P1 → P3**

**Solicitud de recursos** (variante de examen): cuando Pi pide algo:
1. ¿`Request ≤ Necesidad`? Si no → error.
2. ¿`Request ≤ Disponible`? Si no → espera.
3. Simula la entrega (resta Disponible, suma Asignación, resta Necesidad) y corre seguridad.
4. Seguro → concede; Inseguro → **niega** y revierte.

> 🔑 Lección clave: el sistema puede **negar** aunque TENGA los recursos, si concederlos lo deja en estado inseguro. (Ej.: si P1 pide 2 con Disponible=4, queda Trabajo=2 y nadie más cabe → inseguro → se niega.)

## Grafo de asignación de recursos (detección)
- Proceso = círculo, Recurso = rectángulo (con puntos = instancias).
- Flecha **Recurso → Proceso** = asignación. Flecha **Proceso → Recurso** = solicitud.
- Un **ciclo** = interbloqueo. (Ej. TA02: D→E→G→D están en deadlock; B bloqueado indirectamente.)

---

# CLASE 2 — Gestión de Memoria (Semana 7)

## Fragmentación ⭐ (no confundir)
| | ¿Dónde se desperdicia? | ¿Cuándo? |
|---|---|---|
| **Interna** | *Dentro* del bloque asignado | Particiones **fijas** |
| **Externa** | *Entre* bloques (huecos dispersos) | Particiones **variables** |

- **Interna** (estacionamiento: cajón de bus, auto chico): partición 100 KB con proceso 80 KB → **20 KB perdidos dentro**.
  - Ejemplo: 100−80=20, 100−95=5, 100−60=40 → **total 65 KB desperdiciados**.
- **Externa:** $\text{Frag. Externa} = \text{Memoria Total Libre} - \text{Mayor Bloque Contiguo Libre}$.
  - Solución: **compactación** (juntar todo el libre; cuesta CPU y detiene el sistema).

## Algoritmos de ubicación ⭐ (todos O(n))
- **First Fit:** el **primer** hueco donde quepa (rápido).
- **Best Fit:** el hueco **más pequeño** donde quepa (menos sobrante, pero deja fragmentos minúsculos).
- **Worst Fit:** el hueco **más grande** (deja sobrante grande reutilizable).

**Ejemplo resuelto** — huecos `[150][350][200][450][120]`, proceso de **180 KB**:
- First Fit → **350** (el primero ≥180).
- Best Fit → de {350,200,450} el más chico = **200**.
- Worst Fit → el más grande = **450**.

---

# CLASE 3 — Memoria Virtual y Paginación (Semana 8)

## Memoria virtual
Ejecutar programas **más grandes que la RAM** usando el disco como extensión. Solo lo que se usa está en RAM; lo demás en disco. Si se pide algo que no está → **fallo de página** → se trae de disco.

## Paginación ⭐
- **Página** = bloque de memoria lógica. **Marco (frame)** = bloque de RAM, del mismo tamaño.
- **Tabla de páginas** mapea página → marco. Las páginas pueden estar en marcos **no contiguos** (elimina fragmentación externa).
- **MMU** = hardware que traduce dirección lógica → física. **TLB** = caché de traducción.

**Traducción de direcciones** — la dirección lógica es `[ p (nº página) | d (desplazamiento) ]`:
$$\text{Dirección Física} = (\text{Nº de Marco} \times \text{Tamaño de Página}) + \text{Desplazamiento}$$
Pasos: (1) busca `p` en la tabla → marco `f`; (2) el desplazamiento `d` NO cambia; (3) aplica la fórmula.

**Ejemplo resuelto** — tamaño de página = 1000 bytes; tabla: pág0→marco5, pág1→marco2, pág2→marco8, pág3→marco4:
- Pág 2, desp 350 → (8×1000)+350 = **8350**
- Pág 1, desp 600 → (2×1000)+600 = **2600**
- Pág 3, desp 120 → (4×1000)+120 = **4120**

**Tipos de paginación:** Simple (1 tabla) · Multinivel (varias tablas, ahorra espacio) · Invertida (1 entrada por marco; PowerPC/IA-64).

## Algoritmos de reemplazo de página ⭐⭐
Cuando la RAM está llena y llega una página nueva, ¿cuál sacar?

| Algoritmo | Regla | Complejidad |
|---|---|---|
| **FIFO** | Saca la **más antigua** en memoria | O(1) · puede sufrir Anomalía de Belady |
| **LRU** | Saca la que **no se usa hace más tiempo** | O(1)–O(n) · buen rendimiento |
| **Clock** | Aproxima LRU con **bit de referencia** (segunda oportunidad) | O(1) · simple y bueno |
| **Óptimo** | Saca la que se usará **más tarde en el futuro** | Teórico (imposible) · benchmark |

**Métricas:**
$$\text{Tasa de Fallos} = \frac{\text{Nº Fallos}}{\text{Total Referencias}} \times 100\%$$
$$\text{Tiempo Efectivo de Acceso} = (1-p)\cdot T_{RAM} + p\cdot T_{Disco}$$

### 💻 Código Java — Algoritmos de reemplazo
Archivo: `SEMANA_8/AlgoritmosReemplazo.java`. Resultado con la cadena clásica `{7,0,1,2,0,3,0,4,2,3,0,3,2,1,2,0,1,7,0,1}` y **3 marcos**: **FIFO=15, LRU=12, Óptimo=9, Clock=14**.

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AlgoritmosReemplazo {

    // FIFO: reemplaza la pagina mas antigua
    static int fifo(int[] ref, int numMarcos) {
        LinkedList<Integer> marcos = new LinkedList<>();
        int fallos = 0;
        for (int pagina : ref) {
            if (!marcos.contains(pagina)) {
                fallos++;
                if (marcos.size() == numMarcos) marcos.removeFirst();
                marcos.addLast(pagina);
            }
        }
        return fallos;
    }

    // LRU: reemplaza la que no se usa hace mas tiempo
    static int lru(int[] ref, int numMarcos) {
        List<Integer> marcos = new ArrayList<>();
        int fallos = 0;
        for (int pagina : ref) {
            if (marcos.contains(pagina)) {
                marcos.remove((Integer) pagina);
                marcos.add(pagina);
            } else {
                fallos++;
                if (marcos.size() == numMarcos) marcos.remove(0);
                marcos.add(pagina);
            }
        }
        return fallos;
    }

    // OPTIMO: reemplaza la que se usara mas tarde en el futuro
    static int optimo(int[] ref, int numMarcos) {
        List<Integer> marcos = new ArrayList<>();
        int fallos = 0;
        for (int i = 0; i < ref.length; i++) {
            int pagina = ref[i];
            if (!marcos.contains(pagina)) {
                fallos++;
                if (marcos.size() == numMarcos) {
                    int idxVictima = 0, masLejano = -1;
                    for (int m = 0; m < marcos.size(); m++) {
                        int proximoUso = Integer.MAX_VALUE;
                        for (int j = i + 1; j < ref.length; j++) {
                            if (ref[j] == marcos.get(m)) { proximoUso = j; break; }
                        }
                        if (proximoUso > masLejano) { masLejano = proximoUso; idxVictima = m; }
                    }
                    marcos.remove(idxVictima);
                }
                marcos.add(pagina);
            }
        }
        return fallos;
    }

    // CLOCK (segunda oportunidad): bit de referencia + puntero circular
    static int clock(int[] ref, int numMarcos) {
        Integer[] marcos = new Integer[numMarcos];
        int[] bit = new int[numMarcos];
        int puntero = 0, fallos = 0;
        for (int pagina : ref) {
            boolean acierto = false;
            for (int k = 0; k < numMarcos; k++) {
                if (marcos[k] != null && marcos[k] == pagina) { bit[k] = 1; acierto = true; break; }
            }
            if (acierto) continue;
            fallos++;
            while (true) {
                if (marcos[puntero] == null || bit[puntero] == 0) {
                    marcos[puntero] = pagina; bit[puntero] = 1;
                    puntero = (puntero + 1) % numMarcos;
                    break;
                } else {
                    bit[puntero] = 0;
                    puntero = (puntero + 1) % numMarcos;
                }
            }
        }
        return fallos;
    }

    public static void main(String[] args) {
        int[] ref = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int numMarcos = 3;
        System.out.println("FIFO   -> " + fifo(ref, numMarcos));
        System.out.println("LRU    -> " + lru(ref, numMarcos));
        System.out.println("Optimo -> " + optimo(ref, numMarcos));
        System.out.println("Clock  -> " + clock(ref, numMarcos));
    }
}
```
**Cómo correrlo:** `javac AlgoritmosReemplazo.java` y luego `java AlgoritmosReemplazo`.

### 👁️ Modo visual (sin código) — simulación paso a paso
Misma lógica, pero "dibujada" como en el examen. Cadena corta: **`7, 0, 1, 2, 0, 3, 0, 4`** con **3 marcos**. (`✗` = fallo de página, `✓` = acierto). Así es como debes resolverlo a mano.

**FIFO** — sale siempre la **más antigua** (marcos en orden antiguo→nuevo):
| Paso | Pide | M1 | M2 | M3 | ¿? | Sale |
|---|---|---|---|---|---|---|
| 1 | 7 | 7 | · | · | ✗ | — |
| 2 | 0 | 7 | 0 | · | ✗ | — |
| 3 | 1 | 7 | 0 | 1 | ✗ | — |
| 4 | 2 | 0 | 1 | 2 | ✗ | 7 |
| 5 | 0 | 0 | 1 | 2 | ✓ | — |
| 6 | 3 | 1 | 2 | 3 | ✗ | 0 |
| 7 | 0 | 2 | 3 | 0 | ✗ | 1 |
| 8 | 4 | 3 | 0 | 4 | ✗ | 2 |

➡️ **FIFO = 7 fallos**

**LRU** — sale la que **lleva más tiempo sin usarse** (marcos en orden menos→más reciente):
| Paso | Pide | M1 | M2 | M3 | ¿? | Sale |
|---|---|---|---|---|---|---|
| 1 | 7 | 7 | · | · | ✗ | — |
| 2 | 0 | 7 | 0 | · | ✗ | — |
| 3 | 1 | 7 | 0 | 1 | ✗ | — |
| 4 | 2 | 0 | 1 | 2 | ✗ | 7 |
| 5 | 0 | 1 | 2 | 0 | ✓ | — (0 pasa a "más reciente") |
| 6 | 3 | 2 | 0 | 3 | ✗ | 1 |
| 7 | 0 | 2 | 3 | 0 | ✓ | — |
| 8 | 4 | 3 | 0 | 4 | ✗ | 2 |

➡️ **LRU = 6 fallos**

**ÓPTIMO** — sale la que se usará **más tarde en el futuro** (o que ya no se usa):
| Paso | Pide | M1 | M2 | M3 | ¿? | Sale (razón) |
|---|---|---|---|---|---|---|
| 1 | 7 | 7 | · | · | ✗ | — |
| 2 | 0 | 7 | 0 | · | ✗ | — |
| 3 | 1 | 7 | 0 | 1 | ✗ | — |
| 4 | 2 | 0 | 1 | 2 | ✗ | 7 (no se usa más) |
| 5 | 0 | 0 | 1 | 2 | ✓ | — |
| 6 | 3 | 0 | 2 | 3 | ✗ | 1 (no se usa más) |
| 7 | 0 | 0 | 2 | 3 | ✓ | — |
| 8 | 4 | 2 | 3 | 4 | ✗ | 0 (ya no se usa después) |

➡️ **Óptimo = 6 fallos** (siempre es el mínimo posible)

**CLOCK (Segunda Oportunidad)** — bit de referencia; si bit=1 da otra oportunidad (lo baja a 0) y avanza el puntero; si bit=0 reemplaza. Formato `página[bit]`:
| Paso | Pide | M1 | M2 | M3 | ¿? |
|---|---|---|---|---|---|
| 1 | 7 | 7[1] | · | · | ✗ |
| 2 | 0 | 7[1] | 0[1] | · | ✗ |
| 3 | 1 | 7[1] | 0[1] | 1[1] | ✗ |
| 4 | 2 | 2[1] | 0[0] | 1[0] | ✗ (baja bits y reemplaza 7) |
| 5 | 0 | 2[1] | 0[1] | 1[0] | ✓ (0 reactiva su bit) |
| 6 | 3 | 2[1] | 0[0] | 3[1] | ✗ (reemplaza 1) |
| 7 | 0 | 2[1] | 0[1] | 3[1] | ✓ |
| 8 | 4 | 4[1] | 0[0] | 3[0] | ✗ (reemplaza 2) |

➡️ **Clock = 6 fallos**

📊 **Resumen del ejemplo:** FIFO **7** · LRU **6** · Óptimo **6** · Clock **6** → menos fallos = mejor. El Óptimo es la cota ideal (imposible en la práctica); LRU y Clock se le acercan.

---

# CLASE 4 — Segmentación y Gestión de Usuarios Linux (Semana 9)

## Segmentación ⭐
Divide el proceso en **segmentos lógicos de tamaño variable** (a diferencia de la paginación, que usa bloques fijos). Cada segmento refleja la estructura del programa.

**Tipos de segmentos:** Código (solo lectura, compartible) · Datos (variables globales) · Pila/Stack (llamadas, locales) · Heap (memoria dinámica `malloc`/`new`).

**Tabla de segmentos** — cada segmento tiene **Base** (dónde empieza en RAM) y **Límite** (tamaño máx) + permisos:

| Segmento | Base | Límite | Permisos |
|---|---|---|---|
| 0 (Código) | 0x1000 | 4096 | R-X |
| 1 (Datos) | 0x5000 | 2048 | RW- |
| 2 (Stack) | 0x8000 | 8192 | RW- |
| 3 (Heap) | 0xC000 | 16384 | RW- |

- **Paginación vs Segmentación:** paginación = tamaño **fijo**, fragmentación **interna**; segmentación = tamaño **variable**, fragmentación **externa**.
- **Segmentación paginada** (híbrida, usada en x86-64): cada segmento se divide en páginas. Dirección lógica = `(s, p, d)`. Traducción: usar `s` en la tabla de segmentos → tabla de páginas → usar `p` → marco → + `d`.

## Comandos Linux — Usuarios y Grupos ⭐
```bash
sudo adduser juan                  # crear usuario (interactivo, con home)
sudo usermod -aG sudo juan         # agregar usuario a un grupo (-aG)
sudo deluser --remove-home juan    # eliminar usuario (y su home)
sudo passwd juan                   # cambiar contraseña
sudo addgroup desarrolladores      # crear grupo
sudo groupmod -n devs desarrolladores  # renombrar grupo (-n)
sudo gpasswd -a juan desarrolladores    # agregar usuario a grupo (-a)
whoami                             # usuario actual
groups juan                        # grupos de un usuario
```
**Flujo típico de examen:**
```bash
sudo adduser developer1            # 1. crear usuario
sudo addgroup devteam              # 2. crear grupo
sudo usermod -aG devteam developer1   # 3. agregar al grupo
groups developer1                  # 4. verificar  -> developer1 : developer1 devteam
sudo passwd developer1             # 5. cambiar contraseña
```

---

# 🧮 Fórmulas clave del examen
- **Necesidad** = Max − Asignación
- **Frag. Externa** = Memoria Total Libre − Mayor Bloque Contiguo Libre
- **Dirección Física (paginación)** = (Nº Marco × Tamaño Página) + Desplazamiento
- **Tasa de Fallos** = (Nº Fallos / Total Referencias) × 100%
- **Tiempo Efectivo de Acceso** = (1−p)·T_RAM + p·T_Disco

# ✅ Checklist de temas
- [ ] 4 condiciones de Coffman y cómo prevenirlas
- [ ] Algoritmo del Banquero (secuencia segura) + solicitud de recursos
- [ ] Grafo de asignación de recursos (detectar ciclo)
- [ ] Fragmentación interna vs externa
- [ ] First / Best / Worst Fit
- [ ] Traducción de direcciones en paginación
- [ ] Reemplazo FIFO / LRU / Clock / Óptimo
- [ ] Paginación vs Segmentación; segmentación paginada
- [ ] Comandos Linux de usuarios y grupos
