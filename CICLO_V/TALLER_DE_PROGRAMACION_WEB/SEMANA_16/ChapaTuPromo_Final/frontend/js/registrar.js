const formularioRegistro = document.getElementById("formulario-registro");

function mostrarMensajeRegistro(texto, tipo) {
  const mensaje = document.getElementById("mensaje-registro");
  mensaje.textContent = texto;

  if (tipo === "error") {
    mensaje.className = "mensaje-formulario mensaje-error";
  } else {
    mensaje.className = "mensaje-formulario mensaje-exito";
  }
}

function validarRegistro() {
  const nombre = document.getElementById("nombre").value.trim();
  const correo = document.getElementById("correo").value.trim();
  const telefono = document.getElementById("telefono").value.trim();
  const password = document.getElementById("contrasena").value;
  const confirmar = document.getElementById("confirmar").value;

  const soloLetras = /^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$/;
  const correoValido = /^[A-Za-z0-9][A-Za-z0-9._-]*@[A-Za-z0-9-]+\.[A-Za-z]{2,}$/;
  const celularValido = /^9[0-9]{8}$/;

  if (nombre === "" || correo === "" || telefono === "" || password === "" || confirmar === "") {
    mostrarMensajeRegistro("Completa todos los campos.", "error");
    alert("Completa todos los campos");
    return false;
  }

  if (!soloLetras.test(nombre)) {
    mostrarMensajeRegistro("El nombre solo debe tener letras y espacios.", "error");
    alert("Nombre invalido");
    return false;
  }

  if (!correoValido.test(correo)) {
    mostrarMensajeRegistro("Ingresa un correo valido.", "error");
    alert("Correo invalido");
    return false;
  }

  if (!celularValido.test(telefono)) {
    mostrarMensajeRegistro("El celular debe empezar en 9 y tener 9 digitos.", "error");
    alert("Celular invalido");
    return false;
  }

  if (password.length < 6) {
    mostrarMensajeRegistro("La contrasena debe tener minimo 6 caracteres.", "error");
    alert("Contrasena muy corta");
    return false;
  }

  if (password !== confirmar) {
    mostrarMensajeRegistro("Las contrasenas no coinciden.", "error");
    alert("Las contrasenas no coinciden");
    return false;
  }

  return true;
}

function registrarUsuario(evento) {
  evento.preventDefault();

  if (!validarRegistro()) {
    return;
  }

  const datosUsuario = {
    nombre: document.getElementById("nombre").value.trim(),
    correo: document.getElementById("correo").value.trim(),
    password: document.getElementById("contrasena").value,
    telefono: document.getElementById("telefono").value.trim(),
    direccion: ""
  };

  fetch(API_URL + "/api/usuarios", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(datosUsuario)
  })
    .then((respuesta) => respuesta.json())
    .then((datos) => {
      if (datos.usuario) {
        mostrarMensajeRegistro(datos.mensaje, "exito");
        alert("Usuario registrado correctamente");
        window.location.href = "login_chapatupromo.html";
      } else {
        mostrarMensajeRegistro(datos.mensaje || "No se pudo registrar el usuario.", "error");
      }
    })
    .catch((error) => {
      mostrarMensajeRegistro("No se pudo conectar con el backend.", "error");
      console.log(error);
    });
}

formularioRegistro.addEventListener("submit", registrarUsuario);
