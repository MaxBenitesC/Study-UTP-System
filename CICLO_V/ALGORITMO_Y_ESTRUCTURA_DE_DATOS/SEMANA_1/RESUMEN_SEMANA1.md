# Resumen - Semana 1: Algoritmos y Estructuras de Datos
**Universidad Tecnológica del Perú (UTP) - Ciclo 5**

---

## Logro de Aprendizaje

Al finalizar la sesión, el estudiante:
- Utiliza adecuadamente las **estructuras de datos** para desarrollar soluciones algorítmicas en Java.
- Identifica los tipos de estructura de datos y su importancia en la programación.
- Aplica los conceptos en la solución de ejercicios prácticos.

---

## 1. ¿Qué es una Estructura de Datos?

Una **estructura de datos** es:
- Una **agregación de tipos de datos compuestos y atómicos** en un conjunto con relaciones bien definidas.
- Un conjunto de reglas que contienen los datos juntos.
- Una forma de **organizar datos en la computadora** de tal manera que permita realizar operaciones con ellos de forma **muy eficiente**.
- Un **modelo matemático o lógico** para organizar los datos.

Las estructuras de datos pueden estar **anidadas** (estructuradas): una estructura puede contener a otras.

### ¿Por qué son importantes?

| Beneficio | Descripción |
|-----------|-------------|
| Almacenamiento uniforme | Permite definir una forma de almacenar valores con características iguales |
| Operaciones homogéneas | Permite definir operaciones con datos semejantes |
| Herramientas de solución | Proveen un conjunto de herramientas para resolver ciertos tipos de problemas |
| Software eficiente | Permiten desarrollar software más eficiente optimizando recursos |
| Eficiencia en algoritmos | Mejoran el rendimiento de los algoritmos |
| Mejor uso de memoria | Optimizan el espacio en RAM |
| Resolución de problemas complejos | Facilitan problemas que serían difíciles de otra manera |

---

## 2. Cómo Almacenan Datos las Computadoras

- **Memoria principal (RAM):** donde se almacenan los datos durante la ejecución.
- **Direcciones de memoria:** cada dato tiene una ubicación específica en RAM.
- **Representación binaria:** todos los datos se almacenan en forma de 0s y 1s.
- Las **variables** reservan su espacio en memoria según el tipo de dato.
- Los **arreglos** ocupan bloques contiguos de memoria.
- Las **referencias y punteros** apuntan a la dirección de memoria donde está el dato.

---

## 3. Clasificación de las Estructuras de Datos

```
Estructuras de Datos
├── Estáticas
│   ├── Primitivos: Integer, Float, Character, Boolean
│   └── Compuestos
│       ├── String
│       └── Arreglos (arrays)
└── Dinámicas
    ├── Lineales
    │   ├── Listas enlazadas
    │   ├── Pilas (Stacks)
    │   └── Colas (Queues)
    └── No Lineales
        ├── Árboles
        └── Grafos
```

### 3.1 Estructuras Estáticas

- **Tamaño fijo** desde su creación.
- **Acceso rápido** por índice.
- Simples y rápidas.
- Poco flexibles: no crecen automáticamente.

**Ejemplos:** arreglos de una dimensión, arreglos de dos dimensiones, información de un cheque, información de una dirección domiciliaria.

### 3.2 Estructuras Dinámicas Lineales

#### Listas Enlazadas
- Compuestas por **nodos con enlaces** entre sí.
- Inserción y eliminación eficientes.
- Pueden crecer dinámicamente.

#### Pilas (Stacks)
- Principio **LIFO**: *Last In, First Out* (último en entrar, primero en salir).
- Ejemplo de uso: deshacer cambios (Ctrl+Z).

#### Colas (Queues)
- Principio **FIFO**: *First In, First Out* (primero en entrar, primero en salir).
- Ejemplo de uso: gestión de procesos del sistema operativo.

### 3.3 Estructuras Dinámicas No Lineales

#### Árboles
- Relación **jerárquica** entre nodos (nodo padre e hijos).
- Ejemplo: sistemas de archivos de un sistema operativo.

#### Grafos
- Conjuntos de **nodos conectados** entre sí.
- Representan relaciones complejas entre elementos.
- Ejemplo: redes sociales.

---

## 4. Etapas para Seleccionar una Estructura de Datos

1. **Analizar el problema** para determinar las restricciones de recursos de cada solución posible.
2. **Determinar las operaciones básicas** (inserción, supresión, búsqueda) y sus restricciones de recursos.
3. **Seleccionar la estructura** que mejor cumpla los requerimientos.

> El proceso está centrado en los datos: primero se diseñan los datos y las operaciones, luego la representación, y finalmente la implementación.

---

## 5. Arreglos (Arrays)

### Definición

Un **arreglo** es una estructura de datos que contiene una **colección de valores del mismo tipo**, almacenados consecutivamente en memoria. Se referencia por un nombre común y se accede por medio de **índices**.

### Características

| Característica | Descripción |
|----------------|-------------|
| **Finita** | Tiene un límite; se debe determinar el máximo número de elementos |
| **Homogénea** | Todos los elementos son del mismo tipo de dato |
| **Ordenada** | Se puede identificar el primero, segundo, ..., hasta el último elemento |

### Partes de un Arreglo

- **Nombre:** identificador del arreglo.
- **Índice:** posición de cada elemento (empieza en `0`).
- **Elemento (componente):** valor almacenado en cada posición.
- **Tamaño:** cantidad total de elementos del arreglo.

```
Nombre: A
Índice:    [0]  [1]  [2]  [3]  [4]  [5]  [6]
Elementos:  10   50  145    2  456   20   -1
```

### Tipos de Arreglos

- **Unidimensionales** (vectores): una sola fila de elementos.
- **Bidimensionales** (matrices): filas y columnas.
- **Multidimensionales**: tres o más dimensiones.

### Fórmula para Dirección de Memoria

```
Di = B + [(i - li) * w]
```

Donde:
- `Di` = Dirección de inicio del elemento `i`
- `B` = Dirección de inicio del arreglo
- `i` = Índice del elemento
- `li` = Límite inferior del arreglo
- `w` = Número de bytes de cada elemento

**Ejemplo:** Arreglo A(0:10), elemento 4, 3 bytes por elemento, dirección base 100:
```
Dirección = 100 + (4 - 0) * 3 = 112
```

---

## 6. Operaciones con Arreglos en Java

### Declaración

```java
tipo[] nombreArray;
// o equivalentemente:
tipo nombreArray[];
```

### Declaración y Creación

```java
tipo[] nombreArray = new tipo[tamaño];
// o:
tipo nombreArray[] = new tipo[tamaño];

// Ejemplo con 7 elementos:
int[] numeros = new int[7];
```

### Acceso a un Elemento

```java
nombreArray[indice]   // leer
nombreArray[indice] = valor;  // escribir
```

### Recorrido con for-each

```java
for (int numero : numeros) {
    System.out.print(numero + " ");
}
```

---

## 7. Errores Comunes en Arreglos

| Error | Descripción |
|-------|-------------|
| Índice fuera de rango | Usar un índice mayor o igual al tamaño del arreglo |
| Error de uno menos | Olvidar que el primer elemento está en la posición `0` |
| Confundir índice con elemento | El índice es la posición, no el valor almacenado |
| Usar `=` para copiar | `arregloB = arregloA` no copia los valores, copia la referencia |
| Usar `==` para comparar | `arregloA == arregloB` compara referencias, no contenidos |

---

## 8. Ejemplos de Ejercicios Prácticos

### Promedio de horas trabajadas
Ingresar las horas trabajadas de 5 trabajadores y calcular el promedio usando un arreglo.

### Arreglos Paralelos
Ingresar nombre y sueldo de 10 trabajadores en dos arreglos paralelos y mostrar el nombre del que tiene mayor sueldo.

### Dígito Verificador del DNI (Módulo 23)
Calcular el carácter verificador dividiendo el DNI entre 23 y buscando la letra equivalente en un arreglo de 23 letras:

```
Índice: 0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22
Letra:  T  R  W  A  G  M  Y  F  P  D  X  B  N  J  Z  S  Q  V  H  L  C  K  E
```

### Notas con valores aleatorios
Llenar un arreglo de 40 alumnos con notas aleatorias (0-20) y listar los aprobados (nota ≥ 12).

### Valores aleatorios sin repetición
Llenar un arreglo de 10 elementos con números aleatorios entre 1 y 15 sin que se repitan.

---

## 9. Resumen Final

| Concepto | Punto clave |
|----------|-------------|
| Estructura de datos | Forma organizada de almacenar y operar datos eficientemente |
| Estáticas | Tamaño fijo, acceso por índice (ej: arreglos) |
| Dinámicas lineales | Crecen dinámicamente: listas, pilas (LIFO), colas (FIFO) |
| Dinámicas no lineales | Relaciones complejas: árboles (jerárquicos), grafos (redes) |
| Arreglo | Colección finita, homogénea y ordenada, acceso por índice desde `0` |
| Recorrido | Se recorren con estructuras repetitivas (`for`, `while`, `for-each`) |
