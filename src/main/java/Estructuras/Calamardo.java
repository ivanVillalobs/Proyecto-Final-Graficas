package Estructuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author ivano
 */
public class Calamardo {

    private BufferedImage buffer;
    private static final Color SKIN_COLOR = new Color(173, 206, 181);
    private static final Color SKIN_COLOR2 = new Color(123, 175, 153);
    private static final Color EYE_COLOR = new Color(255, 255, 181);
    private static final Color PUP_COLOR = new Color(133, 51, 29);
    private static final Color SHIRT_COLOR = new Color(173, 115, 66);
    private double rotationAngleX = 0;
    private double rotationAngleY = 0;
    private double rotationAngleZ = 0;
    private double scaleX = 1.0;
    private double scaleY = 1.0;
    private double scaleZ = 1.0;
    private double baseRadiusX = 50; // Radio base en X
    private double baseRadiusY = 45; // Radio base en Y
    private double baseRadiusZ = 75;  // Radio base en Z
    private double translationX = 0;
    private static final double PERSPECTIVE_FACTOR = 1 / 300.0;

    public void drawFace(Graphics g) {        
        Graphics2D g2d = (Graphics2D) g.create();
        // Mejillas
        g2d.setColor(SKIN_COLOR);
//        graPixel.fillRect(0, 0, 0, 0);
        g2d.fillRect(80,50, 80, 60);
        g2d.setColor(Color.BLACK);

        // Ojos
        g2d.setColor(EYE_COLOR);     
//        graPixel.fillOval(0, 0, 0, 0);
        g2d.fillOval(50,50, 30, 50);
        g2d.fillOval(80,50, 30, 50);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(50,50, 30, 50);
        g2d.drawOval(80,50, 30, 50);

        // Pupilas
        g2d.setColor(PUP_COLOR);
        g2d.fillRect(50,60, 10, 15);
        g2d.fillRect(70,60, 10, 15);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(50,60, 10, 15);
        g2d.drawRect(70,60, 10, 15);

        // Parpados
//        graPixel.setColor(SKIN_COLOR2);
//        graPixel.fillRect((int) projectedLeftEye.x - 215, (int) projectedLeftEye.y + 10 - 100 - 200, 30, 25);
//        graPixel.fillRect((int) projectedRightEye.x - 220, (int) projectedRightEye.y + 10 - 100 - 200, 30, 25);
//        graPixel.setColor(Color.BLACK);
//        graPixel.drawRect((int) projectedLeftEye.x - 215, (int) projectedLeftEye.y + 10 - 100 - 200, 30, 25);
//        graPixel.drawRect((int) projectedRightEye.x - 220, (int) projectedRightEye.y + 10 - 100 - 200, 30, 25);
//
//        // Boca
//        graPixel.setColor(SKIN_COLOR);
//        graPixel.fillOval((int) projectedRightEye.x - 280, (int) projectedRightEye.y + 60 - 100 - 200, 105, 30);
//        graPixel.setColor(Color.BLACK);
//        graPixel.drawLine((int) projectedRightEye.x - 250, (int) projectedRightEye.y + 75 - 100 - 200, (int) projectedRightEye.x - 270 + 85, (int) projectedRightEye.y + 75 - 100 - 200);
//
//        // Nariz
//        graPixel.setColor(Color.BLACK);
//        graPixel.drawOval((int) projectedRightEye.x - 235, (int) projectedRightEye.y + 50 - 100 - 200, 40, 70);
//        graPixel.setColor(SKIN_COLOR);
//        graPixel.fillOval((int) projectedRightEye.x - 235, (int) projectedRightEye.y + 50 - 100 - 200, 40, 70);
//        graPixel.setColor(Color.BLACK);
//        graPixel.drawOval((int) projectedRightEye.x - 235, (int) projectedRightEye.y + 50 - 100 - 200, 40, 70);
    }

    
}
