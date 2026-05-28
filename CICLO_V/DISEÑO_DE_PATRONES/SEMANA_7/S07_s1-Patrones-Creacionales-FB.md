---
universidad: UTP
curso: Diseño de Patrones
tema: Patrones Creacionales — Patrón Factory, Patrón Abstract Factory, Patrón Abstract Builder
semana: 7
sesion: 07
unidad: Unidad de Aprendizaje 2 — Patrones creacionales
tipo_documento: Diapositivas (PowerPoint exportado a PDF)
paginas: 38
fuente_pdf: S07_s1-Patrones-Creacionales-FB.pdf
autor_pdf: Julia
docentes: Docentes de UTP — Sistemas
---

# CURSO: DISEÑO DE PATRONES

## Tabla de Contenidos

- [1. Portada](#1-portada)
- [2. Motivación para el aprendizaje](#2-motivación-para-el-aprendizaje)
- [3. Recordando los aprendizajes](#3-recordando-los-aprendizajes)
- [4. Logro de la sesión](#4-logro-de-la-sesión)
- [5. Importancia de la sesión](#5-importancia-de-la-sesión)
- [6. Temario](#6-temario)
- [7. Patrones creacionales — Factory](#7-patrones-creacionales--factory)
- [8. Patrón Factory — Caso de estudio de ejemplo](#8-patrón-factory--caso-de-estudio-de-ejemplo)
- [9. Patrón Factory — Ejemplo en Java](#9-patrón-factory--ejemplo-en-java)
- [10. Patrones creacionales — Abstract Factory](#10-patrones-creacionales--abstract-factory)
- [11. Patrón Abstract Factory — Ejemplo en Java](#11-patrón-abstract-factory--ejemplo-en-java)
- [12. Patrones creacionales — Builder](#12-patrones-creacionales--builder)
- [13. ¿Cuál es la diferencia entre Factory y Builder?](#13-cuál-es-la-diferencia-entre-factory-y-builder)
- [14. Caso práctico](#14-caso-práctico)
- [15. Aprendizajes](#15-aprendizajes)
- [16. Conclusiones](#16-conclusiones)
- [17. Tarea](#17-tarea)
- [18. Recursos](#18-recursos)
- [19. Cierre](#19-cierre)

---

## 1. Portada

[FIGURA PORTADA: logo UTP (Universidad Tecnológica del Perú) en la parte superior central. Recuadro rojo destacando el título de la sesión. Fondo con patrón de puntos grises y línea decorativa roja/negra en la parte inferior.]

**PRIMERA UNIDAD DE APRENDIZAJE 2: Patrones creacionales**

# CURSO: DISEÑO DE PATRONES

> **SESIÓN N° 07:**
>
> Patrones Creacionales:
> Patrón Factory. Patrón Abstract Factory. Patrón Abstract Builder.

**DOCENTES DE UTP**
**SISTEMAS**

---

## 2. Motivación para el aprendizaje

### 2.1 Lo que debemos tener en cuenta

[FIGURA: ícono de bombilla amarilla junto al título "Motivación para el aprendizaje". En la esquina inferior derecha, ícono de reloj con texto **"5 minutos"**.]

**Lo que debemos tener en cuenta**

### 2.2 ¿QUÉ SON PATRONES Abstract Factory y Factory?

**¿QUÉ SON PATRONES Abstract Factory y Factory?**

Enlace de referencia:

```
https://youtu.be/izFGKo7Z1lg?si=Mb80kFXQg_Zk9cca
```

---

## 3. Recordando los aprendizajes

### 3.1 ¿Qué vimos en la sesión anterior?

**¿Qué vimos en la sesión anterior?**

### 3.2 Ejercicio de completar (repaso Singleton y Prototype)

El patrón Singleton asegura que una clase tenga una única instancia y proporciona un
punto de acceso ______ a esa instancia. En el patrón Prototype, los objetos son
creados mediante la clonación de un ______ existente. El patrón Singleton es útil en
situaciones donde se necesita controlar el acceso a algún ______ compartido. Una
desventaja del patrón Singleton es que puede ser difícil de ______ en sistemas
multihilo. El patrón Prototype permite la creación de nuevos objetos a partir de un
______ sin necesidad de conocer su clase concreta.

[FIGURA: interruptor con etiqueta "Show answers" (desactivado).]

**Palabras disponibles** (banco de respuestas):

- `probar`
- `global`
- `recurso`
- `modelo`
- `prototype`

---

## 4. Logro de la sesión

| Unidad de aprendizaje 2:         | Semana 5, 6 y 7 |
| -------------------------------- | --------------- |
| Patrones creacionales.           |                 |

**Logro específico de aprendizaje:**
Al finalizar la unidad el participante aplica los patrones creacionales para la solución de problemas.

**Temario:**

- Consolidación de temas (Repaso).
- Patrones Creacionales: Introducción a patrones creacionales. Patrón Singleton. Patrón Prototype.
- Patrones Creacionales: Patrón Factory. Patrón Abstract Factory. Patrón Abstract Builder.

> Al final de la sesión el estudiante aplica patrones creacionales:
> Patrón Factory. Patrón Abstract Factory. Patrón Abstract Builder en un contexto definido

---

## 5. Importancia de la sesión

> Un patrón de diseño de fábrica es una forma de
> patrón de diseño de creación que nos permite crear
> un objeto para instanciarlo mediante una interfaz o
> una clase. La fábrica es el método más eficiente
> para crear un objeto. Los objetos se crean
> mediante este método sin proporcionarle el
> razonamiento al cliente. El cliente utiliza la misma
> interfaz estándar para crear un nuevo tipo de
> objeto.

---

## 6. Temario

**¡Que no se te escape nada de tu clase!:**

### SESIÓN N° 07:

> **Patrones Creacionales:**
> **Patrón Factory. Patrón Abstract Factory. Patrón Abstract Builder.**

---

## 7. Patrones creacionales — Factory

### 7.1 Definición

El patrón Factory es un patrón de diseño de creación, lo que significa que se ocupa de la creación de objetos.

En el diseño Factory, generamos objetos sin revelar el mecanismo de creación al cliente, y el cliente crea nuevos tipos de objetos utilizando la misma interfaz estándar.

El objetivo es emplear una función miembro estática (patrón Factory estático) que crea y devuelve instancias mientras mantiene oculta la información del módulo de clase para el usuario.

### 7.2 Biblioteca y clientes

Un patrón de fábrica es una de las ideas de diseño clave para crear un objeto, que permite a los clientes generar objetos de biblioteca de una manera que no esté estrechamente vinculada con la jerarquía de clases de la biblioteca.

¿A qué nos referimos exactamente cuando hablamos de "biblioteca" y "clientes"? Una biblioteca es proporcionada por un tercero y expone algunas API públicas a las que los clientes realizan llamadas para realizar sus trabajos. Los diferentes tipos de vistas que ofrece el sistema operativo Android son un ejemplo sencillo.

### 7.3 Diagrama de clases del patrón Factory

[DIAGRAMA UML — Clases:

- Clase superior: **Factory method** con métodos:
  - `input()`
  - `translation()`
  - `constructor()`
- Tres clases derivadas (con flechas de herencia hacia `Factory method`):
  - **Twowheeler method** — métodos: `input()`, `translation()`, `constructor()`
  - **Threewheeler method** — métodos: `input()`, `translation()`, `constructor()`
  - **Fourwheeler method** — métodos: `input()`, `translation()`, `constructor()`

Las flechas de herencia (vacías) salen de las tres subclases y apuntan hacia la clase Factory method.]

### 7.4 Patrones creacionales — Aplicabilidad

Su principio es similar al polimorfismo en el sentido de que no se requieren modificaciones en el código del cliente. Supongamos que deseamos dibujar varias formas, como rectángulos, cuadrados, círculos, etc. El método Factory Pattern puede usarse para crear la instancia en función de la entrada del usuario.

Podemos reservar un vehículo de una rueda, de dos ruedas, de tres ruedas y de cuatro ruedas en una aplicación de taxi. El cliente puede reservar cualquiera de los viajes que desee desde esta página. Podemos construir una clase llamada Booking con la ayuda de la función Factory, que nos permitirá generar una instancia que acepte la entrada del usuario. Como resultado, el desarrollador no necesita alterar el código completo para implementar la nueva función.

El método Factory elimina el código lógico complejo que es difícil de conservar. También nos impide realizar cambios en la base de código, ya que alterar el código existente puede producir fallas sutiles y hacer que cambie el comportamiento.

---

## 8. Patrón Factory — Caso de estudio de ejemplo

### 8.1 Planteamiento del caso

Supongamos que queremos crear un sitio web que venda libros en varias partes del país.

La primera edición del sitio web solo acepta pedidos de libros, pero a medida que pase el tiempo y nuestro sitio web crezca en popularidad, no agregaremos cosas adicionales a las ventas, como ropa y calzado.

Es un concepto excelente, pero ¿qué pasa con los desarrolladores de software? Ahora deben actualizar todo el código base porque la mayor parte del código está relacionado con la clase del libro y deben alterar todo el código base. Puede resultar en un código desordenado.

### 8.2 Solución con patrón Factory

En lugar de utilizar la construcción de objetos sencilla, utilizamos el patrón Factory específico en la solución para invocar el objeto de construcción.

Ambos métodos de producción de objetos son bastante similares, pero se hace referencia a ellos dentro de la función Factory.

Por ejemplo, nuestros productos de venta, como libros, teléfonos móviles, ropa y accesorios, deberían tener una interfaz de compra que declare un método de compra. Estos métodos se implementarán de forma diferente en cada clase.

---

## 9. Patrón Factory — Ejemplo en Java

### 9.1 Paso 1: Definir la interfaz del producto

```java
// Interfaz de Notificación
public interface Notificacion {
    void enviarMensaje(String mensaje);
}
```

### 9.2 Paso 2: Crear implementaciones concretas del producto

```java
// Implementación concreta de Notificación por Correo Electrónico
public class NotificacionCorreo implements Notificacion {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando correo electrónico: " + mensaje);
    }
}

// Implementación concreta de Notificación por SMS
public class NotificacionSMS implements Notificacion {
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}
```

### 9.3 Paso 3: Crear la Fábrica

```java
// Fábrica de Notificaciones
public class NotificacionFactory {
    public static Notificacion crearNotificacion(String tipo) {
        if (tipo.equalsIgnoreCase("correo")) {
            return new NotificacionCorreo();
        } else if (tipo.equalsIgnoreCase("sms")) {
            return new NotificacionSMS();
        } else {
            throw new IllegalArgumentException("Tipo de notificación desconocido");
        }
    }
}
```

### 9.4 Paso 4: Usar la Fábrica en el Cliente

```java
public class Cliente {
    public static void main(String[] args) {
        // Crear notificación por correo
        Notificacion notificacionCorreo = NotificacionFactory.crearNotificacion("correo");
        notificacionCorreo.enviarMensaje("Hola por correo!");

        // Crear notificación por SMS
        Notificacion notificacionSMS = NotificacionFactory.crearNotificacion("sms");
        notificacionSMS.enviarMensaje("Hola por SMS!");
    }
}
```

### 9.5 Salida esperada

```
Enviando correo electrónico: Hola por correo!
Enviando SMS: Hola por SMS!
```

---

## 10. Patrones creacionales — Abstract Factory

### 10.1 Definición

El patrón Abstract Factory es un patrón de diseño creacional que proporciona una interfaz para crear familias de objetos relacionados o dependientes sin especificar sus clases concretas. Este patrón es útil cuando el sistema debe ser independiente de cómo sus productos son creados, compuestos y representados.

### 10.2 Estructura

- **Abstract Factory**: Declara una interfaz para la creación de productos abstractos.
- **Concrete Factory**: Implementa la interfaz de la fábrica abstracta para crear instancias de productos concretos.
- **Abstract Product**: Declara una interfaz para un tipo de producto.
- **Concrete Product**: Implementa la interfaz del producto abstracto.
- **Client**: Usa solo las interfaces declaradas por las fábricas abstractas y los productos abstractos.

### 10.3 Relación entre Abstract Factory, Factory Method y Prototype

Las clases abstractas de Factory suelen crearse con Factory Methods, aunque también pueden realizarse mediante Prototype.

Los Factory Methods suelen invocarse desde dentro de Template Methods.

La herencia se utiliza para crear objetos en el Factory Method. La delegación se utiliza para crear prototipos.

Normalmente, los diseños comienzan con el Factory Method (menos difícil, más adaptable, proliferan las subclases) y progresan hacia el Abstract Factory, Prototype o Builder (más flexible, más complejo) cuando el diseñador se da cuenta de que se requiere más flexibilidad.

Aunque la creación de prototipos no necesita subclasificación, sí requiere una acción de inicialización. El Factory Method necesita subclasificación pero no necesita inicializar.

Un Factory Method ofrece la ventaja de devolver repetidamente la misma instancia o de devolver una subclase en lugar de un objeto del mismo tipo.

---

## 11. Patrón Abstract Factory — Ejemplo en Java

### 11.1 Paso 1: Definir interfaces de los productos

```java
// Interfaz para Silla
public interface Silla {
    void sentarse();
}

// Interfaz para Sofá
public interface Sofa {
    void acostarse();
}
```

### 11.2 Paso 2: Crear implementaciones concretas de los productos

```java
// Implementación concreta de Silla Moderna
public class SillaModerna implements Silla {
    @Override
    public void sentarse() {
        System.out.println("Sentado en una silla moderna");
    }
}

// Implementación concreta de Sofá Moderno
public class SofaModerno implements Sofa {
    @Override
    public void acostarse() {
        System.out.println("Acostado en un sofá moderno");
    }
}

// Implementación concreta de Silla Victoriana
public class SillaVictoriana implements Silla {
    @Override
    public void sentarse() {
        System.out.println("Sentado en una silla victoriana");
    }
}

// Implementación concreta de Sofá Victoriano
public class SofaVictoriano implements Sofa {
    @Override
    public void acostarse() {
        System.out.println("Acostado en un sofá victoriano");
    }
}
```

### 11.3 Paso 3: Definir la interfaz de la fábrica abstracta

```java
// Interfaz para la fábrica de muebles
public interface MueblesFactory {
    Silla crearSilla();
    Sofa crearSofa();
}
```

### 11.4 Paso 4: Crear las implementaciones concretas de las fábricas

```java
// Fábrica de Muebles Modernos
public class MueblesModernosFactory implements MueblesFactory {
    @Override
    public Silla crearSilla() {
        return new SillaModerna();
    }
    @Override
    public Sofa crearSofa() {
        return new SofaModerno();
    }
}

// Fábrica de Muebles Victorianos
public class MueblesVictorianosFactory implements MueblesFactory {
    @Override
    public Silla crearSilla() {
        return new SillaVictoriana();
    }
    @Override
    public Sofa crearSofa() {
        return new SofaVictoriano();
    }
}
```

### 11.5 Paso 5: Usar la fábrica abstracta en el cliente

```java
public class Cliente {
    private Silla silla;
    private Sofa sofa;

    public Cliente(MueblesFactory factory) {
        silla = factory.crearSilla();
        sofa = factory.crearSofa();
    }

    public void usarMuebles() {
        silla.sentarse();
        sofa.acostarse();
    }

    public static void main(String[] args) {
        MueblesFactory factoryModernos = new MueblesModernosFactory();
        Cliente clienteModernos = new Cliente(factoryModernos);
        clienteModernos.usarMuebles();

        MueblesFactory factoryVictorianos = new MueblesVictorianosFactory();
        Cliente clienteVictorianos = new Cliente(factoryVictorianos);
        clienteVictorianos.usarMuebles();
    }
}
```

### 11.6 Salida esperada

```
Sentado en una silla moderna
Acostado en un sofá moderno
Sentado en una silla victoriana
Acostado en un sofá victoriano
```

---

## 12. Patrones creacionales — Builder

### 12.1 Definición / Motivación

> Patrón es útil cuando quieres construir un objeto
> complejo paso a paso y quieres evitar constructores
> telescópicos (constructores con muchos
> parámetros). Supongamos que queremos construir
> un objeto `Coche` que tiene varios atributos como
> `marca`, `modelo`, `año`, `color`, etc.

### 12.2 Paso 1: Crear la clase `Coche` con su `Builder`

```java
public class Coche {
    // Atributos del coche
    private String marca;
    private String modelo;
    private int año;
    private String color;
    private boolean tieneAireAcondicionado;
    private boolean tieneGPS;

    // Constructor privado
    private Coche(CocheBuilder builder) {
        this.marca = builder.marca;
        this.modelo = builder.modelo;
        this.año = builder.año;
        this.color = builder.color;
        this.tieneAireAcondicionado = builder.tieneAireAcondicionado;
        this.tieneGPS = builder.tieneGPS;
    }
```

### 12.3 Clase estática Builder (continuación)

```java
    // Clase estática Builder
    public static class CocheBuilder {
        private String marca;
        private String modelo;
        private int año;
        private String color;
        private boolean tieneAireAcondicionado;
        private boolean tieneGPS;

        // Métodos setter que retornan el propio Builder
        public CocheBuilder setMarca(String marca) {
            this.marca = marca;
            return this;
        }

        public CocheBuilder setModelo(String modelo) {
            this.modelo = modelo;
            return this;
        }

        public CocheBuilder setAño(int año) {
            this.año = año;
            return this;
        }

        public CocheBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public CocheBuilder setTieneAireAcondicionado(boolean tieneAireAcondicionado) {
            this.tieneAireAcondicionado = tieneAireAcondicionado;
            return this;
        }

        public CocheBuilder setTieneGPS(boolean tieneGPS) {
            this.tieneGPS = tieneGPS;
            return this;
        }
```

### 12.4 Método `build()` y `toString()` (continuación)

```java
        // Método build que retorna el objeto final Coche
        public Coche build() {
            return new Coche(this);
        }
    }

    @Override
    public String toString() {
        return "Coche [marca=" + marca + ", modelo=" + modelo + ", año=" + año + ", color=" + color
            + ", tieneAireAcondicionado=" + tieneAireAcondicionado + ", tieneGPS=" + tieneGPS + "]";
    }
}
```

### 12.5 Paso 2: Usar el Builder para crear un objeto `Coche`

```java
public class Cliente {
    public static void main(String[] args) {
        Coche coche = new Coche.CocheBuilder()
                .setMarca("Toyota")
                .setModelo("Corolla")
                .setAño(2021)
                .setColor("Rojo")
                .setTieneAireAcondicionado(true)
                .setTieneGPS(true)
                .build();

        System.out.println(coche);
    }
}
```

### 12.6 Salida esperada

```
Coche [marca=Toyota, modelo=Corolla, año=2021, color=Rojo, tieneAireAcondicionado=true, tieneGPS=true]
```

> Este ejemplo muestra cómo el patrón Builder se utiliza para
> construir un objeto `Coche` de manera clara y concisa,
> evitando constructores con muchos parámetros y mejorando
> la legibilidad del código.

---

## 13. ¿Cuál es la diferencia entre Factory y Builder?

[FIGURA: dos íconos de burbujas de diálogo (una azul con signo de interrogación y otra verde con puntos suspensivos) sobre fondo decorativo con puntos grises.]

**¿Cuál es la diferencia entre Factory y Builder?**

---

## 14. Caso práctico

### 14.1 Sección "Caso práctico"

[FIGURA: banner rojo grande con el texto "Caso práctico" centrado.]

### 14.2 Ejercicio Práctico — Plataforma de Pago en Línea

Realizar ejercicio práctico es una excelente manera de aprender y entender cómo elaborar un patrón de diseño creacional. Aquí tienes un ejercicio práctico detallado que puedes seguir:

**Ejercicio Práctico:**

#### Caso de Uso: Plataforma de Pago en Línea

> Una plataforma de pago en línea necesita procesar diferentes métodos de pago, como tarjetas de crédito, PayPal y criptomonedas. Cada método de pago requiere una lógica específica para su procesamiento. Se utiliza el patrón Factory para encapsular la creación de diferentes tipos de procesadores de pago. La plataforma decide cuál procesador de pago utilizar en función del método de pago seleccionado por el usuario

#### Objetivos:

1. Elaborar el código java utilizando el patrón seleccionado.

### 14.3 Sesión 13: Introd. Cassandra — ¿Quién quisiera participar? (2 voluntarios)

[NOTA: El título del encabezado en esta diapositiva indica **"Sesión 13: Introd. Cassandra"**, que parece ser un encabezado residual de otra sesión y no corresponde al tema principal (Sesión 07).]

¿Quién quisiera participar? (2 voluntarios)

[FIGURA: ilustración de tres manos levantadas con tonos de piel variados.]

### 14.4 Sesión 13: Introd. Cassandra — Preguntas de reflexión

[NOTA: Encabezado **"Sesión 13: Introd. Cassandra"** repetido — encabezado residual.]

¿Qué se les hizo más fácil?
¿Qué se les hizo más retador?

[FIGURA: dos íconos de burbujas de diálogo (azul con signo de interrogación y verde con puntos suspensivos).]

---

## 15. Aprendizajes

**¿Qué hemos aprendido el día de hoy?**

---

## 16. Conclusiones

### 16.1 Reflexión inicial

> Tomar apuntes de manera eficaz ayuda
> a consolidar el aprendizaje y prepararse
> para los exámenes.

### 16.2 Propósito y Uso

**1. Propósito y Uso**

- **Factory**: El patrón Factory Method se utiliza principalmente para crear objetos de una jerarquía de clases relacionados mediante una interfaz común, ocultando el tipo específico de objeto que se crea. Es ideal cuando el proceso de creación de objetos puede variar y se desea encapsular esta lógica en una clase separada.
  - **Ejemplo**: Crear diferentes tipos de notificaciones (correo electrónico, SMS) en función de una entrada del usuario.

- **Builder**: El patrón Builder se utiliza para construir objetos complejos paso a paso. Es útil cuando un objeto tiene muchas opciones de configuración y es necesario un enfoque más flexible y controlado para la creación de instancias.
  - **Ejemplo**: Construir un objeto `Coche` con múltiples atributos opcionales como `marca`, `modelo`, `año`, `color`, etc.

### 16.3 Flexibilidad en la Creación

**2. Flexibilidad en la Creación**

- **Factory**: La flexibilidad se centra en qué tipo de objeto se crea, permitiendo la creación de una familia de objetos relacionados sin especificar la clase concreta. La lógica de creación de objetos puede estar centralizada y modificarse fácilmente.
  - **Ventaja**: Facilita la creación de nuevos tipos de objetos sin modificar el código cliente.

- **Builder**: La flexibilidad se centra en cómo se crea el objeto, permitiendo una construcción detallada y paso a paso. Se puede personalizar cada paso del proceso de construcción sin necesidad de múltiples constructores sobrecargados.
  - **Ventaja**: Facilita la creación de objetos complejos con múltiples configuraciones opcionales, mejorando la legibilidad y mantenibilidad del código.

### 16.4 Complejidad y Legibilidad del Código

**3. Complejidad y Legibilidad del Código**

- **Factory**: Simplifica la creación de objetos al encapsular la lógica de creación en una fábrica, pero puede introducir complejidad si hay demasiados tipos de productos o si la lógica de creación es muy variada.
  - **Desventaja**: Puede resultar en una jerarquía de clases más compleja con múltiples fábricas si no se gestiona adecuadamente.

- **Builder**: Simplifica la construcción de objetos complejos proporcionando una interfaz clara y fluida para configurar las opciones del objeto. Esto mejora la legibilidad y la mantenibilidad del código, especialmente cuando el objeto tiene muchos atributos opcionales.
  - **Desventaja**: Puede ser excesivo para objetos simples donde un patrón Factory o incluso un simple constructor serían suficientes.

---

## 17. Tarea

- Elabora la actividad práctica de acuerdo a la guía de laboratorio de sesión
- Sube la actividad práctica en la plataforma virtual de aprendizaje
- Guarda la actividad con la siguiente etiqueta:
  ***DPA_Actividad07_NombreApellido***

> *Nota: No olvides también revisar tu plataforma UTP+Class*

---

## 18. Recursos

- Abstract Factory VS Factory / Concepto básico (2023). Coding Together ES.
  `https://youtu.be/izFGKo7Z1lg?si=bWORsurulo27k2MR`

- bin Uzayr, S. (2023). *Software Design Patterns: The Ultimate Guide*. CRC Press.

> *Nota: No olvides también revisar tu plataforma UTP+Class*

---

## 19. Cierre

### 19.1 Mensaje final

[FIGURA: ilustración de un hombre con corbata y camisa blanca, sonriente, con dedo índice levantado, junto a un ícono de bombilla amarilla con destellos amarillos (representando una idea).]

# MUCHAS GRACIAS QUE DIOS LOS BENDIGA!!!

### 19.2 Contraportada

[FIGURA CONTRAPORTADA: logo institucional UTP — Universidad Tecnológica del Perú, centrado sobre fondo blanco con patrón de puntos grises y línea decorativa roja/negra en la parte inferior.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                 |
| ---------- | -------- | ----------------------------------------------------------------------------- |
| Figuras    | 9        | Portada, íconos decorativos, ilustraciones de manos, burbujas de diálogo, contraportada |
| Tablas     | 1        | Tabla "Unidad de aprendizaje 2 / Semana 5,6 y 7" del Logro de la sesión       |
| Fórmulas   | 0        | No contiene fórmulas matemáticas                                              |
| Código     | 14       | Bloques Java: interfaces, clases concretas, fábricas, Builder y main          |
| Diagramas  | 1        | Diagrama UML Factory Method con tres subclases (Two/Three/Fourwheeler)        |
| Ejercicios | 2        | Repaso (completar oraciones Singleton/Prototype) + Caso práctico (Pasarela de pagos) |

## Referencias

- Coding Together ES. (2023). *Abstract Factory VS Factory / Concepto básico*. Video de YouTube. `https://youtu.be/izFGKo7Z1lg?si=bWORsurulo27k2MR`
- bin Uzayr, S. (2023). *Software Design Patterns: The Ultimate Guide*. CRC Press.
