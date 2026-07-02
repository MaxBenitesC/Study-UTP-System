const tablaPedidos = document.getElementById("tabla-pedidos");
let pedidosActuales = [];
let intervaloEstados = null;

function mostrarMensajePedidos(texto, tipo) {
  const mensaje = document.getElementById("mensaje-pedidos");
  mensaje.textContent = texto;

  if (tipo === "error") {
    mensaje.className = "mensaje-formulario mensaje-error";
  } else {
    mensaje.className = "mensaje-formulario mensaje-exito";
  }
}

function formatearFecha(fechaTexto) {
  const fecha = new Date(fechaTexto);
  return fecha.toLocaleDateString("es-PE") + " " + fecha.toLocaleTimeString("es-PE");
}

function esDelivery(pedido) {
  return pedido.tipo_entrega === "delivery";
}

function obtenerEstados(pedido) {
  if (esDelivery(pedido)) {
    return ["Pendiente", "Preparando", "En camino", "Entregado"];
  }

  return ["Pendiente", "Preparando", "Listo para recojo", "Recogido"];
}

function obtenerEstadoActual(pedido) {
  if (!esDelivery(pedido) && pedido.estado === "Entregado") {
    return "Recogido";
  }

  if (esDelivery(pedido) && pedido.estado === "Listo para recojo") {
    return "En camino";
  }

  return pedido.estado;
}

function pedidoFinalizado(pedido) {
  const estado = obtenerEstadoActual(pedido);
  return estado === "Entregado" || estado === "Recogido";
}

function calcularTotalPedido(pedido) {
  let total = Number(pedido.total);

  if (esDelivery(pedido)) {
    total = total + 5;
  }

  return total.toFixed(2);
}

function mostrarEntrega(pedido) {
  if (esDelivery(pedido)) {
    return "Delivery a domicilio";
  }

  return "Recojo en tienda - " + pedido.tienda;
}

function actualizarEstadisticas(pedidos) {
  const completados = pedidos.filter((pedido) => pedidoFinalizado(pedido)).length;
  const proceso = pedidos.length - completados;

  document.getElementById("total-pedidos").textContent = pedidos.length;
  document.getElementById("pedidos-listos").textContent = completados;
  document.getElementById("pedidos-proceso").textContent = proceso;
}

function mostrarPedidos(pedidos) {
  if (pedidos.length === 0) {
    tablaPedidos.innerHTML = "<p>Todavia no hay pedidos registrados.</p>";
    actualizarEstadisticas(pedidos);
    return;
  }

  let filas = "";

  pedidos.forEach((pedido) => {
    const estado = obtenerEstadoActual(pedido);

    filas += `
      <tr>
        <td>${pedido.id}</td>
        <td>${formatearFecha(pedido.fecha_pedido)}</td>
        <td>${pedido.nombre_cliente}</td>
        <td>${pedido.productos}</td>
        <td>S/. ${calcularTotalPedido(pedido)}</td>
        <td>${mostrarEntrega(pedido)}</td>
        <td>${pedido.metodo_pago}</td>
        <td><span class="estado-pedido">${estado}</span></td>
      </tr>
    `;
  });

  tablaPedidos.innerHTML = `
    <table>
      <tr>
        <th class="celda-primera">Pedido</th>
        <th>Fecha</th>
        <th>Cliente</th>
        <th>Productos</th>
        <th>Total</th>
        <th>Entrega</th>
        <th>Pago</th>
        <th class="celda-ultima">Estado</th>
      </tr>
      ${filas}
    </table>
  `;

  actualizarEstadisticas(pedidos);
}

function guardarEstadoPedido(id, estado) {
  fetch(API_URL + "/api/pedidos/" + id + "/estado", {
    method: "PUT",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      estado: estado
    })
  })
    .then((respuesta) => respuesta.json())
    .then(() => {
      cargarPedidos();
    })
    .catch((error) => {
      mostrarMensajePedidos("No se pudo actualizar el estado.", "error");
      console.log(error);
    });
}

function avanzarEstadosAutomaticamente() {
  pedidosActuales.forEach((pedido) => {
    const estados = obtenerEstados(pedido);
    const estadoActual = obtenerEstadoActual(pedido);
    const posicion = estados.indexOf(estadoActual);

    if (posicion >= 0 && posicion < estados.length - 1) {
      guardarEstadoPedido(pedido.id, estados[posicion + 1]);
    }
  });
}

function cargarPedidos() {
  const usuario = JSON.parse(localStorage.getItem("usuario_chapatupromo"));

  if (!usuario) {
    tablaPedidos.innerHTML = "<p>Debes iniciar sesion para ver tus pedidos.</p>";
    actualizarEstadisticas([]);
    return;
  }

  fetch(API_URL + "/api/pedidos")
    .then((respuesta) => respuesta.json())
    .then((pedidos) => {
      // Filtrar los pedidos para que solo vea los suyos
      const misPedidos = pedidos.filter(p => p.correo_cliente === usuario.correo);
      
      pedidosActuales = misPedidos;
      mostrarPedidos(misPedidos);

      if (!intervaloEstados) {
        intervaloEstados = setInterval(avanzarEstadosAutomaticamente, 5000);
      }
    })
    .catch((error) => {
      mostrarMensajePedidos("No se pudieron cargar los pedidos.", "error");
      console.log(error);
    });
}

cargarPedidos();
