---
universidad: UTP
curso: Diseño de Patrones
tema: Patrones Estructurales — Introducción, Patrón Adapter, Patrón Facade
semana: 8
sesion: 08
unidad: Unidad de Aprendizaje 3 — Patrones estructurales
tipo_documento: Diapositivas (PowerPoint exportado a PDF)
paginas: 34
fuente_pdf: S08_s1-Patrones-Estructurales-AF.pdf
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
- [7. Patrones estructurales — introducción](#7-patrones-estructurales--introducción)
- [8. Patrones estructurales — Adapter](#8-patrones-estructurales--adapter)
- [9. Patrón Adapter — Ejemplo (e-commerce / inventario)](#9-patrón-adapter--ejemplo-e-commerce--inventario)
- [10. Patrones estructural — Facade](#10-patrones-estructural--facade)
- [11. Patrón Facade — Ejemplo (lavadora)](#11-patrón-facade--ejemplo-lavadora)
- [12. ¿Cuál es la diferencia entre Facade y Adapter?](#12-cuál-es-la-diferencia-entre-facade-y-adapter)
- [13. Caso práctico](#13-caso-práctico)
- [14. Aprendizajes](#14-aprendizajes)
- [15. Conclusiones](#15-conclusiones)
- [16. Tarea](#16-tarea)
- [17. Recursos](#17-recursos)
- [18. Cierre](#18-cierre)

---

## 1. Portada

[FIGURA PORTADA: logo UTP (Universidad Tecnológica del Perú) en la parte superior central. Recuadro rojo destacando el título de la sesión. Fondo con patrón de puntos grises y línea decorativa roja/negra en la parte inferior.]

**PRIMERA UNIDAD DE APRENDIZAJE 3: Patrones estructurales**

# CURSO: DISEÑO DE PATRONES

> **SESIÓN N° 08:**
>
> Patrones Estructurales:
> Introducción a patrones estructurales.
> Patrón Adapter. Patrón Facade.

**DOCENTES DE UTP**
**SISTEMAS**

---

## 2. Motivación para el aprendizaje

### 2.1 Lo que debemos tener en cuenta

[FIGURA: ícono de bombilla amarilla junto al título "Motivación para el aprendizaje". En la esquina inferior derecha, ícono de reloj con texto **"5 minutos"**.]

**Lo que debemos tener en cuenta**

### 2.2 ¿QUÉ SON PATRONES Adapter y Facade?

**¿QUÉ SON PATRONES Adapter y Facade?**

**Facade Pattern:**

- **Facade**: Proporciona una interfaz simplificada para un conjunto de interfaces en un subsistema. El Facade delega las llamadas de los clientes a los objetos del subsistema.
- **SubsystemClass1, SubsystemClass2, SubsystemClass3**: Estas son clases que representan diferentes partes del subsistema. Cada una tiene operaciones específicas que el Facade puede utilizar.

**Adapter Pattern:**

- **Adapter**: Adapta la interfaz de una clase (Adaptee) a otra interfaz esperada por el cliente (Target).
- **Target**: Define la interfaz específica que el cliente usa.
- **Adaptee**: Tiene una interfaz incompatible con la esperada por el cliente.
- **Client**: Interactúa con el sistema a través de la interfaz Target y no necesita conocer la existencia del Adaptee.

---

## 3. Recordando los aprendizajes

### 3.1 ¿Qué vimos en la sesión anterior?

**¿Qué vimos en la sesión anterior?**

### 3.2 Ejercicio de completar (repaso Factory)

En el Patrón de diseño de clase Factory, el método principal que crea objetos se llama
______. El Patrón Factory promueve el principio de diseño ______, que sugiere que
se debe depender de abstracciones y no de concreciones. Una ventaja del Patrón
Factory es que facilita la creación de ______ sin especificar la clase exacta del
objeto que se creará. El Patrón Factory ayuda a reducir el ______ del código al
centralizar la lógica de creación de objetos. En el Patrón Factory, las subclases
pueden alterar el tipo de objetos que serán creados mediante la sobreescritura del
método ______.

[FIGURA: interruptor con etiqueta "Show answers" (desactivado).]

**Palabras disponibles** (banco de respuestas):

- `objetos`
- `factoryMethod`
- `acoplamiento`
- `factoryMethod`
- `SOLID`

---

## 4. Logro de la sesión

| Unidad de aprendizaje 3:         | Semana 8, 9, 10 y 11 |
| -------------------------------- | -------------------- |
| Patrones estructurales.          |                      |

**Logro específico de aprendizaje:**
Al finalizar la unidad el participante aplica los patrones estructurales para la solución de problemas.

**Temario:**

- Patrones Estructurales: Introducción a patrones estructurales. Patrón Adapter. Patrón Facade.
- Patrones Estructurales: Patrón Decorator. Patrón Composite.
- Consolidación de temas (Repaso).
- Patrones Estructurales: Patrón Proxy. Patrón Bridge.

> Al final de la sesión el estudiante aplica patrones estructurales:
> Patrón Facade. Patrón Adapter en un contexto definido

---

## 5. Importancia de la sesión

> Aprender patrones estructurales es vital porque ayudan a
> simplificar sistemas complejos, mejoran la organización del código
> y facilitan la reutilización de componentes. Estos patrones
> promueven el desacoplamiento, haciendo el software más flexible
> y fácil de mantener. Además, proporcionan soluciones probadas
> para problemas comunes de diseño, lo que ahorra tiempo y reduce
> errores. Entender estos patrones también mejora la capacidad
> para comunicar diseños y colaboraciones efectivamente con otros
> desarrolladores. En resumen, dominarlos contribuye a la creación
> de software robusto y escalable.

---

## 6. Temario

**¡Que no se te escape nada de tu clase!:**

### SESIÓN N° 08:

> **Patrones Estructurales:**
> **Introducción a patrones estructurales.**
> **Patrón Adapter. Patrón Facade**

---

## 7. Patrones estructurales — introducción

Los patrones estructurales sirven para resolver problemas de diseño estableciendo relaciones estructurales entre las entidades de maneras que suelen sugerir los nombres metafóricos de los patrones.

Estas relaciones estructurales hacen que los objetos sean más ricos en términos de comportamiento, más poderosos o más fáciles de usar debido a la colaboración entre objetos.

La agregación de objetos es el principal mecanismo relacional utilizado para todos los patrones estructurales, aunque una relación contextual puede caracterizarse de manera más restringida como composición, adaptación, delegación, etc. para reflejar la forma en que los objetos colaboran para brindar una solución a un problema particular donde se utiliza un patrón.

---

## 8. Patrones estructurales — Adapter

### 8.1 Definición y motivación

El nombre del patrón parece acertado en lo que respecta a su función: resolver problemas de "desajuste".

Un desajuste puede ser tan pequeño como un conjunto de parámetros que no coinciden al pasarse a un módulo de destino o tan grande como las comunicaciones entre sistemas con protocolos de comunicación, entornos operativos o transferencias de información que no coinciden.

Los sistemas de software operan cada vez más en la nube, pero aún pueden necesitar depender de los sistemas existentes para obtener funcionalidades. Por lo tanto, los desajustes de todo tipo deben abordarse en cualquier esfuerzo por migrar operaciones de software a un entorno de nube.

### 8.2 Estructura UML del patrón Adapter

Target es una abstracción en la que la solicitud es el método que el cliente debe utilizar. Adapter implementa Target y utiliza una instancia de Adaptee para invocar, con la adaptación adecuada, una operación que cumpla con la solicitud.

[DIAGRAMA UML — Patrón Adapter:

- **Client** (clase con círculo de actor): tiene relación "uses" hacia **Target**.
- **Target** (`<<Interface>>`): expone método `+request()`.
- **Adapter** (clase): atributos `-adaptee`, método `+request()`. Implementa Target (flecha de herencia hacia arriba) y "depends on" **Adaptee**.
- **Adaptee** (clase): expone método `+fulfillRequest()`.
- Nota lateral rosa adjunta al Adapter: `adaptee.fulfillRequest()` — indica la llamada que el Adapter realiza internamente.]

---

## 9. Patrón Adapter — Ejemplo (e-commerce / inventario)

### 9.1 Planteamiento

Una empresa de comercio electrónico utiliza un sistema antiguo de gestión de inventarios que no es compatible con su nueva aplicación de ventas en línea.

La nueva aplicación requiere datos de inventario en un formato diferente al que proporciona el sistema antiguo.

Para resolver este problema sin modificar el sistema existente, se implementa el patrón Adapter.

### 9.2 Diagrama UML del ejemplo

[DIAGRAMA UML:

- **Client** (clase): atributo `setting: Setting()`, métodos `getattr()`, `setattr()` — flecha "target" hacia Target.
- **Target** (clase): métodos `+request()`, `+Sender()`.
- **Adapter** (clase): métodos `+request()`, `+Sender()` — flecha de herencia hacia Target; flecha "adaptee" hacia Adapter (Adaptee).
- **Adapter (Adaptee)** (clase): método `+Specific request`.
- Nota inferior: `AdapteeSpecificRequest()`.]

### 9.3 Paso 1: Interfaz existente

```java
// Interfaz existente en la nueva aplicación de ventas en línea
interface InventoryService {
    String getInventoryItem(String itemId);
}
```

### 9.4 Paso 2: Clase Adaptee del sistema antiguo

```java
// Clase Adaptee del sistema antiguo de gestión de inventarios
class OldInventorySystem {
    public String getItemDetails(String itemId) {
        // Devuelve detalles del artículo en un formato diferente
        return "Item ID: " + itemId + ", Quantity: 100, Location: Warehouse A";
    }
}
```

### 9.5 Paso 3: Clase Adapter

```java
// Clase Adapter que adapta la interfaz InventoryService a OldInventorySystem
class InventoryAdapter implements InventoryService {
    private OldInventorySystem oldInventorySystem;

    public InventoryAdapter(OldInventorySystem oldInventorySystem) {
        this.oldInventorySystem = oldInventorySystem;
    }

    @Override
    public String getInventoryItem(String itemId) {
        // Convierte el formato de salida del sistema antiguo al formato requerido
        String oldDetails = oldInventorySystem.getItemDetails(itemId);
        // Realiza las conversiones necesarias (esto es solo un ejemplo simple)
        String[] details = oldDetails.split(", ");
        return "ID: " + details[0].split(": ")[1] + ", Stock: " + details[1].split(": ")[1];
    }
}
```

### 9.6 Paso 4: Cliente

```java
// Cliente que utiliza la interfaz InventoryService
public class AdapterPatternBusinessDemo {
    public static void main(String[] args) {
        InventoryService inventoryService = new InventoryAdapter(new OldInventorySystem());
        String itemDetails = inventoryService.getInventoryItem("12345");
        System.out.println(itemDetails);
    }
}
```

### 9.7 Explicación de los componentes

- **InventoryService**: La interfaz que la nueva aplicación de ventas en línea utiliza para obtener información de inventario.
- **OldInventorySystem**: La clase del sistema antiguo de gestión de inventarios que proporciona detalles en un formato diferente.
- **InventoryAdapter**: La clase adaptadora que implementa la interfaz **InventoryService** y traduce las llamadas al **OldInventorySystem**.
- **AdapterPatternBusinessDemo**: Clase cliente que utiliza la interfaz **InventoryService** para obtener detalles de inventario en el formato requerido por la nueva aplicación de ventas en línea.

---

## 10. Patrones estructural — Facade

### 10.1 Definición

El patrón de fachada es un patrón de diseño estructural que crea una interfaz más unificada para un sistema más complejo. El término fachada se refiere a la cara de un edificio o, más específicamente, a la interfaz exterior de un sistema complejo compuesto por varios subsistemas. Es una parte importante de los patrones de diseño de la Banda de los Cuatro. Simplifica el acceso a los métodos de los sistemas subyacentes al proporcionar un único punto de entrada.

### 10.2 Diagrama estructural del patrón Facade

[DIAGRAMA — Patrón Facade:

- Tres rectángulos en la parte superior: **SubSystem1**, **SubSystem2**, **SubSystem3** — todos con flechas convergiendo hacia un óvalo central etiquetado **Facade**.
- Desde el óvalo **Facade**, líneas descienden hacia múltiples íconos de "actor" (6 íconos en forma de figura humana esquemática con cabeza ovalada negra).]

---

## 11. Patrón Facade — Ejemplo (lavadora)

### 11.1 Caso de uso (comerciante e inventario)

Se utiliza un sistema de gestión de inventario para organizar los artículos. Sin embargo, como el cliente no necesita saber nada sobre el inventario, es preferible que le pida al comerciante una lista de artículos, ya que el comerciante sabe dónde se encuentra cada artículo. El comerciante actúa como interfaz de fachada en este caso.

[DIAGRAMA — Patrón Facade (repetido): SubSystem1, SubSystem2, SubSystem3 convergen hacia Facade, y Facade se conecta con varios actores.]

### 11.2 Caso de uso (lavadora)

Supongamos que tenemos una lavadora que puede lavar, enjuagar y centrifugar la ropa, pero que realiza cada tarea por separado. Debemos abstraer las complejidades de los subsistemas porque el sistema en su conjunto es bastante complejo. Necesitamos un sistema que pueda automatizar toda la tarea sin nuestra interferencia.

[DIAGRAMA UML — Lavadora:

- **Client** (clase) — flecha hacia **WashingMachine**.
- **WashingMachine** (clase): métodos `+ Startwashing()`, `+method1()`.
- Tres subclases (con flechas de herencia hacia WashingMachine):
  - **Washing** — método `+ wash()`, `+methods-processing()`
  - **Rinsing** — método `+ rinse()`, `+methods-processing()`
  - **Spinning** — método `+ spin()`, `+methods-processing()`]

### 11.3 Implementación en Java — Subsistemas

```java
// Subsystem Class: Lavar
class Lavado {
    public void lavar() {
        System.out.println("Lavando la ropa...");
    }
}

// Subsystem Class: Enjuagar
class Enjuague {
    public void enjuagar() {
        System.out.println("Enjuagando la ropa...");
    }
}

// Subsystem Class: Centrifugar
class Centrifugado {
    public void centrifugar() {
        System.out.println("Centrifugando la ropa...");
    }
}
```

### 11.4 Implementación en Java — Clase Facade

```java
// Facade Class
class LavadoraFacade {
    private Lavado lavado;
    private Enjuague enjuague;
    private Centrifugado centrifugado;

    public LavadoraFacade() {
        this.lavado = new Lavado();
        this.enjuague = new Enjuague();
        this.centrifugado = new Centrifugado();
    }

    public void lavarRopa() {
        lavado.lavar();
    }
    public void enjuagarRopa() {
        enjuague.enjuagar();
    }
    public void centrifugarRopa() {
        centrifugado.centrifugar();
    }
}
```

### 11.5 Implementación en Java — Cliente

```java
// Cliente
public class FacadePatternDemo {
    public static void main(String[] args) {
        LavadoraFacade lavadora = new LavadoraFacade();
        lavadora.lavarRopa();
        lavadora.enjuagarRopa();
        lavadora.centrifugarRopa();
    }
}
```

---

## 12. ¿Cuál es la diferencia entre Facade y Adapter?

[FIGURA: dos íconos de burbujas de diálogo (una azul con signo de interrogación y otra verde con puntos suspensivos) sobre fondo decorativo con puntos grises.]

**¿Cuál es la diferencia entre Facade y Adapter?**

---

## 13. Caso práctico

### 13.1 Sección "Caso práctico"

[FIGURA: banner rojo grande con el texto "Caso práctico" centrado.]

### 13.2 Ejercicio Práctico — Sistemas hospitalarios

Realizar ejercicio práctico es una excelente manera de aprender y entender cómo elaborar un patrón de diseño estructural. Aquí tienes un ejercicio práctico detallado que puedes seguir:

**Ejercicio Práctico:**

#### Caso de Uso: Plataforma de Pago en Línea

> Un hospital necesita integrar múltiples sistemas para gestionar la información del paciente, como registros médicos electrónicos (EMR), sistemas de laboratorio y sistemas de facturación.
>
> **Aplicación del Patrón Facade**: Se implementa una fachada que proporciona una interfaz unificada para acceder a estos sistemas dispares. La fachada ofrece métodos simplificados para obtener la historia clínica completa de un paciente, resultados de laboratorio y estado de facturación sin que los usuarios necesiten interactuar directamente con cada sistema.

[NOTA: El título reza "Caso de Uso: Plataforma de Pago en Línea" pero el contenido del enunciado describe un hospital con sistemas EMR, laboratorio y facturación. Se preserva el texto tal cual aparece en el original.]

#### Objetivos:

1. Elaborar el código java y diagrama de clase UML utilizando el patrón seleccionado.

### 13.3 Sesión 13: Introd. Cassandra — ¿Quién quisiera participar? (2 voluntarios)

[NOTA: El encabezado de esta diapositiva indica **"Sesión 13: Introd. Cassandra"**, que parece ser un encabezado residual de otra sesión y no corresponde al tema principal (Sesión 08).]

¿Quién quisiera participar? (2 voluntarios)

[FIGURA: ilustración de tres manos levantadas con tonos de piel variados.]

### 13.4 Sesión 13: Introd. Cassandra — Preguntas de reflexión

[NOTA: Encabezado **"Sesión 13: Introd. Cassandra"** repetido — encabezado residual.]

¿Qué se les hizo más fácil?
¿Qué se les hizo más retador?

[FIGURA: dos íconos de burbujas de diálogo (azul con signo de interrogación y verde con puntos suspensivos).]

---

## 14. Aprendizajes

**¿Qué hemos aprendido el día de hoy?**

---

## 15. Conclusiones

### 15.1 Reflexión inicial

> Tomar apuntes de manera eficaz ayuda
> a consolidar el aprendizaje y prepararse
> para los exámenes.

### 15.2 Síntesis Facade vs Adapter

**Facade**: Proporciona una interfaz simplificada para un conjunto de interfaces en un subsistema, ocultando la complejidad y ofreciendo una única entrada para interactuar con múltiples componentes.

**Adapter**: Permite que una interfaz incompatible sea utilizada por otra. Actúa como un puente entre dos interfaces, adaptando la interfaz de un objeto a la que el cliente espera.

---

## 16. Tarea

- Elabora la actividad práctica de acuerdo a la guía de laboratorio de sesión
- Sube la actividad práctica en la plataforma virtual de aprendizaje
- Guarda la actividad con la siguiente etiqueta:
  ***DPA_Actividad08_NombreApellido***

> *Nota: No olvides también revisar tu plataforma UTP+Class*

---

## 17. Recursos

- Hu, C. (2023). ***An Introduction to Software Design: Concepts, Principles, Methodologies, and Techniques***. Springer Nature.

- bin Uzayr, S. (2023). *Software Design Patterns: The Ultimate Guide*. CRC Press.

> *Nota: No olvides también revisar tu plataforma UTP+Class*

---

## 18. Cierre

### 18.1 Mensaje final

[FIGURA: ilustración de un hombre con corbata y camisa blanca, sonriente, con dedo índice levantado, junto a un ícono de bombilla amarilla con destellos amarillos (representando una idea).]

# MUCHAS GRACIAS QUE DIOS LOS BENDIGA!!!

### 18.2 Contraportada

[FIGURA CONTRAPORTADA: logo institucional UTP — Universidad Tecnológica del Perú, centrado sobre fondo blanco con patrón de puntos grises y línea decorativa roja/negra en la parte inferior.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                       |
| ---------- | -------- | ----------------------------------------------------------------------------------- |
| Figuras    | 8        | Portada, íconos decorativos, ilustración de manos, burbujas de diálogo, contraportada |
| Tablas     | 1        | Tabla "Unidad de aprendizaje 3 / Semana 8,9,10 y 11" del Logro de la sesión          |
| Fórmulas   | 0        | No contiene fórmulas matemáticas                                                    |
| Código     | 7        | Bloques Java: interfaces, Adaptee, Adapter, cliente, subsistemas, Facade            |
| Diagramas  | 5        | UML Adapter (estructura genérica + ejemplo) + estructura Facade (×2) + UML lavadora |
| Ejercicios | 2        | Repaso (completar Factory) + Caso práctico (sistemas hospitalarios)                 |

## Referencias

- Hu, C. (2023). *An Introduction to Software Design: Concepts, Principles, Methodologies, and Techniques*. Springer Nature.
- bin Uzayr, S. (2023). *Software Design Patterns: The Ultimate Guide*. CRC Press.
