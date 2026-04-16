package misclases;
import java.util.Date;
public class Circulo {
    private static int numeroCirculos=0;
    private String color;
    private boolean relleno;
    private Date fechacrea;
    private double radio;
    public Circulo() {
        this("crema", false, 1 );
//        color="crema";
//        relleno=false;
//        radio=1;
//        fechacrea=new Date();  
    }
    public Circulo(String color, boolean relleno, double radio) {
        this.color = color;
        this.relleno = relleno;
        this.radio = radio;
        fechacrea=new Date();
        numeroCirculos++;
    }
    
    
    

    @Override
    public String toString() {
        return "Circulo{" + "color=" + color + ", relleno=" + relleno + ", fechacrea=" + fechacrea + ", radio=" + radio + '}';
    }
    

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the relleno
     */
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * @param relleno the relleno to set
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * @return the fechacrea
     */
    public Date getFechacrea() {
        return fechacrea;
    }

    /**
     * @return the radio
     */
    public double getRadio() {
        return radio;
    }

    /**
     * @param radio the radio to set
     */
    public void setRadio(double radio) {
        this.radio = radio;
    }

    /**
     * @return the numeroCirculos
     */
    public static int getNumeroCirculos() {
        return numeroCirculos;
    }
    
}
