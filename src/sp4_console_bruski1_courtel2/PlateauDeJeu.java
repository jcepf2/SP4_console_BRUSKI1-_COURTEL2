/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

/**
 *
 * @author ilanb
 */
public class PlateauDeJeu {//création d'une nouvelle classe
    private CelluleDeGrille[][]grille=new CelluleDeGrille[6][7];//il s’agit d’un tableau de 6 par 7 objets de type CelluleDeGrille

public PlateauDeJeu(){//constructeur plateaudejeu
    for (int k=0;k<6;k++){
        for(int i=0;i<7;k++){
            grille[k][i]  = new CelluleDeGrille();//on crée ici 42 céllules vides de type CelluleDeGrille
        }
    }
}   

public int ajouterJetonDansColonne(Jeton m, int i){//renvoie true ou false en fonction de la présence du jeton dans la cellule
    for (int k=0;k<6;k++){
        if(grille[k][i].presenceJeton()==false){
            grille[k][i].affecterJeton(m);//ajoute le jeton en paramètre à la cellule
        }
                    return k;//renvoi d'un entier correspondant à l'indice de la ligne
    }
        return 0;//car deux briques imbriquées impliquent deux return statement
}
        
public boolean grilleRemplie(){
    for(int j=0;j<7;j++){
        if (grille[5][j].presenceJeton()==false){
            return false;
                        }
                    }
    return true;//si grille est pleine, le joueur ne peut plus jouer
        }
}


