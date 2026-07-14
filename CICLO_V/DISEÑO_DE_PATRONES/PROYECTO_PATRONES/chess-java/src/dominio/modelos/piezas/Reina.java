package dominio.modelos.piezas;

import dominio.enums.Color;
import dominio.enums.TipoPieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Reina extends Pieza {
    
    public Reina(Color color, Posicion posicion) {
        super(color, posicion, TipoPieza.REINA);
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
            
            while (fila >= 0 && fila < 8 && col >= 0 && col < 8) {
                Posicion nuevaPos = new Posicion(fila, col);
                Pieza pieza = tablero.getPieza(nuevaPos);
                
                if (pieza == null) {
                    movimientos.add(nuevaPos);
                } else {
                    if (esEnemiga(pieza)) {
                        movimientos.add(nuevaPos);
                    }
                    break;
                }
                
                fila += dir[0];
                col += dir[1];
            }
        }
        
        return movimientos;
    }
}
