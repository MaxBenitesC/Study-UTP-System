# Estrategia de equipo — Explorador de Países

Esta estrategia organiza el trabajo de cinco estudiantes. El enunciado oficial está en [DETALLE_TAREA.md](DETALLE_TAREA.md). Las decisiones de arquitectura de este documento son propuestas del equipo, no requisitos adicionales del profesor.

## Producto que se debe mostrar

Una página web interactiva en la que una persona pueda explorar países: buscar, filtrar por región, ordenar, consultar estadísticas, ver detalles, guardar favoritos, comparar hasta tres países y consultar el clima de sus favoritos. El objetivo evaluado es demostrar el uso de métodos de arrays, consumo de APIs, manejo de errores e inmutabilidad. Una tabla estática o un PDF por sí solos no demostrarían las interacciones que pide el enunciado.

La página puede ser una aplicación pequeña de HTML, CSS y JavaScript en el navegador. El enunciado no exige un framework ni un servidor de backend. Para módulos ES, se ejecutará mediante un servidor local durante el desarrollo.

## Fuente de datos comprobada el 14/09/2026

La URL `https://restcountries.com/v3.1/all` y su variante con `fields` del enunciado ya no entregan la lista: redirigen y devuelven un JSON con `success:false` y un mensaje de que v3 está obsoleta. La [documentación de versiones de Rest Countries](https://restcountries.com/docs/countries/api-versions) confirma la obsolescencia; v5 requiere clave y cambia el formato. Se debe validar también el contenido del JSON, porque el destino de la redirección puede responder HTTP 200 aunque sea un error. Además, la variante `fields` que figura en el enunciado no solicita `region`, `area` ni `latlng`, campos necesarios para varias funciones.

El profesor permite usar otra API. La base utiliza **[countries.dev](https://countries.dev/docs)**, que responde desde el navegador sin clave y con CORS habilitado. Esta URL se probó y devolvió un array de 250 registros:

```text
https://countries.dev/countries?fields=name,alpha3Code,capital,region,population,area,latlng,flags
```

La respuesta incluye nombre, código de tres letras, capital, región, población, superficie, coordenadas y bandera. De los 250 registros probados, 10 no tenían superficie, 5 no tenían capital y 1 no tenía coordenadas. La interfaz debe mostrar esos datos como no disponibles cuando corresponda. El clima de un país sin coordenadas debe indicar que no se puede consultar.

Para el clima se adoptó como opción de trabajo [Open-Meteo](https://open-meteo.com/en/docs), que acepta latitud y longitud y permite pedir temperatura y estado actual. **Esta es una elección técnica del equipo; el enunciado no especifica una API meteorológica.** Las coordenadas del dataset representan una referencia geográfica del país, por lo que la interfaz debe decir «clima en una ubicación de referencia» y no «clima de la capital». Las URLs, campos y otras opciones están reunidos en [APIS_EQUIPO.md](APIS_EQUIPO.md).

## Contrato común antes de trabajar en paralelo

La persona 1 entregará un único array en memoria llamado `paises` después de la carga. El resto del equipo consumirá objetos normalizados con esta forma:

```js
{
  codigo: 'AFG',             // código único de tres letras
  nombre: 'Afghanistan',     // texto
  capital: 'Kabul',          // texto o null
  region: 'Asia',            // texto
  poblacion: 40218234,      // número o null
  superficie: 652230,      // número o null, km²
  coordenadas: [33, 65],    // [latitud, longitud] o null
  banderaUrl: 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5c/Flag_of_the_Taliban.svg/320px-Flag_of_the_Taliban.svg.png' // URL o null
}
```

El ejemplo corresponde al primer registro que devolvió la API el 14/09/2026. Es una muestra del contrato, no un dato fijo: los datos reales procederán de cada respuesta. Los integrantes no consultarán otra vez la lista de países ni modificarán `paises` directamente. Favoritos y comparación se guardan aparte como arrays de códigos `codigo`.

Estado compartido propuesto para la interfaz: búsqueda, región, campo de orden, dirección, número de página, códigos favoritos y códigos seleccionados para comparar. Solo el archivo coordinador modifica ese estado; cada módulo recibe los datos que necesita y devuelve un resultado o ejecuta una acción claramente definida.

La lista visible se calcula en este orden:

```text
paises → búsqueda por nombre → filtro de región → copia y ordenamiento →
dirección asc/desc → slice() de 20 → tarjetas
```

Al cambiar búsqueda, región u orden, la página vuelve a 1. Se propone calcular estadísticas sobre los resultados filtrados **antes** de paginar, para que no cambien solo por pasar de página; esta es una decisión de interfaz del equipo. Se debe conservar el array original; `sort()` y `reverse()` actúan sobre una copia.

## Repartición y entregables

| Persona | Responsabilidad principal | Entrega concreta | Plus útil para la revisión |
|---|---|---|---|
| **1 — tú** | Estructura del proyecto, carga de países, normalización y coordinación | Página base, módulo de carga, contrato de datos, estados de carga/error y arranque de la aplicación | Botón de reintento y validación de respuesta aunque HTTP sea 200 |
| **2** | Búsqueda, regiones y tarjetas | Funciones de búsqueda y regiones; renderizado de resultados y detalle por `find()` al pulsar una tarjeta | Mensaje «sin resultados» y nombres accesibles para botones e imágenes |
| **3** | Ordenamiento y estadísticas | Orden por nombre, población y superficie; asc/desc; total/media de población, país más poblado y detección de países sin capital | Manejo claro de superficie/capital faltante y pruebas de que `paises` no cambia |
| **4** | Favoritos y paginación | Altas/bajas de favoritos con `localStorage`; cálculo de páginas y `slice()` de 20 | Restaurar favoritos al recargar y controlar páginas fuera de rango |
| **5** | Comparador y clima | Selección de hasta tres códigos; vista comparativa; clima de favoritos con `Promise.all()` | Fallos de clima aislados por país para que un error no oculte los demás |

La persona 1 también integra y revisa el trabajo de los otros cuatro. La interfaz y los estilos se construyen de forma colaborativa, pero cada sección necesita un responsable para evitar ediciones simultáneas del mismo archivo.

Archivos propuestos para evitar conflictos: persona 1, `index.html`, `styles.css`, `src/app.js` y `src/api/paises.js`; persona 2, `src/features/explorador.js`; persona 3, `src/features/analisis.js`; persona 4, `src/features/favoritos.js` y `src/features/paginacion.js`; persona 5, `src/features/comparador.js` y `src/api/clima.js`. `app.js` será el único punto que conecte los módulos y actualice la vista completa.

### Funciones que cada persona debe exponer

Estas firmas son una propuesta de integración. Se pueden ajustar en equipo, pero cada cambio debe comunicarse antes de modificar `app.js`.

| Persona | Funciones esperadas |
|---|---|
| 2 | `filtrarPorNombre(paises, consulta)`, `obtenerRegiones(paises)`, `filtrarPorRegion(paises, region)`, `buscarPorCodigo(paises, codigo)` y una función para renderizar tarjetas en `#resultados` |
| 3 | `ordenarPaises(paises, criterio, direccion)` y `calcularEstadisticas(paises)` |
| 4 | `paginar(paises, pagina, tamano = 20)`, `leerFavoritos()`, `guardarFavoritos(codigos)` y `alternarFavorito(codigos, codigo)` |
| 5 | `agregarAlComparador(codigos, codigo)`, `quitarDelComparador(codigos, codigo)` y `consultarClimas(favoritos, paises)` |

La persona 1 ya dejó `cargarPaises()` y `normalizarPais()` en `src/api/paises.js`, además de las URLs y campos compartidos en `src/config/apis.js`. `cargarPaises()` devuelve el array con el contrato normalizado y `app.js` lo mantiene en memoria para pasarlo a las demás funciones.

## Acuerdos de integración

1. La persona 1 publica el contrato de datos y una pequeña muestra de objetos con la misma forma antes de que se implementen las otras funciones.
2. Cada persona trabaja en su módulo. Los módulos de lógica reciben arrays u opciones por parámetros; no hacen `fetch()` de países ni leen una copia propia de la lista.
3. Las acciones de la interfaz (buscar, ordenar, cambiar página, favorito, comparar) actualizan el estado central y vuelven a calcular la vista. Se evita que dos módulos construyan tarjetas diferentes.
4. Se revisa cada aporte con escenarios reales: búsqueda + región + orden + página; favoritos después de recargar; tres países en comparación; datos ausentes; API caída.
5. Se comprueba que no haya bucles `for` clásicos y que `paises` conserve el orden y contenido originales tras ordenar o filtrar.
6. Al final, el equipo prepara una demostración y la evidencia de entrega en uno de los formatos indicados por UTP: PDF, video, PPT o exposición.

## Punto académico por confirmar

UTP+Class registra esta tarea con `isGroup=false`. Eso indica que la actividad está configurada como individual en la plataforma; la colaboración de cinco personas no confirma por sí sola que se permita una entrega grupal. Conviene verificar con el docente cómo deben presentar o entregar el trabajo de los cinco. La fecha límite registrada es 16/09/2026 a las 14:51:05, sin zona horaria explícita en la respuesta recuperada.
