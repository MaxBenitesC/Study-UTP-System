# Speech de exposicion - Diapositivas 8 a 11

PDF base: `Java Chess Engine para Glajumedia SAC.pdf`

Proyecto fuente: `PROYECTO_PATRONES/chess-java`

Parte asignada: desde **Interfaz Grafica (Java Swing)** hasta la diapositiva final.

## Objetivo de esta parte

Tu tramo de exposicion debe demostrar 4 cosas:

1. Que la interfaz grafica no es solo pantalla, sino entrada al flujo de negocio.
2. Que el login sigue una separacion por capas: Vista, Aplicacion, Infraestructura y Dominio.
3. Que el tablero interactivo se conecta con la logica del ajedrez.
4. Que puedes cerrar la exposicion reconociendo fortalezas y limitaciones sin perder defensa tecnica.

No debes explicar todas las lineas. Debes explicar el recorrido funcional y ubicar clases/metodos clave.

## Resumen de las diapositivas asignadas

### Diapositiva 8: Interfaz Grafica (Java Swing)

La diapositiva presenta 3 bloques:

- Autenticacion.
- Tablero interactivo.
- Ranking corporativo.

### Diapositiva 9: Flujo de Accion - Proceso de Login

La diapositiva divide el login en:

- Vista UI.
- Aplicacion.
- Infraestructura.
- Dominio.

### Diapositiva 10: Conclusion

Resume que el sistema cumple el objetivo academico y tecnico: arquitectura por capas, POO y patrones.

### Diapositiva 11: Gracias

Cierre y apertura a preguntas.

## Speech completo recomendado

### Inicio de tu parte

> "En esta parte voy a explicar la interfaz grafica y como esta se conecta con la arquitectura interna del sistema. Aunque visualmente vemos pantallas en Java Swing, internamente cada accion del usuario viaja por capas: primero la vista, luego los casos de uso, despues infraestructura cuando se necesita base de datos, y finalmente el dominio, donde estan las reglas principales."

> "Mi parte cubre la autenticacion, el tablero interactivo, el ranking corporativo y el flujo de login. Tambien voy a cerrar con la conclusion tecnica del proyecto."

## Diapositiva 8 - Interfaz Grafica (Java Swing)

### Lo que aparece en la diapositiva

Titulo:

```text
Interfaz Grafica (Java Swing)
```

Bloques:

```text
Autenticacion
Login seguro con encriptacion SHA-256 para mantener historiales personalizados.

Tablero Interactivo
Motor logico integrado para validar movimientos legales en tiempo real (1vs1).

Ranking Corporativo
Visualizacion de posiciones y estadisticas que fomenta la competencia sana.
```

### Speech para la diapositiva 8

> "La interfaz grafica fue desarrollada con Java Swing. En este proyecto Swing cumple el rol de capa de presentacion: muestra ventanas, recibe eventos del usuario y delega la logica a otras capas."

> "Aqui tenemos tres funcionalidades visibles. La primera es autenticacion, donde el usuario ingresa sus credenciales. La segunda es el tablero interactivo, donde el usuario selecciona una pieza y una casilla destino. La tercera es el ranking corporativo, que muestra los jugadores ordenados por estadisticas."

> "Lo importante es que la interfaz no trabaja aislada. Por ejemplo, en el login la vista captura usuario y clave, pero no consulta directamente la base de datos. Esa accion se delega al controlador y luego al caso de uso. En el tablero ocurre algo parecido: la vista detecta el clic, pero la validacion del movimiento se delega a la capa de aplicacion y al dominio."

### Referencias al codigo para la diapositiva 8

#### Autenticacion

Archivo:

```text
src/presentacion/vistas/VistaLogin.java
```

Referencia:

```java
private void label_ingresarMouseClicked(MouseEvent evt) {
    String usuarioIngresado = txt_user.getText();
    String passwordIngresado = new String(txt_password.getPassword());
    controlador.login(usuarioIngresado, passwordIngresado);
}
```

Ubicacion aproximada:

```text
VistaLogin.java:333-338
```

Como explicarlo:

> "En `VistaLogin`, cuando el usuario presiona ingresar, la vista obtiene usuario y password, pero no valida directamente. Llama al metodo `login` del controlador. Eso muestra una separacion basica entre vista y logica."

#### Tablero interactivo

Archivo:

```text
src/presentacion/vistas/VistaJuego.java
```

Referencias:

```java
private void manejarClick(Posicion pos) {
    Tablero tablero = gestionarJuego.getTablero();
    Pieza piezaClickeada = tablero.getPieza(pos);
    ...
    if (gestionarJuego.realizarMovimiento(seleccionOrigen, pos)) {
        seleccionOrigen = null;
        actualizarTablero();
        actualizarTurno();
        verificarEstadoJuego();
    }
}
```

Ubicacion aproximada:

```text
VistaJuego.java:201-220
```

Como explicarlo:

> "El tablero interactivo funciona capturando clics sobre casillas. La vista identifica origen y destino, pero no decide sola si el movimiento es valido. Llama a `gestionarJuego.realizarMovimiento`, que pertenece a la capa de aplicacion."

#### Ranking corporativo

Archivo:

```text
src/presentacion/vistas/VistaRanking.java
```

Referencias:

```java
RepositorioUsuario repo = new RepositorioUsuarioMySQL();
this.usuarios = repo.obtenerUsuarios();
```

```java
List<Usuario> ordenados = usuarios.stream()
    .sorted((u1, u2) -> Integer.compare(u2.getPartidasGanadas(), u1.getPartidasGanadas()))
    .collect(Collectors.toList());
```

Ubicacion aproximada:

```text
VistaRanking.java:19-23
VistaRanking.java:79-82
```

Como explicarlo:

> "El ranking obtiene usuarios desde el repositorio y los ordena por victorias. Luego se muestran en la interfaz junto con medallas para los primeros puestos."

### Si el profesor te pregunta por Java Swing

Pregunta:

> "Por que usaron Java Swing?"

Respuesta recomendada:

> "Porque el objetivo era una aplicacion de escritorio academica, rapida de demostrar y compatible con Java puro. Swing nos permite crear ventanas, formularios y eventos sin depender de un navegador o servidor web. El foco del curso no era la tecnologia visual, sino como esa interfaz se conecta con patrones y capas."

### Si el profesor pregunta si la vista esta totalmente desacoplada

Respuesta honesta:

> "No totalmente. El login esta mejor separado porque usa `VistaLogin`, `ControladorLogin` e `IniciarSesion`. En cambio, algunas vistas como `VistaRanking` instancian directamente `RepositorioUsuarioMySQL`. Eso funciona, pero como mejora aplicaria inyeccion de dependencias para mantener una separacion mas estricta."

## Diapositiva 9 - Flujo de Accion: Proceso de Login

### Lo que aparece en la diapositiva

La diapositiva divide el flujo en 4 partes:

```text
1. Vista (UI)
El usuario ingresa sus credenciales en VistaLogin (Swing) y presiona el boton.
La vista notifica al Controlador.

2. Aplicacion
El ControladorLogin invoca el caso de uso IniciarSesion, actuando como puente y desacoplando la logica.

3. Infraestructura
El Repositorio consulta MySQL. UsuarioMapper transforma el ResultSet en un objeto de Dominio valido.

4. Dominio
La entidad verifica la contrasena (SHA-256). Si es exitosa, se registra en el Singleton de Sesion y se abre el menu.
```

### Speech para la diapositiva 9

> "Esta diapositiva muestra el recorrido completo del login. Este flujo es importante porque demuestra la separacion por capas y varios patrones del proyecto."

> "Primero, en la capa de presentacion, el usuario ingresa sus credenciales en `VistaLogin`. Cuando presiona el boton, la vista no consulta MySQL directamente; notifica al `ControladorLogin`."

> "Segundo, en la capa de aplicacion, `ControladorLogin` invoca el caso de uso `IniciarSesion`. Este caso de uso representa la accion de negocio: autenticar a un usuario."

> "Tercero, en infraestructura, `IniciarSesion` usa el contrato `RepositorioUsuario`. La implementacion concreta es `RepositorioUsuarioMySQL`, que realiza la consulta con JDBC. Cuando obtiene el resultado SQL, `UsuarioMapper` transforma el `ResultSet` en un objeto `Usuario` del dominio."

> "Finalmente, en dominio, la entidad `Usuario` verifica la contrasena aplicando SHA-256. Si la clave coincide, se registra el usuario en `Sesion`, que esta implementada como Singleton, y se abre el menu principal."

### Flujo resumido para decirlo rapido

Memoriza esta cadena:

```text
VistaLogin
 -> ControladorLogin
 -> IniciarSesion
 -> RepositorioUsuarioMySQL
 -> UsuarioMapper
 -> Usuario.verificarPassword
 -> Sesion
 -> VistaMenuPrincipal
```

Frase corta:

> "El login comienza en la vista, pasa por el controlador, ejecuta un caso de uso, consulta infraestructura, valida en dominio y guarda la sesion."

## Sustento en codigo para la diapositiva 9

### 1. Vista UI: `VistaLogin`

Archivo:

```text
src/presentacion/vistas/VistaLogin.java
```

Codigo clave:

```java
private void label_ingresarMouseClicked(MouseEvent evt) {
    String usuarioIngresado = txt_user.getText();
    String passwordIngresado = new String(txt_password.getPassword());
    controlador.login(usuarioIngresado, passwordIngresado);
}
```

Referencia:

```text
VistaLogin.java:333-338
```

Explicacion:

> "Aqui vemos que la vista obtiene los datos ingresados y llama al controlador. No contiene la consulta SQL ni la validacion criptografica."

### 2. Aplicacion/Controlador: `ControladorLogin`

Archivo:

```text
src/presentacion/controladores/ControladorLogin.java
```

Codigo clave:

```java
public void login(String correo, String clave) {
    Usuario usuario = iniciarSesion.ejecutar(correo, clave);
    if (usuario != null) {
        JOptionPane.showMessageDialog(null, "Bienvenido " + usuario.getNombreUsuario() + "!");
        VistaMenuPrincipal menuPrincipal = new VistaMenuPrincipal();
        menuPrincipal.mostrar();
        vista.cerrar();
    } else {
        JOptionPane.showMessageDialog(null, "Credenciales incorrectas", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
```

Referencia:

```text
ControladorLogin.java:23-35
```

Explicacion:

> "El controlador no valida manualmente la contrasena. Coordina el flujo: llama al caso de uso, responde si el usuario existe y decide si abrir el menu principal."

### 3. Caso de uso: `IniciarSesion`

Archivo:

```text
src/aplicacion/casosuso/IniciarSesion.java
```

Codigo clave:

```java
public Usuario ejecutar(String nombreUsuario, String clave) {
    Usuario usuario = repositorioUsuario.buscarPorNombreUsuario(nombreUsuario);

    if (usuario != null && usuario.verificarPassword(clave)) {
        Sesion.iniciarSesion(usuario);
        return usuario;
    }

    return null;
}
```

Referencia:

```text
IniciarSesion.java:14-23
```

Explicacion:

> "Este caso de uso es el centro del login. Primero busca al usuario mediante el repositorio. Luego delega la verificacion de la contrasena a la entidad `Usuario`. Si la validacion es correcta, registra el usuario en `Sesion`."

### 4. Infraestructura: `RepositorioUsuarioMySQL`

Archivo:

```text
src/infraestructura/persistencia/RepositorioUsuarioMySQL.java
```

Codigo clave:

```java
public Usuario buscarPorNombreUsuario(String nombreUsuario) {
    String sql = "SELECT * FROM chess_game.usuario WHERE nombreUsuario = ?";
    Connection conn = DB.getInstancia().getConexion();
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setString(1, nombreUsuario);
    ResultSet rs = stmt.executeQuery();

    if (rs.next()) {
        return UsuarioMapper.mapearUsuario(rs);
    }
}
```

Referencia:

```text
RepositorioUsuarioMySQL.java:17-31
```

Explicacion:

> "Aqui se ve el patron Repository. La clase concreta sabe consultar MySQL, pero el caso de uso depende de la interfaz `RepositorioUsuario`, no de los detalles SQL."

### 5. Mapper: `UsuarioMapper`

Archivo:

```text
src/infraestructura/mapper/UsuarioMapper.java
```

Codigo clave:

```java
public static Usuario mapearUsuario(ResultSet rs) throws SQLException {
    Usuario usuario = new Usuario(
        rs.getString("nombreUsuario"),
        rs.getString("nombre"),
        rs.getString("apellido"),
        rs.getString("password")
    );
    usuario.setPartidasGanadas(rs.getInt("partidasGanadas"));
    usuario.setPartidasPerdidas(rs.getInt("partidasPerdidas"));
    usuario.setPartidasEmpatadas(rs.getInt("partidasEmpatadas"));
    usuario.setEsAdmin(rs.getBoolean("esAdmin"));
    return usuario;
}
```

Referencia:

```text
UsuarioMapper.java:9-20
```

Explicacion:

> "El mapper evita que la conversion de datos SQL a objeto de dominio quede mezclada con toda la logica del repositorio."

### 6. Dominio: `Usuario`

Archivo:

```text
src/dominio/modelos/Usuario.java
```

Codigo clave:

```java
public boolean verificarPassword(String passwordAuth) {
    return this.password.equals(hashearPassword(passwordAuth));
}

public static String hashearPassword(String password) {
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
    ...
}
```

Referencia:

```text
Usuario.java:114-134
```

Explicacion:

> "La verificacion se hace comparando el password guardado con el hash SHA-256 de la clave ingresada. Esto corresponde a la parte de dominio indicada en la diapositiva."

### 7. Singleton de sesion: `Sesion`

Archivo:

```text
src/aplicacion/Sesion.java
```

Codigo clave:

```java
private static Sesion instancia;
private Usuario usuario;

private Sesion() {}

private static synchronized Sesion getInstancia() {
    if (instancia == null) {
        instancia = new Sesion();
    }
    return instancia;
}

public static void iniciarSesion(Usuario usuario) {
    getInstancia().usuario = usuario;
}
```

Referencia:

```text
Sesion.java:5-24
```

Explicacion:

> "Sesion usa Singleton porque durante la ejecucion se necesita una unica sesion activa. El constructor es privado, la instancia es estatica y el acceso se controla desde `getInstancia()`."

## Patrones que aparecen en tus diapositivas

### MVC parcial

Donde esta:

```text
VistaLogin.java
ControladorLogin.java
IniciarSesion.java
Usuario.java
```

Como decirlo:

> "En el login se aplica una separacion tipo MVC. La vista captura datos, el controlador coordina la accion, el caso de uso ejecuta la logica y el modelo `Usuario` representa la entidad de dominio."

Advertencia:

No digas:

> "Todo el proyecto es MVC perfecto."

Di:

> "El login es el modulo donde mejor se observa la separacion tipo MVC."

### Repository

Donde esta:

```text
RepositorioUsuario.java
RepositorioUsuarioMySQL.java
```

Como decirlo:

> "Repository separa la logica de negocio de la persistencia. `IniciarSesion` no necesita saber que existe MySQL; solo llama a la interfaz `RepositorioUsuario`."

### Mapper

Donde esta:

```text
UsuarioMapper.java
```

Como decirlo:

> "Mapper transforma datos de infraestructura, como `ResultSet`, en objetos del dominio, como `Usuario`."

### Singleton

Donde esta:

```text
Sesion.java
DB.java
```

Como decirlo:

> "Singleton se usa para mantener una unica sesion activa y tambien una instancia centralizada de conexion a base de datos."

### Strategy / Polimorfismo

Aunque no aparece directamente en el flujo de login, si aparece en el tablero interactivo de la diapositiva 8.

Donde esta:

```text
Pieza.java
Caballo.java
Torre.java
Alfil.java
Reina.java
Rey.java
Peon.java
```

Codigo clave:

```java
public abstract List<Posicion> getMovimientosPosibles(Tablero tablero);
```

Referencia:

```text
Pieza.java:21
```

Como decirlo:

> "El tablero interactivo se apoya en polimorfismo. Cada pieza tiene su propia estrategia de movimiento. El tablero no necesita un gran switch preguntando si es caballo, torre o peon; simplemente pregunta a la pieza si el movimiento es valido."

## Conexion entre tablero interactivo y dominio

### Speech corto

> "En el tablero interactivo, la interfaz recibe los clics del usuario, pero la regla de movimiento no vive en la pantalla. La pantalla llama a `GestionarJuego`, este delega a `Partida`, y finalmente `Tablero` valida el movimiento usando la pieza concreta."

### Ruta tecnica

```text
VistaJuego.manejarClick()
 -> GestionarJuego.realizarMovimiento()
 -> Partida.realizarMovimiento()
 -> Tablero.ejecutarMovimiento()
 -> Pieza.esMovimientoValido()
 -> getMovimientosPosibles() de la pieza concreta
```

### Codigo clave

Archivo:

```text
src/aplicacion/casosuso/GestionarJuego.java
```

Referencia:

```text
GestionarJuego.java:20-23
```

```java
public boolean realizarMovimiento(Posicion origen, Posicion destino) {
    if (partidaActual == null) return false;
    return partidaActual.realizarMovimiento(origen, destino);
}
```

Archivo:

```text
src/dominio/modelos/Tablero.java
```

Referencia:

```text
Tablero.java:70-89
```

```java
public boolean ejecutarMovimiento(Posicion origen, Posicion destino) {
    Pieza pieza = getPieza(origen);
    if (pieza == null) return false;
    if (!pieza.esMovimientoValido(destino, this)) return false;
    ...
    if (estaEnJaque(pieza.getColor())) {
        ...
        return false;
    }
    return true;
}
```

Explicacion:

> "Aqui se ve que no solo se mueve una imagen. Primero se valida si existe una pieza, luego si el movimiento es legal, y finalmente se verifica que el propio rey no quede en jaque. Si el movimiento deja al rey en jaque, se revierte."

## Diapositiva 10 - Conclusion

### Lo que dice la diapositiva

```text
Se desarrollo una herramienta robusta y escalable que cumple con las metas de team building de Glajumedia SAC, demostrando un alto nivel tecnico en Arquitectura Limpia, POO y Patrones de Diseno.
```

### Speech recomendado para conclusion

> "Como conclusion, el proyecto cumple el objetivo academico porque no solamente implementa una interfaz grafica, sino que organiza el sistema en capas y aplica patrones de diseno en puntos concretos."

> "Desde la parte visual, Java Swing permite demostrar login, menu, tablero y ranking. Desde la parte interna, la aplicacion separa responsabilidades: la presentacion captura eventos, la aplicacion coordina casos de uso, el dominio contiene reglas como usuarios, tablero, partida y piezas, e infraestructura se encarga de MySQL."

> "En patrones, podemos identificar Singleton para sesion y conexion, Repository para aislar acceso a datos, Mapper para transformar resultados SQL, MVC parcial en el login y Strategy mediante el polimorfismo de piezas."

> "Tambien es importante reconocer el alcance. El proyecto es defendible como implementacion academica, pero todavia se puede mejorar: faltan reglas especiales de ajedrez como enroque, promocion de peon y captura al paso; tambien agregaria pruebas unitarias y aplicaria inyeccion de dependencias en todas las vistas para reducir acoplamiento."

> "Por eso, mas que presentarlo como un motor de ajedrez profesional, lo presentamos como una aplicacion de escritorio que demuestra POO, separacion por capas y patrones de diseno aplicados a un caso funcional."

### Cierre fuerte

> "En resumen: la interfaz permite interactuar con el sistema, pero el valor tecnico esta en que esas acciones estan conectadas con una arquitectura por capas y patrones que separan responsabilidades."

## Diapositiva 11 - Gracias

### Speech de cierre

> "Eso seria todo por nuestra parte. Gracias por su atencion. Quedamos atentos a sus preguntas sobre el flujo, los patrones aplicados o las decisiones de diseno del proyecto."

Si quieres sonar mas seguro:

> "Si desea, podemos ubicar en el codigo cualquiera de los patrones mencionados: Singleton en `Sesion`, Repository en `RepositorioUsuario`, Mapper en `UsuarioMapper`, o Strategy en las clases de piezas."

## Version de speech continuo para leer

Usa esta version si quieres leerlo casi de corrido.

> "En esta parte voy a explicar la interfaz grafica y como se conecta con la arquitectura interna del sistema. Aunque visualmente vemos ventanas en Java Swing, internamente cada accion del usuario pasa por capas: presentacion, aplicacion, infraestructura y dominio."

> "En la diapositiva de Interfaz Grafica tenemos tres funcionalidades principales: autenticacion, tablero interactivo y ranking corporativo. La autenticacion permite ingresar con usuario y contrasena. En codigo esto inicia en `VistaLogin`, donde al presionar ingresar se capturan los datos y se llama al controlador. Es importante mencionar que la vista no consulta directamente la base de datos, sino que delega."

> "El tablero interactivo permite seleccionar una pieza y una casilla destino. En `VistaJuego`, el metodo `manejarClick` administra esa seleccion. Pero la pantalla no decide completamente si el movimiento es valido: llama a `GestionarJuego.realizarMovimiento`, y desde ahi se llega al dominio, especialmente a `Tablero.ejecutarMovimiento` y a las clases de piezas."

> "El ranking corporativo se implementa en `VistaRanking`. Alli se obtienen los usuarios desde un repositorio y se ordenan por partidas ganadas. Esto permite mostrar posiciones y estadisticas, reforzando la idea de competencia sana dentro del contexto de Glajumedia SAC."

> "Luego, en el flujo de login, vemos claramente la separacion por capas. Primero esta la Vista UI: `VistaLogin` recibe credenciales y notifica al `ControladorLogin`. Segundo, en Aplicacion, el controlador invoca el caso de uso `IniciarSesion`. Tercero, en Infraestructura, el repositorio consulta MySQL y `UsuarioMapper` convierte el `ResultSet` en un objeto `Usuario`. Finalmente, en Dominio, `Usuario` verifica la contrasena usando SHA-256, y si es correcta se registra en `Sesion`, que esta implementada como Singleton."

> "Este flujo demuestra varios patrones. MVC parcial aparece en el login, porque hay vista, controlador y logica separada. Repository aparece con `RepositorioUsuario` y `RepositorioUsuarioMySQL`, porque el caso de uso depende de una interfaz y no directamente de MySQL. Mapper aparece en `UsuarioMapper`, que transforma los datos de SQL a objeto de dominio. Singleton aparece en `Sesion`, para mantener un unico usuario autenticado durante la ejecucion."

> "Tambien conectamos esta parte con el tablero. En el dominio tenemos la clase abstracta `Pieza`, que declara `getMovimientosPosibles`. Cada pieza concreta implementa su propia forma de moverse: caballo, torre, alfil, reina, rey y peon. Esto aplica polimorfismo y funciona como Strategy, porque cada pieza representa una estrategia distinta de movimiento."

> "Como conclusion, el proyecto cumple el objetivo academico de aplicar arquitectura por capas, POO y patrones de diseno. No lo presentamos como un motor profesional de ajedrez completo, sino como una aplicacion de escritorio funcional y defendible. Entre las mejoras futuras estan completar reglas como enroque, promocion y captura al paso, agregar pruebas unitarias y aplicar inyeccion de dependencias en todas las vistas."

> "Con esto cerramos nuestra exposicion. Gracias por su atencion. Podemos responder preguntas o ubicar en el codigo cualquiera de los patrones mencionados."

## Preguntas probables del profesor y respuestas

### 1. "Donde exactamente esta la interfaz grafica?"

Respuesta:

> "Esta en el paquete `presentacion.vistas`. Por ejemplo `VistaLogin`, `VistaJuego`, `VistaRanking`, `VistaMenuPrincipal`, `VistaCrearUsuario` y `VistaEditarPerfil`. Todas extienden de `JFrame`, que pertenece a Java Swing."

Referencia:

```text
VistaLogin.java:9
VistaJuego.java:16
VistaRanking.java:14
```

### 2. "La vista valida el login?"

Respuesta:

> "No directamente. La vista captura los datos y llama al controlador. La validacion ocurre en el caso de uso `IniciarSesion`, que consulta el repositorio y luego usa `Usuario.verificarPassword`."

Referencia:

```text
VistaLogin.java:333-338
ControladorLogin.java:23-35
IniciarSesion.java:14-23
Usuario.java:114-134
```

### 3. "Que patron hay en el login?"

Respuesta:

> "Hay MVC parcial, Repository, Mapper y Singleton. MVC parcial porque `VistaLogin` delega al `ControladorLogin`; Repository porque `IniciarSesion` usa `RepositorioUsuario`; Mapper porque `UsuarioMapper` convierte datos SQL a objeto; y Singleton porque `Sesion` guarda el usuario autenticado."

### 4. "Por que dices que `Sesion` es Singleton?"

Respuesta:

> "Porque tiene una instancia estatica, constructor privado y un metodo centralizado `getInstancia`. Eso garantiza que durante la ejecucion se use una unica sesion."

Referencia:

```text
Sesion.java:5-24
```

### 5. "Por que usar SHA-256?"

Respuesta:

> "Para no comparar la contrasena en texto plano. El sistema hashea la clave ingresada y compara ese hash con el valor almacenado."

Respuesta si el profesor exige seguridad real:

> "Para un sistema productivo usaria BCrypt, Argon2 o PBKDF2 con salt. SHA-256 cumple el objetivo academico, pero no es la recomendacion mas fuerte para passwords reales."

Referencia:

```text
Usuario.java:114-134
```

### 6. "Donde esta el Repository?"

Respuesta:

> "En `RepositorioUsuario`, que define el contrato, y `RepositorioUsuarioMySQL`, que implementa ese contrato con JDBC. El caso de uso `IniciarSesion` depende de la interfaz, no de la implementacion concreta."

Referencia:

```text
RepositorioUsuarioMySQL.java:14
IniciarSesion.java:8-15
```

### 7. "Que hace UsuarioMapper?"

Respuesta:

> "Convierte un `ResultSet` de JDBC en un objeto `Usuario`. Asi el repositorio no mezcla toda la conversion de columnas con la logica de consulta."

Referencia:

```text
UsuarioMapper.java:9-20
```

### 8. "Como se valida un movimiento del tablero?"

Respuesta:

> "La vista captura el clic y llama a `GestionarJuego.realizarMovimiento`. Luego se delega a la partida y al tablero. En `Tablero.ejecutarMovimiento`, primero se obtiene la pieza, luego se verifica si el movimiento es valido y finalmente se comprueba que no deje al propio rey en jaque."

Referencia:

```text
VistaJuego.java:201-220
GestionarJuego.java:20-23
Tablero.java:70-89
```

### 9. "Donde esta Strategy?"

Respuesta:

> "En la jerarquia de piezas. `Pieza` define el contrato `getMovimientosPosibles`, y cada pieza concreta implementa su propia estrategia: caballo, torre, alfil, reina, rey y peon."

Referencia:

```text
Pieza.java:10-25
```

### 10. "Esto es Strategy o solo herencia?"

Respuesta:

> "Esta implementado con herencia y polimorfismo. Conceptualmente cumple el objetivo de Strategy porque encapsula algoritmos intercambiables de movimiento: cada pieza tiene una forma distinta de calcular movimientos."

### 11. "El tablero interactivo valida movimientos en tiempo real?"

Respuesta:

> "Si, desde la interaccion de usuario. Al seleccionar origen y destino, la vista llama al servicio de juego. El dominio decide si el movimiento procede. Ademas, luego de mover, se actualiza el tablero y se verifica jaque o jaque mate."

Referencia:

```text
VistaJuego.java:215-219
VistaJuego.java:256-274
```

### 12. "El ranking esta bien desacoplado?"

Respuesta:

> "Funciona, pero no esta desacoplado de forma ideal. `VistaRanking` instancia directamente `RepositorioUsuarioMySQL`. Como mejora, pasaria el repositorio por constructor o usaria un caso de uso especifico para ranking."

Referencia:

```text
VistaRanking.java:19-23
```

### 13. "Tu proyecto realmente usa arquitectura limpia?"

Respuesta equilibrada:

> "Tiene una estructura inspirada en arquitectura limpia o por capas: dominio, aplicacion, infraestructura y presentacion. Sin embargo, no es una implementacion perfecta porque algunas vistas aun crean dependencias concretas. Lo correcto es decir que aplica separacion por capas con oportunidades de mejora."

### 14. "Que limitaciones tiene el ajedrez?"

Respuesta:

> "Implementa movimientos basicos, jaque y jaque mate. Como limitaciones, faltan reglas especiales como enroque, promocion de peon, captura al paso y empate completo."

### 15. "Que mejorarias primero?"

Respuesta:

> "Primero reforzaria validaciones en dominio, agregaria pruebas unitarias para movimientos, aplicaria inyeccion de dependencias en las vistas y completaria reglas especiales del ajedrez."

### 16. "Por que el login abre directamente el menu desde el controlador?"

Respuesta:

> "Es una decision practica para el flujo de escritorio. En una version mas robusta, separaria la navegacion en un coordinador de vistas para que el controlador no cree directamente `VistaMenuPrincipal`."

Referencia:

```text
ControladorLogin.java:28-31
```

### 17. "El usuario queda guardado despues del login?"

Respuesta:

> "Si, queda en `Sesion`. El metodo `Sesion.iniciarSesion(usuario)` guarda el usuario autenticado y luego otras vistas pueden recuperarlo con `Sesion.usuario()`."

Referencia:

```text
Sesion.java:18-24
```

### 18. "El ranking usa base de datos?"

Respuesta:

> "Si. `VistaRanking` crea un repositorio MySQL y llama a `obtenerUsuarios()`. Luego ordena por `partidasGanadas` para mostrar posiciones."

Referencia:

```text
VistaRanking.java:19-23
VistaRanking.java:79-82
RepositorioUsuarioMySQL.java:55-70
```

## Preguntas trampa y como salir bien

### "Entonces, si usas SHA-256, el login es completamente seguro?"

No respondas:

> "Si, completamente seguro."

Responde:

> "Es mas seguro que texto plano y cumple el objetivo academico. Para produccion, la recomendacion seria usar algoritmos especializados para passwords como BCrypt o Argon2 con salt."

### "Dices arquitectura limpia, pero la vista crea repositorios. Eso no rompe la arquitectura?"

Responde:

> "Si, es una deuda tecnica identificada. La estructura general separa paquetes y responsabilidades, pero hay puntos acoplados. Por eso lo presento como arquitectura por capas con mejoras pendientes, no como Clean Architecture perfecta."

### "Si falta enroque, entonces el motor de ajedrez esta incompleto?"

Responde:

> "Si hablamos de reglas oficiales completas, si, faltan reglas especiales. Pero para el objetivo del curso, el motor demuestra movimientos basicos, validacion, jaque, jaque mate y patrones de diseno."

### "Por que no hicieron una Factory para crear piezas?"

Responde:

> "Actualmente `Tablero` inicializa las piezas directamente. Una mejora natural seria agregar una Factory para centralizar la creacion de piezas y reducir acoplamiento."

### "Donde se ve que no todo esta en la interfaz?"

Responde:

> "En el login, la vista llama al controlador; el controlador llama a `IniciarSesion`; y este consulta el repositorio y valida contra `Usuario`. En el juego, la vista llama a `GestionarJuego`, y el movimiento se valida en `Tablero` y `Pieza`."

## Mini guia para responder mientras muestras codigo

Usa esta formula:

```text
Patron -> archivo -> metodo -> problema que resuelve -> mejora posible
```

Ejemplo:

> "Repository esta en `RepositorioUsuario` y `RepositorioUsuarioMySQL`. El metodo `buscarPorNombreUsuario` permite obtener un usuario sin que el caso de uso conozca SQL. Resuelve el acoplamiento con MySQL. Como mejora, aplicaria este mismo nivel de desacoplamiento a todas las vistas."

## Frases tecnicas utiles

Puedes usar estas frases:

> "La vista captura eventos, pero no deberia contener reglas profundas de negocio."

> "El controlador coordina el flujo entre la vista y el caso de uso."

> "El caso de uso representa una accion del sistema."

> "El repositorio abstrae la persistencia."

> "El mapper transforma datos de infraestructura a objetos de dominio."

> "El dominio contiene reglas y entidades principales."

> "El tablero no mueve imagenes solamente; valida movimientos sobre objetos de dominio."

> "El polimorfismo permite que cada pieza tenga su propia regla sin llenar el tablero de condicionales."

> "El proyecto es academicamente defendible, pero tiene deuda tecnica identificada."

## Tiempo sugerido para tu parte

Si tienes 5 minutos:

1. Diapositiva 8: 1 minuto 30 segundos.
2. Diapositiva 9: 2 minutos.
3. Diapositiva 10: 1 minuto.
4. Diapositiva 11: 30 segundos.

Si tienes 8 minutos:

1. Diapositiva 8: 2 minutos.
2. Diapositiva 9: 3 minutos.
3. Codigo/patrones: 1 minuto 30 segundos.
4. Conclusion: 1 minuto.
5. Cierre: 30 segundos.

## Version ultra corta por si te cortan el tiempo

> "Esta parte muestra como la interfaz Swing se conecta con la arquitectura interna. En autenticacion, `VistaLogin` captura datos y delega al `ControladorLogin`; este llama a `IniciarSesion`, que consulta el repositorio MySQL y valida el password en `Usuario` usando SHA-256. Si es correcto, se guarda en `Sesion`, que es Singleton. En el tablero, `VistaJuego` captura clics y llama a `GestionarJuego`; la validacion real ocurre en `Tablero` y en las piezas, aplicando polimorfismo y Strategy. El ranking obtiene usuarios desde el repositorio y los ordena por victorias. Como cierre, el proyecto demuestra capas, POO y patrones, aunque tiene mejoras pendientes como reglas especiales de ajedrez, pruebas e inyeccion de dependencias."

## Checklist antes de exponer

- Saber ubicar `VistaLogin.java`.
- Saber ubicar `ControladorLogin.java`.
- Saber ubicar `IniciarSesion.java`.
- Saber ubicar `RepositorioUsuarioMySQL.java`.
- Saber ubicar `UsuarioMapper.java`.
- Saber ubicar `Usuario.java`.
- Saber ubicar `Sesion.java`.
- Saber ubicar `VistaJuego.java`.
- Saber explicar el flujo de login sin leer.
- Saber explicar que Swing es presentacion, no negocio.
- Saber reconocer las limitaciones sin sonar inseguro.

