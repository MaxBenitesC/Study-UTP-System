# Guion de Exposición: Búsqueda por Interpolación
**Expositor:** Max Anderson Benites Corazón
**Diapositiva:** ¿En qué situaciones sería recomendable utilizarlo?

---

## 1. Transición / Inicio
> "Muchas gracias. Bien, compañeros y profesora, para continuar con nuestra exposición, a mí me corresponde explicar **en qué escenarios reales** es verdaderamente útil aplicar la Búsqueda por Interpolación. 
> 
> Como hemos visto, es un algoritmo potente, pero no es para cualquier situación. Principalmente, recomendamos su uso bajo cuatro condiciones específicas:"

---

## 2. Cuadrante 1: Distribución Uniforme
*(Señalar el primer cuadrante)*

> "El primer factor, y el más importante, es que los datos tengan una **distribución uniforme**. 
>
> ¿Qué significa esto? Que los valores crezcan de manera constante y predecible, sin huecos gigantes entre ellos. Un ejemplo perfecto para esto es buscar números de DNI en un padrón electoral, facturas que se emiten secuencialmente, o incluso los códigos de nuestro **ejercicio del almacén**, si asumimos que los productos se registran de forma correlativa (como 1000, 1001, 1002). 
> 
> Al ser predecibles, la fórmula matemática del algoritmo 'adivina' casi con exactitud dónde está el dato."

---

## 3. Cuadrante 2: Volúmenes Masivos (Big Data)
*(Señalar el segundo cuadrante)*

> "En segundo lugar, hablamos de **volúmenes de datos masivos**, lo que hoy conocemos como Big Data. 
> 
> Si tenemos un arreglo pequeño de 30 o 100 elementos, honestamente, la Búsqueda Binaria o lineal podría ser más rápida porque la fórmula matemática de la interpolación consume tiempo de procesador. Sin embargo, cuando hablamos de **millones** de registros, este algoritmo es imbatible. 
> 
> Su complejidad de **O(log(log n))** hace que encuentre un dato en poquísimos intentos, destrozando en velocidad a otros métodos."

---

## 4. Cuadrante 3: Lecturas Costosas
*(Señalar el tercer cuadrante)*

> "El tercer escenario es cuando tenemos **lecturas de memoria costosas**. 
> 
> Imaginen que nuestra inmensa base de datos no está en la memoria RAM, sino guardada en un disco duro físico o en un servidor externo. Cada vez que el algoritmo 'salta' a revisar una posición, cuesta mucho tiempo de lectura. 
> 
> Como la búsqueda por interpolación minimiza drásticamente la cantidad de saltos (va directo a la zona correcta en vez de dividir ciegamente por mitades), nos ahorra muchísimos cuellos de botella de hardware."

---

## 5. Cuadrante 4: Claves Estrictamente Numéricas
*(Señalar el cuarto cuadrante)*

> "Finalmente, hay un requisito y limitación clave: solo es aplicable cuando usamos **claves estrictamente numéricas**. 
> 
> Como el algoritmo internamente necesita sumar, restar y dividir para calcular la posición, no le podemos pasar texto libre. Además, los datos **tienen que estar ordenados** y evitar fluctuaciones extremas. 
> 
> Si en nuestra empresa casi todos ganan mil dólares, y de pronto hay un registro de 50 mil dólares, el algoritmo se confundirá y perderá toda su eficiencia."

---

## 6. Cierre de la presentación
> "En conclusión, si administramos millones de registros numéricos que crecen de forma ordenada y secuencial, la Búsqueda por Interpolación es, por mucho, **la mejor elección de rendimiento** que podemos implementar.
> 
> Con esto concluyo mi parte de la explicación del algoritmo. Gracias por su atención."
