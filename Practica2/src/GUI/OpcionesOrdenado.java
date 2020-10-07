package GUI;

//@author wdani/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;

public class OpcionesOrdenado extends JFrame implements ActionListener{
    
    JFrame incio;
    JPanel tipo;
    JPanel velocidad;
    JPanel algoritmo;
    JRadioButton ascendente;
    JRadioButton descendente;
    JRadioButton baja;
    JRadioButton media;
    JRadioButton alta;
    JRadioButton bubble;
    JRadioButton quick;
    JRadioButton sheell;
    ButtonGroup grupoTipo;
    ButtonGroup grupoVelocidad;
    ButtonGroup grupoAlgoritmo;
    JButton btnOrdenar;
    JButton bttnCancelar;
    
    private boolean tipoOrdenado;
    private int velocidadOrdenado;
    private int algoritmoOrdenado;
    private int error;
    private String datos;
    private String titulos;
    private String nombre;
    private Inicio inicio;

    public OpcionesOrdenado(Inicio inicio, String datos, String titulos, String nombre) {
        this.error = 0;
        this.datos = datos;
        this.titulos = titulos;
        this.nombre = nombre;
        this.inicio = inicio;
        crearVentana();
        this.tipo = crearPanel(10, 10, 425, 100);
        this.velocidad = crearPanel(10, 120, 207, 185);
        this.algoritmo = crearPanel(227, 120, 208, 185);
        this.tipo.add(crearEtiqueta("Tipo de Ordenamiento:", 5, 5, 150, 20, 15));
        ascendente = crearRadio("Ascendente",10,50,130,30);
        descendente = crearRadio("Descendente", 150, 50, 160, 30);
        this.velocidad.add(crearEtiqueta("Velocidad de Ordenamiento:", 5, 5, 205, 20, 15));
        baja = crearRadio("Baja", 10, 50, 130, 30);
        media = crearRadio("Media", 10, 90, 130, 30);
        alta = crearRadio("Alta", 10, 130, 130, 30);
        this.algoritmo.add(crearEtiqueta("Algoritmo de Ordenamiento:", 5, 5, 205, 20, 15));
        bubble = crearRadio("Bubble Sort", 10, 50, 130, 30);
        quick = crearRadio("QuickSort", 10, 90, 130, 30);
        sheell = crearRadio("ShellSort", 10, 130, 130, 30);
        grupoTipo = new ButtonGroup();
        grupoTipo.add(ascendente);
        grupoTipo.add(descendente);
        grupoVelocidad = new ButtonGroup();
        grupoVelocidad.add(baja);
        grupoVelocidad.add(media);
        grupoVelocidad.add(alta);
        grupoAlgoritmo = new ButtonGroup();
        grupoAlgoritmo.add(bubble);
        grupoAlgoritmo.add(quick);
        grupoAlgoritmo.add(sheell);
        this.tipo.add(ascendente);
        this.tipo.add(descendente);
        this.velocidad.add(baja);
        this.velocidad.add(media);
        this.velocidad.add(alta);
        this.algoritmo.add(bubble);
        this.algoritmo.add(quick);
        this.algoritmo.add(sheell);
        btnOrdenar = crearBoton("Ordenar", 30, 315, 150, 40);
        bttnCancelar = crearBoton("Cancelar", 260, 315, 150, 40);
        
    }
    
    private void crearVentana() {
        setTitle("Opciones de Ordenado");
        setLayout(null);
        setSize(450, 400);
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
        nuevo.setBorder(new LineBorder(Color.white));
        nuevo.setVisible(true);
        nuevo.setBackground(Color.BLACK);
        add(nuevo);
        repaint();
        return nuevo;
    }
    
    private JRadioButton crearRadio(String nombre,int x, int y, int x1, int y1){
        JRadioButton nuevo = new JRadioButton(nombre);
        nuevo.setBounds(x, y, x1, y1);
        nuevo.setBackground(Color.black);
        nuevo.setForeground(Color.white);
        repaint();
        return nuevo;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==this.btnOrdenar){
            if(ascendente.isSelected()){
                this.tipoOrdenado = true;
            }else if(descendente.isSelected()){
                this.tipoOrdenado = false;
            }else{
                this.error++;
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de ordenamiento","Error",JOptionPane.ERROR_MESSAGE);
            }
            if(baja.isSelected()){
                this.velocidadOrdenado = 1300;
            }else if(media.isSelected()){
                this.velocidadOrdenado = 900;
            }else if(alta.isSelected()){
                this.velocidadOrdenado = 500;
            }else{
                this.error++;
                JOptionPane.showMessageDialog(this, "Debe seleccionar una velocidad de ordenamiento","Error",JOptionPane.ERROR_MESSAGE);
            }
            if(bubble.isSelected()){
                this.algoritmoOrdenado = 0;
            }else if(quick.isSelected()){
                this.algoritmoOrdenado = 1;
            }else if(sheell.isSelected()){
                this.algoritmoOrdenado = 2;
            }else{
                this.error++;
                JOptionPane.showMessageDialog(this, "Debe seleccionar un algoritmo de ordenamiento","Error",JOptionPane.ERROR_MESSAGE);
            }
            
            if(this.error==0){
                new GraficaOrdenada(this.tipoOrdenado,this.velocidadOrdenado,this.algoritmoOrdenado, this.datos, this.titulos, this.nombre);
                inicio.dispose();
                this.dispose();
            }
            
        }
        if(ae.getSource()==this.bttnCancelar){
            this.dispose();
        }
    }
    
}
