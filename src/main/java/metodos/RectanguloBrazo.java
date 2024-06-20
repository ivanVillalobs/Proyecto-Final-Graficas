package metodos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class RectanguloBrazo {

    public void dibujarRectanguloRotado(Graphics g, int x, int y, int width, int height, double angle, Color color) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Rotar el rectángulo
        g2d.rotate(Math.toRadians(angle), x, y);

        // Dibujar el rectángulo
        g2d.setColor(color);
        g2d.fillRect(x - width / 2, y - height / 2, width, height);

        g2d.dispose();
    }
    public void dibujarRectangulo(Graphics g, int x, int y, int width, int height, double angle, Color color) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Rotar el rectángulo
        g2d.rotate(Math.toRadians(angle), x, y);

        // Dibujar el rectángulo
        g2d.setColor(color);
        g2d.drawRect(x - width / 2, y - height / 2, width, height);

        g2d.dispose();
    }
}
