/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron_factory;

/**
 *
 * @author Administrador
 */
public class Principal {
    public static void main(String[] args) {
        
        Creator creatorA = new ConcreteCreatorA();
        Creator creatorB = new ConcreteCreatorB();
        
        System.out.println(""+creatorA.someOperation());
        System.out.println(""+creatorB.someOperation());
        
    }
}
