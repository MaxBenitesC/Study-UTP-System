package back_atras;

import java.util.Scanner;

public class Laberinto {
    static int N;
    static int[][] laberinto;
    static int[][] solucion;

    static boolean resolver(int x, int y) {
        if (x == N - 1 && y == N - 1) { // Llegó al destino
            solucion[x][y] = 1;
            return true;
        }

        if (esValido(x, y)) {
            solucion[x][y] = 1;

            // derecha, abajo, izquierda, arriba
            if (resolver(x, y + 1)) return true;
            if (resolver(x + 1, y)) return true;
            if (resolver(x, y - 1)) return true;
            if (resolver(x - 1, y)) return true;

            solucion[x][y] = 0; // backtrack
        }
        return false;
    }

    static boolean esValido(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N &&
               laberinto[x][y] == 1 && solucion[x][y] == 0;
    }

    static void mostrar() {
        System.out.println("\nCamino encontrado:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(solucion[i][j] + " ");
            System.out.println();
        }
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIngrese el tamaño del laberinto n (ej. 6 o 10): ");
        N = sc.nextInt();

        laberinto = new int[N][N];
        solucion = new int[N][N];

        System.out.println("\nIngrese la matriz del laberinto (" + N + "x" + N + "), use 1=caminos, 0=muros:");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                laberinto[i][j] = sc.nextInt();
            }
        }

        System.out.println("\n=== Resolviendo laberinto " + N + "x" + N + " ===");
        if (resolver(0, 0)) mostrar();
        else System.out.println("No hay salida posible.");
    }
}