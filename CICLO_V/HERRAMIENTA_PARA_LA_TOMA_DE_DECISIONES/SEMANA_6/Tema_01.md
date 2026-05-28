# Tema 01: BUSCARV

## Definición

`BUSCARV` permite buscar un valor en la **primera columna** de un rango de celdas y devolver un valor de cualquier celda de la **misma fila**.

> Usar BUSCARV cuando los valores de comparación se encuentren en una columna a la **izquierda** de los datos que se desea encontrar.

---

## Sintaxis

```
=BUSCARV(valor_buscado, matriz_buscar_en, indicador_columnas, [ordenado])
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `valor_buscado` | Obligatorio | El valor que se desea encontrar en la primera columna de la matriz |
| `matriz_buscar_en` | Obligatorio | El rango o nombre de tabla donde se realizará la búsqueda |
| `indicador_columnas` | Obligatorio | Número de columna dentro de la matriz desde la cual se devolverá el valor (1 = primera columna, 2 = segunda, etc.) |
| `ordenado` | Opcional | `VERDADERO` o `1` = coincidencia aproximada; `FALSO` o `0` = coincidencia exacta |

---

## Formas de referenciar la matriz

| Método | Sintaxis |
|---|---|
| Rango directo | `Hoja1!B5:D11` |
| Nombre de rango | `datos` (si al rango se le asignó ese nombre) |
| Tabla con encabezados | `Tabla[#Todos]` |

---

## Ejemplo

Buscar la nota del estudiante con código `2013006` en la matriz `B5:D11` (llamada "datos"), donde la nota está en la columna 3:

```
=BUSCARV(2013006, datos, 3, FALSO)
```

| Argumento | Valor | Explicación |
|---|---|---|
| `valor_buscado` | `2013006` | Código del estudiante a buscar |
| `matriz_buscar_en` | `datos` (B5:D11) | Rango donde se realiza la búsqueda |
| `indicador_columnas` | `3` | La nota está en la tercera columna de la matriz |
| `ordenado` | `FALSO` | Se requiere coincidencia exacta |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *BUSCARV* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 6.
