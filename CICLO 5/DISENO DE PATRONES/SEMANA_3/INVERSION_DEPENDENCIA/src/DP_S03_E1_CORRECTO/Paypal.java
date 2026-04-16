package DP_S03_E1_CORRECTO;

public class Paypal implements MetodoPago{
    @Override
    public void pagar(double monto) {
        System.out.printf("Pago de $ %.2f realizado con Paypal\n",monto);
    }
}
