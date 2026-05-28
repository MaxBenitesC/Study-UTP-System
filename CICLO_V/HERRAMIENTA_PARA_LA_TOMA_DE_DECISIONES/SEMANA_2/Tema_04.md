# Tema 04: Business Intelligence - Fundamentos y Componentes

## Definición

El **Business Intelligence (BI)** es un conjunto de tecnologías y metodologías que permiten a las organizaciones transformar datos dispersos en información valiosa para la toma de decisiones estratégicas, facilitando la ejecución de cambios y su comunicación eficaz.

---

## Características clave

| Característica | Descripción |
|---|---|
| **Proceso metodológico** | Ciclo que va desde la obtención de datos en origen hasta su visualización final. |
| **Integración (ETL)** | Herramientas de Extracción, Transformación y Carga para consolidar datos de múltiples fuentes. |
| **Escalabilidad** | Uso de Data Marts como estructuras intermedias para agilizar consultas sin sobrecargar el almacén principal. |

---

## Elementos del sistema BI

| Elemento | Descripción |
|---|---|
| **Sistemas Transaccionales** | La fuente original de los datos (ERP, CRM, gestión de cadena de suministro, etc.). |
| **Data Warehouse** | Almacén centralizado de información estructurada proveniente de múltiples fuentes. |
| **Data Marts** | Subconjuntos del Data Warehouse para consultas rápidas y recurrentes sobre áreas específicas. |
| **Sistemas OLAP** | Procesamiento analítico dinámico y multidimensional de los datos. |

---

## Flujo del sistema

```
Sistemas Transaccionales → ETL → Data Warehouse → Data Marts → OLAP → Visualización
```

---

## Usos y aplicaciones prácticas

| Aplicación | Descripción |
|---|---|
| **Query & Reporting** | Consulta e informes para gerencia sobre los hechos más relevantes. |
| **Análisis multidimensional** | Exploración de datos en tiempo real por dimensiones (productos, clientes, procesos). |
| **Cuadros de Mando (KPIs)** | Visualización de los indicadores clave de rendimiento de la empresa. |
| **Minería de datos** | Descubrimiento de patrones ocultos en grandes volúmenes de información. |

---

## Ejemplos de fuentes de datos

- **ERP** — Gestión global de recursos empresariales
- **CRM** — Gestión de la relación con los clientes
- **Gestión de cadena de suministro** — Control del aprovisionamiento

---

## Resumen

El BI transforma datos dispersos de sistemas transaccionales (ERP, CRM) en información estratégica mediante el proceso ETL. Los datos se consolidan en un Data Warehouse, se segmentan en Data Marts para consultas ágiles y se analizan mediante OLAP. El resultado final son dashboards, informes y KPIs que permiten a la organización tomar decisiones fundamentadas en datos reales.
