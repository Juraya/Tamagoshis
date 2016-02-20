package tamagoshi.jeu;

import tamagoshi.tamafenetre.TamaMenu;


/**
 * Created by Julien on 05/10/2015.
 * Classe utilisée pour lancer le jeu.
 */
public class Main {

    public static void main(String[] args) {
        // On créé une nouvelle partie.
        //TamaGame jeu = new TamaGame();

        /*
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(Main.class.getResource("/music/pokemon.wav").getFile()));
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }

        Tamagoshi tamatest = new Tamagoshi();
        */
        //TamaFrame frameTest = new TamaFrame(tamatest);
        //tamatest.parle();
        //tamatest.setEnergy(0);
        //tamatest.consommeEnergie();

        TamaMenu menu = new TamaMenu();
        /*
        ArrayList<TamaFrame> tamaTab = new ArrayList<>();
        int h = 0;
        int l = 0;
        for (int i=0; i<10; i++) {
            if (i>=1) {
                TamaFrame frameTest = new TamaFrame(tamatest);
                frameTest.setLocation(h, l);
                h = h+410;

                tamatest.parle();
                tamaTab.add(frameTest);
                if (tamaTab.size()>=5) {
                    l = l + 410;
                }
                frameTest.setLocationRelativeTo(tamaTab.get(i-1));
            }
        }
        */
        //tamatest.getMaFrame().getContentPane().remove(2);

        //tamatest.setEnergy(0);
        //tamatest.consommeEnergie();
        //System.out.println(tamatest.getEnergy());





        // On lance cette partie.
        //jeu.play();
    }
}
