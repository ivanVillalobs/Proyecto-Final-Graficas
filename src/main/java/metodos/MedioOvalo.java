package metodos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Arc2D;

public class MedioOvalo {
    public void dibujarMedioOvalo(Graphics g, int x, int y, int width, int height, Color color, double rotationAngle) {
        Graphics2D g2d = (Graphics2D) g;
        
        // Aplicar la rotación al contexto gráfico
        AffineTransform oldTransform = g2d.getTransform();
        g2d.rotate(rotationAngle, x, y);
        
        // Crear un arco que represente la mitad de un óvalo
        Arc2D arc = new Arc2D.Double(x - width / 2, y - height / 2, width, height, 90, 180, Arc2D.PIE);
        
        // Rellenar el arco
        g2d.setColor(color);
        g2d.fill(arc);
        
        // Restaurar la transformación original
        g2d.setTransform(oldTransform);
    }
}
