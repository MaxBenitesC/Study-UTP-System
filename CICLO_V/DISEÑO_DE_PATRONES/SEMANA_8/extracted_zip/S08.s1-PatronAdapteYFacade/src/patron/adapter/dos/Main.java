/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron.adapter.dos;

/**
 *
 * @author c19255
 */
public class Main {
    public static void main(String[] args) {
        
        ExternalOrderService externalService= new ExternalOrderService();
        
        OrdenRepository adapter=new OrderAdapter(externalService);
        
        ECommerceApp app = new ECommerceApp(adapter);
        
        Order order = new Order(1, "product", 2);
        
        app.placeOrder(order);
        
        
        
    }
}
