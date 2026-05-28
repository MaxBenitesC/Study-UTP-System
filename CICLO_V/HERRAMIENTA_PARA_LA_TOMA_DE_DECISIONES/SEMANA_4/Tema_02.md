# Tema 02: Funciones Complementarias de Fecha en Excel

**Fuente:** Anampa, J. P. (2026). *Funciones complementarias de fecha en Excel* [Video]. Universidad Tecnológica del Perú.

---

## Definición

El tema explica cómo calcular con precisión el **tiempo de servicio de un trabajador** utilizando funciones complementarias de fecha en Microsoft Excel. El objetivo es determinar la antigüedad exacta desglosada en años, meses y días.

---

## Funciones descritas

### Función HOY()

Obtiene la **fecha actual del sistema** de forma dinámica. Se actualiza automáticamente según el calendario del equipo, eliminando la necesidad de ingresar la fecha manualmente.

```
=HOY()   →   devuelve la fecha de hoy
```

### Función SI.FECHA()

Es la herramienta principal para calcular la **diferencia entre dos fechas**. Se trata de una función "oculta": no aparece en el autocompletado de versiones antiguas de Excel, pero es completamente funcional.

**Sintaxis:**
```
=SI.FECHA(fecha_inicio, fecha_fin, unidad)
```

| Parámetro | Descripción |
|---|---|
| `fecha_inicio` | Fecha desde la cual se inicia el conteo |
| `fecha_fin` | Fecha hasta la cual se calcula (puede ser `HOY()`) |
| `unidad` | Código que define la unidad de medida del resultado |

**Códigos de unidad disponibles:**

| Código | Devuelve |
|---|---|
| `"Y"` | Años completos entre las dos fechas |
| `"YM"` | Meses restantes ignorando los años ya contados |
| `"MD"` | Días restantes tras contabilizar meses y años completos |

---

## Aplicaciones prácticas

| Área | Uso |
|---|---|
| **Recursos Humanos** | Cálculo de liquidaciones, años de antigüedad para bonos y progresión de carrera |
| **Administración de Proyectos** | Medición del tiempo transcurrido desde el inicio de una obra o tarea |
| **Control de Inventarios** | Seguimiento del tiempo de permanencia de productos en almacén |

---

## Ejemplo práctico

**Dato:** trabajador con fecha de inicio el **2 de febrero de 2010**.

| Cálculo | Fórmula | Resultado |
|---|---|---|
| Años de servicio | `=SI.FECHA("02/02/2010", HOY(), "Y")` | `15 años` |
| Meses restantes | `=SI.FECHA("02/02/2010", HOY(), "YM")` | `2 meses` |
| Días restantes | `=SI.FECHA("02/02/2010", HOY(), "MD")` | `17 días` |

> Tiempo de servicio total: **15 años, 2 meses y 17 días**

---

## Características clave

- **Automatización:** `HOY()` se actualiza sola sin intervención del usuario.
- **Precisión:** Extrae diferencias exactas sin errores de redondeo manual.
- **Función oculta:** `SI.FECHA` no aparece en el autocompletado pero es completamente válida en todas las versiones de Excel.

---

## Resumen

`SI.FECHA` combinada con `HOY()` es la forma más eficiente de gestionar datos temporales en entornos profesionales, permitiendo desglosar la antigüedad de un trabajador en años, meses y días con una sola función y sin cálculos manuales.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones complementarias de fecha en Excel* [Video educativo]. Curso Herramientas informáticas para la toma de decisiones, Semana 4.
