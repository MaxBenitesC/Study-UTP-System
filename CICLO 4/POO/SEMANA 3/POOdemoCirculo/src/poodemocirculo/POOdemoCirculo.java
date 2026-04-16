/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poodemocirculo;

import misclases.Circulo;

/**
 *
 * @author c00301
 */
public class POOdemoCirculo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Circulo c1=new Circulo();
        Circulo c2=new Circulo("gUinda",true,12.34);
         System.out.println(c1);
         System.out.println(c2);
         System.out.println("nro circUlos="+Circulo.getNumeroCirculos());
        
    }
    
}
