-- ============================================================
-- Proyecto: COMEDOR POPULAR
-- Archivo: 04_consultas_super_basicas.sql
-- Motor: PostgreSQL
-- Descripcion: 10 consultas basicas usando JOIN, GROUP BY,
-- COUNT, SUM y CASE.
-- ============================================================

-- 1. Listar productos con su categoria.
SELECT
    p.id_producto,
    p.nombre AS producto,
    c.nombre AS categoria,
    p.unidad_medida,
    p.stock_minimo
FROM productos p
JOIN categorias c ON c.id_categoria = p.id_categoria
ORDER BY c.nombre, p.nombre;


-- 2. Listar trabajadores con su cargo y comedor.
SELECT
    t.id_trabajador,
    t.nombres,
    t.apellidos,
    ca.nombre AS cargo,
    co.nombre AS comedor,
    t.activo
FROM trabajadores t
JOIN cargos ca ON ca.id_cargo = t.id_cargo
JOIN comedores co ON co.id_comedor = t.id_comedor
ORDER BY co.nombre, ca.nombre, t.apellidos;


-- 3. Ver ingresos de productos por proveedor.
SELECT
    i.fecha_ingreso,
    pr.nombre AS proveedor,
    p.nombre AS producto,
    di.cantidad,
    p.unidad_medida
FROM ingresos i
JOIN proveedores pr ON pr.id_proveedor = i.id_proveedor
JOIN detalle_ingreso di ON di.id_ingreso = i.id_ingreso
JOIN productos p ON p.id_producto = di.id_producto
ORDER BY i.fecha_ingreso, pr.nombre, p.nombre;


-- 4. Contar productos por categoria.
SELECT
    c.nombre AS categoria,
    COUNT(p.id_producto) AS cantidad_productos
FROM categorias c
LEFT JOIN productos p ON p.id_categoria = c.id_categoria
GROUP BY c.id_categoria, c.nombre
ORDER BY cantidad_productos DESC;


-- 5. Total recibido por producto.
SELECT
    p.nombre AS producto,
    p.unidad_medida,
    SUM(di.cantidad) AS total_recibido
FROM productos p
JOIN detalle_ingreso di ON di.id_producto = p.id_producto
GROUP BY p.id_producto, p.nombre, p.unidad_medida
ORDER BY total_recibido DESC;


-- 6. Total usado por producto en los menus.
SELECT
    p.nombre AS producto,
    p.unidad_medida,
    SUM(dm.cantidad_usada) AS total_usado
FROM productos p
JOIN detalle_menu dm ON dm.id_producto = p.id_producto
GROUP BY p.id_producto, p.nombre, p.unidad_medida
ORDER BY total_usado DESC;


-- 7. Cantidad de menus preparados por tipo de menu.
SELECT
    tm.nombre AS tipo_menu,
    COUNT(m.id_menu) AS cantidad_registros,
    SUM(m.cantidad_menus) AS total_menus_preparados
FROM tipos_menu tm
JOIN menus m ON m.id_tipo_menu = tm.id_tipo_menu
GROUP BY tm.id_tipo_menu, tm.nombre
ORDER BY total_menus_preparados DESC;


-- 8. Personas atendidas por menu.
SELECT
    m.fecha,
    m.nombre AS menu,
    m.cantidad_menus AS menus_preparados,
    COUNT(c.id_consumo) AS personas_atendidas,
    CASE
        WHEN COUNT(c.id_consumo) > m.cantidad_menus THEN 'FALTARON MENUS'
        WHEN COUNT(c.id_consumo) = m.cantidad_menus THEN 'EXACTO'
        ELSE 'SOBRARON MENUS'
    END AS resultado
FROM menus m
LEFT JOIN consumos c ON c.id_menu = m.id_menu
GROUP BY m.id_menu, m.fecha, m.nombre, m.cantidad_menus
ORDER BY m.fecha, m.nombre;


-- 9. Consumos por tipo de beneficiario.
SELECT
    tb.nombre AS tipo_beneficiario,
    COUNT(c.id_consumo) AS total_consumos
FROM consumos c
JOIN beneficiarios b ON b.id_beneficiario = c.id_beneficiario
JOIN tipos_beneficiario tb ON tb.id_tipo_beneficiario = b.id_tipo_beneficiario
GROUP BY tb.id_tipo_beneficiario, tb.nombre
ORDER BY total_consumos DESC;


-- 10. Total pagado y pendiente por beneficiario.
SELECT
    b.dni,
    b.nombres,
    b.apellidos,
    COUNT(c.id_consumo) AS total_consumos,
    SUM(c.precio_cobrado) AS total_cobrado,
    SUM(c.monto_pagado) AS total_pagado,
    SUM(c.precio_cobrado - c.monto_pagado) AS saldo_pendiente,
    CASE
        WHEN SUM(c.precio_cobrado - c.monto_pagado) > 0 THEN 'DEBE'
        ELSE 'AL DIA'
    END AS estado_pago
FROM beneficiarios b
JOIN consumos c ON c.id_beneficiario = b.id_beneficiario
GROUP BY b.id_beneficiario, b.dni, b.nombres, b.apellidos
ORDER BY saldo_pendiente DESC;
