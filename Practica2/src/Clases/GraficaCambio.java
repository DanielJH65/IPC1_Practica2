package Clases;

import GUI.GraficaOrdenada;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author wdani
 */
public class GraficaCambio {

    public static JPanel Grafico(GraficaOrdenada pantalla, String[][] datos, String[] titulos, String titulo) {
        JPanel grafico = new JPanel();

        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
        String[] datosFinal = new String[2];
        for (int i = 0; i < datos.length; i++) {
            datosFinal[0] = datos[i][0];
            datosFinal[1] = datos[i][1];
            dataSet.addValue(Integer.parseInt(datosFinal[1]), datosFinal[0], datosFinal[0]);
        }

        JFreeChart grafica = ChartFactory.createBarChart(titulo, titulos[0], titulos[1], dataSet, PlotOrientation.VERTICAL, true, true, false);

        BarRenderer renderer = (BarRenderer) grafica.getCategoryPlot().getRenderer();
        renderer.setItemMargin(-5.5);

        BufferedImage imagen = grafica.createBufferedImage(618, 415);

        JLabel imagenLabel = new JLabel();
        imagenLabel.setBounds(0, 0, 618, 415);
        ImageIcon foto = new ImageIcon(imagen);
        Icon icono = new ImageIcon(foto.getImage().getScaledInstance(imagenLabel.getWidth(), imagenLabel.getHeight(), Image.SCALE_DEFAULT));
        imagenLabel.setIcon(icono);

        grafico.add(imagenLabel);

        return grafico;
    }

}
