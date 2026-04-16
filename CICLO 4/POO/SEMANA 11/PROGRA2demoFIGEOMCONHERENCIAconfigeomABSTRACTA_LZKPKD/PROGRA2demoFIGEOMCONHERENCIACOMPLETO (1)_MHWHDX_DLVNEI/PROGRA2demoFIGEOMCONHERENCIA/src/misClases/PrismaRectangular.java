/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package misClases;

/**
 *
 * @author USER
 */
public class PrismaRectangular extends Rectangulo{
    private double altura;
    private static int ctdprismasrectangulares=0;

    public PrismaRectangular() {
        this(3);
    }
    public PrismaRectangular(double altura, 
            double largo, double ancho, 
            String color, boolean relleno) {
        super(largo, ancho, color, relleno);
        this.altura = altura;
    }
    public PrismaRectangular(double altura) {
        this.altura = altura;//base ancho1, largo 2
        ctdprismasrectangulares++;
    }

    @Override
    public String toString() {
        return "PrismaRectangular{" + "altura=" + altura 
                +" Base: "+super.toString()+ '}';
    }
   
    

    /**
     * @return the altura
     */
    public double getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(double altura) {
        this.altura = altura;
    }
    public double area(){
        return 2*super.area()+2*super.getAncho()*altura+
                super.getLargo()*altura;
    }
    public double volumen(){
        return super.area()*altura;
    }
    /**
     * @return the ctdprismasrectangulares
     */
    public static int getCtdprismasrectangulares() {
        return ctdprismasrectangulares;
    }
}
