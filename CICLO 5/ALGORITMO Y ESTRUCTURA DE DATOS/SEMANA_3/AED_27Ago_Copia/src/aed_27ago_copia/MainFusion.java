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
public class MainFusion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[] = {2,6,5,1,15,4,3};
        int b[] = {9,13,7,19};
        int c[] = new int[a.length+b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
        System.out.println(Arrays.toString(c));
    }
    
}
