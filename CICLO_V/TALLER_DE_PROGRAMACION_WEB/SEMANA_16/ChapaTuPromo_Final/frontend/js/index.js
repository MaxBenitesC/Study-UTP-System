const resumenInicio = document.getElementById("resumen-inicio");

function mostrarResumenInicio(productos, ofertas) {
  const stockTotal = productos.reduce((total, producto) => {
    return total + Number(producto.stock);
  }, 0);

  resumenInicio.innerHTML = `
    <div class="tarjeta-resumen-inicio">
      <strong>${productos.length}</strong>
      <span>productos disponibles</span>
    </div>
    <div class="tarjeta-resumen-inicio">
      <strong>${ofertas.length}</strong>
      <span>ofertas activas</span>
    </div>
    <div class="tarjeta-resumen-inicio">
      <strong>${stockTotal}</strong>
      <span>unidades en stock</span>
    </div>
  `;
}

function cargarResumenInicio() {
  Promise.all([
    fetch(API_URL + "/api/productos").then((respuesta) => respuesta.json()),
    fetch(API_URL + "/api/ofertas").then((respuesta) => respuesta.json())
  ])
    .then((datos) => {
      const productos = datos[0];
      const ofertas = datos[1];
      mostrarResumenInicio(productos, ofertas);
    })
    .catch((error) => {
      resumenInicio.innerHTML = "<p>No se pudo cargar el resumen de productos.</p>";
      console.log(error);
    });
}

cargarResumenInicio();
