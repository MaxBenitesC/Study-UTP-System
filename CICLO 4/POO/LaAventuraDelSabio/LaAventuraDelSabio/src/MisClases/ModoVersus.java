package MisClases;

import java.util.List;
import java.util.Scanner;
import InterfaceAbstracta.Personaje;
import InterfaceAbstracta.Sabio;

/**
 * La clase {@code ModoVersus} permite jugar una partida multijugador (2 o 3 jugadores)
 * en modo versus, donde los jugadores responden preguntas por turnos y pueden
 * atacarse entre ellos si responden correctamente.
 * <p>
 * Cada jugador que acierta una pregunta puede atacar a otro jugador (según reglas del modo).
 * Gana el jugador que queda en pie o el que tenga mayor número de respuestas correctas.
 * </p>
 */
public class ModoVersus {
    Preguntas generadordePreguntas = new Preguntas();

    /**
     * Inicia una partida en modo versus.
     * Se reparten 10 turnos generales, y cada jugador responde preguntas en su turno.
     * Si un jugador responde correctamente, puede atacar a un oponente.
     *
     * @param jugadores Lista de sabios participantes.
     * @param cantidadJugadores Número total de jugadores (debe ser 2 o 3).
     */
    public void jugarModoVersus(List<Sabio> jugadores, int cantidadJugadores){
        int[] respuestasCorrectas = new int[cantidadJugadores];
        int[] respuestasIncorrectas = new int[cantidadJugadores];
        Scanner teclado = new Scanner(System.in);
        char opcSeleccionada;

        for (int turno = 0; turno < 10; turno++) {
            long jugadoresVivos = jugadores.stream().filter(Sabio::estaVivo).count();
            if (jugadoresVivos <= 1) {
                System.out.println("La partida ha terminado anticipadamente, solo queda un Sabio.");
                break;
            }

            for (int i = 0; i < cantidadJugadores; i++) {
                Sabio atacante = jugadores.get(i);
                if (!atacante.estaVivo()) {
                    System.out.println("\nEl Sabio " + atacante.getNombre() + " está K.O. y no puede participar.");
                    continue;
                }

                int numPregunta = turno * cantidadJugadores + i;

                // Validación de la opción ingresada por el jugador
                do {
                    generadordePreguntas.mostrarPreguntaCompleta(numPregunta, atacante, turno);
                    System.out.print(" Elige tu respuesta: ");
                    opcSeleccionada = teclado.next().toLowerCase().charAt(0);
                    if (opcSeleccionada != 'a' && opcSeleccionada != 'b' && opcSeleccionada != 'c' && opcSeleccionada != 'd') {
                        System.out.println("\nOPCIÓN INVÁLIDA. Debes ingresar a, b, c o d.");
                    }
                } while (opcSeleccionada != 'a' && opcSeleccionada != 'b' &&
                        opcSeleccionada != 'c' && opcSeleccionada != 'd');

                validarRespuesta(jugadores, atacante, i, opcSeleccionada, numPregunta,
                        respuestasCorrectas, respuestasIncorrectas, cantidadJugadores);
            }
        }

        // Muestra el resumen final
        ResumenPartida.mostrarResumenVersus(jugadores, respuestasCorrectas, respuestasIncorrectas);

        // Opción para volver a jugar
        int decision;
        do {
            MensajeDurantePartida.mostrarJugarNuevamente();
            System.out.print("Elige una opción: ");
            decision = teclado.nextInt();
            if (decision == 1) {
                for (Sabio sabio : jugadores) {
                    sabio.recibirDano(-1000); // Restaura PS
                }
                jugarModoVersus(jugadores, cantidadJugadores);
            }
        } while (decision != 1 && decision != 2);
    }

    /**
     * Valida la respuesta de un jugador. Si es correcta, gana XP y puede atacar.
     * Si es incorrecta, pierde PS. Gestiona ataques según la cantidad de jugadores.
     *
     * @param jugadores Lista de jugadores en la partida.
     * @param atacante Jugador que responde y potencialmente ataca.
     * @param atacanteIndex Índice del atacante en la lista.
     * @param respuestaUsuario Respuesta seleccionada (a, b, c, d).
     * @param turno Número de turno actual (para seleccionar pregunta).
     * @param respuestasCorrectas Arreglo que acumula respuestas correctas por jugador.
     * @param respuestasIncorrectas Arreglo que acumula respuestas incorrectas por jugador.
     * @param cantidadJugadores Número total de jugadores en la partida.
     */
    public void validarRespuesta(List<Sabio> jugadores, Sabio atacante, int atacanteIndex, char respuestaUsuario, int turno,
                                 int[] respuestasCorrectas, int[] respuestasIncorrectas, int cantidadJugadores) {
        Scanner teclado = new Scanner(System.in);

        if (respuestaUsuario == generadordePreguntas.getRespuesta(turno).charAt(0)) {
            System.out.println("¡Correcto! Ganas 10 XP.");
            atacante.sumarXp(10);
            respuestasCorrectas[atacanteIndex]++;
            int DANO_BASE = 10;

            if (cantidadJugadores == 2) {
                Sabio victima = (atacanteIndex == 0) ? jugadores.get(1) : jugadores.get(0);
                if (victima.estaVivo()) {
                    atacante.atacar(victima, DANO_BASE);
                } else {
                    System.out.println("El único rival restante está K.O.");
                }

            } else if (cantidadJugadores == 3) {
                int rivalElegidoIndex;
                Personaje rivalElegido;

                do {
                    System.out.println("\n¿A quién deseas atacar?");
                    for (int i = 0; i < cantidadJugadores; i++) {
                        if (i != atacanteIndex && jugadores.get(i).estaVivo()) {
                            System.out.println("[" + (i + 1) + "] " + jugadores.get(i).getNombre() +
                                    " (PS: " + jugadores.get(i).getPs() + ")");
                        }
                    }
                    System.out.print("Elige el número del jugador: ");
                    rivalElegidoIndex = teclado.nextInt() - 1;
                    rivalElegido = (rivalElegidoIndex >= 0 && rivalElegidoIndex < cantidadJugadores) ?
                            jugadores.get(rivalElegidoIndex) : null;

                    if (rivalElegidoIndex == atacanteIndex || rivalElegido == null || !rivalElegido.estaVivo()) {
                        System.out.println("Opción inválida. Debes elegir un rival vivo y válido.");
                    }
                } while (rivalElegidoIndex == atacanteIndex || rivalElegido == null || !rivalElegido.estaVivo());

                atacante.atacar(rivalElegido, DANO_BASE);
            }

        } else {
            System.out.println("Incorrecto. Pierdes 5 PS.");
            System.out.println("La respuesta correcta era: " + generadordePreguntas.getRespuesta(turno));
            atacante.recibirDano(5);
            respuestasIncorrectas[atacanteIndex]++;
            if (!atacante.estaVivo()) {
                System.out.println("¡El Sabio " + atacante.getNombre() + " se ha quedado sin PS y fue eliminado!");
            }
        }
    }
}