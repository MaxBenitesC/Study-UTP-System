/**
 * 03 · SERVIDOR TCP EN NODE.JS
 * Asignatura: JavaScript Avanzado (100000S51T) - Semana 06
 * Especialista Responsable: Max Anderson Benites Corazón
 *
 * Implementación basada en el Slide 25 de la diapositiva oficial:
 * Utiliza el módulo nativo 'net' para crear un servidor socket TCP
 * que escucha en el puerto 7000.
 */

const net = require('net');

const PORT = 7000;
const HOST = '127.0.0.1';

const server = net.createServer((stream) => {
  const clienteInfo = `${stream.remoteAddress}:${stream.remotePort}`;
  console.log(`[TCP SERVER] Cliente conectado desde: ${clienteInfo}`);

  // 1. Enviar mensaje de bienvenida exacto del Slide 25
  stream.write('hello\r\n');

  // 2. Manejo de recepción de datos (Echo de lo que el cliente envíe)
  stream.on('data', (data) => {
    const mensaje = data.toString().trim();
    console.log(`[TCP SERVER] Datos recibidos de ${clienteInfo}: "${mensaje}"`);

    // Responder con confirmación y eco
    stream.write(`[SERVIDOR TCP ECO]: Recibido -> ${mensaje}\r\n`);
  });

  // 3. Manejo de fin de transmisión exacto del Slide 25
  stream.on('end', () => {
    console.log(`[TCP SERVER] Cliente ${clienteInfo} finalizó transmisión (end).`);
    stream.end('goodbye\r\n');
  });

  // 4. Manejo de cierre y errores para estabilidad
  stream.on('close', () => {
    console.log(`[TCP SERVER] Conexión cerrada con ${clienteInfo}.`);
  });

  stream.on('error', (err) => {
    console.error(`[TCP SERVER] Error en socket ${clienteInfo}:`, err.message);
  });
});

server.listen(PORT, HOST, () => {
  console.log('╔═══════════════════════════════════════════════════════════════════╗');
  console.log(`║   SERVIDOR TCP ACTIVO ESCUCHANDO EN: ${HOST}:${PORT}           ║`);
  console.log('╚═══════════════════════════════════════════════════════════════════╝');
  console.log('Para conectarse y probar desde otra terminal:');
  console.log(`  Opcion A (Node Client) : npm run tcp:client   (o node 04-cliente-tcp.js)`);
  console.log(`  Opcion B (Netcat)      : nc ${HOST} ${PORT}`);
  console.log(`  Opcion C (Telnet)      : telnet ${HOST} ${PORT}`);
  console.log('\nPresiona Ctrl+C para detener el servidor.\n');
});
