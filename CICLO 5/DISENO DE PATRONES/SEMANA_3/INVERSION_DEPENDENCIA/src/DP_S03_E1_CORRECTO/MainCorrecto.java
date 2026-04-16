package DP_S03_E1_CORRECTO;

public class MainCorrecto {
    public static void main(String[] args) {
        TiendaCorrecta t1 = new TiendaCorrecta(new Paypal());//Pago con PayPal
        t1.realizarPago(2000);
        TiendaCorrecta t2=new TiendaCorrecta(new TarjetaCredito());
        t2.realizarPago(5000);
        TiendaCorrecta t3=new TiendaCorrecta(new TransferenciaBancaria());
        t3.realizarPago(10000);
    }
}
