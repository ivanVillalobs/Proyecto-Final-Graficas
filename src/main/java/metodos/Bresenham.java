package metodos;

import java.awt.Color;
import panel.Panel;

/**
 *
 * @author ivano
 */
public class Bresenham {

    private Panel lineas;

    public Bresenham(Panel lineas) {
        this.lineas = lineas;
    }

    public void PintarLineaBresenham(int x1, int y1, int x2, int y2, Color color) {
        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);
        int sx = x1 < x2 ? 1 : -1;
        int sy = y1 < y2 ? 1 : -1;
        int err = dx - dy;

        while (x1 != x2 || y1 != y2) {
            lineas.putPixel(x1, y1, color);
            int e2 = 2 * err;
            if (e2 > -dy)  {
                err -= dy;
                x1 += sx;
            }
            if (e2 < dx) {
                err += dx;
                y1 += sy;
            }
        }
    }
}
