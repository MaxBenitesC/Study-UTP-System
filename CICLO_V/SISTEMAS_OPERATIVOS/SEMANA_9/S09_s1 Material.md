---
universidad: UTP
curso: Sistemas Operativos
tema: Segmentación, tabla de segmentos y segmentación paginada
semana: 9
sesion: 1
unidad: "Unidad de aprendizaje 2: Gestión de memoria — Segmentación"
tipo_documento: diapositivas
paginas: 19
fuente_pdf: S09_s1 Material.pdf
---

# Sistemas Operativos — Semana 09, Sesión 01
**Unidad 2:** Gestión de memoria — Segmentación.

## Inventario
- Archivo: S09_s1 Material.pdf | Páginas: 19 | Tipo: diapositivas | OCR: nítido
- Contiene: diagramas de tipos de segmentos, 1 tabla de segmentos con ejemplo, pasos de traducción.

## Logro / Temario / Conocimientos previos / Utilidad
- **Logro:** administrar cuentas en Linux.
- **Temario:** Tipos de Segmentación; Segmentación Paginada; Comandos Linux.
- **Conocimientos previos:** Arquitectura de computadoras; Programación básica.
- **Utilidad:** administrar cuentas y usuarios Linux.

---

## ¿Qué es la Segmentación?
**Concepto:** técnica de gestión de memoria que divide el **espacio de direcciones de un proceso en segmentos lógicos de tamaño variable**, cada uno con un propósito específico.

**Características principales:**
- **Tamaño variable:** cada segmento puede tener diferente tamaño.
- **Organización lógica:** refleja la estructura del programa.
- **Protección:** cada segmento tiene permisos de acceso.
- **Compartición:** los segmentos pueden compartirse entre procesos.

**Ventaja sobre paginación:** la segmentación se adapta mejor a la **estructura lógica del programa**, mientras que la paginación divide la memoria en bloques de tamaño fijo sin considerar la organización del código.

---

## Tipos de Segmentos
- **CÓDIGO (Segmento de Código):** contiene las instrucciones del programa. Es de **solo lectura** y puede compartirse entre múltiples procesos.
- **DATOS (Segmento de Datos):** almacena variables **globales y estáticas**. Permisos de lectura y escritura.
- **PILA / STACK (Segmento de Pila):** gestiona llamadas a funciones, variables locales y direcciones de retorno. **Crece dinámicamente.**
- **HEAP (Segmento de Heap):** memoria dinámica asignada en tiempo de ejecución (`malloc`, `new`). Crece según demanda.

---

## Tabla de Segmentos
**Funcionamiento:** cada proceso tiene una **Tabla de Segmentos** que contiene información sobre cada segmento del proceso.

**[TABLA — Ejemplo de tabla de segmentos]**
| Nº Segmento | Base | Límite | Permisos |
|-------------|------|--------|----------|
| 0 (Código) | 0x1000 | 4096 bytes | R-X (Lectura/Ejecución) |
| 1 (Datos)  | 0x5000 | 2048 bytes | RW- (Lectura/Escritura) |
| 2 (Stack)  | 0x8000 | 8192 bytes | RW- (Lectura/Escritura) |
| 3 (Heap)   | 0xC000 | 16384 bytes | RW- (Lectura/Escritura) |

> **Nota:** la **Base** indica dónde comienza el segmento en memoria física; el **Límite** indica el tamaño máximo del segmento. (La dirección se valida contra el límite por protección.)

---

## Segmentación Paginada
**¿Qué es?** Técnica **híbrida** que combina segmentación y paginación para aprovechar las ventajas de ambas.

- **De la Segmentación:** mantiene la organización lógica del programa en segmentos con significado (código, datos, stack, heap).
  - ✅ **Ventajas:** elimina fragmentación externa, permite compartir segmentos y proporciona protección por segmento.
- **De la Paginación:** cada segmento se divide en **páginas de tamaño fijo**, eliminando la fragmentación externa.
  - ⚠️ **Desventajas:** mayor complejidad en la traducción de direcciones y requiere **dos niveles de tablas** en memoria.

### Traducción de direcciones en Segmentación Paginada
**Dirección Lógica** → se divide en **tres partes**:
- **Número de Segmento (s):** identifica el segmento.
- **Número de Página (p):** identifica la página dentro del segmento.
- **Desplazamiento (d):** posición dentro de la página.

**Pasos de traducción:**
1. Usar **`s`** para buscar en la **Tabla de Segmentos**.
2. Obtener la dirección de la **Tabla de Páginas** del segmento.
3. Usar **`p`** para buscar el **marco de página** en la Tabla de Páginas.
4. Combinar el marco con **`d`** para obtener la **dirección física**.

> **Ejemplo:** la dirección lógica `(1, 3, 150)` significa **Segmento 1, Página 3, Desplazamiento 150**.

---

## Cierre
- ¿Qué aprendiste en esta sesión? Comparte tus conclusiones en clase.

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras/Diagramas | ~3 | Tipos de segmentos, traducción segmentación paginada |
| Tablas   | 1        | Tabla de segmentos (base/límite/permisos) |
| Fórmulas | 0        | (dirección lógica = s,p,d) |
| Código   | 0        | — |
| Ejercicios | 1      | Ejemplo de traducción (1,3,150) |
