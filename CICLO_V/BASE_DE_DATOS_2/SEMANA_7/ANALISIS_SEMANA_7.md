# Análisis Forense de Documentos - Semana 7 (Base de Datos II)

Este documento consolida el contenido extraído y analizado de la presentación correspondiente a la Semana 7 del curso, referida al **Diseño de Base de Datos Distribuidas**. Todo el contenido se basa estrictamente en el documento `S1_s1_ Diseño Bases de Datos Distribuidas.pptx`.

## 1. Introducción
Las **Bases de Datos Distribuidas (BDD)** son la unión de los Sistemas Gestores de Bases de Datos (SGBD) y los sistemas en red. El objetivo principal de las BDD no es la centralización, sino lograr la **integración y distribución** equilibrada de los datos aprovechando ambos enfoques.

---

## 2. Definiciones Clave
*   **Base de Datos (BD):** Conjunto de datos relacionados entre sí, estructurados para su posterior recuperación o análisis.
*   **SGBD (Sistema de Gestión de BD):** Software que proporciona la organización necesaria para definir, construir, manipular, almacenar y recuperar la base de datos de manera flexible.
*   **Sistema de Computación Distribuido:** Elementos de procesamiento (nodos) interconectados por una red que cooperan para lograr un objetivo común.
*   **Red de Computadoras:** Interconexión de equipos a través de medios de telecomunicaciones para compartir información en paquetes de datos.
*   **BDD (Base de Datos Distribuida):** Colección de datos **lógicamente integrados**, pero que **físicamente** pueden estar almacenados y procesados en **varios nodos** (servidores) distribuidos en una red.
*   **SGBDD (Sistema de Gestión de BDD):** Software que maneja la BDD, haciendo que la distribución de los datos sea **transparente** para los usuarios.
*   **SBDD (Sistema de Bases de Datos Distribuidas):** La unión entre un SGBDD y una BDD (los datos integrados + el software que los gestiona).

---

## 3. Características de las BDD
*   Los datos deben estar físicamente en más de un ordenador (en distintas sedes o nodos) e interconectados mediante una red.
*   Los datos han de estar lógicamente integrados en una única estructura (esquema lógico global común).
*   Los usuarios pueden acceder a datos locales o remotos.
*   Cada nodo facilita un entorno para ejecutar transacciones locales y globales.
*   **Transparencia de distribución:** Un usuario puede consultar y actualizar datos de múltiples sedes sin saber físicamente dónde residen.

---

## 4. Ventajas y Desventajas de las BDD

### Ventajas
1.  **Alineación natural:** Útil en organizaciones con ubicaciones distribuidas (sucursales, distintas ciudades).
2.  **Mayor fiabilidad y disponibilidad:** Si un sitio o nodo falla, los demás siguen operando. Solo dejan de estar accesibles los datos de ese sitio específico, en contraste con los sistemas centralizados.
3.  **Compartición controlada:** Se comparten datos manteniendo cierto grado de control local en cada sitio.
4.  **Mejor rendimiento:** Las consultas locales son rápidas porque las BD locales son más pequeñas; además, las transacciones complejas pueden ejecutarse en paralelo.
5.  **Plataforma autónoma:** Permite tener diferentes máquinas y plataformas en cada sitio.
6.  **Localización transparente:** Los desarrolladores/usuarios no se preocupan de dónde están los datos o las cadenas de conexión complejas.
7.  **Autonomía del sitio:** Mantenimiento de procedimientos administrativos y privacidad.
8.  **Seguridad mejorada:** No se requiere compartir cuentas globales o contraseñas; se reducen las vulnerabilidades a nivel central.

### Desventajas
1.  **Seguridad (control de accesos):** Puede ser difícil rastrear y controlar exhaustivamente quién ingresa mediante enlaces de base de datos distribuidos.
2.  **Coherencia de datos:** Garantizar que las réplicas en distintos nodos mantengan los datos consistentes requiere gran esfuerzo.
3.  **Administración de transacciones:** Coordinar actualizaciones (especialmente con distintas zonas horarias o sedes) puede generar congestiones.
4.  **Seguimiento y Administración:** Incrementa drásticamente la carga de trabajo del DBA (monitoreo de réplicas, colas de transacciones, recuperación manual).
5.  **Recuperación compleja:** Ante un fallo, se deben restaurar no solo datos locales, sino también garantizar la restauración de transacciones globales suspendidas.
6.  **Dependencia de la red (Rendimiento):** El desempeño general está supeditado a la velocidad y latencia de la red.

---

## 5. Arquitectura y Componentes del SGBDD
El SGBDD debe contar con funciones básicas centralizadas además de capacidades de acceso remoto, rastreo de distribución, elaboración de estrategias de ejecución distribuida y consistencia de réplicas. Para lograrlo usa:

*   **Procesador de datos locales:** Se encarga de la gestión local (transacciones, concurrencia, fallos locales).
*   **Diccionario (Directorio global):** Almacena metadatos sobre dónde y cómo se almacenan los datos (mapea el esquema externo al interno).
*   **Procesador de aplicaciones distribuidas:** Diferenciador principal. Accede al directorio global, procesa las peticiones multisitio, genera planes de ejecución distribuidos y reparte el trabajo.
*   **Software y red de comunicaciones:** Proveen primitivas y servicios para transmitir los datos.

---

## 6. Clasificación de los SGBDD
Los sistemas distribuidos se clasifican según tres dimensiones fundamentales:

1.  **Distribución:** Cómo se reparten los datos (particiones horizontales, verticales, réplicas parciales o totales).
2.  **Heterogeneidad:** Grado de diferencias en el hardware, sistemas operativos, protocolos de red y tipo de SGBD (lenguajes, modelos).
3.  **Autonomía:** El nivel de independencia con el que opera cada SGBD local. Se divide en:
    *   *Autonomía de Diseño:* Libertad para elegir modelo de datos, SGBD, diccionarios.
    *   *Autonomía de Comunicación:* Capacidad del nodo para decidir si comunicarse o no con la federación.
    *   *Autonomía de Ejecución:* Habilidad de correr transacciones locales sin interferencia externa.
    *   *Autonomía de Asociación:* Capacidad de compartir funcionalidad o retirarse de la federación.

---

## 7. Diseño de Bases de Datos Distribuidas
El Administrador de la Base de Datos (DBA) establece la distribución de funciones, sincronización de operaciones y descomposición de consultas.
Existen dos enfoques principales de diseño:
*   **Diseño Descendente (Top-Down):** Parte de un Esquema Lógico Global y construye varios Esquemas Lógicos Locales mediante técnicas de *fragmentación* y *asignación/replicación*.
*   **Diseño Ascendente (Bottom-Up):** Parte de BBDD locales ya existentes que se integran en un Esquema Lógico Global único.

### Conceptos Clave del Diseño
*   **Fragmentación:** Técnica para dividir la BD en unidades lógicas (fragmentos) y asignarlas a diversos nodos.
*   **Álgebra Relacional:**
    *   `JOIN`: Combina registros de dos o más tablas usando un campo en común.
    *   `SEMI-JOIN`: Igual que el JOIN, pero el resultado solo contiene los atributos de la primera tabla.
    *   `UNION`: Combina los resultados de dos consultas SQL.

---

## 8. Caso Práctico (Universidad UTP)
Se presentó un caso analizado en clase:
*   **Escenario:** La UTP tiene dos campus (Los Olivos y Centro de Lima).
*   **Problema:** Tienen una BD centralizada en *SQL Server* (Los Olivos) pero la sede Centro de Lima adquirió licencias de *Oracle*.
*   **Objetivo:** Diseñar una BDD autónoma para cada campus que maneje la gestión de las titulaciones, cursos, grupos y profesores, manteniendo la gestión de nóminas y contrataciones centralizada en Los Olivos.
*   *(Nota de clase: Se requiere evaluar el grado de autonomía, heterogeneidad y fragmentación a aplicar sobre este escenario de dos motores SGBD distintos).*