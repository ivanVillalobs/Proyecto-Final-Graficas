package Estructuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class CajaPizza {
    
    int Xc, Yc, Zc; // Vector de proyección
    int width, height, depth; // Dimensiones del cuboide
    int offsetX, offsetY, offsetZ; // Posición del cuboide
    Color q;
    private int[][] vertices;
    
    public CajaPizza(int xc, int yc, int zc, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, Color q) {
        this.Xc = xc;
        this.Yc = yc;
        this.Zc = zc;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.offsetZ = offsetZ;
        this.q = q;
        generateVertices();
    }
    private void generateVertices() {
        vertices = new int[][]{
            {offsetX, offsetY, offsetZ}, // A
            {offsetX, offsetY + height, offsetZ}, // B
            {offsetX + width, offsetY + height, offsetZ}, // C
            {offsetX + width, offsetY, offsetZ}, // D
            {offsetX, offsetY, offsetZ + depth}, // E
            {offsetX, offsetY + height, offsetZ + depth}, // F
            {offsetX + width, offsetY + height, offsetZ + depth}, // G
            {offsetX + width, offsetY, offsetZ + depth} // H
        };
    }
public void dibujar(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();

    // Proyectar las coordenadas 3D en 2D usando el vector de proyección
    int[][] projectedVertices = new int[8][2];
    for (int i = 0; i < vertices.length; i++) {
        projectedVertices[i] = proyeccioncube(vertices[i][0], vertices[i][1], vertices[i][2], Xc, Yc, Zc);
    }

    // Dibujar las caras del cuboide con relleno
    g2d.setColor(q);
    int[] xPoints = {projectedVertices[0][0], projectedVertices[1][0], projectedVertices[2][0], projectedVertices[3][0]};
    int[] yPoints = {projectedVertices[0][1], projectedVertices[1][1], projectedVertices[2][1], projectedVertices[3][1]};
    g2d.fillPolygon(xPoints, yPoints, 4);

    xPoints = new int[]{projectedVertices[4][0], projectedVertices[5][0], projectedVertices[6][0], projectedVertices[7][0]};
    yPoints = new int[]{projectedVertices[4][1], projectedVertices[5][1], projectedVertices[6][1], projectedVertices[7][1]};
    g2d.fillPolygon(xPoints, yPoints, 4);

    xPoints = new int[]{projectedVertices[0][0], projectedVertices[1][0], projectedVertices[5][0], projectedVertices[4][0]};
    yPoints = new int[]{projectedVertices[0][1], projectedVertices[1][1], projectedVertices[5][1], projectedVertices[4][1]};
    g2d.fillPolygon(xPoints, yPoints, 4);

    xPoints = new int[]{projectedVertices[2][0], projectedVertices[3][0], projectedVertices[7][0], projectedVertices[6][0]};
    yPoints = new int[]{projectedVertices[2][1], projectedVertices[3][1], projectedVertices[7][1], projectedVertices[6][1]};
    g2d.fillPolygon(xPoints, yPoints, 4);

    xPoints = new int[]{projectedVertices[0][0], projectedVertices[3][0], projectedVertices[7][0], projectedVertices[4][0]};
    yPoints = new int[]{projectedVertices[0][1], projectedVertices[3][1], projectedVertices[7][1], projectedVertices[4][1]};
    g2d.fillPolygon(xPoints, yPoints, 4);

    xPoints = new int[]{projectedVertices[1][0], projectedVertices[2][0], projectedVertices[6][0], projectedVertices[5][0]};
    yPoints = new int[]{projectedVertices[1][1], projectedVertices[2][1], projectedVertices[6][1], projectedVertices[5][1]};
    g2d.fillPolygon(xPoints, yPoints, 4);

    // Dibujar un cuadrado más pequeño en el centro de la cara superior del cuboide
    int innerSquareSize = width / 2; // Tamaño del cuadrado interior (proporcional al ancho del cuboide)
    int innerSquareX = projectedVertices[0][0] + (width - innerSquareSize) / 2; // Posición X del cuadrado interior
    int innerSquareY = projectedVertices[0][1] + (depth - innerSquareSize) / 2; // Posición Y del cuadrado interior
    g2d.fillRect(innerSquareX, innerSquareY, innerSquareSize, innerSquareSize);

    // Dibujar las aristas del cuboide
    g2d.setColor(Color.BLACK);
    for (int i = 0; i < 4; i++) {
        g2d.drawLine(projectedVertices[i][0], projectedVertices[i][1], projectedVertices[(i + 1) % 4][0], projectedVertices[(i + 1) % 4][1]);
        g2d.drawLine(projectedVertices[i + 4][0], projectedVertices[i + 4][1], projectedVertices[(i + 1) % 4 + 4][0], projectedVertices[(i + 1) % 4 + 4][1]);
        g2d.drawLine(projectedVertices[i][0], projectedVertices[i][1], projectedVertices[i + 4][0], projectedVertices[i + 4][1]);
    }
    
    g2d.dispose();
}

    
    private int[] proyeccion(int x1, int y1, int z1, int Xc, int Yc, int Zc) {
        double U = (double) Zc / (z1 + Zc);
        int x = (int) (Xc - (x1 - Xc) * U);
        int y = (int) (Yc - (y1 - Yc) * U);
        return new int[]{x, y};
    }

    private int[] proyeccioncube(int x1, int y1, int z1, int xp, int yp, int zp) {
        int x = (int) (x1 - xp * ((double) z1 / zp));
        int y = (int) (y1 - yp * ((double) z1 / zp));
        return new int[]{x, y};
    }
    public void rotarEnX(double angulo) {
    double cosAngulo = Math.cos(Math.toRadians(angulo));
    double sinAngulo = Math.sin(Math.toRadians(angulo));

    for (int i = 0; i < vertices.length; i++) {
        int y = vertices[i][1];
        int z = vertices[i][2];
        vertices[i][1] = (int) (y * cosAngulo - z * sinAngulo);
        vertices[i][2] = (int) (y * sinAngulo + z * cosAngulo);
    }
}
    
    

public void rotarEnY(double angulo) {
    double cosAngulo = Math.cos(Math.toRadians(angulo));
    double sinAngulo = Math.sin(Math.toRadians(angulo));

    for (int i = 0; i < vertices.length; i++) {
        int x = vertices[i][0];
        int z = vertices[i][2];
        vertices[i][0] = (int) (x * cosAngulo - z * sinAngulo);
        vertices[i][2] = (int) (x * sinAngulo + z * cosAngulo);
    }
}

public void rotarEnZ(double angulo) {
    double cosAngulo = Math.cos(Math.toRadians(angulo));
    double sinAngulo = Math.sin(Math.toRadians(angulo));

    for (int i = 0; i < vertices.length; i++) {
        int x = vertices[i][0];
        int y = vertices[i][1];
        vertices[i][0] = (int) (x * cosAngulo - y * sinAngulo);
        vertices[i][1] = (int) (x * sinAngulo + y * cosAngulo);
    }
}

}
