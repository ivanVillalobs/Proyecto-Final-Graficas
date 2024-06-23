package metodos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Esfera3D {

    public void dibujarEsfera3D(Graphics g, int x, int y, int radio, Color color) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(color);

        // Dibujar el primer círculo
        g2d.drawOval(x, y, radio, radio);

        // Calcular las coordenadas para los otros dos círculos centrados dentro del primer círculo
        int x2 = x + radio / 4; // Coordenada x para el segundo círculo
        int x3 = x + radio / 4; // Coordenada x para el tercer círculo

        // Dibujar los otros dos círculos centrados dentro del primer círculo
        g2d.drawOval(x2, y, radio / 2, radio); // Segundo círculo
        g2d.setColor(Color.BLACK);
        g2d.drawOval(x3, y, radio / 3, radio); // Tercer círculo

        g2d.dispose();
    }
}
