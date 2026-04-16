//Crear un implementación para invertir la cadena.
package poo36146interface01;
public class InversorCadena implements Tranformador<String> {
    public String transformar(String entrada){
        return new StringBuilder(entrada).reverse().toString();
    }
}
