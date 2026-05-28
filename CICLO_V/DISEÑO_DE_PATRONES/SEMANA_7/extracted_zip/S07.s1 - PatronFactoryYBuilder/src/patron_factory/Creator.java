/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron_factory;

/**
 *
 * @author Administrador
 */
public abstract class Creator {
    
    public abstract  Product factoryMethod();
    
    public String someOperation(){
    
        Product product = factoryMethod();
        return "Creator: Working with "+product.operation();
    
    }
    
    
}
