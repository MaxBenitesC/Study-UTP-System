const formularioPedido = document.getElementById("formulario-pedido");
const resumenCarritoPedido = document.getElementById("resumen-carrito-pedido");
const totalCarritoPedido = document.getElementById("total-carrito-pedido");
const tipoEntrega = document.getElementById("tipo_entrega");
const direccion = document.getElementById("direccion");
const tienda = document.getElementById("tienda");
const referencia = document.getElementById("referencia");

let carrito = JSON.parse(localStorage.getItem("carrito_chapatupromo")) || [];

function mostrarMensajePedido(texto, tipo) {
  const mensaje = document.getElementById("mensaje-pedido");
  mensaje.textContent = texto;

  if (tipo === "error") {
    mensaje.className = "mensaje-formulario mensaje-error";
  } else {
    mensaje.className = "mensaje-formulario mensaje-exito";
  }
}

function calcularTotalCarrito() {
  let total = 0;

  carrito.forEach((producto) => {
    total = total + (producto.precio * producto.cantidad);
  });

  return total;
}

function actualizarTotalPedido() {
  const subtotal = calcularTotalCarrito();
  let delivery = 0;

  if (tipoEntrega.value === "delivery") {
    delivery = 5;
  }

  totalCarritoPedido.textContent = subtotal.toFixed(2);
  document.getElementById("total-final-pedido").textContent = (subtotal + delivery).toFixed(2);
}

function mostrarCarritoPedido() {
  if (carrito.length === 0) {
    resumenCarritoPedido.innerHTML = `
      <p>No tienes productos en el carrito.</p>
      <p><a href="menu_chapatupromo.html">Volver al menu para agregar productos</a></p>
    `;
    actualizarTotalPedido();
    formularioPedido.querySelector("button[type='submit']").disabled = true;
    return;
  }

  let filas = "";

  carrito.forEach((producto) => {
    const subtotal = producto.precio * producto.cantidad;

    filas += `
      <tr>
        <td>${producto.nombre}</td>
        <td>${producto.cantidad}</td>
        <td>S/. ${producto.precio.toFixed(2)}</td>
        <td><strong>S/. ${subtotal.toFixed(2)}</strong></td>
      </tr>
    `;
  });

  resumenCarritoPedido.innerHTML = `
    <table>
      <tr>
        <th class="celda-primera">Producto</th>
        <th>Cantidad</th>
        <th>Precio</th>
        <th class="celda-ultima">Subtotal</th>
      </tr>
      ${filas}
    </table>
  `;

  actualizarTotalPedido();
  formularioPedido.querySelector("button[type='submit']").disabled = false;
}

function validarFormularioPedido() {
  const nombreCliente = document.getElementById("nombre_cliente").value.trim();
  const telefonoCliente = document.getElementById("telefono").value.trim();
  const correoCliente = document.getElementById("correo_cliente").value.trim();
  const entrega = tipoEntrega.value;
  const tiendaSeleccionada = tienda.value;
  const metodoPago = document.getElementById("pago").value;

  const soloLetras = /^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$/;
  const correoValido = /^[A-Za-z0-9][A-Za-z0-9._-]*@[A-Za-z0-9-]+\.[A-Za-z]{2,}$/;
  const celularValido = /^9[0-9]{8}$/;

  if (nombreCliente === "" || telefonoCliente === "" || correoCliente === "") {
    mostrarMensajePedido("Completa tus datos personales.", "error");
    alert("Completa tus datos");
    return false;
  }

  if (!soloLetras.test(nombreCliente)) {
    mostrarMensajePedido("El nombre solo debe tener letras y espacios.", "error");
    alert("Revisa el nombre del cliente");
    return false;
  }

  if (!celularValido.test(telefonoCliente)) {
    mostrarMensajePedido("El celular debe empezar en 9 y tener 9 digitos.", "error");
    alert("Revisa el celular");
    return false;
  }

  if (!correoValido.test(correoCliente)) {
    mostrarMensajePedido("Ingresa un correo valido.", "error");
    alert("Revisa el correo");
    return false;
  }

  if (carrito.length === 0) {
    mostrarMensajePedido("No puedes finalizar un pedido sin productos.", "error");
    alert("Tu carrito esta vacio");
    return false;
  }

  if (entrega === "") {
    mostrarMensajePedido("Selecciona el tipo de entrega.", "error");
    return false;
  }

  if (entrega === "recojo" && tiendaSeleccionada === "") {
    mostrarMensajePedido("Selecciona una tienda.", "error");
    return false;
  }

  if (entrega === "delivery" && direccion.value.trim() === "") {
    mostrarMensajePedido("Para delivery debes ingresar una direccion.", "error");
    return false;
  }

  if (metodoPago === "") {
    mostrarMensajePedido("Selecciona un metodo de pago.", "error");
    return false;
  }

  return true;
}

function obtenerUsuarioActual() {
  return JSON.parse(localStorage.getItem("usuario_chapatupromo")) || null;
}

function llenarDatosUsuario() {
  const usuario = obtenerUsuarioActual();

  if (!usuario) {
    return;
  }

  document.getElementById("nombre_cliente").value = usuario.nombre;
  document.getElementById("correo_cliente").value = usuario.correo;
  document.getElementById("telefono").value = usuario.telefono || "";
}

function crearDatosPedido() {
  const usuario = obtenerUsuarioActual();

  return {
    usuario_id: usuario ? usuario.id : null,
    nombre_cliente: document.getElementById("nombre_cliente").value.trim(),
    correo_cliente: document.getElementById("correo_cliente").value.trim(),
    telefono_cliente: document.getElementById("telefono").value.trim(),
    tipo_entrega: tipoEntrega.value,
    tienda: tipoEntrega.value === "delivery" ? "delivery" : tienda.value,
    direccion_entrega: direccion.value.trim(),
    referencia: tipoEntrega.value === "delivery" ? referencia.value.trim() : "",
    metodo_pago: document.getElementById("pago").value,
    productos: carrito.map((producto) => {
      return {
        producto_id: producto.id,
        cantidad: producto.cantidad
      };
    })
  };
}

function finalizarPedido(evento) {
  evento.preventDefault();

  if (!validarFormularioPedido()) {
    return;
  }

  fetch(API_URL + "/api/pedidos", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(crearDatosPedido())
  })
    .then((respuesta) => respuesta.json())
    .then((datos) => {
      if (datos.pedido) {
        localStorage.removeItem("carrito_chapatupromo");
        mostrarMensajePedido("Pedido registrado correctamente.", "exito");
        alert("Pedido registrado correctamente");
        window.location.href = "pedidos_chapatupromo.html";
      } else {
        mostrarMensajePedido(datos.mensaje || "No se pudo registrar el pedido.", "error");
      }
    })
    .catch((error) => {
      mostrarMensajePedido("No se pudo conectar con el backend.", "error");
      console.log(error);
    });
}

function cambiarEntrega() {
  if (tipoEntrega.value === "delivery") {
    direccion.disabled = false;
    referencia.disabled = false;
    direccion.placeholder = "Ej: Jr. Las Flores 234, Miraflores";
    referencia.placeholder = "Ej: Frente al parque, puerta azul";
    tienda.value = "";
    tienda.disabled = true;
  } else if (tipoEntrega.value === "recojo") {
    direccion.value = "";
    referencia.value = "";
    direccion.disabled = true;
    referencia.disabled = true;
    direccion.placeholder = "Solo necesario si eliges delivery";
    referencia.placeholder = "Solo necesario si eliges delivery";
    tienda.disabled = false;
  } else {
    tienda.value = "";
    direccion.value = "";
    referencia.value = "";
    tienda.disabled = true;
    direccion.disabled = true;
    referencia.disabled = true;
    direccion.placeholder = "Elige primero el tipo de entrega";
    referencia.placeholder = "Elige primero el tipo de entrega";
  }

  actualizarTotalPedido();
}

formularioPedido.addEventListener("submit", finalizarPedido);
tipoEntrega.addEventListener("change", cambiarEntrega);

llenarDatosUsuario();
mostrarCarritoPedido();
cambiarEntrega();
