---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Introducción al diseño lógico. Redes de circuitos virtuales y de datagramas. Estructura de un router.
semana: 5
sesion: 10
tipo_documento: Diapositivas de clase (PPT)
paginas: 21
fuente_pdf: PPT_SESION_10.pdf
---

# Redes y Comunicación de Datos I

## Sesión 10: Introducción al diseño lógico. Redes de circuitos virtuales y de datagramas. Estructura de un router.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) sobre fondo blanco con formas geométricas rojas y negras en las esquinas.]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 5
Sesión: 10

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
  - [1.1 Frase motivadora](#11-frase-motivadora)
- [2. Listado de Materiales](#2-listado-de-materiales-a-utilizar-en-la-sesión)
- [3. Utilidad](#3-utilidad)
  - [3.1 Dudas de la Clase Anterior](#31-dudas-de-la-clase-anterior)
  - [3.2 Conocimientos Previos](#32-conocimientos-previos)
  - [3.3 Logro de aprendizaje](#33-logro-de-aprendizaje)
- [4. Transformación](#4-transformación)
  - [4.1 Pasos Básicos en la configuración de un router](#41-pasos-básicos-en-la-configuración-de-un-router)
  - [4.2 Configurar interfaces del router](#42-configurar-interfaces-del-router)
  - [4.3 Verificación de configuración de interfaz](#43-verificación-de-configuración-de-interfaz)
- [5. Práctica](#5-práctica)
- [6. Cierre](#6-cierre)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### 1.1 Frase motivadora

> "En otras palabras, los ordenadores súper rápidos del futuro serán como sabios autistas, es decir, podrán memorizar amplias cantidades de información, pero no podrán hacer mucho más y serán incapaces de sobrevivir por sus propios medios en el mundo real".
>
> — Michio Kaku

[FIGURA: Retrato fotográfico de Michio Kaku, físico teórico, con cabello blanco, traje gris y corbata roja con motivos.]

Fuente: <https://www.theguardian.com/books/2023/apr/22/physicist-michio-kaku-we-could-unravel-the-secrets-of-the-universe/>

---

## 2. Listado de Materiales a utilizar en la sesión

(equipos, herramientas, insumos, software)

**Software:**

- Cisco Packet Tracer

---

## 3. Utilidad

### 3.1 Dudas de la Clase Anterior

- Indicar el comando para configurar la dirección IP de un router Cisco.
- Indicar que hace el comando `# show interface ip brief`.

[FIGURA: Ilustración 3D estilizada de un router cilíndrico azul con flechas blancas que apuntan hacia adentro y hacia afuera, representando el enrutamiento de paquetes.]

Fuente: <https://class.utp.edu.pe>

### 3.2 Conocimientos Previos

Si con 1 router tengo 2 redes conectadas, con 2 routers tengo 3 redes conectadas con 3 routers. ¿Cuántas redes tendría conectadas?

[DIAGRAMA DE RED — Topología de referencia con dos routers (R1 y R2) y tres subredes:

- **Red izquierda (verde)** `192.168.10.0/24` — IPv6 `2001:db8:acad:10::/64`
  - PC-PT PC1 (`.10` / `::10`) ─ Switch `2960-24TT S1` ─ R1 interfaz `g0/0/0` (`.1` / `::1`).
- **Red central (amarilla)** `209.165.200.224/30` — IPv6 `2001:db8:feed:224::/64`
  - Enlace serial entre R1 (`s0/1/0`, `.225` / `::1`) y R2 (`s0/1/0`, `.226` / `::2`), routers modelo `ISR4331`.
- **Red derecha (naranja)** `10.1.1.0/24` — IPv6 `2001:db8:cafe:1::/64`
  - R2 interfaz hacia LAN (`.1` / `::1`) ─ Switch `2960-24TT S2` ─ PC-PT PC2 (`10` / `::10`).]

Fuente: <https://www.netacad.com/es>

### 3.3 Logro de aprendizaje

> "Al finalizar la sesión, el estudiante diseña la configuración inicial, incluidas las contraseñas en un router de red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo".

---

## 4. Transformación

### 4.1 Pasos Básicos en la configuración de un router

Comandos de configuración básica de router.

La configuración se guarda en NVRAM.

```cisco
R1 (config) # nombre de host R1
R1(config)# enable secret class
R1(config)# line console 0
R1(config-line)# password cisco
R1(config-line)# login
R1(config-line)# line vty 0 4
R1(config-line)# password cisco
R1(config-line)# login
R1(config-line)# transport input ssh telnet
R1(config-line)# exit
R1 (config) # cifrado de contraseña de servicio
R1 (config) # banner motd #
Enter TEXT message. End with a new line and the #
************************************************
WARNING: Unauthorized access is prohibited!
************************************************
R1(config)# exit
R1# copy running-config startup-config
```

Fuente: <https://www.netacad.com/es>

### 4.2 Configurar interfaces del router

#### 4.2.1 Interfaz G0/0/0 en R1

Los comandos para configurar la interfaz G0/0/0 en R1 se muestran aquí:

[DIAGRAMA DE RED — Misma topología base con resaltado naranja sobre R1 (Cisco ISR4331). Tres subredes:

- `192.168.10.0/24` / `2001:db8:acad:10::/64` (LAN izquierda con PC1 y switch).
- `209.165.200.224/30` / `2001:db8:feed:224::/64` (enlace R1 ↔ R2).
- `10.1.1.0/24` / `2001:db8:cafe:1::/64` (LAN derecha con PC2 y switch).

R1 conecta a la LAN izquierda por G0/0/0 (`.1` / `::1`) y a R2 por G0/0/1 (`.225` / `::1` → `.226` / `::2`). R2 conecta a la nube de Internet.]

```cisco
R1(config)# interface gigabitEthernet 0/0/0
R1 (config-if) # description link to LAN
R1(config-if)# ip address 192.168.10.1 255.255.255.0
R1 (config-if) # ipv6 address 2001:db8:acad:10: :1/64
R1(config-if)# no shutdown
R1(config-if)# exit
R1(config)#
*Aug 1 01:43:53.435: %LINK-3-UPDOWN: Interface GigabitEthernet0/0/0, changed state to down
*Aug 1 01:43:56.447: %LINK-3-UPDOWN: Interface GigabitEthernet0/0/0, changed state to up
*Aug 1 01:43:57.447: %LINEPROTO-5-UPDOWN: Line protocol on Interface GigabitEthernet0/0/0, changed
state to up
```

#### 4.2.2 Interfaz G0/0/1 en R1

Los comandos para configurar la interfaz G0/0/1 en R1 se muestran aquí:

[DIAGRAMA DE RED — Misma topología que en 4.2.1, ahora con el resaltado naranja indicando la interfaz G0/0/1 de R1 (la que conecta hacia R2 vía la subred `209.165.200.224/30`).]

```cisco
R1(config)# interface gigabitEthernet 0/0/1
R1(config-if)# description Link to R2
R1(config-if)# ip address 209.165.200.225 255.255.255.252
R1 (config-if) # ipv6 address 2001:db8:feed:224: :1/64
R1(config-if)# no shutdown
R1(config-if)# exit
R1(config)#
*Ago 1 01:46:29 .170: %LINK-3-UPDOWN: Interfaz GigabiteThernet0/0/1, estado cambiado a inactivo
*Aug 1 01:46:32.171: %LINK-3-UPDOWN: Interface GigabitEthernet0/0/1, changed state to up
*Aug 1 01:46:33.171: %LINEPROTO-5-UPDOWN: Line protocol on Interface GigabitEthernet0/0/1, changed
state to up
```

Fuente: <https://www.netacad.com/es>

### 4.3 Verificación de configuración de interfaz

#### 4.3.1 `show ip interface brief` / `show ipv6 interface brief`

Para verificar la configuración de la interfaz, utilice los comandos **show ip interface brief** y **show ipv6 interface brief** que se muestran aquí:

```text
R1# show ip interface brief
Interface              IP-Address       OK? Method Status                Protocol
GigabitEthernet0/0/0   192.168.10.1     YES manual up                    up
GigabiteThernet0/0/1   209.165.200.225  YES manual up                    up
Vlan1                  unassigned       YES unset  administratively down down
```

```text
R1# show ipv6 interface brief
GigabitEthernet0/0/0 [up/up]
    FE80: :201:C9FF:FE 89:4501
    2001:DB8:ACAD:10: :1
GigabitEthernet0/0/1 [up/up]
    FE80: :201:C9FF:FE 89:4502
    2001:DB8:ALIMENTACIÓN:224: :1
Vlan1 [administratively down/down]
    unassigned
R1#
```

#### 4.3.2 `show ip route` / `show ipv6 route`

Mostrar el contenido de las tablas de enrutamiento IP con los comandos **show ip route** y **show ipv6 route** como se muestra a continuación:

```text
R1# show ip route
< output omitted>
Gateway of last resort is not set
      192.168.10.0/24 is variably subnetted, 2 subnets, 2 masks
C 192.168.10.0/24 está directamente conectado, GigabitEthernet0/0/0
L 192.168.10.1/32 está directamente conectado, GigabitEthernet0/0/0
      209.165.200.0/24 is variably subnetted, 2 subnets, 2 masks
C 209.165.200.224/30 está directamente conectado, GigabitEthernet0/0/1
L 209.165.200.225/32 está directamente conectado GigabitEthernet0/0/1
R1#
```

```text
R1# show ipv6 route
<output omitted>
C 2001:DB8:ACAD:10: :/64 [0/0]
    via GigabitEthernet0/0/0, directly connected
L 2001:DB8:ACAD:10: :1/128 [0/0]
    via GigabitEthernet0/0/0, receive
C 2001:DB8:FEED:224: :/64 [0/0]
    via GigabitEthernet0/0/1, directly connected
L 2001:DB8:ALIMENTACIÓN:224: :1/128 [0/0]
    a través de GigabiteThernet0/0/1, reciba
L FF00::/8 [0/0]
    via Null0, receive
R1#
```

#### 4.3.3 `show interfaces`

Mostrar estadísticas de todas las interfaces con el comando **show interfaces**, como se muestra a continuación:

```text
R1# show interfaces gig0/0/0
GigabitEthernet0/0/0 is up, line protocol is up
  El hardware es ISR4321-2x1GE, la dirección es a0e0.af0d.e140 (bia
a0e0.af0d.e140)
  Description: Link to LAN
  Internet address is 192.168.10.1/24
  MTU 1500 bytes, BW 100000 Kbit/sec, DLY 100 usec,
    reliability 255/255, txload 1/255, rxload 1/255
  Encapsulation ARPA, loopback not set
  Keepalive not supported
  Full Duplex, 100Mbps, link type is auto, media type is RJ45
  output flow-control is off, input flow-control is off
  ARP type: ARPA, ARP Timeout 04:00:00
  Last input 00:00:01, output 00:00:35, output hang never
  Last clearing of "show interface" counters never
  Input queue: 0/375/0/0 (size/max/drops/flushes); Total output drops: 0
  Queueing strategy: fifo
  Output queue: 0/40 (size/max)
  5 minute input rate 0 bits/sec, 0 packets/sec
  5 minute output rate 0 bits/sec, 0 packets/sec
    1180 packets input, 109486 bytes, 0 no buffer
    Received 84 broadcasts (0 IP multicasts)
    0 runts, 0 giants, 0 throttles
<output omitted>
```

#### 4.3.4 `show ip interface`

Muestra las estadísticas IPv4 para las interfaces del router con el comando **show ip interface**, como se muestra a continuación:

```text
R1# show ip interface g0/0/0
GigabitEthernet0/0/0 is up, line protocol is up
  Internet address is 192.168.10.1/24
  Broadcast address is 255.255.255.255
  Address determined by setup command
  MTU is 1500 bytes
  Helper address is not set
  Directed broadcast forwarding is disabled
  Outgoing Common access list is not set
  Outgoing access list is not set
  Inbound Common access list is not set
  Inbound access list is not set
  Proxy ARP is enabled
  Local Proxy ARP is disabled
  Security level is default
  Split horizon is enabled
  ICMP redirects are always sent
  ICMP unreachables are always sent
  ICMP mask replies are never sent
  IP fast switching is enabled
  IP Flow switching is disabled
<output omitted>
```

#### 4.3.5 `show ipv6 interface`

Muestra las estadísticas IPv6 para las interfaces del router con el comando **show ipv6 interface** que se muestra aquí:

```text
R1# show ipv6 interface g0/0/0
GigabitEthernet0/0/0 is up, line protocol is up
  IPv6 is enabled, link-local address is
FE80::868A:8DFF:FE44:49B0
  No Virtual link-local address(es):
  Description: Link to LAN
  Global unicast address(es):
    2001:DB8:ACAD:10: :1, la subred es 2001:DB8:ACAD:10: :/64
  Joined group address(es):
    FF02::1
    FF02::1:FF00:1
    FF02: :1:FF 44:49 B0
  MTU is 1500 bytes
  ICMP error messages limited to one every 100 milliseconds
  ICMP redirects are enabled
  ICMP unreachables are sent
  ND DAD is enabled, number of DAD attempts: 1
  ND reachable time is 30000 milliseconds (using 30000)
  ND NS retransmit interval is 1000 milliseconds
R1#
```

Fuente: <https://www.netacad.com/es>

---

## 5. Práctica

### Foro de Práctica

Ingresar a la Plataforma virtual de aprendizaje y realizar el siguiente foro:

**S05.s10 - Foro Configuración básica del router**

Investigar sobre los comandos de configuración del router, configurando un router con dos computadoras.

Desarrollar la actividad establecida y responder en el mismo foro.

---

## 6. Cierre

### Conclusiones

Las siguientes tareas deben completarse al configurar la configuración inicial en un router.

- Configure el nombre del dispositivo.
- Proteja el modo EXEC con privilegios.
- Proteger el modo EXEC de usuario.
- Proteger el acceso remoto por Telnet y SSH.
- Proteja todas las contraseñas del archivo de configuración.
- Proporcione una notificación legal.
- Guarde la configuración.

---

## Referencias bibliográficas

- Kurose, J. y Ross, K. (2017). *Redes de computadoras. Un enfoque descendente.* (7ª ed.). Pearson Educación.
- Robledo, C. (2002). *Redes de computadoras.* (1ª ed.). Instituto Politécnico Nacional.

---

[CONTRAPORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) centrado sobre fondo blanco.]

---

## Notas de transcripción (OCR-aware)

Las siguientes anomalías están presentes en el material original y se preservan tal cual:

- `ipv6 address 2001:db8:acad:10: :1/64` y entradas similares contienen un espacio entre los dos puntos consecutivos (en una dirección IPv6 limpia sería `2001:db8:acad:10::1/64`). Se reproduce el espacio porque aparece así en las capturas de pantalla.
- `GigabiteThernet0/0/1` aparece con mayúscula intermedia (T) en algunos mensajes de log y en la salida `show ip interface brief`. Es un error tipográfico de la diapositiva original.
- En las salidas de `show ipv6 interface brief` y `show ipv6 route` aparece la cadena `2001:DB8:ALIMENTACIÓN:224: :1` donde correspondería `2001:DB8:FEED:224: :1`. Parece producto de una traducción automática que tradujo la palabra "feed" → "alimentación" en parte del texto. Se preserva tal como aparece en el slide.
- Algunos mensajes de log y descripciones aparecen mezclados en español e inglés en la misma captura (p. ej., "está directamente conectado" y "directly connected" en la salida de `show ip route`). Se conserva el bilingüismo del original.

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                                                          |
|------------|----------|------------------------------------------------------------------------------------------------------------------------|
| Figuras    | 2        | Retrato Michio Kaku, ilustración router 3D.                                                                            |
| Tablas     | 0        | —                                                                                                                      |
| Fórmulas   | 0        | —                                                                                                                      |
| Código     | 8        | 1 bloque de configuración base + 2 bloques CLI de configuración de interfaces + 5 capturas de comandos `show`.         |
| Diagramas  | 3        | 3 topologías de red Packet Tracer (Conocimientos previos + G0/0/0 resaltado + G0/0/1 resaltado), todas la misma base.  |
| Ejercicios | 2        | Pregunta de Conocimientos previos (acertijo n-routers ↔ n+1-redes) + Foro S05.s10.                                     |
