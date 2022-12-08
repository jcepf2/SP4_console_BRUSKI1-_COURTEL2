/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

import java.util.Random;
import java.util.Scanner;
import java.util.Random;
    
public class Partie {
    Joueur[] listeJoueurs;
    Joueur joueurCourant;
    PlateauDeJeu plateau;
    PlateauDeJeu grille_jeu = new PlateauDeJeu();
    
    public Partie(Joueur Joueur1, Joueur Joueur2){
        listeJoueurs = new Joueur[2];
        listeJoueurs [0]=Joueur1;
        listeJoueurs [1]=Joueur2;
        }
    
    public void  attribuerCouleurAuxJoueurs(){
        Random r = new Random();
        int c = r.nextInt(1);
        if (c == 0){
            listeJoueurs [0].affecterCouleur("rouge");
            listeJoueurs [1].affecterCouleur("jaune");
        }if (c == 1){
            listeJoueurs [0].affecterCouleur("jaune");
            listeJoueurs [1].affecterCouleur("rouge");
        }
    }
        public void creerEtAffecterJeton(Joueur joueur) {
        Jeton[] jetons = new Jeton[30];
        for (int i = 0; i < 30; i++) {
            jetons[i] = new Jeton(joueur.couleur);
            joueur.ajouterJeton(jetons[i]);
        }
    }

    public void placerTrousNoirsEtDesintegrateurs() {
    Random l = new Random();
        Random c = new Random();
        for (int i = 0; i < 3; i++) {
            int ligne = l.nextInt(0, 6);
            int colonne = c.nextInt(0, 7);
            if (plateau.presenceTrouNoir(ligne, colonne) == false && plateau.presenceDesintegrateur(ligne, colonne) == false) {
                plateau.placerTrouNoir(ligne, colonne);
                plateau.placerDesintegrateur(ligne, colonne);
            } else {
                i -= 1;
            }
        }
        for (int j = 0; j < 2; j++) {
            int ligne = l.nextInt(0, 6);
            int colonne = c.nextInt(0, 7);
            if (plateau.presenceDesintegrateur(ligne, colonne) == false) {
                plateau.placerDesintegrateur(ligne, colonne);
            } else {
                j -= 1;
            }
        }
        for (int k = 0; k < 2; k++) {
            int ligne = l.nextInt(0, 6);
            int colonne = c.nextInt(0, 7);
            if (plateau.presenceTrouNoir(ligne, colonne) == false && plateau.presenceDesintegrateur(ligne, colonne) == false) {
                plateau.placerTrouNoir(ligne, colonne);
            } else {
                k -= 1;
            }
        }
    }

     public void initialiserPartie() {
               
        attribuerCouleurAuxJoueurs();
        creerEtAffecterJeton(listeJoueurs [0]);
        creerEtAffecterJeton(listeJoueurs[1]);
        placerTrousNoirsEtDesintegrateurs();
    }

   
        public void LancerPartie() {
        /*mise en place de la grille*/
        plateau.viderGrille();
        int choix_joueur;
        int nombre_joué = 0;

        /*création des joueurs*/
        Scanner saisie_joueur = new Scanner(System.in);
        System.out.println("Quel est le nom du joueur 1 ?");
        /*on demande le nom du premier joueur*/
        String nom_j1 = saisie_joueur.next();
        listeJoueurs[0].Joueur(nom_j1);
        /*on lui affecte son nom*/

        System.out.println("Quel est le nom du joueur 2 ?");
        /*on demande le nom du second joueur*/
        String nom_j2 = saisie_joueur.next();
        listeJoueurs[1].Joueur(nom_j2);

        /*on récupère le nom et la couleur affectée aux joueurs*/
        placerTrousNoirsEtDesintegrateurs();
        grille_jeu.afficherGrilleSurConsole();
        System.out.println(listeJoueurs[0].nomJoueur + " est de couleur " + listeJoueurs[0].couleur);
        System.out.println(listeJoueurs[1].nomJoueur + " est de couleur " + listeJoueurs[1].couleur);

        /*on détermine le premier joueur*/
        Random generateurAleat = new Random();
        boolean premier_joueur = generateurAleat.nextBoolean();
        if (premier_joueur) {
            joueurCourant = listeJoueurs[0];
        } else {
            joueurCourant = listeJoueurs[1];
        }

        String couleur_jeton = joueurCourant.affecterCouleur();

        boolean victoire = false;
        /*au départ personne n'a gagné*/
        while (victoire != true) {
            /*tant que personne n'a eu 4 jetons alignés on continu de jouer donc la boucle continue de s'exécuter*/
            System.out.println("C'est à " + joueurCourant.nomJoueur + " de jouer");
            plateau.afficherGrilleSurConsole();

            /* création d'un menu avec plusieurs choix possible*/
            System.out.println(joueurCourant.nomJoueur + ", que voulez vous faire?");
            System.out.println("1 - Jouer un jeton");
            System.out.println("2 - Récupérer un jeton");
            System.out.println("3 - Désintegrer un jeton adverse");
            System.out.println("Veuillez entrer le chiffre correspondant à l'action voulue :");
            choix_joueur = saisie_joueur.nextInt();

            while (choix_joueur <= 0 || choix_joueur > 3) {
                /* si l'utilisateur se trompe et ne met pas un nombre entre 1 et 3*/
                System.out.println("veuillez entrer un chiffre compris entre 1 - 3 correspondant a l'action voulue");
                choix_joueur = saisie_joueur.nextInt();
            }

            if (choix_joueur == 1) {
                /*s'il choisi 1 il veut placer un jeton*/
                if (joueurCourant.nombreDeJetons() == 0) {/*on vérifie que le joueur possede assez de jetons pour jouer*/
                    System.out.println("Vous n'avez plus de jeton donc vous ne pouvez pas jouer");
                } else {
                    System.out.println("Où souhaitez vous jouer ?");
                    int colonne = saisie_joueur.nextInt();
                    Jeton jeton_joué = joueurCourant.jouerJeton();/*on crée le jeton qui va être joué et le retire de ceux que le joueur possède*/
                    boolean cr = plateau.colonneRemplie(colonne);/*on vérifie que la colonne n'est pas remplie*/
                    if (cr == true) {/*si la colonne est remplie on ne peut plus placer de pion*/
                        System.out.println("Cette colonne est pleine, choisissez en une autre");
                    } else {/*cas où la colonne n'est pas remplie : on peut jouer*/
                        int ligne = plateau.ajouterJetonsDansColonne(jeton_joué, colonne);
                        if(plateau.presenceTrouNoir(ligne, colonne)==true&&plateau.presenceDesintegrateur(ligne, colonne)==true){
                            plateau.supprimerTrouNoir(ligne, colonne);
                            plateau.supprimerDesintegrateur(ligne,colonne);
                            joueurCourant.obtenirDesintegrateur();
                            plateau.supprimerJeton(ligne, colonne);
                        }
                        if (plateau.presenceTrouNoir(ligne, colonne)) {
                            plateau.supprimerTrouNoir(ligne, colonne);
                            plateau.supprimerJeton(ligne, colonne);
                        }
                        if (plateau.presenceDesintegrateur(ligne, colonne)) {
                            plateau.supprimerDesintegrateur(ligne, colonne);
                            joueurCourant.obtenirDesintegrateur();

                    }
                }
               
            }
}
            
            if (choix_joueur == 2) {/*s'il choisi 2 il veut retirer un jeton*/

                System.out.println("Dans quelle colonne est le jeton que vous voulez retirer ?");
                int colonne = saisie_joueur.nextInt();                      //on demande la ligne et colonne du jeton 
                System.out.println("Dans quelle liNINAgne est le jeton que vous voulez retirer ?");
                int ligne = saisie_joueur.nextInt();
                boolean presence = plateau.presenceJeton(ligne, colonne);//on vérifie qu'il y a bien un jeton à retirer
                String clr_j = joueurCourant.affecterCouleur();
                String clr_jt = plateau.lireCouleurDuJeton(ligne, colonne);
                if (presence == true && clr_j == clr_jt) {//on vérifie s'il y en a un et s'il est de la couleur du joueur courant
                    plateau.recupererJeton(ligne, colonne);
                    plateau.tassercolonne(colonne);
                } else {
                    System.out.println("il n'y a pas de jeton à retirer ou ce jeton ne vous appartient pas");
                }

                
            }

            if (choix_joueur == 3) {
                System.out.println("Dans quelle colonne est le jeton que vous voulez desintégrer ?");
                int colonne = saisie_joueur.nextInt();
                System.out.println("Dans quelle ligne est le jeton que vous voulez desintégrer ?");
                int ligne = saisie_joueur.nextInt();

                if (joueurCourant.utiliserDesintegrateur() == true) {/*on regarde si on a des désintégrateurs*/
                    if (joueurCourant.affecterCouleur() == "rouge") {/*si notre jeton est rouge*/
                        if (plateau.presenceJeton(ligne, colonne) == true) {/*on regarde qu'il y ai bien un jeton*/
                            if (plateau.lireCouleurDuJeton(ligne, colonne) == "jaune") {/*on regarde si c'est celui de l'adversaire*/
                                plateau.supprimerJeton(ligne, colonne);
                                plateau.tassercolonne(colonne);
                            } else {
                                System.out.println("C'est votre jeton");
                            }
                        } else {
                            System.out.println("il n'y a pas de jeton à desintégrer");

                        }
                    } else {
                        if (plateau.presenceJeton(ligne, colonne) == true) {/*on regarde qu'il y ai bien un jeton*/
                            if (plateau.lireCouleurDuJeton(ligne, colonne) == "rouge") {/*on regarde si c'est celui de l'adversaire*/
                                plateau.supprimerJeton(ligne, colonne);
                                plateau.tassercolonne(colonne);
                            } else {
                                System.out.println("C'est votre jeton");
                            }
                            System.out.println("il n'y a pas de jeton à desintégrer");
                        }
                    }
                } else {
                    System.out.println("Vous n'avez pas de désintégrateur");
                }

                
            }   String clr = joueurCourant.affecterCouleur();
                victoire = plateau.etreGagnantePourCouleur(clr);

                if (joueurCourant == listeJoueurs[0]) {
                    joueurCourant = listeJoueurs[1];
                } else if (joueurCourant == listeJoueurs[1]) {
                    joueurCourant = listeJoueurs[0];
                }
            
            
        }
        plateau.afficherGrilleSurConsole();

        if (joueurCourant == listeJoueurs[0]){
            joueurCourant = listeJoueurs[1];
            String gagnant = joueurCourant.afficherNom();
            System.out.println(gagnant + " a gagné");
        } else if (joueurCourant == listeJoueurs[1]){
            joueurCourant = listeJoueurs[0];
            String gagnant = joueurCourant.afficherNom();
            System.out.println(gagnant + " a gagné");

        }

    }
}