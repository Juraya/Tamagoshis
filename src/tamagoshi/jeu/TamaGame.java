package tamagoshi.jeu;

import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

import java.util.*;

/**
 * Created by Julien on 06/10/2015.
 * Classe définissant la partie.
 */
public class TamaGame {
    private List<Tamagoshi> tamaDepart;
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
        System.out.println("Entrez le nombre de Tamagoshis desires : ");
        String nbTama = Utilisateur.saisieClavier();
        int nbTamaInt = 0;
        if (nbTama != null) {
            nbTamaInt = Integer.parseInt(nbTama);
        }
        for (int i = 0; i < nbTamaInt; i++) {
            Random random = new Random();
            int alea = random.nextInt(2) + 1;

            if (alea == 1) {
                Tamagoshi tama = new Tamagoshi();
                this.tamaDepart.add(tama);
                this.tamaActuel.add(tama);
                System.out.println("Nom du Tamagoshi cree : " + tama.getName());
                System.out.println("Energie max : " + tama.getMaxEnergy() + " / " + "Fun max : " + tama.getFunMax());
            }

            else if (alea == 2) {
                alea = random.nextInt(2) + 1;
                if (alea == 1) {
                    GrosMangeur tama = new GrosMangeur();
                    this.tamaDepart.add(tama);
                    this.tamaActuel.add(tama);
                    System.out.println("Nom du Tamagoshi cree : " + tama.getName());
                    System.out.println("Energie max : " + tama.getMaxEnergy() + " / " + "Fun max : " + tama.getFunMax());
                }

                else {
                    GrosJoueur tama = new GrosJoueur();
                    this.tamaDepart.add(tama);
                    this.tamaActuel.add(tama);
                    System.out.println("Nom du Tamagoshi cree : " + tama.getName());
                    System.out.println("Energie max : " + tama.getMaxEnergy() + " / " + "Fun max : " + tama.getFunMax());
                }
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
                System.out.println(atamActuel.parle());

                // On consomme l'energie et on check si les PV atteignent 0.
                if (!atamActuel.consommeEnergie()) {
                    System.err.println(atamActuel.getName() + " EST KO ! NOOOOOOON !");
                    iterator.remove();
                    break;
                }
                System.out.println();
            }

            if (tamaActuel.size() == 0) {
                System.out.println("Partie finie !");
                System.out.println("Score : 0 ! Tu es nul !");
                break;
            }

            else {

            // Partie manger
                System.out.println("Nourrir quel Tamagoshi ?");

            for (Tamagoshi aTamaActuel : tamaActuel) {
                System.out.println("************************");
                System.out.println( "* " + tamaActuel.indexOf(aTamaActuel) + " " + aTamaActuel.getName() + " HP : " + aTamaActuel.getEnergy() + " / " + aTamaActuel.getMaxEnergy() + "  *" );
                System.out.println("************************");
            }

                String choixNourri = Utilisateur.saisieClavier();
                assert choixNourri != null;
                int choixNourriInt = Integer.parseInt(choixNourri);
                tamaActuel.get(choixNourriInt).mange();

            // Partie jouer
                System.out.println("Jouer avec quel Tamagoshi ?");

            for (Tamagoshi aTamaActuel : tamaActuel) {
                System.out.println("************************");
                System.out.println( "* " + tamaActuel.indexOf(aTamaActuel) + " " + aTamaActuel.getName() + " FUN : " + aTamaActuel.getFun() + " / " + aTamaActuel.getFunMax() + " *" );
                System.out.println("************************");
            }
                String choixJouer = Utilisateur.saisieClavier();
                assert choixJouer != null;
                int choixJouerInt = Integer.parseInt(choixJouer);
                tamaActuel.get(choixJouerInt).jouer();
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
            this.resultat();
        }
    }
}
