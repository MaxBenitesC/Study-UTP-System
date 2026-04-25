/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemploarreglos;

/**
 *
 * @author c00210
 */
public class EjemploArreglos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int arr[]={10,5,8,2,13,17,16,20,0,0};
        cArreglo oArreglo = new cArreglo(arr, 7);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.insertaxIndice(50, 3);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.insertaxIndice(60, 4);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.insertaxIndice(70, 1);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.eliminaxIndice(4);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.eliminaxIndice(6);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.eliminaxIndice(7);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.eliminaxIndice(8);
        System.out.println(oArreglo.muestraArreglo()); 
        oArreglo.insertaAntesValor(70, 2);
        System.out.println(oArreglo.muestraArreglo());
        oArreglo.insertaAntesValor(70, 20);
        System.out.println(oArreglo.muestraArreglo());
    }
    
}
