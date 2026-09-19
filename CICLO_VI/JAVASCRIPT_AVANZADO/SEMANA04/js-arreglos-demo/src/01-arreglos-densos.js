/**
 * 01 · ARREGLOS DENSOS
 * --------------------------------------------------------------
 * Un arreglo DENSO es aquel cuyos índices van de 0 a length-1 SIN
 * ningún hueco: todos los índices existen realmente como propiedad
 * del objeto arreglo.
 *
 * Ejecutar:  node src/01-arreglos-densos.js   |   npm run densos
 */
import { titulo, sub, nota, describir, muestra, esPrincipal, esDenso } from './utils/log.js';

export function main() {
  titulo('01 · ARREGLOS DENSOS');

  // ------------------------------------------------------------
  sub('1.1 Creación de arreglos densos');

  const literal = ['manzana', 'pera', 'uva'];
  describir('literal', literal);

  const conFrom = Array.from({ length: 5 }, (_, i) => i * 10);
  describir('Array.from({length:5}, ...)', conFrom);
  nota('Array.from SIEMPRE produce un arreglo denso.');

  const conFill = new Array(4).fill(0);
  describir('new Array(4).fill(0)', conFill);
  nota('new Array(4) crea huecos, pero .fill() los rellena y lo vuelve denso.');

  const conOf = Array.of(1, 2, 3);
  describir('Array.of(1,2,3)', conOf);
  nota('Array.of(3) crea [3]; new Array(3) crea un arreglo de 3 huecos. ¡Ojo!');

  const conPush = [];
  for (let i = 1; i <= 4; i++) conPush.push(i * i);
  describir('construido con push()', conPush);

  // ------------------------------------------------------------
  sub('1.2 Cómo verificar que un arreglo es denso');

  const denso = [10, 20, 30];
  muestra('Object.keys(denso).length === denso.length', Object.keys(denso).length === denso.length);
  muestra('denso.every((_, i) => i in denso)', denso.every((_, i) => i in denso));
  muestra('esDenso(denso)', esDenso(denso));

  nota('El operador "in" pregunta si el índice EXISTE, no si vale undefined.');
  const conUndefined = [1, undefined, 3];
  describir('conUndefined', conUndefined);
  muestra('1 in conUndefined', 1 in conUndefined);
  nota('Tiene el valor undefined, pero el índice existe: sigue siendo DENSO.');

  // ------------------------------------------------------------
  sub('1.3 Recorridos sobre un arreglo denso (todos visitan todo)');

  const numeros = [1, 2, 3, 4, 5];

  const visitadosForEach = [];
  numeros.forEach((n) => visitadosForEach.push(n));
  muestra('forEach visita', visitadosForEach);

  const visitadosForOf = [];
  for (const n of numeros) visitadosForOf.push(n);
  muestra('for...of visita', visitadosForOf);

  muestra('map(n => n * 2)', numeros.map((n) => n * 2));
  muestra('filter(n => n % 2 === 0)', numeros.filter((n) => n % 2 === 0));
  muestra('reduce((a, b) => a + b, 0)', numeros.reduce((a, b) => a + b, 0));
  muestra('Object.keys(numeros)', Object.keys(numeros));

  // ------------------------------------------------------------
  sub('1.4 Acceso: índices, at() y negativos');

  const letras = ['a', 'b', 'c', 'd'];
  muestra('letras[0]', letras[0]);
  muestra('letras[letras.length - 1]', letras[letras.length - 1]);
  muestra('letras.at(-1)', letras.at(-1));
  muestra('letras.at(-2)', letras.at(-2));
  muestra('letras[99] (fuera de rango)', letras[99]);
  nota('Leer fuera de rango devuelve undefined pero NO crea huecos.');

  // ------------------------------------------------------------
  sub('1.5 Arreglos densos de objetos y matrices');

  const usuarios = [
    { id: 1, nombre: 'Ana', edad: 30 },
    { id: 2, nombre: 'Luis', edad: 25 },
    { id: 3, nombre: 'Sofía', edad: 41 },
  ];
  muestra('nombres', usuarios.map((u) => u.nombre));
  muestra('mayores de 28', usuarios.filter((u) => u.edad > 28).map((u) => u.nombre));
  muestra('edad promedio', usuarios.reduce((acc, u) => acc + u.edad, 0) / usuarios.length);

  const matriz = Array.from({ length: 3 }, (_, f) =>
    Array.from({ length: 3 }, (_, c) => f * 3 + c)
  );
  console.log('   matriz 3x3:');
  matriz.forEach((fila) => console.log(`     ${fila.join(' | ')}`));
  muestra('matriz.flat()', matriz.flat());

  nota('CUIDADO: new Array(3).fill([]) repite LA MISMA referencia de arreglo.');
  const malaMatriz = new Array(3).fill([]);
  malaMatriz[0].push('x');
  muestra('malaMatriz tras push en la fila 0', JSON.stringify(malaMatriz));
  const buenaMatriz = Array.from({ length: 3 }, () => []);
  buenaMatriz[0].push('x');
  muestra('buenaMatriz tras push en la fila 0', JSON.stringify(buenaMatriz));
}

if (esPrincipal(import.meta.url)) main();
