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
public class Ejemplo_Fusion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int a[]={10,20,30,40,60};
        int b[]={70,80,90,100};
        int d[]={11,21,31,41,51,61};
        int c[]=new int[a.length+b.length];
        int e[]=new int[a.length+b.length+d.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.out.println(Arrays.toString(c));
        System.arraycopy(b, 0, c, a.length, b.length);
        System.out.println(Arrays.toString(c));
        System.arraycopy(a, 0, e, 0, a.length);
        System.arraycopy(b, 0, e, a.length, b.length);
        System.arraycopy(d, 0, e, a.length+b.length, d.length);
        System.out.println(Arrays.toString(e));
    }
    
}
