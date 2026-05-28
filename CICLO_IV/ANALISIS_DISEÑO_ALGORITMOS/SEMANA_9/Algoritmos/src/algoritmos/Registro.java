package algoritmos;

// Simple POJO for a record
public class Registro {
    private String id;
    private String nombre;
    private String ciudad;

    public Registro(String id, String nombre, String ciudad) {
        this.id = id;
        this.nombre = nombre;
        this.ciudad = ciudad;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getCiudad() { return ciudad; }

    @Override
    public String toString() {
        return id + "|" + nombre + "|" + ciudad;
    }
}
