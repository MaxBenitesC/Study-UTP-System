---
universidad: UTP
curso: Diseño de Patrones
tema: Patrones Estructurales — Patrón Decorator, Patrón Composite
semana: 9
sesion: 09
unidad: Unidad de Aprendizaje 3 — Patrones estructurales
tipo_documento: Diapositivas (PowerPoint exportado a PDF)
paginas: 31
fuente_pdf: S09_s1-Patrones-Estructurales-DC.pdf
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
- [7. Patrones estructurales — Decorator](#7-patrones-estructurales--decorator)
- [8. Patrón Decorator — Ejemplo (Transporte)](#8-patrón-decorator--ejemplo-transporte)
- [9. Patrones estructurales — Composite](#9-patrones-estructurales--composite)
- [10. Patrón Composite — Ejemplo (Gestión de pacientes)](#10-patrón-composite--ejemplo-gestión-de-pacientes)
- [11. ¿Cuál es la diferencia entre Decorator y Composite?](#11-cuál-es-la-diferencia-entre-decorator-y-composite)
- [12. Caso práctico](#12-caso-práctico)
- [13. Aprendizajes](#13-aprendizajes)
- [14. Conclusiones](#14-conclusiones)
- [15. Tarea](#15-tarea)
- [16. Recursos](#16-recursos)
- [17. Cierre](#17-cierre)

---

## 1. Portada

[FIGURA PORTADA: logo UTP (Universidad Tecnológica del Perú) en la parte superior central. Recuadro rojo destacando el título de la sesión. Fondo con patrón de puntos grises y línea decorativa roja/negra en la parte inferior.]

**PRIMERA UNIDAD DE APRENDIZAJE 3: Patrones estructurales**

# CURSO: DISEÑO DE PATRONES

> **SESIÓN N° 09:**
>
> Patrones Estructurales:
> Patrón Decorator. Patrón Composite

**DOCENTES DE UTP**
**SISTEMAS**

---

## 2. Motivación para el aprendizaje

### 2.1 Lo que debemos tener en cuenta

[FIGURA: ícono de bombilla amarilla junto al título "Motivación para el aprendizaje". En la esquina inferior derecha, ícono de reloj con texto **"5 minutos"**.]

**Lo que debemos tener en cuenta**

### 2.2 ¿QUÉ SON PATRONES decorator y composite?

**¿QUÉ SON PATRONES decorator y composite?**

**Decorator**: Permite agregar funcionalidades adicionales a un objeto de manera dinámica sin modificar su estructura. Es útil para extender las capacidades de un objeto de forma flexible.

**Composite**: Permite tratar de manera uniforme objetos individuales y composiciones de objetos. Es útil para representar jerarquías de objetos, donde tanto los objetos simples como las composiciones pueden ser tratados de la misma manera.

---

## 3. Recordando los aprendizajes

### 3.1 ¿Qué vimos en la sesión anterior?

**¿Qué vimos en la sesión anterior?**

### 3.2 Ejercicio de emparejamiento (drag & drop)

**5. Relaciona cada patrón de diseño con su descripción correspondiente.**

*Move the answers to the correct boxes*

**Descripciones disponibles:**

- Permite que dos interfaces incompatibles trabajen juntas convirtiendo la interfaz de una clase en otra que el cliente espera.
- Proporciona una interfaz simplificada para un conjunto de interfaces en un subsistema, facilitando su uso.
- Se utiliza cuando se necesita una interfaz simple para un subsistema complejo o para reducir el acoplamiento entre los clientes y las clases del subsistema.
- Se emplea cuando se necesita utilizar una clase existente pero su interfaz no es compatible con el sistema actual.

**Categorías de destino:**

| Uso del Adapter | Patrón Adapter | Patrón Facade | Uso del Facade |
| --------------- | -------------- | ------------- | -------------- |
| *Drag and drop an answer here* | *Drag and drop an answer here* | *Drag and drop an answer here* | *Drag and drop an answer here* |

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
> Patrón Decorator. Patrón Composite en un contexto definido

---

## 5. Importancia de la sesión

**Decorator**: Es importante porque permite añadir o modificar el comportamiento de un objeto en tiempo de ejecución sin cambiar su código original. Esto proporciona flexibilidad para extender funcionalidades de manera modular y reutilizable.

**Composite**: Es importante porque facilita el manejo y la manipulación de estructuras jerárquicas de objetos (como árboles) de manera uniforme. Permite tratar objetos individuales y grupos de objetos de manera coherente, simplificando la gestión de estructuras complejas.

---

## 6. Temario

**¡Que no se te escape nada de tu clase!:**

### SESIÓN N° 09:

> **Patrones Estructurales:**
> **Patrón Decorator. Patrón Composite**

---

## 7. Patrones estructurales — Decorator

### 7.1 Definición y motivación

Este patrón proporciona una forma de manejar tareas de manera jerárquica comenzando con operaciones más rudimentarias.

Es un diagrama de clases que describe todos los elementos relevantes y sus relaciones. BasicComponent y Decorator son ambos tipos derivados de Component, y el último agrega una instancia del primero. La operación del método en Decorator está "decorada" con la de BasicComponent.

En otras palabras, un decorador es una forma de basar una operación en operaciones de otro componente.

Decorator es estructuralmente como Proxy, pero los dos patrones se basan en intenciones de diseño muy diferentes. Para Decorator, el interés del cliente está en el objeto que se agrega, mientras que para Proxy, el objetivo del cliente es el objeto agregado.

### 7.2 Diagrama UML del patrón Decorator

[DIAGRAMA UML — Patrón Decorator:

- **Client** (clase con círculo de actor): relación "uses" hacia Component.
- **Component** (`<<interface/abstract class>>`): expone método `+operation()`.
- **BasicComponent** (clase): método `+operation ()` — flecha de herencia (línea discontinua) hacia Component.
- **Decorator** (clase): atributo `- basicComp : Component`, método `+operation ()` — flecha de herencia (línea discontinua) hacia Component; relación de agregación (rombo blanco) con Component.
- Nota adjunta al Decorator: bloque de código `operation(){ basicComp.operation(); //decoration code }`.]

---

## 8. Patrón Decorator — Ejemplo (Transporte)

### 8.1 Caso de Estudio: Transporte

Supongamos que tenemos una interfaz Transport y queremos agregar características adicionales como "Seguro" y "Rastreo" a los transportes.

### 8.2 Interfaz base e implementación concreta

```java
// Interfaz base
public interface Transport {
    String getDescription();
    double cost();
}

// Implementación concreta
public class BasicTransport implements Transport {
    public String getDescription() {
        return "Basic Transport";
    }
    public double cost() {
        return 100.0;
    }
}
```

### 8.3 Decorador abstracto

```java
// Decorador abstracto
public abstract class TransportDecorator implements Transport {
    protected Transport decoratedTransport;
    public TransportDecorator(Transport decoratedTransport) {
        this.decoratedTransport = decoratedTransport;
    }
    public String getDescription() {
        return decoratedTransport.getDescription();
    }
    public double cost() {
        return decoratedTransport.cost();
    }
}
```

### 8.4 Decoradores concretos

```java
// Decorador concreto para seguro
public class InsuranceDecorator extends TransportDecorator {
    public InsuranceDecorator(Transport decoratedTransport) {
        super(decoratedTransport);
    }
    public String getDescription() {
        return decoratedTransport.getDescription() + ", with Insurance";
    }
    public double cost() {
        return decoratedTransport.cost() + 20.0;
    }
}

// Decorador concreto para rastreo
public class TrackingDecorator extends TransportDecorator {
    public TrackingDecorator(Transport decoratedTransport) {
        super(decoratedTransport);
    }
    public String getDescription() {
        return decoratedTransport.getDescription() + ", with Tracking";
    }
    public double cost() {
        return decoratedTransport.cost() + 15.0;
    }
}
```

### 8.5 Cliente

```java
// Clase cliente
public class Client {
    public static void main(String[] args) {
        Transport transport = new BasicTransport();
        System.out.println(transport.getDescription() + " costs " + transport.cost());

        Transport insuredTransport = new InsuranceDecorator(new BasicTransport());
        System.out.println(insuredTransport.getDescription() + " costs " + insuredTransport.cost());

        Transport trackedAndInsuredTransport = new TrackingDecorator(new InsuranceDecorator(new BasicTransport()));
        System.out.println(trackedAndInsuredTransport.getDescription() + " costs " + trackedAndInsuredTransport.cost());
    }
}
```

### 8.6 Explicación de componentes

- **BasicTransport**: Implementa la interfaz Transport con una descripción y un costo base.
- **TransportDecorator**: Clase abstracta para los decoradores, que añade funcionalidades a un transporte existente.
- **InsuranceDecorator** y **TrackingDecorator**: Decoradores concretos que añaden seguro y rastreo, respectivamente.
- **Client**: Muestra cómo se pueden combinar decoradores para enriquecer las funcionalidades de un transporte.

---

## 9. Patrones estructurales — Composite

### 9.1 Definición y motivación

Ciertos elementos de software son jerárquicos, como la simulación de un motor, una tarea de un curso o la estructura de gestión de una organización. Dichos elementos se crean en función de relaciones parte-todo.

Por ejemplo, una tarea de un curso puede constar de varias partes; cada una puede tener su propia división de partes (cada parte es una tarea de pequeña escala).

Por lo tanto, resulta útil utilizar el mismo tipo de objeto para todos los elementos de la misma composición, de modo que el código del cliente pueda tratarlos de manera coherente para ejecutar operaciones que se implementan de forma polimórfica.

Aunque un decorador también tiene una relación parte-todo, ya que un componente básico es parte de un todo decorado, solo ofrece una composición "vertical" simple entre el todo y sus partes.

La figura ilustra el patrón Composite, una generalización del patrón Decorator con una colección agregada de instancias de Component.

### 9.2 Diagrama UML del patrón Composite

[DIAGRAMA UML — Patrón Composite:

- **Client** (clase con círculo de actor): relación "uses" hacia Component.
- **Component** (`<<interface/abstract class>>`): expone método `+operation()`.
- **Leaf** (clase): método `+operation ()` — flecha de herencia (línea discontinua) hacia Component.
- **Composite** (clase): atributo `- comp : List<Component>`, métodos `+add(child)`, `+remove(child)`, `+operation()` — flecha de herencia (línea discontinua) hacia Component; relación de agregación con Component con multiplicidad `0..*`.
- Nota adjunta al Composite: bloque de código `operation(){ for (Component c : comp) { c.operation(); } //additional code }`.]

---

## 10. Patrón Composite — Ejemplo (Gestión de pacientes)

### 10.1 Caso de Estudio: Sistema de Gestión de Pacientes

Supongamos que tenemos un sistema para gestionar pacientes y sus tratamientos, donde tanto los tratamientos individuales como los conjuntos de tratamientos (compositores) deben ser tratados de manera uniforme.

### 10.2 Componente base y tratamiento individual

```java
// Componente base
public interface Treatment {
    String getDescription();
    double cost();
}

// Tratamiento individual
public class SingleTreatment implements Treatment {
    private String description;
    private double cost;

    public SingleTreatment(String description, double cost) {
        this.description = description;
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public double cost() {
        return cost;
    }
}
```

### 10.3 Tratamiento compuesto

```java
// Tratamiento compuesto (para varios tratamientos)
import java.util.ArrayList;
import java.util.List;
public class CompositeTreatment implements Treatment {
    private List<Treatment> treatments = new ArrayList<>();
    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }
    public void removeTreatment(Treatment treatment) {
        treatments.remove(treatment);
    }
    public String getDescription() {
        StringBuilder description = new StringBuilder("Composite Treatment: ");
        for (Treatment treatment : treatments) {
            description.append("\n - ").append(treatment.getDescription());
        }
        return description.toString();
    }
    public double cost() {
        double totalCost = 0;
        for (Treatment treatment : treatments) {
            totalCost += treatment.cost();
        }
        return totalCost;
    }
}
```

### 10.4 Cliente

```java
// Clase cliente
public class Client {
    public static void main(String[] args) {
        Treatment checkUp = new SingleTreatment("Check-up", 50.0);
        Treatment xRay = new SingleTreatment("X-Ray", 100.0);
        Treatment bloodTest = new SingleTreatment("Blood Test", 30.0);

        CompositeTreatment compositeTreatment = new CompositeTreatment();
        compositeTreatment.addTreatment(checkUp);
        compositeTreatment.addTreatment(xRay);
        compositeTreatment.addTreatment(bloodTest);

        System.out.println(compositeTreatment.getDescription() + " costs " + compositeTreatment.cost());
    }
}
```

---

## 11. ¿Cuál es la diferencia entre Decorator y Composite?

[FIGURA: dos íconos de burbujas de diálogo (una azul con signo de interrogación y otra verde con puntos suspensivos) sobre fondo decorativo con puntos grises.]

**¿Cuál es la diferencia entre Decorator y Composite?**

---

## 12. Caso práctico

### 12.1 Sección "Caso práctico"

[FIGURA: banner rojo grande con el texto "Caso práctico" centrado.]

### 12.2 Ejercicio Práctico — Monedero digital con patrón Decorator

Realizar ejercicio práctico es una excelente manera de aprender y entender cómo elaborar un patrón de diseño estructural. Aquí tienes un ejercicio práctico detallado que puedes seguir:

**Ejercicio Práctico:**

#### Caso de estudio: Monedero digital con patrón Decorator

> Se desarrolla un monedero digital que permite agregar funcionalidades adicionales a las transacciones básicas. Utilizando el patrón Decorator, se pueden añadir características como "Notificaciones", "Historial de Transacciones" y "Protección contra Fraudes" de manera flexible, sin modificar el código original del monedero. Cada decorador añade una capa adicional de funcionalidad sobre el monedero base, proporcionando una forma modular y extensible de enriquecer las capacidades del sistema.

#### Objetivos:

1. Elaborar el código java y diagrama de clase UML utilizando el patrón seleccionado.

### 12.3 Sesión 13: Introd. Cassandra — ¿Quién quisiera participar? (2 voluntarios)

[NOTA: El encabezado de esta diapositiva indica **"Sesión 13: Introd. Cassandra"**, que parece ser un encabezado residual de otra sesión y no corresponde al tema principal (Sesión 09).]

¿Quién quisiera participar? (2 voluntarios)

[FIGURA: ilustración de tres manos levantadas con tonos de piel variados.]

### 12.4 Sesión 13: Introd. Cassandra — Preguntas de reflexión

[NOTA: Encabezado **"Sesión 13: Introd. Cassandra"** repetido — encabezado residual.]

¿Qué se les hizo más fácil?
¿Qué se les hizo más retador?

[FIGURA: dos íconos de burbujas de diálogo (azul con signo de interrogación y verde con puntos suspensivos).]

---

## 13. Aprendizajes

**¿Qué hemos aprendido el día de hoy?**

---

## 14. Conclusiones

### 14.1 Reflexión inicial

> Tomar apuntes de manera eficaz ayuda
> a consolidar el aprendizaje y prepararse
> para los exámenes.

### 14.2 Síntesis Decorator vs Composite

- **Decorator**: Permite agregar funcionalidades adicionales a un objeto de manera dinámica y flexible, sin alterar su estructura original. Ideal para extender capacidades de un objeto de forma modular.

- **Composite**: Facilita el manejo de estructuras jerárquicas de objetos, tratando objetos individuales y grupos de objetos de manera uniforme. Ideal para representar y manipular composiciones de objetos en estructuras complejas.

---

## 15. Tarea

- Elabora la actividad práctica de acuerdo a la guía de laboratorio de sesión
- Sube la actividad práctica en la plataforma virtual de aprendizaje
- Guarda la actividad con la siguiente etiqueta:
  ***DPA_Actividad09_NombreApellido***

> *Nota: No olvides también revisar tu plataforma UTP+Class*

---

## 16. Recursos

- Hu, C. (2023). ***An Introduction to Software Design: Concepts, Principles, Methodologies, and Techniques***. Springer Nature.

- bin Uzayr, S. (2023). *Software Design Patterns: The Ultimate Guide*. CRC Press.

> *Nota: No olvides también revisar tu plataforma UTP+Class*

---

## 17. Cierre

### 17.1 Mensaje final

[FIGURA: ilustración de un hombre con corbata y camisa blanca, sonriente, con dedo índice levantado, junto a un ícono de bombilla amarilla con destellos amarillos (representando una idea).]

# MUCHAS GRACIAS QUE DIOS LOS BENDIGA!!!

### 17.2 Contraportada

[FIGURA CONTRAPORTADA: logo institucional UTP — Universidad Tecnológica del Perú, centrado sobre fondo blanco con patrón de puntos grises y línea decorativa roja/negra en la parte inferior.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                          |
| ---------- | -------- | -------------------------------------------------------------------------------------- |
| Figuras    | 8        | Portada, íconos decorativos, ilustraciones de manos, burbujas de diálogo, contraportada |
| Tablas     | 1        | Tabla "Unidad de aprendizaje 3 / Semana 8,9,10 y 11" del Logro de la sesión             |
| Fórmulas   | 0        | No contiene fórmulas matemáticas                                                       |
| Código     | 9        | Bloques Java: Transport (Decorator) + Treatment (Composite) — interfaces, clases, cliente |
| Diagramas  | 2        | UML Decorator (Component, BasicComponent, Decorator) + UML Composite (Component, Leaf, Composite) |
| Ejercicios | 2        | Repaso (drag & drop Adapter/Facade) + Caso práctico (Monedero digital con Decorator)   |

## Referencias

- Hu, C. (2023). *An Introduction to Software Design: Concepts, Principles, Methodologies, and Techniques*. Springer Nature.
- bin Uzayr, S. (2023). *Software Design Patterns: The Ultimate Guide*. CRC Press.
