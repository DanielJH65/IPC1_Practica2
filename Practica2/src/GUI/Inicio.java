package GUI;

//@author wdani
import Clases.CrearGraficoPrincipal;
import Clases.Espera;
import Clases.LeerArchivos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Inicio extends JFrame implements ActionListener {

    JButton btnBuscar;
    JButton btnCrear;
    JButton btnOrdenar;
    JTextField txtBuscar;
    JTextField txtNombre;
    JPanel contenedor;

    private String datos;
    private String titulos;
    private String[] entrada;

    public Inicio() {
        this.entrada = new String[3];
        this.entrada[0] = "";
        this.entrada[1] = "";
        this.entrada[2] = "";
        crearVentana();
        crearEtiqueta("Ruta del archivo", 10, 10, 120, 30, 17);
        crearEtiqueta("Nombre de la Gráfica", 10, 100, 170, 30, 17);
        txtBuscar = crearText(130, 10, 500, 30);
        txtNombre = crearText(180, 100, 450, 30);
        btnBuscar = crearBoton("Examinar...", 450, 50, 180, 40);
        btnCrear = crearBoton("Crear Gráfico", 450, 140, 180, 40);
        contenedor = crearPanel();
        imagen();
        //btnOrdenar = crearBoton("Ordenar", 10, 565, 200, 40);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == this.btnBuscar) {
            entrada = LeerArchivos.leerArchivo(this, txtBuscar.getText());
            txtBuscar.setText(entrada[2]);
            repaint();
            this.datos = entrada[0];
            this.titulos = entrada[1];
        }
        if (ae.getSource() == this.btnCrear) {

            if (entrada[0].equals("") && entrada[1].equals("") && entrada[2].equals("")) {
                entrada = LeerArchivos.leerArchivo(this, txtBuscar.getText());
                this.datos = entrada[0];
                this.titulos = entrada[1];
            }

            JPanel nuevo;
            try {
                nuevo = CrearGraficoPrincipal.graficoPrincipal(this.datos, this.titulos, txtNombre.getText());
                nuevo.setBounds(12, 190, 618, 415);
                nuevo.setVisible(true);
                contenedor.setVisible(false);
                nuevo.setBackground(Color.BLACK);
                add(nuevo);
                repaint();
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }

            Espera espera = new Espera(this.datos, this.titulos, txtNombre.getText(), this);
            Thread t1 = new Thread(espera);
            t1.start();
        }
        /*if(ae.getSource() == this.btnOrdenar){
            new GraficaOrdenada();
            this.dispose();
            
        }*/
    }

    private void crearVentana() {
        setTitle("Gráficas");
        setLayout(null);
        setSize(650, 650);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JButton crearBoton(String texto, int x, int y, int x1, int y1) {
        JButton nuevo = new JButton(texto);
        nuevo.setBounds(x, y, x1, y1);
        nuevo.addActionListener(this);
        nuevo.setBackground(Color.CYAN);
        nuevo.setForeground(Color.BLACK);
        add(nuevo);
        repaint();
        return nuevo;
    }

    private void crearEtiqueta(String nombre, int x, int y, int x1, int y1, int tamanio) {
        JLabel nuevo = new JLabel(nombre);
        nuevo.setBounds(x, y, x1, y1);
        nuevo.setFont(new Font("LucidaSans", Font.PLAIN, tamanio));
        nuevo.setForeground(Color.WHITE);
        add(nuevo);
        repaint();
    }

    private JTextField crearText(int x, int y, int x1, int y1) {
        JTextField nuevo = new JTextField();
        nuevo.setBounds(x, y, x1, y1);
        nuevo.setFont(new Font("LucidaSans", Font.PLAIN, 15));
        nuevo.setBackground(Color.LIGHT_GRAY);
        nuevo.setForeground(Color.BLACK);
        add(nuevo);
        repaint();
        return nuevo;
    }

    private JPanel crearPanel() {
        JPanel nuevo = new JPanel();
        nuevo.setBounds(12, 190, 618, 415);
        nuevo.setVisible(true);
        nuevo.setBackground(Color.BLACK);
        add(nuevo);
        repaint();
        return nuevo;
    }

    private void imagen() {
        JLabel imagenLabel = new JLabel();
        imagenLabel.setBounds(175, 105, 258, 205);
        ImageIcon foto = new ImageIcon(getClass().getResource("/Imagenes/Logo FIUSAC BN.png"));
        Icon icono = new ImageIcon(foto.getImage().getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_DEFAULT));
        imagenLabel.setIcon(icono);
        contenedor.add(imagenLabel);
        repaint();
    }
}
