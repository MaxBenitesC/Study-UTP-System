# ANTIGRAVITY.md — Proyecto VoIP Call Center Multisede (UTP)

Este archivo consolida el contexto, las reglas de interacción y el estado de avance desde la perspectiva de **Antigravity** (AI Coding Assistant), para colaborar como *peer programmer* de alto nivel con **Max Anderson Benites Corazón** (Senior Technical Implementation Specialist, NCR VOYIX).

Última actualización: 04 de Junio de 2026.

## 1. Identidad y Contexto del Proyecto

**Proyecto Universitario:** "Diseño e implementación de arquitectura de red IPv4 convergente para la optimización de infraestructura VoIP en un call center multisede"
**Curso:** Redes y Comunicación de Datos I (UTP, Ciclo V).
**Equipo:** Vega Jauregui D. J., Benites Corazón M. A., Quispe Arias K. A., Tasayco Magallanes G. M.
**Problema central:** 2 a 4 incidencias semanales de audio VoIP en una plataforma ViciDial multisede, causadas por falta de políticas de red (QoS, VPN, Segmentación), no por saturación de ancho de banda.

## 2. Estado Actual (Consolidado)

Tras la revisión del diagrama de topología y el plan de direccionamiento por parte de los compañeros de equipo, se ha consolidado la fase de diseño del proyecto:

1. **Topología Definitiva:** Se consolidó el modelo **Hub-and-Spoke** con Bellavista como nodo central. La sede principal incluye un MikroTik RB3011, failover de doble ISP y 3 servidores ViciDial (el maestro en Bellavista y réplicas locales en cada spoke para alta disponibilidad).
2. **Segmentación y Adquisición (VLANs resueltas):** Se decidió proponer la compra de un switch gestionable **Cisco Business CBS250-24T-4G** para Bellavista, lo que habilita la creación de las 3 VLANs (10 Voz, 20 Datos, 30 Gestión) de extremo a extremo.
3. **Direccionamiento Avanzado (VLSM):** Se implementó un esquema de direccionamiento con Máscaras de Subred de Longitud Variable (VLSM) optimizando las redes LAN (/26, /27, /28) y túneles VPN WireGuard (/30).
4. **Cálculos Teóricos y Matemáticos:** Fórmulas de ancho de banda VoIP integradas utilizando notación académica con LaTeX (`amsmath`) en el documento. Se agregó justificación teórica basada en modelos OSI y TCP/IP.
5. **Alineación IEEE:** Se ajustaron citas en formato numérico IEEE `[1]`, `[2]` mediante script de reemplazo automático.
6. **Formato Académico del Documento:** Se eliminaron las meta-etiquetas ("COMPLETADO", "PENDIENTE", "EV-XX") del cuerpo principal del artículo para garantizar el rigor académico. Las etiquetas de control del proyecto se conservan únicamente en la Tabla 9 (Anexo A).
7. **Regla de Imágenes:** Las figuras importadas en el Markdown no llevan el texto "Figura X." manual. Pandoc y LaTeX se encargan automáticamente de la numeración al exportar a PDF, evitando la redundancia "Figura 1: Figura 1".

## 3. Reglas de Interacción de Antigravity

Para asegurar un nivel de ingeniería Senior adecuado para Max Anderson, me comprometo a cumplir estrictamente estas directivas:

- **Cero Alucinaciones:** Bajo ninguna circunstancia inventaré métricas de latencia, jitter, o tiempos de recuperación.
- **Rigor Profesional:** Todo script, reporte de solución o documento técnico que yo genere incluirá los créditos profesionales de **Max Anderson Benites Corazón**. Me dirigiré a él con el respeto de un colega Arquitecto de Redes/Ingeniero Principal.
- **Enfoque en Resultados Reales:** Seguiré el estándar estricto de evidencia (COMPLETADO solo si el artefacto real existe).
- **Formato y Estilo Académico:** Respetaré las configuraciones de compilación en LaTeX (xelatex) y Markdown definidas previamente, manteniendo la seriedad académica (sin emojis ni "bloques coloreados" informales).
- **Control de Metadatos:** Mantener el cuerpo del texto libre de jerga de gestión de proyecto. Las etiquetas internas de estado pertenecen solo a los anexos.
- **Manejo de Imágenes Automático:** Confiar la enumeración de las figuras al compilador.

## 4. Archivos Clave del Repositorio

- `Articulo_Universitario_claude.md`: Fuente principal del paper. Contiene el proyecto al 100% de la fase de diseño.
- `Articulo_Universitario_avance_junio_2026.pdf`: Último PDF compilado, pulido y alineado a IEEE.
- `Diseno_Rede_Propuesta_mejorado.jpeg`: Diagrama oficial del proyecto con VLSM.
- `Plan_direccionamiento_IP.jpeg`: Tabla oficial de VLSM.
- `FORMATO_REDES_PROFESOR.md`: Resumen estructural de la rúbrica exigida por el profesor.
- `latex_header.tex`: Configuraciones de maquetación avanzada (control de viudas/huérfanas, soporte matemático `amsmath`).

## 5. Siguientes Pasos (Agenda Inmediata)

El documento ha alcanzado el punto de madurez necesario para la revisión del profesor de la fase de diseño. Las próximas fases se centrarán en la validación práctica de esta arquitectura:
1. **Configuración Simulada (EV-11 a EV-14):** Preparar los laboratorios de simulación en Cisco Packet Tracer y GNS3 para WireGuard, VLANs y QoS.
2. **Piloto Controlado (EV-15 a EV-18):** Ejecutar pruebas de carga, failover y caídas para contrastarlas con la línea base capturada en campo.
