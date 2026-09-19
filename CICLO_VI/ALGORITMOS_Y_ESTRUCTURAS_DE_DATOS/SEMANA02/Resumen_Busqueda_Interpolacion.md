# Resumen de Búsqueda por Interpolación

Curso: Algoritmos y Estructuras de Datos  
Semana: 02  
Tema asignado: Búsqueda por interpolación

## Idea general

La búsqueda por interpolación es un algoritmo que permite encontrar un valor dentro de un vector ordenado. A diferencia de otros métodos, no revisa siempre desde el inicio ni parte exactamente por la mitad. Este algoritmo calcula matemáticamente una posición aproximada donde debería estar el dato buscado.

Es importante aclarar que el algoritmo no "piensa" ni "adivina" como una inteligencia artificial. Lo que hace es aplicar una fórmula usando el valor buscado, el primer valor del rango y el último valor del rango.

## Diferencia con la búsqueda binaria

La búsqueda binaria siempre revisa el centro del arreglo.

Ejemplo:

```text
Si hay 100 elementos, primero revisa la posición 50.
Si el dato buscado es menor, busca en la mitad izquierda.
Si es mayor, busca en la mitad derecha.
```

La búsqueda por interpolación no revisa siempre el centro. Calcula una posición probable según el valor que se quiere buscar.

Ejemplo:

```text
Si los códigos van de 1000 a 2000 y buscamos 1900,
la interpolación calcula que el dato debe estar cerca del final.
```

Frase para exposición:

```text
La búsqueda binaria siempre divide el arreglo por la mitad. En cambio,
la búsqueda por interpolación estima matemáticamente la posición usando
el valor buscado. Por eso puede ser más rápida cuando los datos están
ordenados y distribuidos de forma regular.
```

## Ejemplo de vida real

Caso: sistema de almacén con códigos de productos.

Vector ordenado:

```text
1000, 1010, 1020, 1030, 1040, 1050, 1060, 1070, 1080, 1090
```

Si queremos buscar el código `1080`, la búsqueda por interpolación calcula que ese valor debe estar cerca del final, porque `1080` está más cerca de `1090` que de `1000`.

Comparación simple:

```text
Búsqueda binaria:
Empieza revisando el centro.

Búsqueda por interpolación:
Calcula una posición aproximada y puede ir directamente cerca del dato.
```

Otro ejemplo sencillo:

```text
Es como buscar una página en un libro.
Si quiero ir a la página 180 de un libro de 200 páginas,
no abro exactamente a la mitad. Abro cerca del final porque
matemáticamente sé que 180 está cerca de 200.
```

## Fórmula del algoritmo

La fórmula usada es:

```text
pos = inicio + ((valorBuscado - arreglo[inicio]) * (fin - inicio)) / (arreglo[fin] - arreglo[inicio])
```

Donde:

```text
inicio = primera posición del rango
fin = última posición del rango
valorBuscado = dato que queremos encontrar
arreglo[inicio] = primer valor del rango
arreglo[fin] = último valor del rango
pos = posición calculada para revisar
```

Ejemplo:

```text
Arreglo:
1000, 1010, 1020, 1030, 1040, 1050, 1060, 1070, 1080, 1090

Buscar:
1080

inicio = 0
fin = 9
arreglo[inicio] = 1000
arreglo[fin] = 1090
```

Aplicando la fórmula:

```text
pos = 0 + ((1080 - 1000) * (9 - 0)) / (1090 - 1000)
pos = 0 + (80 * 9) / 90
pos = 720 / 90
pos = 8
```

Entonces revisa:

```text
arreglo[8] = 1080
```

En este caso lo encuentra directamente.

## Preguntas básicas del Word

### ¿En qué consiste?

Consiste en buscar un dato dentro de un vector ordenado calculando una posición aproximada donde debería estar el valor buscado.

Ejemplo:

```text
Si en un almacén los códigos van de 1000 a 1090 y buscamos 1080,
el algoritmo calcula que debe revisar cerca del final.
```

### ¿Cómo funciona?

Funciona aplicando una fórmula matemática que usa el valor buscado, el primer valor y el último valor del rango. Luego revisa la posición calculada.

Si el dato encontrado es menor que el buscado, continúa buscando hacia la derecha. Si el dato encontrado es mayor, continúa hacia la izquierda.

### ¿Requiere que el vector esté ordenado?

Sí. Obligatoriamente debe estar ordenado.

Si el vector está desordenado, la fórmula no puede estimar correctamente la posición.

Ejemplo desordenado:

```text
1050, 1000, 1090, 1010, 1080
```

En este caso no hay un crecimiento lógico de izquierda a derecha.

### ¿Qué tipo de datos puede utilizar?

Principalmente datos numéricos.

Ejemplos:

```text
Códigos de productos
DNI
Números de factura
Boletas
Tickets
```

No es ideal para texto libre porque la fórmula necesita realizar operaciones matemáticas.

### ¿Cuál es su complejidad temporal?

La complejidad temporal indica cuántos pasos puede necesitar un algoritmo cuando aumenta la cantidad de datos.

En la búsqueda por interpolación:

```text
Mejor caso: O(1)
Promedio: O(log log n)
Peor caso: O(n)
```

Explicación breve:

```text
O(1): encuentra el dato al primer intento.
O(log log n): encuentra el dato muy rápido si los datos están bien distribuidos.
O(n): puede demorarse mucho si los datos son muy irregulares.
```

### ¿Cuáles son sus ventajas?

Puede ser más rápida que la búsqueda binaria cuando se trabaja con muchos datos numéricos, ordenados y distribuidos de forma uniforme.

Ejemplo:

```text
En un almacén con miles de productos codificados de forma correlativa,
puede encontrar un código en pocos intentos.
```

### ¿Cuáles son sus desventajas?

No funciona bien si los datos están desordenados o si tienen un crecimiento muy irregular.

Ejemplo irregular:

```text
1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 2000
```

Si buscamos `1008`, la fórmula ve que el rango llega hasta `2000`. Como `1008` está muy cerca de `1000`, puede calcular una posición cercana al inicio, aunque realmente `1008` está en la posición 8.

### ¿En qué situaciones es recomendable utilizarlo?

Es recomendable cuando se tienen muchos datos numéricos ordenados y con crecimiento regular.

Ejemplo:

```text
Un sistema de almacén donde los productos tienen códigos correlativos:
1000, 1010, 1020, 1030, 1040...
```

En ese caso, si se busca el código `1080`, el algoritmo puede calcular que está cerca del final y llegar más rápido.

## ¿Por qué necesita orden y crecimiento regular?

Necesita que el vector esté ordenado porque la fórmula compara el valor buscado con el primer y último valor del rango.

También funciona mejor cuando los datos tienen crecimiento regular, porque la fórmula asume que los valores están repartidos proporcionalmente.

Ejemplo regular:

```text
1000, 1010, 1020, 1030, 1040, 1050, 1060, 1070, 1080, 1090
```

Aquí los valores crecen de 10 en 10, por eso la estimación es muy precisa.

Ejemplo irregular:

```text
1000, 1001, 1002, 1003, 1004, 1005, 1006, 1007, 1008, 2000
```

Aquí hay un salto muy grande al final. La fórmula puede calcular una posición poco precisa.

Cuando no acierta en la primera posición, el algoritmo hace lo siguiente:

```text
1. Revisa la posición calculada.
2. Si el valor encontrado es menor que el buscado, mueve el inicio hacia adelante.
3. Si el valor encontrado es mayor que el buscado, mueve el fin hacia atrás.
4. Vuelve a aplicar la fórmula en el nuevo rango.
```

Por eso, con datos irregulares no necesariamente falla, pero pierde eficiencia.

## Complejidad temporal explicada simple

La letra `O` no significa cero. Se lee como "O grande" y se usa para expresar cómo crece el tiempo de un algoritmo cuando aumenta la cantidad de datos.

La `n` representa la cantidad de elementos.

Ejemplo:

```text
n = cantidad de productos
```

Si hay 10 productos, `n = 10`.  
Si hay 10 000 productos, `n = 10 000`.

Comparación:

```text
O(n)
Puede revisar uno por uno.
Ejemplo: búsqueda lineal.

O(log n)
Reduce el problema por mitades.
Ejemplo: búsqueda binaria.

O(log log n)
Puede reducir el problema todavía más rápido si calcula bien la posición.
Ejemplo: búsqueda por interpolación con datos uniformes.
```

Frase para exposición:

```text
La complejidad temporal nos dice cuántos pasos puede necesitar un algoritmo
cuando aumenta la cantidad de datos. En búsqueda lineal es O(n), porque puede
revisar uno por uno. En búsqueda binaria es O(log n), porque divide el arreglo
por mitades. En búsqueda por interpolación, si los datos están ordenados y bien
distribuidos, el promedio es O(log log n), porque calcula una posición aproximada
y puede llegar al dato en menos intentos.
```

## Respuesta corta final

```text
La búsqueda por interpolación es un algoritmo para buscar datos numéricos dentro
de un vector ordenado. No revisa siempre la mitad como la búsqueda binaria, sino
que calcula matemáticamente una posición aproximada usando el valor buscado, el
primer valor y el último valor del rango. Funciona mejor cuando los datos tienen
un crecimiento regular, como códigos de productos correlativos. Su mejor caso es
O(1), su promedio es O(log log n) y su peor caso puede llegar a O(n) si los datos
son muy irregulares.
```
