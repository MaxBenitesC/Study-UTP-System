/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploles;

/**
 *
 * @author c00210
 */
public class EjemploLES {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cLES oLES = new cLES();
        oLES.insertaxInicio(10);System.out.println(oLES.muestraLES());
        oLES.insertaxInicio(20);System.out.println(oLES.muestraLES());
        oLES.insertaxInicio(30);System.out.println(oLES.muestraLES());
        oLES.insertaxInicio(40);System.out.println(oLES.muestraLES());
        oLES.insertaxInicio(50);System.out.println(oLES.muestraLES());
        oLES.insertaxFinal(60);System.out.println(oLES.muestraLES());
        oLES.insertaxFinal(70);System.out.println(oLES.muestraLES());
        oLES.insertaxFinal(80);System.out.println(oLES.muestraLES());
        oLES.insertaxFinal(90);System.out.println(oLES.muestraLES());
        oLES.insertaxFinal(100);System.out.println(oLES.muestraLES());
        oLES.insertaDespues(200,10);System.out.println(oLES.muestraLES());
        oLES.insertaAntes(300,10);System.out.println(oLES.muestraLES());
        oLES.eliminaxInicio();System.out.println(oLES.muestraLES());
        oLES.eliminaxInicio();System.out.println(oLES.muestraLES());
        oLES.eliminaxInicio();System.out.println(oLES.muestraLES());
        oLES.eliminaxInicio();System.out.println(oLES.muestraLES());
        oLES.eliminaxInicio();System.out.println(oLES.muestraLES());
    }
}
