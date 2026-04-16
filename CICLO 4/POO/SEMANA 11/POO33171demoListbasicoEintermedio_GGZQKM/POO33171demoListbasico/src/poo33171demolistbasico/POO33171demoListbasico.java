/*Crear una Lista de nombres de estudiantes. Agrega 3 nombres,
muestra el segundo y elimina el primero */
package poo33171demolistbasico;
import java.util.ArrayList;
import java.util.List;
public class POO33171demoListbasico {
    public static void main(String[] args) {
        List<String> estudiantes=new ArrayList<>();
        estudiantes.add("Ana");
        estudiantes.add("Maria");
        estudiantes.add("Airton");
        System.out.println("Segundo estudiante: "+estudiantes.get(1));
        System.out.println("lista antes: "+estudiantes);
        System.out.println("Eliminar estudiante: 2");
        estudiantes.remove(1);
        System.out.println("lista despues: "+estudiantes);
    }
}
