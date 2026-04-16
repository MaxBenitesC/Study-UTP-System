import java.sql.SQLOutput;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Integer> edad = new
        LinkedList<String> prueba1 = new LinkedList<>(); //linkedlist de string
        //agregar elementos
        prueba1.add("Hola");
        prueba1.add("que");
        prueba1.add("tal");
        prueba1.add("como");
        prueba1.add("estas?");

        //probamos
        System.out.println("ORIGINAL: "+prueba1);

        //Agregar al inicio y al final (métodos especiales de LinkedList)
        prueba1.addFirst("INICIO");
        prueba1.addLast("FINAL");

        //probamos_2
        System.out.println("NUEVA PRUEBA: "+prueba1);

        //usando for recorremos la lista
        System.out.println("USANDO FOR SERIA ASI:");
        for (String tarea : prueba1) {
            System.out.println(tarea);
        }

        // Eliminar el primero y el último
        System.out.println("Eliminamos el primero y ultimo");
        prueba1.removeFirst();
        prueba1.removeLast();

        System.out.println("NUEVA PRUEBA DESPUES DE ELIMINAR: "+prueba1);
    }
}