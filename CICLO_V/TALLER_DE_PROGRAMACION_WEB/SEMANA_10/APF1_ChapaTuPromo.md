---
universidad: UTP
curso: Taller de Programacion Web
tema: Avance de Proyecto Final 1 - ChapaTuPromo
semana: SEMANA_10
tipo_documento: Avance de Proyecto Final (APF1)
paginas: 7
fuente_pdf: APF1_ChapaTuPromo.pdf
seccion: 21003
docente: Jesamin Melissa Zevallos Quispe
estudiante: Max Benites Corazón
codigo: U24217839
ciclo: V
porcentaje_avance: 100%
fecha: 2026-04-22
---

# APF1 - 2026 | ChapaTuPromo

[FIGURA PORTADA: Logo UTP (Universidad Tecnológica del Perú) — recuadro rojo con letras "UTP" en blanco y negro, junto al texto "Universidad Tecnológica del Perú".]

## AVANCE DE PROYECTO FINAL 1

# "ChapaTuPromo"

| Campo                 | Valor                            |
|-----------------------|----------------------------------|
| **CURSO:**            | Taller de Programacion Web       |
| **SECCION:**          | 21003                            |
| **DOCENTE:**          | Jesamin Melissa Zevallos Quispe  |
| **ESTUDIANTE:**       | Max Benites Corazón              |
| **CODIGO:**           | U24217839                        |
| **PORCENTAJE DE AVANCE:** | 100%                         |
| **CICLO:**            | V                                |

Lima, 22 de Abril del 2026

*Universidad Tecnologica del Peru | Taller de Programacion Web | Seccion 21003*

---

## 1. DESCRIPCION DEL PROYECTO

ChapaTuPromo es una plataforma web multi-pagina que busca contribuir a la reduccion del desperdicio de alimentos, permitiendo que comercios oferten productos proximos a vencer a precios accesibles. El sitio web permite a los usuarios registrarse, navegar un menu de productos, revisar ofertas especiales y realizar pedidos en linea.

## 2. OBJETIVO DEL PROYECTO

Desarrollar un sitio web completo utilizando HTML puro que cumpla con los estandares basicos de estructura, navegacion y usabilidad, demostrando competencia en la creacion de formularios, tablas y vinculacion de paginas.

## 3. FUNCIONALIDADES IMPLEMENTADAS

- Sistema de autenticacion: login y registro de usuarios.
- Pagina de inicio con navegacion clara hacia todas las secciones.
- Menu de productos con tabla de precios y descuentos.
- Formulario de pedido con validaciones y opciones de entrega.
- Tabla de pedidos realizados con estado de cada uno.
- Seccion de ofertas especiales con indicadores de urgencia.

## 4. ESTRUCTURA DE PAGINAS

| Archivo                          | Descripcion                                  |
|----------------------------------|----------------------------------------------|
| **index.html**                   | Pagina de inicio con descripcion del proyecto. |
| **login_chapatupromo.html**      | Formulario de inicio de session.             |
| **registrar_chapatupromo.html**  | Formulario de registro de nuevo usuario.     |
| **menu_chapatupromo.html**       | Menu de productos disponibles.               |
| **formulario_chapatupromo.html** | Formulario para realizar pedidos.            |
| **pedidos_chapatupromo.html**    | Tabla de pedidos realizados.                 |
| **ofertas_chapatupromo.html**    | Promociones y ofertas especiales.            |

---

## Index.html

[FIGURA: Captura de pantalla del navegador mostrando la página `index.html` de ChapaTuPromo. URL visible: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/index.html`. Contiene logo de ChapaTuPromo (carrito de compras), título "ChapaTuPromo", subtítulo "Productos con descuentos reales TODOS los dias.", menú de navegación con enlaces: "Menu de Productos | Hacer Pedido | Ofertas del Dia | Mis Pedidos | Ingresar | Registrarme |", sección "Bienvenido, chapador" con texto explicativo sobre productos de calidad con descuentos de hasta 60%, sección "Que es ChapaTuPromo?", sección "Como funciona?" con lista numerada de 5 pasos: 1. Revisa el menu de productos disponibles, 2. Elige lo que quieres y haz tu pedido en linea, 3. Paga con Agora Pay, Yape, Plin o tarjeta de debito/credito, 4. Elige recojo en tienda mas cercana o delivery a domicilio, 5. Te llegara un correo cuando tu pedido este listo para recoger. Tabla "Tiendas disponibles" con columnas Tienda, Direccion, Distrito, Horario y filas para: Surco (Av. Primavera 1180, Santiago de Surco, 8am - 10pm), San Miguel (Av. La Marina 2000, San Miguel, 8am - 10pm), La Molina (Av. La Molina 2318, La Molina, 8am - 10pm), San Borja (Av. San Luis 1921, San Borja, 8am - 10pm), Chorrillos (Av. Huaylas 370, Chorrillos, 8am - 10pm), Independencia (Av. Tupac Amaru 3200, Independencia, 8am - 10pm). Footer "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Menu_chapatupromo.html

[FIGURA: Captura de pantalla del navegador mostrando la página `menu_chapatupromo.html`. URL: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/menu_chapatupromo.html`. Título "ChapaTuPromo", menú "Inicio | Hacer Pedido | Ofertas del Dia | Mis Pedidos". Sección "Menu de Productos" con cuatro tablas categorizadas:

**Reposteria y Panaderia** — columnas: Producto, Descripcion, Vence, Precio original, Descuento, Precio ChapaTuPromo, Accion. Filas:
- Torta de Chocolate San Antonio 1kg | Torta entera con relleno de manjar blanco y cobertura de chocolate bitter | Hoy | S/. 45.00 | 50% | S/. 22.50 | Añadir al pedido
- Torta Tres Leches 800g | Torta clasica peruana basada en tres leches, sin conservantes artificiales | Mañana | S/. 38.00 | 40% | S/. 22.80 | Añadir al pedido
- Queque Marmoleado Doncello 500g | Queque esponjoso sabor vainilla y chocolate, ideal para loncheras o desayunos | En 2 dias | S/. 14.00 | 30% | S/. 9.80 | Añadir al pedido
- Pan de Molde Bimbo Integral 500g | Pan integral sin corteza, alto en fibra, ideal para sanguiches saludables | En 3 dias | S/. 6.00 | 20% | S/. 4.80 | Añadir al pedido

**Lacteos** — columnas: Producto, Descripcion, Vence, Precio original, Descuento, Precio ChapaTuPromo, Accion. Filas:
- Yogurt Gloria Fresa 1L | Yogurt bebible sabor fresa, sin lactosa, enriquecido con calcio y vitamina D | Mañana | S/. 8.50 | 40% | S/. 5.10 | Añadir al pedido
- Queso Fresco La Preferida 250g | Queso fresco tradicional peruano, ideal para desayuno criollo con pan frances | En 2 dias | S/. 9.00 | 30% | S/. 6.30 | Añadir al pedido
- Crema de Leche Laive 200ml | Crema de leche fresca para cocinar, ideal para salsas y postres criollos | En 3 dias | S/. 5.00 | 30% | S/. 3.50 | Añadir al pedido

**Carnes y Embutidos** — columnas: Producto, Descripcion, Vence, Precio original, Descuento, Precio ChapaTuPromo, Accion. Filas:
- Pollo Entero Refrigerado aprox. 1.8kg | Pollo entero sin menudencia, refrigerado, listo para cocinar. Ideal para caldo o seco de pollo | Mañana | S/. 22.00 | 40% | S/. 13.20 | Añadir al pedido
- Jamon del Pais Suiza 200g | Jamon del pais en lonchas estilo criollo, ideal para sanguiches o la lonchera | En 2 dias | S/. 12.00 | 30% | S/. 8.40 | Añadir al pedido
- Salchicha estilo Huacho Suiza 100g | Salchicha de cerdo y res estilo Huacho, para parrilla, carnes o con tuco | En 1 dias | S/. 15.00 | 20% | S/. 12.00 | Añadir al pedido
- Filete de Perico refrigerado 500g | Filete de perico fresco refrigerado, ideal para ceviche o sudado de pescado | Hoy | S/. 18.00 | 50% | S/. 9.00 | Añadir al pedido

**Preparados y Listos para comer** — columnas: Producto, Descripcion, Vence, Precio original, Descuento, Precio ChapaTuPromo, Accion. Filas:
- Ensalada Rusa Bells 250g | Ensalada rusa clasica con papa, zanahoria y betarraga, lista para servir | Hoy | S/. 7.50 | 50% | S/. 3.75 | Añadir al pedido
- Causa Rellena de Atun 300g | Causa limeña tradicional rellena de atun con mayonesa y palta. Lista para consumir | Mañana | S/. 12.00 | 40% | S/. 7.20 | Añadir al pedido

Footer: "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Formulario_chapatupromo.html

[FIGURA: Captura de pantalla superior del navegador mostrando la página `formulario_chapatupromo.html`. URL: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/formulario_chapatupromo.html`. Contiene logo ChapaTuPromo (carrito), título "ChapaTuPromo", menú "Inicio | Ver Menu | Mis Pedidos | Ofertas del Dia". Sección "Hacer mi Pedido" con instrucción "Completa el formulario, paga en linea y recoge en tienda o recibelo en casa.". 

**1. Tus datos** — campos:
- Nombre completo: (placeholder "Ej: Carlos Quispe Mamani")
- Celular de contacto: (placeholder "9XXXXXXXX")
- Correo (te avisamos cuando este listo): (placeholder "tucorreo@gmail.com")

**2. Selecciona tus productos** — instrucción "Marca los productos que quieres incluir en tu pedido:". Lista de checkboxes con productos:
- Torta de Chocolate San Antonio 1kg - S/. 22.50 (50% off, vence hoy)
- Torta Tres Leches 800g - S/. 22.80 (40% off, vence mañana)
- Queque Marmoleado Doncello 500g - S/. 9.80 (30% off)
- Pan de Molde Bimbo Integral 500g - S/. 4.80 (20% off)
- Yogurt Gloria Fresa 1L - S/. 5.10 (40% off, vence mañana)
- Queso Fresco La Preferida 250g - S/. 6.30 (30% off)
- Crema de Leche Laive 200ml - S/. 3.50 (30% off)
- Pollo Entero Refrigerado aprox. 1.8kg - S/. 13.20 (40% off, vence mañana)]

[FIGURA: Captura de pantalla inferior continuación del mismo formulario. Lista de checkboxes (continuación):
- Torta Tres Leches 800g - S/. 22.80 (40% off, vence mañana)
- Queque Marmoleado Doncello 500g - S/. 9.80 (30% off)
- Pan de Molde Bimbo Integral 500g - S/. 4.80 (20% off)
- Yogurt Gloria Fresa 1L - S/. 5.10 (40% off, vence mañana)
- Queso Fresco La Preferida 250g - S/. 6.30 (30% off)
- Crema de Leche Laive 200ml - S/. 3.50 (30% off)
- Pollo Entero Refrigerado aprox. 1.8kg - S/. 13.20 (40% off, vence mañana)
- Jamon del Pais Suiza 200g - S/. 8.40 (30% off)

**3. ¿Como quieres recibir tu pedido?** — Tipo de entrega: (dropdown "Elige una opcion")

**4. Tienda de recojo** — Selecciona tu tienda mas cercana: (dropdown "Elige una tienda")

**5. Direccion de delivery (si elegiste delivery a domicilio)** — campos:
- Direccion completa: (placeholder "Ej: Jr. Luis Flores 234, Miraflores")
- Referencia: (placeholder "Ej: Frente al parque, puerta r...")

**6. Metodo de pago** — Selecciona tu metodo de pago: (dropdown "Elige un metodo de pago")

Botón: "Enviar Pedido".

Footer: "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Pedidos_chapatupromo.html

[FIGURA: Captura de pantalla del navegador mostrando la página `pedidos_chapatupromo.html`. URL: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/pedidos_chapatupromo.html`. Contiene logo ChapaTuPromo (carrito), título "ChapaTuPromo", menú "Inicio | Ver Menu | Nuevo Pedido | Ofertas del Dia". 

Sección "Pedidos Realizados" con texto "Aqui puedes ver el estado de todos tus pedidos. Te avisamos por correo cuando tu pedido este listo para recoger o en camino.".

Tabla con columnas: Num. Pedido, Fecha, Cliente, Productos, Cantidad, Total, Tipo de entrega, Pago, Estado. Filas:
- #001 | 17 abr 2026 | Maria Flores Quispe | Torta de Chocolate San Antonio, Yogurt Gloria Fresa 1L | 2 | S/. 27.60 | Recojo - Plaza Vea Surco | Yape | Listo para recojo
- #002 | 17 abr 2026 | Carlos Quispe Mamani | Pollo Entero Refrigerado, Jamon del Pais Suiza | 2 | S/. 21.60 | Delivery - Jr. Los Pinos 234, SJL | Agora Pay | En camino
- #003 | 17 abr 2026 | Rosa Huaman Coca | Torta Tres Leches, Queso Fresco La Preferida, Crema de Leche Laive | 3 | S/. 32.60 | Recojo - Plaza Vea La Molina | Plin | Preparando
- #004 | 16 abr 2026 | Jose Condori Vargas | Pan de Molde Bimbo Integral, Salchicha estilo Huacho Suiza | 2 | S/. 50.40 | Delivery - Av. Los Heroes 890, Chorrillos | Tarjeta Visa | Entregado
- #005 | 16 abr 2026 | Ana Cochuana Torres | Ensalada Rusa Bells, Causa Rellena de Atun | 2 | S/. 10.95 | Recojo - Plaza Vea San Miguel | Yape | Entregado
- #006 | 16 abr 2026 | Luis Mamani Apaza | Filete de Perico Refrigerado, Queso Fresco La Preferida | 2 | S/. 15.30 | Recojo - Plaza Vea San Borja | Agora Pay | Entregado

Total de pedidos: 6.

Enlace "Hacer un nuevo pedido".

Footer: "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Ofertas_chapatupromo.html

[FIGURA: Captura de pantalla del navegador mostrando la página `ofertas_chapatupromo.html`. URL: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/ofertas_chapatupromo.html`. Contiene logo ChapaTuPromo (carrito), título "ChapaTuPromo", menú "Inicio | Ver Menu Completo | Hacer Pedido | Mis Pedidos".

Título "¡¡¡Ofertas Espectaculares!!! ChapaTuPromo".

**VENCEN HOY - 50% de descuento** — tabla con columnas: Producto, Categoria, Descripcion, Precio original, Descuento, Precio final, Stock, Accion. Filas:
- Torta de Chocolate San Antonio 1kg | Reposteria | Torta entera con manjar y cobertura de chocolate bitter. Lista para servir | S/. 45.00 | 50% OFF | S/. 22.50 | *** ULTIMAS 2 UNIDADES *** | Chapalo ya!
- Filete de Perico Refrigerado 500g | Pescados | Filete fresco refrigerado, ideal para ceviche o sudado. Listo para cocinar | S/. 18.00 | 50% OFF | S/. 9.00 | *** ULTIMAS 1 UNIDAD *** | Chapalo ya!
- Ensalada Rusa Bells 250g | Preparados | Ensalada rusa clasica con papa, zanahoria y betarraga. Lista para servir | S/. 7.50 | 50% OFF | S/. 3.75 | *** ULTIMAS 3 UNIDADES *** | Chapalo ya!

**VENCEN MAÑANA - 40% de descuento** — tabla con columnas: Producto, Categoria, Precio original, Descuento, Precio final, Accion. Filas:
- Torta Tres Leches 800g | Reposteria | S/. 38.00 | 40% OFF | S/. 22.80 | Añadir al pedido
- Yogurt Gloria Fresa 1L | Lacteos | S/. 8.50 | 40% OFF | S/. 5.10 | Añadir al pedido
- Pollo Entero Refrigerado aprox. 1.8kg | Carnes | S/. 22.00 | 40% OFF | S/. 13.20 | Añadir al pedido
- Causa Rellena de Atun 300g | Preparados | S/. 12.00 | 40% OFF | S/. 7.20 | Añadir al pedido

**VENCEN EN 2 DIAS - 30% de descuento** — tabla con columnas: Producto, Categoria, Precio original, Descuento, Precio final, Accion. Filas:
- Queque Marmoleado Doncello 500g | Reposteria | S/. 14.00 | 30% OFF | S/. 9.80 | Añadir al pedido
- Queso Fresco La Preferida 250g | Lacteos | S/. 9.00 | 30% OFF | S/. 6.30 | Añadir al pedido
- Crema de Leche Laive 200ml | Lacteos | S/. 5.00 | 30% OFF | S/. 3.50 | Añadir al pedido
- Jamon del Pais Suiza 200g | Embutidos | S/. 12.00 | 30% OFF | S/. 8.40 | Añadir al pedido

Footer: "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Login_chapatupromo.html

[FIGURA: Captura de pantalla del navegador mostrando la página `login_chapatupromo.html`. URL: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/login_chapatupromo.html`. Contiene logo ChapaTuPromo (carrito), título "ChapaTuPromo".

Sección "Ingresa a tu cuenta" con formulario:
- Correo electronico: (placeholder "tucorreo@gmail.com")
- Contraseña: (placeholder "Tu contraseña")

Botón "Ingresar".

Texto: "No tienes cuenta aun? Registrate aqui y aprovecha nuestras ofertas".

Footer: "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Registrar_chapatupromo.html

[FIGURA: Captura de pantalla del navegador mostrando la página `registrar_chapatupromo.html`. URL: `C:/Users/M&M/Documents/MAX/UTP/CICLO%205/PROGRAMACION%20WEB/SEMANA_5/ChapaTuPromo/registrar_chapatupromo.html`. Contiene logo ChapaTuPromo (carrito), título "ChapaTuPromo".

Texto: "Crea tu cuenta gratis y no te pierdas ninguna promo".

Sección "Crear cuenta nueva" con subsección "Tus datos personales":
- Nombre completo: (placeholder "Ej: Maria Flores Quispe")
- Correo electronico: (placeholder "tucorreo@gmail.com")
- Celular: (placeholder "9XXXXXXXX") — texto guía: "Formato: 9 digitos, empieza con 9. Ej: 987654321"

Subsección "Crea tu contraseña":
- Contraseña: (placeholder "Minimo 6 caracteres")
- Confirmar contraseña: (placeholder "Repite tu contraseña")

Botón "Registrarme".

Texto: "Ya tienes cuenta? Ingresa aca".

Footer: "Síguenos: Instagram: @ChapaTuPromo | Facebook: /ChapaTuPromo | TikTok: @ChapaTuPromo |" y "ChapaTuPromo © 2026 | Todos los derechos reservados".]

---

## Link del repositorio: **Ver proyecto en GitHub**

---

## 5. TECNOLOGIA UTILIZADA

- HTML5 puro.
- Estructura semantica con etiquetas como `<header>`, `<nav>`, `<section>`, `<footer>`, entre otras.
- Tablas para presentacion de datos estructurados.
- Formularios con validaciones de campos required.
- Enlaces internos para navegacion entre paginas.
- Sin estilos CSS.

## 6. OBSERVACIONES

El proyecto cumple con todos los requisitos tecnicos establecidos. Se utilizo unicamente HTML puro sin incluir estilos CSS. Todas las paginas estan interconectadas y la navegacion es clara e intuitiva. El sitio puede ser mejorado en futuras iteraciones con CSS para diseno visual y JavaScript para funcionalidad interactiva.

---

*Universidad Tecnologica del Peru | Taller de Programacion Web | Seccion 21003*
