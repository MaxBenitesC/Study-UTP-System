public class ListaMascota {
   NodoMascota inicio;

   public ListaMascota() {
     this.inicio = null;
   }

   public void insertarAlFinal(Mascota m){
     NodoMascota nuevo = new NodoMascota(m);

     if (inicio == null) {
       inicio = nuevo;
     } else {
       NodoMascota actual = inicio;

       while (actual.siguiente != null) {
         actual = actual.siguiente;
       }

       actual.siguiente = nuevo;
     }
   }

   public void mostrar(){
     if (inicio == null) {
      System.out.println("NO hay mascotas");
      return;
     } else {
       NodoMascota actual = inicio;

       while (actual != null) {
        System.out.println(actual.mascota + " -> ");
        actual = actual.siguiente;
       }

       System.out.print("NULL");
     }
   }
}
