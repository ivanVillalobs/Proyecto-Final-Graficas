package panel;

import Estructuras.BoobSponja;
import Estructuras.CajaPizza;
import Estructuras.Fondo;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import java.util.Timer;

public class Panel extends JPanel implements Runnable {

    private BufferedImage buffer; // Buffer para dibujar el rectángulo
    private int x = 1, y = 700, mov; // Coordenadas iniciales de noot noot
    
    private Graphics graPixel;
    private double baseRadiusX = 50; // Radio base en X
    private double baseRadiusY = 45; // Radio base en Y
    private double baseRadiusZ = 75;  // Radio base en Z
    private static final int NUM_U = 30;
    private static final int NUM_V = 30;
    private static final double PERSPECTIVE_FACTOR = 1 / 300.0;
    private double rotationAngleX = 0;
    private double rotationAngleY = 0;
    private double rotationAngleZ = 0;
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    private double scaleZ = 1.0;
    private double scaleIncrement = 0.01;
    private Timer timer;
    private double translationX = 0;
    private double translationIncrement = 5;
    private double maxTranslation = 100;
    private boolean moveRight = true;
    private static final Color SKIN_COLOR = new Color(173, 206, 181);
    private static final Color SKIN_COLOR2 = new Color(123, 175, 153);
    private static final Color EYE_COLOR = new Color(255, 255, 181);
    private static final Color PUP_COLOR = new Color(133, 51, 29);
    private static final Color SHIRT_COLOR = new Color(173, 115, 66);
    private double cylinderRadius = 20;
    private double cylinderHeight = 80;
    private double limbRadius = 7;
    private double legMovementPhase = 0;
    private double legMovementSpeed = 0.05;
    private boolean isWalking = true;
    
    private Point3D centerPosition = new Point3D(-200, 0, 0); // Posición inicial del centro
    

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
    int rotar = 0;
    int rotar2 = 0;
    int rotar3 = 0;
    int x1 = 1;
    int t1 = 100, t2 = 120;
    int t11 = 0, t21 = 0;

    Thread animationThread = new Thread(this);

    public Panel() {
        setPreferredSize(new Dimension(800, 800));
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_ARGB);
        startRotation();
    }

    
    public void setBaseRadius(double x, double y, double z) {
        baseRadiusX = x;
        baseRadiusY = y;
        baseRadiusZ = z;
    }

    public void setCenterPosition(double x, double y, double z) {
        centerPosition.x = x;
        centerPosition.y = y;
        centerPosition.z = z;
    }
    
    private void startRotation() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (isWalking) {
                    legMovementPhase += legMovementSpeed;
                    if (legMovementPhase > 1 || legMovementPhase < 0) {
                        legMovementSpeed = -legMovementSpeed;
                        legMovementPhase += legMovementSpeed;
                    }
                }
                repaint();
            }
        }, 0, 30);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        // Inicializa graPixel con el Graphics del buffer
        graPixel = buffer.getGraphics();
        // Limpia el buffer antes de dibujar
        graPixel.clearRect(0, 0, buffer.getWidth(), buffer.getHeight());
    
        //fondo-----------------------------------------------------------------
        Fondo fondo = new Fondo();
        fondo.fondoboob(graPixel);
        fondo.drawEstrella(graPixel, xInicial1, yInicial1, iteracion, radio);
        fondo.drawEstrella(graPixel, xInicial2, yInicial2, iteracion, radio);
        fondo.drawEstrella(graPixel, xInicial3, yInicial3, iteracion, radio);
        fondo.montañasFondo(graPixel, xmount, 250);
        fondo.montañasFondo2(graPixel, xmount2, 250);
        fondo.montañasFondo3(graPixel, xmount3, 250);
        fondo.lineas(graPixel);
    
        // Ejemplo de uso de la clase BoobSponja
        BoobSponja boobSponja = new BoobSponja(10, 10, 11, t1, 120, 15, mov, 250, 100, Color.YELLOW);
        BoobSponja boobSponja2 = new BoobSponja(10, 10, 11, t1, 20, 15, mov, 350, 100, new Color(123, 63, 6));
        CajaPizza cajaPizza = new CajaPizza(10, 10, 100, 100, 20, 550, mov + 15, 300, 100, Color.WHITE);
        
        //Diseño del boob ------------------------------------------------------
        boobSponja2.manos2(graPixel, Color.YELLOW);
        boobSponja.dibujarCuboide(graPixel);
        boobSponja2.dibujarCuboide2(graPixel);
        boobSponja2.manos1(graPixel, Color.YELLOW);
        boobSponja2.rotarPiernasEnX(x);
        boobSponja2.piernas(graPixel, Color.YELLOW);
    
    
        //Diseño calamardo----------------------------------------------------------------------
        //Brazo Derecho
        drawLimb(new Point3D(centerPosition.x+10, centerPosition.y +10-200, centerPosition.z), new Point3D(centerPosition.x+5, centerPosition.y + 50-200, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x+5, centerPosition.y +50-200, centerPosition.z), new Point3D(centerPosition.x+10, centerPosition.y + 90-200, centerPosition.z));
        drawCylinder();
        drawSphere();
        drawFace();
        //Brazo Izquierdo
        drawLimb(new Point3D(centerPosition.x-30, centerPosition.y +10-200, centerPosition.z), new Point3D(centerPosition.x-35, centerPosition.y + 50-200, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x-35, centerPosition.y +50-200, centerPosition.z), new Point3D(centerPosition.x-30, centerPosition.y + 90-200, centerPosition.z));
        
        drawMovingLegs(g);
    
        // Crear una caja de pizza en el constructor del panel                
        // Dibujar la caja de pizza
        cajaPizza.rotarEnY(rotar);
        cajaPizza.rotarEnX(rotar3);
        cajaPizza.dibujar(graPixel);
    
        // Renderiza el buffer en el JPanel
        g.drawImage(buffer, 0, 0, this);
    
        graPixel.dispose();  // Libera los recursos del objeto Graphics
    }
    
    public void drawSphere() {
        Point3D[][] points = new Point3D[NUM_U][NUM_V];
        Point2D[][] projectedPoints = new Point2D[NUM_U][NUM_V];
    
        for (int i = 0; i < NUM_U; i++) {
            double theta = 2 * Math.PI * i / NUM_U;
            for (int j = 0; j < NUM_V; j++) {
                double phi = Math.PI * (j - NUM_V / 2.0) / NUM_V;
                points[i][j] = calculatePoint(theta, phi);
                Point3D scaledPoint = scale(points[i][j], scaleX, scaleY, scaleZ);
                Point3D rotatedPoint = rotate(scaledPoint, rotationAngleX, rotationAngleY, rotationAngleZ);
                projectedPoints[i][j] = applyPerspective(rotatedPoint);
            }
        }
    
        for (int i = 0; i < NUM_U; i++) {
            for (int j = 0; j < NUM_V - 1; j++) {
                Point2D p1 = projectedPoints[i][j];
                Point2D p2 = projectedPoints[(i + 1) % NUM_U][j];
                Point2D p3 = projectedPoints[i][j + 1];
                Point2D p4 = projectedPoints[(i + 1) % NUM_U][j + 1];
                int[] xPoints = {(int) p1.x, (int) p2.x, (int) p4.x, (int) p3.x};
                int[] yPoints = {(int) p1.y - 100-200, (int) p2.y - 100-200, (int) p4.y - 100-200, (int) p3.y - 100-200};
                graPixel.setColor(SKIN_COLOR);
                graPixel.fillPolygon(xPoints, yPoints, 4);
            }
        }
    }

    private void drawFace() {
        Point3D transformedLeftEye = transformEye(new Point3D(-20, 0, baseRadiusZ - 10));
        Point3D transformedRightEye = transformEye(new Point3D(20, 0, baseRadiusZ - 10));
        Point2D projectedLeftEye = applyPerspective(transformedLeftEye);
        Point2D projectedRightEye = applyPerspective(transformedRightEye);
    
        // Mejillas
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillRect((int)projectedLeftEye.x - 235, (int)projectedLeftEye.y + 15 - 100-200, 80, 60);
        graPixel.setColor(Color.BLACK);
    
        // Ojos
        graPixel.setColor(EYE_COLOR);
        graPixel.fillOval((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100-200, 30, 50);
        graPixel.fillOval((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100-200, 30, 50);
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100-200, 30, 50);
        graPixel.drawOval((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100-200, 30, 50);
    
        // Pupilas
        graPixel.setColor(PUP_COLOR);
        graPixel.fillRect((int)projectedLeftEye.x - 200, (int)projectedLeftEye.y + 30 - 100 -200, 10, 15);
        graPixel.fillRect((int)projectedRightEye.x - 205, (int)projectedRightEye.y + 30 - 100-200, 10, 15);
        graPixel.setColor(Color.BLACK);
        graPixel.drawRect((int)projectedLeftEye.x - 200, (int)projectedLeftEye.y + 30 - 100-200, 10, 15);
        graPixel.drawRect((int)projectedRightEye.x - 205, (int)projectedRightEye.y + 30 - 100-200, 10, 15);
    
        // Parpados
        graPixel.setColor(SKIN_COLOR2);
        graPixel.fillRect((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100-200, 30, 25);
        graPixel.fillRect((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100-200, 30, 25);
        graPixel.setColor(Color.BLACK);
        graPixel.drawRect((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100-200, 30, 25);
        graPixel.drawRect((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100-200, 30, 25);
    
        // Boca
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval((int)projectedRightEye.x - 280, (int)projectedRightEye.y + 60 - 100-200, 105, 30);
        graPixel.setColor(Color.BLACK);
        graPixel.drawLine((int)projectedRightEye.x - 250, (int)projectedRightEye.y + 75 - 100-200, (int)projectedRightEye.x - 270 + 85, (int)projectedRightEye.y + 75 - 100-200);
    
        // Nariz
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int)projectedRightEye.x - 235, (int)projectedRightEye.y + 50 - 100 - 200, 40, 70);
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval((int)projectedRightEye.x - 235, (int)projectedRightEye.y + 50 - 100 -200, 40, 70);
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int)projectedRightEye.x - 235, (int)projectedRightEye.y + 50 - 100 -200, 40, 70);
    }
    
    public Point3D transformEye(Point3D eye) {
        Point3D scaled = scale(eye, scaleX, scaleY, scaleZ);
        return rotate(scaled, rotationAngleX, rotationAngleY, rotationAngleZ);
    }

    private void drawCylinder() {
        Point3D[][] points = new Point3D[NUM_U][2];
        Point2D[][] projectedPoints = new Point2D[NUM_U][2];
    
        for (int i = 0; i < NUM_U; i++) {
            double theta = 2 * Math.PI * i / NUM_U;
            points[i][0] = new Point3D(
                cylinderRadius * Math.cos(theta) + centerPosition.x-10,
                centerPosition.y+230 - cylinderHeight / 2 -200,
                cylinderRadius * Math.sin(theta) + centerPosition.z
            );
            points[i][1] = new Point3D(
                cylinderRadius * Math.cos(theta) + centerPosition.x-10,
                centerPosition.y+50 + cylinderHeight / 2 -200,
                cylinderRadius * Math.sin(theta) + centerPosition.z
            );
    
            projectedPoints[i][0] = applyPerspective(points[i][0]);
            projectedPoints[i][1] = applyPerspective(points[i][1]);
        }

        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval(163,460-200, 50, 60);
    
        // Dibuja los lados del cilindro
        for (int i = 0; i < NUM_U; i++) {
            int nextIndex = (i + 1) % NUM_U;
            int[] xPoints = {(int) projectedPoints[i][0].x, (int) projectedPoints[nextIndex][0].x, (int) projectedPoints[nextIndex][1].x, (int) projectedPoints[i][1].x};
            int[] yPoints = {(int) projectedPoints[i][0].y - 100, (int) projectedPoints[nextIndex][0].y - 100, (int) projectedPoints[nextIndex][1].y - 100, (int) projectedPoints[i][1].y - 100};
            graPixel.setColor(SHIRT_COLOR);
            graPixel.fillPolygon(xPoints, yPoints, 4);
        }  
    }
    
    private void drawMovingLegs(Graphics g) {
        double offset = 50 * Math.sin(Math.PI * legMovementPhase);
    
        // Pierna Derecha
        drawLimb(new Point3D(centerPosition.x-10, centerPosition.y + 110-200, centerPosition.z),
                 new Point3D(centerPosition.x-20 + offset, centerPosition.y + 150-200, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x-20 + offset, centerPosition.y + 150-200, centerPosition.z),
                 new Point3D(centerPosition.x-50 + offset*2, centerPosition.y + 190-200, centerPosition.z));
    
        // Pierna Izquierda
        drawLimb(new Point3D(centerPosition.x-10, centerPosition.y + 110-200, centerPosition.z),
                 new Point3D(centerPosition.x+10 - offset, centerPosition.y + 150-200, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x+10 - offset, centerPosition.y + 150-200, centerPosition.z),
                 new Point3D(centerPosition.x+10 - offset*2, centerPosition.y + 190-200, centerPosition.z));
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
//            frame.add(new ProyectoFinal());

            // Iniciar el hilo de la animación después de que se haya creado el frame            
            animationThread.start();

        });
        javax.swing.Timer termina = new javax.swing.Timer(58000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Panel.this);
                System.exit(0); // termina 
            }
        });
        termina.setRepeats(false); // Solo queremos que se ejecute una vez después del tiempo especificado        
        termina.start(); // Iniciar el temporizador
    }

@Override
public void run() {
    int increment = 1; // Definir el incremento en x
    int increment2 = 1; // Definir el incremento en x

    boolean increasing = true; // Bandera para determinar si estamos incrementando o decrementando
    boolean increasing2 = true; // Bandera para determinar si estamos incrementando o decrementando

    while (true) {
        // Incrementar x en cada iteración    
        // t1 = 100, t2 = 120
        t2 += increment2;
        x += increment;
        x1 += increment;
        y += increment;
        xInicial1 -= increment;
        xInicial2 -= increment;
        xInicial3 -= increment;
        xmount -= increment;
        xmount2 -= increment;
        xmount3 -= increment;
        rotar += increment;

        // Verificar los límites de t1
        if (increasing) {
            t1 += increment2;
            if (t1 >= 110) {
                increasing = false; // Cambiar a decremento
            }
        } else {
            t1 -= increment2;
            if (t1 <= 90) {
                increasing = true; // Cambiar a incremento
            }
        }
        
//        // Verificar los límites de t1
//        if (increasing2) {
//            t2 += increment2;
//            if (t2 >= 120) {
//                increasing = false; // Cambiar a decremento
//            }
//        } else {
//            t2 -= increment2;
//            if (t2 <= 110) {
//                increasing2 = true; // Cambiar a incremento
//            }
//        }

        // Verificar los límites de la pantalla (opcional, para evitar que salga de la pantalla)
        if (x <= 500) {
            mov = x;
        } else {
            mov = 500;
        }
        if (x1 == 1000) {
            x1 = 1;
        }
        if (x1 <= 360) {
            rotar = x;
        } else {
            rotar = 0;
        }
        if (x1 >= 500) {
            rotar2 += 1;
            if (rotar2 <= 360) {
                rotar3 += 1;
            } else {
                rotar3 = 0;
            }
        } else {
            rotar2 = 0;
        }
        if (xInicial1 == -50) {
            xInicial1 = 1600; // Cambiar la dirección al alcanzar los límites
        }
        if (xInicial2 == -50) {
            xInicial2 = 1850; // Cambiar la dirección al alcanzar los límites
        }
        if (xInicial3 == -50) {
            xInicial3 = 2000; // Cambiar la dirección al alcanzar los límites
        }
        if (xmount == -400) {
            xmount = 750;
        }
        if (xmount2 == -600) {
            xmount2 = 1400;
        }
        if (xmount3 == -600) {
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
    
    private void drawLimb(Point3D start, Point3D end) {
        Point3D[][] points = new Point3D[NUM_U][2]; 
        Point2D[][] projectedPoints = new Point2D[NUM_U][2];
    
        for (int i = 0; i < NUM_U; i++) {
            double theta = 2 * Math.PI * i / NUM_U;
            points[i][0] = new Point3D(
                limbRadius * Math.cos(theta) + start.x,
                start.y,
                limbRadius * Math.sin(theta) + start.z
            );
            points[i][1] = new Point3D(
                limbRadius * Math.cos(theta) + end.x,
                end.y,
                limbRadius * Math.sin(theta) + end.z
            );
    
            projectedPoints[i][0] = applyPerspective(points[i][0]);
            projectedPoints[i][1] = applyPerspective(points[i][1]);
        }
    
        for (int i = 0; i < NUM_U; i++) {
            int nextIndex = (i + 1) % NUM_U;
            int[] xPoints = {(int) projectedPoints[i][0].x, (int) projectedPoints[nextIndex][0].x, (int) projectedPoints[nextIndex][1].x, (int) projectedPoints[i][1].x};
            int[] yPoints = {(int) projectedPoints[i][0].y, (int) projectedPoints[nextIndex][0].y, (int) projectedPoints[nextIndex][1].y, (int) projectedPoints[i][1].y};
            graPixel.setColor(SKIN_COLOR); 
            graPixel.fillPolygon(xPoints, yPoints, 4);
        }
    }

    private Point3D calculatePoint(double theta, double phi) {
        double x = baseRadiusX * Math.cos(theta) * Math.cos(phi) + centerPosition.x;
        double y = baseRadiusY * Math.sin(theta) * Math.cos(phi) + centerPosition.y;
        double z = baseRadiusZ * Math.sin(phi) + centerPosition.z;
        return new Point3D(x, y, z);
    }

    public Point3D scale(Point3D point, double scaleX, double scaleY, double scaleZ) {
        double x = point.x * scaleX;
        double y = point.y * scaleY;
        double z = point.z * scaleZ;
        return new Point3D(x, y, z);
    }

    public Point3D rotate(Point3D point, double angleX, double angleY, double angleZ) {
        double cosX = Math.cos(angleX);
        double sinX = Math.sin(angleX);
        double cosY = Math.cos(angleY);
        double sinY = Math.sin(angleY);
        double cosZ = Math.cos(angleZ);
        double sinZ = Math.sin(angleZ);

        double y = point.y * cosX - point.z * sinX;
        double z = point.y * sinX + point.z * cosX;
        point.y = y;
        point.z = z;

        double x = point.x * cosY + point.z * sinY;
        z = -point.x * sinY + point.z * cosY;
        point.x = x;
        point.z = z;

        x = point.x * cosZ - point.y * sinZ;
        y = point.x * sinZ + point.y * cosZ;
        point.x = x;
        point.y = y;

        return point;
    }

    public Point2D applyPerspective(Point3D point) {
        double scaleFactor = 1 / (1 + point.z * PERSPECTIVE_FACTOR);
        double x = point.x * scaleFactor + buffer.getWidth() / 2.0 + translationX;
        double y = point.y * scaleFactor + buffer.getHeight() / 2.0;
        return new Point2D(x, y);
    }

    public static class Point3D {
        double x, y, z;
        Point3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static class Point2D {
        double x, y;
        Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}