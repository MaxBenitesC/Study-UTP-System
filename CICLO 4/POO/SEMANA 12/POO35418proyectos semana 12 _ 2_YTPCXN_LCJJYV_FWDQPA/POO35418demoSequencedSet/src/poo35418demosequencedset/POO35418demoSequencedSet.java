/* lista en secuencia con valores unicos */
package poo35418demosequencedset;
import java.util.LinkedHashSet;
import java.util.SequencedSet;
public class POO35418demoSequencedSet {
    public static void main(String[] args) {
         SequencedSet<String> conjunto = 
                           new LinkedHashSet<>();
         conjunto.add("platano");
         conjunto.add("naranja");
         conjunto.add("manzana");
         conjunto.add("platano");//duplicado
         System.out.println(conjunto);
         for(String elem : conjunto.reversed()){
             System.out.print(elem+" ");
         }
         System.out.println();
    }
}
