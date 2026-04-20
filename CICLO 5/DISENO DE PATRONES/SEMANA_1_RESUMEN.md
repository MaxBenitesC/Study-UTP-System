# SEMANA 1 - Principios de Diseño, SOLID y SRP

**Curso:** Diseño de Patrones | **Docente:** Mg. Iván Robles Fernández | **UTP**

---

## TEMA

- Introducción a principios de diseño
- Introducción a principios SOLID
- Principio de Responsabilidad Única (SRP)

---

## 1. PRINCIPIOS DE DISEÑO (OOP)

Son fundamentos clave que guían la creación de software **robusto, flexible y mantenible**.

| Principio | Descripción |
|-----------|-------------|
| **Encapsulamiento** | Ocultar detalles internos, exponer solo lo necesario |
| **Modularidad** | Dividir el sistema en módulos independientes |
| **Abstracción** | Simplificar complejidad, exponer solo lo esencial |
| **Herencia** | Nuevas clases heredan propiedades de clases existentes |
| **Polimorfismo** | Una interfaz se comporta diferente según el contexto |

---

## 2. PRINCIPIOS SOLID

Los principios **SOLID** son 5 reglas específicas para diseño orientado a objetos:

| Letra | Nombre | Descripción corta |
|-------|--------|-------------------|
| **S** | Single Responsibility Principle (SRP) | Una clase = una sola responsabilidad |
| **O** | Open/Closed Principle (OCP) | Abierto para extensión, cerrado para modificación |
| **L** | Liskov Substitution Principle (LSP) | Las subclases deben poder reemplazar a la clase base |
| **I** | Interface Segregation Principle (ISP) | No forzar interfaces que no se usan |
| **D** | Dependency Inversion Principle (DIP) | Depender de abstracciones, no de implementaciones |

> **Diferencia clave:** Los principios de diseño son **generales** (aplican a todo el ciclo). Los principios SOLID son **específicos** para resolver problemas de escalabilidad y flexibilidad.

---

## 3. PRINCIPIO DE RESPONSABILIDAD ÚNICA (SRP)

### Definición

> "Una clase debe tener una única razón para cambiar."

- Cada clase debe ser responsable de **una sola actividad** del sistema.
- Si una clase hace A y B, hay dos razones para cambiarla → viola SRP.

### ¿Por qué usarlo?

- Es más fácil **re-utilizar** partes del código
- Las clases grandes son más difíciles de leer y cambiar
- Resuelve el dilema del nombre de la clase (si no puedes nombrarla bien, probablemente hace demasiado)

### Importancia

- **Facilita el mantenimiento:** Clases pequeñas con responsabilidad clara
- **Mejora la reutilización:** Clases desacopladas de otras funcionalidades
- **Reduce el acoplamiento:** Menos dependencias entre clases
- **Aumenta la cohesión:** Métodos y atributos relacionados entre sí

---

## 4. EJEMPLOS DEL PDF (Clase)

### Ejemplo del PDF - Biblioteca

**Mal (viola SRP):**
```java
class Libro {
    private String titulo;
    private String autor;

    public void prestar() { /* lógica de préstamo */ }
    public void devolver() { /* lógica de devolución */ }
}
```
> Problema: `Libro` gestiona datos Y lógica de préstamo → dos razones para cambiar.

**Correcto (aplica SRP):**
```java
class Libro {
    private String titulo;
    private String autor;
    // Solo datos, sin lógica de negocio
}

class ServicioPrestamo {
    public void prestar(Libro libro) { /* lógica de préstamo */ }
    public void devolver(Libro libro) { /* lógica de devolución */ }
}
```

### Ejemplo del PDF - Pagos (OCP)

```java
abstract class Pago {
    public abstract void realizarPago();
}
class PagoConTarjeta extends Pago {
    @Override
    public void realizarPago() { /* lógica tarjeta */ }
}
class PagoConPayPal extends Pago {
    @Override
    public void realizarPago() { /* lógica PayPal */ }
}
// Para agregar criptomonedas NO se modifica el código existente, solo se agrega:
class PagoConCriptomoneda extends Pago {
    @Override
    public void realizarPago() { /* lógica cripto */ }
}
```

---

## 5. EJEMPLO DESARROLLADO EN LA UNIVERSIDAD (Código Java)

### Problema: `UsuarioManager` viola SRP

```java
// MAL - Una clase hace todo: datos + validación + persistencia
public class UsuarioManager {
    private Integer id;
    private String nombre;
    private String email;
    private List<UsuarioManager> usuarios = new ArrayList<>();

    // Lógica de validación (responsabilidad 1)
    public boolean validarEmail() {
        return email.contains("@") && email.contains(".");
    }

    // Lógica de base de datos (responsabilidad 2)
    public void guardarUsuario() {
        if (validarEmail()) {
            usuarios.add(this);
            System.out.println("Usuario Guardado " + this.nombre);
        } else {
            System.out.println("Error: Email no válido");
        }
    }

    public void listarUsuarios() {
        for (UsuarioManager usuario : usuarios) {
            System.out.println("ID:" + usuario.getId() + ", Nombre:" + usuario.getNombre());
        }
    }
}
```

### Solución Refactorizada (aplica SRP)

**Clase `Usuario` - solo datos:**
```java
public class Usuario {
    private Integer id;
    private String nombre;
    private String email;
    // Solo getters y setters
}
```

**Clase `UsuarioRepositorio` - solo persistencia:**
```java
public class UsuarioRepositorio {
    private List<Usuario> usuarios = new ArrayList<>();

    public void guardarUsuario(Usuario usuario) {
        usuarios.add(usuario);
        System.out.println("Usuario Guardado: " + usuario.getNombre());
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}
```

**Clase `UsuarioServicio` - solo lógica de negocio:**
```java
public class UsuarioServicio {
    private UsuarioRepositorio repositorio;

    public UsuarioServicio() {
        this.repositorio = new UsuarioRepositorio();
    }

    public boolean validarEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public void registrarUsuario(String nombre, String email) {
        if (validarEmail(email)) {
            Usuario usuario = new Usuario(nombre, email);
            repositorio.guardarUsuario(usuario);
        } else {
            System.out.println("Correo errado");
        }
    }

    public void listarUsuarios() {
        for (Usuario u : repositorio.obtenerUsuarios()) {
            System.out.println("Usuario: " + u.getNombre() + " Correo: " + u.getEmail());
        }
    }
}
```

### Diagrama de responsabilidades

```
UsuarioManager (MAL)          Refactorizado (BIEN)
┌─────────────────────┐       ┌──────────┐
│ datos               │  →    │ Usuario  │ (solo datos)
│ validarEmail()      │       └──────────┘
│ guardarUsuario()    │       ┌───────────────────┐
│ listarUsuarios()    │  →    │ UsuarioServicio   │ (validación + registro)
└─────────────────────┘       └───────────────────┘
                               ┌────────────────────┐
                          →    │ UsuarioRepositorio │ (persistencia)
                               └────────────────────┘
```

---

## 6. CONCLUSIONES CLAVE

- Los principios SOLID mejoran **cohesión** (clases enfocadas) y reducen **acoplamiento** (menos dependencias)
- SRP: si necesitas usar "y" para describir qué hace una clase → viola SRP
- Una clase bien diseñada tiene **una razón para existir y una razón para cambiar**
- Separar en: **Modelo** (datos), **Servicio** (lógica), **Repositorio** (persistencia)
