package DP_S03_E1_INCORRECTO;

public class TiendaIncorrecta {
    private Paypal p1;

    public TiendaIncorrecta() {
        this.p1 = new Paypal();
    }

    public void realizarPago(double monto){
        p1.pagar(monto);
    }
}
