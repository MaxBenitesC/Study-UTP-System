import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ListaProducto lista = new ListaProducto();
        int opcion = 0;

        do {
            System.out.println("\n===== INVENTARIO =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Mostrar productos");
            System.out.println("3. Buscar producto");
            System.out.println("4. Eliminar producto");
            System.out.println("5. Contar productos");
            System.out.println("6. Salir");
            System.out.print("Seleccione: ");

            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Código: ");
                    String codigo = teclado.nextLine().trim();
                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine().trim();

                    // Validamos si ya existe para evitar duplicados
                    if (lista.buscar(codigo) != null) {
                        System.out.println("Error: Ya existe un producto con el código " + codigo);
                    } else {
                        lista.insertar(new Producto(codigo, nombre));
                        System.out.println("Producto registrado correctamente.");
                    }
                    break;

                case 2:
                    lista.mostrar();
                    break;

                case 3:
                    System.out.print("Ingrese el código a buscar: ");
                    String codBuscar = teclado.nextLine().trim();
                    Producto encontrado = lista.buscar(codBuscar);

                    if (encontrado != null) {
                        System.out.println("Producto encontrado: " + encontrado);
                    } else {
                        System.out.println("Producto no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el código a eliminar: ");
                    String codEliminar = teclado.nextLine().trim();

                    if (lista.eliminar(codEliminar)) {
                        System.out.println("Producto eliminado correctamente.");
                    } else {
                        System.out.println("No se encontró ningún producto con ese código.");
                    }
                    break;

                case 5:
                    System.out.println("Cantidad total de productos: " + lista.contar());
                    break;

                case 6:
                    System.out.println("Saliendo del sistema de inventario...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 6);
    }
}
