package Ejercicio_Notificacion;

public class SMS implements Notificacion{
    private final String mensaje;

    public SMS(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void enviar() {
        System.out.println("Enviando SMS: [" + mensaje +"]");
    }
}
