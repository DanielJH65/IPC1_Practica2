package Clases;
//@author wdani

import com.sun.corba.se.spi.monitoring.StatisticsAccumulator;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class LeerArchivos {

    public static String[] leerArchivo(JFrame ventana, String rutaMano) {
        String[] resultado = new String[3];
        String texto = "";
        String titulos = "";
        String ruta = "";
        File archivo;
        try {

            if (rutaMano.equals("")) {
                JFileChooser selecionArchivos = new JFileChooser();
                selecionArchivos.showOpenDialog(ventana);
                archivo = selecionArchivos.getSelectedFile();
                ruta = archivo.getPath();
            } else {
                archivo = new File(rutaMano);
                ruta = archivo.getPath();
            }
            if (archivo != null) {
                FileReader lector = new FileReader(archivo);
                BufferedReader leer = new BufferedReader(lector);
                String linea = "";
                titulos = linea = leer.readLine();
                while ((linea = leer.readLine()) != null) {
                    texto += linea + "\n";
                }
                leer.close();
            }
            JOptionPane.showMessageDialog(ventana, "Datos cargados correctamente", "Satisfactorio", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ventana, ex + " No se ha encontrado el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }

        resultado[0] = texto;
        resultado[1] = titulos;
        resultado[2] = ruta;
        return resultado;
    }
}
