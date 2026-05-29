import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Algoritmos de reemplazo de paginas (Sistemas Operativos - UTP, Semana 8).
 * Cada metodo recibe la cadena de referencias y el numero de marcos,
 * y devuelve la cantidad de FALLOS DE PAGINA (page faults).
 *
 * Compilar y ejecutar:
 *   javac AlgoritmosReemplazo.java
 *   java AlgoritmosReemplazo
 */
public class AlgoritmosReemplazo {

    // ---------- FIFO (First In, First Out) ----------
    // Reemplaza la pagina que lleva MAS TIEMPO en memoria (la mas antigua).
    static int fifo(int[] ref, int numMarcos) {
        LinkedList<Integer> marcos = new LinkedList<>(); // el frente = la mas antigua
        int fallos = 0;
        for (int pagina : ref) {
            if (!marcos.contains(pagina)) {     // no esta -> fallo de pagina
                fallos++;
                if (marcos.size() == numMarcos) {
                    marcos.removeFirst();       // saca la mas antigua
                }
                marcos.addLast(pagina);         // la nueva entra al final
            }
        }
        return fallos;
    }

    // ---------- LRU (Least Recently Used) ----------
    // Reemplaza la pagina que NO se usa hace mas tiempo.
    static int lru(int[] ref, int numMarcos) {
        List<Integer> marcos = new ArrayList<>(); // indice 0 = la menos usada recientemente
        int fallos = 0;
        for (int pagina : ref) {
            if (marcos.contains(pagina)) {
                // acierto: la marca como "usada ahora" -> al final
                marcos.remove((Integer) pagina);
                marcos.add(pagina);
            } else {                            // fallo de pagina
                fallos++;
                if (marcos.size() == numMarcos) {
                    marcos.remove(0);           // saca la menos recientemente usada
                }
                marcos.add(pagina);
            }
        }
        return fallos;
    }

    // ---------- OPTIMO (OPT / MIN) ----------
    // Reemplaza la pagina que se usara MAS TARDE en el futuro (o que ya no se usa).
    // Es teorico (necesita "ver el futuro"); sirve de referencia ideal.
    static int optimo(int[] ref, int numMarcos) {
        List<Integer> marcos = new ArrayList<>();
        int fallos = 0;
        for (int i = 0; i < ref.length; i++) {
            int pagina = ref[i];
            if (!marcos.contains(pagina)) {     // fallo de pagina
                fallos++;
                if (marcos.size() == numMarcos) {
                    int idxVictima = 0, masLejano = -1;
                    for (int m = 0; m < marcos.size(); m++) {
                        int proximoUso = Integer.MAX_VALUE; // si no vuelve a aparecer
                        for (int j = i + 1; j < ref.length; j++) {
                            if (ref[j] == marcos.get(m)) { proximoUso = j; break; }
                        }
                        if (proximoUso > masLejano) { masLejano = proximoUso; idxVictima = m; }
                    }
                    marcos.remove(idxVictima);  // saca la de uso mas lejano
                }
                marcos.add(pagina);
            }
        }
        return fallos;
    }

    // ---------- CLOCK (Segunda Oportunidad) ----------
    // Aproxima LRU con un BIT DE REFERENCIA y un puntero circular.
    // Si el bit es 1 -> se le da "segunda oportunidad" (bit pasa a 0) y avanza.
    // Si el bit es 0 -> se reemplaza esa pagina.
    static int clock(int[] ref, int numMarcos) {
        Integer[] marcos = new Integer[numMarcos]; // null = marco vacio
        int[] bit = new int[numMarcos];
        int puntero = 0, fallos = 0;
        for (int pagina : ref) {
            boolean acierto = false;
            for (int k = 0; k < numMarcos; k++) {
                if (marcos[k] != null && marcos[k] == pagina) {
                    bit[k] = 1;                 // acierto: activa su segunda oportunidad
                    acierto = true;
                    break;
                }
            }
            if (acierto) continue;
            fallos++;                           // fallo de pagina
            while (true) {
                if (marcos[puntero] == null || bit[puntero] == 0) {
                    marcos[puntero] = pagina;   // coloca aqui
                    bit[puntero] = 1;
                    puntero = (puntero + 1) % numMarcos;
                    break;
                } else {
                    bit[puntero] = 0;           // segunda oportunidad: baja el bit y avanza
                    puntero = (puntero + 1) % numMarcos;
                }
            }
        }
        return fallos;
    }

    public static void main(String[] args) {
        // Cadena de referencias clasica (Silberschatz) y 3 marcos
        int[] ref = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1};
        int numMarcos = 3;

        System.out.println("Cadena de referencias: " + java.util.Arrays.toString(ref));
        System.out.println("Numero de marcos: " + numMarcos);
        System.out.println("-------------------------------------------");
        System.out.println("FIFO    -> fallos de pagina: " + fifo(ref, numMarcos));
        System.out.println("LRU     -> fallos de pagina: " + lru(ref, numMarcos));
        System.out.println("Optimo  -> fallos de pagina: " + optimo(ref, numMarcos));
        System.out.println("Clock   -> fallos de pagina: " + clock(ref, numMarcos));
        System.out.println("-------------------------------------------");
        System.out.println("Tasa de fallos = (fallos / total referencias) x 100%");
        System.out.println("Total de referencias: " + ref.length);
    }
}
