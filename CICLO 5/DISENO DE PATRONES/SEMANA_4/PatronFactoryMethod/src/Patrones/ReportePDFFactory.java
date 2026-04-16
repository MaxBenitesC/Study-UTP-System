package Patrones;

public class ReportePDFFactory extends ReporteFactory {
    @Override
    public Reporte crearReporte() {
        return new ReportePDF();
    }
}
