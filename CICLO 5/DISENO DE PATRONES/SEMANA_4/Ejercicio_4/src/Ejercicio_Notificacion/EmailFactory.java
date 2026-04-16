package Ejercicio_Notificacion;

public class EmailFactory extends NotificacionFactory{
    @Override
    public Notificacion crearNotificacion(String mensaje) {
        return new Email(mensaje);
    }
}
