/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Refactorización;

/**
 *
 * @author c19255
 */
public class WhatsappService implements MessageService{

    @Override
    public void sendMessage(String message) {
        System.out.println("Enviando mensaje via whatsapp :"+message);
        
        }
    
}
