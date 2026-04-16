package Ejercicio_Documento;

public class Excel implements Documento{
    @Override
    public void abrir() {
        System.out.println("Abriendo hoja de cálculo Excel");
    }

    @Override
    public void guardar() {
        System.out.println("Guardando hoja de cálculo Excel");
    }

    @Override
    public void cerrar() {
        System.out.println("Cerrando hoja de cálculo Excel");
    }
}
