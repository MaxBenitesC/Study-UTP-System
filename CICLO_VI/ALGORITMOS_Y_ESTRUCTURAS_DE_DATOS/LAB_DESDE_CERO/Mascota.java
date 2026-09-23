public class Mascota {
   private String nombre;
   private String codigo;
   private String especie;
   
   public Mascota(String nombre, String codigo, String especie) {
    this.nombre = nombre;
    this.codigo = codigo;
    this.especie = especie;
   }

   public String getNombre() {
     return nombre;
   }

   public String getCodigo() {
     return codigo;
   }

   public String getEspecie() {
     return especie;
   }

   @Override
   public String toString() {
    return "[" + codigo + " | " + nombre + " (" + especie + ")]";
   }

}
