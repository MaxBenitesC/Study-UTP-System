---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Actividades de subneteo IPv4 (FLSM)
semana: 8
sesion: Semana 12 (paquete complementario)
tipo_documento: Diapositivas mixtas (introducción ES + material Cisco ITE PC v4.0 Chapter 1, EN)
paginas: 16
fuente_pdf: Semana_12_redes.pdf
---

# Actividades de subneteo

> **Nota de transcripción:** este PDF tiene metadatos de título `ITE PC v4.0 Chapter 1` y combina **4 diapositivas introductorias en español** (con la marca UTP de fondo claro, no la plantilla institucional roja/negra) con **11 diapositivas en inglés** extraídas del currículum oficial de Cisco Networking Academy sobre subnetting IPv4. La última página (16) está en blanco.

---

## Tabla de Contenidos

- [1. Introducción (ES)](#1-introducción-es)
  - [1.1 Motivación](#11-motivación)
  - [1.2 Saberes previos](#12-saberes-previos)
  - [1.3 Logro de la sesión](#13-logro-de-la-sesión)
- [2. Subnetting an IPv4 Network — Cisco ITE PC v4.0 Chapter 1 (EN)](#2-subnetting-an-ipv4-network--cisco-ite-pc-v40-chapter-1-en)
  - [2.1 Network Segmentation — Reasons for Subnetting](#21-network-segmentation--reasons-for-subnetting)
  - [2.2 IP Subnetting is FUNdamental](#22-ip-subnetting-is-fundamental)
  - [2.3 Basic Subnetting](#23-basic-subnetting)
  - [2.4 Subnets in Use](#24-subnets-in-use)
  - [2.5 Subnetting Formulas](#25-subnetting-formulas)
  - [2.6 Creating 4 Subnets](#26-creating-4-subnets)
  - [2.7 Creating 8 Subnets](#27-creating-8-subnets)
  - [2.8 Creating 8 Subnets (continued)](#28-creating-8-subnets-continued)
- [3. Determining the Subnet Mask (EN)](#3-determining-the-subnet-mask-en)
  - [3.1 Subnetting Based on Host Requirements](#31-subnetting-based-on-host-requirements)
  - [3.2 Subnetting To Meet Network Requirements](#32-subnetting-to-meet-network-requirements)
  - [3.3 Subnetting To Meet Network Requirements (cont)](#33-subnetting-to-meet-network-requirements-cont)

---

## Portada

**Actividades de subneteo**

[PÁGINA 1: portada minimalista, solo el título centrado en negro sobre fondo blanco — sin logo institucional ni autoría.]

---

## 1. Introducción (ES)

### 1.1 Motivación

Imaginemos que toda una universidad tuviera una sola red para:

- 💻 laboratorios
- 📶 WiFi
- 🖨 impresoras
- 📷 cámaras
- 🗄 servidores
- 👨‍🏫 docentes
- 👨‍🎓 estudiantes

Todo mezclado en una sola red.

¿Creen que funcionaría de manera eficiente?

Hoy descubriremos cómo los ingenieros de redes organizan y dividen grandes redes utilizando subneteo FLSM para mejorar:

- ✅ rendimiento
- ✅ organización
- ✅ seguridad
- ✅ comunicación

> "Las redes inteligentes no crecen desordenadas… se diseñan."

### 1.2 Saberes previos

Antes de iniciar la clase, reflexionemos:

- ✅ ¿Qué es una dirección IPv4?
- ✅ ¿Qué función cumple la máscara de subred?
- ✅ ¿Qué diferencia existe entre dirección de red y broadcast?
- ✅ ¿Qué ocurriría si todos los dispositivos estuvieran en una sola red gigante?
- ✅ ¿Qué entiendes por subneteo?

Hoy conectaremos todos estos conocimientos para aprender a crear subredes reales como lo hacen los ingenieros Cisco.

### 1.3 Logro de la sesión

- Al finalizar la sesión, el estudiante será capaz de aplicar subneteo IPv4 mediante FLSM para dividir una red en subredes organizadas, calculando correctamente:
  - dirección de red
  - dirección broadcast
  - primera y última dirección IP útil
  - cantidad de hosts y subredes
- Asimismo, implementará escenarios prácticos utilizando configuraciones básicas en entornos Cisco Packet Tracer.

> **Nota de transcripción:** la diapositiva original escribe "**Logro dela sesion**" (sin tildes, "dela" pegado). Aquí se preserva el sentido pero el título en el slide es exactamente ese.

---

## 2. Subnetting an IPv4 Network — Cisco ITE PC v4.0 Chapter 1 (EN)

### 2.1 Network Segmentation — Reasons for Subnetting

**Large networks need to be segmented into smaller sub-networks, creating smaller groups of devices and services in order to:**

- Control traffic by containing broadcast traffic within subnetwork.
- Reduce overall network traffic and improve network performance.

**Subnetting** — process of segmenting a network into multiple smaller network spaces called subnetworks or **Subnets**.

#### Communication Between Subnets

- A router is necessary for devices on different networks and subnets to communicate.
- Each router interface must have an IPv4 host address that belongs to the network or subnet that the router interface is connected to.
- Devices on a network and subnet use the router interface attached to their LAN as their default gateway.

### 2.2 IP Subnetting is FUNdamental

[FIGURA — Tres LAN representadas como recuadros amarillos lado a lado, cada una con switch central y varios PCs/servidores:

- **Student LAN**
- **Faculty LAN**
- **Admin LAN**

Sobre el conjunto hay un signo de interrogación grande color púrpura que sugiere el reto de diseño.

Pie: "Planning requires decisions on each subnet in terms of size, the number of hosts per subnet, and how host addresses will be assigned."]

### 2.3 Basic Subnetting

- Borrowing Bits to Create Subnets
- Borrowing 1 bit: $2^1 = 2$ subnets

[FIGURA — Esquema de octetos para `192.168.1.0/24`:

- Dirección: `192 . 168 . 1 . 0000 0000`
- Máscara: `255 . 255 . 255 . 0000 0000`
- Llaves: "Network Portion" (verde, 3 primeros octetos) y "Host Portion" (naranja, último octeto).

Luego, mismo octeto pero con el primer bit del cuarto octeto resaltado:

- Original: `192 . 168 . 1 . 0 000 0000` → Network `192.168.1.0/24`
- Mask:     `255 . 255 . 255 . 0 000 0000` → Mask `255.255.255.0`
- Flecha negra apunta a ese bit: "Borrowing 1 Bit from the host portion creates 2 subnets with the same subnet mask".]

| **Subnet 0**                        | **Subnet 1**                          |
|-------------------------------------|---------------------------------------|
| Network `192.168.1.`**`0-127`**`/25` | Network `192.168.1.`**`128-255`**`/25` |
| Mask: `255.255.255.`**`128`**       | Mask: `255.255.255.`**`128`**         |

### 2.4 Subnets in Use

[DIAGRAMA DE RED — Topología:

- Router R1 con dos interfaces:
  - **G0/0** conectada a un switch, conectada a **PC1** en la subred `192.168.1.0/25`.
  - **G0/1** conectada a otro switch, conectada a **PC2** en la subred `192.168.1.128/25`.]

#### Address Range for `192.168.1.0/25` Subnet

| Tipo               | Octetos                       | Equivale a    |
|--------------------|-------------------------------|---------------|
| Network Address    | `192 . 168 . 1 . 0 000 0000`  | `192.168.1.0`   |
| First Host Address | `192 . 168 . 1 . 0 000 0001`  | `192.168.1.1`   |
| Last Host Address  | `192 . 168 . 1 . 0 111 1110`  | `192.168.1.126` |
| Broadcast Address  | `192 . 168 . 1 . 0 111 1111`  | `192.168.1.127` |

#### Address Range for `192.168.1.128/25` Subnet

| Tipo               | Octetos                       | Equivale a      |
|--------------------|-------------------------------|-----------------|
| Network Address    | `192 . 168 . 1 . 1 000 0000`  | `192.168.1.128` |
| First Host Address | `192 . 168 . 1 . 1 000 0001`  | `192.168.1.129` |
| Last Host Address  | `192 . 168 . 1 . 1 111 1110`  | `192.168.1.254` |
| Broadcast Address  | `192 . 168 . 1 . 1 111 1111`  | `192.168.1.255` |

### 2.5 Subnetting Formulas

#### Calculate Number of Subnets

$$
\text{Subnets} = 2^n \quad (\text{where } n = \text{bits borrowed})
$$

[FIGURA — Octetos `192 . 168 . 1 . 0 000 0000`, flecha apunta al bit prestado: "1 bit was borrowed". $2^1 = 2$ subnets.]

#### Calculate Number of Hosts

$$
\text{Hosts} = 2^n \quad (\text{where } n = \text{host bits remaining})
$$

[FIGURA — Octetos `192 . 168 . 1 . 0 000 0000`, flecha apunta a los 7 bits de host: "7 bits remain in host field". $2^7 = 128$ hosts per subnet.]

### 2.6 Creating 4 Subnets

Borrowing 2 bits to create 4 subnets. $2^2 = 4$ **subnets**.

[FIGURA — Octetos:

- Original: `192 . 168 . 1 . 00 00 0000`
- Mask:     `255 . 255 . 255 . 00 00 0000`
- "Borrowing 2 Bits" indicado con flecha que apunta a los 2 primeros bits del cuarto octeto.

Borrowing 2 bits creates 4 subnets:]

| Subnet | Octetos                            | CIDR                |
|--------|------------------------------------|---------------------|
| Net 0  | `192 . 168 . 1 . 00 00 0000`       | `192.168.1.0/26`    |
| Net 1  | `192 . 168 . 1 . 01 00 0000`       | `192.168.1.64/26`   |
| Net 2  | `192 . 168 . 1 . 10 00 0000`       | `192.168.1.128/26`  |
| Net 3  | `192 . 168 . 1 . 11 00 0000`       | `192.168.192/26`    |

All 4 subnets use the same mask: `255 . 255 . 255 . 11 00 0000` = Mask: `255.255.255.192`.

> **Nota de transcripción:** la diapositiva muestra la cuarta subred como `192.168.192/26` (le falta el último octeto). El valor correcto sería `192.168.1.192/26`. Se preserva la grafía del original.

### 2.7 Creating 8 Subnets

Borrowing 3 bits to Create 8 Subnets. $2^3 = 8$ **subnets**.

| Subnet | Tipo      | Octetos                           | Equivale a       |
|--------|-----------|-----------------------------------|------------------|
| Net 0  | Network   | `192 . 168 . 1 . 000 0 0000`      | `192.168.1.1`    |
| Net 0  | Fist      | `192 . 168 . 1 . 000 0 0001`      | `192.168.1.1`    |
| Net 0  | Last      | `192 . 168 . 1 . 000 1 1110`      | `192.168.1.30`   |
| Net 0  | Broadcast | `192 . 168 . 1 . 000 1 1111`      | `192.168.1.31`   |
| Net 1  | Network   | `192 . 168 . 1 . 001 0 0000`      | `192.168.1.32`   |
| Net 1  | Fist      | `192 . 168 . 1 . 001 0 0001`      | `192.168.1.33`   |
| Net 1  | Last      | `192 . 168 . 1 . 001 1 1110`      | `192.168.1.62`   |
| Net 1  | Broadcast | `192 . 168 . 1 . 001 1 1111`      | `192.168.1.63`   |
| Net 2  | Network   | `192 . 160 . 1 . 010 0 0000`      | `192.168.1.64`   |
| Net 2  | Fist      | `192 . 168 . 1 . 010 0 0001`      | `192.168.1.65`   |
| Net 2  | Last      | `192 . 168 . 1 . 010 1 1110`      | `192.168.1.94`   |
| Net 2  | Broadcast | `192 . 168 . 1 . 010 1 1111`      | `192.168.1.95`   |
| Net 3  | Network   | `192 . 168 . 1 . 010 0 0000`      | `192.168.1.96`   |
| Net 3  | Fist      | `192 . 168 . 1 . 010 0 0001`      | `192.168.1.97`   |
| Net 3  | Last      | `192 . 168 . 1 . 010 1 1110`      | `192.168.1.126`  |
| Net 3  | Broadcast | `192 . 168 . 1 . 010 1 1111`      | `192.168.1.127`  |

> **Notas de transcripción:**
> - Aparece "Fist" en lugar de "First" en todas las filas (probable typo del slide original Cisco).
> - Net 0 muestra `192.168.1.1` tanto en la dirección de red como en la primera dirección de host — la dirección de red correcta sería `192.168.1.0`. Aparente errata del original.
> - Net 2 fila Network: el segundo octeto aparece como `160` en lugar de `168` (errata de OCR/original).
> - Net 3 muestra el patrón `010` en lugar de `011` en los bits prestados — errata del original.

### 2.8 Creating 8 Subnets (continued)

| Subnet | Tipo      | Octetos                           | Equivale a       |
|--------|-----------|-----------------------------------|------------------|
| Net 4  | Network   | `192 . 168 . 1 . 100 0 0000`      | `192.168.1.128`  |
| Net 4  | Fist      | `192 . 168 . 1 . 100 0 0001`      | `192.168.1.129`  |
| Net 4  | Last      | `192 . 168 . 1 . 100 1 1110`      | `192.168.1.158`  |
| Net 4  | Broadcast | `192 . 168 . 1 . 100 1 1111`      | `192.168.1.159`  |
| Net 5  | Network   | `192 . 168 . 1 . 101 0 0000`      | `192.168.1.160`  |
| Net 5  | Fist      | `192 . 168 . 1 . 101 0 0001`      | `192.168.1.161`  |
| Net 5  | Last      | `192 . 168 . 1 . 101 1 1110`      | `192.168.1.190`  |
| Net 5  | Broadcast | `192 . 168 . 1 . 101 1 1111`      | `192.168.1.191`  |
| Net 6  | Network   | `192 . 168 . 1 . 110 0 0000`      | `192.168.1.192`  |
| Net 6  | Fist      | `192 . 168 . 1 . 110 0 0001`      | `192 168 1 193`  |
| Net 6  | Last      | `192 . 168 . 1 . 110 1 1110`      | `192.168.1.222`  |
| Net 6  | Broadcast | `192 . 168 . 1 . 110 1 1111`      | `192.168.1.223`  |
| Net 7  | Network   | `192 . 168 . 1 . 111 0 0000`      | `192.168.1.224`  |
| Net 7  | Fist      | `192 . 168 . 1 . 111 0 0001`      | `192.168.1.225`  |
| Net 7  | Last      | `192 . 168 . 1 . 111 1 1110`      | `192.168.1.254`  |
| Net 7  | Broadcast | `192 . 168 . 1 . 111 1 1111`      | `192.168.1.255`  |

[DIAGRAMA DE RED — "Subnet Allocation" en el lado derecho:

- **PC1** (`192.168.1.2/27`) ─ switch ─ R1 G0/0 (`192.168.1.0/27`).
- **PC2** (`192.168.1.34/27`) ─ switch ─ R1 G0/1 (`192.168.1.32/27`), R1 = `.33`.
- Enlace serial R1 S0/0/0 (`.65`) ↔ R2 S0/0/0 (`.66`) en la subred `192.168.1.64/27`.
- **PC3** (`192.168.98.2/27`) ─ switch ─ R2 G0/0 (`192.168.1.96/27`), R2 = `.97`.
- **PC4** (`192.168.1.130/27`) ─ switch ─ R2 G0/1 (`192.168.1.128/27`), R2 = `.129`.]

> **Nota de transcripción:** PC3 aparece etiquetado `192.168.98.2/27`. Por contexto (subred `192.168.1.96/27`) parecería errata; se preserva el valor mostrado.

---

## 3. Determining the Subnet Mask (EN)

### 3.1 Subnetting Based on Host Requirements

**There are two considerations when planning subnets:**

- Number of Subnets required.
- Number of Host addresses required.

**Formula to determine number of useable hosts:**

$$
2^n - 2
$$

- $2^n$ (where $n$ is the number of host bits remaining) is used to calculate the number of hosts.
- **-2**: Subnetwork ID and broadcast address cannot be used on each subnet.

### 3.2 Subnetting To Meet Network Requirements

- It is important to balance the number of subnets needed and the number of hosts required for the largest subnet.
- Design the addressing scheme to accommodate the maximum number of hosts for each subnet.
- Allow for growth in each subnet.

[DIAGRAMA DE RED — Cinco LAN organizadas alrededor de un switch/router central, etiquetadas con su nombre y cantidad de hosts:

- **Engineering** — 40 hosts.
- **Human Resources** — 20 hosts.
- **Sales** — 20 hosts.
- **Technical Support** — 20 hosts.
- **Executive Management** — 15 hosts.]

### 3.3 Subnetting To Meet Network Requirements (cont)

#### Subnets and Addresses

Red base `172.16.0.0/22`:

```
10101100.00010000.000000 00.00000000  →  172.16.0.0/22
```

Subdivisión en `/26` (se prestan 4 bits, quedan 6 bits de host):

| #  | Binario                                        | CIDR              |
|----|------------------------------------------------|-------------------|
| 0  | `10101100.00010000.00000000.00000000`          | `172.16.0.0/26`     |
| 1  | `10101100.00010000.00000000.01000000`          | `172.16.0.64/26`    |
| 2  | `10101100.00010000.00000000.10000000`          | `172.16.0.128/26`   |
| 3  | `10101100.00010000.00000000.11000000`          | `172.16.0.192/26`   |
| 4  | `10101100.00010000.00000001.00000000`          | `172.16.1.0/26`     |
| 5  | `10101100.00010000.00000001.01000000`          | `172.16.1.64/26`    |
| 6  | `10101100.00010000.00000001.10000000`          | `172.16.1.128/26`   |
|    | *Nets 7 – 14 not shown*                        |                     |
| 15 | `10101100.00010000.00000011.10000000`          | `172.16.3.128/26`   |
| 16 | `10101100.00010000.00000011.11000000`          | `172.16.3.192/26`   |

Bajo la tabla, dos flechas indican:

- $2^4 = 16$ **subnets** (bits prestados resaltados en morado).
- $2^6 - 2 = 62$ **Hosts per subnet** (bits restantes de host resaltados en naranja).

> **Nota de transcripción:** la fila 16 etiqueta una 17.ª subred (índices 0..16). En la tabla original aparece así, pero matemáticamente con 4 bits prestados solo deberían existir las subredes 0..15. Se preserva tal cual.

---

[PÁGINA 16: en blanco]
