/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Refactorización;

/**
 *
 * @author c19255
 */
public class NotificationService {

    private MessageService messageService;
    
    public NotificationService(MessageService messageService) {
        this.messageService=messageService;
    }
    public void sendNotification(String message){
       messageService.sendMessage(message);
    }
    
}
