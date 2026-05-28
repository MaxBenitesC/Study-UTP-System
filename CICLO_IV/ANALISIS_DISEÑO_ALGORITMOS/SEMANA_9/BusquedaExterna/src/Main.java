import algoritmos.*;
import modelos.*;
import java.io.*;
import java.util.*;

public class Main {
    private static final String DATOS = "datos/clientes.txt";
    public static void main(String[] args) throws Exception {
        System.out.println("Proyecto demo: Algoritmos de Búsqueda Externa\n");

        // Preparar ejemplo: crear archivo de datos si no existe
        prepareDatos();

        BusquedaBloques bb = new BusquedaBloques(3);
        System.out.println(bb.buscar(DATOS, "004")); // buscar id 004

        BusquedaIndices bi = new BusquedaIndices();
        String idx = "datos/clientes.idx";
        bi.crearIndice(DATOS, idx);
        System.out.println(bi.buscar(DATOS, idx, "003")); // buscar id 003

        BusquedaHash bh = new BusquedaHash(5, "datos/cubetas");
        // Insertar registros de ejemplo en tabla hash (clave=id, valor=nombre|ciudad)
        bh.insertar("001", "Juan Perez|Lima");
        bh.insertar("002", "Maria Lopez|Arequipa");
        bh.insertar("003", "Pedro Ruiz|Cusco");
        System.out.println(bh.buscar("002")); // buscar id 002

        // Índice invertido demo: palabra -> lista de ids de registro
        ListaInvertida li = new ListaInvertida();
        li.agregar("Lima", 1);
        li.agregar("Lima", 4);
        li.agregar("Cusco", 3);
        System.out.println("IDs en Lima: " + li.buscar("Lima"));
        li.guardar("datos/indice_invertido.txt");

        // Multilista demo
        MultiLista ml = new MultiLista();
        ml.agregar("Lima", "Medico", 1);
        ml.agregar("Lima", "Ingeniero", 4);
        ml.agregar("Cusco", "Medico", 3);
        System.out.println("Lima y Medico -> IDs: " + ml.buscarCombinado("Lima", "Medico"));

        System.out.println("\nDemo finalizada.");
    }

    private static void prepareDatos() throws IOException {
        File f = new File(DATOS);
        if (f.exists()) return;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write("001|Juan Perez|Lima"); bw.newLine();
            bw.write("002|Maria Lopez|Arequipa"); bw.newLine();
            bw.write("003|Pedro Ruiz|Cusco"); bw.newLine();
            bw.write("004|Ana Torres|Lima"); bw.newLine();
            bw.write("005|Luis Ramos|Piura"); bw.newLine();
        }
    }
}
