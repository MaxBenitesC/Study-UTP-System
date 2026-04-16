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
public class Cilindro extends Circulo {
    private double altura;
    private static int ctdcilindros=0;

    public Cilindro() {
        ctdcilindros++;
    }
    public Cilindro(double altura) {
        this.altura = altura;
        ctdcilindros++;
    }
    public Cilindro(double altura, double radio, 
            String color, boolean relleno) {
        super(radio, color, relleno);
        this.altura = altura;
        ctdcilindros++;
    }

    
    
    @Override
    public String toString() {
        return "Cilindro{" +super.toString()+ "altura=" + altura + '}';
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
        return 2*super.area()+super.perimetro()*altura;
    }
    public double volumen(){
        return super.area()*altura;
    }
    /**
     * @return the ctdcilindros
     */
    public static int getCtdcilindros() {
        return ctdcilindros;
    }
    
}
