/**
 * 01 · DEMOSTRACIÓN DEL MÓDULO OS
 * Asignatura: JavaScript Avanzado (100000S51T) - Semana 06
 * Especialista Responsable: Max Anderson Benites Corazón
 *
 * Diapositivas 16 a 22 del material oficial:
 * - os.platform()          -> Plataforma del SO ('linux', 'win32', etc.)
 * - os.type()              -> Nombre del SO ('Linux', 'Windows_NT', etc.)
 * - os.uptime()            -> Tiempo de actividad del sistema en segundos
 * - os.totalmem()          -> Memoria total física en bytes
 * - os.freemem()           -> Memoria libre en bytes
 * - os.arch()              -> Arquitectura del CPU ('x64', 'arm64', etc.)
 * - os.cpus()              -> Array de núcleos de la CPU
 * - os.networkInterfaces() -> Interfaces de red y direcciones IP
 */

const os = require('os');

console.log('╔═══════════════════════════════════════════════════════════════════╗');
console.log('║       DEMOSTRACIÓN TÉCNICA: MÓDULO OS DE NODE.JS (SEMANA 06)       ║');
console.log('╚═══════════════════════════════════════════════════════════════════╝\n');

// 1. Información General del Sistema Operativo (Slides 17-19)
console.log('--- 1. INFORMACIÓN GENERAL DEL SISTEMA ---');
console.log('Plataforma (os.platform()) :', os.platform()); // 'linux', 'win32', 'darwin'
console.log('Tipo de SO (os.type())     :', os.type());     // 'Linux', 'Windows_NT'
console.log('Versión del SO (os.release()):', os.release());
console.log('Hostname (os.hostname())   :', os.hostname());
console.log('Tiempo de actividad (os.uptime()):', `${os.uptime()} segundos (~${(os.uptime() / 3600).toFixed(2)} horas)`);

// 2. Arquitectura y Procesador (Slides 20-21)
console.log('\n--- 2. ARQUITECTURA Y PROCESADOR (CPU) ---');
console.log('Arquitectura de CPU (os.arch()) :', os.arch()); // 'x64', 'arm64'
const cpus = os.cpus();
console.log(`Cantidad de núcleos lógicos     : ${cpus.length}`);
if (cpus.length > 0) {
  console.log(`Modelo del Procesador           : ${cpus[0].model}`);
  console.log(`Velocidad de reloj              : ${cpus[0].speed} MHz`);
}

// 3. Memoria del Sistema (Slide 20)
console.log('\n--- 3. MEMORIA RAM (TOTAL Y LIBRE) ---');
const totalBytes = os.totalmem();
const freeBytes = os.freemem();
const totalGB = (totalBytes / (1024 ** 3)).toFixed(2);
const freeGB = (freeBytes / (1024 ** 3)).toFixed(2);
const usoPorcentaje = (((totalBytes - freeBytes) / totalBytes) * 100).toFixed(2);

console.log(`Memoria Total (os.totalmem())   : ${totalBytes} bytes (${totalGB} GB)`);
console.log(`Memoria Libre (os.freemem())    : ${freeBytes} bytes (${freeGB} GB)`);
console.log(`Uso de Memoria RAM              : ${usoPorcentaje}%`);

// 4. Interfaces de Red (Slide 22)
console.log('\n--- 4. INTERFACES DE RED (os.networkInterfaces()) ---');
const redes = os.networkInterfaces();
for (const [interfaz, lista] of Object.entries(redes)) {
  console.log(`\nInterfaz: [${interfaz}]`);
  lista.forEach((info) => {
    console.log(`  - Familia: ${info.family} | IP: ${info.address} | Interna: ${info.internal}`);
  });
}

console.log('\n✔ Demostración del módulo OS completada con éxito.\n');
