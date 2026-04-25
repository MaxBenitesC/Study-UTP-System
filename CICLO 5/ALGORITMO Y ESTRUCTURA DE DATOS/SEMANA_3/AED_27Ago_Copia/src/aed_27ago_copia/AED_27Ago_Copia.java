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
public class AED_27Ago_Copia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[]={2,6,5,1,15,4,3};
        int b[]={2,6,5,1,15,4,3};
        int c[]={2,6,5,1,15,4};
        int d[]={2,6,5,1,15,4,3,9};
        int e[]={2,1,50,1,15,4,3};
        int f[]={2,6,3,1,15,4,3,9};
        System.out.println(Arrays.compare(a, b));
        System.out.println(Arrays.compare(a, c));
        System.out.println(Arrays.compare(a, d));
        System.out.println(Arrays.compare(a, e));
        System.out.println(Arrays.compare(a, f));
    }
    
}
