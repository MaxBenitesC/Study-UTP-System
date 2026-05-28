
package ordenamiento_interno;

import java.util.Random;


public class Ordenamiento_Interno {
    public static void main(String[] args) {
        int n = 100000;
        int[] arreglo = generarArreglo(n);

        // Clonar arreglo para probar cada algoritmo con la misma entrada
        int[] arrBurbuja = arreglo.clone();
        int[] arrSeleccion = arreglo.clone();
        int[] arrInsercion = arreglo.clone();

        // Burbuja
        long inicio = System.nanoTime();
        OrdenamientoInterno.burbuja(arrBurbuja);
        long fin = System.nanoTime();
        long tiempoBurbuja = fin - inicio;
        System.out.println("Burbuja: \t" + tiempoBurbuja + " ns");

        // Selección
        inicio = System.nanoTime();
        OrdenamientoInterno.seleccion(arrSeleccion);
        fin = System.nanoTime();
        long tiempoSeleccion = fin - inicio;
        System.out.println("Selección: \t" + tiempoSeleccion + " ns");

        // Inserción
        inicio = System.nanoTime();
        OrdenamientoInterno.insercion(arrInsercion);
        fin = System.nanoTime();
        long tiempoInsercion = fin - inicio;
        System.out.println("Inserción: \t" + tiempoInsercion + " ns");

        //INTERCALACION

    }

    // Genera arreglo aleatorio
    public static int[] generarArreglo(int n) {
        Random rnd = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = rnd.nextInt(100000); // valores entre 0 y 100000
            System.out.println("Elemento "+i+" = "+arr[i]);
        } 
        return arr;
    }
}

