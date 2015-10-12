package tamagoshi.jeu;

/**
 * Created by Julien on 05/10/2015.
 * Classe utilisée pour lancer le jeu.
 */
public class Main {
    public static void main(String[] args) {
        // On créé une nouvelle partie.
        TamaGame jeu = new TamaGame();

        // On lance cette partie.
        jeu.play();
    }
}
