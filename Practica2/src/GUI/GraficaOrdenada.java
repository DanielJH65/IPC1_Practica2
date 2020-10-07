package GUI;
//@author wdani

import Clases.BurbujaAscendente;
import Clases.BurbujaDescendente;
import Clases.QuickSortAscendente;
import Clases.QuickSortDescendente;
import Clases.ShellSortAscendente;
import Clases.ShellSortDescendente;
import Clases.Tiempo;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class GraficaOrdenada extends JFrame implements ActionListener {

    private JLabel lblTiempo;
    private JLabel lblPasos;
    private JLabel lblVelocidad;
    private JLabel lblOrden;
    private String algoritmo;
    private String velocidad;
    private String tipo;
    private int orden;
    private JFrame inicio;
    private String datos;
    private String titulos;
    private String nombre;
    private JPanel grafico;
    private JButton btnRegresar;

    public GraficaOrdenada(boolean tipoOrdenado, int velocidadOrdenado, int algoritmoOrdenado, String datos, String titulos, String nombre) {
        this.orden = 100;
        this.datos = datos;
        this.titulos = titulos;
        this.nombre = nombre;

        crearVentana();
        lblTiempo = crearEtiqueta("Tiempo: 0", 450, 10, 200, 40, 20);
        lblPasos = crearEtiqueta("Pasos: 0", 450, 60, 200, 40, 20);
        grafico = crearPanel(10, 160, 625, 430);
        btnRegresar = crearBoton("Regresar al Inicio", 100, 600, 400, 40);

        Tiempo tiempo = new Tiempo(this, lblTiempo);
        Thread t2 = new Thread(tiempo);
        t2.start();

        if (tipoOrdenado) {
            this.tipo = "Ascendente";
        } else {
            this.tipo = "Descendente";
        }

        if (velocidadOrdenado == 1300) {
            this.velocidad = "Baja";
        } else if (velocidadOrdenado == 900) {
            this.velocidad = "Media";
        } else {
            this.velocidad = "Alta";
        }

        if (algoritmoOrdenado == 0) {
            this.algoritmo = "Bubble Sort";
            if (tipoOrdenado) {
                lblVelocidad = crearEtiqueta("Velocidad: " + this.velocidad, 10, 60, 200, 40, 20);
                lblOrden = crearEtiqueta("Orden: " + this.tipo, 10, 110, 200, 40, 20);
                BurbujaAscendente burbujaA = new BurbujaAscendente(this, this.datos, this.titulos, this.nombre, velocidadOrdenado, lblPasos, t2, grafico, lblTiempo, lblVelocidad, lblOrden);
                Thread burbujaA1 = new Thread(burbujaA);
                burbujaA1.start();

            } else {
                lblVelocidad = crearEtiqueta("Velocidad: " + this.velocidad, 10, 60, 200, 40, 20);
                lblOrden = crearEtiqueta("Orden: " + this.tipo, 10, 110, 200, 40, 20);
                BurbujaDescendente burbujaD = new BurbujaDescendente(this, this.datos, this.titulos, this.nombre, velocidadOrdenado, lblPasos, t2, grafico, lblTiempo, lblVelocidad, lblOrden);
                Thread burbujaD1 = new Thread(burbujaD);
                burbujaD1.start();
            }

        } else if (algoritmoOrdenado == 1) {
            this.algoritmo = "Quick Sort";
            if (tipoOrdenado) {
                lblVelocidad = crearEtiqueta("Velocidad: " + this.velocidad, 10, 60, 200, 40, 20);
                lblOrden = crearEtiqueta("Orden: " + this.tipo, 10, 110, 200, 40, 20);
                QuickSortAscendente quickSortA = new QuickSortAscendente(this, this.datos, this.titulos, this.nombre, velocidadOrdenado, lblPasos, t2, grafico, lblTiempo, lblVelocidad, lblOrden);
                Thread quickSortA1 = new Thread(quickSortA);
                quickSortA1.start();
            } else {
                lblVelocidad = crearEtiqueta("Velocidad: " + this.velocidad, 10, 60, 200, 40, 20);
                lblOrden = crearEtiqueta("Orden: " + this.tipo, 10, 110, 200, 40, 20);
                QuickSortDescendente quickSortD = new QuickSortDescendente(this, this.datos, this.titulos, this.nombre, velocidadOrdenado, lblPasos, t2, grafico, lblTiempo, lblVelocidad, lblOrden);
                Thread quickSortD1 = new Thread(quickSortD);
                quickSortD1.start();
            }
        } else {
            this.algoritmo = "Shell Sort";
            if (tipoOrdenado) {
                lblVelocidad = crearEtiqueta("Velocidad: " + this.velocidad, 10, 60, 200, 40, 20);
                lblOrden = crearEtiqueta("Orden: " + this.tipo, 10, 110, 200, 40, 20);
                ShellSortAscendente shellSortA = new ShellSortAscendente(this, this.datos, this.titulos, this.nombre, velocidadOrdenado, lblPasos, t2, grafico, lblTiempo, lblVelocidad, lblOrden);
                Thread shellSortA1 = new Thread(shellSortA);
                shellSortA1.start();
            } else {
                lblVelocidad = crearEtiqueta("Velocidad: " + this.velocidad, 10, 60, 200, 40, 20);
                lblOrden = crearEtiqueta("Orden: " + this.tipo, 10, 110, 200, 40, 20);
                ShellSortDescendente shellSortD = new ShellSortDescendente(this, this.datos, this.titulos, this.nombre, velocidadOrdenado, lblPasos, t2, grafico, lblTiempo, lblVelocidad, lblOrden);
                Thread shellSortD1 = new Thread(shellSortD);
                shellSortD1.start();
            }
        }

        crearEtiqueta("Algoritmo: " + this.algoritmo, 10, 10, 200, 40, 20);

    }

    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    public void setBtnRegresar(JButton btnRegresar) {
        this.btnRegresar = btnRegresar;
    }

    public JPanel getGrafico() {
        return grafico;
    }

    public void setGrafico(JPanel grafico) {
        this.grafico = grafico;
    }

    private void crearVentana() {
        setTitle("Ordenamiento de Gráficas");
        setLayout(null);
        setSize(650, 680);
        setResizable(false);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.BLACK);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private JLabel crearEtiqueta(String nombre, int x, int y, int x1, int y1, int tamanio) {
        JLabel nuevo = new JLabel(nombre);
        nuevo.setBounds(x, y, x1, y1);
        nuevo.setFont(new Font("LucidaSans", Font.PLAIN, tamanio));
        nuevo.setForeground(Color.WHITE);
        add(nuevo);
        repaint();

        return nuevo;
    }

    private JPanel crearPanel(int x, int y, int x1, int y1) {
        JPanel nuevo = new JPanel();
        nuevo.setBounds(x, y, x1, y1);
        nuevo.setLayout(null);
        nuevo.setVisible(true);
        nuevo.setBackground(Color.BLACK);
        add(nuevo);
        repaint();
        return nuevo;
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRegresar) {
            Inicio inicio = new Inicio();
            this.dispose();
        }
    }

    public JLabel getLblPasos() {
        return lblPasos;
    }

    public void setLblPasos(JLabel lblPasos) {
        this.lblPasos = lblPasos;
    }

    public JLabel getLblTiempo() {
        return lblTiempo;
    }

    public void setLblTiempo(JLabel lblTiempo) {
        this.lblTiempo = lblTiempo;
    }

    /*public void crearGrafica() {
        JPanel nuevo = CrearGraficoPrincipal.graficoPrincipal(this.datos, this.titulos, "a");
        nuevo.setBounds(12, 190, 618, 415);
        nuevo.setVisible(true);
        nuevo.setBackground(Color.BLACK);
        nuevo.setComponentZOrder(nuevo, orden);
        orden--;
        add(nuevo);
        repaint();
    }*/
}
