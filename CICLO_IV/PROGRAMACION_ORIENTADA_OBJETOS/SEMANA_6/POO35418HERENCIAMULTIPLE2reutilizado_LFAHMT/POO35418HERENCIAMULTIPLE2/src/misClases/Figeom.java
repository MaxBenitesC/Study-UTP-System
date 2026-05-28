package misClases;
import java.util.Date;
public class Figeom{
 private String color;
 private boolean relleno;
 private Date fechacrea;
 public Figeom(){
   this("Crema",true);
 }
 public Figeom(String color, boolean relleno){
   this.setColor(color); this.setRelleno(relleno);
   fechacrea=new Date();
 }
 public String toString(){
   return color+" "+relleno+" "+fechacrea;
 }
 public void setColor(String color){
   this.color=color;
 }
 public void setRelleno(boolean relleno){
   this.relleno=relleno;
 }
 public String getColor(){return color;}
 public boolean isRelleno(){return relleno;}
}