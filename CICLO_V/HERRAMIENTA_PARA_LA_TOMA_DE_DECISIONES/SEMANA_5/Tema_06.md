# Tema 06: Validación de Datos

## Definición

La validación de datos en Excel permite **controlar el tipo de datos** que se pueden ingresar en una celda. Es útil para evitar errores y mantener la integridad de la información.

---

## Cómo aplicarla

1. Seleccionar las celdas donde se aplicará la validación.
2. Ir a la pestaña **Datos**.
3. Hacer clic en **Validación de datos**.
4. En la ventana que aparece, elegir el tipo de validación.

---

## Tipos de validación disponibles

| Tipo | Descripción |
|---|---|
| **Número entero** | Permite solo valores enteros dentro de un rango definido |
| **Decimal** | Permite números con decimales dentro de un rango |
| **Fecha** | Restringe la entrada a fechas específicas o rangos de fechas |
| **Lista** | Permite solo valores pertenecientes a una lista predefinida |
| **Longitud de texto** | Limita la cantidad de caracteres que se pueden ingresar |

### Ejemplo

Permitir solo números entre 1 y 100 en una columna:

> Seleccionar la columna → Validación de datos → Número entero → Mínimo: `1` → Máximo: `100`

---

## Mensajes personalizados

### Mensaje de entrada

Aparece cuando el usuario selecciona una celda con validación. Sirve para guiar al usuario antes de ingresar el dato.

> Ejemplo: *"Por favor, ingrese un número entre 1 y 100."*

**Configuración:** pestaña **Mensaje de entrada** dentro de Validación de datos.

### Mensaje de error

Aparece cuando se ingresa un valor que no cumple la regla. Ofrece las opciones de volver a intentarlo o cancelar.

> Ejemplo: *"Valor no válido. Ingrese un número entre 1 y 100."*

**Configuración:** pestaña **Mensaje de error** dentro de Validación de datos.

---

## Comportamiento en la celda

| Acción del usuario | Resultado |
|---|---|
| Selecciona la celda | Aparece el **mensaje de entrada** |
| Ingresa un valor válido | Excel lo acepta sin inconvenientes |
| Ingresa un valor no válido | Aparece el **mensaje de error** con opción de reintentar o cancelar |

---

## Conclusión

La validación de datos es una herramienta clave para mantener la **integridad de los datos** en hojas de cálculo compartidas o de uso frecuente, reduciendo errores de ingreso mediante reglas y mensajes claros para el usuario.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Validación de datos* [Video — transcripción]. Curso Herramientas informáticas para la toma de decisiones, Semana 5.
