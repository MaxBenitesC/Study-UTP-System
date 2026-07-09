const contenedorOfertas = document.getElementById("contenedor-ofertas");
let carrito = JSON.parse(localStorage.getItem("carrito_chapatupromo")) || [];

function mostrarMensajeOfertas(texto, tipo) {
  const mensaje = document.getElementById("mensaje-ofertas");
  mensaje.textContent = texto;

  if (tipo === "error") {
    mensaje.className = "mensaje-formulario mensaje-error";
  } else {
    mensaje.className = "mensaje-formulario mensaje-exito";
  }
}

function guardarCarrito() {
  localStorage.setItem("carrito_chapatupromo", JSON.stringify(carrito));
}

function agregarOfertaAlCarrito(id, nombre, precio, stock) {
  const productoEncontrado = carrito.find((producto) => producto.id === id);

  if (productoEncontrado) {
    if (productoEncontrado.cantidad >= stock) {
      mostrarMensajeOfertas("No puedes agregar mas unidades. Stock disponible: " + stock, "error");
      alert("No hay mas stock disponible para este producto");
      return;
    }

    productoEncontrado.cantidad = productoEncontrado.cantidad + 1;
  } else {
    carrito.push({
      id: id,
      nombre: nombre,
      precio: Number(precio),
      stock: Number(stock),
      cantidad: 1
    });
  }

  guardarCarrito();
  mostrarMensajeOfertas("Producto agregado al carrito: " + nombre, "exito");
  alert("Producto agregado al carrito");
}

function actualizarResumenOfertas(ofertas) {
  const hoy = ofertas.filter((producto) => producto.dias_para_vencer === 0).length;
  const manana = ofertas.filter((producto) => producto.dias_para_vencer === 1).length;
  const dosDias = ofertas.filter((producto) => producto.dias_para_vencer === 2).length;

  document.getElementById("ofertas-hoy").textContent = hoy;
  document.getElementById("ofertas-manana").textContent = manana;
  document.getElementById("ofertas-dos-dias").textContent = dosDias;
}

function crearTablaOfertas(titulo, claseTitulo, claseTabla, productos) {
  if (productos.length === 0) {
    return "";
  }

  let filas = "";

  productos.forEach((producto) => {
    filas += `
      <tr>
        <td><strong>${producto.nombre}</strong></td>
        <td>${producto.categoria}</td>
        <td>${producto.descripcion}</td>
        <td><del>S/. ${producto.precio_original}</del></td>
        <td><span class="etiqueta-aviso etiqueta-roja">${producto.descuento_porcentaje}% OFF</span></td>
        <td><strong>S/. ${producto.precio_chapatupromo}</strong></td>
        <td>${producto.stock}</td>
        <td>
          <button type="button" onclick="agregarOfertaAlCarrito(${producto.id}, '${producto.nombre}', '${producto.precio_chapatupromo}', ${producto.stock})">
            Añadir al carrito
          </button>
        </td>
      </tr>
    `;
  });

  return `
    <h3 class="titulo-seccion-ofertas ${claseTitulo}">${titulo}</h3>
    <table class="lista-de-ofertas ${claseTabla}">
      <tr>
        <th class="celda-primera">Producto</th>
        <th>Categoria</th>
        <th>Descripcion</th>
        <th>Precio original</th>
        <th>Descuento</th>
        <th>Precio final</th>
        <th>Stock</th>
        <th class="celda-ultima">Accion</th>
      </tr>
      ${filas}
    </table>
  `;
}

function mostrarOfertas(ofertas) {
  actualizarResumenOfertas(ofertas);

  const ofertasHoy = ofertas.filter((producto) => producto.dias_para_vencer === 0);
  const ofertasManana = ofertas.filter((producto) => producto.dias_para_vencer === 1);
  const ofertasDosDias = ofertas.filter((producto) => producto.dias_para_vencer === 2);
  const ofertasTresDias = ofertas.filter((producto) => producto.dias_para_vencer === 3);

  let contenido = "";
  contenido += crearTablaOfertas('<i class="fas fa-fire"></i> VENCEN HOY - 50% de descuento', "rojo-hoy", "tabla-vence-hoy", ofertasHoy);
  contenido += crearTablaOfertas('<i class="fas fa-clock"></i> VENCEN MANANA - 40% de descuento', "naranja-manana", "tabla-vence-manana", ofertasManana);
  contenido += crearTablaOfertas('<i class="fas fa-calendar-day"></i> VENCEN EN 2 DIAS - 30% de descuento', "purpura-dos-dias", "tabla-vence-pasado", ofertasDosDias);
  contenido += crearTablaOfertas('<i class="fas fa-calendar-check"></i> VENCEN EN 3 DIAS - 20% de descuento', "purpura-dos-dias", "tabla-vence-pasado", ofertasTresDias);

  if (contenido === "") {
    contenedorOfertas.innerHTML = "<p>No hay ofertas disponibles por ahora.</p>";
  } else {
    contenedorOfertas.innerHTML = contenido;
  }
}

function cargarOfertas() {
  fetch(API_URL + "/api/ofertas")
    .then((respuesta) => respuesta.json())
    .then((ofertas) => {
      mostrarOfertas(ofertas);
    })
    .catch((error) => {
      mostrarMensajeOfertas("No se pudieron cargar las ofertas.", "error");
      console.log(error);
    });
}

cargarOfertas();
