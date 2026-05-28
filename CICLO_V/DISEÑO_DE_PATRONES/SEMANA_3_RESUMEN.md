# SEMANA 3 - Principios ISP y DIP

**Curso:** Diseño de Patrones | **Docente:** Mg. Iván Robles Fernández | **UTP**

---

## TEMAS

- Principio de Segregación de Interfaces (ISP)
- Principio de Inversión de Dependencia (DIP)

---

## 1. PRINCIPIO DE SEGREGACIÓN DE INTERFACES (ISP)

### Definición

> "Los clientes **no deben estar obligados a depender de interfaces que no utilizan**."

- Es preferible tener **varias interfaces específicas** en lugar de una interfaz general gigante.
- Una clase no debe implementar métodos que no necesita.

### Señal de violación de ISP

Una interfaz "gorda" que obliga a implementar métodos vacíos o que lanzan excepciones:

```java
// MAL - interfaz gigante (viola ISP)
public interface Impresora {
    void imprimir();
    void escanear();
    void enviarFax();
}

// ImpresoraBasica se ve forzada a implementar escanear y fax que NO necesita
public class ImpresoraBasica implements Impresora {
    @Override
    public void imprimir() { System.out.println("Imprimiendo..."); }

    @Override
    public void escanear() { /* No implementado - forzada */ }

    @Override
    public void enviarFax() { /* No implementado - forzada */ }
}
```

### Solución con ISP

```java
// BIEN - interfaces segregadas (una por responsabilidad)
public interface Impresora {
    void imprimir();
}

public interface Scanner {
    void escanear();
}

public interface Fax {
    void enviarFax();
}

// ImpresoraBasica solo implementa lo que necesita
public class ImpresoraBasica implements Impresora {
    @Override
    public void imprimir() { System.out.println("Imprimiendo..."); }
}

// ImpresoraMultifuncional implementa todo porque SÍ lo necesita
public class ImpresoraMultifuncional implements Impresora, Scanner, Fax {
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo Documento Multifuncional...");
    }

    @Override
    public void escanear() {
        System.out.println("Escaneando Documento...");
    }

    @Override
    public void enviarFax() {
        System.out.println("Enviando Fax....");
    }
}
```

### Diagrama ISP

```
SIN ISP (MAL)                CON ISP (BIEN)
┌─────────────────┐          ┌───────────┐  ┌──────────┐  ┌─────┐
│    Impresora    │          │ Impresora │  │ Scanner  │  │ Fax │
│  - imprimir()  │    →     └─────┬─────┘  └────┬─────┘  └──┬──┘
│  - escanear()  │                │              │            │
│  - enviarFax() │         ImpresoraBasica   ImpresoraMultifuncional
└─────────────────┘         (solo imprimir)   (implementa todo)
```

---

## 2. EJERCICIO ISP DESARROLLADO EN LA UNIVERSIDAD

**Archivos:** `SEMANA_3/SEGREGACION_INTERFACE/`

### Versión incorrecta (viola ISP)
```java
// Una sola interfaz con todo → fuerza a ImpresoraBasica a implementar escanear y fax
package DP_S03_E1_INCORRECTO;
public interface Impresora {
    void imprimir();
    void escanear();
    void enviarFax();
}
```

### Versión correcta (aplica ISP)
```java
// Interfaces separadas
package DP_S03_E1_CORRECTO;

public interface Impresora { void imprimir(); }
public interface Scanner { void escanear(); }
public interface Fax { void enviarFax(); }

// La multifuncional implementa las 3 porque las necesita todas
public class ImpresoraMultifuncional implements Impresora, Scanner, Fax {
    @Override
    public void imprimir() { System.out.println("Imprimiendo Documento Multifuncional..."); }
    @Override
    public void enviarFax() { System.out.println("Enviando Fax...."); }
    @Override
    public void escanear() { System.out.println("Escaneando Documento..."); }
}
```

---

## 3. PRINCIPIO DE INVERSIÓN DE DEPENDENCIA (DIP)

### Definición

> "Los módulos de alto nivel **no deben depender de módulos de bajo nivel**. Ambos deben depender de **abstracciones**."
> 
> "Las abstracciones no deben depender de los detalles. Los detalles deben depender de las abstracciones."

- **Alto nivel:** clases que orquestan lógica de negocio (ej. Tienda, Pedido)
- **Bajo nivel:** clases que implementan detalles (ej. Paypal, BaseDatosSQL)
- La solución: que ambos dependan de una **interfaz/abstracción** intermedia.

### Señal de violación de DIP

```java
// MAL - TiendaIncorrecta depende DIRECTAMENTE de Paypal (implementación concreta)
public class TiendaIncorrecta {
    private Paypal p1;  // acoplada a Paypal

    public TiendaIncorrecta() {
        this.p1 = new Paypal();  // imposible cambiar método de pago sin tocar esta clase
    }

    public void realizarPago(double monto) {
        p1.pagar(monto);
    }
}
```

> Problema: Si quiero pagar con TarjetaCredito, debo **modificar** `TiendaIncorrecta`.

### Solución con DIP

```java
// BIEN - Interfaz como abstracción
public interface MetodoPago {
    void pagar(double monto);
}

// Implementaciones concretas dependen de la interfaz
public class Paypal implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.printf("Pago de $ %.2f realizado con Paypal\n", monto);
    }
}

public class TarjetaCredito implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.printf("Pago de $ %.2f realizado con Tarjeta\n", monto);
    }
}

public class TransferenciaBancaria implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.printf("Pago de $ %.2f por Transferencia\n", monto);
    }
}

// TiendaCorrecta depende de la ABSTRACCIÓN, no de la implementación
public class TiendaCorrecta {
    private MetodoPago m1;  // depende de interfaz

    public TiendaCorrecta(MetodoPago m1) {  // inyección de dependencia
        this.m1 = m1;
    }

    public void realizarPago(double monto) {
        m1.pagar(monto);
    }
}
```

### Uso (Main)

```java
public class MainCorrecto {
    public static void main(String[] args) {
        // Cambiar método de pago sin tocar TiendaCorrecta
        TiendaCorrecta t1 = new TiendaCorrecta(new Paypal());
        t1.realizarPago(2000);

        TiendaCorrecta t2 = new TiendaCorrecta(new TarjetaCredito());
        t2.realizarPago(5000);

        TiendaCorrecta t3 = new TiendaCorrecta(new TransferenciaBancaria());
        t3.realizarPago(10000);
    }
}
```

### Diagrama DIP

```
SIN DIP (MAL)                    CON DIP (BIEN)
┌──────────────────┐             ┌───────────────┐
│ TiendaIncorrecta │             │ TiendaCorrecta│
│ - Paypal p1      │    →        │ - MetodoPago  │ ←── interfaz (abstracción)
└──────────────────┘             └───────────────┘         ↑        ↑        ↑
        ↓                                             Paypal  Tarjeta  Transferencia
     Paypal (acoplado)                            (implementaciones concretas)
```

---

## 4. ANÁLISIS SOLID EN EL CÓDIGO UNIVERSITARIO

### ISP — ¿Dónde está la violación y dónde se corrige?

```java
// ❌ VIOLA ISP — DP_S03_E1_INCORRECTO/Impresora.java
package DP_S03_E1_INCORRECTO;
public interface Impresora {
    void imprimir();     // ← ImpresoraBasica SÍ necesita esto
    void escanear();     // ← ImpresoraBasica NO necesita esto ← PROBLEMA
    void enviarFax();    // ← ImpresoraBasica NO necesita esto ← PROBLEMA
}
// Cualquier clase que implemente esta interfaz se ve FORZADA a
// implementar métodos que no usa → viola ISP
```

```java
// ✅ CUMPLE ISP — DP_S03_E1_CORRECTO/
// Cada interfaz tiene UNA responsabilidad
public interface Impresora { void imprimir(); }   // ← solo imprimir
public interface Scanner { void escanear(); }     // ← solo escanear
public interface Fax { void enviarFax(); }        // ← solo fax

// ImpresoraBasica: solo implementa lo que NECESITA
public class ImpresoraBasica implements Impresora {
    @Override
    public void imprimir() { ... } // ← solo lo suyo
    // No implementa Scanner ni Fax → no es forzada
}

// ImpresoraMultifuncional: implementa TODO porque PUEDE hacer todo
public class ImpresoraMultifuncional implements Impresora, Scanner, Fax {
    @Override public void imprimir() { System.out.println("Imprimiendo Documento Multifuncional..."); }
    @Override public void enviarFax() { System.out.println("Enviando Fax...."); }
    @Override public void escanear() { System.out.println("Escaneando Documento..."); }
}
```

**Dónde se refleja ISP:** la clave está en que `ImpresoraBasica` ya **NO está obligada** a implementar `escanear()` ni `enviarFax()`. Cada clase implementa **solo las interfaces que realmente usa**.

---

### DIP — ¿Dónde está la violación y dónde se corrige?

```java
// ❌ VIOLA DIP — DP_S03_E1_INCORRECTO/TiendaIncorrecta.java
public class TiendaIncorrecta {
    private Paypal p1;              // ← depende de implementación CONCRETA

    public TiendaIncorrecta() {
        this.p1 = new Paypal();     // ← módulo de ALTO NIVEL (Tienda)
    }                               //   crea directamente módulo de BAJO NIVEL (Paypal)
                                    //   → acoplamiento fuerte
    public void realizarPago(double monto){
        p1.pagar(monto);            // ← solo puede pagar con Paypal, nunca con otro método
    }
}
```

```java
// ✅ CUMPLE DIP — DP_S03_E1_CORRECTO/

// La abstracción (interfaz) es el puente
public interface MetodoPago {
    void pagar(double monto);       // ← abstracción que separa alto y bajo nivel
}

// Módulos de BAJO NIVEL dependen de la abstracción
public class Paypal implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.printf("Pago de $ %.2f realizado con Paypal\n", monto);
    }
}
// TarjetaCredito, TransferenciaBancaria → igual

// Módulo de ALTO NIVEL también depende de la abstracción (NO de Paypal)
public class TiendaCorrecta {
    private MetodoPago m1;          // ← depende de ABSTRACCIÓN, no de Paypal

    public TiendaCorrecta(MetodoPago m1) {  // ← inyección de dependencia
        this.m1 = m1;               // recibe cualquier MetodoPago desde afuera
    }

    public void realizarPago(double monto){
        m1.pagar(monto);            // ← no sabe ni le importa qué implementación es
    }
}

// MainCorrecto demuestra DIP: TiendaCorrecta nunca cambia
TiendaCorrecta t1 = new TiendaCorrecta(new Paypal());           // paga con Paypal
TiendaCorrecta t2 = new TiendaCorrecta(new TarjetaCredito());   // paga con tarjeta
TiendaCorrecta t3 = new TiendaCorrecta(new TransferenciaBancaria()); // paga por transferencia
```

**Dónde se refleja DIP:** `TiendaCorrecta` depende de `MetodoPago` (abstracción) y **nunca** de `Paypal`, `TarjetaCredito` ni nada concreto. El módulo de alto nivel y los de bajo nivel **dependen ambos de la interfaz**.

---

## 5. COMPARACIÓN ISP vs DIP

| Aspecto | ISP | DIP |
|---------|-----|-----|
| **Trata sobre** | Interfaces/contratos | Dependencias entre módulos |
| **Problema que resuelve** | Interfaces con demasiados métodos | Clases acopladas a implementaciones |
| **Solución** | Dividir interfaces grandes | Depender de abstracciones (interfaces) |
| **Clave** | "No implementes lo que no usas" | "Depende de interfaces, no de clases concretas" |

---

## 5. PREGUNTAS FRECUENTES DE EXAMEN

**¿Cómo sé si viola ISP?**
→ Una clase implementa métodos con cuerpo vacío o lanza `UnsupportedOperationException`.

**¿Cómo sé si viola DIP?**
→ Una clase de alto nivel hace `new ClaseConcretaDeBajoNivel()` dentro de sí misma.

**¿Cuál es la técnica que usa DIP para resolver el problema?**
→ **Inyección de dependencias**: pasar la implementación por constructor o método.

**Ejemplo de DIP en el mundo real:**
→ `TiendaCorrecta` recibe cualquier `MetodoPago` por constructor → no le importa si es Paypal, tarjeta o transferencia.
