package Ejercicio_Documento;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        System.out.println("Elija el tipo de documento(word,excel,pdf): ");
        String tipo = teclado.nextLine();

        DocumentoFactory factory = obtenerFactory(tipo);
        Documento file = factory.generarDocumento();
        file.abrir();
        file.guardar();
        file.cerrar();
    }

    static DocumentoFactory obtenerFactory(String tipo){
        if(tipo.equals("word")) return new WordFactory();
        if(tipo.equals("excel")) return new ExcelFactory();
        if(tipo.equals("pdf")) return new PDFFactory();
        throw new IllegalArgumentException("Tipo de documento invalido: "+ tipo);
    }
}
