package busqueda;
import java.util.*;

public class ListaInvertida {
    private Map<String, List<Integer>> idx = new HashMap<>();
    public void agregar(String clave, int id) { idx.computeIfAbsent(clave, k-> new ArrayList<>()).add(id); }
    public List<Integer> buscar(String clave) { return idx.getOrDefault(clave, Collections.emptyList()); }
    public Map<String, List<Integer>> getIndice() { return idx; }
}
