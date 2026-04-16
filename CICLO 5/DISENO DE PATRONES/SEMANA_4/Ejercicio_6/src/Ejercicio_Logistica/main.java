package Ejercicio_Logistica;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Elegir tipo de envio(nacional,internacional,express): ");
        String tipo = teclado.nextLine();
        System.out.println("Escriba el nombre del destinatario: ");
        String destino = teclado.nextLine();

        EnvioFactory factory = obtenerFactory(tipo);
        Envio envio = factory.generarEnvio(destino);
        envio.registrar();
        envio.calcularCosto();
        envio.entregar();
    }

    static EnvioFactory obtenerFactory(String tipo){
        if(tipo.equals("nacional"))return new NacionalFactory();
        if(tipo.equals("internacional")) return new InternacionalFactory();
        if(tipo.equals("express")) return new ExpressFactory();
        throw new IllegalArgumentException("Tipo de envío inválido: " +tipo);
    }
}
