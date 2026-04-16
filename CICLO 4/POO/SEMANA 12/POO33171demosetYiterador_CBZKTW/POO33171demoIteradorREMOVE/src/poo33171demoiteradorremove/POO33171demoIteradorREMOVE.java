//eliminar los pares
package poo33171demoiteradorremove;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
public class POO33171demoIteradorREMOVE {
    public static void eliminarPares( List<Integer> lista){
        Iterator<Integer> it=lista.iterator();
        while(it.hasNext()){
            Integer numero=it.next();
            if(numero % 2==0) it.remove();
        }
    }
    public static void main(String[] args) {
        List<Integer> numeros=new LinkedList<> 
                    (Arrays.asList(1,2,3,4,5,6,7,8,9));
        System.out.println("lista inicial: "+numeros);
        eliminarPares(numeros);
        System.out.println("lista sin pares: "+numeros);
//        List<Integer> numeros=new LinkedList<>();
//        numeros.add(1);numeros.add(2);numeros.add(3);
//        numeros.add(4);numeros.add(5);numeros.add(6);
//        numeros.add(7);numeros.add(8);numeros.add(9);
        
    }
    
}
