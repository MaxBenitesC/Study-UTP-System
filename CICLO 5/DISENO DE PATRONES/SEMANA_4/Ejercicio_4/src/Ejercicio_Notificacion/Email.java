package Ejercicio_Notificacion;

public class Email implements Notificacion{
    private final String mensaje;

    public Email(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void enviar() {
        System.out.println("Enviando Email: [" + mensaje +"]");
    }
}
