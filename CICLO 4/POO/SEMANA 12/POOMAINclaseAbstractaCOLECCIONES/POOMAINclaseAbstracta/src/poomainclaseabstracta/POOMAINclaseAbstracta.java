package poomainclaseabstracta;
import java.util.ArrayList;
import misClases.*;

public class POOMAINclaseAbstracta {

    public static void main(String[] args) {
       ArrayList<figgeom> lista1=new ArrayList<>();
       lista1.add(new Circulo());
       lista1.add(new Rectangulo());
       lista1.add(new Circulo(2));
       lista1.add(new Rectangulo(5,3));
       for(figgeom figura : lista1    )    {
           System.out.println(figura);
       }
    }
    
}
