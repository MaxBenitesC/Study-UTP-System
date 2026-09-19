# Tarea 05: Arquitectura Web Responsiva con CSS Grid

**Curso:** Hojas de Estilo en Cascada Avanzado  
**Docente:** Mg. Ing. CIP Hernán Francisco Peña Carnero  
**Especialista Responsable:** Max Anderson Benites Corazón  
**Senior Technical Implementation Specialist - NCR VOYIX**  
**Fecha:** 13 de Septiembre de 2026  

---

## 1. Datos de la Actividad en UTP+Class

* **Nombre de la actividad:** `Tarea 05` (`S05.s1 1Punto PC2`)
* **Consigna oficial del docente:**
  > *"Estimados estudiante por favor subir su proyecto en este lugar El proyecto debe estar comprimido"*
* **Material base de referencia:** [S05_s1_Material_VFF.pdf](MATERIALES/S05_s1_Material_VFF.pdf) (Diapositivas 28 a 41).
* **Entregable generado:** [Semana05_Tarea05_CSS_Grid_Max_Benites.zip](Semana05_Tarea05_CSS_Grid_Max_Benites.zip)

---

## 2. Arquitectura de la Solución Implementada

Se implementó el **Proyecto 01** siguiendo estrictamente los esquemas de maquetación basados en `grid-template-areas` y diseño *Mobile-First* con puntos de ruptura (*breakpoints*):

### A. Estructura Semántica HTML (`index.html`)
Se utilizó una jerarquía semántica limpia:
* `header.header`: Encabezado principal (`HEADER`).
* `nav.navbar`: Barra de navegación (`NAVBAR`).
* `article.main`: Contenido central (`MAIN`).
* `aside.sidebar`: Barra lateral informativa (`SIDEBAR`).
* `footer.footer`: Pie de página (`FOOTER`).

### B. Distribución y Breakpoints en CSS (`estilos.css` / `style.css`)

1. **Mobile Base (< 768px):**
   * Disposición vertical en una sola columna con alturas definidas y separaciones consistentes (`gap: 20px`).
   * Grilla:
     ```css
     grid-template:
       "header" 200px
       "navbar" 50px
       "main" 100px
       "sidebar" auto
       "footer" auto;
     ```

2. **Tablet (`min-width: 768px`):**
   * Dos columnas (`200px auto`).
   * El `header` y la `navbar` comparten la parte superior dividida.
   * `sidebar` se posiciona a la izquierda (columna 1) y `main` a la derecha (columna 2).
   * `footer` se expande a lo ancho de ambas columnas (`100px` de altura).
   * Grilla:
     ```css
     grid-template:
       "header navbar" 50px
       "header navbar" 50px
       "sidebar main" auto
       "footer footer" 100px /
       200px auto;
     ```

3. **Desktop / Pantalla Grande (`min-width: 992px`):**
   * Tres columnas (`200px auto 200px`).
   * `header` ocupa las 3 columnas superiores (`100px` de altura).
   * Fila intermedia: `navbar` a la izquierda (`200px`), `main` al centro (`auto`), `sidebar` a la derecha (`200px`).
   * `footer` ocupa el ancho completo inferior.
   * Grilla:
     ```css
     grid-template:
       "header header header" 100px
       "navbar main sidebar" auto
       "footer footer footer" auto /
       200px auto 200px;
     ```

### C. Paleta Cromática y Estética (Fiel a Diapositiva 41)
* **HEADER:** `rgb(143, 135, 246)` (Lavanda / Púrpura suave)
* **NAVBAR:** `rgb(199, 100, 245)` (Magenta brillante)
* **MAIN:** `rgb(245, 245, 245)` (Gris claro de contraste suave)
* **SIDEBAR:** `rgb(122, 242, 145)` (Verde menta)
* **FOOTER:** `rgb(242, 157, 116)` (Salmón / Terracota)
* **Tipografía:** `Poppins`, `sans-serif` con sombras `box-shadow` y esquinas redondeadas (`border-radius: 10px`).

---

## 3. Evidencias de Verificación Visual

Las capturas generadas mediante renderizado headless confirman la fidelidad exacta frente al material del docente:

1. **Vista Móvil (375px):** `EVIDENCIAS/01_mobile_375px.png` (Coincide con Diapositiva 33).
2. **Vista Tablet (768px):** `EVIDENCIAS/02_tablet_768px.png` (Coincide con Diapositiva 36).
3. **Vista Desktop (1200px):** `EVIDENCIAS/03_desktop_1200px.png` (Coincide con Diapositiva 41).

---

## 4. Archivos Entregables

* **Ubicación del código:** [SEMANA05/PRACTICA/](PRACTICA/)
* **Paquete comprimido listo para UTP+Class:** [SEMANA05/Semana05_Tarea05_CSS_Grid_Max_Benites.zip](Semana05_Tarea05_CSS_Grid_Max_Benites.zip)

*(Nota: En cumplimiento con las normas éticas y de privacidad, no se realizó ninguna publicación ni envío automático en la plataforma UTP+Class).*
