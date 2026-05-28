# Referencia Excel — Herramientas Informáticas UTP
> Companion de `excel_tool.sh`. Cada semana nueva → agregar sección aquí y función al script.

---

## SEMANA 3 — Excel Básico y Referencias

### Tipos de referencia

| Tipo | Ejemplo | Comportamiento al copiar |
|---|---|---|
| Relativa | `A1` | Cambia fila y columna |
| Absoluta | `$A$1` | No cambia nada |
| Mixta col-fija | `$A1` | Columna fija, fila libre |
| Mixta fila-fija | `A$1` | Fila fija, columna libre |

**Atajo F4** — cicla entre tipos al editar una celda:
`A1` → `$A$1` → `A$1` → `$A1` → `A1`

### Referencia a otra hoja
```
=Hoja2!B5
='Nombre con espacios'!C3
```

---

## SEMANA 4 — Operaciones, Fecha, Matemáticas, Estadísticas

### Operadores
| Tipo | Operadores |
|---|---|
| Aritméticos | `+ - * / ^ %` |
| Comparación | `= <> > < >= <=` |
| Texto | `&` (concatenar) |

### Funciones de Fecha

| Función | Sintaxis | Descripción |
|---|---|---|
| `HOY` | `=HOY()` | Fecha actual (dinámica) |
| `AHORA` | `=AHORA()` | Fecha y hora actual |
| `SI.FECHA` | `=SI.FECHA(ini;fin;"Y")` | Diferencia: "Y"=años, "M"=meses, "D"=días |
| `DIASEM` | `=DIASEM(fecha;2)` | Nº día semana (tipo 2: 1=Lun…7=Dom) |
| `FIN.MES` | `=FIN.MES(HOY();0)` | Último día del mes (0=actual, 1=siguiente) |

### Funciones Matemáticas

| Función | Sintaxis | Descripción |
|---|---|---|
| `SUMA` | `=SUMA(rango)` | Suma todos los valores |
| `REDONDEAR` | `=REDONDEAR(num;dec)` | Redondea a N decimales |
| `ENTERO` | `=ENTERO(num)` | Parte entera (trunca hacia abajo) |
| `POTENCIA` | `=POTENCIA(base;exp)` | base^exp |
| `ALEATORIO` | `=ALEATORIO()` | Decimal entre 0 y 1 |
| `ALEATORIO.ENTRE` | `=ALEATORIO.ENTRE(inf;sup)` | Entero aleatorio en rango |
| `K.ESIMO.MAYOR` | `=K.ESIMO.MAYOR(rango;k)` | K-ésimo valor mayor |
| `K.ESIMO.MENOR` | `=K.ESIMO.MENOR(rango;k)` | K-ésimo valor menor |

### Funciones Estadísticas con Criterio

| Función | Sintaxis | Descripción |
|---|---|---|
| `CONTAR` | `=CONTAR(rango)` | Solo valores numéricos |
| `CONTARA` | `=CONTARA(rango)` | Celdas no vacías |
| `CONTAR.BLANCO` | `=CONTAR.BLANCO(rango)` | Celdas vacías |
| `CONTAR.SI` | `=CONTAR.SI(rango;criterio)` | Cuenta con 1 criterio |
| `CONTAR.SI.CONJUNTO` | `=CONTAR.SI.CONJUNTO(r1;c1;r2;c2)` | Cuenta con N criterios |
| `PROMEDIO.SI` | `=PROMEDIO.SI(r_crit;crit;r_prom)` | Promedio con 1 criterio |
| `PROMEDIO.SI.CONJUNTO` | `=PROMEDIO.SI.CONJUNTO(r_p;r1;c1)` | Promedio con N criterios |
| `MAX.SI.CONJUNTO` | `=MAX.SI.CONJUNTO(r_val;r_crit;crit)` | Máximo con criterio |
| `MIN.SI.CONJUNTO` | `=MIN.SI.CONJUNTO(r_val;r_crit;crit)` | Mínimo con criterio |

**Comodines en criterios:** `*` = cualquier texto · `?` = un carácter · `~*` = asterisco literal

---

## SEMANA 5 — Lógicas, Textuales, Fecha avanzada, Validación

### Funciones Lógicas

| Función | Sintaxis | Descripción |
|---|---|---|
| `SI` | `=SI(cond;verdadero;falso)` | Condición simple |
| `Y` | `=Y(c1;c2;…)` | VERDADERO si todas se cumplen |
| `O` | `=O(c1;c2;…)` | VERDADERO si al menos una se cumple |
| `SI+Y` | `=SI(Y(c1;c2);v;f)` | SI con múltiples condiciones AND |
| `SI+O` | `=SI(O(c1;c2);v;f)` | SI con múltiples condiciones OR |
| SI anidada | `=SI(c1;v1;SI(c2;v2;vf))` | Múltiples resultados posibles |

**Ejemplo descuento:**
```
=SI(B2>1000; B2*0.1; SI(B2>500; B2*0.05; 0))
```

### Funciones Textuales

| Función | Sintaxis | Descripción |
|---|---|---|
| `LARGO` | `=LARGO(texto)` | Número de caracteres |
| `MAYUSC` | `=MAYUSC(texto)` | Todo mayúsculas |
| `MINUSC` | `=MINUSC(texto)` | Todo minúsculas |
| `NOMPROPIO` | `=NOMPROPIO(texto)` | Primera letra mayúscula por palabra |
| `IZQUIERDA` | `=IZQUIERDA(texto;n)` | n chars desde la izquierda |
| `DERECHA` | `=DERECHA(texto;n)` | n chars desde la derecha |
| `EXTRAE` | `=EXTRAE(texto;ini;n)` | n chars desde posición ini |
| `HALLAR` | `=HALLAR(buscar;texto)` | Posición del texto buscado (no sensible mayúsculas) |
| `IGUAL` | `=IGUAL(t1;t2)` | VERDADERO si idénticos (sensible mayúsculas) |
| `SUSTITUIR` | `=SUSTITUIR(texto;viejo;nuevo)` | Reemplaza texto |
| Concatenar | `=A1&" "&B1` | Une texto (reemplaza `CONCATENAR()`) |

### Funciones de Fecha (continuación)

| Función | Sintaxis | Descripción |
|---|---|---|
| `DIASEM` | `=DIASEM(fecha;2)` | 1=Lun…7=Dom (tipo 2) |
| `FIN.MES` | `=FIN.MES(fecha;meses)` | Último día del mes relativo |

### Validación de Datos

Ruta: **Datos → Validación de datos**

| Tipo | Uso |
|---|---|
| Número entero / Decimal | Restricción de rango numérico |
| Lista | Desplegable con valores fijos |
| Fecha / Hora | Solo fechas u horas válidas |
| Longitud de texto | Limita caracteres (`=LARGO(A1)=8`) |
| Personalizada | Fórmula libre |

Mensajes: **Entrada** (al seleccionar celda) · **Error** (Detener / Advertencia / Información)

---

## SEMANA 6 — Funciones de Búsqueda

### BUSCARV
```
=BUSCARV(valor_buscado; matriz; indicador_columnas; FALSO)
```
- Busca en la **primera columna** de la matriz
- `indicador_columnas`: 1=primera col, 2=segunda, etc.
- `FALSO` = coincidencia exacta (siempre usar esto)

### BUSCARH
```
=BUSCARH(valor_buscado; matriz; indicador_filas; FALSO)
```
- Busca en la **primera fila** de la matriz
- `indicador_filas`: 1=primera fila, 2=segunda, etc.

### COINCIDIR
```
=COINCIDIR(valor; rango; 0)
```
- Devuelve la **posición** (número) del valor en el rango
- tipo `0` = coincidencia exacta

### ESNOD + SI
```
=SI(ESNOD(BUSCARV(A1;datos;2;FALSO)); "No encontrado"; BUSCARV(A1;datos;2;FALSO))
```
- `ESNOD` devuelve VERDADERO cuando hay error `#N/A`
- Evita que el error `#N/A` sea visible en la celda

### Comparación BUSCARV vs BUSCARH

| | BUSCARV | BUSCARH |
|---|---|---|
| Busca en... | Primera **columna** | Primera **fila** |
| Devuelve de... | Misma **fila** | Misma **columna** |
| Indicador | Número de **columna** | Número de **fila** |
| Cuándo usar | Datos en columnas | Datos en filas |

---

## Guía rápida PA3

### Caso 1 — Regiones (SUMA + PROMEDIO + BUSCARH)
```excel
=SUMA(rango_columna)
=PROMEDIO(rango_columna)
=BUSCARH($F$8; matriz_tabla; indicador_fila; FALSO)
```
- `$F$8` = celda selector de zona fija con `$`
- `indicador_fila`: 2=primer dato, 3=segundo, etc.

### Caso 2 — Empleados (CONCATENAR + BUSCARV)
```excel
=A2&"@empresa.com"
=BUSCARV($A$2; tabla_empleados; indicador_col; FALSO)
```

### Errores comunes
| Error | Causa | Solución |
|---|---|---|
| `#N/A` | Valor no encontrado | Revisar que el código exista, usar ESNOD |
| `#REF!` | Indicador > nº columnas/filas | Reducir el indicador |
| `#VALOR!` | Tipo de dato incorrecto | Verificar que el valor buscado coincida en tipo |

---

## Cómo agregar una semana nueva

1. Agregar sección `## SEMANA N` a este archivo con las funciones cubiertas
2. En `excel_tool.sh`:
   - Crear función `asistente_semana_n()` o `guia_semana_n()`
   - Agregar opción al `case` del menú principal
   - Agregar línea al menú con el número de opción
