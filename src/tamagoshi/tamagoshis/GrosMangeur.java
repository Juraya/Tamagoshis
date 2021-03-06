package tamagoshi.tamagoshis;

import tamagoshi.jeu.TamaGameGraphic;

import javax.swing.*;

/**
 * Created by Julien on 07/10/2015.
 * Classe de Tamagoshi gros mangeur, qui h�rite de Tamagoshi.
 */
public class GrosMangeur extends Tamagoshi {

    /**
     * Constructeur de la classe Tamagoshi. Le seul param�tre � donner est le nom, le reste est d�j� d�fini
     * ou al�atoire.
     */
    public GrosMangeur(){}

    /**
     * M�thode de consommation de ressources sp�cifique au gros mangeur : il perd 2 points de vie par tour.
     * @return Renvoie un boolean. True : Il a mang�. False : Il est KO.
     */
    @Override
    public boolean consommeEnergie() {
        if (this.energy > 0 && this.fun > 0) {
            this.energy = this.energy - 2;
            this.fun = this.fun - 1;
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
