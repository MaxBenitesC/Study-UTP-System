package Ejercicio_Documento;

public class PDFFactory extends DocumentoFactory{
    @Override
    public Documento generarDocumento() {
        return new PDF();
    }
}
