# Análisis Forense de Documentos - Semana 8 (Base de Datos II)

Este documento consolida el contenido extraído y analizado de la presentación y la guía práctica de laboratorio correspondientes a la Semana 8 del curso. Los temas centrales son la **Fragmentación Vertical y Horizontal**, y el **Procesamiento de Consultas en Bases de Datos Distribuidas** (Descomposición, Localización, Optimización) utilizando PostgreSQL.

---

## 1. Conceptos Fundamentales
La fragmentación es crucial en el diseño de bases de datos distribuidas porque permite dividir los datos en subconjuntos más pequeños. Esto reduce la carga de trabajo, minimiza el tráfico en la red y optimiza el almacenamiento y acceso.

### 1.1 Tipos de Fragmentación
*   **Fragmentación Horizontal:** Divide una tabla en subconjuntos de *filas* (registros). Cada fragmento conserva la misma estructura de columnas, pero contiene registros diferentes basándose en un criterio (ej. clientes por zona geográfica). 
*   **Fragmentación Vertical:** Divide una tabla en subconjuntos de *columnas* (atributos). Cada fragmento contiene columnas específicas, siendo obligatorio conservar la Clave Primaria (Primary Key) en todos los fragmentos para poder reconstruir la tabla original. Útil cuando ciertas columnas (ej. datos públicos vs confidenciales) tienen distintos niveles o frecuencias de acceso.

### 1.2 Fases del Procesamiento Distribuido
*   **Descomposición:** Proceso de dividir la base de datos en fragmentos para lograr alta eficiencia de acceso.
*   **Localización:** Ubicación física o lógica de los fragmentos en los diferentes nodos de la red. Almacenar los datos cerca de donde se consumen reduce drásticamente la latencia.
*   **Optimización:** Estrategias para mejorar el rendimiento de las consultas, como elegir los fragmentos más relevantes, crear índices y usar planes de ejecución para minimizar transferencias entre nodos.

---

## 2. Práctica de Laboratorio en PostgreSQL
La práctica simula un escenario empresarial ("Comercial Andina S.A.C.") administrando `clientes`, `empleados` y `ventas` distribuidos en regiones (Norte, Sur, Centro, Oriente). 

Aunque PostgreSQL se ejecute localmente, la práctica emula el comportamiento de los nodos distribuidos creando distintas tablas.

### 2.1 Fragmentación Horizontal (Ejemplo Clientes y Ventas)
Se crearon fragmentos separando por región para los clientes y por estado para las ventas.

*   **Creación de fragmentos horizontales:**
    ```sql
    CREATE TABLE clientes_norte AS
    SELECT * FROM clientes WHERE region = 'Norte';
    ```
*   **Reconstrucción Lógica:** Se utiliza `UNION ALL` en lugar de `UNION` para unir los registros fragmentados, ya que es más rápido al no evaluar duplicados (los fragmentos por definición son disjuntos).
    ```sql
    SELECT * FROM clientes_norte
    UNION ALL
    SELECT * FROM clientes_sur
    -- ...
    ```

### 2.2 Fragmentación Vertical (Ejemplo Empleados)
Los empleados se fragmentaron por seguridad y acceso: datos básicos por un lado, datos confidenciales (salario, datos de contacto) por otro.

*   **Creación de fragmentos verticales:**
    ```sql
    CREATE TABLE empleados_datos_basicos AS
    SELECT id_empleado, nombres, apellidos, cargo, region FROM empleados;

    CREATE TABLE empleados_datos_confidenciales AS
    SELECT id_empleado, salario, fecha_contratacion, correo, telefono FROM empleados;
    ```
*   **Reconstrucción Lógica:** Se realiza utilizando `INNER JOIN` usando la clave primaria obligatoria en ambos fragmentos (`id_empleado`).
    ```sql
    SELECT b.nombres, c.salario 
    FROM empleados_datos_basicos b
    INNER JOIN empleados_datos_confidenciales c ON b.id_empleado = c.id_empleado;
    ```

### 2.3 Simulación de Localización de Fragmentos
Se implementó una tabla de catálogo para mapear dónde reside cada fragmento (su nodo asignado). Esto ayuda al optimizador global a no transferir o consultar información innecesaria.
*   **Tabla de catálogo:** `localizacion_fragmentos (nombre_fragmento, tabla_origen, tipo_fragmentacion, criterio, nodo_asignado, ubicacion)`.

### 2.4 Optimización de Consultas
Para analizar y mejorar el rendimiento de consultas a la base fragmentada:
1.  **Índices:** Se crearon índices sobre las columnas usadas frecuentemente como filtros (ej. `region`, `estado`).
2.  **Análisis Estadístico:** Comando `ANALYZE tabla;` para que el motor actualice las estimaciones.
3.  **Planes de Ejecución:**
    *   `EXPLAIN`: Muestra el costo estimado y plan lógico.
    *   `EXPLAIN ANALYZE`: Ejecuta la consulta y muestra el tiempo real además de los costos. Permite comprobar visualmente la diferencia entre hacer un escaneo secuencial (Seq Scan) en la tabla gigante original vs ir directamente al fragmento que contiene solo los datos útiles.

### 2.5 Consultas Distribuidas Simuladas
Una consulta distribuida real cruza datos entre los diferentes nodos. La simulación combinó la tabla fragmentada (solo la región norte de ventas y clientes) con el `JOIN` respectivo a datos de empleados. La conclusión fue que la consulta es más eficiente cuando "se evitan fragmentos innecesarios" (es decir, el optimizador sabe que no necesita buscar registros del sur si la consulta solo pide ventas en el norte).

---

## 3. Conclusiones y Retos
*   La correcta partición (descomposición) y localización mejoran los tiempos de respuesta.
*   En la fragmentación vertical es obligatorio copiar la PK en todos los fragmentos.
*   La complejidad del DBA aumenta al tener que gestionar operaciones `INSERT`, `UPDATE` o `DELETE`, ya que un cambio de atributo (ej. un cliente se muda de región) podría implicar que su registro salte físicamente de un nodo (tabla `clientes_sur`) a otro nodo (`clientes_norte`).