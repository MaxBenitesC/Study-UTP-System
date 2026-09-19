import test from 'node:test';
import assert from 'node:assert/strict';

// Funciones de lógica pura
const REGEX_SOLO_LETRAS = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
const REGEX_TIPO_SEGURO = /^(Essalud|EPS)$/i;

function normalizarEspacios(str) {
  return str.replace(/\s+/g, ' ').trim();
}

function validarAlumno(alumno) {
  const errores = [];
  if (!REGEX_SOLO_LETRAS.test(alumno.nombres)) errores.push('nombres');
  if (!REGEX_SOLO_LETRAS.test(alumno.apellidos)) errores.push('apellidos');
  if (!REGEX_TIPO_SEGURO.test(alumno.tipoSeguro)) errores.push('seguro');
  return errores;
}

test('normalización de espacios múltiples con RegEx', () => {
  const sucio = '  Max    Anderson   Benites   ';
  assert.equal(normalizarEspacios(sucio), 'Max Anderson Benites');
});

test('validación con RegEx de nombres y seguro', () => {
  const valido = {
    nombres: 'Max Anderson',
    apellidos: 'Benites Corazón',
    tipoSeguro: 'EPS'
  };
  assert.equal(validarAlumno(valido).length, 0);

  const invalido = {
    nombres: 'Max123',
    apellidos: 'Benites',
    tipoSeguro: 'OtroSeguro'
  };
  assert.deepEqual(validarAlumno(invalido), ['nombres', 'seguro']);
});

test('operaciones de cadenas: slice, indexOf, includes e interpolación', () => {
  const texto = 'Universidad Tecnológica del Perú';
  assert.equal(texto.slice(0, 11), 'Universidad');
  assert.equal(texto.includes('Perú'), true);
  assert.equal(texto.indexOf('Tecnológica'), 12);

  const plantilla = `Sede: ${texto.toUpperCase()}`;
  assert.equal(plantilla, 'Sede: UNIVERSIDAD TECNOLÓGICA DEL PERÚ');
});

test('problema Slide 14: parada en cero y acumulación', () => {
  const entradas = [10, 25, 15, 0, 99];
  const vector = [];
  for (const n of entradas) {
    if (n === 0) break;
    vector.push(n);
  }
  assert.equal(vector.length, 3);
  assert.equal(vector.reduce((a, b) => a + b, 0), 50);
});
