# Tema 04: Funciones Estadísticas en Excel (Ejercicios Prácticos)

## Objetivo

Aplicar de forma práctica las funciones estadísticas de Excel mediante 7 ejercicios progresivos, que abarcan desde conteos simples hasta promedios con múltiples criterios sobre bases de datos reales.

---

## Funciones utilizadas

### Funciones de conteo

| Función | Sintaxis | Descripción |
|---|---|---|
| `CONTAR.BLANCO` | `=CONTAR.BLANCO(rango)` | Cuenta celdas **vacías** dentro de un rango |
| `CONTARA` | `=CONTARA(rango)` | Cuenta celdas **no vacías** (con cualquier tipo de dato) |
| `CONTAR` | `=CONTAR(valor1; [valor2]; ...)` | Cuenta celdas que contienen **solo números** |
| `CONTAR.SI` | `=CONTAR.SI(rango; condición)` | Cuenta celdas que cumplen **un** criterio |
| `CONTAR.SI.CONJUNTO` | `=CONTAR.SI.CONJUNTO(rango1; criterio1; rango2; criterio2...)` | Cuenta celdas que cumplen **múltiples** criterios |

### Funciones de extremos

| Función | Sintaxis | Descripción |
|---|---|---|
| `MAX` | `=MAX(número1; [número2]; ...)` | Devuelve el **valor máximo** de una lista |
| `MIN` | `=MIN(número1; [número2]; ...)` | Devuelve el **valor mínimo** de una lista |
| `K.ESIMO.MAYOR` | `=K.ESIMO.MAYOR(Matriz; K)` | Devuelve el k-ésimo **mayor** valor del rango |
| `K.ESIMO.MENOR` | `=K.ESIMO.MENOR(Matriz; K)` | Devuelve el k-ésimo **menor** valor del rango |

### Funciones de promedio

| Función | Sintaxis | Descripción |
|---|---|---|
| `PROMEDIO` | `=PROMEDIO(número1; número2; ...)` | Media aritmética de los argumentos |
| `PROMEDIO.SI` | `=PROMEDIO.SI(rango; criterio; rango_promedio)` | Promedio de celdas que cumplen **un** criterio |
| `PROMEDIO.SI.CONJUNTO` | `=PROMEDIO.SI.CONJUNTO(rango_promedio; rango_criterio1; criterio1; rango_criterio2; criterio2...)` | Promedio de celdas que cumplen **múltiples** criterios |

---

## Estructura del archivo (7 hojas)

| Hoja | Escenario | Columnas principales |
|---|---|---|
| CONTAR.BLANCO | Lista de alumnos | Nº, Apellido, Nombre, Teléfono, Edad, Notas (PP, EP, EF, PF) |
| CONTARA / CONTAR | Control de pagos / socios | Nº, Apellido, Nombre, Sexo, E.Civil, Tipo, Cuota, Distrito |
| CONTAR.SI | Control de pagos | Nº, Apellido, Nombre, Sexo, E.Civil, Tipo, Cuota, Distrito |
| CONTAR.SI.CONJUNTO | Control de pagos | Nº, Apellido, Nombre, Sexo, E.Civil, Tipo, Cuota, Distrito |
| MAX y MIN | Control de productos | Artículo, Cantidad, Precio |
| PROMEDIO.SI | Control de pagos | Nº, Apellido, Nombre, Sexo, E.Civil, Tipo, Cuota, Distrito |
| PROMEDIO.SI.CONJUNTO | Control de pagos | Nº, Apellido, Nombre, Sexo, E.Civil, Tipo, Cuota, Distrito |

---

## Ejemplos y casos resueltos

| Ejercicio | Requerimiento | Función aplicada | Resultado |
|---|---|---|---|
| Auditoría de datos | Alumnos sin número de teléfono | `CONTAR.BLANCO` | Celdas vacías en columna Teléfono |
| Segmentación de socios | Cantidad de socios por tipo (A, B o C) | `CONTAR.SI` | Conteo por cada categoría |
| Socios que pagaron | Cuántos aportaron efectivamente | `CONTAR` | Solo celdas con número en Cuota |
| Inventario | Precio máximo y mínimo de productos | `MAX` / `MIN` | Máx: 350 (TV Color) / Mín: 50 (Radio) |
| Promedio con filtro simple | Promedio de cuotas de hombres | `PROMEDIO.SI` | Promedio del rango filtrado por Sexo = M |
| Promedio con doble filtro | Promedio de cuotas de hombres casados | `PROMEDIO.SI.CONJUNTO` | `116.66` |
| Ranking de valores | Segundo precio más alto / tercer precio más bajo | `K.ESIMO.MAYOR` / `K.ESIMO.MENOR` | Valor según posición K |

---

## Progresión de dificultad

```
Nivel 1 → Funciones simples en un solo rango (MAX, MIN, CONTARA)
Nivel 2 → Funciones con una condición (CONTAR.SI, PROMEDIO.SI)
Nivel 3 → Funciones con múltiples criterios (CONTAR.SI.CONJUNTO, PROMEDIO.SI.CONJUNTO)
```

---

## Conclusiones

1. Las funciones `.SI` y `.SI.CONJUNTO` permiten filtrar y analizar datos sin necesidad de ordenar o separar la información manualmente.
2. Incluir `CONTAR.BLANCO` antes de calcular estadísticas es una buena práctica para **auditar datos faltantes** y evitar resultados incorrectos.
3. La progresión de los ejercicios refleja el flujo real de análisis de datos en entornos empresariales: primero limpiar, luego contar, luego promediar con criterios.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones estadísticas en Excel* [Archivo de ejercicios]. Curso Herramientas informáticas para la toma de decisiones, Semana 4.
