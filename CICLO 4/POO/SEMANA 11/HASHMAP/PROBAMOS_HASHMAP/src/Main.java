import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> clientes = new HashMap<>();

        clientes.put("48148456","MAX BENITES");
        clientes.put("87654321", "Ana Diaz");
        clientes.put("11223344", "Carlos Gomez");

        System.out.println("Clientes registrados:");
        for (Map.Entry<String, String> entry : clientes.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }

        // Obtener un valor
        String nombre = clientes.get("48148456");
        System.out.println("\nCliente 12345678: " + nombre);

        // Actualizar
        clientes.put("12345678", "CHARLIE FLOW");

        System.out.println("\nDespués de cambios:");
        for (Map.Entry<String, String> entry : clientes.entrySet()) {
            System.out.println(entry.getKey() + " → " + entry.getValue());
        }
    }
}