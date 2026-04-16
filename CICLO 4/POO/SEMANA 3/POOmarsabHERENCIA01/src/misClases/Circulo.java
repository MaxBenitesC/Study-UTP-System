package misClases;
public class Circulo extends FigGeom{
    private static int numeroCirculos=0;
    private double radio;

    public Circulo() {
        this( 1 ); 
    }

    public Circulo( double radio) {
        this.radio = radio;
        numeroCirculos++;
    }

    public Circulo(double radio, String color, boolean relleno) {
        super(color, relleno);
        this.radio = radio;
    }
    
    @Override
    public String toString() {
        return "Circulo{" +super.toString()+ ", radio=" + radio + '}';
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
