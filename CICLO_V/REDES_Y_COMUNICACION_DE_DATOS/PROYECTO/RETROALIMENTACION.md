# 📘 Análisis de Retroalimentación del Profesor
## Proyecto: Arquitectura de Red IPv4 para Call Center Multisede

---

# 🧠 1. Contexto General

El profesor **valida la idea del proyecto**, indicando que:

> "El tema está fantástico"

Sin embargo, el problema principal radica en la **forma en que está desarrollado**, no en el tema.

---

# ❌ 2. Problemas Identificados por el Profesor

## 2.1 Problema mal definido
- Se describe de forma general:
  - “hay caídas”
  - “hay latencia”
  - “no están conectados”
- Falta precisión técnica.

### ✔ Lo que se espera:
- Métricas reales:
  - Latencia (ms)
  - Jitter
  - Pérdida de paquetes (%)
  - Uso de ancho de banda (%)
  - Frecuencia de caídas
- Evidencias (pantallazos, logs)

---

## 2.2 Falta comparación “ANTES vs DESPUÉS”
- No se demuestra mejora.

### ✔ Lo que se espera:
- Comparativa clara:
  - Estado actual (problema)
  - Estado propuesto (solución)

Ejemplo:
- Antes: 120 ms latencia
- Después: 20 ms latencia

---

## 2.3 Falta investigación real
- No hay validación con el entorno real.

### ✔ Lo que se espera:
- Entrevista con:
  - Administrador de red
  - Personal técnico
- Recolección de:
  - Configuraciones
  - Proveedores
  - Infraestructura actual

---

## 2.4 Soluciones sin diagnóstico
- Se proponen:
  - VPN
  - VLAN
  - Failover

Pero sin justificar técnicamente.

### ✔ Lo que se espera:
1. Diagnóstico
2. Análisis
3. Justificación
4. Solución

---

## 2.5 Marco teórico incompleto
### Actualmente incluye:
- VoIP
- VLAN
- VPN

### ❌ Falta:
- SIP
- RTP
- QoS
- Plan IP
- Seguridad de red
- Arquitectura
- Cálculo de ancho de banda
- Ubicación de servidores
- Balanceo de carga
- Alta disponibilidad (HA)

---

## 2.6 Falta simulación y pruebas
- No se valida la solución.

### ✔ Lo que se espera:
- Simulación en:
  - Packet Tracer
  - GNS3 u otro
- Pruebas de:
  - Caídas
  - Failover
  - Latencia
  - Carga

---

## 2.7 Falta de procesos organizacionales
- No existe procedimiento ante fallos.

### ✔ Lo que se espera:
- Definir:
  - Qué hacer ante caída
  - Quién responde
  - Flujo de atención

---

## 2.8 Problemas de formato académico
- No se sigue el formato requerido.
- Falta:
  - Estructura correcta
  - Citas en formato APA

---

# 🎯 3. Qué quiere lograr el profesor

El profesor busca que el proyecto sea un:

## 🔥 Proyecto de Ingeniería Real

No solo teoría, sino una solución aplicable y validada.

---

# 🏗️ 4. Componentes esperados del proyecto

## 4.1 Diagnóstico real
- Análisis de la red actual
- Métricas reales
- Evidencia visual (pantallazos)

---

## 4.2 Diseño de solución
- Arquitectura de red
- Topología
- Tecnologías justificadas

---

## 4.3 Simulación
- Modelado de la red
- Pruebas controladas

---

## 4.4 Comparación
- Antes vs después
- Mejora cuantificable

---

## 4.5 Implementación técnica (teórica)
- Cómo se configuraría la solución
- Parámetros y lógica de implementación

---

# 🚨 5. Exigencias clave del profesor

## 🔑 Obligatorio:

1. Evidencia real (pantallazos, métricas)
2. Comparación antes vs después
3. Problema bien definido y medido
4. Simulación y pruebas
5. Validación con el cliente

---

# 💡 6. Conclusión

El profesor no quiere un trabajo descriptivo.

👉 Quiere un proyecto que:
- Diagnostique
- Analice
- Diseñe
- Simule
- Demuestre mejoras reales

---

# 📌 Frase clave

> “Dejen de explicar redes y empiecen a resolver un problema real con datos reales.”
