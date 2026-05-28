# Tema 06: Funciones Estadísticas Parte 2

**Fuente:** Aprendizaje Virtual UTP. (2026). *Funciones estadísticas parte 2* [Video]. Canal YouTube Aprendizaje Virtual UTP.

---

## Definición

Esta segunda parte se centra en las **funciones estadísticas condicionales complejas y de jerarquía**: calcular promedios exigiendo múltiples requisitos simultáneos, evaluar rangos de fechas y extraer valores según su posición en un ranking.

---

## Funciones desarrolladas

### Bloque 1 — Funciones de promedio

| Función | Sintaxis | Descripción |
|---|---|---|
| `PROMEDIO` | `=PROMEDIO(número1; número2; ...)` | Media aritmética de todos los valores del rango |
| `PROMEDIO.SI` | `=PROMEDIO.SI(rango; criterio; rango_promedio)` | Promedio de celdas que cumplen **un** criterio |
| `PROMEDIO.SI.CONJUNTO` | `=PROMEDIO.SI.CONJUNTO(rango_promedio; rango_criterio1; criterio1; rango_criterio2; criterio2...)` | Promedio de celdas que cumplen **múltiples** criterios |

> **Clave:** En `PROMEDIO.SI.CONJUNTO` el **rango de promedio va primero**, seguido de pares rango-criterio sucesivos.

### Bloque 2 — Funciones de posición relativa

| Función | Sintaxis | Descripción |
|---|---|---|
| `K.ESIMO.MAYOR` | `=K.ESIMO.MAYOR(Matriz; K)` | Devuelve el K-ésimo valor **más alto** del rango |
| `K.ESIMO.MENOR` | `=K.ESIMO.MENOR(Matriz; K)` | Devuelve el K-ésimo valor **más bajo** del rango |

> El parámetro `K` indica la posición: `1` = el mayor/menor absoluto, `2` = el segundo, `3` = el tercero, etc.

---

## Características clave

- **Sintaxis multicriterio:** se emparejan rango-criterio de forma sucesiva — se pueden agregar tantos pares como condiciones se necesiten.
- **Referencias absolutas:** permiten copiar y arrastrar fórmulas sin que los rangos varíen, modificando solo el criterio.
- **Rangos de fechas:** como una función no acepta dos criterios en uno solo, se repite el rango con dos criterios separados (`>=fecha_inicio` y `<=fecha_fin`).
- **Jerarquización flexible:** `K.ESIMO` permite rankings intermedios sin necesidad de ordenar la tabla manualmente.

---

## Ejercicios de PROMEDIO.SI

Tabla de trabajo: personas con columnas **Sexo**, **Estado Civil** y **Cuota**.

| # | Requerimiento | Criterio aplicado |
|---|---|---|
| 1 | Promedio de cuotas de varones | `=PROMEDIO.SI(rango_sexo; "M"; rango_cuota)` |
| 2 | Promedio de cuotas de personas casadas | `=PROMEDIO.SI(rango_ecivil; "C"; rango_cuota)` |
| 3 | Promedio de cuotas de solteros | `=PROMEDIO.SI(rango_ecivil; "S"; rango_cuota)` |
| 4 | Promedio de cuotas de divorciados | `=PROMEDIO.SI(rango_ecivil; "D"; rango_cuota)` |
| 5 | Promedio de cuotas cuyo apellido empieza con F | `=PROMEDIO.SI(rango_apellido; "F*"; rango_cuota)` |

---

## Ejercicios de PROMEDIO.SI.CONJUNTO

Tabla de trabajo: **Control de Pagos** con columnas Sexo, Estado Civil, Tipo, Distrito, Fecha y Cuota.

| # | Requerimiento | Criterios |
|---|---|---|
| 1 | Cuotas de hombres casados | Sexo=`"M"` + E.Civil=`"C"` → resultado: `116.66` |
| 2 | Cuotas pagadas en el año 2010 | Fecha `>="01/01/2010"` + Fecha `<="31/12/2010"` |
| 3 | Cuotas entre 2011-2014 en Chorrillos | Fecha entre 2011 y 2014 + Distrito=`"Chorrillos"` |
| 4 | Cuotas de mujeres casadas en Surco | Sexo=`"F"` + E.Civil=`"C"` + Distrito=`"Surco"` |
| 5 | Cuotas de solteros que viven en Lima | E.Civil=`"S"` + Distrito=`"Lima"` |
| 6 | Cuotas de mujeres solteras en Chorrillos con cuota > 120 | Sexo=`"F"` + E.Civil=`"S"` + Distrito=`"Chorrillos"` + Cuota=`">120"` |

### Sintaxis del ejercicio con rango de fechas

Para filtrar un año completo se necesitan **dos pares rango-criterio** sobre la misma columna:

```
=PROMEDIO.SI.CONJUNTO(
    rango_cuota;
    rango_fecha; ">=01/01/2010";
    rango_fecha; "<=31/12/2010"
)
```

---

## Ejercicios de K.ESIMO.MAYOR

Tabla de trabajo: estudiantes con columnas **PP**, **EP**, **EF**, **PF** (Promedio Final).

| # | Requerimiento | Fórmula | K |
|---|---|---|---|
| 1 | 5ª mejor nota del examen parcial | `=K.ESIMO.MAYOR(rango_EP; 5)` | 5 |
| 2 | 3ª mayor nota del promedio de prácticas | `=K.ESIMO.MAYOR(rango_PP; 3)` | 3 |
| 3 | 2ª mayor nota del promedio final | `=K.ESIMO.MAYOR(rango_PF; 2)` | 2 |
| 4 | 7ª mayor nota del examen final | `=K.ESIMO.MAYOR(rango_EF; 7)` | 7 |

---

## Ejercicios de K.ESIMO.MENOR

Tabla de trabajo: trabajadores con columnas **Básico**, **Bonificación 1**, **Bonificación 2**, **Sueldo Total**.

| # | Requerimiento | Fórmula | K |
|---|---|---|---|
| 1 | 5° menor sueldo básico | `=K.ESIMO.MENOR(rango_basico; 5)` | 5 |
| 2 | 3ª menor bonificación 1 | `=K.ESIMO.MENOR(rango_bonif1; 3)` | 3 |
| 3 | 10° sueldo más bajo (total) | `=K.ESIMO.MENOR(rango_total; 10)` | 10 |
| 4 | 8ª bonificación 2 más baja | `=K.ESIMO.MENOR(rango_bonif2; 8)` | 8 |

---

## Aplicaciones prácticas

| Área | Uso |
|---|---|
| **Análisis financiero** | Cruzar variables demográficas para reportes de cuotas por perfil de cliente |
| **RRHH** | Comparar sueldos y bonificaciones por posición sin ordenar la tabla |
| **Educación** | Calcular el tercio superior o las mejores notas del salón automáticamente |
| **Ventas** | Identificar el 2° o 3° mejor vendedor del mes sin modificar la base de datos |

---

## Conclusión

> *"Al ser grandes cantidades de datos, realizar estos cálculos manualmente sería muy complicado. La aplicación de estas fórmulas genera resultados confiables, rápidos y adaptables."* — Docente UTP

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones estadísticas parte 2* [Video educativo]. Curso Herramientas informáticas para la toma de decisiones, Semana 4.
