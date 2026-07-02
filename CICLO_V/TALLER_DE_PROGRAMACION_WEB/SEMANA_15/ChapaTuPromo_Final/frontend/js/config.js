const API_URL = "http://localhost:3000";

function aplicarTemaChapaTuPromo() {
  const tema = localStorage.getItem("tema_chapatupromo") || "claro";

  if (tema === "oscuro") {
    document.body.classList.add("modo-oscuro");
  } else {
    document.body.classList.remove("modo-oscuro");
  }
}

function guardarTemaChapaTuPromo(tema) {
  localStorage.setItem("tema_chapatupromo", tema);
  aplicarTemaChapaTuPromo();
}

function mostrarUsuarioHeader() {
  const usuario = JSON.parse(localStorage.getItem("usuario_chapatupromo")) || null;
  const header = document.querySelector("header");

  if (!usuario || !header) {
    return;
  }

  const cajaUsuario = document.createElement("div");
  cajaUsuario.className = "usuario-header";
  cajaUsuario.innerHTML = `
    <span>Usuario: ${usuario.nombre}</span>
    <button type="button" id="cerrar-sesion">Cerrar sesion</button>
  `;

  header.appendChild(cajaUsuario);

  document.getElementById("cerrar-sesion").addEventListener("click", () => {
    localStorage.removeItem("usuario_chapatupromo");
    localStorage.removeItem("carrito_chapatupromo");
    alert("Sesion cerrada");
    window.location.href = "index.html";
  });
}

function mostrarSelectorTemaGlobal() {
  const nav = document.querySelector("nav");
  if (!nav) return;

  const divControl = document.createElement("div");
  divControl.style.marginLeft = "auto"; 
  divControl.style.display = "flex";
  divControl.style.alignItems = "center";
  divControl.style.gap = "10px";
  
  divControl.innerHTML = `
    <label for="tema-global" style="color: var(--blanco-puro); font-weight: 600; font-size: 14px;">Apariencia:</label>
    <select id="tema-global" style="padding: 4px; border-radius: 4px; border: none;">
      <option value="claro">Modo Claro</option>
      <option value="oscuro">Modo Oscuro</option>
    </select>
  `;

  nav.appendChild(divControl);

  const selectTema = document.getElementById("tema-global");
  const temaActual = localStorage.getItem("tema_chapatupromo") || "claro";
  selectTema.value = temaActual;

  selectTema.addEventListener("change", (e) => {
    guardarTemaChapaTuPromo(e.target.value);
  });
}

aplicarTemaChapaTuPromo();
mostrarUsuarioHeader();
mostrarSelectorTemaGlobal();
