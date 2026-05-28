---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Levantamiento técnico de red — sede Bellavista (call center VoIP multisede)
semana:
tipo_documento: Informe técnico de campo
paginas: 4
fuente_pdf: Levantamiento_Bellavista.pdf
---

# Informe de Levantamiento Técnico

## Tabla de Contenidos

- [1. DATOS GENERALES DE LA SEDE](#1-datos-generales-de-la-sede)
- [2. INFRAESTRUCTURA DE RED](#2-infraestructura-de-red)
  - [2.1 Equipos de red identificados](#21-equipos-de-red-identificados)
  - [2.2 Topología actual identificada](#22-topología-actual-identificada)
  - [2.3 Esquema de direccionamiento actual](#23-esquema-de-direccionamiento-actual)
- [3. SERVIDORES VICIDIAL](#3-servidores-vicidial)
  - [3.1 Servidor confirmado — asterisk2](#31-servidor-confirmado--asterisk2)
  - [3.2 Hallazgo de seguridad — IP pública directa](#32-hallazgo-de-seguridad--ip-pública-directa)
  - [3.3 ZeroTier — VPN informal ya existente](#33-zerotier--vpn-informal-ya-existente)
- [4. MEDICIONES DE RED REALIZADAS](#4-mediciones-de-red-realizadas)
  - [4.1 Latencia hacia internet — ping 8.8.8.8 (100 paquetes)](#41-latencia-hacia-internet--ping-8888-100-paquetes)
  - [4.2 Traceroute a los servidores](#42-traceroute-a-los-servidores)
- [5. PROBLEMAS IDENTIFICADOS EN EL LEVANTAMIENTO](#5-problemas-identificados-en-el-levantamiento)

---

## 1. DATOS GENERALES DE LA SEDE

| Campo | Dato relevado |
|---|---|
| Sede | Bellavista (sede principal) |
| Fecha de levantamiento | Mayo 2026 (visita 2026-05-27) |
| Total de computadoras | 82 equipos (no todos en uso simultáneo) |
| Agentes simultáneos estimados | 30 – 40 agentes activos |
| Plataforma de call center | ViciBox v.12.0.2 (ViciDial sobre openSUSE Leap) |
| Kernel del sistema | 6.4.0-150600.23.33-default |
| ISP Principal | Empresa X — Fibra empresarial — 75 Mbps |
| ISP Secundario | Claro — Fibra hogar — 600 Mbps |

Tabla 1. Datos generales de la sede Bellavista.

---

## 2. INFRAESTRUCTURA DE RED

### 2.1 Equipos de red identificados

| Equipo | Modelo | Tipo | Observación |
|---|---|---|---|
| Router principal | MikroTik RB3011 UiAS-RM | Router gestionable | 10 puertos Gigabit + SFP. Capacidad VLANs, QoS, VPN |
| Switch rack | TP-Link TL-SG1024D | Switch NO gestionable | 24 puertos Gigabit. Sin soporte VLAN |
| Switch piso (agentes) | TP-Link TL-SF1024D | Switch NO gestionable | 24 puertos 10/100 Mbps. Cuello de botella potencial |
| Firewall/UTM | Fortinet (modelo pendiente) | Firewall | Presente en rack. Estado de configuración pendiente |
| Equipo adicional | Lenovo ThinkCentre | PC/servidor | Ubicado en rack. Rol pendiente de confirmar |

Tabla 2. Inventario de equipos de red identificados en Bellavista.

### 2.2 Topología actual identificada

A partir del levantamiento visual y las mediciones realizadas, se identificó la siguiente topología de red en la sede de Bellavista:

1. El MikroTik RB3011 actúa como router principal, conectado a los dos ISP.
2. El TP-Link TL-SG1024D (Gigabit, no gestionable) está montado en el rack y distribuye conectividad a los servidores y equipos del rack.
3. El TP-Link TL-SF1024D (Fast Ethernet, no gestionable) está montado fuera del rack y conecta las computadoras de los agentes.
4. Los 3 servidores ViciDial están conectados al switch del rack.
5. El Fortinet está presente en el rack pero su estado de configuración activa no fue confirmado.

**Observación crítica**: los dos switches TP-Link son NO gestionables, lo que significa que no soportan configuración de VLANs. Para implementar segmentación VLAN en la red de agentes será necesario reemplazar el TL-SF1024D por un switch gestionable, o agregar uno entre el MikroTik y el switch actual.

Adicionalmente, el TL-SF1024D opera a máximo 100 Mbps por puerto, lo que representa un cuello de botella para la calidad de la comunicación entre los PCs de agentes y los servidores, especialmente bajo carga simultánea de 40 agentes.

### 2.3 Esquema de direccionamiento actual

| Dispositivo | IP identificada | Observación |
|---|---|---|
| Gateway / Router MikroTik | 192.168.88.1 | Puerta de enlace de la red interna |
| Servidor asterisk2 (eth0 — red interna) | 192.168.88.233 | Accesible en 1 salto desde la LAN |
| Servidor asterisk2 (eth1 — IP pública) | 161.132.165.84 | Exposición directa a internet — riesgo de seguridad |
| PC agente (ZeroTier) | 10.0.1.232 | Red ZeroTier ya instalada en algunos equipos |
| Rango de red local | 192.168.88.0/24 | Red plana sin segmentación VLAN |

Tabla 3. Direccionamiento IP identificado en la sede Bellavista.

---

## 3. SERVIDORES VICIDIAL

### 3.1 Servidor confirmado — asterisk2

| Parámetro | Valor |
|---|---|
| Nombre del host | asterisk2 |
| Sistema operativo | ViciBox v.12.0.2 (openSUSE Leap) |
| Kernel | 6.4.0-150600.23.33-default |
| IP interna (eth0) | 192.168.88.233 |
| IP pública (eth1) | 161.132.165.84 |
| Servicios activos | ViciDial, Apache, MariaDB, OpenSSH, Fail2Ban |
| Tipo de gabinete | PC de escritorio con gabinete gaming (no servidor rack) |

Tabla 4. Datos del servidor asterisk2 confirmados por captura de pantalla.

### 3.2 Hallazgo de seguridad — IP pública directa

El servidor asterisk2 tiene una segunda interfaz de red (eth1) con IP pública 161.132.165.84 expuesta directamente a internet. Esto representa un riesgo de seguridad significativo ya que el servidor Asterisk es vulnerable a ataques de SIP fraud (llamadas fraudulentas) si los puertos SIP (5060/UDP) están accesibles desde internet sin filtrado. El Fortinet presente en el rack debería estar gestionando este acceso, pero su estado de configuración activa no fue confirmado durante el levantamiento.

### 3.3 ZeroTier — VPN informal ya existente

Se identificó que al menos un PC de agentes tiene instalado ZeroTier, una solución de VPN de terceros basada en la nube, con IP asignada 10.0.1.232. Esto confirma que la necesidad de interconexión entre dispositivos ya fue identificada informalmente por el personal técnico, pero la solución adoptada no es la más adecuada para un entorno de producción: ZeroTier depende de servidores externos, no garantiza la latencia necesaria para VoIP y no está integrada con la gestión de red del MikroTik.

---

## 4. MEDICIONES DE RED REALIZADAS

### 4.1 Latencia hacia internet — ping 8.8.8.8 (100 paquetes)

| Métrica | Valor medido | Evaluación |
|---|---|---|
| Latencia mínima | 36 ms | Aceptable para VoIP (< 150 ms) |
| Latencia máxima | 38 ms | Estable — variación mínima |
| Latencia promedio | ~36-37 ms | Dentro del umbral ITU-T G.114 |
| Pérdida de paquetes | 0 % (visible) | Sin pérdida detectada durante la medición |
| TTL | 117 | Consistente — sin rerouting |

Tabla 5. Resultados del ping a 8.8.8.8 con 100 paquetes desde PC de agente en Bellavista.

**Interpretación**: la latencia hacia internet es estable y dentro del rango aceptable. Esto confirma que el ISP principal no es el problema. Las incidencias de audio ocurren por razones internas a la red local, no por la calidad del enlace WAN.

### 4.2 Traceroute a los servidores

| Destino | Saltos | Latencia | Interpretación |
|---|---|---|---|
| 192.168.88.233 (asterisk2 interna) | 1 salto | < 1 ms | Mismo segmento LAN — excelente |
| 161.132.165.84 (asterisk2 pública) | 2 saltos | < 1 ms | Pasa por router.lan (192.168.88.1) — normal |

Tabla 6. Resultados del traceroute a los servidores desde PC de agente.

---

## 5. PROBLEMAS IDENTIFICADOS EN EL LEVANTAMIENTO

| # | Problema | Evidencia | Impacto |
|---|---|---|---|
| 1 | Switches no gestionables — sin soporte VLAN | TL-SG1024D y TL-SF1024D identificados visualmente | Impide segmentación de tráfico voz/datos |
| 2 | Switch de agentes a 100 Mbps (Fast Ethernet) | TL-SF1024D — 10/100 Mbps | Cuello de botella con 40+ agentes simultáneos |
| 3 | IP pública directa en servidor Asterisk | eth1: 161.132.165.84 — sin NAT visible | Riesgo de SIP fraud y ataques externos |
| 4 | ZeroTier instalado informalmente | Adaptador ZeroTier visible en ipconfig | VPN no gestionada, dependiente de terceros |
| 5 | Red plana sin segmentación | Un solo segmento 192.168.88.0/24 | Todo el tráfico compite sin priorización |
| 6 | Sin failover automático de ISP | Levantamiento de campo | Recuperación manual ante caída del ISP |
| 7 | Sin interconexión entre las 4 sedes | Levantamiento de campo | Islas operativas sin redundancia cruzada |

Tabla 7. Problemas identificados durante el levantamiento técnico.
