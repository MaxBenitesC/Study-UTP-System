/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aed_27ago_copia;

/**
 *
 * @author Administrador
 */
public class MainMatriz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int nf=4, nc=3;
        cMatriz oMatriz= new cMatriz(nf,nc);
        oMatriz.llenaMatriz();
        System.out.println(oMatriz.muestraMatriz());
    }
    
}
