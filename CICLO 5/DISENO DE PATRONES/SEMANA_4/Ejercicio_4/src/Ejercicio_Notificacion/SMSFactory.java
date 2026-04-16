package Ejercicio_Notificacion;

public class SMSFactory extends NotificacionFactory{
    @Override
    public Notificacion crearNotificacion(String mensaje) {
        return new SMS(mensaje);
    }
}
