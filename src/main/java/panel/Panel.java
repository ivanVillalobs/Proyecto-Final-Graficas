package panel;

import Estructuras.BoobSponja;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Panel extends JPanel implements Runnable {

    private BufferedImage buffer; // Buffer para dibujar el rectángulo
    private int x = 1, y = 700; // Coordenadas iniciales de noot noot

    Thread animationThread = new Thread(this);

    public Panel() {
        buffer = new BufferedImage(800, 500, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.WHITE); // Establecer el color de fondo del panel

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);
        // Ejemplo de uso de la clase BoobSponja
        //xc perspectiva
        //offset coordenada del cubo
        BoobSponja boobSponja = new BoobSponja(10, 10, 11, 100, 120, 15, 250, 200, 100,Color.YELLOW);
        BoobSponja boobSponja2 = new BoobSponja(10, 10, 11, 100, 20, 15, 250, 300, 100,new Color(123,63,6));
//        boobSponja.rotarEnX(x); // Rotar 30 grados en el eje Y
//        boobSponja.rotarEnY(); // Rotar 30 grados en el eje Y
//        boobSponja.trasladar(400, 400, 200);
        boobSponja2.manos2(g,Color.YELLOW);
        boobSponja.dibujarCuboide(g);
        boobSponja2.dibujarCuboide2(g);
        boobSponja2.manos1(g,Color.YELLOW);

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
            frame.setSize(800, 500);
            frame.setLocationRelativeTo(null);
            frame.getContentPane().add(this); // Agrega el panel actual al JFrame
            frame.setVisible(true);

            // Iniciar el hilo de la animación después de que se haya creado el frame            
            animationThread.start();
        });
    }

    @Override
    public void run() {
        int increment = 1; // Definir el incremento en x

        while (true) {
            // Incrementar x en cada iteración
            x += increment;
            y += increment;

//            // Verificar los límites de la pantalla (opcional, para evitar que salga de la pantalla)
//            if (x > getWidth() || x < 0) {
//                increment = -increment; // Cambiar la dirección al alcanzar los límites
//            }
            repaint(); // Vuelve a dibujar el panel

            try {
                Thread.sleep(100); // Pausa para controlar la velocidad de la animación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
