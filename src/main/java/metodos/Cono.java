package metodos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

public class Cono {

    public void dibujarCono(Graphics g, int x, int y, int radius, int height, Color color, double rotationAngle) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Rotar el cono alrededor de su base
        AffineTransform transform = new AffineTransform();
        transform.rotate(rotationAngle, x, y + height); // Rotar respecto al centro de la base del cono
        g2d.setTransform(transform);

        // Dibujar el cono
        g2d.setColor(color);
        int[] xPoints = {x - radius, x + radius, x};
        int[] yPoints = {y, y, y + height};
        g2d.fillPolygon(xPoints, yPoints, 3);

        g2d.dispose();
    }
}

