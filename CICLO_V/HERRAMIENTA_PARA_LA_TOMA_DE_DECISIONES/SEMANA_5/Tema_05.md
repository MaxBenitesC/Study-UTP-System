# Tema 05: Funciones de Fecha Complementarias

---

## Función DIASEM

Indica qué **día de la semana** corresponde a una fecha dada, devolviendo un número del 1 al 7.

```
=DIASEM(fecha, tipo)
```

| Parámetro | Descripción |
|---|---|
| `fecha` | La fecha que se desea analizar |
| `tipo` | Valor numérico que define cuál es el primer día de la semana y qué número le corresponde |

### Valores del parámetro `tipo`

| Tipo | Primer día de la semana | Rango de valores |
|---|---|---|
| `1` | Domingo = 1 | 1 (domingo) a 7 (sábado) |
| `2` | Lunes = 1 | 1 (lunes) a 7 (domingo) |
| `3` | Lunes = 0 | 0 (lunes) a 6 (domingo) |

### Ejemplo

```
=DIASEM(A2, 2)   →   devuelve 3
```

> La fecha 22 de febrero de 1995 cae en **miércoles** (valor 3, usando tipo 2 donde lunes = 1).

### Verificación

Para confirmar el resultado se puede cambiar el formato de la celda de la fecha a **"Fecha larga"**, lo que mostrará el nombre del día directamente.

---

## Función FIN.MES

Devuelve el **último día del mes** de una fecha dada, pudiendo avanzar o retroceder meses.

```
=FIN.MES(fecha, meses)
```

| Parámetro | Descripción |
|---|---|
| `fecha` | La fecha de referencia |
| `meses` | Número de meses a avanzar (positivo) o retroceder (negativo) antes de calcular el último día. Usar `0` para el mes actual |

### Comportamiento según el valor de `meses`

| Valor | Resultado |
|---|---|
| `0` | Último día del mismo mes de la fecha |
| `1` | Último día del mes siguiente |
| `2` | Último día de dos meses adelante |
| `-1` | Último día del mes anterior |

### Ejemplo

```
=FIN.MES(A2, 0)   →   28/02/1995
```

> Febrero de 1995 tuvo 28 días, por lo que el último día fue el 28.

### Nota importante

El resultado de `FIN.MES` es un número de serie de fecha. La celda **debe tener formato de fecha**; de lo contrario, se mostrará un número largo sin significado aparente.

---

## Resumen comparativo

| Función | ¿Qué calcula? | Sintaxis |
|---|---|---|
| `DIASEM` | Día de la semana de una fecha (número del 1 al 7) | `=DIASEM(fecha, tipo)` |
| `FIN.MES` | Último día del mes de una fecha (pudiendo avanzar meses) | `=FIN.MES(fecha, meses)` |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones de fecha complementarias* [Video — transcripción]. Curso Herramientas informáticas para la toma de decisiones, Semana 5.
