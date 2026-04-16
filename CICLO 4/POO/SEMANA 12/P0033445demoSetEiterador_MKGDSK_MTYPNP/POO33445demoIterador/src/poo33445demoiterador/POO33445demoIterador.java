package poo33445demoiterador;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class POO33445demoIterador {
    public static void recorrerNombres(List<String> nombres){
        Iterator<String> it=nombres.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public static void main(String[] args) {
        List<String> nombres=new ArrayList<>(List.of("hugo","paco","luis"));
//        List<String> nombres=new ArrayList<>();
//        nombres.add("hugo");nombres.add("paco");nombres.add("luis");
        recorrerNombres(nombres);
    }
}
