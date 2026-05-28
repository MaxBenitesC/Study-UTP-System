---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Introducción al diseño lógico. Redes de circuitos virtuales y de datagramas. Estructura de un router.
semana: 5
sesion: 9
tipo_documento: Diapositivas de clase (PPT)
paginas: 17
fuente_pdf: PPT_SESION_09.pdf
---

# Redes y Comunicación de Datos I

## Sesión 9: Introducción al diseño lógico. Redes de circuitos virtuales y de datagramas. Estructura de un router.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) sobre fondo blanco con formas geométricas rojas y negras en las esquinas.]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 5
Sesión: 9

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
  - [1.1 Frase motivadora](#11-frase-motivadora)
- [2. Utilidad](#2-utilidad)
  - [2.1 Dudas de la Clase Anterior](#21-dudas-de-la-clase-anterior)
  - [2.2 Conocimientos Previos](#22-conocimientos-previos)
  - [2.3 Logro de aprendizaje](#23-logro-de-aprendizaje)
  - [2.4 Configurar un router](#24-configurar-un-router)
- [3. Transformación](#3-transformación)
  - [3.1 Pasos Básicos en la configuración de un router](#31-pasos-básicos-en-la-configuración-de-un-router)
  - [3.2 Configurar interfaces del router](#32-configurar-interfaces-del-router)
  - [3.3 Comandos de Verificación](#33-comandos-de-verificación)
- [4. Práctica](#4-práctica)
- [5. Cierre](#5-cierre)
  - [5.1 Preguntas de reflexión](#51-preguntas-de-reflexión)
  - [5.2 Conclusiones](#52-conclusiones)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### 1.1 Frase motivadora

> "Una máquina puede hacer el trabajo de cincuenta hombres ordinarios. Ninguna máquina puede hacer el trabajo de un hombre extraordinario".
>
> — Elbert Hubbard

[FIGURA: Retrato fotográfico en blanco y negro de Elbert Hubbard.]

Fuente: <https://www.britannica.com/biography/Elbert-Hubbard/>

---

## 2. Utilidad

### 2.1 Dudas de la Clase Anterior

Revisión de la Práctica Calificada 1.

[FIGURA: Captura de la "PRÁCTICA CALIFICADA 1" del curso REDES Y COMUNICACIÓN DE DATOS I (I41N), Sección 39188, dictada por el Ing. David Arthur Gálvez Gálvez. Código C16422.

Indicaciones visibles:
- Toda indisciplina será sancionada de acuerdo al reglamento.
- Escriba en forma ordenada y con letra legible. Cuide su ortografía.
- La práctica debe ser desarrollada únicamente con lapicero, caso contrario no tendrá derecho a reclamo.
- Apague y guarde su teléfono celular o cualquier otro dispositivo electrónico.

FILA A — 1. PROBLEMA (7 PUNTOS):

Utilizando Cisco Packet Tracer. Colocar un Switch cisco modelo 2960, acceder a su CLI y se debe configurar lo siguiente:

- Colocar de nombre: INGA-EMPRESA
- Colocar contraseña modo EXEC: KIAm04
- Colocar contraseña EXEC privilegiado: HarTau05
- Colocar contraseña vty: ParGen06
- Encriptar todas las contraseñas.
- Colocar el siguiente banner: "Solo personal de Inkator debe poder acceder"
- Colocar la siguiente dirección ip: 192.168.166.1 255.255.255.0

Añadir 2 computadoras con direcciones IP en la misma red y comprobar que se puede enviar un paquete de datos desde la primera computadora a la segunda.

Colocar otro Switch cisco modelo 2960, acceder a su CLI y configurar:

- Colocar de nombre: ENAKR-EMPRESA
- Colocar contraseña modo EXEC: SchCar07
- Colocar contraseña EXEC privilegiado: MAystae08
- Colocar contraseña vty: FuckFe09
- Encriptar todas las contraseñas.
- Colocar el siguiente banner: "Solo personal de Emaran debe poder acceder"
- Colocar la siguiente dirección ip: 192.168.167.1 255.255.255.0

Añadir 2 computadoras con direcciones IP en la misma red y comprobar que se puede enviar un paquete de datos desde la primera computadora a la segunda.]

Fuente: <https://class.utp.edu.pe>

### 2.2 Conocimientos Previos

Para configurar una dirección ipv4 en un router ¿Qué comando de configuración se debe de usar?

[FIGURA: Ilustración 3D estilizada de un router cilíndrico azul con flechas blancas que apuntan hacia adentro y hacia afuera, representando el enrutamiento de paquetes.]

Fuente: <https://www.netacad.com/es>

### 2.3 Logro de aprendizaje

> "Al finalizar la sesión, el estudiante implementa la configuración inicial, incluidas las contraseñas en un router de red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo".

### 2.4 Configurar un router

Conocer los ajustes iniciales en un router Cisco y las configuraciones de las interfaces activas en un router con Cisco.

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

---

## 3. Transformación

### 3.1 Pasos Básicos en la configuración de un router

**Configure el nombre del dispositivo.**

```cisco
Router(config)# hostname hostname
```

**Proteja el modo EXEC con privilegios.**

```cisco
Router(config)# enable secret password
```

**Proteger el modo EXEC de usuario**

```cisco
Router(config)# line console 0
Router(config-line)# password password
Router(config-line)# login
```

**Proteger el acceso remoto por Telnet y SSH**

```cisco
Router(config)# line vty 0 4
Router(config-line)# password password
Router(config-line)# login
Router(config-line)# transport input {ssh | telnet}
```

**Cifre todas las contraseñas no cifradas.**

```cisco
Router(config)# service password encryption
```

**Proporcione una notificación legal y guarde la configuración.**

```cisco
Router (config) # banner motd # mensaje #
Router(config)# end
Router# copy running-config startup-config
```

Fuente: <https://www.netacad.com/es>

### 3.2 Configurar interfaces del router

La configuración de una interfaz de router incluye la ejecución de los siguientes comandos:

```cisco
Router (config) # interface type-and-number
 Router (config-if) # description description-text
 Router (config-if) # ip address ipv4-address subnet-mask
 Router (config-if) # ipv6 address ipv6-address/prefix-length
 Router (config-if) # no shutdown
```

Se recomienda utilizar el comando **description** para agregar información sobre la red conectada a la interfaz.

El comando **no shutdown** activa la interfaz.

Fuente: <https://www.netacad.com/es>

### 3.3 Comandos de Verificación

| Comandos                                          | Descripción                                                                                                       |
|---------------------------------------------------|-------------------------------------------------------------------------------------------------------------------|
| `show ip interface brief` / `show ipv6 interface brief` | El resultado muestra todas las interfaces, sus direcciones IPv4 y el estado actual.                              |
| `show ip route` / `show ipv6 route`               | Displays the contents of the IP routing tables stored in RAM.                                                    |
| `show interfaces`                                 | Este comando muestra estadísticas de todas las interfaces del dispositivo. Sólo muestra la información de direcciones IPv4. |
| `show ip interfaces`                              | Muestra las estadísticas de IPv4 correspondientes a todas las interfaces de un router.                            |
| `show ipv6 interfaces`                            | Muestra las estadísticas de IPv6 correspondientes a todas las interfaces de un router.                            |

Fuente: <https://www.netacad.com/es>

---

## 4. Práctica

### Foro de Práctica

Ingresar a la Plataforma virtual de aprendizaje y realizar el siguiente foro:

**S05.s9 - Foro Configuración básica del router**

Investigar sobre los comandos de configuración del router, configurando un router con dos computadoras.

Desarrollar la actividad establecida y responder en el mismo foro.

---

## 5. Cierre

### 5.1 Preguntas de reflexión

- ¿Qué hemos aprendido el día de hoy?
- ¿Cómo puedo aplicar lo aprendido?

[FIGURA: Ilustración estilo cartoon de un joven con polo celeste y pantalón oscuro, mirando hacia arriba con expresión pensativa y un signo de interrogación dentro de una burbuja de pensamiento.]

Fuente: <https://www.pinterest.com/pin/498804021304745750/>

### 5.2 Conclusiones

Las siguientes tareas deben completarse al configurar la configuración inicial en un router.

- Configurar el nombre del dispositivo.
- Proteger el modo EXEC con privilegios.
- Proteger el modo EXEC de usuario
- Proteger el acceso remoto por Telnet y SSH
- Proteger todas las contraseñas del archivo de configuración.
- Proporcionar una notificación legal.
- Guardar la configuración.

---

## Referencias bibliográficas

- Kurose, J. y Ross, K. (2017). *Redes de computadoras. Un enfoque descendente.* (7ª ed.). Pearson Educación.
- Robledo, C. (2002). *Redes de computadoras.* (1ª ed.). Instituto Politécnico Nacional.

---

[CONTRAPORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) centrado sobre fondo blanco.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                                                  |
|------------|----------|----------------------------------------------------------------------------------------------------------------|
| Figuras    | 4        | Retrato Hubbard, captura Práctica Calificada 1, ilustración router 3D, ilustración estudiante pensativo.       |
| Tablas     | 1        | Tabla "Comandos de Verificación" (sección 3.3).                                                                |
| Fórmulas   | 0        | —                                                                                                              |
| Código     | 9        | Bloques de comandos Cisco IOS (config router, configuración base, interfaces).                                 |
| Diagramas  | 0        | —                                                                                                              |
| Ejercicios | 1        | Foro S05.s9 — Configuración básica del router (sección 4).                                                     |
