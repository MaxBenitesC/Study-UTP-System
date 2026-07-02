# Transcripcion: Practica_Laboratorio12_MongoDB_Compass.pdf

## Fuente

- Archivo original: `Practica_Laboratorio12_MongoDB_Compass.pdf`
- Tipo: PDF
- Paginas: 9
- Title: Práctica Laboratorio 12 MongoDB Compass
- Subject: Base de Datos II - MongoDB
- Creator: Microsoft® Word para Microsoft 365
- Producer: Microsoft® Word para Microsoft 365
- CreationDate: Wed Jun 10 18:07:07 2026 -05
- ModDate: Wed Jun 10 18:07:07 2026 -05

## Nota de transcripcion

- El texto fue extraido de la capa de texto del PDF con conservacion de distribucion visual cuando fue posible.
- Las imagenes/graficos se listan como objetos detectados por pagina con sus dimensiones tecnicas; no se describe contenido visual que no tenga texto extraible.
- Las tablas se conservan como texto con espaciado cuando estan presentes en la capa de texto; no se reconstruyen datos que no sean extraibles.

## Contenido por pagina

### Pagina 1

```text
                                                                                       Base de Datos II


          PRÁCTICA DE LABORATORIO N.° 12
MongoDB Compass: sintaxis de consultas, funciones distintivas clave y
                  administración de usuarios


 Curso                                             Base de Datos II

 Unidad                                            Bases de Datos NoSQL

 Herramienta                                       MongoDB Compass y mongosh

 Modalidad                                         Individual o grupos de 2 estudiantes

 Duración estimada                                 90 minutos



Descripción general. Esta práctica está orientada a estudiantes universitarios y se basa en los
temas de la sesión: operaciones CRUD, consultas, agregación, indexación, búsqueda de texto,
operaciones geoespaciales y administración de usuarios en MongoDB.

Logro de aprendizaje. Al finalizar la práctica, el estudiante será capaz de crear una base de datos
NoSQL, insertar documentos, consultar información, actualizar y eliminar registros, crear índices,
ejecutar agregaciones y reconocer la importancia de los roles de usuario.

 Nota: En MongoDB Compass, la mayoría de consultas se escriben en el campo Filter de la
 pestaña Documents. Para comandos como createUser, grantRolesToUser o
 revokeRolesFromUser se recomienda usar mongosh.




                                                                      MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `9 0`, ppi 100x103, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `9 0`, ppi 100x103, tamano `326B`, ratio `3.6%`.

### Pagina 2

```text
                                                                                        Base de Datos II


Caso práctico: Sistema de delivery universitario
Contexto. Una universidad desea implementar un sistema NoSQL para registrar pedidos realizados
por estudiantes dentro del campus. El sistema debe almacenar información sobre estudiantes,
productos, pedidos, pagos, comentarios y ubicaciones de entrega.

 Base de datos                                      delivery_universitario

 Colección principal                                pedidos

 Tipo de datos                                      Documentos JSON


Parte 1. Crear la base de datos y la colección
1. Abrir MongoDB Compass.

2. Conectarse al servidor local:

  mongodb://localhost:27017

3. Hacer clic en Connect.

4. Hacer clic en Create Database y completar:

  Database Name: delivery_universitario
  Collection Name: pedidos


Parte 2. Insertar documentos
Ingresar a delivery_universitario > pedidos y seleccionar Add Data > Insert Document. Insertar los
siguientes documentos en formato JSON:

  [
      {
        "codigo_pedido": "P001",
        "estudiante": {
           "codigo": "U2024001",
           "nombre": "Luis Ramos",
           "facultad": "Ingeniería",
           "ciclo": 5
        },
        "producto": "Hamburguesa clásica",
        "categoria": "Comida rápida",
        "cantidad": 2,
        "precio_unitario": 12.50,
        "estado": "Entregado",
        "metodo_pago": "Yape",
        "fecha": "2026-06-01",
        "ubicacion": { "type": "Point", "coordinates": [-77.0428, -12.0464] },
        "comentario": "Entrega rápida y producto en buen estado"
      },
      {
        "codigo_pedido": "P002",


                                                                       MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 3

```text
                                                                                 Base de Datos II

  "estudiante": {
     "codigo": "U2024002",
     "nombre": "Ana Torres",
     "facultad": "Arquitectura",
     "ciclo": 3
  },
  "producto": "Café americano",
  "categoria": "Bebida",
  "cantidad": 1,
  "precio_unitario": 6.00,
  "estado": "Pendiente",
  "metodo_pago": "Efectivo",
  "fecha": "2026-06-01",
  "ubicacion": { "type": "Point", "coordinates": [-77.0435, -12.0470] },
  "comentario": "Solicita entrega en biblioteca"
},
{
  "codigo_pedido": "P003",
  "estudiante": {
     "codigo": "U2024003",
     "nombre": "Carlos Medina",
     "facultad": "Sistemas",
     "ciclo": 6
  },
  "producto": "Pizza personal",
  "categoria": "Comida rápida",
  "cantidad": 1,
  "precio_unitario": 15.00,
  "estado": "Entregado",
  "metodo_pago": "Plin",
  "fecha": "2026-06-02",
  "ubicacion": { "type": "Point", "coordinates": [-77.0419, -12.0458] },
  "comentario": "Pedido entregado sin inconvenientes"
},
{
  "codigo_pedido": "P004",
  "estudiante": {
     "codigo": "U2024004",
     "nombre": "María López",
     "facultad": "Administración",
     "ciclo": 4
  },
  "producto": "Ensalada saludable",
  "categoria": "Saludable",
  "cantidad": 2,
  "precio_unitario": 10.00,
  "estado": "Cancelado",
  "metodo_pago": "Tarjeta",
  "fecha": "2026-06-02",
  "ubicacion": { "type": "Point", "coordinates": [-77.0442, -12.0468] },
  "comentario": "Cancelado por demora en atención"
},
{
  "codigo_pedido": "P005",
  "estudiante": {
     "codigo": "U2024005",

                                                                MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 4

```text
                                                                                        Base de Datos II

           "nombre": "Jorge Castillo",
           "facultad": "Ingeniería",
           "ciclo": 7
         },
         "producto": "Jugo natural",
         "categoria": "Bebida",
         "cantidad": 3,
         "precio_unitario": 5.50,
         "estado": "Entregado",
         "metodo_pago": "Yape",
         "fecha": "2026-06-03",
         "ubicacion": { "type": "Point", "coordinates": [-77.0420, -12.0475] },
         "comentario": "Buena atención y entrega rápida"
     }
 ]

  Pregunta: ¿Cuántos documentos se insertaron correctamente?

Parte 3. Consultas básicas en MongoDB Compass
Consulta 1. Mostrar todos los pedidos
 {}

  Pregunta: ¿Cuántos documentos aparecen en la colección?

Consulta 2. Buscar pedidos entregados
 { "estado": "Entregado" }

  Pregunta: ¿Qué pedidos ya fueron entregados?

Consulta 3. Buscar pedidos de Ingeniería
 { "estudiante.facultad": "Ingeniería" }

  Pregunta: ¿Cuántos pedidos fueron realizados por estudiantes de Ingeniería?

Consulta 4. Cantidad mayor que 1
 { "cantidad": { "$gt": 1 } }

  Pregunta: ¿Qué productos tienen una cantidad mayor a 1?

Consulta 5. Precio unitario menor o igual a 10
 { "precio_unitario": { "$lte": 10 } }

  Pregunta: ¿Qué productos cuestan 10 soles o menos?

Parte 4. Consultas con operadores lógicos
Consulta 6. Pedidos entregados y pagados con Yape
 {
     "$and": [

                                                                       MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 5

```text
                                                                                     Base de Datos II

          { "estado": "Entregado" },
          { "metodo_pago": "Yape" }
      ]
  }

  Pregunta: ¿Qué estudiantes pagaron con Yape y recibieron su pedido?

Consulta 7. Pedidos de categoría Bebida o Saludable
  {
      "$or": [
        { "categoria": "Bebida" },
        { "categoria": "Saludable" }
      ]
  }

  Pregunta: ¿Qué productos pertenecen a las categorías Bebida o Saludable?

Consulta 8. Pedidos que no estén entregados
  { "estado": { "$ne": "Entregado" } }

  Pregunta: ¿Qué pedidos aún no fueron entregados?

Parte 5. Actualización de documentos
 Nota: Para los siguientes comandos abrir mongosh y ejecutar use delivery_universitario.

  use delivery_universitario


Actividad 1. Actualizar un pedido pendiente
  db.pedidos.updateOne(
    { "codigo_pedido": "P002" },
    { "$set": { "estado": "Entregado" } }
  )

  Pregunta: Verifique en Compass si el estado del pedido P002 cambió correctamente.

Actividad 2. Actualizar varios pedidos
  db.pedidos.updateMany(
    { "metodo_pago": "Yape" },
    { "$set": { "promocion": "Cliente frecuente" } }
  )

  Pregunta: ¿Qué documentos fueron modificados?

Parte 6. Eliminación de documentos
Actividad 3. Eliminar un pedido cancelado
  db.pedidos.deleteOne(
    { "estado": "Cancelado" }
  )


                                                                    MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 6

```text
                                                                                      Base de Datos II

  Pregunta: Compruebe con el filtro { "estado": "Cancelado" }. ¿El documento fue eliminado
  correctamente?

Parte 7. Agregaciones en MongoDB Compass
Ingresar a la pestaña Aggregations de la colección pedidos. Crear los siguientes pipelines:

Agregación 1. Total de pedidos por categoría
  [
      {
          "$group": {
            "_id": "$categoria",
            "total_pedidos": { "$sum": 1 }
          }
      }
  ]

  Pregunta: ¿Qué categoría tiene mayor cantidad de pedidos?

Agregación 2. Monto total vendido por pedido
  [
      {
          "$project": {
            "producto": 1,
            "total_venta": {
              "$multiply": ["$cantidad", "$precio_unitario"]
            }
          }
      }
  ]

  Pregunta: ¿Cuánto generó cada pedido?

Agregación 3. Total vendido por método de pago
  [
      {
        "$project": {
          "metodo_pago": 1,
          "total": {
            "$multiply": ["$cantidad", "$precio_unitario"]
          }
        }
      },
      {
        "$group": {
          "_id": "$metodo_pago",
          "monto_total": { "$sum": "$total" }
        }
      }
  ]

  Pregunta: ¿Qué método de pago generó mayor monto de venta?


                                                                     MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 7

```text
                                                                                    Base de Datos II


Parte 8. Indexación
Crear un índice sobre el nombre del estudiante desde Compass: pestaña Indexes > Create Index >
Field estudiante.nombre > Type 1 ascending.

  db.pedidos.createIndex({ "estudiante.nombre": 1 })

  Pregunta: ¿Por qué sería útil crear un índice sobre el nombre del estudiante?

Parte 9. Búsqueda de texto
  db.pedidos.createIndex({ "comentario": "text" })

Buscar documentos que contengan la palabra rápida:

  db.pedidos.find({ "$text": { "$search": "rápida" } })

  Pregunta: ¿Qué pedidos contienen comentarios relacionados con rapidez?




Parte 10. Consulta geoespacial
  db.pedidos.createIndex({ "ubicacion": "2dsphere" })

Buscar pedidos cercanos a un punto de referencia dentro del campus:

  db.pedidos.find({
    "ubicacion": {
      "$near": {
        "$geometry": {
          "type": "Point",
          "coordinates": [-77.0428, -12.0464]
        },
        "$maxDistance": 500
      }
    }
  })

  Pregunta: ¿Qué pedidos se encuentran cerca del punto de referencia?

Parte 11. Administración de usuarios
Esta sección debe realizarse preferentemente en mongosh. Permite comprender el control de
acceso mediante usuarios y roles.

  use delivery_universitario

  db.createUser({
    user: "usuario_delivery",
    pwd: "Delivery123",
    roles: [
      { role: "readWrite", db: "delivery_universitario" }



                                                                   MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 8

```text
                                                                                   Base de Datos II

    ]
  })


Autenticar usuario
  db.auth("usuario_delivery", "Delivery123")


Asignar rol dbAdmin
  db.grantRolesToUser(
    "usuario_delivery",
    [
      { role: "dbAdmin", db: "delivery_universitario" }
    ]
  )


Revocar rol dbAdmin
  db.revokeRolesFromUser(
    "usuario_delivery",
    [
      { role: "dbAdmin", db: "delivery_universitario" }
    ]
  )

Preguntas de análisis:

1. ¿Qué diferencia existe entre el rol read, readWrite y dbAdmin?
2. ¿Por qué no todos los usuarios deberían tener permisos administrativos?
3. ¿Qué riesgos existen si un usuario tiene demasiados privilegios?

Parte 12. Ejercicios propuestos
Ejercicio 1. Mostrar pedidos realizados por estudiantes del ciclo 5 o superior
  { "estudiante.ciclo": { "$gte": 5 } }


Ejercicio 2. Mostrar pedidos cuyo método de pago sea Yape o Plin
  {
      "$or": [
        { "metodo_pago": "Yape" },
        { "metodo_pago": "Plin" }
      ]
  }


Ejercicio 3. Mostrar pedidos cuyo precio unitario esté entre 5 y 12 soles
  {
      "precio_unitario": {
        "$gte": 5,
        "$lte": 12
      }
  }


                                                                  MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.

### Pagina 9

```text
                                                                                     Base de Datos II

Ejercicio 4. Actualizar el pedido P003 agregando calificación 5
    db.pedidos.updateOne(
      { "codigo_pedido": "P003" },
      { "$set": { "calificacion": 5 } }
    )


Ejercicio 5. Crear una agregación que muestre la cantidad total de productos
vendidos por categoría
    [
        {
            "$group": {
              "_id": "$categoria",
              "total_productos_vendidos": { "$sum": "$cantidad" }
            }
        }
    ]




Parte 13. Evidencias de entrega
•       Captura de la base de datos creada en MongoDB Compass.
•       Captura de la colección pedidos.
•       Capturas de mínimo 5 consultas realizadas.
•       Captura de una actualización.
•       Captura de una eliminación.
•       Captura de una agregación.
•       Captura del índice creado.
•       Respuestas a las preguntas de análisis.
•       Conclusión personal de mínimo 8 líneas.

    Nombre del archivo: Laboratorio12_Nombres_Apellidos


Conclusión de la práctica
En esta práctica, el estudiante trabaja con MongoDB Compass aplicando operaciones CRUD,
consultas con operadores, agregaciones, índices, búsqueda textual, datos geoespaciales y
administración de usuarios. Estas actividades permiten comprender cómo MongoDB organiza
información en documentos flexibles y cómo puede utilizarse para resolver problemas reales en
sistemas modernos.




                                                                    MG. CARLOS IGNACIO FLORES RUJEL
```

**Imagenes/graficos detectados en la pagina:**

- Objeto 1: tipo `image`, dimensiones 694x13, color `rgb`, componentes `3`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `48B`, ratio `0.2%`.
- Objeto 2: tipo `smask`, dimensiones 694x13, color `gray`, componentes `1`, bpc `8`, codificacion `image`, interpolacion `no`, object ID `15 0`, ppi 100x107, tamano `326B`, ratio `3.6%`.
