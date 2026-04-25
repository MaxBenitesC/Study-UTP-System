# Resumen: Operaciones con Arreglos Unidimensionales en Java

## 1. Conceptos Básicos y Complejidad
[cite_start]Un arreglo unidimensional es una estructura lineal que almacena elementos del mismo tipo en posiciones de memoria contiguas[cite: 701, 858]. [cite_start]El acceso a sus elementos se realiza mediante un índice (generalmente comenzando en cero)[cite: 702, 859]. 
* [cite_start]**Acceso por índice**: Costo computacional de $O(1)$[cite: 706, 863].
* [cite_start]**Recorrido, comparación o copia completa**: Costo computacional de $O(n)$[cite: 707, 708, 864, 865].

## 2. Comparación de Arreglos
[cite_start]La comparación permite establecer relaciones de igualdad u orden (lexicográfico) entre dos arreglos[cite: 711, 712, 868, 869].
* [cite_start]**Igualdad Superficial**: Verifica si ambas variables apuntan a la misma referencia en memoria[cite: 716, 873].
* [cite_start]**Igualdad Profunda (Contenido)**: Verifica si los arreglos tienen los mismos valores y en el mismo orden exacto[cite: 717, 874]. [cite_start]Si las longitudes difieren, los arreglos no son iguales[cite: 721, 728, 878, 885].
* [cite_start]**Implementación en Java**: Se puede utilizar un bucle iterativo o el método `Arrays.compare(arreglo1, arreglo2)`[cite: 1022, 1023].
  * [cite_start]Retorna `0` si son iguales[cite: 1024].
  * [cite_start]Retorna `1` si el primer arreglo es mayor[cite: 1025].
  * [cite_start]Retorna `-1` si el primer arreglo es menor[cite: 1026].

## 3. Clonación (Copia) de Arreglos
[cite_start]La clonación se utiliza para crear copias de un arreglo y así evitar modificar los datos del arreglo original[cite: 1017]. [cite_start]Consume memoria adicional equivalente al tamaño del arreglo copiado[cite: 755, 912].
* [cite_start]**Copia Superficial vs. Profunda**: Una copia superficial mantiene las referencias originales a los objetos, mientras que una copia profunda duplica recursivamente los objetos, haciéndolos independientes[cite: 739, 740, 750, 751, 896, 897, 907, 908].
* **Implementación en Java**:
  * **`Arrays.copyOf(arregloOriginal, longitud)`**: Crea una copia con el tamaño especificado. [cite_start]Si la nueva longitud es menor, se trunca; si es mayor, se rellena con ceros (o nulos)[cite: 1043, 1045, 1049, 1050].
  * **`Arrays.copyOfRange(arregloOriginal, desde, hasta)`**: Copia un intervalo específico. [cite_start]El índice "desde" es inclusivo y el "hasta" es exclusivo[cite: 1062, 1063, 1064].
  * [cite_start]**Método `.clone()`**: Crea una copia exacta (ej. `int[] clone1 = a.clone();`)[cite: 1082, 1084].
  * [cite_start]*Nota*: Usar el operador de asignación (`b = a`) NO clona el arreglo, solo copia la referencia de memoria[cite: 1085].

## 4. Fusión de Arreglos
[cite_start]La fusión consiste en unir o combinar dos o más arreglos en uno solo[cite: 1088]. [cite_start]La complejidad de esta operación es proporcional a la suma de los tamaños de los arreglos: $O(|A| + |B|)$[cite: 772, 812, 929, 969].
* [cite_start]**Concatenación**: Simplemente pega el contenido del arreglo B al final del arreglo A[cite: 759, 916].
* **Mezcla Ordenada (Merge)**: Combina dos arreglos que ya están ordenados en un solo arreglo resultante también ordenado. [cite_start]Se suele implementar utilizando "dos punteros" que recorren simultáneamente ambos arreglos para ir insertando el valor menor[cite: 759, 766, 768, 769, 916, 923, 925, 926].
* **Implementación en Java**: Se pueden usar bucles manuales o el método optimizado del sistema:
  * [cite_start]`System.arraycopy(arregloOrigen, indiceOrigen, arregloDestino, indiceDestino, longitud)`[cite: 1090].

## 5. Buenas Prácticas
* [cite_start]Documentar siempre si las copias o comparaciones que realiza el algoritmo son superficiales o profundas[cite: 799, 956].
* [cite_start]Validar siempre los casos borde antes de operar (ej. arreglos vacíos, entradas nulas, tamaños incompatibles)[cite: 800, 804, 957, 961].
* [cite_start]Evitar hacer conversiones de tipo innecesarias dentro de los bucles de iteración[cite: 736, 893].