# Tema 03: Alternar entre Referencias Relativas, Absolutas y Mixtas

## Definición

El tema central es la **gestión de referencias de celda en Excel**: direcciones que indican al programa dónde buscar los datos para una fórmula. Las referencias se comportan de forma diferente al ser copiadas, y el usuario puede controlar ese comportamiento mediante el signo `$`.

---

## Tipos de referencias

| Tipo | Ejemplo | Descripción |
|---|---|---|
| **Relativa** | `A1` | Excel la usa por defecto; se ajusta automáticamente al copiar la fórmula |
| **Absoluta** | `$A$1` | Permanece fija sin importar a dónde se mueva la fórmula |
| **Mixta** | `$A1` / `A$1` | Solo se fija la columna o solo la fila |

---

## Características de cada tipo

### Relativa (`A1`)
- Se basa en la **distancia relativa** entre la celda de la fórmula y la celda referenciada.
- Si la fórmula se mueve o copia, la referencia se ajusta automáticamente.

### Absoluta (`$A$1`)
- Utiliza `$` antes de la letra de columna **y** antes del número de fila para anclar la referencia.
- No cambia al copiar o mover la fórmula.

### Mixta (`$A1` o `A$1`)
| Notación | Columna | Fila |
|---|---|---|
| `$A1` | Absoluta (fija) | Relativa (cambia) |
| `A$1` | Relativa (cambia) | Absoluta (fija) |

---

## Ventajas y beneficios

| Beneficio | Tipo de referencia | Descripción |
|---|---|---|
| **Automatización** | Relativa | Permite aplicar la misma lógica a grandes rangos rápidamente |
| **Precisión** | Absoluta | Asegura que valores críticos (tasas, constantes) siempre apunten a la celda correcta |
| **Flexibilidad** | Mixta | Permite tablas de doble entrada donde solo un eje permanece constante |
| **Rapidez** | Todas | La tecla **F4** alterna entre todos los tipos sin escribir `$` manualmente |

---

## Ejemplos de uso

### Referencias relativas — cálculo en lista
Fórmula en `D4`: `=B4*C4`
Al copiarla a `D5` → se convierte automáticamente en `=B5*C5`

### Referencias absolutas — IVA fijo
Para multiplicar varios valores por un porcentaje de IVA ubicado en `B4`:

```
=A5*$B$4   →   al arrastrar, siempre multiplica por la celda B4
```

### Referencias mixtas — desplazamiento
Fórmula copiada **2 celdas abajo y 2 a la derecha**:

| Referencia original | Resultado | Explicación |
|---|---|---|
| `A$1` | `C$1` | La columna avanzó de A a C; la fila 1 se mantuvo fija |
| `$A1` | `$A3` | La columna A se mantuvo fija; la fila avanzó de 1 a 3 |

---

## Tecla F4 — Atajo para alternar referencias

Al seleccionar una referencia dentro de una fórmula y presionar **F4**, Excel alterna en este orden:

```
A1  →  $A$1  →  A$1  →  $A1  →  A1  →  ...
```

---

## Resumen

| Situación | Tipo recomendado |
|---|---|
| Procesar listas de datos repetitivos | Relativa |
| Usar una constante o tasa fija (IVA, tipo de cambio) | Absoluta |
| Construir tablas de doble entrada | Mixta |

Dominar los tipos de referencia es esencial para el manejo eficiente de hojas de cálculo. Las referencias absolutas y mixtas garantizan la integridad de los datos en modelos financieros y tablas complejas.

---

## Referencias

Universidad Tecnológica del Perú. (2026). *Referencias relativas y absolutas* [Infografía]. Curso Herramientas informáticas para la toma de decisiones, Semana 3.
