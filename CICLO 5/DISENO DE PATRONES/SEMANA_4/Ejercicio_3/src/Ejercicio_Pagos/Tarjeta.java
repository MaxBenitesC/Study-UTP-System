package Ejercicio_Pagos;

public class Tarjeta implements Pagos{
    @Override
    public void pagar() {
        System.out.println("Procesando pago con Tarjeta de crédito");
    }
}
