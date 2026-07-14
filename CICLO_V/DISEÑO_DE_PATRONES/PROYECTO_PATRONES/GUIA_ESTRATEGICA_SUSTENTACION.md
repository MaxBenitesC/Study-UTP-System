# Guia estrategica de sustentacion

Proyecto: `PROYECTO_PATRONES/chess-java`

Objetivo: que los 3 integrantes puedan demostrar que entienden el proyecto, ubicar los patrones en codigo y responder preguntas del profesor sin memorizar todas las lineas.

## 1. Numeros que debes saber

Estos numeros salen del codigo revisado:

| Elemento | Cantidad |
|---|---:|
| Archivos `.java` | 33 |
| Clases concretas | 26 |
| Clase abstracta | 1 |
| Interfaces | 2 |
| Enums | 4 |
| Lineas Java totales | 2504 |
| Declaraciones de metodos/constructores detectadas | 191 aprox. |
| Imagenes PNG | 17 |
| Imagen JPG | 1 |
| Archivo SQL | 1 |
| Libreria JAR | 1 |

Como decirlo:

> "El proyecto tiene 33 archivos Java, organizados en 26 clases concretas, 1 clase abstracta, 2 interfaces y 4 enums. No voy a explicar linea por linea, sino la arquitectura, los patrones y los flujos principales."

Importante: el conteo de 191 metodos es aproximado porque incluye constructores, metodos privados y metodos de eventos Swing.

## 2. Tipos de archivos del proyecto

| Tipo | Uso |
|---|---|
| `.java` | Codigo fuente de la aplicacion |
| `.png` / `.jpg` | Recursos graficos: logo, login, piezas, medallas |
| `.sql` | Script de base de datos |
| `.jar` | Driver MySQL Connector/J |
| `.yml` | Docker Compose para MySQL |

Frase para exposicion:

> "Ademas del codigo Java, el proyecto incluye recursos visuales, un script SQL para la base de datos, el driver JDBC de MySQL y un Docker Compose para levantar la base."

## 3. Capas del proyecto

La primera parte de la explicacion debe ser la arquitectura. Esto ordena toda la exposicion.

```text
Presentacion
  Vistas Swing y controlador de login.

Aplicacion
  Casos de uso: iniciar sesion, registrar usuario, gestionar juego.

Dominio
  Entidades y reglas principales: usuario, partida, tablero, posicion, piezas.

Infraestructura
  Base de datos, conexion JDBC, repositorio MySQL y mapper.

Shared
  Utilidades comunes, por ejemplo carga y escalado de imagenes.
```

## 4. Capas explicadas con clases concretas

### Capa Presentacion

Ubicacion:

```text
src/presentacion/
```

Clases:

- `VistaLogin`
- `VistaMenuPrincipal`
- `VistaJuego`
- `VistaRanking`
- `VistaCrearUsuario`
- `VistaEditarPerfil`
- `ControladorLogin`

Que hace:

Es la parte visual. Contiene ventanas, botones, formularios, tablero grafico y eventos de usuario.

Como explicarlo:

> "Esta capa se encarga de interactuar con el usuario. No deberia contener reglas profundas del negocio; su funcion principal es capturar eventos y mostrar resultados."

Donde abrir:

1. `src/presentacion/vistas/VistaLogin.java`
2. `src/presentacion/controladores/ControladorLogin.java`
3. `src/presentacion/vistas/VistaJuego.java`

### Capa Aplicacion

Ubicacion:

```text
src/aplicacion/
```

Clases:

- `Sesion`
- `IniciarSesion`
- `RegistrarUsuario`
- `GestionarJuego`

Que hace:

Coordina casos de uso. Es el puente entre presentacion y dominio.

Como explicarlo:

> "Esta capa contiene las acciones principales del sistema: autenticar, registrar usuarios y gestionar una partida."

Donde abrir:

1. `src/aplicacion/casosuso/IniciarSesion.java`
2. `src/aplicacion/casosuso/GestionarJuego.java`
3. `src/aplicacion/Sesion.java`

### Capa Dominio

Ubicacion:

```text
src/dominio/
```

Clases importantes:

- `Usuario`
- `Partida`
- `Tablero`
- `Posicion`
- `Pieza`
- `Rey`
- `Reina`
- `Torre`
- `Alfil`
- `Caballo`
- `Peon`

Interfaces:

- `RepositorioUsuario`
- `IGestionarJuego`

Enums:

- `Color`
- `TipoPieza`
- `EstadoJuego`
- `ResultadoPartida`

Que hace:

Contiene las reglas principales del sistema. Es la parte mas importante para defender patrones.

Como explicarlo:

> "El dominio representa el ajedrez y los usuarios. Aqui estan las reglas del tablero, los movimientos de piezas, la partida y el turno."

Donde abrir:

1. `src/dominio/modelos/piezas/Pieza.java`
2. `src/dominio/modelos/piezas/Caballo.java`
3. `src/dominio/modelos/Tablero.java`
4. `src/dominio/modelos/Partida.java`

### Capa Infraestructura

Ubicacion:

```text
src/infraestructura/
```

Clases:

- `DB`
- `RepositorioUsuarioMySQL`
- `UsuarioMapper`

Que hace:

Contiene detalles tecnicos externos: conexion a MySQL, consultas SQL y conversion de resultados.

Como explicarlo:

> "Infraestructura implementa detalles externos. El dominio no sabe como se conecta MySQL; solo conoce interfaces."

Donde abrir:

1. `src/dominio/puertos/RepositorioUsuario.java`
2. `src/infraestructura/persistencia/RepositorioUsuarioMySQL.java`
3. `src/infraestructura/persistencia/DB.java`
4. `src/infraestructura/mapper/UsuarioMapper.java`

## 5. Patrones que debes defender

Estos son los patrones principales. No intentes defender mas de los necesarios.

### Patron 1: Singleton en `Sesion`

Archivo:

```text
src/aplicacion/Sesion.java
```

Lineas/metodos clave:

- `private static Sesion instancia;`
- `private Sesion() {}`
- `private static synchronized Sesion getInstancia()`
- `public static void iniciarSesion(Usuario usuario)`
- `public static Usuario usuario()`

Que problema resuelve:

Permite tener un unico usuario autenticado disponible durante la ejecucion.

Por que se hizo asi:

Porque varias pantallas necesitan saber quien esta logueado, por ejemplo `VistaMenuPrincipal`, `VistaJuego` y `VistaEditarPerfil`.

Como explicarlo:

> "Aqui se aplica Singleton porque solo debe existir una sesion activa. El constructor es privado, la instancia es estatica y el acceso se controla con `getInstancia()`."

Pregunta dificil:

> "Es buena practica usar Singleton?"

Respuesta:

> "Para un proyecto academico simplifica el manejo de sesion. En una aplicacion mas grande preferiria inyeccion de dependencias o un administrador de sesion para facilitar pruebas."

### Patron 2: Singleton en `DB`

Archivo:

```text
src/infraestructura/persistencia/DB.java
```

Lineas/metodos clave:

- `private static DB instancia;`
- `private Connection conexion;`
- `private DB()`
- `public static synchronized DB getInstancia()`
- `public Connection getConexion()`

Que problema resuelve:

Centraliza la conexion a MySQL para que los repositorios usen una misma fuente de conexion.

Por que se hizo asi:

Para evitar crear conexiones en cada consulta manualmente y tener un punto unico de configuracion.

Como explicarlo:

> "La clase `DB` encapsula la conexion JDBC. Aplica Singleton porque el sistema necesita un punto central para obtener la conexion a la base de datos."

Pregunta dificil:

> "Por que no usaste pool de conexiones?"

Respuesta:

> "Porque el alcance del proyecto es academico y de escritorio. En produccion usaria un pool como HikariCP para manejar multiples conexiones de forma eficiente."

### Patron 3: Repository

Archivos:

```text
src/dominio/puertos/RepositorioUsuario.java
src/infraestructura/persistencia/RepositorioUsuarioMySQL.java
```

Metodos clave:

- `buscarPorNombreUsuario(String nombreUsuario)`
- `guardar(Usuario usuario)`
- `obtenerUsuarios()`

Que problema resuelve:

Separa el dominio y los casos de uso de los detalles de MySQL.

Por que se hizo asi:

Para que `IniciarSesion` dependa de una interfaz y no de una clase concreta de base de datos.

Como demostrarlo en codigo:

1. Abrir `RepositorioUsuario.java`.
2. Mostrar que es una interfaz.
3. Abrir `RepositorioUsuarioMySQL.java`.
4. Mostrar `implements RepositorioUsuario`.
5. Abrir `IniciarSesion.java`.
6. Mostrar que recibe `RepositorioUsuario` en el constructor.

Frase de exposicion:

> "El caso de uso no sabe si los usuarios vienen de MySQL, de memoria o de un archivo. Solo usa el contrato `RepositorioUsuario`. Esa es la ventaja del patron Repository."

Pregunta dificil:

> "Donde esta la inversion de dependencias?"

Respuesta:

> "Esta en que `IniciarSesion` depende de la abstraccion `RepositorioUsuario`, no de `RepositorioUsuarioMySQL`."

### Patron 4: Strategy mediante polimorfismo de piezas

Archivos:

```text
src/dominio/modelos/piezas/Pieza.java
src/dominio/modelos/piezas/Caballo.java
src/dominio/modelos/piezas/Torre.java
src/dominio/modelos/piezas/Alfil.java
src/dominio/modelos/piezas/Reina.java
src/dominio/modelos/piezas/Rey.java
src/dominio/modelos/piezas/Peon.java
```

Metodo clave:

```java
getMovimientosPosibles(Tablero tablero)
```

Que problema resuelve:

Evita tener una gran estructura de `if` o `switch` en `Tablero` preguntando "si es caballo, si es torre, si es peon...".

Por que se hizo asi:

Porque cada pieza tiene una regla diferente de movimiento. Es mejor que cada clase encapsule su propia regla.

Como demostrarlo en codigo:

1. Abrir `Pieza.java`.
2. Mostrar que es abstracta.
3. Mostrar `public abstract List<Posicion> getMovimientosPosibles(Tablero tablero);`
4. Abrir `Caballo.java`.
5. Mostrar que implementa saltos en L.
6. Abrir `Torre.java`.
7. Mostrar que recorre direcciones rectas.
8. Abrir `Tablero.java`.
9. Mostrar que usa `pieza.esMovimientoValido(destino, this)`.

Frase de exposicion:

> "El tablero no calcula el movimiento de cada pieza. Le pregunta a la pieza si puede moverse. Cada subclase tiene su propia estrategia."

Pregunta dificil:

> "Esto es Strategy o solo herencia?"

Respuesta:

> "Esta implementado con herencia y polimorfismo. Conceptualmente actua como Strategy porque el algoritmo de movimiento cambia segun el tipo concreto de pieza."

### Patron 5: Template Method parcial

Archivo:

```text
src/dominio/modelos/piezas/Pieza.java
```

Metodos clave:

```java
public boolean esMovimientoValido(Posicion destino, Tablero tablero) {
    List<Posicion> movimientos = getMovimientosPosibles(tablero);
    return movimientos.contains(destino);
}
```

Que problema resuelve:

Centraliza una validacion comun: para saber si un destino es valido, se obtiene la lista de movimientos posibles y se verifica si contiene el destino.

Por que se hizo asi:

Porque todas las piezas pueden validar de la misma forma, aunque calculen movimientos diferentes.

Como explicarlo:

> "La clase abstracta define una parte comun del algoritmo en `esMovimientoValido()`. El paso variable es `getMovimientosPosibles()`, que cada pieza implementa de forma distinta."

Advertencia:

No lo vendas como el patron mas fuerte. Di "Template Method parcial" o "principio similar a Template Method".

### Patron 6: Mapper

Archivo:

```text
src/infraestructura/mapper/UsuarioMapper.java
```

Metodo clave:

```java
mapearUsuario(ResultSet rs)
```

Que problema resuelve:

Convierte una fila de base de datos (`ResultSet`) en un objeto de dominio (`Usuario`).

Por que se hizo asi:

Para no mezclar la logica de conversion dentro de todas las consultas SQL.

Como explicarlo:

> "El mapper separa la transformacion de datos. El repositorio consulta, y el mapper convierte el resultado SQL a objeto de dominio."

### Patron 7: MVC parcial en login

Archivos:

```text
src/presentacion/vistas/VistaLogin.java
src/presentacion/controladores/ControladorLogin.java
src/aplicacion/casosuso/IniciarSesion.java
src/dominio/modelos/Usuario.java
```

Que problema resuelve:

Separa pantalla, evento de usuario, logica de autenticacion y modelo.

Como demostrarlo:

1. En `VistaLogin`, mostrar que al hacer clic llama a `controlador.login(...)`.
2. En `ControladorLogin`, mostrar que llama a `iniciarSesion.ejecutar(...)`.
3. En `IniciarSesion`, mostrar que consulta el repositorio.
4. En `Usuario`, mostrar `verificarPassword(...)`.

Como explicarlo:

> "En el modulo de login usamos una separacion tipo MVC: la vista captura datos, el controlador coordina, el caso de uso ejecuta la logica y el modelo representa al usuario."

Pregunta dificil:

> "Todo el proyecto aplica MVC?"

Respuesta correcta:

> "No de forma perfecta. El login es el modulo mas claro. En otras vistas hay acoplamiento porque algunas crean repositorios directamente. Lo identifico como mejora."

### Patron 8: Facade o Service ligero en `GestionarJuego`

Archivo:

```text
src/aplicacion/casosuso/GestionarJuego.java
```

Metodos clave:

- `crearPartida(...)`
- `realizarMovimiento(...)`
- `getTablero()`
- `getTurnoActual()`
- `estaEnJaque(...)`
- `estaEnJaqueMate(...)`

Que problema resuelve:

Le da a la UI una entrada simple para manejar la partida sin manipular directamente todos los objetos internos.

Como explicarlo:

> "`GestionarJuego` funciona como servicio de aplicacion. La vista no necesita crear una partida y manipular tablero directamente; llama a este caso de uso."

Advertencia:

No lo presentes como Facade clasico si el profesor es estricto. Mejor decir "servicio de aplicacion con comportamiento de fachada".

## 6. Orden estrategico de exposicion

### Paso 1: mostrar que el programa funciona

Demo esperada:

1. Levantar la aplicacion.
2. Login.
3. Mostrar menu.
4. Entrar a jugar.
5. Seleccionar una pieza.
6. Hacer un movimiento valido.
7. Mostrar que cambia el turno.
8. Volver al menu.
9. Mostrar ranking o crear usuario si el usuario es admin.

Frase:

> "Primero mostrare el flujo funcional y luego explicare que patrones soportan internamente ese flujo."

### Paso 2: explicar arquitectura

No entres aun a patrones. Primero ordena:

> "El proyecto se divide en presentacion, aplicacion, dominio e infraestructura."

Luego muestra carpetas.

### Paso 3: explicar flujo de login

Ruta:

```text
Main -> VistaLogin -> ControladorLogin -> IniciarSesion -> RepositorioUsuario -> DB -> Sesion -> VistaMenuPrincipal
```

Patrones aqui:

- MVC parcial.
- Repository.
- Singleton `Sesion`.
- Singleton `DB`.
- Mapper.

### Paso 4: explicar flujo de juego

Ruta:

```text
VistaMenuPrincipal -> VistaJuego -> GestionarJuego -> Partida -> Tablero -> Pieza concreta
```

Patrones aqui:

- Service/Facade ligero.
- Strategy.
- Template Method parcial.
- Polimorfismo.

### Paso 5: reconocer limitaciones

No esperes a que el profesor te ataque. Di algo como:

> "El proyecto cumple el objetivo de patrones, pero no implementa todas las reglas oficiales del ajedrez. Faltan enroque, promocion, captura al paso y empate completo. Tambien reforzaria validaciones en dominio y agregaria pruebas."

Eso suena a criterio tecnico.

## 7. Division recomendada para 3 integrantes

### Integrante 1: arquitectura y login

Debe dominar:

- Capas.
- `Main`.
- `VistaLogin`.
- `ControladorLogin`.
- `IniciarSesion`.
- `RepositorioUsuario`.
- `RepositorioUsuarioMySQL`.
- `Sesion`.

Patrones que explica:

- MVC parcial.
- Repository.
- Singleton `Sesion`.

### Integrante 2: dominio del juego

Debe dominar:

- `Partida`.
- `Tablero`.
- `Posicion`.
- `Pieza`.
- Subclases de piezas.

Patrones que explica:

- Strategy.
- Template Method parcial.
- Polimorfismo.

### Integrante 3: infraestructura, ranking y mejoras

Debe dominar:

- `DB`.
- `UsuarioMapper`.
- `VistaRanking`.
- `VistaCrearUsuario`.
- `bd.sql`.
- Limitaciones y mejoras.

Patrones que explica:

- Singleton `DB`.
- Mapper.
- Repository desde el lado de infraestructura.

Importante: aunque dividan temas, los 3 deben poder responder una definicion corta de cada patron.

## 8. Guion corto para iniciar la exposicion

Puedes decir:

> "Nuestro proyecto es una aplicacion de ajedrez en Java Swing con login, usuarios, ranking y partida 1 contra 1. Tiene 33 archivos Java, organizados en 4 capas principales: presentacion, aplicacion, dominio e infraestructura. La parte mas importante para el curso es que usamos patrones para separar responsabilidades: Singleton para sesion y conexion, Repository para aislar MySQL, Strategy para los movimientos de piezas, Mapper para convertir datos de base a objetos, y MVC parcial en el login."

## 9. Como responder "donde esta el patron"

Usa siempre esta formula:

```text
Patron + archivo + metodo + problema que resuelve + por que se hizo asi.
```

Ejemplo:

> "El patron Repository esta en `RepositorioUsuario` y `RepositorioUsuarioMySQL`. El contrato define operaciones como buscar y guardar usuarios. La implementacion MySQL realiza las consultas. Se hizo asi para que el caso de uso `IniciarSesion` no dependa directamente de MySQL, sino de una abstraccion."

## 10. Tabla rapida de patrones

| Patron | Archivo principal | Metodo clave | Que soluciona |
|---|---|---|---|
| Singleton | `Sesion.java` | `getInstancia()` | Unica sesion activa |
| Singleton | `DB.java` | `getInstancia()` | Punto unico de conexion |
| Repository | `RepositorioUsuario.java` | `buscarPorNombreUsuario()` | Aislar persistencia |
| Strategy | `Pieza.java` y subclases | `getMovimientosPosibles()` | Movimientos distintos por pieza |
| Template Method parcial | `Pieza.java` | `esMovimientoValido()` | Validacion comun de movimiento |
| Mapper | `UsuarioMapper.java` | `mapearUsuario()` | Convertir SQL a objeto |
| MVC parcial | Login | `login()` / `ejecutar()` | Separar vista, controlador y logica |
| Service/Facade ligero | `GestionarJuego.java` | `realizarMovimiento()` | Simplificar acceso al juego |

## 11. Preguntas de defensa y respuestas

### "Por que dices que hay Strategy?"

> "Porque todas las piezas comparten el mismo contrato, `getMovimientosPosibles`, pero cada una tiene una estrategia distinta. Caballo salta en L, Torre se mueve recto, Alfil en diagonal, etc."

### "Donde se usa polimorfismo?"

> "En `Tablero`, porque trabaja con referencias de tipo `Pieza`, pero en tiempo de ejecucion se ejecuta el metodo de la subclase concreta: `Caballo`, `Torre`, `Peon`, etc."

### "Que pasa cuando haces un movimiento?"

> "La vista obtiene origen y destino, llama a `GestionarJuego.realizarMovimiento`, este llama a `Partida.realizarMovimiento`, la partida llama a `Tablero.ejecutarMovimiento`, y el tablero pregunta a la pieza si el movimiento es valido."

### "Que patron conecta con base de datos?"

> "Repository. `RepositorioUsuario` define el contrato y `RepositorioUsuarioMySQL` implementa ese contrato con JDBC."

### "Que debilidad tiene tu arquitectura?"

> "Algunas vistas aun crean dependencias concretas, como `RepositorioUsuarioMySQL`. Eso se puede mejorar con inyeccion de dependencias para que la separacion por capas sea mas estricta."

### "Que reglas de ajedrez faltan?"

> "Faltan reglas especiales como enroque, promocion de peon, captura al paso y empate completo. El objetivo principal del proyecto era demostrar patrones y estructura."

## 12. Lo que no debes decir

Evita:

- "Todo esta perfectamente aplicado."
- "Es arquitectura limpia completa."
- "El ajedrez esta 100% implementado."
- "MVC esta en todo el proyecto."
- "Singleton siempre es la mejor opcion."

Mejor:

- "MVC parcial."
- "Service con comportamiento de fachada."
- "Template Method parcial."
- "Es una implementacion academica defendible."
- "Hay deuda tecnica identificada."

## 13. Ruta de codigo para practicar

Practica abrir archivos en este orden:

1. `src/Main.java`
2. `src/presentacion/vistas/VistaLogin.java`
3. `src/presentacion/controladores/ControladorLogin.java`
4. `src/aplicacion/casosuso/IniciarSesion.java`
5. `src/dominio/puertos/RepositorioUsuario.java`
6. `src/infraestructura/persistencia/RepositorioUsuarioMySQL.java`
7. `src/aplicacion/Sesion.java`
8. `src/aplicacion/casosuso/GestionarJuego.java`
9. `src/dominio/modelos/Partida.java`
10. `src/dominio/modelos/Tablero.java`
11. `src/dominio/modelos/piezas/Pieza.java`
12. `src/dominio/modelos/piezas/Caballo.java`
13. `src/infraestructura/persistencia/DB.java`
14. `src/infraestructura/mapper/UsuarioMapper.java`

Si puedes explicar esos 14 archivos, puedes defender el proyecto.

## 14. Resumen final para memorizar

> "El sistema esta dividido por capas. Presentacion muestra ventanas, aplicacion coordina casos de uso, dominio contiene reglas de ajedrez y usuarios, infraestructura maneja MySQL. Los patrones principales son Singleton, Repository, Strategy, Mapper y MVC parcial. El patron mas fuerte del dominio es Strategy porque cada pieza implementa su propio movimiento mediante polimorfismo. La principal mejora seria reforzar reglas en dominio, completar reglas de ajedrez, inyectar dependencias en todas las vistas y agregar pruebas."

