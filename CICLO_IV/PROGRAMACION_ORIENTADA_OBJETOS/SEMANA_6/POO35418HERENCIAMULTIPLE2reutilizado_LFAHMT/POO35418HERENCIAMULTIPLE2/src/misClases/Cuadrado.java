package misClases;
public class Cuadrado extends Rectangulo{
 public Cuadrado(){
   this(1);
 }
 public Cuadrado(double lado){
   super(lado,lado);
 }
 public Cuadrado(double lado,
            String color, boolean relleno){
   super(lado,lado,color,relleno);
 }
 
}