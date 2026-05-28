# Tema 04: Ejercicios de Funciones de Búsqueda — BUSCARV y BUSCARH (Video guía)

> Este tema es el video guía que explica paso a paso cómo aplicar BUSCARV y BUSCARH en ejercicios prácticos de Excel.

---

## Ejercicio 1: Formulario de búsqueda con BUSCARV

### Objetivo

Al ingresar el **ID de un empleado**, todos sus datos (apellidos, nombres, etc.) se completan automáticamente usando BUSCARV sobre la hoja "Tabla 01".

### Pasos

1. Ubicarse en la celda donde se mostrará el primer dato (ej. Apellidos).
2. Escribir la función BUSCARV:

```
=BUSCARV($A$2, Tabla01!$A:$E, 2, FALSO)
```

3. Fijar el `valor_buscado` con **referencia absoluta** (`$A$2`) para que no cambie al arrastrar.
4. Arrastrar la fórmula hacia las demás celdas variando solo el `indicador_columnas`:

| Campo | Indicador de columna |
|---|---|
| ID | 1 |
| Apellidos | 2 |
| Nombres | 3 |
| … | … |

5. Al cambiar el ID ingresado, todos los campos se actualizan dinámicamente.

### Formas de referenciar la matriz

| Método | Ejemplo |
|---|---|
| Nombre de hoja + rango | `Tabla01!$A:$E` |
| Nombre de rango definido | `empleados` |
| Tabla con todos los datos | `empleados[#Todos]` |

### Crear una tabla para simplificar la fórmula

1. Seleccionar el rango de datos.
2. Ir a **Insertar → Tabla** (o `Ctrl + Q`).
3. Asignarle un nombre (ej. `empleados`).
4. Reemplazar el rango en BUSCARV por el nombre de la tabla:

```
=BUSCARV($A$2, empleados, 2, FALSO)
```

---

## Ejercicio 2: Producción por zona con BUSCARH

### Objetivo

Al ingresar el nombre de una **zona** (Sur, Norte, Central), se cargan automáticamente los valores de producción de cada producto usando BUSCARH sobre la tabla "Productos".

### Pasos

1. Crear una tabla con los datos de producción (Insertar → Tabla, nombre: `Productos`).
2. Ubicarse en la celda del primer producto.
3. Escribir la función BUSCARH:

```
=BUSCARH($B$2, Productos[#Datos], 2, FALSO)
```

4. Fijar el `valor_buscado` (la zona) con **referencia absoluta**.
5. Arrastrar la fórmula variando el `indicador_filas` para cada producto.
6. Al cambiar la zona, todos los valores de producción se actualizan automáticamente.

### Nota sobre encabezados en tablas

| Referencia | Incluye encabezados |
|---|---|
| `Tabla[#Datos]` | No (solo los datos) |
| `Tabla[#Todos]` | Sí (encabezados + datos) |

> Si se necesita acceder a los encabezados de la tabla, usar `[#Todos]`.

---

## Conclusiones del video

| Punto | Detalle |
|---|---|
| **BUSCARV busca por columna** | El criterio de búsqueda debe estar en la primera columna del rango (ej. ID de empleado) |
| **BUSCARH busca por fila** | El criterio de búsqueda debe estar en la primera fila del rango (ej. nombre de zona) |
| **Referencia absoluta en el valor buscado** | Impide que la celda del criterio se desplace al arrastrar la fórmula |
| **Tablas con nombre** | Simplifican la escritura de fórmulas y mejoran la legibilidad |
| **FALSO para coincidencia exacta** | Usar siempre `FALSO` cuando el valor debe coincidir exactamente con el buscado |

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Funciones de búsqueda: BUSCARV y BUSCARH* [Video guía — transcripción]. Curso Herramientas informáticas para la toma de decisiones, Semana 6.
