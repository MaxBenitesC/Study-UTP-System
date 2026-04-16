package MisClases;
import java.util.List;
import InterfaceAbstracta.Sabio;

public class ResumenPartida {
    public static void mostrarResumenSolitario(Sabio jugador, int respuestasCorrectas, int respuestasIncorrectas) {
        System.out.println("\n═══════════════════════════════════════════════");
        System.out.println("\t\t\tRESUMEN DE TU PARTIDA");
        System.out.println("═══════════════════════════════════════════════");
        System.out.printf("Jugador: %s\n", jugador.getNombre());
        System.out.printf("Rol: %s\n", jugador.getRol().toUpperCase());
        System.out.println("-----------------------------------------------");
        System.out.printf("%-25s %5d\n", "Respuestas Correctas:", respuestasCorrectas);
        System.out.printf("%-25s %5d\n", "Respuestas Incorrectas:", respuestasIncorrectas);
        System.out.printf("%-25s %5d\n", "XP Acumulado:", jugador.getXp());
        System.out.printf("%-25s %5d\n", "PS Final:", jugador.getPs());
        System.out.println("-----------------------------------------------");
        if (jugador.getXp() >= 40) {
            System.out.println("\n═══════════════════════════════════════════════════════");
            System.out.println("Felicidades, has demostrado ser un gran Sabio del " + jugador.getRol());
        } else {
            System.out.println("Aún te falta mucho por mejorar.");
        }
        System.out.println("═══════════════════════════════════════════════════════");
    }

    public static void mostrarResumenVersus(List<Sabio> jugadores, int[] correctas, int[] incorrectas) {
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                              RESUMEN FINAL DE PARTIDA                              ║");
        System.out.println("╠════════════╦════════════╦═════════════════╦════════════════════╦════════╦══════════╣");
        System.out.println("║ JUGADOR    ║ ROL        ║ RESP. CORRECTAS ║ RESP. INCORRECTAS  ║ XP     ║ PS FINAL ║");
        System.out.println("╠════════════╬════════════╬═════════════════╬════════════════════╬════════╬══════════╣");
        int maxCorrectas = -1;
        String ganador = "Nadie (todos K.O.)";
        for (int i = 0; i < jugadores.size(); i++) {
            Sabio sabio = jugadores.get(i);
            System.out.printf("║ %-10s ║ %-10s ║ %-15d ║ %-18d ║ %-6d ║ %-8d ║\n",
                    sabio.getNombre(),
                    sabio.getRol().toUpperCase(),
                    correctas[i],
                    incorrectas[i],
                    sabio.getXp(),
                    sabio.getPs());
            if (correctas[i] > maxCorrectas) {
                maxCorrectas = correctas[i];
                ganador = sabio.getNombre();
            }
        }
        System.out.println("╚════════════╩════════════╩═════════════════╩════════════════════╩════════╩══════════╝");
        long jugadoresVivos = jugadores.stream().filter(Sabio::estaVivo).count();
        if (jugadoresVivos == 1) {
            ganador = jugadores.stream().filter(Sabio::estaVivo).findFirst().get().getNombre() + " (Único Sobreviviente)";
        } else if (maxCorrectas > -1) {
            ganador = "El más Sabio: " + ganador;
        }
        System.out.printf("\nResultado: %s con %d respuestas correctas.\n", ganador, maxCorrectas);
    }
}