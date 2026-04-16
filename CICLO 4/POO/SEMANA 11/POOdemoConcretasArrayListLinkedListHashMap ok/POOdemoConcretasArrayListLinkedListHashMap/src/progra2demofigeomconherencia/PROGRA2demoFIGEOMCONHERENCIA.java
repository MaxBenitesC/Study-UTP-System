/*una lista de 4 objetos en ArrayList, LinkedList, HashMap*/
package progra2demofigeomconherencia;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import misClases.*;
public class PROGRA2demoFIGEOMCONHERENCIA {
    public static void main(String[] args) { 
           List<Figeom> arrayListfiguras=new ArrayList<>();
           arrayListfiguras.add(new Circulo(5));
           arrayListfiguras.add(new Cuadrado(3));
           arrayListfiguras.add(new Rectangulo(6,4));
           arrayListfiguras.add(new Triangulo(1,1,1));
           System.out.println("                  Con ARRAYLIST");
           for(Figeom figura : arrayListfiguras){
               System.out.println(figura);
               System.out.print("   area: "+figura.area());
               System.out.println("   perimetro: "+figura.perimetro());
           }
           List<Figeom> linkedListfiguras=new LinkedList<>();
           linkedListfiguras.add(new Circulo(5));
           linkedListfiguras.add(new Cuadrado(3));
           linkedListfiguras.add(new Rectangulo(6,4));
           linkedListfiguras.add(new Triangulo(1,1,1));
           System.out.println("                  Con LINKEDLIST");
           for(Figeom figura : linkedListfiguras){
               System.out.println(figura);
               System.out.print("   area: "+figura.area());
               System.out.println("   perimetro: "+figura.perimetro());
           }
           Map<String,Figeom> hashMapfiguras=new HashMap<>();
           hashMapfiguras.put("Circulo1", new Circulo(5));
           hashMapfiguras.put("Cuadrado1", new Cuadrado(3));
           hashMapfiguras.put("Rectangulo1", new Rectangulo(6,4));
            hashMapfiguras.put("Triangulo1", new Triangulo(1,1,1));
           
           System.out.println("                  Con HASHMAP");
           for(String clave :  hashMapfiguras.keySet()){
                Figeom figura  =hashMapfiguras.get(clave);
                System.out.println(clave+": "+figura);
                System.out.print("   area: "+figura.area());
                System.out.println("   perimetro: "+figura.perimetro());
           }
    }
}
