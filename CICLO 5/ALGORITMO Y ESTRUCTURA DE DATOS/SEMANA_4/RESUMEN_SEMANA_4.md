# Resumen: Arreglos Bidimensionales y Operaciones en Java

## 1. Conceptos Básicos
[cite_start]Un arreglo bidimensional (o matriz) es una estructura compuesta de filas y columnas que permite almacenar un conjunto de datos, evitando el uso excesivo de variables[cite: 561, 564]. 
* [cite_start]**Dimensiones**: Se conforman por filas (m) y columnas (n)[cite: 35]. 
* **Almacenamiento en Memoria**: En la memoria principal, los datos se guardan linealmente. [cite_start]La fórmula para obtener un índice lineal a partir de los índices de fila (i) y columna (j) es: $Indice(i,j) = fila\_inicial + numero\_de\_columnas \cdot i + j$[cite: 587].

## 2. Declaración y Uso en Java
Para manipular matrices en Java, se utilizan las siguientes estructuras y métodos:
* [cite_start]**Declaración con tamaño fijo**: `TipoDato[][] variable = new TipoDato[filas][columnas];`[cite: 634].
* [cite_start]**Declaración con inicialización directa**: `TipoDato[][] variable = {{a1, a2}, {b1, b2}};`[cite: 642].
* **Obtener dimensiones**: 
  * [cite_start]Número de filas: `variable.length`[cite: 649, 651].
  * [cite_start]Número de columnas: `variable[0].length`[cite: 653].
* [cite_start]**Acceso a elementos extremos**: El primer elemento es `variable[0][0]` y el último se obtiene con `variable[variable.length-1][variable[0].length-1]`[cite: 656, 658].

## 3. Clasificación de Matrices

### Matrices Cuadradas Poco Densas
[cite_start]Son matrices (n x n) donde la mayoría de sus entradas son ceros (alta proporción de ceros)[cite: 44, 591, 592]. [cite_start]Su correcta manipulación permite la optimización tanto del uso de memoria como del tiempo de ejecución[cite: 45].

#### 1. Matriz Triangular Inferior
* [cite_start]**Definición**: Los elementos por encima de la diagonal principal son cero, es decir, $a_{ij} = 0$ si $i < j$[cite: 55].
* [cite_start]**Usos**: Factorización LU y algoritmos de sustitución hacia adelante ($Lx=b$)[cite: 55, 111].

#### 2. Matriz Triangular Superior
* [cite_start]**Definición**: Los elementos por debajo de la diagonal principal son cero, es decir, $a_{ij} = 0$ si $i > j$[cite: 83, 84].
* [cite_start]**Usos**: Algoritmos de sustitución hacia atrás ($Ux=b$)[cite: 84, 123].

#### 3. Matriz Tridiagonal
* [cite_start]**Definición**: Solo la diagonal principal, la subdiagonal y la supradiagonal contienen elementos distintos de cero[cite: 136, 614]. [cite_start]Es decir, el valor absoluto de $i - j$ debe ser menor o igual a 1[cite: 616].
* [cite_start]**Almacenamiento**: Se optimiza guardando los valores en 3 vectores unidimensionales en lugar de una matriz completa[cite: 136, 137].

### Matrices Simétricas, Asimétricas y Antisimétricas
* [cite_start]**Matriz Simétrica**: Cumple que $A = A^T$, lo que significa que $a_{ij} = a_{ji}$ para todo i, j[cite: 173, 174, 620]. [cite_start]Permiten ahorrar memoria almacenando solo la mitad de la matriz[cite: 175].
* [cite_start]**Matriz Asimétrica**: Carece de simetría ($A \ne A^T$), por lo que representa relaciones dirigidas y no permite reducir el almacenamiento[cite: 195, 196].
* [cite_start]**Matriz Antisimétrica**: Cumple que el elemento $a_{ij}$ es igual a $-a_{ji}$[cite: 625, 626].

## 4. Almacenamiento Compacto
Para ahorrar memoria en matrices especiales, se mapean índices 2D a un arreglo 1D:
* [cite_start]**Triangular Inferior**: Ocupa $n(n+1)/2$ elementos y se mapea por filas[cite: 211]. [cite_start]La fórmula de acceso es $k = i \cdot (i+1)/2 + j$[cite: 213].
* [cite_start]**Simétrica**: Se guarda solo el triángulo superior o inferior, permitiendo un acceso compacto con una fórmula optimizada[cite: 216, 219].

## 5. Resolución de Sistemas y Complejidad Algorítmica
[cite_start]Existen ventajas drásticas de rendimiento al aprovechar la estructura de estas matrices en sistemas de ecuaciones, comparadas con resolver matrices densas (cuyo costo computacional es de $O(n^3)$)[cite: 236]:
* [cite_start]**Sustitución hacia adelante/atrás (Triangulares)**: Resolverlas cuesta $O(n^2)$[cite: 235].
* [cite_start]**Método de Thomas (Tridiagonales)**: Especializado para matrices tridiagonales, su resolución toma solo $O(n)$ de tiempo y memoria[cite: 158, 159, 235]. [cite_start]Es ideal para problemas como la discretización en ecuaciones de calor estacionaria[cite: 247, 250].

### Validaciones y Buenas Prácticas
* [cite_start]Al programar los algoritmos (como Thomas), es crítico evitar divisiones por cero validando diagonales no nulas[cite: 223, 226].
* [cite_start]El uso de Pruebas Unitarias (JUnit) con aserciones considerando tolerancias para decimales es fundamental para corroborar la validez de los algoritmos[cite: 241, 242].