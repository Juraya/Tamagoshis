package tamagoshi.tamafenetre;

import tamagoshi.jeu.TamaGameGraphic;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Julien on 13/02/2016.
 */
public class TamaMenu extends JFrame {
    private static TamaMenu menuActuel;
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
            TamaGameGraphic partie = new TamaGameGraphic();
        });

        this.nomsAuto.addItemListener(e -> menuActuel.getNomsAuto().setState(this.nomsAuto.getState()));

        menuActuel = this;
        System.err.println("Menu terminé.");
    }

    public JMenuBar getBarreMenu() {
        return barreMenu;
    }

    public void setBarreMenu(JMenuBar barreMenu) {
        this.barreMenu = barreMenu;
    }

    public JMenu getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(JMenu menuOptions) {
        this.menuOptions = menuOptions;
    }

    public JMenu getMenuPartie() {
        return menuPartie;
    }

    public void setMenuPartie(JMenu menuPartie) {
        this.menuPartie = menuPartie;
    }

    public JCheckBoxMenuItem getNomsAuto() {
        return nomsAuto;
    }

    public void setNomsAuto(JCheckBoxMenuItem nomsAuto) {
        this.nomsAuto = nomsAuto;
    }

    public JMenuItem getNouvellePartie() {
        return nouvellePartie;
    }

    public void setNouvellePartie(JMenuItem nouvellePartie) {
        this.nouvellePartie = nouvellePartie;
    }

    public static TamaMenu getMenuActuel() {
        return menuActuel;
    }
}
