package tamagoshi.util;

/**
 * Created by Julien on 06/10/2015.
 * Classe servant uniquement à récupérer les entrées de l'utilisateur.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utilisateur {

    /**
     * Permet la saisie clavier de l'utilisateur
     * @return Renvoie le résultat sous forme de String.
     */
    public static String saisieClavier(){

        try{
            BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
            return clavier.readLine();
        }
        catch(IOException e){
            e.printStackTrace();
            System.exit(0);
            return null;
        }
    }
}

