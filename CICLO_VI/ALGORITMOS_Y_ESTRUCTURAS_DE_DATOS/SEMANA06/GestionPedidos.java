import java.util.Locale;

class NodoPedido {
    String codigo;
    String cliente;
    double monto;
    NodoPedido siguiente;

    public NodoPedido(String codigo, String cliente, double monto) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.monto = monto;
        this.siguiente = null;
    }
}

class ListaPedidos {
    NodoPedido inicio;

    public void insertar(String codigo, String cliente, double monto) {
        NodoPedido nuevo = new NodoPedido(codigo, cliente, monto);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoPedido actual = inicio;

            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    public void mostrar() {
        NodoPedido actual = inicio;

        while (actual != null) {
            System.out.printf(Locale.US, "%s - %s - S/ %.2f%n", actual.codigo, actual.cliente, actual.monto);
            actual = actual.siguiente;
        }
    }

    public void eliminarPrimero() {
        if (inicio == null) {
            System.out.println("La lista está vacía");
        } else {
            inicio = inicio.siguiente;
        }
    }
}

public class GestionPedidos {
    public static void main(String[] args) {
        ListaPedidos lista = new ListaPedidos();

        lista.insertar("P001", "Ana Torres", 120.00);
        lista.insertar("P002", "Luis Pérez", 85.50);
        lista.insertar("P003", "María López", 210.00);
        lista.insertar("P004", "Carlos Ruiz", 150.00);

        lista.mostrar();

        lista.eliminarPrimero();

        System.out.println("Luego de atender el primer pedido:");
        lista.mostrar();
    }
}
