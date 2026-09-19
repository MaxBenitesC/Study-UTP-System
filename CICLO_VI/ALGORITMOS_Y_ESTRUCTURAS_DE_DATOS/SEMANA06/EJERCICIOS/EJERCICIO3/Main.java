import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ListaPedido lista = new ListaPedido();
        int opcion = 0;

        do {
            System.out.println("\n===== CONTROL DE PEDIDOS - PANADERIA =====");
            System.out.println("1. Registrar pedido");
            System.out.println("2. Mostrar pedidos pendientes");
            System.out.println("3. Atender primer pedido");
            System.out.println("4. Total de panes solicitados");
            System.out.println("5. Cantidad de pedidos pendientes");
            System.out.println("6. Salir");
            System.out.print("Seleccione: ");

            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Número de pedido: ");
                    int numero = Integer.parseInt(teclado.nextLine().trim());

                    // Validamos si ya existe para evitar duplicados
                    if (lista.buscar(numero) != null) {
                        System.out.println("Error: Ya existe un pedido con el número " + numero);
                        break;
                    }

                    System.out.print("Cliente: ");
                    String cliente = teclado.nextLine().trim();

                    System.out.print("Cantidad de panes: ");
                    int panes = Integer.parseInt(teclado.nextLine().trim());

                    lista.insertarFinal(new Pedido(numero, cliente, panes));
                    System.out.println("Pedido registrado correctamente.");
                    break;

                case 2:
                    lista.mostrar();
                    break;

                case 3:
                    Pedido atendido = lista.atenderPrimerPedido();
                    if (atendido != null) {
                        System.out.println("Pedido atendido con éxito: " + atendido);
                    } else {
                        System.out.println("No hay pedidos pendientes por atender.");
                    }
                    break;

                case 4:
                    System.out.println("Cantidad total de panes solicitados: " + lista.calcularTotalPanes());
                    break;

                case 5:
                    System.out.println("Total de pedidos pendientes: " + lista.contarPedidos());
                    break;

                case 6:
                    System.out.println("Saliendo del sistema de pedidos...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 6);
    }
}
