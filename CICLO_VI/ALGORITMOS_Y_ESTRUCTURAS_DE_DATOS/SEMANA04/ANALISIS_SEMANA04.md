# Semana 4: operaciones con arreglos bidimensionales

## Materiales revisados

- `S4 - Operaciones con Arreglos Bidimensionales.pdf`
- `Arreglos_bidimensionales.pptx`

## Logro de la sesión

Implementar, identificar y manipular matrices cuadradas, triangulares inferiores y superiores, tridiagonales, simétricas y asimétricas en Java.

## Fundamentos que se aplicarán al ejercicio Comercial Nova

Una matriz es un arreglo bidimensional organizado en filas y columnas. En Java puede declararse indicando primero el número de filas y luego el número de columnas:

```java
int[][] matriz = new int[3][5];
```

Para acceder o asignar un dato se emplean dos índices:

```java
matriz[fila][columna] = valor;
```

La forma general de recorrer todos sus elementos es mediante dos ciclos anidados:

```java
for (int i = 0; i < matriz.length; i++) {
    for (int j = 0; j < matriz[i].length; j++) {
        System.out.print(matriz[i][j] + "\t");
    }
    System.out.println();
}
```

- `matriz.length` devuelve el número de filas.
- `matriz[i].length` devuelve el número de columnas de la fila actual.
- El ciclo externo recorre las filas y el interno recorre las columnas.

## Tipos de matrices estudiados

- **Cuadrada:** tiene el mismo número de filas y columnas.
- **Triangular inferior:** contiene ceros por encima de la diagonal principal.
- **Triangular superior:** contiene ceros por debajo de la diagonal principal.
- **Tridiagonal:** solo puede contener valores distintos de cero en la diagonal principal y en las diagonales adyacentes.
- **Simétrica:** cumple `matriz[i][j] == matriz[j][i]`.
- **Asimétrica:** no cumple la propiedad anterior.

## Criterio de desarrollo

La solución de Comercial Nova se realizó con arreglos bidimensionales y ciclos básicos, respetando el nivel de la sesión. El análisis del archivo adjunto permitió definir:

1. Cada fila representa una de las cuatro tiendas.
2. Cada columna representa uno de los cinco meses, de enero a mayo.
3. Los veinte montos vienen definidos en el enunciado.
4. Se requieren totales por tienda y mes, máximos, mínimos y promedios.
5. También se debe contar los valores mayores a S/ 14 000 y localizar la mayor venta individual.

## Resultados de control de Comercial Nova

- Total general: S/ 262 400.
- Tienda con mayor venta total: Tienda 3, con S/ 79 100.
- Tienda con menor venta total: Tienda 4, con S/ 53 700.
- Mes con mayores ventas: mayo, con S/ 56 400.
- Registros mayores a S/ 14 000: 8.
- Mayor venta individual: S/ 17 000, Tienda 3, mayo.

El programa, el informe editable, la captura y el PDF final se encuentran en la carpeta `NOVA`.
