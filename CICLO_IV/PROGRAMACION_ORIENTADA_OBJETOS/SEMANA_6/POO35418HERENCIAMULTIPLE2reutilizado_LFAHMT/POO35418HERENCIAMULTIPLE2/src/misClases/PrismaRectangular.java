package misClases;
public class PrismaRectangular extends Rectangulo {
    private double altura;
    private static int ctdprismasrectangulares=0;
    public PrismaRectangular() {
        this(3,2,1);
//         altura=3; super.setLargo(2);super.setAncho(1);
//         ctdprismasrectangulares++;
    }
    public PrismaRectangular(double altura, 
            double largo, double ancho) {
        super(largo, ancho);
        this.altura = altura;
        ctdprismasrectangulares++;
    }
    public PrismaRectangular(double altura, double largo, 
            double ancho, String color, boolean relleno) {
        super(largo, ancho, color, relleno);
        this.altura = altura;
        ctdprismasrectangulares++;
    }
    public String toString() {
        return super.getLargo() + " " +super.getAncho()+" "+ altura;
    }
    public double getAltura() {
        return altura;
    }
    public void setAltura(double altura) {
        this.altura = altura;
    }
    public static int getCtdprismasrectangulares() {
        return ctdprismasrectangulares;
    }
    public double area(){
        return 2*(super.area()+altura*super.getLargo()
                +altura*super.getAncho()  );
    }
    public double volumen(){
        return super.area()*altura;
    }
}
