package Ejercicio_Logistica;

public class Internacional implements Envio{
    private final String destino;

    public Internacional(String destino) {
        this.destino=destino;
    }

    @Override
    public void registrar() {
        System.out.println("Registrando envío Internacional");
    }

    @Override
    public void calcularCosto() {
        System.out.println("Costo envío Internacional: S/85.00");
    }

    @Override
    public void entregar() {
        System.out.println("Entregando paquete a "+ destino +" - destino  Internacional");
    }
}
