import java.io.*;
import java.util.*;

public class Main {
  private static final String DATOS_TXT = "clientes.txt";
  private static final String DATOS_DAT = "clientes.dat";
  private static final String INDICE_BIN = "indice.bin";
  private static final String RESULTADOS = "resultados.txt";
  private static final int TAM_REG = 80;
  private static final int TAM_BLOQUE = 4;

  public static void main(String[] args) throws Exception {
    System.out.println("Busqueda Externa - Version Avanzada\n");
    prepararDatosIniciales();

    Scanner sc = new Scanner(System.in);
    int opt = -1;
    while (opt != 0) {
     mostrarMenu();
     try {
      opt = Integer.parseInt(sc.nextLine().trim());
     } catch (Exception e) {
      opt = -1;
     }
     switch (opt) {
      case 1 -> ejecutarBloquesRAF(sc);
      case 2 -> ejecutarIndiceBinario(sc);
      case 3 -> ejecutarHash(sc);
      case 4 -> ejecutarListaInvertida(sc);
      case 5 -> ejecutarMultiLista(sc);
      case 6 -> compararMetodos(sc);
      case 0 -> System.out.println("Saliendo......Bais");
      default -> System.out.println("Opcion No Valida");
     }
    }
    sc.close();
  }

  private static void mostrarMenu() {
    System.out.println("\n\n==========MENU==========\n" +
        "1. Busqueda por bloques(RandomAccessFile)\n" +
        "2. Busqueda con indice binario\n" +
        "3. Busqueda por transformacion (hash)\n" +
        "4. Lista invertida\n" +
        "5. Multilista\n" +
        "6. Ejecutar comparacion\n" +
        "0. Salir" +
        "Elija una opcion [0-6]:");
  }

  private static void prepararDatosIniciales() throws IOException {
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
      for (String r : regs) {
       bw.write(r);
       bw.newLine();
      }
     }
    }
    //Crear archivo .dat con registros de tamaño fijo
    File fdat = new File(DATOS_DAT);
    if (!fdat.exists()) {
      try (RandomAccessFile raf = new RandomAccessFile(fdat,"rw");
          BufferedReader br = new BufferedReader(new FileReader(DATOS_TXT))) {
        String line;
        while ((line = br.readLine()) != null) {
          String rec = String.format("%-" + TAM_REG + "s", line);
          raf.write(rec.getBytes());
        }
      }
    }
    new File(RESULTADOS).delete();
  }

  private static void ejecutarBloquesRAF(Scanner sc) throws IOException {
    System.out.print("Ingrese ID a buscar:");
    String id = sc.nextLine().trim();
    BusquedaBloquesRAF b = new BusquedaBloquesRAF(TAM_BLOQUE,TAM_REG);
    long start = System.nanoTime();
    String res = b.buscar(DATOS_DAT,id);
    long end = System.nanoTime();
    System.out.println(res);
    System.out.printf("Tiempo: %.3f ms | Accesos disco (bloque leidos): %d\n", (end-start)/1e6, b.getAccesosDisco());
    appendResultados("bloques,"+id+","+b.getAccesosDisco()+","+((end-start)/1e6));
  }

  private static void ejecutarIndiceBinario(Scanner sc) throws IOException {
    System.out.print("Ingrese ID a buscar:");
  }

  private static void ejecutarHash(Scanner sc) throws IOException {
    
  }

  private static void ejecutarListaInvertida(Scanner sc) throws IOException {
    
  }

  private static void ejecutarMultiLista(Scanner sc) throws IOException {
    
  }

  private static void compararMetodos(Scanner sc) throws IOException {
    
  }

  private static void appendResultados(String linea) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(RESULTADOS,true))) {
      bw.write(linea);
      bw.newLine();
    } catch (Exception e) {
      //nada
    }
  }
}
