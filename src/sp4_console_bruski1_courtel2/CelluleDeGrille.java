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
    private Jeton jetonCourant ; //ajout attribut "jetonCourant" associé au Jeton
    private boolean avoirTrouNoir ;
    private boolean avoirDesintegrateur ;  
    /*ajout des attributs spécifique au super puissance 4*/
    
    public CelluleDeGrille() { //initialisation des attributs
        jetonCourant = null ;
        avoirTrouNoir = false ;
        avoirDesintegrateur = false ;
    }
    
    public boolean presenceJeton() {/*permet de savoir si la cellule est en
        présence ou non d'un jeton grâce à la méthode boolean*/
        if (jetonCourant == null) {
            return false ;
        }
        else {
            return true ; 
        }
    }
    
    public void affecterJeton(Jeton un_jeton) {/*on ajoute le jeton en parametre a la cellule*/    
        jetonCourant = un_jeton ;
    }
    
    public String lireCouleurDuJeton() {/*on renvoie la couleur du jeton si il est présent */  
        if (jetonCourant == null) {
            return "la case est vide" ;
        }
        else {
            return jetonCourant.lireCouleur() ;  
        }
    }
    
    public void placerTrouNoir() {
        avoirTrouNoir = true ;
    }
    
    public void supprimerTrouNoir() { 
        avoirTrouNoir = false ;
    }
    
    public boolean presenceTrouNoir() {
    /*accesseur en lecture pour les trous noires*/
        if (avoirTrouNoir == true) {
            return true ;
        } else {
            return false ;
        }
    }
    
    public Jeton recupererJeton() {/*variable temporaire permet de recupérer
        le jeton au cours du jeu*/
        Jeton temp = jetonCourant ;
        jetonCourant = null ;
        return (temp) ;
    }
    
    public void supprimerJeton() {
        jetonCourant = null ;
    }
    
    public boolean presenceDesintegrateur() {
    /*accesseur en lecture pour les intégrrateurs*/
        if (avoirDesintegrateur == true) {
            return true ;
        } else {
            return false ;
        }
    }
    
    public void placerDesintegrateur() {
       avoirDesintegrateur = true ;
    }
   
    public void supprimerDesintegrateur() {
       avoirDesintegrateur = false ;
    }
    
    public void activerTrouNoir() { /*methode permettant activer le trou noir et 
       et donc de supprimer le jeton de la case */
        this.supprimerJeton() ;
        this.supprimerTrouNoir() ;
    }
    
    @Override//ecrase
public String toString (){//creation de la methode toString
    String clr=lireCouleurDuJeton();
    String chaine_a_retourner ;//initialisation d'une variable
    chaine_a_retourner="erreur" ;
    
    if (jetonCourant==null && avoirDesintegrateur == false &&avoirTrouNoir == false){
        chaine_a_retourner="." ;
    }
    
    if(clr=="rouge"){
        chaine_a_retourner="R" ;
    }
    
    if(clr=="jaune"){
        chaine_a_retourner="J" ;
    }
    
    if(avoirTrouNoir == true) {
        chaine_a_retourner= "@" ;
    }
    
    if(avoirDesintegrateur == true) {
        chaine_a_retourner= "D" ;
    }
    // retourne les caracteres en fonction des conditions de chaques cases
    return chaine_a_retourner ;//renvoi de la variable au systee en interne
    }
}
