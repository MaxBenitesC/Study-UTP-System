package presentacion.vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import shared.utils.ImageUtil;
import dominio.modelos.piezas.Pieza;
import dominio.modelos.Posicion;
import dominio.modelos.Tablero;
import dominio.modelos.Usuario;
import dominio.puertos.IGestionarJuego;
import aplicacion.Sesion;
import aplicacion.casosuso.GestionarJuego;

public class VistaJuego extends JFrame {

    private JPanel pnlTablero;
    private JPanel pnlInfo;
    private JPanel[][] casillas;
    private JPanel pnlJugador1;
    private JPanel pnlJugador2;
    
    private IGestionarJuego gestionarJuego;
    private Posicion seleccionOrigen;

    private final Color colorClaro = new Color(240, 217, 181);
    private final Color colorOscuro = new Color(181, 136, 99);

    public VistaJuego() {
        Usuario u1 = Sesion.usuario();
        Usuario u2 = new Usuario("jugador2", "Jugador", "Dos", "pass2");
        gestionarJuego = new GestionarJuego();
        gestionarJuego.crearPartida(u1, u2);
        
        initComponents();
        inicializarTablero();
        actualizarTablero();
        actualizarTurno();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        pnlTablero = new JPanel(new GridLayout(8, 8));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.66; 
        int padding = 50;
        gbc.insets = new Insets(padding, padding, padding, padding);
        mainPanel.add(pnlTablero, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);

        pnlInfo = new JPanel(new GridLayout(2, 1));
        pnlInfo.setBackground(Color.WHITE);
        
        pnlJugador2 = crearPanelJugador("Jugador 2 (Negras)", "assets/images/user.png");
        pnlInfo.add(pnlJugador2);
        
        pnlJugador1 = crearPanelJugador("Jugador 1 (Blancas)", "assets/images/user.png");
        pnlInfo.add(pnlJugador1);

        gbc.gridx = 1;
        gbc.weightx = 0.33;
        mainPanel.add(pnlInfo, gbc);

        JButton btnSalir = new JButton("Volver al Menú");
        btnSalir.addActionListener(e -> {
            new VistaMenuPrincipal().mostrar();
            dispose();
        });
        JPanel pnlBoton = new JPanel();
        pnlBoton.add(btnSalir);
        pnlInfo.add(pnlBoton, 0);
        pnlInfo.setLayout(new BorderLayout());
        pnlInfo.removeAll();
        
        JPanel pnlTopRight = new JPanel(new BorderLayout());
        pnlTopRight.add(pnlJugador2, BorderLayout.CENTER);
        
        JLabel lblCerrar = new JLabel("X  ");
        lblCerrar.setFont(new Font("Arial", Font.BOLD, 20));
        lblCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCerrar.setHorizontalAlignment(SwingConstants.RIGHT);
        lblCerrar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                new VistaMenuPrincipal().mostrar();
                dispose();
            }
        });
        pnlTopRight.add(lblCerrar, BorderLayout.NORTH);

        pnlInfo.add(pnlTopRight, BorderLayout.NORTH);
        pnlInfo.add(pnlJugador1, BorderLayout.SOUTH);
        
        JPanel pnlExtra = new JPanel();
        pnlExtra.setBackground(Color.LIGHT_GRAY);
        pnlExtra.add(new JLabel("Historial de Movimientos"));
        pnlInfo.add(pnlExtra, BorderLayout.CENTER);


        mainPanel.add(pnlInfo, gbc);
        this.setContentPane(mainPanel);
    }

    private JPanel crearPanelJugador(String nombre, String imgPath) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder(nombre));
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(0, 150));
        
        JLabel lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(50, 50));
        lblAvatar.setBorder(new LineBorder(Color.BLACK));
        lblAvatar.setText("IMG");
        
        JLabel lblNombre = new JLabel(nombre);
        lblNombre.setFont(new Font("Roboto", Font.BOLD, 16));
        
        panel.add(lblAvatar);
        panel.add(lblNombre);
        
        return panel;
    }

    private void inicializarTablero() {
        casillas = new JPanel[8][8];
        pnlTablero.removeAll();

        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JPanel casilla = new JPanel(new BorderLayout());
                if ((fila + col) % 2 == 0) {
                    casilla.setBackground(colorClaro);
                } else {
                    casilla.setBackground(colorOscuro);
                }
                
                final int r = fila;
                final int c = col;
                casilla.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        manejarClick(new Posicion(r, c));
                    }
                });
                
                casillas[fila][col] = casilla;
                pnlTablero.add(casilla);
            }
        }
    }

    private void actualizarTablero() {
        Tablero tablero = gestionarJuego.getTablero();
        
        for (int fila = 0; fila < 8; fila++) {
            for (int col = 0; col < 8; col++) {
                JPanel casilla = casillas[fila][col];
                casilla.removeAll();
                
                if ((fila + col) % 2 == 0) {
                    casilla.setBackground(colorClaro);
                } else {
                    casilla.setBackground(colorOscuro);
                }
                
                Posicion posActual = new Posicion(fila, col);
                if (seleccionOrigen != null) {
                    if (posActual.equals(seleccionOrigen)) {
                        casilla.setBackground(new Color(186, 202, 68)); 
                    } else {
                        Pieza piezaSeleccionada = tablero.getPieza(seleccionOrigen);
                        if (piezaSeleccionada != null && piezaSeleccionada.esMovimientoValido(posActual, tablero)) {
                             casilla.setBackground(new Color(214, 214, 189)); 
                        }
                    }
                }
                
                Pieza pieza = tablero.getPieza(posActual);
                if (pieza != null) {
                    String tipo = pieza.getTipo().toString().toLowerCase();
                    String color = (pieza.getColor() == dominio.enums.Color.BLANCO) ? "blanco" : "negro";
                    colocarPieza(casilla, tipo, color);
                }
                
                casilla.revalidate();
                casilla.repaint();
            }
        }
    }

    private void manejarClick(Posicion pos) {
        Tablero tablero = gestionarJuego.getTablero();
        Pieza piezaClickeada = tablero.getPieza(pos);
        
        if (seleccionOrigen == null) {
            if (piezaClickeada != null && piezaClickeada.getColor() == gestionarJuego.getTurnoActual()) {
                seleccionOrigen = pos;
                actualizarTablero();
            }
        } else {
            if (pos.equals(seleccionOrigen)) {
                seleccionOrigen = null;
                actualizarTablero();
            } else {
                if (gestionarJuego.realizarMovimiento(seleccionOrigen, pos)) {
                    seleccionOrigen = null;
                    actualizarTablero();
                    actualizarTurno();
                    verificarEstadoJuego();
                } else {
                    if (piezaClickeada != null && piezaClickeada.getColor() == gestionarJuego.getTurnoActual()) {
                        seleccionOrigen = pos;
                        actualizarTablero();
                    }
                }
            }
        }
    }

    private void colocarPieza(JPanel casilla, String tipo, String color) {
        String ruta = "assets/piezas/" + tipo + "_" + color + ".png";
        JLabel lblPieza = new JLabel();
        lblPieza.setHorizontalAlignment(SwingConstants.CENTER);
        
        lblPieza.setSize(80, 80);
        ImageUtil.escalarImgMantenerProporcion(lblPieza, ruta);
        
        casilla.add(lblPieza, BorderLayout.CENTER);
    }
    
    public void mostrar() {
        this.setVisible(true);
    }

    private void actualizarTurno() {
        dominio.enums.Color turno = gestionarJuego.getTurnoActual();
        if (turno == dominio.enums.Color.BLANCO) {
            pnlJugador1.setBackground(new Color(220, 255, 220));
            pnlJugador2.setBackground(Color.WHITE);
        } else {
            pnlJugador1.setBackground(Color.WHITE);
            pnlJugador2.setBackground(new Color(220, 255, 220));
        }
    }

    private void verificarEstadoJuego() {
        dominio.enums.Color turnoActual = gestionarJuego.getTurnoActual();
        if (gestionarJuego.estaEnJaque(turnoActual)) {
            if (gestionarJuego.estaEnJaqueMate(turnoActual)) {
                JOptionPane.showMessageDialog(this, 
                    "¡JAQUE MATE! Ganaron las " + (turnoActual == dominio.enums.Color.BLANCO ? "Negras" : "Blancas"), 
                    "Fin del Juego", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                new VistaMenuPrincipal().mostrar();
                dispose();
            } else {
                System.out.println("Estas en jaque");
                JOptionPane.showMessageDialog(this, 
                    "¡ESTÁS EN JAQUE!", 
                    "Alerta", 
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }
}
