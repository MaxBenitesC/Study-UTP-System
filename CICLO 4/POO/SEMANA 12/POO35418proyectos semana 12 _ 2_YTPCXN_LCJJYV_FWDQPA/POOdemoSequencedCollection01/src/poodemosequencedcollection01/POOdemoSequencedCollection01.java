/*colección secuenciada con OPERACIONES DE EXTREMO */
package poodemosequencedcollection01;
import java.util.LinkedList;
import java.util.SequencedCollection;
public class POOdemoSequencedCollection01 {
    public static void main(String[] args) {
        SequencedCollection<String> lista =new LinkedList<>();
        lista.addLast("manzana");
        lista.addLast("platano");
        lista.addFirst("naranja");
        lista.addFirst("platano");
        System.out.println("la lista:");
        System.out.println(lista);
        System.out.println("el primero: "+lista.getFirst());
        System.out.println("el ultimo: "+lista.getLast());
        System.out.println("la lista invertida:");
        for(String elem : lista.reversed()){
            System.out.print(elem+" ");
        }
        System.out.println();
    }
}
