# Caso Práctico: BD de Identificación Familiar
**Basado en:** Propuesta Fiscalía de Justicia de Zacatecas + colectivos de madres buscadoras
**Metodología:** Guía Paso a Paso BD (GUIA_PASO_A_PASO_BD.md)

---

# ENUNCIADO

> Una familia desea crear una base de datos preventiva para facilitar la localización inmediata de sus integrantes ante un caso de desaparición forzada. Para cada miembro se requiere guardar: datos generales, fotografías actuales y señas particulares (lunares, cicatrices, marcas), muestras biológicas (cabello en bolsa hermética, hisopo bucal), huellas dactilares, y su entorno social (contactos cercanos, lugares de reunión frecuentes). Un contacto puede frecuentar varios lugares, y un lugar puede ser frecuentado por varios contactos.

---

## PASO 1: Leer y entender el problema

Necesitamos registrar información de **personas de la familia** con:
- Sus datos básicos
- Sus registros visuales (fotos y señas)
- Sus muestras biológicas
- Su círculo social (contactos y lugares que frecuentan)

El objetivo es que esta información esté **disponible inmediatamente** al momento de realizar una denuncia.

---

## PASO 2: Identificar Entidades

Busco los **sustantivos** importantes del enunciado:

| # | Entidad | Justificación |
|---|---------|---------------|
| 1 | **PERSONA** | El integrante familiar del que guardamos todo |
| 2 | **FOTOGRAFIA** | Registro visual; puede haber varias por persona |
| 3 | **SENA_PARTICULAR** | Lunar, cicatriz, tatuaje; puede haber varias por persona |
| 4 | **MUESTRA_BIOLOGICA** | Cabello, hisopo bucal; puede haber varias por persona |
| 5 | **CONTACTO** | Persona del círculo social; una persona tiene varios |
| 6 | **LUGAR_FRECUENTE** | Sitios que la persona visita; uno puede estar ligado a varios contactos |

---

## PASO 3: Identificar Atributos

| Entidad | Atributos |
|---------|-----------|
| **PERSONA** | id_persona (PK), nombre_completo, fecha_nacimiento, domicilio_actual, fecha_registro, fecha_actualizacion |
| **FOTOGRAFIA** | id_foto (PK), id_persona (FK), tipo, descripcion, archivo_foto, fecha_toma |
| **SENA_PARTICULAR** | id_sena (PK), id_persona (FK), tipo_sena, descripcion, ubicacion_cuerpo |
| **MUESTRA_BIOLOGICA** | id_muestra (PK), id_persona (FK), tipo_muestra, ubicacion_fisica, fecha_obtencion |
| **CONTACTO** | id_contacto (PK), id_persona (FK), nombre_contacto, relacion, telefono |
| **LUGAR_FRECUENTE** | id_lugar (PK), id_persona (FK), nombre_lugar, tipo_lugar, direccion, horario_tipico |

---

## PASO 4 y 5: Identificar Relaciones y Cardinalidad

| Relación | Tipo | Razonamiento |
|----------|------|--------------|
| PERSONA ↔ FOTOGRAFIA | **1:N** | Una persona tiene varias fotos. Cada foto pertenece a una sola persona |
| PERSONA ↔ SENA_PARTICULAR | **1:N** | Una persona puede tener múltiples señas. Cada seña es de una persona |
| PERSONA ↔ MUESTRA_BIOLOGICA | **1:N** | Una persona puede tener varias muestras guardadas. Cada muestra es de una persona |
| PERSONA ↔ CONTACTO | **1:N** | Una persona tiene varios contactos. Cada contacto está vinculado a una persona |
| PERSONA ↔ LUGAR_FRECUENTE | **1:N** | Una persona frecuenta varios lugares. Cada lugar registrado pertenece a una persona |
| CONTACTO ↔ LUGAR_FRECUENTE | **N:M** | Un contacto puede ser visto en varios lugares. Un lugar puede ser frecuentado por varios contactos |

---

## PASO 6: Resolver N:M

La relación CONTACTO ↔ LUGAR_FRECUENTE es N:M → creo tabla intermedia **CONTACTO_LUGAR**

```
CONTACTO (1) ←——→ (N) CONTACTO_LUGAR (N) ←——→ (1) LUGAR_FRECUENTE
```

La tabla intermedia no necesita atributos extra en este caso. Su PK es compuesta: (id_contacto + id_lugar).

---

## PASO 7: MER Resultante

```
                    ┌─────────────────────────┐
                    │         PERSONA          │
                    │─────────────────────────│
                    │ id_persona  PK           │
                    │ nombre_completo          │
                    │ fecha_nacimiento         │
                    │ domicilio_actual         │
                    │ fecha_registro           │
                    │ fecha_actualizacion      │
                    └───┬──┬──┬──┬────────────┘
                        │  │  │  │
          (1)──────────(N) │  │  │
    ┌──────────────┐       │  │  │
    │  FOTOGRAFIA  │       │  │  │
    │──────────────│   (1)─┘  │  │(1)
    │ id_foto  PK  │          │  │
    │ id_persona FK│    (1)───┘  │
    │ tipo         │             │
    │ descripcion  │  ┌──────────┴──────┐
    │ archivo_foto │  │ MUESTRA_BIOLOGICA│
    │ fecha_toma   │  │─────────────────│
    └──────────────┘  │ id_muestra  PK  │
                      │ id_persona  FK  │
    ┌──────────────┐  │ tipo_muestra    │
    │SENA_PARTICULAR│  │ ubicacion_fisica│
    │──────────────│  │ fecha_obtencion │
    │ id_sena  PK  │  └─────────────────┘
    │ id_persona FK│
    │ tipo_sena    │
    │ descripcion  │
    │ ubic_cuerpo  │
    └──────────────┘

    ┌────────────────┐        ┌─────────────────┐
    │    CONTACTO    │        │  LUGAR_FRECUENTE │
    │────────────────│        │─────────────────│
    │ id_contacto PK │        │ id_lugar    PK  │
    │ id_persona  FK │        │ id_persona  FK  │
    │ nombre_contacto│        │ nombre_lugar    │
    │ relacion       │        │ tipo_lugar      │
    │ telefono       │        │ direccion       │
    └───────┬────────┘        │ horario_tipico  │
            │(1)              └────────┬────────┘
           (N)                        (1)
      ┌─────┴────────────────────────(N)──┐
      │         CONTACTO_LUGAR            │
      │───────────────────────────────────│
      │ id_contacto  FK (PK compuesta)    │
      │ id_lugar     FK (PK compuesta)    │
      └───────────────────────────────────┘
```

---

## PASO 8, 9 y 10: SQL Completo

```sql
-- =============================================
-- PASO 8: Crear la base de datos
-- =============================================
CREATE DATABASE bd_familiar_preventiva;

\c bd_familiar_preventiva

-- =============================================
-- PASO 9: Crear las tablas
-- Orden: primero las que NO tienen FK (tablas "padre")
-- =============================================

-- 1. PERSONA (tabla raíz, sin FK)
CREATE TABLE persona (
    id_persona           SERIAL PRIMARY KEY,
    nombre_completo      VARCHAR(200) NOT NULL,
    fecha_nacimiento     DATE NOT NULL,
    domicilio_actual     VARCHAR(300),
    fecha_registro       DATE DEFAULT CURRENT_DATE,
    fecha_actualizacion  DATE DEFAULT CURRENT_DATE
);

-- 2. FOTOGRAFIA (FK a persona)
CREATE TABLE fotografia (
    id_foto      SERIAL PRIMARY KEY,
    id_persona   INT NOT NULL REFERENCES persona(id_persona),
    tipo         VARCHAR(30) DEFAULT 'general'
                     CHECK (tipo IN ('general', 'sena_particular', 'documento')),
    descripcion  VARCHAR(300),
    archivo_foto BYTEA,           -- equivale a BLOB en PostgreSQL
    fecha_toma   DATE DEFAULT CURRENT_DATE
);

-- 3. SENA_PARTICULAR (FK a persona)
CREATE TABLE sena_particular (
    id_sena        SERIAL PRIMARY KEY,
    id_persona     INT NOT NULL REFERENCES persona(id_persona),
    tipo_sena      VARCHAR(30) NOT NULL
                       CHECK (tipo_sena IN ('lunar', 'cicatriz', 'tatuaje', 'marca_nacimiento', 'otra')),
    descripcion    VARCHAR(400) NOT NULL,
    ubicacion_cuerpo VARCHAR(150)
);

-- 4. MUESTRA_BIOLOGICA (FK a persona)
CREATE TABLE muestra_biologica (
    id_muestra       SERIAL PRIMARY KEY,
    id_persona       INT NOT NULL REFERENCES persona(id_persona),
    tipo_muestra     VARCHAR(50) NOT NULL
                         CHECK (tipo_muestra IN ('cabello', 'hisopo_bucal', 'sangre_seca', 'otra')),
    ubicacion_fisica VARCHAR(300) NOT NULL,  -- donde está guardada físicamente
    fecha_obtencion  DATE DEFAULT CURRENT_DATE
);

-- 5. CONTACTO (FK a persona)
CREATE TABLE contacto (
    id_contacto      SERIAL PRIMARY KEY,
    id_persona       INT NOT NULL REFERENCES persona(id_persona),
    nombre_contacto  VARCHAR(200) NOT NULL,
    relacion         VARCHAR(50)
                         CHECK (relacion IN ('amigo', 'familiar', 'companero_trabajo',
                                             'companero_escuela', 'vecino', 'pareja', 'otro')),
    telefono         VARCHAR(20)
);

-- 6. LUGAR_FRECUENTE (FK a persona)
CREATE TABLE lugar_frecuente (
    id_lugar       SERIAL PRIMARY KEY,
    id_persona     INT NOT NULL REFERENCES persona(id_persona),
    nombre_lugar   VARCHAR(200) NOT NULL,
    tipo_lugar     VARCHAR(50)
                       CHECK (tipo_lugar IN ('trabajo', 'escuela', 'recreativo',
                                             'comercio', 'religioso', 'deportivo', 'otro')),
    direccion      VARCHAR(300),
    horario_tipico VARCHAR(150)
);

-- 7. CONTACTO_LUGAR — tabla intermedia N:M (tiene FK a contacto y a lugar)
CREATE TABLE contacto_lugar (
    id_contacto INT NOT NULL REFERENCES contacto(id_contacto),
    id_lugar    INT NOT NULL REFERENCES lugar_frecuente(id_lugar),
    PRIMARY KEY (id_contacto, id_lugar)
);

-- =============================================
-- PASO 10: Insertar datos de prueba
-- =============================================

-- Insertar personas
INSERT INTO persona (nombre_completo, fecha_nacimiento, domicilio_actual) VALUES
('Ana Lucía Méndez Torres',  '2008-05-14', 'Calle Roble 45, Colonia Centro, Zacatecas'),
('Carlos Eduardo Méndez Torres', '2005-11-03', 'Calle Roble 45, Colonia Centro, Zacatecas');

-- Fotografías de Ana Lucía
INSERT INTO fotografia (id_persona, tipo, descripcion, fecha_toma) VALUES
(1, 'general',         'Foto escolar 2026, frente', '2026-03-10'),
(1, 'general',         'Foto perfil izquierdo', '2026-03-10'),
(1, 'sena_particular', 'Lunar en mejilla derecha, 0.5cm', '2026-03-10');

-- Señas particulares
INSERT INTO sena_particular (id_persona, tipo_sena, descripcion, ubicacion_cuerpo) VALUES
(1, 'lunar',    'Lunar color café oscuro, forma ovalada, ~0.5cm',  'Mejilla derecha'),
(1, 'cicatriz', 'Cicatriz de 2cm, color blanquecino, de caída a los 7 años', 'Rodilla izquierda'),
(2, 'tatuaje',  'Tatuaje de estrella de 5 puntas, tinta negra, 3cm', 'Antebrazo derecho');

-- Muestras biológicas
INSERT INTO muestra_biologica (id_persona, tipo_muestra, ubicacion_fisica, fecha_obtencion) VALUES
(1, 'cabello',      'Sobre manila amarillo, caja fuerte familiar, cajón derecho', '2026-04-15'),
(1, 'hisopo_bucal', 'Bolsa hermética transparente, etiquetada, misma caja fuerte', '2026-04-15'),
(2, 'cabello',      'Sobre manila azul, caja fuerte familiar, cajón derecho', '2026-04-15');

-- Contactos de Ana Lucía
INSERT INTO contacto (id_persona, nombre_contacto, relacion, telefono) VALUES
(1, 'Sofía Ramírez Vega',     'amigo',            '4921123456'),
(1, 'Prof. Jorge Leal',       'companero_escuela', '4921654321'),
(1, 'Mariana Ortega Ruiz',    'amigo',            '4921789012'),
(1, 'Tienda Don Pepe',        'otro',             NULL);

-- Contactos de Carlos
INSERT INTO contacto (id_persona, nombre_contacto, relacion, telefono) VALUES
(2, 'Luis Hernández Mora',    'amigo',             '4921345678'),
(2, 'Equipo Águilas FC',      'companero_trabajo',  NULL);

-- Lugares frecuentes de Ana Lucía
INSERT INTO lugar_frecuente (id_persona, nombre_lugar, tipo_lugar, direccion, horario_tipico) VALUES
(1, 'Escuela Sec. Benito Juárez', 'escuela',     'Av. Independencia 200, Zacatecas', 'Lun-Vie 7:30-14:00'),
(1, 'Plaza Bicentenario',         'recreativo',  'Centro Histórico, Zacatecas',      'Fines de semana 16:00-20:00'),
(1, 'Tienda Don Pepe',            'comercio',    'Calle Hidalgo 33, Colonia Centro',  'Lun-Sab 15:00-17:00');

-- Lugares frecuentes de Carlos
INSERT INTO lugar_frecuente (id_persona, nombre_lugar, tipo_lugar, direccion, horario_tipico) VALUES
(2, 'CECYTEZ Plantel Norte', 'escuela',    'Carretera a Guadalajara Km 3, Zacatecas', 'Lun-Vie 7:00-14:30'),
(2, 'Cancha Deportiva Norte', 'deportivo', 'Colonia Las Torres, Zacatecas',           'Sab-Dom 9:00-12:00');

-- Relacionar contactos con lugares (N:M)
-- Sofía también va a la plaza con Ana
INSERT INTO contacto_lugar (id_contacto, id_lugar) VALUES
(1, 2),  -- Sofía → Plaza Bicentenario
(1, 3),  -- Sofía → Tienda Don Pepe
(2, 1),  -- Prof. Leal → Escuela
(3, 2),  -- Mariana → Plaza Bicentenario
(5, 4),  -- Luis → CECYTEZ
(5, 5),  -- Luis → Cancha Deportiva Norte
(6, 5);  -- Equipo Águilas → Cancha Deportiva Norte

-- =============================================
-- CONSULTAS para verificar
-- =============================================

-- Ver el perfil completo de una persona (Ana Lucía)
SELECT 
    p.nombre_completo,
    p.fecha_nacimiento,
    p.domicilio_actual,
    COUNT(DISTINCT f.id_foto)   AS fotos,
    COUNT(DISTINCT s.id_sena)   AS senas,
    COUNT(DISTINCT m.id_muestra) AS muestras,
    COUNT(DISTINCT c.id_contacto) AS contactos,
    COUNT(DISTINCT l.id_lugar)  AS lugares
FROM persona p
LEFT JOIN fotografia f         ON p.id_persona = f.id_persona
LEFT JOIN sena_particular s    ON p.id_persona = s.id_persona
LEFT JOIN muestra_biologica m  ON p.id_persona = m.id_persona
LEFT JOIN contacto c           ON p.id_persona = c.id_persona
LEFT JOIN lugar_frecuente l    ON p.id_persona = l.id_lugar
WHERE p.id_persona = 1
GROUP BY p.id_persona, p.nombre_completo, p.fecha_nacimiento, p.domicilio_actual;

-- Ver todos los contactos de una persona y los lugares que frecuentan
SELECT 
    p.nombre_completo AS persona,
    c.nombre_contacto,
    c.relacion,
    c.telefono,
    l.nombre_lugar,
    l.tipo_lugar
FROM persona p
JOIN contacto c         ON p.id_persona = c.id_persona
LEFT JOIN contacto_lugar cl ON c.id_contacto = cl.id_contacto
LEFT JOIN lugar_frecuente l ON cl.id_lugar = l.id_lugar
WHERE p.id_persona = 1
ORDER BY c.nombre_contacto;

-- Ver todas las señas particulares registradas
SELECT 
    p.nombre_completo,
    s.tipo_sena,
    s.descripcion,
    s.ubicacion_cuerpo
FROM sena_particular s
JOIN persona p ON s.id_persona = p.id_persona
ORDER BY p.nombre_completo;

-- Ver muestras biológicas con ubicación física (reporte de denuncia)
SELECT 
    p.nombre_completo,
    m.tipo_muestra,
    m.ubicacion_fisica,
    m.fecha_obtencion
FROM muestra_biologica m
JOIN persona p ON m.id_persona = p.id_persona
ORDER BY p.nombre_completo, m.tipo_muestra;
```

---

## Reflexión Final

| Concepto de BD | Aplicación en este caso |
|----------------|------------------------|
| **Entidad** | PERSONA, CONTACTO, LUGAR son objetos reales del mundo |
| **PK** | Cada tabla tiene identificador único propio |
| **FK** | Todo apunta a PERSONA como núcleo del registro |
| **N:M** | Un contacto puede aparecer en varios lugares y viceversa |
| **BLOB/BYTEA** | Fotos y huellas son datos binarios, no texto |
| **CHECK** | Valida tipos de muestra y tipos de señas desde la BD |
| **Alta disponibilidad** | Mantener backup físico Y digital (análogo a RMAN + Data Guard) |
| **Control de acceso** | Solo la familia accede a los datos (análogo a `GRANT` en Oracle) |

> Este caso demuestra que una buena base de datos — bien diseñada desde el modelo entidad-relación — puede tener **impacto directo en la localización de personas desaparecidas**.
