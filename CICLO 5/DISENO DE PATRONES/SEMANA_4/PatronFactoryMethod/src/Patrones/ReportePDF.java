package Patrones;

public class ReportePDF implements Reporte {
    @Override
    public void generar() {
        System.out.println("Generando reporte en formato PDF");
    }
}
