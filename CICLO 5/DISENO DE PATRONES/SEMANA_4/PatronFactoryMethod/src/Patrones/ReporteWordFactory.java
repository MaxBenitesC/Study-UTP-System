package Patrones;

public class ReporteWordFactory extends ReporteFactory {
    @Override
    public Reporte crearReporte() {
        return new ReporteWord();
    }
}
