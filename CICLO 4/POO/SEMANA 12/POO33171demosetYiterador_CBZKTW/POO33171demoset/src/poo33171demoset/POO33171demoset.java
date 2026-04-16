//imprimir las palabras distintas
package poo33171demoset;
import java.util.HashSet; import java.util.List; import java.util.Set;
public class POO33171demoset {
    public static Set<String> obtenerPalabrasDistintas(
                           List<String> listaPalabras){
        return new HashSet(listaPalabras);
    }
    public static void main(String[] args) {
        List<String> listaPalabras=List.of("pera","uva","pera","uva");
        System.out.println("lista original: "+listaPalabras);
        Set<String> palabrasDistintas=obtenerPalabrasDistintas(listaPalabras);
        System.out.println("lista sin duplicados: "+palabrasDistintas);
    }
}
