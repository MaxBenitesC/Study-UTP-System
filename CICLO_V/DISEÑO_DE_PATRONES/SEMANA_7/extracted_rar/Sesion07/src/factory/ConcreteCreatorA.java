/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

/**
 *
 * @author c19255
 */
public class ConcreteCreatorA extends Creator{

    @Override
    public Product createProduct() {
       return new ConcreteProductA();
    }
    
}
