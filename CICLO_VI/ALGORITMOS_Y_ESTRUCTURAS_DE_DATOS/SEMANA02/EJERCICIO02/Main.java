import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        String[] codigos = new String[10];
        String[] productos = new String[10];
        double[] precios = new double[10];
        int[] stock = new int[10];

        int cantidad = 5;

        codigos[0] = "P001";
        productos[0] = "Laptop";
        precios[0] = 2500.00;
        stock[0] = 8;

        codigos[1] = "P002";
        productos[1] = "Mouse";
        precios[1] = 45.00;
        stock[1] = 25;

        codigos[2] = "P003";
        productos[2] = "Teclado";
        precios[2] = 90.00;
        stock[2] = 15;

        codigos[3] = "P004";
        productos[3] = "Monitor";
        precios[3] = 780.00;
        stock[3] = 10;

        codigos[4] = "P005";
        productos[4] = "Impresora";
        precios[4] = 650.00;
        stock[4] = 6;

        System.out.println("INVENTARIO INICIAL");
        mostrarInventario(codigos, productos, precios, stock, cantidad);

        System.out.println("\nINSERTAR NUEVO PRODUCTO");
        cantidad = insertarProducto(codigos, productos, precios, stock, cantidad,
                1, "P006", "Webcam", 180.00, 12);

        System.out.println("\nACTUALIZAR PRODUCTO P003");
        actualizarProducto(codigos, precios, stock, cantidad, "P003", 10, 95.00);

        System.out.println("\nREGISTRAR VENTA DE P001");
        registrarVenta(codigos, productos, precios, stock, cantidad, "P001", 3);

        System.out.println("\nELIMINAR PRODUCTO P004");
        cantidad = eliminarProducto(codigos, productos, precios, stock, cantidad, "P004");

        System.out.print("\nIngrese un codigo de producto para buscar: ");
        String codigoBuscado = teclado.nextLine();
        buscarProducto(codigos, productos, precios, stock, cantidad, codigoBuscado);

        System.out.println("\nPRODUCTO CON MAYOR STOCK");
        mostrarMayorStock(codigos, productos, stock, cantidad);

        System.out.println("\nPRODUCTO CON MAYOR VALOR DE INVENTARIO");
        mostrarMayorValorInventario(codigos, productos, precios, stock, cantidad);

        System.out.println("\nREPORTE FINAL");
        mostrarInventario(codigos, productos, precios, stock, cantidad);

        teclado.close();
    }

    public static void mostrarInventario(String[] codigos, String[] productos,
                                         double[] precios, int[] stock, int cantidad) {
        double total = 0;

        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-8s %-12s %-12s %-10s %-10s%n",
                "CODIGO", "PRODUCTO", "PRECIO", "STOCK", "VALOR");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < cantidad; i++) {
            double valor = precios[i] * stock[i];
            total = total + valor;

            System.out.printf("%-8s %-12s %10.2f %8d %12.2f%n",
                    codigos[i], productos[i], precios[i], stock[i], valor);
        }

        System.out.println("-------------------------------------------------------------");
        System.out.printf("VALOR TOTAL DEL INVENTARIO: S/ %.2f%n", total);
        System.out.println("-------------------------------------------------------------");
    }

    public static int insertarProducto(String[] codigos, String[] productos,
                                       double[] precios, int[] stock, int cantidad,
                                       int posicion, String nuevoCodigo,
                                       String nuevoProducto, double nuevoPrecio,
                                       int nuevoStock) {
        if (cantidad >= codigos.length) {
            System.out.println("No hay espacio para insertar mas productos.");
            return cantidad;
        }

        if (posicion < 0 || posicion > cantidad) {
            System.out.println("Posicion invalida.");
            return cantidad;
        }

        for (int i = cantidad; i > posicion; i--) {
            codigos[i] = codigos[i - 1];
            productos[i] = productos[i - 1];
            precios[i] = precios[i - 1];
            stock[i] = stock[i - 1];
        }

        codigos[posicion] = nuevoCodigo;
        productos[posicion] = nuevoProducto;
        precios[posicion] = nuevoPrecio;
        stock[posicion] = nuevoStock;

        cantidad++;
        return cantidad;
    }

    public static void actualizarProducto(String[] codigos, double[] precios,
                                          int[] stock, int cantidad,
                                          String codigo, int unidadesAdicionales,
                                          double nuevoPrecio) {
        int posicion = buscarPosicion(codigos, cantidad, codigo);

        if (posicion == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }

        stock[posicion] = stock[posicion] + unidadesAdicionales;
        precios[posicion] = nuevoPrecio;

        System.out.println("Producto actualizado correctamente.");
    }

    public static void registrarVenta(String[] codigos, String[] productos,
                                      double[] precios, int[] stock, int cantidad,
                                      String codigo, int cantidadVendida) {
        int posicion = buscarPosicion(codigos, cantidad, codigo);

        if (posicion == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }

        if (stock[posicion] >= cantidadVendida) {
            stock[posicion] = stock[posicion] - cantidadVendida;
            double importe = precios[posicion] * cantidadVendida;

            System.out.println("Venta realizada.");
            System.out.println("Producto: " + productos[posicion]);
            System.out.printf("Importe de venta: S/ %.2f%n", importe);
        } else {
            System.out.println("No hay stock suficiente para realizar la venta.");
        }
    }

    public static int eliminarProducto(String[] codigos, String[] productos,
                                       double[] precios, int[] stock,
                                       int cantidad, String codigo) {
        int posicion = buscarPosicion(codigos, cantidad, codigo);

        if (posicion == -1) {
            System.out.println("Producto no encontrado.");
            return cantidad;
        }

        for (int i = posicion; i < cantidad - 1; i++) {
            codigos[i] = codigos[i + 1];
            productos[i] = productos[i + 1];
            precios[i] = precios[i + 1];
            stock[i] = stock[i + 1];
        }

        cantidad--;

        codigos[cantidad] = null;
        productos[cantidad] = null;
        precios[cantidad] = 0;
        stock[cantidad] = 0;

        System.out.println("Producto eliminado correctamente.");
        return cantidad;
    }

    public static void buscarProducto(String[] codigos, String[] productos,
                                      double[] precios, int[] stock,
                                      int cantidad, String codigo) {
        int posicion = buscarPosicion(codigos, cantidad, codigo);

        if (posicion == -1) {
            System.out.println("Producto no encontrado.");
            return;
        }

        double valor = precios[posicion] * stock[posicion];

        System.out.println("Codigo: " + codigos[posicion]);
        System.out.println("Producto: " + productos[posicion]);
        System.out.printf("Precio: S/ %.2f%n", precios[posicion]);
        System.out.println("Stock: " + stock[posicion]);
        System.out.printf("Valor del inventario: S/ %.2f%n", valor);
    }

    public static void mostrarMayorStock(String[] codigos, String[] productos,
                                         int[] stock, int cantidad) {
        int mayor = 0;

        for (int i = 1; i < cantidad; i++) {
            if (stock[i] > stock[mayor]) {
                mayor = i;
            }
        }

        System.out.println("Codigo: " + codigos[mayor]);
        System.out.println("Producto: " + productos[mayor]);
        System.out.println("Stock: " + stock[mayor]);
    }

    public static void mostrarMayorValorInventario(String[] codigos,
                                                   String[] productos,
                                                   double[] precios,
                                                   int[] stock,
                                                   int cantidad) {
        int mayor = 0;
        double mayorValor = precios[0] * stock[0];

        for (int i = 1; i < cantidad; i++) {
            double valor = precios[i] * stock[i];

            if (valor > mayorValor) {
                mayorValor = valor;
                mayor = i;
            }
        }

        System.out.println("Codigo: " + codigos[mayor]);
        System.out.println("Producto: " + productos[mayor]);
        System.out.println("Stock: " + stock[mayor]);
        System.out.printf("Valor del inventario: S/ %.2f%n", mayorValor);
    }

    public static int buscarPosicion(String[] codigos, int cantidad, String codigo) {
        for (int i = 0; i < cantidad; i++) {
            if (codigos[i].equalsIgnoreCase(codigo)) {
                return i;
            }
        }

        return -1;
    }
}
