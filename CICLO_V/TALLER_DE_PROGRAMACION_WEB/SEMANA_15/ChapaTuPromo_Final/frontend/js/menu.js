let carrito = JSON.parse(localStorage.getItem("carrito_chapatupromo")) || [];

function guardarCarrito() {
  localStorage.setItem("carrito_chapatupromo", JSON.stringify(carrito));
}

function actualizarResumenCarrito() {
  const cantidadCarrito = document.getElementById("cantidad-carrito");
  const totalCarrito = document.getElementById("total-carrito-menu");
  let totalProductos = 0;
  let totalPagar = 0;

  carrito.forEach((producto) => {
    totalProductos = totalProductos + producto.cantidad;
    totalPagar = totalPagar + (producto.precio * producto.cantidad);
  });

  cantidadCarrito.textContent = totalProductos;
  totalCarrito.textContent = totalPagar.toFixed(2);
}

function mostrarMensajeMenu(texto, tipo) {
  const mensaje = document.getElementById("mensaje-menu");
  mensaje.textContent = texto;

  if (tipo === "error") {
    mensaje.className = "mensaje-formulario mensaje-error";
  } else {
    mensaje.className = "mensaje-formulario mensaje-exito";
  }
}

function actualizarCarritoConStock(productos) {
  carrito = carrito.filter((itemCarrito) => {
    const productoBD = productos.find((producto) => producto.id === itemCarrito.id);

    if (!productoBD) {
      return false;
    }

    itemCarrito.nombre = productoBD.nombre;
    itemCarrito.precio = Number(productoBD.precio_chapatupromo);
    itemCarrito.stock = Number(productoBD.stock);

    if (itemCarrito.cantidad > itemCarrito.stock) {
      itemCarrito.cantidad = itemCarrito.stock;
    }

    return itemCarrito.stock > 0;
  });

  guardarCarrito();
}

function agregarAlCarrito(id, nombre, precio, stock) {
 const productoEncontrado = carrito.find((producto) => producto.id === id);
	 
 if (productoEncontrado) {
  if (productoEncontrado.cantidad >= stock) {
    mostrarMensajeMenu("No puedes agregar mas unidades. Stock disponible: " + stock, "error");
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
  actualizarResumenCarrito();

  mostrarMensajeMenu("Producto agregado al carrito: " + nombre, "exito");

  alert("Producto agregado al carrito");
}

function crearTablaCategoria(categoria, productos) {
    let filas = "";

    productos.forEach((producto) => {
      filas += `
        <tr>
          <td>${producto.nombre}</td>
          <td>${producto.descripcion}</td>
          <td>${producto.mensaje_vencimiento}</td>
          <td>S/. ${producto.precio_original}</td>
          <td>${producto.descuento_porcentaje}%</td>
          <td><strong>S/. ${producto.precio_chapatupromo}</strong></td>
          <td>${producto.stock}</td>
          <td>
            <button type="button" onclick="agregarAlCarrito(${producto.id}, '${producto.nombre}', '${producto.precio_chapatupromo}', ${producto.stock})">
              Añadir
            </button>
          </td>
        </tr>
      `;
    });

    return `
      <div class="tarjeta-comida">
        <h3>${categoria}</h3>
        <table>
          <tr>
            <th class="celda-primera">Producto</th>
            <th>Descripcion</th>
            <th>Vence</th>
            <th>Precio original</th>
            <th>Descuento</th>
            <th>Precio ChapaTuPromo</th>
            <th>Stock</th>
            <th class="celda-ultima">Accion</th>
          </tr>
          ${filas}
        </table>
      </div>
    `;
}

function mostrarProductos(productos) {
    const contenedor = document.getElementById("contenedor-productos-bd");

    const categorias = [];

    productos.forEach((producto) => {
      if (!categorias.includes(producto.categoria)) {
        categorias.push(producto.categoria);
      }
    });

    let contenido = "";

    categorias.forEach((categoria) => {
      const productosCategoria = productos.filter((producto) => producto.categoria === categoria);
      contenido += crearTablaCategoria(categoria, productosCategoria);
    });

    contenedor.innerHTML = contenido;
}

function cargarProductos() {
    fetch(API_URL + "/api/productos")
      .then((respuesta) => respuesta.json())
      .then((productos) => {
        console.log(productos);
        actualizarCarritoConStock(productos);
        actualizarResumenCarrito();
        mostrarProductos(productos);
      })
      .catch((error) => {
        const mensaje = document.getElementById("mensaje-menu");
        mensaje.textContent = "No se pudieron cargar los productos";
        mensaje.style.color = "red";
        console.log(error);
      });
}

cargarProductos();
actualizarResumenCarrito();
