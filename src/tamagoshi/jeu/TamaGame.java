package tamagoshi.jeu;

import tamagoshi.tamafenetre.TamaFrame;
import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;
import javax.swing.*;
import java.util.*;

/**
 * Created by Julien on 06/10/2015.
 * Classe définissant la partie.
 */
public class TamaGame {
    /**
     * Tableau de tamagoshis initial, servant de comparaison
     */
    private List<Tamagoshi> tamaDepart;
    /**
     * Tableau de tamagoshis actuel, servant à être édité
     */
    private List<Tamagoshi> tamaActuel;

    /**
     * Méthode qui affiche le résultat du jeu.
     */
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

    /**
     * Propose la liste des Tamagoshis pour jouer
     */
    public void afficherFun() {
        System.out.println("Jouer avec quel Tamagoshi ?");
        for (Tamagoshi aTamaActuel : tamaActuel) {
            System.out.println("************************");
            System.out.println( "* " + tamaActuel.indexOf(aTamaActuel) + " " + aTamaActuel.getName() + " FUN : " + aTamaActuel.getFun() + " / " + aTamaActuel.getFunMax() + " *" );
            System.out.println("************************");
        }
    }

    /**
     * Propose la liste des Tamagoshis pour manger
     */
    public void afficherFaim() {
        System.out.println("Nourrir quel Tamagoshi ?");
        for (Tamagoshi aTamaActuel : tamaActuel) {
            System.out.println("************************");
            System.out.println( "* " + tamaActuel.indexOf(aTamaActuel) + " " + aTamaActuel.getName() + " HP : " + aTamaActuel.getEnergy() + " / " + aTamaActuel.getMaxEnergy() + "  *" );
            System.out.println("************************");
        }
    }

    /**
     * Constructeur de la partie : "Tamagame".
     * Convertit les listes de Tamagoshis en Arraylist, afin de mieux les manipuler.
     */
    public TamaGame() {
        this.tamaDepart = new ArrayList<>();
        this.tamaActuel = new ArrayList<>();
        initialisation();
    }

    /**
     * Méthode d'initialisation appellée en début de partie.
     * Définit par l'utilisateur le nombre de Tamagoshis à gérer, ainsi que leur nom.
     * Affiche ensuite le nom de chaque Tamagoshi créé, ainsi que son énergie maximum.
     * Enfin affiche que la création de Tamagoshis est terminée.
     */
    public void initialisation() {
        String[] choix = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        String difficulte = (String) JOptionPane.showInputDialog(null, "Choisissez la difficulté :", "Nombre de Tamagoshis", JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
        int diffNb = Integer.parseInt(difficulte);
        TamaGameGraphic partie = new TamaGameGraphic();
        ArrayList<TamaFrame> tamaTab = new ArrayList<>();

        int h = 0;
        int l = 0;
        int compteur = 0;
        for (int i=0; i<diffNb; i++){
            if (diffNb<=5) {
                Tamagoshi tamatest = new Tamagoshi();
                TamaFrame frameTest = new TamaFrame(tamatest);
                frameTest.setLocation(h, l);
                h = h + 405;

                tamatest.parle();
                tamaTab.add(frameTest);
            }

            else {
                if (compteur==5) {
                    l = l+405;
                    h = 0;
                }
                Tamagoshi tamatest = new Tamagoshi();
                TamaFrame frameTest = new TamaFrame(tamatest);
                frameTest.setLocation(h, l);
                h = h + 405;
                compteur+=1;

                tamatest.parle();
                tamaTab.add(frameTest);
            }
        }


        System.out.println("Creation des Tamagoshis terminee !");
    }

    /**
     * Boucle de jeu sous forme de méthode.
     * Se fait en 10 tours, avec au début de chaque tour un affichage du numéro de tour en cours.
     * Parcourt la collection de Tamagoshis avec un iterator, affichant leur nom et état, consomme l'énergie du tour,
     * et si la consommation d'énergie achève un Tamagoshi, le retire de la collection.
     */
    public void play() {
        for (int i = 0; i < 10; i++) {
            System.out.println("");
            System.out.println("**********"+"CYCLE NUMERO "+i+"**********");
            System.out.println("");

            // On cree l'iterator pour parcourir la collection de Tamagoshis.
            Iterator<Tamagoshi> iterator = tamaActuel.iterator();
            while (iterator.hasNext()) {
                Tamagoshi atamActuel = iterator.next();
                atamActuel.consommeEnergie();
                if ( (atamActuel.getFun() <= 0) || (atamActuel.getEnergy() <= 0) ) {
                    System.err.println(atamActuel.getName() + " EST KO ! NOOOOOOON !");
                    iterator.remove();
                    break;
                }
                System.out.println(atamActuel.parle());
            }

            if (tamaActuel.size() == 0) {
                System.out.println("Partie finie !");
                System.out.println("Score : 0 ! Tu es nul !");
                break;
            }

            else {

            // Partie manger
                afficherFaim();
                String choix = Utilisateur.saisieClavier();
                assert choix != null;
                int choixInt = Integer.parseInt(choix);
                tamaActuel.get(choixInt).mange();

            // Partie jouer
                afficherFun();
                choix = Utilisateur.saisieClavier();
                assert choix != null;
                choixInt = Integer.parseInt(choix);
                tamaActuel.get(choixInt).jouer();
            }
        }
        /**
         * En fin d'algo, si la collection de Tamagoshis n'est pas vide, affiche le score final définit par le nombre de
         * vivants divisé par le nombre de Tamagoshis au total x 100.
         */
        if (tamaActuel.size() != 0) {

            // Fin du jeu
            System.out.println("Le jeu est termine !");

            // On lance la méthode d'annonce du résultat
            resultat();
        }
    }
}
