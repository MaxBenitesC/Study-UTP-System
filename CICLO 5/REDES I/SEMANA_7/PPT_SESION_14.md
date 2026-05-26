---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Protocolo de Internet IPv6. Direccionamiento IPv6 (configuración práctica).
semana: 7
sesion: 14
tipo_documento: Diapositivas de clase (PPT) — laboratorio Packet Tracer
paginas: 30
fuente_pdf: PPT_SESION_14.pdf
---

# Redes y Comunicación de Datos I

## Sesión 14: Protocolo de Internet IPv6. Direccionamiento IPv6.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) sobre fondo blanco con formas geométricas rojas y negras en las esquinas.]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 7
Sesión: 14

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
- [2. Listado de Materiales](#2-listado-de-materiales-a-utilizar-en-la-sesión)
- [3. Utilidad](#3-utilidad)
  - [3.1 Dudas de la Clase Anterior](#31-dudas-de-la-clase-anterior)
  - [3.2 Conocimientos Previos](#32-conocimientos-previos)
  - [3.3 Logro de aprendizaje](#33-logro-de-aprendizaje)
  - [3.4 Conocimiento de Direcciones IPv6](#34-conocimiento-de-direcciones-ipv6)
- [4. Transformación — Configuración de Direcciones IPv6](#4-transformación--configuración-de-direcciones-ipv6)
  - [4.1 Topología de la práctica](#41-topología-de-la-práctica)
  - [4.2 Tabla de direccionamiento IPv6](#42-tabla-de-direccionamiento-ipv6)
  - [4.3 Indicaciones generales](#43-indicaciones-generales)
  - [4.4 Configuración de los Switches](#44-configuración-de-los-switches)
  - [4.5 Configuración de las PCs](#45-configuración-de-las-pcs)
  - [4.6 Configuración de los Routers](#46-configuración-de-los-routers)
  - [4.7 Verificación de conectividad — ping desde PC1](#47-verificación-de-conectividad--ping-desde-pc1)
- [5. Práctica](#5-práctica)
- [6. Cierre](#6-cierre)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### Frase motivadora

> "Estaremos realmente atrapados con la tecnología cuando todo lo que realmente queramos sean sólo cosas que funcionen".
>
> — Douglas Adams

[FIGURA: Retrato fotográfico en blanco y negro de Douglas Adams, hombre con camisa clara y chaqueta oscura.]

Fuente: <https://www.forbes.com/sites/sap/2014/07/07/douglas-adams-technology-rules/>

---

## 2. Listado de Materiales a utilizar en la sesión

(equipos, herramientas, insumos y software).

**Software:**

- Cisco Packet Tracer

---

## 3. Utilidad

### 3.1 Dudas de la Clase Anterior

Recordar las reglas que tienen las direcciones IPv6.

Fuente: <https://class.utp.edu.pe>

### 3.2 Conocimientos Previos

¿Qué opinas de la forma de configurar direcciones IPv6 en una red?

[FIGURA — Diagrama de red:

- Dos routers conectados entre sí (cada uno etiquetado con "LLA Address") y una computadora conectada al router de la derecha (también con su "LLA Address").
- Flecha (1) entre los dos routers etiquetada "Routing Protocol Messages".
- Flecha (2) entre el router derecho y el PC.
- Notas:
  1. Routers use the LLA of neighbor routers to send routing updates.
  2. Hosts use the LLA of a local router as the default-gateway.]

Fuente: <https://www.netacad.com/es>

### 3.3 Logro de aprendizaje

> "Al finalizar la sesión, el estudiante diseña el direccionamiento IPv6 para usar eficientemente en la red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo".

### 3.4 Conocimiento de Direcciones IPv6

IPv6 es el sucesor de IPv4, se debe conocer como es la asignación de direcciones IPv6, como se representan las direcciones IPv6 y como configurar direcciones de red unicast y link-local del IPv6 de forma estática.

[FIGURA — Imagen de referencia sobre fondo azul oscuro/morado con la dirección ejemplo `2001 : 0DC8 : E004 : 0001 : 0000 : 0000 : 0000 : F00A`. Etiqueta inferior: 8 hextetos de 16 bits = 128 bits totales.]

Fuente: <https://www.netacad.com/es>

---

## 4. Transformación — Configuración de Direcciones IPv6

### 4.1 Topología de la práctica

Se debe realizar el siguiente diagrama con los datos especificados.

[DIAGRAMA DE RED — Topología Packet Tracer con tres LAN conectadas por tres routers ISR4331 (R1, R2, R3) en triángulo, con switches 2960-24TT (S1, S2, S3) en cada LAN:

- **LAN 1** (izquierda): `2001:DB8:1:1::/64`
  - PC1 (`2001:DB8:1:1::F`) ─ Switch S1 ─ Router R1 G0/0/0 (`2001:DB8:1:1::1`).
- **LAN 2** (arriba): `2001:DB8:1:2::/64`
  - PC2 (`2001:DB8:1:2::F`) ─ Switch S2 ─ Router R2 G0/0/0 (`2001:DB8:1:2::1`).
- **LAN 3** (derecha): `2001:DB8:1:3::/64`
  - PC3 (`2001:DB8:1:3::F`) ─ Switch S3 ─ Router R3 G0/0/0 (`2001:DB8:1:3::1`).
- **LAN 4** (serial R1 ↔ R2): `2001:DB8:1:A001::/64`
  - R1 S0/1/0 (`2001:DB8:1:A001::1`) ↔ R2 S0/1/0 (`2001:DB8:1:A001::2`).
- **LAN 5** (serial R2 ↔ R3): `2001:DB8:1:A002::/64`
  - R2 S0/1/1 (`2001:DB8:1:A002::1`) ↔ R3 S0/1/0 (`2001:DB8:1:A002::2`).

Notas al pie del diagrama:

- "En todos los routers: `#ipv6 unicast-routing`"
- "En el tema de las rutas: `ipv6 route prefijo-ipv6/longitud-prefijo { dirección-ipv6 | interfaz-salida }`"]

Fuente: <https://www.netacad.com/es>

### 4.2 Tabla de direccionamiento IPv6

Se tiene la siguiente tabla de direccionamiento IPv6:

| Equipo | Modelo       | Interfaz | Dirección IP/Prefijo IPv6   | Puerta de Enlace | Red    |
|--------|--------------|----------|-----------------------------|------------------|--------|
| PC1    | Genérico     | NIC      | `2001:DB8:1:1::F/64`        | `FE80::1`        | LAN 1  |
| PC2    | Genérico     | NIC      | `2001:DB8:1:2::F/64`        | `FE80::2`        | LAN 2  |
| PC3    | Genérico     | NIC      | `2001:DB8:1:3::F/64`        | `FE80::3`        | LAN 3  |
| R1     | Router 4331  | G0/0/0   | `2001:DB8:1:1::1/64`        | --               | LAN 1  |
| R1     | Router 4331  | S0/1/0   | `2001:DB8:1:A001::1/64`     | --               | LAN 4  |
| R2     | Router 4331  | G0/0/0   | `2001:DB8:1:2::1/64`        | --               | LAN 2  |
| R2     | Router 4331  | S0/1/0   | `2001:DB8:1:A001::2/64`     | --               | LAN 4  |
| R2     | Router 4331  | S0/1/1   | `2001:DB8:1:A002::1/64`     | --               | LAN 5  |
| R3     | Router 4331  | G0/0/0   | `2001:DB8:1:3::1/64`        | --               | LAN 3  |
| R3     | Router 4331  | S0/1/0   | `2001:DB8:1:A002::2/64`     | --               | LAN 5  |

### 4.3 Indicaciones generales

- Realizar el diagrama, configurar las direcciones IP en las computadoras y routers.
- Configurar las rutas estáticas en cada router y verificar la conectividad.

### 4.4 Configuración de los Switches

#### 4.4.1 Switch S1

Ingresamos al Switch S1.

```cisco
S1>enable
S1#configure terminal
Enter configuration commands, one per line. End with CNTL/Z.
S1(config)#hostname S1
```

#### 4.4.2 Switch S2

Ingresamos al Switch S2.

```cisco
S2>enable
S2#configure terminal
Enter configuration commands, one per line. End with CNTL/Z.
S2(config)#hostname S2
```

#### 4.4.3 Switch S3

Ingresamos al Switch S3.

```cisco
S3>enable
S3#configure terminal
Enter configuration commands, one per line. End with CNTL/Z.
S3(config)#hostname S3
```

### 4.5 Configuración de las PCs

#### 4.5.1 PC1

Ingresamos a la Computadora PC1.

[FIGURA — Panel de "IPv6 Configuration" en Packet Tracer:

- Modo: `Static` (radio seleccionado; "Automatic" descartado).
- IPv6 Address: `2001:DB8:1:1::F` / `64`.
- Link Local Address: `FE80::260:3EFF:FE1E:417B`.
- Default Gateway: `FE80::1`.
- DNS Server: (vacío).]

#### 4.5.2 PC2

Ingresamos a la Computadora PC2.

[FIGURA — Panel "IPv6 Configuration":

- Modo: `Static`.
- IPv6 Address: `2001:DB8:1:2::F` / `64`.
- Link Local Address: `FE80::201:63FF:FED0:A064`.
- Default Gateway: `FE80::2`.
- DNS Server: (vacío).]

#### 4.5.3 PC3

Ingresamos a la Computadora PC3.

[FIGURA — Panel "IPv6 Configuration":

- Modo: `Static`.
- IPv6 Address: `2001:DB8:1:3::F` / `64`.
- Link Local Address: `FE80::2D0:97FF:FE91:CB51`.
- Default Gateway: `FE80::3`.
- DNS Server: (vacío).]

### 4.6 Configuración de los Routers

#### 4.6.1 Router R1

Ingresamos al Router R1.

```cisco
hostname R1

ipv6 unicast-routing

interface GigabitEthernet0/0/0

 ipv6 address FE80::1 link-local
 ipv6 address 2001:DB8:1:1::1/64

no shutdown
```

Configuración de la interfaz serial y rutas estáticas de R1:

```cisco
interface Serial0/1/0

 ipv6 address FE80::1 link-local
 ipv6 address 2001:DB8:1:A001::1/64

no shutdown

ipv6 route 2001:DB8:1:2::/64 2001:DB8:1:A001::2
ipv6 route 2001:DB8:1:3::/64 2001:DB8:1:A001::2
ipv6 route 2001:DB8:1:A002::/64 2001:DB8:1:A001::2
```

#### 4.6.2 Router R2

Ingresamos al Router R2.

```cisco
ipv6 unicast-routing

interface GigabitEthernet0/0/0

 ipv6 address FE80::2 link-local
 ipv6 address 2001:DB8:1:2::1/64

no shutdown

interface Serial0/1/0

 ipv6 address FE80::2 link-local
 ipv6 address 2001:DB8:1:A001::2/64

no shutdown
```

Configuración de la segunda interfaz serial y rutas estáticas de R2:

```cisco
interface Serial0/1/1

 ipv6 address FE80::2 link-local
 ipv6 address 2001:DB8:1:A002::1/64

no shutdown

ipv6 route 2001:DB8:1:1::/64 2001:DB8:1:A001::1
ipv6 route 2001:DB8:1:3::/64 2001:DB8:1:A002::2
```

#### 4.6.3 Router R3

Ingresamos al Router R3.

```cisco
hostname R3

ipv6 unicast-routing

interface GigabitEthernet0/0/0

 ipv6 address FE80::3 link-local
 ipv6 address 2001:DB8:1:3::1/64

no shutdown
```

Configuración de la interfaz serial y rutas estáticas de R3:

```cisco
interface Serial0/1/0

 ipv6 address FE80::3 link-local
 ipv6 address 2001:DB8:1:A002::2/64

no shutdown

ipv6 route 2001:DB8:1:1::/64 2001:DB8:1:A002::1
ipv6 route 2001:DB8:1:2::/64 2001:DB8:1:A002::1
ipv6 route 2001:DB8:1:A001::/64 2001:DB8:1:A002::1
```

### 4.7 Verificación de conectividad — ping desde PC1

En la PC1 colocamos al Command Prompt y hacemos ping a las direcciones IPv6 a la PC2 y PC3.

#### 4.7.1 Ping a `2001:db8:1:1::f`

```text
C:\>ping 2001:db8:1:1::f

Pinging 2001:db8:1:1::f with 32 bytes of data:

Reply from 2001:DB8:1:1::F: bytes=32 time=10ms TTL=128
Reply from 2001:DB8:1:1::F: bytes=32 time=14ms TTL=128
Reply from 2001:DB8:1:1::F: bytes=32 time=10ms TTL=128
Reply from 2001:DB8:1:1::F: bytes=32 time<1ms TTL=128

Ping statistics for 2001:DB8:1:1::F:
    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
    Minimum = 0ms, Maximum = 14ms, Average = 8ms
```

> **Nota de transcripción:** la diapositiva muestra que la PC1 se hace ping a sí misma (`2001:db8:1:1::f` corresponde a la propia PC1 según la tabla §4.2). En las diapositivas siguientes se ping a las otras PCs (PC2 y PC3). Se preserva el original aunque sea atípico.

#### 4.7.2 Ping a `2001:db8:1:2::f` (PC2)

```text
C:\>ping 2001:db8:1:2::f

Pinging 2001:db8:1:2::f with 32 bytes of data:

Reply from 2001:DB8:1:2::F: bytes=32 time=17ms TTL=126
Reply from 2001:DB8:1:2::F: bytes=32 time=13ms TTL=126
Reply from 2001:DB8:1:2::F: bytes=32 time=7ms TTL=126
Reply from 2001:DB8:1:2::F: bytes=32 time=13ms TTL=126

Ping statistics for 2001:DB8:1:2::F:
    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
    Minimum = 7ms, Maximum = 17ms, Average = 12ms
```

#### 4.7.3 Ping a `2001:db8:1:3::f` (PC3)

```text
C:\>ping 2001:db8:1:3::f

Pinging 2001:db8:1:3::f with 32 bytes of data:

Reply from 2001:DB8:1:3::F: bytes=32 time=33ms TTL=125
Reply from 2001:DB8:1:3::F: bytes=32 time=2ms TTL=125
Reply from 2001:DB8:1:3::F: bytes=32 time=15ms TTL=125
Reply from 2001:DB8:1:3::F: bytes=32 time=15ms TTL=125

Ping statistics for 2001:DB8:1:3::F:
    Packets: Sent = 4, Received = 4, Lost = 0 (0% loss),
Approximate round trip times in milli-seconds:
    Minimum = 2ms, Maximum = 33ms, Average = 16ms
```

Fuente: <https://www.netacad.com/es>

---

## 5. Práctica

### Foro de Práctica

Ingresar a la Plataforma virtual de aprendizaje y realizar el siguiente foro:

**S07.s14 - Foro Direccionamiento Ipv6**

Investigar sobre las principales categorías de las direcciones IPv4.

Desarrollar la actividad establecida y responder en el mismo foro.

> **Nota de transcripción:** el cuerpo del foro dice "direcciones IPv4" pese a que el tema es IPv6. Se preserva el original.

---

## 6. Cierre

### 6.1 Preguntas de reflexión

- ¿Qué hemos aprendido el día de hoy?
- ¿Cómo puedo aplicar lo aprendido?

[FIGURA: Ilustración estilo cartoon de un joven con polo celeste y pantalón oscuro, mirando hacia arriba con expresión pensativa y un signo de interrogación dentro de una burbuja de pensamiento.]

Fuente: <https://www.pinterest.com/pin/498804021304745750/>

### 6.2 Conclusiones

- Las direcciones IPv6 unicast globales (GUA) son globalmente únicas y enrutables en Internet IPv6.
- Una dirección link-local IPv6 permite que un dispositivo se comunique con otros dispositivos con IPv6 habilitado en el mismo enlace y solo en ese enlace (subred).

---

## Referencias bibliográficas

- Kurose, J. y Ross, K. (2017). *Redes de computadoras. Un enfoque descendente.* (7ª ed.). Pearson Educación.
- Robledo, C. (2002). *Redes de computadoras.* (1ª ed.). Instituto Politécnico Nacional.

---

[CONTRAPORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) centrado sobre fondo blanco.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                                                                          |
|------------|----------|----------------------------------------------------------------------------------------------------------------------------------------|
| Figuras    | 5        | Retrato Douglas Adams, diagrama LLA (Conocimientos previos), dirección IPv6 conceptual, ilustración estudiante pensativo, 3 paneles IPv6 Config de Packet Tracer (contados como 1 grupo). |
| Tablas     | 1        | Tabla de direccionamiento IPv6 con 10 filas (PC1–PC3 + interfaces de R1, R2, R3).                                                      |
| Fórmulas   | 0        | —                                                                                                                                      |
| Código     | 12       | 3 bloques de configuración switches, 3 capturas IPv6 Config PCs, 6 bloques CLI de configuración routers (R1 base + serial/rutas, R2 base + Serial1/1 + rutas, R3 base + serial/rutas), 3 bloques `ping`. |
| Diagramas  | 2        | Topología LLA (Conocimientos previos) y topología 3-LAN/3-routers (Configuración).                                                     |
| Ejercicios | 2        | Laboratorio completo Packet Tracer (configuración IPv6 estática + rutas) y Foro S07.s14.                                               |
