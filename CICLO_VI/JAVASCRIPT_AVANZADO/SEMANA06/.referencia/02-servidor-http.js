/**
 * 02 · SERVIDOR HTTP EN NODE.JS
 * Asignatura: JavaScript Avanzado (100000S51T) - Semana 06
 * Especialista Responsable: Max Anderson Benites Corazón
 *
 * Implementación basada en el Slide 24 de la diapositiva oficial:
 * Utiliza el módulo nativo 'http' para levantar un servidor web
 * escuchando en 127.0.0.1:1337.
 */

const http = require('http');
const os = require('os');

const hostname = '127.0.0.1';
const port = 1337;

const server = http.createServer((req, res) => {
  const url = req.url;
  const timestamp = new Date().toLocaleTimeString();
  console.log(`[${timestamp}] Petición recibida: ${req.method} ${url}`);

  if (url === '/' || url === '') {
    // 1. Respuesta base exacta de la diapositiva oficial (Slide 24)
    res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });
    res.end('Hello World\nServidor HTTP con Node.js en funcionamiento.\n');
  } else if (url === '/os') {
    // 2. Endpoint integrado que entrega métricas del módulo OS en formato JSON
    const datosOS = {
      plataforma: os.platform(),
      tipo: os.type(),
      arquitectura: os.arch(),
      nucleosCPU: os.cpus().length,
      memoriaTotalGB: (os.totalmem() / (1024 ** 3)).toFixed(2),
      memoriaLibreGB: (os.freemem() / (1024 ** 3)).toFixed(2),
      uptimeSegundos: os.uptime(),
      especialista: 'Max Anderson Benites Corazón'
    };
    res.writeHead(200, { 'Content-Type': 'application/json; charset=utf-8' });
    res.end(JSON.stringify(datosOS, null, 2));
  } else if (url === '/html') {
    // 3. Respuesta HTML visual para navegador
    res.writeHead(200, { 'Content-Type': 'text/html; charset=utf-8' });
    res.end(`
      <!DOCTYPE html>
      <html>
      <head>
        <meta charset="utf-8">
        <title>Servidor HTTP Node.js - Semana 06</title>
        <style>
          body { font-family: sans-serif; background: #0f172a; color: #f8fafc; padding: 2rem; }
          .card { background: #1e293b; padding: 1.5rem; border-radius: 8px; max-width: 600px; margin: auto; border: 1px solid #334155; }
          h1 { color: #38bdf8; font-size: 1.5rem; }
          code { background: #334155; padding: 0.2rem 0.4rem; border-radius: 4px; }
          a { color: #38bdf8; }
        </style>
      </head>
      <body>
        <div class="card">
          <h1>Servidor HTTP en Node.js (Semana 06)</h1>
          <p>Especialista: <strong>Max Anderson Benites Corazón</strong></p>
          <hr style="border: 0; border-top: 1px solid #334155; margin: 1rem 0;">
          <p>Endpoints disponibles:</p>
          <ul>
            <li><a href="/">/</a> &rarr; Texto plano Hello World (Slide 24)</li>
            <li><a href="/os">/os</a> &rarr; Métricas del sistema en JSON (Módulo OS)</li>
            <li><a href="/html">/html</a> &rarr; Esta vista gráfica</li>
          </ul>
        </div>
      </body>
      </html>
    `);
  } else {
    // Ruta no encontrada 404
    res.writeHead(404, { 'Content-Type': 'text/plain; charset=utf-8' });
    res.end('404 Not Found - Ruta no encontrada en el servidor.\n');
  }
});

server.listen(port, hostname, () => {
  console.log('╔═══════════════════════════════════════════════════════════════════╗');
  console.log(`║   SERVIDOR HTTP ACTIVO: http://${hostname}:${port}/             ║`);
  console.log('╚═══════════════════════════════════════════════════════════════════╝');
  console.log(`Prueba en otra terminal: curl http://${hostname}:${port}/`);
  console.log(`Prueba endpoint OS     : curl http://${hostname}:${port}/os`);
  console.log('Presiona Ctrl+C para detener el servidor.\n');
});
