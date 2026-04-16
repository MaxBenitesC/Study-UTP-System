/*recorrido hacia adelante y hacia atrás */
package poo35418demolistiterator01;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
public class POO35418DEMOLISTITERATOR01 {
    public static void main(String[] args) {
        List<String> colores=new ArrayList<>();
        colores.add("Crema");
        colores.add("gUinda");
        colores.add("rojo");
        ListIterator<String> iterador=colores.listIterator();
        System.out.println("hacia adelante");
        while(iterador.hasNext()){
            System.out.println(iterador.next());
        }
        System.out.println("hacia atras");
        while(iterador.hasPrevious()){
            System.out.println(iterador.previous());
        }
    }
}
