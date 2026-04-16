package p0033445demoset;
import java.util.HashSet;
import java.util.Set;
public class RegistrodeVisitantes {
    private final Set<Integer> visitantes =new HashSet<>();//Upcasting
    /**
     * Registra un id de visitante
     * @param id
     * @return true si el ID es nuevo, false si ya estaba registrado
     */
    public boolean registrar(int id){
        return visitantes.add(id);
    }
}
