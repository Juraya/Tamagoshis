package tamagoshi.tamagoshis;

import tamagoshi.tamafenetre.TamaFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Julien on 05/10/2015.
 * Classe Tamagoshi, permettant la création et manipulation des Tamagoshis.
 */
public class Tamagoshi {
    /**
     * Age du Tamagoshi
     */
    protected int age;
    /**
     * Etat actuel du Tamagoshi
     */
    protected String etat = "J'arrive !";
    /**
     * Energie maximale du Tamagoshi
     */
    protected int maxEnergy;
    /**
     * Energie actuelle du Tamagoshi
     */
    protected int energy;
    /**
     * Amusement actuel du Tamagoshi
     */
    protected int fun;
    /**
     * Amusement maximal du Tamagoshi
     */
    protected int funMax;
    /**
     * Nom du Tamagoshi
     */
    protected String name;
    /**
     * Temps de vie du Tamagoshi
     */
    protected static int lifeTime = 10;
    /**
     * Variable contenant la génération d'aléatoire
     */
    protected java.util.Random rand = new Random();
    /**
     * Référence à la frame du Tamagoshi
     */
    protected TamaFrame maFrame;
    /**
     * Liste de noms destinée à la création aléatoire
     */
    private List<String> listNoms = Arrays.asList("Tarbek", "Bulbizarre", "Salameche", "Carapuce", "Pikachu", "Racaillou", "Mew", "Mewtwo");

    /**
     * Constructeur de la classe Tamagoshi. Le seul paramètre à donner est le nom, le reste est déjà défini
     * ou aléatoire.
     */
    public Tamagoshi() {
        this.name = pickName();
        this.age = 0;
        this.maxEnergy = jetDes(5,9);
        this.energy = jetDes(3,7);
        this.funMax = jetDes(5,9);
        this.fun = jetDes(3,7);

    }

    public String pickName() {
        return listNoms.get(jetDes(0,7));
    }

    /**
     *
     * @return Renvoie un boolean. True : le Tamagoshi s'est amusé. False : il n'a pas besoin de s'amuser.
     */
    public boolean jouer() {
        if(this.getFun() < this.getFunMax()) {
            this.getMaFrame().getBulle().setText(this.etat="Je m'amuse trop !");
            this.getMaFrame().getContentPane().repaint();
            this.fun += jetDes(1,3);
            return true;
        }
        else {
            this.getMaFrame().getBulle().setText(this.etat="Pas envie de jouer.");
            this.getMaFrame().getContentPane().repaint();
            return false;
        }
    }

    /**
     * Méthode parler : retourne un état sous forme de println. Au dessus de 4 d'énergie, le Tamagoshi est heureux.
     * Sinon, il a faim.
     * @return Renvoie un boolean. True : Il est heureux. False : Il a faim et/ou s'ennuie.
     */
    public boolean parle() {
        if (this.getEnergy() >= 5 && this.getFun() >= 5 ) {
            this.getMaFrame().getBulle().setText(this.etat="Je suis heureux !");
            this.getMaFrame().getEtat().removeAll();
            this.getMaFrame().getEtat().add(new JLabel(this.getMaFrame().getIconeYoupi()));
            //this.getMaFrame().add(new JLabel(this.getMaFrame().getIconeYoupi()));
            this.getMaFrame().repaint();
            return true;
        }

        else if (this.getEnergy() >= 5 && this.getFun() < 5) {
            this.getMaFrame().getBulle().setText(this.etat="J'ai pas faim mais je m'ennuie !");
            //this.getMaFrame().add(new JLabel(this.getMaFrame().getIconeEnnui()));
            this.getMaFrame().getEtat().removeAll();
            this.getMaFrame().getEtat().add(new JLabel(this.getMaFrame().getIconeEnnui()));
            this.getMaFrame().repaint();
            return false;
        }

        else if (this.getEnergy() < 5 && this.getFun() >= 5) {
            this.getMaFrame().getBulle().setText(this.etat="J'ai faim mais je m'amuse bien.");
            //this.getMaFrame().add(new JLabel(this.getMaFrame().getIconeAttention()));
            this.getMaFrame().getEtat().removeAll();
            this.getMaFrame().getEtat().add(new JLabel(this.getMaFrame().getIconeAttention()));
            this.getMaFrame().repaint();
            return false;
        }

        else {
            this.getMaFrame().getBulle().setText(this.etat="J'ai faim et je m'ennuie !");
            //this.getMaFrame().add(new JLabel(this.getMaFrame().getIconeFaim()));
            this.getMaFrame().getEtat().removeAll();
            this.getMaFrame().getEtat().add(new JLabel(this.getMaFrame().getIconeFaim()));
            this.getMaFrame().repaint();
            return false;
        }
    }

    /**
     * Methode manger, alimente le Tamagoshi en lui rendant de 1 à 3 points de vie, de manière aléatoire.
     * Si le Tamagoshi est au maximum de ses points de vie, il déclare ne pas avoir faim.
     * @return Renvoie un boolean. True : Il accepte de manger. False : Il n'a pas faim.
     */
    public boolean mange() {
        //System.err.println("CLICK MANGER");
        //System.err.println(this.getEnergy());
        if (this.maxEnergy > this.energy) {
            this.energy += jetDes(1,3);
            this.getMaFrame().getBulle().setText(this.etat="Miam !");
            this.getMaFrame().getContentPane().repaint();
            return true;
        }

        else {
            this.getMaFrame().getBulle().setText(this.etat="Je n'ai pas faim.");
            this.getMaFrame().getContentPane().repaint();
            return false;
        }
    }

    /**
     * Methode appellée par la boucle de jeu. Retire un point de vie au Tamagoshi.
     * @return Renvoie un boolean. True : Il perd 1 point d'énergie. False : Il est KO.
     */
    public boolean consommeEnergie() {
        if (this.energy > 0 && this.fun > 0) {
            this.energy = this.energy - 1;
            this.fun = this.fun - 1;
            return true;
        }

        else {
            this.getMaFrame().getBulle().setText(this.etat="Je suis KO !");
            //this.getMaFrame().add(new JLabel(this.getMaFrame().getIconeKO()));
            this.getMaFrame().getEtat().removeAll();
            this.getMaFrame().getEtat().add(new JLabel(this.getMaFrame().getIconeKO()));
            this.getMaFrame().getJouer().setEnabled(false);
            this.getMaFrame().getNourrir().setEnabled(false);
            this.getMaFrame().getContentPane().repaint();
            return false;
        }
    }

    /**
     * Retourne l'age du Tamagoshi.
     * @return Renvoie un int contenant l'age.
     */
    protected int getAge() {
        return age;
    }

    /**
     * @return Retourne l'energie (points de vie) maximum du Tamagoshi.
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * @return Retourne l'energie (points de vie) actuelle du Tamagoshi.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @return Retourne le nom du Tamagoshi.
     */
    public String getName() {
        return name;
    }

    /**
     * @return Renvoie l'espérance de vie du Tamagoshi.
     */
    protected static int getLifeTime() {
        return lifeTime;
    }

    /**
     * Permet l'édition de l'age du Tamagoshi.
     * @param age Prend un entier en paramètre pour redéfinir l'age du Tamagoshi.
     */
    protected void setAge(int age) {
        this.age = age;
    }

    /**
     * Permet l'édition de l'energie maximale du Tamagoshi.
     * @param maxEnergy Prend un entier en paramètre pour redéfinir l'énergie maximale du Tamagoshi.
     */
    protected void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    /**
     * Permet l'édition de l'energie du Tamagoshi.
     * @param energy Prend un entier en paramètre pour redéfinir l'énergie du Tamagoshi.
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Permet l'édition du nom du Tamagoshi
     * @param name Prend un string en paramètre pour redéfinir le nom du Tamagoshi.
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Permet l'édition de l'espérance de vie du Tamagoshi
     * @param lifeTime Prend un entier en paramètre
     */
    protected static void setLifeTime(int lifeTime) {
        Tamagoshi.lifeTime = lifeTime;
    }

    /**
     * Lance un dé, avec une limite minimale et maximale sous forme d'entiers. Retourne un entier aléatoire situé entre
     * les limites entrées en paramètre.
     * @param min Valeur minimale aléatoire.
     * @param max Valeur maximale aléatoire.
     * @return Nombre aléatoire renvoyé.
     */
    public int jetDes(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    /**
     * @return Renvoie le fun actuel du Tamagoshi.
     */
    public int getFun() {
        return fun;
    }

    /**
     * @param fun Prend un entier en paramètre pour redéfinir le fun du Tamagoshi.
     */
    public void setFun(int fun) {
        this.fun = fun;
    }

    /**
     * @return Renvoie le fun maximum du Tamagoshi.
     */
    public int getFunMax() {
        return funMax;
    }

    /**
     * @param funMax Prend un entier en paramètre pour redéfinir le fun maximum du Tamagoshi.
     */
    protected void setFunMax(int funMax) {
        this.funMax = funMax;
    }

    public TamaFrame getMaFrame() {
        return maFrame;
    }

    public void setMaFrame(TamaFrame maFrame) {
        this.maFrame = maFrame;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
