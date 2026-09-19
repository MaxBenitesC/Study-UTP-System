/**
 * 04 · CLIENTE TCP DE PRUEBA EN NODE.JS
 * Asignatura: JavaScript Avanzado (100000S51T) - Semana 06
 * Especialista Responsable: Max Anderson Benites Corazón
 *
 * Cliente TCP que se conecta al servidor en 127.0.0.1:7000,
 * envía mensajes, recibe las respuestas del servidor y se desconecta.
 */

const net = require('net');

const PORT = 7000;
const HOST = '127.0.0.1';

console.log(`[CLIENTE TCP] Conectando a ${HOST}:${PORT}...`);

const client = net.createConnection({ port: PORT, host: HOST }, () => {
  console.log('[CLIENTE TCP] Conexión establecida con éxito.');
  console.log('[CLIENTE TCP] Enviando mensaje de prueba...');
  client.write('Hola desde el cliente TCP de Max Anderson!\r\n');
});

client.on('data', (data) => {
  console.log(`[CLIENTE TCP] Respuesta recibida del servidor:\n<<<\n${data.toString().trim()}\n>>>`);

  // Si ya recibimos el eco o saludo, cerramos la conexión de forma limpia
  if (data.toString().includes('SERVIDOR TCP ECO')) {
    console.log('[CLIENTE TCP] Enviando señal de finalización (.end())...');
    client.end();
  }
});

client.on('end', () => {
  console.log('[CLIENTE TCP] El servidor confirmó la finalización del flujo.');
});

client.on('close', () => {
  console.log('[CLIENTE TCP] Conexión cerrada.');
  process.exit(0);
});

client.on('error', (err) => {
  console.error('[CLIENTE TCP] Error de conexión:', err.message);
  console.log('Asegúrate de haber levantado primero el servidor: npm run tcp');
  process.exit(1);
});
