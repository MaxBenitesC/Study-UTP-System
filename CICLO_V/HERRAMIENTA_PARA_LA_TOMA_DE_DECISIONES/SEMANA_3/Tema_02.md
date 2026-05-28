# Tema 02: Referencias Relativas y Absolutas

## Definición

En Excel, una **referencia de celda** indica la ubicación de una celda dentro de la hoja de cálculo. Existen tres tipos según su comportamiento al copiar una fórmula:

| Tipo | Descripción |
|---|---|
| **Relativa** | Se ajusta automáticamente al copiar la fórmula a otra celda |
| **Absoluta** | Siempre apunta a la misma celda, sin importar dónde se copie la fórmula |
| **Mixta** | Fija solo la columna o solo la fila |

---

## Referencias Relativas

De forma predeterminada, toda referencia de celda es **relativa**, lo que significa que es relativa a la ubicación de la celda donde se escribe la fórmula.

> Ejemplo: si en `C1` escribimos `=A1+B1` y copiamos hacia abajo, en `C2` se convierte automáticamente en `=A2+B2`.

---

## Referencias Absolutas

Cuando se copia y pega una fórmula múltiples veces y se requiere que una o más referencias **no cambien**, se usan las **referencias absolutas**. Se identifican anteponiendo el símbolo `$` tanto a la columna como a la fila.

| Referencia relativa | Referencia absoluta |
|---|---|
| `A12` | `$A$12` |
| `B6` | `$B$6` |
| `AZ13` | `$AZ$13` |
| `D80` | `$D$80` |

---

## Referencias Mixtas

Cuando se desea fijar **solo la columna** o **solo la fila**, se usan referencias mixtas:

| Notación | Significado |
|---|---|
| `$A12` | Columna **absoluta**, fila relativa |
| `B$6` | Columna relativa, fila **absoluta** |

---

## Comportamiento al copiar una fórmula (2 celdas abajo y 2 a la derecha)

| Referencia original | Tipo original | Referencia resultante | Tipo resultante |
|---|---|---|---|
| `$A$1` | Columna absoluta y fila absoluta | `$A$1` | Absoluta (no cambia) |
| `A$1` | Columna relativa y fila absoluta | `C$1` | Mixta |
| `$A1` | Columna absoluta y fila relativa | `$A3` | Mixta |
| `A1` | Columna relativa y fila relativa | `C3` | Relativa |

> Regla: solo los elementos **sin** `$` se desplazan al copiar la fórmula.

---

## Resumen

| Referencia | Columna | Fila |
|---|---|---|
| `A1` | Relativa | Relativa |
| `$A$1` | Absoluta | Absoluta |
| `$A1` | Absoluta | Relativa |
| `A$1` | Relativa | Absoluta |

El uso correcto de referencias es clave para construir fórmulas eficientes que puedan copiarse sin errores a lo largo de una hoja de cálculo.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Referencias relativas y absolutas* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 3.
