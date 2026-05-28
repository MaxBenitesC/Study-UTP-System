/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package factory;

/**
 *
 * @author c19255
 */
public abstract class Creator {
   
    public void someOperation(){
      Product product = createProduct();
      product.doStuff();
    }

    public abstract Product createProduct();
    
}
