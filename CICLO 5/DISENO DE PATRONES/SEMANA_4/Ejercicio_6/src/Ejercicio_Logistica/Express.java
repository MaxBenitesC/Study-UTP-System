package Ejercicio_Logistica;

public class Express implements Envio{
    private final String destino;

    public Express(String destino) {
        this.destino = destino;
    }

    @Override
    public void registrar() {
        System.out.println("Registrando envío Express");
    }

    @Override
    public void calcularCosto() {
        System.out.println("Costo envío Express: S/45.00");
    }

    @Override
    public void entregar() {
        System.out.println("Entregando paquete a "+ destino +" - destino Express (24hrs)");
    }
}
