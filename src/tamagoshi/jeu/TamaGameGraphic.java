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
    private Clip clip;
    private int numeroTour;
    /**
     * Tableau de tamagoshis initial, servant de comparaison
     */
    public static List<Tamagoshi> tamaDepart;
    /**
     * Tableau de tamagoshis actuel, servant à être édité
     */
    public static List<Tamagoshi> tamaActuel;
    /**
     * Le tableau de toutes les frames de jeu
     */
    private ArrayList<TamaFrame> tamaTab;

    /**
     * Méthode appelée quand le jeu doit se terminer
     */
    public void resultat() {
        for (TamaFrame t : tamaTab) {
            t.dispose();
        }

        this.clip.stop();

        StringBuilder messageFin = new StringBuilder();

        // Recapitulatif des types de Tamagoshis
        for (Tamagoshi aTamaDepart : tamaDepart) {
            if (aTamaDepart instanceof GrosJoueur) {
                messageFin.append(aTamaDepart.getName()).append(" : ").append("Ce Tamagoshi etait un gros joueur !");
                messageFin.append("\n");
            }
            else if (aTamaDepart instanceof GrosMangeur) {
                messageFin.append(aTamaDepart.getName()).append(" : ").append("Ce Tamagoshi etait un gros mangeur !");
                messageFin.append("\n");
            }
            else {

                messageFin.append(aTamaDepart.getName()).append(" : ").append("Ce Tamagoshi etait normal !");
                messageFin.append("\n");
            }
        }

        // Calcul du score
        double nbVivants = tamaActuel.size();
        double nbTotal = tamaDepart.size();
        double score = nbVivants / nbTotal * 100;

        messageFin.append("Score final : ").append(score).append(" % !").append("\n").append("\n");
        messageFin.append("Difficulté : ").append(tamaDepart.size());

        JOptionPane.showMessageDialog(
                null,
                messageFin,
                "Fin du jeu",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public TamaGameGraphic() {
        tamaDepart = new ArrayList<>();
        tamaActuel = new ArrayList<>();
        initialisation();
    }

    /**
     * Méthode permettant une création aléatoire de Tamagoshis
     * @return Renvoie un Tamagoshi de type aléatoire.
     */
    public Tamagoshi getRandomTama(){
        Random creationAlea = new Random();
        int randomInt = creationAlea.nextInt(3);
        switch(randomInt) {
            case 0: return new Tamagoshi();
            case 1: return new GrosMangeur();
            case 2: return new GrosJoueur();
        }
        return null;
    }

    /**
     * Construction de tous les éléments de jeu
     */
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
        this.clip = null;
        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        if (this.clip != null) {
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        tamaTab = new ArrayList<>();

        int h = 0;
        int l = 0;
        int compteur = 0;
        for (int i=0; i<diffNb; i++){
            if (diffNb<=5) {
                Tamagoshi t = this.getRandomTama();
                System.err.println(t.getClass());
                tamaActuel.add(t);
                tamaDepart.add(t);
                if (TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    t.setName(t.getListNoms().get(i));
                }
                else if (!TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    String nomVoulu = JOptionPane.showInputDialog(null, "Choisissez le nom du Tamagoshi :", "Nom du Tamagoshi", JOptionPane.QUESTION_MESSAGE);
                    t.setName(nomVoulu);
                }
                    TamaFrame frameTest = new TamaFrame(t);
                    frameTest.setLocation(h, l);
                    h = h + 390;

                    t.parle();
                    tamaTab.add(frameTest);
            }

            else {
                if (compteur==5) {
                    l = l + 400;
                    h = 0;
                }
                Tamagoshi t = this.getRandomTama();
                System.err.println(t.getClass());
                tamaActuel.add(t);
                tamaDepart.add(t);
                if (TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    t.setName(t.getListNoms().get(i));
                }
                else if (!TamaMenu.getMenuActuel().getNomsAuto().getState()) {
                    String nomVoulu = JOptionPane.showInputDialog(null, "Choisissez le nom du Tamagoshi :", "Nom du Tamagoshi", JOptionPane.QUESTION_MESSAGE);
                    t.setName(nomVoulu);
                }
                    TamaFrame frameTest = new TamaFrame(t);
                    frameTest.setLocation(h, l);
                    h = h + 390;
                    compteur += 1;

                    t.parle();
                    tamaTab.add(frameTest);
            }

            for (TamaFrame tama : tamaTab) {
                tama.getJouer().addActionListener(e -> {
                    if (tama.getNourrir().isEnabled()) {
                        tama.getMonTama().jouer();
                        for (TamaFrame t : tamaTab) {
                            t.getJouer().setEnabled(false);
                            t.getMonTama().parle();
                        }
                    }

                    else {
                        for (TamaFrame t : tamaTab) {
                            t.getJouer().setEnabled(true);
                            t.getNourrir().setEnabled(true);
                            t.getMonTama().parle();
                        }
                    }
                });
                tama.getNourrir().addActionListener(e -> {
                    if (this.numeroTour == 10) {
                        this.resultat();
                    }
                    else if (tama.getJouer().isEnabled()) {
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

        }
    }
}
