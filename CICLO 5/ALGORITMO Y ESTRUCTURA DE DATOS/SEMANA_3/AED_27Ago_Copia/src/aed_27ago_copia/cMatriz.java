/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aed_27ago_copia;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class cMatriz {
    private int m[][];
    
    public cMatriz(int nf,int nc){
        m= new int[nf][nc];
        
    }
    public void llenaMatriz(){
        int indMaxF = m.length-1;
        int indMaxC = m[0].length-1;
        for (int i = 0; i <= indMaxF; i++) {
            for (int j = 0; j <= indMaxC; j++) {
                m[i][j] = (int)(Math.random()*(50-10+1)+10);
            }
        }
    }
    public String muestraMatriz(){
        String cadena="";
        int indMaxF = m.length-1;
        for (int i = 0; i <= indMaxF; i++) {
            cadena+=Arrays.toString(m[i])+"\n";
        }
        return cadena;
    }
}
