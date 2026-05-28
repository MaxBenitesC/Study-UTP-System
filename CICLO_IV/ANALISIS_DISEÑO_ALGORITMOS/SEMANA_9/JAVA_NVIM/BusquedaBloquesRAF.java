import java.io.*;

public class BusquedaBloquesRAF {
  private int tamanoBloque;
  private int tamanoRegistro;
  private int accesosDisco;

  public BusquedaBloquesRAF(int tamanoBloque, int tamanoRegistro){
    this.tamanoBloque = tamanoBloque;
    this.tamanoRegistro = tamanoRegistro;
    this.accesosDisco = 0;
  }

  public String buscar(String rutaArchivo, String claveBuscada) throws IOException {
    try (RandomAccessFile raf = new RandomAccessFile(rutaArchivo,"r")) {
      long totalRegistros = raf.leng
    } catch (Exception e) {
      //TODO: handle exception
    }
  }


}
