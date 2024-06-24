package panel;

import Estructuras.BoobSponja;
import Estructuras.Fondo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class Panel extends JPanel implements Runnable {

    private BufferedImage buffer; // Buffer para dibujar el rectángulo
    private int x = 1, y = 700; // Coordenadas iniciales de noot noot

    int xInicial1 = 850;
    int yInicial1 = 180;
    int xInicial2 = 1600;
    int yInicial2 = 160;
    int xInicial3 = 1250;
    int yInicial3 = 90;
    int iteracion = 300;
    int radio = 40;
    int xmount = 850;
    int xmount2 = 1400;
    int xmount3 = 2000;

    Thread animationThread = new Thread(this);

    public Panel() {
        buffer = new BufferedImage(800, 500, BufferedImage.TYPE_INT_ARGB);
        setBackground(Color.WHITE); // Establecer el color de fondo del panel

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, this);

    //fondo-----------------------------------------------------------------
    Fondo fondo = new Fondo();
    fondo.fondoboob (g);            
    fondo.drawEstrella (g, xInicial1, yInicial1, iteracion, radio);    
    fondo.drawEstrella (g, xInicial2, yInicial2, iteracion, radio);
    fondo.drawEstrella (g, xInicial3, yInicial3, iteracion, radio);
    fondo.montañasFondo(g, xmount,250);
    fondo.montañasFondo2(g, xmount2,250);
    fondo.montañasFondo3(g, xmount3,250);
    fondo.lineas(g);
    
//        // Ejemplo de uso de la clase BoobSponja
    //xc perspectiva
    //offset coordenada del cubo
    BoobSponja boobSponja = new BoobSponja(10, 10, 11, 100, 120, 15, 200, 200, 100, Color.YELLOW);
    BoobSponja boobSponja2 = new BoobSponja(10, 10, 11, 100, 20, 15, 200, 300, 100, new Color(123, 63, 6));
//        boobSponja.rotarEnX(x); // Rotar 30 grados en el eje Y
//        boobSponja.rotarEnY(); // Rotar 30 grados en el eje Y
//        boobSponja.trasladar(400, 400, 200);
    //diseño del boob ------------------------------------------------------
//        boobSponja2.manos2(g,Color.YELLOW);
//        boobSponja.dibujarCuboide(g);
//        boobSponja2.dibujarCuboide2(g);
//        boobSponja2.manos1(g,Color.YELLOW);
//        boobSponja2.rotarPiernasEnX(x);
//        boobSponja2.piernas(g,Color.YELLOW);
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
            xInicial1 -= increment;
            xInicial2 -= increment;
            xInicial3 -= increment;
            xmount -= increment;
            xmount2 -= increment;
            xmount3 -= increment;

            // Verificar los límites de la pantalla (opcional, para evitar que salga de la pantalla)
            if (xInicial1 == -50) {
                xInicial1 = 1600;// Cambiar la dirección al alcanzar los límites
            }
            if (xInicial2 == -50) {
                xInicial2 = 1850;// Cambiar la dirección al alcanzar los límites
            }
            if (xInicial3 == -50) {
                xInicial3 = 2000;// Cambiar la dirección al alcanzar los límites
            }
            if(xmount == -400){
                xmount = 750;
            }
            if(xmount2 == -600){
                xmount2 = 1400;
            }
            if(xmount3 == -600){
                xmount3 = 2000;
            }
            repaint(); // Vuelve a dibujar el panel

            try {
                Thread.sleep(10); // Pausa para controlar la velocidad de la animación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
