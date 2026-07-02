# Proyecto de Base de Datos: Comedor Popular

## Enfoque del trabajo

El proyecto se enfoca en convertir el control manual de un comedor popular en una base de datos relacional.

El modelo final no solo controla inventario. También permite registrar trabajadores, proveedores, ingresos de insumos, menús preparados, insumos usados por menú, beneficiarios atendidos y diferencia entre beneficiarios registrados y no registrados.

## Punto 2: Diseño de la Base de Datos

El desarrollo completo está en:

```text
PUNTO_2_DISENO_BASE_DATOS.md
```

Ese archivo contiene:

- Problemática.
- Transformación desde tabla sin normalizar.
- 1FN, 2FN y 3FN.
- Tablas finales.
- Relaciones.
- Defensa técnica del modelo.
- Sustento de stock y tipo de beneficiario.
- Guion para exposición.

## Archivos principales

| Archivo | Contenido |
|---|---|
| `PUNTO_2_DISENO_BASE_DATOS.md` | Documento principal del proyecto actualizado al modelo final |
| `PROYECTO_NORMALIZACION_COMEDOR.md` | Copia del mismo documento con nombre descriptivo |
| `diagrams/comedor_popular_mer.drawio` | Diagrama final editable para Draw.io |
| `diagrams/MODELO_COMEDOR_FINAL.drawio` | Copia del diagrama final con nombre descriptivo |
| `diagrams/comedor_popular_mer.mmd` | Diagrama textual Mermaid actualizado |
| `sql/01_schema.sql` | Script anterior del modelo inicial de 7 tablas |
| `sql/02_data.sql` | Datos de prueba del modelo inicial |
| `sql/03_queries.sql` | Consultas del modelo inicial |

## Nota sobre los scripts SQL

Los scripts dentro de `sql/` todavía corresponden al modelo inicial de 7 tablas. El documento y el diagrama ya fueron actualizados al modelo final de 15 tablas.

Si el profesor pide implementación SQL final, debe generarse un nuevo script, por ejemplo:

```text
sql/01_schema_final.sql
```

## Tablas finales del modelo 3FN

El modelo final contiene 15 tablas:

1. `comedores`
2. `cargos`
3. `trabajadores`
4. `proveedores`
5. `categorias`
6. `productos`
7. `ingresos`
8. `detalle_ingreso`
9. `tipos_beneficiario`
10. `familias`
11. `beneficiarios`
12. `tipos_menu`
13. `menus`
14. `detalle_menu`
15. `consumos`

## Relaciones principales

```text
comedores 1 ─── N trabajadores
cargos 1 ─── N trabajadores

comedores 1 ─── N ingresos
proveedores 1 ─── N ingresos
trabajadores 1 ─── N ingresos
ingresos 1 ─── N detalle_ingreso
productos 1 ─── N detalle_ingreso
categorias 1 ─── N productos

tipos_beneficiario 1 ─── N beneficiarios
familias 1 ─── N beneficiarios
beneficiarios 1 ─── N consumos

comedores 1 ─── N menus
tipos_menu 1 ─── N menus
trabajadores 1 ─── N menus
menus 1 ─── N detalle_menu
productos 1 ─── N detalle_menu

menus 1 ─── N consumos
trabajadores 1 ─── N consumos
```

## Decisión importante del modelo

El modelo inicial usaba:

```text
consumo
detalle_consumo
```

El modelo final reemplaza `detalle_consumo` por `detalle_menu`.

Motivo:

> El stock no se descuenta por cada persona que consume, sino por los insumos usados al preparar el menú del día.

Por eso:

```text
stock_actual = ingresos de insumos - insumos usados en menús
```

Y `consumos` queda para registrar qué beneficiario recibió qué menú, cuánto se le cobró y qué trabajador registró la atención.

## Respaldos

Antes de reemplazar los archivos antiguos se generaron respaldos con sufijo:

```text
.bak_20260625
```
