public class ListaProducto {
    NodoProducto inicio;

    public ListaProducto() {
        this.inicio = null;
    }

    //Registrar producto al fnal
    public void insertar(Producto producto) {
        NodoProducto nuevo = new NodoProducto(producto);

        if (inicio == null) {
            inicio = nuevo;
        } else {
            NodoProducto actual = inicio;

            // Recorremos hasta situarnos en el último nodo
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }

            actual.siguiente = nuevo;
        }
    }

    //Mostrar los productos.
    public void mostrar() {
        if (inicio == null) {
            System.out.println("El inventario está vacío.");
            return;
        }

        NodoProducto actual = inicio;
        System.out.println("--- LISTA DE PRODUCTOS ---");
        while (actual != null) {
            System.out.println(actual.producto);
            actual = actual.siguiente;
        }
    }

    //Buscar un producto
    public Producto buscar(String codigo) {
        NodoProducto actual = inicio;

        while (actual != null) {
            if (actual.producto.getCodigo().equalsIgnoreCase(codigo)) {
                return actual.producto;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    //Eliminar un producto
    public boolean eliminar(String codigo) {
        if (inicio == null) {
            return false;
        }

        if (inicio.producto.getCodigo().equalsIgnoreCase(codigo)) {
            inicio = inicio.siguiente;
            return true;
        }

        NodoProducto anterior = inicio;
        NodoProducto actual = inicio.siguiente;

        while (actual != null) {
            if (actual.producto.getCodigo().equalsIgnoreCase(codigo)) {
                anterior.siguiente = actual.siguiente;
                return true;
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        return false;
    }

    //Contar productos
    public int contar() {
        int contador = 0;
        NodoProducto actual = inicio;

        while (actual != null) {
            contador++;
            actual = actual.siguiente;
        }

        return contador;
    }
}
