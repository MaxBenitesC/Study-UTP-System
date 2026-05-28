# PA3 — Análisis de la Actividad y Rúbrica

**Curso:** Herramientas Informáticas para la Toma de Decisiones
**Actividad:** Participación en Clase 03 (I04N-PA3-2026M)
**Puntaje máximo:** 20 pts
**Semana:** Semana 6
**Entregable:** Archivo Excel resuelto (.xlsx)

---

## Logro a evaluar

> Aplicar las funciones de búsqueda a través de los comandos **BUSCARV** y **BUSCARH** en una hoja de cálculo de Excel.

---

## Qué hay que hacer: 2 casos en Excel

### Caso 1 — Datos por región (funciones matemáticas, estadísticas + BUSCARH)

**Objetivo:** Obtener el total y promedio de un conjunto de datos, y realizar búsquedas por nombre de región.

| Tarea | Función a usar |
|---|---|
| Calcular el **total** de cada columna/región | `SUMA` |
| Calcular el **promedio** de cada columna/región | `PROMEDIO` |
| Buscar datos según la **zona seleccionada en F8** | `BUSCARH` |

- La celda **F8** es el selector de zona (ej. Norte, Sur, Central).
- Al cambiar la zona en F8, los resultados de BUSCARH se actualizan automáticamente.
- BUSCARH busca el nombre de la región en la **fila superior** de la tabla.

**Fórmula BUSCARH esperada (estructura):**
```
=BUSCARH(F8, matriz_de_datos, indicador_fila, FALSO)
```

---

### Caso 2 — Correos de empleados + búsqueda por código (funciones textuales + BUSCARV)

**Objetivo:** Generar el correo electrónico de cada empleado y realizar búsquedas por código.

| Tarea | Función a usar |
|---|---|
| Generar el **correo** concatenando código + dominio | Operador `&` o `CONCATENAR` |
| Buscar datos del empleado por su **código** | `BUSCARV` |

**Ejemplo de correo esperado:** `E14024@empresa.com`

**Fórmula para generar el correo:**
```
=A2&"@empresa.com"
```

**Fórmula BUSCARV esperada (estructura):**
```
=BUSCARV(código_buscado, tabla_empleados, indicador_columna, FALSO)
```

---

## Rúbrica de evaluación

### Escala de niveles

| Nivel | Descripción general |
|---|---|
| **Completo** | Usa la función correctamente y obtiene todos los resultados esperados |
| **En proceso 2** | Usa la función pero obtiene algunos resultados incorrectos |
| **En proceso 1** | Usa algunas funciones y obtiene algunos resultados correctos |
| **Inicio** | Usa algunas funciones pero no obtiene los resultados esperados |

---

### Criterios y puntajes

| Criterio | Completo | En proceso 2 | En proceso 1 | Inicio |
|---|---|---|---|---|
| **Funciones matemáticas** (SUMA, etc.) | 4 pts | 2 pts | 1 pt | 0.5 pts |
| **Funciones estadísticas** (PROMEDIO, etc.) | 4 pts | 3 pts | 2 pts | 1 pt |
| **Funciones textuales** (`&`, CONCATENAR, etc.) | 4 pts | 3 pts | 2 pts | 1 pt |
| **BUSCARH** (búsqueda horizontal por región) | 4 pts | 3 pts | 2 pts | 1 pt |
| **BUSCARV** (búsqueda vertical por código) | 4 pts | 3 pts | 2 pts | 1 pt |
| **TOTAL** | **20 pts** | | | |

> Nota: El criterio de **funciones matemáticas** es el único que tiene escala diferente en nivel "En proceso 2" (baja a 2 pts en lugar de 3).

---

## Resumen de funciones que debo dominar

| Función | Caso | Para qué |
|---|---|---|
| `SUMA` | Caso 1 | Totales por región o categoría |
| `PROMEDIO` | Caso 1 | Promedios por región o categoría |
| `BUSCARH` | Caso 1 | Buscar datos según la zona en F8 (búsqueda por fila) |
| `&` / `CONCATENAR` | Caso 2 | Armar el correo: código + "@empresa.com" |
| `BUSCARV` | Caso 2 | Buscar datos del empleado según su código (búsqueda por columna) |

---

## Checklist antes de entregar

- [ ] Caso 1: columna de **SUMA** completa y con resultados correctos
- [ ] Caso 1: columna de **PROMEDIO** completa y con resultados correctos
- [ ] Caso 1: celda F8 con selector de zona y **BUSCARH** funcionando dinámicamente
- [ ] Caso 2: columna de **correos** generada correctamente (`código@empresa.com`)
- [ ] Caso 2: **BUSCARV** buscando datos del empleado por código correctamente
- [ ] Archivo guardado en formato **.xlsx**
- [ ] Subido a la plataforma

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Consigna Participación en Clase 03 — PA3* [Documento]. Curso Herramientas informáticas para la toma de decisiones, Semana 6.
