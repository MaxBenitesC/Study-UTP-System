package dominio.modelos;

public class Posicion {
    private int fila;
    private int columna;
    
    public Posicion(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
    }
    
    public Posicion(String notacion) {
        this.columna = notacion.charAt(0) - 'a';
        this.fila = '8' - notacion.charAt(1);
    }
    
    public boolean esValida() {
        return fila >= 0 && fila < 8 && columna >= 0 && columna < 8;
    }
    
    public String toNotacion() {
        return "" + (char)('a' + columna) + (8 - fila);
    }
    
    public int getFila() {
        return fila;
    }
    public int getColumna() {
        return columna;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Posicion posicion = (Posicion) obj;
        return fila == posicion.fila && columna == posicion.columna;
    }
    
    @Override
    public String toString() {
        return toNotacion();
    }
}
