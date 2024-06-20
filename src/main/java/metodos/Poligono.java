package metodos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import panel.Panel;

/**
 *
 * @author ivano
 */
public class Poligono {

    private Panel panel;
    private BufferedImage buffer;

    public Poligono(Panel panel, BufferedImage buffer) {
        this.panel = panel;
        this.buffer = buffer;
    }

    public void drawPoligono(int[][] puntos, Color color, Color relleno) {
        int n = puntos[0].length;
        for (int i = 0; i < n; i++) {
            int x1 = puntos[0][i];
            int y1 = puntos[1][i];
            int x2 = puntos[0][(i + 1) % n];
            int y2 = puntos[1][(i + 1) % n];
            Bresenham BH = new Bresenham(panel);
            BH.PintarLineaBresenham(x1, y1, x2, y2, color);
        }
        int xMin = Integer.MAX_VALUE, xMax = Integer.MIN_VALUE;
        int yMin = Integer.MAX_VALUE, yMax = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            xMin = Math.min(xMin, puntos[0][i]);
            xMax = Math.max(xMax, puntos[0][i]);
            yMin = Math.min(yMin, puntos[1][i]);
            yMax = Math.max(yMax, puntos[1][i]);
        }
        int rellenoRGB = relleno.getRGB();
        int lineaRGB = color.getRGB();
        for (int y = yMin + 1; y < yMax; y++) {
            for (int x = xMin + 1; x < xMax; x++) {
                if (AfueraTuNoexistesSoloAdentro(puntos, x, y)) {
                    // Aplicar el algoritmo de llenado de área
                    floodFillIterative flood = new floodFillIterative(buffer);
                    flood.floodFillIteratives(x, y, lineaRGB, rellenoRGB);
                }
            }
        }
    }

    private boolean AfueraTuNoexistesSoloAdentro(int[][] puntos, int x, int y) {
        int n = puntos[0].length;
        boolean dentro = false;
        for (int i = 0, j = n - 1; i < n; j = i++) {
            if ((puntos[1][i] > y) != (puntos[1][j] > y)
                    && (x < (puntos[0][j] - puntos[0][i]) * (y - puntos[1][i]) / (puntos[1][j] - puntos[1][i])
                    + puntos[0][i])) {
                dentro = !dentro;
            }
        }
        return dentro;
    }

}
