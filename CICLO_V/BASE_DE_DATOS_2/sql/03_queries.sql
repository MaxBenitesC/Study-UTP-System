-- ============================================================
-- Proyecto: COMEDOR POPULAR "MANOS PIURANAS"
-- Archivo: 03_queries.sql
-- Descripcion: Consultas basicas para inventario.
-- ============================================================

-- 1. Listado de productos con su categoria.
SELECT
    p.id_producto,
    p.nombre_producto,
    c.nombre_categoria,
    p.stock,
    p.fecha_vencimiento
FROM productos p
JOIN categorias c ON p.id_categoria = c.id_categoria
ORDER BY c.nombre_categoria, p.nombre_producto;

-- 2. Productos proximos a vencer.
SELECT
    nombre_producto,
    stock,
    fecha_vencimiento
FROM productos
WHERE fecha_vencimiento IS NOT NULL
ORDER BY fecha_vencimiento;

-- 3. Ingresos de productos por proveedor.
SELECT
    i.fecha_ingreso,
    pr.nombre_proveedor,
    p.nombre_producto,
    di.cantidad
FROM detalle_ingreso di
JOIN ingreso i ON di.id_ingreso = i.id_ingreso
JOIN proveedores pr ON i.id_proveedor = pr.id_proveedor
JOIN productos p ON di.id_producto = p.id_producto
ORDER BY i.fecha_ingreso, pr.nombre_proveedor, p.nombre_producto;

-- 4. Consumo de productos por fecha.
SELECT
    co.fecha_consumo,
    p.nombre_producto,
    dc.cantidad
FROM detalle_consumo dc
JOIN consumo co ON dc.id_consumo = co.id_consumo
JOIN productos p ON dc.id_producto = p.id_producto
ORDER BY co.fecha_consumo, p.nombre_producto;

-- 5. Total ingresado por producto.
SELECT
    p.nombre_producto,
    COALESCE(SUM(di.cantidad), 0) AS total_ingresado
FROM productos p
LEFT JOIN detalle_ingreso di ON p.id_producto = di.id_producto
GROUP BY p.id_producto, p.nombre_producto
ORDER BY p.nombre_producto;

-- 6. Total consumido por producto.
SELECT
    p.nombre_producto,
    COALESCE(SUM(dc.cantidad), 0) AS total_consumido
FROM productos p
LEFT JOIN detalle_consumo dc ON p.id_producto = dc.id_producto
GROUP BY p.id_producto, p.nombre_producto
ORDER BY p.nombre_producto;

-- 7. Stock calculado desde ingresos y consumos.
SELECT
    p.nombre_producto,
    COALESCE(SUM(di.cantidad), 0) AS total_ingresado,
    COALESCE(cons.total_consumido, 0) AS total_consumido,
    COALESCE(SUM(di.cantidad), 0) - COALESCE(cons.total_consumido, 0) AS stock_calculado
FROM productos p
LEFT JOIN detalle_ingreso di ON p.id_producto = di.id_producto
LEFT JOIN (
    SELECT
        id_producto,
        SUM(cantidad) AS total_consumido
    FROM detalle_consumo
    GROUP BY id_producto
) cons ON p.id_producto = cons.id_producto
GROUP BY p.id_producto, p.nombre_producto, cons.total_consumido
ORDER BY p.nombre_producto;
