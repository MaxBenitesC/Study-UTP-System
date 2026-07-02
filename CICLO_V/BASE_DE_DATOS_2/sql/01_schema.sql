-- ============================================================
-- Proyecto: COMEDOR POPULAR "MANOS PIURANAS"
-- Base de datos: comedor_popular
-- Motor: PostgreSQL
-- Descripcion: Modelo relacional para controlar productos,
--              ingresos de productos y consumo de productos.
-- ============================================================

DROP TABLE IF EXISTS detalle_consumo CASCADE;
DROP TABLE IF EXISTS consumo CASCADE;
DROP TABLE IF EXISTS detalle_ingreso CASCADE;
DROP TABLE IF EXISTS ingreso CASCADE;
DROP TABLE IF EXISTS productos CASCADE;
DROP TABLE IF EXISTS proveedores CASCADE;
DROP TABLE IF EXISTS categorias CASCADE;

CREATE TABLE categorias (
    id_categoria SERIAL PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE productos (
    id_producto SERIAL PRIMARY KEY,
    id_categoria INT NOT NULL,
    nombre_producto VARCHAR(50) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0 CHECK (stock >= 0),
    fecha_vencimiento DATE,
    FOREIGN KEY (id_categoria) REFERENCES categorias(id_categoria)
);

CREATE TABLE proveedores (
    id_proveedor SERIAL PRIMARY KEY,
    nombre_proveedor VARCHAR(50) NOT NULL,
    telefono_proveedor VARCHAR(20) NOT NULL,
    direccion_proveedor VARCHAR(100) NOT NULL
);

CREATE TABLE ingreso (
    id_ingreso SERIAL PRIMARY KEY,
    fecha_ingreso DATE NOT NULL,
    id_proveedor INT NOT NULL,
    FOREIGN KEY (id_proveedor) REFERENCES proveedores(id_proveedor)
);

CREATE TABLE detalle_ingreso (
    id_detalle_ingreso SERIAL PRIMARY KEY,
    id_ingreso INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INTEGER NOT NULL CHECK (cantidad > 0),
    FOREIGN KEY (id_ingreso) REFERENCES ingreso(id_ingreso),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE TABLE consumo (
    id_consumo SERIAL PRIMARY KEY,
    fecha_consumo DATE NOT NULL
);

CREATE TABLE detalle_consumo (
    id_detalle_consumo SERIAL PRIMARY KEY,
    id_consumo INT NOT NULL,
    id_producto INT NOT NULL,
    cantidad INTEGER NOT NULL CHECK (cantidad > 0),
    FOREIGN KEY (id_consumo) REFERENCES consumo(id_consumo),
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

CREATE INDEX idx_productos_categoria ON productos(id_categoria);
CREATE INDEX idx_ingreso_proveedor ON ingreso(id_proveedor);
CREATE INDEX idx_detalle_ingreso_ingreso ON detalle_ingreso(id_ingreso);
CREATE INDEX idx_detalle_ingreso_producto ON detalle_ingreso(id_producto);
CREATE INDEX idx_detalle_consumo_consumo ON detalle_consumo(id_consumo);
CREATE INDEX idx_detalle_consumo_producto ON detalle_consumo(id_producto);
