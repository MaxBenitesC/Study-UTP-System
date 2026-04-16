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
public class Triangulo extends Figeom {
    private double lado1;
    private double lado2;
    private double lado3;
    private static int ctdtriangulos=0;

    public Triangulo() {
        this(1,1,1);
    }
    public Triangulo(double lado1, double lado2, double lado3) {
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        ctdtriangulos++;
    }
    public Triangulo(double lado1, double lado2, double lado3, 
            String color, boolean relleno) {
        super(color, relleno);
        this.lado1 = lado1;
        this.lado2 = lado2;
        this.lado3 = lado3;
        ctdtriangulos++;
    }

    @Override
    public String toString() {
        return "Triangulo{" +super.toString()+ "lado1=" + lado1 + 
                ", lado2=" + lado2 + ", lado3=" + lado3 + '}';
    }
 
    /**
     * @return the lado1
     */
    public double getLado1() {
        return lado1;
    }

    /**
     * @param lado1 the lado1 to set
     */
    public void setLado1(double lado1) {
        this.lado1 = lado1;
    }

    /**
     * @return the lado2
     */
    public double getLado2() {
        return lado2;
    }

    /**
     * @param lado2 the lado2 to set
     */
    public void setLado2(double lado2) {
        this.lado2 = lado2;
    }

    /**
     * @return the lado3
     */
    public double getLado3() {
        return lado3;
    }

    /**
     * @param lado3 the lado3 to set
     */
    public void setLado3(double lado3) {
        this.lado3 = lado3;
    }

    /**
     * @return the ctdtriangulos
     */
    public static int getCtdtriangulos() {
        return ctdtriangulos;
    }
    public double area(){
        double s=(lado1+lado2+lado3)/2;
        double area=Math.sqrt(s*(s-lado1)*(s-lado2)*(s-lado3) );
        return area;
    }
    public double perimetro(){
        return (lado1+lado2+lado3);
    }
}
