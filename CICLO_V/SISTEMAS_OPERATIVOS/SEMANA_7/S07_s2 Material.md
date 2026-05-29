---
universidad: UTP
curso: Sistemas Operativos
tema: Intercambio (swapping), comandos Linux de swap y comparación de técnicas de gestión de memoria
semana: 7
sesion: 2
unidad: "Unidad de aprendizaje 2: Gestión de memoria y gestión de archivos"
tipo_documento: diapositivas
paginas: 15
fuente_pdf: S07_s2 Material.pdf
---

# Sistemas Operativos — Semana 07, Sesión 02
**Unidad 2:** Gestión de memoria y gestión de archivos.

## Inventario
- Archivo: S07_s2 Material.pdf | Páginas: 15 | Tipo: diapositivas | OCR: nítido
- Contiene: bloques de pasos, 3 bloques de comandos bash, 1 tabla comparativa con semáforos de color.

## Logro de aprendizaje
- Administrar la memoria y los archivos que componen un sistema operativo.

## Temario
- Gestión de Memoria.
- Administración básica de memoria.
- Asignación de memoria contigua.

## Conocimientos previos
- Arquitectura de Computadoras.
- Estructura de Datos.
- Matemática discreta.

## Utilidad
- Administrar eficientemente la memoria principal (RAM) del sistema.

---

## Intercambio (Swapping)

### Proceso de intercambio (pasos)
1. **Swap Out:** el SO selecciona un proceso víctima y lo copia al área de swap en disco.
2. **Liberación:** se marca como libre el espacio en memoria principal que ocupaba el proceso.
3. **Swap In:** cuando el proceso necesita ejecutarse, se carga de vuelta a memoria.
4. **Reubicación:** el proceso puede cargarse en una dirección diferente a la original.

### Criterios de selección para Swap Out
- Procesos con mayor tiempo sin usar la CPU.
- Procesos de menor prioridad.
- Procesos que han estado más tiempo en memoria.
- Procesos más grandes (liberar más espacio).

---

## Comandos Linux para gestión de Swap

### SWAPON — Activar área de intercambio
```bash
sudo swapon /dev/sdb1     # Activar partición swap
sudo swapon /swapfile     # Activar archivo swap
swapon --show             # Mostrar áreas swap activas
swapon -s                 # Estadísticas de swap (formato corto)
```

### SWAPOFF — Desactivar área de intercambio
```bash
sudo swapoff /dev/sdb1    # Desactivar partición swap
sudo swapoff -a           # Desactivar todas las áreas swap
sudo swapoff /swapfile    # Desactivar archivo swap específico
```

### VMSTAT — Estadísticas de memoria virtual
```bash
vmstat                    # Estadísticas actuales
vmstat 2 5                # Cada 2 segundos, 5 veces
vmstat -S M               # Mostrar en Megabytes
vmstat -a                 # Incluir memoria activa/inactiva
```

---

## Comparación de técnicas de gestión de memoria
| Técnica | Multiprogramación | Fragmentación | Complejidad | Utilización CPU |
|---------|-------------------|---------------|-------------|-----------------|
| **Monoprogramación** | No | Ninguna | Baja | Muy Baja |
| **Particiones Fijas** | Limitada | Interna | Media | Media |
| **Particiones Variables** | Completa | Externa | Alta | Buena |
| **Con Intercambio (swapping)** | Extendida | Variable | Muy Alta | Óptima |

*(En la diapositiva, cada celda usa un semáforo de color: verde = favorable, amarillo = intermedio, rojo = desfavorable. Ej.: monoprogramación tiene CPU "Muy Baja" en rojo; intercambio tiene CPU "Óptima" en verde pero complejidad "Muy Alta" en rojo.)*

---

## Cierre
- ¿Qué aprendiste en esta sesión?
- Te invitamos a compartir tus conclusiones en clase.

---

## Resumen estructural
| Elemento | Cantidad | Observaciones |
|----------|----------|---------------|
| Figuras  | ~5       | Decorativas (logo, íconos) |
| Tablas   | 1        | Comparación de técnicas de gestión de memoria |
| Código   | 3        | Comandos bash: swapon, swapoff, vmstat |
| Diagramas| 0        | — |
| Ejercicios | 0      | — |
