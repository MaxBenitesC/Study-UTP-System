# Sistemas Operativos — Semana 1

## Sistema de Evaluación

| Tipo | Descripción | Semana | Peso |
|------|-------------|--------|------|
| PC1  | Práctica Calificada 1 | 5  | 20% |
| PC2  | Práctica Calificada 2 | 10 | 20% |
| PC3  | Práctica Calificada 3 | 15 | 20% |
| PA   | Participación en Clase | 17 | 10% |
| EXFN | Examen Final          | 18 | 30% |

**Fórmula:** `(20%)PC1 + (20%)PC2 + (20%)PC3 + (10%)PA + (30%)EXFN`  
Nota mínima aprobatoria: **12**

---

## Sesión 01 — Introducción a los Sistemas Informáticos: Elementos Básicos

### ¿Qué es un Sistema Informático?
Conjunto de componentes interconectados que permiten la **entrada, procesamiento, almacenamiento y salida** de datos.  
Está formado por: **Hardware + Software + Personal (usuarios)**.

### Importancia
- Automatización de procesos.
- Mejora de la productividad y eficiencia.
- Facilita la toma de decisiones a través de datos.

### Características clave
| Característica | Descripción |
|----------------|-------------|
| Interactividad | Interactúa con los usuarios de forma dinámica |
| Escalabilidad  | Crece en capacidad: usuarios, datos, procesos |
| Conectividad   | Se comunica con otros sistemas, redes o dispositivos |
| Automatización | Ejecuta tareas sin intervención humana constante |
| Versatilidad   | Se adapta a diferentes usos, entornos o necesidades |

### Ejemplos de sistemas informáticos
Cajeros automáticos (ATM), ERP empresariales, equipos médicos digitales, apps móviles, controladores industriales.

---

### Elementos Básicos del Sistema Informático

#### 1. CPU — Unidad Central de Procesamiento
- Ejecuta instrucciones del programa.
- Se divide en: **Unidad de Control** y **Unidad Aritmético-Lógica (ALU)**.
- Contiene registros internos de alta velocidad.
- Se mide en **GHz** y número de **núcleos**.

#### 2. Memoria Principal (RAM)
- Almacena instrucciones y datos de forma temporal.
- Es **volátil** y de acceso aleatorio.
- Determina cuántas tareas se pueden ejecutar simultáneamente.
- Tipos comunes: DDR3, DDR4, DDR5.

#### 3. Dispositivos de Entrada/Salida (E/S)
| Tipo | Ejemplos |
|------|----------|
| Entrada | Teclado, ratón, cámara, micrófono |
| Salida | Monitor, impresora, altavoces |
| Entrada/Salida | Pantalla táctil, modem, disco duro externo |

#### 4. Registros del Procesador
Memoria ultra rápida dentro de la CPU.

| Registro | Función |
|----------|---------|
| PC (Program Counter) | Apunta a la siguiente instrucción |
| IR (Instruction Register) | Almacena la instrucción en curso |
| ACC (Acumulador) | Guarda resultados de operaciones |

#### 5. Ciclo de Ejecución de Instrucciones
Las instrucciones se procesan en 3 fases:
1. **Fetch** — Obtener la instrucción de memoria.
2. **Decode** — Interpretar la instrucción.
3. **Execute** — Ejecutar la operación.

**Ejemplo:** `SUMAR A, B` → FETCH → DECODE (identifica A y B) → EXECUTE (A + B)

#### 6. Jerarquía de Memoria
Organiza los tipos de memoria por velocidad y costo:

| Nivel | Tipo | Velocidad | Tamaño | Costo |
|-------|------|-----------|--------|-------|
| 1 | Registros | Muy alta | Muy bajo | Muy alto |
| 2 | Caché L1/L2 | Alta | Bajo | Alto |
| 3 | RAM | Media | Medio | Medio |
| 4 | SSD | Baja | Alto | Bajo |
| 5 | HDD | Muy baja | Muy alto | Muy bajo |

**Ventaja:** Mejora la eficiencia al acceder a datos frecuentes desde las memorias más rápidas.

#### 7. Memoria Caché
Actúa de intermediaria entre la CPU y la RAM.

| Nivel | Velocidad | Alcance |
|-------|-----------|---------|
| L1 | Muy rápida | Por núcleo (exclusiva) |
| L2 | Rápida | Compartida por núcleo |
| L3 | Moderada | Compartida por todo el procesador |

Reduce el tiempo de acceso a datos repetidos.

---

### Ejercicios de Sesión 01
1. **Simulación del ciclo de ejecución:** Describir `MULTIPLICAR A * B` indicando registros usados e intervención de la caché en cada fase (Fetch, Decode, Execute).
2. **Análisis de rendimiento:** Identificar el cuello de botella en un sistema lento, proponer mejoras (más RAM, SSD, caché L3) y explicar el impacto.

---

## Sesión 02 — Técnicas de Comunicación de E/S e Instalación de Linux

### Técnicas de Comunicación E/S
La comunicación E/S se gestiona mediante **buses, registros y controladores**.

| Técnica | Descripción | Ventaja |
|---------|-------------|---------|
| Programada | La CPU ejecuta instrucciones directamente para leer/escribir | Simple, pero ineficiente |
| Interrupciones | El hardware avisa a la CPU cuando el dispositivo está listo | Más eficiente |
| DMA (Acceso Directo a Memoria) | Transfiere datos sin intervención directa de la CPU | Ahorra tiempo y recursos de CPU |

### ¿Qué es Linux?
Sistema operativo **libre, estable y seguro** con kernel modular.  
Distribuciones principales: **Ubuntu, Fedora, Debian, Arch Linux**.

### Requisitos de Instalación
- Procesador: mínimo 1 GHz
- RAM: mínimo 1–2 GB
- Almacenamiento: 20 GB mínimo
- BIOS con soporte de booteo desde USB/DVD

### Medio Booteable
- Formato de imagen: `.ISO`
- Herramientas para crear USB booteable:
  - **Windows:** Rufus
  - **Linux:** `dd`, Balena Etcher
- Diferencia clave: **UEFI** (moderno, seguro) vs **BIOS** (legado)

### Particionado de Disco
| Partición | Tamaño sugerido | Tipo | Sistema de Archivos |
|-----------|-----------------|------|---------------------|
| `/`       | 20 GB           | Primaria | ext4 |
| `/home`   | 50 GB           | Lógica   | ext4 |
| `swap`    | 4 GB            | Lógica   | swap |

### Sistemas de Archivos Linux
| Sistema | Característica |
|---------|----------------|
| ext4 | Moderno, confiable, compatible — el más común |
| xfs  | Alto rendimiento para sistemas de gran tamaño |
| btrfs | Soporta snapshots; ideal para servidores |

### Instalación Paso a Paso
1. Iniciar desde USB/DVD.
2. Elegir idioma y distribución de teclado.
3. Crear y configurar particiones.
4. Asignar zona horaria.
5. Crear usuarios del sistema.
6. Instalar GRUB (gestor de arranque) y reiniciar.

### Comandos Post-Instalación
| Comando | Función |
|---------|---------|
| `sudo apt update` | Actualiza la lista de repositorios |
| `sudo apt install <paquete>` | Instala un paquete |
| `chmod +x <archivo>` | Da permisos de ejecución |
| `nano <archivo>` | Edita archivos desde la terminal |

### Linux vs. Windows
| Característica | Linux | Windows |
|----------------|-------|---------|
| Licencia | Libre (open source) | Privativa |
| Consumo de recursos | Bajo | Medio–Alto |
| Seguridad | Alta | Media |
| Personalización | Alta | Limitada |

### Shell y Administración
- **Shell:** Interfaz textual — Bash, Zsh.
- **Comandos comunes:** `ls`, `cd`, `mv`, `rm`, `man`
- **Archivos importantes:** `/etc/passwd`, `/etc/fstab`, `/var/log/`
- **Entornos gráficos:** GNOME, KDE, XFCE
- **Herramientas útiles:** Synaptic (paquetes), GParted (particiones)

---

### Ejercicios de Sesión 02
1. **Simulación de instalación Linux** (Debian o Ubuntu) en máquina virtual: crear particiones `/`, `/home` y `swap`, asignar sistema de archivos y mostrar resultado post-instalación.
2. **Práctica en terminal:** crear usuario `estudiante`, instalar Firefox con `sudo apt install firefox`, cambiar permisos de `/home/estudiante`.

---

## Resumen General de la Semana

- Un sistema informático está compuesto por **CPU, memoria, dispositivos E/S y registros**.
- Las instrucciones siguen un ciclo repetitivo: **Fetch → Decode → Execute**.
- La **jerarquía de memoria** optimiza el acceso a datos según velocidad y costo.
- La **memoria caché** (L1, L2, L3) acelera operaciones frecuentes del procesador.
- La **comunicación E/S** se maneja mediante tres técnicas: programada, interrupciones y DMA.
- La instalación de Linux involucra: particionado, sistema de archivos, GRUB y configuración inicial.
