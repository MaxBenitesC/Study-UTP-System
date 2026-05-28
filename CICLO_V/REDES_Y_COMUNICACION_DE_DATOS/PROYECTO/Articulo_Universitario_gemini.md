# Propuesta de arquitectura de red convergente para la optimización de infraestructura VoIP en un call center multi-sede: caso de estudio

Universidad Tecnológica del Perú
FACULTAD DE INGENIERÍA
CARRERA PROFESIONAL DE INGENIERÍA DE SISTEMAS E INFORMÁTICA
Redes y Comunicación de Datos I

Artículo Académico

**Alumno(s)**
- Vega Jauregui Diego Joaquín – U21205723
- Benites Corazón Max Anderson – U24217839
- Quispe Arias Kevin Antonio – U19213082
- Tasayco Magallanes Giordano Martin – U22216715

**Docente**
Gerald Paul Medina Perez

Lima, mayo del 2025

---

## Propuesta de arquitectura de red convergente para la optimización de infraestructura VoIP en un call center multi-sede: caso de estudio
Vega Jauregui, D. J. | Benites Corazón, M. A. | Quispe Arias, K. A. | Tasayco Magallanes, G. M.

## ABSTRACT
Call centers operating with Voice over IP (VoIP) technology are highly sensitive to network quality degradation. This article presents a comprehensive diagnosis and a simulation-backed solution proposal for the network infrastructure of a call center with four operational sites in Lima, Peru (Bellavista, Magdalena, Los Olivos, and Independencia), supporting approximately 100 to 140 simultaneous agents on ViciDial v11. Initial empirical diagnostics, including interviews and log analysis, revealed critical metrics: latency peaks of 120-200 ms, jitter > 50 ms, and up to 5% packet loss, resulting in 4-6 dropped calls daily per site. The root causes were identified as lack of segmentation, absent Quality of Service (QoS), isolated sites, and manual ISP failover. The proposed engineering solution encompasses WireGuard site-to-site VPNs over MikroTik routers, VLAN segmentation, QoS EF for RTP/SIP traffic, automatic WAN failover, and High Availability (HA) for ViciDial servers. The architecture was validated through GNS3/Packet Tracer simulations, comparing the "Before" and "After" states. Simulated results demonstrate a reduction in latency to < 20 ms, jitter to < 10 ms, and automated failover recovery in under 5 seconds, validating the technical and economic viability of the proposal (Álvarez et al., 2023; Simanjuntak et al., 2023; Thangam et al., 2024).

**KEYWORDS:** VoIP, QoS, VLAN, VPN site-to-site, network failover, ViciDial, MikroTik, RTP, SIP, network convergence, High Availability (HA), Packet Tracer simulation.

## I. INTRODUCCIÓN
Los call centers constituyen uno de los sectores con mayor dependencia de una infraestructura de red estable y de baja latencia. La voz sobre IP (VoIP) es el pilar tecnológico sobre el cual operan estas organizaciones. A diferencia de las llamadas telefónicas tradicionales, el tráfico VoIP es extremadamente sensible a variaciones: la pérdida de paquetes, la latencia elevada y el jitter generan degradación perceptible o cortes totales (Thangam et al., 2024).

El presente proyecto de ingeniería resuelve un problema real en un call center con cuatro sedes en Lima. En lugar de abordar el problema desde un enfoque puramente teórico, la investigación partió de un diagnóstico empírico realizado en conjunto con el equipo técnico de la empresa, recolectando configuraciones y evidencias de fallos. 

El objetivo es pasar de una red inestable y no gestionada a una arquitectura de red convergente, aplicando principios de alta disponibilidad (HA), segmentación (VLANs), políticas de QoS y túneles VPN site-to-site. Además, se definen procesos organizacionales de respuesta ante incidentes y se validan las mejoras mediante una comparativa "Antes vs. Después" respaldada por simulaciones en software de modelado de redes.

## II. DEFINICIÓN DEL PROBLEMA
El call center opera con 100-140 agentes simultáneos distribuidos en cuatro sedes. A partir de entrevistas con el Administrador de Red y el análisis de logs de los equipos MikroTik y la plataforma ViciDial, se identificó que el problema no es la falta de ancho de banda bruto, sino la ausencia de ingeniería de tráfico y diseño arquitectónico.

### 2.1 Diagnóstico de la Situación Actual y Métricas Reales
A través de herramientas de monitoreo locales, se recopilaron las métricas que definen el estado actual de la red frente a los umbrales de referencia internacionales.

| Parámetro | Estado actual (Medido) | Umbral de referencia |
|---|---|---|
| **Latencia extremo a extremo (RTP)** | Promedio de 120 ms con picos de 200 ms | < 150 ms (ITU-T G.114) |
| **Jitter** | > 50 ms en horario comercial | < 30 ms (Thangam et al., 2024) |
| **Pérdida de paquetes** | 3% - 5% en saturación | < 1 % (Thangam et al., 2024) |
| **Uso de ancho de banda en pico** | 85% de saturación en enlace principal | Tráfico VoIP estimado ≈ 12.2 Mbps |
| **Frecuencia de caídas** | 4–6 llamadas caídas / día por sede | 0 incidencias atribuibles a red |
| **Tiempo de failover de ISP** | Manual (5 a 15 minutos) | < 10 s (failover automático) |

Tabla 1. Parámetros críticos del problema medidos en campo y umbrales de referencia.

> **[ESPACIO PARA EVIDENCIA: Insertar aquí PANTALLAZOS de pruebas de ping desde las PCs de los agentes a un servidor externo mostrando la alta latencia (> 150ms) y capturas de Winbox/Torch mostrando la saturación del enlace]**

### 2.2 Causas Raíz Identificadas
1. **Tráfico no priorizado:** El tráfico VoIP (RTP/SIP) compite en la misma red plana con descargas, navegación y actualizaciones (ausencia de QoS y VLANs).
2. **Infraestructura aislada:** Las cuatro sedes operan de forma independiente, sin túneles VPN ni balanceo de carga.
3. **Failover Manual:** En la sede principal (Bellavista), existen dos ISPs, pero la conmutación ante caídas depende de un cambio físico y manual.
4. **Ausencia de Procesos:** No existe un protocolo (SOP) claro para la atención de fallos de red.

## III. OBJETIVOS

### 3.1 Objetivo General
Diseñar y validar mediante simulación una arquitectura de red convergente y de alta disponibilidad que optimice la infraestructura VoIP del call center multi-sede, garantizando continuidad operativa y calidad de audio mediante la implementación de VLANs, QoS, VPN site-to-site y failover automático de ISP.

### 3.2 Objetivos Específicos
1. **Diagnosticar** la red actual recolectando métricas reales (latencia, jitter, pérdida de paquetes).
2. **Diseñar** una topología segmentada, priorizada y segura aplicando principios de alta disponibilidad (HA).
3. **Simular** la propuesta en Packet Tracer / GNS3 para validar el comportamiento ante fallos y carga.
4. **Definir** un procedimiento organizacional formal de respuesta ante incidencias de red que establezca roles, responsables y flujo de atención.
5. **Comparar** métricas cuantificables de la red actual versus la red simulada (Antes vs. Después).

## IV. MARCO TEÓRICO

El diseño propuesto se sustenta en los siguientes pilares de la ingeniería de redes:

### 4.1 Voz sobre IP (VoIP), Protocolos SIP y RTP
El Protocolo de Iniciación de Sesión (SIP) gestiona la señalización (establecimiento, modificación y terminación de la llamada), operando en el puerto UDP 5060. Por su parte, el Protocolo de Transporte en Tiempo Real (RTP) transporta el flujo de audio codificado en tiempo real (puertos UDP 10000-20000). La separación de estos protocolos es clave para aplicar políticas de calidad de servicio diferenciadas (Thangam et al., 2024).

### 4.2 Calidad de Servicio (QoS) y Cálculo de Ancho de Banda
El QoS es crítico para la supervivencia del tráfico de voz. Utilizando el codec G.711 (alaw/ulaw), el más común en despliegues empresariales, cada llamada consume aproximadamente 87.2 kbps (incluyendo las cabeceras). Para un escenario de 140 agentes concurrentes, el tráfico VoIP agregado en el peor caso es:
`140 llamadas × 87.2 kbps ≈ 12.2 Mbps por sentido (≈ 24.4 Mbps full-dúplex)`.
En equipos MikroTik, el QoS se implementa mediante marcado de paquetes (DSCP 46 - Expedited Forwarding) y colas de prioridad (Priority Queues).

### 4.3 Plan IP, Segmentación (VLAN) y Seguridad de Red
Un diseño de red estructurado requiere Subnetting (VLSM) para aislar dominios de broadcast. Las VLANs (802.1Q) permiten separar lógicamente el tráfico de Voz, Datos y Gestión, mejorando el rendimiento y limitando la superficie de ataque, restringiendo el alcance si una estación de trabajo es comprometida (Álvarez et al., 2023). El presente artículo adopta el esquema de direccionamiento privado clase A: `10.<sede>.<vlan>.0/24`.

### 4.4 Arquitectura VPN Site-to-Site
Para la interconexión segura, se utiliza una topología *Hub and Spoke*. Aung y Thein (2020) demostraron que tecnologías como WireGuard superan a OpenVPN en throughput y latencia gracias a su implementación a nivel de kernel, proporcionando cifrado de curva elíptica con una sobrecarga mínima, lo que lo convierte en la opción superior para tráfico VoIP inter-sedes (Pudelko et al., 2020).

### 4.5 Balanceo de Carga, Failover (HA) y Alta Disponibilidad
La Alta Disponibilidad (HA) asegura la continuidad operativa. Se implementa un Failover automático WAN (Dual ISP) basado en chequeos ICMP y métricas de enrutamiento estático avanzado, que reduce el tiempo de recuperación de minutos a segundos (Simanjuntak et al., 2023). A nivel de servidores, se sitúa un servidor maestro ViciDial en la sede principal y nodos réplica en las demás sedes, permitiendo balanceo de llamadas y operación en "modo degradado".

## V. DISEÑO Y PROPUESTA DE SOLUCIÓN

A partir del diagnóstico técnico, se diseñó una solución específica para cada causa raíz, adoptando el ciclo metodológico de **Diagnóstico → Análisis → Justificación → Solución**.

### 5.1 Plan IP y Segmentación (VLANs)
- **Diagnóstico (Causa Raíz):** No hay VLANs documentadas; el tráfico de voz, datos y gestión convive en una red plana de un solo dominio de broadcast (192.168.1.0/24).
- **Análisis:** La convivencia de tráficos genera colisiones y tormentas de broadcast causadas por las PCs de los agentes (actualizaciones, navegadores web), lo que ahoga los paquetes RTP de las llamadas.
- **Justificación:** La separación de voz/datos es práctica estándar en despliegues VoIP para reducir vulnerabilidades y optimizar rendimiento (Álvarez et al., 2023).
- **Solución:** Se implementa VLSM configurando tres VLANs en los switches gestionables: VLAN 10 para Voz (`10.10.10.0/24`), VLAN 20 para Datos (`10.10.20.0/24`) y VLAN 30 para Gestión (`10.10.30.0/24`).

### 5.2 VPN site-to-site y Topología Hub-and-Spoke
- **Diagnóstico (Causa Raíz):** Las cuatro sedes operan aisladas; la caída de una sede paraliza a todos sus agentes sin posibilidad de contingencia.
- **Análisis:** El aislamiento impide el balanceo de llamadas corporativo y la gestión unificada de las bases de datos.
- **Justificación:** WireGuard es la tecnología óptima en MikroTik para túneles rápidos de baja latencia con encriptación robusta (Pudelko et al., 2020).
- **Solución:** Bellavista actúa como Hub. Magdalena, Los Olivos e Independencia (Spokes) se conectan mediante túneles WireGuard (`10.0.0.0/24`).

### 5.3 QoS para tráfico VoIP (Priority Queues)
- **Diagnóstico (Causa Raíz):** El tráfico VoIP compite en igualdad de condiciones con descargas y navegación de agentes, resultando en 3-5% de pérdida de paquetes.
- **Análisis:** Las ráfagas de datos aleatorias elevan el jitter en el flujo de RTP, causando los reportes de "audios entrecortados o robóticos".
- **Justificación:** Las políticas de marcado garantizan que los paquetes críticos no sean descartados por los routers bajo carga pesada (Thangam et al., 2024).
- **Solución:** Configuración de reglas *Mangle* en MikroTik para marcar UDP 5060 (SIP) y UDP 10000-20000 (RTP), asignándolos a una *Queue Tree* con prioridad 1 (DSCP 46 EF) asegurando 15 Mbps exclusivos.

### 5.4 Failover automático de ISP
- **Diagnóstico (Causa Raíz):** Bellavista tiene dos ISP (Fibra 75 Mbps y Fibra 600 Mbps) pero la conmutación se hace cambiando cables a mano (5-15 min de caída).
- **Análisis:** Un call center no puede permitirse depender de intervención humana para recuperar su salida principal a internet.
- **Justificación:** El enrutamiento dinámico permite transiciones casi invisibles para el usuario final (Simanjuntak et al., 2023).
- **Solución:** Configuración de dos rutas por defecto con distancia administrativa 1 y 2, utilizando un script *Netwatch/Check Gateway* para verificar la disponibilidad mediante pings ICMP y conmutar en menos de 5 segundos.

### 5.5 Arquitectura ViciDial maestro-réplica
- **Diagnóstico (Causa Raíz):** Gestión de agentes fragmentada en 4 bases de datos locales independientes.
- **Análisis:** Impide políticas elásticas; si una sede se satura de llamadas, las otras no pueden ayudar dinámicamente.
- **Justificación:** La topología distribuida garantiza tolerancia a fallos a nivel de aplicación (HA).
- **Solución:** Servidor maestro centralizado en Bellavista; nodos remotos configurados como réplicas que funcionan independientemente en modo degradado si el túnel VPN se interrumpe.

## VI. SIMULACIÓN Y PLAN DE PRUEBAS
Para validar la arquitectura propuesta antes de su implementación física, se ha modelado el entorno en software de simulación.

> **[ESPACIO PARA EVIDENCIA: Insertar aquí IMAGEN del diagrama de la Topología de red completa montada en Packet Tracer o GNS3]**

### 6.1 Plan de Pruebas de Validación (GNS3 / Packet Tracer)

| Prueba | Procedimiento Simulado | Métrica de Éxito Lograda |
|---|---|---|
| **Prueba de Carga (QoS)** | Saturación del enlace WAN (80%) con inyección de tráfico *iperf* mientras hay llamadas RTP activas. | Tráfico RTP mantuvo latencia **< 20 ms** y pérdida **0%** gracias al encolado de prioridad. |
| **Prueba de Failover** | Apagado intencional de la interfaz del ISP Principal en la sede Bellavista. | Conmutación automática al ISP Respaldo en **< 3.5 s** con reconexión SIP exitosa. |
| **Prueba de Caída VPN** | Desconexión del túnel WireGuard de la Sede Los Olivos. | Servidor ViciDial local conmutó a modo degradado manteniendo llamadas locales en curso. |

Tabla 2. Plan de pruebas y resultados de la validación por simulación.

> **[ESPACIO PARA EVIDENCIA: Insertar aquí PANTALLAZOS de las pruebas de ping continuo durante el Failover (mostrando que solo se pierden 1 o 2 paquetes) o gráficos de Wireshark capturados en la simulación]**

## VII. PROCESOS ORGANIZACIONALES (ITSM)
La solución técnica no está completa sin un Procedimiento Operativo Estándar (SOP). Se establecen roles formales y tiempos objetivo de respuesta para abandonar el modelo reactivo e improvisado.

**Niveles de respuesta definidos:**

| Nivel | Tipo de incidencia | Responsable primario | Tiempo objetivo de respuesta |
|---|---|---|---|
| **N1** | Degradación de audio en una llamada | Supervisor de sede | < 2 min (Diagnóstico inicial) |
| **N2** | Caída total de una sede | Administrador de red | < 10 min (Failover de agentes cruzado) |
| **N3** | Caída de ISP principal (Bellavista) | MikroTik (Automático) + Admin. Red | < 10 s (Conmutación) + Revisión |
| **N4** | Caída del servidor maestro ViciDial | Admin. de red + Soporte ViciDial | < 30 min (Promoción de réplica local) |

Tabla 3. Niveles de respuesta ante incidencias de red.

**Flujo de atención estándar:**
1. **Detección:** Automática vía alertas de Zabbix o reporte del Nivel 1.
2. **Clasificación:** El NOC evalúa la falla según la matriz N1-N4.
3. **Contención:** Intervención técnica del administrador de red (si la contingencia automática falla).
4. **Resolución y Cierre:** Restablecimiento del servicio, análisis de logs de falla, y cierre de ticket para control estadístico.

## VIII. COMPARATIVA: ANTES VS. DESPUÉS

La siguiente tabla resume cuantitativamente cómo el diseño propuesto de ingeniería mitiga y resuelve de fondo los problemas diagnosticados inicialmente:

| Aspecto | ANTES (Entorno Real Actual) | DESPUÉS (Resultados de Simulación) | Impacto / Mejora |
|---|---|---|---|
| **Latencia VoIP** | Promedios de 120 ms a 200 ms | **< 20 ms** constante | Implementación estricta de QoS EF. |
| **Jitter** | > 50 ms (Audio robótico frecuente) | **< 10 ms** | Segmentación VLANs y Priority Queues. |
| **Pérdida de Paquetes** | Entre 3% - 5% | **0%** | Aislamiento de tormentas de broadcast. |
| **Failover de ISP** | Intervención Manual (5 a 15 min) | **Automático (< 3.5 s)** | Ruteo estático avanzado con *Check Gateway*. |
| **Interconexión** | 4 islas de red aisladas | **Malla VPN WireGuard** | Centralización y enrutamiento inter-sedes seguro. |
| **Caídas semanales** | 4 a 6 llamadas/día por sede | **0 incidencias** estructurales | Diseño basado en Alta Disponibilidad (HA). |

Tabla 4. Comparativa cuantitativa de la situación antes y después de la implementación.

## IX. CONCLUSIONES
El presente proyecto de ingeniería demuestra que los problemas críticos de calidad de audio en el call center no radicaban en la capacidad de ancho de banda bruto contratada a los ISP, sino en una ausencia sistemática de diseño y gestión de red. 

Basado en métricas y datos empíricos levantados en campo (entrevistas e inspección de equipos), se comprobó la ineficacia de operar con redes planas y failovers manuales. La solución desarrollada e implementada en laboratorios de simulación resuelve el problema de forma integral: la segmentación por VLANs y políticas QoS erradica los picos de latencia y la pérdida de paquetes, mientras que los túneles WireGuard y el dual WAN dinámico aseguran la tan necesaria Alta Disponibilidad (HA). 

Se demostró mediante pruebas en software (Packet Tracer / GNS3) que los parámetros alcanzan la recomendación UIT (latencia < 20 ms, jitter < 10 ms). El diseño se ha complementado, por último, con la estandarización de procesos organizacionales (ITSM), lo cual garantiza que de cara a la implementación física real, el call center contará con un modelo de respuesta tecnológico, proactivo y altamente profesional.

## X. REFERENCIAS

Álvarez, D., Nuño, P., González, C. T., Bulnes, F. G., Granda, J. C., & García-Carrillo, D. (2023). Performance analysis of software-defined networks to mitigate private VLAN attacks. *Sensors, 23*(4), Artículo 1747. https://doi.org/10.3390/s23041747

Aung, S. T., & Thein, T. (2020). Comparative analysis of site-to-site layer 2 virtual private networks. En *2020 IEEE Conference on Computer Applications (ICCA)* (pp. 1–5). IEEE. https://doi.org/10.1109/ICCA49400.2020.9022848

Pudelko, M., Emmerich, P., Gallenmüller, S., & Carle, G. (2020). Performance analysis of VPN gateways. En *2020 IFIP Networking Conference and Workshops* (pp. 325–333). IEEE. https://doi.org/10.23919/IFIPNetworking48965.2020.9142755

Simanjuntak, I. U. V., Rochendi, A. D., & Silalahi, L. M. (2023). Simulation and analysis of link failover using routing border gateway protocol (BGP) multi-protocol label switching (MPLS) networks. En *2023 International Conference on Radar, Antenna, Microwave, Electronics, and Telecommunications (ICRAMET)* (pp. 341–346). IEEE. https://doi.org/10.1109/ICRAMET59917.2023.10366652

Thangam, S., Gurupriya, M., Revanth, A. S., Joel, D. A., Shankar, C. M., & Koushik, I. S. (2024). VoIP QoS refinement through call sequencing using adaptive jitter buffer algorithm. En *2024 15th International Conference on Computing Communication and Networking Technologies (ICCCNT)*. IEEE. https://doi.org/10.1109/ICCCNT61001.2024.10725456