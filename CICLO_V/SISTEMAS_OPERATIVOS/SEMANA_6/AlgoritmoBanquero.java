import java.util.Scanner;

public class AlgoritmoBanquero {

    static int n, m;
    static int[] recursos;
    static int[] disponible;
    static int[][] demanda;
    static int[][] asignacion;
    static int[][] necesidad;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║     ALGORITMO DEL BANQUERO       ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.print("\nNumero de procesos : ");
        n = sc.nextInt();
        System.out.print("Numero de recursos : ");
        m = sc.nextInt();

        recursos  = new int[m];
        disponible = new int[m];
        demanda    = new int[n][m];
        asignacion = new int[n][m];
        necesidad  = new int[n][m];

        // Vector de recursos totales
        System.out.println("\n--- Vector de Recursos Totales ---");
        for (int j = 0; j < m; j++) {
            System.out.print("  R" + (j + 1) + ": ");
            recursos[j] = sc.nextInt();
        }

        // Matriz de demanda (Max)
        System.out.println("\n--- Matriz de Demanda (Max) ---");
        for (int i = 0; i < n; i++) {
            System.out.println("  Proceso P" + (i + 1) + ":");
            for (int j = 0; j < m; j++) {
                System.out.print("    R" + (j + 1) + ": ");
                demanda[i][j] = sc.nextInt();
            }
        }

        // Matriz de asignacion actual
        System.out.println("\n--- Matriz de Asignacion Actual ---");
        for (int i = 0; i < n; i++) {
            System.out.println("  Proceso P" + (i + 1) + ":");
            for (int j = 0; j < m; j++) {
                System.out.print("    R" + (j + 1) + ": ");
                asignacion[i][j] = sc.nextInt();
            }
        }

        // Calcular Necesidad = Demanda - Asignacion
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                necesidad[i][j] = demanda[i][j] - asignacion[i][j];

        // Calcular Disponible = Recursos - suma(Asignacion)
        int[] sumaAsignacion = new int[m];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                sumaAsignacion[j] += asignacion[i][j];
        for (int j = 0; j < m; j++)
            disponible[j] = recursos[j] - sumaAsignacion[j];

        // Mostrar datos calculados
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║         DATOS CALCULADOS         ║");
        System.out.println("╚══════════════════════════════════╝");

        System.out.println("\nMatriz de Necesidad (Demanda - Asignacion):");
        imprimirEncabezado();
        for (int i = 0; i < n; i++) {
            System.out.printf("  P%-3d", i + 1);
            for (int j = 0; j < m; j++) System.out.printf("%-5d", necesidad[i][j]);
            System.out.println();
        }

        System.out.print("\nVector Disponible: ");
        for (int j = 0; j < m; j++) System.out.print("R" + (j + 1) + "=" + disponible[j] + "  ");
        System.out.println();

        // Ejecutar algoritmo de seguridad
        ejecutarAlgoritmoSeguridad();

        sc.close();
    }

    static void ejecutarAlgoritmoSeguridad() {
        int[] trabajo = disponible.clone();
        boolean[] fin = new boolean[n];
        int[] secuencia = new int[n];
        int count = 0;

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║      ALGORITMO DE SEGURIDAD      ║");
        System.out.println("╚══════════════════════════════════╝");
        System.out.printf("\n  %-5s %-18s %-6s %-18s%n", "Iter", "Trabajo antes", "Pi", "Trabajo despues");
        System.out.println("  " + "-".repeat(50));

        while (count < n) {
            boolean encontrado = false;
            for (int i = 0; i < n; i++) {
                if (!fin[i] && necesidadCabe(i, trabajo)) {
                    String antes = vectorStr(trabajo);
                    for (int j = 0; j < m; j++)
                        trabajo[j] += asignacion[i][j];
                    fin[i] = true;
                    secuencia[count++] = i;
                    encontrado = true;
                    System.out.printf("  %-5d %-18s P%-5d %-18s%n",
                            count, antes, i + 1, vectorStr(trabajo));
                    break;
                }
            }
            if (!encontrado) break;
        }

        System.out.println();
        System.out.println("╔══════════════════════════════════╗");
        System.out.println("║           RESULTADO              ║");
        System.out.println("╚══════════════════════════════════╝");

        if (count == n) {
            System.out.println("\n  >> ESTADO SEGURO: no hay interbloqueo.");
            System.out.print("  Secuencia segura: ");
            for (int i = 0; i < n; i++) {
                System.out.print("P" + (secuencia[i] + 1));
                if (i < n - 1) System.out.print(" -> ");
            }
            System.out.println();
        } else {
            System.out.println("\n  >> ESTADO INSEGURO: posible interbloqueo.");
            System.out.print("  Procesos que no pudieron completar: ");
            for (int i = 0; i < n; i++)
                if (!fin[i]) System.out.print("P" + (i + 1) + " ");
            System.out.println();
        }
    }

    static boolean necesidadCabe(int i, int[] trabajo) {
        for (int j = 0; j < m; j++)
            if (necesidad[i][j] > trabajo[j]) return false;
        return true;
    }

    static void imprimirEncabezado() {
        System.out.print("  " + " ".repeat(5));
        for (int j = 0; j < m; j++) System.out.printf("R%-4d", j + 1);
        System.out.println();
    }

    static String vectorStr(int[] v) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < v.length; i++) {
            sb.append(v[i]);
            if (i < v.length - 1) sb.append(",");
        }
        return sb.append("]").toString();
    }
}
