public class ListaGuerrero {

  NodoGuerrero inicio;

  public ListaGuerrero() {
    this.inicio = null;
  }
  
  public void insertarAlFinal(Guerrero g){
    NodoGuerrero nuevo = new NodoGuerrero(g);

    if (inicio == null) {
      inicio = nuevo;
    } else {
      NodoGuerrero actual = inicio;
      
      while (actual.siguiente != null) {
        actual = actual.siguiente;
      }

      actual.siguiente = nuevo;
    }
  }

  public void mostrar(){
    if (inicio == null) {
      System.out.println("No hay guerreros");
      return;
    } else {
      NodoGuerrero actual = inicio;

      while (actual != null) {
        System.out.print(actual.guerrero + " -> ");
        actual = actual.siguiente;
      }
      System.out.println("null");
    }
  }
}
