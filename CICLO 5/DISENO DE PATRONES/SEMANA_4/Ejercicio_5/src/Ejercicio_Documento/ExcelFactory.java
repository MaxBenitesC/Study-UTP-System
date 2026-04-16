package Ejercicio_Documento;

public class ExcelFactory extends DocumentoFactory{
    @Override
    public Documento generarDocumento() {
        return new Excel();
    }
}
