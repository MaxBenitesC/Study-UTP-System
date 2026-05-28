package busqueda;

import java.io.*;

public class BusquedaBloquesRAF {
    private int tamanoBloque;
    private int tamanoRegistro;
    private int accesosDisco;

    public BusquedaBloquesRAF(int tamanoBloque, int tamanoRegistro) {
        this.tamanoBloque = tamanoBloque;
        this.tamanoRegistro = tamanoRegistro;
        this.accesosDisco = 0;
    }

    public String buscar(String rutaArchivo, String claveBuscada) throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo, "r")) {
            long totalRegistros = raf.length() / tamanoRegistro;
            long totalBloques = (long) Math.ceil((double) totalRegistros / tamanoBloque);

            for (int i = 0; i < totalBloques; i++) {
                accesosDisco++; // leemos un bloque (simulado por lectura secuencial de registros del bloque)
                long inicio = (long) i * tamanoBloque * tamanoRegistro;
                raf.seek(inicio);
                for (int j = 0; j < tamanoBloque && (i * tamanoBloque + j) < totalRegistros; j++) {
                    byte[] buf = new byte[tamanoRegistro];
                    raf.readFully(buf);
                    String reg = new String(buf).trim();
                    if (reg.startsWith(claveBuscada + "|")) {
                        return "Encontrado: " + reg + " (bloque=" + (i+1) + ")";
                    }
                }
            }
        }
        return "No encontrado.";
    }

    public int getAccesosDisco() { return accesosDisco; }
}
