# Proyecto: normalización de base de datos para un comedor popular

## 1. Objetivo del proyecto

Diseñar una base de datos para un comedor popular que permita registrar:

- Comedor, trabajadores y cargos.
- Proveedores.
- Productos e ingresos de insumos.
- Familias y beneficiarios.
- Menús preparados.
- Ingredientes usados por menú.
- Consumos realizados por los beneficiarios.

El modelo parte de una tabla sin normalizar y evoluciona hasta Tercera Forma Normal (3FN). La decisión final no busca crear tablas por crear, sino separar datos cuando existe una razón técnica o de negocio: evitar redundancia, controlar stock, registrar consumos reales y sustentar el apoyo estatal frente a la cantidad real de beneficiarios atendidos.

---

## Validación contra los puntos del proyecto del curso

Este documento reemplaza el avance inicial del curso y mantiene cubiertos los puntos solicitados para el diseño de base de datos.

```text
/home/ilkay/Documentos/UTP/CICLO_V/BASE_DE_DATOS_2/
```

El proyecto original del curso estaba planteado como un modelo inicial de inventario con 7 tablas:

```text
categorias
productos
proveedores
ingreso
detalle_ingreso
consumo
detalle_consumo
```

El modelo final de este documento es una versión ampliada y corregida para representar mejor el proceso real del comedor popular. La diferencia principal es que ya no se descuenta stock por cada beneficiario que consume, sino por los insumos usados al preparar el menú.

Por eso:

- `detalle_ingreso` se mantiene, porque un ingreso puede traer varios productos.
- `detalle_consumo` se reemplaza por `detalle_menu`, porque los insumos se consumen en la preparación del menú.
- `consumos` queda para registrar personas atendidas, precios cobrados y trazabilidad social.
- Se agregan beneficiarios, familias y tipos de beneficiario porque el profesor observó el tema del apoyo estatal y la diferencia entre población registrada y atendida.

### Correspondencia con los puntos solicitados

| Punto del proyecto | Estado en este documento | Ubicación |
|---|---|---|
| 2.1 Problemática | Cubierto | Sección 2 |
| 2.2 Análisis del proceso real | Cubierto y ampliado | Secciones 1, 2, 4, 7 y 8 |
| 2.3 Identificación de entidades | Cubierto | Sección 4 |
| 2.4 Definición de atributos | Cubierto | Sección 4.1 a 4.15 |
| 2.5 Relaciones entre tablas | Cubierto | Sección 5 |
| 2.6 Uso de tablas detalle | Cubierto y corregido | Secciones 3, 6 y 7 |
| 2.7 Corrección de tipos de datos | Cubierto parcialmente | Sección 4; falta convertir a script SQL final si se entrega como implementación |
| 2.8 Modelo relacional resultante | Cubierto | Sección 5 y archivo Draw.io |
| 2.9 Código SQL corregido | Pendiente si el profesor pide script final | Este documento define el modelo; el SQL puede generarse desde las tablas finales |
| 2.10 Archivo XML Draw.io | Cubierto | `/home/ilkay/Documentos/UTP/CICLO_V/BASE_DE_DATOS_2/diagrams/comedor_popular_mer.drawio` |
| 2.11 Conclusión del diseño | Cubierto | Sección 12 |

Conclusión de la validación:

> El documento cumple los puntos conceptuales del proyecto. La única diferencia frente al avance inicial del curso es intencional: el modelo pasó de un inventario simple de 7 tablas a un modelo final más realista de comedor popular con 15 tablas. Esta ampliación debe explicarse como evolución del análisis, no como contradicción.

---

## 2. Tabla inicial sin normalizar

La tabla inicial concentraba información de proveedores, productos, beneficiarios, menús y consumos en una sola estructura.

```text
comedor_todo
```

| Campo | Descripción |
|---|---|
| id | Identificador del registro |
| fecha_ingreso | Fecha de recepción del insumo |
| nombre_proveedor | Nombre del proveedor |
| telefono_proveedor | Teléfono del proveedor |
| direccion_proveedor | Dirección del proveedor |
| productos | Lista de productos separados por comas |
| cantidades | Lista de cantidades separadas por comas |
| categorias | Lista de categorías separadas por comas |
| nombre_beneficiario | Persona atendida |
| dni_beneficiario | DNI de la persona |
| fecha_consumo | Fecha del consumo |
| tipo_menu | Desayuno, almuerzo o cena |
| descripcion_menu | Menú entregado |

Ejemplo:

```text
productos  = "Arroz, Aceite, Lenteja"
cantidades = "50, 20, 30"
categorias = "Granos, Abarrotes, Menestras"
```

Problemas detectados:

- Una misma celda contiene varios valores.
- Para consultar un producto hay que dividir texto.
- Los datos del proveedor se repiten muchas veces.
- Si cambia el teléfono de un proveedor, hay que actualizar varias filas.
- No se pueden aplicar claves foráneas sobre listas de texto.
- El ingreso de insumos, el menú y el consumo están mezclados.
- No hay forma limpia de calcular stock real.
- No se puede diferenciar bien entre beneficiarios registrados y no registrados.

---

## 3. Proceso de normalización

### 3.1 Primera Forma Normal: 1FN

Regla aplicada:

> Cada columna debe contener un solo valor atómico.

La lista de productos se convierte en filas individuales.

Antes:

```text
Ingreso 1 | Arroz, Aceite, Lenteja | 50, 20, 30
```

Después:

```text
Ingreso 1 | Arroz   | 50
Ingreso 1 | Aceite  | 20
Ingreso 1 | Lenteja | 30
```

Estructura conceptual en 1FN:

```text
ingresos_1fn
- id_detalle
- id_ingreso
- fecha_ingreso
- nombre_proveedor
- telefono_proveedor
- direccion_proveedor
- nombre_producto
- categoria
- cantidad
```

Resultado:

- Se eliminan listas dentro de columnas.
- Cada producto recibido queda en una fila.
- Todavía se repiten proveedor, teléfono, dirección y fecha.

Defensa:

> 1FN mejora la consulta sobre productos porque ya no procesa texto separado por comas, pero todavía mantiene mucha redundancia.

---

### 3.2 Segunda Forma Normal: 2FN

Regla aplicada:

> Los atributos deben depender de la clave completa y no de una parte de ella.

Se detectan dependencias claras:

```text
id_proveedor -> nombre_proveedor, telefono, direccion
id_producto  -> nombre_producto, categoria, unidad_medida
```

Por eso se separan datos maestros:

```text
proveedores
categorias
productos
ingresos_2fn
```

Ejemplo:

```text
proveedores
1 | Alicorp | 999999999 | Lima

productos
1 | Arroz | Granos | kg

ingresos_2fn
id_ingreso | fecha | id_proveedor | id_producto | cantidad
```

Resultado:

- El proveedor se registra una sola vez.
- El producto se registra una sola vez.
- Si cambia la razón social, teléfono o dirección de un proveedor, se actualiza una fila.
- Todavía se repiten datos de cabecera del ingreso por cada producto recibido.

Defensa:

> 2FN elimina la repetición de datos maestros como proveedores y productos. Sin embargo, todavía no separa completamente la cabecera del ingreso y su detalle.

---

### 3.3 Tercera Forma Normal: 3FN

Regla aplicada:

> Ningún atributo no clave debe depender de otro atributo no clave.

Se separa la cabecera del ingreso y su detalle.

```text
ingresos
- id_ingreso
- id_comedor
- id_proveedor
- id_trabajador
- fecha_ingreso
- documento

detalle_ingreso
- id_detalle_ingreso
- id_ingreso
- id_producto
- cantidad
- fecha_vencimiento
- costo_unitario
```

Ejemplo:

```text
ingresos
10 | Comedor Santa Rosa | Alicorp | 2026-06-20 | GUIA-001

detalle_ingreso
1 | 10 | Arroz   | 50 kg
2 | 10 | Aceite  | 20 lt
3 | 10 | Lenteja | 30 kg
```

Resultado:

- La fecha del ingreso se guarda una sola vez.
- El proveedor se guarda una sola vez por ingreso.
- Cada producto recibido queda en el detalle.
- La fecha de vencimiento pertenece al lote recibido, no al producto general.

Defensa:

> 3FN no significa que toda consulta será más rápida. Significa que los datos quedan consistentes, sin duplicidad innecesaria y con relaciones controladas. El costo de algunos JOIN se justifica porque se evitan errores de actualización y se mejora la trazabilidad.

---

## 4. Modelo final propuesto

El modelo final usa 15 tablas.

No se incluye `detalle_consumo`, porque el comedor no descuenta stock por cada persona que come. El stock se reduce por los insumos usados al preparar el menú del día.

Fórmula de stock:

```text
stock_actual = SUM(detalle_ingreso.cantidad) - SUM(detalle_menu.cantidad_usada)
```

---

### 4.1 comedores

Registra el comedor donde ocurren las operaciones.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_comedor | BIGSERIAL | PK |
| nombre | VARCHAR(100) | Nombre del comedor |
| direccion | VARCHAR(200) | Dirección |
| distrito | VARCHAR(100) | Distrito |

---

### 4.2 cargos

Catálogo de cargos del personal.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_cargo | BIGSERIAL | PK |
| nombre | VARCHAR(50) | Ejemplo: administrador, cocinero, apoyo |

Defensa:

> Si una persona cambia de cargo, se actualiza su `id_cargo` en trabajadores. No se repite el nombre del cargo en varias filas.

---

### 4.3 trabajadores

Personal del comedor.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_trabajador | BIGSERIAL | PK |
| id_comedor | BIGINT | FK a comedores |
| id_cargo | BIGINT | FK a cargos |
| nombres | VARCHAR(100) | Nombres |
| apellidos | VARCHAR(100) | Apellidos |
| dni | VARCHAR(8) | Documento |
| telefono | VARCHAR(20) | Teléfono |
| activo | BOOLEAN | Vigente o no |

---

### 4.4 proveedores

Datos de proveedores.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_proveedor | BIGSERIAL | PK |
| ruc | VARCHAR(11) | RUC |
| nombre | VARCHAR(100) | Razón social |
| telefono | VARCHAR(20) | Teléfono |
| direccion | VARCHAR(200) | Dirección |
| activo | BOOLEAN | Vigente o no |

Defensa:

> Si Backus, Alicorp u otro proveedor cambia de razón social, teléfono o dirección, se modifica una sola fila en `proveedores`, no todos los ingresos históricos.

---

### 4.5 categorias

Clasificación de productos.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_categoria | BIGSERIAL | PK |
| nombre | VARCHAR(50) | Ejemplo: granos, abarrotes, verduras |

---

### 4.6 productos

Catálogo de insumos.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_producto | BIGSERIAL | PK |
| id_categoria | BIGINT | FK a categorias |
| nombre | VARCHAR(100) | Arroz, lenteja, aceite |
| unidad_medida | VARCHAR(20) | kg, lt, unidad |
| stock_minimo | NUMERIC(12,2) | Nivel mínimo referencial |
| activo | BOOLEAN | Vigente o no |

Defensa:

> El stock actual no se guarda directamente en productos porque puede calcularse desde ingresos menos lo usado en menús. Guardarlo como campo fijo puede generar inconsistencias si alguien olvida actualizarlo.

---

### 4.7 ingresos

Cabecera de recepción de insumos.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_ingreso | BIGSERIAL | PK |
| id_comedor | BIGINT | FK a comedores |
| id_proveedor | BIGINT | FK a proveedores |
| id_trabajador | BIGINT | FK a trabajadores |
| fecha_ingreso | TIMESTAMP | Fecha de recepción |
| documento | VARCHAR(30) | Guía, boleta u otro |

---

### 4.8 detalle_ingreso

Productos recibidos en cada ingreso.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_detalle_ingreso | BIGSERIAL | PK |
| id_ingreso | BIGINT | FK a ingresos |
| id_producto | BIGINT | FK a productos |
| cantidad | NUMERIC(12,2) | Cantidad recibida |
| fecha_vencimiento | DATE | Vencimiento del lote |
| costo_unitario | NUMERIC(12,2) | Costo de referencia |

Defensa:

> La fecha de vencimiento va en `detalle_ingreso`, no en `productos`, porque el arroz recibido hoy puede vencer en una fecha y el arroz recibido el próximo mes en otra.

---

### 4.9 tipos_beneficiario

Permite diferenciar beneficiarios registrados y no registrados.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_tipo_beneficiario | BIGSERIAL | PK |
| nombre | VARCHAR(50) | Registrado, no registrado, visitante |

Defensa:

> Esta tabla sirve para comparar el apoyo estatal asignado contra la demanda real. El Estado puede entregar insumos considerando cierta cantidad de familias o personas registradas, pero el comedor puede atender también no registrados o visitantes.

---

### 4.10 familias

Agrupa beneficiarios por hogar.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_familia | BIGSERIAL | PK |
| codigo_padron | VARCHAR(30) | Código si existe padrón estatal o municipal |
| direccion | VARCHAR(200) | Dirección |
| distrito | VARCHAR(100) | Distrito |

Defensa:

> La familia representa el hogar mapeado. No se usa una familia genérica; si una persona todavía no pertenece a una familia registrada, su `id_familia` puede quedar vacío hasta regularizarse.

---

### 4.11 beneficiarios

Personas que consumen en el comedor.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_beneficiario | BIGSERIAL | PK |
| id_tipo_beneficiario | BIGINT | FK a tipos_beneficiario |
| id_familia | BIGINT | FK a familias, opcional |
| nombres | VARCHAR(100) | Nombres |
| apellidos | VARCHAR(100) | Apellidos |
| dni | VARCHAR(8) | Documento |
| activo | BOOLEAN | Vigente o no |

Defensa:

> El tipo se guarda en beneficiarios porque el consumo ocurre por persona. Puede existir una persona registrada, no registrada o visitante, con o sin familia regularizada.

---

### 4.12 tipos_menu

Catálogo de tipos de atención.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_tipo_menu | BIGSERIAL | PK |
| nombre | VARCHAR(50) | Desayuno, almuerzo, cena |

Defensa:

> Conviene mantener esta tabla porque hoy puede existir desayuno y almuerzo, pero luego puede agregarse cena sin modificar la estructura de `menus`.

---

### 4.13 menus

Menú preparado por fecha.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_menu | BIGSERIAL | PK |
| id_comedor | BIGINT | FK a comedores |
| id_tipo_menu | BIGINT | FK a tipos_menu |
| id_trabajador | BIGINT | FK a trabajadores |
| fecha | DATE | Día del menú |
| nombre | VARCHAR(100) | Ejemplo: lentejas con arroz |
| cantidad_menus | INT | Cantidad de menús preparados |
| precio_base | NUMERIC(10,2) | Precio regular del menú |

Defensa:

> `cantidad_menus` representa cuántos menús se prepararon. No se manejan estados como planificado o preparado porque sería exagerado para un comedor popular.

---

### 4.14 detalle_menu

Insumos usados para preparar un menú.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_detalle_menu | BIGSERIAL | PK |
| id_menu | BIGINT | FK a menus |
| id_producto | BIGINT | FK a productos |
| cantidad_usada | NUMERIC(12,2) | Cantidad usada del insumo |

Defensa:

> Si se prepararon lentejas con arroz y pescado, aquí se registra cuánto arroz, lenteja, aceite o pescado se usó. Con eso se descuenta el stock de manera lógica, sin registrar una salida por cada persona.

---

### 4.15 consumos

Registro de personas atendidas.

| Campo | Tipo sugerido | Comentario |
|---|---|---|
| id_consumo | BIGSERIAL | PK |
| id_beneficiario | BIGINT | FK a beneficiarios |
| id_menu | BIGINT | FK a menus |
| id_trabajador | BIGINT | FK a trabajadores |
| fecha_hora | TIMESTAMP | Momento del consumo |
| precio_cobrado | NUMERIC(10,2) | Precio aplicado al beneficiario |
| monto_pagado | NUMERIC(10,2) | Monto realmente pagado |

Defensa:

> El precio base está en `menus`, pero el precio cobrado se guarda en `consumos` porque puede variar por beneficiario o por decisión social. Además, permite conservar el histórico aunque luego cambie el precio del menú.

---

## 5. Relaciones principales

```text
comedores 1 ─── N trabajadores
cargos 1 ─── N trabajadores

comedores 1 ─── N ingresos
proveedores 1 ─── N ingresos
trabajadores 1 ─── N ingresos
ingresos 1 ─── N detalle_ingreso
productos 1 ─── N detalle_ingreso
categorias 1 ─── N productos

tipos_beneficiario 1 ─── N beneficiarios
familias 1 ─── N beneficiarios
beneficiarios 1 ─── N consumos

comedores 1 ─── N menus
tipos_menu 1 ─── N menus
trabajadores 1 ─── N menus
menus 1 ─── N detalle_menu
productos 1 ─── N detalle_menu

menus 1 ─── N consumos
trabajadores 1 ─── N consumos
```

---

## 6. Por qué no se unieron productos, consumo y detalle de consumo

La propuesta final no une esas tablas porque representan hechos distintos:

| Concepto | Qué representa |
|---|---|
| productos | Catálogo de insumos: arroz, aceite, lenteja |
| detalle_menu | Insumos usados para preparar un menú |
| consumos | Personas que recibieron un menú |

Si se juntan, se mezclan tres niveles:

```text
producto      = insumo
menú          = preparación diaria
consumo       = persona atendida
```

Ejemplo:

```text
Menú: lentejas con arroz
Cantidad preparada: 200 menús
Insumos usados: 20 kg arroz, 15 kg lenteja, 5 lt aceite
Consumos: 180 beneficiarios atendidos
```

No sería correcto repetir los 20 kg de arroz en cada uno de los 180 consumos. Eso inflaría datos y dificultaría explicar el stock.

---

## 7. Control de stock

El stock se calcula con dos fuentes:

Entradas:

```text
detalle_ingreso.cantidad
```

Salidas por preparación:

```text
detalle_menu.cantidad_usada
```

Consulta conceptual:

```sql
SELECT
    p.nombre,
    COALESCE(SUM(di.cantidad), 0) - COALESCE(SUM(dm.cantidad_usada), 0) AS stock_actual
FROM productos p
LEFT JOIN detalle_ingreso di ON di.id_producto = p.id_producto
LEFT JOIN detalle_menu dm ON dm.id_producto = p.id_producto
GROUP BY p.nombre;
```

Para implementación real, esa consulta debe ajustarse para evitar duplicidad por uniones agregando primero ingresos y usos por producto.

Defensa:

> No se necesita una tabla adicional de salidas si el comedor descuenta insumos al registrar el menú preparado. Para este alcance, `detalle_menu` funciona como la salida real de stock.

---

## 8. Tipo de beneficiario y apoyo estatal

`tipos_beneficiario` se mantiene porque permite sustentar la diferencia entre beneficiarios registrados y personas realmente atendidas.

Ejemplo:

```text
Registrado
No registrado
Visitante
```

Uso práctico:

```sql
SELECT
    tb.nombre AS tipo_beneficiario,
    COUNT(*) AS menus_consumidos
FROM consumos c
JOIN beneficiarios b ON b.id_beneficiario = c.id_beneficiario
JOIN tipos_beneficiario tb ON tb.id_tipo_beneficiario = b.id_tipo_beneficiario
GROUP BY tb.nombre;
```

Defensa:

> Si el Estado entrega apoyo para cierta cantidad de familias o beneficiarios registrados, el comedor puede demostrar cuántos consumos corresponden a registrados y cuántos a no registrados o visitantes. Esto ayuda a justificar mayor apoyo o explicar por qué el stock se consume más rápido.

---

## 9. Resultados de benchmark obtenidos

### Consulta de ingresos por proveedor y producto

| Modelo | Promedio | Mediana | Mínimo | Máximo |
|---|---:|---:|---:|---:|
| Sin normalizar | 10.871 ms | 9.832 ms | 9.193 ms | 13.346 ms |
| 1FN | 3.337 ms | 3.613 ms | 2.438 ms | 4.012 ms |
| 2FN | 4.638 ms | 4.496 ms | 3.435 ms | 5.689 ms |
| 3FN | 7.063 ms | 6.471 ms | 5.820 ms | 9.756 ms |

Interpretación:

> 1FN fue más rápida en esa consulta porque tiene menos JOIN, pero mantiene redundancia. 3FN tarda más que 1FN en esa consulta específica, pero evita inconsistencias y mejora las actualizaciones.

### Actualización de datos maestros de proveedor

| Modelo | Filas modificadas | Promedio |
|---|---:|---:|
| Sin normalizar | 2 500 | 8.798 ms |
| 1FN | 7 500 | 20.928 ms |
| 2FN | 1 | 0.103 ms |
| 3FN | 1 | 0.124 ms |

Interpretación:

> Cuando cambia un dato del proveedor, 3FN modifica una sola fila. En 1FN se modifican miles de filas porque los datos están repetidos.

---

## 10. Guion para exposición

### Inicio

> Nuestro proyecto diseña una base de datos para un comedor popular. El sistema registra insumos, proveedores, trabajadores, beneficiarios, menús preparados y consumos. Partimos de una tabla sin normalizar y aplicamos 1FN, 2FN y 3FN para reducir redundancia y mejorar consistencia.

### Tabla sin normalizar

> En la tabla inicial había columnas como productos, cantidades y categorías con listas separadas por comas. Eso dificulta consultas, validaciones y control de stock, porque PostgreSQL no puede tratar cada producto como una entidad independiente.

### 1FN

> En 1FN convertimos cada producto en una fila. Ya no hay listas dentro de columnas. Sin embargo, los datos del proveedor y la fecha del ingreso se siguen repitiendo.

### 2FN

> En 2FN separamos datos maestros como proveedores, categorías y productos. Esto permite que, si cambia la razón social o teléfono de un proveedor, se actualice una sola fila.

### 3FN

> En 3FN separamos cabeceras y detalles. Un ingreso guarda proveedor, comedor, trabajador y fecha; el detalle guarda los productos recibidos. También separamos menú, detalle de menú y consumo, porque una cosa es preparar alimentos y otra registrar quién consumió.

### Stock

> El stock no se descuenta por cada persona que come, sino por los insumos usados al preparar el menú. Por ejemplo, si hoy se prepararon 200 menús de lentejas con arroz y se usaron 20 kg de arroz, esa cantidad se registra en detalle_menu. Luego el stock se calcula como ingresos menos cantidades usadas.

### Tipo de beneficiario

> Agregamos tipo de beneficiario para diferenciar registrados, no registrados y visitantes. Esto permite comparar el apoyo estatal asignado con la demanda real del comedor. Si el Estado entrega insumos para cierta población registrada, pero el comedor atiende más personas, el sistema puede demostrarlo con datos.

### Cierre

> El modelo final tiene más tablas que una tabla plana, pero cada tabla tiene una responsabilidad. No se normalizó por teoría solamente, sino para evitar duplicidad, controlar stock, registrar consumos reales y sustentar mejor la gestión del comedor.

---

## 11. Preguntas posibles del profesor

### ¿Por qué tantas tablas?

Porque cada tabla representa una entidad o hecho diferente. Un proveedor no es un producto, un ingreso no es un consumo, y preparar un menú no es lo mismo que entregarlo a una persona.

### ¿Más tablas no cuestan más?

Sí, más tablas pueden implicar más JOIN en algunas consultas. Pero el costo se justifica cuando evita duplicidad, inconsistencias y actualizaciones masivas. Además, los JOIN se optimizan con índices.

### ¿Por qué no guardar stock_actual en productos?

Porque sería un dato calculado. Si se guarda y alguien olvida actualizarlo, queda inconsistente. Para este modelo, el stock se calcula desde entradas menos uso en menús.

### ¿Por qué no existe detalle_consumo?

Porque el consumo registra personas atendidas, no insumos usados por persona. Los insumos se descuentan cuando se prepara el menú, mediante `detalle_menu`.

### ¿Por qué precio_base está en menus y precio_cobrado en consumos?

Porque el menú tiene un precio regular, pero el precio aplicado puede variar por beneficiario o situación social. Guardar `precio_cobrado` conserva el histórico real del consumo.

### ¿Por qué tipo_beneficiario está en beneficiarios?

Porque el consumo ocurre por persona. Una persona puede ser registrada, no registrada o visitante. La familia agrupa personas, pero el beneficiario es quien consume.

---

## 12. Conclusión técnica

El modelo final recomendado es 3FN con 15 tablas. La estructura evita redundancia, permite controlar stock desde los insumos usados, registra beneficiarios reales y permite sustentar diferencias entre apoyo estatal asignado y consumo real.

La normalización no se defiende diciendo que siempre será más rápida. Se defiende porque mejora integridad, mantenimiento, trazabilidad y consistencia de datos.
