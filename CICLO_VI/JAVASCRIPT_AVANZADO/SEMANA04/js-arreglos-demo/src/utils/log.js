/**
 * Utilidades compartidas por todos los ejemplos.
 * Sirven para imprimir arreglos mostrando explícitamente los "huecos" (holes),
 * algo que console.log hace de forma poco clara según el entorno.
 */
import { pathToFileURL } from 'node:url';

const COLORES = {
  reset: '\x1b[0m',
  negrita: '\x1b[1m',
  gris: '\x1b[90m',
  cian: '\x1b[36m',
  verde: '\x1b[32m',
  amarillo: '\x1b[33m',
  rojo: '\x1b[31m',
};

/** Título principal de cada módulo de ejemplos. */
export function titulo(texto) {
  const linea = '='.repeat(Math.max(texto.length + 4, 60));
  console.log(`\n${COLORES.cian}${linea}`);
  console.log(`${COLORES.negrita}  ${texto}${COLORES.reset}`);
  console.log(`${COLORES.cian}${linea}${COLORES.reset}`);
}

/** Subtítulo de cada sección dentro de un módulo. */
export function sub(texto) {
  console.log(`\n${COLORES.amarillo}--- ${texto} ---${COLORES.reset}`);
}

/** Comentario explicativo en gris. */
export function nota(texto) {
  console.log(`${COLORES.gris}   // ${texto}${COLORES.reset}`);
}

/** Cuenta cuántos índices dentro de [0, length) NO existen realmente. */
export function contarHuecos(arr) {
  let huecos = 0;
  for (let i = 0; i < arr.length; i++) {
    if (!(i in arr)) huecos++;
  }
  return huecos;
}

/** true si el arreglo NO tiene huecos (es denso). */
export function esDenso(arr) {
  return contarHuecos(arr) === 0;
}

/** Convierte el arreglo a texto marcando los huecos como <hueco>. */
export function formatear(arr) {
  const partes = [];
  for (let i = 0; i < arr.length; i++) {
    if (i in arr) {
      const v = arr[i];
      partes.push(typeof v === 'string' ? `'${v}'` : String(v));
    } else {
      partes.push(`${COLORES.rojo}<hueco>${COLORES.reset}`);
    }
  }
  return `[ ${partes.join(', ')} ]`;
}

/**
 * Imprime un arreglo junto con su "ficha técnica":
 * length real, cantidad de claves propias, huecos y si es denso o no.
 */
export function describir(etiqueta, arr) {
  const huecos = contarHuecos(arr);
  const tipo = huecos === 0
    ? `${COLORES.verde}DENSO${COLORES.reset}`
    : `${COLORES.rojo}NO DENSO (sparse)${COLORES.reset}`;
  console.log(
    `${COLORES.negrita}${etiqueta}${COLORES.reset} = ${formatear(arr)}\n` +
    `   length=${arr.length} · claves reales=${Object.keys(arr).length} · huecos=${huecos} · ${tipo}`
  );
}

/** Convierte cualquier valor a texto legible (JSON.stringify convierte NaN en null). */
export function valorATexto(valor) {
  if (Array.isArray(valor)) return formatear(valor);
  if (typeof valor === 'number' || typeof valor === 'boolean') return String(valor);
  if (valor === null || valor === undefined) return String(valor);
  if (typeof valor === 'string') return `'${valor}'`;
  return JSON.stringify(valor);
}

/** Imprime una expresión y su resultado en una sola línea. */
export function muestra(expresion, valor) {
  console.log(`   ${expresion} ${COLORES.gris}→${COLORES.reset} ${valorATexto(valor)}`);
}

/**
 * Permite que cada archivo se pueda ejecutar solo (node src/01-....js)
 * y también ser importado por index.js sin ejecutarse dos veces.
 */
export function esPrincipal(metaUrl) {
  if (!process.argv[1]) return false;
  return metaUrl === pathToFileURL(process.argv[1]).href;
}
