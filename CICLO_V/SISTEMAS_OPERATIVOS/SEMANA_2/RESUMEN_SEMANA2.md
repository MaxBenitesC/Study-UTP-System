# Sistemas Operativos — Semana 2

## Sesión 01 — Introducción a los Sistemas Operativos

### ¿Qué es un Sistema Operativo?
Software que actúa como **intermediario** entre el hardware del computador y los programas de usuario.  
Gestiona recursos como: CPU, memoria, dispositivos de entrada/salida y almacenamiento.

**Ejemplos:** Windows 11, macOS Sonoma, Linux Ubuntu, Android.

**Características principales:**
- Multitarea
- Gestión de recursos
- Interfaz de usuario
- Seguridad y protección

---

### Funciones Principales del SO

| Función | Recurso gestionado |
|---------|-------------------|
| Administración de CPU | Procesador |
| Administración de memoria | RAM, caché |
| Gestión de archivos | Disco duro, SSD |
| Control de dispositivos | Entrada/Salida (E/S) |
| Interfaz de usuario | GUI o CLI |

---

### Evolución Histórica de los Sistemas Operativos

#### Primera Generación (años 40–50)
- Sin sistemas operativos.
- Programación directa sobre hardware (interruptores físicos).
- No había interfaz ni automatización.
- **Ejemplos:** ENIAC, UNIVAC.
- **Desventaja:** Muy poco amigable para el usuario.

#### Segunda Generación (años 50–60)
- Sistemas por **lotes** (batch processing).
- Uso de tarjetas perforadas para cargar programas.
- Se introduce el **Monitor Residente** (inicio de la automatización).
- **Ejemplo:** IBM 7090.
- **Ventaja:** Reducción del tiempo muerto del CPU.

#### Tercera Generación (años 60–70)
- **Multiprogramación:** el SO controlaba múltiples procesos a la vez.
- Aparece la Interfaz de Línea de Comandos (CLI).
- **Ejemplo:** Unix (1969).
- **Ventaja:** Mayor eficiencia del sistema.

#### Cuarta Generación (años 80 – presente)
- SO personales y móviles.
- Aparición de la **GUI (Interfaz Gráfica)**.
- Sistemas distribuidos y móviles.
- **Ejemplos:** Windows, Android, macOS.
- **Ventaja:** Facilidad de uso y portabilidad.

---

### Componentes del Sistema Operativo

#### Núcleo (Kernel)
Núcleo central del SO. Responsable de la gestión de recursos y la comunicación entre software y hardware.

**Funciones:**
- Planificación de procesos.
- Gestión de memoria.
- Control de dispositivos.

**Ejemplo:** Linux utiliza un kernel monolítico.

#### Gestión de Procesos
Maneja la creación, planificación y terminación de procesos mediante estructuras como el **PCB (Process Control Block)**.

| Proceso | Estado |
|---------|--------|
| P1 | Ejecutando |
| P2 | Listo |
| P3 | Espera |

#### Gestión de Memoria
- Asigna espacio en RAM a los procesos.
- Controla la memoria virtual y física.
- Técnicas: **paginación** y **segmentación**.

**Ejemplo:** Un programa de 1 GB puede usar 200 MB en RAM y el resto en memoria virtual.

#### Sistema de Archivos
Organiza, almacena y permite el acceso a archivos en los dispositivos.

**Tipos:** FAT32, NTFS, ext4.

**Funciones:**
- Jerarquía de carpetas.
- Permisos de acceso.
- Manejadores de archivos.

#### Gestión de Dispositivos
Administra dispositivos de entrada/salida usando **controladores (drivers)**.

| Dispositivo | Driver asociado |
|-------------|-----------------|
| Impresora HP | hp-laserjet-driver |
| GPU NVIDIA | nvidia-driver |

#### Interfaz de Usuario
| Tipo | Características | Ejemplos |
|------|-----------------|---------|
| GUI (Gráfica) | Intuitiva, visual, amigable | Windows, macOS |
| CLI (Comandos) | Potente, flexible, útil para administración | Linux, Unix |

---

### Ejercicios de Sesión 01
1. **Cuadro comparativo:** Analiza un SO actual (Windows, Linux o Android) identificando componentes principales, tipo de kernel e interfaz de usuario. Compáralo con otro SO.
2. **Ciclo de vida de un proceso:** Simula el ciclo desde la creación hasta la finalización, define los estados y representa los cambios con un diagrama de transición.

---

## Sesión 02 — Estructura del SO y Comandos Linux

### Estructura de un Sistema Operativo
Se compone principalmente del **Kernel, Shell y herramientas del sistema**.  
Permite la comunicación entre hardware y software administrando todos los recursos.

---

### El Kernel

#### Definición y Funciones
Es el **núcleo** del SO, encargado de administrar:
- **Procesos:** planificación, ejecución y finalización.
- **Memoria:** asignación, protección y liberación.
- **Dispositivos:** controladores y comunicación.

**Ejemplo:** Linux Kernel 6.x soporta múltiples arquitecturas y drivers.

#### Tipos de Kernel

| Tipo | Descripción | Ventaja | Desventaja |
|------|-------------|---------|------------|
| Monolítico | Todo el código se ejecuta en modo supervisor | Rendimiento alto | Difícil mantenimiento |
| Microkernel | Solo funciones básicas; el resto son servicios externos | Modularidad alta | Menor rendimiento |
| Híbrido | Combinación de ambos | Balance entre rendimiento y modularidad | — |

---

### Shell

El **intérprete de comandos** que permite al usuario interactuar con el SO.

**Tipos:**
- Shell de texto: **Bash, Zsh** (por defecto en la mayoría de distribuciones Linux).
- Shell gráfico: GUI.

---

### Proceso de Arranque (Boot)

| Etapa | Descripción |
|-------|-------------|
| 1. BIOS/UEFI | Inicializa el hardware |
| 2. GRUB | Cargador de arranque; selecciona el sistema operativo |
| 3. Kernel | Se carga en memoria y se inicia |
| 4. Init / systemd | Arranca los servicios y las sesiones de usuario |

**Ejemplo:** GRUB permite seleccionar entre Windows y Linux al arrancar.

---

### Software Libre

**Definición:** Software que garantiza libertad de uso, estudio, modificación y distribución.

**Ejemplos:** GNU/Linux, LibreOffice.

**Ventajas:** Transparencia, comunidad activa, sin costos de licencia.

### Distribuciones de Linux

| Distribución | Enfoque |
|--------------|---------|
| Ubuntu | Fácil de usar; recomendada para principiantes |
| Fedora | Últimas tecnologías; orientada a desarrolladores |
| Debian | Estabilidad; base de muchas otras distribuciones |

---

### Comandos Básicos de Linux

#### `pwd` — Print Working Directory
Muestra el directorio de trabajo actual.
```bash
pwd
# Salida: /home/usuario
```

#### `cd` — Change Directory
Cambia de directorio.
```bash
cd /etc          # Ir a /etc
cd ..            # Subir un nivel
```

#### `ls` — List
Lista archivos y directorios.
```bash
ls          # Lista simple
ls -l       # Lista con detalles (permisos, tamaño, fecha)
ls -a       # Muestra archivos ocultos
ls -la      # Combina ambas opciones
```

#### `cat` — Concatenate
Muestra el contenido de un archivo.
```bash
cat archivo.txt
```
> Nota: no es ideal para archivos largos.

#### `more` y `less`
Paginan el contenido de archivos grandes.
```bash
more archivo.txt    # Desplazamiento solo hacia adelante
less archivo.txt    # Desplazamiento bidireccional (más recomendado)
```

---

### Ejercicios de Sesión 02
1. **Script en Bash** que:
   - Muestre el directorio actual (`pwd`).
   - Liste todos los archivos (`ls`).
   - Muestre el contenido de un archivo especificado por el usuario (`cat`).
2. **Proceso de arranque:** Simula y describe cada etapa (BIOS → GRUB → Kernel → systemd) con ejemplos prácticos en una máquina virtual Linux.

---

## Resumen General de la Semana

- Un **sistema operativo** es esencial para el funcionamiento de cualquier sistema informático.
- Ha evolucionado desde simples gestores de lotes (años 40) hasta plataformas complejas multitarea (presente).
- Está compuesto por múltiples módulos: **gestión de procesos, memoria, archivos, dispositivos e interfaz**.
- El **Kernel** administra recursos y procesos; el **Shell** es la interfaz con el usuario.
- El **proceso de arranque** sigue la secuencia: BIOS/UEFI → GRUB → Kernel → Init/systemd.
- El **software libre** y las distribuciones Linux ofrecen flexibilidad, seguridad y sin costos de licencia.
- Los **comandos básicos** (`pwd`, `cd`, `ls`, `cat`, `more`, `less`) son fundamentales para la administración del sistema.
