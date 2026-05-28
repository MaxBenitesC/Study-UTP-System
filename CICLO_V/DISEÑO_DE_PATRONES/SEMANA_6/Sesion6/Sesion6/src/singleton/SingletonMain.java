/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

/**
 *
 * @author c19255
 */
public class SingletonMain {
    public static void main(String[] args) {
        
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        Singleton singleton3 = Singleton.getInstance();
        
        System.out.println("Instancia1 :"+singleton1);
        System.out.println("Instancia2 :"+singleton2);
   
    }
}
