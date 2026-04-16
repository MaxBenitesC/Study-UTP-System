package misClases;
public class Circulo extends figgeom{
    private double radio;

    public Circulo() {
        this(1);
    }

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public String toString() {
        return "Circulo{" +super.toString()+ "radio=" + radio + '}';
    }
    
    @Override
    public double area() {
        return Math.PI*Math.pow(radio, 2);
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
    
}
