# ChapaTuPromo — Guión de Exposición

> Lee esto en voz alta tal como está escrito. Cada sección corresponde a un archivo HTML.
> Cuando el guión diga *"acá pueden ver..."* o *"si se fijan..."*, señala esa parte en el código.

---

## Introducción general

Buenas, voy a presentar mi proyecto que se llama **ChapaTuPromo**.

La idea es una plataforma web para Supermercados Peruanos, específicamente para Plaza Vea, donde se muestran productos próximos a vencer con descuentos reales. El usuario puede ver el menú, revisar las ofertas del día, hacer un pedido, registrarse e ingresar a su cuenta.

El proyecto tiene 7 páginas HTML: inicio, menú de productos, ofertas del día, mis pedidos, hacer pedido, login y registro. Todas están conectadas entre sí mediante enlaces.

Vamos página por página.

---

## 1. index.html — Página de Inicio

Esta es la página principal, la que el usuario ve primero.

Lo primero que tengo acá es la estructura base de cualquier documento HTML. Empiezo con `<!DOCTYPE html>` que le dice al navegador que esto es HTML5. Luego `<html lang="es">` que es el elemento raíz, y el atributo `lang="es"` indica que el contenido está en español.

Dentro del `<head>` tengo tres cosas importantes. El `<meta charset="UTF-8">` asegura que los caracteres especiales como tildes o la ñ se muestren correctamente. El `<meta name="viewport">` controla cómo se ve la página en dispositivos móviles. Y el `<title>` es el texto que aparece en la pestaña del navegador, en este caso *"ChapaTuPromo | Inicio"*.

Ahora en el `<body>` empiezo con el `<header>`. El header es la cabecera de la página, lo que siempre está arriba. Acá puse el nombre del sitio en un `<h1>` que es el encabezado más importante de la página, y un párrafo `<p>` con `<strong>` para resaltar el slogan en negrita.

Dentro del header también está el `<nav>`, que es la barra de navegación. Usé etiquetas `<a href>` para cada enlace, separadas con una barra vertical. Cada `href` apunta a otro archivo HTML del proyecto: menu.html, form_pedido.html, ofertas.html, pedidos.html, login.html y registrar.html. Así el usuario puede moverse entre páginas.

Después del header puse un `<hr>` que es una línea horizontal, la uso para separar visualmente el encabezado del contenido.

El contenido lo organicé en varias secciones usando la etiqueta `<section>`. Cada `<section>` agrupa un bloque temático. Tengo una sección de bienvenida, una que explica qué es ChapaTuPromo, una de cómo funciona y una de tiendas disponibles.

La sección de *"Cómo funciona"* la estructuré con un `<ol>`, que es una lista ordenada. La usé acá porque los pasos tienen un orden lógico: primero revisas, luego eliges, luego pagas. Si el orden importa, uso `<ol>`. Cada paso es un `<li>`.

La sección de tiendas usa una `<table border="1">` para mostrar la información de forma estructurada en filas y columnas. Tengo una fila de encabezados con `<th>` para Tienda, Dirección, Distrito y Horario, y luego una `<tr>` por cada Plaza Vea con sus datos en `<td>`.

Finalmente el `<footer>` con el símbolo de copyright usando `&copy;`, el año, y la información de contacto y redes sociales.

---

## 2. menu.html — Menú de Productos

Esta página muestra todos los productos disponibles organizados por categorías.

La estructura del `<head>` y el `<header>` son iguales a las demás páginas del proyecto, lo hice así para mantener consistencia. El nav tiene los mismos enlaces principales.

El contenido está dentro de un `<section>`. Uso `<h2>` para el título principal de la sección y `<h3>` para cada categoría de productos: Repostería y Panadería, Lácteos, Carnes y Embutidos, y Preparados.

Por cada categoría tengo una tabla separada con `<table border="1">`. La razón de tener varias tablas en lugar de una sola es que así cada categoría está claramente delimitada y es más fácil de leer.

Cada tabla tiene una fila de encabezados con `<th>`: Producto, Descripción, Vence, Precio original, Descuento, Precio ChapaTuPromo y Acción. Y luego una `<tr>` por cada producto.

En la columna de precio uso `<strong>` para resaltar el precio con descuento porque es lo que más le interesa al usuario. Y en la columna de Acción hay un `<a href>` que lleva directamente a form_pedido.html, para que el usuario pueda hacer su pedido desde ahí.

---

## 3. ofertas.html — Ofertas del Día

Esta página muestra solo los productos con mayor descuento, organizados por urgencia de vencimiento.

Tengo tres grupos: los que vencen hoy con 50% de descuento, los que vencen mañana con 40%, y los que vencen en dos días con 30%.

Algo que usé acá y quiero destacar es la etiqueta `<del>`. Para los precios originales, en lugar de solo mostrar el número, los envolví en `<del>` que los muestra tachados. Así el usuario ve visualmente que ese precio ya no aplica y que el precio de abajo es el real con descuento. Por ejemplo: ~~S/. 45.00~~ y abajo S/. 22.50.

También uso `<strong>` para el porcentaje de descuento y para el precio final, porque esos son los datos más importantes que el usuario tiene que notar de un vistazo.

---

## 4. pedidos.html — Mis Pedidos

Esta página muestra el historial de pedidos realizados.

Acá tengo una tabla grande con nueve columnas: número de pedido, fecha, cliente, productos, cantidad, total, tipo de entrega, pago y estado.

Una cosa importante que usé acá es el atributo `colspan`. En la última fila de la tabla puse una celda que ocupa las 9 columnas con `colspan="9"`, para mostrar el resumen general: total de pedidos, cuántos están en proceso y cuántos fueron entregados. Así no rompo la estructura de la tabla pero puedo mostrar ese dato de forma limpia.

En la columna de estado uso `<strong>` para resaltar si el pedido está *Listo para recojo*, *En camino*, *Preparando* o *Entregado*.

---

## 5. form_pedido.html — Hacer Pedido

Esta es la página más completa del proyecto porque tiene el formulario de pedido.

El formulario empieza con `<form action="#" method="post">`. El `action="#"` significa que por ahora el formulario no envía los datos a ningún servidor, es solo la estructura. El `method="post"` es la forma correcta de enviar datos de formulario.

Organicé el formulario en seis secciones usando `<h3>` para títulos: datos del cliente, selección de productos, tipo de entrega, tienda de recojo, dirección de delivery y método de pago.

Para los datos del cliente uso campos `<input type="text">` con `<label for>` que está vinculado al `id` del input. Esto es importante porque cuando el usuario hace clic en el label, el cursor va directo al campo. Todos los campos de datos del cliente tienen el atributo `required`, que obliga al usuario a llenarlos antes de poder enviar.

Para la selección de productos uso `<input type="checkbox">`. Elegí checkbox porque el usuario puede seleccionar varios productos a la vez, no es una sola opción. Cada checkbox tiene un `name` y un `value` que identifica qué producto es.

Para el tipo de entrega y el método de pago usé `<select>` con `<option>`. Lo elegí porque son opciones únicas, el usuario solo puede elegir una. Las opciones del select son las tiendas disponibles para recojo, o los métodos de pago: Agora Pay, Yape, Plin o tarjeta.

Al final hay un `<button type="submit">` que envía el formulario.

---

## 6. login.html — Ingresar

Esta es la página de inicio de sesión.

Tiene un formulario simple con dos campos: correo y contraseña, ambos `type="text"` con sus respectivos `<label for>`. Los dos tienen `required` porque no puedes ingresar sin llenar ambos. El botón de envío es un `<button type="submit">`.

También hay un enlace `<a href="registrar.html">` para los usuarios que todavía no tienen cuenta, para que puedan ir a registrarse.

---

## 7. registrar.html — Crear Cuenta

Esta página tiene el formulario de registro de nuevo usuario.

Lo dividí en dos grupos con `<h3>`: datos personales y creación de contraseña.

En datos personales tengo nombre completo, correo y celular, todos `type="text"` con `required`. Debajo del campo de celular puse una indicación con `<small>` que dice el formato esperado. Uso `<small>` porque es información secundaria, no es el dato principal sino una ayuda para el usuario.

En la sección de contraseña tengo dos campos: contraseña y confirmar contraseña, también `type="text"` con `required`. El botón de envío es `<button type="submit">`.

Al final hay un enlace a login.html por si el usuario ya tiene cuenta.

---

## Cierre

Para cerrar, lo que quiero destacar de este proyecto es que apliqué todo lo que hemos visto en clases: la estructura base de HTML con `<html>`, `<head>` y `<body>`, las etiquetas semánticas como `<header>`, `<nav>`, `<section>` y `<footer>`, tablas con `<table>`, `<tr>`, `<th>`, `<td>` y `colspan`, formularios con `<form>`, `<input>`, `<label>`, `<select>` y `<button>`, elementos multimedia como `<strong>`, `<del>`, `<small>`, `<em>`, y navegación entre páginas con `<a href>`.

Todo conectado, todo funcional a nivel de estructura HTML.

Eso sería todo, quedo abierto a preguntas.
