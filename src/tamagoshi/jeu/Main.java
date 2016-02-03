package tamagoshi.jeu;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import tamagoshi.tamafenetre.TamaFrame;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Created by Julien on 05/10/2015.
 * Classe utilisée pour lancer le jeu.
 */
public class Main {
    public static void main(String[] args) {
        // On créé une nouvelle partie.
        //TamaGame jeu = new TamaGame();

        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(Main.class.getResource("../../music/pokemon.wav").getFile()));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        new TamaFrame();


        // On lance cette partie.
        //jeu.play();
    }
}
