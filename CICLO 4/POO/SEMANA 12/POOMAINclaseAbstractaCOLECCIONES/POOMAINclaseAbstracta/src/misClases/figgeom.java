package misClases;
import java.util.Date;
public abstract class figgeom {
    private String color;
    private boolean relleno;
    private Date fechacrea;

    public figgeom() {
        color="crema";
        relleno=true;
        fechacrea=new Date();
    }
    public abstract double area();
    @Override
    public String toString() {
        return "figgeom{" + "color=" + color 
                + ", relleno="  + relleno 
                + ", fechacrea=" + fechacrea + '}';
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the relleno
     */
    public boolean isRelleno() {
        return relleno;
    }

    /**
     * @param relleno the relleno to set
     */
    public void setRelleno(boolean relleno) {
        this.relleno = relleno;
    }

    /**
     * @return the fechacrea
     */
    public Date getFechacrea() {
        return fechacrea;
    }
}
