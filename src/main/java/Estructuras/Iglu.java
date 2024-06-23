package Estructuras;

import javax.swing.*;
import java.awt.*;

public class Iglu extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Establecer las configuraciones de renderizado para mejor calidad
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(Color.CYAN);
        g2d.fillArc(100, 200, 200, 20, 0, 360);
        // Dibujar el medio círculo
        g2d.setColor(Color.CYAN);
        g2d.fillArc(100, 110, 200, 200, 0, 180);

        g2d.setColor(Color.cyan);
        g2d.fillArc(280, 168, 20, 100, 0, 180);
        for (int i = 280; i <= 315; i++) {
            g2d.fillArc(i, 168, 20, 100, 0, 180);
        }

        g2d.fillArc(315, 168, 20, 100, 0, 180);
        g2d.setColor(Color.white);
        g2d.drawArc(315, 188, 15, 60, 0, 180);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Dibujo de Medio Círculo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);
        frame.add(new Iglu());
        frame.setVisible(true);
    }
}
