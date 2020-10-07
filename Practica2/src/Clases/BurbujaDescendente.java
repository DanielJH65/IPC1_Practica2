/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import GUI.GraficaOrdenada;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author wdani
 */
public class BurbujaDescendente implements Runnable {

    GraficaOrdenada pantalla;
    JLabel lblPasos = new JLabel("");
    JLabel lblTiempo = new JLabel("");
    JLabel lblVelocidad = new JLabel("");
    JLabel lblOrden = new JLabel("");
    String datos;
    String titulos;
    String nombre;
    String[][] datosFinal;
    JPanel grafico = new JPanel();
    int velocidad;
    int pasos;
    Thread t2;

    public BurbujaDescendente(GraficaOrdenada pantalla, String datos, String titulos, String nombre, int velocidad, JLabel pasos, Thread t2, JPanel grafico, JLabel tiempo, JLabel vel, JLabel orden) {
        this.datos = datos;
        this.titulos = titulos;
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.pasos = 0;
        this.lblPasos = pasos;
        this.lblTiempo = tiempo;
        this.lblOrden = orden;
        this.lblVelocidad = vel;
        this.t2 = t2;
        this.grafico = grafico;
    }

    @Override
    public void run() {
        String[] datosSeparados = datos.split("\n");
        String[] titulosFinal = titulos.split(",");
        String[][] datosInicial = new String[datosSeparados.length][2];
        datosFinal = new String[datosSeparados.length][2];
        int conteo = 0;
        for (String recorrido : datosSeparados) {
            datosFinal[conteo] = recorrido.split(",");
            datosInicial[conteo] = recorrido.split(",");
            conteo++;
        }

        for (int i = 0; i < datosFinal.length - 1; i++) {
            for (int j = 0; j < datosFinal.length - 1; j++) {
                if (Integer.parseInt(datosFinal[j][1]) < Integer.parseInt(datosFinal[j + 1][1])) {

                    int temp = Integer.parseInt(datosFinal[j + 1][1]);
                    String temp2 = datosFinal[j + 1][0];
                    datosFinal[j + 1][0] = datosFinal[j][0];
                    datosFinal[j + 1][1] = datosFinal[j][1];
                    datosFinal[j][1] = String.valueOf(temp);
                    datosFinal[j][0] = temp2;
                    this.pasos++;
                    conteo--;

                    lblPasos.setText("Pasos: " + this.pasos);
                    lblPasos.validate();

                    grafico.setBounds(10, 160, 625, 430);
                    grafico.setVisible(true);
                    DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
                    String[] datosFinal2 = new String[2];

                    for (int k = 0; k < datosFinal.length; k++) {
                        datosFinal2[0] = datosFinal[k][0];
                        datosFinal2[1] = datosFinal[k][1];
                        dataSet.addValue(Integer.parseInt(datosFinal2[1]), datosFinal2[0], datosFinal2[0]);
                    }

                    try {
                        JFreeChart grafica = ChartFactory.createBarChart(this.nombre, titulosFinal[0], titulosFinal[1], dataSet, PlotOrientation.VERTICAL, true, true, false);

                        BarRenderer renderer = (BarRenderer) grafica.getCategoryPlot().getRenderer();
                        renderer.setItemMargin(-5.5);

                        try {
                            OutputStream crearImagen = new FileOutputStream("Imagenes/GraficaBurbujaDescendente.png") {
                            };
                            ChartUtilities.writeChartAsPNG(crearImagen, grafica, 1000, 600);
                        } catch (IOException ex) {
                            Logger.getLogger(CrearGraficoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        BufferedImage imagen = grafica.createBufferedImage(618, 430);

                        JLabel imagenLabel = new JLabel();
                        imagenLabel.setBounds(0, 0, 618, 430);
                        ImageIcon foto = new ImageIcon(imagen);
                        Icon icono = new ImageIcon(foto.getImage().getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_DEFAULT));
                        imagenLabel.setIcon(icono);
                        grafico.removeAll();
                        grafico.add(imagenLabel);
                        grafico.validate();
                        sleep(this.velocidad);

                    } catch (InterruptedException ex) {
                        Logger.getLogger(BurbujaAscendente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Pasos: " + this.pasos);
                }

            }
        }
        t2.stop();
        JOptionPane.showMessageDialog(pantalla, "Ordenamiento terminado", "Completado", JOptionPane.INFORMATION_MESSAGE);
        generarReporte("Reporte Ordenamiento Burbuja Descendente", "ReporteBurbujaDescendente", datosFinal, datosInicial, titulosFinal);
    }
    
    private void generarReporte(String titulo, String ruta, String ultimos[][], String inicio[][], String titulos[]) {

        String tiempo = this.lblTiempo.getText();
        String tiempoFinal = tiempo.substring(8, tiempo.length());

        String orden = this.lblOrden.getText();
        String ordenFinal = orden.substring(7, orden.length());

        String vel = this.lblVelocidad.getText();
        String velFinal = vel.substring(11, vel.length());

        String datosInicio = "";
        String titulosInicio = "";

        for (int i = 0; i < inicio.length; i++) {
            titulosInicio += "<td>" + inicio[i][0] + "</td>\n";
            datosInicio += "<td>" + inicio[i][1] + "</td>\n";
        }

        String datosFinal = "";
        String titulosFinal = "";

        for (int i = 0; i < ultimos.length; i++) {
            titulosFinal += "<td>" + ultimos[i][0] + "</td>\n";
            datosFinal += "<td>" + ultimos[i][1] + "</td>\n";
        }

        String html = "<html>\n"
                + "<head>\n"
                + "<link href=\"estilo.css\" rel=\"Stylesheet\" type=\"text/css\">\n"
                + "<title>" + titulo + "</title>\n"
                + "</head>\n"
                + "<body>\n" + "<center><h2 class=\"nombre\">Walter Daniel Jiménez Hernández</h2>\n"
                + "<h3 class=\"carne\">201901108</h3></center>\n"
                + "<div class=\"barra\"></div>\n"
                + "<table class=\"datos\">\n"
                + "<tr>\n <td><b>Algoritmo: </b>Bubble Sort</td> \n <td><b>Tiempo: </b>" + tiempoFinal + "</td></tr>\n"
                + "<tr>\n <td><b>Velocidad: </b>" + velFinal + "</td> \n <td><b>Pasos: </b>" + pasos + "</td></tr>\n"
                + "<tr>\n <td><b>Orden: </b>" + ordenFinal + "</td>"
                + "</table>\n"
                + "<div class=\"barra\"></div>\n"
                + "<table class=\"maxmin\">\n"
                + "<tr>\n<td><table class=\"minimo\">\n"
                + "<tr>\n <td colspan=\"2\" class=\"titulo\"><b>Dato Minimo</b></td> \n"
                + "<tr>\n <td>" + ultimos[ultimos.length - 1][0] + "</td> \n <td>" + ultimos[ultimos.length - 1][1] + "</td></tr>\n"
                + "</table>\n</td>"
                + "\n<td><table class=\"maximo\">\n"
                + "<tr>\n <td colspan=\"2\" class=\"titulo\"><b>Dato Maximo</b></td> \n"
                + "<tr>\n <td>" + ultimos[0][0] + "</td> \n <td>" + ultimos[0][1] + "</td></tr>\n"
                + "</table>\n</td></tr>"
                + "</table>\n"
                + "<div class=\"barra\"></div>\n"
                + "<br><center><h2 class=\"nombre\">Datos Desordenados</h2>\n<br>\n"
                + "<table class=\"dinicio\">\n"
                + "<tr><td class=\"titulo\">" + titulos[0] + "</td>\n"
                + titulosInicio
                + "</tr><tr><td class=\"titulo\">" + titulos[1] + "</td>\n"
                + datosInicio
                + "</tr></table>\n"
                + "<center><img src=\"../Imagenes/GraficaPrincipal.png\" width=\"80%\" height=\"600px\"></center>"
                + "<br><div class=\"barra\"></div>\n"
                + "<br><center><h2 class=\"nombre\">Datos Ordenados</h2>\n<br>\n"
                + "<table class=\"dinicio\">\n"
                + "<tr><td class=\"titulo\">" + titulos[0] + "</td>\n"
                + titulosFinal
                + "</tr><tr><td class=\"titulo\">" + titulos[1] + "</td>\n"
                + datosFinal
                + "</tr></table>\n"
                + "<center><img src=\"../Imagenes/GraficaBurbujaDescendente.png\" width=\"80%\" height=\"600px\"></center>"
                + "\n</body>\n"
                + "</html>";

        try {
            BufferedWriter escribirreporte = new BufferedWriter(new FileWriter("Reportes/" + ruta + ".html", false));
            escribirreporte.write(html);
            escribirreporte.close();
            JOptionPane.showMessageDialog(pantalla, "Se generó el reporte \"" + ruta + ".html\" en la carpeta Reportes", "Reporte", JOptionPane.INFORMATION_MESSAGE);
            //reporte="";
        } catch (IOException e) {
            JOptionPane.showMessageDialog(pantalla, "No se generó el reporte", "Reporte", JOptionPane.ERROR);
        }
    }
}
