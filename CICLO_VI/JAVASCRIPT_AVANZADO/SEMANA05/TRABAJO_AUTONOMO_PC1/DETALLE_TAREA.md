# Trabajo Autónomo PC1 — Explorador de Países

## Identificación

- **Semana:** 5
- **Título:** TRABAJO AUTÓNOMO PC1
- **Puntaje máximo:** 20 puntos
- **Intentos permitidos:** 2
- **Configuración de grupo en UTP+Class:** `isGroup=false`
- **Estado observado:** sin iniciar y sin entregar
- **Disponible desde:** 2026-09-10 14:31:05
- **Disponible hasta:** 2026-09-16 14:51:05
- **Formato de entrega indicado:** PDF, video, PPT o exposición

> Las fechas y el estado se copiaron tal como aparecen en la información recuperada de UTP+Class. No se asumió una zona horaria.

## Qué se debe desarrollar

Crear una aplicación llamada **“Explorador de Países”**, centrada en el manejo de arrays y sus métodos.

Al iniciar la aplicación, se debe consultar la API de Rest Countries y guardar la respuesta en un array en memoria:

```text
https://restcountries.com/v3.1/all
```

Después de esa carga inicial, las operaciones deben realizarse sobre el array almacenado, sin volver a solicitar los países. Si el endpoint completo no funciona, el enunciado propone usar:

```text
https://restcountries.com/v3.1/all?fields=name,flags,capital,population,cca3
```

## Funcionalidades solicitadas

1. **Buscador:** filtrar por nombre usando `filter()` e `includes()`.
2. **Filtro por región:** mostrar regiones como Europe, Asia y Africa; las opciones del `select` deben generarse dinámicamente con `map()` y eliminar repetidos con `Set` o `reduce()`.
3. **Ordenamiento:** ordenar por nombre, población o superficie con `sort()` y permitir ascendente/descendente mediante un botón y `reverse()`.
4. **Estadísticas:** calcular:
   - población total y población media con `reduce()`;
   - país más poblado con `Math.max(...array.map())`;
   - si existe algún país sin capital con `some()` o `every()`.
5. **Detalle al seleccionar:** usar `find()` para obtener el país cuando se haga clic en una tarjeta.
6. **Favoritos:** mantener los códigos de países en un array guardado en `localStorage`:
   - añadir con spread: `[...favs, code]`;
   - quitar con `filter()`;
   - comprobar con `includes()`;
   - serializar con `JSON.stringify()`.
7. **Comparador:** permitir seleccionar hasta 3 países. Al intentar agregar un cuarto, se debe avisar y no añadirlo.
8. **Paginación:** mostrar 20 resultados por página usando `slice()`.
9. **Clima de favoritos:** usar `Promise.all()` sobre un array de peticiones para consultar simultáneamente el clima de los países favoritos.

## Restricciones técnicas

- No usar bucles `for` clásicos.
- Usar métodos de arrays, entre ellos `map`, `filter`, `reduce`, `find`, `some`, `every`, `sort` y `forEach`.
- No mutar el array original de países.
- Antes de usar `sort()`, trabajar sobre una copia, por ejemplo `[...paises]`, porque `sort()` modifica el array sobre el que se ejecuta.
- Renderizar la lista con `map().join('')` o creando nodos y usando `append(...nodos)`.
- El filtrado, ordenamiento, búsqueda y paginación deben poder combinarse.

## Rúbrica

| Criterio | Peso |
|---|---:|
| Uso correcto de métodos de array (`map`, `filter`, `reduce`, `sort`, `find`) | 35% |
| Inmutabilidad y encadenamiento sin `for` | 15% |
| Consumo de APIs y manejo de errores | 20% |
| Favoritos y paginación con arrays | 15% |
| Interfaz y estados de carga | 15% |

## Reto extra

Hacer que el filtro, el orden, la búsqueda y la paginación funcionen combinados en una sola cadena de transformaciones sobre el array original, sin variables globales intermedias.

## Punto que requiere confirmación

El enunciado exige traer el clima de los países favoritos mediante `Promise.all()`, pero no especifica qué servicio meteorológico, URL o formato de respuesta se debe utilizar. No se asume una API concreta; conviene confirmarlo con el docente antes de implementar esa parte.

## Checklist de trabajo

- [ ] Crear la interfaz del Explorador de Países.
- [ ] Cargar los países una sola vez con `fetch()` y guardar el resultado en `paises`.
- [ ] Implementar estados de carga y manejo de errores.
- [ ] Implementar búsqueda por nombre.
- [ ] Generar y aplicar el filtro de regiones.
- [ ] Implementar orden por nombre, población y superficie.
- [ ] Mantener la inmutabilidad al ordenar y transformar.
- [ ] Mostrar las estadísticas solicitadas.
- [ ] Crear tarjetas y obtener el detalle con `find()`.
- [ ] Implementar favoritos con `localStorage`.
- [ ] Implementar el comparador con máximo de 3 países.
- [ ] Implementar paginación de 20 resultados.
- [ ] Implementar las peticiones simultáneas de clima con `Promise.all()`.
- [ ] Preparar la entrega en uno de los formatos indicados: PDF, video, PPT o exposición.

## Resumen para realizar la tarea

Debo crear una aplicación web que descargue una sola vez la lista de países desde Rest Countries y permita buscar, filtrar, ordenar, paginar, ver estadísticas, consultar detalles, guardar favoritos y comparar hasta tres países. La implementación debe demostrar métodos de arrays, evitar los bucles `for` clásicos y conservar el array original sin mutarlo. También debo manejar la carga y los errores de la API y preparar una evidencia en PDF, video, PPT o exposición.

No se realizó ninguna entrega ni publicación en UTP+Class.
