# Speech de exposición: Gestión y Personal + Consultas

## 1. Introducción

Buenos días, profesor. En esta parte voy a explicar el bloque de **Gestión y Personal** dentro de nuestro modelo de base de datos para un comedor popular.

Nuestro proyecto busca representar el funcionamiento básico de un comedor: registrar el comedor, sus trabajadores, los insumos que ingresan, los productos que se usan para preparar menús, los beneficiarios atendidos y los consumos realizados.

En mi parte me voy a enfocar en dos puntos:

1. El diseño del bloque **Gestión y Personal**.
2. Tres consultas básicas que el comedor podría usar para tomar decisiones reales.

---

## 2. Contexto del comedor y entidad relacionada

Para ubicar el proyecto en un contexto real, revisamos el **Programa de Complementación Alimentaria (PCA)**. Según la Plataforma del Estado Peruano, el PCA pertenece al **MIDIS - Ministerio de Desarrollo e Inclusión Social**.

La página oficial indica que el PCA brinda apoyo alimentario a su población usuaria mediante centros de atención organizados en distintas modalidades. También indica que es un programa descentralizado ejecutado por gobiernos locales en el país.

Fuente oficial:

https://www.gob.pe/114831-programa-complementacion-alimentaria-pca

Esto nos sirve para sustentar que el comedor no funciona como una empresa común, sino como una organización de apoyo social. Por eso el modelo debe registrar bien los insumos, beneficiarios, menús y consumos, pero sin convertir la parte de personal en un sistema de recursos humanos demasiado complejo.

---

## 3. Gestión y Personal

En el bloque de **Gestión y Personal**, al inicio habíamos considerado tres tablas:

```text
comedores
cargos
trabajadores
```

Pero luego decidimos simplificar el diseño y quedarnos solo con:

```text
comedores
trabajadores
```

La tabla `comedores` representa el lugar donde se realiza la atención. Ahí se guardan datos como:

```text
id_comedor
nombre
direccion
distrito
codigo
```

Esta tabla es importante porque permite identificar en qué comedor se registran los ingresos de productos, los menús preparados y los trabajadores asignados.

La tabla `trabajadores` registra a las personas que apoyan o trabajan en el comedor. Guarda datos como:

```text
id_trabajador
id_comedor
dni
nombres
apellidos
telefono
fecha_ingreso
activo
```

La relación es:

```text
Un comedor puede tener muchos trabajadores.
Un trabajador pertenece a un comedor.
```

Por eso la relación es de **1 a N** entre `comedores` y `trabajadores`.

---

## 4. Justificación de quitar la tabla cargos

Quitamos la tabla `cargos` porque, para el alcance de este proyecto, el cargo no necesita una administración separada.

En una empresa grande, sí tendría sentido tener una tabla `cargos`, porque podría haber muchos puestos, cambios frecuentes, historial laboral, sueldos, áreas, contratos y permisos.

Pero en un comedor popular el objetivo principal no es administrar una planilla de personal. El objetivo principal es controlar:

```text
insumos
productos
menús
beneficiarios
consumos
stock
```

La parte de personal se usa principalmente para trazabilidad, es decir, para saber qué trabajador registró un ingreso, un menú o una atención.

Por eso, aunque una normalización estricta podría mantener `cargos`, nosotros decidimos no usarla porque el cargo no cambia constantemente ni es el centro del sistema. En un comedor popular normalmente los roles son pocos y bastante estables: responsable, apoyo, cocina o atención.

Entonces, tener una tabla adicional solo para cargos aumentaba complejidad sin aportar demasiado al objetivo principal.

Una forma de sustentarlo sería decir:

> En este modelo priorizamos la utilidad operativa del comedor. La tabla `cargos` fue retirada porque no se requiere gestionar cargos como entidad independiente. Para el alcance del proyecto, basta con registrar a los trabajadores y asociarlos al comedor correspondiente.

También se puede explicar así:

> No se busca modelar un sistema de recursos humanos, sino un sistema de control de comedor. Por eso la gestión de personal se mantiene simple y suficiente.

---

## 5. Si el profesor pregunta por la 3FN

Si el profesor pregunta si quitar `cargos` afecta la Tercera Forma Normal, se puede responder:

> Sí, en un modelo más estricto se podría separar `cargos` como catálogo. Sin embargo, en este proyecto tomamos una decisión de diseño basada en el alcance real del sistema. Como el comedor no requiere gestionar cargos de forma independiente, no era necesario mantener una tabla adicional. La normalización debe ayudar al sistema, no hacerlo más complicado sin necesidad.

Otra respuesta posible:

> La 3FN es una guía importante para evitar redundancia, pero también se puede ajustar el diseño cuando existe una justificación funcional. En este caso, el cargo no es una entidad crítica del negocio; lo crítico es controlar insumos, menús y beneficiarios.

---

## 6. Consulta 1: stock por categoría seleccionada

### Razón

Queremos que el usuario entre a una pantalla de stock, seleccione una categoría, por ejemplo **Menestras**, y el sistema le muestre los productos de esa categoría con su stock actual y la fecha de vencimiento más cercana.

Esta consulta es necesaria porque el comedor necesita saber qué productos tiene disponibles antes de preparar un menú o antes de solicitar nuevos insumos.

### Consulta

```sql
SELECT
    c.nombre AS categoria,
    p.nombre AS producto,
    p.unidad,
    COALESCE((
        SELECT SUM(di.cantidad)
        FROM detalle_ingreso di
        WHERE di.id_producto = p.id_producto
    ), 0)
    -
    COALESCE((
        SELECT SUM(dm.cantidad_usada)
        FROM detalle_menu dm
        WHERE dm.id_producto = p.id_producto
    ), 0) AS stock_actual,
    (
        SELECT MIN(di.fecha_vencimiento)
        FROM detalle_ingreso di
        WHERE di.id_producto = p.id_producto
    ) AS fecha_vencimiento
FROM productos p
JOIN categorias c
    ON c.id_categoria = p.id_categoria
WHERE c.nombre = 'Menestras'
  AND p.activo = TRUE
ORDER BY p.nombre;
```

### Qué hace el código

La consulta muestra los productos de una categoría específica.

Por ejemplo, si el usuario selecciona `Menestras`, la consulta puede mostrar lenteja, frejol o pallares.

Por cada producto devuelve:

```text
categoría
producto
unidad
stock actual
fecha de vencimiento más cercana
```

El frontend puede tomar estos resultados y decidir cómo mostrarlos: colores, alertas, filtros o mensajes.

---

## 7. Consulta 2: menús preparados y personas atendidas

### Razón

Queremos saber si la cantidad de menús preparados alcanzó para las personas atendidas.

Esto es importante porque el comedor puede demostrar demanda real. Por ejemplo, si se prepararon 100 menús pero se atendieron 120 personas, esa información ayuda a sustentar que se necesitan más insumos o más planificación.

### Consulta

```sql
SELECT
    m.fecha,
    tm.nombre AS tipo_menu,
    m.nombre AS menu,
    m.cantidad_menus,
    COUNT(c.id_consumo) AS personas_atendidas
FROM menus m
JOIN tipos_menu tm
    ON tm.id_tipo_menu = m.id_tipo_menu
LEFT JOIN consumos c
    ON c.id_menu = m.id_menu
WHERE m.fecha = '2026-07-13'
GROUP BY
    m.id_menu,
    m.fecha,
    tm.nombre,
    m.nombre,
    m.cantidad_menus
ORDER BY tm.nombre, m.nombre;
```

### Qué hace el código

La consulta muestra los menús preparados en una fecha específica y cuenta cuántas personas fueron atendidas con cada menú.

Devuelve:

```text
fecha
tipo de menú
nombre del menú
cantidad de menús preparados
personas atendidas
```

Con eso el comedor puede comparar lo preparado contra lo realmente consumido.

---

## 8. Consulta 3: consumos por familia

### Razón

Queremos saber qué familias reciben más apoyo del comedor.

Esto ayuda a identificar familias con mayor dependencia del comedor y también permite tener evidencia social del servicio brindado.

### Consulta

```sql
SELECT
    f.nombre AS familia,
    f.direccion,
    f.telefono,
    COUNT(DISTINCT b.id_beneficiario) AS integrantes_atendidos,
    COUNT(c.id_consumo) AS total_consumos
FROM familias f
JOIN beneficiarios b
    ON b.id_familia = f.id_familia
JOIN consumos c
    ON c.id_beneficiario = b.id_beneficiario
WHERE b.activo = TRUE
GROUP BY
    f.id_familia,
    f.nombre,
    f.direccion,
    f.telefono
ORDER BY total_consumos DESC;
```

### Qué hace el código

La consulta agrupa los consumos por familia.

Devuelve:

```text
familia
dirección
teléfono
cantidad de integrantes atendidos
total de consumos
```

Esto permite saber qué familias son atendidas con más frecuencia.

---

## 9. Si el profesor pregunta qué es COALESCE

`COALESCE` es una función de SQL que devuelve el primer valor que no sea `NULL`.

En nuestras consultas lo usamos porque puede pasar que un producto todavía no tenga ingresos registrados o todavía no haya sido usado en ningún menú.

Por ejemplo:

```sql
COALESCE(SUM(di.cantidad), 0)
```

Significa:

```text
Si la suma tiene valor, usa ese valor.
Si la suma devuelve NULL, usa 0.
```

Esto es importante porque una resta con `NULL` también devuelve `NULL`.

Ejemplo incorrecto:

```text
50 - NULL = NULL
```

Ejemplo correcto con `COALESCE`:

```text
50 - 0 = 50
```

Respuesta corta para exposición:

> Usamos `COALESCE` para evitar valores nulos en los cálculos. Si un producto no tiene ingresos o no tiene usos registrados, SQL podría devolver NULL. Con `COALESCE`, lo convertimos en cero y el cálculo de stock funciona correctamente.

---

## 10. Si el profesor pregunta por qué no usamos un JOIN simple para stock

Para calcular el stock, necesitamos comparar dos cosas:

```text
lo que ingresó
lo que se usó
```

La entrada está en:

```text
detalle_ingreso
```

El uso está en:

```text
detalle_menu
```

Podríamos intentar unir todo con un `JOIN` simple, pero eso puede duplicar cantidades si un producto tiene varios ingresos y varios usos.

Ejemplo:

```text
Arroz tiene 2 ingresos.
Arroz fue usado en 3 menús.
```

Si se unen ambas tablas directamente, se pueden formar combinaciones repetidas:

```text
2 ingresos x 3 usos = 6 filas
```

Eso puede inflar las sumas y dar un stock incorrecto.

Por eso usamos subconsultas: una subconsulta suma los ingresos del producto y otra subconsulta suma los usos del producto. Luego recién se hace la resta.

Respuesta corta para exposición:

> No usamos un JOIN simple para calcular el stock porque podría duplicar cantidades cuando un producto tiene varios ingresos y varios usos. Por eso usamos subconsultas separadas: una suma lo ingresado, otra suma lo usado, y luego restamos ambos resultados.

---

## 11. Conclusión final de Gestión y Personal

En conclusión, el bloque de **Gestión y Personal** se dejó con dos tablas porque el sistema no busca administrar recursos humanos de forma completa, sino mantener trazabilidad básica del comedor.

La tabla `comedores` identifica el lugar donde se realiza la atención, y la tabla `trabajadores` identifica a las personas que registran operaciones o participan en la atención.

Quitamos `cargos` porque no era una entidad crítica para el objetivo del sistema. En este contexto, la prioridad es controlar insumos, menús, beneficiarios y consumos.

Finalmente, las tres consultas elegidas responden preguntas reales del comedor:

```text
¿Cuánto stock queda por categoría?
¿Cuántos menús se prepararon y cuántas personas fueron atendidas?
¿Qué familias reciben más apoyo?
```

Con estas consultas, la base de datos no solo almacena información, sino que ayuda al comedor a tomar decisiones y sustentar pedidos de apoyo.

