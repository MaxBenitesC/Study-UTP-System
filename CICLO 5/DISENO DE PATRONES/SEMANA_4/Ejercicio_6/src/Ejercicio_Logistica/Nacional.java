package Ejercicio_Logistica;

public class Nacional implements Envio{
    private final String destino;
    public Nacional(String destino) {
        this.destino=destino;
    }

    @Override
    public void registrar() {
        System.out.println("Registrando envío Nacional");
    }

    @Override
    public void calcularCosto() {
        System.out.println("Costo envío Nacional: S/15.00");
    }

    @Override
    public void entregar() {
        System.out.println("Entregando paquete a "+ destino +" - destino Nacional");
    }
}
