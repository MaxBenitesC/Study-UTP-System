package presentacion.vistas;

import dominio.modelos.Usuario;
import dominio.puertos.RepositorioUsuario;
import infraestructura.persistencia.RepositorioUsuarioMySQL;
import shared.utils.ImageUtil;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class VistaRanking extends JFrame {

    private final List<Usuario> usuarios;
    private JPanel pnlRanking;

    public VistaRanking() {
        RepositorioUsuario repo = new RepositorioUsuarioMySQL();
        this.usuarios = repo.obtenerUsuarios();
        initComponents();
        cargarRanking();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        topBar.setBackground(Color.WHITE);
        JLabel lblCerrar = new JLabel("X");
        lblCerrar.setFont(new Font("Roboto Light", Font.BOLD, 24));
        lblCerrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCerrar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new VistaMenuPrincipal().mostrar();
                dispose();
            }
            @Override
            public void mouseEntered(MouseEvent e) { lblCerrar.setForeground(Color.RED); }
            @Override
            public void mouseExited(MouseEvent e) { lblCerrar.setForeground(Color.BLACK); }
        });
        topBar.add(lblCerrar);
        mainPanel.add(topBar, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 30, 100));

        JLabel lblTitulo = new JLabel("RANKING DE JUGADORES");
        lblTitulo.setFont(new Font("Roboto Medium", Font.BOLD, 36));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        centerPanel.add(lblTitulo);
        centerPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        pnlRanking = new JPanel();
        pnlRanking.setLayout(new BoxLayout(pnlRanking, BoxLayout.Y_AXIS));
        pnlRanking.setBackground(Color.WHITE);
        JScrollPane scroll = new JScrollPane(pnlRanking);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerPanel.add(scroll);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        this.setContentPane(mainPanel);
    }

    private void cargarRanking() {
        List<Usuario> ordenados = usuarios.stream()
                .sorted((u1, u2) -> Integer.compare(u2.getPartidasGanadas(), u1.getPartidasGanadas()))
                .collect(Collectors.toList());

        for (int i = 0; i < ordenados.size(); i++) {
            Usuario u = ordenados.get(i);
            JPanel fila = new JPanel(new BorderLayout());
            fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));
            fila.setBackground(Color.WHITE);
            fila.setBorder(new LineBorder(Color.LIGHT_GRAY, 1));

            JLabel lblPos = new JLabel(String.valueOf(i + 1) + ".");
            lblPos.setFont(new Font("Roboto", Font.BOLD, 24));
            lblPos.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
            fila.add(lblPos, BorderLayout.WEST);

            JLabel lblInfo = new JLabel(u.getNombreUsuario() + " - " + u.getPartidasGanadas() + " victorias");
            lblInfo.setFont(new Font("Roboto", Font.PLAIN, 20));
            fila.add(lblInfo, BorderLayout.CENTER);

            if (i < 3) {
                JLabel lblMedalla = new JLabel();
                lblMedalla.setMaximumSize(new Dimension(60, 60));
                lblMedalla.setMinimumSize(new Dimension(60, 60));
                lblMedalla.setPreferredSize(new Dimension(60, 60));
                String rutaMedalla = getRutaMedalla(i + 1);
                ImageUtil.escalarImg(lblMedalla, rutaMedalla);
                fila.add(lblMedalla, BorderLayout.EAST);
            }

            pnlRanking.add(fila);
        }
        pnlRanking.revalidate();
        pnlRanking.repaint();
    }

    private String getRutaMedalla(int posicion){
        switch (posicion) {
            case 1:
                return "assets/images/medalla_oro.png";
            case 2:
                return "assets/images/medalla_plata.png";
            case 3:
                return "assets/images/medalla_bronce.png";
            default:
                return "";
        }
    }

    public void mostrar() {
        this.setVisible(true);
    }
}
