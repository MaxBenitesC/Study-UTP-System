# Estrategia de desarrollo — Caso integrador de inventario con arrays

## 1. Actividad identificada

- Título en UTP+Class: **S03.s2 Caso integrador**.
- Modalidad: individual.
- Estado observado: pendiente / no entregado.
- Disponible desde: 27 de agosto de 2026 a las 08:53.
- Fecha límite: 30 de agosto de 2026 a las 23:59.
- Intentos permitidos: 2.
- Actividad no calificada según los datos de UTP+Class.
- Entregable: documento DOCX o PDF con la solución, el código y capturas que demuestren su funcionamiento.
- Enunciado oficial: `../MATERIALES/Ejercicio_practico_Gestion_de_inventario_con_arrays.docx`.
- Material teórico oficial: `../MATERIALES/S3_Operaciones_con_Arreglos_Unidimensionales.pdf`.

## 2. Qué enseña el material de la semana

El material oficial se concentra en tres operaciones sobre arreglos unidimensionales:

1. **Comparación:** dos arreglos son iguales si tienen la misma longitud y el mismo valor en cada posición.
2. **Clonación:** se crea una copia independiente que puede modificarse sin afectar el original.
3. **Fusión:** se concatenan dos o más arreglos para producir uno nuevo.

El caso integrador amplía esas tres operaciones con inserción, recorrido, actualización, eliminación, búsqueda, ordenamiento y análisis de rendimiento.

## 3. Restricción principal y decisión de diseño

El programa debe usar exclusivamente arreglos unidimensionales. No se utilizarán `ArrayList`, listas, colecciones, matrices ni estructuras equivalentes.

Se usarán **cuatro arreglos paralelos**:

```java
int[] codigos;
String[] nombres;
double[] precios;
int[] stocks;
```

La posición `i` representa un solo producto en los cuatro arreglos. Por ejemplo, `codigos[i]`, `nombres[i]`, `precios[i]` y `stocks[i]` pertenecen al mismo producto.

Se mantendrá una variable `cantidad` para distinguir los productos registrados de los espacios libres. La capacidad propuesta es 50, suficiente para insertar nuevos productos y fusionar los 20 productos obligatorios.

No se sobrescribirá `../Main.java`, porque corresponde al ejercicio anterior de comparación, clonación y fusión de ventas. El nuevo código se ubicará en `CASO_INTEGRADOR/Main.java`.

## 4. Conjuntos de datos independientes

Para evitar que una operación contamine las siguientes pruebas, se conservarán los datos oficiales iniciales y se crearán copias para cada bloque:

- **Inventario operativo:** demostrará registro, recorrido, actualización y eliminación.
- **Inventario original:** conservará los 15 productos sin modificaciones para las pruebas obligatorias.
- **Inventario clonado:** recibirá solamente las tres modificaciones exigidas.
- **Inventario ordenado:** se utilizará en búsqueda binaria, Jump Search e interpolación.
- **Inventario consolidado:** contendrá los 15 productos principales y los 5 del segundo almacén.
- **Copia por algoritmo:** cada ordenamiento recibirá exactamente los mismos 15 productos desordenados.

Esta separación garantiza comparaciones válidas y resultados reproducibles.

## 5. Módulos funcionales

### A. Gestión básica del inventario

Métodos propuestos:

```text
registrarProducto
mostrarInventario
buscarPosicionSecuencial
actualizarProducto
eliminarProducto
mostrarStockMenorA10
mostrarProductoMayorPrecio
mostrarMayorYMenorStock
calcularValorTotal
```

Reglas:

- Antes de insertar se comprobará que el código no exista.
- La eliminación desplazará hacia la izquierda los cuatro arreglos.
- Toda actualización se hará después de localizar el código.
- El valor económico será la suma de `precio × stock`.

### B. Clonación y comparación

Se clonarán por separado códigos, nombres, precios y stocks. Solo el clon recibirá estas modificaciones:

- Código 105: stock de 8 a 15.
- Código 315: precio de S/ 950 a S/ 900.
- Código 525: stock de 6 a 20.

La comparación recorrerá ambos inventarios y mostrará exactamente qué campos cambiaron. Después se verificará que el original conserve sus valores.

### C. Comparación y fusión de almacenes

Antes de fusionar:

- buscar códigos repetidos;
- mostrar productos comunes;
- mostrar productos exclusivos del almacén principal;
- mostrar productos exclusivos del segundo almacén.

Si no existen duplicados, se crearán cuatro arreglos consolidados con 20 productos usando copias por recorrido o `System.arraycopy`. Luego se mostrarán, ordenarán por código, buscarán productos de ambas sedes y calculará su valor total.

### D. Algoritmos de búsqueda

Cada método devolverá un arreglo `int[]` con:

```text
[posición, comparaciones]
```

Algoritmos obligatorios:

1. Búsqueda secuencial.
2. Búsqueda secuencial con centinela.
3. Búsqueda binaria.
4. Jump Search.
5. Búsqueda por interpolación.

Pruebas obligatorias para cada algoritmo:

```text
105, 525, 715, 910, 1100, 999, 50, 1500
```

Los algoritmos binario, Jump Search e interpolación se ejecutarán únicamente sobre una copia ordenada por código. El reporte mostrará código, algoritmo, encontrado/no encontrado, posición y comparaciones.

### E. Algoritmos de ordenamiento

Algoritmos obligatorios:

1. Bubble Sort.
2. Selection Sort.
3. Insertion Sort.
4. Shell Sort.
5. Quick Sort.
6. Merge Sort.

Cada algoritmo trabajará con clones nuevos de los cuatro arreglos para partir del mismo orden. Cuando se intercambie o mueva un código, también se moverán nombre, precio y stock.

Cada método recibirá un arreglo de métricas:

```text
[comparaciones, movimientos]
```

Definición que se mantendrá en todo el programa:

- **Comparación:** evaluación entre dos claves de ordenamiento.
- **Movimiento:** traslado o intercambio de un registro completo de producto.

Después de cada prueba se validará programáticamente que los códigos queden así:

```text
105, 150, 210, 275, 315, 350, 420, 525,
630, 680, 715, 760, 820, 910, 1100
```

La tabla final registrará las métricas de los seis algoritmos y señalará cuál obtuvo menos comparaciones y cuál menos movimientos.

### F. Ordenamiento por precio

La comparación principal de los seis algoritmos será por código, tal como exige el enunciado. Se añadirá una segunda demostración que ordene los productos por precio de menor a mayor, manteniendo asociados los demás datos. Para evitar duplicar innecesariamente el programa, esta prueba puede realizarse con Merge Sort, indicando claramente el criterio usado.

## 6. Secuencia propuesta de ejecución

El programa imprimirá secciones numeradas:

1. Inventario inicial.
2. Registro con validación de código repetido.
3. Actualización y eliminación.
4. Consultas estadísticas del inventario.
5. Prueba de clonación e independencia.
6. Comparación de almacenes y fusión.
7. Inventario consolidado y valor total.
8. Comparación de algoritmos de búsqueda.
9. Comparación de algoritmos de ordenamiento.
10. Ordenamiento por precio.
11. Análisis y recomendaciones finales.

El flujo será automático y determinista. No se dependerá de entradas manuales para las pruebas obligatorias, lo cual facilitará repetir la ejecución y obtener capturas consistentes.

## 7. Resultados y análisis que debe producir el programa

Al final se responderá con datos obtenidos durante la ejecución:

- búsqueda con menos comparaciones;
- búsquedas que necesitan datos ordenados;
- ordenamiento más eficiente para este conjunto;
- confirmación de que los seis algoritmos llegan al mismo orden;
- diferencia entre clonar y asignar un arreglo;
- utilidad empresarial de fusionar inventarios;
- importancia de detectar duplicados antes de consolidar;
- recomendación para inventarios grandes.

Las conclusiones no se escribirán de antemano: se completarán con las métricas reales del programa.

## 8. Criterios de aceptación

Antes de preparar el documento final se comprobará:

- [ ] Compila con `javac Main.java`.
- [ ] Ejecuta sin excepciones.
- [ ] No usa `ArrayList`, matrices ni colecciones.
- [ ] Mantiene sincronizados código, nombre, precio y stock.
- [ ] Rechaza códigos duplicados al registrar y antes de fusionar.
- [ ] Ejecuta las 8 claves obligatorias con los 5 algoritmos de búsqueda.
- [ ] Ejecuta los 6 algoritmos de ordenamiento con los mismos 15 datos.
- [ ] Los 6 ordenamientos producen la secuencia esperada.
- [ ] Registra comparaciones y movimientos.
- [ ] El clon cambia y el original permanece intacto.
- [ ] La fusión contiene exactamente 20 productos sin duplicados.
- [ ] Ordena también por precio.
- [ ] Calcula correctamente el valor consolidado.
- [ ] Genera una salida legible para capturas.

### Valores de control calculados desde los datos oficiales

Estos valores servirán como oráculo para detectar errores durante las pruebas:

- Valor del inventario principal: **S/ 99 975.00**.
- Valor del segundo almacén: **S/ 33 170.00**.
- Valor consolidado esperado: **S/ 133 145.00**.
- Productos del almacén principal con stock menor a 10: códigos **105, 525, 275, 150 y 350**.
- Producto de mayor precio: código **105**, Laptop Lenovo, S/ 3500.00.
- Mayor stock: código **910**, 40 unidades.
- Menor stock: código **150**, 5 unidades.
- Posiciones esperadas en el arreglo ordenado por código, usando índices desde cero:
  - 105 → 0
  - 525 → 7
  - 715 → 10
  - 910 → 13
  - 1100 → 14
  - 999, 50 y 1500 → no encontrados
- Orden esperado por precio, expresado mediante códigos:

```text
910, 210, 420, 680, 275, 1100, 630, 715,
760, 820, 525, 350, 315, 150, 105
```

## 9. Estrategia de entrega

Cuando el programa esté validado:

1. Guardar la ejecución completa en `ejecucion.txt`.
2. Tomar capturas separadas de las secciones relevantes, no una sola captura ilegible.
3. Preparar un documento con:
   - carátula sencilla;
   - descripción breve del caso;
   - explicación de los arreglos paralelos;
   - capturas de funcionamiento;
   - tablas de comparación;
   - análisis final;
   - código fuente como anexo.
4. Exportar a PDF y revisar visualmente todas sus páginas.
5. Conservar el DOCX editable como respaldo.

## 10. Orden de implementación

Para reducir riesgos se desarrollará en este orden:

1. Datos y métodos comunes de inventario.
2. CRUD y estadísticas.
3. Clonación, comparación y fusión.
4. Búsquedas y sus métricas.
5. Ordenamientos y sus métricas.
6. Prueba por precio y análisis final.
7. Validación automática.
8. Capturas y documento de entrega.

No se avanzará a la maquetación del informe hasta que todas las pruebas del código sean correctas.
