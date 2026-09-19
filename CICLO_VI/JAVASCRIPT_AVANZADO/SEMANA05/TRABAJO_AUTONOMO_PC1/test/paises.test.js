import test from "node:test";
import assert from "node:assert/strict";
import { cargarPaises, normalizarPais, URL_PAISES } from "../src/api/paises.js";

test("normaliza los campos que compartirá todo el equipo", () => {
  const origen = {
    alpha3Code: "AFG",
    name: "Afghanistan",
    capital: "Kabul",
    region: "Asia",
    population: 40218234,
    area: 652230,
    latlng: [33, 65],
    flags: { png: "https://example.test/afg.png" },
  };

  assert.deepEqual(normalizarPais(origen), {
    codigo: "AFG",
    nombre: "Afghanistan",
    capital: "Kabul",
    region: "Asia",
    poblacion: 40218234,
    superficie: 652230,
    coordenadas: [33, 65],
    banderaUrl: "https://example.test/afg.png",
  });
  assert.deepEqual(origen.latlng, [33, 65]);
});

test("conserva como null los datos opcionales ausentes", () => {
  const pais = normalizarPais({
    alpha3Code: "XYZ",
    name: "Ejemplo",
    region: "Asia",
    population: 0,
  });

  assert.equal(pais.capital, null);
  assert.equal(pais.superficie, null);
  assert.equal(pais.coordenadas, null);
  assert.equal(pais.banderaUrl, null);
});

test("hace una consulta y rechaza un error de API aunque HTTP sea 200", async (t) => {
  let llamadas = 0;
  t.mock.method(globalThis, "fetch", async (url) => {
    llamadas += 1;
    assert.equal(url, URL_PAISES);
    return { ok: true, json: async () => ({ success: false, errors: [] }) };
  });

  await assert.rejects(cargarPaises(), /lista de países válida/);
  assert.equal(llamadas, 1);
});

test("entrega un único array normalizado tras la carga", async (t) => {
  t.mock.method(globalThis, "fetch", async () => ({
    ok: true,
    json: async () => [
      { alpha3Code: "AFG", name: "Afghanistan", region: "Asia", population: 1 },
    ],
  }));

  const paises = await cargarPaises();
  assert.equal(paises.length, 1);
  assert.equal(paises[0].codigo, "AFG");
});
