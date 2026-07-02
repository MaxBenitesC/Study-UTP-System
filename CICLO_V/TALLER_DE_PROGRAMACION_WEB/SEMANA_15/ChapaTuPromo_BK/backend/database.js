const { Pool } = require("pg");
require("dotenv").config();

const db = new Pool({
  host: process.env.PGHOST,
  port: process.env.PGPORT,
  database: process.env.PGDATABASE,
  user: process.env.PGUSER,
  password: process.env.PGPASSWORD
});

db.connect()
  .then((client) => {
    console.log("Base de datos PostgreSQL conectada correctamente");
    client.release();
  })
  .catch((error) => {
    console.log("Error al conectar con PostgreSQL:");
    console.log(error.message);
  });

module.exports = db;

