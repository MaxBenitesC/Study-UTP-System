# Análisis Forense de Documentos - Semana 6 (Base de Datos II)

Este documento consolida el contenido extraído y analizado de los archivos PDF, PowerPoint y Word correspondientes a la Semana 6 del curso. Todo el contenido está basado estrictamente en los documentos proporcionados.

## 1. Oracle vs SQL Server: Diferencias Clave y de Sintaxis
A partir de los scripts y presentaciones analizadas, se destacan las siguientes diferencias y equivalencias entre ambos Sistemas de Gestión de Bases de Datos (SGBD):

### Tipos de Datos y Conceptos Generales
| Concepto | Oracle | SQL Server |
|----------|--------|------------|
| Tipo numérico | `NUMBER` | `INT` / `DECIMAL` |
| Tipo de texto | `VARCHAR2` | `VARCHAR` |
| Fecha actual | `SYSDATE` (usando tabla `dual`) | `GETDATE()` |
| Valores Aleatorios | `DBMS_RANDOM` (ej. `DBMS_RANDOM.VALUE`) | `RAND()` / `NEWID()` |
| Bloques de código | `BEGIN...END; /` | `BEGIN...END` |
| Secuencias / Autoincrementables | `SEQUENCE` | `IDENTITY` |
| Lenguaje utilizado | `PL/SQL` | `T-SQL` (Transact-SQL) |

### Diferencias de Sintaxis en Consultas
*   **Limitar registros:**
    *   *SQL Server:* `SELECT TOP 5 * FROM productos;`
    *   *Oracle:* `SELECT * FROM productos WHERE ROWNUM <= 5;`
*   **Concatenación de cadenas:**
    *   *SQL Server:* `SELECT nombre + ' - ' + ciudad FROM clientes;`
    *   *Oracle:* `SELECT nombre || ' - ' || ciudad FROM clientes;`
*   **Manejo de valores NULL:**
    *   *SQL Server:* `SELECT ISNULL(nombre, 'Sin nombre') FROM clientes;`
    *   *Oracle:* `SELECT NVL(nombre, 'Sin nombre') FROM clientes;`

---

## 2. Instrucción SELECT y Cláusulas
El comando `SELECT` es fundamental para realizar consultas y recuperar datos de una o más tablas.
*   **Sintaxis básica:** `SELECT [columna1, columna2, ...] FROM [tabla];`
*   **Cláusulas adicionales permitidas:**
    *   `WHERE`: Filtra registros según condiciones (ej. `WHERE activo = true`).
    *   `GROUP BY`: Agrupa registros.
    *   `HAVING`: Filtra grupos.
    *   `ORDER BY`: Ordena el resultado.
    *   `LIMIT`: Restringe la cantidad de resultados (dependiendo del motor).
    *   `DISTINCT`: Muestra valores únicos.

---

## 3. Funciones Nativas en Oracle
Oracle incluye diversas funciones propias. Para hacer pruebas o consultas que no dependan de una tabla de usuario, se utiliza la tabla especial **DUAL** (tabla de una sola columna `DUMMY` con valor 'X').

### Funciones de Cadena
*   `LENGTH(cadena)`: Retorna la longitud de la cadena enviada.
*   `UPPER(cadena)`: Retorna la cadena con todos los caracteres en mayúsculas.
*   `LOWER(cadena)`: Retorna la cadena en minúsculas.
*   `REPLACE(cadena, subcadena1, subcadena2)`: Reemplaza las ocurrencias de *subcadena1* por *subcadena2*.
*   `SUBSTR(cadena, inicio, longitud)`: Devuelve una parte de la cadena, comenzando en la posición de *inicio* por la cantidad de caracteres de *longitud*.

### Funciones Matemáticas
*   `ABS(x)`: Retorna el valor absoluto de un número.
*   `POWER(x, y)`: Retorna el valor de *x* elevado a la potencia *y*.
*   `ROUND(numero, [decimales])`: Redondea al valor más próximo (ej. `ROUND(precio, 2)`).
*   `SQRT(x)`: Devuelve la raíz cuadrada.
*   `MOD(x, y)`: Devuelve el resto de la división de *x* entre *y*.

### Funciones de Fecha y Horas
*   `CURRENT_DATE`: Retorna la fecha actual.
*   `SYSDATE`: Retorna la fecha actual del sistema.
*   `ADD_MONTHS(fecha, n)`: Suma (o resta si es negativo) *n* meses a una fecha.
*   `EXTRACT(parte FROM fecha)`: Extrae de la fecha una parte específica como el año (`YEAR`), mes (`MONTH`), día (`DAY`), hora (`HOUR`), etc.

---

## 4. Procedimientos Almacenados
Son bloques de código SQL almacenados en el servidor de base de datos. Encapsulan operaciones repetitivas y complejas (manipulación de datos, control de flujo), lo que facilita su reutilización y mantenimiento. También mejoran la escalabilidad al manejar aumentos en la carga de trabajo.

*   **Oracle:** Usa su propio lenguaje (PL/SQL). Es bastante flexible para escribir lógica compleja.
*   **SQL Server:** Usa T-SQL, ofrece integración con .NET y maneja transacciones y errores de manera avanzada.

**Ejemplo de creación y ejecución en Oracle:**
```sql
-- Creación
CREATE OR REPLACE PROCEDURE total_ventas_cliente (
    p_cliente_id IN NUMBER,
    p_total OUT NUMBER
)
AS
BEGIN
    SELECT SUM(cantidad * p.precio)
    INTO p_total
    FROM ventas v
    JOIN productos p ON v.producto_id = p.id 
    WHERE v.cliente_id = p_cliente_id;
END;
/

-- Ejecución
DECLARE
    v_total NUMBER;
BEGIN
    total_ventas_cliente(1, v_total);
    DBMS_OUTPUT.PUT_LINE('Total: ' || v_total);
END;
/
```

---

## 5. Administración de Usuarios y Seguridad
Ambos sistemas gestionan permisos, pero tienen diferentes características integradas.
*   **Oracle:** Permite seguridad y administración granular de usuarios y permisos, incluyendo roles y políticas de acceso basadas en objetos.
*   **SQL Server:** Además de roles, integra administración detallada con Active Directory y auditoría avanzada.

**Sintaxis práctica en Oracle:**
*   Crear usuario: `CREATE USER usuario_lab IDENTIFIED BY 12345;`
*   Dar permisos (perfil genérico): `GRANT CONNECT, RESOURCE TO usuario_lab;`
*   Dar permisos sobre tablas específicas: `GRANT SELECT, INSERT ON clientes TO usuario_lab;`
*   Revocar permisos: `REVOKE INSERT ON clientes FROM usuario_lab;`

---

## 6. Backup y Recuperación (Respaldos)
*   **Oracle:**
    *   **Backup lógico:** Exporta datos a nivel lógico usando Data Pump (`EXPORT` / `EXPDP` e `IMPORT` / `IMPDP`).
        *   *Export:* `expdp system/password DIRECTORY=backup_dir DUMPFILE=ventas.dmp SCHEMAS=usuario_lab`
        *   *Import:* `impdp system/password DIRECTORY=backup_dir DUMPFILE=ventas.dmp`
    *   **Backup físico:** Realizado mediante la utilidad `RMAN`. Permite recuperación a nivel de base de datos o tabla.
*   **SQL Server:** Proporciona un conjunto integrado de respaldos completos (full backups), diferenciales y de registros de transacciones (transaction logs).

---

## 7. Apuntes del Laboratorio Práctico
El laboratorio comparó la inserción masiva y eliminación de tablas:
*   En **Oracle**, para evitar errores al eliminar tablas que no existen, se usa `EXECUTE IMMEDIATE` envuelto en bloques `BEGIN...END` con manejo de excepciones (`EXCEPTION WHEN OTHERS THEN NULL;`).
*   Para generar bucles e insertar 50 registros, **Oracle** utiliza `FOR i IN 1..50 LOOP`, apoyándose en `MOD()` y `DBMS_RANDOM.VALUE` para crear aleatoriedad.
*   En **SQL Server**, la misma tarea exige limpiar la tabla evaluando con `IF OBJECT_ID() IS NOT NULL`, e inserta datos con un bucle `WHILE @i <= 50`, usando iteradores manuales (`SET @i = @i + 1`) y funciones como `RAND()`, `CHECKSUM()`, y `NEWID()`.

---

## 8. Tarea Asignada (Próxima Clase)
Se indicó entregar un trabajo de forma escrita abordando lo siguiente:
*   **Tema:** Oracle vs MySQL.
*   **Requisito:** Realizar una comparación utilizando **cuadros** para mayor claridad sobre los siguientes puntos:
    1. Sintaxis de consultas y funciones.
    2. Procedimientos Almacenados.
    3. Administración de usuarios, seguridad y backup.