# Informe forense del proyecto de patrones

Proyecto revisado: `PROYECTO_PATRONES/chess-java`

Fecha de revision: 2026-07-13

## Veredicto docente

El proyecto es defendible como aplicacion Java Swing por capas para un juego de ajedrez con login, usuarios, ranking y persistencia MySQL. No es un proyecto "puro" de patrones, pero si contiene varios patrones y principios que se pueden sustentar si se explican con orden.

La estrategia para la exposicion debe ser clara: no venderlo como motor completo de ajedrez profesional, sino como una aplicacion academica donde los patrones ayudan a separar responsabilidades.

Nivel estimado para sustentar en 6 horas de preparacion: viable, siempre que domines estas ideas:

1. Separacion por capas.
2. Patrones usados y donde estan.
3. Flujo de login y flujo de partida.
4. Polimorfismo de piezas.
5. Limitaciones tecnicas reconocidas.

## Mapa general

La aplicacion esta organizada asi:

```text
src/
  Main.java
  aplicacion/
    Sesion.java
    casosuso/
      IniciarSesion.java
      RegistrarUsuario.java
      GestionarJuego.java
  dominio/
    enums/
    modelos/
      Usuario.java
      Partida.java
      Tablero.java
      Posicion.java
      piezas/
    puertos/
      RepositorioUsuario.java
      IGestionarJuego.java
  infraestructura/
    mapper/
      UsuarioMapper.java
    persistencia/
      DB.java
      RepositorioUsuarioMySQL.java
  presentacion/
    controladores/
      ControladorLogin.java
    vistas/
  shared/
    utils/
      ImageUtil.java
```

Lectura docente: la estructura intenta acercarse a arquitectura por capas o arquitectura hexagonal ligera. El dominio no depende de Swing ni de MySQL. Eso es una buena decision.

## Patrones y principios defendibles

### 1. Singleton

Clases:

- `aplicacion.Sesion`
- `infraestructura.persistencia.DB`

Como defenderlo:

`Sesion` centraliza el usuario autenticado durante la ejecucion. Usa constructor privado, atributo estatico `instancia` y acceso controlado por `getInstancia()`.

`DB` centraliza la conexion a MySQL. Usa tambien constructor privado y `getInstancia()` sincronizado.

Riesgo docente:

Singleton es facil de criticar porque introduce estado global y dificulta pruebas. Tu respuesta debe ser:

> "Lo usamos por simplicidad academica para mantener una unica sesion y una unica conexion. En una version mas robusta, preferiria inyeccion de dependencias y pool de conexiones."

### 2. Strategy por polimorfismo

Clases:

- `dominio.modelos.piezas.Pieza`
- `Rey`
- `Reina`
- `Torre`
- `Alfil`
- `Caballo`
- `Peon`

Como defenderlo:

`Pieza` define el contrato `getMovimientosPosibles(Tablero tablero)`. Cada pieza implementa su propia estrategia de movimiento.

Ejemplo:

- `Caballo` calcula saltos en L.
- `Torre` avanza horizontal y vertical.
- `Alfil` avanza en diagonales.
- `Reina` combina movimientos de torre y alfil.
- `Peon` usa direccion segun color.
- `Rey` valida casillas adyacentes y evita casillas atacadas.

Frase para exposicion:

> "El tablero no necesita saber como se mueve cada pieza. Delega esa regla a la pieza concreta. Eso reduce condicionales y permite extender comportamientos."

### 3. Template Method parcial

Clase base:

- `Pieza`

Metodo relevante:

- `esMovimientoValido(Posicion destino, Tablero tablero)`

Como defenderlo:

`Pieza.esMovimientoValido()` usa el algoritmo comun: pedir los movimientos posibles y verificar si el destino esta incluido. El paso variable es `getMovimientosPosibles()`, implementado por cada subclase.

No lo presentes como Template Method puro y formal. Di:

> "Hay una aplicacion parcial del principio Template Method: la validacion comun esta en la clase abstracta y el calculo especifico se delega a las subclases."

### 4. Repository

Clases:

- `dominio.puertos.RepositorioUsuario`
- `infraestructura.persistencia.RepositorioUsuarioMySQL`

Como defenderlo:

El dominio y los casos de uso dependen de una interfaz, no de MySQL directamente. `RepositorioUsuarioMySQL` es una implementacion concreta.

Frase para exposicion:

> "Si manana cambiamos MySQL por archivos, memoria o PostgreSQL, los casos de uso no deberian cambiar; solo se reemplaza la implementacion del repositorio."

### 5. Mapper

Clase:

- `infraestructura.mapper.UsuarioMapper`

Como defenderlo:

Convierte un `ResultSet` de JDBC en un objeto de dominio `Usuario`. Evita llenar `RepositorioUsuarioMySQL` con logica repetitiva de mapeo.

### 6. MVC parcial

Clases:

- `VistaLogin`
- `ControladorLogin`
- `IniciarSesion`
- `Usuario`

Como defenderlo:

En login si hay una separacion razonable:

- Vista: captura usuario y clave.
- Controlador: recibe evento y coordina.
- Caso de uso: valida credenciales.
- Modelo: `Usuario`.

Riesgo:

En otras pantallas las vistas crean repositorios y casos de uso directamente, por ejemplo `VistaRanking` y `VistaCrearUsuario`. Por eso no digas que todo el sistema es MVC perfecto. Di que el login es el modulo mas cercano al patron MVC.

## Flujo de ejecucion que debes memorizar

### Login

1. `Main` arranca Swing con `SwingUtilities.invokeLater`.
2. Crea `RepositorioUsuarioMySQL`.
3. Inyecta ese repositorio en `IniciarSesion`.
4. Crea `VistaLogin`.
5. Crea `ControladorLogin` con vista y caso de uso.
6. El controlador asigna `vista.setControlador(this)`.
7. Usuario hace clic en ingresar.
8. `VistaLogin` llama a `controlador.login(usuario, clave)`.
9. `ControladorLogin` llama `iniciarSesion.ejecutar(...)`.
10. `IniciarSesion` busca usuario en repositorio y valida password.
11. Si es correcto, guarda usuario en `Sesion` y abre `VistaMenuPrincipal`.

### Juego

1. `VistaMenuPrincipal` abre `VistaJuego`.
2. `VistaJuego` obtiene el usuario actual desde `Sesion.usuario()`.
3. Crea un segundo jugador temporal.
4. Crea `GestionarJuego`.
5. `GestionarJuego.crearPartida()` crea una `Partida`.
6. `Partida` crea un `Tablero`.
7. `Tablero` inicializa las 32 piezas.
8. La UI dibuja las 64 casillas.
9. Al hacer clic, la UI selecciona origen.
10. Al segundo clic, llama `gestionarJuego.realizarMovimiento(origen, destino)`.
11. `Partida` delega a `Tablero.ejecutarMovimiento()`.
12. `Tablero` pregunta a la pieza si el movimiento es valido.
13. Si es valido, mueve la pieza y evita dejar a su propio rey en jaque.
14. Se cambia turno.
15. La vista verifica jaque o jaque mate del jugador siguiente.

## Analisis clase por clase

### `Main`

Responsabilidad: punto de entrada y composicion inicial.

Lo bueno:

- Arranca Swing correctamente con `SwingUtilities.invokeLater`.
- Usa la interfaz `RepositorioUsuario` y la implementacion `RepositorioUsuarioMySQL`.
- Inyecta repositorio en `IniciarSesion`.

Debilidad:

- Solo compone el flujo de login. Otras vistas crean sus dependencias internamente.

### `Sesion`

Responsabilidad: mantener el usuario autenticado.

Patron: Singleton.

Debilidad:

- `cerrarSesion()` no es estatico, pero el uso del resto de la clase si lo es.
- El boton "CERRAR SESION" del menu no llama a este metodo.

### `IniciarSesion`

Responsabilidad: caso de uso de autenticacion.

Lo bueno:

- Depende de `RepositorioUsuario`, no de MySQL.
- No contiene codigo Swing.

Debilidad:

- Retorna `null` cuando falla. Una alternativa mas limpia seria retornar un resultado de autenticacion.

### `RegistrarUsuario`

Responsabilidad: guardar un nuevo usuario.

Lo bueno:

- Caso de uso corto y entendible.

Debilidad:

- No valida duplicados ni reglas de password. Depende de que la BD lance error por usuario unico.

### `GestionarJuego`

Responsabilidad: fachada/caso de uso para manejar la partida actual.

Patron defendible: Facade ligera o Service.

Lo bueno:

- La vista no habla directamente con todos los detalles de `Partida`.
- Implementa `IGestionarJuego`.

Debilidad importante:

- Solo maneja una partida en memoria.
- No persiste partidas.

### `Usuario`

Responsabilidad: entidad de usuario, estadisticas y password.

Lo bueno:

- Encapsula victorias, derrotas y empates.
- Tiene metodo de hash SHA-256.

Debilidades:

- Mezcla entidad de dominio con hashing de password.
- SHA-256 sin salt no es ideal para seguridad real.
- El `id` no se setea desde la BD en `UsuarioMapper`.

Respuesta si preguntan seguridad:

> "Para fines academicos se usa SHA-256. En produccion usaria BCrypt, Argon2 o PBKDF2 con salt."

### `Partida`

Responsabilidad: estado de una partida.

Lo bueno:

- Tiene jugadores, tablero, turno, estado, fechas y movimientos.
- Cambia el turno despues de un movimiento valido.

Debilidad critica:

- `realizarMovimiento()` no valida que la pieza de origen sea del color del turno. La UI lo controla, pero el dominio deberia reforzarlo.
- `EstadoJuego.JAQUE`, `JAQUE_MATE` y `EMPATE` existen, pero casi no se actualizan dentro de `Partida`.

Como defenderlo:

> "La interaccion normal desde la UI respeta el turno. Como mejora de diseno, esa regla deberia moverse tambien al dominio para proteger el caso de uso ante llamadas directas."

### `Tablero`

Responsabilidad: matriz 8x8, piezas, movimientos, jaque y jaque mate.

Lo bueno:

- Encapsula la matriz de piezas.
- Inicializa la disposicion estandar.
- Evita movimientos que dejan al propio rey en jaque.
- Tiene logica de jaque y jaque mate.

Debilidades:

- `inicializarTablero()` construye piezas directamente. Podria defenderse mejor con Factory.
- No hay enroque, promocion de peon ni captura al paso.
- `estaEnJaqueMate()` simula movimientos modificando el tablero real y revirtiendo. Funciona como enfoque academico, pero es delicado.

### `Posicion`

Responsabilidad: representar fila y columna.

Lo bueno:

- Convierte notacion tipo `e2` a indices de matriz.
- Convierte indices a notacion con `toNotacion()`.
- Implementa `equals()`, necesario para validar movimientos.

Debilidad:

- No implementa `hashCode()`. Si algun dia se usa en `HashSet` o `HashMap`, habria inconsistencia.
- El constructor por texto no valida longitud ni caracteres.

### `Pieza`

Responsabilidad: clase base abstracta.

Patrones:

- Strategy por subclases.
- Template Method parcial con `esMovimientoValido()`.

Lo bueno:

- Permite tratar todas las piezas de manera polimorfica.

### `Rey`

Responsabilidad: movimientos de rey.

Lo bueno:

- No permite moverse a casillas atacadas.

Debilidad:

- No implementa enroque.

### `Reina`

Responsabilidad: movimientos rectos y diagonales.

Lo bueno:

- Combina logica tipo torre y alfil.

Debilidad:

- Hay duplicacion de recorrido con `Torre` y `Alfil`; se podria extraer una utilidad de movimientos lineales.

### `Torre`

Responsabilidad: movimiento horizontal y vertical.

Debilidad:

- No guarda si ya se movio, dato necesario para enroque.

### `Alfil`

Responsabilidad: movimiento diagonal.

Evaluacion: correcta para alcance academico.

### `Caballo`

Responsabilidad: saltos en L.

Evaluacion: bien aislada y simple. Es la pieza mas facil de explicar.

### `Peon`

Responsabilidad: avance, doble avance inicial y captura diagonal.

Debilidades:

- No implementa promocion.
- No implementa captura al paso.

### Enums

Clases:

- `Color`
- `TipoPieza`
- `EstadoJuego`
- `ResultadoPartida`

Lo bueno:

- Evitan strings magicos para estados y tipos.

Debilidad:

- `ResultadoPartida` no parece usado.
- Algunos estados existen pero no se integran completamente.

### `RepositorioUsuario`

Responsabilidad: puerto de persistencia.

Patron: Repository.

Lo bueno:

- Permite inversion de dependencias.

### `IGestionarJuego`

Responsabilidad: contrato para el servicio de juego.

Lo bueno:

- La vista puede depender de una abstraccion.

Debilidad:

- `VistaJuego` declara el campo como `IGestionarJuego`, pero instancia directamente `new GestionarJuego()`. La inyeccion no esta completa.

### `DB`

Responsabilidad: conexion JDBC.

Patron: Singleton.

Lo bueno:

- Centraliza URL, usuario, password y conexion.

Debilidades:

- Credenciales hardcodeadas.
- No usa pool de conexiones.
- Maneja una conexion global.

### `RepositorioUsuarioMySQL`

Responsabilidad: persistencia concreta con JDBC.

Lo bueno:

- Usa `PreparedStatement`, lo cual reduce riesgo de SQL injection.
- Implementa la interfaz del dominio.

Debilidades:

- No cierra `PreparedStatement` ni `ResultSet` con try-with-resources.
- Captura errores con `printStackTrace()` sin propagar resultado claro.

### `UsuarioMapper`

Responsabilidad: convertir filas SQL a objeto `Usuario`.

Lo bueno:

- Separa mapeo de persistencia.

Debilidad:

- No mapea `id` ni `fechaRegistro`.

### `ControladorLogin`

Responsabilidad: coordinar login entre vista y caso de uso.

Lo bueno:

- Es el punto mas claro de MVC.
- La vista delega el login al controlador.

Debilidad:

- Crea `VistaMenuPrincipal` directamente. En un MVC mas limpio, la navegacion podria estar en un coordinador.

### `VistaLogin`

Responsabilidad: pantalla de autenticacion.

Lo bueno:

- No consulta directamente la base de datos.
- Usa controlador.

Debilidad:

- Usa layout absoluto con `setBounds`, menos adaptable.
- No valida placeholders antes de enviar credenciales.

### `VistaMenuPrincipal`

Responsabilidad: menu de navegacion.

Lo bueno:

- Muestra opciones segun admin.

Debilidades:

- `CERRAR SESION` solo imprime `"Ranking"`.
- Depende directamente de `Sesion.usuario()`.

### `VistaJuego`

Responsabilidad: tablero grafico e interaccion de partida.

Lo bueno:

- Usa `IGestionarJuego` como tipo del servicio.
- Filtra seleccion por turno desde UI.
- Redibuja piezas segun estado del tablero.

Debilidades:

- Crea `GestionarJuego` internamente.
- Crea jugador 2 quemado en codigo.
- No muestra historial real de movimientos, solo el titulo.
- La UI contiene bastante logica de flujo.

### `VistaRanking`

Responsabilidad: mostrar ranking por victorias.

Lo bueno:

- Ordena usuarios por partidas ganadas.
- Usa imagenes de medallas.

Debilidad:

- Instancia `RepositorioUsuarioMySQL` directamente desde la vista, rompiendo la separacion de capas.

### `VistaCrearUsuario`

Responsabilidad: formulario de creacion.

Lo bueno:

- Usa el caso de uso `RegistrarUsuario`.

Debilidad:

- Crea `RepositorioUsuarioMySQL` dentro de la vista.
- Tiene password por defecto `"123456"`.
- Mensaje con error de tipeo: "sone obligatorios".

### `VistaEditarPerfil`

Responsabilidad: editar datos locales del usuario.

Debilidad importante:

- Cambia el objeto `Usuario` en memoria, pero no persiste en MySQL.
- Muestra password cargado desde el usuario. Si viene de BD, probablemente es hash.

### `ImageUtil`

Responsabilidad: carga y escalado de imagenes.

Lo bueno:

- Centraliza carga desde classpath o carpeta `resources`.

Debilidad:

- Algunos metodos asumen que la imagen existe; podrian validar `null` como hace `escalarImg()`.

## Hallazgos forenses importantes

### Hallazgo 1: compila correctamente

Se compilaron las 33 clases Java con `javac` y el conector MySQL. No hubo errores de compilacion.

### Hallazgo 2: no hay pruebas automatizadas

No se encontro README, `pom.xml`, `build.gradle` ni clases de test. Para exposicion, no digas que el proyecto esta probado automaticamente.

### Hallazgo 3: Docker no inicializa el SQL visible

`docker-compose.yml` monta:

```text
./mysql-init:/docker-entrypoint-initdb.d
```

Pero el SQL revisado esta en:

```text
resources/migraciones/bd.sql
```

Si `mysql-init` esta vacio, Docker no ejecutara esa migracion automaticamente. Para demo, debes crear o cargar la BD manualmente.

### Hallazgo 4: reglas de ajedrez incompletas

El motor soporta movimientos basicos, jaque y jaque mate, pero faltan reglas:

- Enroque.
- Promocion de peon.
- Captura al paso.
- Tablas/empate real.
- Validacion de color del turno en dominio.

### Hallazgo 5: acoplamiento en presentacion

Varias vistas instancian infraestructura directamente. Esto debilita la defensa de arquitectura limpia.

Ejemplos:

- `VistaRanking` crea `RepositorioUsuarioMySQL`.
- `VistaCrearUsuario` crea `RepositorioUsuarioMySQL`.
- `VistaJuego` crea `GestionarJuego`.

Como defender:

> "El proyecto aplica separacion por capas en la base, especialmente en login y repositorios. Algunas vistas aun crean dependencias concretas; lo identifico como deuda tecnica y propuesta de mejora."

## Preguntas probables del profesor y respuestas

### "Que patrones usa tu proyecto?"

Respuesta breve:

> "Usa Singleton en sesion y conexion, Repository para persistencia de usuarios, Strategy mediante las piezas de ajedrez, Template Method parcial en la clase abstracta Pieza, Mapper para convertir ResultSet a Usuario y MVC parcial en el flujo de login."

### "Donde esta Strategy?"

Respuesta:

> "En `Pieza` y sus subclases. Todas comparten el metodo `getMovimientosPosibles`, pero cada pieza implementa una estrategia distinta de movimiento."

### "Por que no usaste Factory para las piezas?"

Respuesta:

> "Actualmente `Tablero` construye las piezas directamente. Una mejora natural seria agregar una Factory para centralizar la creacion de piezas y reducir acoplamiento en la inicializacion."

### "Tu MVC esta bien aplicado?"

Respuesta honesta:

> "Esta mejor aplicado en login: `VistaLogin`, `ControladorLogin`, `IniciarSesion` y `Usuario`. En otras pantallas hay acoplamiento porque algunas vistas crean casos de uso o repositorios. Lo presento como MVC parcial, no como MVC puro."

### "El ajedrez esta completo?"

Respuesta:

> "No completamente. Implementa movimientos basicos, turno desde UI, jaque y jaque mate. No implementa reglas especiales como enroque, promocion, captura al paso ni empate. El objetivo principal del curso es demostrar patrones y separacion de responsabilidades."

### "Que mejorarias si tuvieras mas tiempo?"

Respuesta:

1. Mover validacion de turno al dominio.
2. Implementar Factory para piezas.
3. Agregar pruebas unitarias para movimientos.
4. Corregir inyeccion de dependencias en vistas.
5. Persistir edicion de perfil y partidas.
6. Inicializar la BD correctamente desde Docker.

## Orden recomendado para exponer

Tiempo ideal: 10 a 15 minutos.

1. Problema y objetivo: "Aplicacion de ajedrez con usuarios, ranking y partida 1 vs 1".
2. Arquitectura por capas: presentacion, aplicacion, dominio, infraestructura.
3. Demo rapida de login y menu.
4. Explicar Repository con `RepositorioUsuario`.
5. Explicar Singleton con `Sesion` y `DB`.
6. Explicar Strategy con piezas.
7. Demo de movimiento en tablero.
8. Explicar jaque/validacion desde `Tablero`.
9. Reconocer limitaciones.
10. Cerrar con mejoras.

## Plan de estudio de 6 horas

### Hora 1: mapa mental del proyecto

Objetivo: entender carpetas y responsabilidades.

Tareas:

- Dibujar capas: Presentacion, Aplicacion, Dominio, Infraestructura.
- Memorizar el flujo de login.
- Memorizar el flujo de juego.

Resultado esperado:

Puedes explicar que hace cada paquete sin mirar codigo.

### Hora 2: patrones

Objetivo: defender patrones con archivos concretos.

Estudia:

- Singleton: `Sesion`, `DB`.
- Strategy: `Pieza` y subclases.
- Repository: `RepositorioUsuario`, `RepositorioUsuarioMySQL`.
- Mapper: `UsuarioMapper`.
- MVC parcial: login.

Resultado esperado:

Puedes decir clase, metodo y razon de cada patron.

### Hora 3: dominio de ajedrez

Objetivo: explicar el motor.

Estudia:

- `Tablero.ejecutarMovimiento()`
- `Tablero.estaEnJaque()`
- `Tablero.estaEnJaqueMate()`
- `Partida.realizarMovimiento()`
- `Posicion.toNotacion()`

Resultado esperado:

Puedes seguir un movimiento desde clic en UI hasta actualizacion del tablero.

### Hora 4: infraestructura y base de datos

Objetivo: explicar persistencia.

Estudia:

- `DB.getInstancia()`
- `RepositorioUsuarioMySQL.buscarPorNombreUsuario()`
- `RepositorioUsuarioMySQL.guardar()`
- `UsuarioMapper.mapearUsuario()`
- `bd.sql`

Resultado esperado:

Puedes explicar como se valida un usuario contra MySQL.

### Hora 5: demo y riesgos

Objetivo: preparar una demo sin sorpresas.

Checklist:

- Verificar que MySQL este levantado en puerto 3308.
- Verificar que existe tabla `usuario`.
- Verificar usuario `gfarfan` con clave `123456`.
- Abrir login.
- Entrar al menu.
- Mostrar ranking.
- Jugar un movimiento simple.

Riesgos que debes tener respuesta:

- Si no conecta BD: explicar configuracion de `DB.java`.
- Si preguntan por reglas incompletas: reconocer alcance.
- Si preguntan por MVC: decir "MVC parcial".

### Hora 6: simulacro

Objetivo: exponer sin leer.

Practica este guion:

> "Mi proyecto es una aplicacion Java Swing de ajedrez organizada por capas. La capa de presentacion contiene las vistas, aplicacion contiene los casos de uso, dominio contiene las reglas principales y entidades, e infraestructura contiene MySQL. Los patrones principales son Singleton para sesion y conexion, Repository para usuarios, Strategy para movimientos de piezas, Mapper para convertir filas de BD a objetos y MVC parcial en login. El punto mas importante del dominio es que `Tablero` delega a cada `Pieza` la validacion de movimientos, y luego valida que el rey no quede en jaque. Como limitacion, no implementa todas las reglas especiales del ajedrez y algunas vistas todavia crean dependencias concretas, lo cual propongo mejorar con inyeccion de dependencias y Factory."

## Archivos que debes abrir durante la exposicion

Orden recomendado:

1. `src/Main.java`
2. `src/dominio/puertos/RepositorioUsuario.java`
3. `src/infraestructura/persistencia/RepositorioUsuarioMySQL.java`
4. `src/aplicacion/Sesion.java`
5. `src/dominio/modelos/piezas/Pieza.java`
6. `src/dominio/modelos/piezas/Caballo.java`
7. `src/dominio/modelos/Tablero.java`
8. `src/dominio/modelos/Partida.java`
9. `src/presentacion/controladores/ControladorLogin.java`
10. `src/presentacion/vistas/VistaJuego.java`

## Evaluacion probable

### Fortalezas

- Buena separacion inicial por paquetes.
- Dominio independiente de Swing y MySQL.
- Uso real de polimorfismo.
- Repository bien identificado.
- Login con controlador.
- Uso de `PreparedStatement`.

### Debilidades

- Falta README y pruebas.
- Algunas vistas estan acopladas a infraestructura.
- Reglas de ajedrez incompletas.
- Docker no parece ejecutar la migracion actual.
- Edicion de perfil no persiste.
- Cierre de sesion no implementado.
- Credenciales de BD hardcodeadas.

### Nota probable si se expone mal

Regular: si solo muestras pantallas y dices nombres de patrones sin ubicarlos.

### Nota probable si se expone bien

Buena: si explicas capas, patrones, flujo y reconoces limitaciones con propuestas concretas.

## Recomendacion final

No memorices todas las lineas. Memoriza responsabilidades.

La defensa debe girar alrededor de esta idea:

> "El proyecto no intenta ser el motor de ajedrez mas completo, sino demostrar como los patrones permiten separar reglas del juego, persistencia, casos de uso y presentacion."

