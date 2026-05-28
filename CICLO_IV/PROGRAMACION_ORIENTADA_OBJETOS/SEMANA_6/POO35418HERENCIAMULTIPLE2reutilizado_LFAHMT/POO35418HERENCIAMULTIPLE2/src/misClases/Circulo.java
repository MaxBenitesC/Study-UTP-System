package misClases;

public class Circulo extends Figeom {
    private double radio;
    static private int ctdcirculos;

    public Circulo() {
        this.setRadio(1);
        ctdcirculos++;
    }

    public Circulo(double radio) {
        this.setRadio(radio);
        ctdcirculos++;
    }

    public Circulo(String color, boolean relleno, double radio) {
        super(color, relleno);
        this.setRadio(radio);
        ctdcirculos++;
    }

    public static int getCtdcirculos() {
        return ctdcirculos;
    }

    @Override
    public String toString() {
        return "Circulo{" +
                "radio=" + radio +
                '}' + " Area: " + area();
    }

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public double area(){
        return Math.PI * Math.pow(radio, 2);
    }
}
