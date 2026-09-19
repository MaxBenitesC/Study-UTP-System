public class ListaPedido {
    NodoPedido inicio;

    public ListaPedido() {
        this.inicio = null;
    }

    //Registrar pedido al final
    public void insertarFinal(Pedido pedido) {
        NodoPedido nuevo = new NodoPedido(pedido);

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

    //Mostrar los pedidos pendientes
    public void mostrar() {
        if (inicio == null) {
            System.out.println("No hay pedidos pendientes.");
            return;
        }

        NodoPedido actual = inicio;
        System.out.println("--- PEDIDOS PENDIENTES ---");
        while (actual != null) {
            System.out.println(actual.pedido);
            actual = actual.siguiente;
        }
    }

    //Atender el primer pedido (FIFO)
    public Pedido atenderPrimerPedido() {
        if (inicio == null) {
            return null;
        }

        Pedido atendido = inicio.pedido;
        inicio = inicio.siguiente;
        return atendido;
    }

    //Total de panes solicitados
    public int calcularTotalPanes() {
        int total = 0;
        NodoPedido actual = inicio;

        while (actual != null) {
            total += actual.pedido.getCantidadPanes();
            actual = actual.siguiente;
        }

        return total;
    }

    //Contar pedidos pendientes
    public int contarPedidos() {
        int contador = 0;
        NodoPedido actual = inicio;

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        return contador;
    }

    //Buscar un pedido por su numero
    public Pedido buscar(int numeroPedido) {
        NodoPedido actual = inicio;

        while (actual != null) {
            if (actual.pedido.getNumeroPedido() == numeroPedido) {
                return actual.pedido;
            }
            actual = actual.siguiente;
        }

        return null;
    }
}
