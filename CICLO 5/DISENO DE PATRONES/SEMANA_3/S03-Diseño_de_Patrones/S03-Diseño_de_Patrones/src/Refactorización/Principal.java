/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Refactorización;

/**
 *
 * @author c19255
 */
public class Principal {

    public static void main(String[] args) {

        MessageService messageService = new EMailService();
        NotificationService notification1 = new NotificationService(messageService);
        notification1.sendNotification("message");
        MessageService messageService1 = new SMSService();
        NotificationService notification2 = new NotificationService(messageService1);
        notification2.sendNotification("message");
        MessageService messageService2 = new TelegramService();
        NotificationService notification3 = new NotificationService(messageService2);
        MessageService messageService3 = new WhatsappService();

    }
}
