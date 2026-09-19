import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ListaTrabajador lista = new ListaTrabajador();
        int opcion = 0;

        do {
            System.out.println("\n===== GESTION DE TRABAJADORES =====");
            System.out.println("1. Registrar trabajador");
            System.out.println("2. Mostrar trabajadores");
            System.out.println("3. Buscar trabajador");
            System.out.println("4. Actualizar sueldo");
            System.out.println("5. Calcular sueldo promedio");
            System.out.println("6. Salir");
            System.out.print("Seleccione: ");

            opcion = Integer.parseInt(teclado.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Código: ");
                    String codigo = teclado.nextLine().trim();

                    // Validamos si ya existe para evitar duplicados
                    if (lista.buscar(codigo) != null) {
                        System.out.println("Error: Ya existe un trabajador con el código " + codigo);
                        break;
                    }

                    System.out.print("Nombre: ");
                    String nombre = teclado.nextLine().trim();

                    System.out.print("Sueldo: ");
                    double sueldo = Double.parseDouble(teclado.nextLine().trim());

                    lista.insertarInicio(new Trabajador(codigo, nombre, sueldo));
                    System.out.println("Trabajador registrado correctamente al inicio.");
                    break;

                case 2:
                    lista.mostrar();
                    break;

                case 3:
                    System.out.print("Ingrese el código a buscar: ");
                    String codBuscar = teclado.nextLine().trim();
                    Trabajador encontrado = lista.buscar(codBuscar);

                    if (encontrado != null) {
                        System.out.println("Trabajador encontrado: " + encontrado);
                    } else {
                        System.out.println("Trabajador no encontrado.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el código del trabajador: ");
                    String codActualizar = teclado.nextLine().trim();

                    if (lista.buscar(codActualizar) == null) {
                        System.out.println("Error: No existe un trabajador con el código " + codActualizar);
                        break;
                    }

                    System.out.print("Ingrese el nuevo sueldo: ");
                    double nuevoSueldo = Double.parseDouble(teclado.nextLine().trim());

                    if (lista.actualizarSueldo(codActualizar, nuevoSueldo)) {
                        System.out.println("Sueldo actualizado correctamente.");
                    }
                    break;

                case 5:
                    if (lista.inicio == null) {
                        System.out.println("No existen trabajadores registrados para calcular el promedio.");
                    } else {
                        double promedio = lista.calcularPromedio();
                        System.out.println("El sueldo promedio es: S/ " + promedio);
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema de trabajadores...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 6);
    }
}
