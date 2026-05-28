# Prompts de Referencia

## Para videos de YouTube (usar con Gemini)

### Video introductorio
```
Resume este video con los siguientes puntos:
- Tema principal y contexto general
- Ideas o conceptos clave mencionados
- Conclusión o mensaje final del video
Devuélvelo en texto estructurado.
```

### Video de tema específico
```
Resume este video con los siguientes puntos:
- Definición del tema principal
- Características clave
- Tipos o categorías (si aplica)
- Usos o aplicaciones prácticas
- Ejemplos mencionados
Devuélvelo en texto estructurado.
```

---

## Para páginas web (pegar URL directamente en el chat)

> Primero intenta pegar la URL directamente. Si hay error 403, usa Gemini con el prompt de abajo.

### Prompt para Gemini cuando la URL no es accesible
```
Resume el contenido de esta página con los siguientes puntos:
- Definición del tema principal
- Módulos, tipos o categorías descritos
- Características de cada uno
- Ventajas o beneficios mencionados
- Ejemplos o casos de uso
- Conclusiones
Devuélvelo en texto estructurado.
```

---

## Para archivos Excel (usar con Gemini)

```
Analiza este archivo de Excel y resúmelo con los siguientes puntos:
- Tema principal y objetivo del archivo
- Funciones o fórmulas utilizadas (con su sintaxis y descripción)
- Estructura de los datos (columnas, hojas, rangos)
- Ejemplos o casos resueltos que aparecen
- Conclusiones o patrones identificados
Devuélvelo en texto estructurado.
```

---

## Flujo de trabajo por tipo de recurso

| Recurso | Acción |
|---|---|
| **PDF** | Adjuntarlo directamente en el chat |
| **Excel (.xlsx)** | Adjuntarlo en Gemini con el prompt de arriba y pegar el texto en el chat |
| **Video YouTube** | Pedirle el resumen a Gemini y pegar el texto en el chat |
| **URL accesible** | Pegar el enlace directamente en el chat |
| **URL bloqueada (403)** | Pedirle el resumen a Gemini y pegar el texto en el chat |
