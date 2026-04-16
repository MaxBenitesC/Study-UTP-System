package Ejercicio_Documento;

public class PDF implements Documento{
    @Override
    public void abrir() {
        System.out.println("Abriendo documento PDF (solo lectura)");
    }
    @Override
    public void guardar() {
        System.out.println("PDF no permite guardar");
    }

    @Override
    public void cerrar() {
        System.out.println("Cerrando documento PDF");
    }
}
