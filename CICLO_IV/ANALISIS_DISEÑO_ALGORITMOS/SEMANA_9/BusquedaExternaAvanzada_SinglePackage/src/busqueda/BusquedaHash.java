package busqueda;
import java.io.*;

public class BusquedaHash {
    private final int N;
    private final File carpeta;

    public BusquedaHash(int n, String carpetaCubetas) {
        this.N = n; this.carpeta = new File(carpetaCubetas); if (!this.carpeta.exists()) this.carpeta.mkdirs();
    }

    private int h(String clave) {
        try { int v = Integer.parseInt(clave); return Math.abs(v) % N; } catch (Exception e) { return Math.abs(clave.hashCode()) % N; }
    }

    // Inserta la línea completa en la cubeta (simulado). Guarda clave|pos(Optional)
    public void insertar(String clave, String linea) throws IOException {
        int pos = h(clave);
        File f = new File(carpeta, "cubeta_" + pos + ".txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f, true))) {
            bw.write(clave + "|" + linea);
            bw.newLine();
        }
    }

    public String buscar(String clave) throws IOException {
        int pos = h(clave);
        File f = new File(carpeta, "cubeta_" + pos + ".txt");
        if (!f.exists()) return "Cubeta vacía (no encontrado).";
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String l;
            while ((l = br.readLine()) != null) {
                if (l.startsWith(clave + "|")) return "Encontrado en cubeta " + pos + ": " + l.substring((clave+"|").length());
            }
        }
        return "No encontrado en cubeta " + pos;
    }
}
