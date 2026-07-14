package dominio.modelos.piezas;

import dominio.enums.Color;
import dominio.enums.TipoPieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Rey extends Pieza {
    
    public Rey(Color color, Posicion posicion) {
        super(color, posicion, TipoPieza.REY);
    }
    
    @Override
    public List<Posicion> getMovimientosPosibles(Tablero tablero) {
        List<Posicion> movimientos = new ArrayList<>();
        int[][] direcciones = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1},
            {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
        };
        
        for (int[] dir : direcciones) {
            int fila = posicion.getFila() + dir[0];
            int col = posicion.getColumna() + dir[1];
            
            Posicion nuevaPos = new Posicion(fila, col);
            if (nuevaPos.esValida()) {
                Pieza pieza = tablero.getPieza(nuevaPos);
                if (pieza == null || esEnemiga(pieza)) {
                    Color colorEnemigo = (this.color == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
                    if (!tablero.esCasillaAtacada(nuevaPos, colorEnemigo)) {
                        movimientos.add(nuevaPos);
                    }
                }
            }
        }
        
        return movimientos;
    }
}
