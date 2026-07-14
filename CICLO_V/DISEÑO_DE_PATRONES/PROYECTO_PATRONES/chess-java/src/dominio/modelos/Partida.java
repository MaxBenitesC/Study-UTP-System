package dominio.modelos;

import dominio.enums.Color;
import dominio.enums.EstadoJuego;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Partida {
    private String id;
    private Usuario jugadorBlancas;
    private Usuario jugadorNegras;
    private Tablero tablero;
    private Color turnoActual;
    private EstadoJuego estado;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<String> movimientos;
    
    public Partida(Usuario jugadorBlancas, Usuario jugadorNegras) {
        this.id = generarId();
        this.jugadorBlancas = jugadorBlancas;
        this.jugadorNegras = jugadorNegras;
        this.tablero = new Tablero();
        this.turnoActual = Color.BLANCO;
        this.estado = EstadoJuego.EN_PROGRESO;
        this.fechaInicio = LocalDateTime.now();
        this.movimientos = new ArrayList<>();
    }
    
    public boolean realizarMovimiento(Posicion origen, Posicion destino) {
        if (estado != EstadoJuego.EN_PROGRESO) return false;
        
        if (!tablero.ejecutarMovimiento(origen, destino)) return false;
        
        String movimiento = origen.toNotacion() + "-" + destino.toNotacion();
        movimientos.add(movimiento);
        
        turnoActual = (turnoActual == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;
        
        return true;
    }
    
    public void finalizarPartida(Usuario ganador) {
        this.estado = EstadoJuego.FINALIZADO;
        this.fechaFin = LocalDateTime.now();
        
        if (ganador != null) {
            ganador.incrementarVictorias();
            Usuario perdedor = ganador.equals(jugadorBlancas) ? jugadorNegras : jugadorBlancas;
            perdedor.incrementarDerrotas();
        } else {
            jugadorBlancas.incrementarEmpates();
            jugadorNegras.incrementarEmpates();
        }
        
        jugadorBlancas.agregarPartida(this);
        jugadorNegras.agregarPartida(this);
    }
    
    public Usuario getJugadorActual() {
        return turnoActual == Color.BLANCO ? jugadorBlancas : jugadorNegras;
    }
    
    private String generarId() {
        return "GAME-" + System.currentTimeMillis();
    }
    
    public String getId() {
        return id;
    }
    public Usuario getJugadorBlancas() {
        return jugadorBlancas;
    }
    public Usuario getJugadorNegras() {
        return jugadorNegras;
    }
    public Tablero getTablero() {
        return tablero;
    }
    public Color getTurnoActual() {
        return turnoActual;
    }
    public EstadoJuego getEstado() {
        return estado;
    }
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }
    public List<String> getMovimientos() {
        return movimientos;
    }
    
    @Override
    public String toString() {
        return "Partida{" +
                "blancas=" + jugadorBlancas.getNombreUsuario() +
                ", negras=" + jugadorNegras.getNombreUsuario() +
                ", estado=" + estado +
                ", turno=" + turnoActual +
                '}';
    }
}
