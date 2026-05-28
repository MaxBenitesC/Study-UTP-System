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

## Propuesta de arquitectura de red convergente para la optimización de infraestructura VoIP en un call center multi-sede: caso de estudio
Vega Jauregui, D. J. | Benites Corazón, M. A. | Quispe Arias, K. A. | Tasayco Magallanes, G. M.

## ABSTRACT
Call centers operating with Voice over IP (VoIP) technology are highly sensitive to network quality degradation. This article presents the diagnosis and solution proposal for the network infrastructure of a call center with four operational sites in Lima, Peru (Bellavista, Magdalena, Los Olivos, and Independencia), which supports approximately 100 to 140 simultaneous agents using the ViciDial version 11 platform. The current infrastructure operates without formal segmentation, without configured Quality of Service (QoS) policies, without inter-site interconnection, and without automatic Internet Service Provider (ISP) failover mechanisms. As a result, the network registers between two and four audio incidents or dropped calls per week, with no correlation to peak usage hours, which indicates that the root cause is network misconfiguration rather than bandwidth saturation. The proposed solution consists of six components: site-to-site VPN tunneling between all four locations using WireGuard over MikroTik routers, VLAN segmentation (voice, data, and management), QoS policies prioritizing RTP/SIP traffic, automatic ISP failover at the main site, a ViciDial master-replica architecture, and formal network documentation with active monitoring using Zabbix. The implementation relies entirely on existing infrastructure and open-source tools, requiring minimal additional investment. Expected results include a reduction of audio incidents by over 70%, latency below 150 ms, and jitter below 30 ms, in accordance with ITU-T G.114 recommendations [1][3][4].

## KEYWORDS
VoIP, QoS, VLAN, VPN site-to-site, network failover, ViciDial, MikroTik, RTP, SIP, network convergence.

## I. INTRODUCCIÓN
Los call centers constituyen uno de los sectores con mayor dependencia de infraestructura de red estable y de baja latencia. La voz sobre IP (VoIP), tecnología que codifica y transmite señales de audio como paquetes de datos sobre redes TCP/IP, es el pilar tecnológico sobre el cual operan estas organizaciones [1]. A diferencia de las llamadas telefónicas tradicionales, el tráfico VoIP es extremadamente sensible a variaciones en la red: la pérdida de paquetes, la latencia elevada y el jitter generan degradación perceptible en la calidad del audio o cortes totales en las llamadas [1].

En el contexto peruano, numerosas micro y pequeñas empresas (MYPE) del sector operan con infraestructuras construidas de manera incremental, sin diseño formal ni documentación técnica. Esta situación genera vulnerabilidades que se manifiestan de forma recurrente en incidencias operativas. El presente artículo analiza el caso de un call center con cuatro sedes activas en Lima, el cual presenta problemas persistentes de calidad de audio a pesar de disponer de equipos con capacidad técnica suficiente para resolverlos.

La propuesta desarrollada aplica técnicas documentadas en la literatura académica — segmentación VLAN [5], políticas QoS [1], interconexión VPN site-to-site [2][3] y mecanismos de failover automático [4] — adaptadas al contexto específico de la empresa, con énfasis en la viabilidad económica y la reutilización de la infraestructura existente.

## II. PROBLEMA
Un call center con cuatro sedes operativas en Lima, con aproximadamente 100 a 140 agentes simultáneos sobre la plataforma ViciDial v11, registra entre 2 y 4 incidencias semanales de degradación de audio o caídas de llamadas. La ausencia de correlación con horas pico descarta la saturación de ancho de banda como causa raíz.

El problema se origina en cuatro fallas estructurales de la infraestructura de red:

- El tráfico VoIP (RTP/SIP) no tiene prioridad sobre el tráfico de datos, lo que provoca degradación de audio cuando la red está en uso simultáneo por múltiples aplicaciones.
- Las cuatro sedes operan como redes completamente aisladas, sin mecanismo de respaldo cruzado ante la caída de una sede.
- La sede de Bellavista cuenta con dos ISP contratados pero sin failover automático configurado, lo que hace que la recuperación ante fallos dependa de intervención manual.
- No existe documentación de red ni monitoreo activo, lo que impide detectar y diagnosticar fallas de forma proactiva.

Estas deficiencias afectan directamente la continuidad operativa de la empresa, la experiencia de los clientes que reciben las llamadas y la productividad de los agentes, quienes deben interrumpir su trabajo durante cada incidencia.

## III. OBJETIVOS

### Objetivo General
Proponer una arquitectura de red convergente que optimice la infraestructura VoIP del call center multi-sede, garantizando continuidad operativa, calidad de audio y gestión centralizada de agentes mediante la implementación de VLANs, QoS, VPN site-to-site y failover automático de ISP.

### Objetivos Específicos
- 1. Diagnosticar la situación actual de la infraestructura de red en las cuatro sedes, identificando las causas raíz de las incidencias de audio recurrentes.
- 2. Diseñar una arquitectura de red segmentada mediante VLANs que separe el tráfico de voz, datos y gestión en cada sede.
- 3. Proponer la configuración de políticas QoS en los equipos MikroTik existentes para priorizar el tráfico RTP/SIP sobre el resto del tráfico de red.
- 4. Definir el esquema de interconexión VPN site-to-site entre las cuatro sedes y el mecanismo de failover automático de ISP en la sede principal.

## IV. MARCO TEÓRICO

### 4.1 Voz sobre IP (VoIP)
La Voz sobre IP (VoIP) es una tecnología que permite la transmisión de comunicaciones de audio en tiempo real mediante el protocolo de internet (IP). A diferencia de la telefonía tradicional basada en circuitos conmutados, VoIP convierte la señal de voz en paquetes digitales que se transmiten sobre redes de datos compartidas [1]. Los protocolos principales que intervienen en una comunicación VoIP son el Protocolo de Iniciación de Sesión (SIP), encargado del establecimiento y terminación de llamadas, y el Protocolo de Transporte en Tiempo Real (RTP), responsable de la transmisión del audio codificado durante la llamada [1].

El rendimiento de una comunicación VoIP depende de tres parámetros críticos de red: la latencia (retardo de extremo a extremo), el jitter (variación en el retardo entre paquetes consecutivos) y la pérdida de paquetes. La recomendación ITU-T G.114 establece que la latencia máxima aceptable para comunicaciones de voz es de 150 ms, el jitter no debe superar los 30 ms, y la pérdida de paquetes debe mantenerse por debajo del 1% para garantizar una calidad de audio satisfactoria [1].

### 4.2 Calidad de Servicio (QoS)
La Calidad de Servicio (QoS) es el conjunto de técnicas y mecanismos que permiten gestionar el tráfico de red de manera diferenciada, asignando prioridades distintas a diferentes tipos de tráfico según sus requisitos de latencia, ancho de banda y tolerancia a pérdidas. En redes convergentes donde coexisten tráfico de voz, video y datos, la implementación de QoS es fundamental para garantizar que el tráfico sensible al tiempo, como VoIP, reciba el tratamiento preferencial necesario [1].

Thangam et al. [1] demostraron que la implementación de algoritmos de buffer adaptativo combinados con políticas QoS reduce significativamente el jitter en redes VoIP, mejorando la calidad percibida por el usuario. En equipos MikroTik, el QoS se implementa mediante colas de prioridad (Priority Queues) que clasifican el tráfico por protocolo y puerto de destino, asignando la clase de servicio EF (Expedited Forwarding) al tráfico RTP/SIP, lo que garantiza su procesamiento preferente sobre cualquier otro flujo de datos.

### 4.3 Segmentación de red mediante VLANs
Una Red de Área Local Virtual (VLAN) es una técnica de segmentación lógica que permite dividir una red física en múltiples dominios de broadcast independientes, sin necesidad de modificar la infraestructura de cableado existente. La segmentación VLAN mejora el rendimiento de la red al reducir el tráfico innecesario en cada segmento, fortalece la seguridad al aislar grupos de dispositivos y facilita la administración de políticas de red diferenciadas [5].

Álvarez et al. [5] demostraron que la segmentación mediante VLANs, combinada con redes definidas por software, reduce significativamente el impacto de ataques laterales y mejora el rendimiento general en entornos corporativos. En el contexto del presente caso de estudio, se propone la implementación de tres VLANs: VLAN 10 para tráfico de voz (RTP/SIP), VLAN 20 para tráfico de datos (navegación, correo, aplicaciones) y VLAN 30 para gestión de equipos de red y servidores.

### 4.4 VPN site-to-site
Una Red Privada Virtual (VPN) site-to-site establece un canal de comunicación cifrado y autenticado entre dos o más redes geográficamente distribuidas, utilizando internet como medio de transporte. A diferencia de las VPN de acceso remoto, que conectan usuarios individuales a una red corporativa, la VPN site-to-site interconecta redes completas, permitiendo que los dispositivos de cada sede se comuniquen como si pertenecieran a la misma red local [2].

Aung y Thein [2] realizaron un análisis comparativo de tecnologías VPN site-to-site incluyendo L2TP, PPTP, OpenVPN, EoIP y MPLS/VPLS, concluyendo que WireGuard y OpenVPN ofrecen el mejor equilibrio entre seguridad y rendimiento para entornos empresariales de mediana escala. Pudelko et al. [3] complementaron este análisis demostrando que WireGuard supera a OpenVPN en throughput y latencia gracias a su implementación en el espacio del kernel del sistema operativo, lo que lo convierte en la opción técnicamente superior para interconexión de sedes con tráfico VoIP.

### 4.5 Failover automático de ISP
El failover de red es el mecanismo mediante el cual un sistema conmuta automáticamente a un enlace de comunicaciones alternativo cuando detecta la falla del enlace principal, con el objetivo de minimizar el tiempo de indisponibilidad del servicio. En infraestructuras críticas como los call centers, donde la caída del enlace de internet equivale a la pérdida total de la capacidad de llamadas, la implementación de un mecanismo de failover automático es esencial para garantizar la continuidad operativa [4].

Simanjuntak et al. [4] analizaron la implementación de failover de enlace mediante protocolos de enrutamiento dinámico, demostrando que los mecanismos de conmutación automática reducen el tiempo de recuperación ante fallos de enlace de varios minutos (intervención manual) a segundos (conmutación automática). En equipos MikroTik RouterOS, el failover se implementa mediante la configuración de rutas con distancia administrativa diferenciada y verificación de gateway mediante ping periódico, sin necesidad de protocolos de enrutamiento adicionales.

### 4.6 ViciDial como plataforma de call center
ViciDial es una plataforma de call center de código abierto basada en Asterisk, el servidor de comunicaciones VoIP más utilizado a nivel mundial. Desarrollada sobre CentOS/Linux, ViciDial integra un marcador predictivo, gestión de agentes, enrutamiento de llamadas entrantes y salientes, y generación de reportes operativos. Su arquitectura permite despliegues distribuidos en múltiples servidores con roles diferenciados: servidor de telefonía (Asterisk), servidor de base de datos (MySQL) y servidor web (Apache), lo que posibilita la escalabilidad horizontal necesaria para entornos multi-sede como el analizado en el presente artículo.

## V. DIAGNÓSTICO — SITUACIÓN ACTUAL
A partir del levantamiento de información realizado en campo, se identificaron seis problemas principales que afectan la calidad del servicio y la resiliencia operativa del call center:

### 5.1 Ausencia de interconexión entre sedes
Las cuatro sedes operan como entidades completamente aisladas. No existe ningún mecanismo de VPN ni red privada que las vincule. Ante la caída de una sede, el traslado de agentes a otra requiere intervención manual, elevando el tiempo de recuperación y afectando directamente la disponibilidad del servicio.

### 5.2 QoS no configurado
El tráfico VoIP (RTP/SIP) compite en igualdad de condiciones con el tráfico de datos. El switch MikroTik gestionable disponible en Bellavista tiene capacidad nativa para establecer políticas de prioridad de tráfico, pero esta funcionalidad no ha sido implementada, lo que genera degradación de audio ante picos de uso de red [1].

### 5.3 Redundancia de ISP sin política de failover
Bellavista cuenta con dos conexiones de internet: fibra empresarial de 75 Mbps y fibra Claro de 600 Mbps. No existe failover automático configurado. Ante la caída de una línea, el cambio al enlace alternativo se realiza manualmente, incrementando el tiempo de indisponibilidad [4].

### 5.4 Incidencias recurrentes de audio
Se registran entre 2 y 4 incidencias de caídas de llamadas o degradación de audio por semana. La ausencia de correlación con horas pico descarta la saturación del ancho de banda como causa principal y apunta hacia problemas de configuración de red [1].

### 5.5 Gestión de agentes completamente silada
Cada sede administra de forma independiente su pool de agentes en ViciDial. No existe un mecanismo centralizado que permita redistribuir carga entre sedes, implementar políticas de enrutamiento corporativo ni obtener reportes consolidados.

### 5.6 Ausencia de documentación de red
No existe un diagrama de red formal para ninguna de las cuatro sedes. Esta carencia impide la gestión proactiva y dificulta el diagnóstico de fallas.

### 5.7 Inventario de infraestructura actual

| Sede | Agentes aprox. | Servidores | ISP |
|---|---|---|---|
| Bellavista | 30 – 40 | 3 (roles distintos) | 2 ISP: 75 Mbps (empresarial) + 600 Mbps (Claro) |
| Magdalena | 30 – 40 | 1 | Propio |
| Los Olivos | 20 – 30 | 1 | Propio |
| Independencia | 20 – 30 | 1 | Propio |

Tabla 1. Inventario de infraestructura actual por sede.

## VI. PROPUESTA DE SOLUCIÓN
La propuesta responde a cada causa raíz identificada en el diagnóstico mediante seis componentes interrelacionados:

### 6.1 VPN site-to-site entre sedes
Para qué sirve: unificar las cuatro sedes en una red privada corporativa, eliminando el aislamiento actual y permitiendo el failover cruzado de agentes y la administración centralizada de ViciDial.

Por qué sirve: según Aung y Thein [2] y Pudelko et al. [3], WireGuard implementado sobre RouterOS ofrece el mejor rendimiento para este tipo de despliegue, con menor overhead de cifrado que OpenVPN y mejor integración con el kernel del sistema operativo.

Cómo funciona: cada equipo MikroTik establece un túnel WireGuard cifrado con los demás nodos. El tráfico entre sedes viaja encapsulado sobre las conexiones de internet existentes, con autenticación por par de claves pública/privada.

### 6.2 Segmentación por VLANs
Para qué sirve: separar lógicamente el tráfico de voz, datos y gestión, eliminando la competencia entre tráficos de distinta naturaleza y reduciendo la superficie de ataque de la red [5].

Cómo funciona: se configuran tres VLANs en el switch MikroTik — VLAN 10 (voz), VLAN 20 (datos), VLAN 30 (gestión). Los puertos del switch se asignan a su VLAN correspondiente según el dispositivo conectado.

### 6.3 QoS para tráfico VoIP
Para qué sirve: garantizar que las llamadas activas no sufran degradación por la actividad de red de otras aplicaciones, atacando directamente la causa raíz de las incidencias de audio [1].

Cómo funciona: se configura una Priority Queue en MikroTik que asigna clase EF (Expedited Forwarding) al tráfico RTP/SIP, asegurando latencia < 150 ms y jitter < 30 ms según ITU-T G.114.

### 6.4 Failover automático de ISP
Para qué sirve: eliminar la dependencia de intervención manual ante la caída de un ISP, reduciendo el tiempo de indisponibilidad de minutos a segundos [4].

Cómo funciona: se configuran dos rutas por defecto con distancia administrativa 1 (ISP principal) y 2 (ISP secundario). MikroTik verifica periódicamente la disponibilidad del gateway mediante ping y conmuta automáticamente al enlace alternativo cuando detecta falla.

### 6.5 Arquitectura ViciDial maestro-réplica
Se reorganiza la arquitectura de servidores ViciDial hacia un esquema maestro-réplica: un servidor maestro centraliza la base de datos y la configuración global, mientras que los nodos de cada sede operan como réplicas con capacidad de modo degradado ante la caída del maestro. Esto permite reportes consolidados y enrutamiento de llamadas basado en disponibilidad de agentes en todas las sedes.

### 6.6 Documentación y monitoreo activo
Se elabora el diagrama de red lógico y físico de cada sede y se despliega Zabbix como herramienta de monitoreo, permitiendo visualizar en tiempo real el estado de los enlaces, latencia, jitter y utilización de ancho de banda.

## VII. DISEÑO DE RED
El diseño propuesto establece una arquitectura convergente que interconecta las cuatro sedes mediante VPN site-to-site y segmenta internamente cada sede mediante VLANs. A continuación se describe la arquitectura lógica propuesta:

### 7.1 Topología lógica propuesta
La arquitectura propuesta adopta una topología hub-and-spoke en la que la sede de Bellavista actúa como nodo central (hub) por ser la sede con mayor infraestructura (3 servidores, switch MikroTik gestionable y doble ISP). Las sedes de Magdalena, Los Olivos e Independencia actúan como nodos secundarios (spokes) conectados al hub mediante túneles WireGuard.

### 7.2 Esquema de direccionamiento IP propuesto

| Sede / VLAN | VLAN ID | Red propuesta | Uso |
|---|---|---|---|
| Bellavista — Voz | 10 | 10.10.10.0/24 | Softphones / tráfico RTP-SIP |
| Bellavista — Datos | 20 | 10.10.20.0/24 | PCs de agentes / navegación |
| Bellavista — Gestión | 30 | 10.10.30.0/24 | Servidores / equipos de red |
| VPN entre sedes | — | 10.0.0.0/24 | Túneles WireGuard inter-sede |

Tabla 2. Esquema de direccionamiento IP propuesto (Bellavista — referencia).

## VIII. COMPARATIVA: ANTES Y DESPUÉS

| Aspecto | Situación actual | Situación propuesta |
|---|---|---|
| Interconexión de sedes | Ninguna — 4 islas | VPN WireGuard site-to-site |
| Redundancia internet | Manual / sin política | Failover automático (distancia admin.) |
| Segmentación de red | Sin VLANs documentadas | VLAN 10 voz / 20 datos / 30 gestión |
| Calidad de servicio | Sin QoS configurado | QoS EF para RTP/SIP |
| Gestión de agentes | Silada por sede | Centralizable (ViciDial maestro) |
| Incidencias de audio | 2–4 por semana | Reducción estimada > 70 % |
| Documentación | Inexistente | Diagrama formal + Zabbix activo |

Tabla 3. Comparativa de situación antes y después de la implementación propuesta.

## IX. RESULTADOS
Dado que el presente trabajo constituye una propuesta de arquitectura de red, los resultados presentados corresponden a las métricas esperadas sustentadas en la literatura académica y en las capacidades técnicas de los equipos involucrados. La validación mediante simulación en Packet Tracer se incluirá en la siguiente fase del proyecto.

### 9.1 Métricas esperadas post-implementación

| Métrica | Valor actual | Valor esperado | Referencia |
|---|---|---|---|
| Latencia VoIP | No medida formalmente | < 150 ms | ITU-T G.114 |
| Jitter | No medido formalmente | < 30 ms | [1] |
| Pérdida de paquetes | No medida formalmente | < 1 % | [1] |
| Incidencias semanales | 2–4 por semana | > 70 % de reducción | [1][4] |
| Tiempo de failover ISP | Manual (varios min.) | < 10 segundos | [4] |

Tabla 4. Métricas esperadas post-implementación.

## X. CONCLUSIONES
La infraestructura de red actual del call center presenta deficiencias estructurales que explican las incidencias recurrentes de audio. El análisis demuestra que el problema no reside en la capacidad del ancho de banda contratado sino en la ausencia de configuración formal de los equipos existentes: el switch MikroTik gestionable disponible en Bellavista tiene capacidad técnica suficiente para implementar todas las mejoras propuestas.

La propuesta de arquitectura convergente presentada resuelve los seis problemas identificados reutilizando la infraestructura existente, con una inversión adicional estimada menor a S/ 1,100 para equipar las sedes que no disponen de router MikroTik. La implementación de VPN site-to-site, VLANs, QoS y failover automático de ISP constituye una solución técnicamente sólida, respaldada por literatura IEEE reciente [1][2][3][4][5], económicamente accesible y verificable mediante métricas de calidad de audio post-implementación.

Como trabajo futuro se propone la simulación completa de la arquitectura en Packet Tracer, el levantamiento detallado del diagrama de red de las cuatro sedes, y la implementación piloto en la sede de Bellavista para validar las métricas esperadas en condiciones reales de operación.

## XI. REFERENCIAS
[1] S. Thangam, M. Gurupriya, A. S. Revanth, D. A. Joel, C. M. Shankar, and I. S. Koushik, "VoIP QoS refinement through call sequencing using adaptive jitter buffer algorithm," in Proc. 15th Int. Conf. Computing Communication and Networking Technologies (ICCCNT), IEEE, 2024. doi: 10.1109/ICCCNT61001.2024.10725456

[2] S. T. Aung and T. Thein, "Comparative analysis of site-to-site layer 2 virtual private networks," in Proc. IEEE Conf. Computer Applications (ICCA), pp. 1–5, IEEE, 2020. doi: 10.1109/ICCA49400.2020.9022848

[3] M. Pudelko, P. Emmerich, S. Gallenmüller, and G. Carle, "Performance analysis of VPN gateways," in Proc. IFIP Networking Conf. and Workshops, pp. 325–333, IEEE, 2020. doi: 10.23919/IFIPNetworking48965.2020.9142755

[4] I. U. V. Simanjuntak, A. D. Rochendi, and L. M. Silalahi, "Simulation and analysis of link failover using routing border gateway protocol (BGP) multi-protocol label switching (MPLS) networks," in Proc. Int. Conf. Radar, Antenna, Microwave, Electronics, and Telecommunications (ICRAMET), pp. 341–346, IEEE, 2023. doi: 10.1109/ICRAMET59917.2023.10366652

[5] D. Álvarez, P. Nuño, C. T. González, F. G. Bulnes, J. C. Granda, and D. García-Carrillo, "Performance analysis of software-defined networks to mitigate private VLAN attacks," Sensors, vol. 23, no. 4, p. 1747, 2023. doi: 10.3390/s23041747