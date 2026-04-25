/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo_semana4;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class cMatriz {
    private int m[][];
    
    public boolean creaTriDiagonal(int n){
        m= new int[n][n]; boolean creado=false;
        if(m.length >= 3){
            creado = true;
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++) {
                    if(i==j || i+1 == j || i-1 == j)
                        m[i][j]=(int)(Math.random()*(50-10+1)+10);
                    else m[i][j]=0;
                }
            }
        }
        return creado;
    }
    public boolean creaTriDiagonal(int mm[][]){
        m= mm;
        boolean creado=false;
        if(m.length >= 3){
            creado = true;
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++) {
                    if(i==j || i+1 == j || i-1 == j)
                        m[i][j]=(int)(Math.random()*(50-10+1)+10);
                    else m[i][j]=0;
                }
            }
        }
        return creado;
    } 
    public int[][] retornaMatriz(){
        return m;
    }
    public boolean esTriDiagonal(int m[][]){
        boolean creado=true;
        if(m.length >= 3){
            for (int i = 0; i < m.length; i++) {
                for (int j = 0; j < m.length; j++) {
                    if(i==j || i+1 == j || i-1 == j){
                        if(m[i][j]== 0)
                            creado= false;
                    }
                    else if(m[i][j]!=0)
                           creado= false;
                }
            }
        }
        else creado=false;
        return creado;
    }    
    @Override
    public String toString() {
        String cadena="";
        for (int i = 0; i < m.length; i++) {
            cadena+= Arrays.toString(m[i])+"\n";
        }
        return cadena;
    }
    
}
