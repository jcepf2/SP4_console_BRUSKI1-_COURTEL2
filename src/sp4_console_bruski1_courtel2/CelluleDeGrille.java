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
    Jeton jetonCourant ; //ajout attribut "jetonCourant" associé au Jeton
    boolean avoirTrouNoir ;
    boolean avoirDesintegrateur ;  
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
            return "case vide" ;
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
        if (this.presenceJeton()==false){
        
    

        
        
        if(this.presenceTrouNoir()==true&&this.presenceDesintegrateur()==true){
            return"@";
        }
        
         if(this.presenceTrouNoir()==true){
            return"@";
        }
        
        if(this.presenceDesintegrateur()==true){
            return"D";
        }
        return".";
    }
        
    
    else{
    if(this.lireCouleurDuJeton()=="rouge"){
            return "R";   
        }

        if (this.lireCouleurDuJeton()=="jaune"){
            return"J";
 
        }
    }
    return "k";
}
}

