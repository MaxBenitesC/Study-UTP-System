/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ayda_21mayo_listasinv;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class cListaInvertida {
    private String autores[], novelas[];
    private int indice[][];

    public cListaInvertida(String[] autores, String[] novelas, int[][] indice) {
        this.autores = autores;
        this.novelas = novelas;
        this.indice = indice;
    }
    
    public String bucaListaInvertida(String autor){
        int indMaxAut=autores.length-1, indMaxMatriz=indice[0].length-1;
        String mensaje="Autor no encontrado";
        autor=autor.toUpperCase();
        int i = 0, j=0;
        while(i < indMaxAut && !autores[i].equals(autor))
            i++;
        if(autores[i].equals(autor)){
            mensaje="";
            while(j <= indMaxMatriz && indice[i][j]!= -1){
                mensaje+=novelas[indice[i][j]]+"\n";
                j++;
            }
        }    
        return mensaje;
    }
    public String muestraAutores(){
        return Arrays.toString(autores);
    }
    public String muestraNovelas(){
        return Arrays.toString(novelas);
    } 
    public String muestraRelacion(){
        String cadena="";
        int indMaxF=indice.length-1;
        for (int i = 0; i <= indMaxF; i++) {
            cadena+= Arrays.toString(indice[i])+"\n";
        }
        return cadena;
    }
}
