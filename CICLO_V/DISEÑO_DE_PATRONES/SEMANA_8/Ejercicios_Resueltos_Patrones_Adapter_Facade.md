---
universidad: UTP
curso: Diseño de Patrones
tema: Ejercicios Prácticos — Patrón Estructural Adapter y Facade
semana: 8
sesion: 08
tipo_documento: Guía de ejercicios resueltos (Word exportado a PDF)
paginas: 5
fuente_pdf: Ejercicios_Resueltos_Patrones_Adapter_Facade.pdf
autor_pdf: jaime de la torre
---

# DISEÑO DE PATRONES — SEMANA 08

## Ejercicios Prácticos - Patrón Estructural Adapter y Facade

## Tabla de Contenidos

- [1. Ejercicio Adapter — Integración Sistema Cliente Antiguo y Nuevo](#1-ejercicio-adapter--integración-sistema-cliente-antiguo-y-nuevo)
  - [1.1 Estructura del proyecto Adapter](#11-estructura-del-proyecto-adapter)
  - [1.2 Pestaña SistemaClientesAntiguo.java](#12-pestaña-sistemaclientesantiguojava)
  - [1.3 Pestaña ClienteTarget.java](#13-pestaña-clientetargetjava)
  - [1.4 Pestaña ClienteAdapter.java](#14-pestaña-clienteadapterjava)
  - [1.5 Pestaña ClienteDemo.java](#15-pestaña-clientedemojava)
- [2. Ejercicio Facade — Sistema Hospital](#2-ejercicio-facade--sistema-hospital)
  - [2.1 Estructura del proyecto Hospital](#21-estructura-del-proyecto-hospital)
  - [2.2 Pestaña SistemaEMR.java](#22-pestaña-sistemaemrjava)
  - [2.3 Pestaña SistemaLaboratorio.java](#23-pestaña-sistemalaboratoriojava)
  - [2.4 Pestaña SistemaFacturacion.java](#24-pestaña-sistemafacturacionjava)
  - [2.5 Pestaña FacadeHospital.java](#25-pestaña-facadehospitaljava)
  - [2.6 Pestaña Main.java](#26-pestaña-mainjava)
- [3. Ejercicio Facade — Lavadora](#3-ejercicio-facade--lavadora)
  - [3.1 Diagrama UML referencial (lavadora)](#31-diagrama-uml-referencial-lavadora)
  - [3.2 Estructura del proyecto LAVADORA](#32-estructura-del-proyecto-lavadora)
  - [3.3 Pestaña Lavado.java](#33-pestaña-lavadojava)
  - [3.4 Pestaña Enjuague.java](#34-pestaña-enjuaguejava)
  - [3.5 Pestaña Centrifugado.java](#35-pestaña-centrifugadojava)
  - [3.6 Pestaña FacadeLavadora.java](#36-pestaña-facadelavadorajava)
  - [3.7 Pestaña Principal.java](#37-pestaña-principaljava)

---

## 1. Ejercicio Adapter — Integración Sistema Cliente Antiguo y Nuevo

**Ejercicio**: Una empresa cuenta con un sistema antiguo que registra la información de sus clientes en un formato plano de texto, pero la nueva aplicación de pedidos requiere los datos en un formato estructurado (JSON); para integrar ambos sistemas sin modificar el código legado se implementa el patrón **Adapter**, donde el sistema antiguo actúa como **Adaptee**, la interfaz define el **Target**, el adaptador transforma los datos al formato requerido y el cliente demuestra cómo la aplicación antigua y la nueva reciben la información en diferentes representaciones.

### 1.1 Estructura del proyecto Adapter

```
Proyecto: Adapter/
└── Package: Clientepedido/
        ├── SistemaClientesAntiguo.java   (Adaptee)
        ├── ClienteTarget.java            (Target)
        ├── ClienteAdapter.java           (Adapter)
        └── ClienteDemo.java              (Client - main)
```

### 1.2 Pestaña SistemaClientesAntiguo.java

```java
package Clientepedido;

/**
 * Sistema antiguo que devuelve datos de clientes en formato simple.
 */
public class SistemaClientesAntiguo {
    public String obtenerCliente() {
        return "Cliente: Juan Pérez, Dirección: Lima, Teléfono: 999999999";
    }
}
```

### 1.3 Pestaña ClienteTarget.java

```java
package Clientepedido;

public interface ClienteTarget {
    String getDatosCliente();
}
```

### 1.4 Pestaña ClienteAdapter.java

```java
package Clientepedido;

public class ClienteAdapter implements ClienteTarget {
    private SistemaClientesAntiguo sistemaAntiguo;

    public ClienteAdapter(SistemaClientesAntiguo sistemaAntiguo) {
        this.sistemaAntiguo = sistemaAntiguo;
    }

    @Override
    public String getDatosCliente() {
        String datosAntiguos = sistemaAntiguo.obtenerCliente();
        // Transformación simulada a formato JSON
        String datosNuevos = "{ \"nombre\": \"Juan Pérez\", \"direccion\": \"Lima\", \"telefono\": \"999999999\" }";
        return datosNuevos;
    }
}
```

### 1.5 Pestaña ClienteDemo.java

```java
package Clientepedido;

public class ClienteDemo {
    public static void main(String[] args) {
        SistemaClientesAntiguo sistemaAntiguo = new SistemaClientesAntiguo();
        ClienteTarget adaptador = new ClienteAdapter(sistemaAntiguo);

        System.out.println("=== EJECUCIÓN DEL PROGRAMA ===");
        System.out.println("Datos recibidos por la aplicación ANTIGUA:");
        System.out.println(sistemaAntiguo.obtenerCliente());

        System.out.println("\nDatos recibidos por la aplicación NUEVA:");
        System.out.println(adaptador.getDatosCliente());
    }
}
```

---

## 2. Ejercicio Facade — Sistema Hospital

### 2.1 Estructura del proyecto Hospital

```
Proyecto: Hospital
   |
Package: Facade
   |----> SistemaEMR
   |----> SistemaLaboratorio
   |----> SistemaFacturacion
   |----> SistemaFacadeHospital
   |----> Main
```

### 2.2 Pestaña SistemaEMR.java

```java
package com.utp.hospitalfacade;

public class SistemaEMR {
    public String obtenerHistoriaClinica(String paciente) {
        return "Historia clínica de " + paciente + ": [diagnóstico, tratamientos previos]";
    }
}
```

### 2.3 Pestaña SistemaLaboratorio.java

```java
package com.utp.hospitalfacade;

public class SistemaLaboratorio {
    public String obtenerResultados(String paciente) {
        return "Resultados de laboratorio de " + paciente + ": [hemograma, glucosa, colesterol]";
    }
}
```

### 2.4 Pestaña SistemaFacturacion.java

[NOTA: En el PDF original esta pestaña aparece etiquetada erróneamente como "SistemaLaboratorio.java" pero el contenido corresponde a la clase `SistemaFacturacion`. Se preserva el código tal cual.]

```java
package com.utp.hospitalfacade;

public class SistemaFacturacion {
    public String obtenerEstadoCuenta(String paciente) {
        return "Estado de facturación de " + paciente + ": [saldo pendiente, pagos realizados]";
    }
}
```

### 2.5 Pestaña FacadeHospital.java

```java
package com.utp.hospitalfacade;

public class FacadeHospital {
    private SistemaEMR emr;
    private SistemaLaboratorio laboratorio;
    private SistemaFacturacion facturacion;

    public FacadeHospital() {
        emr = new SistemaEMR();
        laboratorio = new SistemaLaboratorio();
        facturacion = new SistemaFacturacion();
    }

    public void mostrarInformacionCompleta(String paciente) {
        System.out.println("=== Información completa del paciente ===");
        System.out.println(emr.obtenerHistoriaClinica(paciente));
        System.out.println(laboratorio.obtenerResultados(paciente));
        System.out.println(facturacion.obtenerEstadoCuenta(paciente));
        System.out.println("==========================================");
    }
}
```

### 2.6 Pestaña Main.java

```java
package com.utp.hospitalfacade;

public class Main {
    public static void main(String[] args) {
        FacadeHospital fachada = new FacadeHospital();
        fachada.mostrarInformacionCompleta("Juan Pérez");
    }
}
```

---

## 3. Ejercicio Facade — Lavadora

### 3.1 Diagrama UML referencial (lavadora)

[FIGURA: captura insertada de las diapositivas de clase "Patrones estructural – Facade - ejemplo" con logo UTP. Contiene el siguiente texto introductorio y un diagrama UML.]

> Supongamos que tenemos una lavadora que puede lavar, enjuagar y centrifugar la ropa, pero que realiza cada tarea por separado. Debemos abstraer las complejidades de los subsistemas porque el sistema en su conjunto es bastante complejo. Necesitamos un sistema que pueda automatizar toda la tarea sin nuestra interferencia.

[DIAGRAMA UML:

- **Client** (clase) — flecha hacia **WashingMachine**.
- **WashingMachine** (clase): métodos `+ Startwashing()`, `+method1()`.
- Tres subclases (con flechas de herencia hacia WashingMachine):
  - **Washing** — método `+ wash()`, `+methods-processing()`
  - **Rinsing** — método `+ rinse()`, `+methods-processing()`
  - **Spinning** — método `+ spin()`, `+methods-processing()`]

### 3.2 Estructura del proyecto LAVADORA

```
Proyecto : LAVADORA/
└── Package : FACADE/
        ├── Lavado.java
        ├── Enjuague.java
        ├── Centrifugado.java
        ├── FacadeLavadora.java
        └── Principal.java
```

### 3.3 Pestaña Lavado.java

```java
package FACADE;

/**
 * Subsistema de lavado.
 * Encargado de realizar la acción de lavar la ropa.
 */
public class Lavado {
    public void lavar() {
        System.out.println("Lavando la ropa con detergente...");
    }
}
```

### 3.4 Pestaña Enjuague.java

```java
package FACADE;

/**
 * Subsistema de enjuague.
 * Encargado de enjuagar la ropa con agua limpia.
 */
public class Enjuague {
    public void enjuagar() {
        System.out.println("Enjuagando la ropa con agua limpia...");
    }
}
```

### 3.5 Pestaña Centrifugado.java

```java
package FACADE;

/**
 * Subsistema de centrifugado.
 * Encargado de quitar el exceso de agua de la ropa.
 */
public class Centrifugado {
    public void centrifugar() {
        System.out.println("Centrifugando la ropa para quitar el exceso de agua...");
    }
}
```

### 3.6 Pestaña FacadeLavadora.java

```java
package FACADE;

/**
 * Clase Facade que simplifica el uso de la lavadora.
 * Permite al cliente ejecutar un ciclo completo sin preocuparse
 * por los detalles internos de cada subsistema.
 */
public class FacadeLavadora {
    private Lavado lavado;
    private Enjuague enjuague;
    private Centrifugado centrifugado;

    public FacadeLavadora() {
        lavado = new Lavado();
        enjuague = new Enjuague();
        centrifugado = new Centrifugado();
    }

    /**
     * Método que automatiza todo el proceso de lavado.
     */
    public void iniciarLavadoCompleto() {
        System.out.println("=== Iniciando ciclo completo de lavado ===");
        lavado.lavar();
        enjuague.enjuagar();
        centrifugado.centrifugar();
        System.out.println("=== Ciclo de lavado terminado ===");
    }
}
```

### 3.7 Pestaña Principal.java

```java
package FACADE;

/**
 * Clase principal (Cliente).
 * Demuestra el uso del patrón Facade en una lavadora.
 */
public class Principal {
    public static void main(String[] args) {
        FacadeLavadora lavadora = new FacadeLavadora();
        lavadora.iniciarLavadoCompleto();
    }
}
```

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                  |
| ---------- | -------- | ------------------------------------------------------------------------------ |
| Figuras    | 1        | Captura insertada del diagrama UML de la lavadora desde las diapositivas       |
| Tablas     | 0        | —                                                                              |
| Fórmulas   | 0        | —                                                                              |
| Código     | 13       | Clases Java: Adapter (4) + Hospital Facade (5) + Lavadora Facade (5)           |
| Diagramas  | 1        | UML WashingMachine con tres subclases (Washing, Rinsing, Spinning)             |
| Ejercicios | 3        | Adapter (cliente JSON), Facade (hospital), Facade (lavadora)                   |

## Notas de la transcripción

- En la sección 2.4, el PDF original etiqueta erróneamente la pestaña como "SistemaLaboratorio.java" aunque el contenido define la clase `SistemaFacturacion`. Se conservó el código tal como aparece y se añadió una nota aclaratoria.
- La estructura del proyecto Adapter en el PDF se presenta como árbol ASCII; se reproduce el mismo árbol en bloque de código.
- Los comentarios Javadoc `/** ... */` se preservan con su formato original.
