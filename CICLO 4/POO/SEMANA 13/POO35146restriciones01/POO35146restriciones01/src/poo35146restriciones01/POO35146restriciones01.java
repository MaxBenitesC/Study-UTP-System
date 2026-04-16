/*método estático  '<T extends Number> double promedio(List<T> lista)' que calcule el promedio de una lista de números.*/
package poo35146restriciones01;
import java.util.Arrays;
import java.util.List;
public class POO35146restriciones01 {
    public static void main(String[] args) {
        List<Integer> lista=Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(Utilidades.promedio(lista));
    }
}
