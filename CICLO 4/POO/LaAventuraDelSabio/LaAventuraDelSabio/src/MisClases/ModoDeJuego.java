package MisClases;

import java.util.Scanner;

/**
 * Clase que representa la lógica de selección del modo de juego.
 * Permite elegir entre los modos: Solitario, Versus y Salir del juego.
 */
public class ModoDeJuego {

    private int modoJuego;
    private int numJugadores;

    /**
     * Muestra el menú principal al usuario y le permite seleccionar un modo de juego.
     * También gestiona la cantidad de jugadores para el modo Versus.
     */
    public void eleccionModoJuego() {
        Scanner teclado = new Scanner(System.in);
        do {
            MensajeDurantePartida.mostrarMenuPrincipal();
            System.out.print("Elige una opción: ");
            modoJuego = teclado.nextInt();
            switch (modoJuego) {
                case 1:
                    System.out.println("---------Modo Solitario seleccionado---------");
                    numJugadores = 1;
                    break;
                case 2:
                    System.out.println("---------Modo Versus seleccionado---------");
                    do {
                        System.out.print("Ingrese cantidad de jugadores (2 o 3): ");
                        numJugadores = teclado.nextInt();
                        if (numJugadores != 2 && numJugadores != 3) {
                            System.out.println("Cantidad de jugadores inválido! Elegir 2 o 3.");
                        }
                    } while (numJugadores != 2 && numJugadores != 3);
                    break;
                case 9:
                    MensajeDurantePartida.mostrarFinalPartida();
                    return;
                default:
                    System.out.println("Opción inválida. Intenta nuevamente.");
            }
        } while (modoJuego != 1 && modoJuego != 2);
    }

    /**
     * Retorna el modo de juego seleccionado por el usuario.
     * @return 1 para Solitario, 2 para Versus, 9 para Salir.
     */
    public int getModoJuego() {
        return modoJuego;
    }

    /**
     * Retorna la cantidad de jugadores ingresada por el usuario.
     * @return Número de jugadores (1, 2 o 3 dependiendo del modo).
     */
    public int getNumJugadores() {
        return numJugadores;
    }
}