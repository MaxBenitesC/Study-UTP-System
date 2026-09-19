/**
 * PUNTO DE ENTRADA
 * --------------------------------------------------------------
 * Ejecuta todos los módulos de ejemplos, o solo uno:
 *
 *   node src/index.js          → ejecuta todos
 *   node src/index.js 3        → ejecuta solo el módulo 3
 *   node src/index.js 1 3      → ejecuta los módulos 1 y 3
 */
import { main as densos } from './01-arreglos-densos.js';
import { main as noDensos } from './02-arreglos-no-densos.js';
import { main as shiftUnshift } from './03-shift-unshift.js';

const MODULOS = {
  1: { nombre: 'Arreglos densos', ejecutar: densos },
  2: { nombre: 'Arreglos no densos (sparse)', ejecutar: noDensos },
  3: { nombre: 'shift(), unshift(), push() y pop()', ejecutar: shiftUnshift },
};

const seleccion = process.argv.slice(2).filter((a) => a in MODULOS);
const aEjecutar = seleccion.length ? seleccion : Object.keys(MODULOS);

console.log('\n╔════════════════════════════════════════════════════════╗');
console.log('║   DENSOS · NO DENSOS · SHIFT/UNSHIFT · PUSH/POP        ║');
console.log('╚════════════════════════════════════════════════════════╝');
console.log('Módulos disponibles:');
for (const [id, { nombre }] of Object.entries(MODULOS)) {
  console.log(`  ${id}. ${nombre}`);
}
console.log('\nSugerencia: node src/index.js 2   (ejecuta solo un módulo)');

for (const id of aEjecutar) {
  MODULOS[id].ejecutar();
}

console.log('\n✔ Fin de la demostración.\n');
