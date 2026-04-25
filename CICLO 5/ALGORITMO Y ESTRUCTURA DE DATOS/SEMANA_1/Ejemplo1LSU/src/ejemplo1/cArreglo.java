/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo1;

import java.util.Arrays;



/**
 *
 * @author Administrador
 */
public class cArreglo {
    private int arreglo[];

    public cArreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }
    public cArreglo(int n) {
        arreglo = new int[n];
        llenaArreglo();
    }
    public void llenaArreglo(){
        int indMax= arreglo.length-1;
        int desde = 20, hasta=50;
        for (int i = 0; i <= indMax; i++) {
            arreglo[i]=(int)(Math.random()*(hasta-desde+1)+desde);
        }
    }
    public double hallaPromedio(){
        int indMax= arreglo.length-1; int suma=0;double promedio;
        for (int i = 0; i <= indMax; i++) {
            suma += arreglo[i];
        }
        promedio= suma *1.0/ arreglo.length;
        return promedio;
    }
    public String muestraArreglo1(){
        String cadena="";int indMax= arreglo.length-1;
        for (int i = 0; i <= indMax; i++) {
            cadena += arreglo[i]+ " - ";
        }
        return cadena;
    }
    public String muestraArreglo2(){ 
        String cadena="";
        cadena = Arrays.toString(arreglo);
        return cadena;
    }   
}
