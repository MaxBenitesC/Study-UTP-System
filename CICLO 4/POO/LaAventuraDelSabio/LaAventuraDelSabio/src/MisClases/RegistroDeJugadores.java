package MisClases;
import LaAventuraDelSabio.*;
import InterfaceAbstracta.Sabio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class RegistroDeJugadores {
    private static final Map<Integer, String> ELEMENTOS_MAP = new HashMap<>();
    static {
        ELEMENTOS_MAP.put(1, "fuego");
        ELEMENTOS_MAP.put(2, "agua");
        ELEMENTOS_MAP.put(3, "tierra");
    }

    public static List<Sabio> registrarJugador(int numJugadores) {
        List<Sabio> jugadores = new ArrayList<>();
        Scanner teclado = new Scanner(System.in);
        String nombre;
        int opcionRol;
        for (int i = 0; i < numJugadores; i++) {
            MensajeDurantePartida.mostrarRegistroJugador(i);
            System.out.print("Ingrese su nombre: ");
            nombre = teclado.next();
            String rolElegido;
            do {
                MensajeDurantePartida.mostrarElementosSabio(nombre);
                System.out.print("Opción → ");
                opcionRol = teclado.nextInt();

                rolElegido = ELEMENTOS_MAP.get(opcionRol);

                if (rolElegido == null) {
                    System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } while (rolElegido == null);
            Sabio nuevoSabio = switch (rolElegido) {
                case "agua" -> new SabioAgua(nombre);
                case "tierra" -> new SabioTierra(nombre);
                default -> new SabioFuego(nombre);
            };
            jugadores.add(nuevoSabio);
        }
        return jugadores;
    }

    public static void mostrarInfoJugador(List<Sabio> jugadores) {
        System.out.println("\n╔═══════════════════════════════════╗");
        System.out.println("║       DETALLE DE JUGADORES        ║");
        System.out.println("╠════════════╦═════════╦══════╦═════╣");
        System.out.printf("║ %-10s │ %-7s │ %-4s │ %-3s ║\n","NOMBRE", "ROL", "PS", "XP");
        System.out.println("╠════════════╬═════════╬══════╬═════╣");
        for (Sabio sabio : jugadores) {
            System.out.printf("║ %-10s │ %-7s │ %-4d │ %-3d ║\n",
                    sabio.getNombre().toUpperCase(),
                    sabio.getRol().toUpperCase(),
                    sabio.getPs(),
                    sabio.getXp());
        }
        System.out.println("╚════════════╩═════════╩══════╩═════╝");
    }
}