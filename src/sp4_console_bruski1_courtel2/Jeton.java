/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

/**
 *
 * @author jules
 */
public class Jeton { //création de la class Jeton 
    private String couleur; // ajout attribut "couleur" associé au jeton
    
    public Jeton(String une_couleur) { /*création constructeur, initialisation 
        attribut couleur du jeton avec la valeur passée en paramètre*/
        couleur = une_couleur; 
    }
    public String lireCouleur() {
        return couleur;
    }


@Override
public String toString() { //methode qui retourne chaine de caractere à afficher en fonction de l'objet "jeton"
    if (couleur == "rouge"){ 
        return "R";
    }
        else if (couleur == "jaune"){
            return "J";
        }
            else {
                return "erreur";
            }
    }
}