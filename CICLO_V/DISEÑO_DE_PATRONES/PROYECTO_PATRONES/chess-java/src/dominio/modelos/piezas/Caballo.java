package dominio.modelos.piezas;

import dominio.enums.Color;
import dominio.enums.TipoPieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;

import java.util.ArrayList;
import java.util.List;

public class Caballo extends Pieza {
    
    public Caballo(Color color, Posicion posicion) {
        super(color, posicion, TipoPieza.CABALLO);
    }
    
    @Override
    public List<Posicion> getMovimientosPosibles(Tablero tablero) {
        List<Posicion> movimientos = new ArrayList<>();
        int[][] saltos = {
            {-2, -1}, {-2, 1},
            {-1, -2}, {-1, 2},
            {1, -2}, {1, 2},
            {2, -1}, {2, 1}
        };
        
        for (int[] salto : saltos) {
            Posicion nuevaPos = new Posicion(posicion.getFila() + salto[0], posicion.getColumna() + salto[1]);
            
            if (nuevaPos.esValida()) {
                Pieza piezaDestino = tablero.getPieza(nuevaPos);
                if (piezaDestino == null || esEnemiga(piezaDestino)) {
                    movimientos.add(nuevaPos);
                }
            }
        }
        
        return movimientos;
    }
}
