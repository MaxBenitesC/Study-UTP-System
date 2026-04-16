package Ejercicio_Pagos;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Ingrese metodo de pago (tarjeta/efectivo/paypal): ");
        String tipo = teclado.nextLine();

        PagosFactory factory = obtenerFactory(tipo);
        if(factory==null){
            System.out.println("Metodo de pago no reconocido: "+ tipo);
            return;
        }
        Pagos pago = factory.generarPago();
        pago.pagar();
    }

    static PagosFactory obtenerFactory(String tipo){
        if(tipo.equals("tarjeta")) return new TarjetaFactory();
        if(tipo.equals("efectivo")) return new EfectivoFactory();
        if(tipo.equals("paypal")) return new PaypalFactory();
        return null;
    }
}
