package panel;

import Estructuras.NootNoot;
import metodos.Esfera;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel implements Runnable {

    private BufferedImage buffer; // Buffer para dibujar el rectángulo
    private int x = 200, y = 300; // Coordenadas iniciales de noot noot

    Thread animationThread = new Thread(this);

    public Panel() {
        buffer = new BufferedImage(800, 500, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.WHITE); // Establecer el color de fondo del panel

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
        NootNoot noot = new NootNoot(g);
        noot.pingu(x, y);
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

            // Iniciar el hilo de la animación después de que se haya creado el frame            
            animationThread.start();
        });
    }

@Override
public void run() {
    int incrementX = 1; // Definir el incremento en x

    while (true) {
        // Incrementar x en cada iteración
        x += incrementX;

        // Verificar los límites de la pantalla (opcional, para evitar que salga de la pantalla)
        if (x > getWidth() || x < 0) {
            incrementX = -incrementX; // Cambiar la dirección al alcanzar los límites
        }

        repaint(); // Vuelve a dibujar el panel

        try {
            Thread.sleep(5); // Pausa para controlar la velocidad de la animación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


}
