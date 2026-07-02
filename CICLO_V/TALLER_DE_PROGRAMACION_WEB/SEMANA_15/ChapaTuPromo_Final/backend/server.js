const express = require("express");
const cors = require("cors");
require("dotenv").config();

const db = require("./database");

const app = express();
const PORT = process.env.PORT || 3000;

app.use(cors());
app.use(express.json());

app.get("/", (req, res) => {
  res.json({
    mensaje: "Backend de Chapatupromo funcionando"
  });
});

app.get("/api/prueba-bd", async (req, res) => {
  try {
   const resultado = await db.query("SELECT NOW() AS fecha_servidor");
    res.json({
      mensaje: "Consulta SQL ejecutada correctamente",
      fecha: resultado.rows[0].fecha_servidor
    });
  } catch (error) {
    res.status(500).json({
      mensaje: "Error al consultar la base de datos",
      error: error.message
    });
  }
});

function calcularDias(fechaVencimiento) {
  const hoy = new Date();
  const fecha = new Date(fechaVencimiento);

  hoy.setHours(0, 0, 0, 0);
  fecha.setHours(0, 0, 0, 0);

  const diferencia = fecha - hoy;
  const milisegundosDia = 1000 * 60 * 60 * 24;

  return Math.round(diferencia / milisegundosDia);
}

function calcularDescuento(dias) {
  if (dias === 0) {
    return 50;
  } else if (dias === 1) {
    return 40;
  } else if (dias === 2) {
    return 30;
  } else if (dias === 3) {
    return 20;
  } else {
    return 0;
  }
}

function obtenerMensajeVencimiento(dias) {
  if (dias === 0) {
    return "Hoy";
  } else if (dias === 1) {
    return "Mañana";
  } else if (dias > 1) {
    return `En ${dias} dias`;
  } else {
    return "Vencido";
  }
}

function prepararProducto(producto) {
  const dias = calcularDias(producto.fecha_vencimiento);
  const descuento = calcularDescuento(dias);
  const precioOriginal = Number(producto.precio_original);
  const precioFinal = precioOriginal - (precioOriginal * descuento / 100);

  return {
    ...producto,
    dias_para_vencer: dias,
    descuento_porcentaje: descuento,
    precio_chapatupromo: precioFinal.toFixed(2),
    mensaje_vencimiento: obtenerMensajeVencimiento(dias)
  };
}

app.get("/api/productos", async (req, res) => {
  try {
   const resultado = await db.query(`
   SELECT id, nombre, categoria, descripcion, fecha_vencimiento, precio_original, stock, estado
   FROM productos
   WHERE estado = 'Disponible'
   ORDER BY categoria, fecha_vencimiento
     `);
    const productos = resultado.rows.map((producto) => {
      return prepararProducto(producto);
    });

    res.json(productos);
  } catch (error) {
    res.status(500).json({
      mensaje: "Error al listar productos",
      error: error.message
    });
  }
});

app.get("/api/ofertas", async (req, res) => {
  try {
    const resultado = await db.query(`
      SELECT id, nombre, categoria, descripcion, fecha_vencimiento, precio_original, stock, estado
      FROM productos
      WHERE estado = 'Disponible'
      ORDER BY fecha_vencimiento, categoria
    `);

    const ofertas = resultado.rows
      .map((producto) => prepararProducto(producto))
      .filter((producto) => producto.dias_para_vencer >= 0 && producto.dias_para_vencer <= 3);

    res.json(ofertas);
  } catch (error) {
    res.status(500).json({
      mensaje: "Error al listar ofertas",
      error: error.message
    });
  }
});

app.post("/api/usuarios", async (req, res) => {
  try {
    const { nombre, correo, password, telefono, direccion } = req.body;
    if (!nombre || !correo || !password) {
      return res.status(400).json({
        mensaje: "Nombre, correo y password son obligatorios"
      });
    }

    const resultado = await db.query(
      `INSERT INTO usuarios (nombre, correo, password, telefono, direccion)
       VALUES ($1, $2, $3, $4, $5)
       RETURNING id, nombre, correo, telefono, direccion, fecha_registro`,
      [nombre, correo, password, telefono, direccion]
    );

    res.status(201).json({
      mensaje: "Usuario registrado correctamente",
      usuario: resultado.rows[0]
    });
  } catch (error) {
    if (error.code === "23505") {
      return res.status(400).json({
        mensaje: "El correo ya esta registrado"
      });
    }

    res.status(500).json({
      mensaje: "Error al registrar usuario",
      error: error.message
    });
  }
});

app.post("/api/login", async (req, res) => {
  try {
    const { correo, password } = req.body;

    const resultado = await db.query(
      `SELECT id, nombre, correo, telefono, direccion
       FROM usuarios
       WHERE correo = $1 AND password = $2`,
      [correo, password]
    );

    if (resultado.rows.length === 0) {
      return res.status(401).json({
        mensaje: "Correo o password incorrectos"
      });
    }

    res.json({
      mensaje: "Inicio de sesion correcto",
      usuario: resultado.rows[0]
    });
  } catch (error) {
    res.status(500).json({
      mensaje: "Error al iniciar sesion",
      error: error.message
    });
  }
});

app.post("/api/pedidos", async (req, res) => {
  const cliente = await db.connect();

  try {
    const {
      usuario_id,
      nombre_cliente,
      correo_cliente,
      telefono_cliente,
      tipo_entrega,
      tienda,
      direccion_entrega,
      referencia,
      metodo_pago,
      productos
    } = req.body;

    if (!productos || productos.length === 0) {
      return res.status(400).json({
        mensaje: "No se puede crear un pedido sin productos"
      });
    }

    await cliente.query("BEGIN");

    let total = 0;
    const productosPreparados = [];

    for (const item of productos) {
      const resultadoProducto = await cliente.query(
        `SELECT id, nombre, fecha_vencimiento, precio_original, stock, estado
         FROM productos
         WHERE id = $1 AND estado = 'Disponible'`,
        [item.producto_id]
      );

      if (resultadoProducto.rows.length === 0) {
        throw new Error("Producto no disponible");
      }

      const producto = prepararProducto(resultadoProducto.rows[0]);
      const cantidad = Number(item.cantidad);

      if (producto.stock < cantidad) {
        throw new Error(`Stock insuficiente para ${producto.nombre}`);
      }

      const precioUnitario = Number(producto.precio_chapatupromo);
      const subtotal = precioUnitario * cantidad;
      total += subtotal;

      productosPreparados.push({
        id: producto.id,
        cantidad,
        precio_unitario: precioUnitario,
        subtotal
      });
    }

    const resultadoPedido = await cliente.query(
      `INSERT INTO pedidos (
        usuario_id, nombre_cliente, correo_cliente, telefono_cliente,
        tipo_entrega, tienda, direccion_entrega, referencia, metodo_pago, total
      )
       VALUES ($1, $2, $3, $4, $5, $6, $7, $8, $9, $10)
       RETURNING *`,
      [
        usuario_id || null,
        nombre_cliente,
        correo_cliente,
        telefono_cliente,
        tipo_entrega,
        tienda,
        direccion_entrega,
        referencia,
        metodo_pago,
        total
      ]
    );

    const pedido = resultadoPedido.rows[0];

    for (const producto of productosPreparados) {
      await cliente.query(
        `INSERT INTO detalle_pedido (pedido_id, producto_id, cantidad, precio_unitario, subtotal)
         VALUES ($1, $2, $3, $4, $5)`,
        [pedido.id, producto.id, producto.cantidad, producto.precio_unitario, producto.subtotal]
      );

      await cliente.query(
        `UPDATE productos
         SET stock = stock - $1
         WHERE id = $2`,
        [producto.cantidad, producto.id]
      );
    }

    await cliente.query("COMMIT");

    res.status(201).json({
      mensaje: "Pedido registrado correctamente",
      pedido
    });
  } catch (error) {
    await cliente.query("ROLLBACK");
    res.status(500).json({
      mensaje: "Error al registrar pedido",
      error: error.message
    });
  } finally {
    cliente.release();
  }
});

app.get("/api/pedidos", async (req, res) => {
  try {
    const resultado = await db.query(`
      SELECT
        p.id,
        p.nombre_cliente,
        p.correo_cliente,
        p.telefono_cliente,
        p.tipo_entrega,
        p.tienda,
        p.metodo_pago,
        p.total,
        p.estado,
        p.fecha_pedido,
        COALESCE(
          STRING_AGG(pr.nombre || ' x' || dp.cantidad, ', ' ORDER BY pr.nombre),
          ''
        ) AS productos
      FROM pedidos p
      LEFT JOIN detalle_pedido dp ON p.id = dp.pedido_id
      LEFT JOIN productos pr ON dp.producto_id = pr.id
      GROUP BY p.id
      ORDER BY p.fecha_pedido DESC
    `);

    res.json(resultado.rows);
  } catch (error) {
    res.status(500).json({
      mensaje: "Error al listar pedidos",
      error: error.message
    });
  }
});

app.put("/api/pedidos/:id/estado", async (req, res) => {
  try {
    const { id } = req.params;
    const { estado } = req.body;

    const resultado = await db.query(
      `UPDATE pedidos
       SET estado = $1
       WHERE id = $2
       RETURNING *`,
      [estado, id]
    );

    if (resultado.rows.length === 0) {
      return res.status(404).json({
        mensaje: "Pedido no encontrado"
      });
    }

    res.json({
      mensaje: "Estado del pedido actualizado",
      pedido: resultado.rows[0]
    });
  } catch (error) {
    res.status(500).json({
      mensaje: "Error al actualizar pedido",
      error: error.message
    });
  }
});

app.listen(PORT, () => {
  console.log(`Servidor backend iniciado en http://localhost:${PORT}`);
});
