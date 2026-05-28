/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s06.s1.patrónsingletonyprototype.singleton;

/**
 *
 * @author c19255
 */
public class Principal {
    public static void main(String[] args) {
        
        Singleton singleton1= Singleton.getInstance();       
        System.out.println("Objeto singleton :"+singleton1.toString());
        singleton1.close();
        Singleton singleton2= Singleton.getInstance();       
        System.out.println("Objeto singleton :"+singleton2.toString());
         singleton2.close();
        Singleton singleton3= Singleton.getInstance();       
        System.out.println("Objeto singleton :"+singleton3.toString());
         singleton2.close();
    }
}
