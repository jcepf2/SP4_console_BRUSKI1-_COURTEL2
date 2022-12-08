/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sp4_console_bruski1_courtel2;

import java.util.Scanner;

/**
 *
 * @author jules
 */
public class SP4_console_BRUSKI1_COURTEL2 {

    /**
     * @param args the command line arguments
     */
    public static void fmain(String[] args) {
        // TODO code application logic here
        Joueur J1 = new Joueur("nom_joueur1");
        
        Joueur J2 = new Joueur("nom_joueur2");
        
        Partie game = new Partie(J1,J2);   

        game.initialiserPartie();

        game.grille_jeu.afficherGrilleSurConsole();
        game.LancerPartie();

    }
    
}
