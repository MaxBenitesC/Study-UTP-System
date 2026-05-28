# Tema 04: Resolución de Ejercicios — Funciones Lógicas (Video guía)

> Este tema es el video guía que explica paso a paso cómo resolver los ejercicios de funciones lógicas del [Tema 03](Tema_03.md).

---

## Ejercicio 1: Promedio y observación (SI)

### Pasos

1. Ubicarse en la celda de **Promedio** del primer estudiante.
2. Escribir `=PROMEDIO(...)` seleccionando las celdas de las tres notas.
3. Arrastrar la fórmula hacia abajo para calcular el promedio de todos los estudiantes.
4. Ubicarse en la celda de **Observación** del primer estudiante.
5. Escribir la función SI con tres parámetros:

```
=SI(E13>=10.5,"APROBADO","REPROBADO")
```

6. Arrastrar la fórmula hacia abajo.

### Lógica

| Parámetro | Valor |
|---|---|
| Condición | Promedio mayor o igual a 10.5 |
| Valor si verdadero | `"APROBADO"` |
| Valor si falso | `"REPROBADO"` |

---

## Ejercicio 2: Descuento con SI + Y

### Pasos

1. Ubicarse en la celda de **Descuento** del primer trabajador.
2. Escribir la función SI. La prueba lógica será una función Y con tres condiciones:

```
=SI(Y(B13="C", D13>1500, C13>=18), D13*0.15, 0)
```

3. Arrastrar la fórmula hacia abajo.

### Lógica

| Condición dentro de Y | Descripción |
|---|---|
| `B13="C"` | La categoría debe ser C |
| `D13>1500` | El básico debe ser mayor a 1500 |
| `C13>=18` | La edad debe ser mayor o igual a 18 (mayoría de edad) |

- Si las **tres** condiciones son verdaderas → descuento = `Básico * 0.15`
- Si **alguna** es falsa → descuento = `0`

---

## Ejercicio 3: Categoría y bonificación con SI Anidadas + Referencias mixtas

### Pasos — Columna Categoría

1. Ubicarse en la celda de **Categoría** del primer trabajador.
2. Escribir la primera función SI evaluando si el código es `"A"`:

```
=SI(B6="A","Estable",SI(B6="B","Contratado",SI(B6="C","Auxiliar","N/A")))
```

3. Al arrastrar, aplicar **referencias mixtas** en las celdas de la tabla de referencia para que las filas no varíen pero las columnas sí puedan ajustarse.

### Pasos — Columna Bonificación

1. Reutilizar la misma estructura de SI anidadas, cambiando los valores de texto por los porcentajes:

```
=SI(B6="A",2.5%,SI(B6="B",2.25%,SI(B6="C",2%,0)))
```

2. Arrastrar la fórmula.
3. Seleccionar el rango → **Formato de celdas → Porcentaje** para mostrar los valores correctamente.

---

## Concepto clave: Referencias mixtas

Al arrastrar fórmulas que consultan una tabla fija, las referencias se desplazan automáticamente y pueden apuntar a celdas incorrectas. La solución es usar **referencias mixtas** o **absolutas**.

| Tipo | Notación | Comportamiento al arrastrar |
|---|---|---|
| Relativa | `B6` | Fila y columna cambian |
| Absoluta | `$B$6` | Fila y columna fijas |
| Mixta (fila fija) | `B$6` | Solo la columna cambia |
| Mixta (columna fija) | `$B6` | Solo la fila cambia |

> En el Ejercicio 3, la celda del código (`B6`) solo aumenta en fila al arrastrar hacia abajo, por lo que se usa referencia mixta para fijar la columna: `$B6`.

---

## Conclusiones del video

- Las funciones lógicas **se pueden anidar**: una función SI puede contener otra función SI dentro.
- Se pueden combinar con otras funciones: `Y` dentro de `SI` permite evaluar múltiples condiciones simultáneas.
- Las **referencias mixtas** son esenciales para arrastrar fórmulas correctamente cuando se consulta una tabla de referencia fija.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones lógicas* [Video guía]. Curso Herramientas informáticas para la toma de decisiones, Semana 5.
