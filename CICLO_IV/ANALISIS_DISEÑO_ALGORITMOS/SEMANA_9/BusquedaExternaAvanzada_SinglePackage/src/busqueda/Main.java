package busqueda;

import java.io.*;
import java.util.*;

public class Main {
    private static final String DATOS_TXT = "datos/clientes.txt";
    private static final String DATOS_DAT = "datos/clientes.dat";
    private static final String INDICE_BIN = "datos/indice.bin";
    private static final String RESULTADOS = "datos/resultados.txt";
    private static final int TAM_REG = 80; // bytes por registro fijo
    private static final int TAM_BLOQUE = 4; // registros por bloque para la demo

    public static void main(String[] args) throws Exception {
        System.out.println("Busqueda Externa - Versión Avanzada (paquete único)\n");
        prepararDatosIniciales();

        Scanner sc = new Scanner(System.in);
        int opt = -1;
        while (opt != 0) {
            mostrarMenu();
            try { opt = Integer.parseInt(sc.nextLine().trim()); } catch (Exception e) { opt = -1; }
            switch (opt) {
                case 1 -> ejecutarBloquesRAF(sc);
                case 2 -> ejecutarIndiceBinario(sc);
                case 3 -> ejecutarHash(sc);
                case 4 -> ejecutarListaInvertida(sc);
                case 5 -> ejecutarMultiLista(sc);
                case 6 -> compararMetodos(sc);
                case 0 -> System.out.println("Saliendo..."); 
                default -> System.out.println("Opción no válida."); 
            }
        }
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("==== MENÚ ====\n1. Búsqueda por bloques (RandomAccessFile)\n2. Búsqueda con índice binario\n3. Búsqueda por transformación (hash)\n4. Lista invertida (demo)\n5. Multilista (demo)\n6. Ejecutar comparación automática\n0. Salir\nSeleccione: "); 
    }

    private static void prepararDatosIniciales() throws IOException {
        // escribir clientes.txt si no existe
        File ftxt = new File(DATOS_TXT);
        if (!ftxt.exists()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(ftxt))) {
                String[] regs = {
                    "001|Juan Perez|Lima|Cliente|100",
                    "002|Maria Lopez|Arequipa|Proveedor|200",
                    "003|Pedro Ruiz|Cusco|Cliente|150",
                    "004|Ana Torres|Lima|Cliente|175",
                    "005|Luis Ramos|Piura|Proveedor|90",
                    "006|Carla Diaz|Trujillo|Cliente|60",
                    "007|Rosa Mejia|Iquitos|Cliente|45",
                    "008|Diego Soto|Tacna|Proveedor|210",
                    "009|Marta Ruiz|Huancayo|Cliente|130",
                    "010|Alberto Vega|Chiclayo|Cliente|88",
                    "011|Sofia Paredes|Arequipa|Cliente|95",
                    "012|Carlos Medina|Lima|Proveedor|300",
                    "013|Elena Flores|Cusco|Cliente|110",
                    "014|Javier Quispe|Puno|Cliente|77",
                    "015|Lorena Rios|Piura|Proveedor|66",
                    "016|Samuel Cruz|Ica|Cliente|54",
                    "017|Adriana Gil|Lima|Proveedor|420",
                    "018|Victor Luna|Tacna|Cliente|35",
                    "019|Patricia Soto|Cusco|Cliente|140",
                    "020|Marco Polo|Arequipa|Proveedor|190"
                };
                for (String r: regs) { bw.write(r); bw.newLine(); }
            }
        }
        // crear archivo .dat con registros de tamaño fijo
        File fdat = new File(DATOS_DAT);
        if (!fdat.exists()) {
            try (RandomAccessFile raf = new RandomAccessFile(fdat, "rw"); 
                 BufferedReader br = new BufferedReader(new FileReader(DATOS_TXT))) {
                String line;
                while ((line = br.readLine()) != null) {
                    // write fixed-length record padded with spaces to TAM_REG bytes
                    String rec = String.format("%-" + TAM_REG + "s", line);
                    raf.write(rec.getBytes());
                }
            }
        }
        // borrar resultados previos
        new File(RESULTADOS).delete();
    }

    // 1. Busqueda por bloques usando RandomAccessFile
    private static void ejecutarBloquesRAF(Scanner sc) throws IOException {
        System.out.print("Ingrese ID a buscar: "); String id = sc.nextLine().trim();
        BusquedaBloquesRAF b = new BusquedaBloquesRAF(TAM_BLOQUE, TAM_REG);
        long start = System.nanoTime();
        String res = b.buscar(DATOS_DAT, id);
        long end = System.nanoTime();
        System.out.println(res);
        System.out.printf("Tiempo: %.3f ms | Accesos disco (bloques leídos): %d\n", (end-start)/1e6, b.getAccesosDisco());
        appendResultado("bloques,"+id+","+b.getAccesosDisco()+","+((end-start)/1e6));
    }

    // 2. Indice binario
    private static void ejecutarIndiceBinario(Scanner sc) throws IOException {
        System.out.print("Ingrese ID a buscar: "); String id = sc.nextLine().trim();
        BusquedaIndicesBinario bi = new BusquedaIndicesBinario();
        // crear indice si no existe
        File idx = new File(INDICE_BIN);
        if (!idx.exists()) bi.crearIndice(DATOS_DAT, INDICE_BIN, TAM_REG);
        long start = System.nanoTime();
        String res = bi.buscar(DATOS_DAT, INDICE_BIN, id, TAM_REG);
        long end = System.nanoTime();
        System.out.println(res);
        System.out.printf("Tiempo: %.3f ms\n", (end-start)/1e6);
        appendResultado("indice,"+id+",-1,"+((end-start)/1e6));
    }

    // 3. Hash demo (cubetas de archivos)
    private static void ejecutarHash(Scanner sc) throws IOException {
        System.out.print("Ingrese ID a insertar (ej: 021) o dejar vacío para buscar: "); 
        String in = sc.nextLine().trim();
        BusquedaHash h = new BusquedaHash(10, "datos/cubetas");
        if (!in.isEmpty()) {
            // para demo, tomamos primer registro que coincida en clientes.txt
            try (BufferedReader br = new BufferedReader(new FileReader(DATOS_TXT))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (linea.startsWith(in + "|")) { h.insertar(in, linea); break; }
                }
            }
            System.out.println("Inserción (demo) completada."); 
        } else {
            System.out.print("Ingrese ID a buscar en hash: "); String id = sc.nextLine().trim();
            long start = System.nanoTime();
            String res = h.buscar(id);
            long end = System.nanoTime();
            System.out.println(res);
            System.out.printf("Tiempo: %.3f ms\n", (end-start)/1e6);
            appendResultado("hash,"+id+",-1,"+((end-start)/1e6));
        }
    }

    // 4. Lista invertida demo
    private static void ejecutarListaInvertida(Scanner sc) throws IOException {
        ListaInvertida li = new ListaInvertida();
        // construir índice invertido simple desde clientes.txt sobre campo ciudad (pos 2)
        try (BufferedReader br = new BufferedReader(new FileReader(DATOS_TXT))) {
            String linea; int idnum = 1;
            while ((linea = br.readLine()) != null) {
                String[] parts = linea.split("\|");
                if (parts.length >= 3) li.agregar(parts[2], idnum);
                idnum++;
            }
        }
        System.out.print("Buscar ciudad: "); String ciudad = sc.nextLine().trim();
        List<Integer> res = li.buscar(ciudad);
        System.out.println("IDs encontrados: " + res);
        appendResultado("invertida,"+ciudad+",-1,0"); 
    }

    // 5. Multilista demo
    private static void ejecutarMultiLista(Scanner sc) throws IOException {
        MultiLista ml = new MultiLista();
        try (BufferedReader br = new BufferedReader(new FileReader(DATOS_TXT))) {
            String linea; int idnum = 1;
            while ((linea = br.readLine()) != null) {
                String[] p = linea.split("\|");
                String ciudad = p[2]; String tipo = p[3];
                ml.agregar(ciudad, tipo, idnum);
                idnum++;
            }
        }
        System.out.print("Ciudad: "); String ciudad = sc.nextLine().trim();
        System.out.print("Tipo (Cliente/Proveedor): "); String tipo = sc.nextLine().trim();
        Set<Integer> res = ml.buscarCombinado(ciudad, tipo);
        System.out.println("IDs resultantes: " + res);
        appendResultado("multilista,"+ciudad+"-"+tipo+",-1,0");
    }

    // 6. Comparación automática simple entre bloques e índice (ejecuta varias búsquedas)
    private static void compararMetodos(Scanner sc) throws Exception {
        String[] ids = {"001","005","010","013","017","020"};
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESULTADOS, true))) {
            bw.write("Comparación automática - " + new Date().toString()); bw.newLine();
        }
        // bloques
        BusquedaBloquesRAF b = new BusquedaBloquesRAF(TAM_BLOQUE, TAM_REG);
        for (String id : ids) {
            long s = System.nanoTime();
            String r = b.buscar(DATOS_DAT, id);
            long e = System.nanoTime();
            appendResultado("auto_bloques,"+id+","+b.getAccesosDisco()+","+((e-s)/1e6));
        }
        // indice
        BusquedaIndicesBinario bi = new BusquedaIndicesBinario();
        bi.crearIndice(DATOS_DAT, INDICE_BIN, TAM_REG);
        for (String id : ids) {
            long s = System.nanoTime();
            String r = bi.buscar(DATOS_DAT, INDICE_BIN, id, TAM_REG);
            long e = System.nanoTime();
            appendResultado("auto_indice,"+id+",-1,"+((e-s)/1e6));
        }
        System.out.println("Comparación finalizada. Resultados en: " + RESULTADOS);
    }

    private static void appendResultado(String linea) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESULTADOS, true))) {
            bw.write(linea); bw.newLine();
        } catch (IOException e) { /* ignore */ }
    }
}
