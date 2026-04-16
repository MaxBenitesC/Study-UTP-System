package DP_S03_E1_CORRECTO;

public class MainCorrecto {
    public static void main(String[] args) {
        ImpresoraBasica i1 = new ImpresoraBasica();
        i1.imprimir();
        ImpresoraMultifuncional m1 = new ImpresoraMultifuncional();
        m1.escanear();
        m1.enviarFax();
        m1.imprimir();
    }
}
