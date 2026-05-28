# Guía Completa: Cómo Crear una Base de Datos desde Cero
**Curso:** Base de Datos II | UTP

> Esta guía responde la pregunta: **¿Qué es lo primero que hay que hacer cuando me piden crear una base de datos?**

---

# EL PROCESO COMPLETO (siempre el mismo orden)

```
ENUNCIADO
    │
    ▼
PASO 1: Leer y entender el problema
    │
    ▼
PASO 2: Identificar ENTIDADES (los "objetos" del mundo real)
    │
    ▼
PASO 3: Identificar ATRIBUTOS de cada entidad
    │
    ▼
PASO 4: Identificar RELACIONES entre entidades
    │
    ▼
PASO 5: Determinar CARDINALIDAD de cada relación (1:1 / 1:N / N:M)
    │
    ▼
PASO 6: Resolver relaciones N:M con tablas intermedias
    │
    ▼
PASO 7: Dibujar el MER (Modelo Entidad-Relación)
    │
    ▼
PASO 8: Crear la BASE DE DATOS en SQL
    │
    ▼
PASO 9: Crear las TABLAS con PK y FK
    │
    ▼
PASO 10: Insertar DATOS de prueba
```

---

# ¿QUÉ ES CADA COSA?

## Entidad
Un "objeto" o "cosa" del mundo real sobre la que necesitamos guardar información.
> Truco: son los **sustantivos** del enunciado → Cliente, Producto, Empleado, Libro

## Atributo
Las características o datos que describem una entidad.
> Truco: son las **características** que necesitas guardar → nombre, apellido, precio, fecha

## Relación
La conexión entre dos entidades.
> Truco: son los **verbos** del enunciado → "un cliente **realiza** una compra", "un autor **escribe** un libro"

## Cardinalidad (¡esto es clave!)

| Tipo | Significado | Ejemplo |
|------|-------------|---------|
| **1:1** | Un registro se relaciona con exactamente un registro del otro lado | Una persona tiene un DNI |
| **1:N** | Un registro se relaciona con muchos del otro lado | Un cliente tiene muchas órdenes |
| **N:M** | Muchos de un lado se relacionan con muchos del otro | Un estudiante toma muchos cursos, un curso tiene muchos estudiantes |

## Clave Primaria (PK - Primary Key)
El identificador único de cada fila de una tabla. Nunca puede repetirse ni ser NULL.

## Clave Foránea (FK - Foreign Key)
Un campo que apunta a la PK de otra tabla. Establece la relación entre tablas.

---

# CÓMO RESOLVER UNA RELACIÓN N:M

Cuando encuentras una relación Muchos a Muchos, **SIEMPRE** debes crear una **tabla intermedia**:

```
ANTES (N:M - imposible implementar así):
    Estudiante ←——————→ Curso

DESPUÉS (con tabla intermedia):
    Estudiante (1) ←——→ (N) Estudiante_Curso (N) ←——→ (1) Curso

La tabla intermedia contiene:
    - FK hacia Estudiante (id_estudiante)
    - FK hacia Curso (id_curso)
    - Ambas juntas forman la PK compuesta
    - Puede tener atributos propios (ej: nota, fecha_inscripcion)
```

---

# EJEMPLO 1: Sistema de Biblioteca

## Enunciado
> Una biblioteca necesita un sistema para gestionar sus libros, autores y préstamos. Cada libro puede tener uno o varios autores. Los socios de la biblioteca pueden pedir prestado varios libros. Se requiere saber qué libros están prestados, a quién y desde cuándo. Cada libro puede ser prestado muchas veces a lo largo del tiempo.

---

## PASO 1: Leer y entender

Necesitamos gestionar: libros, autores, socios y préstamos.

---

## PASO 2: Identificar Entidades

Busco los **sustantivos** importantes del enunciado:

- **Libro** → necesito guardar info de los libros
- **Autor** → necesito guardar info de los autores
- **Socio** → necesito guardar info de quien pide prestado
- **Prestamo** → necesito guardar el registro del préstamo

---

## PASO 3: Identificar Atributos

| Entidad | Atributos |
|---------|-----------|
| **Libro** | id_libro (PK), titulo, año_publicacion, isbn, genero |
| **Autor** | id_autor (PK), nombre, apellido, nacionalidad |
| **Socio** | id_socio (PK), nombre, apellido, telefono, email, fecha_registro |
| **Prestamo** | id_prestamo (PK), fecha_prestamo, fecha_devolucion, estado |

---

## PASO 4 y 5: Identificar Relaciones y Cardinalidad

| Relación | Tipo | Razonamiento |
|---------|------|-------------|
| Libro ↔ Autor | **N:M** | Un libro puede tener varios autores. Un autor puede escribir varios libros |
| Socio ↔ Prestamo | **1:N** | Un socio puede tener muchos préstamos. Un préstamo pertenece a un solo socio |
| Libro ↔ Prestamo | **1:N** | Un libro puede prestarse muchas veces. Un préstamo es de un libro específico |

---

## PASO 6: Resolver N:M

La relación Libro ↔ Autor es N:M → creo tabla intermedia **Libro_Autor**

```
Libro (1) ←——→ (N) Libro_Autor (N) ←——→ (1) Autor
```

---

## PASO 7: MER Resultante

```
┌─────────────┐        ┌──────────────┐        ┌─────────────┐
│    AUTOR    │        │  LIBRO_AUTOR │        │    LIBRO    │
│─────────────│        │──────────────│        │─────────────│
│ id_autor PK │◄──(N)──│ id_autor  FK │──(N)──►│ id_libro PK │
│ nombre      │        │ id_libro  FK │        │ titulo      │
│ apellido    │        └──────────────┘        │ año_pub     │
│ nacionalidad│                                │ isbn        │
└─────────────┘                                │ genero      │
                                               └──────┬──────┘
                                                      │ (1)
                                                      │
                                                     (N)
                                               ┌──────┴──────┐
                                               │  PRESTAMO   │
                                               │─────────────│
                                               │id_prestamo  │PK
                                               │fecha_prest. │
                                               │fecha_devol. │
                                               │estado       │
                                               │id_libro   FK│
                                               │id_socio   FK│
                                               └──────┬──────┘
                                                      │ (N)
                                                      │
                                                     (1)
                                               ┌──────┴──────┐
                                               │    SOCIO    │
                                               │─────────────│
                                               │ id_socio  PK│
                                               │ nombre      │
                                               │ apellido    │
                                               │ telefono    │
                                               │ email       │
                                               │fecha_registro│
                                               └─────────────┘
```

---

## PASO 8, 9 y 10: SQL Completo

```sql
-- =============================================
-- PASO 8: Crear la base de datos
-- =============================================
CREATE DATABASE biblioteca;

-- Conectarse a la base de datos (en psql)
\c biblioteca

-- =============================================
-- PASO 9: Crear las tablas (orden importante:
-- primero las que NO tienen FK, luego las que sí)
-- =============================================

-- 1. Tabla AUTOR (sin FK)
CREATE TABLE autor (
    id_autor     SERIAL PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    apellido     VARCHAR(100) NOT NULL,
    nacionalidad VARCHAR(80)
);

-- 2. Tabla LIBRO (sin FK)
CREATE TABLE libro (
    id_libro        SERIAL PRIMARY KEY,
    titulo          VARCHAR(200) NOT NULL,
    anio_publicacion INTEGER,
    isbn            VARCHAR(20) UNIQUE,
    genero          VARCHAR(80)
);

-- 3. Tabla SOCIO (sin FK)
CREATE TABLE socio (
    id_socio        SERIAL PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    apellido        VARCHAR(100) NOT NULL,
    telefono        VARCHAR(20),
    email           VARCHAR(150),
    fecha_registro  DATE DEFAULT CURRENT_DATE
);

-- 4. Tabla intermedia LIBRO_AUTOR (tiene FK a libro y autor)
CREATE TABLE libro_autor (
    id_libro  INT NOT NULL REFERENCES libro(id_libro),
    id_autor  INT NOT NULL REFERENCES autor(id_autor),
    PRIMARY KEY (id_libro, id_autor)
);

-- 5. Tabla PRESTAMO (tiene FK a libro y socio)
CREATE TABLE prestamo (
    id_prestamo      SERIAL PRIMARY KEY,
    fecha_prestamo   DATE NOT NULL DEFAULT CURRENT_DATE,
    fecha_devolucion DATE,
    estado           VARCHAR(20) DEFAULT 'activo' CHECK (estado IN ('activo', 'devuelto')),
    id_libro         INT NOT NULL REFERENCES libro(id_libro),
    id_socio         INT NOT NULL REFERENCES socio(id_socio)
);

-- =============================================
-- PASO 10: Insertar datos de prueba
-- =============================================

-- Insertar autores
INSERT INTO autor (nombre, apellido, nacionalidad) VALUES
('Gabriel', 'García Márquez', 'Colombiano'),
('Isabel',  'Allende',        'Chilena'),
('Mario',   'Vargas Llosa',   'Peruano'),
('Jorge',   'Amado',          'Brasileño');

-- Insertar libros
INSERT INTO libro (titulo, anio_publicacion, isbn, genero) VALUES
('Cien años de soledad',    1967, '978-0307474728', 'Realismo mágico'),
('La casa de los espíritus', 1982, '978-1501117015', 'Realismo mágico'),
('La ciudad y los perros',  1963, '978-8420471839', 'Novela'),
('El amor en los tiempos del cólera', 1985, '978-0307389732', 'Romance');

-- Relacionar libros con autores (tabla intermedia)
INSERT INTO libro_autor (id_libro, id_autor) VALUES
(1, 1),  -- Cien años → García Márquez
(2, 2),  -- La casa → Allende
(3, 3),  -- La ciudad → Vargas Llosa
(4, 1);  -- El amor → García Márquez

-- Insertar socios
INSERT INTO socio (nombre, apellido, telefono, email) VALUES
('Max',    'García',   '987654321', 'max@email.com'),
('Lucía',  'Torres',   '912345678', 'lucia@email.com'),
('Carlos', 'Mendoza',  '934567890', 'carlos@email.com');

-- Insertar préstamos
INSERT INTO prestamo (fecha_prestamo, fecha_devolucion, estado, id_libro, id_socio) VALUES
('2026-04-01', NULL,         'activo',   1, 1),  -- Max tiene Cien años
('2026-03-15', '2026-03-25', 'devuelto', 2, 2),  -- Lucía devolvió La casa
('2026-04-10', NULL,         'activo',   3, 3);  -- Carlos tiene La ciudad

-- =============================================
-- CONSULTAS para verificar que todo funciona
-- =============================================

-- Ver todos los libros con sus autores
SELECT l.titulo, a.nombre || ' ' || a.apellido AS autor
FROM libro l
JOIN libro_autor la ON l.id_libro = la.id_libro
JOIN autor a ON la.id_autor = a.id_autor;

-- Ver préstamos activos con datos del socio y libro
SELECT 
    s.nombre || ' ' || s.apellido AS socio,
    l.titulo AS libro,
    p.fecha_prestamo,
    p.estado
FROM prestamo p
JOIN socio s ON p.id_socio = s.id_socio
JOIN libro l ON p.id_libro = l.id_libro
WHERE p.estado = 'activo';
```

---

# EJEMPLO 2: Sistema de Colegio

## Enunciado
> Un colegio necesita gestionar información de sus alumnos, profesores, cursos y calificaciones. Un alumno puede inscribirse en varios cursos. Un profesor puede dictar varios cursos pero cada curso es dictado por un solo profesor. Se necesita registrar la nota que cada alumno obtiene en cada curso.

---

## PASO 1: Leer y entender

Necesitamos gestionar: alumnos, profesores, cursos y las notas (relación entre alumno y curso).

---

## PASO 2: Identificar Entidades

- **Alumno** → datos de los estudiantes
- **Profesor** → datos de los docentes
- **Curso** → información de cada materia

---

## PASO 3: Identificar Atributos

| Entidad | Atributos |
|---------|-----------|
| **Alumno** | id_alumno (PK), nombre, apellido, dni, fecha_nacimiento, grado |
| **Profesor** | id_profesor (PK), nombre, apellido, especialidad, email |
| **Curso** | id_curso (PK), nombre_curso, descripcion, creditos, id_profesor (FK) |

> La nota va en la tabla intermedia Alumno_Curso porque es una característica de la **relación** (no del alumno ni del curso por sí solos)

---

## PASO 4 y 5: Identificar Relaciones y Cardinalidad

| Relación | Tipo | Razonamiento |
|---------|------|-------------|
| Profesor ↔ Curso | **1:N** | Un profesor dicta varios cursos. Un curso tiene un solo profesor |
| Alumno ↔ Curso | **N:M** | Un alumno toma varios cursos. Un curso tiene varios alumnos |

---

## PASO 6: Resolver N:M

Alumno ↔ Curso es N:M → tabla intermedia **Inscripcion** (con atributo extra: nota)

```
Alumno (1) ←——→ (N) Inscripcion (N) ←——→ (1) Curso
                     - nota
                     - fecha_inscripcion
```

---

## PASO 7: MER Resultante

```
┌────────────┐       ┌────────────┐       ┌──────────────┐
│  PROFESOR  │       │   CURSO    │       │    ALUMNO    │
│────────────│       │────────────│       │──────────────│
│id_profesor │PK     │ id_curso   │PK     │ id_alumno    │PK
│ nombre     │◄─(1)──│ nombre     │       │ nombre       │
│ apellido   │       │ descripcion│       │ apellido     │
│especialidad│       │ creditos   │       │ dni          │
│ email      │       │id_profesor │FK     │ fecha_nac    │
└────────────┘       └─────┬──────┘       │ grado        │
                           │(1)           └──────┬───────┘
                           │                     │(1)
                          (N)                   (N)
                      ┌────┴─────────────────────┴─┐
                      │        INSCRIPCION          │
                      │────────────────────────────-│
                      │ id_alumno   FK              │
                      │ id_curso    FK              │
                      │ nota                        │
                      │ fecha_inscripcion           │
                      └────────────────────────────-┘
```

---

## PASO 8, 9 y 10: SQL Completo

```sql
-- =============================================
-- PASO 8: Crear la base de datos
-- =============================================
CREATE DATABASE colegio;

\c colegio

-- =============================================
-- PASO 9: Crear las tablas
-- =============================================

-- 1. Tabla PROFESOR (sin FK)
CREATE TABLE profesor (
    id_profesor  SERIAL PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    apellido     VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100),
    email        VARCHAR(150) UNIQUE
);

-- 2. Tabla ALUMNO (sin FK)
CREATE TABLE alumno (
    id_alumno       SERIAL PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    apellido        VARCHAR(100) NOT NULL,
    dni             VARCHAR(20) UNIQUE NOT NULL,
    fecha_nacimiento DATE,
    grado           VARCHAR(20)
);

-- 3. Tabla CURSO (tiene FK a profesor)
CREATE TABLE curso (
    id_curso     SERIAL PRIMARY KEY,
    nombre_curso VARCHAR(150) NOT NULL,
    descripcion  TEXT,
    creditos     INT DEFAULT 3,
    id_profesor  INT NOT NULL REFERENCES profesor(id_profesor)
);

-- 4. Tabla intermedia INSCRIPCION (N:M entre alumno y curso)
CREATE TABLE inscripcion (
    id_alumno         INT NOT NULL REFERENCES alumno(id_alumno),
    id_curso          INT NOT NULL REFERENCES curso(id_curso),
    nota              DECIMAL(4,2) CHECK (nota >= 0 AND nota <= 20),
    fecha_inscripcion DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (id_alumno, id_curso)
);

-- =============================================
-- PASO 10: Insertar datos de prueba
-- =============================================

-- Insertar profesores
INSERT INTO profesor (nombre, apellido, especialidad, email) VALUES
('Ana',    'Ramírez', 'Matemáticas',        'ana.ramirez@colegio.edu'),
('Pedro',  'López',   'Comunicación',       'pedro.lopez@colegio.edu'),
('Silvia', 'Flores',  'Ciencias Naturales', 'silvia.flores@colegio.edu');

-- Insertar alumnos
INSERT INTO alumno (nombre, apellido, dni, fecha_nacimiento, grado) VALUES
('Luis',   'Castillo', '74100001', '2008-03-15', '4to Secundaria'),
('Paola',  'Vega',     '74100002', '2008-07-22', '4to Secundaria'),
('Diego',  'Morales',  '74100003', '2007-11-05', '5to Secundaria'),
('Fátima', 'Chávez',   '74100004', '2008-01-30', '4to Secundaria');

-- Insertar cursos (asignados a un profesor)
INSERT INTO curso (nombre_curso, descripcion, creditos, id_profesor) VALUES
('Álgebra',        'Fundamentos de álgebra lineal',    4, 1),  -- Prof. Ana
('Trigonometría',  'Funciones trigonométricas básicas', 3, 1), -- Prof. Ana
('Comunicación I', 'Comprensión lectora y redacción',  3, 2),  -- Prof. Pedro
('Biología',       'Biología celular y genética',      4, 3);  -- Prof. Silvia

-- Insertar inscripciones con notas
INSERT INTO inscripcion (id_alumno, id_curso, nota, fecha_inscripcion) VALUES
(1, 1, 15.5, '2026-03-10'),  -- Luis → Álgebra: 15.5
(1, 3, 17.0, '2026-03-10'),  -- Luis → Comunicación I: 17
(2, 1, 13.0, '2026-03-10'),  -- Paola → Álgebra: 13
(2, 2, 16.5, '2026-03-10'),  -- Paola → Trigonometría: 16.5
(2, 4, 18.0, '2026-03-10'),  -- Paola → Biología: 18
(3, 3, 14.0, '2026-03-10'),  -- Diego → Comunicación I: 14
(3, 4, 12.5, '2026-03-10'),  -- Diego → Biología: 12.5
(4, 1, 19.0, '2026-03-10'),  -- Fátima → Álgebra: 19
(4, 2, 18.5, '2026-03-10'),  -- Fátima → Trigonometría: 18.5
(4, 3, 17.0, '2026-03-10');  -- Fátima → Comunicación I: 17

-- =============================================
-- CONSULTAS para verificar
-- =============================================

-- Ver las notas de todos los alumnos por curso
SELECT 
    a.nombre || ' ' || a.apellido AS alumno,
    c.nombre_curso,
    i.nota,
    p.nombre || ' ' || p.apellido AS profesor
FROM inscripcion i
JOIN alumno a  ON i.id_alumno = a.id_alumno
JOIN curso c   ON i.id_curso  = c.id_curso
JOIN profesor p ON c.id_profesor = p.id_profesor
ORDER BY alumno, c.nombre_curso;

-- Ver el promedio de notas por alumno
SELECT 
    a.nombre || ' ' || a.apellido AS alumno,
    ROUND(AVG(i.nota), 2) AS promedio
FROM inscripcion i
JOIN alumno a ON i.id_alumno = a.id_alumno
GROUP BY a.id_alumno, alumno
ORDER BY promedio DESC;

-- Ver cuántos alumnos tiene cada profesor
SELECT 
    p.nombre || ' ' || p.apellido AS profesor,
    COUNT(DISTINCT i.id_alumno) AS total_alumnos
FROM profesor p
JOIN curso c ON p.id_profesor = c.id_profesor
JOIN inscripcion i ON c.id_curso = i.id_curso
GROUP BY p.id_profesor, profesor;
```

---

# EJEMPLO 3: Sistema de Tienda / E-commerce

## Enunciado
> Una tienda online necesita un sistema para gestionar sus clientes, productos, categorías y pedidos. Un cliente puede realizar varios pedidos. Cada pedido puede contener varios productos y cada producto puede aparecer en varios pedidos. Se necesita saber la cantidad y precio de cada producto en cada pedido. Los productos pertenecen a una categoría.

---

## PASO 1: Leer y entender

Necesitamos gestionar: clientes, productos, categorías y pedidos (incluyendo el detalle de qué productos lleva cada pedido).

---

## PASO 2: Identificar Entidades

- **Cliente** → datos de quien compra
- **Categoria** → agrupación de productos
- **Producto** → artículos que se venden
- **Pedido** → la orden de compra
- **Detalle_Pedido** → (tabla intermedia que resuelve N:M entre Pedido y Producto, con cantidad y precio)

---

## PASO 3: Identificar Atributos

| Entidad | Atributos |
|---------|-----------|
| **Cliente** | id_cliente (PK), nombre, apellido, email, telefono, direccion |
| **Categoria** | id_categoria (PK), nombre_categoria, descripcion |
| **Producto** | id_producto (PK), nombre, descripcion, precio_unitario, stock, id_categoria (FK) |
| **Pedido** | id_pedido (PK), fecha_pedido, estado, total, id_cliente (FK) |
| **Detalle_Pedido** | id_pedido (FK), id_producto (FK), cantidad, precio_en_momento |

> `precio_en_momento`: guardamos el precio al momento de comprar porque puede cambiar después

---

## PASO 4 y 5: Identificar Relaciones y Cardinalidad

| Relación | Tipo | Razonamiento |
|---------|------|-------------|
| Categoria ↔ Producto | **1:N** | Una categoría tiene muchos productos. Un producto pertenece a una categoría |
| Cliente ↔ Pedido | **1:N** | Un cliente hace muchos pedidos. Un pedido es de un cliente |
| Pedido ↔ Producto | **N:M** | Un pedido tiene muchos productos. Un producto aparece en muchos pedidos |

---

## PASO 6: Resolver N:M

Pedido ↔ Producto es N:M → tabla intermedia **Detalle_Pedido** (con cantidad y precio_en_momento)

---

## PASO 7: MER Resultante

```
┌──────────────┐       ┌─────────────┐       ┌────────────────┐
│  CATEGORIA   │       │   PRODUCTO  │       │    CLIENTE     │
│──────────────│       │─────────────│       │────────────────│
│ id_categoria │PK     │ id_producto │PK     │ id_cliente     │PK
│ nombre_cat.  │◄─(1)──│ nombre      │       │ nombre         │
│ descripcion  │       │ descripcion │       │ apellido       │
└──────────────┘       │ precio_unit.│       │ email          │
                       │ stock       │       │ telefono       │
                       │ id_categoria│FK     │ direccion      │
                       └──────┬──────┘       └───────┬────────┘
                              │(1)                   │(1)
                             (N)                    (N)
                        ┌─────┴──────────────────────┴──┐
                        │        DETALLE_PEDIDO          │
                        │────────────────────────────────│
                        │ id_pedido        FK            │
                        │ id_producto      FK            │
                        │ cantidad                       │
                        │ precio_en_momento              │
                        └────────────────┬───────────────┘
                                        (N)
                                         │
                                        (1)
                                  ┌──────┴──────┐
                                  │   PEDIDO    │
                                  │─────────────│
                                  │ id_pedido   │PK
                                  │ fecha_pedido│
                                  │ estado      │
                                  │ total       │
                                  │ id_cliente  │FK
                                  └─────────────┘
```

---

## PASO 8, 9 y 10: SQL Completo

```sql
-- =============================================
-- PASO 8: Crear la base de datos
-- =============================================
CREATE DATABASE tienda_online;

\c tienda_online

-- =============================================
-- PASO 9: Crear las tablas
-- =============================================

-- 1. Tabla CLIENTE (sin FK)
CREATE TABLE cliente (
    id_cliente SERIAL PRIMARY KEY,
    nombre     VARCHAR(100) NOT NULL,
    apellido   VARCHAR(100) NOT NULL,
    email      VARCHAR(150) UNIQUE NOT NULL,
    telefono   VARCHAR(20),
    direccion  TEXT
);

-- 2. Tabla CATEGORIA (sin FK)
CREATE TABLE categoria (
    id_categoria    SERIAL PRIMARY KEY,
    nombre_categoria VARCHAR(100) NOT NULL,
    descripcion     TEXT
);

-- 3. Tabla PRODUCTO (tiene FK a categoria)
CREATE TABLE producto (
    id_producto    SERIAL PRIMARY KEY,
    nombre         VARCHAR(200) NOT NULL,
    descripcion    TEXT,
    precio_unitario DECIMAL(10,2) NOT NULL CHECK (precio_unitario > 0),
    stock          INT NOT NULL DEFAULT 0 CHECK (stock >= 0),
    id_categoria   INT REFERENCES categoria(id_categoria)
);

-- 4. Tabla PEDIDO (tiene FK a cliente)
CREATE TABLE pedido (
    id_pedido    SERIAL PRIMARY KEY,
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado       VARCHAR(30) DEFAULT 'pendiente'
                     CHECK (estado IN ('pendiente', 'en_proceso', 'enviado', 'entregado', 'cancelado')),
    total        DECIMAL(12,2) DEFAULT 0,
    id_cliente   INT NOT NULL REFERENCES cliente(id_cliente)
);

-- 5. Tabla intermedia DETALLE_PEDIDO (N:M entre pedido y producto)
CREATE TABLE detalle_pedido (
    id_pedido         INT NOT NULL REFERENCES pedido(id_pedido),
    id_producto       INT NOT NULL REFERENCES producto(id_producto),
    cantidad          INT NOT NULL CHECK (cantidad > 0),
    precio_en_momento DECIMAL(10,2) NOT NULL,  -- precio al momento de comprar
    PRIMARY KEY (id_pedido, id_producto)
);

-- =============================================
-- PASO 10: Insertar datos de prueba
-- =============================================

-- Insertar categorías
INSERT INTO categoria (nombre_categoria, descripcion) VALUES
('Electrónica',  'Dispositivos y accesorios electrónicos'),
('Ropa',         'Prendas de vestir para todas las edades'),
('Libros',       'Libros físicos y digitales'),
('Deportes',     'Equipamiento y ropa deportiva'),
('Hogar',        'Artículos para el hogar y decoración');

-- Insertar clientes
INSERT INTO cliente (nombre, apellido, email, telefono, direccion) VALUES
('Max',     'García',   'max@correo.com',    '987001001', 'Av. Lima 123, Lima'),
('Sandra',  'Ríos',     'sandra@correo.com', '987001002', 'Jr. Arequipa 456, Lima'),
('Roberto', 'Puma',     'roberto@correo.com','987001003', 'Calle Cusco 789, Lima');

-- Insertar productos
INSERT INTO producto (nombre, descripcion, precio_unitario, stock, id_categoria) VALUES
('Laptop HP 15"',        'Laptop Intel Core i5, 8GB RAM, 256GB SSD', 2800.00, 15, 1),
('Auriculares Sony',     'Auriculares inalámbricos con cancelación de ruido', 350.00, 30, 1),
('Polo Adidas',          'Polo deportivo talla M, color azul', 120.00, 50, 2),
('Zapatillas Nike',      'Zapatillas running Air Max', 280.00, 25, 4),
('Cien años de soledad', 'Novela de Gabriel García Márquez', 45.00, 100, 3),
('Silla de escritorio',  'Silla ergonómica con soporte lumbar', 450.00, 10, 5),
('Teclado mecánico',     'Teclado gaming RGB, switches rojos', 220.00, 20, 1);

-- Insertar pedidos
INSERT INTO pedido (fecha_pedido, estado, id_cliente) VALUES
('2026-04-01 10:30:00', 'entregado', 1),  -- Pedido 1 de Max
('2026-04-05 14:00:00', 'enviado',   2),  -- Pedido 2 de Sandra
('2026-04-10 09:15:00', 'pendiente', 1),  -- Pedido 3 de Max (otro pedido)
('2026-04-12 16:45:00', 'en_proceso',3);  -- Pedido 4 de Roberto

-- Insertar detalle de pedidos
INSERT INTO detalle_pedido (id_pedido, id_producto, cantidad, precio_en_momento) VALUES
-- Pedido 1 de Max: Laptop + Auriculares
(1, 1, 1, 2800.00),
(1, 2, 2, 350.00),

-- Pedido 2 de Sandra: Polo + Zapatillas + Libro
(2, 3, 3, 120.00),
(2, 4, 1, 280.00),
(2, 5, 2, 45.00),

-- Pedido 3 de Max: Silla + Teclado
(3, 6, 1, 450.00),
(3, 7, 1, 220.00),

-- Pedido 4 de Roberto: Auriculares + Libro
(4, 2, 1, 350.00),
(4, 5, 3, 45.00);

-- Actualizar el total de cada pedido
UPDATE pedido SET total = (
    SELECT SUM(cantidad * precio_en_momento)
    FROM detalle_pedido
    WHERE detalle_pedido.id_pedido = pedido.id_pedido
);

-- =============================================
-- CONSULTAS para verificar
-- =============================================

-- Ver todos los pedidos con el nombre del cliente y total
SELECT 
    p.id_pedido,
    c.nombre || ' ' || c.apellido AS cliente,
    p.fecha_pedido,
    p.estado,
    p.total
FROM pedido p
JOIN cliente c ON p.id_cliente = c.id_cliente
ORDER BY p.fecha_pedido;

-- Ver el detalle completo de un pedido específico (pedido 1)
SELECT 
    pr.nombre AS producto,
    dp.cantidad,
    dp.precio_en_momento AS precio_unitario,
    dp.cantidad * dp.precio_en_momento AS subtotal
FROM detalle_pedido dp
JOIN producto pr ON dp.id_producto = pr.id_producto
WHERE dp.id_pedido = 1;

-- Ver cuánto ha gastado cada cliente en total
SELECT 
    c.nombre || ' ' || c.apellido AS cliente,
    COUNT(p.id_pedido) AS num_pedidos,
    SUM(p.total) AS total_gastado
FROM cliente c
JOIN pedido p ON c.id_cliente = p.id_cliente
GROUP BY c.id_cliente, cliente
ORDER BY total_gastado DESC;

-- Ver los productos más vendidos (por cantidad)
SELECT 
    pr.nombre AS producto,
    SUM(dp.cantidad) AS total_vendido
FROM detalle_pedido dp
JOIN producto pr ON dp.id_producto = pr.id_producto
GROUP BY pr.id_producto, pr.nombre
ORDER BY total_vendido DESC;
```

---

# RESUMEN: REGLAS DE ORO

```
1. SIEMPRE empieza leyendo bien el enunciado antes de escribir SQL

2. IDENTIFICA entidades (sustantivos) → PRIMERO

3. IDENTIFICA relaciones (verbos) → SEGUNDO

4. DEFINE la cardinalidad de cada relación → TERCERO

5. Toda relación N:M SIEMPRE se resuelve con tabla intermedia

6. Crea las tablas en este orden:
   → Primero las que NO tienen FK (tablas "padre")
   → Luego las que SÍ tienen FK (tablas "hijo")

7. Toda tabla DEBE tener una PRIMARY KEY

8. Usa FK para conectar tablas (integridad referencial)

9. Usa CHECK para validar datos desde la BD

10. La tabla intermedia lleva las FKs de ambas tablas
    y ambas forman la PK compuesta (o puedes agregar
    una PK propia si la relación tiene muchos atributos)
```

---

# EJERCICIOS PARA PRACTICAR

---

## Ejercicio 1: Hospital

> Un hospital necesita gestionar médicos, pacientes, consultas y medicamentos. Un médico puede atender a muchos pacientes. Un paciente puede ser atendido por varios médicos a lo largo del tiempo. En cada consulta se recetan varios medicamentos. Un medicamento puede recetarse en varias consultas.

**Desarrolla:**
- Identificar entidades y atributos
- Identificar relaciones y cardinalidad
- Crear el MER
- Escribir el SQL completo (CREATE + INSERT)

**Pista de entidades:** Medico, Paciente, Consulta, Medicamento
**Pista de relaciones N:M:** Consulta ↔ Medicamento

---

## Ejercicio 2: Empresa de Delivery

> Una empresa de delivery necesita gestionar sus repartidores, clientes, restaurantes y pedidos. Un cliente puede pedir a varios restaurantes. Un repartidor puede hacer varios pedidos por día. Cada pedido es de un solo restaurante y es atendido por un solo repartidor. Un pedido puede contener varios platos del menú del restaurante.

**Desarrolla:**
- Identificar entidades y atributos
- Identificar relaciones y cardinalidad
- Crear el MER
- Escribir el SQL completo (CREATE + INSERT)

**Pista de entidades:** Cliente, Restaurante, Repartidor, Pedido, Plato
**Pista de relaciones N:M:** Pedido ↔ Plato

---

## Ejercicio 3: Academia de Idiomas

> Una academia de idiomas ofrece cursos en distintos idiomas (inglés, francés, alemán). Cada curso tiene varios niveles (A1, A2, B1, B2, C1). Un alumno puede inscribirse a varios cursos de diferentes idiomas. Cada curso-nivel es dictado por un profesor específico. Se requiere registrar la asistencia y la nota final de cada alumno por cada curso.

**Desarrolla:**
- Identificar entidades y atributos
- Identificar relaciones y cardinalidad
- Crear el MER
- Escribir el SQL completo (CREATE + INSERT)

**Pista de entidades:** Alumno, Idioma, Nivel, Profesor, Inscripcion
**Pista de relaciones N:M:** Alumno ↔ Nivel (con nota y asistencia)

---

## Ejercicio 4 (Reto): Red Social Simple

> Una red social permite a usuarios publicar posts. Un usuario puede seguir a otros usuarios. Los usuarios pueden dar "like" a los posts de otros usuarios. Un post puede tener varias etiquetas y una etiqueta puede estar en varios posts.

**Desarrolla todo el flujo completo.**

**Pista:** Hay varias relaciones N:M aquí:
- Usuario ↔ Usuario (seguir) → tabla: Seguidor
- Usuario ↔ Post (like) → tabla: Like
- Post ↔ Etiqueta → tabla: Post_Etiqueta

---

## Checklist para cada ejercicio

Antes de entregar o considerar completo un ejercicio, verifica:

- [ ] ¿Identifiqué TODAS las entidades del enunciado?
- [ ] ¿Cada entidad tiene PK definida?
- [ ] ¿Identifiqué todas las relaciones?
- [ ] ¿Determiné la cardinalidad de cada relación?
- [ ] ¿Resolví TODAS las relaciones N:M con tablas intermedias?
- [ ] ¿Creé las tablas en el orden correcto (primero las "padre")?
- [ ] ¿Agregué las FK donde corresponde?
- [ ] ¿Inserté datos de prueba en todas las tablas?
- [ ] ¿Probé al menos una consulta JOIN para verificar que todo funciona?
