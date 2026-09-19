# 100000SI44 – Sistemas Operativos

## Segunda Práctica Calificada

**Julio 2026**

**Sede:** Arequipa  
**Área:** Ingeniería  
**Profesora:** Ing. Ivonne S. Musayón O  
**Clase Nro.:** 21002  
**Duración:** 90 minutos  
**Fecha del examen:** 21 de julio de 2026  
**Hora programada:** 08:30 – 10:00

**Apellidos y nombres:** ________________________________________________  
**Código de alumno:** ____________________  
**Ciclo:** V

---

## Pregunta 1 (4 puntos)

Resalte con AMARILLO la alternativa correcta.

Un sistema operativo debe almacenar información de los usuarios y mantener la estructura del sistema de archivos.

¿Cuál de las siguientes afirmaciones es correcta?

a) Los archivos especiales almacenan documentos del usuario.

<mark>**b) Los directorios organizan la estructura del sistema de archivos.**</mark>

c) Los archivos regulares representan dispositivos de entrada y salida.

d) Todos los archivos poseen la misma función.

---

## Pregunta 2 (4 puntos)

Indique si la siguiente afirmación es Verdadera (V) o Falsa (F). Resalte con AMARILLO la respuesta correcta y JUSTIFIQUE.

> “La virtualización permite ejecutar varios sistemas operativos sobre un mismo hardware físico, mejorando el aprovechamiento de los recursos computacionales”.

<mark>**[VERDADERO]**</mark> [FALSO]

**Justificación:** La afirmación es verdadera porque la virtualización utiliza un hipervisor para dividir y administrar los recursos de un equipo físico, como el procesador, la memoria, el almacenamiento y la red. De esta manera, varias máquinas virtuales pueden ejecutar sistemas operativos independientes y aislados sobre el mismo hardware. Esto permite aprovechar mejor la capacidad disponible y reduce la necesidad de contar con un servidor físico para cada sistema operativo o servicio.

---

## Pregunta 3 (4 puntos)

Resalte con AMARILLO la alternativa correcta.

Para proteger la información de una empresa, un administrador implementa un mecanismo que transforma los datos en un formato ilegible para usuarios no autorizados.

¿A qué mecanismo corresponde?

a) Backup

b) Autenticación

<mark>**c) Criptografía**</mark>

d) Virtualización

---

## Pregunta 4 (4 puntos)

Una empresa desea migrar sus servidores físicos a una infraestructura virtualizada.

Explique dos ventajas de la virtualización y mencione un mecanismo de seguridad que implementaría para proteger la información.

**Respuesta:**

1. **Mejor aprovechamiento de los recursos:** varios servidores virtuales pueden funcionar sobre un mismo servidor físico y compartir de manera controlada su capacidad de procesamiento, memoria y almacenamiento. Esto evita que los recursos permanezcan subutilizados y disminuye la cantidad de equipos físicos necesarios.

2. **Mayor flexibilidad y facilidad de recuperación:** las máquinas virtuales se pueden crear, copiar, trasladar y restaurar con mayor rapidez que un servidor físico. Además, mediante copias de seguridad e instantáneas es posible recuperar un servicio con rapidez ante una falla, reduciendo el tiempo de inactividad.

Como mecanismo de seguridad implementaría el **cifrado de la información**, tanto en los discos virtuales como durante su transmisión por la red. El cifrado transforma los datos en contenido ilegible para quien no posea la clave correspondiente, protegiendo la confidencialidad de la información incluso si un archivo o respaldo fuera obtenido por una persona no autorizada.

---

## Pregunta 5 (4 puntos)

### Caso práctico

Dadas las solicitudes de acceso al disco:

**98, 183, 37, 122, 14, 124, 65 y 67**

con la cabeza lectora inicialmente en el cilindro **53**, resuelva utilizando los algoritmos **FCFS** y **SSTF**, indicando:

- El orden de atención.
- El recorrido total de la cabeza.
- ¿Cuál algoritmo presenta un mejor desempeño? Justifique.

### Resolución mediante FCFS

FCFS atiende las solicitudes en el mismo orden en que llegaron.

**Orden de atención:**

**53 → 98 → 183 → 37 → 122 → 14 → 124 → 65 → 67**

**Cálculo del recorrido:**

- De 53 a 98: |98 − 53| = 45 cilindros.
- De 98 a 183: |183 − 98| = 85 cilindros.
- De 183 a 37: |37 − 183| = 146 cilindros.
- De 37 a 122: |122 − 37| = 85 cilindros.
- De 122 a 14: |14 − 122| = 108 cilindros.
- De 14 a 124: |124 − 14| = 110 cilindros.
- De 124 a 65: |65 − 124| = 59 cilindros.
- De 65 a 67: |67 − 65| = 2 cilindros.

**Recorrido total de FCFS:**

45 + 85 + 146 + 85 + 108 + 110 + 59 + 2 = **640 cilindros**.

### Resolución mediante SSTF

SSTF selecciona, en cada paso, la solicitud pendiente que se encuentra a menor distancia de la posición actual de la cabeza.

**Orden de atención:**

**53 → 65 → 67 → 37 → 14 → 98 → 122 → 124 → 183**

**Cálculo del recorrido:**

- De 53 a 65: |65 − 53| = 12 cilindros.
- De 65 a 67: |67 − 65| = 2 cilindros.
- De 67 a 37: |37 − 67| = 30 cilindros.
- De 37 a 14: |14 − 37| = 23 cilindros.
- De 14 a 98: |98 − 14| = 84 cilindros.
- De 98 a 122: |122 − 98| = 24 cilindros.
- De 122 a 124: |124 − 122| = 2 cilindros.
- De 124 a 183: |183 − 124| = 59 cilindros.

**Recorrido total de SSTF:**

12 + 2 + 30 + 23 + 84 + 24 + 2 + 59 = **236 cilindros**.

### Comparación y conclusión

El algoritmo **SSTF presenta un mejor desempeño para este conjunto de solicitudes**, porque la cabeza realiza un recorrido total de **236 cilindros**, mientras que con FCFS recorre **640 cilindros**. SSTF reduce el desplazamiento en **404 cilindros**, equivalente a una disminución aproximada del **63.13 %** respecto de FCFS. Esto ocurre porque atiende primero las solicitudes más cercanas a la posición actual de la cabeza, reduciendo el movimiento y el tiempo promedio de búsqueda.

---

**Fin de la evaluación**
