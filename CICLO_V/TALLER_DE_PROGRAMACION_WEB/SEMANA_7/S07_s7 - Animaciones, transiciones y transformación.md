# Animaciones, transiciones y transformación con CSS
**Semana 07 - Sesión 07**

**TALLER DE PROGRAMACIÓN WEB**
M.Sc. Jesamin Melissa Zevallos Quispe
c29741@utp.edu.pe

---

## Dudas de la clase anterior
* Estilos CSS
* Modelo de texto
* Modelo de caja

---

## Conocimientos previos
* **Uso de redes sociales y aplicaciones móviles:** Familiarizados con efectos visuales como botones que cambian de color, imágenes que giran, menús que se despliegan suavemente y pantallas que se deslizan.
* **Conocimientos técnicos previos requeridos:** Manejo de HTML y CSS.

---

## Logro de aprendizaje
Al finalizar la sesión, el estudiante:
Aplica propiedades de CSS para realizar transiciones, transformaciones y animaciones en elementos HTML, comprendiendo su utilidad en la mejora de la experiencia de usuario en sitios web.

---

## Utilidad
**¿Para qué sirve esto en la vida real?**
* Mejora la interacción y experiencia de usuario (UX) en sitios web y aplicaciones móviles.
* Se usa en interfaces profesionales como formularios con efectos de validación, banners animados o botones interactivos.
* Dominar animaciones y transiciones es fundamental para diseñadores front-end, desarrolladores web y freelancers que crean páginas atractivas y modernas.
* Estas técnicas son valoradas en la industria de desarrollo web, donde se destaca la estética y fluidez del sitio.

---

## Contenido
* Animaciones
* Transiciones
* Transformación
* Práctica

---

## ¿Qué es una animación?
Son pequeños cambios en el contenido o aspecto visual de una página, de modo que sea más amigable su visualización. Pueden ser sencillas, como cambiar el color o tamaño de una imagen, así como animaciones más complejas, como por ejemplo transformaciones.

En las animaciones hay dos conceptos básicos importantes:
* **Estado:** Es el estado inicial en el que se encuentra un elemento de la web.
* **Transición:** Es el paso que se realiza entre un estado concreto a otro estado diferente.

---

## Propósito de una animación
* **Mejorar la experiencia del usuario:** El usuario sentirá que su estancia en la web es más satisfactoria y agradable, por lo que preferirá continuar su navegación.
* **Crear narrativas o efectos visuales:** Por último, las animaciones se pueden utilizar para crear narrativas, historias, decorar la página o simplemente dotar de elementos atractivos a una web.
* **Guiar o dirigir la atención del usuario:** Las animaciones son buenas para llamar la atención del usuario y poner el foco en una parte concreta a la que debe darle prioridad.
* **Retroalimentar las acciones del usuario:** Las animaciones permiten dar feedback al usuario para saber si está haciendo lo correcto o, por el contrario, se ha equivocado o ha hecho algo que no debe hacer.

---

## Animación en CSS
Para que el ojo humano pueda percibir una animación se necesitan observar varios fotogramas. En los navegadores, el papel de un fotograma lo toma el estado que revisamos. Como no sería práctico definir múltiples fotogramas para percibir la animación, lo que se hace es definir una serie de fotogramas clave y el navegador irá generando los fotogramas intermedios que ocurren durante la transición de un estado a otro.

---

## Tipos de animaciones
* **Transiciones:** Una animación simple, desde un estado inicial a un estado final.
* **Animaciones:** Una animación más compleja, con 2 o más estados.
* **Trayectos animados:** Movimiento de un elemento a lo largo de un trayecto o ruta.
* **Animaciones de scroll:** Animación controlada por desplazamiento de ratón.
* **View Transition:** Animación de vistas, al cambiar de una página a otra.
* **WebAnimations:** Animaciones nativas complejas, creadas desde Javascript.

---

## Ejemplos prácticos
Taller de Programación Web

---

## Transiciones
Las transiciones son los cambios de un estado a otro.

```css
.element {
  width: 100px;
  height: 100px;
  background: red;
}
.element:hover {
  background: blue;
  transition: 2s;
}

.element {
  width: 100px;
  height: 100px;
  background: red;
}
.element:not(:hover){
  background: blue;
  transition: 2s;
}
```

---

## Desencadenante de la transición
* `:hover`: Cuando mueves el ratón (o dispositivo apuntador) sobre un elemento.
* `:active`: Cuando estás pulsando con el ratón (o dispositivo apuntador) sobre un elemento.
* `:focus`: Cuando un elemento gana el foco (por ejemplo, entrar en un campo de texto `<input>`).

---

## Transformaciones
Estas transformaciones se pueden efectuar en CSS mediante la propiedad `transform` que permite recibir una función de transformación determinada, la cual será aplicada en el elemento HTML en cuestión seleccionado mediante CSS.

```css
.element {
  width: 100px;
  height: 100px;
  background: skyblue;
  transform: translate(100px, 100px) rotate(45deg);
}
```

```html
<body>
    <div class="element"></div>
</body>
```

---

## Animaciones
Una animación permite que un elemento cambie gradualmente de un estilo a otro.
Para que podamos percibir una animación, se debe crear una secuencia de imágenes (aproximadamente 30-60 fotogramas por segundo) para generar el efecto de movimiento que conocemos como animación.

```css
.element {
    width: 100px;
    height: 100px;
    animation: change-color 1.5s infinite;
}

@keyframes change-color {
    from {
        background: red;
    }
    to {
        background: green;
    }
}
```

```html
<body>
    <div class="element"></div>
</body>
```

---

## Preguntas
*Preguntas y dudas generales*

---

## Práctica
Integrar animaciones, transiciones y transformaciones en su proyecto final.

---

## Resumen de la sesión
**¿Qué hemos aprendido hoy?**
Animaciones dentro de CSS, transiciones y transformaciones.
**¿Cuál es la diferencia entre una transición y una animación en CSS?**

---

## ¡Gracias!

TALLER DE PROGRAMACIÓN WEB
M.Sc. Jesamin Melissa Zevallos Quispe
c29741@utp.edu.pe
