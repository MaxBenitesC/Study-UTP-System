-- ============================================================
-- Proyecto: COMEDOR POPULAR "MANOS PIURANAS"
-- Archivo: 02_data.sql
-- Descripcion: Datos de prueba para el modelo de inventario.
-- ============================================================

INSERT INTO categorias (nombre_categoria) VALUES
('Viveres'),
('Verduras'),
('Carnes'),
('Abarrotes'),
('Combustible');

INSERT INTO productos (id_categoria, nombre_producto, stock, fecha_vencimiento) VALUES
(1, 'Arroz', 200, '2026-12-31'),
(1, 'Frejoles', 100, '2026-11-30'),
(1, 'Harina', 100, '2026-10-31'),
(2, 'Papa', 80, '2026-07-05'),
(2, 'Verduras surtidas', 20, '2026-06-28'),
(3, 'Pollo', 15, '2026-06-25'),
(3, 'Pescado', 40, '2026-06-24'),
(4, 'Aceite', 25, '2027-01-15'),
(4, 'Condimentos', 10, '2026-09-30'),
(5, 'Gas', 1, NULL);

INSERT INTO proveedores (nombre_proveedor, telefono_proveedor, direccion_proveedor) VALUES
('PRONAA', '000000000', 'Apoyo institucional'),
('Municipalidad de Santa Anita', '111111111', 'Santa Anita'),
('Mercado La Parada', '999888777', 'La Parada'),
('Proveedor local de gas', '999777666', 'Santa Anita');

INSERT INTO ingreso (fecha_ingreso, id_proveedor) VALUES
('2026-06-08', 1),
('2026-06-10', 3),
('2026-06-11', 4),
('2026-06-12', 3);

INSERT INTO detalle_ingreso (id_ingreso, id_producto, cantidad) VALUES
(1, 1, 200),
(1, 2, 100),
(1, 3, 100),
(1, 8, 20),
(2, 4, 80),
(2, 5, 20),
(2, 6, 15),
(3, 10, 1),
(4, 7, 40),
(4, 9, 10);

INSERT INTO consumo (fecha_consumo) VALUES
('2026-06-10'),
('2026-06-11'),
('2026-06-12');

INSERT INTO detalle_consumo (id_consumo, id_producto, cantidad) VALUES
(1, 1, 7),
(1, 4, 20),
(1, 5, 8),
(1, 6, 10),
(2, 1, 6),
(2, 2, 8),
(2, 5, 6),
(2, 10, 1),
(3, 1, 5),
(3, 7, 40),
(3, 9, 2);
