package Ejercicio_Pagos;

public class PaypalFactory extends PagosFactory{
    @Override
    public Pagos generarPago() {
        return new Paypal();
    }
}
