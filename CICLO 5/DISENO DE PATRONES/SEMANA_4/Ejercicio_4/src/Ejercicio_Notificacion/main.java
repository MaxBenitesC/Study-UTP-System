package Ejercicio_Notificacion;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese canal del mensaje(email/sms/wts): ");
        String canal = teclado.nextLine();
        System.out.println("Ingrese el mensaje: ");
        String mensaje = teclado.nextLine();

        NotificacionFactory factory = obtenerFactory(canal);
        Notificacion notif = factory.crearNotificacion(mensaje);
        notif.enviar();


    }
    static NotificacionFactory obtenerFactory(String canal){
        if(canal.equals("email")) return new EmailFactory();
        if(canal.equals("sms")) return new SMSFactory();
        if(canal.equals("wts")) return new WhatsAppFactory();
        throw new IllegalArgumentException("Canal no reconocido: "+ canal);
    }
}
