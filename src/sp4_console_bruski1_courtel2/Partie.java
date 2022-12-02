/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

/**
 *
 * @author jules
 */
public class Partie {
    private Joueur[] ListeJoueurs ;
    private Joueur joueurCourant ;
    PlateauDeJeu plateau ;
    
    public Partie(Joueur Joueur1, Joueur Joueur2) {
        ListeJoueurs = new Joueur[2] ;
        ListeJoueurs [0] = Joueur1 ;
        ListeJoueurs [1] = Joueur2 ;
    }
    
    public void attribuerCouleurAuxJoueurs() {
        Random r = new Random();
        
        
    }
    
    
    
    
}
