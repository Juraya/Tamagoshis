package tamagoshi.tamafenetre;

import javax.swing.*;

/**
 * Created by Julien on 27/01/2016.
 */
public class Fenetre {

    public Fenetre() {

        JFrame fenetre = new JFrame();
        fenetre.setSize(400, 400);
        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }
}
