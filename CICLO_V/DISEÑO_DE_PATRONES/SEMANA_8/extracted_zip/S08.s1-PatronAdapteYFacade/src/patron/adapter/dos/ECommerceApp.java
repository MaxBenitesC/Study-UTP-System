/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron.adapter.dos;

/**
 *
 * @author c19255
 */
public class ECommerceApp {
    private final OrdenRepository ordenRepository;

    public ECommerceApp(OrdenRepository ordenRepository) {
        this.ordenRepository = ordenRepository;
    }
    
    public void placeOrder(Order order){
        System.out.println("Procesando tu pedido :"+order.getId());
        ordenRepository.save(order);
    }
}
