const formularioLogin = document.getElementById("formulario-login");

function mostrarMensajeLogin(texto, tipo) {
  const mensaje = document.getElementById("mensaje-login");
  mensaje.textContent = texto;

  if (tipo === "error") {
    mensaje.className = "mensaje-formulario mensaje-error";
  } else {
    mensaje.className = "mensaje-formulario mensaje-exito";
  }
}

function iniciarSesion(evento) {
  evento.preventDefault();

  const correo = document.getElementById("usuario").value.trim();
  const password = document.getElementById("contrasena").value;
  const correoValido = /^[A-Za-z0-9][A-Za-z0-9._-]*@[A-Za-z0-9-]+\.[A-Za-z]{2,}$/;

  if (correo.length === 0 || password.length === 0) {
    mostrarMensajeLogin("Completa tu correo y contrasena.", "error");
    alert("Completa todos los campos");
    return;
  }

  if (!correoValido.test(correo)) {
    mostrarMensajeLogin("Ingresa un correo valido.", "error");
    alert("Correo invalido");
    return;
  }

  fetch(API_URL + "/api/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      correo: correo,
      password: password
    })
  })
    .then((respuesta) => respuesta.json())
    .then((datos) => {
      if (datos.usuario) {
        localStorage.setItem("usuario_chapatupromo", JSON.stringify(datos.usuario));
        localStorage.removeItem("carrito_chapatupromo");
        mostrarMensajeLogin("Inicio de sesion correcto. Bienvenido " + datos.usuario.nombre, "exito");
        alert("Inicio de sesion correcto");
        window.location.href = "index.html";
      } else {
        mostrarMensajeLogin(datos.mensaje || "Correo o contrasena incorrectos.", "error");
      }
    })
    .catch((error) => {
      mostrarMensajeLogin("No se pudo conectar con el backend.", "error");
      console.log(error);
    });
}

formularioLogin.addEventListener("submit", iniciarSesion);


