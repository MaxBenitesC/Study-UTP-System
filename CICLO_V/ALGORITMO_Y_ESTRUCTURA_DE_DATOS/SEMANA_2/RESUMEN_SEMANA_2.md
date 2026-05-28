# Resumen - SEMANA 2: Arreglos y Operaciones con Arreglos

## 📚 Contenido de la Semana

Esta semana abarca el estudio de **arreglos lineales** y las **operaciones fundamentales** que se realizan sobre ellos en Java.

---

## 1. ARREGLOS LINEALES (Arreglo Unidimensional)

### Definición
Un **arreglo** es un conjunto de variables del mismo tipo almacenadas en memoria bajo un mismo nombre. En Java, los arreglos pueden tener una o más dimensiones, siendo el arreglo unidimensional el más común. Se utilizan para agrupar variables relacionadas de forma práctica.

### Tipos de Arreglos
- **Arreglo lineal** (unidimensional / vector)
- **Arreglo bidimensional** (matriz)
- **Arreglo multidimensional** (3 o más dimensiones)

### Sintaxis de Declaración
```java
<tipo> <nom_var>[] = new <tipo>[<tamaño>];
```

**Ejemplo:**
```java
int Beta[] = new int[10];
```

### Representación Gráfica
Un arreglo se representa como una serie de celdas contiguas, cada una con un índice (comenzando en 0):

```
Índice:  0    1    2    3    4    5    6    7    8    9
Valor:  34   12   -5   90   18   33   11   46   22   98
```

---

## 2. OPERACIONES CON ARREGLOS

### 2.1 Asignación (Directa)
Asignar valores individuales a cada posición del arreglo:

```java
Beta[0] = 34;
Beta[1] = 12;
Beta[9] = 98;
```

### 2.2 Asignación Aleatoria
Llenar el arreglo con valores aleatorios usando `Math.random()`:

```java
for(i = 0; i < 10; i++) {
    Beta[i] = (int)(Math.random() * 100);
}
```

### 2.3 Lectura (Entrada de Datos)
Leer valores del usuario y asignarlos al arreglo:

```java
for(i = 0; i < 10; i++) {
    Beta[i] = Integer.parseInt(JOptionPane.showInputDialog("Ingrese un numero"));
}
```

### 2.4 Escritura (Salida de Datos)
Imprimir los valores del arreglo:

```java
for(i = 0; i < 10; i++) {
    System.out.println(Beta[i]);
}
```

---

## 3. MÉTODOS AVANZADOS DE OPERACIÓN

### 3.1 Inserción
- **Descripción:** Insertar un elemento en una posición específica del arreglo, desplazando los elementos a la derecha.
- **Consideraciones:**
  - Si el arreglo está lleno, no se pueden desplazar elementos
  - Si estando lleno se intenta insertar, se elimina el último elemento

### 3.2 Actualización
- **Descripción:** Cambiar el valor de un elemento en una posición del arreglo.
- **Dos tipos de procedimientos:**
  1. Actualizar por índice conocido
  2. Buscar un valor existente y reemplazarlo por otro

### 3.3 Eliminación
- **Descripción:** Eliminación lógica de elementos (no disminuye el tamaño del arreglo)
- **Procedimientos:**
  - Eliminar por índice
  - Buscar un valor y eliminarlo (requiere búsqueda previa)
  - Opcionalmente desplazar elementos de derecha a izquierda
  - O dejar espacios vacíos sin desplazar

### 3.4 Recorrido
- **Descripción:** Recorrer todos los elementos del arreglo usando un proceso repetitivo (bucle)
- Generalmente realizado con la estructura `for`

### 3.5 Copia de Arreglos
- **Descripción:** Copiar los valores de un arreglo a otro
- **Métodos:**
  1. Mediante recorrido elemento por elemento
  2. Usando el operador de asignación `=`

---

## 4. OBJETIVOS DE APRENDIZAJE

Al finalizar la semana 2, los estudiantes deben ser capaces de:

✓ Utilizar adecuadamente arreglos de tipo lineal en la solución de ejercicios prácticos

✓ Comprender y aplicar operaciones con arreglos: inserción, actualización, eliminación, recorrido y copia

✓ Desarrollar soluciones algorítmicas en Java que utilicen estas operaciones

---

## 5. EJEMPLOS PRÁCTICOS

### Ejemplo 1: Contar Números Pares e Impares
```
Enunciado: Diseñar un programa que ingrese N números enteros en un arreglo lineal 
y calcule la cantidad de números pares e impares.
```

### Ejemplo 2: Cálculo de Ingresos de Taxista
```
Enunciado: Un taxista almacena en un vector de 30 posiciones el número de carreras 
por día del mes. Calcular:
- Total de dinero ganado (con tarifa variable según cantidad de carreras)
- Promedio de carreras por día
```

---

## 6. CONCEPTOS CLAVE

| Concepto | Descripción |
|----------|-------------|
| **Índice** | Posición del elemento en el arreglo (comienza en 0) |
| **Elemento** | Cada uno de los valores almacenados en el arreglo |
| **Tamaño** | Número total de posiciones del arreglo |
| **Declaración** | Crear la estructura del arreglo |
| **Inicialización** | Asignar valores iniciales |
| **Búsqueda** | Localizar un valor en el arreglo |

---

## 7. NOTAS IMPORTANTES

- Los arreglos en Java son **de tamaño fijo** (se definen al declarar)
- La indexación comienza en **0** (no en 1)
- El atributo **`.length`** proporciona el tamaño del arreglo
- Las operaciones de inserción y eliminación requieren desplazamiento de elementos
- La eliminación es **lógica** (no reduce el tamaño físico del arreglo)

---

## 📖 Fuentes
- Material de UTP - Algoritmos y Estructuras de Datos
- Semana 2: Arreglos Lineales y Operaciones Unidimensionales
