package tamagoshi.jeu;

import tamagoshi.tamagoshis.GrosJoueur;
import tamagoshi.tamagoshis.GrosMangeur;
import tamagoshi.tamagoshis.Tamagoshi;
import tamagoshi.util.Utilisateur;

import java.util.*;

/**
 * Created by Julien on 17/02/2016.
 */
public class TamaGameGraphic {
    /**
     * Tableau de tamagoshis initial, servant de comparaison
     */
    private List<Tamagoshi> tamaDepart;
    /**
     * Tableau de tamagoshis actuel, servant à être édité
     */
    private List<Tamagoshi> tamaActuel;

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
        this.tamaDepart = new ArrayList<>();
        this.tamaActuel = new ArrayList<>();
        initialisation();
    }

    public void initialisation() {
        System.err.println("Initialisation de la partie.");

    }

    public void play() {
        System.err.println("Lancement de la partie.");
    }


}
