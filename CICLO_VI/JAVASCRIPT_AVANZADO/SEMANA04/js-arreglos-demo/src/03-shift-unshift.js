/**
 * 03 · shift() Y unshift()
 * --------------------------------------------------------------
 * shift()   → elimina y devuelve el PRIMER elemento. Muta el arreglo.
 * unshift() → inserta uno o más elementos al INICIO y devuelve el
 *             NUEVO length. Muta el arreglo.
 *
 * Ejecutar:  node src/03-shift-unshift.js   |   npm run shift
 */
import { titulo, sub, nota, describir, muestra, esPrincipal } from './utils/log.js';

export function main() {
  titulo('03 · shift() Y unshift()');

  // ------------------------------------------------------------
  sub('3.1 shift(): quitar del inicio');

  const cola = ['Ana', 'Luis', 'Sofía', 'Marco'];
  describir('cola antes', cola);

  const primero = cola.shift();
  muestra('cola.shift() devuelve', primero);
  describir('cola después', cola);
  nota('El arreglo original CAMBIÓ: shift es un método mutador.');

  const vacio = [];
  muestra('[].shift()', vacio.shift());
  muestra('[].length tras shift', vacio.length);
  nota('shift sobre un arreglo vacío devuelve undefined y no falla.');

  // ------------------------------------------------------------
  sub('3.2 unshift(): agregar al inicio');

  const espera = ['Sofía', 'Marco'];
  describir('espera antes', espera);

  const nuevoLength = espera.unshift('Ana');
  muestra("espera.unshift('Ana') devuelve", nuevoLength);
  describir('espera después', espera);
  nota('unshift devuelve el NUEVO length, no el arreglo.');

  espera.unshift('Pedro', 'Rosa');
  describir("unshift('Pedro', 'Rosa')", espera);
  nota('Con varios argumentos, se insertan en el mismo orden que se escriben.');

  // ------------------------------------------------------------
  sub('3.3 Comparación con push() y pop()');

  const lista = ['b', 'c'];
  lista.unshift('a');   // inicio
  lista.push('d');      // final
  describir('tras unshift("a") y push("d")', lista);
  muestra('lista.shift() (saca del inicio)', lista.shift());
  muestra('lista.pop()   (saca del final)', lista.pop());
  describir('resultado', lista);

  console.log('\n   ┌──────────┬───────────┬──────────────────┬────────────────┐');
  console.log('   │ Método   │ Posición  │ Devuelve         │ ¿Muta?         │');
  console.log('   ├──────────┼───────────┼──────────────────┼────────────────┤');
  console.log('   │ push     │ final     │ nuevo length     │ Sí             │');
  console.log('   │ pop      │ final     │ elemento quitado │ Sí             │');
  console.log('   │ unshift  │ inicio    │ nuevo length     │ Sí             │');
  console.log('   │ shift    │ inicio    │ elemento quitado │ Sí             │');
  console.log('   └──────────┴───────────┴──────────────────┴────────────────┘');

  // ------------------------------------------------------------
  sub('3.4 Cola (FIFO) y pila (LIFO)');

  const colaImpresion = [];
  colaImpresion.push('documento-1.pdf');
  colaImpresion.push('documento-2.pdf');
  colaImpresion.push('documento-3.pdf');
  describir('cola de impresión', colaImpresion);
  muestra('se imprime primero (shift)', colaImpresion.shift());
  describir('cola restante', colaImpresion);
  nota('FIFO: push para encolar, shift para desencolar.');

  const historial = [];
  historial.unshift('/inicio');
  historial.unshift('/productos');
  historial.unshift('/productos/42');
  describir('historial (más reciente primero)', historial);
  muestra('página actual', historial[0]);
  muestra('volver atrás (shift)', historial.shift());
  describir('historial tras volver', historial);

  // ------------------------------------------------------------
  sub('3.5 shift/unshift sobre arreglos NO DENSOS');

  const sparse = [, 'b', , 'd'];
  describir('sparse antes', sparse);
  muestra('sparse.shift() (era un hueco)', sparse.shift());
  describir('sparse después', sparse);
  nota('shift devolvió undefined y los huecos se reindexaron.');

  const sparse2 = [1, , 3];
  sparse2.unshift('nuevo');
  describir("sparse2.unshift('nuevo')", sparse2);
  nota('unshift desplaza los huecos, no los rellena.');

  // ------------------------------------------------------------
  sub('3.6 Alternativas INMUTABLES a shift/unshift');

  const original = ['a', 'b', 'c'];

  const sinPrimero = original.slice(1);                 // como shift
  const conNuevoInicio = ['z', ...original];            // como unshift
  const [, ...resto] = original;                        // desestructuración
  const conSpliced = original.toSpliced(0, 1);          // ES2023

  describir('original (intacto)', original);
  describir('original.slice(1)', sinPrimero);
  describir("['z', ...original]", conNuevoInicio);
  describir('const [, ...resto] = original', resto);
  describir('original.toSpliced(0, 1)', conSpliced);
  nota('Ninguna de estas operaciones modificó el arreglo original.');

  // ------------------------------------------------------------
  sub('3.7 Rendimiento: shift es O(n), pop es O(1)');

  const N = 200_000;
  const paraShift = Array.from({ length: N }, (_, i) => i);
  const paraPop = Array.from({ length: N }, (_, i) => i);

  const t1 = performance.now();
  while (paraShift.length) paraShift.shift();
  const t2 = performance.now();
  while (paraPop.length) paraPop.pop();
  const t3 = performance.now();

  muestra(`${N} shift()`, `${(t2 - t1).toFixed(2)} ms`);
  muestra(`${N} pop()`, `${(t3 - t2).toFixed(2)} ms`);
  nota('shift/unshift reindexan todo el arreglo. Para colas grandes usa un índice de cabeza o una lista enlazada.');

  // Cola eficiente con puntero, sin shift():
  class ColaEficiente {
    #datos = [];
    #cabeza = 0;
    encolar(v) { this.#datos.push(v); return this; }
    desencolar() {
      if (this.#cabeza >= this.#datos.length) return undefined;
      const v = this.#datos[this.#cabeza];
      this.#datos[this.#cabeza++] = undefined; // libera la referencia
      if (this.#cabeza > 32 && this.#cabeza * 2 >= this.#datos.length) {
        this.#datos = this.#datos.slice(this.#cabeza); // compacta
        this.#cabeza = 0;
      }
      return v;
    }
    get tamano() { return this.#datos.length - this.#cabeza; }
  }

  const c = new ColaEficiente();
  c.encolar('a').encolar('b').encolar('c');
  muestra('ColaEficiente.desencolar()', c.desencolar());
  muestra('tamaño restante', c.tamano);
}

if (esPrincipal(import.meta.url)) main();
