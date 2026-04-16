package Ejercicio_Documento;

public class Word implements Documento{
    @Override
    public void abrir() {
        System.out.println("Abriendo documento Word");
    }

    @Override
    public void guardar() {
        System.out.println("Guardando documento Word");
    }

    @Override
    public void cerrar() {
        System.out.println("Cerrando documento Word");
    }
}
