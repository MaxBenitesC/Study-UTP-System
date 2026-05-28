# Análisis Forense: Ejercicios de Clase (Semana 7)

Este documento contiene el análisis detallado de los proyectos desarrollados en la Semana 7 (extraídos de `Sesion07.rar` y `s07_s1-ParonFactoryyBuilder.zip`). Los patrones creacionales abordados en esta sesión son **Factory Method** y **Builder**.

Al igual que en la semana anterior, desglosaremos la implementación técnica y su equivalente de "Entendimiento Humano".

---

## Parte 1: Patrón Factory Method

El patrón Factory (o Factory Method) delega la lógica de *qué* objeto exacto debe crearse a subclases específicas, mientras que la clase principal (el Creador) usa siempre una interfaz común.

**Código analizado:** Paquete `patron_factory` (`Product.java`, `ConcreteProductA.java`, `Creator.java`, `ConcreteCreatorA.java`, `Principal.java`)

### 1. La Interfaz del Producto (`Product.java` y `ConcreteProductA.java`)
El profesor crea un contrato genérico (`Product`) que tiene una operación. Luego, crea un producto real (`ConcreteProductA`) que firma ese contrato.

```java
public class ConcreteProductA implements Product {
    @Override
    public String operation() {
       return "New concrete ProductA";
    }
}
```

### 2. El Creador Abstracto (`Creator.java`)
Aquí radica la magia de este patrón. Fíjate que el `Creator` es abstracto y tiene un método abstracto `factoryMethod()`.
Pero además, tiene un método ya programado (`someOperation()`) que usa al producto **¡sin saber cuál es su clase exacta!**.

```java
public abstract class Creator {
    
    // Obliga a los hijos a decidir qué producto construir
    public abstract Product factoryMethod(); 
    
    // Usa el producto usando el contrato genérico (Interfaz)
    public String someOperation(){
        Product product = factoryMethod();
        return "Creator: Working with " + product.operation();
    }
}
```

### 3. Las Fábricas Concretas (`ConcreteCreatorA.java`)
Es aquí donde se toma la decisión. La fábrica 'A' decide crear un Producto 'A'.
```java
public class ConcreteCreatorA extends Creator {
    @Override
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}
```

* **Qué quiso enseñar el profesor:** El objetivo es demostrar el **desacoplamiento**. En el `main`, llamas a `creatorA.someOperation()`. El creador sabe *qué hacer* con el producto, pero le cedió la responsabilidad de *crearlo* a su subclase. Esto cumple con el Principio Open/Closed: si mañana quieres un ProductoC, solo creas un `ConcreteCreatorC`, sin tocar el código existente.

---

## Parte 2: Patrón Builder

El patrón Builder nos ayuda a construir objetos complejos paso a paso, evitando tener constructores gigantescos (telescópicos) donde pasas 15 parámetros y te pierdes.

**Código analizado:** Paquete `builder` (`Customer.java`, `Principal.java`)

### 1. La Clase Cliente con su Constructor Privado
El profesor define la clase `Customer`. Lo primero que hace es poner su constructor en `private`. ¡Nadie puede hacer `new Customer()` desde fuera!

```java
public class Customer {
    private Integer id;
    private String name;
    // ... otros atributos ...

    private Customer() { } // Bloqueado
    // ...
```

### 2. La Clase Interna y Estática: El Constructor (`Builder`)
Dentro de la misma clase `Customer`, el profesor crea un `public static class Builder`.
Este Builder tiene los mismos atributos y métodos para ir llenándolos **uno por uno**, validando cada dato, y retornando `this` para poder encadenarlos.

```java
public static class Builder {
    private Customer customer;

    public Builder() {
        this.customer = new Customer();
    }

    public Builder email(String email) {
        // ¡Validaciones de negocio dentro de la construcción!
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.matches("^[uU][0-9]{8}@(utp.edu.pe)$")) {
            throw new IllegalArgumentException("Email no cumple con el formato");
        }
        this.customer.email = email;
        return this; // Retorna el builder para seguir encadenando
    }
    
    // El paso final para entregar el objeto construido
    public Customer build() {
        return this.customer;
    }
}
```

* **Qué quiso enseñar el profesor:**
  1.  **Inmutabilidad y Construcción Paso a Paso:** En lugar de enviar un DNI, un correo y un teléfono en un solo `new` y confundir el orden de los Strings, usas un constructor fluido: `.email("...").name("...")`.
  2.  **Validaciones Seguras:** El Builder del profesor incluye expresiones regulares (Regex) para validar que el correo sea exclusivamente institucional (`@utp.edu.pe`). Si algo está mal, el objeto jamás termina de construirse, protegiendo la integridad de los datos.

### 3. La Magia del `return this;` (Interfaz Fluida)
Una de las claves técnicas más importantes del Builder es que cada método de configuración termina con `return this;`. 
* `this` hace referencia al propio objeto `Builder` (el "albañil").
* Al devolver al albañil, el método permite el **Encadenamiento de Métodos** (Fluent Interface). Desde el `main`, no necesitas llamar al builder en líneas separadas, sino que puedes encadenar las instrucciones usando puntos: `new Builder().id(1).name("Max").build();`.
* El método final `.build()` es el único que rompe la cadena, ya que en lugar de devolver al albañil (`Builder`), entrega finalmente el producto terminado (`Customer`).

---

## 🧠 Apartado de Entendimiento Humano

### 🏭 Factory Method: "La Franquicia de Restaurantes"
Imagina que eres dueño de la franquicia "Burgers". Tienes un manual general de cómo atender al cliente, cobrar y entregar la comida (el `Creator`).
Sin embargo, abres un local en Lima y otro en México. 
- La franquicia de Lima (ConcreteCreatorA) sabe que cuando hay un pedido, debe cocinar una "Hamburguesa a lo Pobre" (ConcreteProductA). 
- La franquicia de México (ConcreteCreatorB) sabe que debe cocinar una "Hamburguesa con Jalapeños" (ConcreteProductB). 
- Como gerente central, no te importa qué hamburguesa hacen; tú solo les dices "Preparen un combo" (`someOperation()`) y cada local decide los ingredientes exactos (`factoryMethod()`).

### 🏗️ Builder: "El Subway (Arma tu Sandwich)"
Imagina que vas a pedir un sandwich. 
- **Sin Builder:** Tendrías que decirle al cajero todo de un solo respiro: *"Quiero pan orégano de 15cm con pavo queso suizo lechuga tomate sin cebolla mayonesa y mostaza"*. (Esto es un constructor normal de 10 parámetros; si te equivocas de orden, le echan mayonesa al pan en vez del tomate).
- **Con Builder:** El cajero te guía paso a paso.
  - Cajero: *"¿Qué pan?"* -> Tú: `.setPan("Orégano")`
  - Cajero: *"¿Qué carne?"* -> Tú: `.setCarne("Pavo")`
  - Cajero: *"¿Verduras?"* -> Tú: `.setVerduras("Lechuga y Tomate")`
  - Al final le dices: *"¡Listo!"* -> `.build()`
El Builder te permite armar objetos complejos sin estresarte por el orden o cantidad de los parámetros.

---

## 💻 Ejercicio Rápido (Versión Humana) para Repasar

**Problema 1:** Tienes un sistema que exporta reportes de ventas. Dependiendo del país del usuario, el sistema debe crear un Reporte con formato europeo (Día/Mes/Año) o americano (Mes/Día/Año). El código principal solo llama a `exportar()`, no debe saber de países. ¿Qué patrón usas?
> **Respuesta:** ¡Factory Method! Creas un creador general de reportes, y dejas que la fábrica de Europa y la de América decidan qué tipo exacto de reporte generar.

**Problema 2:** En un juego de rol, puedes crear un "Caballero". Un caballero puede tener o no tener: Escudo, Casco, Capa, Espada de fuego, Pociones, etc. Si trataras de hacer todo en un constructor, tendrías que pasar 15 `booleanos` (verdadero o falso). ¿Qué patrón usas para que la creación sea limpia?
> **Respuesta:** ¡Patrón Builder! Haces `new CaballeroBuilder().setEspada("Fuego").setCasco(true).build()`. ¡Solo agregas lo que realmente quieres!