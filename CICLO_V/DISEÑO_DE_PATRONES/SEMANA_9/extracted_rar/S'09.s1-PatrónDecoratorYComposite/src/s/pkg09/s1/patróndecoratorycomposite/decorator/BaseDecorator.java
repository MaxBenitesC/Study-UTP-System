/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s.pkg09.s1.patróndecoratorycomposite.decorator;

/**
 *
 * @author Admin
 */
public class BaseDecorator implements Component{

    protected  Component wrappe;
 
    
    public BaseDecorator(Component component){
         this.wrappe = component;
    }
    
    @Override
    public String operation() {
        return wrappe.operation();
    }
    
 
    
}
