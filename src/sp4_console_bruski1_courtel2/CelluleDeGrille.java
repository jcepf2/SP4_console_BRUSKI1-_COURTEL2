/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

/**
 *
 * @author jules
 */
public class CelluleDeGrille { //création de la class CelluleDeGrille
    private Jeton jetonCourant; //ajout attribut "jetonCourant" associé au Jeton
    
    public CelluleDeGrille() { //initialisation de l'attribut
        jetonCourant = null;
    }
    
    public boolean presenceJeton() {/*permet de savoir si la cellule est en
        présence ou non d'un jeton grâce à la méthode boolean*/
        if (jetonCourant == null) {
            return false;
        }
        else {
            return true;
        }
    }
    
    public void afficherJeton(Jeton un_jeton) {/*on ajoute le jeton en parametre a la cellule*/    
        jetonCourant = un_jeton;
    }
    
    public String lireCouleurDuJeton() {/*on renvoie la couleur du jeton si il est présent */  
        if (jetonCourant == null) {
            return "la case est vide";
        }
        else {
            return jetonCourant.lireCouleur();  
        }
    }
    
    public Jeton recupererJeton() {
        Jeton temp = jetonCourant;
        jetonCourant = null;
        return (temp);
    }
    
}   
