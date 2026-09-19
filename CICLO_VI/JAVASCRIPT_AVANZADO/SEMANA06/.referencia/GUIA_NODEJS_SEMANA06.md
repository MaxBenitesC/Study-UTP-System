# Guía de Laboratorio — Node.js: Módulo OS, Servidores HTTP y TCP
## Asignatura: JavaScript Avanzado (`100000S51T`) — Semana 06 (UTP)
**Docente:** Mtro. Iván Robles Fernández  
**Especialista Responsable:** Max Anderson Benites Corazón (*Senior Technical Implementation Specialist — NCR VOYIX*)  
**Material Oficial de Referencia:** `SEMANA06/MATERIALES/S06_s1 - JavaScript Avanzado - NodeJS.pdf`  

---

## 1. Contexto Teórico (Diapositivas Semana 06)

Node.js es un entorno de ejecución (*runtime*) para JavaScript construido sobre el motor V8 de Google Chrome, orientado a arquitecturas asíncronas dirigidas por eventos y operaciones de E/S no bloqueantes (*Non-blocking I/O*).

En la sesión 06 se abordan tres bloques fundamentales de la API nativa de Node.js:
1. **El módulo `os`:** Inspección de hardware, sistema operativo, memoria y red.
2. **El módulo `http`:** Construcción de servidores web para intercambio de peticiones y respuestas mediante el protocolo HTTP.
3. **El módulo `net`:** Construcción de servidores de bajo nivel basados en sockets TCP (capa de transporte).

---

## 2. Requerimiento 1: Utilizar Métodos del Módulo `os`

* **Script:** [`01-modulo-os.js`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA06/01-modulo-os.js)
* **Comando para ejecutar:**
  ```bash
  npm run os
  # o directamente: node 01-modulo-os.js
  ```

### Métodos oficiales demostrados (Slides 17-22):
| Método | Descripción en diapositiva | Ejemplo de salida |
|---|---|---|
| `os.platform()` | Nombre de la plataforma del sistema operativo (`'linux'`, `'win32'`, `'darwin'`). | `linux` |
| `os.type()` | Nombre del sistema operativo devuelto por el kernel. | `Linux` |
| `os.uptime()` | Tiempo de actividad acumulado del sistema expresado en segundos. | `6152 segundos` |
| `os.arch()` | Arquitectura del procesador (`'x64'`, `'arm64'`, etc.). | `x64` |
| `os.cpus()` | Array de objetos con el modelo, velocidad (MHz) y tiempos de cada núcleo. | 12 núcleos detectados |
| `os.totalmem()` | Cantidad de memoria física RAM total expresada en bytes. | `16447840256 bytes` (~15.32 GB) |
| `os.freemem()` | Memoria RAM física disponible en bytes. | `10163089408 bytes` (~9.47 GB) |
| `os.networkInterfaces()` | Diccionario de interfaces de red con sus familias (IPv4/IPv6) e IPs asignadas. | `lo`, `wlo1` con IP asignada |

---

## 3. Requerimiento 2: Servidor HTTP

* **Script:** [`02-servidor-http.js`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA06/02-servidor-http.js)
* **Comando para ejecutar:**
  ```bash
  npm run http
  # o directamente: node 02-servidor-http.js
  ```

### Implementación exacta del Slide 24:
```javascript
const http = require('http');

const hostname = '127.0.0.1';
const port = 1337;

const server = http.createServer((req, res) => {
  res.writeHead(200, { 'Content-Type': 'text/plain' });
  res.end('Hello World\n');
});

server.listen(port, hostname, () => {
  console.log(`Server running at http://${hostname}:${port}/`);
});
```

### Endpoints adicionales configurados en la solución:
* `GET /`: Devuelve `Hello World\n` en texto plano (exacto a la diapositiva).
* `GET /os`: Devuelve un objeto JSON con métricas del sistema tomadas directamente del módulo `os`.
* `GET /html`: Devuelve una interfaz web visualmente estilizada con el estado del servidor.

### Cómo probarlo:
Desde otra terminal:
```bash
curl http://127.0.0.1:1337/
curl http://127.0.0.1:1337/os
```
O ingresando en el navegador: `http://127.0.0.1:1337/html`

---

## 4. Requerimiento 3: Servidor TCP (Sockets)

* **Script del Servidor:** [`03-servidor-tcp.js`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA06/03-servidor-tcp.js)
* **Script del Cliente de Prueba:** [`04-cliente-tcp.js`](file:///home/ilkay/Documentos/UTP/CICLO_VI/JAVASCRIPT_AVANZADO/SEMANA06/04-cliente-tcp.js)

### Implementación exacta del Slide 25:
```javascript
const net = require('net');

net.createServer((stream) => {
  stream.write('hello\r\n');

  stream.on('end', () => {
    stream.end('goodbye\r\n');
  });
}).listen(7000);
```

### Ciclo de vida y eventos del Socket TCP:
1. **Conexión (`connection`):** El servidor acepta el cliente y emite `'hello\r\n'`.
2. **Flujo de datos (`data`):** Cada paquete recibido se procesa y el servidor devuelve una respuesta en tiempo real (*echo server*).
3. **Fin de transmisión (`end`):** Cuando el cliente termina de enviar datos, el servidor emite `'goodbye\r\n'` y cierra la transmisión.
4. **Cierre (`close`):** Se libera el descriptor de socket y la conexión se da por concluida.

### Cómo probar el Servidor TCP:
1. En la primera terminal, arranca el servidor:
   ```bash
   npm run tcp
   ```
2. En una segunda terminal, puedes probarlo de cualquiera de estas formas:
   * **Con el cliente Node automatizado:**
     ```bash
     npm run tcp:client
     ```
   * **Con Netcat (`nc`):**
     ```bash
     nc 127.0.0.1 7000
     ```
   * **Con Telnet:**
     ```bash
     telnet 127.0.0.1 7000
     ```

---

## 5. Resumen de Archivos Creados

```text
SEMANA06/
├── MATERIALES/
│   └── S06_s1 - JavaScript Avanzado - NodeJS.pdf   # Diapositiva oficial UTP
├── package.json                                    # Scripts automatizados npm
├── 01-modulo-os.js                                 # Requerimiento 1: Métodos de OS
├── 02-servidor-http.js                             # Requerimiento 2: Servidor HTTP (puerto 1337)
├── 03-servidor-tcp.js                              # Requerimiento 3: Servidor TCP (puerto 7000)
├── 04-cliente-tcp.js                               # Cliente de prueba TCP socket
└── GUIA_NODEJS_SEMANA06.md                         # Guía técnica y de sustentación
```
