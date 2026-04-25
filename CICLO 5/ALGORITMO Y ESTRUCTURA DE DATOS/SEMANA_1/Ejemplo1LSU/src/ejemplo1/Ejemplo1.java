/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo1;

/**
 *
 * @author Administrador
 */
public class Ejemplo1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n=9;double promedio;
        //cArreglo oArreglo= new cArreglo(n);
        int arr[]={10,15,19,25,30,12,7};
        cArreglo oArreglo = new cArreglo(arr);
        System.out.println(oArreglo.muestraArreglo1());
        System.out.println(oArreglo.muestraArreglo2());
        promedio=oArreglo.hallaPromedio();
        System.out.println("Promedio: "+promedio);
        promedio = Math.round(promedio*100)/100.0;
        System.out.println("Promedio: "+promedio);
    }
    
}
