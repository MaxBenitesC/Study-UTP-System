# Semana 05: CSS Grid Layout

**Curso:** Hojas de Estilo en Cascada Avanzado  
**Docente:** Mg. Ing. CIP Hernán Francisco Peña Carnero  
**Especialista Responsable:** Max Anderson Benites Corazón  

---

## 1. Materiales de la Semana en el Directorio Local

Los archivos se encuentran organizados dentro de `SEMANA05/MATERIALES/`:

1. **[S05_s1_Material_VFF.pdf](MATERIALES/S05_s1_Material_VFF.pdf)** *(2.4 MB, 49 páginas)*
   - Versión PDF generada del material oficial proyectado en el visor `/pdf` de UTP+Class.
2. **[S05_s1_Material_VFF.pptx](MATERIALES/S05_s1_Material_VFF.pptx)** *(6.1 MB, 49 diapositivas)*
   - Presentación original descargada desde el almacenamiento oficial S3 de la plataforma.
3. **[S05_s1_Material.pdf](MATERIALES/S05_s1_Material.pdf)** *(1.1 MB)*
   - Documento inicial cargado por UTP (plantilla base).

---

## 2. Contenido Temático Desarrollado (Diapositivas 1 – 49)

### Logro de la Sesión
> Al finalizar la sesión, el estudiante aplica las propiedades de **CSS Grid** en el diseño de páginas web responsivas.

### Estructura de Temas

1. **Introducción al Modelo Grid Bidimensional:**
   * Comparativa histórica: tablas vs. floats vs. inline-block vs. flexbox vs. CSS Grid.
   * Concepto de contenedor (`grid container`) y elementos hijos (`grid items`).

2. **Propiedades del Contenedor Padre:**
   * `display: grid` / `display: inline-grid`
   * `grid-template-columns`: definición de columnas fijas, flexibles (`fr`) y funciones (`repeat()`, `minmax()`).
   * `grid-template-rows`: definición de pistas horizontales.
   * `grid-template-areas`: mapeo visual con cadenas de texto para layouts semánticos.
   * `gap`, `row-gap`, `column-gap`: canaletas y separación entre celdas.

3. **Propiedades de Posicionamiento del Hijo:**
   * `grid-column: <start> / <end>`
   * `grid-row: <start> / <end>`
   * `grid-area: <name>` (sin comillas al asignar el hijo al área).

4. **Alineación y Justificación en Grid:**
   * **Malla completa:** `justify-content` (horizontal) y `align-content` (vertical).
   * **Celdas / Items (desde el padre):** `justify-items` y `align-items`.
   * **Elemento individual (en el hijo):** `justify-self` y `align-self`.

---

## 3. Actividad Identificada en Plataforma

* **Nombre:** `S05.s1 1Punto PC2`
* **Tipo:** Tarea calificada (1 punto para la Práctica Calificada 2).
* **Enlace UTP+Class:** [Abrir detalle de tarea en UTP+Class](https://class.utp.edu.pe/student/courses/e37c660f-c54e-559f-a036-f7f32313355e/section/4214affb-26e4-5eb0-a205-24479d42f917/learnv2/week/5/unit/4214affb-26e4-5eb0-a205-24479d42f917/theme/c7565439-4554-4c2e-b31e-e2b50359bc3e/content/69e0d4c1-7734-4c20-8d05-f75535e625dc/homework/6fc34caa-a114-4f96-8816-2b6763401298)
* *Nota:* Para sincronizar automáticamente las instrucciones exactas y rúbrica de esta tarea, basta con abrir el enlace una vez en su navegador Chrome.
