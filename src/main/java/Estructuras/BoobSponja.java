package Estructuras;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BoobSponja {

    int Xc, Yc, Zc; // Vector de proyección
    int width, height, depth; // Dimensiones del cuboide
    int offsetX, offsetY, offsetZ; // Posición del cuboide
    Color q;
    private int[][] vertices;

    public BoobSponja(int xc, int yc, int zc, int width, int height, int depth, int offsetX, int offsetY, int offsetZ, Color q) {
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

    public void dibujarCuboide(Graphics g) {
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

        // Dibujar las aristas del cuboide
        g2d.setColor(Color.YELLOW);
        for (int i = 0; i < 4; i++) {
            g2d.drawLine(projectedVertices[i][0], projectedVertices[i][1], projectedVertices[(i + 1) % 4][0], projectedVertices[(i + 1) % 4][1]);
            g2d.drawLine(projectedVertices[i + 4][0], projectedVertices[i + 4][1], projectedVertices[(i + 1) % 4 + 4][0], projectedVertices[(i + 1) % 4 + 4][1]);
            g2d.drawLine(projectedVertices[i][0], projectedVertices[i][1], projectedVertices[i + 4][0], projectedVertices[i + 4][1]);
        }

        // Definir las posiciones de los círculos al frente del cuboide
        g2d.setColor(new Color(161,165,33,255));
        int circleDiameter = 10;
        int[][] circleFrontPositions = {
            {projectedVertices[0][0] + circleDiameter, projectedVertices[0][1] + circleDiameter},
            {projectedVertices[3][0] - circleDiameter, projectedVertices[3][1] + circleDiameter},
            {projectedVertices[0][0] + circleDiameter, projectedVertices[0][1] + height / 2},
            {projectedVertices[3][0] - circleDiameter, projectedVertices[3][1] + height / 2},
            {projectedVertices[0][0] + circleDiameter, projectedVertices[0][1] + height - circleDiameter},
            {projectedVertices[3][0] - circleDiameter, projectedVertices[3][1] + height - circleDiameter}
        };

        // Definir las posiciones de los círculos a los lados del cuboide
        int circleSideDiameter = 3;
        int[][] circleSidePositions = {
            {(projectedVertices[0][0] + projectedVertices[4][0]) / 2 - circleSideDiameter / 2,
                (projectedVertices[0][1] + projectedVertices[1][1]) / 2 - circleSideDiameter / 2},
            {(projectedVertices[3][0] + projectedVertices[7][0]) / 2 - circleSideDiameter / 2,
                (projectedVertices[3][1] + projectedVertices[2][1]) / 2 - circleSideDiameter / 2},
            {(projectedVertices[1][0] + projectedVertices[5][0]) / 2 - circleSideDiameter / 2,
                (projectedVertices[1][1] + projectedVertices[2][1]) / 2 - circleSideDiameter / 2}
        };

        // Dibujar los círculos al frente del cuboide más separados
        for (int i = 0; i < 6; i++) {
            g2d.fillOval(circleFrontPositions[i][0], circleFrontPositions[i][1], circleDiameter, circleDiameter);
        }

        // Dibujar los círculos a los lados del cuboide
        g2d.setColor(new Color(204, 193, 62));
        for (int i = 0; i < 3; i++) {
            g2d.fillOval(circleSidePositions[i][0], circleSidePositions[i][1], circleSideDiameter, circleSideDiameter);
        }

        // Dibujar los ojos------------------------------------------------------------------------------------------------
        // Dibujar los ojos
        int eyeWidth = width / 3; // Ajustar el ancho de los ojos
        int eyeHeight = height / 4; // Ajustar la altura de los ojos
        int eyeSpacing = width / 16; // Espacio entre los ojos

        // Posiciones de los ojos
        int eye1X = (projectedVertices[0][0] + projectedVertices[3][0]) / 2 - eyeWidth - eyeSpacing / 2;
        int eye1Y = (projectedVertices[0][1] + projectedVertices[1][1]) / 2 - eyeHeight / 2;
        int eye2X = (projectedVertices[0][0] + projectedVertices[3][0]) / 2 + eyeSpacing / 2;
        int eye2Y = (projectedVertices[0][1] + projectedVertices[1][1]) / 2 - eyeHeight / 2;

        // Dibujar los ojos
        g2d.setColor(Color.WHITE);
        g2d.fillOval(eye1X, eye1Y - 20, eyeWidth, eyeHeight);
        g2d.fillOval(eye2X, eye2Y - 20, eyeWidth, eyeHeight);

        // Dibujar el borde negro de los ojos
        g2d.setColor(Color.BLACK);
        g2d.drawOval(eye1X, eye1Y - 20, eyeWidth, eyeHeight);
        g2d.drawOval(eye2X, eye2Y - 20, eyeWidth, eyeHeight);

        // Dibujar el iris azul
        g2d.setColor(Color.BLUE);
        int irisDiameter = eyeWidth / 2;
        int iris1X = eye1X + eyeWidth / 4 - irisDiameter / 2;
        int iris1Y = eye1Y + eyeHeight / 2 - irisDiameter / 2;
        int iris2X = eye2X + eyeWidth / 4 - irisDiameter / 2;
        int iris2Y = eye2Y + eyeHeight / 2 - irisDiameter / 2;

        g2d.fillOval(iris1X, iris1Y - 20, irisDiameter, irisDiameter);
        g2d.fillOval(iris2X, iris2Y - 20, irisDiameter, irisDiameter);

        // Dibujar la nariz
        int noseWidth = eyeWidth / 2; // Ancho de la nariz
        int noseHeight = eyeHeight / 2; // Altura de la nariz
        int noseX = (projectedVertices[0][0] + projectedVertices[3][0]) / 2 - noseWidth / 2; // Posición X de la nariz
        int noseY = (projectedVertices[0][1] + projectedVertices[1][1]) / 2 + eyeHeight / 2; // Posición Y de la nariz

        // Dibujar la nariz como un óvalo
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(noseX, noseY - 20, noseWidth + 40, noseHeight - 5);

        // Dibujar el contorno negro de la nariz
        g2d.setColor(Color.BLACK);
        g2d.drawOval(noseX, noseY - 20, noseWidth + 40, noseHeight - 5);

        // Dibujar la boca
        int mouthWidth = eyeWidth; // Ancho de la boca
        int mouthHeight = eyeHeight / 2; // Altura de la boca
        int mouthX = (projectedVertices[0][0] + projectedVertices[3][0]) / 2 - mouthWidth / 2; // Posición X de la boca
        int mouthY = (projectedVertices[0][1] + projectedVertices[1][1]) / 2 + eyeHeight; // Posición Y de la boca

        // Dibujar la boca con una forma más característica
        g2d.setColor(Color.red);
        g2d.fillArc(mouthX, mouthY - 40, mouthWidth, mouthHeight + 20, 180, 180);

        g2d.dispose();
    }

    public void dibujarCuboide2(Graphics g) {
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

        // Dibujar las aristas del cuboide que están en frente
        g2d.setColor(Color.BLACK);
        g2d.drawLine(projectedVertices[0][0], projectedVertices[0][1], projectedVertices[1][0], projectedVertices[1][1]);
        g2d.drawLine(projectedVertices[1][0], projectedVertices[1][1], projectedVertices[2][0], projectedVertices[2][1]);
        g2d.drawLine(projectedVertices[2][0], projectedVertices[2][1], projectedVertices[3][0], projectedVertices[3][1]);
        g2d.drawLine(projectedVertices[3][0], projectedVertices[3][1], projectedVertices[0][0], projectedVertices[0][1]);

        g2d.drawLine(projectedVertices[4][0], projectedVertices[4][1], projectedVertices[5][0], projectedVertices[5][1]);
//    g2d.drawLine(projectedVertices[5][0], projectedVertices[5][1], projectedVertices[6][0], projectedVertices[6][1]);
//    g2d.drawLine(projectedVertices[6][0], projectedVertices[6][1], projectedVertices[7][0], projectedVertices[7][1]);
//    g2d.drawLine(projectedVertices[7][0], projectedVertices[7][1], projectedVertices[4][0], projectedVertices[4][1]);

        g2d.drawLine(projectedVertices[0][0], projectedVertices[0][1], projectedVertices[4][0], projectedVertices[4][1]);
//    g2d.drawLine(projectedVertices[1][0], projectedVertices[1][1], projectedVertices[5][0], projectedVertices[5][1]);
        //g2d.drawLine(projectedVertices[2][0], projectedVertices[2][1], projectedVertices[6][0], projectedVertices[6][1]);
        //g2d.drawLine(projectedVertices[3][0], projectedVertices[3][1], projectedVertices[7][0], projectedVertices[7][1]);

        g2d.dispose();
    }

    public void manos1(Graphics g, Color q) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Proyectar las coordenadas 3D en 2D usando el vector de proyección
        int[][] projectedVertices = new int[8][2];
        for (int i = 0; i < vertices.length; i++) {
            projectedVertices[i] = proyeccioncube(vertices[i][0], vertices[i][1], vertices[i][2], Xc, Yc, Zc);
        }

        // Dibujar las manos a los lados del cuboide
        int manoWidth = 70; // Ancho de la mano
        int manoHeight = 10; // Altura de la mano
        int manoY = (projectedVertices[0][1] + projectedVertices[1][1]) / 2 - manoHeight / 2; // Posición Y de las manos

        // Posición X de la mano izquierda
        int manoX1 = projectedVertices[0][0] - manoWidth;
        // Posición X de la mano derecha
        //int manoX2 = projectedVertices[3][0];

        // Dibujar las manos como rectángulos
        g2d.setColor(q); // Color de las manos
        g2d.fillRect(manoX1 + 60, manoY - 20, manoWidth, manoHeight); // Dibujar mano izquierda
        //g2d.fillRect(manoX2, manoY - 20, manoWidth, manoHeight); // Dibujar mano derecha

        // Dibujar las manos como rectángulos con contorno negro
        g2d.setColor(Color.BLACK); // Color del contorno
        g2d.drawRect(manoX1 + 60, manoY - 20, manoWidth, manoHeight); // Contorno de la mano izquierda
        //g2d.drawRect(manoX2, manoY - 20, manoWidth, manoHeight); // Contorno de la mano derecha

        g2d.dispose();
    }

    public void manos2(Graphics g, Color q) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Proyectar las coordenadas 3D en 2D usando el vector de proyección
        int[][] projectedVertices = new int[8][2];
        for (int i = 0; i < vertices.length; i++) {
            projectedVertices[i] = proyeccioncube(vertices[i][0], vertices[i][1], vertices[i][2], Xc, Yc, Zc);
        }

        // Dibujar las manos a los lados del cuboide
        int manoWidth = 50; // Ancho de la mano
        int manoHeight = 10; // Altura de la mano
        int manoY = (projectedVertices[0][1] + projectedVertices[1][1]) / 2 - manoHeight / 2; // Posición Y de las manos

        // Posición X de la mano izquierda
        //int manoX1 = projectedVertices[0][0] - manoWidth;
        // Posición X de la mano derecha
        int manoX2 = projectedVertices[3][0];

        // Dibujar las manos como rectángulos
        g2d.setColor(q); // Color de las manos
//        g2d.fillRect(manoX1, manoY - 20, manoWidth, manoHeight); // Dibujar mano izquierda
        g2d.fillRect(manoX2 - 5, manoY - 20, manoWidth, manoHeight); // Dibujar mano derecha

        // Dibujar las manos como rectángulos con contorno negro
        g2d.setColor(Color.BLACK); // Color del contorno
//        g2d.drawRect(manoX1, manoY - 20, manoWidth, manoHeight); // Contorno de la mano izquierda
        g2d.drawRect(manoX2 - 5, manoY - 20, manoWidth, manoHeight); // Contorno de la mano derecha

        g2d.dispose();
    }

    public void piernas(Graphics g, Color q) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Proyectar las coordenadas 3D en 2D usando el vector de proyección
        int[][] projectedVertices = new int[8][2];
        for (int i = 0; i < vertices.length; i++) {
            projectedVertices[i] = proyeccioncube(vertices[i][0], vertices[i][1], vertices[i][2], Xc, Yc, Zc);
        }

        // Dibujar las piernas debajo del cuboide
        int piernaWidth = 10; // Ancho de la pierna
        int piernaHeight = 60; // Altura de la pierna
        int piernaY = projectedVertices[4][1] + 34; // Posición Y de las piernas

// Calcular una posición X intermedia
        int centroX = (projectedVertices[4][0] + projectedVertices[7][0]) / 2;

// Posición X de las piernas
        int piernaX1 = centroX - piernaWidth - 5; // Izquierda de la pierna izquierda, ajustada para que esté más cerca
        int piernaX2 = centroX + 5; // Izquierda de la pierna derecha, ajustada para que esté más cerca

// Dibujar las piernas como rectángulos
        g2d.setColor(Color.YELLOW); // Color de las piernas
        g2d.fillRect(piernaX1, piernaY, piernaWidth, piernaHeight); // Dibujar pierna izquierda
        g2d.fillRect(piernaX2, piernaY, piernaWidth, piernaHeight); // Dibujar pierna derecha

// Dibujar contorno negro de las piernas
        g2d.setColor(Color.BLACK);
        g2d.drawRect(piernaX1, piernaY, piernaWidth, piernaHeight); // Contorno de la pierna izquierda
        g2d.drawRect(piernaX2, piernaY, piernaWidth, piernaHeight); // Contorno de la pierna derecha
        
        g2d.dispose();
    }
    
public void rotarPiernasEnX(double angulo) {
    double cosAngulo = Math.cos(Math.toRadians(angulo));
    double sinAngulo = Math.sin(Math.toRadians(angulo));

    // Coordenadas de la base de las piernas
    int baseY = vertices[4][1]; // Posición Y de la base de las piernas
    int baseZ = vertices[4][2]; // Posición Z de la base de las piernas

    // Coordenadas de los puntos superiores de las piernas
    int punto1Y = vertices[5][1];
    int punto1Z = vertices[5][2];
    int punto2Y = vertices[6][1];
    int punto2Z = vertices[6][2];

    // Rotar los puntos superiores de las piernas alrededor del eje X
    vertices[5][1] = baseY + (int) ((punto1Y - baseY) * cosAngulo - (punto1Z - baseZ) * sinAngulo);
    vertices[5][2] = baseZ + (int) ((punto1Y - baseY) * sinAngulo + (punto1Z - baseZ) * cosAngulo);
    vertices[6][1] = baseY + (int) ((punto2Y - baseY) * cosAngulo - (punto2Z - baseZ) * sinAngulo);
    vertices[6][2] = baseZ + (int) ((punto2Y - baseY) * sinAngulo + (punto2Z - baseZ) * cosAngulo);
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

    public void trasladar(int dx, int dy, int dz) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i][0] += dx;
            vertices[i][1] += dy;
            vertices[i][2] += dz;
        }
    }
}