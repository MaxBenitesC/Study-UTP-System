# Tarea Académica - Caso 2: Metodologías de Gestión de Proyectos Ágiles (Scrum)
**Curso:** Gestión de Proyectos (`100000I33N`)  
**Docente:** Nora Noelia Sernaque Zapata  
**Especialista / Estudiante:** Benites Corazón, Max Anderson - U24217839  

---

## B. Caso 2: Metodologías de Gestión de Proyectos Ágiles

### IV. Descripción de la empresa y/o Área afectada por el proyecto a implementar:

#### a) Nombre de la empresa y/o Área
De conformidad con los registros tributarios oficiales de la Superintendencia Nacional de Aduanas y de Administración Tributaria (SUNAT, 2026), la entidad financiera local corresponde a:
* **Razón Social:** BANCO DE CREDITO DEL PERU (subsidiaria principal del grupo financiero Credicorp Ltd.)
* **RUC:** 20100047218
* **Domicilio Fiscal:** Calle Centenario Nro. 156, Urb. Las Laderas de Melgarejo, La Molina, Lima, Perú
* **Área afectada:** Centro de Innovación y Transformación Digital BCP / División de Soluciones Digitales – Squad de Pagos e Interoperabilidad Digital (Yape)

#### b) Descripción de la empresa
El Banco de Crédito del Perú (BCP) es la institución financiera líder y de mayor trayectoria en el sistema bancario peruano, con más de 135 años de operaciones en el mercado nacional (BCP, 2023). A través de su red nacional de agencias, cajeros automáticos, agentes corresponsales y ecosistema de banca digital, atiende a millones de personas y empresas. Como respuesta a la disrupción fintech y la necesidad de acelerar la inclusión financiera en el Perú, el BCP estableció su Centro de Innovación y adoptó un modelo operativo ágil a nivel corporativo, descentralizando el desarrollo de productos en células multidisciplinarias (squads) enfocadas en la entrega continua de soluciones digitales de alto impacto, entre las cuales destaca la superapp Yape (BCP, 2023; Credicorp, 2023).

#### c) Misión, Visión de la empresa
De acuerdo con las declaraciones institucionales y reportes de sostenibilidad corporativa del banco (BCP, 2023):
* **Misión:** Promover el éxito de nuestros clientes con una oferta financiera simple, accesible y confiable, transformando vidas y negocios en el Perú para contribuir al progreso y desarrollo sostenible del país.
* **Visión:** Ser la empresa peruana que brinda la mejor experiencia a sus clientes: simple, cercana y oportuna, consolidándose como el referente indiscutible de innovación y confianza en la región.

#### d) Descripción del proyecto
* **Nombre del proyecto:** Desarrollo y Evolución Ágil de la Plataforma de Pagos Interoperables QR y Transferencias Inmediatas Multibancarias en Yape.
* **Objetivo:** Implementar de manera progresiva y sin interrupciones la interoperabilidad de pagos inmediatos entre Yape y las demás entidades del sistema financiero nacional (bancos, cajas municipales y billeteras competidoras como Plin), cumpliendo las fases regulatorias dispuestas por el Banco Central de Reserva del Perú (BCRP) y manteniendo una disponibilidad de servicio del 99.9% para más de 13 millones de usuarios activos (BCP, 2023; SBS, 2023).
* **Alcance del proyecto:**
  1. Diseño e integración de microservicios de pago interoperable basados en estándares ISO 20022 con la Cámara de Compensación Electrónica (CCE) y redes de switch transaccional (Niubiz/YellowPepper).
  2. Implementación de una arquitectura de lectura y generación dinámica de códigos QR interoperables en la aplicación móvil para clientes personales y comercios (Yape Negocios).
  3. Optimización de la experiencia de usuario (UI/UX) para validar transferencias interbancarias en menos de dos segundos mediante el número celular o alias digital.
  4. Despliegue de mecanismos de seguridad transaccional, tokenización y detección de fraudes en tiempo real mediante algoritmos de validación asíncrona.
  5. Automatización del pipeline de integración continua y entrega continua (CI/CD) para habilitar pases a producción quincenales sin ventanas de indisponibilidad para el usuario final.

#### e) Metodología Implementada
Se implementó el marco de trabajo adaptativo **Scrum** (Schwaber & Sutherland, 2020), bajo el modelo de escalado ágil por squads y tribus del Centro de Innovación BCP.

**Justificación:** A diferencia de proyectos de obra o infraestructura física, el desarrollo de funcionalidades en aplicaciones móviles masivas opera en un contexto de alta incertidumbre, requerimientos regulatorios en constante refinamiento por parte del BCRP y una necesidad crítica de tiempo de salida al mercado (time-to-market). La adopción de Scrum permitió al equipo fragmentar la complejidad del proyecto en iteraciones de dos semanas (sprints), validar la estabilidad de las pasarelas de pago con usuarios reales desde versiones tempranas e incorporar de forma inmediata ajustes técnicos y de experiencia de usuario derivados de los picos transaccionales observados en producción (Schwaber & Sutherland, 2020).

---

### V. Aplicación: Identificación de características Generales del modelo Ágil:

#### a) Roles y responsabilidades del Scrum Master en el proyecto
De acuerdo con la Guía oficial de Scrum (Schwaber & Sutherland, 2020) y los principios de agilidad estudiados en la Semana 03 del curso, el Scrum Master actúa como un líder servicial (*servant leader*) con responsabilidades claramente delimitadas dentro del squad de Yape:
1. **Facilitador de eventos de Scrum:** Conduce con disciplina los eventos del marco (Sprint Planning, Daily Scrum, Sprint Review y Sprint Retrospective), asegurando que se mantengan dentro de sus límites temporales (*timebox*) y alcancen sus objetivos operativos sin convertirse en reuniones de reporte burocrático (Schwaber & Sutherland, 2020).
2. **Remoción activa de impedimentos:** Detecta y gestiona de inmediato los bloqueos técnicos y dependencias externas que ralentizan el avance del equipo, tales como demoras en la homologación de APIs con la Cámara de Compensación Electrónica (CCE), lentitud en ambientes de pruebas o restricciones de cortafuegos de seguridad corporativa.
3. **Protección del equipo de desarrollo frente a interrupciones:** Actúa como amortiguador ante demandas o solicitudes emergentes de stakeholders comerciales o directivos del banco, protegiendo el Sprint Backlog acordado para que el equipo mantenga el foco en el Sprint Goal.
4. **Coaching ágil al Product Owner y al equipo:** Colabora con el Product Owner en la aplicación de técnicas efectivas para redactar y descomponer historias de usuario con criterios de aceptación claros (investigables, negociables, valiosas, estimables, pequeñas y testeables - INVEST), promoviendo que el Product Backlog se encuentre debidamente refinado.
5. **Garante de la calidad y la Definición de Terminado (DoD):** Supervisa que ningún incremento se considere completado si no cumple rigurosamente con los estándares de pruebas unitarias, análisis de vulnerabilidades estáticas de código (SAST) y homologación funcional.

#### b) Habilidades requeridas para un Scrum Master en el proyecto
En concordancia con el perfil del facilitador ágil y el marco conceptual de competencias analizado en las sesiones teóricas (PMI, 2021; Schwaber & Sutherland, 2020), el Scrum Master en este entorno requiere el dominio de tres áreas fundamentales:
1. **Formas de trabajar y agilidad técnica (Ways of Working):** Comprensión sólida del flujo de entrega de software moderno, integración continua / despliegue continuo (CI/CD), pruebas automatizadas y gestión de tableros ágiles en herramientas como Jira. Asimismo, debe dominar métricas de rendimiento ágil (velocidad del equipo, diagramas de flujo acumulado, gráficos de *burndown* y tiempo de ciclo), permitiendo al squad proyectar su capacidad real de trabajo basada en datos empíricos y no en conjeturas (Schwaber & Sutherland, 2020).
2. **Habilidades interpersonales y liderazgo servicial (Power Skills):** Destreza sobresaliente en facilitación neutral, escucha activa y mediación de conflictos constructivos entre las metas de negocio (urgencia de lanzamiento) y los criterios de ingeniería (deuda técnica y seguridad transaccional). Debe promover una cultura de seguridad psicológica donde los errores en las retrospectivas se analicen sin señalamientos personales, transformándose en oportunidades tangibles de mejora continua.
3. **Visión y comprensión empresarial (Business Acumen):** Familiaridad con la normativa del sistema financiero peruano (resoluciones del BCRP sobre interoperabilidad de pagos minoristas y disposiciones de ciberseguridad de la SBS), lo que le permite entender el contexto regulatorio en el que opera el Product Owner y priorizar la remoción de dependencias que comprometan fechas normativas mandatorias (SBS, 2023).

#### c) Ciclo de Vida del Proyecto
El proyecto siguió un ciclo de vida adaptativo, iterativo e incremental, articulado en sprints sucesivos de dos semanas de duración (Schwaber & Sutherland, 2020):
1. **Refinamiento del Product Backlog (Backlog Grooming):** Actividad continua en la que el Product Owner, el Scrum Master y el squad analizan y priorizan las historias de usuario relacionadas con la interoperabilidad (por ejemplo: validación de códigos QR de Plin, confirmación visual de transferencia interbancaria, manejo de respuestas de timeout), estimando su esfuerzo relativo mediante técnicas ágiles como *Planning Poker* con puntos de historia (*Story Points*).
2. **Planificación del Sprint (Sprint Planning):** Al inicio de cada ciclo de dos semanas, el equipo define el objetivo del sprint (*Sprint Goal*) —por ejemplo: "Habilitar la recepción de transferencias en tiempo real desde cajas municipales vía QR"— y selecciona del Product Backlog el conjunto de historias que integrarán el Sprint Backlog en función de la velocidad histórica del equipo.
3. **Ejecución del Sprint y Sincronización Diaria (Daily Scrum):** Durante los diez días hábiles del sprint, el equipo multidisciplinario (desarrolladores backend, frontend móvil, ingenieros QA y DevOps) diseña, codifica y prueba los componentes. Cada mañana sostienen una reunión de sincronización de 15 minutos frente al tablero Kanban/Scrum para responder: ¿qué se avanzó ayer?, ¿qué se avanzará hoy? y ¿qué impedimentos obstaculizan el avance?, identificando bloqueos en etapas tempranas.
4. **Revisión del Sprint (Sprint Review):** Al finalizar la iteración, el squad realiza una demostración en vivo del incremento de producto terminado y potencialmente desplegable a los directores de canales digitales del BCP y representantes de operaciones. Se valida la funcionalidad real en ambientes homologados y se recibe retroalimentación directa para ajustar las prioridades del backlog.
5. **Retrospectiva del Sprint (Sprint Retrospective):** Sesión formal de inspección del proceso interno del equipo previa a la siguiente planificación. Se analizan qué prácticas funcionaron adecuadamente (como la automatización de pruebas de regresión API), qué dificultades surgieron (como cuellos de botella en la aprobación de certificados criptográficos) y se define un plan de acción concreto con compromisos específicos de mejora para el siguiente ciclo.

#### d) Beneficios del uso de la metodología en el proyecto
Conforme a los fundamentos empíricos del modelo ágil documentados por Schwaber y Sutherland (2020), los beneficios tangibles obtenidos fueron:
* **Reducción del tiempo de salida al mercado (*Time-to-Market*):** Permitió liberar al mercado versiones funcionales de pagos interoperables en etapas quincenales, cumpliendo con holgura los plazos establecidos por el regulador financiero nacional antes que la competencia.
* **Adaptabilidad ante cambios regulatorios y de usuario:** Las modificaciones técnicas en los protocolos de comunicación emitidos por el BCRP durante la fase de despliegue se incorporaron en el sprint inmediato siguiente sin generar sobrecostos ni paralizaciones traumáticas de proyecto.
* **Mitigación temprana de riesgos de estabilidad transaccional:** Las pruebas de estrés e integración continua ejecutadas en cada iteración permitieron detectar cuellos de botella en la concurrencia de transacciones antes de su liberación a millones de usuarios finales.
* **Empoderamiento y compromiso del equipo:** La autoorganización del squad fomentó un sentido de propiedad compartida sobre los resultados del producto, incrementando la productividad y reduciendo la rotación de talento técnico especializado.

#### e) Desventajas del uso de la metodología en el proyecto
Siguiendo las restricciones identificadas en la literatura y en los materiales del curso sobre marcos adaptativos (PMI, 2021; Schwaber & Sutherland, 2020), se evidenciaron las siguientes desventajas:
* **Incertidumbre en la estimación de alcance y presupuesto total:** La naturaleza flexible del Product Backlog generó resistencia y fricción inicial con las áreas de control financiero y auditoría tradicional del banco, habituadas a presupuestos cerrados y cronogramas determinísticos anuales.
* **Riesgo latente de acumulación de deuda técnica:** La constante urgencia comercial por exhibir funcionalidades visibles en las revisiones quincenales tentó al equipo a priorizar la entrega rápida sobre tareas críticas de optimización de arquitectura interna, requiriendo una firmeza constante del Scrum Master para blindar la Definición de Terminado (DoD).
* **Fricción por disparidad de velocidad con áreas de soporte corporativo:** Mientras el squad de desarrollo operaba con ciclos ágiles de dos semanas, dependencias críticas con áreas externas como ciberseguridad, asuntos legales y cumplimiento normativo operaban bajo procesos secuenciales lentos, generando esperas y cuellos de botella fuera del control directo del equipo.

---

### VI. Conclusiones del Caso Ágil

1. La implementación del marco de trabajo Scrum resultó fundamental para el éxito de la interoperabilidad en Yape (BCP), demostrando que en el sector de banca digital y productos tecnológicos masivos, la capacidad de adaptación rápida a normativas regulatorias y la entrega continua de valor quincenal constituyen una ventaja competitiva decisiva frente a modelos predictivos rígidos que habrían retrasado la liberación de funcionalidades esenciales.

2. La efectividad del Scrum Master en contextos corporativos altamente regulados trasciende la simple facilitación de ceremonias: su rol se consolida como un catalizador estratégico capaz de proteger el ritmo de desarrollo del equipo, eliminar bloqueos burocráticos interdepartamentales y velar por el cumplimiento intransigente de la Definición de Terminado (DoD), asegurando que la velocidad del desarrollo ágil no vulnere los rigurosos estándares de seguridad y estabilidad que demanda el sistema financiero peruano.

---

### Referencias Bibliográficas Complementarias (Normas APA 7ma Edición)

* Banco de Crédito del Perú. (2023). *Reporte de Sostenibilidad y Gobierno Corporativo 2023*. Credicorp Ltd. https://www.viabcp.com
* Credicorp. (2023). *Digital transformation report: Accelerating financial inclusion in the Andean region*. Credicorp Ltd. https://www.credicorp.net
* Schwaber, K., & Sutherland, J. (2020). *La Guía de Scrum: Las reglas del juego*. Scrum.org. https://scrumguides.org
* Superintendencia de Banca, Seguros y Administradoras Privadas de Fondos de Pensiones. (2023). *Informe de inclusión financiera y digitalización de pagos en el Perú*. SBS. https://www.sbs.gob.pe
* Superintendencia Nacional de Aduanas y de Administración Tributaria. (2026). *Consulta RUC: BANCO DE CREDITO DEL PERU (RUC 20100047218)*. Plataforma del Estado Peruano. https://e-consultaruc.sunat.gob.pe
