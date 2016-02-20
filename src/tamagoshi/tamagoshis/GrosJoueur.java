package tamagoshi.tamagoshis;

/**
 * Created by Julien on 07/10/2015.
 * Classe de Tamagoshi gros joueur, qui h�rite de Tamagoshi.
 */
public class GrosJoueur extends Tamagoshi {

    /**
     * Constructeur de la classe Tamagoshi. Le seul param�tre � donner est le nom, le reste est d�j� d�fini
     * ou al�atoire.
     */
    public GrosJoueur() {

    }

    /**
     * M�thode de consommation de ressources sp�cifique au gros joueur : il perd 2 points de fun par tour.
     * @return Renvoie un boolean. True : Il a mang�. False : Il est KO.
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
            //System.out.println(this.getName() + " : Je suis KO !");
            return false;
        }
    }
}
