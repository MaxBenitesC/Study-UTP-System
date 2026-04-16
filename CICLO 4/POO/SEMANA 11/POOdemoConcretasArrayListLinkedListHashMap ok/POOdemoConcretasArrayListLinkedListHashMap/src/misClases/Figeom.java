package misClases;
import java.util.Date;
public abstract class Figeom {
    private String color;
    private boolean relleno;
    private Date fechacrea;

    @Override
    public String toString() {
        return "Figeom{" + "color=" 
                + color + ", relleno=" 
                + relleno + ", fechacrea=" + fechacrea + '}';
    }

    public Figeom() {
        color="crema";
        relleno=true;
        fechacrea=new Date();
    }

    public Figeom(String color, boolean relleno) {
        this.color = color;
        this.relleno = relleno;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isRelleno() {
        return relleno;
    }

    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    public Date getFechacrea() {
        return fechacrea;
    }
    public abstract double area();
    public abstract double perimetro();
}













