# Tema 01: Funciones Lógicas

## Definición

Las funciones lógicas permiten ejecutar determinadas acciones teniendo como referente un valor lógico que puede ser `VERDADERO` o `FALSO`. Son de gran utilidad para la toma de decisiones, ya que permiten ejecutar una acción en base al resultado de una evaluación lógica.

Las operaciones lógicas fundamentales son: **negación**, **conjunción** y **disyunción**.

---

## Funciones principales

### Función SI

Comprueba si se cumple una condición y devuelve un resultado según si es verdadera o falsa.

| Caso | Resultado |
|---|---|
| La condición **se cumple** | Devuelve `VERDADERO` o ejecuta la operación del lado verdadero |
| La condición **no se cumple** | Devuelve `FALSO` o ejecuta la operación del lado falso |

**Sintaxis:**
```
SI(Condición; Verdadero; Falso)
```

---

### Función Y

Permite exigir el cumplimiento de **varias condiciones** dentro de la función SI.

| Resultado | Condición |
|---|---|
| `VERDADERO` | **Todos** los argumentos son verdaderos |
| `FALSO` | **Al menos uno** de los argumentos es falso |

**Sintaxis:**
```
Y(Valor_lógico1; Valor_lógico2; ...)
```

---

### Función O

Evalúa una serie de comparaciones y devuelve verdadero si al menos una se cumple.

| Resultado | Condición |
|---|---|
| `VERDADERO` | **Al menos una** comparación es verdadera |
| `FALSO` | **Todas** las comparaciones son falsas |

**Sintaxis:**
```
O(Valor_lógico1; Valor_lógico2; ...)
```

---

### Función SI ANIDADA

Consiste en colocar una segunda función SI dentro de la primera para probar condiciones adicionales. Aumenta la flexibilidad de la función SI al ampliar el número de posibles resultados a evaluar.

**Estructura general:**
```
SI(Condición1; Verdadero1; SI(Condición2; Verdadero2; Falso2))
```

---

## Resumen comparativo

| Función | Devuelve VERDADERO cuando… | Devuelve FALSO cuando… |
|---|---|---|
| `SI` | La condición se cumple | La condición no se cumple |
| `Y` | Todos los argumentos son verdaderos | Al menos uno es falso |
| `O` | Al menos un argumento es verdadero | Todos los argumentos son falsos |
| `SI ANIDADA` | Se cumple la condición evaluada en cada nivel | Ningún nivel cumple su condición |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones lógicas* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 5.
