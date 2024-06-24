package Estructuras;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ProyectoFinal extends JPanel {

  
    private BufferedImage buffer;
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
    private double limbHeight = 70;
    private double legMovementPhase = 0;
    private double legMovementSpeed = 0.05;
    private boolean isWalking = true;



    private Point3D centerPosition = new Point3D(-200, 0, 0); // Posición inicial del centro
    

    public ProyectoFinal() {
        setPreferredSize(new Dimension(650, 650));
        setBackground(Color.WHITE);
        buffer = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        graPixel = buffer.createGraphics();
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
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        graPixel.setColor(Color.WHITE);
        graPixel.fillRect(0, 0, buffer.getWidth(), buffer.getHeight());
        //Brazo Derecho
        drawLimb(new Point3D(centerPosition.x+10, centerPosition.y +10, centerPosition.z), new Point3D(centerPosition.x+5, centerPosition.y + 50, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x+5, centerPosition.y +50, centerPosition.z), new Point3D(centerPosition.x+10, centerPosition.y + 90, centerPosition.z));
        drawCylinder();
        drawSphere();
        drawFace();
        //Brazo Izquierdo
        drawLimb(new Point3D(centerPosition.x-30, centerPosition.y +10, centerPosition.z), new Point3D(centerPosition.x-35, centerPosition.y + 50, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x-35, centerPosition.y +50, centerPosition.z), new Point3D(centerPosition.x-30, centerPosition.y + 90, centerPosition.z));
        
        drawMovingLegs(g);

        g.drawImage(buffer, 0, 0, null);
    }

    private void drawSphere() {
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
                int[] yPoints = {(int) p1.y - 100, (int) p2.y - 100, (int) p4.y - 100, (int) p3.y - 100};
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
        graPixel.fillRect((int)projectedLeftEye.x - 235, (int)projectedLeftEye.y + 15 - 100, 80, 60);
        graPixel.setColor(Color.BLACK);
    
        // Ojos
        graPixel.setColor(EYE_COLOR);
        graPixel.fillOval((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100, 30, 50);
        graPixel.fillOval((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100, 30, 50);
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100, 30, 50);
        graPixel.drawOval((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100, 30, 50);
    
        // Pupilas
        graPixel.setColor(PUP_COLOR);
        graPixel.fillRect((int)projectedLeftEye.x - 200, (int)projectedLeftEye.y + 30 - 100, 10, 15);
        graPixel.fillRect((int)projectedRightEye.x - 205, (int)projectedRightEye.y + 30 - 100, 10, 15);
        graPixel.setColor(Color.BLACK);
        graPixel.drawRect((int)projectedLeftEye.x - 200, (int)projectedLeftEye.y + 30 - 100, 10, 15);
        graPixel.drawRect((int)projectedRightEye.x - 205, (int)projectedRightEye.y + 30 - 100, 10, 15);
    
        // Parpados
        graPixel.setColor(SKIN_COLOR2);
        graPixel.fillRect((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100, 30, 25);
        graPixel.fillRect((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100, 30, 25);
        graPixel.setColor(Color.BLACK);
        graPixel.drawRect((int)projectedLeftEye.x - 215, (int)projectedLeftEye.y + 10 - 100, 30, 25);
        graPixel.drawRect((int)projectedRightEye.x - 220, (int)projectedRightEye.y + 10 - 100, 30, 25);
    
        // Boca
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval((int)projectedRightEye.x - 280, (int)projectedRightEye.y + 60 - 100, 105, 30);
        graPixel.setColor(Color.BLACK);
        graPixel.drawLine((int)projectedRightEye.x - 250, (int)projectedRightEye.y + 75 - 100, (int)projectedRightEye.x - 270 + 85, (int)projectedRightEye.y + 75 - 100);
    
        // Nariz
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int)projectedRightEye.x - 235, (int)projectedRightEye.y + 50 - 100, 40, 70);
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval((int)projectedRightEye.x - 235, (int)projectedRightEye.y + 50 - 100, 40, 70);
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int)projectedRightEye.x - 235, (int)projectedRightEye.y + 50 - 100, 40, 70);
    }
    
    

    private Point3D transformEye(Point3D eye) {
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
                centerPosition.y+230 - cylinderHeight / 2,
                cylinderRadius * Math.sin(theta) + centerPosition.z
            );
            points[i][1] = new Point3D(
                cylinderRadius * Math.cos(theta) + centerPosition.x-10,
                centerPosition.y+50 + cylinderHeight / 2,
                cylinderRadius * Math.sin(theta) + centerPosition.z
            );
    
            projectedPoints[i][0] = applyPerspective(points[i][0]);
            projectedPoints[i][1] = applyPerspective(points[i][1]);
        }

        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval(163,460, 50, 60);
    
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
        drawLimb(new Point3D(centerPosition.x-10, centerPosition.y + 110, centerPosition.z),
                 new Point3D(centerPosition.x-20 + offset, centerPosition.y + 150, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x-20 + offset, centerPosition.y + 150, centerPosition.z),
                 new Point3D(centerPosition.x-50 + offset*2, centerPosition.y + 190, centerPosition.z));
    
        // Pierna Izquierda
        drawLimb(new Point3D(centerPosition.x-10, centerPosition.y + 110, centerPosition.z),
                 new Point3D(centerPosition.x+10 - offset, centerPosition.y + 150, centerPosition.z));
        drawLimb(new Point3D(centerPosition.x+10 - offset, centerPosition.y + 150, centerPosition.z),
                 new Point3D(centerPosition.x+10 - offset*2, centerPosition.y + 190, centerPosition.z));
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

    private Point3D scale(Point3D point, double scaleX, double scaleY, double scaleZ) {
        double x = point.x * scaleX;
        double y = point.y * scaleY;
        double z = point.z * scaleZ;
        return new Point3D(x, y, z);
    }

    private Point3D rotate(Point3D point, double angleX, double angleY, double angleZ) {
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

    private Point2D applyPerspective(Point3D point) {
        double scaleFactor = 1 / (1 + point.z * PERSPECTIVE_FACTOR);
        double x = point.x * scaleFactor + buffer.getWidth() / 2.0 + translationX;
        double y = point.y * scaleFactor + buffer.getHeight() / 2.0;
        return new Point2D(x, y);
    }

//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame("Calamardo");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//            frame.setVisible(true);
//        });
//    }

    private static class Point3D {
        double x, y, z;
        Point3D(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    private static class Point2D {
        double x, y;
        Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
}
