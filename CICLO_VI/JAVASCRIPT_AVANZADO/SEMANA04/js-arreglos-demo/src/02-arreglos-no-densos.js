/**
 * 02 · ARREGLOS NO DENSOS (SPARSE ARRAYS)
 * --------------------------------------------------------------
 * Un arreglo NO DENSO tiene "huecos" (holes): posiciones dentro del
 * rango [0, length) que NO existen como propiedad del objeto.
 * Un hueco NO es lo mismo que el valor undefined.
 *
 * Ejecutar:  node src/02-arreglos-no-densos.js   |   npm run no-densos
 */
import { titulo, sub, nota, describir, muestra, esPrincipal, contarHuecos } from './utils/log.js';

export function main() {
  titulo('02 · ARREGLOS NO DENSOS (SPARSE)');

  // ------------------------------------------------------------
  sub('2.1 Las 4 formas de crear huecos');

  // (a) Literal con comas seguidas (elisión)
  const porElision = [1, , 3];
  describir('[1, , 3]', porElision);

  // (b) Constructor Array con longitud
  const porConstructor = new Array(4);
  describir('new Array(4)', porConstructor);

  // (c) Asignar un índice más allá del final
  const porIndiceLejano = ['a'];
  porIndiceLejano[4] = 'e';
  describir("['a'] con arr[4] = 'e'", porIndiceLejano);

  // (d) delete sobre un elemento
  const porDelete = ['w', 'x', 'y', 'z'];
  delete porDelete[1];
  describir('delete arr[1]', porDelete);
  nota('delete NUNCA reduce length: solo deja un hueco. Usa splice() si quieres eliminar de verdad.');

  // (e) Aumentar length a mano
  const porLength = [1, 2];
  porLength.length = 5;
  describir('arr.length = 5', porLength);

  // ------------------------------------------------------------
  sub('2.2 Hueco vs. undefined: no son lo mismo');

  const conHueco = [1, , 3];
  const conUndefined = [1, undefined, 3];

  muestra('1 in conHueco', 1 in conHueco);
  muestra('1 in conUndefined', 1 in conUndefined);
  muestra('conHueco[1]', conHueco[1]);
  muestra('conUndefined[1]', conUndefined[1]);
  muestra('conHueco.hasOwnProperty(1)', Object.prototype.hasOwnProperty.call(conHueco, 1));
  muestra('Object.keys(conHueco)', Object.keys(conHueco));
  muestra('Object.keys(conUndefined)', Object.keys(conUndefined));
  nota('Leer un hueco devuelve undefined, pero la propiedad no existe.');

  // ------------------------------------------------------------
  sub('2.3 Cómo detectar si un arreglo es no denso');

  const detectarHuecos = (arr) => arr.length !== Object.keys(arr).filter((k) => /^\d+$/.test(k)).length;
  muestra('detectarHuecos([1, , 3])', detectarHuecos([1, , 3]));
  muestra('detectarHuecos([1, undefined, 3])', detectarHuecos([1, undefined, 3]));
  muestra('contarHuecos(new Array(5))', contarHuecos(new Array(5)));

  const indicesDeHuecos = (arr) => {
    const salida = [];
    for (let i = 0; i < arr.length; i++) if (!(i in arr)) salida.push(i);
    return salida;
  };
  muestra('indicesDeHuecos([ ,1, ,3, ])', indicesDeHuecos([, 1, , 3]));

  // ------------------------------------------------------------
  sub('2.4 Métodos que IGNORAN los huecos');

  const sparse = [1, , 3, , 5];
  describir('sparse', sparse);

  const visitados = [];
  sparse.forEach((v, i) => visitados.push(`${i}:${v}`));
  muestra('forEach visitó', visitados);
  nota('forEach saltó los índices 1 y 3 por completo.');

  muestra('sparse.filter(() => true)', sparse.filter(() => true));
  nota('filter DENSIFICA: el resultado ya no tiene huecos.');

  muestra('sparse.reduce((a, b) => a + b, 0)', sparse.reduce((a, b) => a + b, 0));
  muestra('Object.keys(sparse)', Object.keys(sparse));
  muestra('sparse.some(v => v === undefined)', sparse.some((v) => v === undefined));
  nota('some/every también saltan los huecos.');

  // ------------------------------------------------------------
  sub('2.5 Métodos que PRESERVAN los huecos');

  const mapeado = sparse.map((v) => v * 2);
  describir('sparse.map(v => v * 2)', mapeado);
  nota('map no ejecuta el callback en los huecos, pero los conserva en la salida.');

  describir('sparse.slice(0, 4)', sparse.slice(0, 4));
  describir('sparse.reverse()', [1, , 3].reverse());
  describir('[1, , 3].concat([4])', [1, , 3].concat([4]));

  // ------------------------------------------------------------
  sub('2.6 Métodos que TRATAN los huecos como undefined');

  const s = [1, , 3];
  muestra('[1, , 3].includes(undefined)', s.includes(undefined));
  muestra('[1, , 3].indexOf(undefined)', s.indexOf(undefined));
  nota('includes ve undefined; indexOf ignora los huecos. ¡Comportamientos opuestos!');

  const recorridoForOf = [];
  for (const v of s) recorridoForOf.push(v);
  muestra('for...of sobre [1, , 3]', recorridoForOf);
  muestra('[...[1, , 3]]', [...s]);
  muestra('Array.from([1, , 3])', Array.from(s));
  muestra('[1, , 3].find(v => v === undefined) !== undefined', s.find((v) => v === undefined) !== undefined);
  muestra('[1, , 3].join("-")', s.join('-'));
  nota('join convierte huecos, null y undefined en cadena vacía.');

  muestra('JSON.stringify([1, , 3])', JSON.stringify(s));
  nota('JSON no tiene concepto de hueco: los serializa como null.');

  describir('[3, , 1].sort()', [3, , 1].sort());
  nota('sort empuja los huecos al final del arreglo.');

  // ------------------------------------------------------------
  sub('2.7 Cómo DENSIFICAR un arreglo no denso');

  const roto = [1, , 3, , 5];

  describir('original', roto);
  describir('Array.from(roto)', Array.from(roto));
  describir('[...roto]', [...roto]);
  describir('roto.flat()', roto.flat());
  nota('flat() elimina los huecos y ACORTA el arreglo (length distinto).');

  const rellenado = Array.from(roto, (v) => v ?? 0);
  describir('Array.from(roto, v => v ?? 0)', rellenado);

  const soloValores = roto.filter(() => true);
  describir('roto.filter(() => true)', soloValores);
  nota('filter conserva los valores existentes y compacta el arreglo.');

  // ------------------------------------------------------------
  sub('2.8 Por qué evitar los arreglos no densos');

  nota('1) Motores como V8 cambian a un modo "dictionary" más lento.');
  nota('2) El comportamiento de los métodos es inconsistente (ver 2.4 a 2.6).');
  nota('3) Producen bugs difíciles: length miente sobre los datos reales.');
  nota('Regla práctica: usa splice() en vez de delete, y fill()/Array.from en vez de new Array(n).');

  const malo = new Array(3);
  const bueno = Array.from({ length: 3 }, () => null);
  describir('MAL: new Array(3)', malo);
  describir('BIEN: Array.from({length:3}, () => null)', bueno);
}

if (esPrincipal(import.meta.url)) main();
