---
universidad: UTP
curso: Sistemas Operativos
tema: Gestión de Interbloqueos — detección, prevención y algoritmos
semana: 6
sesion: 1
tipo_documento: diapositivas
paginas: 16
fuente_pdf: S06_s1 Material.pdf
---

# Sistemas Operativos — Semana 06, Sesión 01
## Gestión de Interbloqueos

## Inventario del documento
- **Archivo:** S06_s1 Material.pdf | **Páginas:** 16
- **Curso:** Sistemas Operativos | **Semana/Sesión:** 06 / 01
- **Tema:** Gestión de interbloqueos (deadlocks): detección, prevención, evitación, recuperación
- **Tipo:** Diapositivas (PDF digital) | **OCR:** texto digital nítido
- **Contiene:** figuras decorativas, 2 bloques de pseudocódigo/protocolo

---

## Logro de aprendizaje
Al finalizar la sesión el estudiante logra:
- **Aprender la detección, prevención y el uso de algoritmos.**

## Temario
- Detección de Interbloqueos
- Prevención de Interbloqueos
- Técnicas y Algoritmos
- Desarrollo de Algoritmos

## Conocimientos previos
- Programación

## Utilidad
- Diseñar sistemas robustos que manejen la concurrencia de manera eficiente y segura.

---

## Estrategias para manejar Deadlocks
Las cuatro grandes estrategias:

1. **Prevención:** Eliminar una de las 4 condiciones de Coffman.
   - Atacar exclusión mutua
   - Atacar retención y espera
   - Permitir apropiación
   - Evitar espera circular
2. **Evitación:** Análisis dinámico para evitar estados inseguros.
   - Algoritmo del Banquero
   - Grafos de asignación de recursos
   - Estados seguros vs inseguros
3. **Detección:** Permitir deadlocks y detectarlos cuando ocurran.
   - Algoritmos de detección de ciclos
   - Matrices de asignación y solicitud
   - Ejecución periódica
4. **Recuperación:** Resolver deadlocks una vez detectados.
   - Terminación de procesos
   - Apropiación de recursos
   - Rollback de procesos

---

## Prevención: Eliminar las 4 condiciones

### 1. Eliminar Exclusión Mutua ❌
**Generalmente imposible:** muchos recursos son inherentemente no compartibles.
```text
// Ejemplo: Impresora - solo un trabajo a la vez
if (recurso_compartible) {
    permitir_acceso_multiple();
} else {
    // No podemos eliminar exclusión mutua
    aplicar_otra_estrategia();
}
```

### 2. Eliminar Retención y Espera ✅
**Factible:** Protocolo de solicitud de todos los recursos.
```text
Protocolo "Todo o Nada":
1. Proceso solicita TODOS los recursos al inicio
2. Solo inicia ejecución si obtiene TODOS
3. Libera TODOS los recursos al terminar

Protocolo de Liberación:
1. Proceso libera todos sus recursos antes de solicitar nuevos
2. Solicita nuevos recursos
3. Re-solicita recursos previamente liberados
```

### 3. Permitir Apropiación ✅
**Efectivo para ciertos recursos:** recursos con estado salvable.

### 4. Eliminar Espera Circular — Orden de Recursos ✅
**Muy efectivo:** ordenamiento global de recursos.
```text
Algoritmo de Ordenamiento:
1. Asignar número único a cada tipo de recurso
   R1 = Impresora (ID: 1)
   R2 = Escáner   (ID: 2)
   R3 = Disco     (ID: 3)
2. Los procesos deben solicitar recursos en orden creciente
   CORRECTO:   Solicitar R1, luego R2, luego R3
   INCORRECTO: Solicitar R3, luego R1
3. Si necesita recurso menor, debe liberar recursos mayores
```

---

## Cierre
- ¿Qué aprendiste en esta sesión?
- Te invitamos a compartir tus conclusiones en clase.

---

## Resumen estructural
| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | ~6       | Decorativas (logo UTP, íconos diana/escalera/muñeco) |
| Tablas     | 0        | — |
| Fórmulas   | 0        | — |
| Código     | 2        | Pseudocódigo de exclusión mutua; protocolos "Todo o Nada", liberación y ordenamiento de recursos |
| Diagramas  | 0        | — |
| Ejercicios | 0        | — |
