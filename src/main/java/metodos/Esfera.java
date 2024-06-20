package metodos;

import java.awt.Color;
import java.awt.Graphics;

public class Esfera {
    private int radio;
    private Color color;

    /**
     * Constructor para inicializar la esfera con un radio y color dado.
     * @param radio El radio de la esfera.
     * @param color El color de la esfera.
     */
    public Esfera(int radio, Color color) {
        this.radio = radio;
        this.color = color;
    }

    /**
     * Método para dibujar la esfera en 3D en un componente gráfico.
     * @param g El contexto gráfico donde se dibuja la esfera.
     * @param x La coordenada X del centro de la esfera.
     * @param y La coordenada Y del centro de la esfera.
     */
    public void dibujarEsfera3D(Graphics g, int x, int y) {
        for (int i = 0; i <= radio; i += 5) {
            int alpha = (int) (255 * (1 - (double) i / radio));
            g.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha));
            g.fillOval(x - radio + i, y - radio + i, 2 * (radio - i), 2 * (radio - i));
        }
    }

    // Getters y setters
    public int getRadio() {
        return radio;
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
