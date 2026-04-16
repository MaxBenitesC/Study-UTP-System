# Repaso Semana 4 - Introducción a Oracle Database
**Curso:** Base de Datos II | **Unidad:** 1 - Bases de Datos Relacionales | **Sesión:** 7

---

## Logro de la Sesión
Al finalizar la unidad el participante comprende los fundamentos y uso de otras bases de datos relacionales y aplica a situaciones reales.

---

## 1. ¿Qué es Oracle?

**Oracle Corporation** es una empresa multinacional de tecnología informática especializada en software y hardware, principalmente conocida por su sistema de gestión de bases de datos.

| Dato | Detalle |
|------|---------|
| Fundada | **1977** |
| Fundadores | **Larry Ellison**, Bob Miner y Ed Oates |
| Sede | Estados Unidos |
| Posición | Uno de los líderes mundiales en SGBD y soluciones empresariales |

### Principales Productos y Servicios de Oracle

| Producto | Descripción |
|---------|-------------|
| **Oracle Database** | SGBD más popular y robusto del mercado. Maneja grandes volúmenes de datos de forma eficiente y segura |
| **Oracle Cloud** | Servicios en la nube: IaaS, PaaS y SaaS. Escalable y flexible |
| **Oracle Applications** | Suite de aplicaciones empresariales (ERP, CRM, HCM) |

---

## 2. Oracle Database 23C - Características Clave

Oracle Database 23C es la **última versión** de la base de datos insignia de Oracle, diseñada para las demandas del entorno empresarial moderno.

### 2.1 Gestión de Datos Automatizada (Autonomía)
- **Aprovisionamiento automatizado** sin intervención humana
- **Auto-ajuste y auto-reparación** → reduce carga de los DBAs
- **Adaptación dinámica** a las condiciones cambiantes de carga de trabajo
- Minimiza el riesgo de errores humanos

### 2.2 Seguridad Mejorada
- **Cifrado mejorado** de datos en reposo y en tránsito
- **Autenticación avanzada** y controles de acceso más robustos
- Detección y respuesta a amenazas en tiempo real
- Auditorías mejoradas y seguimiento en tiempo real
- Cumplimiento normativo garantizado

### 2.3 Rendimiento y Escalabilidad
- **Procesamiento paralelo** mejorado
- **Compresión avanzada** de datos
- Gestión eficiente de la memoria
- Maneja cargas de trabajo intensivas y volúmenes de datos masivos
- Tiempos de respuesta rápidos incluso en alta demanda

### 2.4 Facilidad de Uso y Desarrollo
- Nuevas herramientas e interfaces de usuario mejoradas
- Soporte ampliado para lenguajes de programación modernos y frameworks
- Integración simplificada con otros servicios Oracle Cloud

### 2.5 Inteligencia Artificial y Machine Learning
- Capacidades de **IA y ML integradas**
- Automatización de análisis de datos avanzados
- Insights en tiempo real y predicciones precisas
- Modelos de ML entrenables y desplegables **directamente en la base de datos**
- No es necesario mover datos a plataformas externas

---

## 3. Ventajas de Oracle Database

| Ventaja | Descripción |
|---------|-------------|
| **Rendimiento y escalabilidad** | Diseñado para grandes volúmenes y transacciones de alto rendimiento. Soporta escalabilidad vertical y horizontal |
| **Seguridad avanzada** | Cifrado en reposo y en tránsito, auditoría detallada, control de acceso por roles, políticas granulares por columna y fila |
| **Alta disponibilidad** | Arquitectura **RAC** (Real Application Clusters): distribuye carga en múltiples servidores → el sistema sigue funcionando si un servidor falla |
| **Características avanzadas** | Soporte para JSON, XML, análisis en tiempo real, procedimientos almacenados, triggers |
| **Soporte técnico** | Soporte oficial de alta calidad + gran comunidad de usuarios y desarrolladores |
| **Fiabilidad comprobada** | Usado por bancos, gobiernos y grandes corporaciones mundialmente |

---

## 4. Desventajas de Oracle Database

| Desventaja | Descripción |
|-----------|-------------|
| **Costo elevado** | Altos costos de **licenciamiento y soporte**. Obstáculo para PyMEs o proyectos con presupuesto limitado |
| **Complejidad de administración** | Requiere alto nivel de conocimientos técnicos. Puede necesitar personal especializado (DBA Oracle) |
| **Requerimientos de hardware** | Necesita hardware robusto para funcionar de forma óptima → aumenta costos de infraestructura |

---

## 5. ¿Es Oracle una buena opción para proyectos profesionales?

**Sí**, Oracle es excelente para proyectos profesionales **de gran escala** por estas razones:

1. **Rendimiento y escalabilidad** → maneja grandes volúmenes de datos y crece con la empresa
2. **Fiabilidad y disponibilidad** → RAC garantiza continuidad del servicio
3. **Seguridad robusta** → cifrado, control de acceso, auditoría, cumplimiento normativo
4. **Características avanzadas** → JSON, XML, análisis en tiempo real, ML integrado
5. **Soporte de calidad** → documentación exhaustiva y soporte técnico oficial

> Consideración: Para proyectos pequeños o con presupuesto limitado, PostgreSQL (open source) puede ser una alternativa más viable.

---

## 6. Comparación: PostgreSQL vs SQL Server vs Oracle

| Característica | PostgreSQL | SQL Server | Oracle |
|---------------|-----------|-----------|--------|
| Licencia | Open Source (gratis) | Comercial (Microsoft) | Comercial (costosa) |
| Escalabilidad | Alta | Alta | Muy alta |
| Rendimiento | Bueno | Excelente (OLTP) | Excelente |
| Seguridad | Avanzada | Avanzada | Muy avanzada |
| Alta Disponibilidad | Replicación, Citus | Always On AG | RAC (Real Application Clusters) |
| Costo | Bajo | Medio-Alto | Muy Alto |
| Facilidad de administración | Media | Media-Alta | Compleja |
| Uso típico | Startups, proyectos medios | Empresas Microsoft | Grandes corporaciones, bancos |

---

## 7. Instalación de Oracle Database

1. Visitar el sitio oficial: **www.oracle.com**
2. Navegar a **Products → Databases → Oracle Database**
3. Seleccionar la versión deseada (19c, 21c, 23c)
4. Ir a la sección **Downloads**
5. **Iniciar sesión** con cuenta Oracle (o crear una cuenta gratuita)
6. Seleccionar el instalador para tu sistema operativo (Linux o Windows)
7. Seguir las guías de instalación proporcionadas

---

## 8. Caso Práctico: Sistema de Gestión de Inventarios para Tienda Online

El sistema debe gestionar:
- **Productos** (nombre, descripción, precio, stock)
- **Categorías** (clasificación de productos)
- **Proveedores** (datos del proveedor)
- **Pedidos** (órdenes de compra/venta)

### Ejemplo de estructura de tablas:

```sql
-- En Oracle (sintaxis similar a SQL estándar)

-- Tabla Categorias
CREATE TABLE categorias (
    id_categoria  NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre        VARCHAR2(100) NOT NULL,
    descripcion   VARCHAR2(300)
);

-- Tabla Proveedores
CREATE TABLE proveedores (
    id_proveedor  NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre        VARCHAR2(150) NOT NULL,
    contacto      VARCHAR2(100),
    telefono      VARCHAR2(20)
);

-- Tabla Productos
CREATE TABLE productos (
    id_producto   NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nombre        VARCHAR2(150) NOT NULL,
    precio        NUMBER(10,2) NOT NULL,
    stock         NUMBER(10) DEFAULT 0,
    id_categoria  NUMBER REFERENCES categorias(id_categoria),
    id_proveedor  NUMBER REFERENCES proveedores(id_proveedor)
);

-- Tabla Pedidos
CREATE TABLE pedidos (
    id_pedido     NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    fecha_pedido  DATE DEFAULT SYSDATE,
    total         NUMBER(12,2),
    estado        VARCHAR2(50) DEFAULT 'pendiente'
);
```

> **Diferencias de sintaxis con PostgreSQL:**
> - Oracle usa `NUMBER` en lugar de `INTEGER` o `DECIMAL`
> - Oracle usa `VARCHAR2` en lugar de `VARCHAR`
> - `GENERATED ALWAYS AS IDENTITY` equivale a `SERIAL` en PostgreSQL
> - `SYSDATE` equivale a `NOW()` o `CURRENT_DATE` en PostgreSQL

---

## 9. Resumen General de Oracle

| Aspecto | Detalle |
|---------|---------|
| Empresa | Oracle Corporation (fundada 1977) |
| Fundador | Larry Ellison, Bob Miner, Ed Oates |
| Posicionamiento | Líder en bases de datos empresariales de misión crítica |
| Versión actual | Oracle Database 23C |
| Diferenciadores | RAC, alta disponibilidad, IA integrada, seguridad avanzada |
| Clientes típicos | Bancos, gobiernos, grandes corporaciones |

---

## Preguntas de Repaso

1. ¿Quién fundó Oracle y en qué año?
2. ¿Qué es Oracle RAC y qué problema resuelve?
3. ¿Qué significa que Oracle Database 23C sea "autónoma"?
4. ¿Cuál es la principal desventaja de Oracle frente a PostgreSQL?
5. ¿Qué ventaja tiene Oracle al integrar IA y ML directamente en la base de datos?
6. ¿En qué tipo de empresa o proyecto recomendarías Oracle vs PostgreSQL? Justifica.
7. ¿Qué diferencias de sintaxis existen entre Oracle y PostgreSQL en la creación de tablas?
