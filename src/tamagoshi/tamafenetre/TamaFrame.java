package tamagoshi.tamafenetre;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Julien on 27/01/2016.
 */
public class TamaFrame {

    public TamaFrame() {

        JFrame fenetre = new JFrame();
        fenetre.setSize(400, 400);
        ImageIcon icone = new ImageIcon(new ImageIcon(getClass().getResource("../../img/hungry.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
        ImageIcon bulleImg = new ImageIcon(new ImageIcon(getClass().getResource("../../img/bulle_2.png")).getImage().getScaledInstance(150, 80, Image.SCALE_DEFAULT));

        fenetre.add(new JLabel(icone));
        Font myFont = new Font("Serif", Font.BOLD, 18);

        JPanel boutons = new JPanel();
        JButton nourrir = new JButton();
        nourrir.setText("Nourrir");
        JButton jouer = new JButton();
        jouer.setText("Jouer");
        boutons.add(nourrir);
        boutons.add(jouer);
        fenetre.add(boutons, BorderLayout.SOUTH);

        JPanel etat = new JPanel();
        JLabel bulle = new JLabel();
        bulle.setIcon(bulleImg);
        bulle.setText("J'ai faim !");
        bulle.setFont(myFont);
        bulle.setHorizontalTextPosition(SwingConstants.CENTER);
        etat.add(bulle);
        fenetre.add(etat, BorderLayout.NORTH);

        fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        fenetre.setVisible(true);
    }
}
