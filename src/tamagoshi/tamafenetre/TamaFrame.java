package tamagoshi.tamafenetre;

import tamagoshi.tamagoshis.Tamagoshi;
import javax.swing.*;
import java.awt.*;

/**
 * Created by Julien on 27/01/2016.
 */
public class TamaFrame extends JFrame {

    private Tamagoshi monTama;
    private ImageIcon iconeFaim = new ImageIcon(new ImageIcon(getClass().getResource("/img/hungry.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    private ImageIcon iconeEnnui = new ImageIcon(new ImageIcon(getClass().getResource("/img/bored.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    private ImageIcon iconeJoie = new ImageIcon(new ImageIcon(getClass().getResource("/img/happy.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    private ImageIcon iconeKO = new ImageIcon(new ImageIcon(getClass().getResource("/img/KO.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    private ImageIcon iconeAttention = new ImageIcon(new ImageIcon(getClass().getResource("/img/warning.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
    private ImageIcon iconeYoupi = new ImageIcon(new ImageIcon(getClass().getResource("/img/overjoyed.gif")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));

    private JPanel boutons = new JPanel();
    private JPanel bullePanel = new JPanel();
    private JButton nourrir = new JButton();
    private JButton jouer = new JButton();
    private JLabel bulle = new JLabel();
    private JPanel etat = new JPanel();
    private JLabel etatImg = new JLabel();

    /**
     * Construit une frame en lui passant son Tamagoshi en paramètre
     * @param tamagoshi Objet tamagoshi à relier à la frame
     */
    public TamaFrame(Tamagoshi tamagoshi) {

        /**
         * On fait la référence au Tamagoshi
         */
        this.setMonTama(tamagoshi);
        tamagoshi.setMaFrame(this);

        this.setSize(400, 400);
        ImageIcon bulleImg = new ImageIcon(new ImageIcon(getClass().getResource("/img/bulle_2.png")).getImage().getScaledInstance(390, 80, Image.SCALE_DEFAULT));
        Font myFont = new Font("Serif", Font.BOLD, 18);

        this.add(this.etat, BorderLayout.CENTER);

        nourrir.setText("Nourrir");
        jouer.setText("Jouer");
        boutons.add(nourrir);
        boutons.add(jouer);
        this.add(boutons, BorderLayout.SOUTH);


        JPanel etat = new JPanel();
        etat.add(etatImg);


        bulle.setIcon(bulleImg);
        bulle.setText(this.getMonTama().getEtat());
        bulle.setFont(myFont);
        bulle.setHorizontalTextPosition(SwingConstants.CENTER);
        bullePanel.add(bulle);

        this.add(bullePanel, BorderLayout.NORTH);
        this.add(etat, BorderLayout.CENTER);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(this.getMonTama().getName());
        this.setVisible(true);

        this.nourrir.addActionListener(e -> getMonTama().mange());
        this.jouer.addActionListener(e -> getMonTama().jouer());
    }

    public Tamagoshi getMonTama() {
        return monTama;
    }

    public void setMonTama(Tamagoshi monTama) {
        this.monTama = monTama;
    }

    public ImageIcon getIconeFaim() {
        return iconeFaim;
    }

    public void setIconeFaim(ImageIcon iconeFaim) {
        this.iconeFaim = iconeFaim;
    }

    public ImageIcon getIconeEnnui() {
        return iconeEnnui;
    }

    public void setIconeEnnui(ImageIcon iconeEnnui) {
        this.iconeEnnui = iconeEnnui;
    }

    public ImageIcon getIconeJoie() {
        return iconeJoie;
    }

    public void setIconeJoie(ImageIcon iconeJoie) {
        this.iconeJoie = iconeJoie;
    }

    public ImageIcon getIconeKO() {
        return iconeKO;
    }

    public void setIconeKO(ImageIcon iconeKO) {
        this.iconeKO = iconeKO;
    }

    public JButton getNourrir() {
        return nourrir;
    }

    public void setNourrir(JButton nourrir) {
        this.nourrir = nourrir;
    }

    public JButton getJouer() {
        return jouer;
    }

    public void setJouer(JButton jouer) {
        this.jouer = jouer;
    }

    public JLabel getBulle() {
        return bulle;
    }

    public void setBulle(JLabel bulle) {
        this.bulle = bulle;
    }

    public ImageIcon getIconeAttention() {
        return iconeAttention;
    }

    public void setIconeAttention(ImageIcon iconeAttention) {
        this.iconeAttention = iconeAttention;
    }

    public ImageIcon getIconeYoupi() {
        return iconeYoupi;
    }

    public void setIconeYoupi(ImageIcon iconeYoupi) {
        this.iconeYoupi = iconeYoupi;
    }

    public JPanel getEtat() {
        return etat;
    }

    public void setEtat(JPanel etat) {
        this.etat = etat;
    }

    public JPanel getBoutons() {
        return boutons;
    }

    public void setBoutons(JPanel boutons) {
        this.boutons = boutons;
    }

    public JLabel getEtatImg() {
        return etatImg;
    }

    public void setEtatImg(JLabel etatImg) {
        this.etatImg = etatImg;
    }
}
