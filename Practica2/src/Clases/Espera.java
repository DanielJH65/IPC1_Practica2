package Clases;
//@author wdani

import GUI.GraficaOrdenada;
import GUI.Inicio;
import GUI.OpcionesOrdenado;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Espera implements Runnable{
    
    JFrame inicio;
    String datos;
    String titulos;
    String nombre;
    Inicio inicio1;
    
    public Espera(String datos, String titulos, String nombre, Inicio inicio) {
        this.datos = datos;
        this.titulos = titulos;
        this.nombre = nombre;
        this.inicio1 = inicio;
    }
    
    @Override
    public void run() {
        try {
            sleep(2000);
            Object opciones[]= {"Si","No"};
            int prueba = JOptionPane.showOptionDialog(inicio, "Desea Ordenar el Gráfico","Ordenar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
            if(prueba==0){
                new OpcionesOrdenado(inicio1,datos, titulos, nombre);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Espera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
