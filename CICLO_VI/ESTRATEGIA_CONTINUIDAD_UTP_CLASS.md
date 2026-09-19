# Estrategia de continuidad: UTP+Class

Última actualización: 2026-09-02 (America/Lima).

Este documento permite que otra IA continúe la recuperación de materiales y
tareas desde UTP+Class sin pedir credenciales ni repetir investigaciones ya
resueltas. Las reglas generales y el formato de caché están documentados en
`AGENTS.md`; ambos documentos deben leerse antes de actuar.

## Objetivo activo

Organizar el curso **Hojas de Estilo en Cascada Avanzado**, descargar todos los
archivos oficiales visibles y recuperar los enunciados y adjuntos de las tareas
que estén presentes en la caché local de Chrome.

No se autoriza publicar, entregar tareas, enviar mensajes ni responder foros.

## Curso y ubicación

- URL entregada por el usuario:
  `https://class.utp.edu.pe/student/courses/e37c660f-c54e-559f-a036-f7f32313355e/section/4214affb-26e4-5eb0-a205-24479d42f917/learnv2`
- `courseId`: `e37c660f-c54e-559f-a036-f7f32313355e`
- `sectionId`: `4214affb-26e4-5eb0-a205-24479d42f917`
- Directorio local:
  `HOJAS_DE_ESTILO_EN_CASCADA_AVANZADO`
- Perfil de Chrome autorizado: `Profile 1`
- Caché HTTP:
  `/home/ilkay/.cache/google-chrome/Profile 1/Cache/Cache_Data`

## Estado comprobado

La respuesta completa del curso está disponible en:

- Archivo de caché: `9a0d8018835517b8_0`
- Clave exacta:
  `https://api-pao.utpxpedition.com/course/student/courses/e37c660f-c54e-559f-a036-f7f32313355e/sections/4214affb-26e4-5eb0-a205-24479d42f917/full`

La respuesta indica:

- 18 semanas.
- Semana actual informada por UTP: 4.
- Las semanas 1–18 y sus archivos aparecen visibles en la respuesta `full`.
- 35 archivos oficiales de tipo `FILE` (aprox. 115 MiB).
- 13 actividades de tipo `HOMEWORK`.
- 17 foros de consulta, de las semanas 1–17.
- Una evaluación adicional `PC-01` en la semana 4.
- Cuatro enlaces bibliográficos en la semana 0.

Los 35 archivos oficiales ya fueron descargados y validados. También se
crearon el índice del curso y los documentos locales de Tarea FlexBox, S04.s1
PC1 y PC3. Falta recuperar los resúmenes de las tareas que el usuario aún no ha
abierto en Chrome.

El usuario pidió después priorizar las semanas 1–4 por la PC-01. Se creó
`HOJAS_DE_ESTILO_EN_CASCADA_AVANZADO/REPASO_PC01_SEMANAS01_A_03.md`.

La semana 4 contiene dos elementos distintos:

- `S04.s1 PC1`: tarea no calificada y vencida del 6 de septiembre de 2024;
  puede analizarse como práctica antigua.
- `PC-01`: evaluación calificada vigente, programada el 2 de septiembre de
  2026 de 18:35 a 20:05, con 20 preguntas, 20 puntos, un intento y 90 minutos.
  La caché indica `NOT_STARTED`. No iniciar el intento ni recuperar, revelar o
  resolver sus preguntas. Limitar la asistencia al repaso de materiales ya
  publicados y ejercicios originales equivalentes.

## Archivos locales que ya existían

Preservar estos archivos; pertenecen al usuario y no deben sobrescribirse:

- `SEMANA03/TAREA FLEXBOX.docx`
- `SEMANA03/TAREA FLEXBOX.pdf`
- `SEMANA03/index.html`
- `SEMANA03/estilos.css`

Antes de descargar adjuntos de la tarea Flexbox, comparar nombre, tamaño y hash
para evitar duplicados o pérdida del trabajo local.

## Inventario resumido de archivos oficiales

| Semana | Archivos `FILE` | Actividades relevantes |
|---:|---:|---|
| 1 | 5 | Foro |
| 2 | 2 | Foro, Tarea 02 |
| 3 | 2 | Foro, Tarea FlexBox |
| 4 | 1 | Foro, S04.s1 PC1, PC-01 |
| 5 | 2 | Foro, S05.s1 1Punto PC2 |
| 6 | 2 | Foro |
| 7 | 2 | Foro |
| 8 | 1 | Foro, S08.s1 PC2 |
| 9 | 2 | Foro, S09.s1 Tarea 09 |
| 10 | 2 | Foro, S10.s1 Tarea transform |
| 11 | 2 | Foro, PC3 ok; uno de los archivos es `PC3.docx` |
| 12 | 2 | Foro, S12.s1 Tarea Framework |
| 13 | 2 | Foro, S13.s1 2Puntos EF. |
| 14 | 2 | Foro, S14.s1 Tarea 14 Bootstrap |
| 15 | 2 | Foro |
| 16 | 1 | Foro |
| 17 | 2 | Foro, S17.s1 1 Punto Final |
| 18 | 1 | Examen Final CSS |

## Tareas y UUID de actividad

Estos UUID corresponden a actividades académicas, no a personas:

| Semana | Tarea | `activityId` | Resumen en caché |
|---:|---|---|---|
| 2 | Tarea 02 | `99a28a84-8454-4053-a6ea-35700ea40963` | No localizado |
| 3 | Tarea FlexBox | `1978679c-6892-44a8-a627-33a46a30547b` | Sí: `668623a1560f089f_0` |
| 4 | S04.s1 PC1 | `9d6c649c-8e9f-4bdf-8bfa-140461ce5379` | Sí: `15dfbe58c894709c_0` |
| 5 | S05.s1 1Punto PC2 | `6fc34caa-a114-4f96-8816-2b6763401298` | No localizado |
| 8 | S08.s1 PC2 | `789916f3-f163-48f3-b474-c3254d54f6f6` | No localizado |
| 9 | S09.s1 Tarea 09 | `0532a7f7-19da-429e-b7c8-ca9209fb0f10` | No localizado |
| 10 | S10.s1 Tarea transform | `545f2783-ebcf-4297-ad94-176c44c26b61` | No localizado |
| 11 | PC3 ok | `0d80f177-1af6-46d5-9c1d-d377a8bde16e` | No localizado |
| 12 | S12.s1 Tarea Framework | `a408e33f-7995-4e56-bb57-4a0d2a985297` | No localizado |
| 13 | S13.s1 2Puntos EF. | `d03cbbae-fb9a-4edf-999d-ca7b5d996aad` | No localizado |
| 14 | S14.s1 Tarea 14 Bootstrap | `0feb45bb-b682-49bb-a27b-1d388de6ed13` | No localizado |
| 17 | S17.s1 1 Punto Final | `e89cd7bb-52bd-4337-9af4-3d77a4dcda6a` | No localizado |
| 18 | Examen Final CSS | `98911ed7-b006-4d4a-b030-7bd4ec8d0d35` | No localizado |

La evaluación `PC-01` de la semana 4 usa el identificador
`17fb3fec-85d4-45da-8208-69e734e609de`; no tratarla como tarea ordinaria sin
revisar primero su tipo y disponibilidad.

## Cómo recuperar la estructura sin conectarse con credenciales

No abrir la URL con herramientas web externas, Selenium ni un Chrome copiado.
La vía comprobada es leer la respuesta que el Chrome habitual ya guardó.

```python
from pathlib import Path
import json
import zlib

path = Path(
    "/home/ilkay/.cache/google-chrome/Profile 1/Cache/Cache_Data/"
    "9a0d8018835517b8_0"
)
raw = path.read_bytes()
key_length = int.from_bytes(raw[12:16], "little")
body_offset = 24 + key_length
decoder = zlib.decompressobj(31)
body = decoder.decompress(raw[body_offset:]) + decoder.flush()
course = json.loads(body)
```

Procesar el objeto en memoria. No guardar el JSON completo dentro del
workspace porque contiene datos de sección que no son necesarios para el
resultado académico.

Los recursos se encuentran en:

```text
course["data"]["unities"][*]["themes"][*]["contents"][*]
```

Para los objetos con `type == "FILE"`, usar:

- `theme["weekNumber"]`
- `content["title"]`
- `content["metadata"]["filename"]`
- `content["metadata"]["filetype"]`
- `content["metadata"]["size"]`
- `content["metadata"]["url"]`

## Plan de descarga

1. Crear `SEMANAxx/MATERIALES` para cada semana con archivos.
2. Descargar los 35 objetos `FILE` desde sus URL oficiales S3 con `curl -L`.
3. Usar nombres descriptivos y únicos. Conservar la extensión real.
4. Comparar el tamaño descargado con `metadata.size`.
5. Validar:
   - PDF: `file`, `pdfinfo` y `pdftotext`.
   - PPTX/DOCX: `file` y `unzip -t`.
   - Imágenes: `file` e identificación de dimensiones.
6. No sobrescribir archivos existentes. Si hay coincidencia, comparar hash.
7. Crear un inventario Markdown por curso o actualizar el README solo después
   de que todas las validaciones terminen correctamente.

Ejemplo de descarga:

```bash
curl -L --fail --show-error --silent 'URL_OFICIAL' \
  -o 'HOJAS_DE_ESTILO_EN_CASCADA_AVANZADO/SEMANA01/MATERIALES/Nombre.ext'
```

## Recuperación de tareas

El endpoint esperado es:

```text
https://api-pao.utpxpedition.com/course/student/sections/{sectionId}/homeworks/{activityId}/resume
```

Hay dos respuestas `resume` ya almacenadas:

- Semana 3, Tarea FlexBox: `668623a1560f089f_0`.
- Semana 4, S04.s1 PC1: `15dfbe58c894709c_0`.

Para cada resumen disponible:

1. Descomprimir en memoria usando el mismo formato de caché.
2. Extraer únicamente título, enunciado, fechas, modalidad, rúbrica y adjuntos.
3. Descargar los adjuntos oficiales dentro de `SEMANAxx/MATERIALES` o una
   subcarpeta `TAREA` cuando convenga distinguir enunciado y entregables.
4. Guardar una síntesis local como `SEMANAxx/ENUNCIADO_TAREA_<tema>.md`.
5. No incluir identificadores personales ni información de otros estudiantes.

Para las tareas cuyo resumen no está en caché, pedir al usuario que abra el
detalle exacto de cada tarea una vez en su Chrome habitual (`Profile 1`). Luego
buscar el `activityId` con `rg -a -l` y repetir la lectura. No pedir cookies,
tokens, usuario ni contraseña.

## Próximos pasos exactos

1. Pedir al usuario que abra en Chrome el detalle de las tareas enumeradas como
   pendientes en `INDICE_MATERIALES_Y_TAREAS.md`.
2. Buscar nuevamente cada `activityId` en `Cache_Data`.
3. Analizar y documentar los nuevos resúmenes sin conservar JSON de la API.
4. Descargar sus adjuntos, si los hubiera, sin sobrescribir trabajo local.
5. Limpiar cualquier extracción temporal; nunca borrar la caché original.

## Criterio de finalización

La tarea se considera terminada cuando:

- Los 35 archivos oficiales estén organizados y validados.
- Los dos resúmenes disponibles estén documentados localmente.
- Se haya indicado qué tareas requieren que el usuario abra su detalle.
- No queden JSON de API, perfiles copiados ni diagnósticos temporales.
- Se confirme expresamente que no se publicó ni entregó nada en UTP.
