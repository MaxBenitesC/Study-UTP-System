package Ejercicio_Notificacion;

public class WhatsApp implements Notificacion{
    private final String mensaje;

    public WhatsApp(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void enviar() {
        System.out.println("Enviando WhatsApp: [" + mensaje +"]");
    }
}
