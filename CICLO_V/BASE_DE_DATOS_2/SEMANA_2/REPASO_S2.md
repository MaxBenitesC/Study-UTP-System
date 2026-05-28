# Repaso Semana 2 - Estructura de Funcionamiento de PostgreSQL
**Curso:** Base de Datos II | **Unidad:** 1 - Bases de Datos Relacionales | **Sesión:** 2

---

## Logro de la Sesión
Comprender la estructura de funcionamiento de PostgreSQL, la creación de bases de datos y tablas para gestionar datos de manera segura y efectiva.

---

## 1. Modelo Cliente-Servidor

PostgreSQL se basa en el clásico **modelo cliente-servidor**:

```
Cliente (psql / pgAdmin / app web)
        ↓ petición de sesión
   Postmaster (servidor central)
        ↓ crea proceso dedicado
   Proceso Servidor PostgreSQL
        ↓ accede a
   Buffers Compartidos + Procesos Background
        ↓ lee/escribe en
   Archivos de datos en disco (PGDATA)
```

| Componente | Rol |
|-----------|-----|
| **Postmaster** | Proceso central que administra archivos y conexiones |
| **Cliente psql** | Interfaz de línea de comandos nativa |
| **pgAdmin / phpPgAdmin** | Interfaces gráficas de usuario |
| **Proceso servidor** | Atiende a cada cliente en modo peer-to-peer |

---

## 2. Estructura del Directorio de Datos (PGDATA)

PostgreSQL almacena sus datos en el directorio llamado **PGDATA**, que contiene varios subdirectorios importantes:

| Subdirectorio | Descripción |
|--------------|-------------|
| **Global** | Tablas del clúster (ej. base de datos de usuarios) |
| **Base** | Ubicación física del espacio de tablas por defecto. Contiene subdirectorios por base de datos |
| **PID** | Archivo con el ID del proceso postmaster actual |
| **PG_VERSION** | Información de la versión de la base de datos |
| **PG_NOTIFY** | Datos de estado de LISTEN/NOTIFY |

> PostgreSQL puede tener varias bases de datos que juntas forman un **cluster de bases de datos**.

---

## 3. Componentes de la Instancia PostgreSQL

La **Instancia PostgreSQL** = Memoria Compartida + Procesos Background

| Componente | Función |
|-----------|---------|
| **Buffer Cache** | Memoria intermedia para mejorar E/S. Los datos frecuentes se guardan en caché para reducir acceso al disco |
| **WAL (Write-Ahead Logging)** | Garantiza durabilidad. Registra cambios ANTES de aplicarlos. Permite recuperación ante fallos |
| **Catálogo del Sistema** | Almacena info sobre bases de datos, tablas, índices, usuarios y otros objetos |
| **MVCC** | Control de Concurrencia Multiversión. Permite transacciones simultáneas sin conflictos ni bloqueos |

---

## 4. Funciones Principales de PostgreSQL

1. **Almacenamiento eficiente de datos** → organiza y almacena con integridad y consistencia
2. **Consultas avanzadas** → SQL para consultas complejas y análisis
3. **Backend confiable** → aplicaciones web y móviles (almacenamiento seguro y escalable)
4. **Soporte geoespacial** → funciones para datos geográficos (mapas, GPS)

---

## 5. Estructura de Gestión de la Información

PostgreSQL sigue el esquema **relacional**, estructurado con:

| Elemento | Descripción |
|---------|-------------|
| **Tablas** | Mecanismo principal de almacenamiento (similar a hoja de cálculo con filas y columnas) |
| **Columnas** (atributos/campos) | Representan una propiedad dentro de la tabla (nombre + tipo de dato + restricciones) |
| **Filas** (registros/tuplas) | Representan una instancia de datos; deben cumplir los tipos y restricciones definidos |

---

## 6. Tipos de Datos en PostgreSQL

PostgreSQL sigue estándares **ANSI e ISO**. Los tipos más usados:

- Números enteros
- Números decimales y puntos flotantes
- Textos y caracteres (`VARCHAR`, `TEXT`)
- Fechas y horas (`TIMESTAMP`, `DATE`, `TIME`)
- Booleanos (`TRUE` / `FALSE`)
- Binarios
- Arreglos
- **JSON y JSONB** (datos semi-estructurados)

---

## 7. Performance Tuning (Afinamiento)

El **Performance Tuning** es el proceso de cambiar la configuración de la BD para obtener mejor rendimiento. Depende del **caso de uso**:

| Sistema | Tipo |
|---------|------|
| OLTP (ej. ventas) | Muchas transacciones pequeñas → optimizar escrituras |
| DSS / Data Warehouse | Consultas complejas → optimizar lecturas e I/O |
| BATCH | Procesamiento masivo por lotes |

### Factores de hardware que afectan el rendimiento:

| Hardware | Impacto |
|---------|---------|
| **CPU** | Operaciones complejas (agregaciones, uniones, hash, sort). Genera el Plan de Ejecución de consultas |
| **RAM** | El buffer pool vive en RAM. Más RAM = menos I/O en disco = mejor rendimiento |
| **Disco (I/O)** | Lectura/escritura rápida mejora consultas. SSDs vs magnéticos. Usar tablespaces en discos separados |
| **Red** | Retrasos de red son cuellos de botella. Alta velocidad y ancho de banda son críticos |

### Diseño de base de datos para rendimiento:
- **Indexar columnas FK** (claves foráneas)
- Crear índices en columnas usadas como filtros (`WHERE`)
- **Particionar** tablas grandes
- Aplicar **normalización** (hasta 3FN)

---

## 8. Escalabilidad

| Tipo | Descripción |
|------|-------------|
| **Escalabilidad Vertical** | Aumentar recursos de hardware (más CPU, RAM, almacenamiento) |
| **Escalabilidad Horizontal** | Replicación lógica, herramientas como **Citus** para entornos distribuidos |

---

## 9. Conclusiones Clave

- PostgreSQL es **flexible y potente**: soporta amplia gama de tipos de datos y operaciones avanzadas
- **Integridad garantizada** por transacciones ACID
- **Expansible** mediante extensiones y módulos
- La creación de bases de datos y tablas usa **comandos SQL estándar**

---

## Preguntas de Repaso

1. ¿Cuál es la diferencia entre el Postmaster y el proceso servidor en PostgreSQL?
2. ¿Qué es el WAL y por qué es importante?
3. ¿Qué es MVCC y qué problema resuelve?
4. ¿Cuáles son los subdirectorios más importantes de PGDATA y qué contiene cada uno?
5. ¿Qué es el Buffer Cache y cómo mejora el rendimiento?
6. ¿Cómo afecta la RAM al rendimiento de PostgreSQL?
7. ¿Qué consideraciones se deben tener al crear una base de datos para que sea eficiente?

---

## Ejemplo: Crear una Base de Datos y Tabla en PostgreSQL

```sql
-- Crear base de datos
CREATE DATABASE mi_tienda;

-- Conectarse a la base de datos
\c mi_tienda

-- Crear tabla
CREATE TABLE productos (
    id_producto SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    stock INTEGER DEFAULT 0
);

-- Insertar datos
INSERT INTO productos (nombre, precio, stock)
VALUES ('Laptop', 2500.00, 10);

-- Consultar datos
SELECT * FROM productos;
```
