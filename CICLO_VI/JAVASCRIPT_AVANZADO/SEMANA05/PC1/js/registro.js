document.getElementById('formRegistro').addEventListener('submit', function (e) {
  e.preventDefault();

  const nombre = document.getElementById('nombre').value.trim();
  const correo = document.getElementById('correo').value.trim();
  const password = document.getElementById('password').value;
  const confirmarPassword = document.getElementById('confirmarPassword').value;
  const regexCorreo = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  const regexPasswordAlfanumerico = /^[a-zA-Z0-9]+$/;

  if (nombre.length < 3) {
    alert('El nombre debe tener al menos 3 caracteres.');
    return;
  }

  if (!regexCorreo.test(correo)) {
    alert('Ingresa un correo electrónico válido.');
    return;
  }

  if (password.length < 6 || !regexPasswordAlfanumerico.test(password)) {
    alert('La contraseña debe tener al menos 6 caracteres y ser alfanumérica.');
    return;
  }

  if (password !== confirmarPassword) {
    alert('Las contraseñas no coinciden.');
    return;
  }

  const usuario = { nombre, correo, password };
  localStorage.setItem('usuario', JSON.stringify(usuario));

  alert('Registro exitoso. Ahora puedes iniciar sesión.');
  window.location.href = 'login.html';
});
