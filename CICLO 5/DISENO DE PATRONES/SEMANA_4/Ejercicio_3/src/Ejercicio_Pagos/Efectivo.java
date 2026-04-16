package Ejercicio_Pagos;

public class Efectivo implements Pagos{
    @Override
    public void pagar() {
        System.out.println("Procesando pago en Efectivo");
    }
}
