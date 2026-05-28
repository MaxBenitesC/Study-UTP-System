package algoritmos;
import java.io.*;
import java.util.*;

// Búsqueda secuencial con índice simple (índice en archivo de texto)
public class BusquedaIndices {
    // Crea un índice simple: clave|posicionLinea
    public void crearIndice(String archivoDatos, String archivoIndice) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoDatos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoIndice))) {
            String linea;
            long posicion = 0;

            while ((linea = br.readLine()) != null) {
                String clave = linea.split("\\|", 2)[0];
                bw.write(clave + "|" + posicion);
                bw.newLine();
                posicion++;
            }
        }
    }

    // Busca en el índice y luego recupera la línea correspondiente en datos
    public String buscar(String archivoDatos, String archivoIndice, String claveBuscada) throws IOException {
        long posicion = -1;
        try (BufferedReader brIdx = new BufferedReader(new FileReader(archivoIndice))) {
            String linea;
            while ((linea = brIdx.readLine()) != null) {
                String[] partes = linea.split("\n|", 2);
                if (partes.length >= 2 && partes[0].equals(claveBuscada)) {
                    posicion = Long.parseLong(partes[1]);
                    break;
                }
            }
        }

        if (posicion == -1) return "No encontrado en índice.";

        try (BufferedReader brDat = new BufferedReader(new FileReader(archivoDatos))) {
            for (long i = 0; i < posicion; i++) brDat.readLine();
            String resultado = brDat.readLine();
            return resultado != null ? "Encontrado: " + resultado : "No existe la posición en datos.";
        }
    }
}
