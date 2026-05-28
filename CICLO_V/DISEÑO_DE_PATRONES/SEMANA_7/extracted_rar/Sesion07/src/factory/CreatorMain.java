/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

/**
 *
 * @author c19255
 */
public class CreatorMain {
    public static void main(String[] args) {
        
        Creator creatorA = new ConcreteCreatorA();
         creatorA.someOperation();
        Creator creatorB = new ConcreteCreatorB();
         creatorB.someOperation();
        
        
        
    }
}
