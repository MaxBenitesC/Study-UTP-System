public class ListaTrabajador {
    NodoTrabajador inicio;

    public ListaTrabajador() {
        this.inicio = null;
    }

    //Registrar trabajador al inicio
    public void insertarInicio(Trabajador trabajador) {
        NodoTrabajador nuevo = new NodoTrabajador(trabajador);
        nuevo.siguiente = inicio;
        inicio = nuevo;
    }

    //Mostrar los trabajadores
    public void mostrar() {
        if (inicio == null) {
            System.out.println("No existen trabajadores registrados.");
            return;
        }

        NodoTrabajador actual = inicio;
        System.out.println("--- LISTA DE TRABAJADORES ---");
        while (actual != null) {
            System.out.println(actual.trabajador);
            actual = actual.siguiente;
        }
    }

    //Buscar un trabajador
    public Trabajador buscar(String codigo) {
        NodoTrabajador actual = inicio;

        while (actual != null) {
            if (actual.trabajador.getCodigo().equalsIgnoreCase(codigo)) {
                return actual.trabajador;
            }
            actual = actual.siguiente;
        }

        return null;
    }

    //Actualizar sueldo de un trabajador
    public boolean actualizarSueldo(String codigo, double nuevoSueldo) {
        Trabajador t = buscar(codigo);
        if (t != null) {
            t.setSueldo(nuevoSueldo);
            return true;
        }
        return false;
    }

    //Calcular sueldo promedio
    public double calcularPromedio() {
        if (inicio == null) {
            return 0.0;
        }

        double suma = 0;
        int contador = 0;
        NodoTrabajador actual = inicio;

        while (actual != null) {
            suma += actual.trabajador.getSueldo();
            contador++;
            actual = actual.siguiente;
        }

        return suma / contador;
    }
}
