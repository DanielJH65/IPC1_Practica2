package Clases;

// @author wdani
import GUI.GraficaOrdenada;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

public class Tiempo implements Runnable {

    GraficaOrdenada pantalla;
    JLabel lblTiempo = new JLabel("");
    private int tiempo = 0;
    private int milesimas = 0;
    private int segundos = 0;
    private int minutos = 0;

    public Tiempo(GraficaOrdenada pantalla, JLabel t) {
        this.pantalla = pantalla;
        this.lblTiempo = t;
    }

    @Override
    public void run() {
        do {
            try {
                tiempo++;
                milesimas = tiempo;
                if(milesimas==1000){
                    segundos++;
                    tiempo=0;
                    if(segundos==60){
                        segundos=0;
                        minutos++;
                        tiempo=0;
                    }
                }
                sleep(1);
            } catch (InterruptedException ex) {
                Logger.getLogger(Tiempo.class.getName()).log(Level.SEVERE, null, ex);
            }
            lblTiempo.setText("Tiempo: " + minutos + ":" + segundos + ":" + milesimas);
            pantalla.setLblTiempo(lblTiempo);
            pantalla.repaint();
        } while (true);
    }

}
