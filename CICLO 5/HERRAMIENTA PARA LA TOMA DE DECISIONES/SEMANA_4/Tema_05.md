# Tema 05: Funciones Estadísticas Parte 1

**Fuente:** Aprendizaje Virtual UTP. (2026). *Funciones estadísticas parte 1* [Video]. Canal YouTube Aprendizaje Virtual UTP.

---

## Definición

El tema aborda la introducción y aplicación práctica de las **funciones estadísticas en Excel**, enseñando cómo gestionar, auditar y resumir grandes volúmenes de datos mediante fórmulas que automatizan el conteo, el cálculo de promedios y la extracción de valores específicos.

---

## Características clave

- **Evaluación de rangos:** todas las funciones operan seleccionando una matriz o bloque de celdas específico.
- **Uso de criterios lógicos:** se usan comillas para definir condiciones textuales o numéricas (ej. `"M"` para masculino, `"C"` para casado, `">120"` para cuota mayor a 120).
- **Referencias absolutas:** permiten arrastrar fórmulas sin que el rango varíe, modificando solo el criterio.
- **Comodines en texto:** el `*` reemplaza cualquier secuencia de caracteres y el `?` reemplaza un solo carácter exacto.
- **Escalabilidad:** progresa de funciones simples (sin condición) a funciones avanzadas con múltiples criterios simultáneos.

---

## Funciones desarrolladas

### Bloque 1 — Funciones de conteo

| Función | Sintaxis | Descripción |
|---|---|---|
| `CONTAR.BLANCO` | `=CONTAR.BLANCO(rango)` | Cuenta celdas **vacías** |
| `CONTARA` | `=CONTARA(rango)` | Cuenta celdas con **cualquier tipo de dato** |
| `CONTAR` | `=CONTAR(valor1; [valor2]; ...)` | Cuenta celdas que contienen **solo números** |
| `CONTAR.SI` | `=CONTAR.SI(rango; condición)` | Cuenta celdas que cumplen **un** criterio |
| `CONTAR.SI.CONJUNTO` | `=CONTAR.SI.CONJUNTO(rango1; criterio1; rango2; criterio2...)` | Cuenta celdas que cumplen **múltiples** criterios |

### Bloque 2 — Funciones de promedio

| Función | Sintaxis | Descripción |
|---|---|---|
| `PROMEDIO` | `=PROMEDIO(número1; número2; ...)` | Media aritmética sin filtro |
| `PROMEDIO.SI` | `=PROMEDIO.SI(rango; criterio; rango_promedio)` | Promedio con **un** criterio |
| `PROMEDIO.SI.CONJUNTO` | `=PROMEDIO.SI.CONJUNTO(rango_promedio; rango_criterio1; criterio1; ...)` | Promedio con **múltiples** criterios |

### Bloque 3 — Funciones de valores extremos

| Función | Sintaxis | Descripción |
|---|---|---|
| `MAX` | `=MAX(número1; [número2]; ...)` | Valor **máximo** de un rango |
| `MIN` | `=MIN(número1; [número2]; ...)` | Valor **mínimo** de un rango |
| `K.ESIMO.MAYOR` | `=K.ESIMO.MAYOR(Matriz; K)` | K-ésimo valor **mayor** (ej. 2° más alto) |
| `K.ESIMO.MENOR` | `=K.ESIMO.MENOR(Matriz; K)` | K-ésimo valor **menor** (ej. 3° más bajo) |

---

## Uso de comodines en CONTAR.SI

| Criterio | Significado | Ejemplo |
|---|---|---|
| `"C*"` | Comienza con C | Apellidos que empiezan con C |
| `"*S*"` | Contiene S | Nombres que contienen la letra S |
| `"*Z"` | Termina con Z | Apellidos que terminan en Z |
| `"?A*"` | A en la segunda posición | Nombres cuya 2ª letra es A |

---

## Ejercicios resueltos

| # | Requerimiento | Función | Criterio / Detalle |
|---|---|---|---|
| 1 | Estudiantes sin datos de edad | `CONTAR.BLANCO` | Rango columna Edad |
| 2 | Alumnos que registraron teléfono | `CONTARA` | Rango columna Teléfono |
| 3 | Socios por tipo (A, B o C) | `CONTAR.SI` | Criterio: `"A"`, `"B"`, `"C"` con ref. absoluta |
| 4 | Socios que aportaron cuota | `CONTAR.SI` | Criterio: `">0"` en columna Cuota |
| 5 | Socios con cuota mayor a 120 | `CONTAR.SI` | Criterio: `">120"` |
| 6 | Socios con cuota de 100 y de 120 | `CONTAR.SI + CONTAR.SI` | Suma de dos funciones con criterios exactos |
| 7 | Apellidos que comienzan con C | `CONTAR.SI` | Criterio: `"C*"` |
| 8 | Nombres que contienen la letra S | `CONTAR.SI` | Criterio: `"*S*"` |
| 9 | Apellidos que terminan en Z | `CONTAR.SI` | Criterio: `"*Z"` |
| 10 | Nombres con A en la 2ª posición | `CONTAR.SI` | Criterio: `"?A*"` |
| 11 | Personas casadas y varones | `CONTAR.SI.CONJUNTO` | Sexo=`"M"` y E.Civil=`"C"` |
| 12 | Personas casadas en Surco | `CONTAR.SI.CONJUNTO` | E.Civil=`"C"` y Distrito=`"Surco"` |
| 13 | Mujeres solteras de tipo B | `CONTAR.SI.CONJUNTO` | Sexo=`"F"`, E.Civil=`"S"`, Tipo=`"B"` |
| 14 | Hombres casados con cuota > 100 | `CONTAR.SI.CONJUNTO` | Sexo=`"M"`, E.Civil=`"C"`, Cuota=`">100"` |
| 15 | Precio máximo de productos | `MAX` | Rango columna Precio → `350` (TV Color) |
| 16 | Precio mínimo de productos | `MIN` | Rango columna Precio → `50` (Radio) |

---

## Aplicaciones prácticas

| Área | Uso |
|---|---|
| **Auditoría de datos** | Detectar registros incompletos (alumnos sin teléfono, trabajadores sin edad) |
| **Recursos Humanos** | Filtrar nóminas por perfil demográfico (género, estado civil, distrito) |
| **Finanzas** | Analizar promedios de cuotas con múltiples condiciones simultáneas |
| **Control de inventarios** | Identificar el producto más caro y más barato del almacén |

---

## Resumen

> El video concluye con la despedida: *"Nos vemos en la parte 2 del video"*, confirmando que esta sesión cubre el primer bloque de funciones estadísticas (conteo, valores extremos), dejando promedios avanzados y funciones de posición para la siguiente parte.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones estadísticas parte 1* [Video educativo]. Curso Herramientas informáticas para la toma de decisiones, Semana 4.
