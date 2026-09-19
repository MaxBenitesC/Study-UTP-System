import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo"};

        int[][] ventas = {
            {12500, 13800, 14200, 15000, 14800},
            {10800, 11500, 12100, 11900, 13000},
            {15200, 14900, 15800, 16200, 17000},
            { 9800, 10500, 11000, 10800, 11600}
        };

        int[] totalTiendas = new int[ventas.length];
        int[] totalMeses = new int[meses.length];
        int cantidadMayoresA14000 = 0;
        int mayorVentaIndividual = ventas[0][0];
        int tiendaMayorVentaIndividual = 0;
        int mesMayorVentaIndividual = 0;

        // Recorremos toda la matriz para realizar los cálculos principales.
        for (int i = 0; i < ventas.length; i++) {
            for (int j = 0; j < ventas[i].length; j++) {
                totalTiendas[i] += ventas[i][j];
                totalMeses[j] += ventas[i][j];

                if (ventas[i][j] > 14000) {
                    cantidadMayoresA14000++;
                }

                if (ventas[i][j] > mayorVentaIndividual) {
                    mayorVentaIndividual = ventas[i][j];
                    tiendaMayorVentaIndividual = i;
                    mesMayorVentaIndividual = j;
                }
            }
        }

        int tiendaConMayorTotal = 0;
        int tiendaConMenorTotal = 0;

        for (int i = 1; i < totalTiendas.length; i++) {
            if (totalTiendas[i] > totalTiendas[tiendaConMayorTotal]) {
                tiendaConMayorTotal = i;
            }

            if (totalTiendas[i] < totalTiendas[tiendaConMenorTotal]) {
                tiendaConMenorTotal = i;
            }
        }

        int mesConMayorTotal = 0;

        for (int j = 1; j < totalMeses.length; j++) {
            if (totalMeses[j] > totalMeses[mesConMayorTotal]) {
                mesConMayorTotal = j;
            }
        }

        int totalEmpresa = 0;
        for (int totalTienda : totalTiendas) {
            totalEmpresa += totalTienda;
        }

        System.out.println("============ COMERCIAL NOVA ============");
        System.out.println("\nMATRIZ DE VENTAS (S/)\n");

        System.out.printf("%-12s", "");
        for (String mes : meses) {
            System.out.printf("%12s", mes);
        }
        System.out.println();

        for (int i = 0; i < ventas.length; i++) {
            System.out.printf("%-12s", "Tienda " + (i + 1));
            for (int j = 0; j < ventas[i].length; j++) {
                System.out.printf("%,12d", ventas[i][j]);
            }
            System.out.println();
        }

        System.out.println("\n--------- RESULTADOS ---------");
        System.out.println("\nTOTAL VENDIDO POR TIENDA");
        for (int i = 0; i < totalTiendas.length; i++) {
            System.out.printf("Total Tienda %d: S/ %,d%n", i + 1, totalTiendas[i]);
        }

        System.out.println("\nTOTAL VENDIDO POR MES");
        for (int j = 0; j < totalMeses.length; j++) {
            System.out.printf("Total %s: S/ %,d%n", meses[j], totalMeses[j]);
        }

        System.out.printf("%nTienda con mayores ventas: Tienda %d - S/ %,d%n",
                tiendaConMayorTotal + 1, totalTiendas[tiendaConMayorTotal]);
        System.out.printf("Tienda con menores ventas: Tienda %d - S/ %,d%n",
                tiendaConMenorTotal + 1, totalTiendas[tiendaConMenorTotal]);
        System.out.printf("Mes con mayores ventas: %s - S/ %,d%n",
                meses[mesConMayorTotal], totalMeses[mesConMayorTotal]);

        System.out.println("\nPROMEDIO DE VENTAS POR TIENDA");
        for (int i = 0; i < totalTiendas.length; i++) {
            double promedio = (double) totalTiendas[i] / ventas[i].length;
            System.out.printf("Promedio Tienda %d: S/ %,.2f%n", i + 1, promedio);
        }

        System.out.printf("%nVentas mayores a S/ 14,000: %d%n", cantidadMayoresA14000);
        System.out.println("\nMAYOR VENTA INDIVIDUAL");
        System.out.printf("S/ %,d - Tienda %d - Mes %s%n",
                mayorVentaIndividual,
                tiendaMayorVentaIndividual + 1,
                meses[mesMayorVentaIndividual]);

        System.out.println("\n--------- REPORTE GERENCIAL ---------");
        System.out.printf("Venta total de la empresa: S/ %,d%n", totalEmpresa);
        System.out.printf("La Tienda %d presentó el mejor desempeño del periodo.%n",
                tiendaConMayorTotal + 1);
        System.out.printf("La Tienda %d obtuvo el menor total y requiere seguimiento.%n",
                tiendaConMenorTotal + 1);
        System.out.printf("%s fue el mes con mayor venta para Comercial Nova.%n",
                meses[mesConMayorTotal]);
        System.out.printf("Se registraron %d ventas mensuales superiores a S/ 14,000.%n",
                cantidadMayoresA14000);
    }
}
