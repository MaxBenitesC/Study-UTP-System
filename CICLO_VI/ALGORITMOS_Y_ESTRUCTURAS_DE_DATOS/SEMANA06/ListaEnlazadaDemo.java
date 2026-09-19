class Nodo {
    int dato;
    Nodo siguiente;

    public Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}

class ListaEnlazada {
    Nodo inicio;

    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            Nodo actual = inicio;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    public void mostrar() {
        Nodo actual = inicio;

        while (actual != null) {
            System.out.print(actual.dato + " -> ");
            actual = actual.siguiente;
        }

        System.out.println("null");
    }

    public void eliminarPrimero() {
        if (inicio == null) {
            System.out.println("La lista está vacía");
        } else {
            inicio = inicio.siguiente;
        }
    }
}

public class ListaEnlazadaDemo {
    public static void main(String[] args) {
        ListaEnlazada lista = new ListaEnlazada();

        lista.insertar(5);
        lista.insertar(10);
        lista.insertar(15);
        lista.insertar(20);

        System.out.println("Lista inicial:");
        lista.mostrar();

        lista.eliminarPrimero();

        System.out.println();
        System.out.println("Después de eliminar el primer elemento:");
        lista.mostrar();
    }
}
