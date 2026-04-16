package MisClases;

import InterfaceAbstracta.Sabio;
import java.util.Scanner;

/**
 * Gestiona el modo de juego en solitario dentro del videojuego
 * "La Aventura del Sabio".
 * <p>
 * Esta clase controla:
 * <ul>
 *   <li>El flujo de preguntas al jugador.</li>
 *   <li>La validación de respuesta ingresada.</li>
 *   <li>La asignación de XP o daño según la respuesta.</li>
 *   <li>El resumen final y la opción de volver a jugar.</li>
 * </ul>
 *
 * Se ejecuta cuando el usuario selecciona el modo "Solitario".
 */
public class ModoSolitario {

    /** Generador de preguntas y opciones para el modo solitario. */
    Preguntas generadordePreguntas = new Preguntas();

    /** Contador de respuestas correctas durante la partida. */
    private int respuestasCorrectas = 0;

    /** Contador de respuestas incorrectas durante la partida. */
    private int respuestasIncorrectas = 0;

    /**
     * Inicia y gestiona una partida en modo solitario.
     * <p>
     * El jugador deberá responder 10 preguntas (o menos si pierde todos sus PS).
     * Tras cada respuesta se evalúa si es correcta o incorrecta.
     * Al finalizar la partida se muestra un resumen completo y se ofrece
     * la opción de volver a jugar.
     *
     * @param jugador El objeto {@link Sabio} que participa en el modo solitario.
     */
    public void jugarModoSolitario(Sabio jugador) {
        Scanner teclado = new Scanner(System.in);
        char opcSeleccionada;

        for (int turno = 0; turno < 10; turno++) {

            // Si el jugador está sin vida, termina la partida
            if (!jugador.estaVivo()) {
                System.out.println("¡" + jugador.getNombre() + " se ha quedado sin PS! Fin de la partida.");
                break;
            }

            // Preguntar hasta recibir una opción válida
            do {
                generadordePreguntas.mostrarPreguntaCompleta(turno, jugador, turno);
                System.out.print(" Elige tu respuesta: ");
                opcSeleccionada = teclado.next().toLowerCase().charAt(0);

                if (opcSeleccionada != 'a' && opcSeleccionada != 'b' &&
                        opcSeleccionada != 'c' && opcSeleccionada != 'd') {

                    System.out.println("\nOPCIÓN INVÁLIDA. Debes ingresar a, b, c o d.");
                }

            } while (opcSeleccionada != 'a' && opcSeleccionada != 'b' &&
                    opcSeleccionada != 'c' && opcSeleccionada != 'd');

            validarRespuesta(jugador, opcSeleccionada, turno);
        }

        // Mostrar el resumen final
        ResumenPartida.mostrarResumenSolitario(jugador, respuestasCorrectas, respuestasIncorrectas);

        // Opción para volver a jugar
        int decision;
        do {
            MensajeDurantePartida.mostrarJugarNuevamente();
            System.out.print("Elige una opción: ");
            decision = teclado.nextInt();

            if (decision == 1) {
                jugador.recibirDano(-50);  // Restaura PS para rejugar
                jugarModoSolitario(jugador); // Reinicia la partida
            }

        } while (decision != 1 && decision != 2);
    }

    /**
     * Valida la respuesta ingresada por el jugador y aplica las consecuencias:
     * sumar XP si es correcta, o restar PS si es incorrecta.
     *
     * @param jugador          El sabio participante.
     * @param respuestaUsuario La opción seleccionada ('a', 'b', 'c' o 'd').
     * @param turno            Número de pregunta actual.
     */
    public void validarRespuesta(Sabio jugador, char respuestaUsuario, int turno) {

        if (respuestaUsuario == generadordePreguntas.getRespuesta(turno).charAt(0)) {

            System.out.println("¡Correcto! Ganas 10 XP.");
            jugador.sumarXp(10);
            respuestasCorrectas++;

        } else {

            System.out.println("Incorrecto. Pierdes 5 PS.");
            System.out.println("La respuesta correcta era: " + generadordePreguntas.getRespuesta(turno));

            jugador.recibirDano(5);
            respuestasIncorrectas++;
        }
    }
}