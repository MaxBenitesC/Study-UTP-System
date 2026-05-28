/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronadapter;

/**
 *
 * @author c19255
 */
public class Adapter implements ClientInterface{

    private final Service adaptee;

    public Adapter(Service adaptee) {
        this.adaptee = adaptee;
    }
 
    @Override
    public void method(String data) {
        String specialData = converToServiceFormat(data);
        adaptee.serviceMethod(specialData);
    }
    
    private String converToServiceFormat(String data){
        return "[Formato Adaptado]"+data;
    }
}
