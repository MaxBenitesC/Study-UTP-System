---
universidad: UTP
curso: Diseño de Patrones
tema: Ejercicios Propuestos y Resueltos — Decorator y Composite
semana: 9
sesion: 09
tipo_documento: Guía de ejercicios (Word exportado a PDF)
paginas: 3
fuente_pdf: Ejercicios_Propuestos.pdf
autor_pdf: jaime de la torre
---

# DISEÑO DE PATRONES — SEMANA 09

## Ejercicios Propuestos y Resueltos — Decorator y Composite

## Tabla de Contenidos

- [1. Decorator — Sistema de notificaciones](#1-decorator--sistema-de-notificaciones)
  - [1.1 Enunciado](#11-enunciado)
  - [1.2 Interfaz Notificador](#12-interfaz-notificador)
  - [1.3 Clase concreta NotificadorEmail](#13-clase-concreta-notificadoremail)
  - [1.4 Clase abstracta NotificadorDecorator](#14-clase-abstracta-notificadordecorator)
  - [1.5 Decorador concreto NotificadorSMS](#15-decorador-concreto-notificadorsms)
  - [1.6 Decorador concreto NotificadorWhatsApp](#16-decorador-concreto-notificadorwhatsapp)
  - [1.7 Clase Main](#17-clase-main)
- [2. Composite — Menú jerárquico de restaurante](#2-composite--menú-jerárquico-de-restaurante)
  - [2.1 Enunciado](#21-enunciado)
  - [2.2 Importaciones e interfaz MenuComponent](#22-importaciones-e-interfaz-menucomponent)
  - [2.3 Clase hoja Plato](#23-clase-hoja-plato)
  - [2.4 Clase compuesta Menu](#24-clase-compuesta-menu)
  - [2.5 Clase Main](#25-clase-main)

---

## 1. Decorator — Sistema de notificaciones

### 1.1 Enunciado

> Diseñe un sistema de notificaciones donde la clase base envíe mensajes por correo electrónico y, mediante el uso del patrón Decorator, se puedan añadir dinámicamente nuevas funcionalidades como el envío de SMS y WhatsApp, de modo que el mismo mensaje pueda transmitirse por múltiples canales sin modificar el código original de la clase base.

### 1.2 Interfaz Notificador

```java
interface Notificador
{
    void enviar(String mensaje);
}
```

### 1.3 Clase concreta NotificadorEmail

```java
class NotificadorEmail implements Notificador
{
    public void enviar(String mensaje)
    {
        System.out.println("Enviando correo: " + mensaje);
    }
}
```

### 1.4 Clase abstracta NotificadorDecorator

```java
class NotificadorDecorator implements Notificador
{
    protected Notificador notificador;

    public NotificadorDecorator(Notificador notificador)
    {
        this.notificador = notificador;
    }

    public void enviar(String mensaje)
    {
        notificador.enviar(mensaje); // delega la acción
    }
}
```

### 1.5 Decorador concreto NotificadorSMS

```java
class NotificadorSMS extends NotificadorDecorator
{
    public NotificadorSMS(Notificador notificador)
    {
        super(notificador);
    }

    public void enviar(String mensaje)
    {
        super.enviar(mensaje);
        System.out.println("Enviando SMS: " + mensaje);
    }
}
```

### 1.6 Decorador concreto NotificadorWhatsApp

```java
class NotificadorWhatsApp extends NotificadorDecorator
{
    public NotificadorWhatsApp(Notificador notificador)
    {
        super(notificador);
    }
    public void enviar(String mensaje)
    {
        super.enviar(mensaje);
        System.out.println("Enviando WhatsApp: " + mensaje);
    }
}
```

### 1.7 Clase Main

```java
public class Main
{
    public static void main(String[] args)
    {
        Notificador notificador = new NotificadorWhatsApp(new NotificadorSMS(
                                    new NotificadorEmail()));
        notificador.enviar("Reunión a las 5 PM");
    }
}
```

---

## 2. Composite — Menú jerárquico de restaurante

### 2.1 Enunciado

> El sistema debe permitir representar un menú jerárquico de restaurante donde tanto platos individuales como menús completos se traten de manera uniforme, definiendo una interfaz común llamada MenuComponent que declare la operación de mostrar, implementando clases hoja (Plato) que representan cada plato y muestran su nombre, y clases compuestas (Menu) que almacenan y gestionan una colección de componentes delegando la operación en cada hijo y añadiendo la lógica de mostrar el título del menú, de modo que el cliente pueda invocar la operación sin distinguir si trabaja con un plato individual o con un menú completo.

### 2.2 Importaciones e interfaz MenuComponent

```java
import java.util.ArrayList;
import java.util.List;

interface MenuComponent
{
    void mostrar();
}
```

### 2.3 Clase hoja Plato

```java
class Plato implements MenuComponent
{
    private String nombre;

    public Plato(String nombre)
    {
        this.nombre = nombre;
    }

    public void mostrar()
    {
        System.out.println("Plato: " + nombre);
    }
}
```

### 2.4 Clase compuesta Menu

```java
class Menu implements MenuComponent
{
    private String nombre;
    private List<MenuComponent> items = new ArrayList<>();
    public Menu(String nombre)
    {
        this.nombre = nombre;
    }
    public void add(MenuComponent item)
    {
        items.add(item);
    }
    public void remove(MenuComponent item)
    {
        items.remove(item);
    }
    public void mostrar()
    {
        System.out.println("Menú: " + nombre);
        for (MenuComponent item : items)
        {
            item.mostrar();
        }
    }
}
```

### 2.5 Clase Main

```java
public class Main
{
    public static void main(String[] args)
    {
        Plato sopa = new Plato("Sopa de verduras");
        Plato pasta = new Plato("Pasta al pesto");
        Plato postre = new Plato("Tiramisú");
        Menu menuPrincipal = new Menu("Menú del día");
        menuPrincipal.add(sopa);
        menuPrincipal.add(pasta);
        menuPrincipal.add(postre);
        menuPrincipal.mostrar();
    }
}
```

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                  |
| ---------- | -------- | ------------------------------------------------------------------------------ |
| Figuras    | 0        | —                                                                              |
| Tablas     | 0        | —                                                                              |
| Fórmulas   | 0        | —                                                                              |
| Código     | 11       | Notificador (Decorator): 5 clases + Main; MenuComponent (Composite): 3 clases + Main |
| Diagramas  | 0        | —                                                                              |
| Ejercicios | 2        | Decorator (notificaciones multicanal) + Composite (menú jerárquico restaurante) |
