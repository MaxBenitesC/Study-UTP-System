---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Subredes con máscara de subred de longitud fija (FLSM).
semana: 8
sesion: 15
tipo_documento: Diapositivas de clase (PPT)
paginas: 24
fuente_pdf: PPT_SESION_15.pdf
---

# Redes y Comunicación de Datos I

## Sesión 15: Subredes con máscara de subred de longitud fija — FLSM.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) sobre fondo blanco con formas geométricas rojas y negras en las esquinas.]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 8
Sesión: 15

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
- [2. Utilidad](#2-utilidad)
  - [2.1 Dudas de la Clase Anterior](#21-dudas-de-la-clase-anterior)
  - [2.2 Conocimientos Previos](#22-conocimientos-previos)
  - [2.3 Logro de aprendizaje](#23-logro-de-aprendizaje)
  - [2.4 Subneteo de Direcciones IP](#24-subneteo-de-direcciones-ip)
- [3. Transformación](#3-transformación)
  - [3.1 División en subredes en el límite del octeto](#31-división-en-subredes-en-el-límite-del-octeto)
  - [3.2 Crear subredes con un prefijo /16](#32-crear-subredes-con-un-prefijo-16)
  - [3.3 Crear 100 subredes con un prefijo de barra diagonal 16](#33-crear-100-subredes-con-un-prefijo-de-barra-diagonal-16)
  - [3.4 Minimice las direcciones IPv4 de host no utilizadas](#34-minimice-las-direcciones-ipv4-de-host-no-utilizadas)
  - [3.5 División de una red IPv6 en subredes](#35-división-de-una-red-ipv6-en-subredes)
  - [3.6 Subnetear una red IPv6](#36-subnetear-una-red-ipv6)
- [4. Práctica](#4-práctica)
- [5. Cierre](#5-cierre)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### Frase motivadora

> "No se puede dotar ni siquiera a la mejor máquina de iniciativa; la apisonadora más alegre jamás podrá plantar flores".
>
> — Walter Lippmann

[FIGURA: Retrato fotográfico en blanco y negro de Walter Lippmann, hombre mayor sentado en un sillón con anteojos en la mano, biblioteca al fondo.]

Fuente: <https://www.infobae.com/america/opinion/2024/05/18/walter-lippmann-el-gran-teorico-de-la-opinion-publica-que-advirtio-sobre-la-expansion-europea-de-rusia/>

---

## 2. Utilidad

### 2.1 Dudas de la Clase Anterior

Indicar la configuración de una ruta en direccionamiento IPv6.

[DIAGRAMA DE RED — Topología en triángulo con 3 routers ISR4331 (R1, R2, R3) y 3 LANs (idéntica a la usada en la sesión 14):

- **LAN 1**: `2001:DB8:1:1::/64`, PC1 (`2001:DB8:1:1::F`) ─ Switch S1 ─ R1 (`2001:DB8:1:1::1`).
- **LAN 2**: `2001:DB8:1:2::/64`, PC2 (`2001:DB8:1:2::F`) ─ Switch S2 ─ R2 (`2001:DB8:1:2::1`).
- **LAN 3**: `2001:DB8:1:3::/64`, PC3 (`2001:DB8:1:3::F`) ─ Switch S3 ─ R3 (`2001:DB8:1:3::1`).
- **Serial R1↔R2** (LAN 4): `2001:DB8:1:A001::/64`, R1 (`::1`) ↔ R2 (`::2`).
- **Serial R2↔R3** (LAN 5): `2001:DB8:1:A002::/64`, R2 (`::1`) ↔ R3 (`::2`).

Notas al pie:

- "En todos los routers: `#ipv6 unicast-routing`"
- "En el tema de las rutas: `ipv6 route prefijo-ipv6/longitud-prefijo { dirección-ipv6 | interfaz-salida }`"]

Fuente: <https://class.utp.edu.pe>

### 2.2 Conocimientos Previos

- ¿Qué opinas de la cantidad de direcciones IP que se desperdician?
- ¿Qué consideras como subneteo de una red IPv4?

#### Tabla — Repaso de máscaras por prefijo (clases tradicionales)

| Longitud de prefijo | Máscara de subred | Máscara de subred en sistema binario (n = red, h = host)                                              | Cantidad de hosts |
|--------------------:|-------------------|--------------------------------------------------------------------------------------------------------|------------------:|
| /8                  | 255.0.0.0         | `nnnnnnnn.hhhhhhhh.hhhhhhhh.hhhhhhhh` / `11111111.00000000.00000000.00000000`                          | 16777214          |
| /16                 | 255.255.0.0       | `nnnnnnnn.nnnnnnnn.hhhhhhhh.hhhhhhhh` / `11111111.11111111.00000000.00000000`                          | 65534             |
| /24                 | 255.255.255.0     | `nnnnnnnn.nnnnnnnn.nnnnnnnn.hhhhhhhh` / `11111111.11111111.11111111.00000000`                          | 254               |

Fuente: <https://www.netacad.com/es>

### 2.3 Logro de aprendizaje

> "Al finalizar la sesión, el estudiante reconoce el subneteo IPv4 para usar eficientemente las direcciones IP en la red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo".

### 2.4 Subneteo de Direcciones IP

La utilidad del direccionamiento IP radica en su capacidad para identificar y localizar dispositivos en una red, permitiendo la comunicación y el intercambio de datos entre ellos.

[DIAGRAMA DE RED — Topología LAN: router en la parte superior conectado a un switch central; del switch parten enlaces a 4 PCs (`172.16.4.1/24`, `172.16.4.2/24`, `172.16.4.3 / 224.10.10.5/24`, `172.16.4.4 / 224.10.10.5/24`) y a un servidor (`172.16.4.253/24`). Flecha naranja del PC `172.16.4.1/24` (con sobre amarillo) indica envío de paquete. Etiqueta "Source: 172.16.4.1/24".]

Fuente: <https://www.netacad.com/es>

---

## 3. Transformación

### 3.1 División en subredes en el límite del octeto

Las redes se subdividen con más facilidad en el límite del octeto de /8, /16 y /24.

Observe que el uso de longitudes de prefijo más extensas disminuye la cantidad de hosts por subred.

#### Tabla — Máscaras por prefijo en límite de octeto

| Longitud de prefijo | Máscara de subred | Máscara de subred en sistema binario (n = red, h = host)                                              | Cantidad de hosts |
|--------------------:|-------------------|--------------------------------------------------------------------------------------------------------|------------------:|
| /8                  | 255.0.0.0         | `nnnnnnnn.hhhhhhhh.hhhhhhhh.hhhhhhhh` / `11111111.00000000.00000000.00000000`                          | 16777214          |
| /16                 | 255.255.0.0       | `nnnnnnnn.nnnnnnnn.hhhhhhhh.hhhhhhhh` / `11111111.11111111.00000000.00000000`                          | 65534             |
| /24                 | 255.255.255.0     | `nnnnnnnn.nnnnnnnn.nnnnnnnn.hhhhhhhh` / `11111111.11111111.11111111.00000000`                          | 254               |

#### Ejemplo 1 — Red 10.0.0.0/8 subdivida en /16

En la primera tabla `10.0.0.0/8` se subred usando `/16`.

| Dirección de subred (256 subredes posibles) | Rango de host (65,534 hosts posibles por subred) | Dirección       |
|---------------------------------------------|--------------------------------------------------|-----------------|
| **10.0**.0.0/**16**                         | **10.0**.0.1 - **10.0**.255.254                  | **10.0**.255.255 |
| **10.1**.0.0/**16**                         | **10.1**.0.1 - **10.1**.255.254                  | **10.1**.255.255 |
| **10.2**.0.0/**16**                         | **10.2**.0.1 - **10.2**.255.254                  | **10.2**.255.255 |
| **10,3**.0.0/**16**                         | **10,3**.0.1 - **10,3**.255.254                  | **10.3**.255.255 |
| **10,4**.0.0/**16**                         | **10,4**.0.1 - **10,4**.255.254                  | **10.4**.255.255 |
| **10,5**.0.0/**16**                         | **10,5**.0.1 - **10,5**.255.254                  | **10.5**.255.255 |
| **10,6**.0.0/**16**                         | **10,6**.0.1 - **10,6**.255.254                  | **10.6**.255.255 |
| **10,7**.0.0/**16**                         | **10,7**.0.1 - **10,7**.255.254                  | **10.7**.255.255 |
| …                                           | …                                                | …               |
| **10.255**,0.0/**16**                       | **10.255**.0.1 - **10.255**.255.254              | **10.255**.255.255 |

> **Nota de transcripción:** la tabla original usa comas en lugar de puntos en varias filas (`10,3.0.0/16`, `10,4.0.0/16`, etc.). Se preserva tal cual aparece — clara errata tipográfica del slide; deberían ser puntos.

#### Ejemplo 2 — Red 10.0.0.0 subdividida en /24

En la segunda tabla, una máscara `/24`.

| Dirección de subred (65,536 subredes posibles) | Rango de host (254 hosts posibles por subred) | Dirección          |
|------------------------------------------------|------------------------------------------------|--------------------|
| **10.0.0**.0/**24**                            | **10.0.0**.1 - **10.0.0**.254                  | **10.0.0**.255     |
| **10.0.1**.0/**24**                            | **10.0.1**.1 - **10.0.1**.254                  | **10.0.1**.255     |
| **10.0.2**.0/**24**                            | **10.0.2**.1 - **10.0.2**.254                  | **10.0.2**.255     |
| …                                              | …                                              | …                  |
| **10.0.255**.0/**24**                          | **10.0.255**.1 - **10.0.255**.254              | **10.0.255**.255   |
| **10.1.0**.0/**24**                            | **10.1.0**.1 - **10.1.0**.254                  | **10.1.0**.255     |
| **10.1.1**.0/**24**                            | **10.1.1**.1 - **10.1.1**.254                  | **10.1.1**.255     |
| **10.1.2**.0/**24**                            | **10.1.2**.1 - **10.1.2**254                   | **10.1.2**.255     |
| …                                              | …                                              | …                  |
| **10.100,0**.0/**24**                          | **10,100,0**,1 - 10,100,0 ,254                 | **10.100,0**.255   |
| …                                              | …                                              | …                  |
| **10.255.255**.0/**24**                        | **10.255.255**.1 - **10.2255.255**.254         | **10.255.255**.255 |

> **Nota de transcripción:** varias celdas aparecen con comas en lugar de puntos (`10.100,0.0/24`, `10,100,0,1`, etc.) y una con valor mal formado (`10.2255.255.254`). Erratas del original; se preservan.

#### Tabla — Seis formas de subred de una red /24

| Longitud de prefijo | Máscara de subred  | Máscara de subred en sistema binario (n = red, h = host)                              | Cantidad de subredes | Cantidad de hosts |
|--------------------:|--------------------|----------------------------------------------------------------------------------------|---------------------:|------------------:|
| /25                 | 255.255.255.128    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nhhhhhhh` / `11111111.11111111.11111111.10000000`          | **2**                | 126               |
| /26                 | 255.255.255.192    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnhhhhhh` / `11111111.11111111.11111111.11000000`          | **4**                | 62                |
| /27                 | 255.255.255.224    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnhhhhh` / `11111111.11111111.11111111.11100000`          | **8**                | 30                |
| /28                 | 255.255.255.240    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnhhhh` / `11111111.11111111.11111111.11110000`          | **16**               | 14                |
| /29                 | 255.255.255.248    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnnhhh` / `11111111.11111111.11111111.11111000`          | **32**               | 6                 |
| /30                 | 255.255.255.252    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnnnhh` / `11111111.11111111.11111111.11111100`          | **64**               | 2                 |

Fuente: <https://www.netacad.com/es>

### 3.2 Crear subredes con un prefijo /16

La tabla resalta todos los escenarios posibles para dividir en subredes un prefijo /16.

| Longitud de prefijo | Máscara de subred | Dirección de red (n = red, h = host)                                                       | Cantidad de subredes | Cantidad de hosts |
|--------------------:|-------------------|---------------------------------------------------------------------------------------------|---------------------:|------------------:|
| /17                 | 255.255.**128**.0 | `nnnnnnnn.nnnnnnnn.nhhhhhhh.hhhhhhhh` / `11111111.11111111.10000000.00000000`               | **2**                | 32766             |
| /18                 | 255.255.**192**.0 | `nnnnnnnn.nnnnnnnn.nnhhhhhh.hhhhhhhh` / `11111111.11111111.11000000.00000000`               | **4**                | 16382             |
| /19                 | 255.255.**224**.0 | `nnnnnnnn.nnnnnnnn.nnnhhhhh.hhhhhhhh` / `11111111.11111111.11100000.00000000`               | **8**                | 8190              |
| /20                 | 255.255.**240**.0 | `nnnnnnnn.nnnnnnnn.nnnnhhhh.hhhhhhhh` / `11111111.11111111.11110000.00000000`               | **16**               | 4094              |
| /21                 | 255.255.**248**.0 | `nnnnnnnn.nnnnnnnn.nnnnnhhh.hhhhhhhh` / `11111111.11111111.11111000.00000000`               | **32**               | 2046              |
| /22                 | 255.255.**252**.0 | `nnnnnnnn.nnnnnnnn.nnnnnnhh.hhhhhhhh` / `11111111.11111111.11111100.00000000`               | **64**               | 1022              |
| /23                 | 255.255.**254**.0 | `nnnnnnnn.nnnnnnnn.nnnnnnnh.hhhhhhhh` / `11111111.11111111.11111110.00000000`               | **128**              | 510               |
| /24                 | 255.255.**255.**0 | `nnnnnnnn.nnnnnnnn.nnnnnnnn.hhhhhhhh` / `11111111.11111111.11111111.00000000`               | **256**              | 254               |
| /25                 | 255.255.255.**128** | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nhhhhhhh` / `11111111.11111111.11111111.10000000`             | **512**              | 126               |
| /26                 | 255.255.255.**192** | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnhhhhhh` / `11111111.11111111.11111111.11000000`             | **1024**             | 62                |
| /27                 | 255.255.255.**224** | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnhhhhh` / `11111111.11111111.11111111.11100000`             | **2048**             | 30                |
| /28                 | 255.255.255.**240** | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnhhhh` / `11111111.11111111.11111111.11110000`             | **4096**             | 14                |
| /29                 | 255.255.255.**248** | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnnhhh` / `11111111.11111111.11111111.11111000`             | **8192**             | 6                 |
| /30                 | 255.255.255.**252** | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnnnhh` / `11111111.11111111.11111111.11111100`             | **16384**            | 2                 |

Fuente: <https://www.netacad.com/es>

### 3.3 Crear 100 subredes con un prefijo de barra diagonal 16

Imagine una gran empresa que requiere, como mínimo, 100 subredes y eligió la dirección privada `172.16.0.0/16` como su dirección de red interna.

Para satisfacer el requisito de 100 subredes para la empresa, se necesitarían prestar 7 bits (es decir, $2^7$ = 28 subredes) (para un total de 128 subredes).

> **Nota de transcripción:** el slide indica "$2^7$ = 28 subredes". El cálculo correcto es $2^7 = 128$. Aparente errata del original (probablemente la superíndice "7" se imprimió pegada al "12", quedando "28"). Se preserva la grafía visible.

La figura muestra el número de subredes que se pueden crear al tomar prestados bits del tercer octeto y el cuarto octeto.

Observe que ahora hay hasta 14 bits de host que se pueden tomar prestados (es decir, los dos últimos bits no se pueden tomar prestados).

[FIGURA — Esquema de la dirección `172.16.0.0` (`nnnnnnnn.nnnnnnnn.hhhhhhhh.hhhhhhhh`) con flechas naranjas que conectan cada cantidad de bits prestados con el número resultante de subredes:

- Borrowing 1 bit: $2^1 = 2$
- Borrowing 2 bit: $2^2 = 4$
- Borrowing 3 bit: $2^3 = 8$
- Borrowing 4 bit: $2^4 = 16$
- Borrowing 5 bit: $2^5 = 32$
- Borrowing 6 bit: $2^6 = 64$
- Borrowing 7 bit: $2^7 = 128$
- Borrowing 8 bit: $2^8 = 256$
- Borrowing 9 bit: $2^9 = 512$
- Borrowing 10 bit: $2^{10} = 1024$
- Borrowing 11 bit: $2^{11} = 2048$
- Borrowing 12 bit: $2^{12} = 4096$
- Borrowing 13 bit: $2^{13} = 8192$
- Borrowing 14 bit: $2^{14} = 16384$]

Fuente: <https://www.netacad.com/es>

### 3.4 Minimice las direcciones IPv4 de host no utilizadas

Existen dos factores que se deben tener en cuenta al planificar las subredes:

- El número de direcciones de host requeridas para cada red.
- El número de subredes individuales necesarias.

#### Tabla — Repaso /25 a /30

| Longitud de prefijo | Máscara de subred  | Máscara de subred en sistema binario (n = red, h = host)                              | Cantidad de subredes | Cantidad de hosts |
|--------------------:|--------------------|----------------------------------------------------------------------------------------|---------------------:|------------------:|
| /25                 | 255.255.255.128    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nhhhhhhh` / `11111111.11111111.11111111.10000000`          | **2**                | 126               |
| /26                 | 255.255.255.192    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnhhhhhh` / `11111111.11111111.11111111.11000000`          | **4**                | 62                |
| /27                 | 255.255.255.224    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnhhhhh` / `11111111.11111111.11111111.11100000`          | **8**                | 30                |
| /28                 | 255.255.255.240    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnhhhh` / `11111111.11111111.11111111.11110000`          | **16**               | 14                |
| /29                 | 255.255.255.248    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnnhhh` / `11111111.11111111.11111111.11111000`          | **32**               | 6                 |
| /30                 | 255.255.255.252    | `nnnnnnnn.nnnnnnnn.nnnnnnnn.nnnnnnhh` / `11111111.11111111.11111111.11111100`          | **64**               | 2                 |

Fuente: <https://www.netacad.com/es>

### 3.5 División de una red IPv6 en subredes

IPv6 se diseñó teniendo en cuenta las subredes.

- Se utiliza un campo ID de subred independiente en IPv6 GUA para crear subredes.
- El campo ID de subred es el área entre el Prefijo de enrutamiento global y la ID de interfaz.

[FIGURA — Esquema de una dirección IPv6 GUA dividida en tres campos por longitud:

- **48 bits** — Global Routing Prefix (morado).
- **16 bits** — Subnet ID (verde-azulado).
- **64 bits** — Interface ID (verde oliva).

Etiqueta inferior: "A /48 routing prefix + 16 bit Subnet ID = /64 prefix".]

Fuente: <https://www.netacad.com/es>

### 3.6 Subnetear una red IPv6

Dado el prefijo de enrutamiento global `2001:db8:acad::/48` con un ID de subred de 16 bits.

- Permite 65.536 subredes /64.
- El prefijo de enrutamiento global es igual para todas las subredes.
- Solo se incrementa el hexteto de la ID de subred en sistema hexadecimal para cada subred.

[FIGURA — Recuadro a la izquierda con la nota "Increment subnet ID to create 65,536 subnets", flecha roja apuntando al recuadro derecho con el listado de subredes:

```
2001:db8:acad:0000::/64
2001:db8:acad:0001::/64
2001:db8:acad:0002::/64
2001:db8:acad:0003::/64
2001:db8:acad:0004::/64
2001:db8:acad:0005::/64
2001:db8:acad:0006::/64
2001:db8:acad:0007::/64
2001:db8:acad:0008::/64
2001:db8:acad:0009::/64
2001:db8:acad:000a::/64
2001:db8:acad:000b::/64
2001:db8:acad:000c::/64
Subnets 13 – 65,534 not shown
2001:db8:acad:ffff::/64
```
]

Fuente: <https://www.netacad.com/es>

---

## 4. Práctica

### Foro de Práctica

Ingresar a la Plataforma virtual de aprendizaje y realizar el siguiente foro:

**S08.s15 - Foro Subneteo FLSM**

Investigar sobre el subneteo FLSM en direcciones IPv4.

Desarrollar la actividad establecida y responder en el mismo foro.

---

## 5. Cierre

### 5.1 Preguntas de reflexión

- ¿Qué hemos aprendido el día de hoy?
- ¿Cómo puedo aplicar lo aprendido?

[FIGURA: Ilustración estilo cartoon de un joven con polo celeste y pantalón oscuro, mirando hacia arriba con expresión pensativa y un signo de interrogación dentro de una burbuja de pensamiento.]

Fuente: <https://www.pinterest.com/pin/498804021304745750/>

### 5.2 Conclusiones

- Reduzca los dominios de broadcast grandes mediante subredes para crear dominios de broadcast más pequeños, reducir el tráfico de red general y mejorar el rendimiento de la red.
- Cree subredes IPv4 utilizando uno o más de los bits del host como bits de red. Las redes se subdividen con más facilidad en el límite del octeto de /8, /16 y /24.

---

## Referencias bibliográficas

- Kurose, J. y Ross, K. (2017). *Redes de computadoras. Un enfoque descendente.* (7ª ed.). Pearson Educación.
- Robledo, C. (2002). *Redes de computadoras.* (1ª ed.). Instituto Politécnico Nacional.

---

[CONTRAPORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) centrado sobre fondo blanco.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                                                                                  |
|------------|----------|------------------------------------------------------------------------------------------------------------------------------------------------|
| Figuras    | 4        | Retrato Walter Lippmann, topología LAN multi-host, esquema dirección IPv6 GUA, listado de subredes IPv6, ilustración estudiante pensativo.     |
| Tablas     | 8        | Repaso prefijos /8/16/24 (×2), subredes /16 en /24, subredes /24 en /24, máscaras /25-/30 (×2), subredes posibles /16, lista subredes IPv6.    |
| Fórmulas   | 0        | (Sólo expresiones de potencias $2^n$ inline en figura 3.3).                                                                                    |
| Código     | 1        | Lista de subredes IPv6 generadas por incremento del hexteto.                                                                                   |
| Diagramas  | 2        | Topología 3 routers / 3 LAN (Dudas), esquema dirección IPv6 GUA con sus 3 campos.                                                              |
| Ejercicios | 2        | Pregunta sobre desperdicio de direcciones (Conocimientos previos) y Foro S08.s15 (Subneteo FLSM).                                              |
