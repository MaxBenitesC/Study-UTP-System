package Patrones;

public class Main {
    public static void main(String[] args) {
        //Crear Reporte PDF
        ReporteFactory factoryPDF = new ReportePDFFactory();
        Reporte reportePDF = factoryPDF.crearReporte();
        reportePDF.generar();

        //Crear Reporte Word
        ReporteFactory factoryWord = new ReporteWordFactory();
        Reporte reporteWord = factoryWord.crearReporte();
        reporteWord.generar();
    }
}
