public class Trabajador {
  private String codigo;
  private String nombre;
  private double sueldo;

  public Trabajador(String codigo, String nombre, double sueldo) {
    this.codigo = codigo;
    this.nombre = nombre;
    this.sueldo = sueldo;
  }

  public String getCodigo() {
    return codigo;
  }

  public String getNombre() {
    return nombre;
  }

  public double getSueldo() {
    return sueldo;
  }

  public void setSueldo(double sueldo) {
    this.sueldo = sueldo;
  }

  @Override
  public String toString() {
    return "[" + codigo + "] " + nombre + " - Sueldo: S/ " + sueldo;
  }
}
