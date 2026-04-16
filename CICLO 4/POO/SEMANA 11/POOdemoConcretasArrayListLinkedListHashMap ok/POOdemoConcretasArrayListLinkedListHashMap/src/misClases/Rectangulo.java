package misClases;
import java.util.Date;
public class Rectangulo extends Figeom {
    private double largo;
    private double ancho;
    private static int ctdrectangulos=0;
    
    public Rectangulo() {
        ancho=1;largo=2;
        ctdrectangulos++;
    }
    public Rectangulo(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
        ctdrectangulos++;
    }

    public Rectangulo(double largo, double ancho, 
            String color,  boolean relleno) {
        super(color, relleno);
        this.largo = largo;
        this.ancho = ancho;
         ctdrectangulos++;
    }

    @Override
    public String toString() {
        return "Rectangulo{" + super.toString()
                +"largo=" + largo + ", ancho=" + ancho + '}';
    }


    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public static int getCtdrectangulos() {
        return ctdrectangulos;
    }
    public double perimetro(){
        return 2*(largo+ancho);
    }
    public double area(){
        return largo*ancho;
    }
    
}
