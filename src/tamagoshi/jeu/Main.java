package tamagoshi.jeu;

import tamagoshi.tamafenetre.TamaFrame;
import tamagoshi.tamagoshis.Tamagoshi;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Julien on 05/10/2015.
 * Classe utilisée pour lancer le jeu.
 */
public class Main {
    public static void main(String[] args) {
        // On créé une nouvelle partie.
        //TamaGame jeu = new TamaGame();

        /**
         * Lancement de la musique
         */
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(Main.class.getResource("/music/pokemon.wav").getFile()));
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

        Tamagoshi tamatest = new Tamagoshi();

        TamaFrame frameTest = new TamaFrame(tamatest);
        tamatest.parle();

        //tamatest.getMaFrame().getContentPane().remove(2);

        //tamatest.setEnergy(0);
        //tamatest.consommeEnergie();
        //System.out.println(tamatest.getEnergy());





        // On lance cette partie.
        //jeu.play();
    }
}
