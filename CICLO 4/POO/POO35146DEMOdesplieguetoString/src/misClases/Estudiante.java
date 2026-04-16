package misClases;
import java.util.Date;
public class Estudiante {
    public int codigo;
    public String nombre;
    public Date fechacrea;
    public boolean sexo;
    public static int nroEstudiantes=0;
    @Override
    public String toString() {
        return "Estudiante{" + "codigo=" + codigo 
                + ", nombre=" + nombre 
                + ", fechacrea=" + fechacrea 
                + ", sexo=" + sexo + '}';
    }

    public Estudiante(int codigo, String nombre, boolean sexo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.sexo = sexo;
        fechacrea=new Date();
        nroEstudiantes++;
    }
    
    public Estudiante() {
        codigo=Integer.MIN_VALUE;
        nombre="desconocido nn";
        fechacrea=new Date();
        sexo=true;
        nroEstudiantes++;
    }
    
    
}
