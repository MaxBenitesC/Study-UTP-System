# Tema 03: Ejercicios de Funciones Lógicas en Excel

---

## Ejercicio 1: Aprobado / Reprobado

### Enunciado

Calcular el promedio de tres notas (N1, N2, N3) y determinar la observación: si el promedio es mayor o igual a 13, mostrar **APROBADO**; de lo contrario, **REPROBADO**.

### Datos

| Alumno | N1 | N2 | N3 |
|---|---|---|---|
| Valentin Pan. | 5 | 6 | 10 |
| Alejandro T. | 8 | 9 | 10 |
| Lady Toledo | 16 | 16 | 14 |
| Elian Karp | 6 | 15 | 13 |
| Guisella V. | 8 | 11 | 10 |
| Magaly M. | 16 | 12 | 11 |

### Fórmulas

**Columna Prom. (ej. celda E13):**
```
=PROMEDIO(B13:D13)
```

**Columna Observación (ej. celda F13):**
```
=SI(E13>=13,"APROBADO","REPROBADO")
```

### Resultado esperado

| Alumno | Prom. | Observación |
|---|---|---|
| Valentin Pan. | 7.00 | REPROBADO |
| Alejandro T. | 9.00 | REPROBADO |
| Lady Toledo | 15.33 | APROBADO |
| Elian Karp | 11.33 | REPROBADO |
| Guisella V. | 9.67 | REPROBADO |
| Magaly M. | 13.00 | APROBADO |

---

## Ejercicio 2: Descuento por categoría

### Enunciado

Calcular el **descuento** de los trabajadores. Solo reciben descuento del **15% del básico** quienes cumplan **todas** estas condiciones simultáneamente:

- Categoría **C**
- Básico **mayor a 1500**
- Edad **mayor de edad** (mayor que 18)

Caso contrario: descuento = **0**.

### Datos

| Nombres | Categ. | Edad | Básico |
|---|---|---|---|
| Katya | B | 20 | 1800 |
| Pedro | C | 18 | 1200 |
| Enian | A | 15 | 2000 |
| Rodrigo | C | 20 | 1600 |
| Diana | C | 19 | 1550 |
| Sebastián | A | 18 | 2500 |

### Fórmula

**Columna Descuento (ej. celda E13):**
```
=SI(Y(B13="C",D13>1500,C13>18),D13*15%,0)
```

### Lógica de la función Y

La función `Y` exige que las tres condiciones sean verdaderas al mismo tiempo:

| Condición | Descripción |
|---|---|
| `B13="C"` | La categoría debe ser C |
| `D13>1500` | El básico debe ser mayor a 1500 |
| `C13>18` | La edad debe ser mayor a 18 |

### Resultado esperado

| Nombres | Descuento |
|---|---|
| Katya | 0 |
| Pedro | 0 |
| Enian | 0 |
| Rodrigo | 240 |
| Diana | 232.5 |
| Sebastián | 0 |

---

## Ejercicio 3: Categoría y bonificación por código

### Enunciado

Asignar la **categoría** y la **bonificación** de cada trabajador según su código, usando SI anidadas:

| Cod | Categoría | Bonif. |
|---|---|---|
| A | Estable | 2.50% |
| B | Contratado | 2.25% |
| C | Auxiliar | 2% |
| Ninguna de las anteriores | N/A | 0 |

### Datos

| Trabajador | Cod |
|---|---|
| Miranda Ascencio | A |
| Alvarez Morales | C |
| Córdova Torres | F |
| Coronel Linares | C |
| Dulanto Muchotrigo | B |
| Falcón Gutierrez | D |
| Martinez Jimenez | B |
| Pardo Aliaga | C |

### Fórmulas

**Columna Categoría (ej. celda C6):**
```
=SI(B6="A","Estable",SI(B6="B","Contratado",SI(B6="C","Auxiliar","N/A")))
```

**Columna Bonif. (ej. celda F6):**
```
=SI(B6="A",2.5%,SI(B6="B",2.25%,SI(B6="C",2%,0)))
```

### Resultado esperado

| Trabajador | Cod | Categoría | Bonif. |
|---|---|---|---|
| Miranda Ascencio | A | Estable | 2.50% |
| Alvarez Morales | C | Auxiliar | 2% |
| Córdova Torres | F | N/A | 0 |
| Coronel Linares | C | Auxiliar | 2% |
| Dulanto Muchotrigo | B | Contratado | 2.25% |
| Falcón Gutierrez | D | N/A | 0 |
| Martinez Jimenez | B | Contratado | 2.25% |
| Pardo Aliaga | C | Auxiliar | 2% |

---

## Resumen de funciones utilizadas

| Función | Uso en los ejercicios |
|---|---|
| `PROMEDIO` | Calcular el promedio de tres notas |
| `SI` | Evaluar condición y devolver un resultado u otro |
| `Y` | Exigir el cumplimiento de múltiples condiciones a la vez |
| `SI anidada` | Evaluar más de dos posibles resultados en cadena |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Ejercicios de funciones lógicas en Excel* [Archivo Excel]. Curso Herramientas informáticas para la toma de decisiones, Semana 5.
