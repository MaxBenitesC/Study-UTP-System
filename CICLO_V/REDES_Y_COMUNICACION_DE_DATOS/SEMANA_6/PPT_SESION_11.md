---
universidad: UTP
curso: Redes y Comunicación de Datos I
tema: Protocolo de Internet IPv4. Direccionamiento IPv4.
semana: 6
sesion: 11
tipo_documento: Diapositivas de clase (PPT)
paginas: 26
fuente_pdf: PPT_SESION_11.pdf
---

# Redes y Comunicación de Datos I

## Sesión 11: Protocolo de Internet IPv4. Direccionamiento IPv4.

[FIGURA PORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) sobre fondo blanco con formas geométricas rojas y negras en las esquinas.]

Universidad: Universidad Tecnológica del Perú
Curso: Redes y Comunicación de Datos I
Semana: 6
Sesión: 11

---

## Tabla de Contenidos

- [1. Inicio](#1-inicio)
- [2. Utilidad](#2-utilidad)
  - [2.1 Dudas de la Clase Anterior](#21-dudas-de-la-clase-anterior)
  - [2.2 Conocimientos Previos](#22-conocimientos-previos)
  - [2.3 Logro de aprendizaje](#23-logro-de-aprendizaje)
  - [2.4 Conocimiento de Direcciones IPv4](#24-conocimiento-de-direcciones-ipv4)
- [3. Transformación](#3-transformación)
  - [3.1 Direcciones binarias e IPv4](#31-direcciones-binarias-e-ipv4)
  - [3.2 Notación Posición Binaria](#32-notación-posición-binaria)
  - [3.3 Notación Posición Binaria del sistema de números binarios](#33-notación-posición-binaria-del-sistema-de-números-binarios)
  - [3.4 Convertir binario a decimal](#34-convertir-binario-a-decimal)
  - [3.5 Convertir decimal del sistema de números binarios a binario](#35-convertir-decimal-del-sistema-de-números-binarios-a-binario)
  - [3.6 Ejemplo de conversión de decimal a binario](#36-ejemplo-de-conversión-de-decimal-a-binario)
  - [3.7 Direcciones IPv4](#37-direcciones-ipv4)
- [4. Práctica](#4-práctica)
- [5. Cierre](#5-cierre)
- [Referencias bibliográficas](#referencias-bibliográficas)

---

## 1. Inicio

### Frase motivadora

> "La tecnología hizo posible las grandes poblaciones; ahora las grandes poblaciones hacen que la tecnología sea indispensable".
>
> — José Krutch

[FIGURA: Retrato fotográfico en blanco y negro de Joseph Wood Krutch, hombre mayor con anteojos y traje.]

Fuente: <https://knoxvillehistoryproject.org/writers/joseph-wood-krutch-utl-hitres-needed/>

> **Nota de transcripción:** la diapositiva indica autor "José Krutch", pero el URL de fuente apunta a "Joseph Wood Krutch". Se preserva exactamente lo que dice la diapositiva.

---

## 2. Utilidad

### 2.1 Dudas de la Clase Anterior

Indicar cuales son las puertas de enlace de las redes verde y naranja.

[DIAGRAMA DE RED — Topología de tres subredes (la misma utilizada en sesión 10):

- **Subred izquierda (verde)** `192.168.10.0/24` — IPv6 `2001:db8:acad:10::/64`
  - PC-PT PC1 (`.10` / `::10`) ─ Switch `2960-24TT S1` ─ R1 `g0/0/0` (`.1` / `::1`).
- **Subred central (amarilla)** `209.165.200.224/30` — IPv6 `2001:db8:feed:224::/64`
  - Enlace serial R1 (`s0/1/0`, `.225` / `::1`) ↔ R2 (`s0/1/0`, `.226` / `::2`). Routers `ISR4331`.
- **Subred derecha (naranja)** `10.1.1.0/24` — IPv6 `2001:db8:cafe:1::/64`
  - R2 (`.1` / `::1`) ─ Switch `2960-24TT S2` ─ PC-PT PC2 (`.10` / `::10`).]

Fuente: <https://class.utp.edu.pe>

### 2.2 Conocimientos Previos

Indicar información sobre las direcciones IPv4.

[FIGURA — Diagrama de una dirección IPv4 dividida en "Network Portion" y "Host Portion":

- Dirección IPv4 mostrada: `192 . 168 . 10 . 10`
- Representación binaria por octeto: `11000000 10101000 00001010 | 00001010`
- Línea punteada naranja vertical entre el tercer octeto y el cuarto separa la porción de red (más clara) de la porción de host (más oscura).]

Fuente: <https://www.netacad.com/es>

### 2.3 Logro de aprendizaje

> "Al finalizar la sesión, el estudiante reconoce el direccionamiento IPv4 para usar eficientemente en la red considerando ejemplos de Redes de Comunicación de Cisco y el aplicativo".

### 2.4 Conocimiento de Direcciones IPv4

La utilidad del direccionamiento IP radica en su capacidad para identificar y localizar dispositivos en una red, permitiendo la comunicación y el intercambio de datos entre ellos.

[DIAGRAMA DE RED — Topología LAN con un router en la parte superior conectado a un switch central. Del switch parten enlaces a:

- 4 PCs en la fila inferior (`172.16.4.1/24`, `172.16.4.2/24`, `172.16.4.3 / 224.10.10.5/24`, `172.16.4.4 / 224.10.10.5/24`).
- 1 servidor/equipo a la derecha (`172.16.4.253/24`).

Una flecha naranja parte del PC marcado `172.16.4.1/24` (con un sobre amarillo encima) e indica el envío de un paquete (Source: `172.16.4.1/24`).]

Fuente: <https://www.netacad.com/es>

---

## 3. Transformación

### 3.1 Direcciones binarias e IPv4

- El sistema de numeración binaria consta de 1s y 0s, llamados bits.
- Sistema de numeración decimal consta de dígitos del 0 al 9.

- Hosts, servidores y equipos de red que utilizan direccionamiento binario para identificarse entre sí.
- Cada dirección está compuesta por una cadena de 32 bits, dividida en cuatro secciones llamadas octetos.

- Para facilitar el uso de las personas, esta notación punteada se convierte en decimal punteado.

[DIAGRAMA DE RED COMPARATIVO — Dos topologías en paralelo mostrando la misma red en binario y en decimal:

**Izquierda (notación binaria):**

- Nube con etiqueta `11010001.10100101.11001000.11100001`.
- LAN A: dirección de red `11000000.10101000.00001010.00000000 / 24`. PC1 con IP `11000000.10101000.00001010.00001010`, conectada a switch, conectada a router (interfaz G0/0/0).
- LAN B: dirección de red `11000000.10101000.00001011.00000000 / 24`. PC2 con IP `11000000.10101000.00001011.00001010`, conectada a switch, conectada al mismo router (interfaz G0/0/1).

Una flecha azul horizontal indica conversión hacia la derecha.

**Derecha (notación decimal punteada equivalente):**

- Nube con etiqueta `209.165.200.225`.
- LAN A: `192.168.10.0/24`. PC1 = `192.168.10.10`. Router G0/0/0 = `192.168.10.1`.
- LAN B: `192.168.11.0/24`. PC2 = `192.168.11.10`. Router G0/0/1 = `192.168.11.1`.]

Fuente: <https://www.netacad.com/es>

### 3.2 Notación Posición Binaria

El término "notación de posición" significa que un dígito representa diferentes valores según la "posición" que el dígito ocupa en la secuencia de números.

El sistema de notación posicional decimal funciona como se muestra en las siguientes tablas.

#### Tabla — Sistema posicional decimal (base 10)

| Base                | 10              | 10              | 10              | 10              |
|---------------------|-----------------|-----------------|-----------------|-----------------|
| Posición en número  | 3               | 2               | 1               | 0               |
| Cálculo             | (10³)           | (10²)           | (10¹)           | (10⁰)           |
| Valor de la posición| 1000            | 100             | 10              | 1               |

#### Tabla — Conversión del número 1234

|                  | Millares | Centenas | Decenas | Unidades |
|------------------|---------:|---------:|--------:|---------:|
| Valor de posición| 1000     | 100      | 10      | 1        |
| Número decimal (1234) | 1   | 2        | 3       | 4        |
| Cálculo          | 1 × 1000 | 2 × 100  | 3 × 10  | 4 × 1    |
| Súmelos...       | 1000     | + 200    | + 30    | + 4      |
| Resultado        | **1234** |          |         |          |

Fuente: <https://www.netacad.com/es>

### 3.3 Notación Posición Binaria del sistema de números binarios

El sistema de notación posicional binaria funciona como se muestra en las siguientes tablas.

#### Tabla — Sistema posicional binario (base 2)

| Base                | 2      | 2      | 2      | 2      | 2      | 2      | 2      | 2      |
|---------------------|--------|--------|--------|--------|--------|--------|--------|--------|
| Posición en número  | 7      | 6      | 5      | 4      | 3      | 2      | 1      | 0      |
| Cálculo             | (2⁷)   | (2⁶)   | (2⁵)   | (2⁴)   | (2³)   | (2²)   | (2¹)   | (2⁰)   |
| Valor de la posición| 128    | 64     | 32     | 16     | 8      | 4      | 2      | 1      |

#### Tabla — Ejemplo: número binario `11000000`

| Valor de posición       | 128   | 64    | 32    | 16    | 8     | 4     | 2     | 1     |
|-------------------------|-------|-------|-------|-------|-------|-------|-------|-------|
| Número binario (11000000)| 1    | 1     | 0     | 0     | 0     | 0     | 0     | 0     |
| Cálculo                 | 1×128 | 1×64  | 0×32  | 0×16  | 0×8   | 0×4   | 0X2   | 0×1   |
| Añádelas...             | 128   | + 64  | + 0   | + 0   | + 0   | + 0   | + 0   | + 0   |
| Resultado               | **192** |     |       |       |       |       |       |       |

[NOTA AL PIE: La diapositiva incluye el logo `cisco` y la línea "Información confidencial de Cisco" como pie de página técnico.]

Fuente: <https://www.netacad.com/es>

### 3.4 Convertir binario a decimal

Convertir `11000000.10101000.00001011.00001010` a decimal.

#### Tabla — Conversión por octetos

| Valor de posición          | 128   | 64    | 32    | 16    | 8     | 4     | 2     | 1     | Resultado |
|----------------------------|-------|-------|-------|-------|-------|-------|-------|-------|-----------|
| Número binario (11000000)  | 1     | 1     | 0     | 0     | 0     | 0     | 0     | 0     |           |
| Cálculo                    | 1×128 | 1×64  | 0×32  | 0×16  | 0×8   | 0×4   | 0X2   | 0×1   |           |
| Añádelas...                | 128   | + 64  | + 0   | + 0   | + 0   | + 0   | + 0   | + 0   | → **192** |
| Número binario (10101000)  | 1     | 0     | 1     | 0     | 1     | 0     | 0     | 0     |           |
| Cálculo                    | 1×128 | 0×64  | 1×32  | 0×16  | 1×8   | 0×4   | 0X2   | 0×1   |           |
| Añádelas...                | 128   | + 0   | + 32  | + 0   | + 8   | + 0   | + 0   | + 0   | → **168** |
| Número binario (00001011)  | 0     | 0     | 0     | 0     | 1     | 0     | 1     | 1     |           |
| Cálculo                    | 0×128 | 0×64  | 0×32  | 0×16  | 1×8   | 0×4   | 1×2   | 1 × 1 |           |
| Añádelas...                | 0     | + 0   | + 0   | + 0   | + 8   | + 0   | + 2   | + 1   | → **11**  |
| Número binario (00001010)  | 0     | 0     | 0     | 0     | 1     | 0     | 1     | 0     |           |
| Cálculo                    | 0×128 | 0×64  | 0×32  | 0×16  | 1×8   | 0×4   | 1×2   | 0×1   |           |
| Añádelas...                | 0     | + 0   | + 0   | + 0   | + 8   | + 0   | + 2   | + 0   | → **10**  |

Resultado final: **192.168.11.10**

Fuente: <https://www.netacad.com/es>

### 3.5 Convertir decimal del sistema de números binarios a binario

La tabla de valores posicionales binarios es útil para convertir una dirección IPv4 decimal punteada a binaria.

[DIAGRAMA DE FLUJO — Diagrama de decisión con la pregunta `n >= 128`. La fila de valores posicionales muestra: `128 | 64 | 32 | 16 | 8 | 4 | 2 | 1`, con la primera celda (128) resaltada en naranja. Dos ramas:

- **No (Add 0)** → flecha que indica registrar 0 y avanzar.
- **Yes (Add 1)** → flecha que indica registrar 1, restar 128 (`n - 128`) y avanzar.]

- Comience en la posición 128 (el bit más significativo). ¿Es el número decimal del octeto (n) igual o mayor que 128?
- Si no, registre un 0 binario en el valor posicional 128 y muévase al valor posicional 64.
- En caso afirmativo, registre un 1 binario en el valor posicional 128, reste 128 del número decimal y vaya al valor posicional 64.
- Repita estos pasos a través del valor posicional 1.

Fuente: <https://www.netacad.com/es>

### 3.6 Ejemplo de conversión de decimal a binario

Convertir el decimal **168** a binario:

- ¿Es 168 >= 128?
  - Sí, escriba 1 en la posición 128 y restar 128 (168 - 128 = 40).
- ¿Es 40 >= 64?
  - No, escribe 0 en la posición 64 y sigue adelante.
- ¿Es 40 >= 32?
  - Sí, escriba 1 en la posición 32 y restar 32 (40 - 32 = 8).
- ¿Es 8 >= 16?
  - No, escribe 0 en la posición 16 y sigue adelante.
- ¿Es 8 >= 8?
  - Igual. Introduzca 1 en la posición 8 y restar 8 (8 - 8 = 0).
- No quedan valores. Introduzca 0 en las posiciones binarias restantes.

#### Tabla — Resultado de la conversión

| 128 | 64 | 32 | 16 | 8 | 4 | 2 | 1 |
|----:|---:|---:|---:|--:|--:|--:|--:|
| 1   | 0  | 1  | 0  | 1 | 0 | 0 | 0 |

Decimal **168** se escribe como **10101000** en binario.

Fuente: <https://www.netacad.com/es>

### 3.7 Direcciones IPv4

Los Routers y las computadoras solo entienden el binario, mientras que los humanos trabajan en decimal. Es importante que usted conozca a fondo estos dos sistemas de numeración y cómo se utilizan en redes.

[FIGURA — Esquema de una dirección IPv4 con los cuatro octetos en decimal y su equivalente binario debajo de cada uno (todos enmarcados en naranja):

- `192` → `11000000`
- `168` → `10101000`
- `10` → `00001010`
- `10` → `00001010`]

Fuente: <https://www.netacad.com/es>

---

## 4. Práctica

### Foro de Práctica

Ingresar a la Plataforma virtual de aprendizaje y realizar el siguiente foro:

**S06.s11 - Foro Direccionamiento Ipv4**

Investigar sobre las principales categorías de las direcciones IPv4.

Desarrollar la actividad establecida y responder en el mismo foro.

---

## 5. Cierre

### 5.1 Preguntas de reflexión

- ¿Qué hemos aprendido el día de hoy?
- ¿Cómo puedo aplicar lo aprendido?

[FIGURA: Ilustración estilo cartoon de un joven con polo celeste y pantalón oscuro, mirando hacia arriba con expresión pensativa y un signo de interrogación dentro de una burbuja de pensamiento.]

Fuente: <https://www.pinterest.com/pin/498804021304745750/>

### 5.2 Conclusiones

- La estructura de direcciones IP consta de una dirección de red jerárquica de 32 bits que identifica una red y una parte de host.
- Los dispositivos de red utilizan un proceso denominado **AnDing** mediante la dirección IP y la máscara de subred asociada para identificar las porciones de red y host.

> **Nota de transcripción:** la diapositiva dice "AnDing" — corresponde al proceso **ANDing** (operación lógica AND bit a bit). Se preserva la grafía exacta del original.

---

## Referencias bibliográficas

### Versión 1 (formato APA estilo cita corta)

- Kurose, J. y Ross, K. (2017). *Redes de computadoras. Un enfoque descendente.* (7ª ed.). Pearson Educación.
- Robledo, C. (2002). *Redes de computadoras.* (1ª ed.). Instituto Politécnico Nacional.

### Versión 2 (formato extenso, en mayúsculas)

- *KUROSE, JAMES F.* (2017), Redes de computadoras un enfoque descendente basado en internet, 7ma Edición, Pearson Educación.
- *ROBLEDO SOSA, CORNELIO.* (2002), Redes de computadoras, 1ra Edición, Instituto Politécnico Nacional.

> **Nota de transcripción:** la diapositiva original incluye **dos páginas consecutivas** de referencias bibliográficas con la misma información en distintos formatos. Ambas se preservan tal como aparecen.

---

[CONTRAPORTADA: Logo institucional UTP (Universidad Tecnológica del Perú) centrado sobre fondo blanco.]

---

## Resumen estructural

| Elemento   | Cantidad | Observaciones                                                                                                                  |
|------------|----------|--------------------------------------------------------------------------------------------------------------------------------|
| Figuras    | 6        | Retrato Krutch, diagrama IPv4 network/host, ilustración estudiante pensativo, diagrama decisión n≥128, esquema decimal↔binario de octetos, diagrama LAN multi-host. |
| Tablas     | 6        | 2 de notación posicional decimal/binaria, 2 de conversión binaria → decimal por octeto, 1 de resultado conversión 168 → binario. |
| Fórmulas   | 0        | (Sólo expresiones de potencias inline 2⁰…2⁷ y 10⁰…10³ en tablas).                                                              |
| Código     | 0        | —                                                                                                                              |
| Diagramas  | 3        | Topología 3-subredes (Dudas), topología LAN multi-host (Utilidad), comparativa binario↔decimal de dos LAN (Transformación).    |
| Ejercicios | 2        | Pregunta sobre puertas de enlace (Dudas) + Foro S06.s11 (Direccionamiento IPv4).                                               |
