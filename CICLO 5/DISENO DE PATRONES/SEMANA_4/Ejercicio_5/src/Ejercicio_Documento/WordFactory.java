package Ejercicio_Documento;

public class WordFactory extends DocumentoFactory{
    @Override
    public Documento generarDocumento() {
        return new Word();
    }
}
