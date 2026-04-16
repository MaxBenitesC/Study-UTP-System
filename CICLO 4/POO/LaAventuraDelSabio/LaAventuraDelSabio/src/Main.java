import MisClases.*;
import InterfaceAbstracta.*;
import java.util.List;

/**
 * Punto de entrada principal del videojuego educativo
 * "La Aventura del Sabio".
 *
 * <p>Esta clase contiene el método {@code main}, encargado de:
 * <ul>
 *     <li>Mostrar el menú principal del juego.</li>
 *     <li>Permitir elegir el modo de juego (Solitario o Versus).</li>
 *     <li>Gestionar el registro de jugadores dependiendo del modo.</li>
 *     <li>Invocar la lógica correspondiente al modo Solitario o Versus.</li>
 *     <li>Repetir el flujo hasta que el usuario seleccione la opción de salida.</li>
 * </ul>
 *
 * <p>La clase actúa como controlador general del programa,
 * iniciando y coordinando las clases principales del proyecto.</p>
 */
public class Main {

    /**
     * Método principal que ejecuta el flujo general del videojuego.
     *
     * <p>La ejecución sigue estos pasos:</p>
     * <ol>
     *     <li>Crear una instancia de {@link ModoDeJuego}.</li>
     *     <li>Mostrar el menú principal y esperar elección del usuario.</li>
     *     <li>Si el usuario elige modo Solitario:
     *         <ul>
     *             <li>Registrar un único jugador.</li>
     *             <li>Mostrar su información.</li>
     *             <li>Iniciar el modo de juego Solitario.</li>
     *         </ul>
     *     </li>
     *     <li>Si el usuario elige modo Versus:
     *         <ul>
     *             <li>Solicitar la cantidad de jugadores (2 o 3).</li>
     *             <li>Registrar a todos los jugadores.</li>
     *             <li>Mostrar su información.</li>
     *             <li>Iniciar el modo de juego Versus.</li>
     *         </ul>
     *     </li>
     *     <li>Repetir hasta que la opción seleccionada sea 9 (salida).</li>
     * </ol>
     *
     * @param args parámetros de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        ModoDeJuego mododejuego = new ModoDeJuego();

        do {
            mododejuego.eleccionModoJuego();

            // MODO SOLITARIO
            if (mododejuego.getModoJuego() == 1) {

                List<Sabio> jugadores = RegistroDeJugadores.registrarJugador(1);
                RegistroDeJugadores.mostrarInfoJugador(jugadores);

                MensajeDurantePartida.mostrarInicioPartida();

                ModoSolitario modosolitario = new ModoSolitario();
                modosolitario.jugarModoSolitario(jugadores.get(0));

            }
            // MODO VERSUS
            else if (mododejuego.getModoJuego() == 2) {

                int numJugadores = mododejuego.getNumJugadores();

                List<Sabio> jugadoresVersus = RegistroDeJugadores.registrarJugador(numJugadores);
                RegistroDeJugadores.mostrarInfoJugador(jugadoresVersus);

                MensajeDurantePartida.mostrarInicioPartida();

                ModoVersus modoversus = new ModoVersus();
                modoversus.jugarModoVersus(jugadoresVersus, numJugadores);
            }

        } while (mododejuego.getModoJuego() != 9);
    }
}