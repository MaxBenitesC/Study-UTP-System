# Tema 01: Operaciones con Excel

## Definición

En Excel existen dos grandes tipos de operaciones dentro de las fórmulas:

- **Fórmulas aritméticas:** combinan números, referencias de celda, funciones y operadores aritméticos para realizar cálculos matemáticos.
- **Operadores de comparación:** comparan dos o más números o cadenas de texto, devolviendo `VERDADERO` o `FALSO` según el resultado.

---

## Principales operadores

### Operadores aritméticos

| Operador | Nombre | Ejemplo | Resultado |
|---|---|---|---|
| `+` | Suma | `=10+5` | `15` |
| `-` | Resta | `=10-5` | `5` |
| `-` | Negación | `=-10` | `-10` |
| `*` | Multiplicación | `=10*5` | `50` |
| `%` | Porcentaje | `=10%` | `0.1` |
| `^` | Exponenciación | `=10^5` | `100000` |

### Operadores de comparación

| Operador | Nombre | Ejemplo | Resultado |
|---|---|---|---|
| `=` | Igual a | `=10=5` | `FALSO` |
| `>` | Mayor que | `=10>5` | `VERDADERO` |
| `<` | Menor que | `=10<5` | `FALSO` |
| `>=` | Mayor o igual que | `="a">="b"` | `FALSO` |
| `<=` | Menor o igual que | `="a"<="b"` | `VERDADERO` |
| `<>` | Diferente | `="a"<>"b"` | `VERDADERO` |

### Operadores de texto

| Operador | Nombre | Ejemplo | Resultado |
|---|---|---|---|
| `&` | Concatenación | `="abc"&"123"` | `abc123` |

### Operadores de referencia

| Operador | Nombre | Descripción | Ejemplo |
|---|---|---|---|
| `:` | Rango | Produce un rango a partir de dos referencias de celda | `A1:D5` |
| `,` | Unión | Produce un rango que es la unión de dos rangos | `A1:D5,F1:H5` |
| ` ` (espacio) | Intersección | Produce un rango con las celdas comunes de dos rangos | `A1:D5 B3:F8` |

---

## Partes de una fórmula de Excel

Una fórmula puede contener algunos o todos los elementos siguientes:

| Elemento | Descripción |
|---|---|
| **Funciones** | Fórmulas predefinidas que realizan cálculos. Ej: `PI()` devuelve 3,142... |
| **Referencias** | Indican la celda de donde se toma el valor. Ej: `A2` devuelve el valor de la celda A2 |
| **Operadores** | Símbolos que definen el tipo de operación. Ej: `^` eleva a una potencia, `*` multiplica |
| **Constantes** | Números o valores de texto escritos directamente en la fórmula. Ej: `2`, `3` |

### Ejemplo desglosado

```
= PI() * 2 ^ 3
```

| Parte | Elemento | Explicación |
|---|---|---|
| `PI()` | Función | Devuelve el valor de Pi (3,142...) |
| `*` y `^` | Operadores | `^` eleva a una potencia; `*` multiplica |
| `2` | Referencia | Devuelve el valor de la celda A2 |
| `3` | Constante | Número escrito directamente en la fórmula |

---

## Referencias en la misma hoja de cálculo

| Descripción | Notación |
|---|---|
| Celda de la columna A, fila 10 | `A10` |
| Rango de columna A, filas 10 a 20 | `A10:A20` |
| Rango de fila 15, columnas B a E | `B15:E15` |
| Todas las celdas de la fila 5 | `5:5` |
| Todas las celdas de la fila 5 a la 10 | `5:10` |
| Todas las celdas de la columna H | `H:H` |
| Todas las celdas de la columna H hasta J | `H:J` |
| Rango de columnas A a E y filas 10 a 20 | `A10:E20` |

---

## Referencias de otra hoja de cálculo

Para referenciar celdas de una hoja distinta dentro del mismo libro se usa el signo `!` como separador:

```
= PROMEDIO(MARKETING!B1:B10)
```

| Parte | Significado |
|---|---|
| `MARKETING` | Nombre de la hoja de cálculo |
| `!` | Separador entre la hoja y el rango |
| `B1:B10` | Rango de celdas de B1 a B10 en esa hoja |

---

## Resumen

Excel ofrece cuatro tipos de operadores (aritméticos, comparación, texto y referencia) que, combinados con funciones, referencias y constantes, permiten construir fórmulas capaces de realizar desde cálculos simples hasta modelos de análisis complejos en múltiples hojas de un mismo libro.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Operaciones con Excel* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 4.
