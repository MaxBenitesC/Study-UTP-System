package Ejercicio_Pagos;

public class Paypal implements Pagos{
    @Override
    public void pagar() {
        System.out.println("Procesando pago con PayPal");
    }
}
