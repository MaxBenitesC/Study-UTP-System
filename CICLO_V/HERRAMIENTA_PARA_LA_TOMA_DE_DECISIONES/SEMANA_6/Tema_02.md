# Tema 02: BUSCARH

## Definición

`BUSCARH` busca un valor en la **fila superior** de una tabla o matriz y devuelve un valor en la **misma columna** de una fila especificada.

> Usar BUSCARH cuando los valores de comparación se encuentren en una **fila en la parte superior** de la tabla y se desee obtener información de una fila inferior específica.

---

## Sintaxis

```
=BUSCARH(valor_buscado, matriz_buscar_en, indicador_filas, [ordenado])
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `valor_buscado` | Obligatorio | Valor que se busca en la primera fila de la tabla. Puede ser un valor, referencia o cadena de texto |
| `matriz_buscar_en` | Obligatorio | Tabla de información donde se buscan los datos. Se usa una referencia a un rango o el nombre de un rango |
| `indicador_filas` | Obligatorio | Número de fila desde la cual se devolverá el valor coincidente (1 = primera fila, 2 = segunda fila, etc.) |
| `ordenado` | Opcional | `VERDADERO` = coincidencia aproximada; `FALSO` = coincidencia exacta |

---

## Comportamiento del argumento `indicador_filas`

| Valor | Resultado |
|---|---|
| `1` | Devuelve el valor de la primera fila de la matriz |
| `2` | Devuelve el valor de la segunda fila |
| Menor que `1` | Error `#¡VALOR!` |
| Mayor que el número de filas | Error `#¡REF!` o `#VALOR!` |

---

## Comportamiento del argumento `ordenado`

| Valor | Comportamiento |
|---|---|
| `VERDADERO` (o se omite) | Coincidencia aproximada: si no hay exacta, devuelve el siguiente valor mayor que sea inferior al buscado |
| `FALSO` | Coincidencia exacta: si no encuentra ninguna, devuelve `#N/A` |

---

## Ejemplo

Buscar el valor de "Ejes" en la tabla `A1:C4` y devolver el dato de la fila 2:

```
=BUSCARH("Ejes", A1:C4, 2, VERDADERO)
```

| Argumento | Valor | Explicación |
|---|---|---|
| `valor_buscado` | `"Ejes"` | Se buscará este texto en la primera fila de la tabla |
| `matriz_buscar_en` | `A1:C4` | Tabla completa incluyendo encabezados |
| `indicador_filas` | `2` | Se devuelve el valor de la segunda fila de la columna encontrada |
| `ordenado` | `VERDADERO` | Busca coincidencia aproximada |

---

## Comparación BUSCARV vs BUSCARH

| Característica | BUSCARV | BUSCARH |
|---|---|---|
| Dirección de búsqueda | Vertical (en columnas) | Horizontal (en filas) |
| Busca el valor en... | Primera **columna** de la matriz | Primera **fila** de la matriz |
| Indicador de posición | Número de **columna** | Número de **fila** |
| Cuándo usarlo | Datos organizados en columnas | Datos organizados en filas |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *BUSCARH* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 6.
