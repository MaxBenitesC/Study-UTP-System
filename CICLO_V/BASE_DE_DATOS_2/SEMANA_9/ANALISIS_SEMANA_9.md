# Análisis Forense de Documentos - Semana 9 (Base de Datos II)

Este documento consolida el contenido extraído y analizado de la presentación y la guía práctica de laboratorio correspondientes a la Semana 9 del curso. Los temas centrales son las **Transacciones y el Control de Concurrencia** en Bases de Datos Distribuidas, utilizando PostgreSQL como entorno de simulación.

---

## 1. Conceptos Teóricos Fundamentales

### 1.1 Transacciones Distribuidas
Una **Transacción** es un conjunto de operaciones que se ejecutan como una única unidad lógica e indivisible (ej. verificar inventario, descontar saldo, registrar venta, actualizar pedido). 
Para que una transacción distribuida sea confiable, debe cumplir con las propiedades **ACID**:
*   **Atomicidad (Atomicity):** O se ejecutan todas las operaciones de la transacción en todos los nodos, o no se ejecuta ninguna (todo o nada).
*   **Coherencia (Consistency):** La base de datos debe pasar de un estado válido a otro estado válido, respetando reglas e integridades (ej. el stock no puede ser negativo).
*   **Aislamiento (Isolation):** Las transacciones concurrentes no deben interferir entre sí.
*   **Durabilidad (Durability):** Una vez confirmada (`COMMIT`), los cambios son permanentes, incluso ante fallos del sistema.

### 1.2 Control de Concurrencia
Son los mecanismos empleados por el SGBD para manejar el acceso simultáneo a los mismos datos por múltiples usuarios o transacciones. 
El objetivo es evitar anomalías como:
*   **Actualización perdida:** Dos transacciones leen un dato y lo sobrescriben casi al mismo tiempo, perdiéndose una modificación.
*   **Lecturas sucias:** Leer datos no confirmados por otra transacción que finalmente hace *rollback*.
*   **Lecturas no repetibles y lecturas fantasma.**

---

## 2. Implementación Práctica en PostgreSQL
En el laboratorio se simuló un entorno distribuido estructurando los datos en diferentes **esquemas lógicos** que representan nodos: `nodo_inventario`, `nodo_pedidos`, `nodo_pagos` y `nodo_logistica`.

### 2.1 Sintaxis de Transacciones (`BEGIN`, `COMMIT`, `ROLLBACK`)
Para agrupar operaciones, se envuelven en un bloque transaccional:
*   `BEGIN;` inicia la transacción.
*   `COMMIT;` confirma y hace permanentes los cambios.
*   `ROLLBACK;` deshace cualquier cambio realizado dentro del bloque si ocurre un error o no se cumplen condiciones de negocio (ej. validación de stock insuficiente).

### 2.2 Bloqueos (Locking) para Control de Concurrencia
Se utiliza el bloqueo pesimista mediante la cláusula `FOR UPDATE`.
```sql
SELECT * FROM nodo_inventario.productos 
WHERE id_producto = 4 FOR UPDATE;
```
*   **Efecto:** Si la "Sesión A" ejecuta esto, bloquea la fila del producto 4. Si la "Sesión B" intenta modificar el mismo producto (ej. `UPDATE`), la Sesión B **quedará en espera** hasta que la Sesión A finalice con un `COMMIT` o `ROLLBACK`.
*   Esto previene el clásico escenario de "sobreventa" (vender el mismo producto físico a dos clientes distintos).

### 2.3 Niveles de Aislamiento (Isolation Levels)
El laboratorio compara dos niveles clave:
1.  **READ COMMITTED (Por defecto en Postgres):** Una transacción solo ve los cambios que ya han sido confirmados (`COMMIT`) por otras transacciones. Cada comando `SELECT` dentro de la transacción obtiene un nuevo "snapshot" (foto instantánea) de la base de datos.
2.  **REPEATABLE READ:** Toda la transacción opera sobre la misma instantánea tomada al inicio del bloque. Si otra transacción modifica datos y hace *commit*, la transacción original seguirá viendo los datos antiguos hasta que termine.
*   *Sintaxis:* `SET TRANSACTION ISOLATION LEVEL READ COMMITTED;`

### 2.4 Validación Adicional en Sentencias UPDATE
Al lidiar con concurrencia, es buena práctica hacer validaciones directly en el `UPDATE`:
```sql
UPDATE nodo_inventario.productos
SET stock = stock - 1
WHERE id_producto = 4 AND stock >= 1;
```
*   El filtro `AND stock >= 1` garantiza que bajo ninguna circunstancia (incluso en altas concurrencias optimistas) el stock quede en números negativos.

---

## 3. Tablas Administrativas: Catálogos y Auditoría

### 3.1 Localización de Nodos
Al igual que en la fragmentación (Semana 8), el diseño distribuido exige conocer qué nodo físico procesa qué tabla. Se crea un catálogo: `localizacion_nodos (esquema, tabla, nodo_logico, ubicacion_geografica, funcion_principal)`.
Esto ayuda al SGBD global a decidir a qué servidores dirigir las consultas.

### 3.2 Auditoría de Transacciones
En bases distribuidas críticas, se crea una tabla centralizada de historial de operaciones (ej. `auditoria_transacciones`).
Permite registrar:
*   La operación (`COMPRA`, `DEVOLUCION`).
*   Las tablas afectadas.
*   Fecha y hora (`CURRENT_TIMESTAMP`).
Se planteó en clase el uso de *Triggers* (Disparadores) vs el registro manual para automatizar este proceso.

---

## 4. Retos Abordados en el Análisis
*   Las transacciones distribuidas son más complejas que las locales porque una falla de conexión entre los nodos o la caída de un servidor remoto (`nodo_inventario`, por ejemplo) obliga al Coordinador de Transacciones a abortar toda la operación (Protocolo de Compromiso de Dos Fases / 2PC - Concepto subyacente).
*   La latencia de red incrementa el tiempo durante el cual los bloqueos se mantienen activos, lo que puede formar *cuellos de botella* si no se diseña con un nivel de aislamiento y granularidad adecuados.