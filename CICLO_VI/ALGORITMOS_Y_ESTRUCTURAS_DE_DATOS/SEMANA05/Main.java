import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        double[] ventas = new double[10];
        double ventatotal = 0.0;

        // ==========================================
        // ENTRADA DE DATOS
        // ==========================================
        System.out.println("===================================");
        System.out.println("Ingrese la venta de los vendedores:");         
        System.out.println("===================================");

        for (int i = 0; i < ventas.length; i++) {
            System.out.println("Ingrese venta del vendedor " + (i + 1) + ":");
            ventas[i] = teclado.nextDouble();
        }

        System.out.println("===================================");
        System.out.println("Ingrese el monto de venta a buscar:");
        double montoBuscar = teclado.nextDouble();
        System.out.println();

        //Calculando el total de ventas
        for (int i = 0; i < ventas.length; i++) {
            ventatotal += ventas[i];
        }

        //Caludnaod el promedio
        double promedioVentas = ventatotal / ventas.length;

        //caclculando el vendedor que obtuvo la mayor y menor venta
        double mayorVenta = ventas[0];
        int vendedorMayor = 0;
        double menorVenta = ventas[0];
        int vendedorMenor = 0;
        for (int i = 1; i < ventas.length; i++) {
            if (ventas[i] > mayorVenta) {
                mayorVenta = ventas[i];
                vendedorMayor = i;
            }
            if (ventas[i] < menorVenta) {
                menorVenta = ventas[i];
                vendedorMenor = i;
            }
        }
        //Calculando los vendedores que pasaron el promedio
        int vendeSuperPromedio=0;
        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] > promedioVentas) {
                vendeSuperPromedio++;
            }
        }
        //Calculo de busqueda de una venta
        int posicionEncontrada=-1;
        for (int i = 0; i < ventas.length; i++) {
            if (ventas[i] == montoBuscar) {
                posicionEncontrada = i;
                break; 
            }
        }

        // SALIDA
        System.out.println("================================");
        System.out.println("Informacion de Ventas Obtenidas");
        System.out.println("================================");
        System.out.printf("%-12s", "Vendedor:");
        for (int i = 0; i < ventas.length; i++) {
            System.out.printf("%-8d", (i + 1));
        }
        System.out.println();
        System.out.printf("%-12s", "Ventas:");
        for (int i = 0; i < ventas.length; i++) {
            System.out.printf("%-8.2f", ventas[i]);
        }
        System.out.println();
        System.out.printf("Venta Total de la Empresa: S/. %.2f%n", ventatotal);
        System.out.printf("Promedio de Ventas: S/. %.2f%n", promedioVentas);
        System.out.printf("El vendedor %d obtuvo la mayor venta con S/. %.2f%n", (vendedorMayor + 1), mayorVenta);
        System.out.printf("El vendedor %d obtuvo la menor venta con S/. %.2f%n", (vendedorMenor + 1), menorVenta);
        System.out.printf("Cantidad de vendedores que superaron el promedio: %d%n", vendeSuperPromedio);

        if (posicionEncontrada != -1) {
            System.out.printf("El monto S/. %.2f se encuentra registrado y corresponde al Vendedor %d.%n", 
                              montoBuscar, (posicionEncontrada + 1));
        } else {
            System.out.printf("El monto S/. %.2f NO se encuentra registrado en el vector.%n", montoBuscar);
        }
    }
}
