/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s.pkg09.s1.patróndecoratorycomposite.decorator;

/**
 *
 * @author Admin
 */
public class Principal {
    public static void main(String[] args) {
        
        Component simple = new ConcreteComponent();
        System.out.println("RESULT :"+simple.operation());
        
        Component decoratorA= new ConcreteDecoratorA(simple);
        Component decoratorB = new ConcreteDecoratorB(decoratorA);
        
        System.out.println("RESULT :"+decoratorB.operation());
        
    }
}
