/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejemplo_cola;

/**
 *
 * @author Administrador
 */
public class cCola {
    private int cola[];
    private int first, last, tamaño;
    public cCola(int tamaño){
        this.tamaño = tamaño;
        creaCola();
    }
    public void creaCola(){
        cola = new int[tamaño];
        colaVacia();
    }
    public void colaVacia(){
        first=-1; last = -1;
    }
    public void incluirElemento(int valor){
        if(last < tamaño-1){
            last++;
            cola[last]= valor;
            if(first==0)
                first++;
        }
    }
    public int eliminaElemento(){
        int valor=0;
        if(first >= 0){
            valor=cola[first];
            first++;
            if(last < first)
                colaVacia();
        }
        return valor;
    }
    public int acceso(){
        int valor=0;
        if(first >= 0){
            valor=cola[first];
        }
        return valor;
    }
    public String muestraArreglo(){
        String cadena="";
        if(first >= 0){
            for (int i = first; i <= last; i++) {
                cadena+= cola[i]+" - ";
            }
        }
        return cadena;
    }
}
