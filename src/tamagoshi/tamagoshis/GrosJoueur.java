package tamagoshi.tamagoshis;

import tamagoshi.jeu.TamaGameGraphic;

import javax.swing.*;

/**
 * Created by Julien on 07/10/2015.
 * Classe de Tamagoshi gros joueur, qui hérite de Tamagoshi.
 */
public class GrosJoueur extends Tamagoshi {

    /**
     * Constructeur de la classe Tamagoshi. Le seul paramètre à donner est le nom, le reste est déjà défini
     * ou aléatoire.
     */
    public GrosJoueur(){}

    /**
     * Méthode de consommation de ressources spécifique au gros joueur : il perd 2 points de fun par tour.
     * @return Renvoie un boolean. True : Il a mangé. False : Il est KO.
     */
    @Override
    public boolean consommeEnergie() {
        if (this.energy > 0 && this.fun > 0) {
            this.energy = this.energy - 1;
            this.fun = this.fun - 2;
            this.getMaFrame().getContentPane().repaint();
            return true;
        }

        else {
            this.getMaFrame().getBulle().setText(this.etat="Je suis KO !");
            this.getMaFrame().getEtat().removeAll();
            this.getMaFrame().getEtat().add(new JLabel(this.getMaFrame().getIconeKO()));
            this.getMaFrame().remove(this.getMaFrame().getBoutons());
            this.getMaFrame().getJouer().setEnabled(false);
            this.getMaFrame().getNourrir().setEnabled(false);
            this.getMaFrame().getContentPane().repaint();
            return false;
        }
    }
}
