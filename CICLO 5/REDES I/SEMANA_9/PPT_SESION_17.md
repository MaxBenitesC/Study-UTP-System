---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Subredes con máscara de subred de longitud variable (VLSM) y Enrutamiento básico
semana: 9
sesion: 17
tipo_documento: Diapositivas de clase (PPT)
paginas: 22
fuente_pdf: PPT_SESION_17.pdf
---

# Redes y Comunicación de Datos I

## Sesión 17: Subredes con máscara de subred de longitud variable — VLSM. Enrutamiento básico.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) con formas geométricas en rojo y negro sobre fondo blanco. Texto: "Sesión 17: Subredes con mascara de subred de longitud variable – VLSM. Enrutamiento básico."]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 9
Sesión: 17

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
- [2. Utilidad](#2-utilidad)
  - [2.1 Dudas de la Clase Anterior](#21-dudas-de-la-clase-anterior)
  - [2.2 Conocimientos Previos](#22-conocimientos-previos)
  - [2.3 Logro de aprendizaje](#23-logro-de-aprendizaje)
- [3. Transformación](#3-transformación)
  - [3.1 Subneteo de Direcciones IP](#31-subneteo-de-direcciones-ip)
  - [3.2 División en subredes en el límite del octeto](#32-división-en-subredes-en-el-límite-del-octeto)
  - [3.3 División en subredes con prefijos mayores a /24](#33-división-en-subredes-con-prefijos-mayores-a-24)
  - [3.4 Crear subredes con un prefijo /16](#34-crear-subredes-con-un-prefijo-16)
  - [3.5 Crear 100 subredes con un prefijo /16](#35-crear-100-subredes-con-un-prefijo-16)
  - [3.6 Minimice las direcciones IPv4 de host no utilizadas](#36-minimice-las-direcciones-ipv4-de-host-no-utilizadas)
  - [3.7 División de una red IPv6 en subredes](#37-división-de-una-red-ipv6-en-subredes)
  - [3.8 Subnetear una red IPv6](#38-subnetear-una-red-ipv6)
- [4. Práctica](#4-práctica)
- [5. Cierre](#5-cierre)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### Frase motivadora
> “La ciencia y la tecnología revolucionan nuestras vidas, pero la memoria, la tradición y el mito cercan nuestra respuesta”.
>
> — **Arthur Schlesinger**

---

## 2. Utilidad

### 2.1 Dudas de la Clase Anterior
*   Indicar si requiero 16 subredes cuántos hosts pueden tener en cada subred.

### 2.2 Conocimientos Previos
*   ¿Cómo podemos realizar un mejor subneteo de redes, para evitar el desperdicio de direcciones IP?

### 2.3 Logro de aprendizaje
“Al finalizar la sesión, el estudiante reconoce el subneteo IPv4 para usar eficientemente las direcciones IP en la red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo”.

---

## 3. Transformación

### 3.1 Subneteo de Direcciones IP
La utilidad del direccionamiento IP radica en su capacidad para identificar y localizar dispositivos en una red, permitiendo la comunicación y el intercambio de datos entre ellos.

### 3.2 División en subredes en el límite del octeto
Las redes se subdividen con más facilidad en el límite del octeto de /8, /16 y /24.
Observe que el uso de longitudes de prefijo más extensas disminuye la cantidad de hosts por subred.

#### Ejemplo: Subneteo de 10.0.0.0/8 usando /16
En la primera tabla se muestra la división usando el límite del segundo octeto.

| Dirección de subred (256 posibles) | Rango de host (65,534 hosts por subred) | Dirección de Broadcast |
| :--- | :--- | :--- |
| 10.0.0.0/16 | 10.0.0.1 - 10.0.255.254 | 10.0.255.255 |
| 10.1.0.0/16 | 10.1.0.1 - 10.1.255.254 | 10.1.255.255 |
| 10.2.0.0/16 | 10.2.0.1 - 10.2.255.254 | 10.2.255.255 |
| 10.3.0.0/16 | 10.3.0.1 - 10.3.255.254 | 10.3.255.255 |
| 10.4.0.0/16 | 10.4.0.1 - 10.4.255.254 | 10.4.255.255 |
| 10.5.0.0/16 | 10.5.0.1 - 10.5.255.254 | 10.5.255.255 |
| 10.6.0.0/16 | 10.6.0.1 - 10.6.255.254 | 10.6.255.255 |
| 10.7.0.0/16 | 10.7.0.1 - 10.7.255.254 | 10.7.255.255 |
| ... | ... | ... |
| 10.255.0.0/16 | 10.255.0.1 - 10.255.255.254 | 10.255.255.255 |

#### Ejemplo: Subneteo usando máscara /24
División de la red en el límite del tercer octeto.

| Dirección de subred (65,536 posibles) | Rango de host (254 hosts por subred) | Dirección de Broadcast |
| :--- | :--- | :--- |
| 10.0.0.0/24 | 10.0.0.1 - 10.0.0.254 | 10.0.0.255 |
| 10.0.1.0/24 | 10.0.1.1 - 10.0.1.254 | 10.0.1.255 |
| 10.0.2.0/24 | 10.0.2.1 - 10.0.2.254 | 10.0.2.255 |
| ... | ... | ... |
| 10.0.255.0/24 | 10.0.255.1 - 10.0.255.254 | 10.0.255.255 |
| 10.1.0.0/24 | 10.1.0.1 - 10.1.0.254 | 10.1.0.255 |
| 10.1.1.0/24 | 10.1.1.1 - 10.1.1.254 | 10.1.1.255 |
| 10.1.2.0/24 | 10.1.2.1 - 10.1.2.254 | 10.1.2.255 |
| ... | ... | ... |
| 10.255.255.0/24 | 10.255.255.1 - 10.255.255.254 | 10.255.255.255 |

### 3.3 División en subredes con prefijos mayores a /24
Consulte la tabla para ver seis formas de subred una red /24.

| Longitud de prefijo | Máscara de subred | Máscara de subred en binario (n = red, h = host) | Cantidad de subredes | Cantidad de hosts |
| :--- | :--- | :--- | :--- | :--- |
| /25 | 255.255.255.128 | `11111111.11111111.11111111.10000000` | 2 | 126 |
| /26 | 255.255.255.192 | `11111111.11111111.11111111.11000000` | 4 | 62 |
| /27 | 255.255.255.224 | `11111111.11111111.11111111.11100000` | 8 | 30 |
| /28 | 255.255.255.240 | `11111111.11111111.11111111.11110000` | 16 | 14 |
| /29 | 255.255.255.248 | `11111111.11111111.11111111.11111000` | 32 | 6 |
| /30 | 255.255.255.252 | `11111111.11111111.11111111.11111100` | 64 | 2 |

### 3.4 Crear subredes con un prefijo /16
La tabla resalta todos los escenarios posibles para dividir en subredes un prefijo /16.

| Prefijo | Máscara | Dirección de red (n=red, h=host) | Subredes | Hosts |
| :--- | :--- | :--- | :--- | :--- |
| /17 | 255.255.128.0 | `11111111.11111111.10000000.00000000` | 2 | 32766 |
| /18 | 255.255.192.0 | `11111111.11111111.11000000.00000000` | 4 | 16382 |
| /19 | 255.255.224.0 | `11111111.11111111.11100000.00000000` | 8 | 8190 |
| /20 | 255.255.240.0 | `11111111.11111111.11110000.00000000` | 16 | 4094 |
| /21 | 255.255.248.0 | `11111111.11111111.11111000.00000000` | 32 | 2046 |
| /22 | 255.255.252.0 | `11111111.11111111.11111100.00000000` | 64 | 1022 |
| /23 | 255.255.254.0 | `11111111.11111111.11111110.00000000` | 128 | 510 |
| /24 | 255.255.255.0 | `11111111.11111111.11111111.00000000` | 256 | 254 |
| /25 | 255.255.255.128 | `11111111.11111111.11111111.10000000` | 512 | 126 |
| /26 | 255.255.255.192 | `11111111.11111111.11000000.00000000` | 1024 | 62 |
| /27 | 255.255.255.224 | `11111111.11111111.11100000.00000000` | 2048 | 30 |
| /28 | 255.255.255.240 | `11111111.11111111.11110000.00000000` | 4096 | 14 |
| /29 | 255.255.255.248 | `11111111.11111111.11111000.00000000` | 8192 | 6 |
| /30 | 255.255.255.252 | `11111111.11111111.11111100.00000000` | 16384 | 2 |

### 3.5 Crear 100 subredes con un prefijo /16
Imagine una gran empresa que requiere, como mínimo, 100 subredes y eligió la dirección privada 172.16.0.0/16 como su dirección de red interna.

Para satisfacer el requisito de 100 subredes para la empresa, se necesitarían prestar 7 bits (es decir, $2^7 = 128$ subredes).

[FIGURA: Diagrama de préstamo de bits donde se resalta el tercer octeto para la creación de subredes y el cuarto para hosts.]

Observe que ahora hay hasta 14 bits de host que se pueden tomar prestados (es decir, los dos últimos bits no se pueden tomar prestados).

### 3.6 Minimice las direcciones IPv4 de host no utilizadas
Existen dos factores que se deben tener en cuenta al planificar las subredes:
*   El número de direcciones de host requeridas para cada red.
*   El número de subredes individuales necesarias.

### 3.7 División de una red IPv6 en subredes
IPv6 se diseñó teniendo en cuenta las subredes.
*   Se utiliza un campo ID de subred independiente en IPv6 GUA para crear subredes.
*   El campo ID de subred es el área entre el Prefijo de enrutamiento global y la ID de interfaz.

### 3.8 Subnetear una red IPv6
Dado el prefijo de enrutamiento global `2001:db8:acad::/48` con un ID de subred de 16 bits:
*   Permite 65,536 subredes /64.
*   El prefijo de enrutamiento global es igual para todas las subredes.
*   Solo se incrementa el hexteto de la ID de subred en sistema hexadecimal para cada subred.

[FIGURA: Estructura de dirección IPv6 resaltando Global Routing Prefix (48 bits), Subnet ID (16 bits) e Interface ID (64 bits).]

---

## 4. Práctica

### Foro de Práctica
S09.s17 - Foro Subneteo VLSM
Investigar sobre el subneteo VLSM en direcciones IPv4. Desarrollar la actividad establecida y responder en el mismo foro.

---

## 5. Cierre

### Preguntas de reflexión
*   ¿Qué hemos aprendido el día de hoy?
*   ¿Cómo puedo aplicar lo aprendido?

### Conclusiones
*   VLSM asigna tamaños específicos a cada subred según su necesidad real, eliminando el desperdicio de direcciones que ocurre con máscaras de longitud fija (FLSM).
*   Permite que los administradores diseñen redes más adaptables, asignando subredes de tamaños diferentes y creando una arquitectura de red más flexible y escalable.

---

## Referencias bibliográficas
*   Kurose, J. y Ross, K. (2017). Redes de computadoras. Un enfoque descendente. (7ª ed.). Pearson Educación.
*   Robledo, C. (2002). Redes de computadoras. (1ª ed.). Instituto Politécnico Nacional.
