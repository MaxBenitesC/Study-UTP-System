# Tema 03: Funciones Matemáticas y Estadísticas

## Definición

Las **funciones matemáticas y estadísticas** de Excel permiten realizar cálculos numéricos de forma automatizada, desde operaciones simples como sumas y potencias, hasta análisis estadísticos con criterios múltiples.

---

## Funciones matemáticas

### ALEATORIO()

Devuelve un número real aleatorio mayor a cero y menor a uno. No requiere argumentos.

```
=ALEATORIO()   →   resultado entre 0 y 1 (varía con cada recálculo)
```

---

### ALEATORIO.ENTRE(inferior, superior)

Devuelve un número **entero** aleatorio dentro del rango especificado. Se recalcula con cada cambio en la hoja o al presionar **F9**.

**Sintaxis:**
```
=ALEATORIO.ENTRE(inferior, superior)
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `inferior` | Obligatorio | Menor número entero que puede devolver |
| `superior` | Obligatorio | Mayor número entero que puede devolver |

**Ejemplos:**

| Fórmula | Descripción | Resultado |
|---|---|---|
| `=ALEATORIO.ENTRE(1; 100)` | Número aleatorio entre 1 y 100 | Varía |
| `=ALEATORIO.ENTRE(-1; 1)` | Número aleatorio entre -1 y 1 | Varía |

> **Nota:** Cada vez que se introduce una fórmula, se ingresan datos o se presiona F9, se genera un nuevo número aleatorio.

---

### ENTERO(número)

Convierte un número real al **entero inferior más próximo**.

**Sintaxis:**
```
=ENTERO(número)
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `número` | Obligatorio | Número real que se desea redondear hacia abajo |

**Ejemplos:**

| Fórmula | Resultado |
|---|---|
| `=ENTERO(1.365)` | `1` |
| `=ENTERO(8.95)` | `8` |

---

### POTENCIA(número, potencia)

Eleva un número a una potencia especificada.

**Sintaxis:**
```
=POTENCIA(número, potencia)
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `número` | Obligatorio | Base que se elevará a la potencia |
| `potencia` | Obligatorio | Exponente al que se eleva el número |

**Ejemplos:**

| Fórmula | Resultado |
|---|---|
| `=POTENCIA(5, 2)` | `25` |
| `=POTENCIA(8, 3)` | `512` |

---

### SUMA(...)

Suma todos los argumentos indicados. Cada argumento puede ser un rango, un número o una referencia de celda, todos separados por comas o punto y coma.

**Ejemplos:**

```
=SUMA(A2:A4; 2429; 10482)
=SUMA(4823; A3:A4; C2:C3)
=SUMA(4823; 12335; 9718; C2:C3)
=SUMA(A2; A3; A4; 2429; 10482)
```

---

### REDONDEAR(número, núm_decimales)

Redondea un número a la cantidad de decimales especificada. El número de decimales puede ser positivo, negativo o cero.

**Sintaxis:**
```
=REDONDEAR(número, núm_decimales)
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `número` | Obligatorio | Número que se va a redondear |
| `núm_decimales` | Obligatorio | Cantidad de decimales a la que se redondea |

---

### K.ESIMO.MAYOR / K.ESIMO.MENOR

Devuelven el k-ésimo valor más grande o más pequeño de un conjunto de datos, útiles para seleccionar valores según su posición relativa.

| Función | Sintaxis | Descripción |
|---|---|---|
| `K.ESIMO.MAYOR` | `=K.ESIMO.MAYOR(Matriz, K)` | Devuelve el k-ésimo **mayor** valor del rango |
| `K.ESIMO.MENOR` | `=K.ESIMO.MENOR(Matriz, K)` | Devuelve el k-ésimo **menor** valor del rango |

---

## Funciones estadísticas

### Funciones CONTAR

| Función | Sintaxis | Descripción |
|---|---|---|
| `CONTAR.BLANCO` | `=CONTAR.BLANCO(rango)` | Cuenta celdas **vacías** dentro de un rango |
| `CONTARA` | `=CONTARA(rango)` | Cuenta celdas que **contienen cualquier valor** |
| `CONTAR.SI` | `=CONTAR.SI(rango, condición)` | Cuenta celdas que cumplen **un** criterio |
| `CONTAR.SI.CONJUNTO` | `=CONTAR.SI.CONJUNTO(rango1; criterio1; rango2; criterio2...)` | Cuenta celdas que cumplen **múltiples** criterios |

---

### MAX y MIN

| Función | Sintaxis | Descripción |
|---|---|---|
| `MAX` | `=MAX(número1; [número2]; ...)` | Devuelve el **valor máximo** de una lista |
| `MIN` | `=MIN(número1; [número2]; ...)` | Devuelve el **valor mínimo** de una lista |

---

### Funciones PROMEDIO

| Función | Sintaxis | Descripción |
|---|---|---|
| `PROMEDIO` | `=PROMEDIO(número1; número2; ...)` | Devuelve la media aritmética de los argumentos |
| `PROMEDIO.SI` | `=PROMEDIO.SI(rango; criterio; rango_promedio)` | Promedio de celdas que cumplen **un** criterio |
| `PROMEDIO.SI.CONJUNTO` | `=PROMEDIO.SI.CONJUNTO(rango_promedio; rango_criterio1; criterio1; rango_criterio2; criterio2...)` | Promedio de celdas que cumplen **múltiples** criterios |

---

## Resumen

| Categoría | Funciones |
|---|---|
| **Aleatorias** | `ALEATORIO`, `ALEATORIO.ENTRE` |
| **Matemáticas** | `ENTERO`, `POTENCIA`, `SUMA`, `REDONDEAR`, `K.ESIMO.MAYOR`, `K.ESIMO.MENOR` |
| **Contar** | `CONTAR.BLANCO`, `CONTARA`, `CONTAR.SI`, `CONTAR.SI.CONJUNTO` |
| **Extremos** | `MAX`, `MIN` |
| **Promedios** | `PROMEDIO`, `PROMEDIO.SI`, `PROMEDIO.SI.CONJUNTO` |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones matemáticas y estadísticas* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 4.
