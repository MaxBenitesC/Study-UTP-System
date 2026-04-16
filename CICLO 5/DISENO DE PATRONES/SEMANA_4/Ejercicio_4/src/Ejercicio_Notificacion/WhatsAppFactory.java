package Ejercicio_Notificacion;

public class WhatsAppFactory extends NotificacionFactory{
    @Override
    public Notificacion crearNotificacion(String mensaje) {
        return new WhatsApp(mensaje);
    }
}
