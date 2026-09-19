# APIs del Explorador de Países — acuerdo para compartir

## Países: countries.dev

La persona 1 define y mantiene la API de países. La base actual ya consulta una sola vez:

```text
https://countries.dev/countries?fields=name,alpha3Code,capital,region,population,area,latlng,flags
```

La URL se probó el 14/09/2026: devolvió un array JSON de 250 países, sin clave y con CORS para el navegador. Los campos se convierten en `src/api/paises.js` al contrato común `codigo`, `nombre`, `capital`, `region`, `poblacion`, `superficie`, `coordenadas` y `banderaUrl`. Los demás integrantes reciben ese único array normalizado; no hacen otra consulta de países. [Documentación de countries.dev](https://countries.dev/docs).

`https://restcountries.com/` es un sitio que sí abre, pero no es el endpoint que devuelve la lista para esta tarea. La ruta `https://restcountries.com/v3.1/all` indicada en el enunciado devolvió un JSON de versión obsoleta en la prueba del 14/09/2026. [La documentación oficial de Rest Countries](https://restcountries.com/docs/countries/api-versions) señala que v5 requiere clave y tiene otra estructura. Si el equipo obtiene una URL concreta de Rest Countries que devuelva la lista sin error, solo la persona 1 cambiará el adaptador de carga; los otros módulos seguirán usando el mismo contrato.

## Clima: Open-Meteo

La persona 1 fija la fuente y el contrato; la persona 5 implementa las peticiones y la interfaz del clima. Opción de trabajo seleccionada: **[Open-Meteo Forecast API](https://open-meteo.com/en/docs)**. No requiere clave para este uso educativo no comercial. La consulta siguiente se probó el 14/09/2026 y devolvió `current.time`, `current.temperature_2m`, `current.weather_code` y `current.wind_speed_10m`:

```text
https://api.open-meteo.com/v1/forecast?latitude=33&longitude=65&current=temperature_2m,weather_code,wind_speed_10m
```

Para cada favorito, la persona 5 reemplaza `latitude` y `longitude` por `coordenadas[0]` y `coordenadas[1]` del país normalizado. Debe usar `Promise.all()` sobre las peticiones de los favoritos. Si un país no tiene coordenadas, se informa «clima no disponible» sin consultar la API. Los fallos de un favorito deben mostrarse por separado para que no oculten los resultados de los demás.

Las coordenadas disponibles son una referencia del país, **no necesariamente la capital**. La interfaz debe etiquetar el dato como «clima en ubicación de referencia». [Open-Meteo exige atribución a su fuente de datos](https://open-meteo.com/en/terms); agregar un enlace visible «Datos meteorológicos: Open-Meteo» en la vista de clima.

Las constantes compartidas están en `src/config/apis.js`. La persona 5 debe importar `URL_CLIMA_BASE` y `CAMPOS_CLIMA_ACTUAL`, sin copiar cadenas distintas en su módulo.

## Otras opciones de clima si el equipo decide cambiar

| Servicio | Clave | Ventaja | Implicación para esta página |
|---|---|---|---|
| [Open-Meteo](https://open-meteo.com/en/docs) | No, para uso no comercial | Probado con coordenadas; respuesta y CORS comprobados | Es la opción de trabajo actual |
| [OpenWeather Current Weather](https://openweathermap.org/api/current) | Sí | Datos actuales y documentación amplia | Requiere crear cuenta y gestionar una clave; no incluir una clave privada en el código público |
| [WeatherAPI.com Current](https://www.weatherapi.com/docs/) | Sí | Acepta coordenadas o nombre de ciudad | Requiere cuenta y clave; cambia el formato de respuesta |

Un cambio de proveedor debe hacerse en `src/config/apis.js` y `src/api/clima.js`, conservando el contrato que reciba el resto de la aplicación.
