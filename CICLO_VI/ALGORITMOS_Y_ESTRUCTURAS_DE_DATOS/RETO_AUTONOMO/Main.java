public class Main {
 public static void main(String[] args) {

   ListaGuerrero batalla = new ListaGuerrero();

   System.out.println("BATALLA VACIA:");
   batalla.mostrar();

   System.out.println("Creamos 3 guerreros");
   batalla.insertarAlFinal(new Guerrero("GOKU", 100));
   batalla.insertarAlFinal(new Guerrero("FREEZER", 120));
   batalla.insertarAlFinal(new Guerrero("CELL", 80));

   System.out.println("Mostramos los 3 guerreros");
   batalla.mostrar();
 } 
}
