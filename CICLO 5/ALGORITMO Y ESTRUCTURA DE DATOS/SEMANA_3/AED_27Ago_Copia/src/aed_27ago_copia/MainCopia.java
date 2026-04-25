/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aed_27ago_copia;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class MainCopia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[]={2,6,5,1,15,4,3};
        int b[]= Arrays.copyOf(a, a.length);
        int c[]= Arrays.copyOf(a, 3);
        int d[]= Arrays.copyOf(a, 10);
        //int e[]= Arrays.copyOf(a, -1);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
        System.out.println(Arrays.toString(d));
        //System.out.println(Arrays.toString(e));  error de ejecucion
        System.out.println("-------------------------");
        b=Arrays.copyOfRange(a, 0, a.length);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println("-------------------------");
        int clone1[]=a.clone();
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(clone1));
        a[0]=12;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(clone1)); 
        System.out.println("-------------------------");
        int copia[]=a;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(copia));
        a[0]=15;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(copia)); 
        copia[0]=25;
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(copia));
    }
}
