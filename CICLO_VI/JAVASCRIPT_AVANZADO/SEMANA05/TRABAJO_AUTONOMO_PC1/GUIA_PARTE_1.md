# Mi parte 1: base y carga de países

## ¿Qué estoy construyendo?

Una página web sencilla. **HTML** coloca el título y los espacios donde aparecerán las funciones. **CSS** da colores, separación y tamaño. **JavaScript** consulta una API, guarda los países en un array y muestra si la carga salió bien o mal. No hace falta React, una base de datos ni un servidor de aplicación para esta primera parte.

La página actual se ve así:

```text
┌──────────────────────────────────────────┐
│ Explorador de Países                     │
│ Descubre y compara países                │
├──────────────────────────────────────────┤
│ Cargando países…                         │
│ o: 250 países disponibles                │
│ o: error + botón «Volver a intentar»     │
└──────────────────────────────────────────┘
```

Debajo ya existen secciones vacías para búsqueda, estadísticas, tarjetas, paginación, favoritos, comparador y clima. Tus compañeros llenarán esas secciones. Esta primera parte no implementa sus funciones.

## Archivos que debo conocer

| Archivo | Para qué sirve |
|---|---|
| `index.html` | Estructura visible de la página y lugares donde se conectarán los módulos |
| `styles.css` | Diseño simple: fondo claro, encabezado azul, tarjeta de estado y botón |
| `src/config/apis.js` | Guarda en un solo sitio las URLs y campos de las APIs elegidas |
| `src/api/paises.js` | Hace la petición de países y les da un formato común |
| `src/app.js` | Arranca la página, guarda `paises` y muestra carga, éxito o error |

## Cómo funciona el código

1. El navegador abre `index.html`. La etiqueta `<script type="module" src="./src/app.js">` carga el JavaScript. `type="module"` permite usar `import` y separar archivos.
2. `app.js` empieza con `let paises = []`: un array vacío. Ese será el **único array principal de países** que usará el equipo.
3. `app.js` llama a `cargarPaises()`. La función está en `src/api/paises.js`.
4. `cargarPaises()` usa `fetch(URL_PAISES)` para pedir los datos. `await` espera la respuesta; `respuesta.json()` convierte el JSON en datos de JavaScript.
5. Se comprueba que haya una respuesta correcta y que sea un array. Esto también detecta un JSON de error que llegue con HTTP 200.
6. `datos.map(normalizarPais)` recorre el array y crea otro array con objetos que tienen nombres de campos iguales para todos: `codigo`, `nombre`, `capital`, `region`, `poblacion`, `superficie`, `coordenadas` y `banderaUrl`.
7. `app.js` guarda el resultado en `paises` y escribe la cantidad en la página con `textContent`.
8. Si la red o la respuesta falla, `catch` muestra un mensaje y el botón de reintento. El botón vuelve a ejecutar `iniciar()`; después de una carga exitosa no se repite la petición.

Ejemplo del cambio de nombre de un campo:

```js
// La API envía: { alpha3Code: "AFG", name: "Afghanistan" }
// El equipo recibe: { codigo: "AFG", nombre: "Afghanistan" }
```

`normalizarPais()` también convierte datos ausentes en `null`. Por ejemplo, algunas entradas no traen capital, superficie o coordenadas. Así otro módulo puede comprobar `pais.capital === null` y mostrar «No disponible».

## Qué debo decirles a mis compañeros

«Ya hay una sola carga de países. No hagan otro `fetch()` para buscar o filtrar. Sus funciones deben recibir el array `paises` y usar estos campos: `codigo`, `nombre`, `capital`, `region`, `poblacion`, `superficie`, `coordenadas`, `banderaUrl`. No cambien el array original; si van a ordenar, hagan antes una copia con `[...paises]`. La URL del clima está definida para el compañero 5, pero las peticiones de clima son parte de su módulo».

Las URLs exactas y el criterio de elección están en [APIS_EQUIPO.md](APIS_EQUIPO.md). La distribución de funciones está en [ESTRATEGIA_EQUIPO.md](ESTRATEGIA_EQUIPO.md).

## Cómo lo ejecuto y compruebo

Desde esta carpeta, abrir una terminal y ejecutar:

```bash
python3 -m http.server 8000
```

Dejar la terminal abierta y luego abrir `http://localhost:8000/`. No usar doble clic sobre `index.html`; en ese caso la barra de direcciones empieza con `file://` y los módulos JavaScript no arrancan. Debe aparecer el encabezado y, cuando termine la consulta, el número de países cargados. El número puede variar si la API actualiza sus datos. Si se desconecta Internet o la API falla, debe aparecer el botón «Volver a intentar».

Para ejecutar las pruebas de la carga:

```bash
npm test
```

No se usa ningún bucle `for` clásico en esta parte. El método de array que puedo mostrar y explicar es `map()`: transforma la lista recibida en la lista con el formato común del equipo.
