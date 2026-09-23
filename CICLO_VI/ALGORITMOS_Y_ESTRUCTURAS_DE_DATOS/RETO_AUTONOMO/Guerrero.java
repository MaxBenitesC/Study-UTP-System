public class Guerrero {
 private String nombre;
 private int ki;
 
 public Guerrero(String nombre, int ki) {
	this.nombre = nombre;
	this.ki = ki;
 }

 public String getNombre() {
	return nombre;
 }

 public int getKi() {
	return ki;
 }

 @Override
 public String toString() {
	return "[" + nombre + " | Ki: " + ki + "]";
 }
}
