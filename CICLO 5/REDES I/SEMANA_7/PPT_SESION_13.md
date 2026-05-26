---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Protocolo de Internet IPv6. Direccionamiento IPv6.
semana: 7
sesion: 13
tipo_documento: Diapositivas de clase (PPT)
paginas: 29
fuente_pdf: PPT_SESION_13.pdf
---

# Redes y Comunicación de Datos I

## Sesión 13: Protocolo de Internet IPv6. Direccionamiento IPv6.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) sobre fondo blanco con formas geométricas rojas y negras en las esquinas.]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 7
Sesión: 13

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
- [2. Utilidad](#2-utilidad)
  - [2.1 Dudas de la Clase Anterior](#21-dudas-de-la-clase-anterior)
  - [2.2 Conocimientos Previos](#22-conocimientos-previos)
  - [2.3 Logro de aprendizaje](#23-logro-de-aprendizaje)
  - [2.4 Conocimiento de Direcciones IPv6](#24-conocimiento-de-direcciones-ipv6)
- [3. Transformación](#3-transformación)
  - [3.1 Necesidad de IPv6](#31-necesidad-de-ipv6)
  - [3.2 Coexistencia de IPv4 IPv6](#32-coexistencia-de-ipv4-ipv6)
  - [3.3 Direcciones hexadecimales e IPv6](#33-direcciones-hexadecimales-e-ipv6)
  - [3.4 Conversiones decimales a hexadecimales](#34-conversiones-decimales-a-hexadecimales)
  - [3.5 Conversiones hexadecimales a decimales](#35-conversiones-hexadecimales-a-decimales)
  - [3.6 Formatos de Direcciones IPv6](#36-formatos-de-direcciones-ipv6)
  - [3.7 Regla 1 – Omitir el cero inicial](#37-regla-1--omitir-el-cero-inicial)
  - [3.8 Regla 2 – Dos Puntos](#38-regla-2--dos-puntos)
- [4. Práctica](#4-práctica)
- [5. Cierre](#5-cierre)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### Frase motivadora

> "Si tu negocio no está en Internet, tu negocio no existe".
>
> — Bill Gates

[FIGURA: Retrato fotográfico a color de Bill Gates, con anteojos, traje oscuro y corbata amarilla con rayas.]

Fuente: <https://startupmexico.com/bill-gates-identifica-tres-trabajos-que-la-inteligencia-artificial-no-podra-reemplazar//>

---

## 2. Utilidad

### 2.1 Dudas de la Clase Anterior

¿Qué recuerdas del direccionamiento IPv4 que aprendimos la clase anterior?

Fuente: <https://class.utp.edu.pe>

### 2.2 Conocimientos Previos

- ¿Qué opinas de IPv6?
- ¿Cómo reconocer direcciones IPv6?

[FIGURA — Imagen de referencia sobre fondo azul oscuro/morado:

- Título: "Dirección IPv6".
- Ejemplo de dirección: `2001 : 0DC8 : E004 : 0001 : 0000 : 0000 : 0000 : F00A`.
- Etiqueta inferior: `16 bits : 16 bits : 16 bits : 16 bits : 16 bits : 16 bits : 16 bits : 16 bits` y "128 bits" en total.]

Fuente: <https://www.netacad.com/es>

### 2.3 Logro de aprendizaje

> "Al finalizar la sesión, el estudiante reconoce el direccionamiento IPv6 para usar eficientemente en la red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo".

### 2.4 Conocimiento de Direcciones IPv6

IPv6 es el sucesor de IPv4, se debe conocer como es la asignación de direcciones IPv6, como se representan las direcciones IPv6 y como configurar direcciones de red unicast y link-local del IPv6 de forma estática.

[FIGURA: misma imagen de referencia de la sección 2.2 — dirección IPv6 ejemplo `2001 : 0DC8 : E004 : 0001 : 0000 : 0000 : 0000 : F00A` con la anotación de 128 bits divididos en 8 hextetos de 16 bits.]

Fuente: <https://www.netacad.com/es>

---

## 3. Transformación

### 3.1 Necesidad de IPv6

- IPv4 se está quedando sin direcciones. IPv6 es el sucesor de IPv4. IPv6 tiene un espacio de direcciones de 128 bits mucho más grande.
- El desarrollo de IPv6 también incluyó correcciones para limitaciones de IPv4 y otras mejoras.

- Con una población que accede a Internet cada vez mayor, un espacio de direcciones IPv4 limitado, los problemas de NAT y la Internet de todo, llegó el momento de comenzar la transición hacia IPv6.

[FIGURA — Mapa mundi en azul, con etiquetas que indican las fechas de agotamiento de IPv4 por cada Registro Regional de Internet (RIR):

- **ARIN** (American Registry for Internet Numbers) — IPv4 exhaustion date: July 2015.
- **RIPE NCC** — IPv4 exhaustion date: September 2012.
- **APNIC** — IPv4 exhaustion date: June 2014.
- **AfriNIC** — Projected IPv4 exhaustion date: 2020.
- **LACNIC** — IPv4 exhaustion date: April 2011.]

Fuente: <https://www.netacad.com/es>

### 3.2 Coexistencia de IPv4 IPv6

Tanto IPv4 como IPv6 coexistirán en un futuro próximo y la transición llevará varios años.

El IETF creó diversos protocolos y herramientas para ayudar a los administradores de redes a migrar las redes a IPv6.

Las técnicas de migración pueden dividirse en tres categorías:

- **Dual stack** — Los dispositivos ejecutan pilas de protocolos IPv4 e IPv6 de manera simultánea.
- **Tunneling** — Es un método para transportar un paquete IPv6 a través de una red IPv4. El paquete IPv6 se encapsula dentro de un paquete IPV4.
- **Translation** — Network Address Translation 64 (NAT64) permite que los dispositivos con IPv6 habilitado se comuniquen con dispositivos con IPv4 habilitado mediante una técnica de traducción similar a la NAT para IPv4.

Fuente: <https://www.netacad.com/es>

### 3.3 Direcciones hexadecimales e IPv6

Para entender las direcciones IPv6, debe ser capaz de convertir hexadecimal a decimal y viceversa.

Hexadecimal es un sistema de numeración de base dieciséis, que utiliza los dígitos del 0 al 9 y las letras A a F.

Es más fácil expresar un valor como un solo dígito hexadecimal que como cuatro bits binarios.

Hexadecimal se usa para representar direcciones IPv6 y direcciones MAC.

#### Tabla — Equivalencia Decimal / Binario / Hexadecimal (0 a 15)

| Decimal | Binary | Hexadecimal |
|--------:|:-------|:-----------:|
| 0       | 0000   | 0           |
| 1       | 0001   | 1           |
| 2       | 0010   | 2           |
| 3       | 0011   | 3           |
| 4       | 0100   | 4           |
| 5       | 0101   | 5           |
| 6       | 0110   | 6           |
| 7       | 0111   | 7           |
| 8       | 1000   | 8           |
| 9       | 1001   | 9           |
| 10      | 1010   | A           |
| 11      | 1011   | B           |
| 12      | 1100   | C           |
| 13      | 1101   | D           |
| 14      | 1110   | E           |
| 15      | 1111   | F           |

Las direcciones IPv6 tienen 128 bits de longitud. Cada 4 bits está representado por un solo dígito hexadecimal.

Esto hace que la dirección IPv6 tenga un total de 32 valores hexadecimales.

[FIGURA — Diagrama que ilustra el método preferido para escribir una dirección IPv6:

- Línea superior: `X : X : X : X : X : X : X : X` (8 grupos separados por dos puntos).
- Debajo de cada X: rango `0000 to ffff` (cada hexteto puede ir de 0000 a FFFF).
- Una flecha naranja apunta desde un hexteto hacia un detalle expandido:
  - El hexteto seleccionado se descompone en 4 grupos de dígitos hexadecimales, cada uno con rango `0000 to 1111` (4 dígitos binarios).
- Etiqueta: "4 hexadecimal digits = 16 binary digits".]

La figura muestra el método preferido para escribir una dirección IPv6, con cada X representando cuatro valores hexadecimales.

Cada grupo de cuatro caracteres hexadecimales se conoce como **hexteto**.

Fuente: <https://www.netacad.com/es>

### 3.4 Conversiones decimales a hexadecimales

Siga los pasos indicados para convertir números decimales a valores hexadecimales:

- Convertir el número decimal a cadenas binarias de 8 bits.
- Divida las cadenas binarias en grupos de cuatro comenzando desde la posición más a la derecha.
- Convierta cada cuatro números binarios en su dígito hexadecimal equivalente.

#### Ejemplo: convertir **168** a hexadecimal

Por ejemplo, 168 convertido en hexadecimal usando el proceso de tres pasos.

- 168 en binario es `10101000`.
- `10101000` en dos grupos de cuatro dígitos binarios es `1010` y `1000`.
- `1010` es hex **A** y `1000` es hex **8**, por lo que **168 es A8 en hexadecimal**.

Fuente: <https://www.netacad.com/es>

### 3.5 Conversiones hexadecimales a decimales

Siga los pasos indicados para convertir números hexadecimales en valores decimales:

- Convertir el número hexadecimal en cadenas binarias de 4 bits.
- Cree una agrupación binaria de 8 bits comenzando desde la posición más a la derecha.
- Convierta cada agrupación binaria de 8 bits en su dígito decimal equivalente.

#### Ejemplo: convertir **D2** a decimal

Por ejemplo, D2 convertido a decimal mediante el proceso de tres pasos:

- D2 en cadenas binarias de 4 bits es `1101` y `0010`.
- `1101` y `0010` es `11010010` en un grupo de 8 bits.
- `11010010` en binario es equivalente a **210 en decimal**, por lo que **D2 es 210 es decimal**.

> **Nota de transcripción:** la diapositiva original dice "**D2 es 210 es decimal**" — frase con redacción atípica. Probablemente quiso decir "D2 es 210 en decimal". Se preserva el original.

Fuente: <https://www.netacad.com/es>

### 3.6 Formatos de Direcciones IPv6

- Las direcciones IPv6 tienen 128 bits de longitud y están escritas en hexadecimal.
- Las direcciones IPv6 no distinguen entre mayúsculas y minúsculas, y pueden escribirse en minúsculas o en mayúsculas.

- El formato preferido para escribir una dirección IPv6 es `x: x: x: x: x: x: x: x`, donde cada "x" consta de cuatro valores hexadecimales.
- En IPv6, un "hexteto" es el término no oficial que se utiliza para referirse a un segmento de 16 bits o cuatro valores hexadecimales.
- Ejemplos de direcciones IPv6 en el formato preferido:

  ```
  2001:0db8:0000:1111:0000:0000:0000:0200
  2001:0 db 8:0000:00 a3:abcd:0000:0000:1234
  ```

> **Nota de transcripción:** el segundo ejemplo aparece en la diapositiva con espacios espurios entre dígitos: `2001:0 db 8:0000:00 a3:...`. Sin esos espacios, la dirección equivalente sería `2001:0db8:0000:00a3:abcd:0000:0000:1234`. Se preserva el original.

Fuente: <https://www.netacad.com/es>

### 3.7 Regla 1 – Omitir el cero inicial

La primera regla para ayudar a reducir la notación de las direcciones IPv6 es omitir los 0s (ceros) iniciales.

**Ejemplos:**

- `01ab` se puede representar como `1ab`
- `09f0` se puede representar como `9f0`
- `0a00` se puede representar como `a00`
- `00ab` se puede representar como `ab`

Esta regla solo es válida para los ceros iniciales, y NO para los ceros finales; de lo contrario, la dirección sería ambigua.

#### Tabla — Comparativa de notación con/sin ceros iniciales

| Tipo                  | Formato                                                  |
|-----------------------|----------------------------------------------------------|
| Recomendado           | `2001: 0db8: 0000:1111: 0000: 0000: 0000: 0200`          |
| Sin los ceros iniciales | `2001 : db8 : 0 : 1111 : 0 : 0 : 0 : 200`              |

Fuente: <https://www.netacad.com/es>

### 3.8 Regla 2 – Dos Puntos

Los dos puntos dobles (`::`) pueden reemplazar cualquier cadena única y contigua de uno o más segmentos de 16 bits (hextetos) que estén compuestas solo por ceros.

**Por ejemplo:**

- `2001:db8:cafe: 1:0:0:0:1` (0s iniciales omitidos) podría representarse como `2001:db8:cafe:1: :1`

Los dos puntos dobles (`::`) se pueden utilizar solamente una vez dentro de una dirección; de lo contrario, habría más de una dirección resultante posible.

#### Tabla — Comparativa de notación recomendada vs. comprimida

| Tipo        | Formato                                                  |
|-------------|----------------------------------------------------------|
| Recomendado | `2001: 0db8: 0000:1111: 0000: 0000: 0000: 0200`          |
| Comprimido  | `2001:db8:0:1111::200`                                   |

> **Nota de transcripción:** la diapositiva muestra `2001:db8:cafe:1: :1` con un espacio entre los dos puntos consecutivos. La forma comprimida correcta sería `2001:db8:cafe:1::1`. Se preserva la grafía visible en la diapositiva.

Fuente: <https://www.netacad.com/es>

---

## 4. Práctica

### Foro de Práctica

Ingresar a la Plataforma virtual de aprendizaje y realizar el siguiente foro:

**S07.s13 - Foro Direccionamiento Ipv6**

Investigar sobre las principales categorías de las direcciones IPv6.

Desarrollar la actividad establecida y responder en el mismo foro.

---

## 5. Cierre

### 5.1 Preguntas de reflexión

- ¿Qué hemos aprendido el día de hoy?
- ¿Cómo puedo aplicar lo aprendido?

[FIGURA: Ilustración estilo cartoon de un joven con polo celeste y pantalón oscuro, mirando hacia arriba con expresión pensativa y un signo de interrogación dentro de una burbuja de pensamiento.]

Fuente: <https://www.pinterest.com/pin/498804021304745750/>

### 5.2 Conclusiones

- Las direcciones IPv6 tienen una longitud de 128 bits y se escriben como una cadena de valores hexadecimales.
- El formato preferido para escribir una dirección IPv6 es `x: x: x: x: x: x: x: x`, donde cada "x" consta de cuatro valores hexadecimales.

---

## Referencias bibliográficas

- Kurose, J. y Ross, K. (2017). *Redes de computadoras. Un enfoque descendente.* (7ª ed.). Pearson Educación.
- Robledo, C. (2002). *Redes de computadoras.* (1ª ed.). Instituto Politécnico Nacional.

---

[CONTRAPORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) centrado sobre fondo blanco.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                                                                |
|------------|----------|------------------------------------------------------------------------------------------------------------------------------|
| Figuras    | 5        | Retrato Bill Gates, dirección IPv6 conceptual (2 veces), mapa mundi RIRs, ilustración estudiante pensativo, diagrama hexteto. |
| Tablas     | 3        | Equivalencia Decimal/Binario/Hex (16 filas), comparativa Regla 1 (con/sin ceros), comparativa Regla 2 (recomendado/comprimido). |
| Fórmulas   | 0        | —                                                                                                                            |
| Código     | 1        | Bloque de ejemplos de direcciones IPv6 en formato preferido.                                                                 |
| Diagramas  | 0        | —                                                                                                                            |
| Ejercicios | 3        | Pregunta repaso IPv4 (Dudas), preguntas Conocimientos previos sobre IPv6, Foro S07.s13.                                      |
