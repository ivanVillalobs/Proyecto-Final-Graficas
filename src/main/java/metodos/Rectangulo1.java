package metodos;

import java.awt.Color;
import panel.Panel;
import java.awt.image.BufferedImage;

/**
 *
 * @author EL iván
 */
public class Rectangulo1 {

    private Panel panel;
    private BufferedImage buffer;

    public Rectangulo1(Panel panel, BufferedImage buffer) {
        this.panel = panel;
        this.buffer = buffer;
    }

    public void PanitRectangle(int x1, int y1, int x2, int y2, Color colorq, Color fillColor) {
        // Dibujar las cuatro líneas que forman el cuadrado
        Bresenham BH = new Bresenham(panel);
        BH.PintarLineaBresenham(x1, y1, x2, y1, colorq); // Dibujar línea superior
        BH.PintarLineaBresenham(x1, y1, x1, y2, colorq); // Dibujar línea izquierda
        BH.PintarLineaBresenham(x2, y1, x2, y2, colorq); // Dibujar línea derecha
        BH.PintarLineaBresenham(x1, y2, x2, y2, colorq); // Dibujar línea inferior

        // Aplicar el algoritmo de llenado de área
        floodFillIterative flood = new floodFillIterative(buffer);
        flood.floodFillIteratives(x1 + 1, y1 + 1, colorq.getRGB(), fillColor.getRGB());
    }
}
