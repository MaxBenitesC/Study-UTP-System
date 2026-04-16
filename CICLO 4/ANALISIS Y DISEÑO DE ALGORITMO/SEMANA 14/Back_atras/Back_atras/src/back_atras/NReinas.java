package back_atras;

/**
 * Ejemplo de Backtracking combinatorio: problema de las N-Reinas.
 */
public class NReinas {
    static int N = 4;
    static int[][] tablero = new int[N][N];

    static boolean esSeguro(int fila, int col) {
        for (int i = 0; i < fila; i++)
            if (tablero[i][col] == 1) return false;
        for (int i = fila - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
            if (tablero[i][j] == 1) return false;
        for (int i = fila - 1, j = col + 1; i >= 0 && j < N; i--, j++)
            if (tablero[i][j] == 1) return false;
        return true;
    }

    static boolean resolver(int fila) {
        if (fila == N) return true;
        for (int col = 0; col < N; col++) {
            if (esSeguro(fila, col)) {
                tablero[fila][col] = 1;
                if (resolver(fila + 1)) return true;
                tablero[fila][col] = 0; // backtrack
            }
        }
        return false;
    }

    static void mostrar() {
        System.out.println("\nSolución encontrada:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(tablero[i][j] == 1 ? " Q " : " . ");
            System.out.println();
        }
    }

    public static void ejecutar() {
        System.out.println("\n=== Problema de las N-Reinas ===");
        if (resolver(0)) mostrar();
        else System.out.println("No se encontró solución.");
    }
}
