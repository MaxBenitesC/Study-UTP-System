package DP_S03_E1_CORRECTO;

public class ImpresoraMultifuncional implements Impresora,Scanner,Fax{
    @Override
    public void imprimir() {
        System.out.println("Imprimiendo Documento Multifuncional...");
    }

    @Override
    public void enviarFax() {
        System.out.println("Enviando Fax....");
    }

    @Override
    public void escanear() {
        System.out.println("Escaneando Documento...");
    }
}
