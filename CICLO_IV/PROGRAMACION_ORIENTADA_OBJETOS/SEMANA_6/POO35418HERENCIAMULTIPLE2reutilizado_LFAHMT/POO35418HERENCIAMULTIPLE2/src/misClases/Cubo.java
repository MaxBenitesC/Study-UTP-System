package misClases;
public class Cubo extends Cuadrado{
 public Cubo(){
   this(1);
 }
 public Cubo(double arista){
   super(arista);
 }
 public Cubo(double arista,String color, boolean relleno){
   super(arista,color, relleno);
 }
 public double area(){
   return 6*super.area();
 }
 public double volumen(){
   return getLargo()*getLargo()*getLargo();
 }
}


