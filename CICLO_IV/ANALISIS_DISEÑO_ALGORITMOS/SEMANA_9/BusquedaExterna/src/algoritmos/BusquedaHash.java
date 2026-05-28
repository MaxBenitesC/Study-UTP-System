package algoritmos;
import java.io.*;
import java.util.*;

// Implementación simple basada en hashing por cubetas (archivos por cubeta)
public class BusquedaHash {
    private final int TAM_TABLA;
    private final String carpetaCubetas;

    public BusquedaHash(int tamTabla, String carpetaCubetas) {
        this.TAM_TABLA = tamTabla;
        this.carpetaCubetas = carpetaCubetas;
        new File(carpetaCubetas).mkdirs();
    }

    private int funcionHash(String clave) {
        return Math.abs(clave.hashCode()) % TAM_TABLA;
    }

    public void insertar(String clave, String valor) throws IOException {
        int pos = funcionHash(clave);
        File f = new File(carpetaCubetas, "hash" + pos + ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            bw.write(clave + "|" + valor);
            bw.newLine();
        }
    }

    public String buscar(String clave) throws IOException {
        int pos = funcionHash(clave);
        File f = new File(carpetaCubetas, "hash" + pos + ".txt");
        if (!f.exists()) return "No encontrado en cubeta " + pos;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.startsWith(clave + "|")) return "Encontrado en cubeta " + pos + ": " + linea;
            }
        }
        return "No encontrado en cubeta " + pos;
    }
}
