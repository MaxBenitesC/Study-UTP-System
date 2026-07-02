# Avance del Proyecto - Base de Datos II

## Contexto actual

El proyecto corresponde a un comedor popular. El objetivo es transformar un control manual de insumos, menús y beneficiarios en una base de datos relacional en 3FN.

El avance inicial del curso tenía un modelo simple de inventario con 7 tablas. Luego, por observaciones del profesor, el modelo evolucionó para cubrir:

- Stock real por preparación de menús.
- Beneficiarios registrados, no registrados y visitantes.
- Familias o padrón social.
- Trabajadores y cargos.
- Precio base del menú y precio cobrado por consumo.

## Modelo final acordado

El modelo final tiene 15 tablas:

```text
comedores
cargos
trabajadores
proveedores
categorias
productos
ingresos
detalle_ingreso
tipos_beneficiario
familias
beneficiarios
tipos_menu
menus
detalle_menu
consumos
```

## Decisiones finales

### Stock

No se guarda `stock_actual` como campo fijo en `productos`.

El stock se calcula así:

```text
stock_actual = SUM(detalle_ingreso.cantidad) - SUM(detalle_menu.cantidad_usada)
```

### Consumos

`consumos` registra personas atendidas.

No existe `detalle_consumo` en el modelo final porque no se descuenta arroz, lenteja o aceite por cada persona. Los insumos se descuentan cuando se registra el menú preparado en `detalle_menu`.

### Menús

`menus` registra:

```text
fecha
nombre
cantidad_menus
precio_base
```

No se usan estados como planificado, preparado o cancelado porque el alcance debe seguir siendo realista para un comedor popular.

### Beneficiarios

`tipos_beneficiario` permite clasificar:

```text
Registrado
No registrado
Visitante
```

Esto permite sustentar la diferencia entre el apoyo estatal asignado y las personas realmente atendidas.

`beneficiarios.id_familia` puede ser opcional, porque una persona puede estar registrada como beneficiaria aunque todavía no esté asociada a una familia regularizada.

## Archivos actualizados

| Archivo | Estado |
|---|---|
| `PUNTO_2_DISENO_BASE_DATOS.md` | Actualizado al modelo final |
| `PROYECTO_NORMALIZACION_COMEDOR.md` | Copia del documento final |
| `diagrams/comedor_popular_mer.drawio` | Actualizado al modelo final |
| `diagrams/MODELO_COMEDOR_FINAL.drawio` | Copia del diagrama final |
| `diagrams/comedor_popular_mer.mmd` | Actualizado al modelo final |
| `estructura_wor_proyecto_comedor_popular.md` | Actualizado |

## Pendiente técnico

Los archivos `sql/01_schema.sql`, `sql/02_data.sql` y `sql/03_queries.sql` todavía pertenecen al modelo inicial de 7 tablas.

Si se requiere implementación completa, generar:

```text
sql/01_schema_final.sql
sql/02_data_final.sql
sql/03_queries_final.sql
```
