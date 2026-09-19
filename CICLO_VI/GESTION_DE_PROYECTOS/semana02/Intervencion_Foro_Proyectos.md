# Foro: Contando nuestras experiencias en proyectos

A continuación, tienes listas tus dos intervenciones para copiar y pegar en la plataforma de tu curso.

He seleccionado a **Priscilla Zarella Misari Magallanes** para tu segunda intervención, ya que ella menciona dificultades específicas con la asignación de roles y la encriptación de contraseñas en su proyecto web. Esto conecta de forma inmejorable y muy natural con tu experiencia técnica en el proyecto de *Identity Server* que acabas de exponer.

---

## 📌 1. Primera Intervención (Tu aporte principal)

**Asunto / Título recomendado:** Experiencia en auditoría y remediación de arquitectura: Proyecto Identity Server

¡Un saludo cordial para todos!

Para este foro, quiero compartir un proyecto muy reciente y crítico que lideré en mi rol de **Senior Technical Implementation Specialist en NCR VOYIX**. 

**a. Contexto del Proyecto:**
Se trató de la auditoría de seguridad, remediación técnica y entrega de una plataforma de autenticación centralizada (**Identity Server**) para un cliente corporativo muy grande del sector retail (Intercorp/SPSA). El reto principal era parchar vulnerabilidades críticas y refactorizar la arquitectura de despliegue (Docker) sobre un framework *legacy* (.NET Core 3.1), todo mientras el sistema seguía integrándose con las aplicaciones satélites del cliente vía tokens JWT.

**b. Análisis de las prácticas del proyecto:**

**¿Qué prácticas se cumplieron y tuvieron buen desempeño?**
1. **Refactorización y Optimización de Arquitectura (Eficiencia):** Logramos optimizar la construcción de la imagen Docker. La práctica original saturaba el disco y generaba condiciones de carrera al compilar. Al aplicar directivas estrictas, redujimos el peso del entregable en un 50% (de 470 MB a 238 MB) y garantizamos la estabilidad del despliegue.
2. **Defensa en Profundidad (Seguridad):** Tuvimos un excelente desempeño mitigando vulnerabilidades (como escalamiento de privilegios y fugas multi-tenant). Se aplicó una buena práctica de inyectar seguridad en múltiples capas (filtros *Row-Level Security* en Base de Datos y validaciones estrictas a nivel de Controladores API).

**¿Qué prácticas no tuvieron un buen desempeño?**
1. **Gestión de la Deuda Técnica vs. Compromisos Comerciales:** Esta práctica tuvo un desempeño deficiente a nivel gerencial. Se prometió operativamente la entrega sobre una versión de software que el equipo de Desarrollo ya consideraba obsoleta (*End of Life*). Esto nos obligó a realizar "hotfixes" quirúrgicos complejos asumiendo un alto riesgo técnico, en lugar de hacer una migración limpia hacia una versión estable LTS.
2. **Planificación de Entregables de Migración (Base de Datos):** Entregamos los scripts de creación de base de datos desde cero, pero fallamos en no incluir scripts de migración *incremental* automatizados. Esto trasladó la responsabilidad y el riesgo de la migración de los datos históricos al equipo de ingeniería del cliente, lo cual generó fricciones durante el pase a producción.

Esta experiencia refuerza que, en proyectos de software y seguridad, alinear las promesas comerciales con la realidad de la arquitectura técnica es fundamental para evitar cuellos de botella.

¡Espero que este caso les resulte enriquecedor!

*Atentamente,*
**Max Anderson Benites Corazón**
*Senior Technical Implementation Specialist | NCR VOYIX*

---

## 📌 2. Segunda Intervención (Respuesta a un compañero)

**Respuesta a PRISCILLA ZARELLA MISARI MAGALLANES:**

¡Hola Priscilla! Excelente trabajo. Entiendo perfectamente tu reto con la asignación de roles y la encriptación de contraseñas. 

Como comenté en mi intervención, en integraciones corporativas, gestionar la seguridad e identidades suele ser de lo más complejo (y por ello utilizamos herramientas de gran escala como Identity Server para centralizarlo). Administrar esos accesos desde cero y en solitario toma muchísimo tiempo, así que tiene mucho mérito que hayas logrado culminar tu proyecto y presentarlo a tiempo. ¡Muy buen análisis crítico!

Saludos cordiales,

**Max Anderson Benites Corazón**
*Senior Technical Implementation Specialist | NCR VOYIX*
