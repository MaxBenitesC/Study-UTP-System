import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] ventasCentro = {120, 350, 180, 420, 250, 300};
        int[] ventasNorte = {120, 350, 180, 420, 250, 300};

        System.out.println("1. COMPARACIÓN DE LOS REGISTROS DE VENTAS");
        System.out.println("Ventas Tienda Centro: " + Arrays.toString(ventasCentro));
        System.out.println("Ventas Tienda Norte:  " + Arrays.toString(ventasNorte));
        System.out.println("¿Los registros de ventas son iguales? " + (Arrays.equals(ventasCentro, ventasNorte) ? "Si" : "No"));
        System.out.println();

        System.out.println("2. CREACIÓN DE UNA COPIA DE SEGURIDAD");
        int[] ventasRespaldo = ventasCentro.clone();

        System.out.println("Estado Inicial antes de modificar:");
        System.out.println("Ventas originales Tienda Centro: " + Arrays.toString(ventasCentro));
        System.out.println("Copia de seguridad:         " + Arrays.toString(ventasRespaldo));
        System.out.println();

        System.out.println("Actualizamos la posición [2] a 200");
        ventasCentro[2] = 200;
        System.out.println();

        System.out.println("Despues del cambio:");
        System.out.println("Ventas actuales Tienda Centro:   " + Arrays.toString(ventasCentro));
        System.out.println("Copia de seguridad: " + Arrays.toString(ventasRespaldo));
        System.out.println();

        System.out.println("3. CONSOLIDACIÓN DE LAS VENTAS");
        int[] ventasConsolidadas = new int[ventasCentro.length + ventasNorte.length];
        System.arraycopy(ventasCentro, 0, ventasConsolidadas, 0, ventasCentro.length);
        System.arraycopy(ventasNorte, 0, ventasConsolidadas, ventasCentro.length, ventasNorte.length);

        System.out.println("Tienda Centro:");
        System.out.println(Arrays.toString(ventasCentro));
        System.out.println("Tienda Norte:");
        System.out.println(Arrays.toString(ventasNorte));
        System.out.println("Ventas consolidadas:");
        System.out.println(Arrays.toString(ventasConsolidadas));
        System.out.println();

        System.out.println("4. REPORTE GERENCIAL");
        int cantidadVentas = ventasConsolidadas.length;
        int totalVendido = 0;
        int mayorVenta = ventasConsolidadas[0];
        int menorVenta = ventasConsolidadas[0];
        int ventasSuperiores300 = 0;

        for (int i = 0; i < ventasConsolidadas.length; i++) {
            int venta = ventasConsolidadas[i];
            totalVendido += venta;

            if (venta > mayorVenta) {
                mayorVenta = venta;
            }
            if (venta < menorVenta) {
                menorVenta = venta;
            }
            if (venta > 300) {
                ventasSuperiores300++;
            }
        }

        double promedioVenta = (double) totalVendido / cantidadVentas;

        System.out.println("========== REPORTE GENERAL DE VENTAS ==========");
        System.out.println("Cantidad total de ventas realizadas: " + cantidadVentas);
        System.out.printf("Monto total vendido por la empresa:  S/ %d.00%n", totalVendido);
        System.out.printf("Promedio de venta:                   S/ %.2f%n", promedioVenta);
        System.out.printf("Mayor venta:                         S/ %d.00%n", mayorVenta);
        System.out.printf("Menor venta:                         S/ %d.00%n", menorVenta);
        System.out.println("Cantidad de ventas superiores a S/ 300: " + ventasSuperiores300);
        System.out.println("===============================================");
    }
}
