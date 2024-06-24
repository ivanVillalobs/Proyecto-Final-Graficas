package Estructuras;

import java.awt.Color;
import java.awt.Graphics;
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
    private Graphics graPixel;
    private double translationX = 0;
    private static final double PERSPECTIVE_FACTOR = 1 / 300.0;

    public void drawFace() {
        ProyectoFinal.Point3D transformedLeftEye = transformEye(new ProyectoFinal.Point3D(-20, 0, baseRadiusZ - 10));
        ProyectoFinal.Point3D transformedRightEye = transformEye(new ProyectoFinal.Point3D(20, 0, baseRadiusZ - 10));
        ProyectoFinal.Point2D projectedLeftEye = applyPerspective(transformedLeftEye);
        ProyectoFinal.Point2D projectedRightEye = applyPerspective(transformedRightEye);

        // Mejillas
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillRect((int) projectedLeftEye.x - 235, (int) projectedLeftEye.y + 15 - 100 - 200, 80, 60);
        graPixel.setColor(Color.BLACK);

        // Ojos
        graPixel.setColor(EYE_COLOR);
        graPixel.fillOval((int) projectedLeftEye.x - 215, (int) projectedLeftEye.y + 10 - 100 - 200, 30, 50);
        graPixel.fillOval((int) projectedRightEye.x - 220, (int) projectedRightEye.y + 10 - 100 - 200, 30, 50);
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int) projectedLeftEye.x - 215, (int) projectedLeftEye.y + 10 - 100 - 200, 30, 50);
        graPixel.drawOval((int) projectedRightEye.x - 220, (int) projectedRightEye.y + 10 - 100 - 200, 30, 50);

        // Pupilas
        graPixel.setColor(PUP_COLOR);
        graPixel.fillRect((int) projectedLeftEye.x - 200, (int) projectedLeftEye.y + 30 - 100 - 200, 10, 15);
        graPixel.fillRect((int) projectedRightEye.x - 205, (int) projectedRightEye.y + 30 - 100 - 200, 10, 15);
        graPixel.setColor(Color.BLACK);
        graPixel.drawRect((int) projectedLeftEye.x - 200, (int) projectedLeftEye.y + 30 - 100 - 200, 10, 15);
        graPixel.drawRect((int) projectedRightEye.x - 205, (int) projectedRightEye.y + 30 - 100 - 200, 10, 15);

        // Parpados
        graPixel.setColor(SKIN_COLOR2);
        graPixel.fillRect((int) projectedLeftEye.x - 215, (int) projectedLeftEye.y + 10 - 100 - 200, 30, 25);
        graPixel.fillRect((int) projectedRightEye.x - 220, (int) projectedRightEye.y + 10 - 100 - 200, 30, 25);
        graPixel.setColor(Color.BLACK);
        graPixel.drawRect((int) projectedLeftEye.x - 215, (int) projectedLeftEye.y + 10 - 100 - 200, 30, 25);
        graPixel.drawRect((int) projectedRightEye.x - 220, (int) projectedRightEye.y + 10 - 100 - 200, 30, 25);

        // Boca
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval((int) projectedRightEye.x - 280, (int) projectedRightEye.y + 60 - 100 - 200, 105, 30);
        graPixel.setColor(Color.BLACK);
        graPixel.drawLine((int) projectedRightEye.x - 250, (int) projectedRightEye.y + 75 - 100 - 200, (int) projectedRightEye.x - 270 + 85, (int) projectedRightEye.y + 75 - 100 - 200);

        // Nariz
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int) projectedRightEye.x - 235, (int) projectedRightEye.y + 50 - 100 - 200, 40, 70);
        graPixel.setColor(SKIN_COLOR);
        graPixel.fillOval((int) projectedRightEye.x - 235, (int) projectedRightEye.y + 50 - 100 - 200, 40, 70);
        graPixel.setColor(Color.BLACK);
        graPixel.drawOval((int) projectedRightEye.x - 235, (int) projectedRightEye.y + 50 - 100 - 200, 40, 70);
    }

    public ProyectoFinal.Point3D transformEye(ProyectoFinal.Point3D eye) {
        ProyectoFinal.Point3D scaled = scale(eye, scaleX, scaleY, scaleZ);
        return rotate(scaled, rotationAngleX, rotationAngleY, rotationAngleZ);
    }

    public ProyectoFinal.Point3D scale(ProyectoFinal.Point3D point, double scaleX, double scaleY, double scaleZ) {
        double x = point.x * scaleX;
        double y = point.y * scaleY;
        double z = point.z * scaleZ;
        return new ProyectoFinal.Point3D(x, y, z);
    }

    public ProyectoFinal.Point2D applyPerspective(ProyectoFinal.Point3D point) {
        double scaleFactor = 1 / (1 + point.z * PERSPECTIVE_FACTOR);
        double x = point.x * scaleFactor + buffer.getWidth() / 2.0 + translationX;
        double y = point.y * scaleFactor + buffer.getHeight() / 2.0;
        return new ProyectoFinal.Point2D(x, y);
    }

    public ProyectoFinal.Point3D rotate(ProyectoFinal.Point3D point, double angleX, double angleY, double angleZ) {
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
}
