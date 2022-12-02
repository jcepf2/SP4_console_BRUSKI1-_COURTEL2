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
    private CelluleDeGrille [] [] grille = new CelluleDeGrille [6] [7] ;//il s’agit d’un tableau de 6 par 7 objets de type CelluleDeGrille

public PlateauDeJeu(){//constructeur plateaudejeu
    for (int k = 0 ; k < 6 ; k ++){
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

    public void afficherGrilleSurConsole(){//fonction d'affichage de la grille sur la console
    for (int k=5;k>0;k--){
        for (int i=6;k>0;k--){
            grille[k][i].toString();
        }
    }
    }
    
    public void presenceJeton(int x, int y){
    grille[x][y].presenceJeton();
    }
    
    public void  lireCouleurDuJeton(int x, int y){
    grille[x][y].lireCouleurDuJeton();//retourne  la valeur de presenceJeton() de CelluleDeGrille aux coordonnées [x][y]
    }

    public boolean etreGagnantePourCouleur(String c){
    boolean B1 = ligneGagnantePourCouleur(c);
    boolean B2 = colonneGagnantePourCouleur(c);
    boolean B3 = diagonaleMontanteGagnantePourCouleur(c);
    boolean B4 = diagonaleDesencanteGagnantePourCouleur(c);  
if (B1 == true || B2 == true || B3 == true || B4 == true ){
    return true;//renvoie true si la grille est gagnante pour la couleur passée en paramètre
}
return false;

}
//les lignes suivantes sont la décomposition de la methode etreGagnantePourCouleur
public boolean diagonaleDesencanteGagnantePourCouleur(String C){
    for (int k=5;k>3;k--){
        for (int i=3;i>0;k--){
            if (C==grille[k][i].lireCouleurDuJeton()& C==grille[k-1][i+1].lireCouleurDuJeton()& C==grille[k-2][i+2].lireCouleurDuJeton() & C==grille[k-3][i+3].lireCouleurDuJeton()){
                return true;
        }
        }
    }
    return false;
}

public boolean colonneGagnantePourCouleur(String C){
    for (int k=2;k>0;k--){
        for (int i=6;i>0;k--){
            if (C==grille[k][i].lireCouleurDuJeton()& C==grille[k+1][i].lireCouleurDuJeton()& C==grille[k+2][i].lireCouleurDuJeton() & C==grille[k+3][i].lireCouleurDuJeton()){
                return true;//renvoie true si la grille est gagnante
        }
        }
    }
    return false;//renvoi false si la grille n'est gagnante
}

public boolean diagonaleMontanteGagnantePourCouleur(String C){
    for (int k=2;k>0;k--){
        for (int i=3;i>0;k--){
            if (C==grille[k][i].lireCouleurDuJeton()& C==grille[k+1][i+1].lireCouleurDuJeton()& C==grille[k+2][i+2].lireCouleurDuJeton() & C==grille[k+3][i+3].lireCouleurDuJeton()){
                return true;
        }
        }
    }
    return false;
}

public boolean ligneGagnantePourCouleur(String C){
    for (int k=5;k>0;k--){
        for (int i=3;i>0;k--){
            if (C==grille[k][i].lireCouleurDuJeton()& C==grille[k][i+1].lireCouleurDuJeton()& C==grille[k][i+2].lireCouleurDuJeton() & C==grille[k][i+3].lireCouleurDuJeton()){
                return true;//renvoie true si la grille est gagnante
            }
        }
    }
    return false;//renvoi false si la grille n'est pas gagnante
}
public boolean colonneRemplie(int y){//y car colonne
    if(grille[5][y].presenceJeton()==false){
        return false;
    }
    return true;//renvoie  true si la colonne est remplie
}
}
