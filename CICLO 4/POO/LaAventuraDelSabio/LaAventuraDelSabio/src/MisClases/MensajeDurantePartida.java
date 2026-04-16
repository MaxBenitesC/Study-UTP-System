package MisClases;

/**
 * Clase utilitaria para mostrar mensajes durante el desarrollo del juego.
 * Incluye menús, mensajes de registro, selección de elementos y finalización.
 */
public class MensajeDurantePartida {

    /**
     * Muestra el menú principal del juego con las opciones disponibles.
     */
    public static void mostrarMenuPrincipal() {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║         LA AVENTURA DEL SABIO            ║");
        System.out.println("╠══════════════════════════════════════════╣");
        System.out.println("║ 1. Solitario (contra la máquina)         ║");
        System.out.println("║ 2. Versus (2 o 3 jugadores)              ║");
        System.out.println("║ 9. Salir del Juego                       ║");
        System.out.println("╚══════════════════════════════════════════╝");
    }

    /**
     * Muestra el encabezado para el registro de un jugador.
     *
     * @param i Índice del jugador (usualmente 0, 1 o 2).
     */
    public static void mostrarRegistroJugador(int i) {
        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║    REGISTRO DE JUGADOR " + (i + 1) + "    ║");
        System.out.println("╚═════════════════════════════╝");
    }

    /**
     * Muestra las opciones de elementos para que el sabio elija.
     *
     * @param nombre Nombre del sabio que está eligiendo el elemento.
     */
    public static void mostrarElementosSabio(String nombre) {
        System.out.println("Hola SABIO " + nombre.toUpperCase() + " ¿A qué elemento quieres pertenecer?:");
        System.out.println("┌─────┬──────────┐");
        System.out.println("│ [1] │ FUEGO    │");
        System.out.println("│ [2] │ AGUA     │");
        System.out.println("│ [3] │ TIERRA   │");
        System.out.println("└─────┴──────────┘");
    }

    /**
     * Muestra un mensaje indicando el inicio de la partida.
     */
    public static void mostrarInicioPartida() {
        System.out.println("\n══════════════════════════════════════════════");
        System.out.println("    LA AVENTURA COMIENZA... ¡PREPÁRATE! ");
        System.out.println("══════════════════════════════════════════════");
        System.out.println();
    }

    /**
     * Muestra un mensaje al finalizar la partida.
     */
    public static void mostrarFinalPartida() {
        System.out.println("Partida finalizada! Gracias por jugar.");
    }

    /**
     * Muestra las opciones para jugar nuevamente o volver al menú principal.
     */
    public static void mostrarJugarNuevamente() {
        System.out.println("\n¿Deseas volver a jugar?");
        System.out.println("[1] Sí");
        System.out.println("[2] Volver al menú principal");
    }
}