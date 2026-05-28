public class AlgoritmoDeOrdenamiento {
  private int arreglo[];

  public AlgoritmoDeOrdenamiento(int arreglo[]){
    this.arreglo=arreglo;
  }

  public int buscaMenor(int primerIndice,int ultimoIndice){
    int menor = arreglo[primerIndice];
    int indMenor=primerIndice;
    for (int i = primerIndice; i <= ultimoIndice; i++) {
      if(menor>arreglo[i]){
        menor=arreglo[i];
        indMenor=i;
      }
    }
    return indMenor;
  }
  
  public void ordenamientoBurbuja(int[] arr){
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      for (int j= 0; j < n - i -1; j++) {
        if (arr[j] > arr[j + 1]){
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
    mostrarArreglo_2(arr);
  }

  public void ordenamientoSeleccion(int[] arr){
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
      int min = i;
      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[min]){
          min = j;
        }
      }
      int temp=arr[min];
      arr[min]=arr[i];
      arr[i] = temp;
    }
    mostrarArreglo_2(arr);
  }

  public void ordenamientoInsercion(int[] arr){
    int n = arr.length;
    for (int i = 1; i < n; i++) {
      int key = arr[i];
      int j = i - 1;
      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j--;
      }
      arr[j + 1] = key;
    }
    mostrarArreglo_2(arr);
  }

  public int [] mezclaDirecta(int[] arreglo){
    int i,j,k;
    if(arreglo.length >1){
      int nElementosIzq = arreglo.length / 2;
      int nElementosDer = arreglo.length - nElementosIzq;
      int arregloIzq[] = new int[nElementosIzq];
      int arregloDer[] = new int[nElementosDer];

      for (i = 0; i < nElementosIzq; i++) {
        arregloIzq[i]=arreglo[i];
      }
      for (i = nElementosIzq; i < nElementosIzq + nElementosDer; i++) {
        arregloDer[i - nElementosIzq] = arreglo[i];
      }
      //RECURSIVIDAD
      arregloIzq = mezclaDirecta(arregloIzq);
      arregloDer = mezclaDirecta(arregloDer);
      i = 0;
      j = 0;
      k = 0;
      while (arregloIzq.length != j && arregloDer.length != k) {
        if (arregloIzq[j]<arregloDer[k]) {
          arreglo[i] = arregloIzq[j];
          i++;
          j++;
        } else{
          arreglo[i] = arregloDer[k];
          i++;
          k++;
        }
      }
      //ARREGLO FINAL
      while (arregloIzq.length != j ) {
        arreglo[i] = arregloIzq[j];
        i++;
        j++;
      }
      while (arregloDer.length != k) {
        arreglo[i] = arregloDer[k];
        i++;
        k++;
      }
    }//Fin del if
    return arreglo;
  }

  public void mezclaNatural(int[] arreglo){
    int izquierda=0,izq=0,derecha=arreglo.length-1,der=derecha;
    boolean ordenado=false;
    do {
      ordenado=true;
      izquierda=0;
      while (izquierda < derecha) {
        izq=izquierda;
        while (izq < derecha && arreglo[izq] <= arreglo[izq + 1]) {
          izq++;
        }
        der=izq+1;
        while (der==derecha-1 || der < derecha && arreglo[der] <= arreglo[der+1]) {
          der++;
        }
        if (der <= derecha) {
          mezclaDirecta2(arreglo);
          ordenado=false;
        }
        izquierda=izq;
      }
    } while (!ordenado);
  }

  public void mezclaDirecta2(int[] arreglo){
    int i,j,k;
    if(arreglo.length >1){
      int nElementosIzq = arreglo.length / 2;
      int nElementosDer = arreglo.length - nElementosIzq;
      int arregloIzq[] = new int[nElementosIzq];
      int arregloDer[] = new int[nElementosDer];

      for (i = 0; i < nElementosIzq; i++) {
        arregloIzq[i]=arreglo[i];
      }
      for (i = nElementosIzq; i < nElementosIzq + nElementosDer; i++) {
        arregloDer[i - nElementosIzq] = arreglo[i];
      }
      //RECURSIVIDAD
      arregloIzq = mezclaDirecta(arregloIzq);
      arregloDer = mezclaDirecta(arregloDer);
      i = 0;
      j = 0;
      k = 0;
      while (arregloIzq.length != j && arregloDer.length != k) {
        if (arregloIzq[j]<arregloDer[k]) {
          arreglo[i] = arregloIzq[j];
          i++;
          j++;
        } else{
          arreglo[i] = arregloDer[k];
          i++;
          k++;
        }
      }
      //ARREGLO FINAL
      while (arregloIzq.length != j ) {
        arreglo[i] = arregloIzq[j];
        i++;
        j++;
      }
      while (arregloDer.length != k) {
        arreglo[i] = arregloDer[k];
        i++;
        k++;
      }
    }//Fin del if
  }

  public void mostrarArreglo(int[] a){
    System.out.print("[");
    for (int i = 0; i <= arreglo.length - 1; i++) {
      if(i>0){
        System.out.print(", ");
      }
      System.out.print(a[i]);
    }
    System.out.print("]\n");
  }

  public void mostrarArreglo_2(int[] arreglo){
    int k;
    for (k = 0; k < arreglo.length; k++) {
      System.out.print("[" + arreglo[k] + "]");
    }
    System.out.println();
  }
}
