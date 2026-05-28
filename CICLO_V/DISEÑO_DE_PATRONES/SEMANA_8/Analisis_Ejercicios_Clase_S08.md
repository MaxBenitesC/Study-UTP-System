# Análisis Forense: Ejercicios de Clase (Semana 8)

Este documento contiene el análisis detallado de los proyectos desarrollados en la Semana 8 (extraídos de `S08_s1-PatronAdapteYFacade.zip`). En esta sesión dejamos atrás los patrones creacionales y entramos a los **Patrones Estructurales**, específicamente: **Adapter** (Adaptador) y **Facade** (Fachada).

Los patrones estructurales se enfocan en cómo ensamblar clases y objetos para formar estructuras más grandes, manteniendo esas estructuras flexibles y eficientes.

---

## Parte 1: Patrón Adapter (El Traductor)

El patrón Adapter permite que clases con interfaces incompatibles trabajen juntas. Envuelve un objeto existente y proporciona la interfaz que otro componente espera.

El profesor dejó dos ejemplos. Analizaremos el segundo (`patron.adapter.dos`), que es un caso práctico excelente de la vida real.

**Código analizado:** Paquete `patron.adapter.dos` (`ECommerceApp.java`, `OrdenRepository.java`, `ExternalOrderService.java`, `OrderAdapter.java`, `Main.java`)

### 1. El Cliente y el Contrato Local (`OrdenRepository.java`)
Tu aplicación de E-Commerce moderna trabaja con objetos Java. Para guardar una orden, espera que cualquier repositorio cumpla con este contrato:
```java
public interface OrdenRepository {
    void save(Order order); // Recibe un objeto Order de Java
}
```

### 2. El Servicio Incompatible (`ExternalOrderService.java`)
Tienes un sistema externo antiguo (legado) que es buenísimo guardando, pero **no entiende objetos Java**. Solo entiende texto en formato XML.
```java
public class ExternalOrderService {
    public void saveOrderXml(String orderXML){
        System.out.println("Guardando pedido en el sistema externo");
        System.out.println("" + orderXML);
    }
}
```
*Problema:* Tu app quiere mandar un objeto `Order`, pero el sistema externo pide un `String XML`. ¡No hablan el mismo idioma!

### 3. La Solución: El Adaptador (`OrderAdapter.java`)
Creamos una clase que "firme" el contrato moderno (`OrdenRepository`), pero que por dentro use el sistema antiguo.

```java
public class OrderAdapter implements OrdenRepository {

    private final ExternalOrderService externalService; // Tiene el sistema antiguo por dentro

    public OrderAdapter(ExternalOrderService service) {
        this.externalService = service;
    }

    @Override
    public void save(Order order) {
        // 1. Traduce de "Objeto Java" a "Texto XML"
        String xml = convertToXml(order); 
        
        // 2. Le pasa el XML al sistema antiguo
        externalService.saveOrderXml(xml); 
    }
    
    // Método privado de traducción
    public String convertToXml(Order order){
        return "<order><id>" + order.getId() + "</id>...</order>";    
    }
}
```

* **Qué quiso enseñar el profesor:** El objetivo es demostrar cómo integrar librerías de terceros, APIs externas o código legado sin tener que reescribir tu propia aplicación. Tu App (`ECommerceApp`) sigue usando `repository.save(order)` felizmente, ignorando que por debajo hay un adaptador traduciendo todo a XML para un sistema viejo.

---

## Parte 2: Patrón Facade (La Recepcionista)

El patrón Facade (Fachada) proporciona una interfaz simplificada a una biblioteca, un framework o cualquier grupo complejo de clases.

**Código analizado:** Paquete `facade` (`Proyector.java`, `Amplifier.java`, `DVDPlayer.java`, `HomeTheater.java`)

### 1. El Subsistema Complejo
Tienes 3 aparatos diferentes para tu cine en casa. Para ver una simple película, mira todo lo que tienes que hacer:
- Encender el proyector (`proyector.on()`)
- Darle play al proyector (`proyector.play()`)
- Encender el amplificador (`amplifier.on()`)
- Conectar el DVD al amplificador (`amplifier.setDVD(dvd)`)
- Encender el DVD (`dvd.on()`)
- Darle play al DVD (`dvd.play()`)

Escribir todo esto en el `main` cada vez que quieres ver una película es un dolor de cabeza y acopla tu `main` a demasiadas clases.

### 2. La Fachada (`HomeTheater.java`)
El profesor crea una clase que agrupa toda esta lógica compleja detrás de un solo botón mágico.

```java
public class HomeTheater {
    private DVDPlayer dvd;
    private Proyector proyector;
    private Amplifier amplifier;

    // ... Constructor que recibe los equipos ...
    
    // LA FACHADA: Un solo método sencillo para el cliente
    public void watchMovie(String movie){
        System.out.println("Prepara el sistema para ver la pelicula :" + movie);
        proyector.on();
        proyector.play();
        amplifier.on();
        amplifier.setDVD(dvd);
        dvd.on();
        dvd.play();  
    }
}
```

* **Qué quiso enseñar el profesor:** Si el cliente (el `main`) quiere ver "Matrix", no tiene por qué aprender cómo configurar el audio, los cables y el proyector. Solo llama a `homeTheater.watchMovie("Matrix")`. La fachada se encarga de la coreografía sucia por debajo. 

---

## 🧠 Apartado de Entendimiento Humano

### 🔌 Adapter: "El Enchufe de Viaje"
Imagina que viajas de Perú a Europa. Llevas tu cargador de laptop (Objeto Java) que tiene **puntas planas**. Pero la pared en Europa (Sistema Externo) solo tiene agujeros para **puntas redondas**. 
No puedes romper la pared ni romper tu cargador. 
¿Qué haces? Te compras un **Adaptador**. Por la parte de atrás, el adaptador recibe tus puntas planas (implementa la interfaz que tú necesitas), y por la parte de adelante tiene puntas redondas que entran en la pared de Europa. El Adaptador tradujo físicamente la conexión.

### 🏢 Facade: "El Mesero del Restaurante"
Imagina que vas a un restaurante fino. Quieres comer una "Pasta a la Carbonara".
- **Sin Fachada:** Tendrías que ir a la cocina, decirle al cocinero 1 que hierva el agua, decirle al cocinero 2 que fría el tocino, ir al cuarto de limpieza a buscar tu propio plato, y luego armarlo tú mismo. Tienes que interactuar con todo el "subsistema" de la cocina.
- **Con Fachada:** Hablas con **El Mesero (Facade)**. Solo le dices "Quiero Pasta a la Carbonara". El mesero va a la cocina y coordina al cocinero 1, al cocinero 2 y busca los platos. A ti no te importa cómo lo hizo, tú solo pediste la orden y recibiste la comida.

---

## 💻 Ejercicio Rápido (Versión Humana) para Repasar

**Problema 1:** Tienes un bloque de código enorme que arranca la base de datos, carga la caché, valida las contraseñas y abre el puerto del servidor. Cansa escribir todo eso. Decides meter todo ese código feo en una clase `ServerStart` con un método único llamado `encender()`. ¿Qué patrón acabas de usar?
> **Respuesta:** ¡El patrón Facade! Escondiste la complejidad de arrancar un servidor detrás de un solo método simple.

**Problema 2:** Tienes un sistema moderno de mapas que dibuja coordenadas `[Latitud, Longitud]`. Un proveedor te vende unos mapas increíbles de clima, pero su sistema solo recibe coordenadas `[X, Y]`. Creas una clase intermedia que recibe Latitud y Longitud, y por dentro hace una fórmula matemática para pasarlas a X e Y antes de enviarlas al proveedor. ¿Qué patrón es?
> **Respuesta:** ¡El patrón Adapter! "Tradujiste" los datos para que el sistema moderno y el proveedor incompatible puedan trabajar juntos.