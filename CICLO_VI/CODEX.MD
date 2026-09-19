# Memoria persistente - CICLO_VI

Última actualización: 2026-08-18  
Workspace: `/home/ilkay/Documentos/UTP/CICLO_VI`

## Contexto general

El usuario está trabajando el curso:

`ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS`

Actualmente se está avanzando la `SEMANA02`, tema:

`Estructuras de Datos Estáticas`

La metodología solicitada por el usuario es docente/práctica: explicar como profesor con experiencia, usando ejemplos simples, paso a paso, sin enfoque demasiado profesional o avanzado cuando se trate de ejercicios estudiantiles.

## Archivos organizados

Se movieron desde `/home/ilkay/Downloads` los PDFs recientes del curso de Algoritmos y Estructuras de Datos:

- `S1 - Introducción a las Estructuras de Datos.pdf`
  - Destino: `ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA01/`
- `S2 - Estructuras de Datos Estaticas.pdf`
  - Destino: `ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/`

## PDFs convertidos a Markdown

Se analizaron e interpretaron ambos PDFs, incluyendo texto, imágenes, gráficos y bloques de código.

Archivos generados:

- `ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA01/S1 - Introducción a las Estructuras de Datos.md`
- `ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/S2 - Estructuras de Datos Estaticas.md`

## Semana 02: contenido trabajado

Tema central:

- estructuras de datos estáticas;
- arreglos unidimensionales;
- operaciones sobre vectores/arreglos:
  - recorrido;
  - inserción;
  - actualización;
  - eliminación;
  - copia.

Puntos explicados al usuario:

- Los índices en Java empiezan en `0`.
- El último índice válido es `arreglo.length - 1`.
- Acceso y actualización por índice cuestan `O(1)`.
- Recorrido, inserción, eliminación y copia suelen costar `O(n)`.
- Si el enunciado dice “capacidad máxima” y “cantidad actual”, se debe usar el mismo arreglo con espacio libre, no necesariamente crear otro arreglo.
- La inserción desplaza elementos hacia la derecha.
- La eliminación desplaza elementos hacia la izquierda.
- `System.arraycopy(...)` fue explicado, pero para los ejercicios se prefirió usar `for`, porque es más claro para nivel estudiantil.

## Ejercicios de SEMANA02

Archivo fuente del enunciado:

- `ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/Ejercicios.docx`

El Word contiene dos ejercicios:

1. Gestión de ventas de productos usando vectores en Java.
2. Gestión de inventario y ventas con vectores paralelos en Java.

## Ejercicio 1

Ruta:

`ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/EJERCICIO01/Main.java`

Estado:

- Implementado por el usuario.
- Validado por Codex.
- Compila correctamente con `javac Main.java`.
- Ejecuta correctamente con `java Main`.

Cumple:

- usa vector de capacidad 10;
- usa variable `cantidad`;
- no usa `ArrayList`;
- muestra recorrido inicial;
- inserta venta `275.50` en posición/índice `2`;
- actualiza posición/índice `3` a `350.00`;
- elimina posición/índice `1`;
- muestra recorrido final;
- inserción con desplazamiento hacia la derecha;
- eliminación con desplazamiento hacia la izquierda.

Resultado final validado:

```text
VENTAS FINALES
Posición 0: S/ 250.00
Posición 1: S/ 275.50
Posición 2: S/ 350.00
Posición 3: S/ 150.00
Posición 4: S/ 400.00
```

Comandos útiles:

```bash
cd /home/ilkay/Documentos/UTP/CICLO_VI/ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/EJERCICIO01
javac Main.java
java Main
```

## Ejercicio 2

Ruta:

`ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/EJERCICIO02/Main.java`

Estado:

- Creado por Codex.
- Estilo estudiantil, no demasiado profesional.
- Compila correctamente con `javac Main.java`.
- Ejecuta correctamente con `java Main`.

Cumple:

- no usa `ArrayList`;
- trabaja solo con vectores;
- usa vectores paralelos:
  - `String[] codigos`
  - `String[] productos`
  - `double[] precios`
  - `int[] stock`
- usa variable `cantidad`;
- muestra inventario inicial;
- inserta producto `P006 / Webcam / 180.00 / 12`;
- actualiza `P003`, sumando 10 unidades y cambiando precio a `95.00`;
- registra venta de `P001`, cantidad 3;
- elimina `P004`;
- permite búsqueda de producto por código ingresado por usuario;
- muestra producto con mayor stock;
- muestra producto con mayor valor de inventario;
- muestra reporte final.

Nota importante:

En el ejercicio 2, para “registrar el producto en la posición 2”, se usó índice `1`, porque la posición humana 2 corresponde al índice Java 1. Esto además coincide con el ejemplo del Word, donde el reporte final muestra:

```text
P001
P006
...
```

Comandos útiles:

```bash
cd /home/ilkay/Documentos/UTP/CICLO_VI/ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS/SEMANA02/EJERCICIO02
javac Main.java
java Main
```

Para prueba automática con búsqueda de `P003`:

```bash
printf 'P003\n' | java Main
```

Resultado final validado para búsqueda `P003`:

```text
P001     Laptop          2500.00        5     12500.00
P006     Webcam           180.00       12      2160.00
P002     Mouse             45.00       25      1125.00
P003     Teclado           95.00       25      2375.00
P005     Impresora        650.00        6      3900.00
VALOR TOTAL DEL INVENTARIO: S/ 22060.00
```

## Punto exacto donde quedó el trabajo

El usuario ya terminó y validó el ejercicio 1.

Se creó el ejercicio 2 y está funcionando. Pendiente recomendado:

1. Leer junto con el usuario el código de `EJERCICIO02/Main.java`.
2. Explicar especialmente:
   - vectores paralelos;
   - inserción desplazando los cuatro vectores;
   - eliminación desplazando los cuatro vectores;
   - búsqueda con `buscarPosicion`;
   - cálculo de valor de inventario;
   - diferencia entre índice Java y posición humana.
3. Si el usuario quiere, simplificar todavía más el código del ejercicio 2 o agregar comentarios línea por línea.
4. Si el docente exige menú, implementar luego el reto adicional del menú.

## Preferencias de trabajo del usuario

- Usa consola y normalmente edita con `nvim`.
- Necesita que los programas Java corran sin errores de sintaxis.
- Prefiere explicación paso a paso y con ejemplos visuales de arreglos.
- Le ayuda comparar el estado del arreglo antes y después de cada operación.
- Para tareas universitarias, prefiere código claro y estudiantil antes que una solución demasiado avanzada.

