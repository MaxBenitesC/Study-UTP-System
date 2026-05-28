# Tema 02: Funciones Textuales

## Definición

Las funciones textuales permiten manipular cadenas de texto en Excel. El símbolo `&` es el operador básico de concatenación que une cadenas de texto.

```
=A1&B1
```

---

## Funciones principales

### LARGO

Devuelve el número de caracteres de una cadena de texto. Los espacios cuentan como caracteres.

```
=LARGO(texto)
```

---

### MAYUSC / MINUSC

| Función | Efecto |
|---|---|
| `=MAYUSC(texto)` | Convierte todo el texto a **MAYÚSCULAS** |
| `=MINUSC(texto)` | Convierte todo el texto a **minúsculas** |

---

### NOMPROPIO

Cambia a mayúscula solo la primera letra de cada palabra del texto.

```
=NOMPROPIO(texto)
```

---

### DERECHA

Devuelve los últimos caracteres de una cadena de texto según el número especificado.

```
DERECHA(texto, [núm_de_caracteres])
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `texto` | Obligatorio | Cadena de la que se extraen caracteres |
| `núm_de_caracteres` | Opcional | Cantidad de caracteres a extraer desde la derecha |

- Si `núm_de_caracteres` es mayor que la longitud del texto, devuelve todo el texto.
- Si se omite `núm_de_caracteres`, se calcula como `1`.

---

### IZQUIERDA

Devuelve los primeros caracteres de una cadena de texto según el número especificado.

```
IZQUIERDA(texto, [núm_de_caracteres])
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `texto` | Obligatorio | Cadena de la que se extraen caracteres |
| `núm_de_caracteres` | Opcional | Cantidad de caracteres a extraer desde la izquierda |

- `núm_de_caracteres` debe ser mayor o igual a cero.
- Si es mayor que la longitud del texto, devuelve todo el texto.
- Si se omite, se calcula como `1`.

---

### EXTRAE

Devuelve un número específico de caracteres de una cadena, comenzando en la posición indicada.

```
EXTRAE(texto, posición_inicial, núm_de_caracteres)
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `texto` | Obligatorio | Cadena de la que se extraen caracteres |
| `posición_inicial` | Obligatorio | Posición del primer carácter a extraer (la posición 1 es el primer carácter) |
| `núm_de_caracteres` | Obligatorio | Cantidad de caracteres a devolver |

- Si `posición_inicial` es mayor que la longitud del texto, devuelve `""` (texto vacío).
- Si `posición_inicial` + `núm_de_caracteres` supera la longitud, devuelve los caracteres hasta el final.
- Si `núm_de_caracteres` es negativo, devuelve `#VALUE!` (error de valor).

---

### HALLAR

Devuelve la posición de un carácter o texto buscado dentro de una cadena, empezando desde la posición inicial especificada.

```
=HALLAR(texto_buscado, texto, posición_inicial)
```

| Argumento | Descripción |
|---|---|
| `texto_buscado` | El texto que se desea encontrar |
| `texto` | La cadena donde se realiza la búsqueda |
| `posición_inicial` | Posición desde donde se inicia la búsqueda |

---

### IGUAL

Compara dos cadenas de texto. Distingue entre mayúsculas y minúsculas, pero ignora diferencias de formato.

```
=IGUAL(texto1, texto2)
```

| Resultado | Condición |
|---|---|
| `VERDADERO` | Las dos cadenas son exactamente iguales |
| `FALSO` | Las cadenas son diferentes |

| Argumento | Tipo | Descripción |
|---|---|---|
| `texto1` | Obligatorio | Primera cadena de texto |
| `texto2` | Obligatorio | Segunda cadena de texto |

---

### SUSTITUIR

Reemplaza un texto existente por otro texto nuevo dentro de una cadena.

```
=SUSTITUIR(texto, texto_original, texto_nuevo, [núm_de_ocurrencia])
```

| Argumento | Tipo | Descripción |
|---|---|---|
| `texto` | Obligatorio | Texto o referencia de celda donde se desea sustituir |
| `texto_original` | Obligatorio | Texto que se desea reemplazar |
| `texto_nuevo` | Obligatorio | Texto que reemplazará al original |
| `núm_de_ocurrencia` | Opcional | Instancia específica de `texto_original` a reemplazar |

- Si se especifica `núm_de_ocurrencia`, solo se reemplaza esa instancia.
- Si se omite, se reemplazan **todas** las ocurrencias de `texto_original`.

---

## Resumen de funciones

| Función | Descripción breve | Sintaxis |
|---|---|---|
| `&` | Concatena cadenas | `=A1&B1` |
| `LARGO` | Cuenta caracteres | `=LARGO(texto)` |
| `MAYUSC` | Todo en mayúsculas | `=MAYUSC(texto)` |
| `MINUSC` | Todo en minúsculas | `=MINUSC(texto)` |
| `NOMPROPIO` | Primera letra en mayúscula | `=NOMPROPIO(texto)` |
| `DERECHA` | Extrae desde la derecha | `=DERECHA(texto, n)` |
| `IZQUIERDA` | Extrae desde la izquierda | `=IZQUIERDA(texto, n)` |
| `EXTRAE` | Extrae desde una posición | `=EXTRAE(texto, inicio, n)` |
| `HALLAR` | Busca posición de un texto | `=HALLAR(buscado, texto, inicio)` |
| `IGUAL` | Compara dos cadenas | `=IGUAL(texto1, texto2)` |
| `SUSTITUIR` | Reemplaza texto | `=SUSTITUIR(texto, original, nuevo, [n])` |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones textuales* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 5.
