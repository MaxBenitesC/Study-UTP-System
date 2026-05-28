# Guía de Administración de Usuarios y Grupos en Linux

**Responsable Técnico:** Max Anderson Benites Corazón
**Cargo:** Senior Technical Implementation Specialist
**Empresa:** NCR VOYIX (Sede Perú)

Este documento detalla el flujo de trabajo y los comandos necesarios para completar la práctica de gestión de usuarios y grupos (Semana 9).

## Flujo de Trabajo - Laboratorio Semana 9

### 1. Verificación Inicial
Identificar el usuario actual de la sesión.
```bash
whoami
```

### 2. Creación de Usuarios
Provisión de cuentas para el personal de TechSolutions SAC.
```bash
sudo adduser developer1
sudo adduser developer2
sudo adduser soporte1
```

### 3. Gestión de Grupos
Creación de contenedores de seguridad y asignación de membresías.
```bash
# Crear grupos
sudo addgroup devteam
sudo addgroup soporte

# Asignar usuarios a grupos (Append)
sudo usermod -aG devteam developer1
sudo usermod -aG devteam developer2
sudo usermod -aG soporte soporte1

# Verificación
groups developer1
```

### 4. Seguridad y Mantenimiento
Rotación de credenciales y reestructuración de grupos.
```bash
# Cambio de contraseña
sudo passwd developer1

# Renombrar grupo (de devteam a desarrolladores)
sudo groupmod -n desarrolladores devteam
```

### 5. Administración Avanzada y Auditoría
Uso de herramientas de gestión de miembros y limpieza de sistema.
```bash
# Agregar miembro vía gpasswd
sudo gpasswd -a developer1 desarrolladores

# Auditoría de grupos
groups developer1

# Eliminación segura (sin dejar huérfanos)
sudo deluser --remove-home soporte1
```

### 6. Actividad Integradora
Escenario de alta de nuevo personal.
```bash
sudo adduser analista1
sudo usermod -aG desarrolladores analista1
groups analista1
sudo passwd analista1
```

---
*Nota: Este flujo es compatible con distribuciones basadas en Debian (como Parrot OS y Ubuntu).*
