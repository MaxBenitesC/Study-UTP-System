document.getElementById('formLogin').addEventListener('submit', function (e) {
  e.preventDefault();

  const correo = document.getElementById('correo').value.trim();
  const password = document.getElementById('password').value;
  const usuarioGuardado = localStorage.getItem('usuario');

  if (!usuarioGuardado) {
    alert('No hay ningún usuario registrado. Regístrate ahora.');
    return;
  }

  const usuario = JSON.parse(usuarioGuardado);

  if (correo === usuario.correo && password === usuario.password) {
    localStorage.setItem('isLoggedIn', 'true');
    window.location.href = 'noticias.html';
  } else {
    alert('Correo o contraseña incorrectos.');
  }
});
