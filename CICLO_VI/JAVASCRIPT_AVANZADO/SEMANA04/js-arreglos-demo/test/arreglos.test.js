/**
 * Pruebas que documentan el comportamiento real de JavaScript.
 * Ejecutar:  npm test   |   node --test test/
 */
import test from 'node:test';
import assert from 'node:assert/strict';
import { contarHuecos, esDenso } from '../src/utils/log.js';

test('un literal normal es denso', () => {
  assert.equal(esDenso([1, 2, 3]), true);
  assert.equal(contarHuecos([1, 2, 3]), 0);
});

test('undefined explícito NO crea un hueco', () => {
  const arr = [1, undefined, 3];
  assert.equal(esDenso(arr), true);
  assert.equal(1 in arr, true);
});

test('la elisión, new Array(n) y delete SÍ crean huecos', () => {
  assert.equal(contarHuecos([1, , 3]), 1);
  assert.equal(contarHuecos(new Array(4)), 4);
  const arr = ['a', 'b', 'c'];
  delete arr[1];
  assert.equal(contarHuecos(arr), 1);
  assert.equal(arr.length, 3, 'delete no reduce length');
});

test('forEach salta huecos pero for...of no', () => {
  const sparse = [1, , 3];
  const visitadosForEach = [];
  sparse.forEach((v) => visitadosForEach.push(v));
  assert.deepEqual(visitadosForEach, [1, 3]);

  const visitadosForOf = [];
  for (const v of sparse) visitadosForOf.push(v);
  assert.deepEqual(visitadosForOf, [1, undefined, 3]);
});

test('map preserva huecos y filter los elimina', () => {
  assert.equal(contarHuecos([1, , 3].map((v) => v * 2)), 1);
  assert.equal(contarHuecos([1, , 3].filter(() => true)), 0);
});

test('includes ve undefined en un hueco pero indexOf no', () => {
  assert.equal([1, , 3].includes(undefined), true);
  assert.equal([1, , 3].indexOf(undefined), -1);
});

test('Array.from y el spread densifican', () => {
  assert.deepEqual(Array.from([1, , 3]), [1, undefined, 3]);
  assert.equal(esDenso([...[1, , 3]]), true);
  assert.deepEqual([1, , 3].flat(), [1, 3], 'flat elimina huecos y acorta');
});

test('shift devuelve el primer elemento y muta', () => {
  const arr = ['a', 'b', 'c'];
  assert.equal(arr.shift(), 'a');
  assert.deepEqual(arr, ['b', 'c']);
  assert.equal([].shift(), undefined);
});

test('unshift devuelve el nuevo length y muta', () => {
  const arr = ['b'];
  assert.equal(arr.unshift('a'), 2);
  assert.deepEqual(arr, ['a', 'b']);
  arr.unshift('x', 'y');
  assert.deepEqual(arr, ['x', 'y', 'a', 'b']);
});

test('slice(1) es el shift inmutable y [x,...arr] el unshift inmutable', () => {
  const original = ['a', 'b', 'c'];
  assert.deepEqual(original.slice(1), ['b', 'c']);
  assert.deepEqual(['z', ...original], ['z', 'a', 'b', 'c']);
  assert.deepEqual(original, ['a', 'b', 'c'], 'el original no cambió');
});
