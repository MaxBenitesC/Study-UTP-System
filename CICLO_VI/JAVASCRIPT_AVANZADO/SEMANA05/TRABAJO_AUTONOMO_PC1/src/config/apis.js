// "export" permite que otros archivos usen estas constantes con "import".
// Esta URL devuelve la lista de países con los campos que necesita el proyecto.
export const URL_PAISES =
  "https://countries.dev/countries?fields=name,alpha3Code,capital,region,population,area,latlng,flags";

// La persona 5 usará esta dirección para pedir el clima de los países favoritos.
export const URL_CLIMA_BASE = "https://api.open-meteo.com/v1/forecast";

// Estos son los datos de clima que se pedirán: temperatura, estado y viento.
export const CAMPOS_CLIMA_ACTUAL = "temperature_2m,weather_code,wind_speed_10m";
