package metodos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class Ovalo {

    /**
     * Método para dibujar un ovoide en 3D en un componente gráfico.
     *
     * @param g El contexto gráfico donde se dibuja el ovoide.
     * @param x La coordenada X del centro del ovoide.
     * @param y La coordenada Y del centro del ovoide.
     * @param width El ancho del ovoide.
     * @param height La altura del ovoide.
     * @param color El color del ovoide.
     */
    public void dibujarOvoide3D(Graphics g, int x, int y, int width, int height, Color color, double rotationAngleX) {
        Graphics2D g2d = (Graphics2D) g;

        // Aplicar rotación sobre el eje X
        AffineTransform oldTransform = g2d.getTransform();
        g2d.rotate(rotationAngleX, x, y);

        // Dibujar una elipse
        g2d.setColor(color);
        g2d.fillOval(x - width / 2, y - height / 2, width, height);

        // Crear un gradiente para dar la apariencia de 3D
        float[] dist = {0.0f, 0.8f, 1.0f};
        Color[] colors = {color.brighter(), color, color.darker()};
        RadialGradientPaint p = new RadialGradientPaint(x, y, width / 2, dist, colors);
        g2d.setPaint(p);
        g2d.fill(new Ellipse2D.Double(x - width / 2, y - height / 2, width, height));

        // Restaurar la transformación original
        g2d.setTransform(oldTransform);
    }

}
