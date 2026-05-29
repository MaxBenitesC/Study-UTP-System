---
universidad: UTP
curso: Algoritmos y Estructuras de Datos
tema: Tipos Abstractos de Datos (TAD)
semana: 6
tipo_documento: Diapositivas de clase (convertidas de PPTX)
paginas: 22
fuente_pdf: S06_s1 - TAD.pdf (convertido de S06_s1 - TAD.pptx)
---

# Inventario del documento

- **Archivo:** S06_s1 - TAD.pdf (convertido con LibreOffice desde `S06_s1 - TAD.pptx`)
- **Páginas:** 22 (diapositivas)
- **Curso:** Algoritmos y Estructuras de Datos · **Semana:** 6 (S06_s1)
- **Tema:** Tipos Abstractos de Datos (TAD) — teoría clásica (abstracción, especificación, implementación)
- **Tipo:** Diapositivas de clase (PowerPoint UTP, plantilla minimalista de texto)
- **Estado OCR:** Limpio (texto vectorial)
- **Contiene:** Figuras/diagramas ✔ (1 diagrama de implementación) · Tablas ✘ · Fórmulas ✔ (ecuación del TAD) · Código ✘ · Ejercicios/preguntas ✔
- **Nota:** Este deck es **distinto** y **complementario** al de `S06_s1 TAD_Estructuras_Dinamicas K.pdf`; cubre la teoría conceptual del TAD (no el ejemplo de código de la Pila).

---

# Algoritmos y Estructuras de Datos
## TAD

*(Portada.)*

---

## Logro de aprendizaje

> Al finalizar la sesión el estudiante reconoce los Tipos Abstractos de Datos para desarrollar soluciones algorítmicas en java.

---

## Dudas sobre la clase anterior

Matrices:

- Diferencia entre una matriz triangular y una matriz simétrica
- Es posible que algún elemento de la matriz, no se almacenan en forma contigua, por su tipo de dato, por su tamaño?

---

## Conocimientos previos: TAD

- Creación de algún producto existente
- Imitación de una obra

---

## Utilidad del tema: TAD

Porque es importante:

- Los tipos de datos son necesarios para identificar valores y operaciones posibles para variables y expresiones.
- Para tratar de acercar al mundo real la especificación de los datos de problemas.

---

## Tipos Abstractos de Datos - TDA (abstracción)

- La abstracción es un proceso cognitivo humano esencial para la comprensión de fenómenos o situaciones complejas que consiste en la categorización de elementos en grupos o clases de características similares.
- Cada una de las clases o grupo representa una abstracción, en virtud de la cual se destacan o ignoran determinadas características del grupo.
- La abstracción, por lo tanto, presenta dos caras complementarias:
  - En primer lugar, considera o resalta algunos de los aspectos de los elementos en estudio, en concreto, los aspectos relevantes para el problema o situación que se desea resolver.
  - En segundo lugar, ignora el resto de los detalles –no relevantes para la tarea en curso– de los elementos que se abstraen.

---

## Tipos Abstractos de Datos - TDA (método jerárquico)

- La abstracción permite estudiar un sistema complejo a diferentes niveles de detalle, es decir, la abstracción sigue un método jerárquico.
- El objetivo es poder representar y manejar sistemas complejos de manera más sencilla.
- Para conseguirlo se suele realizar un proceso de abstracción en sentido descendente, lo que implica ir abstrayendo desde niveles más generales a niveles más detallados.

---

## Tipos Abstractos de Datos - TDA (ocultamiento)

- La abstracción produce como resultado el ocultamiento de la información, es decir, las entidades de uno de los niveles están compuestos internamente por entidades del nivel inferior, pero las ocultan. Podemos hablar de dos tipos de abstracción:
  - Abstracción funcional
  - Abstracción de datos

---

## ABSTRACCIÓN FUNCIONAL

- La abstracción funcional surge de la idea de crear procedimientos y funciones e invocarlos en diferentes partes del programa mediante un nombre.
- La parte que se ignora ("la información que se oculta") en este proceso es la de cómo el procedimiento o función realiza su tarea.
- La parte con la que nos quedamos es la interfaz: los parámetros de entrada y salida (y sus tipos), y la descripción de la tarea que realiza.

---

## ABSTRACCIÓN FUNCIONAL (ventajas)

Las ventajas de la abstracción funcional son las siguientes:

- **Generalización del concepto de operador.-** Gracias a esta abstracción se definen operadores adicionales a los del lenguaje (por ejemplo, una función factorial), incluyendo la posibilidad de que dichos operadores no trabajen sobre tipos básicos del lenguaje (por ejemplo, determinantes o multiplicaciones de matrices).
- **Encapsulación y ocultamiento.-** Haciendo que una secuencia de acciones esté oculta y sólo se haga visible desde el exterior su resultado global que se entrega a través de parámetros de salida bien definidos

---

## ABSTRACCIÓN DE DATOS

- La abstracción de datos es la técnica de programación que permite inventar o definir nuevos tipos de datos (tipos de datos definidos por el usuario) adecuados a la aplicación que se desea realizar.
- La abstracción de datos es una técnica muy potente que permite diseñar programas más cortos, legibles y flexibles
- Los tipos de datos son abstracciones y el proceso de construir nuevos tipos se llama abstracción de datos. Los nuevos tipos de datos definidos por el usuario se llaman tipos abstractos de datos

---

## TIPOS ABSTRACTOS DE DATOS (TAD)

- Es una colección de propiedades y de operaciones que se definen mediante una especificación que es independiente de cualquier representación.
- Es un tipo de dato definido por el usuario
- La abstracción se centra en la independencia de la representación.
- Se suele considerar que un tipo abstracto de datos es un tipo de datos construido por el programador para [TEXTO CORTADO — la frase continúa pero queda tapada por el recuadro de la ecuación].

[FÓRMULA / RECUADRO destacado:]
$$\text{TAD} = \text{Representación (datos)} + \text{Operaciones (funciones y procedimientos)}$$

> Un tipo abstracto de datos puede definirse mediante la ecuación:
> **TAD = Representación (datos) + Operaciones (funciones y procedimientos)**

---

## CONSTRUCCIÓN DE TIPOS ABSTRACTOS DE DATOS (TAD)

- Para definir un TAD, se debe comenzar por definir las operaciones que se pueden realizar con él, es decir, qué operaciones son relevantes y útiles para operar con las variables pertenecientes al mismo.
- Esto se conoce como establecer la interfaz del tipo.
- La interfaz permite al programador utilizar el tipo (qué se puede hacer frente a cómo está hecho).

---

## CONSTRUCCIÓN DE TIPOS ABSTRACTOS DE DATOS (TAD) — dos partes

Los TAD tienen dos partes:

- **Especificación.-** Refleja qué hace el tipo. Es describir el comportamiento del TAD. Es necesaria para que el programador sepa cómo debe implementar el TAD.
- **Implementación.-** La implementación a su vez se compone de una interfaz pública, que permite al programador utilizar el TAD, y una implementación que puede variar siempre y cuando la interfaz se mantenga.

---

## ESPECIFICACIÓN DE TIPOS ABSTRACTOS DE DATOS (TAD)

- La especificación de un TAD permite conocer las operaciones que el tipo ofrece y cómo actúan estas operaciones.
- La especificación del TAD puede tener:
  - Un enfoque informal, que describe los datos y las operaciones relacionadas en lenguaje natural.
  - Un enfoque formal, supone suministrar un conjunto de axiomas que describen las operaciones en su aspecto sintáctico y semántico.

---

## ESPECIFICACIÓN DE TIPOS ABSTRACTOS DE DATOS (TAD) — especificación informal

La especificación informal de un TAD consta de dos partes:

- Detallar en los datos del tipo los valores que pueden tomar.
- Describir las operaciones relacionándolas con los datos.

---

## ESPECIFICACIÓN DE TIPOS ABSTRACTOS DE DATOS (TAD) — cuatro propiedades

La especificación de un TAD debe poseer cuatro propiedades:

- **Precisión:** Que implica decir sólo lo imprescindible.
- **Generalidad:** Que quiere decir que sea adaptable a diferentes contextos.
- **Legibilidad:** Entendida como facilidad de comprensión.
- **No-ambigüedad:** Que quiere decir que no dé lugar a distintas interpretaciones.

---

## IMPLEMENTACIÓN DE UN TAD

La implementación de un tipo abstracto de datos es un tipo de datos que consta de:

- **Estructuras de datos propias** (encapsuladas, invisibles para el usuario del tipo de datos). La elección de estas estructuras queda a la libertad del programador, que seleccionará las más eficientes o convenientes.
- **Operaciones sobre esas estructuras propias** (procedimientos y funciones). Dependerán de las estructuras internas seleccionadas.

---

## IMPLEMENTACIÓN DE UN TAD (diagrama)

[DIAGRAMA: "Implementación de un TAD". A la izquierda, una caja "Módulo que utiliza el TAD" conectada por una flecha doble hacia un contenedor rotulado "Implementación de un TAD" que contiene dos cajas:
- "Interfaz (definida en un especificación)" — apuntada por una flecha punteada desde una nota "Vista pública".
- "Implementación (algoritmos + estructuras de datos)" — apuntada por una flecha punteada desde una nota "Vista privada".
Es decir, el módulo cliente solo ve la interfaz (vista pública); la implementación con algoritmos y estructuras queda como vista privada/oculta.]

---

## Resumiendo

- El TAD, es importante porque permite definir estructuras propias para implementar una solución
- Todo lenguaje de programación permite implementar un TAD

---

## Cierre de la clase

Responder lo siguiente:

- El TAD, puede ser un tipo de dato suministrado por el lenguaje de programación?
- El programador, puede crear un TAD?

---

## Preguntas

*(Diapositiva de cierre.)*

---

# Resumen estructural

| Elemento   | Cantidad | Observaciones |
|------------|----------|---------------|
| Figuras    | 1 | Diagrama de "Implementación de un TAD" (módulo cliente → interfaz [vista pública] + implementación [vista privada]). |
| Tablas     | 0 | — |
| Fórmulas   | 1 | Ecuación del TAD: **TAD = Representación (datos) + Operaciones (funciones y procedimientos)**. |
| Código     | 0 | — |
| Diagramas  | 1 | (Contabilizado como figura.) |
| Ejercicios/Preguntas | 4 | 2 de cierre + 2 de dudas previas. |

**Observaciones:** Deck convertido de PPTX con OCR limpio. **Material teórico núcleo del examen de semana 6** sobre TAD: abstracción (funcional vs. de datos), ocultamiento de información, especificación (informal/formal, 4 propiedades: precisión, generalidad, legibilidad, no-ambigüedad) e implementación (interfaz pública / implementación privada). Una frase queda cortada por el recuadro de la ecuación del TAD. Es complementario al deck `S06_s1 TAD_Estructuras_Dinamicas K` (ese trae el ejemplo de código de la Pila). Se conservó la redacción original con erratas ("TDA", "no se almacenan").
