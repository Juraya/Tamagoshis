package tamagoshi.tamagoshis;

/**
 * Created by Julien on 07/10/2015.
 * Classe de Tamagoshi gros mangeur, qui hérite de Tamagoshi.
 */
public class GrosMangeur extends Tamagoshi {

    /**
     * Constructeur de la classe Tamagoshi. Le seul paramètre à donner est le nom, le reste est déjà défini
     * ou aléatoire.
     */
    public GrosMangeur() {

    }

    /**
     * Méthode de consommation de ressources spécifique au gros mangeur : il perd 2 points de vie par tour.
     * @return Renvoie un boolean. True : Il a mangé. False : Il est KO.
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
            System.out.println(this.getName() + " : Je suis KO !");
            return false;
        }
    }
}
