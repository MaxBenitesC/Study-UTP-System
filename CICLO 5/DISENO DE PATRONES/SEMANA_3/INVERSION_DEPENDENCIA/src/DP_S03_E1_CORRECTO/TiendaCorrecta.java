package DP_S03_E1_CORRECTO;

public class TiendaCorrecta {
    private MetodoPago m1;

    public TiendaCorrecta(MetodoPago m1) {
        this.m1 = m1;
    }

    public void realizarPago(double monto){
        m1.pagar(monto);
    }
}
