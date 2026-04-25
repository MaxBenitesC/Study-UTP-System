/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemploarreglos;

import java.util.Arrays;

/**
 *
 * @author c00210
 */
public class cArreglo {
    private int arreglo[];
    private int ultIndice;

    public cArreglo(int[] arreglo, int ultIndice) {
        this.arreglo = arreglo;
        this.ultIndice = ultIndice;
    }
    public void insertaxIndice(int valor, int indice){
        int indMax= arreglo.length-1;
        if(indice >=0 && indice <=ultIndice && ultIndice < indMax){
            for (int i = ultIndice; i >= indice; i--) {
                arreglo[i+1]= arreglo[i];
            }
            arreglo[indice]= valor;
            ultIndice++;
        } 
    }
    public void insertaAntesValor(int valor, int valor2){
        int indMax= arreglo.length-1;int indice;
        int j=0;
        while(j <= ultIndice && valor2 != arreglo[j]){
            j++;
        }
        if(valor2 == arreglo[j]){
            indice = j;
        }
        else indice = indMax+1;
        if(indice >=0 && indice <=ultIndice && ultIndice < indMax){
            for (int i = ultIndice; i >= indice; i--) {
                arreglo[i+1]= arreglo[i];
            }
            arreglo[indice]= valor;
            ultIndice++;
        } 
    }    
    public void eliminaxIndice(int indice){
        if(indice >=0 && indice <=ultIndice){
            for (int i = indice; i < ultIndice; i++) {
                arreglo[i]=arreglo[i+1];
            }
            arreglo[ultIndice]=0; ultIndice--;
        }
    }
    public String muestraArreglo(){
        return Arrays.toString(arreglo);
    }
}
