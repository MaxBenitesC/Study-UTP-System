/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patron.adapter.dos;

/**
 *
 * @author c19255
 */
public class OrderAdapter implements OrdenRepository{

    private final ExternalOrderService externalService;

    public OrderAdapter(ExternalOrderService service) {
        this.externalService = service;
    }

    @Override
    public void save(Order order) {
        String xml= convertToXml(order);
        externalService.saveOrderXml(xml);
    }
    public String convertToXml(Order order){
    
        return    "<order>"+
                  "<id>"+order.getId()+"</id>"+
                  "<product>"+order.getProduct()+"</product>"+
                  "<quantity>"+order.getQuantity()+"</quantity>"+
                  "</order>";    
    }
}
