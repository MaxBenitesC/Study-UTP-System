# CODEX.MD - Memoria del Workspace

Workspace: `TALLER_DE_PROGRAMACION_WEB`
Curso: Taller de Programacion Web - UTP, Ciclo V
Estudiante: Max Anderson Benites Corazon
Proyecto principal: `ChapaTuPromo`
Fecha de memoria: 2026-07-01

## Estado general

El workspace esta organizado por semanas del curso. `ChapaTuPromo` es el proyecto principal y ya tiene APF1 y APF2 entregados. La entrega APF2 cerrada esta en:

```text
SEMANA_10/ChapaTuPromo/
```

No modificar la entrega final de `SEMANA_10` salvo que el usuario lo pida. Para APF3 se creo una copia nueva en:

```text
SEMANA_15/ChapaTuPromo_Final/
```

Estructura actual de APF3:

```text
SEMANA_15/ChapaTuPromo_Final/
├── backend/
│   ├── .env
│   ├── database.js
│   ├── package.json
│   ├── package-lock.json
│   ├── schema.sql
│   ├── seed.sql
│   ├── server.js
│   └── node_modules/
└── frontend/
    ├── index.html
    ├── login_chapatupromo.html
    ├── registrar_chapatupromo.html
    ├── menu_chapatupromo.html
    ├── formulario_chapatupromo.html
    ├── pedidos_chapatupromo.html
    ├── ofertas_chapatupromo.html
    ├── css/estilos_chapatupromo.css
    └── logo/chapatupromo.png
```

El usuario decidio mantener los nombres de HTML originales porque ya fueron revisados en APF1/APF2 y obtuvo 20. No renombrar a `login.html`, `menu.html`, etc.

## Materiales convertidos

Se crearon carpetas y conversiones de semanas 11 a 15.

Materiales JS:

```text
SEMANA_11/S11-INTRODUCCION_A_JAVASCRIPT.md
SEMANA_12/S12_s12_-Introduccion_a_JavaScript_II_pptx.md
SEMANA_13/S13_s13_-Introduccion_al_DOM.md
SEMANA_14/S14_s14_-Menu_responsivo_y_ventanas_flotantes.md
```

Rubrica APF3:

```text
SEMANA_15/APF 3 - 2026.pdf
SEMANA_15/APF_3_2026.md
SEMANA_15/APF_3_2026_page-1.png
```

`SEMANA_15/APF_3_2026.md` fue ampliado con apuntes del video/clase. Leerlo antes de continuar APF3.

## Interpretacion APF3 segun PDF y video

La docente recalco que APF3 se centra en:

- JavaScript en todas las 7 paginas principales.
- Conexion a base de datos relacional.
- HTML, CSS y JavaScript sin Bootstrap ni frameworks.
- Validaciones basicas en formularios.
- Eventos como `onclick`, `onchange`, botones, selects, alertas.
- DOM para modificar texto, contenido, estilos o visibilidad.
- Evidenciar variables, constantes, funciones, condicionales, arreglos y/o repetitivas.
- Capturas del codigo JavaScript de cada vista y breve explicacion.
- Capturas de conexion a BD y consultas SQL ejecutadas o invocadas desde backend.

Notas importantes del video:

- `DELETE` no es obligatorio.
- No hace falta forzar CRUD completo.
- Si se compra 1 producto de stock 5, el stock debe actualizarse a 4.
- Carrito puede ser temporal en JavaScript.
- El pedido se crea en BD recien al confirmar compra/pago.
- No permitir comprar si el carrito esta vacio.
- Verificar si usuario existe en BD.
- Crear usuario en BD.
- Crear pedido y actualizar estado de pedido.
- Validar nombres: solo caracteres alfabeticos, no numeros ni caracteres especiales.
- Validar formato de correo.
- Se puede implementar modo claro/modo oscuro o cambios de estilo con `select`.
- En los videos revisados no aparecio `fetch`, JSON o API explicitamente, pero se justifica porque el backend Node.js necesita comunicarse con el frontend.

## Nivel de JavaScript del curso

El nivel visto es basico universitario:

- Semana 11: `let`, `const`, funciones, `alert`, `console.log`, `onclick`, `onchange`, lectura de inputs con `document.getElementById(...).value`.
- Semana 12: sintaxis, objetos, acceso a propiedades, `if/else`, arreglos, `for`, `forEach`, metodos como `push`, `pop`, `shift`, `unshift`, `join`.
- Semana 13: DOM, `document`, `getElementById`, `querySelector`, modificar estilos, insertar datos en un `div` sin recargar.
- Semana 14: menu responsivo, modal, eventos DOM, manipulacion de visibilidad/estilos.

Estrategia de implementacion: mantener JS explicable con funciones simples, eventos, DOM, validaciones y arreglos. Usar `fetch` solo como puente necesario entre frontend y backend.

## Decision tecnica APF3

El usuario eligio:

- Base de datos: PostgreSQL, para diferenciarse de SQLite/MySQL/SQL Server usados por otros.
- Backend: Node.js + Express.
- Gestor: npm.
- Editor: nvim.
- Desarrollo pedagogico: el usuario escribe y ejecuta; Codex guia, explica y corrige.

Arquitectura:

```text
frontend HTML/CSS/JS
        |
        | fetch()
        v
backend Node.js + Express
        |
        | SQL con pg
        v
PostgreSQL
```

Explicacion clave:

- PostgreSQL guarda datos reales.
- Node.js ejecuta JavaScript en backend.
- Express crea rutas API.
- `pg` conecta Node.js con PostgreSQL.
- `dotenv` carga `.env`.
- `cors` permite llamadas desde frontend.
- `fetch` pedira datos al backend.
- DOM mostrara datos y mensajes en cada HTML.

## Base de datos PostgreSQL

La base ya fue creada y validada.

Credenciales actuales:

```text
Base de datos: chapatupromo
Usuario: chapatupromo_user
Password: chapatupromo123
Host: localhost
Puerto: 5432
```

`.env` actual:

```env
PORT=3000
PGHOST=localhost
PGPORT=5432
PGDATABASE=chapatupromo
PGUSER=chapatupromo_user
PGPASSWORD=chapatupromo123
```

Tablas creadas:

```text
usuarios
productos
pedidos
detalle_pedido
```

`schema.sql` simplificado:

- `usuarios`: registro/login.
- `productos`: datos reales del inventario: nombre, categoria, descripcion, fecha_vencimiento, precio_original, stock, estado.
- `pedidos`: cabecera del pedido.
- `detalle_pedido`: productos incluidos en cada pedido.

`seed.sql` carga 13 productos reales del menu.

Fechas actuales para demo al 2026-07-01:

```text
2026-07-01 -> Hoy
2026-07-02 -> Mañana
2026-07-03 -> En 2 dias
2026-07-04 -> En 3 dias
```

Comandos usados/validos:

```bash
psql -h localhost -U chapatupromo_user -d chapatupromo -f schema.sql
psql -h localhost -U chapatupromo_user -d chapatupromo -f seed.sql
psql -h localhost -U chapatupromo_user -d chapatupromo
```

Consultas de validacion:

```sql
\dt
SELECT COUNT(*) AS total_productos FROM productos;
SELECT id, nombre, categoria, fecha_vencimiento, precio_original, stock, estado
FROM productos
ORDER BY fecha_vencimiento, categoria;
```

Estado validado:

- Las 4 tablas existen.
- `productos` tiene 13 filas.
- El usuario `chapatupromo_user` es dueño de las tablas.

## Backend Node.js actual

Dependencias instaladas:

```text
express
pg
dotenv
cors
```

`package.json` usa:

```json
"scripts": {
  "start": "node server.js",
  "dev": "node server.js"
}
```

Comando para iniciar:

```bash
cd SEMANA_15/ChapaTuPromo_Final/backend
npm start
```

Tambien equivale a:

```bash
node server.js
```

Archivos:

- `database.js`: importa `Pool` desde `pg`, carga `.env`, crea conexion a PostgreSQL y exporta `db`.
- `server.js`: configura Express, CORS, JSON, rutas y logica de productos.

Rutas ya implementadas:

```text
GET /
GET /api/prueba-bd
GET /api/productos
```

Validaciones realizadas:

```bash
curl http://localhost:3000/
curl http://localhost:3000/api/prueba-bd
curl http://localhost:3000/api/productos
```

`GET /api/productos` ya funciona y devuelve JSON con:

- Datos originales de PostgreSQL: `id`, `nombre`, `categoria`, `descripcion`, `fecha_vencimiento`, `precio_original`, `stock`, `estado`.
- Datos calculados en Node.js: `dias_para_vencer`, `descuento_porcentaje`, `precio_chapatupromo`, `mensaje_vencimiento`.

Funciones actuales en `server.js`:

- `calcularDias(fechaVencimiento)`
- `calcularDescuento(dias)`
- `obtenerMensajeVencimiento(dias)`
- `prepararProducto(producto)`

Decision: el descuento y precio ChapaTuPromo se calculan en Node.js para dar protagonismo a JavaScript. PostgreSQL solo guarda datos reales.

Regla de descuento:

```text
dias 0 -> 50%
dias 1 -> 40%
dias 2 -> 30%
dias 3 -> 20%
otro  -> 0%
```

## Conceptos explicados al usuario

El usuario ya recibio explicacion y practica sobre:

- `npm start` y `package.json`.
- `server.js` vs `database.js`.
- `schema.sql` y `seed.sql`.
- `require`.
- `dotenv`.
- `const app = express()`.
- `app.use(cors())`.
- `app.use(express.json())`.
- `app.get("/")`.
- `app.get("/api/productos")`.
- `app.listen(PORT)`.
- `req` y `res`.
- `await db.query(...)`.
- `resultado.rows`.
- `map`.
- `...producto` spread operator.
- `==` vs `===`.
- Funciones solo se ejecutan cuando son llamadas.

Puntos que el usuario entendio bien:

- `npm start` ejecuta el script `start` de `package.json`, que llama `node server.js`.
- `app.get` crea una ruta GET.
- `resultado.rows` es un arreglo con filas del `SELECT`.
- `map` transforma cada producto.
- `prepararProducto` conserva datos originales con `...producto` y agrega campos calculados.
- Calcular descuento en Node.js tiene sentido porque el curso es de JavaScript.

Puntos a reforzar luego:

- `req`/`res` con ejemplos `POST`.
- `express.json()` para leer `req.body`.
- `await` y asincronia de forma simple.
- `fetch` en frontend.

## Proximo paso al retomar

Continuar con backend antes de tocar frontend.

Siguiente ruta recomendada:

```text
POST /api/usuarios
```

Objetivo:

- Registrar usuario.
- Ejecutar `INSERT INTO usuarios`.
- Capturar error de correo duplicado.
- Probar con `curl`.
- Luego conectar con `registrar_chapatupromo.html`.

Ruta posterior:

```text
POST /api/login
```

Objetivo:

- Verificar usuario existente con `SELECT`.
- Validar correo/password contra PostgreSQL.

Luego:

```text
POST /api/pedidos
GET /api/pedidos
PUT /api/pedidos/:id/estado
```

No implementar `DELETE` salvo que el usuario cambie de decision.

## Plan frontend pendiente

Crear:

```text
SEMANA_15/ChapaTuPromo_Final/frontend/js/
├── inicio.js
├── registrar.js
├── login.js
├── menu.js
├── formulario_pedido.js
├── pedidos.js
└── ofertas.js
```

Cada una de las 7 paginas debe enlazar su JS al final del `body`.

Alcance por pagina:

- `index.html`: modo claro/oscuro o cambio visual dinamico, resumen de productos/ofertas.
- `registrar_chapatupromo.html`: validar nombre alfabetico, correo, password; crear usuario.
- `login_chapatupromo.html`: validar campos; verificar usuario en BD.
- `menu_chapatupromo.html`: listar productos desde BD; agregar al carrito temporal.
- `formulario_chapatupromo.html`: validar datos; no permitir carrito vacio; confirmar pedido.
- `pedidos_chapatupromo.html`: listar pedidos; actualizar estado.
- `ofertas_chapatupromo.html`: mostrar ofertas por cercania de vencimiento y estilos por urgencia.

## Convenciones de trabajo

- Trabajar en espanol.
- El usuario quiere aprender: explicar antes/despues de cada bloque.
- El usuario escribe el codigo y ejecuta comandos; Codex guia y revisa.
- Mantener nivel basico universitario.
- No sobreingenierizar.
- Mantener nombres HTML originales.
- No usar frameworks.
- Priorizar claridad para exposicion.
