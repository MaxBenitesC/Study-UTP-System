package back_atras;

import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking hacia atrás aplicado a árboles binarios.
 * Muestra el árbol con el camino cuya suma es igual al objetivo.
 * Compatible con consola ASCII (sin emojis ni Unicode).
 */
public class Arbol {
    static Nodo raiz;
    static List<Integer> caminoCorrecto = new ArrayList<>();

    static boolean encontrar(Nodo nodo, int sumaObjetivo, int sumaActual, List<Integer> camino) {
        if (nodo == null) return false;

        // agregar nodo actual
        camino.add(nodo.valor);
        sumaActual += nodo.valor;

        // caso base: hoja + suma exacta
        if (nodo.izquierda == null && nodo.derecha == null && sumaActual == sumaObjetivo) {
            caminoCorrecto = new ArrayList<>(camino);
            return true;
        }

        // recursión a hijos
        boolean encontradoIzq = encontrar(nodo.izquierda, sumaObjetivo, sumaActual, camino);
        boolean encontradoDer = encontrar(nodo.derecha, sumaObjetivo, sumaActual, camino);

        // backtrack
        camino.remove(camino.size() - 1);

        return encontradoIzq || encontradoDer;
    }

    static void imprimir(Nodo nodo, String prefijo, boolean esIzq) {
        if (nodo != null) {
            boolean enCamino = caminoCorrecto.contains(nodo.valor);
            String marca = enCamino ? "[*]" : "[ ]";
            System.out.println(prefijo + (esIzq ? "|-- " : "+-- ") + marca + " " + nodo.valor);

            imprimir(nodo.izquierda, prefijo + (esIzq ? "|   " : "    "), true);
            imprimir(nodo.derecha, prefijo + (esIzq ? "|   " : "    "), false);
        }
    }

    public static void ejecutar() {
        // === Árbol corregido ===
        raiz = new Nodo(5);
        raiz.izquierda = new Nodo(4);
        raiz.derecha = new Nodo(8);

        raiz.izquierda.izquierda = new Nodo(11);
        raiz.izquierda.izquierda.izquierda = new Nodo(7);
        raiz.izquierda.izquierda.derecha = new Nodo(2);

        raiz.derecha.izquierda = new Nodo(13);
        raiz.derecha.derecha = new Nodo(4);

        int objetivo = 22;
        encontrar(raiz, objetivo, 0, new ArrayList<>());

        System.out.println("\n=== Árbol Binario (Suma Objetivo = " + objetivo + ") ===");
        imprimir(raiz, "", false);
        System.out.println("\nCamino correcto encontrado: " + caminoCorrecto);
    }
}
