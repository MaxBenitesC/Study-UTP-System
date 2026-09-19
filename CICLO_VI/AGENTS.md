# Instrucciones persistentes para trabajar con UTP+Class

Este archivo documenta el procedimiento que ya funcionó para consultar cursos, semanas, foros y tareas de UTP+Class usando la sesión local del usuario. Debe aplicarse a las sesiones de Codex que trabajen dentro de `CICLO_VI` y sus subdirectorios.

## Objetivo

Cuando el usuario entregue un enlace de `https://class.utp.edu.pe/.../learnv2`, recuperar el enunciado y los materiales oficiales desde la sesión que ya utilizó en Chrome. El usuario no debe descargar manualmente los archivos si estos pueden recuperarse mediante el procedimiento descrito aquí.

## Autorización y límites

- El usuario autoriza la inspección local de la caché de su sesión de UTP en Chrome cuando proporciona el curso o enlace que desea trabajar.
- No pedir ni almacenar usuario, contraseña, cookies, encabezados `Authorization`, tokens ni secretos de sesión.
- No publicar foros, subir tareas, enviar mensajes ni realizar entregas en nombre del usuario salvo que lo solicite explícitamente.
- La consulta y preparación de respuestas es el flujo normal. Informar siempre que no se publicó nada.
- No conservar respuestas JSON de la API que incluyan datos personales. Si se usan archivos temporales, eliminarlos al terminar.
- No mostrar en la respuesta final correos, códigos de estudiante, UUID personales ni otros datos privados de los compañeros.

## Entorno local comprobado

- Perfil usado por UTP: `Profile 1`.
- Perfil de Chrome: `/home/ilkay/.config/google-chrome/Profile 1`
- Caché HTTP de Chrome: `/home/ilkay/.cache/google-chrome/Profile 1/Cache/Cache_Data`
- Workspace académico: `/home/ilkay/Documentos/UTP/CICLO_VI`

Cursos ya identificados:

- Administración y Organización de Empresas
  - Curso: `36180ea2-75a4-523f-a0a8-0e10459accea`
  - Sección: `4fc2ca24-6b8b-5f62-a633-cf4f639191af`
  - Directorio: `ADMINISTRACION_Y_ORGANIZACION_DE_EMPRESAS`
- Gestión de Proyectos
  - Curso: `720da441-4166-5b6e-8022-d6c25d078561`
  - Sección: `56a6cac4-b2c9-52bb-91da-dd2e7641e1a1`
  - Directorio: `GESTION_DE_PROYECTOS`
- Algoritmos y Estructuras de Datos
  - Curso: `25cdcf4b-0d6d-512a-9e9e-4438acd17964`
  - Sección: `8a788477-d6e3-513f-b910-09cecf97394b`
  - Directorio: `ALGORITMOS_Y_ESTRUCTURAS_DE_DATOS`

Los UUID de otros cursos deben tomarse del enlace proporcionado por el usuario, sin asumirlos.

## Procedimiento recomendado

### 1. Extraer los identificadores del enlace

Los enlaces suelen incluir:

```text
/courses/{courseId}/section/{sectionId}/learnv2
/week/{weekNumber}/unit/{unitId}/theme/{themeId}/content/{contentId}/...
```

En foros, `contentId` suele coincidir con `forumId`. En tareas, la URL contiene `homework/{homeworkId}`.

### 2. Comprobar si el contenido ya está en caché

Buscar primero por los UUID del curso, sección o actividad:

```bash
rg -a -l 'UUID_DEL_CURSO|UUID_DE_LA_ACTIVIDAD' \
  '/home/ilkay/.cache/google-chrome/Profile 1/Cache/Cache_Data' \
  '/home/ilkay/.config/google-chrome/Profile 1/Service Worker'
```

Si no aparece la actividad:

1. Pedir al usuario que abra el enlace exacto en su Chrome habitual (`Profile 1`).
2. Esperar a que la página termine de cargar.
3. Para un foro, pedirle que entre al foro una vez.
4. Para una tarea, pedirle que abra el detalle de la tarea una vez.
5. Repetir la búsqueda.

Normalmente no es necesario que el usuario vuelva a iniciar sesión ni que entregue credenciales.

### 3. Identificar las URLs internas almacenadas

Chrome guarda estas respuestas en archivos `*_0`. En el formato observado:

- bytes `12:16`: longitud de la clave, entero little-endian;
- la clave comienza en el byte `24`;
- el cuerpo comprimido comienza en `24 + longitud_de_clave`;
- las respuestas observadas usan gzip y pueden abrirse con `zlib.decompressobj(31)`.

Listado de claves coincidentes, solo lectura:

```python
from pathlib import Path

base = Path("/home/ilkay/.cache/google-chrome/Profile 1/Cache/Cache_Data")
needle = b"UUID_DEL_CURSO_O_ACTIVIDAD"

for path in base.glob("*_0"):
    raw = path.read_bytes()
    if needle not in raw or len(raw) < 24:
        continue
    key_length = int.from_bytes(raw[12:16], "little")
    key = raw[24:24 + key_length].decode("utf-8", "replace")
    print(path.name, key)
```

Verificar siempre que la clave impresa corresponda al curso, sección y actividad solicitados. No elegir un archivo únicamente porque contenga un UUID dentro del cuerpo.

### 4. Descomprimir una respuesta JSON

```python
from pathlib import Path
import json
import zlib

def load_chrome_cache_json(filename):
    path = Path(
        "/home/ilkay/.cache/google-chrome/Profile 1/Cache/Cache_Data",
        filename,
    )
    raw = path.read_bytes()
    key_length = int.from_bytes(raw[12:16], "little")
    body_offset = 24 + key_length
    decoder = zlib.decompressobj(31)
    body = decoder.decompress(raw[body_offset:]) + decoder.flush()
    return json.loads(body)
```

Preferir analizar el JSON en memoria y mostrar únicamente los campos necesarios. Si el formato cambia o el cuerpo no empieza con gzip, detenerse e inspeccionar el archivo; no asumir offsets distintos ni buscar credenciales como atajo.

## Endpoints internos útiles

La clave de cada archivo de caché contiene la URL completa. Los patrones comprobados son:

### Estructura completa del curso

```text
https://api-pao.utpxpedition.com/course/student/courses/{courseId}/sections/{sectionId}/full
```

`data.unities[].themes[]` contiene semanas, temas y contenidos. Filtrar por `weekNumber`. Los contenidos HTML suelen guardar el material en `metadata.contentHtml`, incluidos enlaces oficiales de S3, YouTube, Canvas o H5P.

### Datos generales de la sección

```text
https://api-pao.utpxpedition.com/course/student/courses/{courseId}/sections/{sectionId}
```

### Resumen de un foro

```text
https://api-pao.utpxpedition.com/forum/student/sections/{sectionId}/forum/{forumId}/resume?includeComments=false
```

Contiene el título, el enunciado exacto, las fechas, el estado y las indicaciones de participación.

### Comentarios de un foro

```text
https://api-pao.utpxpedition.com/forum/student/{studentUuid}/courses/{courseId}/sections/{sectionId}/forums/{forumId}/comment?role=student&page=1
```

Revisar `totalPages`; puede haber `page=2`, `page=3`, etc. Usar los comentarios solo para comprender el nivel, la extensión y evitar repetir ideas. Nunca copiar una respuesta ni exponer datos personales.

### Resumen de una tarea

```text
https://api-pao.utpxpedition.com/course/student/sections/{sectionId}/homeworks/{homeworkId}/resume
```

Contiene el enunciado, archivos, fechas, intentos, rúbrica y estado de entrega.

## Descarga de materiales oficiales

Los enlaces dentro de `metadata.contentHtml` y los enunciados suelen apuntar a:

```text
https://utp-prd-upload-file-storage.s3.amazonaws.com/pao/content/...
```

Estos archivos se pudieron descargar directamente con `curl -L`, sin copiar credenciales:

```bash
curl -L 'URL_OFICIAL' -o 'SEMANA03/MATERIALES/Nombre_descriptivo.pdf'
```

Reglas:

- Guardar en el curso y semana correspondientes, dentro de `MATERIALES`.
- Usar nombres descriptivos y conservar la extensión real.
- Verificar con `file`, `pdfinfo`, `unzip -t` u otra herramienta adecuada.
- Para PDF con texto, usar `pdftotext`.
- Si `pdftotext` devuelve poco o nada, renderizar páginas con `pdftoppm` y revisarlas visualmente; algunas transcripciones de UTP son imágenes.
- Para Excel, inspeccionar hojas y celdas con `openpyxl` sin alterar el original.
- Un contenido H5P público puede incluir `H5PIntegration` y `jsonContent` en su página `embed`; analizar ese JSON cuando no exista transcripción descargable.

## Cómo preparar foros

Antes de redactar:

1. Leer el enunciado exacto desde `forum/.../resume`.
2. Revisar los materiales oficiales vinculados.
3. Revisar una muestra suficiente de respuestas existentes y sus páginas.
4. Determinar la longitud y el tono usados por el aula.
5. Identificar las ideas repetidas y buscar un ángulo propio basado en los materiales o en la experiencia real del usuario.

Preferencias del usuario:

- Redacción clara, natural y de estudiante; evitar tono de consultor o textos excesivamente largos.
- No inventar experiencias ni datos.
- Cuando sea pertinente, usar ejemplos reales pero genéricos de implementaciones tecnológicas, redes, POS, despliegues o resolución de incidentes, sin nombrar clientes ni revelar información confidencial.
- Preparar por separado el aporte principal y la respuesta a un compañero cuando el foro pida diálogo.
- Elegir un compañero concreto y responder a una idea real de su comentario.
- Si el enunciado menciona video pero la interfaz solo permite comentarios y todas las participaciones son escritas, señalar la inconsistencia y seguir el formato efectivo del aula.
- Guardar el resultado como `SEMANAxx/Intervencion_Foro_<tema>.md`.
- Incluir en el archivo la pregunta, fecha límite, estado observado y materiales revisados, pero dejar claramente separada la parte que debe copiarse.

## Lecciones de la investigación anterior

- Abrir el enlace mediante una herramienta web externa no recupera la sesión autenticada; UTP+Class es una aplicación JavaScript y puede devolver errores o solamente el contenedor HTML.
- Copiar todo el perfil de Chrome, iniciar Chrome headless, usar Selenium o leer el DOM por CDP produjo bloqueos y no fue la vía efectiva.
- El método que funcionó fue buscar y descomprimir las respuestas de la API que el Chrome normal ya había guardado en caché.
- No repetir los intentos con Selenium mientras las respuestas necesarias ya estén presentes en `Cache_Data`.
- La API `.../full` proporciona la estructura de la semana y los enlaces de materiales; los endpoints `resume` proporcionan el enunciado preciso de foros y tareas.
- Los objetos S3 usados por el curso pudieron descargarse sin exportar cookies.

## Limpieza y entrega

- Conservar únicamente los materiales académicos y los documentos preparados dentro del curso.
- Eliminar copias temporales de perfiles, JSON de API, HTML de H5P y archivos de diagnóstico cuando dejen de ser necesarios.
- No borrar la caché original de Chrome.
- Al finalizar, indicar qué archivos se crearon, cuál debe copiar o entregar el usuario y que no se realizó ninguna publicación en UTP.
