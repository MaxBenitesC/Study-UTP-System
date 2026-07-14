package presentacion.vistas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import presentacion.controladores.ControladorLogin;
import shared.utils.ImageUtil;

public class VistaLogin extends JFrame {
    
    private int xMouse, yMouse;
    private ControladorLogin controlador;
    private static final Color COLOR_PLACEHOLDER = new Color(204, 204, 204);
    private static final Color COLOR_TEXTO = Color.BLACK;
    
    private JPanel jPanel_fondo;
    private JLabel jLabel_logo;
    private JLabel jLabel_lateral;
    private JPanel jPanel_encabezado;
    private JPanel jPanel_cerrar;
    private JLabel jLabel5;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JTextField txt_user;
    private JSeparator jSeparator1;
    private JLabel jLabel3;
    private JPasswordField txt_password;
    private JSeparator jSeparator2;
    private JPanel jPanel_ingresar;
    private JLabel label_ingresar;

    public VistaLogin() {
        initComponents();
        configurarPlaceholders();
        cargarImagenes();
        this.setLocationRelativeTo(null);
    }
    
    private void cargarImagenes() {
        ImageUtil.escalarImg(jLabel_logo, "assets/images/img_logo.png");
        ImageUtil.escalarImgPorAltoYCentrar(jLabel_lateral, "assets/images/img_login.jpg");
    }
    
    private void configurarPlaceholders() {
        setupTextFieldPlaceholder(txt_user, "Ingrese su nombre de usuario");
        setupPasswordFieldPlaceholder(txt_password, "***************");
    }

    private void setupTextFieldPlaceholder(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(COLOR_TEXTO);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getText().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(COLOR_PLACEHOLDER);
                }
            }
        });
    }

    private void setupPasswordFieldPlaceholder(JPasswordField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (String.valueOf(field.getPassword()).equals(placeholder)) {
                    field.setText("");
                    field.setForeground(COLOR_TEXTO);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (field.getPassword().length == 0) {
                    field.setText(placeholder);
                    field.setForeground(COLOR_PLACEHOLDER);
                }
            }
        });
    }

    private void initComponents() {

        jPanel_fondo = new JPanel();
        jLabel_logo = new JLabel();
        jLabel_lateral = new JLabel();
        jPanel_encabezado = new JPanel();
        jPanel_cerrar = new JPanel();
        jLabel5 = new JLabel();
        jLabel1 = new JLabel();
        jLabel2 = new JLabel();
        txt_user = new JTextField();
        jSeparator1 = new JSeparator();
        jLabel3 = new JLabel();
        txt_password = new JPasswordField();
        jSeparator2 = new JSeparator();
        jPanel_ingresar = new JPanel();
        label_ingresar = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(255, 255, 255));
        setLocationByPlatform(true);
        setUndecorated(true);
        setResizable(false);

        jPanel_fondo.setBackground(new Color(255, 255, 255));
        jPanel_fondo.setForeground(new Color(255, 255, 255));
        jPanel_fondo.setMinimumSize(new Dimension(800, 500));
        jPanel_fondo.setLayout(null);

        jLabel_logo.setMaximumSize(new Dimension(200, 100));
        jLabel_logo.setMinimumSize(new Dimension(200, 100));
        jLabel_logo.setPreferredSize(new Dimension(200, 100));
        jPanel_fondo.add(jLabel_logo);
        jLabel_logo.setBounds(30, 40, 200, 100);

        jLabel_lateral.setMaximumSize(new Dimension(300, 500));
        jLabel_lateral.setMinimumSize(new Dimension(300, 500));
        jLabel_lateral.setPreferredSize(new Dimension(300, 500));
        jPanel_fondo.add(jLabel_lateral);
        jLabel_lateral.setBounds(500, 0, 300, 500);

        jPanel_encabezado.setBackground(new Color(255, 255, 255));
        jPanel_encabezado.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel_encabezadoMouseDragged(evt);
            }
        });
        jPanel_encabezado.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel_encabezadoMousePressed(evt);
            }
        });

        jPanel_cerrar.setBackground(new Color(255, 255, 255));
        jPanel_cerrar.setForeground(new Color(255, 255, 255));
        jPanel_cerrar.setMaximumSize(new Dimension(30, 30));
        jPanel_cerrar.setMinimumSize(new Dimension(30, 30));
        jPanel_cerrar.setRequestFocusEnabled(false);
        jPanel_cerrar.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel_cerrarMousePressed(evt);
            }
        });

        jLabel5.setFont(new Font("Roboto Light", 0, 24));
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("X");
        jLabel5.setToolTipText("");
        jLabel5.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jLabel5.setMaximumSize(new Dimension(50, 30));
        jLabel5.setMinimumSize(new Dimension(50, 30));
        jLabel5.setPreferredSize(new Dimension(50, 30));
        jLabel5.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                jLabel5MouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                jLabel5MouseExited(evt);
            }
            public void mousePressed(MouseEvent evt) {
                jLabel5MousePressed(evt);
            }
        });

        GroupLayout jPanel_cerrarLayout = new GroupLayout(jPanel_cerrar);
        jPanel_cerrar.setLayout(jPanel_cerrarLayout);
        jPanel_cerrarLayout.setHorizontalGroup(
            jPanel_cerrarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_cerrarLayout.createSequentialGroup()
                .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel_cerrarLayout.setVerticalGroup(
            jPanel_cerrarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GroupLayout jPanel_encabezadoLayout = new GroupLayout(jPanel_encabezado);
        jPanel_encabezado.setLayout(jPanel_encabezadoLayout);
        jPanel_encabezadoLayout.setHorizontalGroup(
            jPanel_encabezadoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_encabezadoLayout.createSequentialGroup()
                .addComponent(jPanel_cerrar, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
                .addGap(0, 750, Short.MAX_VALUE))
        );
        jPanel_encabezadoLayout.setVerticalGroup(
            jPanel_encabezadoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_cerrar, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel_fondo.add(jPanel_encabezado);
        jPanel_encabezado.setBounds(0, 0, 800, 40);

        jLabel1.setFont(new Font("Roboto Medium", 1, 36));
        jLabel1.setText("INICIAR SESIÓN");
        jPanel_fondo.add(jLabel1);
        jLabel1.setBounds(50, 150, 300, 50);

        jLabel2.setFont(new Font("Roboto", 0, 18));
        jLabel2.setText("CONTRASEÑA");
        jPanel_fondo.add(jLabel2);
        jLabel2.setBounds(50, 300, 150, 30);

        txt_user.setFont(new Font("Roboto Medium", 0, 14));
        txt_user.setForeground(COLOR_PLACEHOLDER);
        txt_user.setText("Ingrese su nombre de usuario");
        txt_user.setBorder(null);
        txt_user.setMaximumSize(new Dimension(300, 30));
        txt_user.setMinimumSize(new Dimension(300, 30));
        txt_user.setName("");
        txt_user.setPreferredSize(new Dimension(300, 30));
        jPanel_fondo.add(txt_user);
        txt_user.setBounds(50, 240, 300, 30);

        jSeparator1.setBackground(new Color(0, 0, 0));
        jSeparator1.setForeground(new Color(0, 0, 0));
        jPanel_fondo.add(jSeparator1);
        jSeparator1.setBounds(50, 360, 300, 10);

        jLabel3.setFont(new Font("Roboto", 0, 18));
        jLabel3.setText("USUARIO");
        jPanel_fondo.add(jLabel3);
        jLabel3.setBounds(50, 220, 100, 20);

        txt_password.setFont(new Font("Roboto Medium", 0, 14));
        txt_password.setForeground(COLOR_PLACEHOLDER);
        txt_password.setText("***************");
        txt_password.setBorder(null);
        txt_password.setMaximumSize(new Dimension(300, 30));
        txt_password.setMinimumSize(new Dimension(300, 30));
        txt_password.setPreferredSize(new Dimension(300, 30));
        jPanel_fondo.add(txt_password);
        txt_password.setBounds(50, 330, 300, 30);

        jSeparator2.setBackground(new Color(0, 0, 0));
        jSeparator2.setForeground(new Color(0, 0, 0));
        jPanel_fondo.add(jSeparator2);
        jSeparator2.setBounds(50, 270, 300, 10);

        jPanel_ingresar.setBackground(new Color(255, 255, 255));
        jPanel_ingresar.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
        jPanel_ingresar.setMaximumSize(new Dimension(150, 50));
        jPanel_ingresar.setMinimumSize(new Dimension(150, 50));

        label_ingresar.setFont(new Font("Roboto", 1, 14));
        label_ingresar.setHorizontalAlignment(SwingConstants.CENTER);
        label_ingresar.setText("INGRESAR");
        label_ingresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        label_ingresar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                label_ingresarMouseClicked(evt);
            }
            public void mouseEntered(MouseEvent evt) {
                label_ingresarMouseEntered(evt);
            }
            public void mouseExited(MouseEvent evt) {
                label_ingresarMouseExited(evt);
            }
        });

        GroupLayout jPanel_ingresarLayout = new GroupLayout(jPanel_ingresar);
        jPanel_ingresar.setLayout(jPanel_ingresarLayout);
        jPanel_ingresarLayout.setHorizontalGroup(
            jPanel_ingresarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(label_ingresar, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
        );
        jPanel_ingresarLayout.setVerticalGroup(
            jPanel_ingresarLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(label_ingresar, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
        );

        jPanel_fondo.add(jPanel_ingresar);
        jPanel_ingresar.setBounds(50, 410, 150, 50);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_fondo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_fondo, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }

    private void jPanel_encabezadoMousePressed(MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void jPanel_encabezadoMouseDragged(MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x- xMouse, y - yMouse);
    }

    private void jPanel_cerrarMousePressed(MouseEvent evt) {
        
    }

    private void jLabel5MousePressed(MouseEvent evt) {
        
    }

    private void jLabel5MouseClicked(MouseEvent evt) {
        System.exit(0);
    }

    private void jLabel5MouseEntered(MouseEvent evt) {
        jPanel_cerrar.setBackground(Color.black);
        jLabel5.setForeground(Color.white);
    }

    private void jLabel5MouseExited(MouseEvent evt) {
        jPanel_cerrar.setBackground(Color.white);
        jLabel5.setForeground(Color.black);
    }

    private void label_ingresarMouseClicked(MouseEvent evt) {
        String usuarioIngresado = txt_user.getText();
        String passwordIngresado = new String(txt_password.getPassword());
        
        controlador.login(usuarioIngresado, passwordIngresado);
    }

    private void label_ingresarMouseEntered(MouseEvent evt) {
        jPanel_ingresar.setBackground(Color.black);
        label_ingresar.setForeground(Color.white);
    }

    private void label_ingresarMouseExited(MouseEvent evt) {
        jPanel_ingresar.setBackground(Color.white);
        label_ingresar.setForeground(Color.black);
    }

    public void mostrar(){
        this.setVisible(true);
    }
    public void cerrar(){
        this.dispose();
    }

    public void setControlador(ControladorLogin controlador) {
        this.controlador = controlador;
    }
}