/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patronadapter;

/**
 *
 * @author c19255
 */
public class Main {
    public static void main(String[] args) {
        
        Service service = new Service();
        
        ClientInterface adapter=new Adapter(service);
        
        Client client=new  Client(adapter);
        
        client.doWork("Datos normales del cliente");
        
        
    }
}
