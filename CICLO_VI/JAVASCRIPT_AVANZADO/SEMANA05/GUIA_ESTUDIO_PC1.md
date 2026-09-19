# Guía de Preparación Técnica — Primera Práctica Calificada (PC1)
## Asignatura: JavaScript Avanzado (`100000S51T`) — Ciclo VI (UTP)
**Docente:** Mtro. Iván Robles Fernández  
**Especialista Responsable:** Max Anderson Benites Corazón (*Senior Technical Implementation Specialist — NCR VOYIX*)  
**Fecha de Evaluación:** 07 de Septiembre de 2026 | **Ventana:** 10:15 - 11:45 (90 minutos)  
**Modalidad:** Individual · Solución integrada · Sustentación ante el docente  

---

## 1. Desglose del Temario Oficial (Semana 05)

De acuerdo a la sesión oficial `S05_s1 - JavaScript Avanzado - Evaluación`:

```mermaid
mindmap
  root((Temario PC1))
    Arreglos
      Creación: [], new Array(), new Array(n), new Array(a, b)
      Densos: índices contiguos, length coincide con claves
      No densos: sparse, huecos por elisión o delete
      Propiedad length y mutabilidad
    Cadenas de Texto
      Comillas simples, dobles y Plantillas Literales (backticks)
      Interpolación con ${expresion}
      Métodos: slice(), substring(), indexOf(), lastIndexOf(), includes()
      Transformación: toUpperCase(), toLowerCase(), trim(), split()
    Expresiones Regulares (RegEx)
      Notación literal /patron/flags vs constructor new RegExp()
      Banderas: i (case-insensitive), g (global), m (multilínea)
      Metacaracteres: \\d (dígito), \\s (espacio), [a-zA-Z], [0-9]
      Cuantificadores: + (1 o más), * (0 o más), ? (0 o 1)
      Métodos: regex.test(), regex.exec(), str.match(), str.replace(), str.search()
```

---

## 2. Puntos Críticos y Preguntas Frecuentes de Examen

### A. Arreglos en JavaScript
1. **Diferencia entre `new Array(3)` y `[3]`:**
   - `new Array(3)`: Crea un arreglo de tamaño 3 con **3 huecos vacíos** (*sparse array*).
   - `[3]` o `Array.of(3)`: Crea un arreglo denso de longitud 1 con el valor `3` en la posición `0`.

2. **Arreglos Densos vs. No Densos (*Sparse*):**
   - **Denso:** Los índices de `0` a `length - 1` existen físicamente en memoria como claves del objeto (`0 in arr === true`).
   - **No Denso:** Contiene "huecos" (*holes*), causados por:
     - Elisión literal: `[1, , 3]`
     - Constructor con longitud: `new Array(5)`
     - Operador `delete`: `delete arr[1]` (¡Ojo! `delete` elimina la propiedad pero **no** decrementa `length`).
     - Asignación en índice lejano: `arr[10] = 'x'`.
   - **Comportamiento ante métodos:**
     - `forEach`, `map`, `filter`, `reduce` **saltan** los huecos.
     - `for...of`, `Array.from` y `[...arr]` tratan los huecos como `undefined` y los densifican.
     - `includes(undefined)` devuelve `true`; `indexOf(undefined)` devuelve `-1`.

3. **La propiedad `length`:**
   - En JavaScript `length` no es solo de lectura: modificar `arr.length = 0` vacía el arreglo en memoria; aumentar `arr.length` crea huecos al final.

---

### B. Manipulación de Cadenas e Interpolación
1. **Comillas y Plantillas Literales:**
   - Comillas simples `'...'` y dobles `"..."`: Cadenas tradicionales, no soportan multilínea nativa sin escape ni interpolación directa.
   - Plantilla literal (comillas invertidas `` `...` ``): Permite cadenas multilínea y evaluación de expresiones incrustadas:
     ```javascript
     const mensaje = `Hola, ${nombre.toUpperCase()}. Tu promedio es ${(nota1 + nota2) / 2}`;
     ```

2. **Diferencias entre Métodos de Extracción:**
   - `str.slice(inicio, fin)`: Admite índices negativos (cuenta desde el final). No incluye el índice `fin`.
   - `str.substring(inicio, fin)`: Si `inicio > fin`, invierte los argumentos automáticamente. Convierte negativos a `0`.
   - `str.includes(subcadena)`: Devuelve booleano (`true`/`false`), sensible a mayúsculas.
   - `str.indexOf(subcadena)`: Devuelve la posición inicial (0-indexed) o `-1` si no existe.

---

### C. Expresiones Regulares (RegEx)
1. **Métodos esenciales de RegEx:**
   | Método | Sintaxis | Devuelve | Uso habitual |
   |---|---|---|---|
   | `test()` | `regex.test(cadena)` | `boolean` (`true`/`false`) | Validaciones de formulario (DNI, nombres, correo). |
   | `match()` | `cadena.match(regex)` | `Array` de coincidencias o `null` | Extracción de tokens. Con flag `/g` devuelve todas. |
   | `replace()`| `cadena.replace(regex, reemplazo)` | Nueva cadena transformada | Sanitización, limpieza de espacios o enmascarado. |
   | `search()` | `cadena.search(regex)` | Índice numérico o `-1` | Búsqueda por patrón. |

2. **Patrones comunes para el examen:**
   - Solo letras y tildes: `/^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/`
   - Validación de seguro: `/^(Essalud|EPS)$/i`
   - Solo números (ej. DNI / Teléfono): `/^\d{8}$/` o `/^[0-9]+$/`
   - Normalizar múltiples espacios en blanco: `texto.replace(/\s+/g, ' ').trim()`

---

## 3. Solución Integrada para la Práctica (Slide 17)

En la diapositiva 17 se exige:
> "Crear un archivo HTML con funcionalidad JavaScript que me permita:
> - Ingresar por teclado nombres, apellidos, lugar de nacimiento, dirección, universidad, tipo de seguro (Essalud o EPS).
> - Demostrar la funcionalidad de las operaciones con cadenas de texto.
> - Mostrar los datos en pantalla.
> Exponga sus resultados con el docente y reciba retroalimentación."

El proyecto completo e integrado se encuentra implementado en:
📁 [`SEMANA05/practica-integrada/`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA05/practica-integrada/)
- [`index.html`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA05/practica-integrada/index.html)
- [`style.css`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA05/practica-integrada/style.css)
- [`app.js`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA05/practica-integrada/app.js)

### Puntos clave para sustentar ante el docente:
1. **Captura de datos híbrida:** Permite entrada mediante formulario en pantalla y mediante `prompt()` secuencial (por si el docente pide explícitamente ver `prompt()`).
2. **Validación RegEx:** Se validan los campos en tiempo real usando `.test()`.
3. **Demostración de Cadenas:** Muestra explícitamente en una tarjeta dedicada las operaciones `slice`, `substring`, `indexOf`, `includes`, `replace`, `toUpperCase`, `toLowerCase` y la interpolación con plantilla literal multilínea.
4. **Almacenamiento en Arreglo:** Cada registro se inserta en un vector denso con `.push()`, mostrando la propiedad `.length`.
5. **Problema adicional de arreglos (Slide 14):** Se incluye un módulo para sumar números ingresados hasta digitar `0`.
