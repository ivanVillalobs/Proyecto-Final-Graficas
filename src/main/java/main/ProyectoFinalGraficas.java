package main;

import Estructuras.AudioPlayer;
import panel.Panel;

/**
 *
 * @author ivano
 */
public class ProyectoFinalGraficas {

    public static void main(String[] args) {
        Panel panel = new Panel();
        panel.componentes();
        
        AudioPlayer player = new AudioPlayer();
        player.load("src/boob.wav"); // Ruta al archivo de audio en el classpath
        player.play();

        // Mantener el programa en ejecución para que el audio pueda reproducirse completamente
        try {
            Thread.sleep(100000); // Ajusta esto a la duración de tu archivo de audio
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        player.close();    
    }
}
