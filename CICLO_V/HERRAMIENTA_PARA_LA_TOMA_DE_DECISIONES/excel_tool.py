#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
excel_tool.py  v2.0
Herramienta inteligente para analizar y resolver ejercicios de Excel.
Curso: Herramientas Informaticas para la Toma de Decisiones - UTP

Flujo:
  1. Carga el archivo  →  analiza TODAS las hojas automaticamente
  2. Construye contexto (columnas, celdas vacias, formulas existentes, relaciones)
  3. Para la hoja a trabajar sugiere formulas basadas en el contexto completo
  4. Escribe las formulas y muestra resultados
"""

import sys, os, re
sys.stdout.reconfigure(encoding='utf-8')
sys.stdin.reconfigure(encoding='utf-8')

import xlwings as xw
import pandas as pd
from collections import defaultdict

ANCHO = 70

# ──────────────────────────────────────────────────────────────────────────────
# UTILIDADES
# ──────────────────────────────────────────────────────────────────────────────

def sep(titulo='', char='='):
    if titulo:
        lado = max(2, (ANCHO - len(titulo) - 2) // 2)
        print(f'\n{char * lado} {titulo} {char * lado}')
    else:
        print(f'\n{char * ANCHO}')

def col_letra(idx):
    """Indice 0-based → letra de columna Excel (0→A, 26→AA)."""
    result, idx = '', idx + 1
    while idx > 0:
        idx, rem = divmod(idx - 1, 26)
        result = chr(65 + rem) + result
    return result

def letra_idx(letra):
    """Letra de columna Excel → indice 0-based."""
    result = 0
    for c in letra.upper():
        result = result * 26 + (ord(c) - 64)
    return result - 1

# ──────────────────────────────────────────────────────────────────────────────
# ESTADO GLOBAL
# ──────────────────────────────────────────────────────────────────────────────

ctx = {
    'app'    : None,
    'wb'     : None,
    'archivo': '',
    'hojas'  : {},        # nombre → dict con analisis completo
    'activa' : None,      # xlwings sheet activa
    'bd'     : {},        # nombre → dict con rango BD definido por el usuario
}

# Keywords → tipo de formula sugerida
HINTS = {
    'total'      : 'SUMA',
    'suma'       : 'SUMA',
    'subtotal'   : 'SUMA',
    'promedio'   : 'PROMEDIO',
    'media'      : 'PROMEDIO',
    'maximo'     : 'MAX',
    'minimo'     : 'MIN',
    'conteo'     : 'CONTAR',
    'cantidad'   : 'CONTAR',
    'contarsi'   : 'CONTARSI',
    'correo'     : 'CONCATENAR',
    'email'      : 'CONCATENAR',
    'mail'       : 'CONCATENAR',
    'observacion': 'SI',
    'estado'     : 'SI',
    'descuento'  : 'SI',
    'bonif'      : 'SI',
    'categoria'  : 'SI',
}

# ──────────────────────────────────────────────────────────────────────────────
# ANALISIS DE HOJAS
# ──────────────────────────────────────────────────────────────────────────────

def analizar_hoja(nombre):
    """
    Analiza una hoja y devuelve un dict con su estructura completa:
    df, formulas, valores, mapa_columnas, celdas_vacias, tipo, fila_header.
    """
    ws   = ctx['wb'].sheets[nombre]
    ruta = ctx['wb'].fullname
    df   = pd.read_excel(ruta, sheet_name=nombre, header=None)

    # Leer formulas y valores reales con xlwings
    formulas, valores = {}, {}
    for r in range(df.shape[0]):
        for c in range(df.shape[1]):
            celda = f'{col_letra(c)}{r + 1}'
            v = ws.range(celda).value
            f = ws.range(celda).formula
            if v is not None or (f and f.startswith('=')):
                valores[celda] = v
                formulas[celda] = f

    # Primera celda con dato
    primera = None
    for r in range(df.shape[0]):
        for c in range(df.shape[1]):
            if pd.notna(df.iat[r, c]) and str(df.iat[r, c]).strip():
                primera = f'{col_letra(c)}{r + 1}'
                break
        if primera:
            break

    # Fila de encabezados: la que tenga mas celdas con texto
    fila_header = 0
    mejor = 0
    for r in range(min(10, df.shape[0])):
        n = sum(1 for v in df.iloc[r] if pd.notna(v) and str(v).strip())
        if n > mejor:
            mejor, fila_header = n, r

    # Mapa de columnas {nombre_col: {indice_buscarv, col_excel}}
    mapa_columnas = {}
    idx_col = 1
    for c, val in enumerate(df.iloc[fila_header]):
        if pd.notna(val) and str(val).strip():
            mapa_columnas[str(val).strip()] = {
                'indice'   : idx_col,
                'col_excel': col_letra(c),
            }
            idx_col += 1

    # Celdas vacias por columna (solo columnas que tienen algun dato)
    celdas_vacias = {}
    for c in range(df.shape[1]):
        tiene = any(pd.notna(df.iat[r, c]) and str(df.iat[r, c]).strip()
                    for r in range(df.shape[0]))
        if tiene:
            vacias = [r + 1 for r in range(df.shape[0])
                      if pd.isna(df.iat[r, c]) or not str(df.iat[r, c]).strip()]
            # Excluir filas que ya tienen formula
            vacias = [fv for fv in vacias
                      if not formulas.get(f'{col_letra(c)}{fv}', '').startswith('=')]
            if vacias:
                celdas_vacias[col_letra(c)] = vacias

    # Tipo de hoja
    n_form = sum(1 for f in formulas.values() if f and f.startswith('='))
    n_dato = sum(1 for r in range(df.shape[0]) for c in range(df.shape[1])
                 if pd.notna(df.iat[r, c]) and str(df.iat[r, c]).strip())
    if n_form == 0:
        tipo = 'datos'
    elif n_form > n_dato * 0.3:
        tipo = 'formulas'
    else:
        tipo = 'mixta'

    return {
        'df'           : df,
        'formulas'     : formulas,
        'valores'      : valores,
        'primera_celda': primera,
        'fila_header'  : fila_header,
        'mapa_columnas': mapa_columnas,
        'celdas_vacias': celdas_vacias,
        'tipo'         : tipo,
        'shape'        : df.shape,
    }

def analizar_todo():
    """Analiza todas las hojas y guarda el contexto."""
    sep('ANALIZANDO LIBRO')
    for s in ctx['wb'].sheets:
        print(f'  → {s.name}...', end=' ', flush=True)
        try:
            ctx['hojas'][s.name] = analizar_hoja(s.name)
            info = ctx['hojas'][s.name]
            print(f'OK  [{info["tipo"]}]  '
                  f'{info["shape"][0]}f x {info["shape"][1]}c  '
                  f'vacias: {sum(len(v) for v in info["celdas_vacias"].values())} celdas')
        except Exception as e:
            ctx['hojas'][s.name] = None
            print(f'ERROR: {e}')

# ──────────────────────────────────────────────────────────────────────────────
# SUGERENCIAS INTELIGENTES
# ──────────────────────────────────────────────────────────────────────────────

def sugerencias_para(nombre):
    """
    Genera sugerencias de formulas para la hoja 'nombre' usando el contexto
    de TODAS las hojas del libro.
    """
    info = ctx['hojas'].get(nombre)
    if not info:
        return []

    sug = []

    # 1. Extender patrones de formulas BUSCARV/H existentes
    sug += _extender_patron_buscar(nombre, info)

    # 2. Labels en col A que coinciden con columnas de hojas de datos → BUSCARV
    sug += _detectar_buscarv_por_labels(nombre, info)

    # 3. Labels en col A que coinciden con FILAS de hojas de datos → BUSCARH
    sug += _detectar_buscarh_por_labels(nombre, info)

    # 4. Keywords en encabezados → SUMA, PROMEDIO, etc.
    sug += _detectar_por_keywords(nombre, info)

    # 5. Tabla de datos numericos sin fila de totales → SUMA/PROMEDIO
    sug += _detectar_tabla_sin_totales(nombre, info)

    # 6. Columna de IDs sin correo → CONCATENAR
    sug += _detectar_correo_faltante(nombre, info)

    return sug

def _extender_patron_buscar(nombre, info):
    """Si hay BUSCARV/H existentes, extiende el patron a celdas vacias."""
    sug = []
    for celda, formula in info['formulas'].items():
        if not formula or not formula.upper().startswith(('=BUSCARV', '=BUSCARH')):
            continue

        col = ''.join(filter(str.isalpha, celda))
        fila = int(''.join(filter(str.isdigit, celda)))

        match = re.search(
            r'(BUSCARV|BUSCARH)\(([^;,]+)[;,]([^;,]+)[;,](\d+)[;,]([^)]+)\)',
            formula, re.IGNORECASE
        )
        if not match:
            continue

        fn, val_b, matriz, ind, ord_ = match.groups()
        ind = int(ind)
        vacias = [f for f in info['celdas_vacias'].get(col, []) if f > fila]

        for i, fv in enumerate(vacias, 1):
            nueva = formula.replace(f';{ind};', f';{ind + i};') \
                           .replace(f',{ind},', f',{ind + i},')
            sug.append({
                'celda'  : f'{col}{fv}',
                'formula': nueva,
                'tipo'   : fn,
                'razon'  : f'Extiende patron de {celda} (indicador {ind + i})',
                'indice' : ind + i,
                'auto'   : True,
            })
    return sug

def _detectar_buscarv_por_labels(nombre, info):
    """
    Busca labels en col A con celda B vacia y compara con columnas de hojas fuente.
    Usa ctx['bd'] si esta definido; sino el analisis automatico.
    Si hay coincidencias → sugiere BUSCARV con formula completa.
    """
    sug = []
    df  = info['df']

    # Pares (fila_excel, label) donde A tiene texto y B esta vacio
    pares = []
    for r in range(df.shape[0]):
        val_a = df.iat[r, 0] if df.shape[1] > 0 else None
        celda_b = f'B{r + 1}'
        tiene_formula_b = info['formulas'].get(celda_b, '').startswith('=')
        val_b  = df.iat[r, 1] if df.shape[1] > 1 else None
        if (pd.notna(val_a) and str(val_a).strip() and
                not tiene_formula_b and
                (pd.isna(val_b) or not str(val_b).strip())):
            pares.append((r + 1, str(val_a).strip()))

    if not pares:
        return sug

    # Usar BD definidas primero, luego hojas auto
    fuentes = {}
    for h_nombre, bd in ctx.get('bd', {}).items():
        if h_nombre != nombre:
            fuentes[h_nombre] = {'mapa': bd['mapa'], 'rango_abs': bd['rango_abs'],
                                 'tipo': 'bd', 'bd': bd}
    for h_nombre, h_info in ctx['hojas'].items():
        if h_nombre != nombre and h_nombre not in fuentes and h_info:
            fuentes[h_nombre] = {'mapa': h_info['mapa_columnas'],
                                 'rango_abs': None, 'tipo': 'auto', 'bd': None}

    # Buscar celda del valor buscado en la hoja actual (selector de ID)
    # Es la celda vacia en col B cuya fila anterior tiene un label tipo "Ingresar ID"
    celda_selector = _detectar_celda_selector(info)

    for h_nombre, fuente in fuentes.items():
        mapa = fuente['mapa']
        coincidencias = [(fila, label, mapa[label]['indice'])
                         for fila, label in pares if label in mapa]
        if len(coincidencias) < 2:
            continue

        # Obtener rango de matriz
        if fuente['rango_abs']:
            matriz = fuente['rango_abs']
        else:
            h_info = ctx['hojas'][h_nombre]
            fh = h_info['fila_header']
            uf = h_info['shape'][0]
            uc = col_letra(h_info['shape'][1] - 1)
            matriz = f'{h_nombre}!$A${fh + 1}:${uc}${uf}'

        for fila, label, indice in coincidencias:
            # Si tenemos selector, generar formula completa AUTO
            if celda_selector:
                cs = celda_selector.replace('$', '')
                cf, cc = ''.join(filter(str.isdigit, cs)), ''.join(filter(str.isalpha, cs))
                val_b_ref = f'${cc}${cf}'
                formula = f'=BUSCARV({val_b_ref};{matriz};{indice};FALSO)'
                es_auto  = True
            else:
                formula = None
                es_auto  = False

            sug.append({
                'celda'      : f'B{fila}',
                'formula'    : formula,
                'tipo'       : 'BUSCARV',
                'razon'      : f'"{label}" = col {indice} de {h_nombre}',
                'hoja_fuente': h_nombre,
                'indice'     : indice,
                'label'      : label,
                'auto'       : es_auto,
            })
    return sug

def _detectar_celda_selector(info):
    """
    Detecta la celda donde el usuario ingresa el valor a buscar.
    Patron: celda con texto como 'Ingresar', 'ID', 'Codigo' seguida de
    una celda con valor numerico/texto en la fila siguiente o misma fila col B.
    """
    df = info['df']
    for r in range(df.shape[0]):
        val = df.iat[r, 0] if df.shape[1] > 0 else None
        if pd.isna(val) or not str(val).strip():
            continue
        label = str(val).strip().lower()
        if any(kw in label for kw in ('ingresar', 'ingrese', 'buscar', 'id', 'codigo', 'cod')):
            # El selector puede estar en la fila siguiente col A, o en col B misma fila
            for fila_s, col_s in [(r + 2, 'A'), (r + 1, 'B')]:
                if fila_s <= df.shape[0]:
                    v = df.iat[fila_s - 1, 0] if col_s == 'A' else (
                        df.iat[r, 1] if df.shape[1] > 1 else None)
                    if pd.notna(v) and str(v).strip():
                        return f'${col_s}${fila_s if col_s == "A" else r + 1}'
    return None

def _detectar_por_keywords(nombre, info):
    """Busca keywords en headers y sugiere formulas correspondientes."""
    sug = []
    df  = info['df']
    for r in range(df.shape[0]):
        for c in range(df.shape[1]):
            val = df.iat[r, c]
            if pd.isna(val) or not str(val).strip():
                continue
            label_lower = str(val).strip().lower()
            for kw, tipo_f in HINTS.items():
                if kw in label_lower:
                    col_l = col_letra(c)
                    for rv in range(r + 1, df.shape[0]):
                        celda = f'{col_l}{rv + 1}'
                        v2 = df.iat[rv, c]
                        ya_tiene = info['formulas'].get(celda, '').startswith('=')
                        if (pd.isna(v2) or not str(v2).strip()) and not ya_tiene:
                            sug.append({
                                'celda'  : celda,
                                'formula': None,
                                'tipo'   : tipo_f,
                                'razon'  : f'Header "{val}" sugiere {tipo_f}',
                                'header' : str(val).strip(),
                                'auto'   : False,
                            })
    return sug

def _detectar_buscarh_por_labels(nombre, info):
    """
    Detecta BUSCARH: labels en col A de esta hoja coinciden con valores en col A
    de otra hoja (no con sus encabezados de columna).
    Patron tipico: BuscarH tiene productos en col A, Tabla02 tiene los mismos
    productos en su primera columna de datos.
    """
    sug = []
    df  = info['df']

    # Labels en col A de esta hoja donde col B esta vacia
    labels_vacias = []
    for r in range(df.shape[0]):
        val_a = df.iat[r, 0] if df.shape[1] > 0 else None
        celda_b = f'B{r + 1}'
        ya_formula = info['formulas'].get(celda_b, '').startswith('=')
        val_b = df.iat[r, 1] if df.shape[1] > 1 else None
        if (pd.notna(val_a) and str(val_a).strip() and
                not ya_formula and
                (pd.isna(val_b) or not str(val_b).strip())):
            labels_vacias.append((r + 1, str(val_a).strip()))

    if not labels_vacias:
        return sug

    # Detectar selector de zona/categoria
    celda_selector = _detectar_celda_selector(info)

    # Usar BD definidas primero, luego hojas auto
    fuentes = {}
    for h_nombre, bd in ctx.get('bd', {}).items():
        if h_nombre != nombre:
            fuentes[h_nombre] = ('bd', bd, None)
    for h_nombre, h_info in ctx['hojas'].items():
        if h_nombre != nombre and h_nombre not in fuentes and h_info:
            fuentes[h_nombre] = ('auto', None, h_info)

    for h_nombre, (origen, bd, h_info) in fuentes.items():
        if origen == 'bd':
            h_df = ctx['hojas'][h_nombre]['df'] if ctx['hojas'].get(h_nombre) else None
            fh_excel = bd['f_ini']   # 0-based
            col_a_vals_idx = {}      # label → indice fila (1=header row)
            if h_df is not None:
                for r in range(fh_excel + 1, bd['f_fin'] + 1):
                    if r < h_df.shape[0]:
                        v = h_df.iat[r, bd['c_ini']]
                        if pd.notna(v) and str(v).strip():
                            ind = r - fh_excel + 1
                            col_a_vals_idx[str(v).strip()] = ind
            matriz = bd['rango_abs']
        else:
            h_df = h_info['df']
            fh = h_info['fila_header']
            col_a_vals_idx = {}
            for r in range(fh + 1, h_df.shape[0]):
                v = h_df.iat[r, 0]
                if pd.notna(v) and str(v).strip():
                    ind = r - fh + 1
                    col_a_vals_idx[str(v).strip()] = ind
            uf   = h_df.shape[0]
            ucol = col_letra(h_df.shape[1] - 1)
            matriz = f'{h_nombre}!$A${fh + 1}:${ucol}${uf}'

        coincidencias = [(fila, label) for fila, label in labels_vacias
                         if label in col_a_vals_idx]
        if len(coincidencias) < 2:
            continue

        for fila, label in coincidencias:
            ind = col_a_vals_idx[label]
            # Si tenemos selector, generar formula completa AUTO
            if celda_selector:
                cs = celda_selector.replace('$', '')
                cf2 = ''.join(filter(str.isdigit, cs))
                cc2 = ''.join(filter(str.isalpha, cs))
                val_b_ref = f'${cc2}${cf2}'
                formula  = f'=BUSCARH({val_b_ref};{matriz};{ind};FALSO)'
                es_auto  = True
            else:
                formula = None
                es_auto = False

            sug.append({
                'celda'      : f'B{fila}',
                'formula'    : formula,
                'tipo'       : 'BUSCARH',
                'razon'      : f'"{label}" fila {ind} de {h_nombre} → BUSCARH',
                'hoja_fuente': h_nombre,
                'matriz'     : matriz,
                'indice'     : ind,
                'label'      : label,
                'auto'       : es_auto,
            })
    return sug

def _detectar_tabla_sin_totales(nombre, info):
    """
    Si la hoja tiene una tabla con columnas numericas pero sin fila de TOTAL/PROMEDIO,
    sugiere agregar SUMA y PROMEDIO al final.
    """
    sug = []
    df  = info['df']
    fh  = info['fila_header']

    # Buscar columnas con encabezado de texto y datos numericos abajo
    cols_num = []
    for c in range(df.shape[1]):
        hdr = df.iat[fh, c] if fh < df.shape[0] else None
        if pd.isna(hdr) or not str(hdr).strip():
            continue
        hdr_lower = str(hdr).strip().lower()
        # Saltar columnas que son identificadores o ya tienen totales
        if any(kw in hdr_lower for kw in
               ('total', 'suma', 'promedio', 'media', 'id', 'dni',
                'codigo', 'cod', 'numero', 'nro', 'telefon', 'cel')):
            continue
        vals = [df.iat[r, c] for r in range(fh + 1, df.shape[0])
                if pd.notna(df.iat[r, c])]
        if len(vals) >= 2 and all(isinstance(v, (int, float)) for v in vals):
            cols_num.append((c, str(hdr).strip()))

    if not cols_num:
        return sug

    # Ver si ya hay alguna fila de SUMA justo despues de los datos
    ultima_fila_datos = fh + 1
    for r in range(fh + 1, df.shape[0]):
        tiene_dato = any(pd.notna(df.iat[r, c]) and str(df.iat[r, c]).strip()
                         for c in range(df.shape[1]))
        if tiene_dato:
            ultima_fila_datos = r

    fila_total   = ultima_fila_datos + 2   # fila Excel (1-based)
    fila_promedio = ultima_fila_datos + 3

    # Verificar si ya existe una SUMA en esa zona
    for c, hdr in cols_num[:1]:
        ya = info['formulas'].get(f'{col_letra(c)}{fila_total}', '')
        if 'SUMA' in ya.upper():
            return sug   # ya tiene totales

    # Rango de datos
    f_ini = fh + 2  # fila Excel primer dato
    f_fin = ultima_fila_datos + 1

    for c, hdr in cols_num:
        cl = col_letra(c)
        rango = f'{cl}{f_ini}:{cl}{f_fin}'
        sug.append({
            'celda'  : f'{cl}{fila_total}',
            'formula': f'=SUMA({rango})',
            'tipo'   : 'SUMA',
            'razon'  : f'Col "{hdr}": tabla numerica sin totales',
            'auto'   : True,
        })
        sug.append({
            'celda'  : f'{cl}{fila_promedio}',
            'formula': f'=PROMEDIO({rango})',
            'tipo'   : 'PROMEDIO',
            'razon'  : f'Col "{hdr}": tabla numerica sin promedios',
            'auto'   : True,
        })
    # Label en col A si esta vacio
    col_a_total   = f'A{fila_total}'
    col_a_promedio = f'A{fila_promedio}'
    if not info['formulas'].get(col_a_total) and pd.isna(df.iat[ultima_fila_datos + 1, 0] if ultima_fila_datos + 1 < df.shape[0] else None):
        sug.append({'celda': col_a_total,   'formula': None, 'tipo': 'LABEL_TOTAL',
                    'razon': 'Label para fila TOTAL',   'auto': False})
        sug.append({'celda': col_a_promedio, 'formula': None, 'tipo': 'LABEL_PROMEDIO',
                    'razon': 'Label para fila PROMEDIO', 'auto': False})
    return sug

def _detectar_correo_faltante(nombre, info):
    """
    Si la hoja tiene una columna de IDs/codigos y NO tiene columna de correo,
    sugiere agregar la columna con CONCATENAR.
    """
    sug = []
    df  = info['df']
    fh  = info['fila_header']

    # Solo aplica en hojas de datos (no formularios de busqueda)
    if info.get('tipo') not in ('datos', 'mixta'):
        return sug

    # Solo si el encabezado de columna sugiere tabla de personas/empleados
    todos_hdrs = [str(df.iat[fh, c]).lower()
                  for c in range(df.shape[1])
                  if fh < df.shape[0] and pd.notna(df.iat[fh, c])]
    tiene_hdr_persona = any(kw in h for h in todos_hdrs
                            for kw in ('nombre', 'apellido', 'trabaj',
                                       'personal', 'sueldo', 'cargo'))
    if not tiene_hdr_persona:
        return sug

    # Hay alguna col con 'correo' o 'email' ya?
    tiene_correo = any(
        any(kw in str(df.iat[fh, c]).lower()
            for kw in ('correo', 'email', 'mail', '@empresa'))
        for c in range(df.shape[1])
        if fh < df.shape[0] and pd.notna(df.iat[fh, c])
    )
    if tiene_correo:
        return sug

    # Hay col A con IDs (texto o numero) y tiene datos?
    col_a_vals = [df.iat[r, 0] for r in range(fh + 1, df.shape[0])
                  if pd.notna(df.iat[r, 0]) and str(df.iat[r, 0]).strip()]
    if len(col_a_vals) < 2:
        return sug

    # Sugerir nueva columna al final
    col_nueva = col_letra(df.shape[1])
    fila_datos_ini = fh + 2
    fila_datos_fin = fh + 1 + len(col_a_vals)
    ejemplo = f'={col_letra(0)}{fila_datos_ini}&"@empresa.com"'
    sug.append({
        'celda'  : f'{col_nueva}{fila_datos_ini}',
        'formula': None,
        'tipo'   : 'CONCATENAR',
        'razon'  : f'Tabla sin columna de correo — agregar col {col_nueva} con {ejemplo}',
        'col_id' : col_letra(0),
        'f_ini'  : fila_datos_ini,
        'f_fin'  : fila_datos_fin,
        'auto'   : False,
    })
    return sug

# ──────────────────────────────────────────────────────────────────────────────
# ESCRITURA DE FORMULAS
# ──────────────────────────────────────────────────────────────────────────────

def _escribir_pares(pares):
    """Escribe una lista de (celda, formula) usando FormulaLocal y muestra resultados."""
    if not ctx['activa']:
        print('  ✗ No hay hoja activa.')
        return
    sep('RESULTADOS', '-')
    print(f'  {"Celda":<7} {"Formula":<50} Resultado')
    print(f'  {"─"*7} {"─"*50} {"─"*18}')
    for celda, formula in pares:
        try:
            ctx['activa'].range(celda).api.FormulaLocal = formula
            valor = ctx['activa'].range(celda).value
            print(f'  {celda:<7} {formula:<50} {repr(valor)[:18]}')
        except Exception as e:
            print(f'  {celda:<7} ERROR: {e}')
    print(f'\n  ✓ {len(pares)} formulas escritas.')
    # Re-analizar la hoja para actualizar el contexto
    ctx['hojas'][ctx['activa'].name] = analizar_hoja(ctx['activa'].name)

# ──────────────────────────────────────────────────────────────────────────────
# CARGAR ARCHIVO
# ──────────────────────────────────────────────────────────────────────────────

def cargar_archivo():
    sep('CARGAR ARCHIVO EXCEL')
    directorio = os.getcwd()
    print(f'  Directorio actual: {directorio}\n')

    # Buscar xlsx (sin temporales) aqui y en subcarpetas directas
    archivos = []
    for f in sorted(os.listdir(directorio)):
        if f.endswith('.xlsx') and not f.startswith('~$'):
            archivos.append(os.path.join(directorio, f))
    for d in sorted(os.listdir(directorio)):
        rd = os.path.join(directorio, d)
        if os.path.isdir(rd):
            for f in sorted(os.listdir(rd)):
                if f.endswith('.xlsx') and not f.startswith('~$'):
                    archivos.append(os.path.join(rd, f))

    if archivos:
        print('  Archivos encontrados:')
        for i, a in enumerate(archivos, 1):
            print(f'    {i}. {os.path.relpath(a, directorio)}')
        print('    0. Ruta manual')
        sel = input('\n  Selecciona: ').strip()
        if sel.isdigit() and 1 <= int(sel) <= len(archivos):
            ruta = archivos[int(sel) - 1]
        else:
            ruta = input('  Ruta del archivo: ').strip().strip('"')
    else:
        print('  No se encontraron archivos .xlsx.')
        ruta = input('  Ruta del archivo: ').strip().strip('"')

    if not os.path.exists(ruta):
        print(f'  ✗ No encontrado: {ruta}')
        return

    # Cerrar sesion anterior
    if ctx['wb']:
        try: ctx['wb'].close()
        except: pass
    if ctx['app']:
        try: ctx['app'].quit()
        except: pass

    ctx['app']     = xw.App(visible=False)
    ctx['wb']      = ctx['app'].books.open(ruta)
    ctx['archivo'] = os.path.basename(ruta)
    ctx['hojas']   = {}
    ctx['activa']  = None

    print(f'\n  ✓ Cargado: {ctx["archivo"]}')

    # Auto-analizar todas las hojas
    analizar_todo()

    # Mostrar resumen y luego definir BD de contexto
    mostrar_resumen()
    definir_contexto_bd()


# ──────────────────────────────────────────────────────────────────────────────
# DEFINIR CONTEXTO BD
# ──────────────────────────────────────────────────────────────────────────────

def _parsear_rango(rango_str):
    """Convierte 'A3:Q17' en (col_ini_0, fila_ini_0, col_fin_0, fila_fin_0)."""
    rango_str = rango_str.strip().upper()
    m = re.match(r'([A-Z]+)(\d+):([A-Z]+)(\d+)', rango_str)
    if not m:
        return None
    c1, f1, c2, f2 = m.groups()
    return (letra_idx(c1), int(f1) - 1, letra_idx(c2), int(f2) - 1)

def _validar_rango_bd(nombre, rango_str):
    """
    Lee el rango indicado, detecta headers, tipos de columna y previsualizacion.
    Devuelve dict con info o None si el rango es invalido.
    """
    coords = _parsear_rango(rango_str)
    if not coords:
        print(f'  ✗ Rango invalido: {rango_str}  (formato esperado: A3:Q17)')
        return None

    c_ini, f_ini, c_fin, f_fin = coords
    ws  = ctx['wb'].sheets[nombre]

    # Leer con xlwings para tener formulas y valores
    n_filas = f_fin - f_ini + 1
    n_cols  = c_fin - c_ini + 1

    # Primera fila → headers
    headers = []
    for c in range(c_ini, c_fin + 1):
        v = ws.range(f'{col_letra(c)}{f_ini + 1}').value
        headers.append(str(v).strip() if v is not None else f'Col{c - c_ini + 1}')

    # Contar filas de datos (despues del header)
    n_datos = 0
    for f in range(f_ini + 1, f_fin + 1):
        tiene = any(ws.range(f'{col_letra(c)}{f + 1}').value is not None
                    for c in range(c_ini, min(c_ini + 4, c_fin + 1)))
        if tiene:
            n_datos += 1

    # Detectar tipo por columna (texto / numero / fecha)
    tipos_col = []
    for c in range(c_ini, c_fin + 1):
        muestras = []
        for f in range(f_ini + 1, min(f_ini + 6, f_fin + 1)):
            v = ws.range(f'{col_letra(c)}{f + 1}').value
            if v is not None:
                muestras.append(v)
        if not muestras:
            tipos_col.append('vacio')
        elif all(isinstance(v, (int, float)) for v in muestras):
            tipos_col.append('num')
        elif all(hasattr(v, 'year') for v in muestras):
            tipos_col.append('fecha')
        else:
            tipos_col.append('texto')

    # Mapa columnas {nombre: {indice_buscarv, col_excel, tipo}}
    mapa = {}
    for i, (hdr, tipo) in enumerate(zip(headers, tipos_col)):
        mapa[hdr] = {
            'indice'   : i + 1,
            'col_excel': col_letra(c_ini + i),
            'tipo'     : tipo,
        }

    # Primeros 3 valores de cada columna (para preview)
    preview = {}
    for c in range(c_ini, c_fin + 1):
        vals = []
        for f in range(f_ini + 1, min(f_ini + 4, f_fin + 1)):
            v = ws.range(f'{col_letra(c)}{f + 1}').value
            if v is not None:
                vals.append(v)
        preview[col_letra(c)] = vals

    # Rango absoluto para formulas
    rango_abs = (f'{nombre}!${col_letra(c_ini)}${f_ini + 1}'
                 f':${col_letra(c_fin)}${f_fin + 1}')

    return {
        'rango_str' : rango_str.upper(),
        'rango_abs' : rango_abs,
        'c_ini'     : c_ini,
        'f_ini'     : f_ini,       # 0-based
        'c_fin'     : c_fin,
        'f_fin'     : f_fin,
        'headers'   : headers,
        'tipos_col' : tipos_col,
        'mapa'      : mapa,
        'n_datos'   : n_datos,
        'n_cols'    : n_cols,
        'preview'   : preview,
        'col_id'    : col_letra(c_ini),   # primera columna (clave de busqueda)
    }

def _mostrar_info_bd(nombre, bd):
    """Muestra el detalle de una BD validada."""
    sep(f'BD VALIDADA: {nombre}  [{bd["rango_str"]}]', '-')
    print(f'  Rango absoluto : {bd["rango_abs"]}')
    print(f'  Filas de datos : {bd["n_datos"]}')
    print(f'  Columnas       : {bd["n_cols"]}')
    print()
    print(f'  {"#":>3}  {"Col":>4}  {"Tipo":>6}  Nombre')
    print(f'  {"─"*3}  {"─"*4}  {"─"*6}  {"─"*35}')
    for hdr, info_c in bd['mapa'].items():
        prevs = bd['preview'].get(info_c['col_excel'], [])
        ej = repr(prevs[0])[:18] if prevs else ''
        print(f'  {info_c["indice"]:>3}  {info_c["col_excel"]:>4}  '
              f'{info_c["tipo"]:>6}  {hdr:<35} ej: {ej}')

def definir_contexto_bd():
    """
    Pregunta al usuario qué hojas son datos de origen (BD) y con qué rango exacto.
    Valida el rango y muestra los detalles detectados.
    """
    if not ctx['wb']:
        return

    ctx['bd'] = {}
    sep('DEFINIR DATOS DE ORIGEN (BD / CONTEXTO)')
    print('  Aqui indicas qué hojas contienen la base de datos.')
    print('  El script usara ese contexto para sugerir las formulas correctas.\n')

    nombres = list(ctx['hojas'].keys())
    while True:
        print('  Hojas disponibles:')
        for i, n in enumerate(nombres, 1):
            info  = ctx['hojas'].get(n)
            tipo  = info['tipo'] if info else 'error'
            marca = '  [BD definida]' if n in ctx['bd'] else ''
            print(f'    {i}. {n:<22} [{tipo}]{marca}')
        print('    0. Continuar (ya defini las BD necesarias)')

        sel = input('\n  Selecciona hoja BD: ').strip()
        if sel == '0':
            break
        if not sel.isdigit() or not (1 <= int(sel) <= len(nombres)):
            print('  ✗ Invalido.\n')
            continue

        h_nombre = nombres[int(sel) - 1]
        h_info   = ctx['hojas'].get(h_nombre)

        # Sugerir rango automatico basado en el analisis previo
        if h_info:
            fh   = h_info['fila_header']
            uf   = h_info['shape'][0]
            uc   = col_letra(h_info['shape'][1] - 1)
            sug_rango = f'A{fh + 1}:{uc}{uf}'
        else:
            sug_rango = 'A1:A10'

        print(f'\n  Hoja seleccionada: {h_nombre}')
        print(f'  Rango sugerido   : {sug_rango}')
        print(f'  (Enter para aceptar, o escribe otro rango, ej: A3:Q17)')
        rango_inp = input('  Rango BD: ').strip()
        if not rango_inp:
            rango_inp = sug_rango

        bd_info = _validar_rango_bd(h_nombre, rango_inp)
        if not bd_info:
            continue

        _mostrar_info_bd(h_nombre, bd_info)

        ok = input('\n  ¿Es correcto este rango? (s/n): ').strip().lower()
        if ok == 's':
            ctx['bd'][h_nombre] = bd_info
            print(f'  ✓ BD "{h_nombre}" registrada.\n')
        else:
            print('  Vuelve a ingresar el rango.\n')

    if ctx['bd']:
        print(f'\n  BD de contexto definidas: {list(ctx["bd"].keys())}')
    else:
        print('\n  Sin BD definida. Las sugerencias usaran deteccion automatica.')
    print()

# ──────────────────────────────────────────────────────────────────────────────
# RESUMEN
# ──────────────────────────────────────────────────────────────────────────────

def mostrar_resumen():
    if not ctx['wb']:
        print('  ✗ No hay archivo cargado.')
        return

    sep('RESUMEN DEL LIBRO')
    print(f'  {"Hoja":<20} {"Tipo":<10} {"Tamaño":<15} {"Columnas detectadas"}')
    print(f'  {"─"*20} {"─"*10} {"─"*15} {"─"*35}')

    for nombre, info in ctx['hojas'].items():
        if not info:
            print(f'  {nombre:<20} ERROR')
            continue
        cols = list(info['mapa_columnas'].keys())
        cols_str = ', '.join(cols[:4]) + ('...' if len(cols) > 4 else '')
        vacias = sum(len(v) for v in info['celdas_vacias'].values())
        tamano = f'{info["shape"][0]}f x {info["shape"][1]}c'
        tipo_str = f'{info["tipo"]}' + (f' ({vacias} vacias)' if vacias else '')
        print(f'  {nombre:<20} {tipo_str:<22} {tamano:<15} {cols_str}')

    # Relaciones entre hojas
    sep('RELACIONES ENTRE HOJAS', '-')
    relaciones = set()
    for nombre, info in ctx['hojas'].items():
        if not info:
            continue
        for formula in info['formulas'].values():
            if not formula:
                continue
            for ref in re.findall(r"'?([A-Za-z0-9_\s]+)'?!", formula):
                ref_limpio = ref.strip()
                for h in ctx['hojas']:
                    if h.lower() == ref_limpio.lower() and h != nombre:
                        relaciones.add((nombre, h))
    if relaciones:
        for origen, destino in sorted(relaciones):
            print(f'  {origen}  →  usa datos de  →  {destino}')
    else:
        print('  No se detectaron referencias entre hojas aun.')

# ──────────────────────────────────────────────────────────────────────────────
# TRABAJAR EN UNA HOJA (nucleo inteligente)
# ──────────────────────────────────────────────────────────────────────────────

def trabajar_hoja():
    if not ctx['wb']:
        print('  ✗ Primero carga un archivo.')
        return

    # ── 1. Elegir hoja ──────────────────────────────────────────────
    sep('ELEGIR HOJA A TRABAJAR')
    nombres = list(ctx['hojas'].keys())
    for i, n in enumerate(nombres, 1):
        info   = ctx['hojas'].get(n)
        tipo   = info['tipo'] if info else 'error'
        vacias = sum(len(v) for v in info.get('celdas_vacias', {}).values()) if info else 0
        es_bd  = n in ctx.get('bd', {})
        marca  = '(BD de contexto)' if es_bd else ('*** NECESITA TRABAJO' if vacias > 0 else '')
        print(f'  {i}. {n:<22} [{tipo}]  {vacias} celdas vacias  {marca}')

    sel = input('\n  Numero de hoja: ').strip()
    if not sel.isdigit() or not (1 <= int(sel) <= len(nombres)):
        print('  ✗ Invalido.')
        return

    nombre        = nombres[int(sel) - 1]
    ctx['activa'] = ctx['wb'].sheets[nombre]
    info          = ctx['hojas'][nombre]

    # ── 2. Resumen breve de la hoja ─────────────────────────────────
    sep(f'HOJA: {nombre}')
    print(f'  Tamaño    : {info["shape"][0]} filas x {info["shape"][1]} columnas')
    formulas_ex = {c: f for c, f in info['formulas'].items() if f and f.startswith('=')}
    print(f'  Formulas  : {len(formulas_ex)} existentes')

    # Mostrar contenido de la hoja de forma compacta (solo celdas con dato)
    ws  = ctx['activa']
    df  = info['df']
    print(f'\n  Contenido detectado:')
    for r in range(df.shape[0]):
        fila_excel = r + 1
        celdas_fila = []
        for c in range(df.shape[1]):
            v = df.iat[r, c]
            f = info['formulas'].get(f'{col_letra(c)}{fila_excel}', '')
            if pd.notna(v) and str(v).strip():
                txt = f'[={f[1:15]}...]' if f.startswith('=') else repr(str(v))[:18]
                celdas_fila.append(f'{col_letra(c)}{fila_excel}:{txt}')
        if celdas_fila:
            linea = '  '.join(celdas_fila[:6])
            print(f'    Fila {fila_excel:>2}: {linea}')

    # ── 3. Preguntar rango de celdas a completar ────────────────────
    sep('RANGO A COMPLETAR', '-')
    print('  Indica el rango de celdas donde deben ir las formulas.')
    print('  Ejemplo:  B11:B26   o   E4:E17   o   B11  (celda individual)\n')
    rango_inp = input('  Rango a completar: ').strip().upper()

    if not rango_inp:
        print('  ✗ Sin rango.')
        return

    # Normalizar celda individual a rango
    if ':' not in rango_inp:
        rango_inp = f'{rango_inp}:{rango_inp}'

    coords = _parsear_rango(rango_inp)
    if not coords:
        print(f'  ✗ Rango invalido: {rango_inp}')
        return

    c_ini, f_ini, c_fin, f_fin = coords

    # ── 4. Analizar ese rango ───────────────────────────────────────
    sep(f'ANALISIS DEL RANGO {rango_inp}', '-')

    # Para cada celda del rango: estado actual + label a la izquierda
    filas_rango = list(range(f_ini, f_fin + 1))   # 0-based
    col_trabajo  = col_letra(c_ini)                 # primera col del rango

    estado_celdas = []   # (fila_excel, label_izq, valor_actual, formula_actual, estado)
    for fr in filas_rango:
        fila_excel = fr + 1
        celda      = f'{col_trabajo}{fila_excel}'
        valor_act  = ws.range(celda).value
        form_act   = ws.range(celda).formula

        # Label a la izquierda (col anterior)
        label_izq = None
        if c_ini > 0:
            v_izq = ws.range(f'{col_letra(c_ini - 1)}{fila_excel}').value
            if v_izq is not None and str(v_izq).strip():
                label_izq = str(v_izq).strip()

        if form_act and form_act.startswith('='):
            estado = 'formula'
        elif valor_act is not None and str(valor_act).strip():
            estado = 'tiene_dato'
        else:
            estado = 'vacio'

        estado_celdas.append((fila_excel, label_izq, valor_act, form_act, estado))

    n_vacias   = sum(1 for _, _, _, _, e in estado_celdas if e == 'vacio')
    n_formulas = sum(1 for _, _, _, _, e in estado_celdas if e == 'formula')
    n_datos    = sum(1 for _, _, _, _, e in estado_celdas if e == 'tiene_dato')
    labels     = [l for _, l, _, _, _ in estado_celdas if l]

    print(f'  Celdas en rango  : {len(estado_celdas)}')
    print(f'    Vacias          : {n_vacias}  ← estas necesitan formula')
    print(f'    Con formula     : {n_formulas}')
    print(f'    Con dato fijo   : {n_datos}')

    if labels:
        print(f'\n  Labels detectados a la izquierda:')
        for fila_excel, label, _, _, estado in estado_celdas:
            marca = '  [vacio]' if estado == 'vacio' else (
                    '  [=formula]' if estado == 'formula' else '  [dato]')
            if label:
                print(f'    Fila {fila_excel:>2}: {label:<35}{marca}')

    # ── 5. Detectar tipo de formula y generar sugerencias ──────────
    sep('SUGERENCIAS PARA ESTE RANGO', '-')
    sugs_rango = _sugerir_para_rango(nombre, estado_celdas, col_trabajo, rango_inp)

    if not sugs_rango:
        print('  No se pudo detectar automaticamente el tipo de formula.')
        print('  Opciones disponibles abajo.')
    else:
        print(f'  Tipo detectado : {sugs_rango[0]["tipo"]}')
        print(f'  Fuente de datos: {sugs_rango[0].get("hoja_fuente", "—")}')
        print(f'\n  Preview ({len(sugs_rango)} formulas):')
        for s in sugs_rango:
            est = next((e for f, _, _, _, e in estado_celdas if f == int(''.join(filter(str.isdigit, s['celda'])))), '')
            marca = '' if est == 'vacio' else f'  [ya tiene {est}]'
            print(f'    {s["celda"]:<7} {s["formula"][:58]}{marca}')

    # ── 6. Acciones ─────────────────────────────────────────────────
    sep('ACCIONES', '-')
    if sugs_rango:
        print('  1. Aplicar estas formulas en las celdas VACIAS del rango')
        print('  2. Aplicar en TODAS las celdas del rango (sobreescribe)')
    print('  3. Asistente BUSCARV')
    print('  4. Asistente BUSCARH')
    print('  5. Asistente SUMA / PROMEDIO / MAX / MIN')
    print('  6. Asistente CONCATENAR')
    print('  7. Formula manual con patron {IND}')
    print('  8. Formula individual manual')
    print('  0. Volver')

    accion = input('\n  Accion: ').strip()

    if accion == '1' and sugs_rango:
        solo_vacias = [(s['celda'], s['formula']) for s in sugs_rango
                       if next((e for f, _, _, _, e in estado_celdas
                                if f == int(''.join(filter(str.isdigit, s['celda'])))), '') == 'vacio']
        if solo_vacias:
            _escribir_pares(solo_vacias)
        else:
            print('  No hay celdas vacias en el rango.')

    elif accion == '2' and sugs_rango:
        ok = input(f'  Sobreescribir {len(sugs_rango)} celdas? (s/n): ').strip().lower()
        if ok == 's':
            _escribir_pares([(s['celda'], s['formula']) for s in sugs_rango])

    elif accion == '3':
        _asistente_buscar('BUSCARV', nombre, sugs_rango, rango_inp)
    elif accion == '4':
        _asistente_buscar('BUSCARH', nombre, sugs_rango, rango_inp)
    elif accion == '5':
        _asistente_mat_est(rango_inp)
    elif accion == '6':
        _asistente_concatenar(sugs_rango)
    elif accion == '7':
        _rango_patron(rango_inp)
    elif accion == '8':
        _formula_individual()


def _sugerir_para_rango(nombre, estado_celdas, col_trabajo, rango_str):
    """
    Dado el análisis de un rango específico, genera formulas concretas
    mirando los labels a la izquierda y cruzando con las BD definidas.
    """
    labels  = [(fila, label) for fila, label, _, _, _ in estado_celdas if label]
    if not labels:
        return []

    # Detectar celda selector (buscar en la hoja completa)
    info    = ctx['hojas'][nombre]
    selector = _detectar_celda_selector(info)

    sugs = []

    # ── Intentar BUSCARV: labels coinciden con nombres de columnas de una BD ──
    for h_nombre, bd in ctx.get('bd', {}).items():
        if h_nombre == nombre:
            continue
        mapa  = bd['mapa']
        hits  = [(fila, label, mapa[label]['indice'])
                 for fila, label in labels if label in mapa]
        if len(hits) < 2:
            continue
        # Usar selector si lo hay, sino dejar placeholder
        if selector:
            cs  = selector.replace('$', '')
            ref = f'${("".join(filter(str.isalpha, cs)))}${("".join(filter(str.isdigit, cs)))}'
        else:
            ref = '???'
        for fila, label, ind in hits:
            sugs.append({
                'celda'      : f'{col_trabajo}{fila}',
                'formula'    : f'=BUSCARV({ref};{bd["rango_abs"]};{ind};FALSO)',
                'tipo'       : 'BUSCARV',
                'razon'      : f'"{label}" = col {ind} de {h_nombre}',
                'hoja_fuente': h_nombre,
                'indice'     : ind,
            })
        if sugs:
            return sugs   # primera BD que tenga suficientes coincidencias

    # ── Intentar BUSCARH: labels coinciden con filas de col A de una BD ──
    for h_nombre, bd in ctx.get('bd', {}).items():
        if h_nombre == nombre:
            continue
        h_df = ctx['hojas'].get(h_nombre, {})
        if not h_df:
            continue
        h_df = h_df['df']
        col_a_vals = {}
        for r in range(bd['f_ini'] + 1, bd['f_fin'] + 1):
            if r < h_df.shape[0]:
                v = h_df.iat[r, bd['c_ini']]
                if pd.notna(v) and str(v).strip():
                    ind = r - bd['f_ini'] + 1
                    col_a_vals[str(v).strip()] = ind
        hits = [(fila, label) for fila, label in labels if label in col_a_vals]
        if len(hits) < 2:
            continue
        if selector:
            cs  = selector.replace('$', '')
            ref = f'${("".join(filter(str.isalpha, cs)))}${("".join(filter(str.isdigit, cs)))}'
        else:
            ref = '???'
        for fila, label in hits:
            ind = col_a_vals[label]
            sugs.append({
                'celda'      : f'{col_trabajo}{fila}',
                'formula'    : f'=BUSCARH({ref};{bd["rango_abs"]};{ind};FALSO)',
                'tipo'       : 'BUSCARH',
                'razon'      : f'"{label}" fila {ind} de {h_nombre}',
                'hoja_fuente': h_nombre,
                'indice'     : ind,
            })
        if sugs:
            return sugs

    return sugs

# ──────────────────────────────────────────────────────────────────────────────
# ASISTENTES
# ──────────────────────────────────────────────────────────────────────────────

def _aplicar_automaticas(sugs):
    auto = [s for s in sugs if s.get('auto') and s.get('formula')]
    if not auto:
        print('  No hay sugerencias automaticas con formula completa.')
        print('  Usa los asistentes especificos (opciones 2-5).')
        return
    print(f'\n  Se escribiran {len(auto)} formulas:')
    for s in auto:
        print(f'  {s["celda"]}: {s["formula"][:60]}')
        print(f'         → {s["razon"]}')

    # Detectar si hay labels de TOTAL/PROMEDIO que agregar
    labels_extra = []
    tipos_auto = {s['tipo'] for s in auto}
    if 'SUMA' in tipos_auto:
        celdas_suma = [s['celda'] for s in auto if s['tipo'] == 'SUMA']
        # Fila de la primera SUMA
        fila_t = int(''.join(filter(str.isdigit, celdas_suma[0])))
        fila_p = fila_t + 1
        labels_extra = [(f'A{fila_t}', 'TOTAL'), (f'A{fila_p}', 'PROMEDIO')]
        print(f'\n  Tambien se escribiran labels: A{fila_t}=TOTAL  A{fila_p}=PROMEDIO')

    ok = input('\n  Confirmar? (s/n): ').strip().lower()
    if ok == 's':
        pares = [(s['celda'], s['formula']) for s in auto]
        _escribir_pares(pares)
        # Escribir labels directamente (no formulas)
        if labels_extra and ctx['activa']:
            for celda, val in labels_extra:
                ctx['activa'].range(celda).value = val
                print(f'  {celda} = {val}')

def _asistente_buscar(tipo, nombre, sugs, rango_inp=None):
    """Asistente completo para BUSCARV o BUSCARH."""
    sep(f'ASISTENTE {tipo}')
    info = ctx['hojas'][nombre]

    # Intentar inferir parametros de formulas existentes
    refs_existentes = {c: f for c, f in info['formulas'].items()
                       if f and f.upper().startswith(f'={tipo}')}

    if refs_existentes:
        celda_ref, formula_ref = next(iter(refs_existentes.items()))
        match = re.search(
            r'=(?:BUSCARV|BUSCARH)\(([^;,]+)[;,]([^;,]+)[;,](\d+)[;,]([^)]+)\)',
            formula_ref, re.IGNORECASE)
        if match:
            val_b, matriz, ind_ref, ord_ = match.groups()
            ind_ref = int(ind_ref)
            print(f'  Parametros inferidos de {celda_ref}:')
            print(f'    Valor buscado : {val_b}')
            print(f'    Matriz        : {matriz}')
            print(f'    Ordenado      : {ord_}')
            print(f'    Indicador ref : {ind_ref}')

            # Tomar sugerencias del tipo correcto sin formula aun
            pendientes = [s for s in sugs if s['tipo'] == tipo and not s.get('auto')]

            if pendientes:
                print(f'\n  Celdas pendientes detectadas:')
                pares = []
                for s in pendientes:
                    formula = re.sub(
                        r'(BUSCARV|BUSCARH)\([^;,]+[;,][^;,]+[;,]\d+[;,]',
                        lambda m: m.group(0).rsplit(';', 2)[0] + f';{s["indice"]};',
                        formula_ref, flags=re.IGNORECASE)
                    # Construccion directa mas simple
                    formula = (f'={tipo}({val_b};{matriz};{s["indice"]};{ord_})')
                    pares.append((s['celda'], formula))
                    print(f'    {s["celda"]:<7} ind={s["indice"]}  {s.get("label", "")}')

                ok = input('\n  Confirmar y escribir? (s/n): ').strip().lower()
                if ok == 's':
                    _escribir_pares(pares)
                return

    # Sin referencias pero hay sugerencias BUSCARH con matriz detectada
    bh_sugs = [s for s in sugs if s['tipo'] == tipo and s.get('matriz')]
    if bh_sugs and tipo == 'BUSCARH':
        s0 = bh_sugs[0]
        print(f'  Contexto detectado automaticamente:')
        print(f'    Hoja fuente : {s0["hoja_fuente"]}')
        print(f'    Matriz      : {s0["matriz"]}')
        print(f'    Celdas      : {[s["celda"] for s in bh_sugs]}')
        print(f'\n  Celda del selector de zona/categoria (ej: $A$9):')
        val_b = input('  > ').strip() or '$A$9'
        matriz = input(f'  Matriz (Enter = {s0["matriz"]}): ').strip() or s0['matriz']
        ord_   = 'FALSO'
        pares  = [(s['celda'], f'=BUSCARH({val_b};{matriz};{s["indice"]};{ord_})')
                  for s in bh_sugs]
        print('\n  Preview:')
        for c, f in pares:
            print(f'  {c}: {f}')
        ok = input('\n  Confirmar? (s/n): ').strip().lower()
        if ok == 's':
            _escribir_pares(pares)
        return

    # Sin referencias: pedir todos los parametros
    print('  Ingresa los parametros:')

    # Valor buscado
    val_b = input('  Celda del valor buscado (ej: $A$9): ').strip()

    # Hoja fuente
    hojas_datos = [(h, i) for h, i in ctx['hojas'].items()
                   if i and i['tipo'] == 'datos' and h != nombre]
    if hojas_datos:
        print('\n  Hojas de datos disponibles:')
        for i, (h, inf) in enumerate(hojas_datos, 1):
            fh    = inf['fila_header']
            uf    = inf['shape'][0]
            ucol  = col_letra(inf['shape'][1] - 1)
            rango = f'{h}!$A${fh + 2}:${ucol}${uf}'
            print(f'    {i}. {h}  →  rango sugerido: {rango}')
            print(f'       Columnas: ' + ', '.join(list(inf['mapa_columnas'].keys())[:6]))
        sel = input('\n  Selecciona hoja fuente (numero): ').strip()
        if sel.isdigit() and 1 <= int(sel) <= len(hojas_datos):
            h_nombre, h_info = hojas_datos[int(sel) - 1]
            fh   = h_info['fila_header']
            uf   = h_info['shape'][0]
            ucol = col_letra(h_info['shape'][1] - 1)
            rango_sug = f'{h_nombre}!$A${fh + 2}:${ucol}${uf}'
            matriz = input(f'  Matriz (Enter = {rango_sug}): ').strip()
            if not matriz:
                matriz = rango_sug
            # Mostrar indices de columna de la hoja fuente
            print(f'\n  Indices de columna de {h_nombre}:')
            for col_n, col_i in h_info['mapa_columnas'].items():
                print(f'    [{col_i["indice"]:2d}] {col_n}')
        else:
            matriz = input('  Rango de la matriz (ej: Tabla01!$A$4:$Q$17): ').strip()
    else:
        matriz = input('  Rango de la matriz: ').strip()

    ord_ = 'FALSO'  # Por defecto coincidencia exacta

    # Celdas a llenar
    print('\n  Donde escribir las formulas?')
    print('  1. Rango con indicador variable {IND}')
    print('  2. Lista manual de celdas')
    modo = input('  Modo: ').strip()

    pares = []
    if modo == '1':
        rango = input('  Rango (ej: B11:B26): ').strip().upper()
        ind_i = input('  Indicador inicial (ej: 2): ').strip()
        if rango and ind_i.isdigit():
            ini, fin_ = rango.split(':')
            col_l = ''.join(filter(str.isalpha, ini))
            f_ini = int(''.join(filter(str.isdigit, ini)))
            f_fin = int(''.join(filter(str.isdigit, fin_)))
            for fila, ind in zip(range(f_ini, f_fin + 1),
                                  range(int(ind_i), int(ind_i) + f_fin - f_ini + 1)):
                pares.append((f'{col_l}{fila}',
                               f'={tipo}({val_b};{matriz};{ind};{ord_})'))
    elif modo == '2':
        print('  Ingresa celda;indicador por linea. FIN para terminar.')
        while True:
            linea = input('  > ').strip()
            if linea.upper() == 'FIN':
                break
            if ';' in linea:
                c_, i_ = linea.split(';', 1)
                pares.append((c_.strip().upper(),
                               f'={tipo}({val_b};{matriz};{i_.strip()};{ord_})'))

    if pares:
        print('\n  Preview:')
        for c, f in pares:
            print(f'  {c}: {f}')
        ok = input('\n  Confirmar? (s/n): ').strip().lower()
        if ok == 's':
            _escribir_pares(pares)

def _asistente_mat_est(rango_inp=None):
    """Asistente para SUMA, PROMEDIO, MAX, MIN, CONTAR, CONTARSI."""
    sep('ASISTENTE MATEMATICA / ESTADISTICA')
    if rango_inp:
        print(f'  Rango destino pre-seleccionado: {rango_inp}')
    print('  Tipos: SUMA, PROMEDIO, MAX, MIN, CONTAR, CONTARSI')
    tipo = input('  Tipo de formula: ').strip().upper()

    if tipo == 'CONTARSI':
        rango  = input('  Rango a contar (ej: C4:C17): ').strip()
        crit   = input('  Criterio (ej: "Masculino" o A2): ').strip()
        celda  = input('  Celda destino (ej: D20): ').strip().upper()
        formula = f'=CONTARSI({rango};{crit})'
        _escribir_pares([(celda, formula)])
    else:
        rango  = input(f'  Rango de datos para {tipo} (ej: B4:D4): ').strip()

        print('\n  Donde escribir? (rango o celda individual)')
        destino = input('  Destino (ej: E4:E17 o E4): ').strip().upper()

        if ':' in destino:
            ini, fin_ = destino.split(':')
            col_l = ''.join(filter(str.isalpha, ini))
            f_ini = int(''.join(filter(str.isdigit, ini)))
            f_fin = int(''.join(filter(str.isdigit, fin_)))

            print('\n  El rango de datos tiene fila variable? (ej: B4:D4, B5:D5...)')
            var = input('  (s/n): ').strip().lower()
            if var == 's':
                # Detectar la fila variable en el rango
                match = re.search(r'(\d+)', rango)
                fila_base = int(match.group(1)) if match else f_ini
                pares = []
                for fila in range(f_ini, f_fin + 1):
                    offset = fila - f_ini
                    rango_ajustado = re.sub(r'\d+', lambda m: str(int(m.group()) + offset), rango)
                    pares.append((f'{col_l}{fila}', f'={tipo}({rango_ajustado})'))
                print('\n  Preview:')
                for c, f in pares:
                    print(f'  {c}: {f}')
                ok = input('\n  Confirmar? (s/n): ').strip().lower()
                if ok == 's':
                    _escribir_pares(pares)
            else:
                pares = [(f'{col_l}{f}', f'={tipo}({rango})') for f in range(f_ini, f_fin + 1)]
                print('\n  Preview:')
                for c, f in pares:
                    print(f'  {c}: {f}')
                ok = input('\n  Confirmar? (s/n): ').strip().lower()
                if ok == 's':
                    _escribir_pares(pares)
        else:
            formula = f'={tipo}({rango})'
            _escribir_pares([(destino, formula)])

def _asistente_concatenar(sugs=None):
    """Asistente para generar textos concatenados (ej: correos)."""
    sep('ASISTENTE CONCATENAR')
    print('  Ejemplo tipico: codigo + "@empresa.com" → E14024@empresa.com')
    print('  La formula sera: =<celda_codigo>&"<sufijo>"')

    # Pre-llenar desde sugerencia si existe
    sug_conc = next((s for s in (sugs or []) if s['tipo'] == 'CONCATENAR'), None)
    if sug_conc:
        print(f'\n  Detectado automaticamente:')
        print(f'    Col de IDs   : {sug_conc["col_id"]}')
        print(f'    Rango datos  : fila {sug_conc["f_ini"]} a {sug_conc["f_fin"]}')
        print(f'    Col destino  : {sug_conc["celda"][0]}')

    celda_codigo = input('  Celda del codigo (ej: A4): ').strip()
    sufijo = input('  Texto a agregar (ej: @empresa.com): ').strip()
    destino = input('  Celda o rango destino (ej: E4 o E4:E17): ').strip().upper()

    formula_base = f'={celda_codigo}&"{sufijo}"'

    if ':' in destino:
        ini, fin_ = destino.split(':')
        col_l = ''.join(filter(str.isalpha, ini))
        col_c = ''.join(filter(str.isalpha, celda_codigo))
        f_ini = int(''.join(filter(str.isdigit, ini)))
        f_fin = int(''.join(filter(str.isdigit, fin_)))
        f_cod = int(''.join(filter(str.isdigit, celda_codigo)))

        pares = []
        for fila in range(f_ini, f_fin + 1):
            offset  = fila - f_ini
            c_ajust = f'{col_c}{f_cod + offset}'
            pares.append((f'{col_l}{fila}', f'={c_ajust}&"{sufijo}"'))

        print('\n  Preview (primeras 5):')
        for c, f in pares[:5]:
            print(f'  {c}: {f}')
        if len(pares) > 5:
            print(f'  ... y {len(pares)-5} mas')
        ok = input('\n  Confirmar? (s/n): ').strip().lower()
        if ok == 's':
            _escribir_pares(pares)
    else:
        _escribir_pares([(destino, formula_base)])

def _rango_patron(rango_inp=None):
    """Escribe formulas en un rango con indicador {IND} variable."""
    sep('RANGO CON PATRON {IND}')
    if not ctx['activa']:
        print('  ✗ Selecciona una hoja primero.')
        return
    print('  Usa {IND} donde va el numero variable.')
    rango  = input(f'  Rango (ej: B11:B26){" [Enter=" + rango_inp + "]" if rango_inp else ""}: ').strip().upper()
    if not rango and rango_inp:
        rango = rango_inp
    base   = input('  Formula base: ').strip()
    ind_i  = input('  Indicador inicial: ').strip()
    if not ind_i.isdigit():
        print('  ✗ Indicador invalido.')
        return
    try:
        ini, fin_ = rango.split(':')
        col_l = ''.join(filter(str.isalpha, ini))
        f_ini = int(''.join(filter(str.isdigit, ini)))
        f_fin = int(''.join(filter(str.isdigit, fin_)))
    except:
        print('  ✗ Rango invalido.')
        return
    pares = [(f'{col_l}{f}', base.replace('{IND}', str(int(ind_i) + f - f_ini)))
             for f in range(f_ini, f_fin + 1)]
    print('\n  Preview (primeras 5):')
    for c, f in pares[:5]:
        print(f'  {c}: {f}')
    ok = input('\n  Confirmar? (s/n): ').strip().lower()
    if ok == 's':
        _escribir_pares(pares)

def _formula_individual():
    """Escribe una sola formula en una celda."""
    if not ctx['activa']:
        print('  ✗ Selecciona una hoja primero.')
        return
    celda   = input('  Celda (ej: B11): ').strip().upper()
    formula = input('  Formula (en espanol, con ;): ').strip()
    _escribir_pares([(celda, formula)])

def _ver_rango():
    """Muestra formulas y valores de un rango."""
    if not ctx['activa']:
        print('  ✗ Selecciona una hoja primero.')
        return
    rango = input('  Rango (ej: A11:B26): ').strip().upper()
    try:
        ini, fin_ = rango.split(':')
        cl_i = ''.join(filter(str.isalpha, ini))
        cl_f = ''.join(filter(str.isalpha, fin_))
        f_i  = int(''.join(filter(str.isdigit, ini)))
        f_f  = int(''.join(filter(str.isdigit, fin_)))
    except:
        print('  ✗ Rango invalido.')
        return
    print(f'\n  {"Celda":<8} {"Formula / Dato":<50} Valor')
    print(f'  {"─"*8} {"─"*50} {"─"*18}')
    for fila in range(f_i, f_f + 1):
        for ci in range(letra_idx(cl_i), letra_idx(cl_f) + 1):
            celda   = f'{col_letra(ci)}{fila}'
            formula = ctx['activa'].range(celda).formula
            valor   = ctx['activa'].range(celda).value
            display = formula if formula and formula.startswith('=') else '(dato)'
            print(f'  {celda:<8} {str(display):<50} {repr(valor)[:18]}')

# ──────────────────────────────────────────────────────────────────────────────
# GUARDAR
# ──────────────────────────────────────────────────────────────────────────────

def guardar():
    if not ctx['wb']:
        print('  ✗ No hay archivo cargado.')
        return
    sep('GUARDAR')
    print('  1. Sobreescribir el mismo archivo')
    print('  2. Guardar con nuevo nombre')
    op = input('  Opcion: ').strip()
    if op == '1':
        ctx['wb'].save()
        print(f'  ✓ Guardado: {ctx["archivo"]}')
    elif op == '2':
        nombre = input('  Nombre nuevo (sin .xlsx): ').strip()
        ruta   = os.path.join(os.path.dirname(ctx['wb'].fullname), f'{nombre}.xlsx')
        ctx['wb'].save(ruta)
        print(f'  ✓ Guardado: {nombre}.xlsx')

# ──────────────────────────────────────────────────────────────────────────────
# MAIN
# ──────────────────────────────────────────────────────────────────────────────

def main():
    sep('HERRAMIENTA EXCEL INTELIGENTE v2.0 - UTP', '=')
    print('  Analisis automatico y resolucion de ejercicios Excel')
    print('  Soporta: BUSCARV, BUSCARH, SUMA, PROMEDIO, MAX, MIN,')
    print('           CONTAR, CONTARSI, SI, CONCATENAR y mas.')

    MENU = {
        '1': ('Cargar archivo Excel  (analiza todo automaticamente)', cargar_archivo),
        '2': ('Ver resumen del libro', mostrar_resumen),
        '3': ('Definir / cambiar BD de contexto', definir_contexto_bd),
        '4': ('Trabajar en una hoja  (sugerencias inteligentes)', trabajar_hoja),
        '5': ('Formula individual manual', _formula_individual),
        '6': ('Rango con patron {IND}', _rango_patron),
        '7': ('Ver valores de un rango', _ver_rango),
        '8': ('Guardar archivo', guardar),
    }

    while True:
        arch  = ctx['archivo'] or '(ninguno)'
        activ = ctx['activa'].name if ctx['activa'] else '(ninguna)'
        bds   = ', '.join(ctx['bd'].keys()) if ctx['bd'] else '(sin definir)'
        sep('MENU PRINCIPAL')
        print(f'  Archivo: {arch}   |   Hoja activa: {activ}   |   BD: {bds}\n')
        for k, (desc, _) in MENU.items():
            print(f'  {k}. {desc}')
        print('  0. Salir')

        op = input('\n  Opcion: ').strip()
        if op == '0':
            if ctx['wb']:
                try: ctx['wb'].close()
                except: pass
            if ctx['app']:
                try: ctx['app'].quit()
                except: pass
            print('\n  Hasta luego.\n')
            break
        elif op in MENU:
            try:
                MENU[op][1]()
            except Exception as e:
                print(f'\n  ✗ Error inesperado: {e}')
                import traceback; traceback.print_exc()
        else:
            print('  ✗ Opcion invalida.')

if __name__ == '__main__':
    main()
