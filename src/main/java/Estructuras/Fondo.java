package Estructuras;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ivano
 */
public class Fondo {

    int xInicial = 400;
    int yInicial = 200;
    int iteracion = 300;
    int radio = 20;

    public void fondoboob(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Color inicial del degradado
        Color startColor = new Color(32, 82, 183, 255);
        // Color final del degradado
        Color endColor = new Color(0, 0, 128, 255);

        // Dibujar el degradado
        for (int y = 0; y < 250; y++) {
            // Calcular el valor de interpolación para obtener el color en esta posición
            float t = (float) y / 250;
            int red = (int) (startColor.getRed() + t * (endColor.getRed() - startColor.getRed()));
            int green = (int) (startColor.getGreen() + t * (endColor.getGreen() - startColor.getGreen()));
            int blue = (int) (startColor.getBlue() + t * (endColor.getBlue() - startColor.getBlue()));
            Color gradientColor = new Color(red, green, blue, 255);

            // Dibujar la línea horizontal en la posición y con el color calculado
            g2d.setColor(gradientColor);
            g2d.fillRect(0, y, 800, 1);
        }
        //arena
        g2d.setColor(new Color(218, 210, 137, 255));
        g2d.fill3DRect(0, 250, 800, 250, true);
//        g2d.setColor(new Color(32,82,183,255));
//        g2d.fill3DRect(0, 0, 800, 250, true);                
    }

    public void drawEstrella(Graphics g, int x0, int y0, int rep, int rad) {
        Graphics2D g2d = (Graphics2D) g.create();
        double tMin = 0;
        double tMax = 2 * Math.PI; // Un ciclo completo de 0 a 2pi
        double deltaT = (tMax - tMin) / rep;

        for (int i = 0; i < rep; i++) {
            double t = tMin + i * deltaT;
            double x = Math.cos(t) + 0.5 * Math.cos(7 * t) + (1.0 / 3.0) * Math.sin(17 * t);
            double y = Math.sin(t) + 0.5 * Math.sin(7 * t) + (1.0 / 3.0) * Math.cos(17 * t);
            int xCurr = x0 + (int) (x * rad);
            int yCurr = y0 - (int) (y * rad);

            if (i > 0) {
                double tPrev = tMin + (i - 1) * deltaT;
                double xPrev = Math.cos(tPrev) + 0.5 * Math.cos(7 * tPrev) + (1.0 / 3.0) * Math.sin(17 * tPrev);
                double yPrev = Math.sin(tPrev) + 0.5 * Math.sin(7 * tPrev) + (1.0 / 3.0) * Math.cos(17 * tPrev);
                int xPrevInt = x0 + (int) (xPrev * rad);
                int yPrevInt = y0 - (int) (yPrev * rad);
                g2d.setColor(new Color(208, 56, 22));
                g2d.drawLine(xPrevInt, yPrevInt, xCurr, yCurr);
            }
        }
    }

    public void montañasFondo(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Definir los colores para el degradado
        Color montañaInicio = new Color(103, 79, 91, 255);
        Color montañaFin = new Color(92, 77, 82, 255);

        // Crear un objeto GradientPaint para el degradado
        GradientPaint gradient = new GradientPaint(0, 0, montañaInicio, 0, y, montañaFin);

        // Establecer el degradado como color de relleno
        g2d.setPaint(gradient);

        // Crear un polígono para representar las montañas
        int[] xPoints = {x+100,x+315,x+305,x+290,x+270,x+260,x+275,x+275,x+250,x+220,x+245,x+240,x+200,x+190,x+200,x+170,x+160,x+150,x+150,x+170,x+150,x+140,x+140,x+130,x+110};
        int[] yPoints = {250,250,210,210,230,210,200,190,170,195,215,215,225,220,190,170,150,150,170,205,215,190,160,190,210};
        int nPoints = xPoints.length;

        // Dibujar el polígono con el degradado de color
        g2d.fillPolygon(xPoints, yPoints, nPoints);

        // Liberar los recursos gráficos
        g2d.dispose();
    }
    public void montañasFondo2(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Definir los colores para el degradado
        Color montañaInicio = new Color(103, 79, 91, 255);
        Color montañaFin = new Color(92, 77, 82, 255);

        // Crear un objeto GradientPaint para el degradado
        GradientPaint gradient = new GradientPaint(0, 0, montañaInicio, 0, y, montañaFin);

        // Establecer el degradado como color de relleno
        g2d.setPaint(gradient);

        // Crear un polígono para representar las montañas
        int[] xPoints = {x+100,x+500,x+470,x+495,x+480,x+440,x+450,x+455,x+420,x+435,x+400,x+385,x+330,x+280,x+250,x+230,x+185,x+220,x+170,x+152,x+140,x+120,x+100,x+77};
        int[] yPoints = {250,250,230,200,150,150,180,225,225,190,150,220,210,180,150,120,140,170,205,215,190,190,185,185,215};
        int nPoints = xPoints.length;

        // Dibujar el polígono con el degradado de color
        g2d.fillPolygon(xPoints, yPoints, nPoints);

        // Liberar los recursos gráficos
        g2d.dispose();
    }

    public void montañasFondo3(Graphics g, int x, int y) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Definir los colores para el degradado
        Color montañaInicio = new Color(103, 79, 91, 255);
        Color montañaFin = new Color(92, 77, 82, 255);

        // Crear un objeto GradientPaint para el degradado
        GradientPaint gradient = new GradientPaint(0, 0, montañaInicio, 0, y, montañaFin);

        // Establecer el degradado como color de relleno
        g2d.setPaint(gradient);

        // Crear un polígono para representar las montañas
        int[] xPoints = {x+100,x+315,x+360,x+350,x+360,x+350,x+330,x+330,x+300,x+300,x+305,x+300,x+270,x+240,x+190,x+200,x+210,x+170,x+160,x+140,x+130,x+110,x+70,x+80};
        int[] yPoints = {250,250,200,150,110,100,105,130,215,170,140,100,120,160,150,185,220,225,175,145,105,108,130,215};
        int nPoints = xPoints.length;

        // Dibujar el polígono con el degradado de color
        g2d.fillPolygon(xPoints, yPoints, nPoints);

        // Liberar los recursos gráficos
        g2d.dispose();
    }
    
public void lineas(Graphics g) {
    Graphics2D g2d = (Graphics2D) g.create();
    
    // Coordenadas y dimensiones de la arena
    int arenaY = 250;
    int arenaHeight = 250;
    int arenaWidth = 800;
    
    // Coordenadas y dimensiones del arco
    int arcWidth = 160;
    int arcHeight = 30; // Ajusta este valor según la altura que desees para el arco

    // Número de arcos y espacio entre ellos
    int numArcos = 5;
    int espacio = (arenaWidth - numArcos * arcWidth) / (numArcos + 1);

    // Espacio vertical entre cada línea de arcos
    int espacioLineas = 40; // Ajusta este valor según el espacio que desees entre las líneas de arcos

    g2d.setColor(new Color(255, 245, 155));

    // Dibujar múltiples líneas de arcos
    for (int y = arenaY + arenaHeight - arcHeight; y >= arenaY; y -= espacioLineas) {
        for (int i = 0; i < numArcos; i++) {
            int arcX = espacio + i * (arcWidth + espacio);
            g2d.drawArc(arcX, y, arcWidth, arcHeight, 0, 180);
        }
    }
}



}
