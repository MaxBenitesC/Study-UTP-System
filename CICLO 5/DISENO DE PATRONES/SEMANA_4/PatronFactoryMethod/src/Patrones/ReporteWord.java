package Patrones;

public class ReporteWord implements Reporte{
    @Override
    public void generar() {
        System.out.println("Generando reporte en formato Word");
    }
}
