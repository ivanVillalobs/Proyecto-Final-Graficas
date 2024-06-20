package metodos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ovoide {
     public void dibujarOvoide(Graphics g, int x, int y, int width, int height, Color color) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(color);

        // Dibujar un óvalo escalado en el eje Y para simular un ovoide
        g2d.fillOval(x - width / 2, y - height / 4, width, height / 2);
    }
}
