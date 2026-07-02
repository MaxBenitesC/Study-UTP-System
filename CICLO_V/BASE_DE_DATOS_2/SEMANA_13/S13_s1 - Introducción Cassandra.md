# Transcripcion: S13_s1 - Introducción Cassandra.pptx

## Fuente

- Archivo original: `S13_s1 - Introducción Cassandra.pptx`
- Tipo: PowerPoint PPTX
- Slides: 32
- Recursos multimedia internos: 27
- Graficos internos: 0

## Nota de transcripcion

- El texto fue extraido de la estructura XML del PPTX, slide por slide.
- Las tablas se transcriben por filas y celdas cuando existen como tablas editables.
- Las imagenes/graficos se listan como objetos detectados con nombre interno, relacion y descripcion alternativa si existe; no se describe contenido visual sin texto extraible.

## Contenido por slide

### Slide 1

#### Texto

**Google Shape;47;p1:**

- CURSO: BASE DE DATOS II

**Google Shape;48;p1:**

- PRIMERA UNIDAD DE APRENDIZAJE 3: Bases de Datos NoSQL

**Google Shape;49;p1:**

- SESIÓN N° 13:
- Cassandra:
- - Introducción a las características de Cassandra - Instalación de la Herramienta

**Google Shape;50;p1:**

- DOCENTES DE UTP
- SISTEMAS

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;52;p1`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.

### Slide 2

#### Texto

**Google Shape;59;p2:**

- Motivación para el aprendizaje

**Google Shape;62;p2:**

- Lo que debemos tener en cuenta

**Google Shape;64;p2:**

- 5 minutos

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;60;p2`, relacion `rId3`, destino interno `../media/image4.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;63;p2`, relacion `rId4`, destino interno `../media/image3.png`, descripcion alternativa: no especificada.

### Slide 3

#### Texto

**Google Shape;70;p3:**

- Motivación para el aprendizaje

**Google Shape;72;p3:**

- ¿BD relacional o columnar?

**Google Shape;73;p3:**

- https://youtu.be/e_MCU2qHUI4?feature=shared

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;71;p3`, relacion `rId3`, destino interno `../media/image4.png`, descripcion alternativa: no especificada.

### Slide 4

#### Texto

**Google Shape;79;p4:**

- Recordando los aprendizajes

**Google Shape;82;p4:**

- ¿Qué vimos en la sesión anterior?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;80;p4`, relacion `rId3`, destino interno `../media/image4.png`, descripcion alternativa: no especificada.

### Slide 5

#### Texto

**Google Shape;88;p5:**

- Recordando los aprendizajes

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;89;p5`, relacion `rId3`, destino interno `../media/image4.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;90;p5`, relacion `rId4`, destino interno `../media/image6.png`, descripcion alternativa: no especificada.
- Imagen 3: nombre `Google Shape;91;p5`, relacion `rId5`, destino interno `../media/image7.png`, descripcion alternativa: no especificada.

### Slide 6

#### Texto

**Google Shape;97;p6:**

- Logro de la sesión

**Google Shape;99;p6:**

- Al final de la sesión el estudiante comprende los fundamentos y uso de  bases de datos no relacional Cassandra
- .

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;100;p6`, relacion `rId3`, destino interno `../media/image10.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;101;p6`, relacion `rId4`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.

### Slide 7

#### Texto

**Google Shape;107;p7:**

- Importancia de la sesión

**Google Shape;109;p7:**

- Cassandra nos proporciona tolerancia a fallos y disponibilidad, pero a cambio de ser eventualmente consistente, ya que la actividad de inserción de datos no es su fuerte, si no su consulta de información de forma rápida. El nivel de consistencia puede ser configurado, según nos interese incluso a nivel de query.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;110;p7`, relacion `rId3`, destino interno `../media/image8.png`, descripcion alternativa: no especificada.

### Slide 8

#### Texto

**Google Shape;115;p8:**

- Cassandra:
- Introducción a las características de Cassanda
- Instalación de la Herramienta

**Google Shape;116;p8:**

- SESIÓN N° 13:

**Google Shape;118;p8:**

- Temario

**Google Shape;120;p8:**

- ¡Que no se te escape nada de tu clase!:

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;119;p8`, relacion `rId3`, destino interno `../media/image4.png`, descripcion alternativa: no especificada.

### Slide 9

#### Texto

**Google Shape;125;p9:**

- Diseño de una base de datos columnar

**Google Shape;127;p9:**

- Conceptos importantes:
- • No existe la normalización en estas bases de datos.
- • No existen las relaciones.
- • No se utilizan uniones.
- • Se pueden hacer uso de columnas sin valor.
- • Para almacenar los datos se usan el nombre o el valor de la columna.
- Es muy importante entender que las columnas pueden variar entre filas, es decir, puede haber columnas que tienen un valor para un registro mientras que para otro tiene otro valor o incluso puede ser nula y no existir.
- Hay casos en los que se puede utilizar el nombre de la columna para almacenar también valores.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;126;p9`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;128;p9`, relacion `rId4`, destino interno `../media/image19.png`, descripcion alternativa: no especificada.

### Slide 10

#### Texto

**Google Shape;133;p10:**

- Diseño de una base de datos columnar

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;134;p10`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;135;p10`, relacion `rId4`, destino interno `../media/image19.png`, descripcion alternativa: no especificada.
- Imagen 3: nombre `Google Shape;136;p10`, relacion `rId5`, destino interno `../media/image15.png`, descripcion alternativa: no especificada.

### Slide 11

#### Texto

**Google Shape;141;p11:**

- Base de datos: Cassandra - Introducción

**Google Shape;143;p11:**

- Cassandra es un almacén de datos “orientado a la columna", lo que significa que en lugar de almacenar tuplas idénticas de datos, ordenadas de acuerdo a una estructura  fija (el esquema de una tabla),
- Cassandra almacena  familias de columnas" en keyspaces. Cada familia de columna se compone de  las identificadas por una clave. Es decir, Cassandra asocia un valor de clave con un número variable de pares nombre/valor (columnas) que puede ser totalmente diferente de las otras  filas dentro de la familia de la columna.
- En términos prácticos, se va a suponer que se esta usando Cassandra para almacenar una
- colección de personas. Dentro del almacén de claves “Planeta" hay una familia de columna llamada “Gente", que a su vez tiene  filas con este aspecto:

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;142;p11`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;144;p11`, relacion `rId4`, destino interno `../media/image14.png`, descripcion alternativa: no especificada.

### Slide 12

#### Texto

**Google Shape;149;p12:**

- Base de datos: Cassandra - Introducción

**Google Shape;151;p12:**

- Apache Cassandra es un motor de bases de datos NoSQL, Open
- Source e implementado en Java.
- Fue originalmente creada por Facebook y donada a Apache como
- software libre en 2009.
- Es una de las base de datos NoSQL más relevantes a nivel mundial:
- Netflix, eBay, Twitter, Urban Airship, Constant Contact, Reddit, Cisco,
- OpenX, Digg, CloudKick, Ooyala, …
- Cassandra puede manejar varios terabytes de datos si lo necesita y
- puede, fácilmente, manejar millones de ficheros, incluso en un clúster pequeño (Big Data).
- La información en las bases de datos relacionales, se almacenan en
- forma de filas, pero en Cassandra la información se almacena en
- columnas con pares key-value.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;150;p12`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;152;p12`, relacion `rId4`, destino interno `../media/image22.png`, descripcion alternativa: "Qué es Apache Cassandra | OpenWebinars".

### Slide 13

#### Texto

**Google Shape;157;p13:**

- Base de datos: Cassandra - Introducción

**Google Shape;159;p13:**

- Esquema dinámico. El esquema que define la estructura de los datos puede cambiar en tiempo de ejecución.
- No hay un único punto de fallo. Los datos se replican automáticamente a varios nodos. Perder un nodo no causa la baja del clúster.
- Alta disponibilidad. Los datos estás disponibles la mayor parte del tiempo gracias a la redundancia que introduce la replicación de datos.
- Particionamiento de los datos. La topología de Cassandra es la de un anillo a través del cual se distribuyen los datos para minimizar cuellos de botella en el acceso a los mismos.
- Escalabilidad horizontal. Hasta un alto número de máquinas la capacidad de cómputo aumenta linealmente con el número de máquinas.
- Capacidad para manejar cientos de gigabytes de datos.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;158;p13`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;160;p13`, relacion `rId4`, destino interno `../media/image12.png`, descripcion alternativa: no especificada.

### Slide 14

#### Texto

**Google Shape;165;p14:**

- Base de datos: Cassandra - Arquitectura

**Google Shape;167;p14:**

- Esquema libre
- Cassandra requiere definir un contenedor llamado espacio de claves (keyspaces) que contiene
- “familias de columnas". Es esencialmente un nombre para mantener estas familias de columnas y
- sus propiedades de configuración. Las familias de columnas o columnas comunes son nombres para
- información asociada. Las tablas de datos son dinámicas, así   que se puede agregar información
- usando las columnas que quieras.
- Alto rendimiento
- Cada vez que hay actividad de escritura en uno de los nodos, este lo transcribe en su log de
- actividad para asegurar que haya coherencia. Los datos adem as son almacenados en una estructura
- en memoria (memtable) y, una vez se sobrepasa el tama~no de esta, son escritos en un  chero llamado
- SSTable (Sorted String Table)

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;166;p14`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;168;p14`, relacion `rId4`, destino interno `../media/image13.png`, descripcion alternativa: no especificada.

### Slide 15

#### Texto

**Google Shape;173;p15:**

- Base de datos: Cassandra – Modelo de datos

**Google Shape;175;p15:**

- Column. Es la unidad más básica en el modelo de datos de Cassandra. Una column es un triplete de un key (un nombre) un value (un valor) y un timestamp.
- Los valores son todos suministrados por el cliente. El tipo de dato del key y el value son matrices de bytes de Java, el tipo de dato del timestamp es un long primitive.
- Las column son inmutables para evitar problemas de multithreading.
- Las column se organizan dentro de las columns families.
- Las column se ordenan por un tipo, que pueden ser uno de los siguientes:
- AsciiType
- BytesType
- LongType
- TimeUUIDType
- UTF8Type

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;174;p15`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;176;p15`, relacion `rId4`, destino interno `../media/image21.png`, descripcion alternativa: no especificada.

### Slide 16

#### Texto

**Google Shape;181;p16:**

- Base de datos: Cassandra – Modelo de datos

**Google Shape;183;p16:**

- SuperColumn. Es una column cuyos values son una o más columns, que en este contexto se llamaran subcolumns. Las subcolumns están ordenadas, y el número de columnas que se puede definir es ilimitada. Las Super columns, a diferencias de las columns, no tienen un timestamp definido.
- Column Family. Es más o menos análogo a una tabla en un modelo relacional. Se trata de un contenedor para una colección ordenada de columns. Cada column family se almacena en un archivo separado
- Keyspace. Es el contenedor para las column family. Es más o menos análogo a una base de datos en un modelo relacional, usado en Cassandra para separar aplicaciones. Un keyspace es una colección
- ordenada de columns family.
- Clúster. Conjunto de máquinas que dan soporte a Cassandra y son vistas por los clientes como una única máquina.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;182;p16`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;184;p16`, relacion `rId4`, destino interno `../media/image17.png`, descripcion alternativa: no especificada.

### Slide 17

#### Texto

**Google Shape;189;p17:**

- Base de datos: Cassandra – Modelo de datos

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;190;p17`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;191;p17`, relacion `rId4`, destino interno `../media/image16.png`, descripcion alternativa: no especificada.

### Slide 18

#### Texto

**Google Shape;196;p18:**

- Base de datos: Cassandra – Modelo de datos

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;197;p18`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;198;p18`, relacion `rId4`, destino interno `../media/image20.png`, descripcion alternativa: no especificada.
- Imagen 3: nombre `Google Shape;199;p18`, relacion `rId5`, destino interno `../media/image31.png`, descripcion alternativa: no especificada.

### Slide 19

#### Texto

**Google Shape;204;p19:**

- Base de datos: Cassandra – Modelo de datos - Relaciones

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;205;p19`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;206;p19`, relacion `rId4`, destino interno `../media/image29.png`, descripcion alternativa: no especificada.

### Slide 20

#### Texto

**Google Shape;211;p20:**

- Base de datos: Cassandra – Modelo de datos - Relaciones

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;212;p20`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;213;p20`, relacion `rId4`, destino interno `../media/image32.png`, descripcion alternativa: no especificada.

### Slide 21

#### Texto

**Google Shape;218;p21:**

- ¿Cuál es la estructura del modelo de datos de cassandra?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;219;p21`, relacion `rId3`, destino interno `../media/image27.png`, descripcion alternativa: no especificada.

### Slide 22

#### Texto

**Google Shape;225;p22:**

- Caso práctico

### Slide 23

#### Texto

**Google Shape;231;p23:**

- Caso práctico

**Google Shape;234;p23:**

- Realizar ejercicio práctico es una excelente manera de aprender y entender cómo elaborar un esquema de datos nosql orientada por columnas.Aquí tienes un ejercicio práctico detallado que puedes seguir:
- Ejercicio Práctico:
- Caso de Uso: Sistema de Biblioteca
- Para este caso, el esquema de datos debe manejar libros, autores, usuarios y préstamos
- Objetivos:
- Elaborar el modelo conceptual de datos.
- Elaborar el esquema de datos por entidad.
- Elaborar el esquema de datos de las relaciones entre entidades.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;233;p23`, relacion `rId3`, destino interno `../media/image23.png`, descripcion alternativa: no especificada.

### Slide 24

#### Texto

**Google Shape;240;p24:**

- Sesión 13: Introd. Cassandra

**Google Shape;243;p24:**

- ¿Quién quisiera participar? (2 voluntarios)

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;242;p24`, relacion `rId3`, destino interno `../media/image23.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;244;p24`, relacion `rId3`, destino interno `../media/image23.png`, descripcion alternativa: no especificada.

### Slide 25

#### Texto

**Google Shape;250;p25:**

- Sesión 13: Introd. Cassandra

**Google Shape;252;p25:**

- ¿Qué se les hizo más fácil?
- ¿Qué se les hizo más retador?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;253;p25`, relacion `rId3`, destino interno `../media/image27.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;254;p25`, relacion `rId3`, destino interno `../media/image27.png`, descripcion alternativa: no especificada.

### Slide 26

#### Texto

**Google Shape;260;p26:**

- Aprendizajes

**Google Shape;262;p26:**

- ¿Qué hemos aprendido el día de hoy?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;263;p26`, relacion `rId3`, destino interno `../media/image27.png`, descripcion alternativa: no especificada.

### Slide 27

#### Texto

**Google Shape;269;p27:**

- Conclusiones

**Google Shape;271;p27:**

- Tomar apuntes de manera eficaz ayuda a consolidar el  aprendizaje y prepararse para los exámenes.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;272;p27`, relacion `rId3`, destino interno `../media/image28.png`, descripcion alternativa: no especificada.

### Slide 28

#### Texto

**Google Shape;278;p28:**

- Conclusiones

**Google Shape;280;p28:**

- Cassandra proporciona tolerancia a fallos y disponibilidad, todo ello a cambio de ser consistente de manera eventual,
- Cassandra presenta una arquitectura Peer to Peer, es decir, todos los nodos tienen la misma importancia, todos los nodos serán tratados como nodos primarios y tendrán la misma información que el resto
- Cassandra no es relacional, representa las estructuras de datos como tablas hash multidimensionales, en donde cada registro puede tener una o más columnas, aunque no todos los registros de un mismo tipo deben tener el mismo n umero de columnas

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;281;p28`, relacion `rId3`, destino interno `../media/image28.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;282;p28`, relacion `rId4`, destino interno `../media/image33.png`, descripcion alternativa: no especificada.

### Slide 29

#### Texto

**Google Shape;288;p29:**

- Tarea

**Google Shape;291;p29:**

- Elabora la actividad práctica de acuerdo a la guía de laboratorio de sesión
- Sube la actividad práctica en la  plataforma virtual de aprendizaje
- Guarda la actividad con la siguiente etiqueta:
- BDII_Actividad13_NombreApellido

**Google Shape;292;p29:**

- Nota: No olvides también revisar tu plataforma UTP+Class

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;290;p29`, relacion `rId3`, destino interno `../media/image28.png`, descripcion alternativa: no especificada.

### Slide 30

#### Texto

**Google Shape;298;p30:**

- Recursos

**Google Shape;301;p30:**

- -  Bases de datos relacionales y columnares | | UPV (sf).      https://youtu.be/e_MCU2qHUI4?feature=shared
- Escudero Romero, D. (2015). Estudio del SGBD Cassandra. https://uvadoc.uva.es/bitstream/handle/10324/15204/TFG-G1674.pdf?sequence=1&isAllowed=y
- Taller Apache Cassandra(2013). https://eventos.citius.usc.es/bigdata/workshops/Cassandra.pdf
- Díaz Moreno, A. (2019). Introducción a las bases de datos NoSQL: comparativa MongoDB vs Cassandra. https://ebuah.uah.es/dspace/bitstream/handle/10017/38612/TFG_Diaz_Moreno_2019.pdf?sequence=1&isAllowed=y

**Google Shape;302;p30:**

- Nota: No olvides también revisar tu plataforma UTP+Class

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;300;p30`, relacion `rId3`, destino interno `../media/image28.png`, descripcion alternativa: no especificada.

### Slide 31

#### Texto

**Google Shape;308;p31:**

- Tarea

**Google Shape;310;p31:**

- MUCHAS GRACIAS QUE DIOS LOS BENDIGA!!!

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;309;p31`, relacion `rId3`, destino interno `../media/image28.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;311;p31`, relacion `rId4`, destino interno `../media/image30.jpg`, descripcion alternativa: no especificada.

### Slide 32

#### Texto

_Sin texto extraible en este slide._

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;317;p32`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.
