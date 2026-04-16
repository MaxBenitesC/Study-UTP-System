package DP_S03_E1_CORRECTO;

public class TarjetaCredito implements MetodoPago {
    @Override
    public void pagar(double monto) {
        System.out.printf("Pago de $ %.2f realizado con Tarjeta de Crédito\n",monto);
    }
}
