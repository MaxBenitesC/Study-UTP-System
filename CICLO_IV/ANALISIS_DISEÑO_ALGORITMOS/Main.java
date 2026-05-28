import java.util.Random;

public class Main {
  public static void main(String[] args) {

    int n = 100000;
    int [] arreglo = generarArreglo(n);
    int[] vector1 = new int[] {40,23,56,112,57,64,234,8};
    int[] vector2 = new int[] {6,278,76,172,27,604,24,8,-5,-8,0};
    int[] vector3 = new int[] {5,2,1,8,3,9,7,6,-5,-3,0,-6};
    int[] vector4 = new int[] {58,14,78,20,3,0,-15,14,72,65,-5,-1,10};
    int[] vector5 = new int[] {32,6,22,11,9,7,1,32,-8,0,2,35};
    AlgoritmoDeOrdenamiento a = new AlgoritmoDeOrdenamiento(vector1);
    long start = System.nanoTime();
    System.out.println("==================================");
    System.out.println("ARREGLO ORINIGAL 1:");
    a.mostrarArreglo_2(vector1);
    System.out.println("ARREGLO ORDENADO POR SELECCION:");
    a.ordenamientoSeleccion(vector1);
    System.out.println("=================================="); 
    System.out.println("ARREGLO ORIGINAL 2:");
    a.mostrarArreglo_2(vector2);
    System.out.println("ARREGLO ORDENADO POR MEZCLA DIRECTA:");
    vector2=a.mezclaDirecta(vector2);
    a.mostrarArreglo_2(vector2);
    System.out.println("=================================="); 
    System.out.println("ARREGLO ORIGINAL 3:");
    a.mostrarArreglo_2(vector3);
    System.out.println("ARREGLO ORDENADO POR MEZCLA NATURAL:");
    a.mezclaNatural(vector3);
    a.mostrarArreglo_2(vector3);
    System.out.println("==================================");
    System.out.println("ARREGLO ORIGINAL 4:");
    a.mostrarArreglo_2(vector4);
    System.out.println("ARREGLO ORDENADO POR BURBUJA:");
    a.ordenamientoBurbuja(vector4);
    System.out.println("==================================");
    System.out.println("ARREGLO ORIGINAL 5:");
    a.mostrarArreglo_2(vector5);
    System.out.println("ARREGLO ORDENADO POR INSERCION:");
    a.ordenamientoInsercion(vector5);
    System.out.println("==================================");

    long end = System.nanoTime();
    long tiempoTotal= end-start;
    System.out.println("\nEl tiempo total de ejecución es de: " + tiempoTotal + "ns.");
  }

  public static int[] generarArreglo(int n){
    Random rnd = new Random();
    int arr[] = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = rnd.nextInt(1000000);
      System.out.println("Elemento " + i + "=" + arr[i]);
    }
    return arr;
  }
}
