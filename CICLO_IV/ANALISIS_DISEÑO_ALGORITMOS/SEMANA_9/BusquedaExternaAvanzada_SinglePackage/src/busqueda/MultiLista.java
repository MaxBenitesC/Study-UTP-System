package busqueda;
import java.util.*;

public class MultiLista {
    private Map<String, Set<Integer>> porCiudad = new HashMap<>();
    private Map<String, Set<Integer>> porTipo = new HashMap<>();

    public void agregar(String ciudad, String tipo, int id) {
        porCiudad.computeIfAbsent(ciudad, k-> new HashSet<>()).add(id);
        porTipo.computeIfAbsent(tipo, k-> new HashSet<>()).add(id);
    }

    public Set<Integer> buscarCombinado(String ciudad, String tipo) {
        Set<Integer> a = porCiudad.getOrDefault(ciudad, Collections.emptySet());
        Set<Integer> b = porTipo.getOrDefault(tipo, Collections.emptySet());
        Set<Integer> r = new HashSet<>(a); r.retainAll(b); return r;
    }
}
