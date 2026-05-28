package misClases;
public class Rectangulo extends Figeom{
 private double largo,ancho;
 private static int ctdrectangulos = 0 ;
 public Rectangulo(){
   this(2,1);
 }
 public Rectangulo(double largo,double ancho){
   setLargo(largo); setAncho(ancho);
   ctdrectangulos++;
 }
 public Rectangulo(double largo,double ancho,
            String color, boolean relleno){
   super(color,relleno);
   setLargo(largo); setAncho(ancho);
   ctdrectangulos++;
 }
 public String toString(){
   return super.toString()+" "+largo+" "+ancho;
 }
 public void setLargo(double largo){
   this.largo=largo;
 }
 public void setAncho(double ancho){
   this.ancho=ancho;
 }
 public double getLargo(){return largo;}
 public double getAncho(){return ancho;}
 public static int getCtdrectangulos(){return ctdrectangulos;}
 public double area(){return largo*ancho;}
}

