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
public class Ejemplo_clonacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[]={10,20,30,40,60};
        int b[],c[],d[];
        System.out.println("CopyOf");
        b= Arrays.copyOf(a, 0);
        System.out.println(Arrays.toString(b));
        b= Arrays.copyOf(a, a.length);
        System.out.println(Arrays.toString(b));
        b= Arrays.copyOf(a, a.length+5);
        System.out.println(Arrays.toString(b)); 
        b= Arrays.copyOf(a, a.length/2);
        System.out.println(Arrays.toString(b));
        System.out.println("CopyOfRange");
        b= Arrays.copyOfRange(a, 0, 0);
        System.out.println(Arrays.toString(b));
        b= Arrays.copyOfRange(a, 0, 2);
        System.out.println(Arrays.toString(b));
        b[0]=1000;
        System.out.println("a "+Arrays.toString(a));
        b= Arrays.copyOfRange(a, 2, 3);
        System.out.println(Arrays.toString(b));   
        b= Arrays.copyOfRange(a, 4, a.length+2);
        System.out.println(Arrays.toString(b));   
        System.out.println("Clone");
        b=a.clone();
        System.out.println(Arrays.toString(b));
        c=a;
        System.out.println(Arrays.toString(c));
        a[0]=100;
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));        
    }
    
}
