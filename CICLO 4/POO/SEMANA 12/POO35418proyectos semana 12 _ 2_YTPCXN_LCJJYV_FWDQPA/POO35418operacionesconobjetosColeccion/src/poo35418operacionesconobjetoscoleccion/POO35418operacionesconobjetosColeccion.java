/*operaciones basicas con colecciones de objetos*/
package poo35418operacionesconobjetoscoleccion;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
public class POO35418operacionesconobjetosColeccion {
    public static void main(String[] args) {
        ArrayList<Persona> personas=new ArrayList<>();
        personas.add(new Persona("hUgo",66));
        personas.add(new Persona("paco",36));
        personas.add(new Persona("lUis",23));
        Collections.sort(personas, Comparator.comparingInt(p->p.edad));
        for(Persona p : personas){
            System.out.println(p);
        }
    }
}
