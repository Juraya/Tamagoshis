package tamagoshi.tamafenetre;

import tamagoshi.jeu.TamaGameGraphic;
import tamagoshi.tamagoshis.Tamagoshi;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Julien on 13/02/2016.
 */
public class TamaMenu extends JFrame {
    private JMenuBar barreMenu;
    private JMenu menuOptions;
    private JMenu menuPartie;
    private JCheckBoxMenuItem nomsAuto;
    private JMenuItem nouvellePartie;

    public TamaMenu() {
        this.barreMenu = new JMenuBar();
        this.menuOptions = new JMenu();
        this.menuPartie = new JMenu();

        this.nouvellePartie = new JMenuItem();
        this.nouvellePartie.setText("Nouvelle partie");

        this.nomsAuto = new JCheckBoxMenuItem();
        this.nomsAuto.setText("Génération de noms automatique");
        this.menuOptions.add(nomsAuto);

        this.menuOptions.setText("Options");
        this.menuPartie.setText("Partie");
        this.menuPartie.add(nouvellePartie);

        this.barreMenu.add(this.menuPartie);
        this.barreMenu.add(this.menuOptions);
        this.add(barreMenu, BorderLayout.NORTH);

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(300, 300);
        this.setVisible(true);

        this.nouvellePartie.addActionListener(e -> {
            String[] choix = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
            String difficulte = (String) JOptionPane.showInputDialog(null, "Choisissez la difficulté :", "Nombre de Tamagoshis", JOptionPane.QUESTION_MESSAGE, null, choix, choix[0]);
            int diffNb = Integer.parseInt(difficulte);
            TamaGameGraphic partie = new TamaGameGraphic();
            //partie.initialisation();
            //partie.play();
            ArrayList<TamaFrame> tamaTab = new ArrayList<>();

            int h = 0;
            int l = 0;
            int compteur = 0;
            for (int i=0; i<diffNb; i++){
                if (diffNb<=5) {
                    Tamagoshi tamatest = new Tamagoshi();
                    TamaFrame frameTest = new TamaFrame(tamatest);
                    frameTest.setLocation(h, l);
                    h = h + 405;

                    tamatest.parle();
                    tamaTab.add(frameTest);
                }

                else {
                    if (compteur==5) {
                        l = l+405;
                        h = 0;
                    }
                    Tamagoshi tamatest = new Tamagoshi();
                    TamaFrame frameTest = new TamaFrame(tamatest);
                    frameTest.setLocation(h, l);
                    h = h + 405;
                    compteur+=1;

                    tamatest.parle();
                    tamaTab.add(frameTest);
                }
            }
        });
    }
}
