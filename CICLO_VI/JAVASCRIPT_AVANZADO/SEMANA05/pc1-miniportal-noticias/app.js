/**
 * ============================================================================
 * MiniPortal de Noticias — JavaScript Avanzado (PC1)
 * Autor: Max Anderson Benites Corazón (Senior Technical Implementation Specialist)
 * Entorno: UTP Ciclo VI — Evaluación Práctica
 * ============================================================================
 */

'use strict';

// ----------------------------------------------------------------------------
// 1. Constantes y Expresiones Regulares (RegEx) para Validaciones
// ----------------------------------------------------------------------------
const REGEX_RULES = {
  // Nombre completo: mínimo 3 caracteres, letras y espacios (permite tildes y diacríticos)
  fullName: /^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]{3,60}$/,
  // Correo electrónico: estructura estándar de email RFC 5322 simplificada
  email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/,
  // Contraseña: mínimo 6 caracteres, alfanumérica (debe contener letras y números)
  password: /^(?=.*[a-zA-Z])(?=.*\d)[a-zA-Z\d!@#$%^&*()_+={}\[\]:;"'<>,.?/-]{6,}$/
};

const STORAGE_KEYS = {
  USERS: 'portal_news_users',
  SESSION: 'portal_news_session',
  API_KEY: 'portal_newsapi_key'
};

// Dataset de contingencia (Fallback Mock)
// Se activa si NewsAPI no tiene key o si la política CORS del navegador bloquea peticiones client-side
const FALLBACK_NEWS = [
  {
    title: 'Avances en Inteligencia Artificial Generativa transforman la computación en la nube',
    description: 'Nuevos modelos de lenguaje y agentes autónomos están siendo integrados directamente en infraestructuras distribuidas para optimizar flujos de despliegue.',
    source: { name: 'Tech Innovations' },
    urlToImage: 'https://images.unsplash.com/photo-1677442136019-21780efad99a?auto=format&fit=crop&w=800&q=80',
    url: 'https://news.google.com',
    publishedAt: new Date(Date.now() - 3600000).toISOString()
  },
  {
    title: 'Novedades de ECMAScript: Optimizaciones en motores V8 y nuevas API del DOM',
    description: 'El comité TC39 introduce mejoras en la administración de memoria para arrays densos y estructuras asíncronas de alto rendimiento.',
    source: { name: 'JS Weekly' },
    urlToImage: 'https://images.unsplash.com/photo-1579468118864-1b9ea3c0db4a?auto=format&fit=crop&w=800&q=80',
    url: 'https://developer.mozilla.org',
    publishedAt: new Date(Date.now() - 7200000).toISOString()
  },
  {
    title: 'Transformación de Sistemas POS y Pagos Electrónicos en el Sector Retail',
    description: 'La adopción de arquitecturas orientadas a eventos y conectividad segura acelera la modernización de cajas y terminales inteligentes.',
    source: { name: 'Retail Tech Trends' },
    urlToImage: 'https://images.unsplash.com/photo-1556742049-0a67c5574f73?auto=format&fit=crop&w=800&q=80',
    url: 'https://news.google.com',
    publishedAt: new Date(Date.now() - 14400000).toISOString()
  },
  {
    title: 'Ciberseguridad y Autenticación sin Contraseñas: El auge de las Passkeys',
    description: 'Organizaciones globales aceleran la transición hacia estándares FIDO2 para erradicar ataques de phishing y robo de credenciales.',
    source: { name: 'Security Digest' },
    urlToImage: 'https://images.unsplash.com/photo-1563986768609-322da13575f3?auto=format&fit=crop&w=800&q=80',
    url: 'https://news.google.com',
    publishedAt: new Date(Date.now() - 21600000).toISOString()
  },
  {
    title: 'Arquitecturas Serverless y Edge Computing: Eficiencia y Reducción de Latencia',
    description: 'El despliegue de micro-funciones en los bordes de la red promete respuestas en milisegundos para aplicaciones móviles y analítica en tiempo real.',
    source: { name: 'Cloud Architecture' },
    urlToImage: 'https://images.unsplash.com/photo-1451187580459-43490279c0fa?auto=format&fit=crop&w=800&q=80',
    url: 'https://news.google.com',
    publishedAt: new Date(Date.now() - 28800000).toISOString()
  },
  {
    title: 'Sostenibilidad y Computación Verde: Centros de Datos Reducen Huella de Carbono',
    description: 'Iniciativas de enfriamiento líquido y uso de energías renovables marcan la pauta para la infraestructura tecnológica hacia el 2030.',
    source: { name: 'Green Computing' },
    urlToImage: 'https://images.unsplash.com/photo-1498050108023-c5249f4df085?auto=format&fit=crop&w=800&q=80',
    url: 'https://news.google.com',
    publishedAt: new Date(Date.now() - 36000000).toISOString()
  }
];

// ----------------------------------------------------------------------------
// 2. Elementos del DOM
// ----------------------------------------------------------------------------
const DOM = {
  // Secciones
  authSection: document.getElementById('authSection'),
  newsSection: document.getElementById('newsSection'),
  navUserArea: document.getElementById('navUserArea'),

  // Pestañas Auth
  tabLogin: document.getElementById('tabLogin'),
  tabRegister: document.getElementById('tabRegister'),
  authAlert: document.getElementById('authAlert'),

  // Formulario Login
  formLogin: document.getElementById('formLogin'),
  loginEmail: document.getElementById('loginEmail'),
  loginPassword: document.getElementById('loginPassword'),
  loginEmailError: document.getElementById('loginEmailError'),
  loginPasswordError: document.getElementById('loginPasswordError'),
  linkToRegister: document.getElementById('linkToRegister'),

  // Formulario Registro
  formRegister: document.getElementById('formRegister'),
  regFullName: document.getElementById('regFullName'),
  regEmail: document.getElementById('regEmail'),
  regPassword: document.getElementById('regPassword'),
  regConfirmPassword: document.getElementById('regConfirmPassword'),
  regFullNameError: document.getElementById('regFullNameError'),
  regEmailError: document.getElementById('regEmailError'),
  regPasswordError: document.getElementById('regPasswordError'),
  regConfirmPasswordError: document.getElementById('regConfirmPasswordError'),
  linkToLogin: document.getElementById('linkToLogin'),

  // Sección Noticias
  newsGrid: document.getElementById('newsGrid'),
  newsStatusText: document.getElementById('newsStatusText'),
  newsApiKeyInput: document.getElementById('newsApiKeyInput'),
  btnApplyApiKey: document.getElementById('btnApplyApiKey'),
  btnReloadNews: document.getElementById('btnReloadNews'),
  categoryFilters: document.getElementById('categoryFilters')
};

// ----------------------------------------------------------------------------
// 3. Gestión de Estado y Almacenamiento (localStorage)
// ----------------------------------------------------------------------------
const StorageService = {
  // Obtener usuarios almacenados en localStorage
  getUsers() {
    try {
      const raw = localStorage.getItem(STORAGE_KEYS.USERS);
      return raw ? JSON.parse(raw) : [];
    } catch (e) {
      console.error('Error al parsear usuarios de localStorage:', e);
      return [];
    }
  },

  // Guardar un nuevo usuario
  saveUser(newUser) {
    const users = this.getUsers();
    users.push(newUser);
    localStorage.setItem(STORAGE_KEYS.USERS, JSON.stringify(users));
  },

  // Buscar usuario por correo electrónico
  findUserByEmail(email) {
    const users = this.getUsers();
    return users.find(u => u.email.toLowerCase() === email.trim().toLowerCase());
  },

  // Gestión de sesión activa
  getSession() {
    try {
      const session = localStorage.getItem(STORAGE_KEYS.SESSION);
      return session ? JSON.parse(session) : null;
    } catch (e) {
      return null;
    }
  },

  setSession(user) {
    const sessionData = {
      isLoggedIn: true,
      email: user.email,
      fullName: user.fullName,
      loginTime: new Date().toISOString()
    };
    localStorage.setItem(STORAGE_KEYS.SESSION, JSON.stringify(sessionData));
  },

  clearSession() {
    localStorage.removeItem(STORAGE_KEYS.SESSION);
  },

  // API Key para NewsAPI
  getApiKey() {
    return localStorage.getItem(STORAGE_KEYS.API_KEY) || '';
  },

  setApiKey(key) {
    localStorage.setItem(STORAGE_KEYS.API_KEY, key.trim());
  }
};

// ----------------------------------------------------------------------------
// 4. Validaciones de Formularios (RegEx y Reglas de Negocio)
// ----------------------------------------------------------------------------
const Validator = {
  // Validación de campo con RegEx
  validateField(inputElement, errorElement, regex, errorMessage) {
    const value = inputElement.value.trim();
    if (!value) {
      this.setError(inputElement, errorElement, 'Este campo es obligatorio.');
      return false;
    }
    if (!regex.test(value)) {
      this.setError(inputElement, errorElement, errorMessage);
      return false;
    }
    this.setSuccess(inputElement, errorElement);
    return true;
  },

  setError(input, errorElement, message) {
    input.classList.remove('is-valid');
    input.classList.add('is-invalid');
    if (errorElement) errorElement.textContent = message;
  },

  setSuccess(input, errorElement) {
    input.classList.remove('is-invalid');
    input.classList.add('is-valid');
    if (errorElement) errorElement.textContent = '';
  },

  clearValidation(input, errorElement) {
    input.classList.remove('is-valid', 'is-invalid');
    if (errorElement) errorElement.textContent = '';
  }
};

// ----------------------------------------------------------------------------
// 5. Controlador de Autenticación
// ----------------------------------------------------------------------------
const AuthController = {
  init() {
    this.bindEvents();
    this.checkCurrentSession();
  },

  bindEvents() {
    // Alternar pestañas
    DOM.tabLogin.addEventListener('click', () => this.switchTab('login'));
    DOM.tabRegister.addEventListener('click', () => this.switchTab('register'));
    DOM.linkToRegister.addEventListener('click', (e) => {
      e.preventDefault();
      this.switchTab('register');
    });
    DOM.linkToLogin.addEventListener('click', (e) => {
      e.preventDefault();
      this.switchTab('login');
    });

    // Enviar formularios
    DOM.formLogin.addEventListener('submit', (e) => this.handleLogin(e));
    DOM.formRegister.addEventListener('submit', (e) => this.handleRegister(e));

    // Validaciones en tiempo real (Blur/Input)
    DOM.regFullName.addEventListener('input', () => {
      Validator.validateField(
        DOM.regFullName,
        DOM.regFullNameError,
        REGEX_RULES.fullName,
        'Mínimo 3 caracteres alfabéticos.'
      );
    });

    DOM.regEmail.addEventListener('input', () => {
      Validator.validateField(
        DOM.regEmail,
        DOM.regEmailError,
        REGEX_RULES.email,
        'Formato de correo no válido (ejemplo: usuario@correo.com).'
      );
    });

    DOM.regPassword.addEventListener('input', () => {
      Validator.validateField(
        DOM.regPassword,
        DOM.regPasswordError,
        REGEX_RULES.password,
        'Mínimo 6 caracteres con letras y números.'
      );
    });

    DOM.regConfirmPassword.addEventListener('input', () => {
      this.validateConfirmPassword();
    });
  },

  validateConfirmPassword() {
    const password = DOM.regPassword.value;
    const confirm = DOM.regConfirmPassword.value;
    if (!confirm) {
      Validator.setError(DOM.regConfirmPassword, DOM.regConfirmPasswordError, 'Confirme su contraseña.');
      return false;
    }
    if (password !== confirm) {
      Validator.setError(DOM.regConfirmPassword, DOM.regConfirmPasswordError, 'Las contraseñas no coinciden.');
      return false;
    }
    Validator.setSuccess(DOM.regConfirmPassword, DOM.regConfirmPasswordError);
    return true;
  },

  switchTab(tab) {
    this.hideAlert();
    if (tab === 'login') {
      DOM.tabLogin.classList.add('active');
      DOM.tabRegister.classList.remove('active');
      DOM.formLogin.classList.remove('hidden');
      DOM.formRegister.classList.add('hidden');
    } else {
      DOM.tabRegister.classList.add('active');
      DOM.tabLogin.classList.remove('active');
      DOM.formRegister.classList.remove('hidden');
      DOM.formLogin.classList.add('hidden');
    }
  },

  showAlert(message, type = 'danger') {
    DOM.authAlert.className = `alert alert-${type}`;
    DOM.authAlert.textContent = message;
    DOM.authAlert.classList.remove('hidden');
  },

  hideAlert() {
    DOM.authAlert.classList.add('hidden');
  },

  handleRegister(e) {
    e.preventDefault();
    this.hideAlert();

    const isNameValid = Validator.validateField(
      DOM.regFullName,
      DOM.regFullNameError,
      REGEX_RULES.fullName,
      'Ingrese al menos 3 caracteres alfabéticos.'
    );

    const isEmailValid = Validator.validateField(
      DOM.regEmail,
      DOM.regEmailError,
      REGEX_RULES.email,
      'Ingrese un formato de correo electrónico válido.'
    );

    const isPasswordValid = Validator.validateField(
      DOM.regPassword,
      DOM.regPasswordError,
      REGEX_RULES.password,
      'La contraseña debe tener mínimo 6 caracteres, combinando letras y números.'
    );

    const isConfirmValid = this.validateConfirmPassword();

    if (!isNameValid || !isEmailValid || !isPasswordValid || !isConfirmValid) {
      this.showAlert('Por favor, corrija los errores marcados en el formulario.', 'danger');
      return;
    }

    const email = DOM.regEmail.value.trim().toLowerCase();
    const existing = StorageService.findUserByEmail(email);

    if (existing) {
      Validator.setError(DOM.regEmail, DOM.regEmailError, 'Este correo ya se encuentra registrado.');
      this.showAlert('El correo ingresado ya pertenece a un usuario registrado.', 'danger');
      return;
    }

    // Registrar nuevo usuario
    const newUser = {
      id: Date.now(),
      fullName: DOM.regFullName.value.trim(),
      email: email,
      password: DOM.regPassword.value // En un entorno cliente para la PC1 se almacena localmente
    };

    StorageService.saveUser(newUser);

    // Feedback y cambio automático a Login
    DOM.formRegister.reset();
    [DOM.regFullName, DOM.regEmail, DOM.regPassword, DOM.regConfirmPassword].forEach(el => {
      Validator.clearValidation(el);
    });

    this.switchTab('login');
    this.showAlert('¡Registro completado con éxito! Por favor inicie sesión.', 'success');
    DOM.loginEmail.value = newUser.email;
    DOM.loginPassword.focus();
  },

  handleLogin(e) {
    e.preventDefault();
    this.hideAlert();

    const email = DOM.loginEmail.value.trim().toLowerCase();
    const password = DOM.loginPassword.value;

    if (!email || !password) {
      this.showAlert('Debe ingresar su correo y contraseña.', 'danger');
      return;
    }

    const user = StorageService.findUserByEmail(email);

    if (!user || user.password !== password) {
      this.showAlert('Credenciales incorrectas. Verifique su correo o contraseña.', 'danger');
      return;
    }

    // Inicio de sesión exitoso: guardar en localStorage
    StorageService.setSession(user);
    DOM.formLogin.reset();
    [DOM.loginEmail, DOM.loginPassword].forEach(el => Validator.clearValidation(el));

    this.checkCurrentSession();
  },

  checkCurrentSession() {
    const session = StorageService.getSession();
    if (session && session.isLoggedIn) {
      this.renderAuthenticatedUI(session);
      NewsController.loadNews();
    } else {
      this.renderGuestUI();
    }
  },

  renderAuthenticatedUI(session) {
    DOM.authSection.classList.add('hidden');
    DOM.newsSection.classList.remove('hidden');

    const initial = session.fullName ? session.fullName.charAt(0).toUpperCase() : 'U';

    DOM.navUserArea.innerHTML = `
      <div class="user-badge" title="${session.email}">
        <div class="user-avatar">${initial}</div>
        <span>${session.fullName}</span>
      </div>
      <button id="btnLogout" class="btn btn-secondary btn-sm" type="button">
        Cerrar Sesión
      </button>
    `;

    document.getElementById('btnLogout').addEventListener('click', () => this.handleLogout());
  },

  renderGuestUI() {
    DOM.authSection.classList.remove('hidden');
    DOM.newsSection.classList.add('hidden');
    DOM.navUserArea.innerHTML = `
      <span style="font-size: 0.85rem; color: var(--gray-500);">Sesión no iniciada</span>
    `;
  },

  handleLogout() {
    StorageService.clearSession();
    this.checkCurrentSession();
    this.switchTab('login');
    this.showAlert('Ha cerrado sesión correctamente.', 'info');
  }
};

// ----------------------------------------------------------------------------
// 6. Controlador de la API de Noticias (NewsAPI + Fallback de Contingencia)
// ----------------------------------------------------------------------------
const NewsController = {
  currentCategory: 'general',

  init() {
    this.bindEvents();
    // Pre-cargar key guardada si existe
    DOM.newsApiKeyInput.value = StorageService.getApiKey();
  },

  bindEvents() {
    // Guardar Key personalizada
    DOM.btnApplyApiKey.addEventListener('click', () => {
      const key = DOM.newsApiKeyInput.value.trim();
      StorageService.setApiKey(key);
      alert(key ? 'API Key guardada exitosamente.' : 'Se eliminó la API Key (usando fallback).');
      this.loadNews();
    });

    // Botón Recargar
    DOM.btnReloadNews.addEventListener('click', () => {
      this.loadNews();
    });

    // Filtros de categoría
    DOM.categoryFilters.addEventListener('click', (e) => {
      const target = e.target.closest('.filter-chip');
      if (!target) return;

      document.querySelectorAll('.filter-chip').forEach(btn => btn.classList.remove('active'));
      target.classList.add('active');

      this.currentCategory = target.dataset.category;
      this.loadNews();
    });
  },

  async loadNews() {
    this.renderLoading();
    const apiKey = StorageService.getApiKey();

    if (apiKey) {
      try {
        DOM.newsStatusText.textContent = `Consultando NewsAPI (${this.currentCategory})...`;
        
        // Endpoint oficial de NewsAPI especificado en la práctica
        const url = `https://newsapi.org/v2/top-headlines?country=us&category=${this.currentCategory}&apiKey=${apiKey}`;
        const response = await fetch(url);

        if (!response.ok) {
          throw new Error(`HTTP ${response.status}: ${response.statusText}`);
        }

        const data = await response.json();

        if (data.status === 'ok' && Array.isArray(data.articles) && data.articles.length > 0) {
          DOM.newsStatusText.textContent = `Mostrando ${data.articles.length} noticias en vivo desde NewsAPI`;
          this.renderArticles(data.articles);
          return;
        } else {
          throw new Error(data.message || 'No se recibieron artículos de la API.');
        }
      } catch (error) {
        console.warn('Aviso: Falló la petición directa a NewsAPI (posible CORS o límite de cuota). Activando dataset local de contingencia:', error.message);
        DOM.newsStatusText.textContent = `⚠️ Modo Contingencia (Dataset Local). Motivo: ${error.message}`;
        this.renderFallback();
      }
    } else {
      DOM.newsStatusText.textContent = `Mostrando dataset de contingencia local (Ingrese una API Key para consumir newsapi.org)`;
      this.renderFallback();
    }
  },

  renderFallback() {
    // Filtrar dataset mock según categoría si aplica
    this.renderArticles(FALLBACK_NEWS);
  },

  renderLoading() {
    DOM.newsGrid.innerHTML = `
      <div class="loading-state">
        <div class="spinner"></div>
        <p>Cargando los titulares de noticias más recientes...</p>
      </div>
    `;
  },

  renderArticles(articles) {
    if (!articles || articles.length === 0) {
      DOM.newsGrid.innerHTML = `
        <div class="empty-state">
          <p>No se encontraron noticias disponibles en este momento.</p>
        </div>
      `;
      return;
    }

    // Renderizado dinámico usando Plantillas Literales (Template Strings con backticks)
    const cardsHTML = articles
      .filter(art => art.title && art.title !== '[Removed]')
      .map(art => {
        const image = art.urlToImage || 'https://images.unsplash.com/photo-1504711434969-e33886168f5c?auto=format&fit=crop&w=800&q=80';
        const title = art.title || 'Titular no disponible';
        const description = art.description || 'Haga clic en el enlace para leer la cobertura completa y los detalles de esta noticia.';
        const sourceName = (art.source && art.source.name) ? art.source.name : 'Noticias';
        const link = art.url || '#';
        const dateStr = art.publishedAt ? new Date(art.publishedAt).toLocaleDateString('es-PE', {
          year: 'numeric',
          month: 'short',
          day: 'numeric'
        }) : 'Reciente';

        return `
          <article class="news-card">
            <div class="news-card-img-wrapper">
              <img 
                src="${image}" 
                alt="${title.replace(/"/g, '&quot;')}" 
                class="news-card-img" 
                loading="lazy" 
                onerror="this.src='https://images.unsplash.com/photo-1585829365295-ab7cd400c167?auto=format&fit=crop&w=800&q=80'"
              />
              <span class="news-card-source">${sourceName}</span>
            </div>
            <div class="news-card-body">
              <h3 class="news-card-title">${title}</h3>
              <p class="news-card-desc">${description}</p>
              <div class="news-card-meta">
                <span>📅 ${dateStr}</span>
                <a href="${link}" target="_blank" rel="noopener noreferrer" class="news-card-link">
                  Leer completa ↗
                </a>
              </div>
            </div>
          </article>
        `;
      })
      .join('');

    DOM.newsGrid.innerHTML = cardsHTML;
  }
};

// ----------------------------------------------------------------------------
// 7. Inicialización de la Aplicación al cargar el DOM
// ----------------------------------------------------------------------------
document.addEventListener('DOMContentLoaded', () => {
  AuthController.init();
  NewsController.init();
});
