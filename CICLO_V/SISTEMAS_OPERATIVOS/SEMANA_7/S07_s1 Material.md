---
universidad: UTP
curso: Sistemas Operativos
tema: Gestión de memoria — monoprogramación, particiones fijas, asignación contigua, compactación, algoritmos de ubicación, swapping
semana: 7
sesion: 1
unidad: "Unidad de aprendizaje 2: Gestión de memoria y gestión de archivos"
tipo_documento: diapositivas
paginas: 24
fuente_pdf: S07_s1 Material.pdf
---

# Sistemas Operativos — Semana 07, Sesión 01
**Unidad 2:** Gestión de memoria y gestión de archivos.

## Inventario
- Archivo: S07_s1 Material.pdf | Páginas: 24 | Tipo: diapositivas | OCR: nítido
- Contiene: varios diagramas de mapas de memoria, tablas ventajas/desventajas, 1 fórmula, ejemplo numérico de fragmentación interna.

## Logro de aprendizaje
- Administrar la memoria y los archivos que componen un sistema operativo.

## Temario
- Gestión de Memoria.
- Administración básica de memoria.
- Asignación de memoria contigua.

## Conocimientos previos
- Arquitectura de Computadoras. Estructura de Datos. Matemática discreta.

## Utilidad
- Administrar eficientemente la memoria principal (RAM) del sistema.

---

## Introducción a la Gestión de Memoria
**Objetivos principales:**
- Maximizar la utilización de la memoria.
- Minimizar la fragmentación.
- Proporcionar protección entre procesos.
- Facilitar la multiprogramación.

---

## Monoprogramación sin Intercambio ni Paginación
El esquema más simple: **solo un proceso de usuario puede estar en memoria a la vez.**

**[DIAGRAMA — Mapa de memoria]** De 0K a 512K:
- `0K–32K`: **Sistema Operativo** (32 KB)
- `32K–232K`: **Proceso de Usuario** (200 KB)
- `232K–512K`: **Memoria Libre** (280 KB)

| ✅ Ventajas | ❌ Desventajas |
|------------|----------------|
| Simplicidad máxima | Subutilización severa de CPU |
| Sin fragmentación externa | No hay multiprogramación |
| Control total de memoria | Desperdicio de memoria |
| Sin overhead de gestión | Tiempos de respuesta largos |

---

## Multiprogramación con Particiones Fijas
La memoria se divide en **particiones fijas de tamaños predeterminados**; cada una puede contener un proceso.

**[DIAGRAMA — Particiones iguales]** SO (100 KB) + 4 particiones de 100 KB:
- Partición 1 (100 KB) → Proceso A (80 KB)
- Partición 2 (100 KB) → Proceso B (95 KB)
- Partición 3 (100 KB) → Libre
- Partición 4 (100 KB) → Proceso C (60 KB)

### Cálculo de Fragmentación Interna (ejemplo de examen)
- Partición 1: 100 − 80 = **20 KB** desperdiciados
- Partición 2: 100 − 95 = **5 KB** desperdiciados
- Partición 4: 100 − 60 = **40 KB** desperdiciados
- **Total desperdiciado: 65 KB (16.25 %)** [sobre los 400 KB de particiones de usuario]

**[DIAGRAMA — Particiones desiguales]** SO (100 KB) + Partición 1 (50 KB) → Proceso Pequeño; Partición 2 (100 KB) → Proceso Mediano; Partición 3 (150 KB) → Libre; Partición 4 (200 KB) → Proceso Grande.

| ✅ Ventajas | ❌ Desventajas |
|------------|----------------|
| Menor fragmentación interna al tener tamaños variados | Complejidad en la asignación y posible fragmentación externa |

---

## Asignación de Memoria Contigua
- Los procesos se cargan en **bloques contiguos** de memoria.
- Cada proceso ocupa un área continua, sin interrupciones.

**[FIGURA — Simulador interactivo]** Botones: Añadir Proceso A (50KB), B (100KB), C (80KB), Remover Proceso B, Añadir Proceso D (120KB), Compactación, Reset. Estado inicial: `[OS][Free...]`. Tras compactar: `[OS][Proceso B][Proceso A][Free...]` con mensaje "Compactación realizada — Fragmentación externa eliminada".

### Fórmula — Fragmentación Externa
$$
\text{Fragmentación Externa} = \text{Memoria Total Libre} - \text{Mayor Bloque Contiguo Libre}
$$

---

## Compactación
**Proceso:** mover todos los procesos hacia un extremo de la memoria para consolidar el espacio libre.

| ✅ Ventajas | ❌ Desventajas |
|------------|----------------|
| Elimina fragmentación externa | Costo computacional alto |
| Maximiza espacio contiguo | Detiene el sistema temporalmente |

---

## Algoritmos de Ubicación
- **First Fit:** O(n) — Rápido, pero puede crear fragmentación. (Asigna el primer hueco suficientemente grande.)
- **Best Fit:** O(n) — Menos desperdicio, más fragmentación pequeña. (Asigna el hueco más pequeño que alcance.)
- **Worst Fit:** O(n) — Deja grandes bloques libres. (Asigna el hueco más grande.)

---

## Intercambio (Swapping)
Técnica que permite **sacar procesos temporalmente** de la memoria principal al almacenamiento secundario (swap) y traerlos de vuelta cuando sea necesario.

**[DIAGRAMA — Swapping]** Dos columnas con flecha bidireccional (⇄):
- **Memoria Principal:** SO, Proceso A, Proceso B, Libre.
- **Área de Swap:** Proceso C (swapped), Proceso D (swapped), Espacio Swap Libre.

---

## Cierre
- ¿Qué aprendiste en esta sesión?
- Te invitamos a compartir tus conclusiones en clase.

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras/Diagramas | 6 | Mapas de memoria (monoprogramación, particiones iguales/desiguales), simulador contiguo, swapping |
| Tablas   | 4        | Ventajas/desventajas (monoprogramación, particiones, compactación) + ubicación |
| Fórmulas | 1        | Fragmentación externa = Total libre − Mayor bloque contiguo |
| Ejercicios | 1      | Cálculo de fragmentación interna (65 KB, 16.25 %) |
