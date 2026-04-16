/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

import java.util.Date;

/**
 *
 * @author Usuario
 */
public class Circulo extends Figeom {
    private double radio;
    private static int ctdcirculos=0;
    @Override
    public double area(){
        return Math.PI*Math.pow(radio, 2);
    }
    @Override
    public double perimetro(){
        return 2*Math.PI*radio;
    }

    @Override
    public String toString() {
        return "Circulo{" +super.toString()+ "radio=" + radio + '}';
    }
    
  
    public Circulo() {
        radio=1;
        ctdcirculos++;
    }
    public Circulo(double radio) {
        this.radio = radio;
        ctdcirculos++;
    }

    public Circulo(double radio, String color, boolean relleno) {
        super(color, relleno);
        this.radio = radio;
        ctdcirculos++;
    }
    

    public double getRadio() {
        return radio;
    }

    public void setRadio(double radio) {
        this.radio = radio;
    }

    public static int getCtdcirculos() {
        return ctdcirculos;
    }
}
