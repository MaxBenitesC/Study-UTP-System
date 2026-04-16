/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ppp35146demoprivateystatic;

import misClases.Estudiante;

/**
 *
 * @author c00301
 */
public class PPP35146demoPRIVATEySTATIC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Estudiante e1=new Estudiante();
        Estudiante e2=new Estudiante(1,"javier");
        System.out.println(e1);
        System.out.println(e2);
        System.out.println("Cantidad de estudiantes: " + Estudiante.getCtdestudiantes());
    }
    
}
