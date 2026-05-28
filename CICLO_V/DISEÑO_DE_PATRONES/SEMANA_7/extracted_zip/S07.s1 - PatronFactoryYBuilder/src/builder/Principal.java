/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package builder;

/**
 *
 * @author Administrador
 */
public class Principal {
    public static void main(String[] args) {
        
        
        Customer customer = new Customer.Builder()
                .id(Integer.SIZE)
                .build();
        
        System.out.println(""+Integer.SIZE);
        System.out.println("Customer :"+customer);
    }
}
