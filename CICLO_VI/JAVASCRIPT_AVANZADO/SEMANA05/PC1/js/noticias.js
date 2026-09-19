const isLoggedIn = localStorage.getItem('isLoggedIn');

if (isLoggedIn !== 'true') {
  alert('Debes iniciar sesión para ver las noticias.');
  window.location.href = 'login.html';
}

const API_KEY = '921b347a55c34d69ab78ccc7a4aa2fd1';
const contenedor = document.getElementById('listaDeNoticias');

document.getElementById('btnCerrarSesion').addEventListener('click', function () {
  localStorage.removeItem('isLoggedIn');
  window.location.href = 'login.html';
})

fetch(`https://newsapi.org/v2/top-headlines?country=us&apiKey=${API_KEY}`)
  .then(response => response.json())
  .then(data => {
    const articles = data.articles;

    articles.forEach(articulo => {
      const div = document.createElement('div');
      div.className = 'noticia';

      div.innerHTML = `
        <h3>${articulo.title ?? ''}</h3>
        <p>${articulo.description ?? ''}</p>
        ${articulo.urlToImage ? `<img src="${articulo.urlToImage}" alt="${articulo.title ?? ''}">` : ''}
        <p><a href="${articulo.url}" target="_blank">Leer noticia completa</a></p>
      `;

      contenedor.appendChild(div);
    });
  })
  .catch(error => {
    contenedor.innerHTML = '<p>Ocurrió un error al cargar las noticias.</p>';
    console.error('Error al obtener noticias:', error);
  });
