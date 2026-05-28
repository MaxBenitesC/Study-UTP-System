package misClases;
public class PrismaTriangular extends Triangulo{
    private double altura;
    private static int ctdtriangulos=0;

    public PrismaTriangular() {
        this(1,1,1,1);
    }
    public PrismaTriangular(double altura, double s1, double s2, double s3) {
        super(s1, s2, s3);
        this.altura = altura;
        ctdtriangulos++;
    }
    public PrismaTriangular(double altura, double s1, double s2, double s3, 
            String color, boolean relleno) {
        super(s1, s2, s3, color, relleno);
        this.altura = altura;
        ctdtriangulos++;
    }
    public String toString() {
        return super.toString()+" " + altura;
    }
    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }
    public static int getCtdtriangulos() {
        return ctdtriangulos;
    }
    public double volumen(){
        return super.area()*altura;
    }
    public double area(){
        return 2*super.area()+altura*(super.getS1()+super.getS2()+super.getS3());
    }
}
