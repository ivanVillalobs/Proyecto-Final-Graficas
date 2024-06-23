package Estructuras;

import java.awt.Color;
import java.awt.Graphics;
import metodos.Circulo;
import metodos.Cono;
import metodos.Esfera;
import metodos.MedioOvalo;
import metodos.Ovalo;
import metodos.Ovoide;
import metodos.RectanguloBrazo;
import panel.Panel;

/**
 *
 * @author ivano
 */

//este metodo esta diseñado para dibujar al noot noot
public class NootNoot {

    Graphics g;

    public NootNoot(Graphics g) {
        this.g = g;
    }

    public void pingu(int x, int y) {
        Panel panel = new Panel();

//        Esfera esfera = new Esfera();
//        esfera.dibujarEsfera3D(g, centerX, centerY, 20, Color.BLACK);
        Ovalo ovalo = new Ovalo();
        Ovoide ovoide = new Ovoide();
        MedioOvalo MV = new MedioOvalo();

        ovoide.dibujarOvoide(g, x, y, 170, 550, new Color(48, 45, 45));//cuerpo
        double rotationAngle1 = Math.toRadians(180); // Rotar 45 grados
        MV.dibujarMedioOvalo(g, x, y, 170, 277, new Color(238, 229, 229), rotationAngle1);//panza blanca

        double rotationAngleX = Math.toRadians(75); // Rotar 30 grados
        ovalo.dibujarOvoide3D(g, x+30, y-150, 100, 150, new Color(48, 45, 45), rotationAngleX);//cabeza

        Cono cono = new Cono();
        double rotationAngle2 = Math.toRadians(250); // Rotar 45 grados
        cono.dibujarCono(g, x+230, y-230, 30, 80, new Color(231, 81, 81), rotationAngle2);//pico
        //cono.dibujarCono(g, 415, 70, 30, 80, new Color(231, 81, 81), rotationAngle2);//pico

        Circulo circulo = new Circulo();
        circulo.dibujarCirculo(g, x+30, y-160, 15, Color.white); //ojos
        circulo.dibujarCirculo(g, x+34, y-160, 8, Color.black); // ojos

        //brazos
        RectanguloBrazo rectangulo = new RectanguloBrazo();
        double angle1 = 15; // Ángulo de rotación en grados
        rectangulo.dibujarRectanguloRotado(g, x-40, y-30, 45, 125, angle1, /*new Color(48, 45, 45)*/Color.black); // Dibujar un rectángulo azul rotado
//        rectangulo.dibujarRectangulo(g, 160, 270, 45, 125, angle1, Color.black); // Dibujar un rectángulo azul rotado
        
        double angle2 = 165; // Ángulo de rotación en grados
        rectangulo.dibujarRectanguloRotado(g, x-40, y+80, 45, 125, angle2, /*new Color(48, 45, 45)*/Color.black); // Dibujar un rectángulo azul rotado
//        rectangulo.dibujarRectangulo(g, 160, 380, 45, 125, angle2, Color.black); // Dibujar un rectángulo azul rotado
    }
}
