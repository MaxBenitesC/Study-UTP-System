/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pooherencia;

import misClases.Estudiante;

/**
 *
 * @author Administrador
 */
public class POOherencia {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Estudiante e1= new Estudiante();
       Estudiante e2= new Estudiante(64,19.6);
       Estudiante e3= new Estudiante(62,19.3,"hugo",true);
        System.out.println(e1+"\n"+e2+"\n"+e3);
    }
    
}
