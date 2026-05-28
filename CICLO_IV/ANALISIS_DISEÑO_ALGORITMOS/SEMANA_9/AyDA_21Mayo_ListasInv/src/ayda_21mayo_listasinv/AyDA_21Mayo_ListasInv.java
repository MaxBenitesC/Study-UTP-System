/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ayda_21mayo_listasinv;

import java.util.Arrays;

/**
 *
 * @author Administrador
 */
public class AyDA_21Mayo_ListasInv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String autores[]={"CERVANTES","KAFKA","TOLSTOI","CAPOTE"};
        String novelas[]={"A SANGRE FRIA","EL PROCESO","LA GALATEA","AMO Y CRIADO","EL QUIJOTE",
        "ANA KARENINA","HISTORIAS EJEMPLARES","LA GUERRA Y LA PAZ","EL CASTILLO","LA METAMORFOSIS"};
        int indice[][]={{4,2,6,-1,-1},{1,8,9,-1,-1},{5,3,7,-1,},{0,-1,-1,-1,-1}};
//
//        System.out.println("Autor "+Arrays.toString(indice[0]));
//        System.out.println("Autor "+Arrays.toString(indice[1]));
//        System.out.println("Autor "+Arrays.toString(indice[2]));
//        System.out.println("Autor "+Arrays.toString(indice[3]));
        
        cListaInvertida oLista= new cListaInvertida(autores, novelas, indice);
        String autor="garcia";
        System.out.println("Autores "+oLista.muestraAutores());
        System.out.println("Novelas "+oLista.muestraNovelas());   
        System.out.println("Relacion \n"+oLista.muestraRelacion());
        System.out.println(oLista.bucaListaInvertida(autor));

    }
    
}
