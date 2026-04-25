/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Ejemplo2;

/**
 *
 * @author Administrador
 */
public class MainArreglo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombres[]={"Fabio","Geral","Adriel","Maria","Maryory"};
        cArreglo oArreglo = new cArreglo(nombres);
        oArreglo.llenaArreglo();
        System.out.println(oArreglo.muestraArreglo());
    }
    
}
