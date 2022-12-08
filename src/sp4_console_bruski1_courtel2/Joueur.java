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
    String nomJoueur ;
    String couleur ;
    private ArrayList <Jeton> reserveJetons;
    int nombreDesintegrateurs ;
    
    public Joueur(String un_nom) {
        
        nomJoueur = un_nom ;
        reserveJetons = new ArrayList() ;
        nombreDesintegrateurs = 0 ;
   
        nomJoueur = un_nom;
    }
    
       public void leNom(String le_n){
        nomJoueur = le_n;
    }
    
    public String afficherNom (){
        return nomJoueur;
    }
    
public String afficherCouleur(){
        return couleur;
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
        Jeton j = reserveJetons.remove(0) ;
        return j ;
    }
    
    public void  obtenirDesintegrateur() {
        nombreDesintegrateurs += 1 ;
    }

    public void affecterCouleur(String couleur) {
        this.couleur = couleur;
    }
    
     public String affecterCouleur() {
         return couleur;
     }
    
    public boolean  utiliserDesintegrateur() {
        if (nombreDesintegrateurs > 0) {
            nombreDesintegrateurs -= 1 ;
            return true;
        } else {
            return false;
        }
    }
    public int NombreDesintegrateurs() {
        return nombreDesintegrateurs;
    }
}


