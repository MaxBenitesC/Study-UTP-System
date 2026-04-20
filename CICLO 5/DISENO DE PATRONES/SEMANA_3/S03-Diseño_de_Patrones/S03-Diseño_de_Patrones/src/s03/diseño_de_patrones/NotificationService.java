/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package s03.diseño_de_patrones;

/**
 *
 * @author c19255
 */
public class NotificationService {
    private EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    public void sendNotification(String mensaje){
        emailService.sendEmail(mensaje);
    }
}
