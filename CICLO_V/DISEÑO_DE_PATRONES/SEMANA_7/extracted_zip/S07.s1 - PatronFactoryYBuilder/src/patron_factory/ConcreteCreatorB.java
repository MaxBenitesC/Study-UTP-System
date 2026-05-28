/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron_factory;

/**
 *
 * @author Administrador
 */
public class ConcreteCreatorB extends Creator{

    @Override
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
    
}
