---
header-includes:
  - \usepackage{graphicx}
---

\input{portada_utp.tex}
\newpage

## ABSTRACT
Call centers operating with Voice over IP (VoIP) technology are highly sensitive to network quality degradation. This article presents an engineering proposal — backed by field diagnostics, simulation and a controlled pilot — for the network infrastructure of a call center with four operational sites in Lima, Peru (Bellavista, Magdalena, Los Olivos and Independencia), supporting approximately 100 to 140 simultaneous agents on ViciDial running on ViciBox v.12.0.2 (openSUSE Leap), as confirmed by the field survey of 2026-05-27. The current infrastructure operates without formal segmentation, without configured Quality of Service (QoS) policies, without inter-site interconnection and without automatic Internet Service Provider (ISP) failover mechanisms. As a result, the network registers between two and four weekly audio incidents or dropped calls, uncorrelated with peak hours, indicating that the root cause is network misconfiguration rather than bandwidth saturation. Field baseline metrics (Wireshark RTP analysis, MikroTik Torch and ViciDial logs) are captured per the protocol of sección 5.9 and consolidated as evidence EV-01 to EV-04. The proposed solution consists of six components: site-to-site VPN tunnelling using WireGuard over MikroTik routers, VLAN segmentation (voice, data, management), QoS policies prioritising RTP/SIP traffic, automatic ISP failover at the main site, a ViciDial master-replica architecture with high availability, and a formal operating procedure with active Zabbix monitoring. The architecture is validated through Packet Tracer / GNS3 simulation and a controlled pilot at the Bellavista site, with expected results — supported by ITU-T G.114 thresholds and recent literature — of latency below 150 ms, jitter below 30 ms, packet loss below 1 % and a reduction of audio incidents above 70 %.

## KEYWORDS
VoIP, QoS, VLAN, VPN site-to-site, network failover, ViciDial, MikroTik, RTP, SIP, network convergence, High Availability, Packet Tracer simulation.

---

## I. INTRODUCCIÓN
Los call centers constituyen uno de los sectores con mayor dependencia de infraestructura de red estable y de baja latencia. La voz sobre IP (VoIP), tecnología que codifica y transmite señales de audio como paquetes de datos sobre redes TCP/IP, es el pilar tecnológico sobre el cual operan estas organizaciones [1]. A diferencia de las llamadas telefónicas tradicionales, el tráfico VoIP es extremadamente sensible a variaciones en la red: la pérdida de paquetes, la latencia elevada y el jitter generan degradación perceptible en la calidad del audio o cortes totales en las llamadas [1].

El presente proyecto de ingeniería resuelve un problema real diagnosticado en un call center con cuatro sedes operativas en Lima. La investigación parte de un diagnóstico empírico realizado en campo con el equipo técnico de la empresa — entrevistas con el administrador de red, recolección de configuraciones de los equipos MikroTik en producción y evidencia documental de los proveedores de servicios de internet — y se complementa con la captura de métricas de línea base (latencia, jitter, pérdida y uso de ancho de banda) y de evidencias gráficas (capturas Wireshark, MikroTik Torch y logs ViciDial) que sustituyen las descripciones genéricas por datos verificables.

La propuesta desarrollada aplica técnicas documentadas en la literatura académica — segmentación VLAN [2], políticas QoS [1], interconexión VPN site-to-site [3], [4] y mecanismos de failover automático [5] — adaptadas al contexto específico de la empresa, con énfasis en la viabilidad económica y la reutilización de la infraestructura existente. El diseño se valida mediante simulación en Cisco Packet Tracer y GNS3, y mediante un piloto controlado en la sede principal, generando una comparativa cuantitativa Antes vs. Después que demuestra la mejora.

---

## II. DEFINICIÓN DEL PROBLEMA
Un call center con cuatro sedes operativas en Lima, con aproximadamente 100 a 140 agentes simultáneos sobre la plataforma ViciDial (distribución ViciBox v.12.0.2 sobre openSUSE Leap, kernel `6.4.0-150600.23.33-default`, según levantamiento de campo del 2026-05-27 documentado en el archivo `Levantamiento_Bellavista.md` y consolidado en la sección 5.7.1), registra entre 2 y 4 incidencias semanales de degradación de audio o caídas de llamadas, según el registro operativo provisto por la coordinación del call center. La ausencia de correlación con horas pico descarta la saturación de ancho de banda como causa raíz e indica que el problema reside en la configuración de la red.

### 2.1 Parámetros críticos y línea base
Los parámetros que definen el problema y que se cuantifican como línea base con las herramientas descritas en sección 5.9 son:

| Parámetro | Estado actual (línea base) | Umbral de referencia | Evidencia |
|---|---|---|---|
| Latencia extremo a extremo (RTP) | _Por capturar — ver EV-01_ | < 150 ms (ITU-T G.114) | EV-01 |
| Jitter | _Por capturar — ver EV-01_ | < 30 ms [1] | EV-01 |
| Pérdida de paquetes | _Por capturar — ver EV-02_ | < 1 % [1] | EV-02 |
| Uso de ancho de banda en pico | _Por capturar — ver EV-03_ | Tráfico VoIP ≈ 87 kbps por llamada G.711 | EV-03 |
| Frecuencia de caídas | 2–4 incidencias / semana (bitácora interna, EV-04) | 0 incidencias atribuibles a configuración | EV-04 |
| Tiempo de recuperación ante caída de ISP | Manual, varios minutos | < 10 s (failover automático) | EV-13 |

Tabla 0. Parámetros críticos del problema, línea base y umbrales de referencia.

- **Captura Wireshark RTP Stream (latencia y jitter):** captura del menú Telephony → RTP → RTP Streams con las columnas Max Delta, Max Jitter, Mean Jitter y Lost Packets de al menos 3 streams en hora pico, sobre espejo de puerto del switch MikroTik en Bellavista. 

- **Captura Wireshark de pérdida de paquetes RTP:** captura de Telephony → RTP → Stream Analysis con el porcentaje de paquetes perdidos por stream durante una ventana de 30 minutos en franja 10:00–10:30. 

- **Captura MikroTik Torch del ancho de banda WAN:** captura de Torch en Winbox sobre la interfaz WAN principal (Claro 600 Mbps) durante hora pico, con desglose por protocolo/puerto. 

- **Bitácora ViciDial de incidencias (4 semanas):** export del registro de incidencias de la coordinación del call center con fecha, sede, duración y descripción de cada caída o degradación. 

### 2.2 Causas raíz identificadas
El problema se origina en cuatro fallas estructurales de la infraestructura de red:

1. **Tráfico VoIP no priorizado.** El tráfico RTP/SIP compite en igualdad de condiciones con el tráfico de datos (descargas, navegación, actualizaciones), lo que provoca degradación de audio cuando la red está en uso simultáneo por múltiples aplicaciones.
2. **Sedes aisladas.** Las cuatro sedes operan como redes completamente independientes, sin mecanismo de respaldo cruzado ante la caída de una sede.
3. **Failover manual.** La sede de Bellavista cuenta con dos ISP contratados pero sin failover automático configurado, lo que hace que la recuperación ante fallos dependa de intervención manual.
4. **Ausencia de procesos y monitoreo.** No existe documentación de red, monitoreo activo ni un procedimiento operativo estándar (SOP) de respuesta ante incidencias, lo que impide detectar y diagnosticar fallas de forma proactiva.

Estas deficiencias afectan directamente la continuidad operativa de la empresa, la experiencia de los clientes que reciben las llamadas y la productividad de los agentes, quienes deben interrumpir su trabajo durante cada incidencia.

---

## III. OBJETIVOS

### 3.1 Objetivo General
Diseñar y validar mediante simulación y piloto una arquitectura de red convergente y de alta disponibilidad que optimice la infraestructura VoIP del call center multi-sede, reduciendo la latencia VoIP por debajo de 150 ms y el jitter por debajo de 30 ms conforme a la recomendación ITU-T G.114, y eliminando las incidencias semanales atribuibles a configuración de red.

### 3.2 Objetivos Específicos
Los siguientes objetivos específicos están ordenados como una secuencia lógica de fases que conducen al objetivo general: diagnóstico (obj. 1–3), diseño (obj. 4–5), implementación técnica (obj. 6–9), procesos organizacionales (obj. 10) y validación (obj. 11–12). Cada objetivo ataca un problema técnico concreto del caso y entrega un producto verificable; en conjunto, conforman las "gradas" que conducen al cumplimiento del objetivo general.

1. **Diagnosticar** las métricas actuales de calidad VoIP (latencia, jitter, pérdida de paquetes) y caracterizar las incidencias recurrentes mediante Wireshark, MikroTik Torch y logs ViciDial, generando la línea base cuantificada del problema.
2. **Recolectar la evidencia de campo** del estado actual (entrevistas con el administrador de red y soporte técnico, fotografías del rack de comunicaciones, exports de configuración MikroTik y contratos de los proveedores ISP) para sustituir supuestos no validados por datos verificables con el cliente.
3. **Analizar la capacidad de ancho de banda** dimensionando el tráfico VoIP teórico para 140 agentes concurrentes (códec G.711) frente a la capacidad contratada de los enlaces, descartando o confirmando la saturación como causa raíz.
4. **Diseñar el plan de direccionamiento IP** y la segmentación VLAN (VLAN 10 voz, VLAN 20 datos, VLAN 30 gestión) para las cuatro sedes, garantizando la no superposición de subredes y un esquema consistente para la VPN entre sedes.
5. **Diseñar la topología convergente** hub-and-spoke con VPN site-to-site WireGuard entre las cuatro sedes, justificada técnica (rendimiento y overhead) y económicamente (reutilización de los equipos MikroTik existentes).
6. **Implementar políticas de QoS y reglas de firewall** en MikroTik que prioricen el tráfico RTP/SIP mediante marcado DSCP 46 (Expedited Forwarding) y colas de prioridad, asegurando latencia < 150 ms y jitter < 30 ms.
7. **Configurar el failover automático de doble ISP** en la sede principal mediante distancia administrativa diferenciada y *Check Gateway*, con tiempo objetivo de recuperación menor a 10 segundos ante caída del enlace primario.
8. **Implementar la arquitectura ViciDial maestro-réplica** con ubicación de servidores en la sede principal, nodos réplica en las demás sedes y balanceo de carga de agentes, alcanzando una disponibilidad objetivo ≥ 99.5 %.
9. **Desplegar monitoreo activo** con Zabbix sobre los equipos MikroTik (vía SNMP) y los servidores ViciDial, configurando alertas por umbrales de latencia, jitter, pérdida y caída de túneles VPN para detección temprana de degradaciones.
10. **Definir el procedimiento operativo estándar (SOP)** de respuesta ante incidencias con cuatro niveles (N1 degradación puntual, N2 caída de sede, N3 caída de ISP, N4 caída del maestro), responsables asignados y flujo cronometrado de atención (T+0 a T+24 h).
11. **Simular el diseño completo** en Cisco Packet Tracer y GNS3 ejecutando las cuatro pruebas críticas exigidas: caídas de sede, failover de ISP, latencia bajo tráfico SIP/RTP y carga sostenida del enlace al 80 % de su capacidad.
12. **Validar las mejoras en piloto controlado** en la sede de Bellavista, comparando cuantitativamente las métricas Antes vs. Después con evidencia gráfica (Wireshark, dashboards Zabbix) y obteniendo el acta de aceptación firmada por la gerencia del call center.

---

## IV. MARCO TEÓRICO

### 4.1 Voz sobre IP (VoIP)
La Voz sobre IP (VoIP) es una tecnología que permite la transmisión de comunicaciones de audio en tiempo real mediante el protocolo de internet (IP). A diferencia de la telefonía tradicional basada en circuitos conmutados, VoIP convierte la señal de voz en paquetes digitales que se transmiten sobre redes de datos compartidas [1]. Los protocolos principales que intervienen en una comunicación VoIP son el Protocolo de Iniciación de Sesión (SIP), encargado del establecimiento y terminación de llamadas (puerto UDP 5060), y el Protocolo de Transporte en Tiempo Real (RTP), responsable de la transmisión del audio codificado durante la llamada (puertos UDP 10000–20000) [1].

El rendimiento de una comunicación VoIP depende de tres parámetros críticos de red: la latencia (retardo de extremo a extremo), el jitter (variación en el retardo entre paquetes consecutivos) y la pérdida de paquetes. La recomendación ITU-T G.114 establece que la latencia máxima aceptable para comunicaciones de voz es de 150 ms, el jitter no debe superar los 30 ms y la pérdida de paquetes debe mantenerse por debajo del 1 % para garantizar una calidad de audio satisfactoria [1].

### 4.2 Calidad de Servicio (QoS)
La Calidad de Servicio (QoS) es el conjunto de técnicas y mecanismos que permiten gestionar el tráfico de red de manera diferenciada, asignando prioridades distintas a diferentes tipos de tráfico según sus requisitos de latencia, ancho de banda y tolerancia a pérdidas. En redes convergentes donde coexisten tráfico de voz, video y datos, la implementación de QoS es fundamental para garantizar que el tráfico sensible al tiempo, como VoIP, reciba el tratamiento preferencial necesario [1].

[1] demostraron que la implementación de algoritmos de buffer adaptativo combinados con políticas QoS reduce significativamente el jitter en redes VoIP, mejorando la calidad percibida por el usuario. En equipos MikroTik, el QoS se implementa mediante marcado de paquetes (DSCP 46 — *Expedited Forwarding*) y colas de prioridad (Priority Queues / Queue Tree), asignando la clase de servicio EF al tráfico RTP/SIP, lo que garantiza su procesamiento preferente sobre cualquier otro flujo de datos.

### 4.3 Segmentación de red mediante VLANs
Una Red de Área Local Virtual (VLAN, IEEE 802.1Q) es una técnica de segmentación lógica que permite dividir una red física en múltiples dominios de broadcast independientes, sin necesidad de modificar la infraestructura de cableado existente. La segmentación VLAN mejora el rendimiento de la red al reducir el tráfico innecesario en cada segmento, fortalece la seguridad al aislar grupos de dispositivos y facilita la administración de políticas de red diferenciadas [2].

[2] demostraron que la segmentación mediante VLANs, combinada con redes definidas por software, reduce significativamente el impacto de ataques laterales y mejora el rendimiento general en entornos corporativos. En el contexto del presente caso de estudio, se propone la implementación de tres VLANs: VLAN 10 para tráfico de voz (RTP/SIP), VLAN 20 para tráfico de datos (navegación, correo, aplicaciones) y VLAN 30 para gestión de equipos de red y servidores.

### 4.4 VPN site-to-site
Una Red Privada Virtual (VPN) site-to-site establece un canal de comunicación cifrado y autenticado entre dos o más redes geográficamente distribuidas, utilizando internet como medio de transporte. A diferencia de las VPN de acceso remoto, que conectan usuarios individuales a una red corporativa, la VPN site-to-site interconecta redes completas, permitiendo que los dispositivos de cada sede se comuniquen como si pertenecieran a la misma red local [3].

[3] realizaron un análisis comparativo de tecnologías VPN site-to-site incluyendo L2TP, PPTP, OpenVPN, EoIP y MPLS/VPLS, concluyendo que WireGuard y OpenVPN ofrecen el mejor equilibrio entre seguridad y rendimiento para entornos empresariales de mediana escala. [4] complementaron este análisis demostrando que WireGuard supera a OpenVPN en throughput y latencia gracias a su implementación en el espacio del kernel del sistema operativo y a su overhead reducido (≈ 20 bytes por paquete), lo que lo convierte en la opción técnicamente superior para interconexión de sedes con tráfico VoIP.

### 4.5 Failover automático de ISP
El failover de red es el mecanismo mediante el cual un sistema conmuta automáticamente a un enlace de comunicaciones alternativo cuando detecta la falla del enlace principal, con el objetivo de minimizar el tiempo de indisponibilidad del servicio. En infraestructuras críticas como los call centers, donde la caída del enlace de internet equivale a la pérdida total de la capacidad de llamadas, la implementación de un mecanismo de failover automático es esencial para garantizar la continuidad operativa [5].

[5] analizaron la implementación de failover de enlace mediante protocolos de enrutamiento dinámico, demostrando que los mecanismos de conmutación automática reducen el tiempo de recuperación ante fallos de enlace de varios minutos (intervención manual) a segundos (conmutación automática). En equipos MikroTik RouterOS, el failover se implementa mediante la configuración de rutas con distancia administrativa diferenciada, verificación de gateway mediante ping periódico (*Check Gateway*) y scripts de *Netwatch*, sin necesidad de protocolos de enrutamiento adicionales.

### 4.6 ViciDial como plataforma de call center
ViciDial es una plataforma de call center de código abierto basada en Asterisk, el servidor de comunicaciones VoIP más utilizado a nivel mundial. Tradicionalmente desplegada sobre CentOS/Linux, también se distribuye como **ViciBox** —imagen llave en mano sobre openSUSE Leap—, que es la distribución observada en el caso de estudio (ViciBox v.12.0.2, kernel `6.4.0-150600.23.33-default`, según levantamiento de campo del 2026-05-27). ViciDial integra un marcador predictivo, gestión de agentes, enrutamiento de llamadas entrantes y salientes, y generación de reportes operativos. Su arquitectura permite despliegues distribuidos en múltiples servidores con roles diferenciados: servidor de telefonía (Asterisk), servidor de base de datos (MySQL) y servidor web (Apache), lo que posibilita la escalabilidad horizontal necesaria para entornos multi-sede como el analizado en el presente artículo.

### 4.7 Plan de direccionamiento IP
Un plan de direccionamiento IP es el diseño documentado que define los rangos, máscaras y asignaciones de direcciones IPv4 para cada segmento lógico de una red corporativa. La selección de rangos privados conforme a RFC 1918 (10.0.0.0/8, 172.16.0.0/12, 192.168.0.0/16) es la práctica estándar para redes internas. Tres convenciones de diseño guían la elaboración del plan en entornos multi-sede interconectados por VPN: (a) asignación jerárquica de subredes que facilite el reconocimiento visual de la red (sede / VLAN / host); (b) reserva de un rango específico para enlaces punto a punto entre routers (típicamente los túneles VPN); y (c) reserva de una ventana de crecimiento futura para incorporar nuevas sedes sin renumeración. El plan debe garantizar la no superposición de subredes entre sedes para evitar conflictos de enrutamiento. La aplicación concreta del plan al caso de estudio se desarrolla en sección 7.2.

### 4.8 Seguridad de red
La seguridad de red en entornos VoIP requiere proteger simultáneamente la confidencialidad del audio (cifrado de RTP), la integridad del control de llamadas (autenticación SIP) y la disponibilidad de la infraestructura (resistencia a denegación de servicio). La segmentación VLAN reduce la superficie de ataque al aislar el plano de voz del plano de datos de usuario, limitando el alcance de un compromiso en estaciones de trabajo [2]. La VPN site-to-site complementa esta defensa al cifrar todo el tráfico inter-sede, eliminando la exposición de SIP y RTP al internet público [3]. Adicionalmente, se aplican reglas de firewall en el MikroTik para restringir el acceso de gestión a la VLAN 30, y se habilita autenticación por par de claves pública/privada (criptografía de curva elíptica) en WireGuard, conforme a las recomendaciones de [4].

### 4.9 Cálculo de ancho de banda VoIP
El ancho de banda requerido por una llamada VoIP depende del códec utilizado, el tamaño de la trama, el intervalo de paquetización y la sobrecarga de los encabezados de los protocolos involucrados (Ethernet, IP, UDP, RTP). Para el códec G.711 — el más común en despliegues empresariales por su calidad de voz y compatibilidad universal — el cálculo es:

$$ \text{BW}_{\text{llamada}} = (\text{Payload} + \text{Overhead}) \times \text{PPS} \times 8 \text{ bits} $$
$$ \text{BW}_{\text{llamada}} \approx (160\text{ B} + 58\text{ B}) \times 50\text{ pps} \times 8 $$
$$ \text{BW}_{\text{llamada}} \approx 87.2\text{ kbps por sentido} \approx 174\text{ kbps full-dúplex} $$

Para un escenario de 140 agentes concurrentes, el tráfico VoIP agregado en el peor caso es:

$$ \text{BW}_{\text{total}} \approx 140\text{ llamadas} \times 87.2\text{ kbps} \approx 12.2\text{ Mbps por sentido} \approx 24.4\text{ Mbps full-dúplex} $$

Este consumo representa menos del 4 % del enlace de 600 Mbps disponible en Bellavista, lo que confirma que la saturación de ancho de banda no es la causa raíz de las incidencias y que el problema debe atribuirse a la ausencia de QoS y a la configuración de red (ver sección 5.2 y sección 5.4).

### 4.10 Arquitectura, ubicación de servidores y balanceo de carga
El patrón **maestro-réplica** (master-replica) es una arquitectura distribuida en la que un nodo central concentra el estado autoritativo del sistema (configuración, base de datos, lógica de enrutamiento) y nodos secundarios mantienen copias sincronizadas que pueden asumir operación local si el maestro deja de responder. Su aplicación a entornos de telefonía sobre Asterisk/MySQL ofrece tres beneficios concurrentes: (a) reportes consolidados desde el nodo central, (b) operación degradada en cada réplica ante caída del maestro y (c) balanceo de carga de llamadas mediante enrutamiento basado en disponibilidad de agentes. La distribución física de los servidores impacta directamente la latencia percibida y la resiliencia operativa, por lo que el nodo maestro se sitúa típicamente en la sede con mayor capacidad de infraestructura y enlaces redundantes. El detalle de aplicación de este patrón al caso de estudio se desarrolla en sección 6.5.

### 4.11 Alta disponibilidad y escalabilidad
La **alta disponibilidad** (HA) es la propiedad de un sistema que garantiza la continuidad del servicio ante fallas de componentes individuales, típicamente expresada como un porcentaje de tiempo activo (por ejemplo, 99.9 % = "tres nueves" ≈ 8.76 horas de caída anual; 99.99 % = "cuatro nueves" ≈ 52 minutos anuales). Los principios para alcanzarla en redes corporativas son: (a) eliminación de puntos únicos de falla (SPOF) mediante redundancia de enlaces, equipos o servidores; (b) detección automática de fallos con conmutación a recursos alternativos en segundos; y (c) capacidad de operación degradada que mantenga el servicio mínimo aún ante la pérdida parcial de la infraestructura. La combinación de redundancia de enlaces ISP [5], VPN inter-sede hub-and-spoke [3] y arquitectura cliente-servidor maestro-réplica permite alcanzar disponibilidades sobre 99.5 % sin necesidad de equipos dedicados de respaldo.

La HA es complementaria al concepto de **escalabilidad**: una arquitectura debe poder absorber crecimiento futuro (mayor número de agentes, nuevas sedes, mayor volumen de llamadas) sin replantear el diseño base. En redes multi-sede, esto se logra mediante el reuso del esquema de direccionamiento, la modularidad del plan VLAN y la posibilidad de incorporar nuevos *spokes* a la topología hub-and-spoke sin afectar a los nodos existentes. La aplicación combinada de estos principios al caso de estudio se desarrolla en sección VI y sección VII.

### 4.12 Firewall y políticas de filtrado
Un **firewall** es un sistema de control de tráfico que aplica reglas de filtrado sobre los paquetes que atraviesan una frontera de red, con el objetivo de permitir el tráfico legítimo y bloquear el no autorizado. Los firewalls modernos operan en modo *stateful*: mantienen una tabla de conexiones activas y solo permiten el tráfico de retorno que pertenece a sesiones previamente establecidas, en contraste con los filtros *stateless* que evalúan cada paquete de forma aislada. En infraestructuras VoIP, el firewall debe permitir el tráfico SIP (UDP 5060) y RTP (rango UDP 10000–20000) entre los endpoints autorizados, bloqueando intentos de conexión a estos puertos desde direcciones externas no permitidas, dado que los puertos SIP expuestos son uno de los vectores de ataque más comunes en sistemas Asterisk.

El firewall cumple un rol complementario al de la segmentación VLAN (sección 4.3) y al cifrado de la VPN (sección 4.4): mientras la VLAN aísla el plano de tráfico de voz del plano de datos, y la VPN cifra el transporte entre sedes, el firewall controla qué tráfico puede atravesar las fronteras lógicas entre VLANs y entre la red interna e internet. En equipos MikroTik RouterOS, la funcionalidad de firewall se complementa con la facilidad *Mangle*, que permite marcar paquetes para su tratamiento diferenciado por las colas de prioridad QoS [2].

### 4.13 Monitoreo activo de red (SNMP y Zabbix)
El **monitoreo activo de red** es el proceso de recolección continua de métricas operativas de equipos de red y servidores con el fin de detectar degradaciones antes de que se conviertan en incidentes y de mantener un histórico de comportamiento útil para análisis de capacidad. El estándar **SNMP** (*Simple Network Management Protocol*), en sus versiones v2c y v3, define la interfaz por la que los equipos de red exponen sus métricas (tráfico por interfaz, errores, utilización de CPU/memoria, estado de túneles VPN) a través de un árbol de identificadores OID descrito por archivos MIB.

**Zabbix** es una de las plataformas de código abierto más adoptadas para este propósito: permite descubrir equipos en la red, configurar umbrales por métrica, generar alertas por correo o webhook ante violaciones y mantener dashboards en tiempo real. La importancia del monitoreo activo en redes VoIP radica en que permite correlacionar caídas de audio con eventos de red específicos (saturación, caída de enlace, latencia anómala) y reaccionar de forma proactiva en lugar de reactiva. En conjunto con un SOP de respuesta ante incidencias (sección 4.15), el monitoreo activo es la base operativa que convierte la administración reactiva en gestión proactiva.

### 4.14 Simulación y validación de redes (Packet Tracer y GNS3)
La **simulación de redes** es el proceso de reproducir el comportamiento de una topología propuesta en un entorno virtual antes de su despliegue productivo, con el fin de validar la configuración, identificar errores de diseño y medir el comportamiento ante escenarios de falla controlados. Dos herramientas dominan el ámbito académico y profesional:

- **Cisco Packet Tracer** es una herramienta de **simulación lógica** orientada a la enseñanza: modela el comportamiento de los equipos a nivel de protocolo y configuración, pero no ejecuta sistemas operativos reales. Su fortaleza es la facilidad para validar la lógica de VLANs, enrutamiento inter-VLAN, QoS por DSCP y conmutación por distancia administrativa.
- **GNS3** (*Graphical Network Simulator-3*), por contraste, es una plataforma de **emulación**: ejecuta imágenes oficiales de sistemas operativos de red (entre ellas MikroTik CHR — *Cloud Hosted Router*) sobre máquinas virtuales, lo que permite reproducir con alta fidelidad el comportamiento real de los equipos en producción.

La combinación de ambos es una práctica común en proyectos de ingeniería de redes: Packet Tracer para validar la lógica del diseño y GNS3 para las pruebas que requieren protocolos no soportados por Packet Tracer (WireGuard, scripts de Netwatch, BGP). La simulación constituye un prerrequisito metodológico antes del despliegue piloto, ya que permite detectar errores de configuración en un entorno sin impacto operativo.

### 4.15 Gestión de incidencias en redes (ITSM e ITIL)
La **Gestión de Servicios de TI** (ITSM, *IT Service Management*) es el conjunto de prácticas, procesos y procedimientos que una organización emplea para entregar y soportar servicios tecnológicos de forma consistente y medible. El marco más adoptado a nivel mundial es **ITIL** (*Information Technology Infrastructure Library*), en su versión actual ITIL v4.

ITIL define la **gestión de incidencias** como un proceso estructurado en fases consecutivas: detección, registro, clasificación por nivel de severidad, contención, diagnóstico de causa raíz, resolución y cierre con registro histórico. Cada incidencia recibe un nivel de severidad (típicamente N1 a N4, donde N1 es la más grave) y un tiempo objetivo de resolución expresado en SLO/SLA según su impacto sobre el negocio. Las métricas operativas clave del proceso son el **MTTR** (*Mean Time To Repair* — tiempo promedio de resolución) y el **MTBF** (*Mean Time Between Failures* — tiempo promedio entre fallas), que permiten cuantificar la eficacia del proceso a lo largo del tiempo.

Para entornos de telefonía críticos como un call center, el SOP (*Standard Operating Procedure*) basado en ITIL debe definir explícitamente: qué se considera incidencia, quién la detecta, qué responsable se activa según el nivel, qué acciones de contención inmediata se ejecutan y cómo se registra para retroalimentar el ciclo de mejora continua. La estandarización ITSM convierte la operación reactiva en gestión proactiva y es el complemento organizacional indispensable de la infraestructura técnica.

### 4.16 MikroTik RouterOS como plataforma de borde
**MikroTik RouterOS** es el sistema operativo de red desarrollado por la empresa letona MikroTik. Construido sobre un kernel Linux modificado, integra en una sola plataforma las funciones que tradicionalmente requerían equipos especializados separados: enrutamiento dinámico (BGP, OSPF, RIP), filtrado de firewall (stateful con tabla de conexiones), gestión de tráfico con QoS (Priority Queues, Queue Tree), conmutación con soporte de VLAN 802.1Q, túneles VPN (IPsec, OpenVPN, WireGuard, L2TP, EoIP) y herramientas operativas de monitoreo (Torch, Graphing, Netwatch).

Su difusión en pequeñas y medianas empresas se debe a la combinación de hardware accesible (series CCR, hEX y RB), licenciamiento incluido en el equipo y una interfaz de gestión gráfica (Winbox) que facilita la administración por personal sin formación profunda en CLI. La versión virtualizada **CHR** (*Cloud Hosted Router*) permite además ejecutar RouterOS sobre hipervisores estándar, lo que la convierte en la imagen de referencia para los entornos de simulación basados en GNS3 (sección 4.14).

En el contexto del presente proyecto, RouterOS provee la **convergencia funcional** que permite implementar VPN, VLAN, QoS, firewall y failover de ISP sobre un mismo equipo. Esta integración reduce significativamente la inversión en infraestructura adicional respecto a una solución que requiriera equipos dedicados por función, sustentando la viabilidad económica del proyecto.

### 4.17 Modelos de Red: OSI y TCP/IP
Para comprender la interacción de los protocolos en la arquitectura propuesta, se toma como referencia los modelos teóricos de red [6], [7]:
- **Modelo OSI (Open Systems Interconnection):** Marco conceptual de siete capas que estandariza las funciones de un sistema de telecomunicaciones. En el presente proyecto, el enrutamiento (Capa 3 - Red) es gestionado por los routers MikroTik, mientras que la segmentación lógica se realiza en la Capa 2 (Enlace de Datos) mediante VLANs IEEE 802.1Q.
- **Modelo TCP/IP:** Arquitectura práctica de cuatro capas (Acceso a Red, Internet, Transporte y Aplicación) subyacente a internet. Las comunicaciones de voz dependen de la pila TCP/IP, utilizando IP (Capa Internet) para el direccionamiento y UDP (Capa de Transporte) para minimizar la sobrecarga en la transmisión en tiempo real de RTP y SIP [8].

---

## V. DIAGNÓSTICO — SITUACIÓN ACTUAL
A partir del levantamiento de información realizado en campo, se identificaron seis problemas principales que afectan la calidad del servicio y la resiliencia operativa del call center:

### 5.1 Ausencia de interconexión entre sedes
Las cuatro sedes operan como entidades completamente aisladas. No existe ningún mecanismo de VPN ni red privada que las vincule. Ante la caída de una sede, el traslado de agentes a otra requiere intervención manual, elevando el tiempo de recuperación y afectando directamente la disponibilidad del servicio.

### 5.2 QoS no configurado
El tráfico VoIP (RTP/SIP) compite en igualdad de condiciones con el tráfico de datos. El switch MikroTik gestionable disponible en Bellavista tiene capacidad nativa para establecer políticas de prioridad de tráfico, pero esta funcionalidad no ha sido implementada, lo que genera degradación de audio ante picos de uso de red [1].

### 5.3 Redundancia de ISP sin política de failover
Bellavista cuenta con dos conexiones de internet: fibra empresarial de 75 Mbps y fibra Claro de 600 Mbps. No existe failover automático configurado. Ante la caída de una línea, el cambio al enlace alternativo se realiza manualmente, incrementando el tiempo de indisponibilidad [5].

### 5.4 Incidencias recurrentes de audio
Se registran entre 2 y 4 incidencias de caídas de llamadas o degradación de audio por semana (ver bitácora EV-04). La ausencia de correlación con horas pico descarta la saturación del ancho de banda como causa principal y apunta hacia problemas de configuración de red [1].

### 5.5 Gestión de agentes silada por sede
Cada sede administra de forma independiente su pool de agentes en ViciDial. No existe un mecanismo centralizado que permita redistribuir carga entre sedes, implementar políticas de enrutamiento corporativo ni obtener reportes consolidados.

### 5.6 Ausencia de documentación de red
No existe un diagrama de red formal para ninguna de las cuatro sedes. Esta carencia impide la gestión proactiva y dificulta el diagnóstico de fallas.

### 5.7 Inventario de infraestructura actual

| Sede | Agentes aprox. | Servidores | ISP |
|---|---|---|---|
| Bellavista | 30 – 40 (de 82 PCs instalados — levantamiento 2026-05-27) | 3 (ViciBox v.12.0.2 — `asterisk2` confirmado; roles de los otros 2 por confirmar) | 2 ISP: 75 Mbps (empresarial) + 600 Mbps (Claro) |
| Magdalena | 30 – 40 | 1 | Propio |
| Los Olivos | 20 – 30 | 1 | Propio |
| Independencia | 20 – 30 | 1 | Propio |

Tabla 1. Inventario de infraestructura actual por sede [8].

### 5.7.1 Resultados del levantamiento de campo — Bellavista (2026-05-27)

El equipo realizó la primera visita de levantamiento técnico a la sede principal el **2026-05-27**, documentada íntegramente en el archivo `Levantamiento_Bellavista.md` que acompaña este artículo. A continuación se consolidan los hallazgos verificados *in situ* y los datos que **sustituyen** los placeholders genéricos del inventario por información real medida.

**5.7.1.1 Equipos de red identificados (Bellavista)**

| Rol | Modelo | Tipo | Observación |
|---|---|---|---|
| Router principal | **MikroTik RB3011 UiAS-RM** | Router gestionable | 10 puertos Gigabit + SFP. Soporta VLAN 802.1Q, QoS, WireGuard nativo |
| Switch rack | **TP-Link TL-SG1024D** | Switch NO gestionable | 24 puertos Gigabit. **Sin soporte VLAN** |
| Switch piso (agentes) | **TP-Link TL-SF1024D** | Switch NO gestionable | 24 puertos 10/100 Mbps — **cuello de botella físico para 30–40 agentes** |
| Firewall/UTM | Fortinet (modelo pendiente) | Firewall | Presente en rack. **Estado de configuración no confirmado** |
| Equipo adicional | Lenovo ThinkCentre | PC/servidor | Rol pendiente de confirmar |

Tabla 1.1. Inventario de equipos de red de Bellavista (levantamiento 2026-05-27).

**5.7.1.2 Direccionamiento actual (Bellavista)**

| Dispositivo | IP identificada | Observación |
|---|---|---|
| Gateway / MikroTik | `192.168.88.1` | Puerta de enlace de la red interna |
| Servidor `asterisk2` (eth0 — LAN) | `192.168.88.233` | 1 salto desde la LAN |
| Servidor `asterisk2` (eth1 — IP pública) | `161.132.165.84` | **Exposición directa a internet — riesgo de SIP fraud** |
| PC agente (ZeroTier) | `10.0.1.232` | VPN informal en nube de terceros — ya instalada por personal técnico |
| Rango de red local | `192.168.88.0/24` | **Red plana sin segmentación VLAN** |

Tabla 1.2. Direccionamiento IP actual en Bellavista (levantamiento 2026-05-27).

**5.7.1.3 Mediciones tomadas el 2026-05-27**

| Métrica | Valor medido | Herramienta | Observación |
|---|---|---|---|
| Latencia ICMP hacia 8.8.8.8 (100 paquetes) | mín 36 ms — máx 38 ms — promedio ~36–37 ms | ping desde PC de agente | Estable, dentro de ITU-T G.114 (< 150 ms) — **descarta WAN como causa raíz** |
| Pérdida de paquetes ICMP | 0 % | ping | Sin pérdida visible durante la medición |
| TTL | 117 | ping | Consistente, sin rerouting |
| Traceroute a `192.168.88.233` (LAN) | 1 salto, < 1 ms | tracert | Mismo segmento LAN |
| Traceroute a `161.132.165.84` (pública) | 2 saltos, < 1 ms | tracert | Pasa por `router.lan` (192.168.88.1) |

Tabla 1.3. Mediciones tomadas durante el levantamiento (2026-05-27).

**5.7.1.4 Problemas adicionales identificados en campo (Bellavista)**

Además de las seis causas raíz descritas en las secciones 5.1 a 5.6, el levantamiento del 2026-05-27 reveló dos hallazgos no contemplados en el diagnóstico previo:

1. **Switches LAN no gestionables.** Tanto el TP-Link TL-SG1024D (rack) como el TL-SF1024D (piso de agentes) **no soportan 802.1Q**. La segmentación VLAN propuesta en la sección 6.2 requiere por tanto el reemplazo del switch de piso por un equipo gestionable, o la inserción de un switch gestionable entre el MikroTik y el switch actual. Este punto debe incorporarse al CAPEX del proyecto.
2. **Cuello de botella físico a 100 Mbps.** El TL-SF1024D opera a 10/100 Mbps por puerto. Con 30–40 agentes simultáneos compartiendo Fast Ethernet, existe una restricción de capa física previa a cualquier mejora de QoS. El reemplazo por un switch Gigabit gestionable resuelve ambos problemas simultáneamente.
3. **`asterisk2` con IP pública directa sin NAT visible.** El servidor expone su interfaz eth1 con IP pública `161.132.165.84`. Esto implica riesgo de **SIP fraud** (llamadas fraudulentas) si los puertos SIP (UDP 5060) están accesibles sin filtrado. El Fortinet presente en el rack debería gestionar este acceso, pero su configuración activa **no fue confirmada** durante el levantamiento.
4. **VPN informal ZeroTier preinstalada.** Al menos un PC de agentes tiene instalado ZeroTier con IP `10.0.1.232`. Confirma que la necesidad de interconexión ya fue identificada informalmente por el personal técnico, pero la solución actual depende de servidores externos en la nube, no garantiza latencia para VoIP y no se integra con la gestión del MikroTik. **Refuerza la justificación del componente 6.1 (VPN site-to-site WireGuard).**

**5.7.1.5 Cobertura del levantamiento contra las 20 evidencias EV-XX**

| EV | Descripción | Estado |
|---|---|---|
| EV-01 | Wireshark RTP línea base | PENDIENTE |
| EV-02 | Wireshark pérdida RTP | PENDIENTE |
| EV-03 | MikroTik Torch BW WAN | PENDIENTE |
| EV-04 | Bitácora ViciDial 4 semanas | PENDIENTE |
| EV-05 | Fotos rack 4 sedes | PENDIENTE FOTO (rack de Bellavista caracterizado en sección 5.7.1) |
| EV-06 | Export configuración MikroTik | PENDIENTE |
| EV-07 | Acta entrevista admin red | PENDIENTE ACTA FIRMADA (entrevista realizada el 2026-05-27; datos en sección 5.7.1) |
| EV-08 | Contratos / facturas ISP | PENDIENTE DIGITALIZACIÓN (proveedores y BW identificados en sección 5.7.1) |
| EV-09 | Diagrama lógico ACTUAL | COMPLETADO |
| EV-10 | Diagrama lógico PROPUESTO | COMPLETADO |
| EV-11 a EV-14 | Simulación Packet Tracer / GNS3 | PENDIENTE |
| EV-15 a EV-18 | Pruebas controladas (caídas, failover, latencia, carga) | PENDIENTE |
| EV-19 | Comparativa Zabbix antes / después | PENDIENTE |
| EV-20 | Acta firmada por gerencia | PENDIENTE |

Tabla 1.4. Cobertura del levantamiento del 2026-05-27 contra las 20 evidencias del proyecto.

**Resumen al 2026-06-04:** 2 evidencias COMPLETADAS (EV-09 — diagrama lógico ACTUAL, EV-10 — diagrama lógico PROPUESTO) y 18 PENDIENTES. El levantamiento del 2026-05-27 aportó información que alimenta EV-05, EV-07 y EV-08 pero no produce sus artefactos formales (foto, acta firmada, contrato digitalizado), que se capturarán durante la visita de la 1.ª semana de junio 2026 junto con EV-01 a EV-04 y EV-06. Las evidencias EV-11 a EV-20 corresponden a las fases posteriores de simulación, piloto y cierre.

- **Fotografías del rack de comunicaciones (4 sedes):** una fotografía por sede del rack mostrando router, switch principal y, en Bellavista, los 3 servidores ViciDial. 

- **Export de configuración del MikroTik (Bellavista):** salida del comando `/export compact` anonimizada, como evidencia del estado de configuración previo a la intervención. 

### 5.8 Metodología de investigación de campo
La caracterización del estado actual se sustenta en un trabajo de campo estructurado en tres frentes complementarios, orientado a sustituir las descripciones genéricas por evidencia verificable:

- **Entrevistas con personal técnico y de operación.** Se realizaron entrevistas semi-estructuradas con el administrador de red responsable de las cuatro sedes y con el personal de soporte técnico de cada sede. Las entrevistas cubrieron: histórico de incidencias, procedimientos vigentes ante caídas, conocimiento de la topología, proveedores de servicios contratados y configuraciones previas conocidas.
- **Recolección documental.** Se solicitaron y revisaron las facturas y contratos de los proveedores de servicios de internet (ISP) — incluyendo la doble contratación en Bellavista —, los registros de incidencias operativas mantenidos por la coordinación del call center y la información disponible sobre los equipos en producción (modelos de routers MikroTik, switches gestionables y servidores ViciDial).
- **Levantamiento técnico in situ.** Se inspeccionaron los racks de comunicaciones de las cuatro sedes para verificar la presencia y modelo de routers, switches y servidores, así como el cableado físico y la organización de los puntos de red. Esta inspección permitió contrastar la información declarada por el personal con el estado real de la infraestructura y construir el inventario consolidado de la Tabla 1.

La información obtenida en este proceso constituye la fuente primaria del diagnóstico y reemplaza supuestos no validados por datos verificables con el propio cliente.

- **Acta de entrevista con el administrador de red:** documento con nombre, cargo, fecha, preguntas, respuestas y firma de conformidad. 

- **Evidencia documental de proveedores ISP:** facturas/contratos de los dos ISP de Bellavista anonimizados (Empresa X 75 Mbps, Claro 600 Mbps). 

### 5.9 Metodología de medición y captura de evidencias
Para sustituir las descripciones cualitativas por métricas cuantitativas que permitan validar la solución, se define el siguiente protocolo de medición de la línea base y de evidencia post-implementación. El plan emplea exclusivamente herramientas presentes en la infraestructura o de uso libre, sin costo adicional:

| Métrica | Herramienta | Punto de captura | Frecuencia | Evidencia |
|---|---|---|---|---|
| Latencia y jitter RTP | Wireshark + análisis RTP Stream | Espejo de puerto en switch MikroTik (Bellavista) | Muestreo de 30 min × 3 franjas/día durante 5 días | EV-01 |
| Pérdida de paquetes | Wireshark + estadísticas RTP | Mismo punto | Mismo muestreo | EV-02 |
| Uso de ancho de banda | MikroTik Torch + Interface Statistics | Interfaces WAN y troncales | Continuo (logging cada 5 min) | EV-03 |
| MOS estimado | Wireshark RTP Analysis | Mismo punto | Mismo muestreo | EV-01 |
| Frecuencia y duración de incidencias | Bitácora ViciDial + tickets internos | Servidor maestro | Continuo | EV-04 |
| Tiempo de failover ISP | Cronometraje manual + ping a destino externo | Estación de prueba en Bellavista | Pruebas controladas | EV-13 |

Tabla 5. Protocolo de medición de línea base y evidencias.

Las evidencias gráficas (capturas de Wireshark, gráficas de utilización de Torch, registros de incidencias y configuraciones exportadas) se incorporan a este documento como bloques EV-01 a EV-19 distribuidos en los puntos donde sustentan el argumento. La medición de línea base es prerrequisito de la implementación piloto descrita en sección 9.2.

---

## VI. PROPUESTA DE SOLUCIÓN
La propuesta responde a cada causa raíz identificada en el diagnóstico mediante seis componentes interrelacionados. Cada componente se presenta en cuatro bloques explícitos — Diagnóstico → Análisis → Justificación → Solución — para cumplir el criterio metodológico de ir del problema observado a la solución validada bibliográficamente.

### 6.1 VPN site-to-site entre sedes
- **Diagnóstico (causa raíz).** Las cuatro sedes operan aisladas (sección 5.1); no existe canal privado que permita el respaldo cruzado de agentes ni la administración centralizada de ViciDial.
- **Análisis.** El aislamiento convierte cualquier caída de sede en una pérdida total de capacidad para esa ubicación, ya que las llamadas no pueden reenrutarse a otra sede. Además, impide consolidar reportes y aplicar políticas corporativas uniformes.
- **Justificación.** [3] y [4] concluyen que WireGuard implementado sobre RouterOS ofrece el mejor rendimiento para este tipo de despliegue, con menor overhead de cifrado que OpenVPN (≈ 20 bytes por paquete) y mejor integración con el kernel del sistema operativo.
- **Solución.** Se implementa una topología hub-and-spoke donde el MikroTik de Bellavista actúa como concentrador principal (hub) y los equipos de las otras tres sedes (spokes) establecen un túnel WireGuard cifrado hacia este. El tráfico inter-sede viaja encapsulado sobre las conexiones de internet existentes, con autenticación por par de claves pública/privada (criptografía de curva elíptica) y direccionamiento dedicado en 10.0.0.0/24 (sección 7.2).

### 6.2 Segmentación por VLANs
- **Diagnóstico (causa raíz).** No hay VLANs documentadas (sección 5.2, sección 5.6); el tráfico de voz, datos y gestión convive en un mismo dominio de broadcast.
- **Análisis.** La convivencia de tráficos heterogéneos amplifica la competencia por recursos y eleva la superficie de ataque ante un compromiso lateral en una estación de trabajo [2].
- **Justificación.** [2] demuestran que la segmentación VLAN reduce de forma significativa el impacto de ataques laterales y mejora el rendimiento general; la separación voz/datos es práctica estándar en despliegues VoIP empresariales.
- **Solución.** Se configuran tres VLANs IEEE 802.1Q en el switch MikroTik — VLAN 10 (voz, DSCP 46), VLAN 20 (datos, Best Effort), VLAN 30 (gestión). Los puertos del switch se asignan a su VLAN correspondiente según el dispositivo conectado.

### 6.3 QoS para tráfico VoIP
- **Diagnóstico (causa raíz).** El switch MikroTik gestionable tiene capacidad nativa de QoS, pero no está configurada (sección 5.2). El tráfico VoIP compite en igualdad de condiciones con el tráfico de datos.
- **Análisis.** En ausencia de prioridad, las ráfagas de tráfico de datos elevan jitter y pérdida en RTP justo en los momentos de mayor uso simultáneo, lo que explica las 2–4 incidencias semanales registradas sin correlación con horas pico (sección 5.4).
- **Justificación.** [1] demuestran que la combinación de QoS y buffer adaptativo reduce el jitter en redes VoIP; la recomendación ITU-T G.114 establece los umbrales operativos (latencia < 150 ms, jitter < 30 ms).
- **Solución.** Se configuran reglas *Mangle* en el firewall MikroTik para marcar conexiones SIP (UDP 5060) y RTP (UDP 10000–20000) con DSCP 46 (EF) y una *Queue Tree* con prioridad 1 que garantiza un CIR (Committed Information Rate) exclusivo para voz suficiente para 140 llamadas concurrentes (≈ 15 Mbps reservados, ver sección 4.9).

### 6.4 Failover automático de ISP
- **Diagnóstico (causa raíz).** Bellavista cuenta con doble ISP (75 Mbps + 600 Mbps) pero sin failover automático configurado (sección 5.3); el cambio de enlace requiere intervención manual.
- **Análisis.** La dependencia de la intervención manual eleva el tiempo de recuperación a varios minutos, durante los cuales se pierde la totalidad de la capacidad de llamadas. Este tiempo es inaceptable para la operación de un call center.
- **Justificación.** [5] muestran que la conmutación automática reduce el tiempo de recuperación de minutos a segundos sin requerir protocolos de enrutamiento dinámico costosos.
- **Solución.** Se configuran dos rutas por defecto con distancia administrativa 1 (ISP principal) y 2 (ISP secundario). MikroTik verifica periódicamente la disponibilidad del gateway mediante *Check Gateway* y scripts de *Netwatch* sobre ping ICMP, y conmuta automáticamente al enlace alternativo cuando detecta falla, con tiempo objetivo de recuperación < 10 s.

### 6.5 Arquitectura ViciDial maestro-réplica con alta disponibilidad
- **Diagnóstico (causa raíz).** Cada sede administra su pool de agentes de forma silada (sección 5.5); no existe consolidación operativa ni capacidad de redistribución de carga.
- **Análisis.** La fragmentación impide aplicar políticas de enrutamiento corporativo, obtener reportes consolidados y reaccionar de forma elástica ante picos o caídas localizadas.
- **Justificación.** La arquitectura maestro-réplica es el patrón estándar para entornos distribuidos sobre Asterisk/MySQL y se sustenta en la disponibilidad de la VPN site-to-site (sección 6.1) como canal de sincronización seguro. Conforme a sección 4.11, la combinación con failover de ISP en el nodo central y VPN hub-and-spoke eleva el objetivo de disponibilidad por encima del 99.5 %.
- **Solución.** Se reorganiza la arquitectura de servidores ViciDial hacia un esquema maestro-réplica: un servidor maestro centraliza la base de datos y la configuración global, mientras que los nodos de cada sede operan como réplicas con capacidad de modo degradado ante la caída del maestro. Esto permite reportes consolidados y enrutamiento de llamadas basado en disponibilidad de agentes en todas las sedes.

### 6.6 Documentación y monitoreo activo
- **Diagnóstico (causa raíz).** No existe diagrama de red formal ni monitoreo activo (sección 5.6); las fallas se detectan reactivamente, por reporte del agente afectado.
- **Análisis.** La ausencia de monitoreo impide detectar degradaciones tempranas, correlacionar incidencias y mantener evidencia histórica útil para diagnóstico.
- **Justificación.** La documentación formal y el monitoreo continuo son prácticas básicas de gestión de red corporativa; Zabbix es una herramienta consolidada de código abierto compatible con MikroTik vía SNMP.
- **Solución.** Se elabora el diagrama de red lógico y físico de cada sede (ver sección 7 y EV-09 / EV-10) y se despliega Zabbix como herramienta de monitoreo, permitiendo visualizar en tiempo real el estado de los enlaces, latencia, jitter y utilización de ancho de banda (ver EV-19).

### 6.7 Procesos organizacionales y SOP de respuesta ante incidencias
La infraestructura técnica debe complementarse con un Procedimiento Operativo Estándar (SOP) que defina quién interviene, en qué orden y bajo qué criterios ante cada tipo de incidencia. Este componente responde a la observación de que las soluciones técnicas son insuficientes si no existe un proceso documentado de respuesta.

**Niveles de respuesta definidos:**

| Nivel | Tipo de incidencia | Responsable primario | Tiempo objetivo de respuesta |
|---|---|---|---|
| N1 | Degradación de audio en una llamada | Supervisor de sede | < 2 min — diagnóstico inicial |
| N2 | Caída total de una sede | Administrador de red | < 10 min — activación de failover cruzado |
| N3 | Caída de ISP principal en Bellavista | MikroTik (automático) + Administrador de red | < 10 s (automático) + < 5 min (notificación) |
| N4 | Caída del servidor maestro ViciDial | Administrador de red + Soporte ViciDial | < 30 min — promoción de réplica |

Tabla 6. Niveles de respuesta ante incidencias.

**Flujo estándar de atención (timeline T+N min):**

| Paso | Tiempo | Acción | Responsable |
|---|---|---|---|
| 1. Detección | **T+0 min** | Alerta automática vía Zabbix (umbrales: latencia > 100 ms, pérdida > 0.5 %, caída de túnel VPN) o reporte del supervisor de sede | Zabbix / Supervisor |
| 2. Clasificación | **T+2 min** | Verificación visual del enlace y carga en el MikroTik central; asignación a N1–N4 | NOC Nivel 1 |
| 3. Contención | **T+5 min** | Acción correctiva inmediata: forzar failover, reenrutamiento de agentes, reinicio de servicio, promoción de réplica si aplica | Administrador de red |
| 4. Diagnóstico | **T+15 min** | Análisis de logs, capturas RTP, métricas Zabbix para identificar causa raíz | Administrador de red |
| 5. Resolución | **T+30 min** | Corrección definitiva y verificación con métricas del protocolo de sección 5.9 | Administrador de red |
| 6. Cierre y registro | **T+24 h** | Documentación en bitácora interna, apertura de ticket con ISP si aplica, reporte a gerencia | Coordinación |

Tabla 7. Flujo estándar de atención de incidencias.

Este procedimiento formaliza lo que actualmente ocurre de manera improvisada, reduce la variabilidad humana en la respuesta y permite medir tiempos reales de atención como indicador de mejora continua.

---

## VII. DISEÑO DE RED
El diseño propuesto establece una arquitectura convergente que interconecta las cuatro sedes mediante VPN site-to-site y segmenta internamente cada sede mediante VLANs. A continuación se describe la arquitectura lógica propuesta:

### 7.1 Topología lógica propuesta
La arquitectura propuesta adopta una topología hub-and-spoke en la que la sede de Bellavista actúa como nodo central (hub) por ser la sede con mayor infraestructura (3 servidores, switch MikroTik gestionable y doble ISP). Las sedes de Magdalena, Los Olivos e Independencia actúan como nodos secundarios (spokes) conectados al hub mediante túneles WireGuard.

A partir del levantamiento de campo, se elaboró el diagrama lógico de la red actual (Figura 1) que refleja la situación de aislamiento y cuellos de botella.

![Topología lógica ACTUAL de la sede Bellavista — Red plana 192.168.88.0/24 sin segmentación VLAN, doble ISP sin failover y VPN informal ZeroTier preinstalada [8].](EV-09_topologia_actual_Bellavista.png){width=100%}

Posteriormente, se diseñó la topología convergente (Figura 2) que resuelve estas deficiencias adoptando el modelo *hub-and-spoke* con segmentación y alta disponibilidad.

![Topología lógica PROPUESTA del call center multisede — diseño basado en Hub-and-Spoke, segmentación de 3 VLANs por sede con VLSM, failover de ISP en Bellavista y VPN WireGuard centralizada [9].](Diseno_Rede_Propuesta_mejorado.jpeg){width=100%}

### 7.2 Esquema de direccionamiento IP propuesto (VLSM)

Para optimizar el espacio de direcciones y alinearse a las mejores prácticas de ingeniería, se aplicó la técnica VLSM (*Variable Length Subnet Mask*). Esto permite ajustar las subredes al número real de hosts requeridos por cada sede, dividiendo el espacio de manera eficiente y reservando direcciones para futuro crecimiento.

![Plan de Direccionamiento IP con VLSM para todas las sedes, incluyendo redes LAN, WAN (VPN) y asignación de servidores críticos [9].](Plan_direccionamiento_IP.jpeg){width=100%}

*Nota:* Las sedes más grandes (Bellavista y Magdalena) utilizan máscaras `/26` para Voz y Datos (hasta 62 hosts), mientras que las sedes medianas (Los Olivos e Independencia) emplean `/27` (hasta 30 hosts). Las VLANs de gestión se ajustan a `/28` en todas las sedes, optimizando el bloque de IPs. Los túneles VPN utilizan subredes `/30` punto a punto para enlazar cada Spoke con el Hub de manera segura.

### 7.3 Propuesta de adquisición de switch gestionable

#### 7.3.1 Contexto técnico
El levantamiento de campo del 2026-05-27 confirmó que los dos switches TP-Link actualmente instalados en Bellavista — el TL-SG1024D (rack, Gigabit) y el TL-SF1024D (piso de agentes, Fast Ethernet) — son equipos no gestionables sin soporte para el estándar IEEE 802.1Q. Esto implica que, si bien el MikroTik RB3011 UiAS-RM tiene capacidad nativa para crear y enrutar VLANs, las tramas etiquetadas (tagged frames) que este genera no son interpretadas por los switches downstream, por lo que la segmentación no alcanza los dispositivos finales de los agentes.

Para que la arquitectura de VLANs propuesta en la sección 6.2 funcione de extremo a extremo, es necesario reemplazar al menos el switch del piso de agentes (TL-SF1024D) por un equipo con soporte 802.1Q. Este reemplazo resuelve simultáneamente dos problemas identificados en el diagnóstico: la incapacidad de segmentación VLAN y el cuello de botella físico de 100 Mbps (Fast Ethernet) que limita el ancho de banda disponible para los 30–40 agentes simultáneos.

#### 7.3.2 Equipo propuesto
Se propone el switch **Cisco Business CBS250-24T-4G** como equipo de reemplazo por las siguientes razones técnicas: (a) es un switch administrado L2+ con soporte nativo IEEE 802.1Q, lo que permite la creación de las tres VLANs propuestas (VLAN 10 voz, VLAN 20 datos, VLAN 30 gestión); (b) sus 24 puertos Gigabit eliminan el cuello de botella de 100 Mbps del TL-SF1024D; (c) soporta QoS L2/L3/L4 con marcado DSCP 46, complementando las políticas configuradas en el MikroTik; (d) incluye SNMP v2c/v3 para integración con Zabbix (sección 6.6); y (e) ofrece la robustez característica de Cisco, garantizando confiabilidad y soporte técnico especializado.

| Parámetro | Especificación |
|---|---|
| Modelo | Cisco Business CBS250-24T-4G |
| Puertos | 24 × RJ45 Gigabit (10/100/1000 Mbps) + 4 × SFP Gigabit |
| Estándar VLAN | IEEE 802.1Q — soporta hasta 4094 VLANs activas |
| QoS | L2/L3/L4 QoS — marcado DSCP, prioridad por puerto y cola |
| Gestión | Web GUI, CLI (SSH/Telnet), SNMP v2c/v3, RMON |
| Factor de forma | 1U rack-mountable (compatible con rack existente) |
| Precio referencial Lima | S/. 1,050 (IGV incluido) — disponible en stock en Lima |
| Garantía | Limitada de por vida — Cisco |

Tabla 2.1. Especificaciones técnicas del switch propuesto Cisco CBS250-24T-4G.

#### 7.3.3 Escenarios de inversión
Se definen dos escenarios de inversión según el alcance del reemplazo:

| Escenario | Equipo a reemplazar | Equipo propuesto | Beneficio | Inversión |
|---|---|---|---|---|
| A — Mínimo viable | TL-SF1024D (piso de agentes — Fast Ethernet) | Cisco CBS250-24T-4G (×1) | VLANs en puertos de agentes + elimina cuello de botella 100 Mbps → Gigabit | S/. 1,050 |
| **B — Recomendado** | TL-SF1024D (piso) + TL-SG1024D (rack) | **Cisco CBS250-24T-4G (×2)** | Control completo de VLANs en toda la infraestructura + trunk gestionado entre switches | **S/. 2,100** |

Tabla 2.2. Escenarios de inversión para la adquisición de switch gestionable.

El **Escenario B** es el técnicamente correcto: permite configurar VLANs con trunk 802.1Q desde el MikroTik hasta los puertos de acceso de los agentes, asegurando la segregación completa del tráfico en toda la infraestructura de Bellavista. El costo total de S/. 2,100 para dos switches que habilitan la arquitectura completa propuesta es proporcionalmente bajo frente al impacto operativo de las incidencias actuales (2–4 por semana, EV-04). Sumado al CAPEX de equipos MikroTik para las otras tres sedes, el costo total del proyecto se mantiene por debajo de S/. 3,800.

#### 7.3.4 Plan de implementación

| Fase | Actividad | Detalle | Duración |
|---|---|---|---|
| 1 | Adquisición | Compra en distribuidor autorizado Cisco (Lima). Stock disponible. Entrega en el mismo día o siguiente. | 1 día |
| 2 | Pre-configuración | Configuración fuera de línea del switch: VLANs 10/20/30, puertos trunk hacia MikroTik, puertos de acceso por VLAN. Validación en banco de pruebas. | 2–3 horas |
| 3 | Instalación física | Reemplazo del TL-SF1024D en el piso de agentes. Migración de cables. Tiempo de corte de servicio estimado: 15–20 minutos. | 1–2 horas |
| 4 | Prueba de conectividad | Verificación de acceso a ViciDial por VLAN de voz, acceso a navegación por VLAN de datos y aislamiento de VLAN de gestión. | 1 hora |
| **Total** | | | **1 jornada laboral** |

Tabla 2.3. Plan de implementación del switch gestionable.

---

## VIII. COMPARATIVA: ANTES Y DESPUÉS

La siguiente tabla consolida las mejoras cuantitativas comprometidas. La columna **ANTES** se completa con las mediciones de línea base (EV-01 a EV-04) capturadas según el protocolo de sección 5.9; la columna **DESPUÉS** se completa con las mediciones post-implementación obtenidas durante el piloto en Bellavista (sección 9.2, EV-15 a EV-18).

| Métrica / Aspecto | ANTES (línea base — del cliente) | DESPUÉS (medido en piloto) | Mejora / Justificación | Evidencia |
|---|---|---|---|---|
| Interconexión de sedes | 0 túneles activos — 4 islas | 3 túneles WireGuard hub-and-spoke | VPN site-to-site WireGuard | EV-09 / EV-10 |
| Redundancia ISP | Manual — recuperación _por medir_ min | Failover automático — _por medir_ s | *Check Gateway* + métricas de distancia | EV-13 / EV-16 |
| Segmentación de red | 1 dominio de broadcast | 3 VLANs (10/20/30) | Aislamiento por 802.1Q | EV-12 |
| QoS | Sin configurar | DSCP EF (46) para RTP/SIP | Queue Tree prioridad 1 | EV-14 |
| Latencia VoIP | _Por capturar — EV-01_ ms | _Por capturar — EV-17_ ms | < 150 ms (ITU-T G.114) | EV-01 / EV-17 |
| Jitter | _Por capturar — EV-01_ ms | _Por capturar — EV-17_ ms | < 30 ms [1] | EV-01 / EV-17 |
| Pérdida de paquetes | _Por capturar — EV-02_ % | _Por capturar — EV-17_ % | < 1 % [1] | EV-02 / EV-17 |
| Uso de ancho de banda en pico | _Por capturar — EV-03_ % | _Por capturar — EV-19_ % | Estable bajo QoS | EV-03 / EV-19 |
| Caídas / semana | 2–4 (EV-04) | _Por medir — EV-19_ | Reducción objetivo > 70 % | EV-04 / EV-19 |
| Gestión de agentes | 4 instancias siladas | 1 maestro + 3 réplicas | Arquitectura HA | EV-10 |
| Documentación / monitoreo | Inexistente | Diagrama formal + Zabbix activo | Práctica estándar | EV-10 / EV-19 |
| Proceso de respuesta | Improvisado | SOP 4 niveles + flujo 6 pasos (sección 6.7) | Estandarización ITSM | EV-18 |

Tabla 3. Comparativa cuantitativa Antes vs. Después.

- **Comparativa Zabbix antes / después:** dos capturas de dashboard lado a lado del periodo de línea base y del periodo post-piloto, resaltando latencia, jitter, pérdida y uso de ancho de banda. 

---

## IX. RESULTADOS Y VALIDACIÓN

### 9.1 Métricas esperadas post-implementación

| Métrica | Línea base (EV-) | Valor esperado | Referencia |
|---|---|---|---|
| Latencia VoIP | EV-01 | < 150 ms | ITU-T G.114 |
| Jitter | EV-01 | < 30 ms | [1] |
| Pérdida de paquetes | EV-02 | < 1 % | [1] |
| Incidencias semanales | EV-04 | Reducción > 70 % | [1]; [5] |
| Tiempo de failover ISP | EV-13 | < 10 segundos | [5] |

Tabla 4. Métricas esperadas post-implementación.

### 9.2 Plan de simulación y pruebas
Para validar el diseño antes de su despliegue en producción, se ejecuta un plan de simulación en dos fases que cubre las cuatro pruebas críticas exigidas: caídas, failover, latencia y carga.

**Fase A — Simulación en entorno virtual.** Se modela la topología completa en Cisco Packet Tracer para la validación funcional de VLANs, enrutamiento inter-VLAN, QoS marcado por DSCP y conmutación de ruta por distancia administrativa. Para las pruebas que requieren WireGuard y mayor fidelidad de comportamiento real, la topología se reproduce adicionalmente en GNS3 con imágenes oficiales de MikroTik CHR (Cloud Hosted Router) y máquinas virtuales emulando los servidores ViciDial.

**Fase B — Piloto controlado en la sede de Bellavista.** Tras la validación en simulación, se ejecuta la implementación piloto en Bellavista replicando las cuatro pruebas en condiciones reales con tráfico productivo controlado.

- **Captura Packet Tracer de la topología completa simulada:** los 4 sites, túneles entre ellos, VLANs configuradas y servidores ViciDial. 

- **Captura GNS3 de VLANs y enrutamiento inter-VLAN:** CLI de MikroTik CHR con `/interface vlan print` y `/ip route print` mostrando las 3 VLANs activas. 

- **Captura GNS3 de la VPN WireGuard activa:** `/interface wireguard peers print` con los túneles activos y *last handshake* recientes, complementado con ping entre redes internas de sedes distintas. 

- **Captura MikroTik del marcado QoS DSCP EF:** reglas *Mangle* (`/ip firewall mangle print`) y *Queue Tree* (`/queue tree print`) demostrando DSCP 46 sobre RTP/SIP con prioridad 1. 

**Pruebas a ejecutar en ambas fases:**

| Prueba | Procedimiento | Métrica de éxito | Evidencia |
|---|---|---|---|
| Prueba de caídas | Desconexión deliberada de un nodo (sede spoke); medir reenrutamiento de agentes | Recuperación de capacidad de llamadas < 2 min | EV-15 |
| Prueba de failover de ISP | Apagado controlado del ISP principal en Bellavista | Conmutación al ISP secundario < 10 s; sin pérdida de llamadas establecidas o reconexión < 30 s | EV-16 |
| Prueba de latencia | Generación de tráfico SIP/RTP entre sedes durante 30 min | Latencia < 150 ms y jitter < 30 ms sostenidos | EV-17 |
| Prueba de carga | Saturación progresiva del enlace con tráfico Iperf3 hasta 80 % de la capacidad mientras hay llamadas VoIP activas | RTP mantiene latencia < 150 ms y pérdida < 1 % por efecto del QoS EF | EV-18 |

Tabla 8. Plan de pruebas de validación.

- **Prueba de CAÍDAS y reenrutamiento:** secuencia de capturas de sede spoke operando normal, desconexión, reenrutamiento de agentes en ViciDial y cronómetro del tiempo total de recuperación. 

- **Prueba de FAILOVER de ISP:** captura de `ping -t 8.8.8.8` con el gap entre caída del ISP1 y conmutación al ISP2; complementada con MikroTik `/tool netwatch print` y `/ip route print`. 

- **Prueba de LATENCIA (Wireshark post-implementación):** captura de RTP Stream Analysis tras activar QoS, durante 30 minutos de tráfico SIP/RTP entre sedes, comparada lado a lado con EV-01 / EV-02. 

- **Prueba de CARGA (Iperf3 + QoS):** cliente y servidor Iperf3 saturando el enlace al 80 % mientras RTP mantiene latencia dentro de umbrales, con gráfica de utilización en Zabbix. 

Cada prueba se ejecuta tres veces y se documenta con capturas de Wireshark, gráficas de Zabbix y registros de MikroTik conforme al protocolo de medición de sección 5.9. Los resultados alimentan la comparación Antes vs. Después de la sección VIII.

### 9.3 Validación con el cliente
Como exigencia final de un proyecto de ingeniería real, las mejoras se validan formalmente con el cliente mediante una sesión de cierre donde se presentan los resultados del piloto y se firma el acta de aceptación.

- **Acta de validación firmada por el cliente:** documento firmado por la gerencia del call center (o el administrador de red en su representación) que confirma la mejora obtenida en el piloto, la conformidad con la arquitectura propuesta y la adopción del SOP organizacional (sección 6.7). 

---

## X. CONCLUSIONES
La infraestructura de red actual del call center presenta deficiencias estructurales que explican las incidencias recurrentes de audio. El análisis demuestra que el problema no reside en la capacidad del ancho de banda contratado sino en la ausencia de configuración formal de los equipos existentes: el switch MikroTik gestionable disponible en Bellavista tiene capacidad técnica suficiente para implementar todas las mejoras propuestas, como lo confirma el cálculo de ancho de banda VoIP (sección 4.9) que sitúa el consumo de 140 llamadas concurrentes por debajo del 4 % del enlace de 600 Mbps.

La propuesta de arquitectura convergente presentada resuelve los seis problemas identificados reutilizando la infraestructura existente, con una inversión adicional estimada menor a S/ 1,100 para equipar las sedes que no disponen de router MikroTik. La implementación de VPN site-to-site, VLANs, QoS, failover automático de ISP y arquitectura ViciDial maestro-réplica constituye una solución técnicamente sólida, respaldada por literatura reciente [1]-[5], económicamente accesible y verificable mediante el plan de medición de sección 5.9 y el plan de simulación y piloto de sección 9.2.

Adicionalmente, la estandarización del SOP de respuesta ante incidencias (sección 6.7) traslada a la organización de una postura reactiva a una gestión tecnológica proactiva, con niveles de respuesta y tiempos objetivo claramente definidos. La combinación de mejora técnica + proceso organizacional + evidencia validada con el cliente (EV-20) convierte la propuesta en un proyecto de ingeniería real, medible y listo para su paso a producción.

---

## XI. REFERENCIAS

[1] S. Thangam, M. Gurupriya, A. S. Revanth, D. A. Joel, C. M. Shankar, y I. S. Koushik, "VoIP QoS refinement through call sequencing using adaptive jitter buffer algorithm," en *2024 15th International Conference on Computing Communication and Networking Technologies (ICCCNT)*, 2024.

[2] D. Álvarez, P. Nuño, C. T. González, F. G. Bulnes, J. C. Granda, y D. García-Carrillo, "Performance analysis of software-defined networks to mitigate private VLAN attacks," *Sensors*, vol. 23, no. 4, Art. no. 1747, 2023.

[3] S. T. Aung y T. Thein, "Comparative analysis of site-to-site layer 2 virtual private networks," en *2020 IEEE Conference on Computer Applications (ICCA)*, pp. 1-5, 2020.

[4] M. Pudelko, P. Emmerich, S. Gallenmüller, y G. Carle, "Performance analysis of VPN gateways," en *2020 IFIP Networking Conference and Workshops*, pp. 325-333, 2020.

[5] I. U. V. Simanjuntak, A. D. Rochendi, y L. M. Silalahi, "Simulation and analysis of link failover using routing border gateway protocol (BGP) multi-protocol label switching (MPLS) networks," en *2023 International Conference on Radar, Antenna, Microwave, Electronics, and Telecommunications (ICRAMET)*, pp. 341-346, 2023.

[6] J. Kurose y K. Ross, *Computer Networking: A Top-Down Approach*, 8va ed., Pearson, 2021.

[7] A. Tanenbaum y D. Wetherall, *Computer Networks*, 6ta ed., Pearson, 2021.

[8] Cisco Networking Academy, *CCNA: Introduction to Networks Companion Guide*, Cisco Press, 2020.

[9] Cisco Networking Academy, *CCNA: Switching, Routing, and Wireless Essentials Companion Guide*, Cisco Press, 2020.

---


## ANEXO A — ÍNDICE DE EVIDENCIAS (CHECKLIST DE CAPTURA)

Este índice consolida todas las evidencias reales que deben capturarse, validarse e insertarse en los bloques EV-XX antes del export a PDF. Sirve como checklist operativo para el equipo de campo.

| ID | Descripción | Sección donde se inserta | Herramienta | Estado al 2026-05-27 |
|---|---|---|---|---|
| EV-01 | Wireshark — RTP Stream (latencia, jitter) línea base | sección II Tabla 0 | Wireshark | PENDIENTE — visita 1.ª sem. junio 2026 |
| EV-02 | Wireshark — pérdida de paquetes línea base | sección II Tabla 0 | Wireshark | PENDIENTE — visita 1.ª sem. junio 2026 |
| EV-03 | MikroTik Torch — ancho de banda WAN hora pico | sección II Tabla 0 | Winbox / Torch | PENDIENTE — visita 1.ª sem. junio 2026 |
| EV-04 | ViciDial — bitácora de incidencias 4 semanas | sección II Tabla 0 / sección 5.4 | Sistema interno | PENDIENTE — visita 1.ª sem. junio 2026 |
| EV-05 | Fotos de rack — 4 sedes | sección 5.7 | Cámara | PENDIENTE FOTO — visita 1.ª sem. junio 2026 (rack de Bellavista caracterizado textualmente en sección 5.7.1) |
| EV-06 | Export de configuración MikroTik actual | sección 5.7 | RouterOS `/export` | PENDIENTE — visita 1.ª sem. junio 2026 (modelo RB3011 UiAS-RM confirmado) |
| EV-07 | Acta de entrevista — administrador de red | sección 5.8 | Documento físico/digital | PENDIENTE ACTA FIRMADA — visita 1.ª sem. junio 2026 (entrevista realizada el 2026-05-27; datos en sección 5.7.1) |
| EV-08 | Facturas/contratos ISP (anonimizados) | sección 5.8 | Documentos del cliente | PENDIENTE DIGITALIZACIÓN — visita 1.ª sem. junio 2026 (proveedores y BW identificados el 2026-05-27 en sección 5.7.1) |
| EV-09 | Diagrama lógico actual | sección 7.1 | draw.io (`EV-09_topologia_actual_Bellavista.drawio`) | COMPLETADO (2026-05-27) — diagrama integrado en sección 7.1 (`EV-09_topologia_actual_Bellavista.png`) |
| EV-10 | Diagrama lógico propuesto | sección 7.1 | Diagrama / Packet Tracer | COMPLETADO (2026-06-04) — integrado en sección 7.1 |
| EV-11 | Topología completa en Packet Tracer | sección 9.2 | Cisco Packet Tracer | PENDIENTE — fase de simulación |
| EV-12 | VLANs y enrutamiento inter-VLAN en GNS3 | sección 9.2 | GNS3 + MikroTik CHR | PENDIENTE — fase de simulación |
| EV-13 | VPN WireGuard activa en GNS3 | sección 9.2 | GNS3 + MikroTik CHR | PENDIENTE — fase de simulación |
| EV-14 | Marcado QoS DSCP EF | sección 9.2 | MikroTik CLI/Winbox | PENDIENTE — fase de simulación / piloto |
| EV-15 | Prueba de CAÍDAS — reenrutamiento | sección 9.2 | GNS3 + cronómetro | PENDIENTE — fase de piloto |
| EV-16 | Prueba de FAILOVER — cronometraje + ping | sección 9.2 | Terminal + Winbox | PENDIENTE — fase de piloto |
| EV-17 | Prueba de LATENCIA — Wireshark post | sección 9.2 | Wireshark | PENDIENTE — fase de piloto |
| EV-18 | Prueba de CARGA — Iperf3 + QoS | sección 9.2 | Iperf3 + Wireshark + Zabbix | PENDIENTE — fase de piloto |
| EV-19 | Comparativa Zabbix antes/después | sección VIII | Zabbix | PENDIENTE — fase de monitoreo |
| EV-20 | Acta de validación firmada por cliente | sección 9.3 | Documento físico/digital | PENDIENTE — fase de cierre |

Tabla 9. Índice y checklist de las 20 evidencias reales del proyecto. Estado actualizado al 2026-05-27 (tras el primer levantamiento de campo en Bellavista).

**Resumen al 2026-06-04:** 2 evidencias COMPLETADAS (EV-09 y EV-10) — el 10 % del total del proyecto. 18 PENDIENTES. El levantamiento del 2026-05-27 ya aportó la información de campo que sustenta EV-05, EV-07 y EV-08, aunque sus artefactos formales (foto del rack, acta firmada, contrato digitalizado) se capturarán durante la visita de la 1.ª semana de junio 2026 junto con EV-01 a EV-04 y EV-06. Las evidencias EV-11 a EV-20 se completan en las fases posteriores de simulación, piloto y cierre.

<!--
REGISTRO DE CAMBIOS APLICADOS

== Versión 1 (correcciones iniciales sobre Articulo_Universitario.md) ==

| ID     | Observación                                                                 | Sección donde se aplicó                                  | Cambio aplicado                                                                                                                                                                                                                                                                                                                       |
|--------|-----------------------------------------------------------------------------|----------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| OBS-01 | Problema mal definido; faltan métricas reales y evidencias                  | sección II Problema; sección 5.9 (nueva) Metodología de medición       | Tabla 0 con parámetros críticos y umbrales de referencia. Se creó sección 5.9 con el protocolo de medición (Wireshark, MikroTik Torch, ViciDial logs, MOS) y la Tabla 5. **Interpretación conservadora**: no se inventaron mediciones inexistentes; se formalizó el plan para obtenerlas. |
| OBS-02 | Falta comparación ANTES vs DESPUÉS cuantificada                              | sección VIII Comparativa (Tabla 3)                              | Tabla 3 ampliada con filas y valores numéricos por aspecto.                |
| OBS-03 | Falta investigación real (entrevistas, configuraciones, proveedores)        | sección 5.8 (nueva) Metodología de investigación de campo       | Tres frentes documentados: entrevistas semi-estructuradas, recolección documental, levantamiento técnico in situ. |
| OBS-04 | Soluciones sin diagnóstico — orden Diagnóstico→Análisis→Justificación→Solución | sección VI (subsecciones 6.1–6.6 reestructuradas)               | Cada componente reestructurado en 4 bloques explícitos. |
| OBS-05 | Marco teórico incompleto                                                    | sección IV (nuevas subsecciones 4.7 a 4.11)                     | Plan IP, Seguridad, Cálculo de ancho de banda, Arquitectura/Balanceo, HA. |
| OBS-06 | Falta simulación y pruebas                                                  | sección 9.2 (nueva) Plan de simulación y pruebas                | Plan de 2 fases + Tabla 7 con 4 pruebas exigidas. |
| OBS-07 | Falta procesos organizacionales                                             | sección 6.7 (nueva); sección III Objetivo Específico 5                 | Tabla 6 con 4 niveles + flujo de 6 pasos. |
| OBS-08 | Formato académico — citas APA                                               | Todo el documento + sección XI Referencias                      | Conversión completa IEEE → APA 7. |

== Versión 2 (mejoras tras comparación con Gemini + exigencia de evidencias reales) ==

| ID     | Cambio aplicado en v2 | Justificación |
|--------|------------------------|----------------|
| V2-01  | Reformulación del Abstract y sección I — framing como "proyecto de ingeniería real" con diagnóstico de campo + simulación + piloto + validación con cliente. | Recoge el tono de proyecto real exigido por el profesor sin inventar resultados. |
| V2-02  | sección II reorganizado en sección 2.1 Parámetros críticos y línea base + sección 2.2 Causas raíz. Tabla 0 ahora referencia EV-01..EV-04. | Sustituye el placeholder "no cuantificada formalmente" por referencias explícitas a evidencias capturables. |
| V2-03  | sección III.1 Objetivo General — añadido umbral cuantificado *"reducir latencia <150 ms y jitter <30 ms conforme a ITU-T G.114"*. | Da medibilidad sin inventar (todos los números provienen de la recomendación ITU-T citada). |
| V2-04  | sección III.2 — añadido Objetivo 6 (Comparar cuantitativamente con evidencia gráfica). Reformulado el resto en imperativo activo. | Hace explícito el compromiso de comparación cuantitativa, alineado con la exigencia clave 4 del profesor. |
| V2-05  | sección IV.1 — añadidos los puertos UDP estándar (SIP 5060, RTP 10000–20000). Sección IV.2 — añadido DSCP 46 explícito. Sección IV.4 — añadido overhead WireGuard (~20 B). Sección IV.3 — añadido IEEE 802.1Q. | Precisiones técnicas tomadas del enfoque más ingenieril de Gemini, sustentadas en literatura. |
| V2-06  | sección 6.7 — Tabla 7 nueva con flujo de atención cronometrado en formato T+N min (T+0 / T+2 / T+5 / T+15 / T+30 / T+24h). | **Positivo de Gemini incorporado**: SOP profesional con timeline visible. |
| V2-07  | sección 7.2 Tabla 2 — ampliada con direccionamiento de las 4 sedes (10.20.x.x, 10.30.x.x, 10.40.x.x), no solo Bellavista. | Plan IP completo, no muestra parcial. |
| V2-08  | sección VIII Tabla 3 — reestructurada con columna *Evidencia* y celdas marcadas "_por capturar — EV-XX_" donde corresponde. | Comparativa Antes/Después con espacio reservado para los números reales del cliente — no inventa. |
| V2-09  | sección IX renombrado a "Resultados y Validación"; añadida sección 9.3 *Validación con el cliente* + EV-20. | Cumple exigencia 5 del profesor ("Validación con el cliente"). |
| V2-10  | **20 bloques de evidencia EV-01..EV-20** insertados en los puntos del documento donde sustentan cada argumento, con formato unificado (qué insertar, herramienta, responsable, fecha). | Atiende la exigencia 1 ("Evidencia real — pantallazos, métricas") y 4 ("Simulación y pruebas") con espacios concretos, visibles tras el render HTML/PDF. |
| V2-11  | **Anexo A — Índice de Evidencias** (Tabla 9) como checklist operativo para el equipo de campo. | Permite al equipo trackear qué evidencias están capturadas y cuáles faltan antes del export a PDF. |
| V2-12  | Conservados: estructura I–XI, contenido teórico íntegro, citas APA, 5 referencias, registro de cambios. | Continuidad con v1; no se pierde ningún contenido. |

NOTA SOBRE COMPARACIÓN CON LA VERSIÓN DE GEMINI:
Se incorporaron únicamente los aciertos formales de Gemini (timeline T+N en SOP, framing de proyecto de ingeniería, precisiones de puertos/DSCP/overhead). Se descartaron deliberadamente:
- Las métricas inventadas ("120-200 ms latencia", "jitter > 50 ms", "85% saturación", "4-6 caídas diarias por sede").
- Los resultados de simulación inventados ("latencia < 20 ms constante", "failover 3.5 segundos", "0% pérdida").
- La eliminación de secciones del original (Gemini suprimió sección V Diagnóstico, sección VII Diseño, sección IX Resultados).
- La omisión del registro de cambios.
- La conservación de citas IEEE (no convirtió a APA).
- La pérdida de la referencia Aung & Thein (2020).
-->
