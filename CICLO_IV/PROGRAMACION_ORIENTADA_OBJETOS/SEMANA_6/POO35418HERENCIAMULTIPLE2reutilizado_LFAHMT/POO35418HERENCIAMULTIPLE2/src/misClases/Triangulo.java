package misClases;
public class Triangulo extends Figeom{
    private double s1;
    private double s2;
    private double s3;
    private static int ctdtriangulos = 0;
    public Triangulo() {
        this(1,1,1);      
    }
    public Triangulo(double s1, double s2, double s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        ctdtriangulos++;
    }
    public Triangulo(double s1, double s2, double s3, 
            String color, boolean relleno) {
        super(color, relleno);
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
        ctdtriangulos++;
    }
    public String toString() {
        return s1 + " " + s2 + " " + s3 ;
    }
    public double getS1() {   return s1;   }
    public void setS1(double s1) {  this.s1 = s1;  }
    public double getS2() {  return s2;   }
    public void setS2(double s2) {  this.s2 = s2;  }
    public double getS3() { return s3; }
    public void setS3(double s3) { this.s3 = s3; }
    public static int getCtdtriangulos() {  return ctdtriangulos;  }
    public double area(){
        double s=(s1+s2+s2)/2;
        return Math.sqrt(s*(s-s1)*(s-s2)*(s-s3));
    }
}
