# Guión de exposición — APF2 ChapaTuPromo (3 minutos)

**Estrategia:** primero muestro cómo se ve y se navega (demo), después abro el código y explico lo importante.

**Antes de empezar:**
- Abrir `index.html` en el navegador a pantalla completa.
- Tener VSCode/editor con `estilos_chapatupromo.css` ya abierto en otra pestaña.
- Tener `menu_chapatupromo.html` listo para mostrar.

---

## ⏱️ 0:00 – 0:20  |  APERTURA (20 seg)

> "Buenos días/tardes profesora. Mi proyecto se llama **ChapaTuPromo**.
>
> La idea es una plataforma web donde las tiendas pueden vender productos próximos a vencer con descuentos de hasta 50%, para reducir el desperdicio de alimentos.
>
> Lo desarrollé con **HTML5 puro y CSS3**, sin JavaScript ni frameworks, aplicando todo lo visto en clase desde la semana 6 hasta la 9."

---

## ⏱️ 0:20 – 1:30  |  DEMO NAVEGABLE (70 seg)

### Empieza en `index.html`

> "Esta es la página de inicio. Tengo un **header con el logo y la navegación**, un **hero principal** con dos botones que llevan al menú y a ofertas, y abajo, los 5 pasos de cómo funciona en un **grid de 5 columnas**."

**Acción:** Pasa el mouse sobre el botón "Ver Menu" → se hace un poco más grande. Y señala el logo del carrito en el header que flota arriba y abajo.

> "Le apliqué una **transición con transform scale** para que cuando pases el mouse el botón crezca suavemente. Y al logo le puse una **animación con @keyframes** para que flote arriba y abajo todo el tiempo."

### Click en "Menu de Productos"

> "Aquí está el menú con las 4 categorías de productos. Cada categoría es una **card con bordes redondeados** y al pasar el mouse se levanta unos pixeles."

**Acción:** Hover sobre una categoría → se levanta. Hover sobre una fila → se pinta de morado clarito.

### Click en "Hacer Pedido"

> "Este es el formulario de pedido. Los productos van en un **grid de 2 columnas** para que se vean más ordenados. Cuando entro a un input, el borde se pone dorado."

**Acción:** Click en un input → muestra el focus dorado.

### Click en "Mis Pedidos"

> "Aquí tengo arriba **3 stat cards** con el resumen de pedidos en un grid de 3 columnas, y abajo la tabla con todos los pedidos."

### Click en "Ofertas del Dia"

> "Las ofertas las organicé por urgencia: rojo para las que vencen hoy, naranja para mañana, morado para 2 días. Es como un **sistema de semáforo visual**. Y al badge rojo le puse una **animación de latido con @keyframes** para que resalte la urgencia."

**Acción:** Señala el badge rojo "Vencen HOY" que está pulsando.

### Demo responsive (¡importante!)

**Acción:** Achicar la ventana del navegador hasta que se vea como tablet, luego como celular.

> "Y todo es **responsive con media queries**. Cuando llego a 900 píxeles cambia para tablet, y a 600 píxeles se ve como en celular: todo se acomoda en una columna."

---

## ⏱️ 1:30 – 2:40  |  CÓDIGO (70 seg)

> "Ahora les muestro lo más importante del código."

### 1️⃣  GRID MAESTRO  (en `estilos_chapatupromo.css`, líneas 5-20)

**Abrir el CSS y mostrar:**

```css
body {
  display: grid;
  grid-template-rows: auto 1fr auto;
  min-height: 100vh;
}
```

> "Apliqué **Grid Layout en el body** con 3 filas: el header arriba, el contenido en el medio que se estira con `1fr`, y el footer abajo. Así garantizo que el footer siempre quede pegado abajo en todas las páginas."

### 2️⃣  GRID INTERNO POR PÁGINA  (sección 14, líneas ~440)

**Mostrar:**

```css
.productos-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 5px 20px;
}
```

> "Y dentro de cada página tengo un grid específico. Este por ejemplo es el del formulario: los productos van en 2 columnas. El `gap` lo aprendimos en la semana 8."

### 3️⃣  TRANSICIÓN + TRANSFORM  (sección 9, líneas ~290)

**Mostrar:**

```css
.btn-hero {
  transition: background-color 0.3s, color 0.3s, transform 0.3s;
}
.btn-hero:hover {
  transform: scale(1.05);
}
```

> "Esta es la animación principal del botón del hero. Usé `transition` y `transform: scale` que vimos en la semana 7. Cuando pasas el mouse el botón crece un 5% suavemente."

### 4️⃣  ANIMACIONES @keyframes  (sección 19)

**Mostrar:**

```css
@keyframes flotar {
  from { transform: translateY(0); }
  to   { transform: translateY(-6px); }
}

@keyframes latido {
  from { transform: scale(1); }
  to   { transform: scale(1.05); }
}

header img {
  animation: flotar 1.5s infinite alternate;
}

.resumen-hoy {
  animation: latido 1s infinite alternate;
}
```

> "Tengo dos animaciones con `@keyframes` de la semana 7. La primera, `flotar`, hace que el logo del header suba y baje. La segunda, `latido`, hace que el badge rojo de las ofertas que vencen hoy pulse para llamar la atención. La palabra clave `infinite alternate` significa que se repite siempre y va de ida y vuelta."


### 5️⃣  MEDIA QUERIES  (sección 20, al final del archivo)

**Mostrar el bloque completo de `@media`:**

```css
@media (max-width: 900px) {
  .pasos-grid { grid-template-columns: 1fr 1fr; }
  nav { flex-direction: column; }
}

@media (max-width: 600px) {
  .pasos-grid { grid-template-columns: 1fr; }
  .hero-botones { flex-direction: column; }
}
```

> "Y al final del archivo están los media queries de la semana 9. Tengo 2 breakpoints: 900 píxeles para tablet, donde los grids pasan a 2 columnas, y 600 píxeles para celular, donde todo colapsa a una sola columna."

---

## ⏱️ 2:40 – 3:00  |  CIERRE (20 seg)

> "En resumen, mi proyecto cumple todos los requisitos del APF2:
>
> Uso **Flexbox** en el header y footer, **Grid Layout** en las 7 páginas, **transiciones y transformaciones CSS** en cada página, **animaciones con @keyframes** en el logo y en el badge de urgencia, y **responsive con media queries** para tablet y celular.
>
> Todo el código está en mi repositorio de GitHub. Muchas gracias."

---

# 📋 Checklist mental antes de exponer

- [ ] Tengo `index.html` abierto en pantalla completa
- [ ] Tengo el editor con `estilos_chapatupromo.css` en otra pestaña
- [ ] Conozco las 4 líneas/secciones que voy a mostrar del CSS
- [ ] Practiqué redimensionar la ventana para el demo responsive
- [ ] Tengo el link de GitHub a la mano

---

# 💡 Frases técnicas clave para sonar pro

Si la profe pregunta algo, usa estas:

| Si pregunta sobre... | Responde algo como... |
|---|---|
| **Grid vs Flexbox** | "Flexbox lo usé para alineaciones en una sola dirección, como el header. Grid para layouts bidimensionales como las cards de productos." |
| **¿Por qué `1fr`?** | "Es la unidad fraccionaria del grid, divide el espacio disponible. `1fr 1fr` significa 2 columnas iguales." |
| **¿Por qué `auto 1fr auto` en el body?** | "Auto para que header y footer ocupen solo el espacio que necesitan, y `1fr` para que el contenido se estire y empuje el footer hacia abajo." |
| **¿Qué hace `border-collapse: separate`?** | "Permite que los bordes de las celdas se vean separados, eso me deja aplicar `border-radius` en las esquinas." |
| **¿Por qué `box-sizing: border-box`?** | "Para que el padding y el border cuenten dentro del ancho del elemento. Sin eso, los inputs con `width: 100%` se salen del card." |
| **¿Diferencia entre transition y @keyframes?** | "La `transition` necesita un disparador como `:hover` para activarse. Con `@keyframes` la animación corre sola desde que carga la página, y puedo hacer que se repita infinitas veces con `infinite`." |
| **¿Qué hace `infinite alternate`?** | "`infinite` repite la animación para siempre, y `alternate` hace que vaya de ida y vuelta. Sin `alternate` saltaría al estado inicial bruscamente." |
| **¿Por qué Google Fonts?** | "Para que el sitio se vea con tipografías consistentes en cualquier máquina. Las cargo desde el `<link>` en el HTML." |

---

# 🎯 Tip de exposición

- Habla en **primera persona**: "yo apliqué", "yo decidí", "yo elegí".
- Cuando muestres código, **señala con el cursor** la línea exacta.
- Si te trabás, di "como vimos en la semana X" — eso da seguridad.
- No leas el CSS literal. **Explica qué hace**, no lo que dice.
- Si te sobra tiempo, muestra otra página (login o registrar). Si te falta, salta el menú y ve directo al formulario.
