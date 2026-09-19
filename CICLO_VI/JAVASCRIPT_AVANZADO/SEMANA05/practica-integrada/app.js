/**
 * SOLUCIÓN INTEGRADA: SEMANA 05 - EVALUACIÓN PRÁCTICA (PC1)
 * Asignatura: JavaScript Avanzado (100000S51T)
 * Especialista Responsable: Max Anderson Benites Corazón (Senior Technical Implementation Specialist)
 *
 * Contenidos demostrados:
 * 1. Manejo de Arreglos (Creación, arreglos densos, .length, .push, .reduce)
 * 2. Operaciones con Cadenas de Texto (slice, substring, indexOf, includes, toUpperCase, toLowerCase)
 * 3. Plantillas Literales e Interpolación multilínea (Template Literals con backticks)
 * 4. Expresiones Regulares (validación con .test, sustitución con .replace, flags i y g)
 * 5. Resolución interactiva del problema del Slide 14 (vector numérico con parada en cero)
 */

// ============================================================================
// 1. ESTADO GLOBAL Y VECTORES DE DATOS
// ============================================================================
// Vector denso de alumnos (creado con la sintaxis literal [])
const vectorAlumnos = [];

// Vector denso para el problema del Slide 14
let vectorNumeros = [];

// Patrones de Expresiones Regulares (RegEx)
const REGEX_SOLO_LETRAS = /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$/;
const REGEX_TIPO_SEGURO = /^(Essalud|EPS)$/i;
const REGEX_ESPACIOS_MULTIPLES = /\s+/g;

// Referencias a elementos del DOM
const formAlumno = document.getElementById('formAlumno');
const nombresInput = document.getElementById('nombres');
const apellidosInput = document.getElementById('apellidos');
const lugarNacimientoInput = document.getElementById('lugarNacimiento');
const direccionInput = document.getElementById('direccion');
const universidadInput = document.getElementById('universidad');
const tipoSeguroInput = document.getElementById('tipoSeguro');

const cuerpoTabla = document.getElementById('cuerpoTabla');
const vectorLengthBadge = document.getElementById('vectorLengthBadge');
const vectorTipoBadge = document.getElementById('vectorTipoBadge');
const resultadoCadenas = document.getElementById('resultadoCadenas');

const btnPrompt = document.getElementById('btnPrompt');
const btnDemo = document.getElementById('btnDemo');

// Elementos del problema Slide 14
const inputNumero = document.getElementById('inputNumero');
const btnAgregarNumero = document.getElementById('btnAgregarNumero');
const btnCargaPromptNumeros = document.getElementById('btnCargaPromptNumeros');
const btnReiniciarNumeros = document.getElementById('btnReiniciarNumeros');
const txtVectorNumeros = document.getElementById('txtVectorNumeros');
const txtCantidadNumeros = document.getElementById('txtCantidadNumeros');
const txtSumaNumeros = document.getElementById('txtSumaNumeros');
const txtEstadoCero = document.getElementById('txtEstadoCero');

// ============================================================================
// 2. FUNCIONES DE VALIDACIÓN Y NORMALIZACIÓN CON REGEX
// ============================================================================
function normalizarEspacios(cadena) {
  // Uso de replace con flag global /g para limpiar espacios duplicados
  return cadena.replace(REGEX_ESPACIOS_MULTIPLES, ' ').trim();
}

function validarCampos(datos) {
  const errores = [];

  if (!REGEX_SOLO_LETRAS.test(datos.nombres)) {
    errores.push('El campo "Nombres" solo debe contener letras del alfabeto y espacios.');
  }

  if (!REGEX_SOLO_LETRAS.test(datos.apellidos)) {
    errores.push('El campo "Apellidos" solo debe contener letras del alfabeto y espacios.');
  }

  if (!REGEX_TIPO_SEGURO.test(datos.tipoSeguro)) {
    errores.push('El tipo de seguro debe ser exclusivamente "Essalud" o "EPS".');
  }

  if (datos.direccion.length < 5) {
    errores.push('La dirección debe tener al menos 5 caracteres.');
  }

  return errores;
}

// ============================================================================
// 3. ANÁLISIS TÉCNICO DE CADENAS (SLIDE 17 Y 18)
// ============================================================================
function generarReporteCadenas(alumno) {
  const nombreCompleto = `${alumno.nombres} ${alumno.apellidos}`;
  const palabras = nombreCompleto.split(' ');

  // Operaciones de extracción y búsqueda
  const extractoSlice = nombreCompleto.slice(0, 7);
  const extractoSubstring = nombreCompleto.substring(0, 7);
  const primeraVocal = nombreCompleto.search(/[aeiouáéíóú]/i);
  const contieneUTP = alumno.universidad.toUpperCase().includes('UTP');
  const indexUniversidad = alumno.universidad.indexOf('Tecnológica');
  const direccionEnmascarada = alumno.direccion.replace(/\d+/g, '***');

  // Plantilla Literal multilínea (Template Literal con backticks)
  const plantillaPresentacion = `Ficha de Registro Oficial:
------------------------------------------
Estudiante : ${nombreCompleto.toUpperCase()}
Origen     : ${alumno.lugarNacimiento}
Residencia : ${alumno.direccion}
Universidad: ${alumno.universidad} (${contieneUTP ? 'Institución UTP verificada' : 'Otra institución'})
Afiliación : Régimen de Seguro ${alumno.tipoSeguro.toUpperCase()}
Total Palabras del Nombre: ${palabras.length}`;

  // Renderizado visual en el DOM
  resultadoCadenas.className = '';
  resultadoCadenas.innerHTML = `
    <div class="ops-grid">
      <div class="op-card">
        <h3>Extracción y Búsqueda</h3>
        <div class="op-row"><strong>Texto analizado:</strong> "${nombreCompleto}"</div>
        <div class="op-row"><strong>.slice(0, 7):</strong> <code>${extractoSlice}</code></div>
        <div class="op-row"><strong>.substring(0, 7):</strong> <code>${extractoSubstring}</code></div>
        <div class="op-row"><strong>.indexOf("Tecnológica"):</strong> <code>${indexUniversidad}</code></div>
        <div class="op-row"><strong>.includes("UTP"):</strong> <code>${contieneUTP}</code></div>
      </div>

      <div class="op-card">
        <h3>Transformación y RegEx</h3>
        <div class="op-row"><strong>.toUpperCase():</strong> <code>${nombreCompleto.toUpperCase()}</code></div>
        <div class="op-row"><strong>.toLowerCase():</strong> <code>${nombreCompleto.toLowerCase()}</code></div>
        <div class="op-row"><strong>Posición 1ª vocal (.search):</strong> <code>Índice ${primeraVocal}</code></div>
        <div class="op-row"><strong>RegEx Replace (Dirección enmascarada):</strong> <code>${direccionEnmascarada}</code></div>
        <div class="op-row"><strong>Validación Seguro (.test):</strong> <code>${REGEX_TIPO_SEGURO.test(alumno.tipoSeguro)}</code></div>
      </div>
    </div>

    <div class="template-literal-box">
      <strong>Salida generada mediante Plantilla Literal (Template Literal con \`...\`):</strong>
      <pre>${plantillaPresentacion}</pre>
    </div>
  `;
}

// ============================================================================
// 4. GESTIÓN DEL ARREGLO Y RENDERIZADO EN TABLA
// ============================================================================
function registrarAlumno(datos) {
  // Validación estricta
  const errores = validarCampos(datos);
  if (errores.length > 0) {
    alert('Errores de validación:\n- ' + errores.join('\n- '));
    return false;
  }

  // Se almacena en el vector denso
  vectorAlumnos.push(datos);

  // Actualización de métricas del arreglo
  actualizarVistaTabla();
  generarReporteCadenas(datos);

  // Registro en consola técnica (DevTools)
  console.group(`Alumno Registrado: ${datos.nombres} ${datos.apellidos}`);
  console.log('Objeto:', datos);
  console.log('Tamaño actual del vector (vectorAlumnos.length):', vectorAlumnos.length);
  console.log('¿Es arreglo denso?:', Object.keys(vectorAlumnos).length === vectorAlumnos.length);
  console.table(vectorAlumnos);
  console.groupEnd();

  return true;
}

function actualizarVistaTabla() {
  vectorLengthBadge.textContent = `Tamaño del vector: ${vectorAlumnos.length}`;
  
  // Verificación de si el arreglo es estrictamente denso (sin huecos)
  const esDenso = vectorAlumnos.every((_, i) => i in vectorAlumnos);
  vectorTipoBadge.textContent = esDenso ? 'Arreglo Denso: Sí' : 'Arreglo Denso: No (Sparse)';
  vectorTipoBadge.className = esDenso ? 'badge badge-success' : 'badge badge-warning';

  if (vectorAlumnos.length === 0) {
    cuerpoTabla.innerHTML = `
      <tr>
        <td colspan="6" class="text-center">No hay registros almacenados en el vector todavía.</td>
      </tr>
    `;
    return;
  }

  cuerpoTabla.innerHTML = vectorAlumnos
    .map((alumno, idx) => `
      <tr>
        <td><strong>[${idx}]</strong></td>
        <td>${alumno.nombres} ${alumno.apellidos}</td>
        <td>${alumno.lugarNacimiento}</td>
        <td>${alumno.direccion}</td>
        <td>${alumno.universidad}</td>
        <td><span class="badge">${alumno.tipoSeguro}</span></td>
      </tr>
    `)
    .join('');
}

// ============================================================================
// 5. EVENTOS DEL FORMULARIO Y ENTRADA DE DATOS
// ============================================================================
formAlumno.addEventListener('submit', (e) => {
  e.preventDefault();

  const alumno = {
    nombres: normalizarEspacios(nombresInput.value),
    apellidos: normalizarEspacios(apellidosInput.value),
    lugarNacimiento: normalizarEspacios(lugarNacimientoInput.value),
    direccion: normalizarEspacios(direccionInput.value),
    universidad: normalizarEspacios(universidadInput.value),
    tipoSeguro: tipoSeguroInput.value,
  };

  if (registrarAlumno(alumno)) {
    formAlumno.reset();
    universidadInput.value = 'Universidad Tecnológica del Perú';
  }
});

// Carga mediante ventanas interactivas prompt() (alternativa clásica de la UTP)
btnPrompt.addEventListener('click', () => {
  const nombres = prompt('Ingrese sus Nombres:', 'Max Anderson');
  if (nombres === null) return;

  const apellidos = prompt('Ingrese sus Apellidos:', 'Benites Corazón');
  if (apellidos === null) return;

  const lugarNacimiento = prompt('Ingrese Lugar de Nacimiento:', 'Chiclayo');
  if (lugarNacimiento === null) return;

  const direccion = prompt('Ingrese Dirección:', 'Av. José Leonardo Ortiz 250');
  if (direccion === null) return;

  const universidad = prompt('Ingrese Universidad:', 'Universidad Tecnológica del Perú');
  if (universidad === null) return;

  const tipoSeguro = prompt('Ingrese Tipo de Seguro (Essalud o EPS):', 'EPS');
  if (tipoSeguro === null) return;

  const alumno = {
    nombres: normalizarEspacios(nombres),
    apellidos: normalizarEspacios(apellidos),
    lugarNacimiento: normalizarEspacios(lugarNacimiento),
    direccion: normalizarEspacios(direccion),
    universidad: normalizarEspacios(universidad),
    tipoSeguro: normalizarEspacios(tipoSeguro),
  };

  registrarAlumno(alumno);
});

// Carga rápida con datos de prueba
btnDemo.addEventListener('click', () => {
  nombresInput.value = 'Max Anderson';
  apellidosInput.value = 'Benites Corazón';
  lugarNacimientoInput.value = 'Chiclayo, Lambayeque';
  direccionInput.value = 'Av. Salaverry 1200, Dpto. 402';
  universidadInput.value = 'Universidad Tecnológica del Perú';
  tipoSeguroInput.value = 'EPS';
});

// ============================================================================
// 6. RESOLUCIÓN DEL PROBLEMA DEL SLIDE 14
// "Crear un vector vacío. Mediante una estructura repetitiva solicitar la
// carga de elementos por teclado hasta que se ingrese el cero. No almacenar
// dicho valor en el vector. Luego sumar todas las componentes del vector,
// mostrar dicha suma y el tamaño del vector."
// ============================================================================
function actualizarPanelNumeros(estado) {
  txtVectorNumeros.textContent = `[ ${vectorNumeros.join(', ')} ]`;
  txtCantidadNumeros.textContent = vectorNumeros.length;
  
  // Suma de componentes usando reduce() o bucle for clásico
  const suma = vectorNumeros.reduce((acum, n) => acum + n, 0);
  txtSumaNumeros.textContent = suma;

  if (estado) {
    txtEstadoCero.textContent = estado;
    if (estado.includes('FINALIZADO')) {
      txtEstadoCero.className = 'badge badge-warning';
    } else {
      txtEstadoCero.className = 'badge badge-success';
    }
  }
}

btnAgregarNumero.addEventListener('click', () => {
  const valorTexto = inputNumero.value.trim();
  if (valorTexto === '') {
    alert('Por favor ingrese un número.');
    return;
  }

  const num = parseFloat(valorTexto);
  if (isNaN(num)) {
    alert('El valor ingresado no es un número válido.');
    return;
  }

  if (num === 0) {
    actualizarPanelNumeros('CERO INGRESADO: Proceso FINALIZADO (no se guardó el 0).');
    inputNumero.disabled = true;
    btnAgregarNumero.disabled = true;
    alert(`Proceso terminado con cero.\n- Elementos: ${vectorNumeros.length}\n- Suma total: ${vectorNumeros.reduce((a, b) => a + b, 0)}`);
  } else {
    vectorNumeros.push(num);
    actualizarPanelNumeros('Agregando componentes...');
  }

  inputNumero.value = '';
  inputNumero.focus();
});

btnCargaPromptNumeros.addEventListener('click', () => {
  let valor;
  do {
    const entrada = prompt('Ingrese un número (ingrese 0 para finalizar el ciclo):', '10');
    if (entrada === null) break; // Usuario canceló

    valor = parseFloat(entrada);
    if (isNaN(valor)) {
      alert('Número no válido, intente de nuevo.');
      continue;
    }

    if (valor !== 0) {
      vectorNumeros.push(valor);
      actualizarPanelNumeros('Elemento agregado vía prompt');
    }
  } while (valor !== 0);

  actualizarPanelNumeros('Carga por prompt FINALIZADA al digitar 0.');
});

btnReiniciarNumeros.addEventListener('click', () => {
  vectorNumeros = [];
  inputNumero.disabled = false;
  btnAgregarNumero.disabled = false;
  inputNumero.value = '';
  actualizarPanelNumeros('Vector reiniciado');
});

// Inicialización
actualizarVistaTabla();
