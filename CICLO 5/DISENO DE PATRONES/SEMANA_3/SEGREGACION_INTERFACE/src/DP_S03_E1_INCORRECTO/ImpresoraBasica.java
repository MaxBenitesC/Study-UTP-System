package DP_S03_E1_INCORRECTO;

public class ImpresoraBasica implements Impresora{
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo documento....");
    }

    @Override
    public void escanear() {
        throw new UnsupportedOperationException("No soporta el escaneo");
    }

    @Override
    public void enviarFax() {
        throw new UnsupportedOperationException("No puede enviar Fax");
    }
}
