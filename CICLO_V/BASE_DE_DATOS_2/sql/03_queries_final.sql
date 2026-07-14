-- ============================================================
-- Proyecto: COMEDOR POPULAR
-- Archivo: 03_queries_final.sql
-- Motor: PostgreSQL
-- Descripcion:
-- Consultas de gestion para sustentar pedidos de insumos,
-- personal, apoyo social y control operativo del comedor.
--
-- Estas consultas usan el modelo final de 15 tablas:
-- comedores, cargos, trabajadores, proveedores, categorias,
-- productos, ingresos, detalle_ingreso, tipos_beneficiario,
-- familias, beneficiarios, tipos_menu, menus, detalle_menu,
-- consumos.
-- ============================================================

-- 1. Stock actual por producto.
-- Evidencia: muestra que insumos quedan disponibles segun entradas menos uso en menus.
WITH ingresos_producto AS (
    SELECT
        id_producto,
        SUM(cantidad) AS total_ingresado
    FROM detalle_ingreso
    GROUP BY id_producto
),
usos_producto AS (
    SELECT
        id_producto,
        SUM(cantidad_usada) AS total_usado
    FROM detalle_menu
    GROUP BY id_producto
)
SELECT
    p.id_producto,
    p.nombre AS producto,
    c.nombre AS categoria,
    p.unidad_medida,
    COALESCE(i.total_ingresado, 0) AS total_ingresado,
    COALESCE(u.total_usado, 0) AS total_usado,
    COALESCE(i.total_ingresado, 0) - COALESCE(u.total_usado, 0) AS stock_actual,
    p.stock_minimo,
    CASE
        WHEN COALESCE(i.total_ingresado, 0) - COALESCE(u.total_usado, 0) <= 0 THEN 'SIN STOCK'
        WHEN COALESCE(i.total_ingresado, 0) - COALESCE(u.total_usado, 0) < p.stock_minimo THEN 'BAJO MINIMO'
        ELSE 'OK'
    END AS estado_stock
FROM productos p
JOIN categorias c ON c.id_categoria = p.id_categoria
LEFT JOIN ingresos_producto i ON i.id_producto = p.id_producto
LEFT JOIN usos_producto u ON u.id_producto = p.id_producto
WHERE p.activo = TRUE
ORDER BY estado_stock DESC, stock_actual ASC, p.nombre;


-- 2. Productos que requieren reposicion.
-- Evidencia: lista directa para solicitar mas insumos.
WITH stock_producto AS (
    SELECT
        p.id_producto,
        p.nombre AS producto,
        p.unidad_medida,
        p.stock_minimo,
        COALESCE(SUM(di.cantidad), 0) AS total_ingresado,
        (
            SELECT COALESCE(SUM(dm.cantidad_usada), 0)
            FROM detalle_menu dm
            WHERE dm.id_producto = p.id_producto
        ) AS total_usado
    FROM productos p
    LEFT JOIN detalle_ingreso di ON di.id_producto = p.id_producto
    WHERE p.activo = TRUE
    GROUP BY p.id_producto, p.nombre, p.unidad_medida, p.stock_minimo
)
SELECT
    producto,
    unidad_medida,
    total_ingresado - total_usado AS stock_actual,
    stock_minimo,
    stock_minimo - (total_ingresado - total_usado) AS cantidad_sugerida_reponer
FROM stock_producto
WHERE total_ingresado - total_usado < stock_minimo
ORDER BY cantidad_sugerida_reponer DESC;


-- 3. Dias estimados de cobertura por producto segun uso de los ultimos 30 dias.
-- Evidencia: permite decir "este insumo alcanza para X dias".
WITH ingresos_producto AS (
    SELECT id_producto, SUM(cantidad) AS total_ingresado
    FROM detalle_ingreso
    GROUP BY id_producto
),
usos_producto AS (
    SELECT id_producto, SUM(cantidad_usada) AS total_usado
    FROM detalle_menu
    GROUP BY id_producto
),
uso_30_dias AS (
    SELECT
        dm.id_producto,
        SUM(dm.cantidad_usada) / 30.0 AS promedio_diario
    FROM detalle_menu dm
    JOIN menus m ON m.id_menu = dm.id_menu
    WHERE m.fecha >= CURRENT_DATE - INTERVAL '30 days'
    GROUP BY dm.id_producto
)
SELECT
    p.nombre AS producto,
    p.unidad_medida,
    COALESCE(i.total_ingresado, 0) - COALESCE(u.total_usado, 0) AS stock_actual,
    ROUND(COALESCE(ud.promedio_diario, 0), 2) AS promedio_diario_ultimos_30_dias,
    CASE
        WHEN COALESCE(ud.promedio_diario, 0) = 0 THEN NULL
        ELSE ROUND((COALESCE(i.total_ingresado, 0) - COALESCE(u.total_usado, 0)) / ud.promedio_diario, 1)
    END AS dias_estimados_cobertura
FROM productos p
LEFT JOIN ingresos_producto i ON i.id_producto = p.id_producto
LEFT JOIN usos_producto u ON u.id_producto = p.id_producto
LEFT JOIN uso_30_dias ud ON ud.id_producto = p.id_producto
WHERE p.activo = TRUE
ORDER BY dias_estimados_cobertura NULLS LAST, stock_actual ASC;


-- 4. Lotes proximos a vencer.
-- Evidencia: sustenta urgencia de uso, redistribucion o perdida potencial.
SELECT
    p.nombre AS producto,
    p.unidad_medida,
    di.cantidad AS cantidad_recibida,
    di.fecha_vencimiento,
    pr.nombre AS proveedor,
    i.documento,
    i.fecha_ingreso,
    di.fecha_vencimiento - CURRENT_DATE AS dias_para_vencer
FROM detalle_ingreso di
JOIN ingresos i ON i.id_ingreso = di.id_ingreso
JOIN proveedores pr ON pr.id_proveedor = i.id_proveedor
JOIN productos p ON p.id_producto = di.id_producto
WHERE di.fecha_vencimiento IS NOT NULL
  AND di.fecha_vencimiento BETWEEN CURRENT_DATE AND CURRENT_DATE + INTERVAL '30 days'
ORDER BY di.fecha_vencimiento, p.nombre;


-- 5. Insumos recibidos por proveedor y mes.
-- Evidencia: muestra quien abastece, cuanto se recibe y en que periodo.
SELECT
    DATE_TRUNC('month', i.fecha_ingreso)::date AS mes,
    pr.nombre AS proveedor,
    p.nombre AS producto,
    p.unidad_medida,
    SUM(di.cantidad) AS cantidad_recibida,
    SUM(di.cantidad * COALESCE(di.costo_unitario, 0)) AS costo_estimado
FROM ingresos i
JOIN proveedores pr ON pr.id_proveedor = i.id_proveedor
JOIN detalle_ingreso di ON di.id_ingreso = i.id_ingreso
JOIN productos p ON p.id_producto = di.id_producto
GROUP BY DATE_TRUNC('month', i.fecha_ingreso), pr.nombre, p.nombre, p.unidad_medida
ORDER BY mes DESC, proveedor, producto;


-- 6. Proveedores con mayor aporte de insumos.
-- Evidencia: identifica dependencia de proveedores o donantes.
SELECT
    pr.nombre AS proveedor,
    COUNT(DISTINCT i.id_ingreso) AS cantidad_ingresos,
    COUNT(di.id_detalle_ingreso) AS lineas_productos_recibidos,
    SUM(di.cantidad * COALESCE(di.costo_unitario, 0)) AS valor_estimado_recibido
FROM proveedores pr
JOIN ingresos i ON i.id_proveedor = pr.id_proveedor
JOIN detalle_ingreso di ON di.id_ingreso = i.id_ingreso
GROUP BY pr.id_proveedor, pr.nombre
ORDER BY valor_estimado_recibido DESC, cantidad_ingresos DESC;


-- 7. Menus preparados vs personas atendidas por dia.
-- Evidencia: demuestra si la demanda real supera lo preparado o si sobran raciones.
SELECT
    m.fecha,
    tm.nombre AS tipo_menu,
    m.nombre AS menu,
    m.cantidad_menus AS menus_preparados,
    COUNT(c.id_consumo) AS personas_atendidas,
    m.cantidad_menus - COUNT(c.id_consumo) AS diferencia,
    CASE
        WHEN COUNT(c.id_consumo) > m.cantidad_menus THEN 'DEMANDA MAYOR A LO PREPARADO'
        WHEN COUNT(c.id_consumo) = m.cantidad_menus THEN 'ATENCION COMPLETA'
        ELSE 'SOBRARON RACIONES'
    END AS resultado
FROM menus m
JOIN tipos_menu tm ON tm.id_tipo_menu = m.id_tipo_menu
LEFT JOIN consumos c ON c.id_menu = m.id_menu
GROUP BY m.id_menu, m.fecha, tm.nombre, m.nombre, m.cantidad_menus
ORDER BY m.fecha DESC, tm.nombre;


-- 8. Personas atendidas por tipo de beneficiario y mes.
-- Evidencia: sustenta diferencia entre registrados, no registrados y visitantes.
SELECT
    DATE_TRUNC('month', c.fecha_hora)::date AS mes,
    tb.nombre AS tipo_beneficiario,
    COUNT(*) AS cantidad_consumos
FROM consumos c
JOIN beneficiarios b ON b.id_beneficiario = c.id_beneficiario
JOIN tipos_beneficiario tb ON tb.id_tipo_beneficiario = b.id_tipo_beneficiario
GROUP BY DATE_TRUNC('month', c.fecha_hora), tb.nombre
ORDER BY mes DESC, cantidad_consumos DESC;


-- 9. Porcentaje de atenciones a no registrados o visitantes.
-- Evidencia: ayuda a pedir mas apoyo cuando se atiende poblacion fuera del padron.
SELECT
    DATE_TRUNC('month', c.fecha_hora)::date AS mes,
    COUNT(*) AS total_atenciones,
    SUM(CASE WHEN LOWER(tb.nombre) IN ('no registrado', 'visitante') THEN 1 ELSE 0 END) AS atenciones_fuera_padron,
    ROUND(
        100.0 * SUM(CASE WHEN LOWER(tb.nombre) IN ('no registrado', 'visitante') THEN 1 ELSE 0 END)
        / NULLIF(COUNT(*), 0),
        2
    ) AS porcentaje_fuera_padron
FROM consumos c
JOIN beneficiarios b ON b.id_beneficiario = c.id_beneficiario
JOIN tipos_beneficiario tb ON tb.id_tipo_beneficiario = b.id_tipo_beneficiario
GROUP BY DATE_TRUNC('month', c.fecha_hora)
ORDER BY mes DESC;


-- 10. Consumos por distrito de familia.
-- Evidencia: muestra zonas con mayor demanda social.
SELECT
    COALESCE(f.distrito, 'SIN FAMILIA REGISTRADA') AS distrito,
    COUNT(*) AS cantidad_consumos,
    COUNT(DISTINCT b.id_beneficiario) AS beneficiarios_atendidos
FROM consumos c
JOIN beneficiarios b ON b.id_beneficiario = c.id_beneficiario
LEFT JOIN familias f ON f.id_familia = b.id_familia
GROUP BY COALESCE(f.distrito, 'SIN FAMILIA REGISTRADA')
ORDER BY cantidad_consumos DESC;


-- 11. Beneficiarios activos sin familia regularizada.
-- Evidencia: sirve para depurar padron y sustentar trabajo administrativo/social.
SELECT
    b.id_beneficiario,
    b.dni,
    b.nombres,
    b.apellidos,
    tb.nombre AS tipo_beneficiario
FROM beneficiarios b
JOIN tipos_beneficiario tb ON tb.id_tipo_beneficiario = b.id_tipo_beneficiario
WHERE b.activo = TRUE
  AND b.id_familia IS NULL
ORDER BY tb.nombre, b.apellidos, b.nombres;


-- 12. Recaudacion, deuda y subsidio social por menu.
-- Evidencia: demuestra cuanto se cobro, cuanto falta pagar y cuanto se subsidio.
SELECT
    m.fecha,
    tm.nombre AS tipo_menu,
    m.nombre AS menu,
    m.precio_base,
    COUNT(c.id_consumo) AS atenciones,
    SUM(c.precio_cobrado) AS total_debido_segundo_precio_aplicado,
    SUM(c.monto_pagado) AS total_pagado,
    SUM(c.precio_cobrado - c.monto_pagado) AS saldo_pendiente,
    SUM(GREATEST(m.precio_base - c.precio_cobrado, 0)) AS subsidio_social_estimado
FROM menus m
JOIN tipos_menu tm ON tm.id_tipo_menu = m.id_tipo_menu
LEFT JOIN consumos c ON c.id_menu = m.id_menu
GROUP BY m.id_menu, m.fecha, tm.nombre, m.nombre, m.precio_base
ORDER BY m.fecha DESC, tm.nombre;


-- 13. Beneficiarios con mayor saldo pendiente.
-- Evidencia: control de pagos pendientes sin perder el enfoque social.
SELECT
    b.dni,
    b.nombres,
    b.apellidos,
    tb.nombre AS tipo_beneficiario,
    COUNT(c.id_consumo) AS cantidad_consumos,
    SUM(c.precio_cobrado) AS total_debido,
    SUM(c.monto_pagado) AS total_pagado,
    SUM(c.precio_cobrado - c.monto_pagado) AS saldo_pendiente
FROM consumos c
JOIN beneficiarios b ON b.id_beneficiario = c.id_beneficiario
JOIN tipos_beneficiario tb ON tb.id_tipo_beneficiario = b.id_tipo_beneficiario
GROUP BY b.id_beneficiario, b.dni, b.nombres, b.apellidos, tb.nombre
HAVING SUM(c.precio_cobrado - c.monto_pagado) > 0
ORDER BY saldo_pendiente DESC;


-- 14. Carga operativa por trabajador en el mes actual.
-- Evidencia: ayuda a justificar mas personal o redistribuir funciones.
SELECT
    t.id_trabajador,
    t.nombres,
    t.apellidos,
    ca.nombre AS cargo,
    COUNT(DISTINCT c.id_consumo) AS atenciones_registradas,
    COUNT(DISTINCT m.id_menu) AS menus_registrados,
    COUNT(DISTINCT i.id_ingreso) AS ingresos_registrados
FROM trabajadores t
JOIN cargos ca ON ca.id_cargo = t.id_cargo
LEFT JOIN consumos c
    ON c.id_trabajador = t.id_trabajador
   AND c.fecha_hora >= DATE_TRUNC('month', CURRENT_DATE)
LEFT JOIN menus m
    ON m.id_trabajador = t.id_trabajador
   AND m.fecha >= DATE_TRUNC('month', CURRENT_DATE)::date
LEFT JOIN ingresos i
    ON i.id_trabajador = t.id_trabajador
   AND i.fecha_ingreso >= DATE_TRUNC('month', CURRENT_DATE)
WHERE t.activo = TRUE
GROUP BY t.id_trabajador, t.nombres, t.apellidos, ca.nombre
ORDER BY atenciones_registradas DESC, menus_registrados DESC, ingresos_registrados DESC;


-- 15. Atenciones diarias por trabajador.
-- Evidencia: muestra dias de alta carga.
SELECT
    c.fecha_hora::date AS fecha,
    t.nombres,
    t.apellidos,
    ca.nombre AS cargo,
    COUNT(*) AS atenciones_registradas
FROM consumos c
JOIN trabajadores t ON t.id_trabajador = c.id_trabajador
JOIN cargos ca ON ca.id_cargo = t.id_cargo
WHERE c.fecha_hora >= CURRENT_DATE - INTERVAL '30 days'
GROUP BY c.fecha_hora::date, t.id_trabajador, t.nombres, t.apellidos, ca.nombre
ORDER BY fecha DESC, atenciones_registradas DESC;


-- 16. Demanda diaria comparada con personal activo.
-- Evidencia: si hay muchos atendidos por trabajador, sirve para pedir mas personal.
WITH personal_activo AS (
    SELECT
        id_comedor,
        COUNT(*) AS trabajadores_activos
    FROM trabajadores
    WHERE activo = TRUE
    GROUP BY id_comedor
),
atenciones_dia AS (
    SELECT
        m.id_comedor,
        m.fecha,
        COUNT(c.id_consumo) AS personas_atendidas
    FROM menus m
    LEFT JOIN consumos c ON c.id_menu = m.id_menu
    GROUP BY m.id_comedor, m.fecha
)
SELECT
    co.nombre AS comedor,
    ad.fecha,
    ad.personas_atendidas,
    pa.trabajadores_activos,
    ROUND(ad.personas_atendidas::numeric / NULLIF(pa.trabajadores_activos, 0), 2) AS atenciones_por_trabajador
FROM atenciones_dia ad
JOIN comedores co ON co.id_comedor = ad.id_comedor
LEFT JOIN personal_activo pa ON pa.id_comedor = ad.id_comedor
ORDER BY ad.fecha DESC, atenciones_por_trabajador DESC;


-- 17. Productos mas usados en preparacion de menus.
-- Evidencia: identifica insumos criticos para pedir reposicion frecuente.
SELECT
    DATE_TRUNC('month', m.fecha)::date AS mes,
    p.nombre AS producto,
    p.unidad_medida,
    SUM(dm.cantidad_usada) AS cantidad_usada
FROM detalle_menu dm
JOIN menus m ON m.id_menu = dm.id_menu
JOIN productos p ON p.id_producto = dm.id_producto
GROUP BY DATE_TRUNC('month', m.fecha), p.nombre, p.unidad_medida
ORDER BY mes DESC, cantidad_usada DESC;


-- 18. Costo estimado de cada menu preparado.
-- Evidencia: permite saber si el precio base cubre el costo aproximado.
WITH costo_promedio_producto AS (
    SELECT
        id_producto,
        AVG(costo_unitario) AS costo_unitario_promedio
    FROM detalle_ingreso
    WHERE costo_unitario IS NOT NULL
    GROUP BY id_producto
)
SELECT
    m.fecha,
    tm.nombre AS tipo_menu,
    m.nombre AS menu,
    m.cantidad_menus,
    m.precio_base,
    SUM(dm.cantidad_usada * COALESCE(cpp.costo_unitario_promedio, 0)) AS costo_total_estimado,
    ROUND(
        SUM(dm.cantidad_usada * COALESCE(cpp.costo_unitario_promedio, 0))
        / NULLIF(m.cantidad_menus, 0),
        2
    ) AS costo_estimado_por_racion,
    m.precio_base - ROUND(
        SUM(dm.cantidad_usada * COALESCE(cpp.costo_unitario_promedio, 0))
        / NULLIF(m.cantidad_menus, 0),
        2
    ) AS margen_estimado_por_racion
FROM menus m
JOIN tipos_menu tm ON tm.id_tipo_menu = m.id_tipo_menu
JOIN detalle_menu dm ON dm.id_menu = m.id_menu
LEFT JOIN costo_promedio_producto cpp ON cpp.id_producto = dm.id_producto
GROUP BY m.id_menu, m.fecha, tm.nombre, m.nombre, m.cantidad_menus, m.precio_base
ORDER BY m.fecha DESC, tm.nombre;


-- 19. Familias con mayor cantidad de consumos.
-- Evidencia: identifica hogares con mayor dependencia del comedor.
SELECT
    f.codigo_padron,
    f.direccion,
    f.distrito,
    COUNT(DISTINCT b.id_beneficiario) AS integrantes_atendidos,
    COUNT(c.id_consumo) AS total_consumos
FROM familias f
JOIN beneficiarios b ON b.id_familia = f.id_familia
JOIN consumos c ON c.id_beneficiario = b.id_beneficiario
GROUP BY f.id_familia, f.codigo_padron, f.direccion, f.distrito
ORDER BY total_consumos DESC;


-- 20. Posibles DNI duplicados en beneficiarios.
-- Evidencia: control de calidad del padron.
SELECT
    dni,
    COUNT(*) AS cantidad_registros
FROM beneficiarios
WHERE dni IS NOT NULL
GROUP BY dni
HAVING COUNT(*) > 1
ORDER BY cantidad_registros DESC, dni;

