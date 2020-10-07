package Clases;
//@author wdani

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class CrearGraficoPrincipal {
    
    public static JPanel graficoPrincipal(String datos, String titulos, String titulo) throws FileNotFoundException{
        
        
        JPanel grafico = new JPanel();
        
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        
        String[] datosSeparados = datos.split("\n");
        String[] titulosFinal = titulos.split(",");
        for(String recorrido : datosSeparados){
            String[] datosFinal = recorrido.split(",");
            dataSet.addValue(Integer.parseInt(datosFinal[1]),datosFinal[0], datosFinal[0]);
        }
        
        JFreeChart grafica = ChartFactory.createBarChart(titulo, titulosFinal[0], titulosFinal[1], dataSet, PlotOrientation.VERTICAL, true, true, false);
        
        BarRenderer renderer = (BarRenderer) grafica.getCategoryPlot().getRenderer(); renderer.setItemMargin(-5.5);
        
        BufferedImage imagen = grafica.createBufferedImage(618, 415);
        
        try {
            OutputStream crearImagen = new FileOutputStream("Imagenes/GraficaPrincipal.png") {};
            ChartUtilities.writeChartAsPNG(crearImagen, grafica, 1000, 600);
        } catch (IOException ex) {
            Logger.getLogger(CrearGraficoPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JLabel imagenLabel = new JLabel();
        imagenLabel.setBounds(0, 0, 618, 415);
        ImageIcon foto = new ImageIcon(imagen);
        Icon icono = new ImageIcon(foto.getImage().getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_DEFAULT));
        imagenLabel.setIcon(icono);
        
        grafico.add(imagenLabel);
        
        return grafico;
    }
    
}
