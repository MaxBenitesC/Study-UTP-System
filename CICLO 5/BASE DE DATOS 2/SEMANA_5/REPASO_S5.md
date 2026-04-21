# Repaso Semana 5 - Estructura de Funcionamiento de Oracle Database
**Curso:** Base de Datos II | **Unidad:** 1 - Bases de Datos Relacionales | **Sesión:** 5 (Semanas 9 y 10)

---

## Logro de la Sesión
Comprender la estructura de funcionamiento de Oracle Database, la creación de bases de datos y tablas para gestionar datos de manera segura y efectiva en entornos empresariales.

---

## 1. Conceptos Fundamentales de Oracle

### Instancia vs Base de Datos

| Término | Descripción |
|---------|-------------|
| **Instancia** | Conjunto de procesos del SO y estructuras de memoria que permiten acceso a la BD. Incluye SGA y PGA |
| **Base de Datos** | Conjunto de archivos físicos donde se almacenan los datos reales |

> Una instancia puede gestionar una o más bases de datos simultáneamente.

---

### ¿Qué es una Instancia? (explicación simple)

**Analogía:** Piensa en un restaurante.

```
LA BASE DE DATOS = La despensa y el almacén
                   (donde están los ingredientes guardados físicamente)

LA INSTANCIA     = Los cocineros + la cocina funcionando
                   (los que toman los ingredientes y los preparan)
```

> Sin cocineros (instancia), el almacén (base de datos) existe pero nadie puede acceder a los datos.
> Sin almacén (base de datos), los cocineros (instancia) no tienen con qué trabajar.

**En términos técnicos:**

```
INSTANCIA = RAM activa + procesos corriendo en el sistema operativo

          ┌──────────────────────────────────┐
          │  INSTANCIA (vive en la memoria)   │
          │                                  │
          │  • SGA  → memoria compartida      │
          │  • PGA  → memoria por usuario     │
          │  • DBWR → proceso que escribe     │
          │  • LGWR → proceso que registra    │
          │                                  │
          │  ← desaparece si apagas el server │
          └──────────────────────────────────┘
                          ↕ accede a
          ┌──────────────────────────────────┐
          │  BASE DE DATOS (vive en el disco) │
          │                                  │
          │  • Archivos .dbf  → datos reales  │
          │  • Archivos .ctl  → metadatos     │
          │  • Archivos .log  → transacciones │
          │                                  │
          │  ← persiste aunque apagues todo  │
          └──────────────────────────────────┘
```

**Ejemplo concreto:**

Tienes una empresa con Oracle. Guardas la tabla `clientes` con 50,000 registros.

- Los 50,000 registros están en un archivo `.dbf` en el disco → eso es la **base de datos**
- Cuando Oracle arranca, carga parte de esos datos en RAM y lanza procesos para atender consultas → eso es la **instancia**
- Si se va la luz, la **instancia se destruye** (la memoria RAM se borra)
- Pero cuando vuelve la luz y arrancas Oracle, los 50,000 registros siguen en el disco intactos → la **base de datos sobrevive**

**Resumen en una línea:**

> La **instancia** es Oracle "encendido y corriendo en memoria". La **base de datos** son los archivos guardados en disco.

---

---

## 2. Arquitectura de Oracle Database

### Componentes Principales

```
┌─────────────────────────────────────────────┐
│          INSTANCIA ORACLE                    │
│  ┌────────────────────────────────────────┐ │
│  │  SGA (System Global Area)              │ │
│  │  - Database Buffer Cache (datos)       │ │
│  │  - Shared Pool (diccionario SQL)       │ │
│  │  - Redo Log Buffer (transacciones)     │ │
│  └────────────────────────────────────────┘ │
│  ┌────────────────────────────────────────┐ │
│  │  PGA (Program Global Area)             │ │
│  │  - Memoria privada por usuario         │ │
│  │  - Stack de ejecución                  │ │
│  └────────────────────────────────────────┘ │
│  ┌────────────────────────────────────────┐ │
│  │  Procesos de Fondo                     │ │
│  │  - DBWR, LGWR, CKPT, SMON, PMON       │ │
│  └────────────────────────────────────────┘ │
└─────────────────────────────────────────────┘
                      ↓
    ┌─────────────────────────────────┐
    │   BASE DE DATOS (Archivos)      │
    │ ┌─────────────────────────────┐ │
    │ │ Archivos de Datos (.dbf)    │ │
    │ │ Archivos de Control         │ │
    │ │ Archivos de Redo Log        │ │
    │ └─────────────────────────────┘ │
    └─────────────────────────────────┘
```

### SGA (System Global Area)

| Componente | Función |
|-----------|---------|
| **Database Buffer Cache** | Almacena bloques de datos leídos desde archivos. Reduce I/O de disco |
| **Shared Pool** | Almacena diccionario de datos y declaraciones SQL compiladas |
| **Redo Log Buffer** | Almacena registros de transacciones antes de escribirse al disco |
| **Large Pool** | Memoria adicional para operaciones específicas |

### PGA (Program Global Area)

- **Área de memoria privada** para cada proceso/usuario
- Contiene variables locales y stack de ejecución
- No compartida entre usuarios
- Se crea al conectar, se libera al desconectar

---

## 3. Procesos de Fondo Críticos

| Proceso | Sigla | Función |
|---------|-------|---------|
| **Database Writer** | DBWR | Escribe bloques modificados del buffer cache a archivos de datos |
| **Log Writer** | LGWR | Escribe redo log buffer a archivos de redo log |
| **Checkpoint** | CKPT | Sincroniza datos en disco y actualiza archivos de control |
| **System Monitor** | SMON | Recuperación automática y limpieza de memoria compartida |
| **Process Monitor** | PMON | Recupera procesos fallidos y libera recursos |

> Estos procesos garantizan integridad, recuperación y optimización de la BD

---

## 4. Estructura de Archivos Oracle

### Tipos de Archivos Principales

| Archivo | Extensión | Función |
|---------|-----------|---------|
| **Archivos de Datos** | `.dbf` | Almacenan datos reales de tablas e índices |
| **Archivos de Control** | `.ctl` | Mantienen metadatos y estructura de la BD |
| **Archivos de Redo Log** | `.log` | Registran todas las transacciones para recuperación |
| **Parameter File** | `.ora` | Contiene parámetros de inicialización |

### Tablespaces

Un tablespace es una **unidad lógica de almacenamiento** que agrupa uno o más archivos de datos.

| Tipo | Propósito |
|------|-----------|
| **Tablespace de Usuario** | Almacena objetos de usuario (tablas, índices) |
| **Tablespace Temporal** | Operaciones temporales (ordenaciones, agrupaciones) |
| **Tablespace de Sistema** | Objetos del sistema y diccionario de datos |
| **Tablespace Undo** | Almacena versiones anteriores de datos para reversión |

---

## 5. Tipos de Datos en Oracle

### Tipos Numéricos

```sql
NUMBER(p,s)      -- Número con precisión y escala (10,2 = 8 enteros, 2 decimales)
FLOAT(p)         -- Punto flotante
INTEGER          -- Números enteros
```

### Tipos de Cadena

```sql
CHAR(n)          -- Cadena fija (rellena con espacios)
VARCHAR2(n)      -- Cadena variable (recomendado)
CLOB             -- Caracteres grandes (hasta 4GB)
NCHAR(n)         -- Cadena nacional fija
NVARCHAR2(n)     -- Cadena nacional variable
```

### Tipos de Fecha y Hora

```sql
DATE                          -- Fecha y hora hasta el segundo
TIMESTAMP                     -- Con fracciones de segundo
TIMESTAMP WITH TIME ZONE      -- Con información de zona horaria
INTERVAL YEAR TO MONTH        -- Intervalo en años/meses
INTERVAL DAY TO SECOND        -- Intervalo en días/horas/minutos/segundos
```

### Tipos Especializados

```sql
RAW                -- Datos binarios fijos
LONG RAW           -- Datos binarios variables
BLOB               -- Objetos binarios grandes (hasta 4GB)
BFILE              -- Puntero a archivo binario externo
ROWID              -- Dirección única de una fila
```

---

## 6. Funciones de Oracle

Oracle tiene funciones integradas para manipular datos sin necesidad de código externo. Se dividen en 4 categorías:

---

### Funciones de Cadena (String)

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `CONCAT(a, b)` | Une dos cadenas | `CONCAT('Hola', ' Mundo')` → `'Hola Mundo'` |
| `SUBSTR(str, inicio, largo)` | Extrae parte de una cadena | `SUBSTR('Oracle DB', 1, 6)` → `'Oracle'` |
| `LENGTH(str)` | Cantidad de caracteres | `LENGTH('Hola')` → `4` |
| `UPPER(str)` | Convierte a mayúsculas | `UPPER('oracle')` → `'ORACLE'` |
| `LOWER(str)` | Convierte a minúsculas | `LOWER('ORACLE')` → `'oracle'` |
| `TRIM(str)` | Elimina espacios al inicio y fin | `TRIM('  hola  ')` → `'hola'` |
| `REPLACE(str, busca, reemplaza)` | Reemplaza texto | `REPLACE('BD Oracle', 'Oracle', 'SQL')` → `'BD SQL'` |
| `INSTR(str, busca)` | Posición de un texto dentro de otro | `INSTR('Oracle DB', 'DB')` → `8` |

```sql
-- Ejemplos ejecutables en Oracle
SELECT CONCAT('Hola', ' Mundo') FROM dual;
SELECT SUBSTR('Oracle Database', 1, 6) FROM dual;   -- Oracle
SELECT UPPER('max garcia') FROM dual;                -- MAX GARCIA
SELECT REPLACE('BD Oracle', 'Oracle', 'SQL') FROM dual; -- BD SQL
SELECT LENGTH('Base de Datos') FROM dual;            -- 13
```

> `dual` es una tabla especial de Oracle de una sola fila, usada para evaluar expresiones y funciones.

---

### Funciones de Fecha y Hora

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `SYSDATE` | Fecha y hora actual del servidor | `SELECT SYSDATE FROM dual` |
| `SYSTIMESTAMP` | Fecha+hora actual con milisegundos | `SELECT SYSTIMESTAMP FROM dual` |
| `ADD_MONTHS(fecha, n)` | Suma n meses a una fecha | `ADD_MONTHS(SYSDATE, 3)` |
| `MONTHS_BETWEEN(f1, f2)` | Meses entre dos fechas | `MONTHS_BETWEEN(fecha_fin, fecha_inicio)` |
| `TRUNC(fecha)` | Trunca la hora (deja solo la fecha) | `TRUNC(SYSDATE)` |
| `TO_DATE(texto, formato)` | Convierte texto a fecha | `TO_DATE('20/04/2026', 'DD/MM/YYYY')` |
| `TO_CHAR(fecha, formato)` | Convierte fecha a texto | `TO_CHAR(SYSDATE, 'DD/MM/YYYY')` |

```sql
-- Fecha actual
SELECT SYSDATE FROM dual;

-- Cuántos meses lleva registrado un producto
SELECT MONTHS_BETWEEN(SYSDATE, fecha_creacion) AS meses_activo
FROM productos;

-- Mostrar la fecha con formato legible
SELECT TO_CHAR(SYSDATE, 'DD/MM/YYYY HH24:MI') AS ahora FROM dual;

-- Sumar 6 meses a hoy
SELECT ADD_MONTHS(SYSDATE, 6) AS vence FROM dual;
```

---

### Funciones Matemáticas

| Función | Qué hace | Ejemplo |
|---------|----------|---------|
| `ROUND(n, decimales)` | Redondea al número de decimales | `ROUND(3.567, 2)` → `3.57` |
| `TRUNC(n, decimales)` | Trunca sin redondear | `TRUNC(3.567, 2)` → `3.56` |
| `ABS(n)` | Valor absoluto | `ABS(-15)` → `15` |
| `MOD(n, divisor)` | Resto de la división | `MOD(10, 3)` → `1` |
| `POWER(base, exp)` | Potencia | `POWER(2, 8)` → `256` |
| `SQRT(n)` | Raíz cuadrada | `SQRT(16)` → `4` |
| `CEIL(n)` | Redondea hacia arriba | `CEIL(3.2)` → `4` |
| `FLOOR(n)` | Redondea hacia abajo | `FLOOR(3.9)` → `3` |

```sql
SELECT ROUND(1234.567, 2) FROM dual;   -- 1234.57
SELECT TRUNC(1234.567, 2) FROM dual;   -- 1234.56
SELECT MOD(17, 5) FROM dual;           -- 2
SELECT POWER(2, 10) FROM dual;         -- 1024
```

---

### Funciones de Agregación (GROUP BY)

Estas operan sobre **grupos de filas**, no sobre una fila individual.

| Función | Qué hace |
|---------|----------|
| `COUNT(*)` | Cuenta el número de filas |
| `SUM(col)` | Suma los valores de una columna |
| `AVG(col)` | Promedio de los valores |
| `MAX(col)` | Valor máximo |
| `MIN(col)` | Valor mínimo |

```sql
-- Cuántos productos hay en total
SELECT COUNT(*) AS total_productos FROM productos;

-- Precio promedio, máximo y mínimo
SELECT
    AVG(precio_unitario) AS promedio,
    MAX(precio_unitario) AS mas_caro,
    MIN(precio_unitario) AS mas_barato
FROM productos;

-- Total de ventas por categoría
SELECT
    id_categoria,
    COUNT(*) AS cantidad,
    SUM(precio_unitario * stock) AS valor_inventario
FROM productos
GROUP BY id_categoria;
```

---

### Función NVL (manejo de NULL)

`NVL(valor, reemplazo)` → si `valor` es NULL, devuelve `reemplazo`.

```sql
-- Si no hay descripción, mostrar 'Sin descripción'
SELECT nombre, NVL(descripcion, 'Sin descripción') FROM productos;

-- Si no hay precio, asumir 0 para el cálculo
SELECT nombre, NVL(precio_unitario, 0) * stock AS valor_total FROM productos;
```

---

## 7. SQL vs PL/SQL en Oracle

### ¿Cuál es la diferencia?

```
SQL     = Le haces UNA pregunta o UNA instrucción a la base de datos
PL/SQL  = Le escribes un PROGRAMA completo con lógica, condiciones y repeticiones
```

**Analogía:**
- **SQL** es como enviarle un mensaje de texto a alguien: corto, directo, una sola acción.
- **PL/SQL** es como escribirle un procedimiento paso a paso: "si pasa esto, haz aquello, repite hasta que..."

| | SQL | PL/SQL |
|-|-----|--------|
| ¿Qué es? | Lenguaje de consulta | Lenguaje de programación sobre SQL |
| ¿Para qué? | SELECT, INSERT, UPDATE, DELETE | Lógica, procedimientos, funciones, triggers |
| ¿Tiene IF/LOOP? | No | Sí |
| ¿Tiene variables? | No | Sí |
| ¿Se ejecuta? | Línea a línea | Como un bloque completo |

---

### Estructura básica de un bloque PL/SQL

```sql
DECLARE
    -- Aquí declaras tus variables (opcional)
    nombre_var VARCHAR2(100);
    precio     NUMBER := 0;
BEGIN
    -- Aquí va el código principal (obligatorio)
    SELECT nombre INTO nombre_var FROM productos WHERE id_producto = 1;
    DBMS_OUTPUT.PUT_LINE('Producto: ' || nombre_var);
EXCEPTION
    -- Aquí manejas errores (opcional)
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('No se encontró el producto');
END;
/
```

> La `/` al final ejecuta el bloque en Oracle SQL*Plus o SQL Developer.

---

### Variables en PL/SQL

```sql
DECLARE
    -- Tipo fijo
    nombre     VARCHAR2(100) := 'Laptop HP';
    precio     NUMBER(10,2)  := 2800.00;
    activo     BOOLEAN       := TRUE;

    -- Tipo dinámico (toma el tipo de la columna automáticamente)
    v_nombre   productos.nombre%TYPE;       -- mismo tipo que columna nombre
    v_precio   productos.precio_unitario%TYPE;
BEGIN
    DBMS_OUTPUT.PUT_LINE('Nombre: ' || nombre);
    DBMS_OUTPUT.PUT_LINE('Precio: S/ ' || precio);
END;
/
```

---

### Condicionales IF / ELSIF / ELSE

```sql
DECLARE
    nota NUMBER := 14;
BEGIN
    IF nota >= 18 THEN
        DBMS_OUTPUT.PUT_LINE('Excelente');
    ELSIF nota >= 14 THEN
        DBMS_OUTPUT.PUT_LINE('Aprobado');
    ELSIF nota >= 11 THEN
        DBMS_OUTPUT.PUT_LINE('Regular');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Desaprobado');
    END IF;
END;
/
```

---

### Bucles (LOOP)

```sql
-- LOOP básico (necesita EXIT manual)
DECLARE
    i NUMBER := 1;
BEGIN
    LOOP
        DBMS_OUTPUT.PUT_LINE('Vuelta: ' || i);
        i := i + 1;
        EXIT WHEN i > 5;
    END LOOP;
END;
/

-- FOR LOOP (más común y limpio)
BEGIN
    FOR i IN 1..5 LOOP
        DBMS_OUTPUT.PUT_LINE('Item ' || i);
    END LOOP;
END;
/

-- WHILE LOOP
DECLARE
    i NUMBER := 1;
BEGIN
    WHILE i <= 5 LOOP
        DBMS_OUTPUT.PUT_LINE('Contador: ' || i);
        i := i + 1;
    END WHILE;
END;
/
```

---

### Procedimientos Almacenados (Stored Procedures)

Un procedimiento es un **bloque PL/SQL guardado con nombre** que puedes llamar cuando quieras. Es como guardar una receta para ejecutarla después.

```sql
-- Crear el procedimiento
CREATE OR REPLACE PROCEDURE actualizar_precio(
    p_id     IN NUMBER,
    p_nuevo  IN NUMBER
) AS
BEGIN
    UPDATE productos
    SET precio_unitario = p_nuevo
    WHERE id_producto = p_id;

    IF SQL%ROWCOUNT = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Producto no encontrado');
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Precio actualizado correctamente');
    END IF;
END;
/

-- Llamar el procedimiento
EXEC actualizar_precio(1, 3200.00);
-- o también:
BEGIN
    actualizar_precio(1, 3200.00);
END;
/
```

---

### Funciones PL/SQL

Una función es como un procedimiento pero **devuelve un valor**.

```sql
-- Crear función que calcula el precio con descuento
CREATE OR REPLACE FUNCTION precio_con_descuento(
    p_precio    IN NUMBER,
    p_descuento IN NUMBER  -- porcentaje, ej: 10 = 10%
) RETURN NUMBER AS
    v_resultado NUMBER;
BEGIN
    v_resultado := p_precio * (1 - p_descuento / 100);
    RETURN ROUND(v_resultado, 2);
END;
/

-- Usar la función en una consulta
SELECT
    nombre,
    precio_unitario AS precio_original,
    precio_con_descuento(precio_unitario, 15) AS precio_con_15pct_descuento
FROM productos;
```

---

### Triggers (Disparadores)

Un trigger es un **bloque PL/SQL que se ejecuta automáticamente** cuando ocurre algo en una tabla (INSERT, UPDATE, DELETE). No se llama manualmente.

```sql
-- Trigger que registra auditoría cuando se actualiza un precio
CREATE OR REPLACE TRIGGER trg_auditoria_precio
BEFORE UPDATE OF precio_unitario ON productos
FOR EACH ROW
BEGIN
    INSERT INTO auditoria_precios (
        id_producto,
        precio_anterior,
        precio_nuevo,
        fecha_cambio,
        usuario
    ) VALUES (
        :OLD.id_producto,
        :OLD.precio_unitario,
        :NEW.precio_unitario,
        SYSDATE,
        USER
    );
END;
/
-- :OLD = valores antes del cambio
-- :NEW = valores nuevos
-- USER = usuario de Oracle que hizo el cambio
```

> Cuando alguien haga `UPDATE productos SET precio_unitario = 3500 WHERE id_producto = 1`, el trigger se dispara solo y guarda el historial.

---

### Cuándo usar SQL y cuándo PL/SQL

| Situación | Usar |
|-----------|------|
| Consultar datos (`SELECT`) | SQL |
| Insertar/actualizar/eliminar simple | SQL |
| Lógica condicional (si/sino) | PL/SQL |
| Repetir algo N veces | PL/SQL |
| Guardar lógica reutilizable | PL/SQL (procedimiento) |
| Calcular y devolver un valor | PL/SQL (función) |
| Reaccionar automáticamente a cambios | PL/SQL (trigger) |
| Manejo de errores complejo | PL/SQL |

---

## 9. Alta Disponibilidad en Oracle

### Oracle RAC (Real Application Clusters)

**Permite que una BD funcione en múltiples servidores simultáneamente**

| Ventaja | Detalle |
|---------|---------|
| **Alta Disponibilidad** | Si un servidor falla, operaciones continúan en otros |
| **Escalabilidad** | Distribuye carga entre múltiples servidores |
| **Load Balancing** | Distribuye trabajo automáticamente |
| **Sin punto único de fallo** | Redundancia total |

### Oracle Data Guard

**Proporciona protección ante desastres mediante BD en espera (standby)**

- BD **primaria** en operación normal
- BD **standby** en espera, replica cambios en tiempo real
- Si falla primaria, standby **asume automáticamente** el control
- Protección contra pérdida de datos y desastres

---

## 10. Recuperación ante Desastres

### Flashback Technology

Permite **retroceder el estado de la BD a un punto anterior en el tiempo**

```sql
-- Recuperar una tabla a un punto anterior
FLASHBACK TABLE mi_tabla TO TIMESTAMP (SYSTIMESTAMP - INTERVAL '1' HOUR);

-- Ver qué cambios se hicieron
SELECT * FROM flashback_transaction_query;
```

**Ventajas:**
- Recuperación de errores lógicos sin restaurar toda la BD
- Recuperación de corrupción de datos
- No requiere downtime completo

### RMAN (Recovery Manager)

**Herramienta integral de backup y recuperación**

| Tipo de Backup | Descripción |
|----------------|-------------|
| **Backup Completo** | Copia todos los bloques de datos de la BD |
| **Backup Incremental** | Solo bloques modificados desde último backup |
| **Backup Diferencial** | Bloques modificados desde último backup incremental |

```sql
-- Backup completo con RMAN
BACKUP DATABASE;

-- Backup incremental
BACKUP INCREMENTAL LEVEL 1 DATABASE;

-- Restaurar desde backup
RESTORE DATABASE;
```

---

## 11. Performance Tuning en Oracle

### ¿Qué es el Performance Tuning?

**Afinamiento de la base de datos** = proceso de cambiar la configuración de Oracle para que funcione más rápido y consuma menos recursos.

> No es solo comprar mejor hardware. Muchas veces el problema está en cómo están escritas las consultas o cómo está diseñada la base de datos.

**Analogía:** Afinar una BD es como optimizar un auto. Puedes poner un motor nuevo (más CPU/RAM) o puedes revisar si las ruedas están bien infladas, el aceite es el correcto y el camino que toma es el más corto (consultas eficientes, índices, diseño).

---

### Factor 1: CPU

La CPU procesa las operaciones complejas. Cada consulta que envías genera lo que se llama un **Plan de Ejecución** — Oracle evalúa decenas de formas de ejecutar la query y elige la "más barata".

```
Operaciones que consumen más CPU:
┌────────────────────────────────────────┐
│ • Agregaciones: SUM, COUNT, AVG, MAX   │
│ • Uniones: JOIN entre múltiples tablas │
│ • Ordenamientos: ORDER BY, GROUP BY    │
│ • Operaciones HASH                     │
│ • Generar el Plan de Ejecución         │
└────────────────────────────────────────┘
```

**Regla de oro:** Antes de comprar más CPU, **optimiza primero las consultas**. Ampliar CPU es muy costoso y muchas veces innecesario si las queries están mal escritas.

---

### Factor 2: RAM (Memoria)

La RAM es crítica porque el **Buffer Pool (SGA)** vive ahí. Cuando ejecutas una consulta, Oracle primero carga los datos del disco a la RAM y trabaja desde ahí.

```
disco → RAM → procesamiento → resultado al usuario

Leer del disco: ~10 ms
Leer de la RAM: ~0.0001 ms  (100,000x más rápido)
```

**¿Qué pasa si falta RAM?**
- Las queries se caen con error `ORA-04031: unable to allocate memory`
- Otros procesos del servidor se cierran abruptamente para liberar espacio
- Oracle empieza a leer datos del disco constantemente (muy lento)

**Regla:** Nunca está mal tener más RAM de la necesaria. Es la inversión más rentable para Oracle.

---

### Factor 3: Disco (I/O)

Todo dato está en disco. Cada vez que Oracle necesita algo que no está en RAM, va al disco. La velocidad de lectura/escritura del disco impacta directamente el rendimiento.

| Tipo de disco | Velocidad relativa | Recomendación |
|--------------|-------------------|---------------|
| Disco magnético HDD | Lenta | Solo para archivos de backup fríos |
| SSD SATA | Media | Aceptable para BD medianas |
| SSD NVMe | Muy rápida | Ideal para tablespaces de producción |

**Estrategias de I/O:**
- Poner cada tablespace en un disco físico diferente (distribuye las lecturas)
- Separar los Redo Logs de los archivos de datos en discos distintos
- Usar SSD para los datos más consultados

---

### Factor 4: Red

Si Oracle está en un servidor separado de la aplicación (lo más común en empresas), la red es el canal por donde viajan todos los datos.

```
Aplicación Web ←──── RED ────→ Servidor Oracle
                (aquí puede estar el cuello de botella)
```

**Problemas comunes:**
- Latencia de red alta → cada query tarda más en llegar
- Ancho de banda insuficiente → múltiples usuarios saturan el canal
- Conectividad lenta entre nodos de RAC

**Soluciones:**
- Tarjetas de red de alta velocidad (1Gbps o 10Gbps)
- Minimizar los "viajes" de datos: procesar la mayor lógica posible dentro de la BD (con PL/SQL) en lugar de traer todos los datos a la aplicación

---

### Diseño de Base de Datos para Rendimiento

El diseño es el paso más importante y el más ignorado. Una BD mal diseñada no se arregla con más hardware.

**1. Índices**
```sql
-- Sin índice: Oracle lee toda la tabla (Full Table Scan) → LENTO
SELECT * FROM clientes WHERE email = 'max@correo.com';

-- Con índice: Oracle va directo a la fila → RÁPIDO
CREATE INDEX idx_clientes_email ON clientes(email);
```
> Crea índices en columnas que uses frecuentemente en cláusulas `WHERE` y `JOIN`.

**2. Particionar tablas grandes**
```sql
-- Tabla de ventas dividida por año
CREATE TABLE ventas (
    id_venta  NUMBER,
    fecha     DATE,
    monto     NUMBER
)
PARTITION BY RANGE (fecha) (
    PARTITION p2024 VALUES LESS THAN (DATE '2025-01-01'),
    PARTITION p2025 VALUES LESS THAN (DATE '2026-01-01'),
    PARTITION p2026 VALUES LESS THAN (DATE '2027-01-01')
);
-- Consultar solo 2026 solo escanea 1 partición, no toda la tabla
```

**3. Normalización**
Evitar datos repetidos reduce el tamaño de las tablas y hace las actualizaciones más rápidas.

**4. Indexar columnas FK**
```sql
-- Si haces JOIN frecuente entre pedidos y clientes:
CREATE INDEX idx_pedidos_cliente ON pedidos(id_cliente);
```

---

### Optimización del Sistema Operativo

El SO es la capa entre Oracle y el hardware. Si está mal configurado, Oracle no puede aprovechar todo el hardware disponible.

**Configuraciones clave:**

| Parámetro del SO | Qué hace | Impacto |
|-----------------|----------|---------|
| Tamaño de buffer de lectura | Cuánto lee el SO en cada operación de disco | Lectura de archivos grandes más eficiente |
| Páginas de memoria grandes (HugePages) | Reduce overhead de gestión de RAM | Mejora rendimiento del SGA |
| TCP Keepalive | Mantiene conexiones activas entre cliente y servidor Oracle | Evita errores de conexión cortada |
| Límites de archivos abiertos | Cuántos archivos puede abrir Oracle simultáneamente | Evita errores `ORA-27037` |

**Ejemplo de error por mala configuración de red/SO:**
```
ORA-12170: TNS: Connect Timeout Occurred
→ La conexión TCP se cortó por inactividad
→ Solución: ajustar TCP keepalive en el SO para mantener la conexión viva
```

> Cada SO (Linux, Windows, AIX) tiene sus propios parámetros. Oracle publica guías específicas para cada uno.

---

## 12. Control de Concurrencia y Transacciones

### ¿Qué es la concurrencia?

Cuando **múltiples usuarios** acceden a la misma BD al mismo tiempo, pueden surgir conflictos. La concurrencia es cómo Oracle maneja eso.

**Problema real:**
```
Usuario A: Leyendo el saldo de cuenta 1001 → ve S/ 1,000
Usuario B: En ese mismo momento descuenta S/ 500 de la cuenta 1001
Usuario A: Completa su operación basándose en S/ 1,000 (dato viejo)
→ INCONSISTENCIA
```

---

### Transacciones: COMMIT y ROLLBACK

Una **transacción** es un grupo de operaciones que se ejecutan como una sola unidad. O todas se completan, o ninguna.

```sql
-- Ejemplo: transferencia bancaria
BEGIN
    -- Paso 1: descontar de cuenta origen
    UPDATE cuentas SET saldo = saldo - 500 WHERE id_cuenta = 1001;

    -- Paso 2: abonar a cuenta destino
    UPDATE cuentas SET saldo = saldo + 500 WHERE id_cuenta = 2002;

    -- Si ambas operaciones OK → confirmar todo
    COMMIT;

EXCEPTION
    WHEN OTHERS THEN
        -- Si algo falló → deshacer todo como si nada hubiera ocurrido
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/
```

| Comando | Qué hace |
|---------|----------|
| `COMMIT` | Confirma todos los cambios y los hace permanentes en el disco |
| `ROLLBACK` | Deshace todos los cambios de la transacción actual |
| `SAVEPOINT nombre` | Marca un punto intermedio para hacer ROLLBACK parcial |

```sql
-- SAVEPOINT: rollback parcial
UPDATE productos SET stock = stock - 1 WHERE id_producto = 5;
SAVEPOINT despues_descuento;

UPDATE pedidos SET estado = 'procesado' WHERE id_pedido = 101;
-- Algo falló en este punto...

ROLLBACK TO SAVEPOINT despues_descuento;
-- Deshace solo el UPDATE de pedidos, mantiene el de stock
```

---

### Bloqueos (Locks)

Oracle bloquea filas automáticamente para evitar que dos usuarios modifiquen el mismo dato simultáneamente.

```
Usuario A hace UPDATE en fila X → Oracle pone bloqueo exclusivo en fila X
Usuario B intenta UPDATE en fila X → Espera hasta que A haga COMMIT o ROLLBACK
Usuario B puede hacer SELECT en fila X sin problema (lectura no bloquea)
```

| Tipo de bloqueo | Qué permite | Ejemplo |
|----------------|-------------|---------|
| **Exclusivo (X)** | Solo el que lo tiene puede modificar | UPDATE, DELETE |
| **Compartido (S)** | Múltiples usuarios pueden leer | SELECT normal |

**Ventaja de Oracle (MVCC):** Oracle usa **Multi-Version Concurrency Control**. Cuando haces SELECT, ves una "foto" del dato al momento en que empezaste tu consulta, aunque alguien más lo esté modificando al mismo tiempo. Nunca bloquea lecturas.

```sql
-- Bloqueo manual explícito (cuando necesitas reservar una fila antes de procesarla)
SELECT * FROM pedidos WHERE id_pedido = 101 FOR UPDATE;
-- Nadie más puede modificar el pedido 101 hasta que hagas COMMIT
```

---

### Propiedades ACID

Toda transacción en Oracle cumple **ACID**:

| Propiedad | Significado | Ejemplo |
|-----------|-------------|---------|
| **A**tomicidad | Todo o nada | La transferencia: o descuenta Y abona, o no hace nada |
| **C**onsistencia | La BD siempre queda en estado válido | No puede quedar un saldo negativo si hay CHECK |
| **I**solamiento | Las transacciones no se interfieren entre sí | Usuario A no ve cambios de Usuario B hasta su COMMIT |
| **D**urabilidad | Un COMMIT confirmado sobrevive a fallos | Si se va la luz después del COMMIT, el dato está guardado |

---

## 13. Estructura de Gestión de Información

### Elementos Básicos de una BD Relacional

| Elemento | Descripción |
|----------|-------------|
| **Tablas** | Mecanismo principal de almacenamiento (similar a hoja de cálculo) |
| **Columnas** | Propiedades/campos de la tabla (nombre, tipo de dato, restricciones) |
| **Filas** | Registros individuales, instancias de datos |

### Esquemas (Schemas)

Un **schema es un contenedor lógico** de objetos de BD pertenecientes a un usuario específico

```sql
-- Crear un schema para un usuario
CREATE USER ventas IDENTIFIED BY password;
GRANT CREATE SESSION, CREATE TABLE TO ventas;

-- Los objetos creados por 'ventas' pertenecen a schema 'ventas'
CREATE TABLE ventas.productos (
    id_producto NUMBER,
    nombre VARCHAR2(100)
);
```

---

## 14. Conclusiones Clave

### Arquitectura Modular y Escalable

- Separación clara entre instancia y BD
- Permite gestión eficiente de recursos
- Escalable para grandes volúmenes de datos

### Memoria y Procesos Eficientes

- **SGA + PGA** → gestión eficiente de acceso a datos
- **Procesos de fondo** → aseguran integridad, recuperación y optimización
- Tareas críticas automáticas (escritura, recuperación, limpieza)

### Estructuras de Almacenamiento Robustas

- **Archivos de datos** → almacenamiento principal
- **Archivos de control** → metadatos de estructura
- **Redo logs** → recuperación ante fallos y auditoría

### Control de Concurrencia y Transacciones

- Modelo robusto de bloqueos
- Control de versiones para lecturas consistentes
- Garantiza integridad en operaciones simultáneas

### Recuperación y Disponibilidad

- **RAC** → múltiples servidores, sin punto único de fallo
- **Data Guard** → replicación en standby automática
- **RMAN** → backups y recuperación flexible
- **Flashback** → recuperación sin downtime

### Alto Rendimiento

- Recuperación automática
- Manejo eficiente de recursos
- Optimización automática de acceso a datos
- Escalable horizontalmente con RAC

---

## 15. Diferencias Clave: Oracle vs PostgreSQL

| Aspecto | Oracle | PostgreSQL |
|--------|--------|-----------|
| **Instancia** | Separada de la BD, compleja | Proceso único |
| **Recuperación** | RMAN + RAC + Data Guard | WAL + replicación simple |
| **Alto rendimiento** | Automatización avanzada | Manual, comunidad |
| **Memoria** | SGA + PGA complejos | Más simple |
| **Alta disponibilidad** | RAC nativo | Requiere herramientas externas |
| **Costo** | Muy elevado | Gratuito |

---

## Caso Práctico: Sistema de Gestión de Inventarios (del PPTX)

> Resuelto con la metodología de los **10 pasos** de la Guía Paso a Paso BD.

---

### PASO 1: Leer y entender el problema

**Enunciado del docente:**
> Una tienda en línea necesita gestionar su inventario: productos, categorías, proveedores y pedidos. Un producto pertenece a una categoría y tiene un proveedor. Un pedido puede contener varios productos, y un producto puede aparecer en varios pedidos. Se necesita saber la cantidad y el precio de cada producto al momento del pedido. También se quiere saber qué productos tienen stock bajo el mínimo.

**¿Qué necesitamos guardar?**
- Categorías de productos
- Proveedores
- Productos (con su categoría y proveedor)
- Pedidos
- El detalle de qué productos lleva cada pedido (con cantidad y precio)

---

### PASO 2: Identificar Entidades

Busco los **sustantivos** del enunciado:

| # | Entidad | Justificación |
|---|---------|---------------|
| 1 | **CATEGORIA** | Agrupación de productos (Electrónica, Ropa, Hogar...) |
| 2 | **PROVEEDOR** | Empresa que suministra los productos |
| 3 | **PRODUCTO** | Artículo que se vende en la tienda |
| 4 | **PEDIDO** | Orden de compra que hace un cliente |
| 5 | **DETALLE_PEDIDO** | Tabla intermedia que resuelve la relación N:M entre Pedido y Producto |

---

### PASO 3: Identificar Atributos

| Entidad | Atributos |
|---------|-----------|
| **CATEGORIA** | id_categoria (PK), nombre_categoria, descripcion |
| **PROVEEDOR** | id_proveedor (PK), nombre, contacto, telefono, email, direccion |
| **PRODUCTO** | id_producto (PK), nombre, descripcion, precio_unitario, stock_actual, stock_minimo, id_categoria (FK), id_proveedor (FK), fecha_ingreso |
| **PEDIDO** | id_pedido (PK), fecha_pedido, estado, total |
| **DETALLE_PEDIDO** | id_pedido (FK), id_producto (FK), cantidad, precio_en_momento |

> `precio_en_momento` guarda el precio al instante de comprar porque puede cambiar después.
> `stock_minimo` sirve para generar alertas de reabastecimiento.

---

### PASO 4 y 5: Identificar Relaciones y Cardinalidad

| Relación | Tipo | Razonamiento |
|----------|------|--------------|
| CATEGORIA ↔ PRODUCTO | **1:N** | Una categoría tiene muchos productos. Un producto pertenece a una sola categoría |
| PROVEEDOR ↔ PRODUCTO | **1:N** | Un proveedor suministra muchos productos. Un producto tiene un proveedor principal |
| PEDIDO ↔ PRODUCTO | **N:M** | Un pedido tiene muchos productos. Un producto aparece en muchos pedidos |

---

### PASO 6: Resolver la relación N:M

PEDIDO ↔ PRODUCTO es N:M → creo tabla intermedia **DETALLE_PEDIDO** con atributos propios:

```
PEDIDO (1) ←——→ (N) DETALLE_PEDIDO (N) ←——→ (1) PRODUCTO
                      - cantidad
                      - precio_en_momento
```

---

### PASO 7: MER Resultante

```
┌──────────────┐        ┌─────────────────────────────────┐
│  CATEGORIA   │        │           PRODUCTO               │
│──────────────│        │─────────────────────────────────│
│id_categoria PK│◄─(1)──│ id_producto    PK               │
│nombre_cat.   │        │ nombre                          │
│descripcion   │        │ descripcion                     │
└──────────────┘        │ precio_unitario                 │
                        │ stock_actual                    │
┌──────────────┐        │ stock_minimo                    │
│  PROVEEDOR   │        │ id_categoria   FK               │
│──────────────│        │ id_proveedor   FK               │
│id_proveedor PK│◄─(1)──│ fecha_ingreso                   │
│nombre        │        └──────────────┬──────────────────┘
│contacto      │                       │(1)
│telefono      │                      (N)
│email         │            ┌──────────┴──────────┐
│direccion     │            │   DETALLE_PEDIDO     │
└──────────────┘            │─────────────────────│
                            │ id_pedido   FK (PK)  │
                            │ id_producto FK (PK)  │
                            │ cantidad             │
                            │ precio_en_momento    │
                            └──────────┬──────────┘
                                       │(N)
                                       │
                                      (1)
                               ┌───────┴───────┐
                               │    PEDIDO      │
                               │───────────────│
                               │ id_pedido   PK │
                               │ fecha_pedido   │
                               │ estado         │
                               │ total          │
                               └───────────────┘
```

---

### PASO 8: Crear la Base de Datos

```sql
-- En Oracle no se usa CREATE DATABASE como en PostgreSQL.
-- Se trabaja creando un usuario/schema dedicado:
CREATE USER inventario IDENTIFIED BY pass123;
GRANT CREATE SESSION, CREATE TABLE, CREATE INDEX, CREATE SEQUENCE TO inventario;
CONNECT inventario/pass123;
```

---

### PASO 9: Crear las Tablas con PK y FK

```sql
-- ================================================
-- ORDEN: primero tablas SIN FK (tablas "padre")
-- ================================================

-- 1. CATEGORIA (sin FK)
CREATE TABLE categoria (
    id_categoria     NUMBER PRIMARY KEY,
    nombre_categoria VARCHAR2(100) NOT NULL,
    descripcion      VARCHAR2(300)
);

-- 2. PROVEEDOR (sin FK)
CREATE TABLE proveedor (
    id_proveedor NUMBER PRIMARY KEY,
    nombre       VARCHAR2(150) NOT NULL,
    contacto     VARCHAR2(100),
    telefono     VARCHAR2(20),
    email        VARCHAR2(150),
    direccion    VARCHAR2(300)
);

-- 3. PRODUCTO (FK a categoria y proveedor)
CREATE TABLE producto (
    id_producto     NUMBER PRIMARY KEY,
    nombre          VARCHAR2(200) NOT NULL,
    descripcion     VARCHAR2(500),
    precio_unitario NUMBER(10,2) NOT NULL CHECK (precio_unitario > 0),
    stock_actual    NUMBER(10)   DEFAULT 0 CHECK (stock_actual >= 0),
    stock_minimo    NUMBER(10)   DEFAULT 5,
    id_categoria    NUMBER REFERENCES categoria(id_categoria),
    id_proveedor    NUMBER REFERENCES proveedor(id_proveedor),
    fecha_ingreso   DATE DEFAULT SYSDATE
);

-- Índice en FK para JOINs eficientes
CREATE INDEX idx_prod_categoria ON producto(id_categoria);
CREATE INDEX idx_prod_proveedor ON producto(id_proveedor);

-- 4. PEDIDO (sin FK a producto)
CREATE TABLE pedido (
    id_pedido    NUMBER PRIMARY KEY,
    fecha_pedido DATE DEFAULT SYSDATE,
    estado       VARCHAR2(30) DEFAULT 'pendiente'
                     CHECK (estado IN ('pendiente','en_proceso','enviado','completado','cancelado')),
    total        NUMBER(12,2) DEFAULT 0
);

-- 5. DETALLE_PEDIDO — tabla intermedia N:M (FK a pedido y producto)
CREATE TABLE detalle_pedido (
    id_pedido         NUMBER NOT NULL REFERENCES pedido(id_pedido),
    id_producto       NUMBER NOT NULL REFERENCES producto(id_producto),
    cantidad          NUMBER(10)   NOT NULL CHECK (cantidad > 0),
    precio_en_momento NUMBER(10,2) NOT NULL,
    PRIMARY KEY (id_pedido, id_producto)
);
```

---

### PASO 10: Insertar Datos de Prueba

```sql
-- Categorías
INSERT INTO categoria VALUES (1, 'Electrónica', 'Dispositivos y accesorios electrónicos');
INSERT INTO categoria VALUES (2, 'Ropa',        'Prendas de vestir');
INSERT INTO categoria VALUES (3, 'Hogar',       'Artículos para el hogar');

-- Proveedores
INSERT INTO proveedor VALUES (1, 'TechPeru SAC',  'Juan Ríos',   '987001001', 'tech@peru.com',  'Lima');
INSERT INTO proveedor VALUES (2, 'ModaLima EIRL', 'Ana Torres',  '987002002', 'moda@lima.com',  'Lima');
INSERT INTO proveedor VALUES (3, 'HogarPlus SAC', 'Luis Castro', '987003003', 'hogar@plus.com', 'Lima');

-- Productos (algunos con stock bajo el mínimo para probar el reporte)
INSERT INTO producto VALUES (1, 'Laptop HP 15"',     'Core i5 8GB 256SSD', 2800, 2,  5,  1, 1, SYSDATE);
INSERT INTO producto VALUES (2, 'Mouse Logitech',    'Inalámbrico negro',   85,  25, 10, 1, 1, SYSDATE);
INSERT INTO producto VALUES (3, 'Teclado Mecánico',  'RGB switches rojos', 220,  3,  8,  1, 1, SYSDATE);
INSERT INTO producto VALUES (4, 'Polo Adidas',       'Talla M azul',        120, 50, 15, 2, 2, SYSDATE);
INSERT INTO producto VALUES (5, 'Zapatillas Nike',   'Air Max talla 42',    280, 10, 10, 2, 2, SYSDATE);
INSERT INTO producto VALUES (6, 'Silla Ergonómica',  'Soporte lumbar',      450,  4,  5,  3, 3, SYSDATE);

-- Pedidos
INSERT INTO pedido VALUES (1, SYSDATE,       'completado',  0);
INSERT INTO pedido VALUES (2, SYSDATE - 3,   'enviado',     0);
INSERT INTO pedido VALUES (3, SYSDATE - 1,   'pendiente',   0);

-- Detalle de pedidos
INSERT INTO detalle_pedido VALUES (1, 1, 1, 2800);  -- Pedido 1: 1 Laptop
INSERT INTO detalle_pedido VALUES (1, 2, 2, 85);    -- Pedido 1: 2 Mouse
INSERT INTO detalle_pedido VALUES (2, 4, 3, 120);   -- Pedido 2: 3 Polos
INSERT INTO detalle_pedido VALUES (2, 5, 1, 280);   -- Pedido 2: 1 Zapatilla
INSERT INTO detalle_pedido VALUES (3, 3, 1, 220);   -- Pedido 3: 1 Teclado
INSERT INTO detalle_pedido VALUES (3, 6, 2, 450);   -- Pedido 3: 2 Sillas

-- Actualizar totales de cada pedido
UPDATE pedido SET total = (
    SELECT SUM(cantidad * precio_en_momento)
    FROM detalle_pedido
    WHERE detalle_pedido.id_pedido = pedido.id_pedido
);

COMMIT;

-- ================================================
-- CONSULTAS para verificar que todo funciona
-- ================================================

-- Ver todos los productos con su categoría y proveedor
SELECT
    p.nombre          AS producto,
    c.nombre_categoria AS categoria,
    pr.nombre          AS proveedor,
    p.precio_unitario,
    p.stock_actual
FROM producto p
JOIN categoria c  ON p.id_categoria  = c.id_categoria
JOIN proveedor pr ON p.id_proveedor  = pr.id_proveedor
ORDER BY c.nombre_categoria, p.nombre;

-- Ver detalle completo de todos los pedidos
SELECT
    ped.id_pedido,
    ped.fecha_pedido,
    ped.estado,
    pro.nombre   AS producto,
    dp.cantidad,
    dp.precio_en_momento,
    dp.cantidad * dp.precio_en_momento AS subtotal
FROM detalle_pedido dp
JOIN pedido  ped ON dp.id_pedido   = ped.id_pedido
JOIN producto pro ON dp.id_producto = pro.id_producto
ORDER BY ped.id_pedido;

-- REPORTE 1: Productos con stock por debajo del mínimo (alerta de reabastecimiento)
SELECT
    p.nombre,
    p.stock_actual   AS stock_disponible,
    p.stock_minimo   AS stock_requerido,
    p.stock_minimo - p.stock_actual AS unidades_a_pedir,
    pr.nombre        AS proveedor,
    pr.telefono
FROM producto p
JOIN proveedor pr ON p.id_proveedor = pr.id_proveedor
WHERE p.stock_actual < p.stock_minimo
ORDER BY unidades_a_pedir DESC;

-- REPORTE 2: Valor total del inventario por categoría
SELECT
    c.nombre_categoria,
    COUNT(p.id_producto)                        AS total_productos,
    SUM(p.precio_unitario * p.stock_actual)     AS valor_inventario
FROM categoria c
JOIN producto p ON c.id_categoria = p.id_categoria
GROUP BY c.id_categoria, c.nombre_categoria
ORDER BY valor_inventario DESC;

-- REPORTE 3: Resumen de pedidos
SELECT
    id_pedido,
    TO_CHAR(fecha_pedido, 'DD/MM/YYYY') AS fecha,
    estado,
    total
FROM pedido
ORDER BY fecha_pedido DESC;
```

---

## Preguntas de Repaso

1. ¿Cuál es la diferencia entre una instancia y una base de datos en Oracle?
2. ¿Qué es el SGA y qué componentes contiene?
3. ¿Cuál es la función de los procesos de fondo (DBWR, LGWR, SMON)?
4. ¿Qué son los tablespaces y cuántos tipos hay?
5. ¿Qué es RAC y por qué es importante para alta disponibilidad?
6. ¿Qué es Data Guard y cómo funciona?
7. ¿Cuáles son los 4 factores de hardware que afectan rendimiento?
8. ¿Qué es Flashback Technology?
9. ¿Cuál es la diferencia entre RMAN backup completo e incremental?
10. ¿Por qué la RAM es tan crítica en Oracle?
11. ¿Qué tipos de datos numéricos soporta Oracle?
12. ¿Qué es un schema en Oracle?
13. ¿Cuál es la diferencia entre SQL y PL/SQL?
14. ¿Qué es un procedimiento almacenado y para qué sirve?
15. ¿Qué es un trigger y cuándo se ejecuta?
16. ¿Qué hace COMMIT y qué hace ROLLBACK?
17. ¿Qué son las propiedades ACID?
18. ¿Qué es MVCC y qué ventaja da en lecturas?
19. ¿Qué es un índice y cuándo conviene crearlo?
20. ¿Qué es el Performance Tuning y cuáles son los 4 factores de hardware?

---

## Ejemplo: Crear Base de Datos y Tabla en Oracle

```sql
-- Crear tablespace de usuario
CREATE TABLESPACE ts_ventas
DATAFILE '/oracle/data/ventas_01.dbf' SIZE 100M
AUTOEXTEND ON NEXT 10M MAXSIZE 500M;

-- Crear usuario
CREATE USER ventas IDENTIFIED BY password123
DEFAULT TABLESPACE ts_ventas
QUOTA UNLIMITED ON ts_ventas;

-- Otorgar permisos
GRANT CREATE SESSION, CREATE TABLE, CREATE INDEX TO ventas;

-- Conectar como usuario ventas
CONNECT ventas/password123;

-- Crear tabla
CREATE TABLE productos (
    id_producto    NUMBER PRIMARY KEY,
    nombre         VARCHAR2(100) NOT NULL,
    descripcion    VARCHAR2(500),
    precio_unitario NUMBER(10,2) NOT NULL,
    stock          NUMBER(10) DEFAULT 0 CHECK (stock >= 0),
    fecha_creacion DATE DEFAULT SYSDATE
);

-- Crear índice en columna de búsqueda frecuente
CREATE INDEX idx_productos_nombre ON productos(nombre);

-- Insertar datos
INSERT INTO productos (id_producto, nombre, precio_unitario, stock)
VALUES (1, 'Laptop HP', 2800.00, 15);

-- Confirmar cambios
COMMIT;

-- Consultar datos
SELECT * FROM productos;

-- Ver estructura de la tabla
DESC productos;
```

---

## Recursos Útiles

- **Documentación oficial:** https://docs.oracle.com
- **Oracle Learning:** Oracle University
- **Comunidad:** Stack Overflow, Oracle Forums
- **Videos:** YouTube - Oracle Database tutorials

---

## Caso Práctico: Archivo de Identificación Familiar (Base de Datos Preventiva)

> Propuesta desarrollada en colaboración entre la **Fiscalía de Justicia de Zacatecas** y colectivos de madres buscadoras. Aplica conceptos de diseño de BD a un contexto real de alto impacto social.

### Objetivo del Sistema

Crear un registro doméstico de datos exactos sobre integrantes de la familia para **facilitar su localización inmediata** ante un caso de desaparición forzada.

### Diseño de Entidades y Datos a Registrar

Este caso ilustra cómo se identifican las entidades, atributos y tipos de datos en una BD real:

| Grupo de Datos | Atributos | Tipo de Dato Equivalente |
|----------------|-----------|--------------------------|
| **Datos Generales** | Nombre completo, fecha de nacimiento, domicilio actual | `VARCHAR2`, `DATE` |
| **Registro Visual** | Fotografías actuales, señas particulares (lunares, cicatrices, marcas) | `BLOB` / `VARCHAR2` |
| **Entorno Social** | Contactos cercanos, números telefónicos, lugares de reunión frecuentes | `VARCHAR2`, relaciones entre tablas |
| **Huellas Dactilares** | Imagen digitalizada de huellas | `BLOB` / `RAW` |
| **Perfil Genético** | Tipo de muestra biológica almacenada (cabello, hisopo bucal) | `VARCHAR2`, `CLOB` |

### Recomendaciones de Diseño (Aplicadas a BD)

1. **Integridad de datos:** No contaminar muestras biológicas — análogo a no corromper datos en inserción.
2. **Control de acceso:** Información almacenada en lugar privado, accesible solo para la familia — equivalente a permisos de usuario en Oracle (`GRANT`/`REVOKE`).
3. **Disponibilidad inmediata:** La info debe estar lista al momento de la denuncia — análogo a alta disponibilidad y backup en Oracle (RMAN, Data Guard).
4. **Actualización continua:** Mantener fotografías y contactos actualizados — equivalente a mantener datos consistentes y sin redundancia.

### Lección de Diseño de BD

```
Entidad principal: PERSONA_FAMILIA
├── id_persona        NUMBER PK
├── nombre_completo   VARCHAR2(200) NOT NULL
├── fecha_nacimiento  DATE NOT NULL
├── domicilio         VARCHAR2(300)
├── foto_actual       BLOB
├── senas_particulares VARCHAR2(500)
└── fecha_actualizacion DATE DEFAULT SYSDATE

Entidad relacionada: CONTACTO_SOCIAL
├── id_contacto       NUMBER PK
├── id_persona        NUMBER FK → PERSONA_FAMILIA
├── nombre_contacto   VARCHAR2(200)
├── telefono          VARCHAR2(20)
└── lugar_reunion     VARCHAR2(300)

Entidad relacionada: MUESTRA_BIOLOGICA
├── id_muestra        NUMBER PK
├── id_persona        NUMBER FK → PERSONA_FAMILIA
├── tipo_muestra      VARCHAR2(50)  -- 'CABELLO', 'HISOPO_BUCAL'
└── ubicacion_fisica  VARCHAR2(200)
```

> Este caso demuestra que el diseño de una BD correcta — con entidades bien definidas, tipos de datos apropiados, control de acceso y disponibilidad — puede tener **impacto directo en vidas humanas**.
