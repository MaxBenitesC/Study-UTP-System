# Transcripcion: S14_s1 - Cassandra consultas funciones.pptx

## Fuente

- Archivo original: `S14_s1 - Cassandra consultas funciones.pptx`
- Tipo: PowerPoint PPTX
- Slides: 27
- Recursos multimedia internos: 16
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

- SESIÓN N° 14:
- Cassandra:
- BD NoSQL - Cassandra::
- Sintaxis de consultas y funciones distintivas clave
- Administración de usuarios

**Google Shape;50;p1:**

- DOCENTES DE UTP
- SISTEMAS

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;52;p1`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.

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
- Imagen 2: nombre `Google Shape;63;p2`, relacion `rId4`, destino interno `../media/image7.png`, descripcion alternativa: no especificada.

### Slide 3

#### Texto

**Google Shape;70;p3:**

- Motivación para el aprendizaje

**Google Shape;72;p3:**

- ¿Enfoque de consulta relacional o columnar?

**Google Shape;73;p3:**

- https://youtu.be/5LTs-G308wY?feature=shared

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
- Imagen 2: nombre `Google Shape;90;p5`, relacion `rId4`, destino interno `../media/image19.png`, descripcion alternativa: no especificada.
- Imagen 3: nombre `Google Shape;91;p5`, relacion `rId5`, destino interno `../media/image14.png`, descripcion alternativa: no especificada.
- Imagen 4: nombre `Google Shape;92;p5`, relacion `rId6`, destino interno `../media/image22.png`, descripcion alternativa: no especificada.
- Imagen 5: nombre `Google Shape;93;p5`, relacion `rId7`, destino interno `../media/image18.png`, descripcion alternativa: no especificada.

### Slide 6

#### Texto

**Google Shape;99;p6:**

- Logro de la sesión

**Google Shape;101;p6:**

- Al final de la sesión el estudiante comprende los fundamentos y uso de  bases de datos no relacional Cassandra con CQL
- .

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;102;p6`, relacion `rId3`, destino interno `../media/image6.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;103;p6`, relacion `rId4`, destino interno `../media/image21.png`, descripcion alternativa: no especificada.

### Slide 7

#### Texto

**Google Shape;109;p7:**

- Importancia de la sesión

**Google Shape;111;p7:**

- Apache Cassandra es una base de datos NoSQL diseñada para manejar grandes cantidades de datos a través de muchos servidores sin un punto único de falla. Tiene una sintaxis y funcionalidades específicas que la distinguen de otras bases de datos. Vamos a desglosar algunos aspectos clave, incluyendo la sintaxis de consultas, funciones distintivas y la administración de usuarios.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;112;p7`, relacion `rId3`, destino interno `../media/image5.png`, descripcion alternativa: no especificada.

### Slide 8

#### Texto

**Google Shape;117;p8:**

- Cassandra:
- Sintaxis de consultas y funciones distintivas clave
- Administración de usuarios

**Google Shape;118;p8:**

- SESIÓN N° 14:

**Google Shape;120;p8:**

- Temario

**Google Shape;122;p8:**

- ¡Que no se te escape nada de tu clase!:

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;121;p8`, relacion `rId3`, destino interno `../media/image4.png`, descripcion alternativa: no especificada.

### Slide 9

#### Texto

**Google Shape;127;p9:**

- Base de datos: Cassandra – Sintaxis de Consultas en Cassandra

**Google Shape;129;p9:**

- Cassandra usa CQL (Cassandra Query Language), que es similar a SQL pero adaptado para el modelo de datos de Cassandra.
- 1. Creación de una Keyspace:
- CREATE KEYSPACE nombre_del_keyspace
- WITH REPLICATION = {
- 'class': 'SimpleStrategy',
- 'replication_factor': 3
- };
- - `SimpleStrategy` es adecuado para entornos de desarrollo o pruebas. Para producción, se recomienda `NetworkTopologyStrategy`.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;128;p9`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;130;p9`, relacion `rId4`, destino interno `../media/image2.png`, descripcion alternativa: no especificada.

### Slide 10

#### Texto

**Google Shape;135;p10:**

- Base de datos: Cassandra – Sintaxis de Consultas en Cassandra

**Google Shape;137;p10:**

- 2. Creación de una Tabla:
- CREATE TABLE nombre_del_keyspace.nombre_de_la_tabla (
- id UUID PRIMARY KEY,
- nombre TEXT,
- edad INT
- );
- - `PRIMARY KEY` en Cassandra incluye la partición y las claves de clustering.
- 3. Inserción de Datos:
- INSERT INTO nombre_del_keyspace.nombre_de_la_tabla (id, nombre, edad)
- VALUES (uuid(), 'Juan', 30);

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;136;p10`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;138;p10`, relacion `rId4`, destino interno `../media/image2.png`, descripcion alternativa: no especificada.

### Slide 11

#### Texto

**Google Shape;143;p11:**

- Base de datos: Cassandra – Sintaxis de Consultas en Cassandra

**Google Shape;145;p11:**

- 4. Consulta de Datos:
- SELECT * FROM nombre_del_keyspace.nombre_de_la_tabla WHERE id = some_uuid;
- - Nota: Las consultas en Cassandra deben ser realizadas por la clave de partición. Las búsquedas secundarias pueden requerir índices o estructuras adicionales.
- 5. Actualización de Datos:
- UPDATE nombre_del_keyspace.nombre_de_la_tabla
- SET edad = 31
- WHERE id = some_uuid;
- 6. Eliminación de Datos:
- DELETE FROM nombre_del_keyspace.nombre_de_la_tabla
- WHERE id = some_uuid;
- 7. Eliminación de una Tabla:
- DROP TABLE nombre_del_keyspace.nombre_de_la_tabla;

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;144;p11`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;146;p11`, relacion `rId4`, destino interno `../media/image2.png`, descripcion alternativa: no especificada.

### Slide 12

#### Texto

**Google Shape;151;p12:**

- Base de datos: Cassandra – Funciones Distintivas Clave

**Google Shape;153;p12:**

- 1. Modelado de Datos:
- - Cassandra utiliza un modelo de datos basado en columnas y no en filas. Los datos se organizan en "column families", que son análogas a las tablas en bases de datos relacionales.
- 2. Clustering:
- - Las tablas en Cassandra pueden tener claves de clustering que definen el orden de los datos dentro de una partición.
- 3. Escalabilidad Horizontal:
- - Cassandra está diseñada para ser escalable horizontalmente, lo que significa que se pueden añadir más nodos para aumentar el rendimiento y la capacidad.
- 4. Consistencia Eventual:
- - Cassandra ofrece opciones de consistencia configurables (e.g., QUORUM, ONE, ALL) para gestionar la consistencia y disponibilidad.
- 5. Replica y Recuperación:
- - Los datos se replican en múltiples nodos, lo que garantiza la disponibilidad y la tolerancia a fallos.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;152;p12`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.

### Slide 13

#### Texto

**Google Shape;158;p13:**

- Base de datos: Cassandra – Administración de Usuarios

**Google Shape;160;p13:**

- 1. Creación de Usuarios:
- CREATE USER nombre_usuario WITH PASSWORD 'contraseña' SUPERUSER;
- 2. Asignación de Roles y Permisos:
- GRANT ALL PERMISSIONS ON KEYSPACE nombre_del_keyspace TO nombre_usuario;
- - Puedes especificar permisos más granulares, como `SELECT`, `MODIFY`, etc.
- 3. Eliminación de Usuarios:
- DROP USER nombre_usuario;
- 4. Cambio de Contraseña:
- ALTER USER nombre_usuario WITH PASSWORD 'nueva_contraseña’;
- 5. Ver Usuarios y Roles:
- SELECT * FROM system_auth.roles;

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;159;p13`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.

### Slide 14

#### Texto

**Google Shape;165;p14:**

- Base de datos: Cassandra – Funciones Agregadas y de Consulta

**Google Shape;167;p14:**

- 1.Contar Filas:
- SELECT COUNT(*) FROM nombre_del_keyspace.nombre_de_la_tabla;
- 2.Sumar Valores:
- SELECT SUM(edad) FROM nombre_del_keyspace.nombre_de_la_tabla;
- 3.Clustering y Diseño de Tablas
- Diseño de Claves de Clustering:
- CREATE TABLE nombre_del_keyspace.nombre_de_la_tabla (
- id UUID,
- fecha TIMESTAMP,
- nombre TEXT,
- PRIMARY KEY (id, fecha)
- ) WITH CLUSTERING ORDER BY (fecha DESC);
- - Aquí, `id` es la clave de partición y `fecha` es la clave de clustering. Los datos se ordenan por `fecha` en orden descendente.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;166;p14`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.

### Slide 15

#### Texto

**Google Shape;172;p15:**

- Base de datos: Cassandra – Funciones Agregadas y de Consulta

**Google Shape;174;p15:**

- 4.Consulta de Metadatos
- Para ver la estructura de un Keyspace o una tabla, puedes usar las siguientes consultas:
- Mostrar Keyspaces:
- DESCRIBE KEYSPACES;
- Mostrar Tablas:
- DESCRIBE TABLE nombre_del_keyspace.nombre_de_la_tabla;
- Mostrar Esquema de la Tabla:
- SELECT * FROM system_schema.tables WHERE keyspace_name = 'nombre_del_keyspace';

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;173;p15`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.

### Slide 16

#### Texto

**Google Shape;179;p16:**

- ¿Qué diferencias observa entre SQL y CQL?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;180;p16`, relacion `rId3`, destino interno `../media/image9.png`, descripcion alternativa: no especificada.

### Slide 17

#### Texto

**Google Shape;186;p17:**

- Caso práctico

### Slide 18

#### Texto

**Google Shape;192;p18:**

- Caso práctico

**Google Shape;195;p18:**

- Realizar ejercicio práctico es una excelente manera de aprender y entender cómo elaborar un esquema de datos nosql orientada por columnas.Aquí tienes un ejercicio práctico detallado que puedes seguir:
- Ejercicio Práctico:
- Caso de Uso: Sistema de Biblioteca
- Para este caso, el esquema de datos debe manejar libros, autores, usuarios y préstamos
- Objetivos:
- Elaborar  la sintaxis CQL de creación de la base de datos NOSql
- Elaborar la sintaxis CQL de creación de tablas
- Elaborar la sintaxis CQL de búsqueda de libros por títulos
- Elaborar la sixtaxis CQL de búsqueda de préstamos por usuarios

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;194;p18`, relacion `rId3`, destino interno `../media/image17.png`, descripcion alternativa: no especificada.

### Slide 19

#### Texto

**Google Shape;201;p19:**

- Sesión 14 Introd. Cassandra

**Google Shape;204;p19:**

- ¿Quién quisiera participar? (2 voluntarios)

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;203;p19`, relacion `rId3`, destino interno `../media/image17.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;205;p19`, relacion `rId3`, destino interno `../media/image17.png`, descripcion alternativa: no especificada.

### Slide 20

#### Texto

**Google Shape;211;p20:**

- Sesión 14: Introd. Cassandra

**Google Shape;213;p20:**

- ¿Qué se les hizo más fácil?
- ¿Qué se les hizo más retador?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;214;p20`, relacion `rId3`, destino interno `../media/image9.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;215;p20`, relacion `rId3`, destino interno `../media/image9.png`, descripcion alternativa: no especificada.

### Slide 21

#### Texto

**Google Shape;221;p21:**

- Aprendizajes

**Google Shape;223;p21:**

- ¿Qué hemos aprendido el día de hoy?

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;224;p21`, relacion `rId3`, destino interno `../media/image9.png`, descripcion alternativa: no especificada.

### Slide 22

#### Texto

**Google Shape;230;p22:**

- Conclusiones

**Google Shape;232;p22:**

- Tomar apuntes de manera eficaz ayuda a consolidar el  aprendizaje y prepararse para los exámenes.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;233;p22`, relacion `rId3`, destino interno `../media/image20.png`, descripcion alternativa: no especificada.

### Slide 23

#### Texto

**Google Shape;239;p23:**

- Conclusiones

**Google Shape;241;p23:**

- Apache Cassandra ofrece una robusta plataforma para manejar grandes volúmenes de datos con alta disponibilidad y escalabilidad. La sintaxis de CQL es accesible para aquellos familiarizados con SQL, aunque hay diferencias importantes debido al modelo de datos y el enfoque en la escalabilidad. La administración de usuarios y roles permite un control granular sobre el acceso y la seguridad en el entorno de Cassandra
- CQL es una interfaz poderosa pero específica para interactuar con Cassandra. Mientras que es similar a SQL en algunos aspectos, es importante entender las diferencias en el modelo de datos y en la forma en que Cassandra maneja la consistencia y la escalabilidad. La clave para usar CQL de manera efectiva es diseñar tus tablas para optimizar las consultas más comunes, dado que el modelo de datos de Cassandra es muy diferente al de las bases de datos relacionales tradicionales.

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;242;p23`, relacion `rId3`, destino interno `../media/image20.png`, descripcion alternativa: no especificada.

### Slide 24

#### Texto

**Google Shape;248;p24:**

- Tarea

**Google Shape;251;p24:**

- Elabora la actividad práctica de acuerdo a la guía de laboratorio de sesión
- Sube la actividad práctica en la  plataforma virtual de aprendizaje
- Guarda la actividad con la siguiente etiqueta:
- BDII_Actividad14_NombreApellido

**Google Shape;252;p24:**

- Nota: No olvides también revisar tu plataforma UTP+Class

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;250;p24`, relacion `rId3`, destino interno `../media/image20.png`, descripcion alternativa: no especificada.

### Slide 25

#### Texto

**Google Shape;258;p25:**

- Recursos

**Google Shape;261;p25:**

- -  Bases de datos relacionales y columnares | | UPV (sf).      https://youtu.be/e_MCU2qHUI4?feature=shared
- Escudero Romero, D. (2015). Estudio del SGBD Cassandra. https://uvadoc.uva.es/bitstream/handle/10324/15204/TFG-G1674.pdf?sequence=1&isAllowed=y
- Taller Apache Cassandra(2013). https://eventos.citius.usc.es/bigdata/workshops/Cassandra.pdf
- Díaz Moreno, A. (2019). Introducción a las bases de datos NoSQL: comparativa MongoDB vs Cassandra. https://ebuah.uah.es/dspace/bitstream/handle/10017/38612/TFG_Diaz_Moreno_2019.pdf?sequence=1&isAllowed=y

**Google Shape;262;p25:**

- Nota: No olvides también revisar tu plataforma UTP+Class

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;260;p25`, relacion `rId3`, destino interno `../media/image20.png`, descripcion alternativa: no especificada.

### Slide 26

#### Texto

**Google Shape;268;p26:**

- Tarea

**Google Shape;270;p26:**

- MUCHAS GRACIAS QUE DIOS LOS BENDIGA!!!

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;269;p26`, relacion `rId3`, destino interno `../media/image20.png`, descripcion alternativa: no especificada.
- Imagen 2: nombre `Google Shape;271;p26`, relacion `rId4`, destino interno `../media/image12.jpg`, descripcion alternativa: no especificada.

### Slide 27

#### Texto

_Sin texto extraible en este slide._

#### Imagenes/graficos detectados

- Imagen 1: nombre `Google Shape;277;p27`, relacion `rId3`, destino interno `../media/image11.png`, descripcion alternativa: no especificada.
