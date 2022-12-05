/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

import java.util.ArrayList;

/**
 *
 * @author jules
 */
public class Joueur {
    private String nomJoueur ;
    private String couleur ;
    private ArrayList <Jeton> reserveJetons = new ArrayList<Jeton>() ;
    private int nombreDesintegrateurs ;
    
    public void Joueur(String un_nom) {
        nomJoueur = un_nom ;
        reserveJetons = null ;
        nombreDesintegrateurs = 0 ;
    }
    
    public void affecterCouleur(String une_couleur) {
        couleur = une_couleur ;
    }
    
    public int nombreDeJetons() {
        int taille = reserveJetons.size() ;
        return taille ;
    }
    
    public void ajouterJeton(Jeton ajout) {
        reserveJetons.add( ajout) ;
    }      
    
    public Jeton jouerJeton() {
        int m = nombreDeJetons() ;
        Jeton j = reserveJetons.remove(m) ;
        return j ;
    }
    
    public void  obtenirDesintegrateur() {
        nombreDesintegrateurs += 1 ;
    }
    
    public void  utiliserDesintegrateur() {
        nombreDesintegrateurs -= 1 ;
    }
}

