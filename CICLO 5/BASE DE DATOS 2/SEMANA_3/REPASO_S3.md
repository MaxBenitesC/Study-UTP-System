# Repaso Semana 3 - PostgreSQL vs SQL Server + MER
**Curso:** Base de Datos II | **Unidad:** 1 - Bases de Datos Relacionales | **Sesión:** 3

---

## Logro de la Sesión
Comprender las diferencias entre PostgreSQL y SQL Server, y aplicar el Modelo Entidad-Relación (MER) en un caso práctico.

---

## PARTE A: PostgreSQL vs SQL Server

---

## 1. Comparación General

| Característica | PostgreSQL | SQL Server |
|---------------|-----------|-----------|
| Tipo | SGBD relacional **objeto-relacional** | SGBD relacional |
| Licencia | **Código abierto** (gratuito) | Producto **comercial** de Microsoft |
| Plataforma | Mayoría de sistemas operativos | Solo Microsoft Windows o Linux |
| Lenguaje SQL | SQL estándar | **T-SQL** (Transact-SQL: SQL estándar + extensiones) |

---

## 2. Ventajas y Desventajas

### PostgreSQL

| Ventajas | Desventajas |
|---------|------------|
| Altamente extensible (funciones, tipos de datos, lenguajes) | Rendimiento más lento en comparación con SQL Server y MySQL |
| Compatible con tipos de datos no estructurados (audio, video, imágenes) | Mejoras de velocidad requieren más trabajo |
| MVCC para procesamiento simultáneo sin interbloqueo | Instalación puede ser difícil para principiantes |
| Alta disponibilidad y recuperación de fallos | |
| Seguridad avanzada (cifrado SSL, autenticación SCRAM-SHA-256, LDAP) | |
| Comunidad open source activa | |
| Gratuito | |

### SQL Server

| Ventajas | Desventajas |
|---------|------------|
| Alto rendimiento en entornos OLTP | Sin compatibilidad con MVCC (usa bloqueos) |
| Capacidades de base de datos en memoria | **Costos elevados** de licencia, asistencia y funciones avanzadas |
| Seguridad integrada (alertas, supervisión, clasificación de datos) | Restricciones de hardware pueden requerir actualizaciones |
| Fácil de instalar con interfaz amigable | |
| Funciones prácticas de backup y recuperación | |
| Funciona bien con herramientas de análisis de Microsoft | |

---

## 3. Sintaxis: Diferencias Clave

| Operación | SQL Server (T-SQL) | PostgreSQL |
|-----------|-------------------|-----------|
| Limitar filas | `SELECT TOP 10 ...` | `SELECT ... LIMIT 10` |
| Fecha actual | `GETDATE()` | `CURRENT_DATE()` / `NOW()` |
| Parte de fecha | `DATEPART()` | `EXTRACT()` |
| Alias de columna | `SELECT AVG(col1) = avg1` | `SELECT AVG(col1) AS avg1` |
| Alias de tabla | `SELECT [col1], [col2]` | `SELECT col1, col2` |

### Compatibilidad con lenguajes de programación:
- **PostgreSQL**: Python, PHP, Perl, Tcl, .Net, C, C++, Delphi, Java, JavaScript (Node.js) y más
- **SQL Server**: Java, JavaScript (Node.js), C#, C++, PHP, Python, Ruby

---

## 4. Procedimientos Almacenados

Son **bloques de código SQL** almacenados y ejecutados en el servidor. Encapsulan operaciones repetitivas y complejas.

| Característica | PostgreSQL | SQL Server |
|---------------|-----------|-----------|
| Lenguaje | **PL/pgSQL**, PL/Python, PL/Perl | **T-SQL** (Transact-SQL) |
| Manejo de errores | Bloques `EXCEPTION` | Bloques `TRY...CATCH` |
| Flexibilidad | Múltiples lenguajes de procedimiento | Integración con .NET |
| Rendimiento | Bueno para cargas mixtas OLTP/OLAP | Excelente en entornos OLTP |
| Escalabilidad | Muy escalable, grandes volúmenes y conexiones concurrentes | Altamente escalable, múltiples instancias |

---

## 5. Administración de Usuarios y Seguridad

### PostgreSQL - Roles y Permisos
- Sistema de **roles** para gestionar usuarios y permisos
- Tipos: **Login Roles** (usuarios) y **Group Roles** (grupos)
- Permisos: `SELECT`, `INSERT`, `UPDATE`, `DELETE`, etc.
- Los roles pueden **heredar permisos** de otros roles
- Autenticación: `MD5`, `SCRAM-SHA-256`, `LDAP`, `Kerberos`
- Configuración: archivo **`pg_hba.conf`**
- Cifrado: **SSL** + cifrado a nivel de columna mediante extensiones
- Auditoría: extensión **pgaudit**

### SQL Server - Roles y Permisos
- Tipos: **Server Roles** y **Database Roles**
- Roles predefinidos: `sysadmin`, `db_owner`, `db_datareader`
- Autenticación de **Windows** (integrado con Active Directory) y SQL Server
- Cifrado: **SSL** + **TDE** (Transparent Data Encryption) + **Always Encrypted** (nivel columna)
- Auditoría integrada (inicio de sesión, acceso a BD, cambios de datos)

---

## 6. Backup y Recuperación

### PostgreSQL

| Tipo de Backup | Herramienta | Descripción |
|---------------|------------|-------------|
| Backup lógico | **pg_dump** | Script SQL para recrear la BD. Útil para migrar entre versiones |
| Backup físico | **pg_basebackup** | Copia física del directorio de datos. Para BDs grandes |
| Recuperación en el tiempo | **WAL Archiving** | Recuperación hasta un punto específico usando registros de transacciones |

### SQL Server

| Tipo de Backup | Descripción |
|---------------|-------------|
| **Backup Completo** | Copia completa de la BD en archivo `.bak`. Base para otros backups |
| **Backup Diferencial** | Solo los cambios desde el último backup completo. Ahorra tiempo y espacio |
| **Backup de Log de Transacciones** | Copia de registros. Permite restaurar hasta un punto específico |
| **SSMS** | Interfaz gráfica para administración de backups |

---

## ¿Cuándo usar PostgreSQL vs SQL Server?

| Usar PostgreSQL cuando... | Usar SQL Server cuando... |
|--------------------------|--------------------------|
| Presupuesto limitado (open source) | Ya se usa ecosistema Microsoft |
| Se necesita extensibilidad y tipos de datos complejos | Se requiere máximo rendimiento OLTP |
| Proyectos en Linux/Mac | Integración con Active Directory |
| Entorno multi-lenguaje | Soporte técnico empresarial garantizado |

---

## PARTE B: Modelo Entidad-Relación (MER)

---

## 7. ¿Qué es el MER?

El **Modelo Entidad-Relación (MER)** es una herramienta de diseño conceptual que permite representar gráficamente la estructura de una base de datos antes de implementarla.

**¿Por qué es importante?**
- Permite visualizar entidades, atributos y relaciones
- Detecta problemas de diseño antes de implementar
- Facilita la comunicación con el equipo
- Guía la implementación en SQL

---

## 8. Relación N:M (Muchos a Muchos)

Una relación **Muchos a Muchos (N:M)** ocurre cuando:
- Un registro de la tabla A puede relacionarse con muchos de la tabla B
- Un registro de la tabla B puede relacionarse con muchos de la tabla A

**¿Cómo se resuelve?** → Con una **tabla intermedia** (tabla de unión/pivote)

```
Consulta (1) ←→ (N) Consulta_Tratamiento (N) ←→ (1) Tratamiento
```

**¿Por qué es necesaria la tabla intermedia?**
- Evita redundancia de datos
- Permite agregar atributos propios a la relación
- Mantiene la integridad referencial
- Sin ella: columnas duplicadas, datos inconsistentes, anomalías de actualización

---

## 9. Caso Práctico: Sistema de Clínica Veterinaria

### Entidades y Atributos Principales

| Entidad | Atributos clave |
|---------|----------------|
| **Propietario** | id_propietario (PK), nombre, apellido, telefono, email |
| **Mascota** | id_mascota (PK), nombre, especie (perro/gato), raza, fecha_nacimiento, id_propietario (FK) |
| **Veterinario** | id_veterinario (PK), nombre, apellido, especialidad |
| **Consulta** | id_consulta (PK), fecha, motivo, diagnostico, id_mascota (FK), id_veterinario (FK) |
| **Tratamiento** | id_tratamiento (PK), nombre, descripcion, costo |
| **Consulta_Tratamiento** | id_consulta (FK), id_tratamiento (FK) ← **tabla intermedia N:M** |

### Diagrama de Relaciones

```
Propietario (1) ──────── (N) Mascota
                               │
                              (N)
                           Consulta ─────── (1) Veterinario
                              │
                             (N)
                    Consulta_Tratamiento
                              │
                             (N)
                          Tratamiento
```

### Implementación en PostgreSQL

```sql
-- Crear base de datos
CREATE DATABASE clinica_veterinaria;

-- Tabla Propietario
CREATE TABLE propietario (
    id_propietario SERIAL PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    apellido       VARCHAR(100) NOT NULL,
    telefono       VARCHAR(20),
    email          VARCHAR(150)
);

-- Tabla Mascota
CREATE TABLE mascota (
    id_mascota      SERIAL PRIMARY KEY,
    nombre          VARCHAR(100) NOT NULL,
    especie         VARCHAR(50) CHECK (especie IN ('perro', 'gato')),
    raza            VARCHAR(100),
    fecha_nacimiento DATE,
    id_propietario  INT NOT NULL REFERENCES propietario(id_propietario)
);

-- Tabla Veterinario
CREATE TABLE veterinario (
    id_veterinario SERIAL PRIMARY KEY,
    nombre         VARCHAR(100) NOT NULL,
    apellido       VARCHAR(100) NOT NULL,
    especialidad   VARCHAR(100)
);

-- Tabla Consulta
CREATE TABLE consulta (
    id_consulta    SERIAL PRIMARY KEY,
    fecha          DATE NOT NULL,
    motivo         TEXT,
    diagnostico    TEXT,
    id_mascota     INT NOT NULL REFERENCES mascota(id_mascota),
    id_veterinario INT NOT NULL REFERENCES veterinario(id_veterinario)
);

-- Tabla Tratamiento
CREATE TABLE tratamiento (
    id_tratamiento SERIAL PRIMARY KEY,
    nombre         VARCHAR(150) NOT NULL,
    descripcion    TEXT,
    costo          DECIMAL(10,2)
);

-- Tabla intermedia Consulta_Tratamiento (N:M)
CREATE TABLE consulta_tratamiento (
    id_consulta    INT REFERENCES consulta(id_consulta),
    id_tratamiento INT REFERENCES tratamiento(id_tratamiento),
    PRIMARY KEY (id_consulta, id_tratamiento)
);
```

---

## 10. Ventajas del MER antes de implementar

1. **Claridad visual** de la estructura antes de escribir SQL
2. **Detectar relaciones N:M** y resolverlas con tablas intermedias
3. **Evitar errores costosos** de rediseño tras la implementación
4. **Comunicación clara** entre diseñadores, desarrolladores y clientes
5. Facilita la **normalización** de datos

---

## Preguntas de Repaso

1. ¿Cuál es la diferencia principal entre PL/pgSQL y T-SQL?
2. ¿Cuándo usarías `pg_dump` vs `pg_basebackup`?
3. ¿Qué es `pg_hba.conf` y para qué sirve?
4. ¿Qué problemas surgirían si no existiera la tabla `consulta_tratamiento` en el caso de la veterinaria?
5. ¿Por qué PostgreSQL no tiene MVCC en SQL Server y cómo lo suple SQL Server?
6. ¿Qué ventajas tiene el MER sobre comenzar directo con el SQL?
7. Define la cardinalidad de cada relación en el sistema de la veterinaria.
