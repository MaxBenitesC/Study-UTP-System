package algoritmos;
import java.util.*;

// Multilistas simples: dos índices por ciudad y profesión (ejemplo)
public class MultiLista {
    private Map<String, Set<Integer>> listaCiudad = new HashMap<>();
    private Map<String, Set<Integer>> listaProfesion = new HashMap<>();

    public void agregar(String ciudad, String profesion, int id) {
        listaCiudad.computeIfAbsent(ciudad, k -> new HashSet<>()).add(id);
        listaProfesion.computeIfAbsent(profesion, k -> new HashSet<>()).add(id);
    }

    public Set<Integer> buscarCombinado(String ciudad, String profesion) {
        Set<Integer> c = listaCiudad.getOrDefault(ciudad, Collections.emptySet());
        Set<Integer> p = listaProfesion.getOrDefault(profesion, Collections.emptySet());
        Set<Integer> interseccion = new HashSet<>(c);
        interseccion.retainAll(p);
        return interseccion;
    }
}

