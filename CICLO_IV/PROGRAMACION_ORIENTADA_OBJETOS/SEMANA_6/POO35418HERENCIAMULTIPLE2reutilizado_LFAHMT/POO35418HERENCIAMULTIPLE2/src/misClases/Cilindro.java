package misClases;

public class Cilindro extends Circulo{
    private double altura;
    private static int ctdcilindro;

    public Cilindro() {
        this.setAltura(1);
        this.setRadio(1);
        ctdcilindro++;
    }

    public Cilindro(double radio, double altura) {
        super(radio);
        this.setAltura(altura);
        ctdcilindro++;
    }

    public Cilindro(String color, boolean relleno, double radio, double altura) {
        super(color, relleno, radio);
        this.setAltura(altura);
        ctdcilindro++;
    }

    @Override
    public String toString() {
        return "Cilindro{" +
                "altura=" + getAltura() +
                '}' + " Area: " + areaC() + " Volumen: " + volumen();
    }

    public static int getCtdcilindro() {
        return ctdcilindro;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double areaC(){
        return 2*super.area()+Math.PI*super.getRadio()* getAltura();
    }

    public double volumen(){
        return super.area()* getAltura();
    }
}
