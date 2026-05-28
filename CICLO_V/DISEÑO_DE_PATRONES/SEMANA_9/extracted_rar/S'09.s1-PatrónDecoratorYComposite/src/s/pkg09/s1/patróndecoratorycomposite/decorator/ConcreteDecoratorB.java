/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s.pkg09.s1.patróndecoratorycomposite.decorator;

/**
 *
 * @author Admin
 */
public class ConcreteDecoratorB extends BaseDecorator {
    
    
    
    public ConcreteDecoratorB(Component component) {
        super(component);
    }
    
    public String operation(){
      return "Concrete Decorator B("+super.operation()+")";
    }
}
