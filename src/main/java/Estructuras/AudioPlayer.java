package Estructuras;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioPlayer {

    private Clip audioClip;

    public void load(String audioFilePath) {
        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        if (audioClip != null) {
            audioClip.start();
        }
    }

    public void stop() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
        }
    }

    public void close() {
        if (audioClip != null) {
            audioClip.close();
        }
    }

//    public static void main(String[] args) {
//        AudioPlayer player = new AudioPlayer();
//        player.load("src/boob.wav"); // Ruta al archivo de audio en el classpath
//        player.play();
//
//        // Mantener el programa en ejecución para que el audio pueda reproducirse completamente
//        try {
//            Thread.sleep(100000); // Ajusta esto a la duración de tu archivo de audio
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        player.close();
//    }
}
