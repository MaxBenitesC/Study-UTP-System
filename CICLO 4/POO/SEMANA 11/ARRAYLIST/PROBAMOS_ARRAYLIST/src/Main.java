import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> frutas = new ArrayList<>();
        frutas.add("Manzana");
        frutas.add("Pera");
        frutas.add("Plátano");

        System.out.println(frutas);

        System.out.println("Lista inicial:");
        for (String f : frutas) {
            System.out.println(f);
        }

        frutas.add(1, "Fresa");            // insertar
        frutas.set(2, "Kiwi");             // modificar
        frutas.remove("Manzana");          // eliminar

        System.out.println("\nLista final:");
        for (String f : frutas) {
            System.out.println(f);
        }
    }
}