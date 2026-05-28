package algoritmos;
import java.io.*;
import java.util.*;

// Índice invertido en memoria y persistible a archivo
public class ListaInvertida {
    private Map<String, List<Integer>> indiceInvertido = new HashMap<>();

    public void agregar(String palabra, int idRegistro) {
        indiceInvertido.computeIfAbsent(palabra, k -> new ArrayList<>()).add(idRegistro);
    }

    public List<Integer> buscar(String palabra) {
        return indiceInvertido.getOrDefault(palabra, Collections.emptyList());
    }

    public void guardar(String rutaArchivo) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Map.Entry<String, List<Integer>> e : indiceInvertido.entrySet()) {
                bw.write(e.getKey() + ":" + e.getValue().toString());
                bw.newLine();
            }
        }
    }

    // Carga simple (para propósitos demo)
    public void cargar(String rutaArchivo) throws IOException {
        indiceInvertido.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(":", 2);
                if (partes.length < 2) continue;
                String clave = partes[0];
                String lista = partes[1].replaceAll("[\n[\n]\s]", ""); // ejemplo: 1,2,3
                if (lista.isEmpty()) continue;
                String[] ids = lista.split(",");
                List<Integer> valores = new ArrayList<>();
                for (String id : ids) valores.add(Integer.parseInt(id));
                indiceInvertido.put(clave, valores);
            }
        }
    }
}
