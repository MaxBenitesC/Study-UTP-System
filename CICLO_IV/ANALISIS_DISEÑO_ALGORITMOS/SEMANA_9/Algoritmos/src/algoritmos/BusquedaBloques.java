
package algoritmos;
import java.io.*;
import java.util.*;

// Búsqueda secuencial mediante bloques
public class BusquedaBloques {
    private int tamanoBloque;

    public BusquedaBloques(int tamanoBloque) {
        this.tamanoBloque = tamanoBloque;
    }

    public String buscar(String rutaArchivo, String codigoBuscado) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            List<String> bloque = new ArrayList<>();
            int bloqueNum = 0;

            while ((linea = br.readLine()) != null) {
                bloque.add(linea);

                if (bloque.size() == tamanoBloque) {
                    bloqueNum++;
                    for (String registro : bloque) {
                        if (registro.startsWith(codigoBuscado + "|")) {
                            return "Encontrado en bloque " + bloqueNum + ": " + registro;
                        }
                    }
                    bloque.clear();
                }
            }

            // Revisar último bloque si existe
            if (!bloque.isEmpty()) {
                bloqueNum++;
                for (String registro : bloque) {
                    if (registro.startsWith(codigoBuscado + "|")) {
                        return "Encontrado en bloque " + bloqueNum + " (último): " + registro;
                    }
                }
            }
        }
        return "No encontrado.";
    }
}
