/*método estático  
'<T extends Number> double promedio(List<T> lista)' 
que calcule el promedio de una lista de números.*/
package poo35146restriciones01;
import java.util.List;
public class Utilidades {
    public static <T extends Number> double promedio(List<T> lista){
        if(lista.isEmpty()) return 0.0;
        return lista.stream().mapToDouble(Number::doubleValue).average().orElse(0);
    }
}
