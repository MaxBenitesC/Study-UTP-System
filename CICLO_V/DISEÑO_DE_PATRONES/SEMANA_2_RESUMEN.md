# SEMANA 2 - Principios OCP y LSP (con ejercicios)

**Curso:** Diseño de Patrones | **Docente:** Mg. Iván Robles Fernández | **UTP**

---

## TEMAS

- Principio Abierto/Cerrado (OCP)
- Principio de Sustitución de Liskov (LSP)
- Ejercicios prácticos de SRP, OCP y LSP

---

## 1. PRINCIPIO ABIERTO/CERRADO (OCP)

### Definición

> "Las entidades de software deben estar **abiertas para extensión**, pero **cerradas para modificación**."

- Se puede **agregar comportamiento nuevo** sin tocar el código que ya funciona.
- Se logra mediante **herencia** o **interfaces**.

### Señal de violación de OCP

Cuando para agregar un nuevo tipo, tienes que modificar una clase existente con `if/else` o `switch`:

```java
// MAL - viola OCP (cada tipo nuevo exige modificar esta clase)
public class ClaseQueNocumple {
    public void enviar(String tipo, String mensaje) {
        if (tipo.equals("email")) {
            System.out.println("Enviando email: " + mensaje);
        } else if (tipo.equals("sms")) {
            System.out.println("Enviando SMS: " + mensaje);
        } else if (tipo.equals("push")) {
            System.out.println("Enviando notificación Push: " + mensaje);
        }
        // Para agregar WhatsApp, hay que modificar este método → viola OCP
    }
}
```

### Solución con OCP

```java
// BIEN - interfaz base
public interface Notificacion {
    void enviar(String mensaje);
}

// Cada tipo es una clase independiente
public class NotificacionEmail implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando Email: " + mensaje);
    }
}

public class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando SMS: " + mensaje);
    }
}

public class NotificacionPush implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando Push: " + mensaje);
    }
}

public class NotificacionWhatsapp implements Notificacion {
    @Override
    public void enviar(String mensaje) {
        System.out.println("Enviando WhatsApp: " + mensaje);
    }
}

// El Notificador NO cambia nunca, sin importar cuántos tipos se agreguen
public class Notificador {
    public void notificar(Notificacion notificacion, String mensaje) {
        notificacion.enviar(mensaje);
    }
}
```

> Para agregar nuevo canal (Telegram, Signal), solo se crea una nueva clase. El `Notificador` **nunca cambia**.

---

## 2. PRINCIPIO DE SUSTITUCIÓN DE LISKOV (LSP)

### Definición

> "Los objetos de una clase derivada deben ser **reemplazables** por objetos de la clase base sin alterar el funcionamiento correcto del programa."

- Las subclases deben **respetar el contrato** de la clase base.
- Si una subclase no puede cumplir el comportamiento de la base → hay un problema de diseño.

### Señal de violación de LSP

```java
abstract class Animal {
    public abstract void hacerSonido();
}
class Perro extends Animal {
    @Override
    public void hacerSonido() { System.out.println("Ladrido"); }
}

// MAL - Pez viola LSP: no puede cumplir el contrato de hacerSonido
class Pez extends Animal {
    @Override
    public void hacerSonido() {
        throw new UnsupportedOperationException("Los peces no hacen sonido");
    }
}
```

### Solución: rediseñar la jerarquía

Si `Pez` no puede hacer sonido, la abstracción `Animal` con `hacerSonido()` es incorrecta. Se separa la jerarquía.

---

## 3. EJERCICIO DESARROLLADO EN LA UNIVERSIDAD - LSP con Aves

### Problema original (viola LSP)

Si `Ave` tiene `volar()`, el `Pinguino` no puede volar → viola LSP al intentar sobrescribir con excepción.

### Solución implementada en clase

```java
// Clase base - solo lo común a TODAS las aves
public class Ave {
    public void comer() {
        System.out.println("Estoy comiendo");
    }
}

// Subclase para aves que PUEDEN volar
public class AveVoladora extends Ave {
    public void volar() {
        System.out.println("Estoy volando");
    }
}

// Subclase para aves que NO pueden volar
public class AveNoVoladora extends Ave {
    public void caminar() {
        System.out.println("Caminar");
    }
}

// Aguila extiende AveVoladora (cumple LSP)
public class Aguila extends AveVoladora {
    // puede volar y comer
}

// Paloma extiende AveVoladora (cumple LSP)
public class Paloma extends AveVoladora {
    // puede volar y comer
}

// Pinguino extiende AveNoVoladora (cumple LSP - no necesita volar)
public class Pinguino extends AveNoVoladora {
    @Override
    public void caminar() {
        System.out.println("Estoy caminando");
    }

    @Override
    public void comer() {
        System.out.println("Estoy comiendo pescado");
    }
}
```

### Diagrama de jerarquía (LSP correcto)

```
Ave
├── AveVoladora
│   ├── Aguila
│   └── Paloma
└── AveNoVoladora
    └── Pinguino
```

> Clave: `Pinguino` NUNCA se le pide que vuele → No puede violar LSP.

---

## 4. EJERCICIO DESARROLLADO EN LA UNIVERSIDAD - OCP con Notificaciones

### El problema (viola OCP)

```java
// ClaseQueNocumple - si/else para cada tipo nuevo
public void enviar(String tipo, String mensaje) {
    if (tipo.equals("email")) { ... }
    else if (tipo.equals("sms")) { ... }
    else if (tipo.equals("push")) { ... }
    // Agregar Whatsapp = modificar este método → viola OCP
}
```

### La solución (cumple OCP)

```java
// Interfaz como contrato
public interface Notificacion {
    void enviar(String mensaje);
}

// Cada canal es independiente
public class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String mensaje) { /* lógica SMS */ }
}

// El Notificador nunca cambia
public class Notificador {
    public void notificar(Notificacion notificacion, String mensaje) {
        notificacion.enviar(mensaje);
    }
}
```

---

## 5. ANÁLISIS SOLID EN EL CÓDIGO UNIVERSITARIO

### OCP — ¿Dónde está la violación y dónde se corrige?

```java
// ❌ VIOLA OCP — ClaseQueNocumple.java
public class ClaseQueNocumple {
    public void enviar(String tipo, String mensaje){
        if(tipo.equals("email")){               // ← para agregar WhatsApp
            System.out.println("Enviando email: "+mensaje);  // hay que venir
        } else if(tipo.equals("sms")){          // ← AQUÍ y modificar
            System.out.println("Enviando SMS: "+mensaje);    // esta clase
        } else if(tipo.equals("push")){
            System.out.println("Enviando notificación Push: "+mensaje);
        }
        // ← Agregar WhatsApp = modificar esta clase → viola OCP
    }
}
```

**Por qué viola OCP:** cada nuevo canal de notificación obliga a **modificar** `ClaseQueNocumple`.

```java
// ✅ CUMPLE OCP — Notificacion.java (interfaz = punto de extensión)
public interface Notificacion {
    void enviar(String mensaje);  // ← contrato que NUNCA cambia
}

// Agregar nuevo canal = nueva clase, sin tocar nada existente
public class NotificacionSMS implements Notificacion {
    @Override
    public void enviar(String mensaje) { /* SMS */ }
}
// NotificacionWhatsapp, NotificacionEmail, NotificacionPush → igual

// Notificador.java — NUNCA cambia sin importar cuántos canales se agreguen
public class Notificador {
    public void notificar(Notificacion notificacion, String mensaje){
        notificacion.enviar(mensaje);  // ← solo conoce la abstracción
    }
}
```

**Dónde se refleja OCP:** la interfaz `Notificacion` es el **mecanismo de extensión**. El `Notificador` está **cerrado** (no se modifica) y el sistema está **abierto** (se extiende con nuevas clases).

---

### LSP — ¿Dónde está la violación y dónde se corrige?

```java
// ❌ SI HUBIERA PUESTO: Pinguino extends AveVoladora → violaría LSP
// class PinguinoMalo extends AveVoladora {
//     @Override
//     public void volar() {
//         throw new UnsupportedOperationException("No puedo volar"); // ← VIOLA LSP
//     }                                                              // rompe el contrato
// }

// ✅ CUMPLE LSP — jerarquía correcta
public class Ave {
    public void comer(){ ... }  // ← comportamiento COMÚN a toda ave
}

public class AveVoladora extends Ave {
    public void volar(){ ... }  // ← solo aves que SÍ vuelan
}

public class AveNoVoladora extends Ave {
    public void caminar(){ ... } // ← solo aves que NO vuelan
}

// Pinguino extiende AveNoVoladora → NUNCA se le pedirá volar → LSP cumplido
public class Pinguino extends AveNoVoladora {
    @Override
    public void caminar() { System.out.println("Estoy caminando"); }

    @Override
    public void comer() { System.out.println("Estoy comiendo pescado"); }
    // ← puede sustituir a AveNoVoladora sin problema
}
```

**Dónde se refleja LSP:** `Pinguino` puede reemplazar a `AveNoVoladora` en cualquier lugar del código sin sorpresas. Nunca se rompe el contrato de la clase base.

---

## 6. RESUMEN COMPARATIVO

| Principio | Qué prohíbe | Solución |
|-----------|-------------|----------|
| **OCP** | Modificar clases existentes para agregar funcionalidad | Usar herencia/interfaces para extender |
| **LSP** | Subclases que rompen el contrato de la clase base | Rediseñar la jerarquía de herencia |

---

## 6. PREGUNTAS FRECUENTES DE EXAMEN

**¿Cuándo viola OCP un código?**
→ Cuando hay `if/else` o `switch` que evalúa tipos para elegir comportamiento.

**¿Cuándo viola LSP un código?**
→ Cuando una subclase lanza `UnsupportedOperationException` o hace algo inesperado al heredar.

**¿Cuál es la diferencia entre OCP y LSP?**
→ OCP es sobre **extensión sin modificación**. LSP es sobre **sustitución correcta en herencia**.
