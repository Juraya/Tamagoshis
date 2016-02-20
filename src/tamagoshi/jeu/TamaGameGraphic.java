package tamagoshi.jeu;

import tamagoshi.tamafenetre.TamaFrame;
import tamagoshi.tamafenetre.TamaMenu;
import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by Julien on 17/02/2016.
 */
public class TamaGameGraphic {
    private int numeroTour;
    /**
     * Tableau de tamagoshis initial, servant de comparaison
     */
    public static List<Tamagoshi> tamaDepart;
    /**
     * Tableau de tamagoshis actuel, servant à être édité
     */
    public static List<Tamagoshi> tamaActuel;

    public void resultat() {
        // Recapitulatif des types de Tamagoshis
        for (Tamagoshi aTamaDepart : tamaDepart) {
            if (aTamaDepart instanceof GrosJoueur) {
                System.out.println(aTamaDepart.getName() + " : " + "Ce Tamagoshi etait un gros joueur !");
            }
            else if (aTamaDepart instanceof  GrosMangeur) {
                System.out.println(aTamaDepart.getName() + " : " + "Ce Tamagoshi etait un gros mangeur !");
            }
        }

        // Calcul du score
        double nbVivants = tamaActuel.size();
        double nbTotal = tamaDepart.size();
        double score = nbVivants / nbTotal * 100;

        // Affichage du score
        System.out.println("***** " + "Score : " + score + " % !" + " *****");
        System.out.println("Difficulte : "+tamaDepart.size());
    }

    public TamaGameGraphic() {
        tamaDepart = new ArrayList<>();
        tamaActuel = new ArrayList<>();
        initialisation();
    }

    public void initialisation() {
        // Création de la fenêtre pour choisir la difficulté
        System.err.println("Initialisation de la partie.");
        String[] choix = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String difficulte = (String) JOptionPane.showInputDialog(null, "Choisissez la difficulté :", "Nombre de Tamagoshis", JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
        int diffNb = Integer.parseInt(difficulte);

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

        ArrayList<TamaFrame> tamaTab = new ArrayList<>();

        int h = 0;
        int l = 0;
        int compteur = 0;
        for (int i=0; i<diffNb; i++){
            if (diffNb<=5) {
                Tamagoshi tamatest = new Tamagoshi();
                if (TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    tamatest.setName(tamatest.getListNoms().get(i));
                }
                else if (!TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    String nomVoulu = JOptionPane.showInputDialog(null, "Choisissez le nom du Tamagoshi :", "Nom du Tamagoshi", JOptionPane.QUESTION_MESSAGE);
                    tamatest.setName(nomVoulu);
                }
                    TamaFrame frameTest = new TamaFrame(tamatest);
                    frameTest.setLocation(h, l);
                    h = h + 390;

                    tamatest.parle();
                    tamaTab.add(frameTest);
            }

            else {
                if (compteur==5) {
                    l = l + 400;
                    h = 0;
                }
                Tamagoshi tamatest = new Tamagoshi();
                if (TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    tamatest.setName(tamatest.getListNoms().get(i));
                }
                else if (!TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    String nomVoulu = JOptionPane.showInputDialog(null, "Choisissez le nom du Tamagoshi :", "Nom du Tamagoshi", JOptionPane.QUESTION_MESSAGE);
                    tamatest.setName(nomVoulu);
                }
                    TamaFrame frameTest = new TamaFrame(tamatest);
                    frameTest.setLocation(h, l);
                    h = h + 390;
                    compteur += 1;

                    tamatest.parle();
                    tamaTab.add(frameTest);
            }

            for (TamaFrame tama : tamaTab) {
                tama.getJouer().addActionListener(e -> {
                    if (tama.getNourrir().isEnabled()) {
                        tama.getMonTama().jouer();
                        for (TamaFrame t : tamaTab) {
                            t.getJouer().setEnabled(false);
                        }
                    }

                    else {
                        for (TamaFrame t : tamaTab) {
                            t.getJouer().setEnabled(true);
                            t.getNourrir().setEnabled(true);
                        }
                        //this.numeroTour+=1;
                        //System.err.println(this.numeroTour);

                    }
                });
                tama.getNourrir().addActionListener(e -> {
                    if (tama.getJouer().isEnabled()) {
                        tama.getMonTama().mange();
                        for (TamaFrame t : tamaTab) {
                            t.getNourrir().setEnabled(false);
                        }
                    } else {
                        for (TamaFrame t : tamaTab) {
                            t.getJouer().setEnabled(true);
                            t.getNourrir().setEnabled(true);
                        }
                        this.numeroTour += 1;
                        System.err.println(this.numeroTour);

                        for (TamaFrame ta : tamaTab) {
                            if (ta.getMonTama().getEnergy() > 0 && ta.getMonTama().getFun() > 0) {
                                ta.getMonTama().consommeEnergie();
                                ta.getMonTama().parle();
                                System.err.println("Nom : " + ta.getMonTama().getName() + " / Energie : " + ta.getMonTama().getEnergy()
                                + " / Fun : " + ta.getMonTama().getFun());
                            }
                        }

                    }
                });
            }

            this.play();
        }
    }

    public void play() {
        System.err.println("Lancement de la partie.");

    }


}
