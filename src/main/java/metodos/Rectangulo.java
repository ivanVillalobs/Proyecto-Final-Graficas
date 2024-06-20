package metodos;

import java.awt.Color;
import panel.Panel;

/**
 *
 * @author EL iván
 */
public class Rectangulo {

    private Panel lineas;

    public Rectangulo(Panel lineas) {
        this.lineas = lineas;
    }

    public void PanitRectangle(int x1, int y1, int x2, int y2, Color colorq) {
        // Dibujar el rectángulo exterior
        for (int i = x1; i <= x2; i++) {
            lineas.putPixel(i, y1, Color.BLACK); // Línea superior
            lineas.putPixel(i, y2, Color.BLACK); // Línea inferior
        }
        for (int j = y1; j <= y2; j++) {
            lineas.putPixel(x1, j, Color.BLACK); // Línea izquierda
            lineas.putPixel(x2, j, Color.BLACK); // Línea derecha
        }
    }
}
