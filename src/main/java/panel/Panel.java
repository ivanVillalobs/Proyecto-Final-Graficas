package panel;

import Estructuras.NootNoot;
import metodos.Esfera;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel {
    private BufferedImage buffer; // Buffer para dibujar el rectángulo
    
    public Panel() {
        buffer = new BufferedImage(800, 500, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.WHITE); // Establecer el color de fondo del panel
               
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);        
        NootNoot noot = new NootNoot(g);
        noot.pingu();
    }

    public void putPixel(int x, int y, Color color) {
        if (x >= 0 && x < buffer.getWidth() && y >= 0 && y < buffer.getHeight()) {
            buffer.setRGB(x, y, color.getRGB());
            repaint();
        }
    }

    public void componentes() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Figuras");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(790, 520);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(this); // Agrega el panel actual al JFrame
            frame.setVisible(true);
        });
    }
}
