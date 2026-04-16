package back_atras;

import java.util.Scanner;

/**
 * Ejemplo de recursividad simple que calcula el factorial de un número.
 */
public class Factorial {

    public static int factorial(int n) {
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    public static void ejecutar() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIngrese un número: ");
        int n = sc.nextInt();
        System.out.println("Factorial de " + n + " = " + factorial(n));
    }
}
