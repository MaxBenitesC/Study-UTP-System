/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejemplo_semana4;

/**
 *
 * @author Administrador
 */
public class Ejemplo_Semana4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n=6;

        cMatriz oMatriz=new cMatriz();
        System.out.println(oMatriz.creaTriDiagonal(n));
        System.out.println(oMatriz.toString());
        System.out.println(oMatriz.esTriDiagonal(oMatriz.retornaMatriz()));
        int ma[][]={{1,2,3,4},{5,6,7,8},
                            {9,10,11,12},{13,14,15,16}};
        System.out.println("Evalua ma\n"+oMatriz.esTriDiagonal(ma)); 
        System.out.println(oMatriz.creaTriDiagonal(ma));
        System.out.println(oMatriz.toString());
    }
    
}
