DROP TABLE IF EXISTS detalle_pedido;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS usuarios;

CREATE TABLE usuarios (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    telefono VARCHAR(20),
    direccion VARCHAR(200),
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE productos (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(150) NOT NULL,
    categoria VARCHAR(80) NOT NULL,
    descripcion TEXT NOT NULL,
    fecha_vencimiento DATE NOT NULL,
    precio_original NUMERIC(10,2) NOT NULL,
    stock INTEGER NOT NULL,
    estado VARCHAR(20) DEFAULT 'Disponible'
);

CREATE TABLE pedidos (
    id SERIAL PRIMARY KEY,
    usuario_id INTEGER REFERENCES usuarios(id) ON DELETE SET NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    correo_cliente VARCHAR(120) NOT NULL,
    telefono_cliente VARCHAR(20) NOT NULL,
    tipo_entrega VARCHAR(30) NOT NULL,
    tienda VARCHAR(100) NOT NULL,
    direccion_entrega VARCHAR(200),
    referencia VARCHAR(200),
    metodo_pago VARCHAR(40) NOT NULL,
    total NUMERIC(10,2) NOT NULL DEFAULT 0,
    estado VARCHAR(30) DEFAULT 'Pendiente',
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE detalle_pedido (
    id SERIAL PRIMARY KEY,
    pedido_id INTEGER NOT NULL REFERENCES pedidos(id) ON DELETE CASCADE,
    producto_id INTEGER NOT NULL REFERENCES productos(id),
    cantidad INTEGER NOT NULL DEFAULT 1,
    precio_unitario NUMERIC(10,2) NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL
);
