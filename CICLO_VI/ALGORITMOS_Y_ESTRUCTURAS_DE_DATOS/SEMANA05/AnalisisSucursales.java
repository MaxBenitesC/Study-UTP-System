import java.util.Scanner;

public class AnalisisSucursales {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[][] ventas = new int[4][5];
        int[] ventasPorSucursal = new int[4];
        int[] ventasPorProducto = new int[5];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print("Ingrese venta Sucursal " + (i + 1) + " Producto " + (j + 1) + ": ");
                ventas[i][j] = teclado.nextInt();
            }
        }

        System.out.print("Ingrese numero de producto a consultar (1 al 5): ");
        int prodBuscar = teclado.nextInt();

        // CALCULO
        int totalGeneral = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                ventasPorSucursal[i] += ventas[i][j];
                ventasPorProducto[j] += ventas[i][j];
                totalGeneral += ventas[i][j];
            }
        }

        double promedio = (double) totalGeneral / 4;

        int mayorSucursal = ventasPorSucursal[0];
        int posMayorSuc = 0;
        for (int i = 1; i < 4; i++) {
            if (ventasPorSucursal[i] > mayorSucursal) {
                mayorSucursal = ventasPorSucursal[i];
                posMayorSuc = i;
            }
        }

        int mayorProducto = ventasPorProducto[0];
        int posMayorProd = 0;
        for (int j = 1; j < 5; j++) {
            if (ventasPorProducto[j] > mayorProducto) {
                mayorProducto = ventasPorProducto[j];
                posMayorProd = j;
            }
        }

        int mayorVenta = ventas[0][0];
        int sucMayor = 0;
        int prodMayor = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (ventas[i][j] > mayorVenta) {
                    mayorVenta = ventas[i][j];
                    sucMayor = i;
                    prodMayor = j;
                }
            }
        }

        // SALIDA
        System.out.println("\nMatriz de Ventas:");
        System.out.println("Sucursal\tP1\tP2\tP3\tP4\tP5");
        for (int i = 0; i < 4; i++) {
            System.out.print("Sucursal " + (i + 1) + "\t");
            for (int j = 0; j < 5; j++) {
                System.out.print(ventas[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\nVector ventasPorSucursal:");
        System.out.println("S1\tS2\tS3\tS4");
        for (int i = 0; i < 4; i++) {
            System.out.print(ventasPorSucursal[i] + "\t");
        }
        System.out.println();

        System.out.println("\nVector ventasPorProducto:");
        System.out.println("P1\tP2\tP3\tP4\tP5");
        for (int j = 0; j < 5; j++) {
            System.out.print(ventasPorProducto[j] + "\t");
        }
        System.out.println();

        System.out.println("\nTotal general de unidades vendidas: " + totalGeneral);
        System.out.println("Sucursal con mayor cantidad de ventas: Sucursal " + (posMayorSuc + 1) + " con " + mayorSucursal);
        System.out.println("Producto mas vendido: P" + (posMayorProd + 1) + " con " + mayorProducto);

        System.out.println("\nSucursales que superaron el promedio de ventas (" + promedio + "):");
        for (int i = 0; i < 4; i++) {
            if (ventasPorSucursal[i] > promedio) {
                System.out.println("Sucursal " + (i + 1) + " con " + ventasPorSucursal[i]);
            }
        }

        System.out.println("\nVentas del Producto P" + prodBuscar + " en cada sucursal:");
        for (int i = 0; i < 4; i++) {
            System.out.println("Sucursal " + (i + 1) + ": " + ventas[i][prodBuscar - 1]);
        }

        System.out.println("\nMayor venta individual:");
        System.out.println("Sucursal " + (sucMayor + 1) + " - Producto P" + (prodMayor + 1) + " con " + mayorVenta + " unidades");

        teclado.close();
    }
}
