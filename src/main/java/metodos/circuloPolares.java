package metodos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import panel.Panel;

/**
 *
 * @author ivano
 */
public class circuloPolares {

    private BufferedImage buffer;
    private Panel panel;

    public circuloPolares(BufferedImage buffer) {
        this.buffer = buffer;
    }

    public void circuloPolaress(int x0, int y0, int rad, Color colorq, Color fillColor) {
        double theta = 0;
        double dTheta = 1.0 / rad; // Incremento de ángulo en radianes

        while (theta < Math.PI / 4) { // Recorremos solo un octante del círculo
            int x = (int) Math.round(rad * Math.cos(theta));
            int y = (int) Math.round(rad * Math.sin(theta));
            // Tramo en los octantes 1,8,2,7,3,4,5,6
            panel = new Panel();
            panel.putPixel(x0 + x, y0 + y, colorq);
            panel.putPixel(x0 - x, y0 + y, colorq);
            panel.putPixel(x0 + x, y0 - y, colorq);
            panel.putPixel(x0 - x, y0 - y, colorq);
            panel.putPixel(x0 + y, y0 + x, colorq);
            panel.putPixel(x0 - y, y0 + x, colorq);
            panel.putPixel(x0 + y, y0 - x, colorq);
            panel.putPixel(x0 - y, y0 - x, colorq);
            theta += dTheta;
        }
    
    }
}
