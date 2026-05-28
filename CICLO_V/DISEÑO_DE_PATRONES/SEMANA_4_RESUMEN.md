# SEMANA 4 - Patrones de Diseño: Singleton y Factory Method

**Curso:** Diseño de Patrones | **Docente:** Mg. Iván Robles Fernández | **UTP**

---

## TEMAS

- Patrón Singleton
- Patrón Factory Method
- Ejercicios prácticos de ambos patrones

---

## INTRODUCCIÓN: ¿Qué son los Patrones de Diseño?

Los **patrones de diseño** son soluciones reutilizables a problemas comunes del software. Son como "plantillas" probadas para resolver situaciones recurrentes.

### Categorías de patrones:
| Categoría | Descripción | Ejemplos |
|-----------|-------------|---------|
| **Creacionales** | Cómo se crean los objetos | Singleton, Factory Method, Abstract Factory |
| **Estructurales** | Cómo se organizan las clases | Adapter, Decorator, Facade |
| **Comportamiento** | Cómo se comunican los objetos | Observer, Strategy, Command |

> Singleton y Factory Method son patrones **Creacionales**.

---

## 1. PATRÓN SINGLETON

### Definición

> Garantiza que una clase tenga **una única instancia** y proporciona un punto de acceso global a ella.

### ¿Cuándo usar Singleton?
- Configuración de la aplicación (una sola config)
- Conexión a base de datos (un solo pool de conexiones)
- Logs del sistema
- Caché

### Estructura del Singleton

```java
public class Singleton {
    private static Singleton instancia;  // 1. atributo estático privado

    private Singleton() { }  // 2. constructor PRIVADO (nadie puede hacer new)

    public static Singleton getInstancia() {  // 3. método estático público
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }
}
```

### 3 reglas del Singleton

1. **Constructor privado** → nadie puede hacer `new ClaseSingleton()`
2. **Atributo estático privado** → guarda la única instancia
3. **Método estático `getInstancia()`** → devuelve siempre la misma instancia

---

## 2. SINGLETON DESARROLLADO EN LA UNIVERSIDAD

**Archivo:** `SEMANA_4/PatronSingleton/src/Patrones/ConfiguracionApp.java`

```java
package Patrones;

public class ConfiguracionApp {
    private static ConfiguracionApp instancia;  // Única instancia

    private String urlBase;
    private String username;
    private String password;

    // Constructor privado - se inicializa con valores por defecto
    public ConfiguracionApp() {
        this.urlBase = "http://localhost:8080";
        this.username = "admin";
        this.password = "1234";
    }

    // Método estático para obtener la instancia única
    public static ConfiguracionApp getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionApp();
        }
        return instancia;
    }
}
```

### Uso del Singleton

```java
// Siempre se obtiene la MISMA instancia
ConfiguracionApp config1 = ConfiguracionApp.getInstancia();
ConfiguracionApp config2 = ConfiguracionApp.getInstancia();
// config1 == config2 → true (mismo objeto en memoria)
```

---

## 3. PATRÓN FACTORY METHOD

### Definición

> Define una **interfaz para crear objetos**, pero deja a las subclases decidir qué clase instanciar. El Factory Method permite que una clase delegue la creación de objetos a sus subclases.

### ¿Cuándo usar Factory Method?
- Cuando no sabes de antemano qué clase concreta crear
- Cuando quieres que las subclases decidan qué crear
- Para desacoplar el código cliente de las clases concretas

### Estructura del Factory Method

```
«interface»          «abstract class»
Producto             CreadorFactory
  + operacion()        + crearProducto(): Producto
      ↑                        ↑
ProductoConcreto       CreadorConcreto
  + operacion()          + crearProducto(): Producto
                              └─── return new ProductoConcreto()
```

**Componentes:**
| Componente | Rol |
|------------|-----|
| `Producto` (interface) | Define el contrato del objeto a crear |
| `ProductoConcreto` (clase) | Implementación específica |
| `Factory` (abstract) | Declara el método `crearProducto()` |
| `FactoryConcreta` (clase) | Implementa `crearProducto()` devolviendo el concreto |

---

## 4. FACTORY METHOD DESARROLLADO EN LA UNIVERSIDAD

**Archivos:** `SEMANA_4/PatronFactoryMethod/src/Patrones/`

```java
// 1. Producto (interface) - contrato
public interface Reporte {
    void generar();
}

// 2. Productos concretos
public class ReportePDF implements Reporte {
    @Override
    public void generar() {
        System.out.println("Generando reporte en formato PDF");
    }
}

public class ReporteWord implements Reporte {
    @Override
    public void generar() {
        System.out.println("Generando reporte en formato Word");
    }
}

// 3. Factory abstracta
public abstract class ReporteFactory {
    public abstract Reporte crearReporte();
}

// 4. Factories concretas
public class ReportePDFFactory extends ReporteFactory {
    @Override
    public Reporte crearReporte() {
        return new ReportePDF();
    }
}

public class ReporteWordFactory extends ReporteFactory {
    @Override
    public Reporte crearReporte() {
        return new ReporteWord();
    }
}

// 5. Uso (Main)
public class Main {
    public static void main(String[] args) {
        // Crear Reporte PDF
        ReporteFactory factoryPDF = new ReportePDFFactory();
        Reporte reportePDF = factoryPDF.crearReporte();
        reportePDF.generar();  // "Generando reporte en formato PDF"

        // Crear Reporte Word
        ReporteFactory factoryWord = new ReporteWordFactory();
        Reporte reporteWord = factoryWord.crearReporte();
        reporteWord.generar();  // "Generando reporte en formato Word"
    }
}
```

---

## 5. EJERCICIOS PRÁCTICOS (6 ejercicios realizados en clase)

### Ejercicio 1: Vehículos

```java
// Interfaz producto
public interface Vehiculo { void arrancar(); }

// Factory abstracta
public abstract class VehiculoFactory {
    public abstract Vehiculo crearVehiculo();
}

// Implementaciones concretas
public class Auto implements Vehiculo {
    @Override
    public void arrancar() { System.out.println("Arranca el Auto"); }
}

public class AutoFactory extends VehiculoFactory {
    @Override
    public Vehiculo crearVehiculo() { return new Auto(); }
}

// Igual para MotoFactory y CamionFactory...

// Main
public class main {
    public static void main(String[] args) {
        VehiculoFactory factoryAuto = new AutoFactory();
        Vehiculo auto = factoryAuto.crearVehiculo();
        auto.arrancar();  // "Arranca el Auto"

        VehiculoFactory factoryMoto = new MotoFactory();
        factoryMoto.crearVehiculo().arrancar();  // "Arranca la Moto"

        VehiculoFactory factoryCamion = new CamionFactory();
        factoryCamion.crearVehiculo().arrancar();  // "Arranca el Camion"
    }
}
```

### Ejercicio 2: Cafetería (Bebidas)
- **Producto:** `Bebida` (interface con `preparar()`)
- **Factories:** `CafeFactory`, `JugoFactory`, `TeFactory`
- Concretos: `Cafe`, `Jugo`, `Te`

### Ejercicio 3: Pagos
- **Producto:** `Pagos` (interface con `realizar()`)
- **Factories:** `EfectivoFactory`, `PaypalFactory`, `TarjetaFactory`
- Concretos: `Efectivo`, `Paypal`, `Tarjeta`

### Ejercicio 4: Notificaciones
- **Producto:** `Notificacion` (interface con `enviar()`)
- **Factories:** `EmailFactory`, `SMSFactory`, `WhatsAppFactory`
- Concretos: `Email`, `SMS`, `WhatsApp`

### Ejercicio 5: Documentos
- **Producto:** `Documento` (interface con `abrir()`)
- **Factories:** `PDFFactory`, `WordFactory`, `ExcelFactory`
- Concretos: `PDF`, `Word`, `Excel`

### Ejercicio 6: Logística
- **Producto:** `Envio` (interface con `entregar()`)
- **Factories:** `ExpressFactory`, `NacionalFactory`, `InternacionalFactory`
- Concretos: `Express`, `Nacional`, `Internacional`

---

## 6. ANÁLISIS DE PATRONES EN EL CÓDIGO UNIVERSITARIO

### Singleton — ¿Dónde están los 3 elementos clave en `ConfiguracionApp`?

```java
public class ConfiguracionApp {

    // ELEMENTO 1 ↓ — atributo estático privado (guarda la única instancia)
    private static ConfiguracionApp instancia;
    //              ^^^^^^ static = pertenece a la clase, no a un objeto

    private String urlBase;
    private String username;
    private String password;

    // NOTA: en el código universitario el constructor NO tiene private,
    // pero en un Singleton correcto DEBE ser private para evitar new ConfiguracionApp()
    public ConfiguracionApp() {     // ← idealmente debería ser PRIVATE
        this.urlBase = "http://localhost:8080";
        this.username = "admin";
        this.password = "1234";
    }

    // ELEMENTO 2 ↓ — método estático público (única puerta de entrada)
    public static ConfiguracionApp getInstancia(){
    //             ^^^^^^ static = se llama sin crear objeto primero

        // ELEMENTO 3 ↓ — lazy initialization (crea solo si no existe)
        if (instancia == null){
            instancia = new ConfiguracionApp();  // ← primera vez: crea
        }
        return instancia;  // ← siempre devuelve la misma
    }
}

// USO:
ConfiguracionApp c1 = ConfiguracionApp.getInstancia();
ConfiguracionApp c2 = ConfiguracionApp.getInstancia();
// c1 == c2 → TRUE (mismo objeto en memoria)
```

**Los 3 pilares del Singleton en el código:**
1. `private static ConfiguracionApp instancia` → guarda la referencia única
2. Constructor privado (idealmente) → nadie puede hacer `new ConfiguracionApp()`
3. `public static getInstancia()` → único punto de acceso, con control `if null`

---

### Factory Method — ¿Dónde están los 4 componentes en el código universitario?

```java
// COMPONENTE 1: Producto (interfaz) — contrato que todos los productos deben cumplir
public interface Reporte {
    void generar();  // ← todos los reportes deben poder generarse
}

// COMPONENTE 2: Productos concretos — implementan el contrato
public class ReportePDF implements Reporte {
    @Override
    public void generar() {
        System.out.println("Generando reporte en formato PDF");
        // ← implementación específica de PDF
    }
}
public class ReporteWord implements Reporte {
    @Override
    public void generar() {
        System.out.println("Generando reporte en formato Word");
    }
}

// COMPONENTE 3: Factory abstracta — declara el método de creación
public abstract class ReporteFactory {
    public abstract Reporte crearReporte();  // ← subclases deciden QUÉ crear
}

// COMPONENTE 4: Factories concretas — implementan la creación específica
public class ReportePDFFactory extends ReporteFactory {
    @Override
    public Reporte crearReporte() {
        return new ReportePDF();  // ← sabe qué crear, el cliente NO
    }
}
public class ReporteWordFactory extends ReporteFactory {
    @Override
    public Reporte crearReporte() {
        return new ReporteWord();
    }
}

// CLIENTE (Main) — solo conoce las abstracciones, nunca hace "new ReportePDF()"
public class Main {
    public static void main(String[] args) {
        ReporteFactory factoryPDF = new ReportePDFFactory();
        Reporte reportePDF = factoryPDF.crearReporte();  // ← no sabe que es PDF
        reportePDF.generar();                            // ← solo llama al contrato

        ReporteFactory factoryWord = new ReporteWordFactory();
        Reporte reporteWord = factoryWord.crearReporte();
        reporteWord.generar();
    }
}
```

**Los 4 componentes en el código:**
- `Reporte` (interface) → **Producto abstracto** — define qué puede hacer un reporte
- `ReportePDF`, `ReporteWord` → **Productos concretos** — cómo se hace cada uno
- `ReporteFactory` (abstract) → **Creator abstracto** — dice que se puede crear, no cómo
- `ReportePDFFactory`, `ReporteWordFactory` → **Creators concretos** — saben exactamente qué crear

---

## 7. COMPARACIÓN SINGLETON vs FACTORY METHOD

| Aspecto | Singleton | Factory Method |
|---------|-----------|----------------|
| **Categoría** | Creacional | Creacional |
| **Propósito** | Una sola instancia | Delegar creación de objetos |
| **Clave** | Constructor privado + `getInstancia()` | Interface + subclases Factory |
| **Cuándo usar** | Config, logs, conexiones DB | Cuando el tipo de objeto varía |
| **Problema resuelve** | Múltiples instancias no deseadas | Acoplamiento a clases concretas |

---

## 7. PLANTILLA FACTORY METHOD (para cualquier ejercicio)

```java
// 1. Interfaz del producto
interface Producto { void operacion(); }

// 2. Implementaciones
class ProductoA implements Producto {
    public void operacion() { System.out.println("Producto A"); }
}

// 3. Factory abstracta
abstract class Factory {
    public abstract Producto crear();
}

// 4. Factory concreta
class FactoryA extends Factory {
    public Producto crear() { return new ProductoA(); }
}

// 5. Uso
Factory f = new FactoryA();
Producto p = f.crear();
p.operacion();
```

---

## 8. PREGUNTAS FRECUENTES DE EXAMEN

**¿Cuáles son las 3 características del Singleton?**
→ Constructor privado, atributo estático privado, método `getInstancia()` estático.

**¿Para qué sirve el Factory Method?**
→ Para crear objetos sin que el cliente sepa qué clase concreta se crea; desacopla creación de uso.

**¿Cuántos componentes tiene el Factory Method?**
→ 4: Producto (interface), ProductoConcreto, Factory (abstract), FactoryConcreta.

**¿Qué devuelve `getInstancia()` en Singleton?**
→ Siempre el mismo objeto; si no existe, lo crea; si ya existe, lo devuelve.
