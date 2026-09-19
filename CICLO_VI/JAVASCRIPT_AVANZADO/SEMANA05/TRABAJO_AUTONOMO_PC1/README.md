# Explorador de Países — base de la persona 1

Esta carpeta contiene la estrategia del equipo y la primera parte ejecutable del proyecto. La página ya consulta la API de países, normaliza los datos y muestra el total cargado o un error con botón de reintento. Las funciones de búsqueda, estadísticas, favoritos, paginación, comparación y clima se integrarán en las secciones preparadas de `index.html`.

## Ejecutar

Desde esta carpeta:

```bash
python3 -m http.server 8000
```

Dejar esa terminal abierta y abrir `http://localhost:8000/` en un navegador. El servidor solo entrega los archivos estáticos; la consulta a la API ocurre en el navegador. **No abrir `index.html` con doble clic**: eso usa `file://` y bloquea los módulos JavaScript. Si aparece «Preparando la consulta de países…» sin cambiar, comprobar que la barra de direcciones empiece con `http://localhost:8000/`.

Para probar la carga y el contrato de datos sin instalar dependencias:

```bash
npm test
```

## Documentos del equipo

- [Enunciado y requisitos](DETALLE_TAREA.md)
- [Guía para entender la parte 1](GUIA_PARTE_1.md)
- [Estrategia, contrato y reparto](ESTRATEGIA_EQUIPO.md)
- [APIs para compartir con el equipo](APIS_EQUIPO.md)

Las URLs y campos elegidos están centralizados en `src/config/apis.js`. La carga de países rechaza respuestas que no sean arrays, incluso si el servidor responde HTTP 200.
