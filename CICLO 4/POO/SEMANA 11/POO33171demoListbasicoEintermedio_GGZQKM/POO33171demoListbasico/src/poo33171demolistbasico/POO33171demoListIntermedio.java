/*lista productos (como cadenas). Permitir al usuario:
Añadir producto
Eliminar producto por nombre
Mostrar todos los productos    */
package poo33171demolistbasico;
import java.util.*;
public class POO33171demoListIntermedio {
    public static void main(String[] args) {
        List<String> inventario=new ArrayList<>();
        Scanner sc=new Scanner(System.in);
        inventario.add("Manzanas");
        inventario.add("leche");
        inventario.add("pan");
        System.out.println("inventario actual: "+inventario);
        System.out.print("Adicionar producto: ");
        String nuevo=sc.nextLine();
        inventario.add(nuevo);
        System.out.println("inventario actual2: "+inventario);
        System.out.print("Eliminar producto por nombre: ");
        String produaeliminar=sc.nextLine();
        inventario.remove(produaeliminar);
         System.out.println("inventario actual3: "+inventario);
        System.out.print("Eliminar producto por indice: ");
        int produeliminar_indice=sc.nextInt();
        inventario.remove(produeliminar_indice);
        System.out.println("inventario actual 4: "+inventario);
    }
}
