---
universidad: UTP
curso: Sistemas Operativos
tema: Gestión de usuarios y grupos en Linux (comandos) + repaso de segmentación
semana: 9
sesion: 2
unidad: "Unidad de aprendizaje 2: Gestión de memoria — Segmentación"
tipo_documento: diapositivas
paginas: 21
fuente_pdf: S09_s2 Material.pdf
---

# Sistemas Operativos — Semana 09, Sesión 02
**Unidad 2:** Gestión de memoria — Segmentación (y administración de cuentas en Linux).

## Inventario
- Archivo: S09_s2 Material.pdf | Páginas: 21 | Tipo: diapositivas | OCR: nítido
- Contiene: múltiples bloques de comandos bash (gestión de usuarios/grupos), flujo práctico completo, conclusiones.

## Logro / Temario / Conocimientos previos / Utilidad
- **Logro:** administrar cuentas en Linux.
- **Temario:** Tipos de Segmentación; Segmentación Paginada; Comandos Linux.
- **Conocimientos previos:** Arquitectura de computadoras; Programación básica.
- **Utilidad:** administrar cuentas y usuarios Linux.

---

## Comandos Linux: Gestión de Usuarios

### `adduser` — crear usuario
```bash
sudo adduser nombre_usuario
# Crea un nuevo usuario de forma interactiva, con directorio home y configuración básica.
$ sudo adduser juan
```

### `usermod` — modificar usuario
```bash
sudo usermod [opciones] nombre_usuario
# Modifica una cuenta de usuario existente.
# Opciones: -aG (agregar a grupo), -l (cambiar nombre), -d (cambiar home).
$ sudo usermod -aG sudo juan
```

### `deluser` — eliminar usuario
```bash
sudo deluser [--remove-home] nombre_usuario
# Elimina un usuario del sistema. Con --remove-home también elimina su directorio personal.
$ sudo deluser --remove-home juan
```

---

## Comandos Linux: Contraseñas y Grupos

### `passwd` — cambiar contraseña
```bash
passwd [nombre_usuario]
# Cambia la contraseña de un usuario. Sin argumentos, cambia la del usuario actual.
$ sudo passwd juan
```

### `addgroup` — crear grupo
```bash
sudo addgroup nombre_grupo
# Crea un nuevo grupo en el sistema.
$ sudo addgroup desarrolladores
```

### `groupmod` — modificar grupo
```bash
sudo groupmod [opciones] nombre_grupo
# Modifica las propiedades de un grupo. Opciones: -n (renombrar), -g (cambiar GID).
$ sudo groupmod -n devs desarrolladores
```

---

## Comandos Linux: Información y Gestión

### `gpasswd` — administrar grupos
```bash
sudo gpasswd [opciones] nombre_grupo
# Administra grupos. Opciones: -a (agregar usuario), -d (eliminar usuario), -A (administradores).
$ sudo gpasswd -a juan desarrolladores
```

### `whoami` — usuario actual
```bash
whoami
# Muestra el nombre del usuario actual que está ejecutando el comando.
$ whoami
juan
```

### `groups` — grupos de un usuario
```bash
groups [nombre_usuario]
# Muestra los grupos a los que pertenece un usuario. Sin argumentos, los del usuario actual.
$ groups juan
juan : juan sudo desarrolladores
```

---

## Ejemplos Prácticos — Flujo Completo ⭐ (escenario típico de examen)
```bash
# 1. Crear nuevo usuario
$ sudo adduser developer1

# 2. Crear grupo de desarrollo
$ sudo addgroup devteam

# 3. Agregar usuario al grupo
$ sudo usermod -aG devteam developer1

# 4. Verificar membresía
$ groups developer1
developer1 : developer1 devteam

# 5. Cambiar contraseña
$ sudo passwd developer1
```

---

## Conclusiones
- **Segmentación:** proporciona una visión lógica de la memoria que se alinea con la estructura del programa, facilitando la protección y compartición de código.
- **Segmentación Paginada:** combina lo mejor de ambos mundos — organización lógica de la segmentación y eficiencia de la paginación, eliminando la fragmentación externa.
- **Comandos Linux:** la gestión de usuarios y grupos es fundamental para la administración de sistemas Linux, permitiendo control de acceso y organización de permisos.
- **Recuerda:** la segmentación paginada se usa en sistemas modernos como **x86-64**, combinando protección por segmentos con la eficiencia de las páginas.

## Cierre
- ¿Qué aprendiste en esta sesión? Comparte tus conclusiones en clase.
- *[Página 21: cierre con logo UTP.]*

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras  | ~2       | Decorativas (logo, ícono) |
| Tablas   | 0        | — |
| Código   | 9        | Comandos: adduser, usermod, deluser, passwd, addgroup, groupmod, gpasswd, whoami, groups + flujo completo |
| Diagramas| 0        | — |
| Ejercicios | 1      | Flujo práctico completo (crear usuario → grupo → agregar → verificar → contraseña) |
