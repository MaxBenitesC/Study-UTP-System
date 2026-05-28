/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s.pkg09.s1.patróndecoratorycomposite.decorator;

/**
 *
 * @author Admin
 */
public class ConcreteDecoratorA extends BaseDecorator{
    
    public ConcreteDecoratorA(Component component) {
        super(component);
    }
    
    @Override
    public String operation(){   
        return "ConcreteDecorater A"+super.operation()+")";
    }
    
}
