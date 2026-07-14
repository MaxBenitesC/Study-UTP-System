package dominio.modelos.piezas;

import dominio.enums.Color;
import dominio.enums.TipoPieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;

import java.util.List;

public abstract class Pieza {
    protected Color color;
    protected Posicion posicion;
    protected TipoPieza tipo;
    
    public Pieza(Color color, Posicion posicion, TipoPieza tipo) {
        this.color = color;
        this.posicion = posicion;
        this.tipo = tipo;
    }
    
    public abstract List<Posicion> getMovimientosPosibles(Tablero tablero);

    public boolean esMovimientoValido(Posicion destino, Tablero tablero){
        List<Posicion> movimientos = getMovimientosPosibles(tablero);
        return movimientos.contains(destino);
    }
    
    public void mover(Posicion nuevaPosicion) {
        this.posicion = nuevaPosicion;
    }
    
    protected boolean esEnemiga(Pieza otraPieza) {
        return otraPieza != null && this.color != otraPieza.color;
    }
    
    protected boolean esAliada(Pieza otraPieza) {
        return otraPieza != null && this.color == otraPieza.color;
    }
    
    public Color getColor() { return color; }
    public Posicion getPosicion() { return posicion; }
    public TipoPieza getTipo() { return tipo; }
    
    @Override
    public String toString() {
        return tipo + " " + color;
    }
}
