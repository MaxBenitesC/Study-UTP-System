// Traemos la URL de países desde el archivo donde se guardan las APIs.
import { URL_PAISES } from "../config/apis.js";

// También la dejamos disponible aquí para quien importe este archivo.
export { URL_PAISES };

// Devuelve true si el dato es un número válido de cero o más.
function numeroValido(valor) {
  return typeof valor === "number" && Number.isFinite(valor) && valor >= 0;
}

// Si hay texto, quita espacios al inicio y al final; si no, devuelve null.
function textoOpcional(valor) {
  return typeof valor === "string" && valor.trim() ? valor.trim() : null;
}

// Convierte un país recibido de la API al formato que usará todo el equipo.
export function normalizarPais(origen) {
  // Comprobamos que el país exista y sea un objeto.
  if (!origen || typeof origen !== "object") {
    throw new Error("La API devolvió un país inválido.");
  }

  // Tomamos los tres datos que cada país necesita obligatoriamente.
  const codigo = textoOpcional(origen.alpha3Code);
  const nombre = textoOpcional(origen.name);
  const region = textoOpcional(origen.region);

  // Si falta alguno, avisamos del problema en lugar de usar datos incompletos.
  if (!codigo || !nombre || !region) {
    throw new Error("La API devolvió un país sin código, nombre o región.");
  }

  // Algunos países no tienen coordenadas; por eso empezamos con null.
  let coordenadas = null;
  // latlng debe ser un array con exactamente dos números: latitud y longitud.
  if (
    Array.isArray(origen.latlng) &&
    origen.latlng.length === 2 &&
    origen.latlng.every((valor) => typeof valor === "number" && Number.isFinite(valor))
  ) {
    // Los corchetes con ... crean una copia para no tocar el array recibido.
    coordenadas = [...origen.latlng];
  }

  // Preferimos la bandera PNG; si falta, usaremos la SVG.
  const banderaPng = origen.flags ? textoOpcional(origen.flags.png) : null;
  const banderaSvg = origen.flags ? textoOpcional(origen.flags.svg) : null;

  // Creamos un objeto nuevo con nombres de campos iguales para todos.
  return {
    codigo, // Código de tres letras; servirá para identificar cada país.
    nombre, // Nombre que se mostrará y se usará en la búsqueda.
    capital: textoOpcional(origen.capital), // Puede ser null si no hay capital.
    region, // Servirá para el filtro de regiones.
    poblacion: numeroValido(origen.population) ? origen.population : null, // Habitantes.
    superficie: numeroValido(origen.area) ? origen.area : null, // Área en km².
    coordenadas, // Las usará la consulta de clima.
    banderaUrl: banderaPng || banderaSvg, // Imagen de la bandera o null.
  };
}

// "async" indica que esta función espera una respuesta de Internet.
export async function cargarPaises() {
  // fetch() pide los países a la API. "await" espera su respuesta.
  const respuesta = await fetch(URL_PAISES);

  // Si el servidor responde con un error HTTP, detenemos la carga.
  if (!respuesta.ok) {
    throw new Error(`No se pudieron cargar los países (HTTP ${respuesta.status}).`);
  }

  // Convertimos el JSON recibido en datos que JavaScript puede usar.
  const datos = await respuesta.json();

  // Aunque la respuesta sea HTTP 200, debe contener un array no vacío.
  if (!Array.isArray(datos) || datos.length === 0) {
    throw new Error("La API no devolvió una lista de países válida.");
  }

  // map() pasa cada país por normalizarPais() y devuelve un nuevo array.
  return datos.map(normalizarPais);
}
