# Análisis Forense: Ejercicios de Clase (Semana 6)

Este documento contiene el análisis detallado de los dos proyectos desarrollados en clase (extraídos de `Sesion6.rar` y `S06_s1-PatronSingletonyPrototype.zip`), los cuales abordan la implementación práctica de los patrones creacionales **Singleton** y **Prototype**.

El objetivo de esta guía es que puedas repasar los conceptos técnicos con claridad y comprender la verdadera finalidad de cada patrón a través de explicaciones sencillas ("Entendimiento Humano").

---

## Parte 1: Proyecto `Sesion6.rar` (Implementación Clásica)

En este ejercicio, el profesor se enfocó en mostrar la forma más pura y básica de implementar ambos patrones.

### 1. Patrón Singleton
**Código analizado:** `Singleton.java` y `SingletonMain.java`

* **Implementación:** Se utiliza la técnica de "instanciación perezosa" (Lazy Initialization). Se declara un constructor privado, una variable estática para guardar la única instancia y un método `getInstance()` que verifica si la instancia es nula antes de crearla.
* **Qué quiso enseñar el profesor:** El objetivo aquí es demostrar cómo restringir la creación de un objeto. Al ejecutar el `main`, si llamas 3 veces a `Singleton.getInstance()`, la dirección de memoria impresa en consola será **exactamente la misma**. Esto prueba que no importa cuántas veces lo pidas, el sistema solo te da un único objeto.

### 2. Patrón Prototype
**Código analizado:** `Prototype.java` (Interface), `ConcretePrototype.java` y `SubclassPrototype.java`

* **Implementación:** Se define una interfaz `Prototype` con el método `clone()`. Luego, la clase `ConcretePrototype` (que representa a una Persona con datos como nombre, apellido, correo, dni, celular) implementa este método devolviendo un `new ConcretePrototype(...)` con sus mismos datos. La clase hija `SubclassPrototype` hace lo mismo llamando a `super()` e inyectando atributos adicionales (sexo, edad, grupo sanguíneo).
* **Qué quiso enseñar el profesor:** Mostrar cómo funciona la clonación en cadena y el uso de la herencia dentro del patrón Prototype. Al usar `clone()`, se crea un nuevo objeto en memoria (distinto al original) pero con los mismos valores exactos, evitando tener que setear campo por campo manualmente.

### 2.1 Desglose del `PrototypeMain` y la "bulla" del `sout`

Para entender completamente cómo funciona la clase hija (`SubclassPrototype`) en el programa principal, analicemos las líneas clave del `main`:

```java
// 1. CREACIÓN DEL OBJETO ORIGINAL (SUBCLASE)
SubclassPrototype subclassPrototype = new SubclassPrototype("nombre2", "apellido2", "correo2", "dni2", "celular2", "sexo2", 24, "O+");
```
* **Los Parámetros:** Aquí estamos llamando al constructor de la hija. Los primeros 5 parámetros (`"nombre2"` hasta `"celular2"`) son los que la clase hija le pasará automáticamente a la clase padre usando `super(...)`. Los últimos 3 (`"sexo2", 24, "O+"`) son exclusivos de la hija y se guardan en sus propios atributos.

```java
// 2. CLONACIÓN CON CASTING
SubclassPrototype subclassPrototype1 = (SubclassPrototype) subclassPrototype.clone();
```
* **¿Por qué el `(SubclassPrototype)`?** El método `clone()` (definido en la interfaz) está obligado a devolver un tipo general `Prototype`. Sin embargo, la variable donde lo queremos guardar (`subclassPrototype1`) es de un tipo más específico (`SubclassPrototype`). El `(SubclassPrototype)` es un **casting**; le dice a Java: *"Confía en mí, yo sé que el objeto que sale de este clon es exactamente un SubclassPrototype, así que fuérzalo a entrar en esta variable"*.

```java
// 3. IMPRESIÓN EN CONSOLA (La "bulla")
System.out.println("" + subclassPrototype);
System.out.println("" + subclassPrototype1);
```
* **¿Por qué el texto impreso se ve raro?** Cuando haces un `println` de un objeto, Java llama automáticamente al método `toString()`. 
* Si miras el `toString()` de la hija, dice: `return "SubclassPrototype{" + super.toString() + "sexo=" ...`
* El `super.toString()` trae el texto tal como lo armó el padre: `"ConcretePrototype{nombre=nombre2, apellido=apellido2...}"`.
* Al concatenarlo todo, en la consola sale una mezcla anidada que hace "bulla" visualmente: 
  `SubclassPrototype{ConcretePrototype{nombre=nombre2, apellido=apellido2, correo=correo2, dni=dni2, celular=celular2}sexo=sexo2, edad=24, grupoSanguineo=O+}`.
* Aunque se vea feo, **técnicamente demuestra el éxito del patrón**: los datos heredados del padre y los propios del hijo se clonaron perfectamente.

---

## Parte 2: Proyecto `S06_s1-PatronSingletonyPrototype.zip` (Implementación Avanzada)

En este segundo archivo, el profesor introduce variantes técnicas muy interesantes que complementan la teoría.

### 1. Singleton con Destrucción (Reset)
**Código analizado:** `Singleton.java` y `Principal.java`

* **Implementación:** Además del clásico `getInstance()`, el profesor agregó un método `close()` que hace `instance = null`.
* **Qué quiso enseñar el profesor:** Quiso demostrar la gestión del ciclo de vida del Singleton. En la clase `Principal`, se crea el Singleton, se usa y luego se llama a `close()`. Al volver a pedir la instancia, el sistema se ve obligado a crear un **objeto nuevo** en memoria. Esto es útil en conexiones a bases de datos: usas la conexión única, la cierras, y si alguien más necesita conectarse, se abre una conexión completamente nueva.

### 2. Prototype con "Constructores de Copia"
**Código analizado:** `ConcretePrototype.java`, `SubClassPrototype.java` y `Principal.java`

* **Implementación:** A diferencia del primer ejercicio donde `clone()` pasaba los parámetros uno por uno, aquí el profesor utiliza **Constructores de Copia** (Ej. `public ConcretePrototype(ConcretePrototype prototype)`). El método `clone()` simplemente hace `return new ConcretePrototype(this);`.
* **Qué quiso enseñar el profesor:** Esta es una forma mucho más limpia, profesional y escalable de clonar objetos en Java. Si en el futuro agregas 10 atributos nuevos a la clase, no tienes que modificar la firma del método `clone()`, solo actualizas el constructor de copia. 

---

## 🧠 Apartado de Entendimiento Humano

Si los conceptos técnicos se sienten pesados, aquí tienes la traducción al mundo real para que nunca se te olviden:

### 👑 Singleton: "El Presidente"
Imagina que en un país (tu programa) solo puede haber **un** Presidente. 
- Si el Ministerio de Salud pide hablar con el Presidente, va a hablar con *Juan Pérez*. 
- Si el Ministerio de Educación pide hablar con el Presidente, también hablará con *Juan Pérez*. 
- Nadie puede usar el operador `new Presidente()`, porque las leyes (el constructor privado) lo prohíben. Todos deben usar `Presidente.getInstance()`.

* **El detalle del Profesor (El método `close()`):** ¿Qué pasa si el presidente termina su mandato o renuncia? El método `close()` lo destituye (`instance = null`). La próxima vez que alguien pida al presidente con `getInstance()`, asumirá el cargo una persona completamente nueva.

### 🐑 Prototype: "La Oveja Dolly"
Imagina que estás diseñando un videojuego y necesitas crear 1000 enemigos "Orco" que tienen exactamente las mismas estadísticas (100 Vida, 50 Fuerza, Espada de Hierro, Armadura Negra).
- **Sin Prototype:** Tendrías que crear cada orco uno por uno y asignarle las armas y la vida de forma manual 1000 veces. ¡Muy agotador para el procesador!
- **Con Prototype:** Creas **un solo Orco** perfecto (tu prototipo). Luego, seleccionas a ese orco y le das al botón de "Duplicar" (`clone()`). Magia, tienes copias idénticas instantáneas. 

* **El detalle del Profesor (El constructor de copia):** En lugar de hacer una máquina de clonación que copie pierna por pierna y brazo por brazo (como en el primer proyecto), el profesor enseñó a usar una máquina que escanea todo el cuerpo a la vez y saca la copia exacta de golpe (Constructor de copia).

---

## 💻 Ejercicio Rápido (Versión Humana) para Repasar

Intenta responder mentalmente a esta situación:

**Problema:** Tienes un sistema que controla las impresiones de toda tu universidad. Si hay 50 computadoras mandando a imprimir, no quieres que se abran 50 programas gestores de impresión al mismo tiempo porque colapsaría la red. ¿Qué patrón usas?

> **Respuesta:** ¡El patrón Singleton! Así garantizas que todas las 50 computadoras envíen sus archivos a una única y exclusiva instancia del `GestorDeImpresion`.

**Problema:** Ahora, en ese mismo sistema, los alumnos envían "Documentos". Un alumno necesita imprimir el mismo documento 5 veces pero con distinto nombre de portada. ¿Qué patrón usas para no tener que cargar el documento pesado 5 veces desde cero?

> **Respuesta:** ¡El patrón Prototype! Tomas el `Documento` original y lo clonas 4 veces más. Ya tienes 5 objetos en memoria rapidísimo, y solo le cambias el atributo de la portada a las copias.