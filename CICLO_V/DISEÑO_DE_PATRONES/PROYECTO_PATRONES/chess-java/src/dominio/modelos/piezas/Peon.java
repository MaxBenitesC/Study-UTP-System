package dominio.modelos.piezas;

import dominio.enums.Color;
import dominio.enums.TipoPieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Peon extends Pieza {
    
    public Peon(Color color, Posicion posicion) {
        super(color, posicion, TipoPieza.PEON);
    }
    
    @Override
    public List<Posicion> getMovimientosPosibles(Tablero tablero) {
        List<Posicion> movimientos = new ArrayList<>();
        int direccion = (this.color == Color.BLANCO) ? -1 : 1;
        int filaInicio = (this.color == Color.BLANCO) ? 6 : 1;
        
        int filaActual = posicion.getFila();
        int colActual = posicion.getColumna();
        
        Posicion frente = new Posicion(filaActual + direccion, colActual);
        if (frente.esValida() && tablero.getPieza(frente) == null) {
            movimientos.add(frente);
            
            
            if (filaActual == filaInicio) {
                Posicion frente2 = new Posicion(filaActual + 2 * direccion, colActual);
                if (frente2.esValida() && tablero.getPieza(frente2) == null) {
                    movimientos.add(frente2);
                }
            }
        }
        
        int[] colsCaptura = {colActual - 1, colActual + 1};
        for (int col : colsCaptura) {
            Posicion diagonal = new Posicion(filaActual + direccion, col);
            if (diagonal.esValida()) {
                Pieza piezaDestino = tablero.getPieza(diagonal);
                if (piezaDestino != null && esEnemiga(piezaDestino)) {
                    movimientos.add(diagonal);
                }
            }
        }
        
        return movimientos;
    }
}
