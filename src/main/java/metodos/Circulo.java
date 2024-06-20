package metodos;

import java.awt.Color;
import java.awt.Graphics;

public class Circulo {
    public void dibujarCirculo(Graphics g, int x, int y, int radius, Color color) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}

