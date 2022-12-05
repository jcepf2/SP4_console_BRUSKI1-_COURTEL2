/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sp4_console_bruski1_courtel2;

/**
 *
 * @author jules
 */
public class Partie {
    //attributs :
    private Joueur[] ListeJoueurs ;
    private Joueur joueurCourant ;
    PlateauDeJeu plateau ;
    
    public Partie(Joueur Joueur1, Joueur Joueur2) {//constructeur
        ListeJoueurs = new Joueur[2] ;
        ListeJoueurs [0] = Joueur1 ;
        ListeJoueurs [1] = Joueur2 ;
    }
    
    public void attribuerCouleurAuxJoueurs() {//distribue une couleur aléatoire aux deux joueurs
        Random r = new Random();
        //en fonction du resultat, on donne une condition :
        int couleur = generateurAleat.nextInt(1);
        if (couleur == 1) {
            listeJoueurs[0].affecterCouleur("rouge");
            listeJoueurs[1].affecterCouleur("jaune");
        } else {
            listeJoueurs[1].affecterCouleur("rouge");
            listeJoueurs[0].affecterCouleur("jaune");

        }
    }
    public void creerEtAffecterJeton(Joueur){
        for (int k = 0 ; k < 30 ; k ++){
            jeton[k] = new jeton();//on crée ici 30 jetons
      }
    
    public void placerTrousNoirsEtDesintegrateurs(){//nouvelle methode pour placer les deux objets
        Random lgn= new Random();
        Random cln= new Random();
        for(int k=0; k<3;k++){//on crée les 3 premiers
            int ligne = lgn.nextInt(6);
            int colonne= cln.nextInt(7);//car 42 cellules
        if(plateau.presenceTrouNoir(ligne,colonne)==false && plateau.presenceDesintegrateur(ligne, colonne) == false){//on regarde si il y a la présece d'un trou noir OU d'un désintégrateur 
         plateau.placerTrouNoir(ligne, colonne);
                plateau.placerDesintegrateur(ligne, colonne);
            } else {//si il y en a un
                k -= 1;//renvoi d'une valeur obliagtoire
            }
        }
     for (int i = 0; i < 2; i++) {
            int ligne = lgn.nextInt(6);
            int colonne = cln.nextInt(7);//car 42 cellules
            if (plateau.presenceDesintegrateur(ligne, colonne) == false) { plateau.placerDesintegrateur(Ligne, Colonne);
            } else {
                j -= 1;//renvoi d'une valeur obligatoire
            }
        }
    for (int r = 0; r< 2; r++) {
            int Ligne = lgn.nextInt(6);
            int Colonne = cln.nextInt(7);
            if (plateau.presenceTrouNoir(Ligne, Colonne) == false && plateau.presenceDesintegrateur(Ligne, Colonne) == false) {//encore une fois on verifie qu'il n'y a rien
                plateau.placerTrouNoir(Ligne, Colonne);
            } else {
                r -= 1;//renvoi d'une valeur obligatoire
            }
        }
    }

    }
    public void initialiserPartie(){//appel de trois methodes :
    attribuerCouleurAuxJoueurs();
    creerEtAffecterJeton(listeJoueurs[0]);
    creerEtAffecterJeton(listeJoueurs[1]);
    placerTrousNoirsEtDesintegrateurs();      
    }
    public void LancerPartie{//lancement final de la partie
    
}
}
