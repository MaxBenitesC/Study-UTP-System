# Repaso Semana 1 - Introducción a PostgreSQL
**Curso:** Base de Datos II | **Unidad:** 1 - Bases de Datos Relacionales | **Sesión:** 1

---

## Logro de la Sesión
Al finalizar la unidad el participante comprende los fundamentos y uso de otras bases de datos relacionales.

---

## 1. ¿Qué es PostgreSQL?

PostgreSQL es un **sistema de gestión de bases de datos (SGBD) de código abierto de clase empresarial**. Admite **SQL** y **JSON** para consultas relacionales y no relacionales. También se le conoce como **Postgres**.

> Admite tipos de datos avanzados y funciones de optimización del rendimiento comparables a bases de datos comerciales como Oracle y SQL Server.

---

## 2. Origen de PostgreSQL

| Dato | Detalle |
|------|---------|
| Nombre original | **Postgres** (reemplazo de INGRES) |
| Institución | Universidad de **Berkeley**, California |
| Creador principal | Profesor **Michael Stonebraker** |
| Año del sufijo SQL | **1996** (para evidenciar soporte SQL) |
| Garantía | Principios **ACID** en sus transacciones |

---

## 3. Características Principales

- **Código abierto** con enorme comunidad detrás
- Compatibilidad con **ACID** (Atomicidad, Consistencia, Aislamiento, Durabilidad)
- Creación de entornos **tolerantes a fallos**
- Soporta multitud de **tipos de datos**
- **Multiplataforma**
- **Control de concurrencias multiversión (MVCC)**
- Enfoque empresarial hacia el soporte de **consultas complejas**
- Interoperabilidad con múltiples frameworks: Django, Node, PHP, Hibernate...
- Compatible con **SQL y JSON**

---

## 4. Ventajas de PostgreSQL

| Ventaja | Descripción |
|---------|-------------|
| Pila LAMP | Puede ejecutar sitios web dinámicos y aplicaciones web |
| Tolerante a fallos | Gracias al **registro de escritura anticipada (WAL)** |
| Código abierto | Software libre bajo licencia open source |
| Soporte geoespacial | Compatible con servicios GIS y ubicación |
| Extensibilidad | Permite tipos de datos, funciones y operadores personalizados |
| Alta disponibilidad | Soporta replicación y clustering |
| Seguridad avanzada | Autenticación, cifrado, control de acceso detallado |

---

## 5. Desventajas de PostgreSQL

- No es propiedad de una sola organización → tiene problemas de visibilidad/marketing
- Las mejoras de velocidad requieren **más trabajo** que en MySQL
- Muchas aplicaciones open source son compatibles con MySQL pero **puede no ser compatible** con PostgreSQL
- En métricas de rendimiento, es **más lento que MySQL** en algunos escenarios
- La instalación puede ser más difícil para principiantes

---

## 6. ¿Por qué elegir PostgreSQL en proyectos profesionales?

1. **Código abierto** → gratuito con comunidad activa
2. **Cumplimiento de estándares SQL** → alta interoperabilidad
3. **Extensibilidad** → tipos de datos, funciones y operadores personalizados
4. **Seguridad** → autenticación, cifrado, control de acceso granular
5. **Rendimiento y escalabilidad** → para grandes volúmenes de datos
6. **Alta disponibilidad** → replicación y clustering
7. **Documentación y soporte comunitario**

---

## 7. Instalación

La instalación se realiza desde la página oficial: [postgresql.org/download](https://www.postgresql.org/download/)

Al ser código abierto, el software es de **descarga libre y gratuita**.

---

## 8. Resumen Rápido

| Aspecto | PostgreSQL |
|---------|-----------|
| Tipo | SGBD relacional y objeto-relacional |
| Licencia | Open Source (gratuito) |
| Lenguaje | SQL + JSON |
| Garantía | ACID |
| Plataforma | Multiplataforma |
| Uso industrial | Finanzas, GIS gubernamental, manufactura, tecnología web, NoSQL, ciencia |

---

## Preguntas de Repaso

1. ¿Qué significa que PostgreSQL sea ACID?
2. ¿De qué sistema fue reemplazo PostgreSQL y en qué universidad nació?
3. ¿Cuál es la diferencia entre ventajas y desventajas frente a MySQL?
4. ¿Por qué el WAL hace a PostgreSQL tolerante a fallos?
5. ¿Qué es MVCC y para qué sirve?
