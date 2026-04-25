/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo_s03;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class Ejemplo_S03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[]={10,20,30,40,60};
        int b[]={10,20,30,40,60};
        int c[]={10,20,30,40,60,70,80,90,100};
        int d[]={10,20,15,40,60,70,80};
        int resul;
        System.out.println("a y b "+Arrays.compare(a, b));
        System.out.println("a y c "+Arrays.compare(a, c));
        System.out.println("a y d "+Arrays.compare(a, d));
        System.out.println("c y d "+Arrays.compare(c, d));
        resul = Arrays.compare(a, d);
        if(resul == 0)
            System.out.println("Arreglos iguales");
        if (resul <0) 
            System.out.println("primer Arreglos menor que 2do arreglo");
        if (resul >0) 
            System.out.println("primer Arreglos mayor que 2do arreglo");
    }
}
