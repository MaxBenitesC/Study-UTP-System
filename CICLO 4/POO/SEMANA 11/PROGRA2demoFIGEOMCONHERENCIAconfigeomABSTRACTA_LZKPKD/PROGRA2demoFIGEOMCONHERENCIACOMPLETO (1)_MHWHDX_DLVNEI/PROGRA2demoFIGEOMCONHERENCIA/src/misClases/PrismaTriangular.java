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
public class PrismaTriangular extends Triangulo{
    private double altura;
    private static int ctdprismastriangulares=0;

    public PrismaTriangular() {
        this(1); // //triangulo equilatero de lado 1, altura=1
    } 
    public PrismaTriangular(double altura) {
        //triangulo equilatero de lado 1
        this.altura = altura;
        ctdprismastriangulares++;
    }
    public PrismaTriangular(double altura, 
            double lado1, double lado2, double lado3, 
            String color, boolean relleno) {
        super(lado1, lado2, lado3, color, relleno);
        this.altura = altura;
        ctdprismastriangulares++;
    }
    @Override
    public String toString() {
        return "PrismaTriangular{" + "altura=" + altura 
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
        return 2*super.area() +altura*(super.getLado1()
                +super.getLado2()+super.getLado3());
    }
    public double volumen(){
        return super.area()*altura;
    }
    /**
     * @return the ctdprismastriangulares
     */
    public static int getCtdprismastriangulares() {
        return ctdprismastriangulares;
    }
    
}
