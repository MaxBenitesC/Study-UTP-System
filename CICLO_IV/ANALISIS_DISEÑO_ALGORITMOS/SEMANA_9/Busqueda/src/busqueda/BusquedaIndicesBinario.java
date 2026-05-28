package busqueda;
import java.io.*;

public class BusquedaIndicesBinario {

    // Crea un índice binario: UTF(clave) + long(posicion)
    public void crearIndice(String archivoDatos, String archivoIndice, int tamanoRegistro) throws IOException {
        try (RandomAccessFile datos = new RandomAccessFile(archivoDatos, "r");
             DataOutputStream out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(archivoIndice)))) {
            long pos = 0;
            while (datos.getFilePointer() < datos.length()) {
                byte[] buf = new byte[tamanoRegistro];
                datos.readFully(buf);
                String reg = new String(buf).trim();
                String clave = reg.split("\\|", 2)[0].trim();
                out.writeUTF(clave);
                out.writeLong(pos);
                pos += tamanoRegistro;
            }
        }
    }

    public String buscar(String archivoDatos, String archivoIndice, String claveBuscada, int tamanoRegistro) throws IOException {
        try (DataInputStream in = new DataInputStream(new BufferedInputStream(new FileInputStream(archivoIndice)))) {
            while (in.available() > 0) {
                String clave = in.readUTF();
                long pos = in.readLong();
                if (clave.equals(claveBuscada)) {
                    try (RandomAccessFile datos = new RandomAccessFile(archivoDatos, "r")) {
                        datos.seek(pos);
                        byte[] buf = new byte[tamanoRegistro];
                        datos.readFully(buf);
                        return "Encontrado: " + new String(buf).trim();
                    }
                }
            }
        }
        return "No encontrado en índice binario.";
    }
}
