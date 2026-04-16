package back_atras;

import back_atras.Factorial;
import back_atras.Arbol;
import back_atras.Laberinto;
import back_atras.NReinas;
import java.util.Scanner;



/**
 * Laboratorio de Backtracking hacia atrás en Java.
 * Incluye: Factorial, Laberinto, N-Reinas y Árbol Binario.
 */
public class MenuPrincipal {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n========================================");
            System.out.println("   LABORATORIO DE BACKTRACKING HACIA ATRÁS");
            System.out.println("========================================");
            System.out.println("1. Factorial Recursivo");
            System.out.println("2. Laberinto 10x10");
            System.out.println("3. Problema de las N-Reinas");
            System.out.println("4. Árbol Binario (Camino con Suma Objetivo)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    Factorial.ejecutar();
                    break;
                case 2:
                    Laberinto.ejecutar();
                    break;
                case 3:
                    NReinas.ejecutar();
                    break;
                case 4:
                    Arbol.ejecutar();
                    break;
                case 0:
                    System.out.println("Fin del programa. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        } while (opcion != 0);

        sc.close();
    }
}
