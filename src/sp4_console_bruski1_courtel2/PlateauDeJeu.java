/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

/**
 *
 * @author ilanb
 */
public class PlateauDeJeu { //on crée notre tableau

    CelluleDeGrille[][] grille = new CelluleDeGrille[6][7];

    public PlateauDeJeu() {
        for (int i = 0; i < 6; i++) { //lignes
            for (int j = 0; j < 7; j++) { //colonnes
                grille[i][j] = new CelluleDeGrille();
            }
        }
    }

    public int ajouterJetonsDansColonne(Jeton jeton1, int colonne) {
        for (int i = 0; i < 6; i++) { // parcours ligne
            if (grille[i][colonne].jetonCourant == null) { // Nombre jeton nul dans case
                grille[i][colonne].affecterJeton(jeton1); // si oui, on met jeton

                return i;

            }
        }
        return -1;
    }

    public boolean grilleRemplie() {
        int colonne = 0;
        for (int j = 0; j < 7; j++) {
            if (grille[5][j].jetonCourant != null) {
                colonne += 1;
            }
        }
        if (colonne == 7) {
            return true; //grille remplie
        } else {
            return false;
        }
    }

    public void viderGrille() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                grille[i][j].jetonCourant = null;
            }
        }

    }

    public void afficherGrilleSurConsole() {
        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                if ((grille[i][j].lireCouleurDuJeton() == "vide")
                        && grille[i][j].avoirTrouNoir == false
                        && grille[i][j].avoirDesintegrateur == false) {
                    System.out.print("_ "); // s'il n'y a ni de TN et D alors la case est vide on la note _
                }
                if (grille[i][j].lireCouleurDuJeton() == "rouge") { // si la cellule est occupé par un jeton rouge
                    System.out.print("R"); // R apparait dans la cellule concerné
                }

                if (grille[i][j].lireCouleurDuJeton() == "jaune") { // si la cellule est occupé par un jeton jaune
                    System.out.print("J"); // J apparait dans la cellule concerné

                }
                if (grille[i][j].avoirTrouNoir == true) {
                    System.out.print(" O");
                }
                if ((grille[i][j].avoirDesintegrateur == true && grille[i][j].avoirTrouNoir == false)) {
                    System.out.print(" D");
                }

            }
            System.out.println();

        }
    }

    public boolean presenceJeton(int ligne, int colonne) {
        if (grille[ligne][colonne].jetonCourant == null) {
            return false;
        } else {
            return true;
        }

    }

    public String lireCouleurDuJeton(int ligne, int colonne) {
        return grille[ligne][colonne].lireCouleurDuJeton();
    }

    public boolean etreGagnantePourCouleur(String uneCouleur) {
        boolean resultat = false;

        for (int i = 0; i < 4; i++) {      //ici on verifie ligne gagnante 
            for (int j = 0; j < 7; j++) {
                if (grille[i][j].jetonCourant != null) {
                    if (grille[i][j].lireCouleurDuJeton() == grille[i + 1][j].lireCouleurDuJeton()) {
                        if (grille[i][j].lireCouleurDuJeton() == grille[i + 2][j].lireCouleurDuJeton()) {
                            if (grille[i][j].lireCouleurDuJeton() == grille[i + 3][j].lireCouleurDuJeton()) {
                                resultat = true;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 4; i++) {      //verifie si colonne gagnante
            for (int j = 0; j < 7; j++) {
                if (grille[i][j].jetonCourant != null) {
                    if (grille[i][j].lireCouleurDuJeton() == grille[i][j + 1].lireCouleurDuJeton()) {
                        if (grille[i][j].lireCouleurDuJeton() == grille[i][j + 2].lireCouleurDuJeton()) {
                            if (grille[i][j].lireCouleurDuJeton() == grille[i][j + 3].lireCouleurDuJeton()) {
                                resultat = true;
                            }
                        }
                    }
                }
            }
        }

        //Diagonale montante gagnante
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 7; j++) {
                if (grille[i][j].jetonCourant != null) {
                    if (grille[i][j].lireCouleurDuJeton() == grille[i + 1][j + 1].lireCouleurDuJeton()) {
                        if (grille[i][j].lireCouleurDuJeton() == grille[i + 2][j + 2].lireCouleurDuJeton()) {
                            if (grille[i][j].lireCouleurDuJeton() == grille[i + 3][j + 3].lireCouleurDuJeton()) {
                                resultat = true;
                            }
                        }
                    }
                }
            }
        }
        //Diagonale descendante gagnante en case 3
        for (int i = 3; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (grille[i][j].jetonCourant != null) {
                    if (grille[i][j].lireCouleurDuJeton() == grille[i - 1][j + 1].lireCouleurDuJeton()) {
                        if (grille[i][j].lireCouleurDuJeton() == grille[i - 2][j + 2].lireCouleurDuJeton()) {
                            if (grille[i][j].lireCouleurDuJeton() == grille[i - 3][j + 3].lireCouleurDuJeton()) {
                                resultat = true;
                            }
                        }
                    }
                }
            }
        }
        return resultat;

    }

    public void tassercolonne(int colonne) {
        for (int i = 0; i < 5; i++) {
            if (grille[i][colonne].presenceJeton() == false) {
                if (grille[i + 1][colonne].presenceJeton() == true) {
                    grille[i][colonne].affecterJeton(grille[i + 1][colonne].recupererJeton());
                }
            }
        }
    }

    boolean colonneRemplie(int colonne) {
        if (grille[5][colonne].presenceJeton()==true) {
            return true;
        }
        return false;
    }

    public boolean presenceTrouNoir(int ligne, int colonne) {
        if (grille[ligne][colonne].presenceTrouNoir() == true) {
            return true;
        } else {
            return false;
        }

    }
    
    public void tasserGrille(){
        for (int i = 0; i < 7; i++){
            tassercolonne(i);
        }
    }

    public void placerTrouNoir(int ligne, int colone) {

        grille[ligne][colone].placerTrouNoir();

    }

    public void supprimerTrouNoir(int ligne, int colonne) {

        grille[ligne][colonne].supprimerTrouNoir();
    }
    
    public void supprimerDesintegrateur(int ligne, int colonne) {
        grille[ligne][colonne].supprimerDesintegrateur();
    }
    
    public void placerDesintegrateur(int ligne, int colonne) {
        grille[ligne][colonne].placerDesintegrateur();

    }

    public void supprimerJeton(int ligne, int colonne) {
        grille[ligne][colonne].supprimerJeton();

    }

    public boolean presenceDesintegrateur(int ligne, int colonne) {
        if (grille[ligne][colonne].presenceDesintegrateur() == true) {
            return true;
        } else {
            return false;
        }
    }

    public Jeton recupererJeton(int ligne, int colonne) {
        Jeton t = grille[ligne][colonne].recupererJeton();
        return t;
    }

}
