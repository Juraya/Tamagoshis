package tamagoshi.tamagoshis;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Julien on 05/10/2015.
 * Classe Tamagoshi, permettant la cr�ation et manipulation des Tamagoshis.
 */
public class Tamagoshi {
    protected int age;
    protected int maxEnergy;
    protected int energy;
    protected int fun;
    protected int funMax;
    protected String name;
    protected static int lifeTime = 10;
    protected java.util.Random rand = new Random();
    private List<String> listNoms = Arrays.asList("Tarbek", "Bulbizarre", "Salameche", "Carapuce", "Pikachu", "Racaillou", "Mew", "Mewtwo");

    /**
     * Constructeur de la classe Tamagoshi. Le seul param�tre � donner est le nom, le reste est d�j� d�fini
     * ou al�atoire.
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
     * @return Renvoie un boolean. True : le Tamagoshi s'est amus�. False : il n'a pas besoin de s'amuser.
     */
    public boolean jouer() {
        if(this.getFun() < this.getFunMax()) {
            System.out.println(this.getName() + " : " + "C'est le putain de fun !");
            this.fun += jetDes(1,3);
            return true;
        }
        else {
            System.out.println(this.getName() + " : " + "Pas envie de jouer.");
            return false;
        }
    }

    /**
     * M�thode parler : retourne un �tat sous forme de println. Au dessus de 4 d'�nergie, le Tamagoshi est heureux.
     * Sinon, il a faim.
     * @return Renvoie un boolean. True : Il est heureux. False : Il a faim et/ou s'ennuie.
     */
    public boolean parle() {
        if (this.getEnergy() >= 5 && this.getFun() >= 5 ) {
            System.out.println(this.getName() + " : " + "Je suis heureux !");
            return true;
        }

        else if (this.getEnergy() >= 5 && this.getFun() < 5) {
            System.out.println(this.getName() + " : " + "Je n'ai pas sp�cialement faim mais je m'ennuie !");
            return false;
        }

        else if (this.getEnergy() < 5 && this.getFun() >= 5) {
            System.out.println(this.getName() + " : " + "J'ai faim mais je m'amuse bien.");
            return false;
        }

        else {
            System.out.println(this.getName() + " : " + "J'ai faim et je m'ennuie !");
            return false;
        }
    }

    /**
     * Methode manger, alimente le Tamagoshi en lui rendant de 1 � 3 points de vie, de mani�re al�atoire.
     * Si le Tamagoshi est au maximum de ses points de vie, il d�clare ne pas avoir faim.
     * @return Renvoie un boolean. True : Il accepte de manger. False : Il n'a pas faim.
     */
    public boolean mange() {
        if (this.maxEnergy > this.energy) {
            this.energy += jetDes(1,3);
            System.out.println(this.getName() + " : " + "Miam !");
            return true;
        }

        else {
            System.out.println(this.getName() + " : " + "Je n'ai pas faim.");
            return false;
        }
    }

    /**
     * Methode appell�e par la boucle de jeu. Retire un point de vie au Tamagoshi.
     * @return Renvoie un boolean. True : Il perd 1 point d'�nergie. False : Il est KO.
     */
    public boolean consommeEnergie() {
        if (this.energy > 0 && this.fun > 0) {
            this.energy = this.energy - 1;
            this.fun = this.fun - 1;
            return true;
        }

        else {
            System.out.println(this.getName() + " : Je suis KO !");
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
     * @return Renvoie l'esp�rance de vie du Tamagoshi.
     */
    protected static int getLifeTime() {
        return lifeTime;
    }

    /**
     * Permet l'�dition de l'age du Tamagoshi.
     * @param age Prend un entier en param�tre pour red�finir l'age du Tamagoshi.
     */
    protected void setAge(int age) {
        this.age = age;
    }

    /**
     * Permet l'�dition de l'energie maximale du Tamagoshi.
     * @param maxEnergy Prend un entier en param�tre pour red�finir l'�nergie maximale du Tamagoshi.
     */
    protected void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    /**
     * Permet l'�dition de l'energie du Tamagoshi.
     * @param energy Prend un entier en param�tre pour red�finir l'�nergie du Tamagoshi.
     */
    protected void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * Permet l'�dition du nom du Tamagoshi
     * @param name Prend un string en param�tre pour red�finir le nom du Tamagoshi.
     */
    protected void setName(String name) {
        this.name = name;
    }

    /**
     * Permet l'�dition de l'esp�rance de vie du Tamagoshi
     * @param lifeTime Prend un entier en param�tre
     */
    protected static void setLifeTime(int lifeTime) {
        Tamagoshi.lifeTime = lifeTime;
    }

    /**
     * Lance un d�, avec une limite minimale et maximale sous forme d'entiers. Retourne un entier al�atoire situ� entre
     * les limites entr�es en param�tre.
     * @param min Valeur minimale al�atoire.
     * @param max Valeur maximale al�atoire.
     * @return Nombre al�atoire renvoy�.
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
     * @param fun Prend un entier en param�tre pour red�finir le fun du Tamagoshi.
     */
    protected void setFun(int fun) {
        this.fun = fun;
    }

    /**
     * @return Renvoie le fun maximum du Tamagoshi.
     */
    public int getFunMax() {
        return funMax;
    }

    /**
     * @param funMax Prend un entier en param�tre pour red�finir le fun maximum du Tamagoshi.
     */
    protected void setFunMax(int funMax) {
        this.funMax = funMax;
    }
}
