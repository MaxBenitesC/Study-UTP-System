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
public class Cubo extends Cuadrado{
    private static int ctdcubos=0;
    public Cubo() {
        ctdcubos++;
    }
    public Cubo(double lado) {
        super(lado);
        ctdcubos++;
    }
    public Cubo(double lado, String color, boolean relleno) {
        super(lado, color, relleno);
        ctdcubos++;
    }

    @Override
    public String toString() {
        return "Cubo{" +" base "+super.toString()
                +" y altura= "+ super.getAncho()+ '}';
    }
    
    public double area(){
        return 6*super.area();
    }
    public double volumen(){
        return super.area()*super.getAncho();
    }
    /**
     * @return the ctdcubos
     */
    public static int getCtdcubos() {
        return ctdcubos;
    }
    
}
