// Importamos la función que consulta y prepara los países.
import { cargarPaises } from "./api/paises.js";

// Aquí guardaremos todos los países después de cargarlos una sola vez.
let paises = [];
// Evita que dos clics rápidos hagan dos consultas a la vez.
let cargando = false;

// Buscamos en el HTML los elementos que vamos a actualizar.
const estadoCarga = document.querySelector("#estado-carga");
const resumenDatos = document.querySelector("#resumen-datos");
const botonReintentar = document.querySelector("#reintentar");
const aplicacion = document.querySelector("#aplicacion");

// Esta función inicia la carga cuando se abre la página o se pulsa reintentar.
async function iniciar() {
  // Si ya se está cargando o ya hay países, no hacemos otra petición.
  if (cargando || paises.length > 0) return;

  // Mostramos el estado de carga y ocultamos el botón y los resultados anteriores.
  cargando = true;
  estadoCarga.textContent = "Cargando países…";
  botonReintentar.hidden = true;
  resumenDatos.hidden = true;
  aplicacion.hidden = true;

  // try intenta ejecutar la consulta; si falla, pasamos a catch.
  try {
    // await espera a cargar y normalizar los datos; luego los guardamos en paises.
    paises = await cargarPaises();
    // textContent cambia el texto que ve el usuario en la página.
    estadoCarga.textContent = "Datos cargados correctamente.";
    resumenDatos.textContent = `${paises.length} países disponibles para explorar.`;
    // hidden = false hace visibles el resumen y las secciones de la aplicación.
    resumenDatos.hidden = false;
    aplicacion.hidden = false;

    // Aquí se conectarán los módulos de búsqueda, análisis, favoritos y comparación.
    // Todos recibirán este mismo array `paises` mediante parámetros.
  } catch (error) {
    // Si falla la red o los datos, mostramos el problema.
    estadoCarga.textContent =
      error instanceof Error ? error.message : "Ocurrió un error al cargar los países.";
    // El usuario podrá volver a intentar la consulta con este botón.
    botonReintentar.hidden = false;
  } finally {
    // finally se ejecuta tanto si la carga salió bien como si falló.
    cargando = false;
  }
}

// Al hacer clic en el botón, volvemos a llamar a iniciar().
botonReintentar.addEventListener("click", iniciar);
// Hacemos la primera carga automáticamente al abrir la página.
iniciar();
