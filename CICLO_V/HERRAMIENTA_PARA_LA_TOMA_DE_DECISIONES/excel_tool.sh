#!/usr/bin/env bash
# excel_tool.sh  v3.0  — UTP Herramientas Informáticas para la Toma de Decisiones
# Menú para principiantes | Ejemplos visuales | Generador de fórmulas | Prácticas con validador

# ── Colores ───────────────────────────────────────────────────────────────────
R='\033[0;31m'  G='\033[0;32m'  Y='\033[1;33m'  C='\033[0;36m'
W='\033[1;37m'  D='\033[2m'     BG='\033[1;32m'  BY='\033[1;33m'
BC='\033[1;36m' BM='\033[1;35m' BR='\033[1;31m'  NC='\033[0m'
ANCHO=70

# ── Utilidades base ───────────────────────────────────────────────────────────
sep() {
    local t="$1" c="${2:-=}" lado linea
    if [[ -n "$t" ]]; then
        lado=$(( (ANCHO - ${#t} - 2) / 2 ))
        linea=$(printf "%${lado}s" | tr ' ' "$c")
        echo -e "\n${BC}${linea} ${BY}${t}${BC} ${linea}${NC}"
    else
        echo -e "\n${D}$(printf "%${ANCHO}s" | tr ' ' "$c")${NC}"
    fi
}
ok()     { echo -e "  ${BG}[OK]${NC} $*"; }
err()    { echo -e "  ${BR}[X]${NC} $*"; }
info()   { echo -e "  ${C}>>>${NC} $*"; }
hint()   { echo -e "  ${D}$*${NC}"; }
titulo() { echo -e "\n  ${BY}$*${NC}"; }
pausar() { echo -en "\n  ${D}Presiona Enter para continuar...${NC}"; read -r; }
limpiar(){ printf '\n%.0s' {1..2}; }

parse_rango() {
    local r="${1^^}"; [[ "$r" != *":"* ]] && r="$r:$r"
    INI_COL=$(echo "${r%%:*}" | grep -oP '^[A-Z]+')
    INI_FIL=$(echo "${r%%:*}" | grep -oP '[0-9]+$')
    FIN_COL=$(echo "${r##*:}" | grep -oP '^[A-Z]+')
    FIN_FIL=$(echo "${r##*:}" | grep -oP '[0-9]+$')
}
parse_celda() {
    COL_P=$(echo "${1^^}" | grep -oP '^[A-Z]+')
    FIL_P=$(echo "${1^^}" | grep -oP '[0-9]+$')
}

mostrar_formulas() {
    local -a pares=("$@")
    sep "FORMULAS GENERADAS — copia y pega en Excel" "-"
    echo -e "  ${D}Celda    Formula${NC}"
    echo -e "  ${D}$(printf '%.0s─' {1..65})${NC}"
    local i
    for (( i=0; i<${#pares[@]}; i+=2 )); do
        echo -e "  ${BG}${pares[$i]}${NC}    ${W}${pares[$((i+1))]}${NC}"
    done
    echo -e "\n  ${BG}Listo: $((${#pares[@]}/2)) formula(s) para pegar en tu hoja de Excel.${NC}"
    pausar
}

# ── Validador de practicas ─────────────────────────────────────────────────────
PUNTAJE=0
TOTAL=0

validar() {
    local esperada="$1" pista="${2:-}"
    TOTAL=$(( TOTAL + 1 ))
    echo -en "\n  ${C}Escribe tu formula: ${NC}"
    read -r resp
    local nr ne
    nr=$(echo "${resp^^}" | tr -d ' ' | sed 's/,/;/g; s/\.\././')
    ne=$(echo "${esperada^^}" | tr -d ' ' | sed 's/,/;/g; s/\.\././')
    if [[ "$nr" == "$ne" ]]; then
        echo -e "  ${BG}[CORRECTO!] Excelente.${NC}"
        PUNTAJE=$(( PUNTAJE + 1 ))
        return 0
    else
        echo -e "  ${BR}[INCORRECTO]${NC}"
        echo -e "  Respuesta correcta: ${G}${esperada}${NC}"
        [[ -n "$pista" ]] && echo -e "  ${D}Pista: ${pista}${NC}"
        return 1
    fi
}

mostrar_puntaje() {
    sep "RESULTADO DE LA PRACTICA" "-"
    local pct=$(( PUNTAJE * 100 / TOTAL ))
    echo -e "  Respondiste correctamente ${BY}${PUNTAJE} de ${TOTAL}${NC} ejercicios (${pct}%)"
    if   (( pct == 100 )); then echo -e "  ${BG}Perfecto! Dominas este tema.${NC}"
    elif (( pct >= 60 ));  then echo -e "  ${BY}Bien! Repasa los que fallaste.${NC}"
    else                        echo -e "  ${BR}Sigue practicando, revisa la seccion APRENDER.${NC}"
    fi
    PUNTAJE=0; TOTAL=0
    pausar
}

# ═════════════════════════════════════════════════════════════════════════════
# A P R E N D E R
# ═════════════════════════════════════════════════════════════════════════════

# ─── A1. Referencias ──────────────────────────────────────────────────────────
aprender_referencias() {
    sep "A1 · COMO FUNCIONA EL SIGNO $ EN EXCEL"

    titulo "EL PROBLEMA SIN $"
    echo -e "
  Imagina que tienes este precio fijo en la celda ${W}E1 = 1.18${NC} (IGV)
  y en ${W}B2${NC} tienes el precio base. Tu formula en ${W}C2${NC} es:

      ${G}=B2*E1${NC}

  Si copias C2 hacia abajo (a C3, C4...) Excel ajusta TODO:
      C3 → ${R}=B3*E2${NC}  ← ERROR: E2 esta vacia, el IGV desaparece!

  ${BY}SOLUCION: fijar E1 con el signo $${NC}"

    titulo "LOS 3 TIPOS DE REFERENCIAS"
    echo -e "
  ${W}A1${NC}     Relativa   → cambia TODO al copiar
            Al copiar 2 filas abajo: ${G}A3${NC}

  ${W}\$A\$1${NC}  Absoluta   → NO cambia nada (fija col Y fila)
            Al copiar donde sea: ${G}\$A\$1${NC}
            Usa esto para celdas fijas como IGV, tasas, tablas.

  ${W}\$A1${NC}   Mixta col  → columna fija, fila libre
  ${W}A\$1${NC}   Mixta fil  → fila fija, columna libre
            Utiles para tablas de multiplicar, tablas cruzadas."

    titulo "EL ATAJO F4 — cicla entre tipos"
    echo -e "
  Escribe ${W}A1${NC} en la barra de formulas, luego presiona F4:
  ${C}F4 x1${NC}  A1  →  \$A\$1   (ambas fijas)
  ${C}F4 x2${NC}  A1  →  A\$1    (solo fila fija)
  ${C}F4 x3${NC}  A1  →  \$A1    (solo columna fija)
  ${C}F4 x4${NC}  A1  →  A1     (vuelve a relativa)"

    titulo "EJEMPLO PRACTICO"
    echo -e "
  ${D}Tabla de precios con IGV en F1:${NC}

  ${D}     A          B       C${NC}
  ${D}  1  Producto   Precio  Con IGV${NC}
  ${D}  2  Laptop     2000    =B2*\$F\$1${NC}  ← F1 fija, B2 relativa
  ${D}  3  Celular    800     =B3*\$F\$1${NC}  ← al copiar, B cambia pero F1 no
  ${D}  4  Tablet     500     =B4*\$F\$1${NC}"

    titulo "REFERENCIA A OTRA HOJA"
    echo -e "
  ${W}=Empleados!B5${NC}           celda B5 de la hoja 'Empleados'
  ${W}='Datos Ventas'!C3${NC}       si el nombre tiene espacios, usa comillas"
    pausar
}

# ─── A2. Fechas ───────────────────────────────────────────────────────────────
aprender_fecha() {
    sep "A2 · CALCULAR CON FECHAS EN EXCEL"

    titulo "FUNCIONES PRINCIPALES"
    echo -e "
  ${W}=HOY()${NC}
  Muestra la fecha de hoy automaticamente. Se actualiza cada vez
  que abres el archivo. No necesita argumentos.
  Ejemplo → si hoy es 03/05/2026, la celda mostrara: ${G}03/05/2026${NC}

  ${W}=AHORA()${NC}
  Igual que HOY() pero incluye la hora: ${G}03/05/2026 14:35${NC}

  ${W}=SI.FECHA(fecha_inicio; fecha_fin; unidad)${NC}
  Calcula la diferencia entre dos fechas.
  ${D}Unidades disponibles:${NC}
      ${G}\"Y\"${NC}  → años completos entre las fechas
      ${G}\"M\"${NC}  → meses completos
      ${G}\"D\"${NC}  → dias exactos"

    titulo "EJEMPLO: Calcular edad de empleados"
    echo -e "
  ${D}     A          B            C${NC}
  ${D}  1  Empleado   Nacimiento   Edad${NC}
  ${D}  2  Juan       15/03/1990   =SI.FECHA(B2;HOY();\"Y\")  → 36${NC}
  ${D}  3  Maria      20/07/2000   =SI.FECHA(B3;HOY();\"Y\")  → 25${NC}"

    titulo "DIASEM — saber que dia de la semana es"
    echo -e "
  ${W}=DIASEM(fecha; tipo)${NC}
  Devuelve un numero que representa el dia de la semana.
  Con tipo=2: 1=Lunes, 2=Martes, ... 7=Domingo

  ${D}Ejemplo: si A1 tiene 03/05/2026 (domingo):${NC}
  ${G}=DIASEM(A1; 2)${NC}  →  devuelve ${G}7${NC}  (domingo = 7)"

    titulo "FIN.MES — el ultimo dia del mes"
    echo -e "
  ${W}=FIN.MES(fecha_inicio; meses)${NC}
  ${G}=FIN.MES(HOY(); 0)${NC}   → ultimo dia del mes ACTUAL
  ${G}=FIN.MES(HOY(); 1)${NC}   → ultimo dia del mes SIGUIENTE
  ${G}=FIN.MES(HOY(); -1)${NC}  → ultimo dia del mes ANTERIOR

  Muy util para calcular vencimientos y fechas de pago."
    pausar
}

# ─── A3. Estadística básica ───────────────────────────────────────────────────
aprender_estadistica_basica() {
    sep "A3 · SUMAR, PROMEDIAR Y ENCONTRAR EL MAYOR/MENOR"

    titulo "LAS FUNCIONES MAS USADAS"
    echo -e "
  ${W}=SUMA(rango)${NC}       Suma todos los numeros del rango
  ${W}=PROMEDIO(rango)${NC}   Promedio (suma dividida entre cantidad)
  ${W}=MAX(rango)${NC}        El numero mas grande
  ${W}=MIN(rango)${NC}        El numero mas pequeno
  ${W}=CONTAR(rango)${NC}     Cuantas celdas tienen numeros
  ${W}=CONTARA(rango)${NC}    Cuantas celdas NO estan vacias (texto o numero)"

    titulo "EJEMPLO CON TABLA DE NOTAS"
    echo -e "
  ${D}     A          B       C       D       E${NC}
  ${D}  1  Alumno     Nota1   Nota2   Nota3   Promedio${NC}
  ${D}  2  Ana Soto   16      14      18${NC}
  ${D}  3  Luis Rojas  8      12      10${NC}
  ${D}  4  Rosa Meza  18      19      17${NC}
  ${D}  5  Omar Cruz   5       9       7${NC}
  ${D}  6  TOTALES${NC}

  Calcular promedio de Ana (fila 2):
  ${G}=PROMEDIO(B2:D2)${NC}  →  ${G}16${NC}

  Calcular promedio general de Nota1 (columna B):
  ${G}=PROMEDIO(B2:B5)${NC}  →  ${G}11.75${NC}

  Nota mas alta de toda la clase:
  ${G}=MAX(B2:D5)${NC}  →  ${G}19${NC}

  Cuantos alumnos hay:
  ${G}=CONTARA(A2:A5)${NC}  →  ${G}4${NC}"

    titulo "FUNCIONES MATEMATICAS ADICIONALES"
    echo -e "
  ${W}=REDONDEAR(numero; decimales)${NC}
  ${G}=REDONDEAR(16.666; 2)${NC}  →  ${G}16.67${NC}
  ${G}=REDONDEAR(16.666; 0)${NC}  →  ${G}17${NC}

  ${W}=K.ESIMO.MAYOR(rango; k)${NC}   El k-esimo mayor
  ${G}=K.ESIMO.MAYOR(B2:B5; 1)${NC}  →  el 1er mayor: ${G}18${NC}
  ${G}=K.ESIMO.MAYOR(B2:B5; 2)${NC}  →  el 2do mayor: ${G}16${NC}

  ${W}=ENTERO(numero)${NC}   Elimina los decimales (trunca hacia abajo)
  ${G}=ENTERO(16.9)${NC}  →  ${G}16${NC}"
    pausar
}

# ─── A4. Estadística con criterio ─────────────────────────────────────────────
aprender_estadistica_criterio() {
    sep "A4 · CONTAR Y PROMEDIAR CON CONDICIONES"

    titulo "EL CONCEPTO"
    echo -e "
  A veces no quieres sumar TODO, sino solo los que cumplen
  una condicion. Ejemplo: solo los de Ventas, solo los mayores
  a 3000, solo los de Lima."

    titulo "CONTAR.SI — contar los que cumplen una condicion"
    echo -e "
  ${W}=CONTAR.SI(rango; criterio)${NC}
  ${D}El criterio puede ser: un texto, un numero, una comparacion${NC}

  ${D}     A          B       C       D${NC}
  ${D}  1  Empleado   Area    Sueldo${NC}
  ${D}  2  Juan       Ventas  2500${NC}
  ${D}  3  Maria      RRHH    3200${NC}
  ${D}  4  Carlos     Ventas  4100${NC}
  ${D}  5  Ana        Marketing 2800${NC}

  Cuantos son de Ventas:
  ${G}=CONTAR.SI(B2:B5; \"Ventas\")${NC}  →  ${G}2${NC}

  Cuantos ganan mas de 3000:
  ${G}=CONTAR.SI(C2:C5; \">3000\")${NC}  →  ${G}2${NC}"

    titulo "PROMEDIO.SI — promediar solo los que cumplen"
    echo -e "
  ${W}=PROMEDIO.SI(rango_criterio; criterio; rango_promedio)${NC}

  Promedio de sueldo solo de Ventas:
  ${G}=PROMEDIO.SI(B2:B5; \"Ventas\"; C2:C5)${NC}  →  ${G}3300${NC}
  (Juan 2500 + Carlos 4100) / 2 = 3300"

    titulo "CONTAR.SI.CONJUNTO — con multiples condiciones"
    echo -e "
  ${W}=CONTAR.SI.CONJUNTO(rango1; crit1; rango2; crit2)${NC}

  Cuantos son de Ventas Y ganan mas de 3000:
  ${G}=CONTAR.SI.CONJUNTO(B2:B5; \"Ventas\"; C2:C5; \">3000\")${NC}  →  ${G}1${NC}
  (solo Carlos cumple ambas condiciones)"

    titulo "MAX.SI.CONJUNTO y MIN.SI.CONJUNTO"
    echo -e "
  ${W}=MAX.SI.CONJUNTO(rango_max; rango_crit; criterio)${NC}

  El sueldo mas alto del area de Ventas:
  ${G}=MAX.SI.CONJUNTO(C2:C5; B2:B5; \"Ventas\")${NC}  →  ${G}4100${NC}

  Los comodines en criterios:
  ${G}\"*\"${NC}  cualquier texto   ${G}\"?\"${NC}  exactamente un caracter
  ${G}\"Ve*\"${NC} cualquier texto que empiece con Ve → Ventas, Ventura..."
    pausar
}

# ─── A5. Funciones lógicas ────────────────────────────────────────────────────
aprender_logicas() {
    sep "A5 · PONER RESULTADOS SEGUN UNA CONDICION (SI, Y, O)"

    titulo "LA FUNCION SI — la mas importante de Excel"
    echo -e "
  ${W}=SI(condicion; valor_si_verdadero; valor_si_falso)${NC}

  Preguntate: ¿que quiero mostrar? ¿cuando?

  ${D}Ejemplo: mostrar 'Aprobado' si la nota es >= 11${NC}
  ${G}=SI(B2>=11; \"Aprobado\"; \"Desaprobado\")${NC}

  ${D}     A          B         C${NC}
  ${D}  2  Ana Soto   16   →    Aprobado${NC}
  ${D}  3  Luis Rojas  8   →    Desaprobado${NC}

  El resultado tambien puede ser un calculo:
  ${G}=SI(C2>1000; C2*0.1; 0)${NC}
  Si el monto es mayor a 1000, aplica 10% de descuento, si no, 0."

    titulo "SI ANIDADA — mas de dos opciones"
    echo -e "
  Para 3 o mas resultados posibles, metes un SI dentro de otro:
  ${W}=SI(cond1; val1; SI(cond2; val2; val_final))${NC}

  ${D}Ejemplo: categoria por nota${NC}
  ${G}=SI(B2>=16; \"Excelente\"; SI(B2>=11; \"Aprobado\"; \"Desaprobado\"))${NC}

  Lee asi: Si nota>=16 → Excelente
           Si no, y nota>=11 → Aprobado
           Si no → Desaprobado

  ${D}     A          B    C${NC}
  ${D}  2  Ana Soto   18   Excelente${NC}
  ${D}  3  Luis Rojas  9   Desaprobado${NC}
  ${D}  4  Rosa Meza  13   Aprobado${NC}"

    titulo "Y — exigir que SE CUMPLAN TODAS las condiciones"
    echo -e "
  ${W}=SI(Y(cond1; cond2); verdadero; falso)${NC}

  Descuento del 15% solo si: es de Ventas Y gana mas de 3000
  ${G}=SI(Y(B2=\"Ventas\"; C2>3000); C2*0.15; 0)${NC}"

    titulo "O — basta con que SE CUMPLA UNA condicion"
    echo -e "
  ${W}=SI(O(cond1; cond2); verdadero; falso)${NC}

  Bonificacion si es de Ventas O de Marketing:
  ${G}=SI(O(B2=\"Ventas\"; B2=\"Marketing\"); \"Bono\"; \"-\")${NC}"
    pausar
}

# ─── A6. Funciones textuales ──────────────────────────────────────────────────
aprender_textuales() {
    sep "A6 · MANIPULAR TEXTO EN EXCEL"

    titulo "CAMBIAR MAYUSCULAS Y MINUSCULAS"
    echo -e "
  ${W}=MAYUSC(texto)${NC}     todo en MAYUSCULAS
  ${W}=MINUSC(texto)${NC}     todo en minusculas
  ${W}=NOMPROPIO(texto)${NC}  Primera Letra De Cada Palabra En Mayuscula

  ${D}Si A1 tiene 'juan garcia':${NC}
  ${G}=MAYUSC(A1)${NC}      →  JUAN GARCIA
  ${G}=NOMPROPIO(A1)${NC}   →  Juan Garcia"

    titulo "EXTRAER PARTES DEL TEXTO"
    echo -e "
  ${W}=IZQUIERDA(texto; n)${NC}  los primeros n caracteres
  ${W}=DERECHA(texto; n)${NC}    los ultimos n caracteres
  ${W}=EXTRAE(texto; inicio; n)${NC}  n caracteres desde la posicion inicio
  ${W}=LARGO(texto)${NC}          cuantos caracteres tiene

  ${D}Si A1 tiene 'E14023':${NC}
  ${G}=IZQUIERDA(A1; 1)${NC}    →  E
  ${G}=DERECHA(A1; 5)${NC}      →  14023
  ${G}=EXTRAE(A1; 2; 5)${NC}    →  14023
  ${G}=LARGO(A1)${NC}           →  6"

    titulo "BUSCAR Y REEMPLAZAR TEXTO"
    echo -e "
  ${W}=HALLAR(que_buscar; donde)${NC}  devuelve la POSICION del texto buscado
  ${D}Si A1 tiene 'juan@empresa.com':${NC}
  ${G}=HALLAR(\"@\"; A1)${NC}    →  5  (el @ esta en la posicion 5)

  ${W}=SUSTITUIR(texto; viejo; nuevo)${NC}  reemplaza texto
  ${G}=SUSTITUIR(A1; \"empresa\"; \"utp\")${NC}  →  juan@utp.com

  ${W}=IGUAL(t1; t2)${NC}  VERDADERO si son exactamente iguales
  ${G}=IGUAL(\"Excel\"; \"excel\")${NC}  →  FALSO  (distingue mayusculas)"

    titulo "UNIR TEXTO — el operador &"
    echo -e "
  ${W}=celda1&\"texto\"&celda2${NC}  une varias partes

  ${D}Si A2 tiene 'E14023' y queremos correo:${NC}
  ${G}=A2&\"@empresa.com\"${NC}   →  E14023@empresa.com

  ${D}Si B2 tiene 'Juan' y C2 tiene 'Garcia':${NC}
  ${G}=B2&\" \"&C2${NC}           →  Juan Garcia"
    pausar
}

# ─── A7. BUSCARV y BUSCARH ────────────────────────────────────────────────────
aprender_buscar() {
    sep "A7 · BUSCAR DATOS EN TABLAS (BUSCARV y BUSCARH)"

    titulo "BUSCARV — busca por COLUMNA (lo mas comun)"
    echo -e "
  Imagina un formulario donde ingresas un codigo y Excel
  rellena automaticamente todos los datos del empleado.

  ${W}=BUSCARV(valor_buscado; tabla; numero_columna; FALSO)${NC}

  ${D}TABLA DE EMPLEADOS (hoja 'Empleados', rango A1:E6):${NC}
  ${D}  Col:  A        B           C        D         E${NC}
  ${D}  Fil:  Codigo   Apellidos   Nombres  Area      Sueldo${NC}
  ${D}     2  E14001   Garcia      Juan     Ventas    2500${NC}
  ${D}     3  E14002   Lopez       Maria    RRHH      3200${NC}
  ${D}     4  E14003   Torres      Carlos   Sistemas  4100${NC}

  ${D}FORMULARIO DE BUSQUEDA:${NC}
  ${D}  A9: Ingresar codigo:   B9: E14002  ← el usuario escribe aqui${NC}
  ${D}  A10: Apellidos:        B10: ?${NC}
  ${D}  A11: Nombres:          B11: ?${NC}
  ${D}  A12: Area:             B12: ?${NC}

  Formula en B10 (busca apellidos = columna 2):
  ${G}=BUSCARV(\$B\$9; Empleados!\$A\$1:\$E\$6; 2; FALSO)${NC}
  Formula en B11 (nombres = columna 3):
  ${G}=BUSCARV(\$B\$9; Empleados!\$A\$1:\$E\$6; 3; FALSO)${NC}

  El ${W}\$${NC} en \$B\$9 fija la celda para copiar la formula abajo sin que cambie."

    titulo "BUSCARH — busca por FILA"
    echo -e "
  Cuando los encabezados estan en una FILA (no en columna).
  Tipico para tablas de zonas, meses, categorias.

  ${W}=BUSCARH(valor_buscado; tabla; numero_fila; FALSO)${NC}

  ${D}TABLA DE VENTAS (hoja 'Ventas', A1:E4):${NC}
  ${D}  Fil:  A          B       C     D        E${NC}
  ${D}     1  Zonas:     Norte   Sur   Central  Lima${NC}
  ${D}     2  Laptops    50      30    45        80${NC}
  ${D}     3  Celulares  120     90    110       200${NC}
  ${D}     4  Tablets    35      25    40         60${NC}

  ${D}  F8: selector de zona (ej: 'Norte')${NC}
  ${D}  A10: Laptops    B10: ?${NC}
  ${D}  A11: Celulares  B11: ?${NC}

  Formula en B10 (Laptops = fila 2 de la tabla):
  ${G}=BUSCARH(\$F\$8; Ventas!\$A\$1:\$E\$4; 2; FALSO)${NC}
  Formula en B11 (Celulares = fila 3):
  ${G}=BUSCARH(\$F\$8; Ventas!\$A\$1:\$E\$4; 3; FALSO)${NC}"

    titulo "MANEJAR ERROR #N/A con ESNOD"
    echo -e "
  Si el codigo no existe, BUSCARV muestra ${BR}#N/A${NC} (feo).
  Usa ESNOD para mostrar un mensaje amigable:

  ${G}=SI(ESNOD(BUSCARV(\$B\$9;Empleados!\$A:\$E;2;FALSO));\"No encontrado\";BUSCARV(\$B\$9;Empleados!\$A:\$E;2;FALSO))${NC}"
    pausar
}

# ─── A8. Validación de datos ──────────────────────────────────────────────────
aprender_validacion() {
    sep "A8 · IMPEDIR QUE ESCRIBAN DATOS INCORRECTOS (VALIDACION)"

    titulo "DONDE SE CONFIGURA"
    echo -e "
  En Excel: selecciona la celda → menu ${W}Datos${NC} → ${W}Validacion de datos${NC}"

    titulo "TIPOS DE VALIDACION"
    echo -e "
  ${W}Numero entero${NC}
  Solo permite numeros enteros. Puedes poner min y max.
  Ejemplo: notas entre 0 y 20.

  ${W}Lista desplegable${NC}  ← la mas usada
  Crea un menu con opciones fijas.
  En 'Origen' escribes: ${G}Norte,Sur,Central,Lima${NC}
  O puedes referenciar un rango: ${G}=\$H\$1:\$H\$4${NC}

  ${W}Fecha${NC}
  Solo acepta fechas validas.

  ${W}Longitud de texto${NC}
  Limita la cantidad de caracteres.
  Ejemplo: DNI debe tener exactamente 8 digitos.
  En tipo 'Personalizada' usas: ${G}=LARGO(A1)=8${NC}"

    titulo "MENSAJES PERSONALIZADOS"
    echo -e "
  ${BY}Mensaje de entrada:${NC} aparece cuando el usuario hace clic en la celda
  Ejemplo: 'Ingresa la zona: Norte, Sur, Central o Lima'

  ${BY}Mensaje de error:${NC} aparece si se escribe algo incorrecto
  3 estilos:
    ${W}Detener${NC}      → bloquea, no deja escribir otro valor
    ${W}Advertencia${NC}  → avisa pero deja continuar
    ${W}Informacion${NC}  → solo informa"

    titulo "EJEMPLO PRACTICO"
    echo -e "
  Quieres que la celda B2 solo acepte sueldos entre 1025 y 20000:
  1. Selecciona B2
  2. Datos → Validacion de datos
  3. Permitir: Numero entero
  4. Minimo: 1025   Maximo: 20000
  5. En 'Error': Detener | Mensaje: 'El sueldo debe estar entre 1025 y 20000'"
    pausar
}

# ═════════════════════════════════════════════════════════════════════════════
# G E N E R A R   F O R M U L A S
# ═════════════════════════════════════════════════════════════════════════════

generar_buscarv() {
    sep "GENERAR · BUSCARV en serie"
    hint "  Busca datos por codigo/ID en una tabla y rellena varias celdas."
    echo
    echo -en "  Celda donde el usuario escribe el codigo (ej: \$B\$9): "; read -r val
    echo -en "  Rango de la tabla (ej: Empleados!\$A\$1:\$E\$20): "; read -r matriz
    echo -en "  Cuantas columnas tiene la tabla: "; read -r nc
    [[ "$nc" =~ ^[0-9]+$ ]] && hint "  Indices disponibles: 1 a $nc (1=primera col, 2=segunda, etc.)"
    echo -en "  Rango destino (ej: B10:B14 o B10): "; read -r dest
    echo -en "  Numero de columna inicial: "; read -r ind
    [[ "$ind" =~ ^[0-9]+$ ]] || { err "Numero invalido."; return; }
    parse_rango "$dest"
    local -a p=(); local f i
    for (( f=INI_FIL, i=ind; f<=FIN_FIL; f++, i++ )); do
        p+=("${INI_COL}${f}" "=BUSCARV(${val};${matriz};${i};FALSO)")
    done
    mostrar_formulas "${p[@]}"
}

generar_buscarh() {
    sep "GENERAR · BUSCARH en serie"
    hint "  Busca datos por zona/categoria y rellena varias celdas."
    echo
    echo -en "  Celda selector de zona (ej: \$F\$8): "; read -r val
    echo -en "  Rango de la tabla (ej: Ventas!\$A\$1:\$E\$5): "; read -r matriz
    echo -en "  Cuantas filas tiene la tabla: "; read -r nf
    [[ "$nf" =~ ^[0-9]+$ ]] && hint "  Indices disponibles: 1 a $nf (1=encabezado, 2=primer dato, etc.)"
    echo -en "  Rango destino (ej: B10:B13 o B10): "; read -r dest
    echo -en "  Numero de fila inicial: "; read -r ind
    [[ "$ind" =~ ^[0-9]+$ ]] || { err "Numero invalido."; return; }
    parse_rango "$dest"
    local -a p=(); local f i
    for (( f=INI_FIL, i=ind; f<=FIN_FIL; f++, i++ )); do
        p+=("${INI_COL}${f}" "=BUSCARH(${val};${matriz};${i};FALSO)")
    done
    mostrar_formulas "${p[@]}"
}

generar_si() {
    sep "GENERAR · SI / SI anidada"
    echo -e "
  ${Y}1.${NC} SI simple           (2 resultados posibles)
  ${Y}2.${NC} SI + Y              (varias condiciones, todas deben cumplirse)
  ${Y}3.${NC} SI + O              (basta con que se cumpla una)
  ${Y}4.${NC} SI anidada 2 niveles (3 resultados)
  ${Y}5.${NC} SI anidada 3 niveles (4 resultados)
"
    echo -en "  ${C}Opcion: ${NC}"; read -r op
    local celda formula

    case "$op" in
    1)
        echo -en "  Condicion (ej: B2>=11): "; read -r c
        echo -en "  Valor si verdadero (ej: \"Aprobado\" o B2*0.1): "; read -r vt
        echo -en "  Valor si falso (ej: \"Desaprobado\" o 0): "; read -r vf
        echo -en "  Rango destino (ej: C2:C10): "; read -r dest
        formula="=SI(${c};${vt};${vf})"
        parse_rango "$dest"
        local -a p=(); local f
        for (( f=INI_FIL; f<=FIN_FIL; f++ )); do
            local cf="${c//2/$f}"; local vtf="${vt//2/$f}"; local vff="${vf//2/$f}"
            p+=("${INI_COL}${f}" "=SI(${cf};${vtf};${vff})")
        done
        mostrar_formulas "${p[@]}"
        ;;
    2)
        echo -en "  Condicion 1 (ej: B2>3000): "; read -r c1
        echo -en "  Condicion 2 (ej: D2=\"Ventas\"): "; read -r c2
        echo -en "  Valor si verdadero: "; read -r vt
        echo -en "  Valor si falso: "; read -r vf
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=SI(Y(${c1};${c2});${vt};${vf})"
        ;;
    3)
        echo -en "  Condicion 1 (ej: D2=\"Ventas\"): "; read -r c1
        echo -en "  Condicion 2 (ej: D2=\"Marketing\"): "; read -r c2
        echo -en "  Valor si verdadero: "; read -r vt
        echo -en "  Valor si falso: "; read -r vf
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=SI(O(${c1};${c2});${vt};${vf})"
        ;;
    4)
        echo -en "  Condicion 1 (ej: B2>=16): "; read -r c1
        echo -en "  Valor si c1 verdadera (ej: \"Excelente\"): "; read -r v1
        echo -en "  Condicion 2 (ej: B2>=11): "; read -r c2
        echo -en "  Valor si c2 verdadera (ej: \"Aprobado\"): "; read -r v2
        echo -en "  Valor si todo falso (ej: \"Desaprobado\"): "; read -r vf
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=SI(${c1};${v1};SI(${c2};${v2};${vf}))"
        ;;
    5)
        echo -en "  Condicion 1: "; read -r c1; echo -en "  Valor 1: "; read -r v1
        echo -en "  Condicion 2: "; read -r c2; echo -en "  Valor 2: "; read -r v2
        echo -en "  Condicion 3: "; read -r c3; echo -en "  Valor 3: "; read -r v3
        echo -en "  Valor si todo falso: "; read -r vf
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=SI(${c1};${v1};SI(${c2};${v2};SI(${c3};${v3};${vf})))"
        ;;
    *) err "Opcion invalida." ;;
    esac
}

generar_estadistica() {
    sep "GENERAR · Estadistica basica"
    echo -e "  ${Y}1.${NC}SUMA  ${Y}2.${NC}PROMEDIO  ${Y}3.${NC}MAX  ${Y}4.${NC}MIN  ${Y}5.${NC}CONTAR  ${Y}6.${NC}CONTARA  ${Y}7.${NC}REDONDEAR  ${Y}8.${NC}K.ESIMO\n"
    echo -en "  ${C}Opcion: ${NC}"; read -r op
    local fn
    case "$op" in
        1) fn="SUMA" ;; 2) fn="PROMEDIO" ;; 3) fn="MAX" ;;
        4) fn="MIN"  ;; 5) fn="CONTAR"   ;; 6) fn="CONTARA" ;;
        7)
            echo -en "  Celda o valor (ej: B2): "; read -r num
            echo -en "  Decimales (ej: 2): "; read -r dec
            echo -en "  Celda destino: "; read -r celda
            mostrar_formulas "$celda" "=REDONDEAR(${num};${dec})"; return ;;
        8)
            echo -en "  MAYOR o MENOR: "; read -r tipo; tipo="${tipo^^}"
            echo -en "  Rango (ej: B2:B20): "; read -r rango
            echo -en "  Posicion k (1=primero, 2=segundo): "; read -r k
            echo -en "  Celda destino: "; read -r celda
            mostrar_formulas "$celda" "=K.ESIMO.${tipo}(${rango};${k})"; return ;;
        *) err "Opcion invalida."; return ;;
    esac
    echo -en "  Rango de datos (ej: B2:B20): "; read -r rango
    echo -en "  Rango destino (ej: B21 o B21:E21): "; read -r dest
    parse_rango "$dest"
    if [[ "$INI_FIL" == "$FIN_FIL" && "$INI_COL" == "$FIN_COL" ]]; then
        mostrar_formulas "${INI_COL}${INI_FIL}" "=${fn}(${rango})"
    else
        echo -en "  ¿El rango de datos varia por fila? (s/n): "; read -r var
        local -a p=(); local f
        local bf; bf=$(echo "$rango" | grep -oP '[0-9]+' | head -1)
        for (( f=INI_FIL; f<=FIN_FIL; f++ )); do
            local ra="$rango"
            [[ "${var,,}" == "s" ]] && ra=$(echo "$rango" | sed -E "s/([0-9]+)/$(( bf + f - INI_FIL ))/g")
            p+=("${INI_COL}${f}" "=${fn}(${ra})")
        done
        mostrar_formulas "${p[@]}"
    fi
}

generar_estadistica_criterio() {
    sep "GENERAR · Estadistica con condicion"
    echo -e "
  ${Y}1.${NC} CONTAR.SI              ${Y}4.${NC} PROMEDIO.SI
  ${Y}2.${NC} CONTAR.SI.CONJUNTO     ${Y}5.${NC} PROMEDIO.SI.CONJUNTO
  ${Y}3.${NC} CONTAR.BLANCO          ${Y}6.${NC} MAX.SI.CONJUNTO / MIN.SI.CONJUNTO
"
    echo -en "  ${C}Opcion: ${NC}"; read -r op; local celda

    case "$op" in
    1)
        echo -en "  Rango a contar (ej: D2:D20): "; read -r rc
        echo -en "  Criterio (ej: \"Ventas\" o \">3000\"): "; read -r cr
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=CONTAR.SI(${rc};${cr})" ;;
    2)
        echo -en "  Nro de condiciones: "; read -r n; local args=""
        for (( i=1; i<=n; i++ )); do
            echo -en "  Rango condicion $i: "; read -r rc
            echo -en "  Criterio $i: "; read -r cr
            args+="${rc};${cr}"; (( i < n )) && args+=";"
        done
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=CONTAR.SI.CONJUNTO(${args})" ;;
    3)
        echo -en "  Rango (ej: B2:B20): "; read -r r
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=CONTAR.BLANCO(${r})" ;;
    4)
        echo -en "  Rango criterio (ej: D2:D20): "; read -r rc
        echo -en "  Criterio (ej: \"Ventas\"): "; read -r cr
        echo -en "  Rango a promediar (Enter=mismo): "; read -r rp
        [[ -z "$rp" ]] && rp="$rc"
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=PROMEDIO.SI(${rc};${cr};${rp})" ;;
    5)
        echo -en "  Rango a promediar: "; read -r rp
        echo -en "  Nro de condiciones: "; read -r n; local args="$rp"
        for (( i=1; i<=n; i++ )); do
            echo -en "  Rango condicion $i: "; read -r rc
            echo -en "  Criterio $i: "; read -r cr; args+=";${rc};${cr}"
        done
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=PROMEDIO.SI.CONJUNTO(${args})" ;;
    6)
        echo -en "  MAX o MIN: "; read -r fn; fn="${fn^^}"
        echo -en "  Rango de valores (ej: C2:C20): "; read -r rv
        echo -en "  Rango criterio (ej: D2:D20): "; read -r rc
        echo -en "  Criterio (ej: \"Ventas\"): "; read -r cr
        echo -en "  Celda destino: "; read -r celda
        mostrar_formulas "$celda" "=${fn}.SI.CONJUNTO(${rv};${rc};${cr})" ;;
    *) err "Opcion invalida." ;;
    esac
}

generar_textuales() {
    sep "GENERAR · Funciones de texto"
    echo -e "
  ${Y}1.${NC}LARGO       ${Y}5.${NC}EXTRAE       ${Y}9.${NC}SUSTITUIR
  ${Y}2.${NC}MAYUSC      ${Y}6.${NC}HALLAR       ${Y}10.${NC}IGUAL
  ${Y}3.${NC}MINUSC      ${Y}7.${NC}NOMPROPIO    ${Y}11.${NC}Correos (codigo@empresa.com)
  ${Y}4.${NC}IZQUIERDA   ${Y}8.${NC}DERECHA
"
    echo -en "  ${C}Opcion: ${NC}"; read -r op; local celda

    case "$op" in
    1)  echo -en "  Celda de texto: "; read -r c; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=LARGO(${c})" ;;
    2)  echo -en "  Celda: "; read -r c; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=MAYUSC(${c})" ;;
    3)  echo -en "  Celda: "; read -r c; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=MINUSC(${c})" ;;
    4)  echo -en "  Celda: "; read -r c; echo -en "  N chars: "; read -r n; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=IZQUIERDA(${c};${n})" ;;
    5)  echo -en "  Celda: "; read -r c; echo -en "  Pos inicio: "; read -r p; echo -en "  N chars: "; read -r n; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=EXTRAE(${c};${p};${n})" ;;
    6)  echo -en "  Texto a buscar (ej: \"@\"): "; read -r b; echo -en "  Celda donde buscar: "; read -r c; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=HALLAR(${b};${c})" ;;
    7)  echo -en "  Celda: "; read -r c; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=NOMPROPIO(${c})" ;;
    8)  echo -en "  Celda: "; read -r c; echo -en "  N chars: "; read -r n; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=DERECHA(${c};${n})" ;;
    9)  echo -en "  Celda: "; read -r c; echo -en "  Texto viejo: "; read -r v; echo -en "  Texto nuevo: "; read -r nu; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=SUSTITUIR(${c};\"${v}\";\"${nu}\")" ;;
    10) echo -en "  Celda 1: "; read -r c1; echo -en "  Celda 2: "; read -r c2; echo -en "  Destino: "; read -r celda
        mostrar_formulas "$celda" "=IGUAL(${c1};${c2})" ;;
    11) echo -en "  Celda del codigo (ej: A2): "; read -r cb
        echo -en "  Sufijo (ej: @empresa.com): "; read -r suf
        echo -en "  Rango destino (ej: F2:F20): "; read -r dest
        parse_rango "$dest"; parse_celda "$cb"
        local -a p=(); local f
        for (( f=INI_FIL; f<=FIN_FIL; f++ )); do
            p+=("${INI_COL}${f}" "=${COL_P}$(( FIL_P + f - INI_FIL ))&\"${suf}\"")
        done
        mostrar_formulas "${p[@]}" ;;
    *) err "Opcion invalida." ;;
    esac
}

generar_patron() {
    sep "GENERAR · Patron {IND} — formula con numero variable"
    hint "  Escribe la formula con {IND} donde va el numero que cambia."
    hint "  Ej: =BUSCARV(\$B\$9;Tabla!\$A:\$E;{IND};FALSO)"
    echo
    echo -en "  Rango destino (ej: B10:B15): "; read -r dest
    echo -en "  Formula base (usa {IND}): "; read -r fb
    echo -en "  Valor inicial de {IND}: "; read -r ini
    [[ "$ini" =~ ^[0-9]+$ ]] || { err "Numero invalido."; return; }
    parse_rango "$dest"
    local -a p=(); local f i
    for (( f=INI_FIL, i=ini; f<=FIN_FIL; f++, i++ )); do
        p+=("${INI_COL}${f}" "${fb/\{IND\}/$i}")
    done
    mostrar_formulas "${p[@]}"
}

# ═════════════════════════════════════════════════════════════════════════════
# P R A C T I C A R
# ═════════════════════════════════════════════════════════════════════════════

practica_referencias() {
    sep "PRACTICA · Referencias y formulas basicas"
    PUNTAJE=0; TOTAL=0
    echo -e "
  ${D}Contexto: tienes esta tabla de precios${NC}
  ${D}  A1: IGV = 1.18   (tasa fija)${NC}
  ${D}  B2: 2000  (precio laptop)${NC}
  ${D}  B3: 800   (precio celular)${NC}
  ${D}  B4: 500   (precio tablet)${NC}
"
    echo -e "  ${BY}Ejercicio 1${NC}"
    echo -e "  Escribe la formula para C2 que multiplique B2 por el IGV de A1,"
    echo -e "  dejando A1 ${W}fija${NC} para poder copiar la formula hacia abajo."
    validar "=B2*\$A\$1" "Usa \$A\$1 para fijar la celda del IGV"

    echo -e "\n  ${BY}Ejercicio 2${NC}"
    echo -e "  La formula =B2*\$A\$1 esta en C2. Si la copias a C3, ¿que formula queda?"
    validar "=B3*\$A\$1" "B2 es relativa → cambia a B3. \$A\$1 es absoluta → no cambia."

    echo -e "\n  ${BY}Ejercicio 3${NC}"
    echo -e "  En D2 quieres mostrar el precio de la hoja 'Precios', celda B5."
    hint "  Usa el formato: NombreHoja!Celda"
    validar "=Precios!B5" "Escribe el nombre de la hoja, luego !, luego la celda"

    echo -e "\n  ${BY}Ejercicio 4${NC}"
    echo -e "  ¿Como se escribe A1 con la COLUMNA fija y la FILA libre?"
    validar "=\$A1" "Pon \$ solo antes de la letra de columna: \$A1"

    mostrar_puntaje
}

practica_estadistica() {
    sep "PRACTICA · Estadistica basica y con condicion"
    PUNTAJE=0; TOTAL=0
    echo -e "
  ${D}Contexto: tabla de empleados${NC}
  ${D}  A: Codigo   B: Area      C: Sueldo   D: Ciudad${NC}
  ${D}  Filas 2 a 11 (10 empleados)${NC}
  ${D}  Algunos de Ventas, algunos de RRHH, algunos de Lima, otros de Arequipa${NC}
"
    echo -e "  ${BY}Ejercicio 1${NC} — suma todos los sueldos (C2 a C11)"
    validar "=SUMA(C2:C11)" "Usa SUMA con el rango de sueldos"

    echo -e "\n  ${BY}Ejercicio 2${NC} — promedio de sueldos"
    validar "=PROMEDIO(C2:C11)" "Igual que SUMA pero con PROMEDIO"

    echo -e "\n  ${BY}Ejercicio 3${NC} — el sueldo mas alto"
    validar "=MAX(C2:C11)" "Usa MAX"

    echo -e "\n  ${BY}Ejercicio 4${NC} — cuantos empleados son de Ventas (col B)"
    validar "=CONTAR.SI(B2:B11;\"Ventas\")" "CONTAR.SI(rango; criterio_entre_comillas)"

    echo -e "\n  ${BY}Ejercicio 5${NC} — promedio de sueldo solo de los de Lima (col D)"
    hint "  Rango criterio: D2:D11  |  criterio: \"Lima\"  |  rango promedio: C2:C11"
    validar "=PROMEDIO.SI(D2:D11;\"Lima\";C2:C11)" "PROMEDIO.SI(rango_criterio; criterio; rango_a_promediar)"

    echo -e "\n  ${BY}Ejercicio 6${NC} — cuantos son de Ventas Y de Lima a la vez"
    validar "=CONTAR.SI.CONJUNTO(B2:B11;\"Ventas\";D2:D11;\"Lima\")" "CONTAR.SI.CONJUNTO: pares de rango-criterio"

    mostrar_puntaje
}

practica_logicas() {
    sep "PRACTICA · Funciones logicas (SI, Y, O)"
    PUNTAJE=0; TOTAL=0
    echo -e "
  ${D}Contexto: tabla de notas${NC}
  ${D}  A: Codigo   B: Nombre   C: Nota (0-20)   D: Observacion${NC}
  ${D}  Las formulas van en la columna D${NC}
"
    echo -e "  ${BY}Ejercicio 1${NC}"
    echo -e "  Escribe la formula para D2 que muestre 'Aprobado' si C2>=11,"
    echo -e "  o 'Desaprobado' si no."
    validar "=SI(C2>=11;\"Aprobado\";\"Desaprobado\")" "=SI(condicion; si_verdad; si_falso)"

    echo -e "\n  ${BY}Ejercicio 2${NC}"
    echo -e "  Formula para D2 que muestre:"
    echo -e "  'Excelente' si C2>=18, 'Aprobado' si C2>=11, 'Desaprobado' si no."
    validar "=SI(C2>=18;\"Excelente\";SI(C2>=11;\"Aprobado\";\"Desaprobado\"))" "SI anidada: SI(c1;v1;SI(c2;v2;vfinal))"

    echo -e "\n  ${BY}Ejercicio 3${NC}"
    echo -e "  Ahora hay una columna E con Asistencia (%)."
    echo -e "  Formula para D2: 'Aprobado' si C2>=11 Y E2>=70, 'Desaprobado' si no."
    validar "=SI(Y(C2>=11;E2>=70);\"Aprobado\";\"Desaprobado\")" "Usa SI(Y(cond1;cond2);...)"

    echo -e "\n  ${BY}Ejercicio 4${NC}"
    echo -e "  Formula para D2: 'Revision' si C2<7 O E2<50, 'Normal' si no."
    validar "=SI(O(C2<7;E2<50);\"Revision\";\"Normal\")" "Usa SI(O(cond1;cond2);...)"

    mostrar_puntaje
}

practica_textuales() {
    sep "PRACTICA · Funciones de texto"
    PUNTAJE=0; TOTAL=0
    echo -e "
  ${D}Contexto: tabla de empleados${NC}
  ${D}  A2: E14023  (codigo)${NC}
  ${D}  B2: garcia  (apellido en minusculas)${NC}
  ${D}  C2: juan    (nombre en minusculas)${NC}
"
    echo -e "  ${BY}Ejercicio 1${NC} — cuantos caracteres tiene el codigo en A2"
    validar "=LARGO(A2)" "LARGO(celda) cuenta caracteres"

    echo -e "\n  ${BY}Ejercicio 2${NC} — mostrar el apellido en MAYUSCULAS"
    validar "=MAYUSC(B2)" "MAYUSC(celda)"

    echo -e "\n  ${BY}Ejercicio 3${NC} — mostrar el nombre con Primera Letra Mayuscula"
    validar "=NOMPROPIO(C2)" "NOMPROPIO(celda)"

    echo -e "\n  ${BY}Ejercicio 4${NC} — extraer solo los 5 numeros del codigo (E14023 → 14023)"
    hint "  El codigo tiene 6 caracteres, los numeros estan del 2 al 6"
    validar "=DERECHA(A2;5)" "DERECHA(celda; cantidad) toma desde la derecha"

    echo -e "\n  ${BY}Ejercicio 5${NC} — generar el correo del empleado"
    echo -e "  El correo es: codigo + \"@empresa.com\"  (ej: E14023@empresa.com)"
    validar "=A2&\"@empresa.com\"" "Usa el operador & para unir texto"

    echo -e "\n  ${BY}Ejercicio 6${NC}"
    echo -e "  Mostrar nombre completo como 'GARCIA, Juan' (apellido en MAY, coma, nombre NOMPROPIO)"
    validar "=MAYUSC(B2)&\", \"&NOMPROPIO(C2)" "Combina MAYUSC, &, NOMPROPIO"

    mostrar_puntaje
}

practica_buscar() {
    sep "PRACTICA · BUSCARV y BUSCARH"
    PUNTAJE=0; TOTAL=0
    echo -e "
  ${D}TABLA DE EMPLEADOS (hoja 'Empleados', rango \$A\$1:\$E\$11):${NC}
  ${D}  Col 1=Codigo  2=Apellidos  3=Nombres  4=Area  5=Sueldo${NC}

  ${D}FORMULARIO: el codigo a buscar esta en \$B\$9${NC}

  ${D}TABLA DE VENTAS (hoja 'Ventas', rango \$A\$1:\$E\$5):${NC}
  ${D}  Fila 1=encabezado zonas  2=Laptops  3=Celulares  4=Tablets  5=Accesorios${NC}
  ${D}  La zona seleccionada esta en \$F\$8${NC}
"
    echo -e "  ${BY}Ejercicio 1${NC}"
    echo -e "  Formula en B10 para mostrar los APELLIDOS del empleado buscado."
    validar "=BUSCARV(\$B\$9;Empleados!\$A\$1:\$E\$11;2;FALSO)" "BUSCARV(valor;\$tabla;col;FALSO) — apellidos=col 2"

    echo -e "\n  ${BY}Ejercicio 2${NC}"
    echo -e "  Formula en B12 para mostrar el AREA (columna 4)."
    validar "=BUSCARV(\$B\$9;Empleados!\$A\$1:\$E\$11;4;FALSO)" "Misma formula, solo cambia el numero de columna"

    echo -e "\n  ${BY}Ejercicio 3${NC}"
    echo -e "  Formula en B13 para mostrar el SUELDO (columna 5)."
    validar "=BUSCARV(\$B\$9;Empleados!\$A\$1:\$E\$11;5;FALSO)" "Columna 5 = Sueldo"

    echo -e "\n  ${BY}Ejercicio 4${NC}"
    echo -e "  Formula en B10 para mostrar ventas de LAPTOPS segun la zona en \$F\$8."
    hint "  Laptops = fila 2 de la tabla de ventas"
    validar "=BUSCARH(\$F\$8;Ventas!\$A\$1:\$E\$5;2;FALSO)" "BUSCARH(zona;\$tabla;fila;FALSO) — Laptops=fila 2"

    echo -e "\n  ${BY}Ejercicio 5${NC}"
    echo -e "  Formula en B12 para mostrar ventas de TABLETS (fila 4) segun la zona."
    validar "=BUSCARH(\$F\$8;Ventas!\$A\$1:\$E\$5;4;FALSO)" "Tablets = fila 4"

    echo -e "\n  ${BY}Ejercicio 6${NC} — manejo de error"
    echo -e "  Formula en B10 que muestre 'No existe' si el codigo no se encuentra,"
    echo -e "  y los apellidos si si existe. (ESNOD + BUSCARV)"
    hint "  =SI(ESNOD(BUSCARV(...)); \"No existe\"; BUSCARV(...))"
    validar "=SI(ESNOD(BUSCARV(\$B\$9;Empleados!\$A\$1:\$E\$11;2;FALSO));\"No existe\";BUSCARV(\$B\$9;Empleados!\$A\$1:\$E\$11;2;FALSO))" "Envuelve el BUSCARV en ESNOD dentro de SI"

    mostrar_puntaje
}

# ═════════════════════════════════════════════════════════════════════════════
# M E N U S
# ═════════════════════════════════════════════════════════════════════════════

menu_aprender() {
    while true; do
        sep "APRENDER — elige un tema"
        echo -e "
  ${BY}1.${NC} ¿Que hace el signo \$ en una formula?  ${D}(referencias)${NC}
  ${BY}2.${NC} Calcular con fechas: edad, dias, fin de mes
  ${BY}3.${NC} Sumar, promediar, encontrar el mayor y el menor
  ${BY}4.${NC} Contar y promediar solo los que cumplen una condicion
  ${BY}5.${NC} Mostrar 'Aprobado/Desaprobado' segun un valor  ${D}(SI, Y, O)${NC}
  ${BY}6.${NC} Trabajar con texto: mayusculas, extraer letras, unir palabras
  ${BY}7.${NC} Buscar datos en tablas  ${D}(BUSCARV y BUSCARH)${NC}
  ${BY}8.${NC} Impedir que escriban datos incorrectos  ${D}(Validacion)${NC}
  ${BY}0.${NC} Volver al menu principal
"
        echo -en "  ${C}Que quieres aprender? ${NC}"; read -r op
        case "$op" in
            1) aprender_referencias ;;  2) aprender_fecha ;;
            3) aprender_estadistica_basica ;; 4) aprender_estadistica_criterio ;;
            5) aprender_logicas ;;      6) aprender_textuales ;;
            7) aprender_buscar ;;       8) aprender_validacion ;;
            0) return ;;
            *) err "Opcion invalida." ;;
        esac
    done
}

menu_generar() {
    while true; do
        sep "GENERAR FORMULAS — elige que necesitas"
        echo -e "
  ${BY}1.${NC} Buscar empleado/producto por codigo  ${D}→ BUSCARV${NC}
  ${BY}2.${NC} Buscar ventas por zona/categoria     ${D}→ BUSCARH${NC}
  ${BY}3.${NC} Poner resultado segun condicion      ${D}→ SI / SI anidada${NC}
  ${BY}4.${NC} Calcular totales y estadisticas      ${D}→ SUMA, PROMEDIO, MAX, MIN...${NC}
  ${BY}5.${NC} Calcular solo los que cumplen algo   ${D}→ CONTAR.SI, PROMEDIO.SI...${NC}
  ${BY}6.${NC} Trabajar con texto y correos         ${D}→ LARGO, IZQUIERDA, &...${NC}
  ${BY}7.${NC} Repetir formula con numero variable  ${D}→ patron {IND}${NC}
  ${BY}0.${NC} Volver al menu principal
"
        echo -en "  ${C}Que formulas necesitas generar? ${NC}"; read -r op
        case "$op" in
            1) generar_buscarv ;;   2) generar_buscarh ;;
            3) generar_si ;;        4) generar_estadistica ;;
            5) generar_estadistica_criterio ;; 6) generar_textuales ;;
            7) generar_patron ;;    0) return ;;
            *) err "Opcion invalida." ;;
        esac
    done
}

menu_practicar() {
    while true; do
        sep "PRACTICAR — ejercicios con correccion automatica"
        echo -e "
  ${D}Escribe la formula exacta. El validador acepta ; y , indistintamente.${NC}

  ${BY}1.${NC} El signo \$ y las referencias  ${D}(S3)${NC}
  ${BY}2.${NC} Estadisticas: SUMA, PROMEDIO, CONTAR.SI...  ${D}(S4)${NC}
  ${BY}3.${NC} Decisiones: SI, Y, O, SI anidada  ${D}(S5)${NC}
  ${BY}4.${NC} Texto: LARGO, MAYUSC, EXTRAE, &...  ${D}(S5)${NC}
  ${BY}5.${NC} BUSCARV y BUSCARH  ${D}(S6)${NC}
  ${BY}0.${NC} Volver al menu principal
"
        echo -en "  ${C}Que quieres practicar? ${NC}"; read -r op
        case "$op" in
            1) practica_referencias ;;   2) practica_estadistica ;;
            3) practica_logicas ;;       4) practica_textuales ;;
            5) practica_buscar ;;        0) return ;;
            *) err "Opcion invalida." ;;
        esac
    done
}

# ═════════════════════════════════════════════════════════════════════════════
# A N A L I Z A R   A R C H I V O   E X C E L
# ═════════════════════════════════════════════════════════════════════════════

analizar_excel() {
    sep "ANALIZAR ARCHIVO EXCEL"

    # Directorio base: carpeta del script
    local BASE
    BASE="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

    # Buscar todos los .xlsx bajo SEMANA_*
    mapfile -t archivos < <(find "$BASE" -maxdepth 3 -iname "*.xlsx" | sort)

    if [[ ${#archivos[@]} -eq 0 ]]; then
        err "No se encontraron archivos .xlsx en ${BASE}"
        pausar; return
    fi

    # Listar archivos
    sep "Archivos encontrados" "-"
    local i=1
    for f in "${archivos[@]}"; do
        local rel="${f#$BASE/}"
        echo -e "  ${BY}${i}.${NC} ${W}${rel}${NC}"
        (( i++ ))
    done

    echo -en "\n  ${C}Selecciona un archivo (numero): ${NC}"; read -r sel
    if ! [[ "$sel" =~ ^[0-9]+$ ]] || (( sel < 1 || sel > ${#archivos[@]} )); then
        err "Numero invalido."; pausar; return
    fi

    local archivo="${archivos[$((sel-1))]}"
    local rel_archivo="${archivo#$BASE/}"
    info "Archivo: ${W}${rel_archivo}${NC}"

    # Python: obtener hojas
    local hojas_raw
    hojas_raw=$(python3 - "$archivo" <<'PYEOF'
import sys, openpyxl
wb = openpyxl.load_workbook(sys.argv[1], read_only=True, data_only=True)
print('\n'.join(wb.sheetnames))
wb.close()
PYEOF
)

    if [[ -z "$hojas_raw" ]]; then
        err "No se pudieron leer las hojas del archivo."; pausar; return
    fi

    mapfile -t hojas <<< "$hojas_raw"

    sep "Hojas del archivo" "-"
    echo -e "  ${D}Total: ${#hojas[@]} hoja(s)${NC}\n"
    local j=1
    for h in "${hojas[@]}"; do
        echo -e "  ${BY}${j}.${NC} ${W}${h}${NC}"
        (( j++ ))
    done

    echo -en "\n  ${C}Selecciona una hoja (numero): ${NC}"; read -r sel_h
    if ! [[ "$sel_h" =~ ^[0-9]+$ ]] || (( sel_h < 1 || sel_h > ${#hojas[@]} )); then
        err "Numero invalido."; pausar; return
    fi

    local hoja="${hojas[$((sel_h-1))]}"
    echo -e "\n  ${D}Usa ← → para scroll horizontal, Q para salir.${NC}\n"

    # Python: renderizar la hoja como tabla con columnas truncadas + pipe a less -SR
    python3 - "$archivo" "$hoja" <<'PYEOF' | less -SR
import sys, openpyxl, re

CYAN   = '\033[0;36m'
YELLOW = '\033[1;33m'
BWHITE = '\033[1;37m'
WHITE  = '\033[0;37m'
DIM    = '\033[2m'
BOLD   = '\033[1m'
NC     = '\033[0m'

MAX_W = 18   # ancho máximo por celda antes de truncar

def clean(v):
    if v is None: return ''
    s = str(v)
    s = re.sub(r'\s00:00:00$', '', s)          # quitar timestamp de fechas
    try:
        f = float(s)
        s = str(int(round(f))) if abs(f - round(f)) < 1e-6 else f'{f:.2f}'
    except (ValueError, OverflowError):
        pass
    return s

def trunc(s, w):
    return (s[:w-1] + '▸') if len(s) > w else s  # ▸ si se trunca

def is_num(s):
    try: float(s); return s != ''
    except: return False

def col_letter(n):
    s = ''
    while True:
        s = chr(ord('A') + n % 26) + s
        n = n // 26 - 1
        if n < 0: break
    return s

wb = openpyxl.load_workbook(sys.argv[1], read_only=True, data_only=True)
ws = wb[sys.argv[2]]

rows = [[clean(v) for v in row] for row in ws.iter_rows(values_only=True)]

# Quitar filas vacías del final
while rows and all(c == '' for c in rows[-1]):
    rows.pop()

if not rows:
    print(f'\n  {DIM}(hoja vacía){NC}\n')
    wb.close(); sys.exit(0)

num_cols = max(len(r) for r in rows)
rows = [r + [''] * (num_cols - len(r)) for r in rows]

# Ancho por columna: máximo del contenido, tope en MAX_W
col_w = [min(MAX_W, max(4, max(len(r[ci]) for r in rows))) for ci in range(num_cols)]

# Fila de cabecera (primera con >= 3 celdas no vacías → encabezados de la tabla)
hdr_idx = next((i for i, r in enumerate(rows) if sum(1 for c in r if c.strip()) >= 3), 0)

# ── Construir líneas ──────────────────────────────────────────────────────────
SEP   = f' {DIM}│{NC} '
HSEP  = f'{DIM}─┼─{NC}'
THICK = f'{CYAN}═╪═{NC}'

def make_sep(chars=('─','┼','─'), color=DIM):
    return color + chars[0] + chars[1].join(chars[0]*w for w in col_w) + chars[0] + NC

# Letras de columna
letters = [DIM + col_letter(i).center(col_w[i]) + NC for i in range(num_cols)]
print('\n      ' + '   '.join(letters))
print('      ' + DIM + '─' * (sum(col_w) + 3*(num_cols-1)) + NC)

for ri, row in enumerate(rows):
    cells = []
    for ci, val in enumerate(row):
        t = trunc(val, col_w[ci])
        w = col_w[ci]
        if ri == hdr_idx:
            cells.append(YELLOW + BOLD + t.ljust(w) + NC)
        elif is_num(val):
            cells.append(BWHITE + t.rjust(w) + NC)
        else:
            cells.append(WHITE + t.ljust(w) + NC)

    num_lbl = DIM + f'{ri+1:>4}' + NC
    print(f' {num_lbl} │ ' + SEP.join(cells) + f' {DIM}│{NC}')

    if ri == hdr_idx:
        print(' ' * 7 + CYAN + '╪═'.join('═'*w for w in col_w) + '╡' + NC)

print()
wb.close()
PYEOF

}

# ═════════════════════════════════════════════════════════════════════════════
# M E N U   P R I N C I P A L
# ═════════════════════════════════════════════════════════════════════════════

main() {
    clear
    sep "HERRAMIENTA EXCEL  v4.0  —  UTP  Herramientas Informaticas" "="
    echo -e "  ${D}Aprende, genera formulas, practica y analiza tus archivos Excel${NC}"

    while true; do
        sep "QUE QUIERES HACER?"
        echo -e "
  ${BC}[A]${NC} ${BY}APRENDER${NC}
      Explicaciones con ejemplos visuales de cada tema.
      Ideal si no sabes como funciona una formula.
  ${D}    Temas: referencias, fechas, estadisticas, SI/Y/O,${NC}
  ${D}    texto, BUSCARV, BUSCARH, validacion.${NC}

  ${BC}[G]${NC} ${BY}GENERAR FORMULAS${NC}
      El asistente te hace preguntas y genera la formula lista
      para que la copies y pegues en tu hoja de Excel.

  ${BC}[P]${NC} ${BY}PRACTICAR${NC}
      Ejercicios con escenarios reales. Escribes la formula
      y el programa te dice si esta bien o mal.
      Al final te da tu puntaje.

  ${BC}[V]${NC} ${BY}VER / ANALIZAR UN ARCHIVO EXCEL${NC}
      Lista los .xlsx de las semanas, elige uno,
      elige una hoja y la ve completa en pantalla.

  ${BY}1.${NC} Aprender
  ${BY}2.${NC} Generar formulas
  ${BY}3.${NC} Practicar
  ${BY}4.${NC} Ver formulas de las 6 semanas de un vistazo
  ${BY}5.${NC} Ver / Analizar un archivo Excel
  ${BY}0.${NC} Salir
"
        echo -en "  ${C}Escribe una opcion (1/2/3/4/5/0): ${NC}"; read -r op

        case "$op" in
            1|a|A) menu_aprender ;;
            2|g|G) menu_generar ;;
            3|p|P) menu_practicar ;;
            4)
                sep "RESUMEN DE LAS 6 SEMANAS"
                echo -e "
  ${BC}S1-S2${NC}  Teoria: CRM, ERP, BPM, BI, MS Excel como herramienta
  ${BC}S3${NC}    Referencias: A1  \$A\$1  \$A1  A\$1  |  F4 para alternar
  ${BC}S4${NC}    Fecha: HOY() AHORA() SI.FECHA() DIASEM() FIN.MES()
         Matematica: SUMA PROMEDIO MAX MIN REDONDEAR ENTERO POTENCIA K.ESIMO
         Estadistica: CONTAR CONTARA CONTAR.BLANCO CONTAR.SI CONTAR.SI.CONJUNTO
                      PROMEDIO.SI PROMEDIO.SI.CONJUNTO MAX.SI.CONJUNTO MIN.SI.CONJUNTO
  ${BC}S5${NC}    Logicas: SI  Y  O  SI anidada
         Textuales: LARGO MAYUSC MINUSC NOMPROPIO IZQUIERDA DERECHA EXTRAE
                    HALLAR IGUAL SUSTITUIR &
         Fecha extra: DIASEM() FIN.MES()
         Validacion de datos: Lista, Entero, Fecha, Personalizada
  ${BC}S6${NC}    Busqueda: BUSCARV BUSCARH COINCIDIR ESNOD
         =BUSCARV(val; \$tabla; num_col; FALSO)
         =BUSCARH(val; \$tabla; num_fila; FALSO)
         =SI(ESNOD(BUSCARV(...)); \"No existe\"; BUSCARV(...))
"
                pausar ;;
            5|v|V) analizar_excel ;;
            0) echo -e "\n  ${BG}Hasta luego!${NC}\n"; exit 0 ;;
            *) err "Escribe 1, 2, 3, 4, 5 o 0." ;;
        esac
    done
}

main
