public class Main {
    public static void main(String[] args) {

      ListaMascota veterinaria = new ListaMascota();

      System.out.println("CANCHA VACIA");
      veterinaria.mostrar();

      System.out.println("INSERTANDO 3 MASCOTAS AL FINAL");
      veterinaria.insertarAlFinal(new Mascota("Boby", "M01", "Perro"));
      veterinaria.insertarAlFinal(new Mascota("Michi", "M02", "Gato"));
      veterinaria.insertarAlFinal(new Mascota("Rocky", "M03", "Loro"));

      System.out.println("Mostrando todo completo");
      veterinaria.mostrar();
    }
}
