package POO35418HERENCIAMULTIPLE2;
import java.util.*;
import misClases.*;

public class POO35418HERENCIAMULTIPLE2 {
   public static void main(String[] args) {
       Cilindro cil1 = new Cilindro();
       Cilindro cil2 = new Cilindro(4,4);
       System.out.println(cil1 + "\n" + cil2);
       Circulo c1 = new Circulo();
       Circulo c2 = new Circulo(5);
       Circulo c3 = new Circulo("ROJO",true,3);
       System.out.println(c1);
       System.out.println(c2);
       System.out.println(c3);
//       PrismaTriangular pt1=new PrismaTriangular();
//        System.out.println("area="+pt1.area());
//        System.out.println("volumen="+pt1.volumen());
//       PrismaRectangular p1= new PrismaRectangular();
//       System.out.println("area="+p1.area());
//       System.out.println("volumen="+p1.volumen());
//     Cubo c1 = new Cubo();
//     System.out.println("c1{area= "+c1.area()
//               +" ,volumen="+c1.volumen()+"}");
//     Cubo c2 = new Cubo(2);
//     System.out.println("c2{area= "+c2.area()
//               +" ,volumen="+c2.volumen()+"}");
 }
}
