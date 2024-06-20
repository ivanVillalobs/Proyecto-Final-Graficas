package metodos;

import java.awt.Color;
import java.awt.Graphics;

public class Esfera {

    public Esfera() {
    }

    public void dibujarEsfera3D(Graphics g, int x, int y, int radio, Color color) {
        for (int i = 0; i <= radio; i += 5) {
            int alpha = (int) (255 * (1 - (double) i / radio));
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
            g.fillOval(x - radio + i, y - radio + i, 2 * (radio - i), 2 * (radio - i));
        }
    }
}
