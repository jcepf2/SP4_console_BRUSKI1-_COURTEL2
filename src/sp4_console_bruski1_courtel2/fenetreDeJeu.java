/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package sp4_console_bruski1_courtel2;

import java.util.Random;
/**
 *
 * @author jules
 */
public class fenetreDeJeu extends javax.swing.JFrame {

    Joueur ListeJoueurs[] = new Joueur[2];
    Joueur joueurCourant;
    PlateauDeJeu plateau = new PlateauDeJeu();

    public static void main(String args[]) {

        System.out.println("OK");
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fenetreDeJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fenetreDeJeu().setVisible(true);
            }
        });
    }

    /**
     * Creates new form fenetreDeJeu
     */
    public fenetreDeJeu() {
        initComponents();
        panneau_info_joueurs.setVisible(false);
        panneau_info_partie.setVisible(false);

        for (int i = 5; i >= 0; i--) {
            for (int j = 0; j < 7; j++) {
                CelluleGraphique CellGraph = new CelluleGraphique(plateau.grille[i][j]);
                CellGraph.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        CelluleDeGrille c = CellGraph.celluleAssociee;
                        if (c.jetonCourant == null) {
                            return;
                        }

                        if (c.jetonCourant.equals(joueurCourant.couleur)) {
                            message.setText("le joueur " + joueurCourant.nomJoueur + " récupère un de ses jetons");
                            Jeton jeton_recup = c.recupererJeton();
                            joueurCourant.ajouterJeton(jeton_recup);
                            joueurSuivant();
                        } else {
                            if (joueurCourant.nombreDesintegrateurs > 0) {
                                message.setText("le joueur " + joueurCourant.nomJoueur + " désintègre un jeton");
                                c.supprimerJeton();
                                joueurCourant.utiliserDesintegrateur();
                                joueurSuivant();
                            } else {
                                return;
                            }
                        }
                        plateau.tasserGrille();
                        panneau_grille.repaint();
                        lbl_j1_desint.setText(ListeJoueurs[0].nombreDesintegrateurs + "");
                        lbl_j2_desint.setText(ListeJoueurs[1].nombreDesintegrateurs + "");
                        if (plateau.etreGagnantePourCouleur(joueurCourant.afficherCouleur()) == true) {
                            message.setText("victoire de " + joueurCourant.afficherNom());
                        }
                    }
                });
                panneau_grille.add(CellGraph);
            }
        }
    }

    public void attribuerCouleurAuxJoueurs() {
        Random r = new Random();
        int c = r.nextInt(1);
        if (c == 0) {
            ListeJoueurs[0].affecterCouleur("rouge");
            ListeJoueurs[1].affecterCouleur("jaune");
        }
        if (c == 1) {
            ListeJoueurs[0].affecterCouleur("jaune");
            ListeJoueurs[1].affecterCouleur("rouge");
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

        String nomJoueur1 = nom_joueur1.getText();
        Joueur Joueur1 = new Joueur(nomJoueur1);
        String nomJoueur2 = nom_joueur2.getText();
        Joueur Joueur2 = new Joueur(nomJoueur2);
        ListeJoueurs[0] = Joueur1;
        ListeJoueurs[1] = Joueur2;

        lbl_j1_nom.setText(nomJoueur1);
        lbl_j2_nom.setText(nomJoueur2);

        lbl_j1_desint.setText(Joueur1.NombreDesintegrateurs() + "");
        lbl_j2_desint.setText(Joueur2.NombreDesintegrateurs() + "");

        Random r = new Random();
        boolean le_premier = r.nextBoolean();
        if (le_premier) {
            joueurCourant = ListeJoueurs[0];
        } else {
            joueurCourant = ListeJoueurs[1];
        }
        lbl_jcourant.setText(joueurCourant.afficherCouleur());
        attribuerCouleurAuxJoueurs();
        creerEtAffecterJeton(ListeJoueurs[0]);
        creerEtAffecterJeton(ListeJoueurs[1]);
        placerTrousNoirsEtDesintegrateurs();

        lbl_j1_couleur.setText(Joueur1.couleur);
        lbl_j2_couleur.setText(Joueur2.couleur);

    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panneau_grille = new javax.swing.JPanel();
        panneau_creation_partie = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        nom_joueur1 = new javax.swing.JTextField();
        nom_joueur2 = new javax.swing.JTextField();
        btm_start_partie = new javax.swing.JButton();
        panneau_info_joueurs = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_j1_nom = new javax.swing.JLabel();
        lbl_j1_couleur = new javax.swing.JLabel();
        lbl_j1_desint = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_j2_nom = new javax.swing.JLabel();
        lbl_j2_couleur = new javax.swing.JLabel();
        lbl_j2_desint = new javax.swing.JLabel();
        panneau_info_partie = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        lbl_jcourant = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        message = new javax.swing.JTextArea();
        btn_col_1 = new javax.swing.JButton();
        btn_col_2 = new javax.swing.JButton();
        btn_col_3 = new javax.swing.JButton();
        btn_col_4 = new javax.swing.JButton();
        btn_col_5 = new javax.swing.JButton();
        btn_col_6 = new javax.swing.JButton();
        btn_col_7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 255, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panneau_grille.setBackground(new java.awt.Color(255, 255, 255));
        panneau_grille.setLayout(new java.awt.GridLayout(6, 7));
        getContentPane().add(panneau_grille, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 672, 576));

        panneau_creation_partie.setBackground(new java.awt.Color(153, 204, 255));
        panneau_creation_partie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Nom Joueur 1 :");
        panneau_creation_partie.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 15, 120, 30));

        jLabel2.setText("Nom Joueur 2 :");
        panneau_creation_partie.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 80, 50));

        nom_joueur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nom_joueur1ActionPerformed(evt);
            }
        });
        panneau_creation_partie.add(nom_joueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 15, 140, 25));
        panneau_creation_partie.add(nom_joueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 45, 140, 25));

        btm_start_partie.setText("Démarer Partie");
        btm_start_partie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btm_start_partieActionPerformed(evt);
            }
        });
        panneau_creation_partie.add(btm_start_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 83, 140, 20));

        getContentPane().add(panneau_creation_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 50, 290, 110));

        panneau_info_joueurs.setBackground(new java.awt.Color(153, 204, 255));
        panneau_info_joueurs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Infos Joueurs");
        panneau_info_joueurs.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 130, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Joueur1 :");
        panneau_info_joueurs.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        jLabel5.setText("couleur :");
        panneau_info_joueurs.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, 20));

        jLabel6.setText("désintégrateurs :");
        panneau_info_joueurs.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        lbl_j1_nom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_j1_nom.setText("nomjoueur1");
        panneau_info_joueurs.add(lbl_j1_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 100, 20));

        lbl_j1_couleur.setText("couleurjoueur1");
        panneau_info_joueurs.add(lbl_j1_couleur, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, -1, 20));

        lbl_j1_desint.setText("désintégrationjoueur1");
        panneau_info_joueurs.add(lbl_j1_desint, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jSeparator1.setForeground(new java.awt.Color(51, 51, 255));
        panneau_info_joueurs.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 260, 10));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Joueur2 :");
        panneau_info_joueurs.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 20));

        jLabel8.setText("couleur :");
        panneau_info_joueurs.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 20));

        jLabel9.setText("désintégrateurs :");
        panneau_info_joueurs.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 20));

        lbl_j2_nom.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lbl_j2_nom.setText("nomjoueur2");
        panneau_info_joueurs.add(lbl_j2_nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 150, 100, 20));

        lbl_j2_couleur.setText("couleurjoueur2");
        panneau_info_joueurs.add(lbl_j2_couleur, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, -1, 20));

        lbl_j2_desint.setText("désintégrationjoueur2");
        panneau_info_joueurs.add(lbl_j2_desint, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 190, -1, 20));

        getContentPane().add(panneau_info_joueurs, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 290, 260));

        panneau_info_partie.setBackground(new java.awt.Color(153, 204, 255));
        panneau_info_partie.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Infos Jeu");
        panneau_info_partie.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 110, 30));

        jLabel11.setText("joueur courant :");
        panneau_info_partie.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 36, 90, 30));

        lbl_jcourant.setText("nom joueur");
        panneau_info_partie.add(lbl_jcourant, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 80, 20));

        message.setColumns(20);
        message.setRows(5);
        jScrollPane1.setViewportView(message);

        panneau_info_partie.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 66, 250, 60));

        getContentPane().add(panneau_info_partie, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 290, 140));

        btn_col_1.setText("1");
        btn_col_1.setToolTipText("");
        btn_col_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_1ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 10, 96, -1));

        btn_col_2.setText("2");
        btn_col_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_2ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 10, 96, -1));

        btn_col_3.setText("3");
        btn_col_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_3ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(211, 10, 96, -1));

        btn_col_4.setText("4");
        btn_col_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_4ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(307, 10, 96, -1));

        btn_col_5.setText("5");
        btn_col_5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_5ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 10, 96, -1));

        btn_col_6.setText("6");
        btn_col_6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_6ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(499, 10, 96, -1));

        btn_col_7.setText("7");
        btn_col_7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_col_7ActionPerformed(evt);
            }
        });
        getContentPane().add(btn_col_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(595, 10, 96, -1));

        setSize(new java.awt.Dimension(1044, 671));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btn_col_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_1ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(0);
        if (plateau.colonneRemplie(0) == true) {
            btn_col_1.setEnabled(false);
        }
        joueurSuivant();
    }//GEN-LAST:event_btn_col_1ActionPerformed

    private void btn_col_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_4ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(3);
        if (plateau.colonneRemplie(3) == true) {
            btn_col_4.setEnabled(false);
        }
        joueurSuivant();
    }//GEN-LAST:event_btn_col_4ActionPerformed

    private void btn_col_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_3ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(2);
        if (plateau.colonneRemplie(2) == true) {
            btn_col_3.setEnabled(false);
        }
        joueurSuivant();
    }//GEN-LAST:event_btn_col_3ActionPerformed

    private void btn_col_7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_7ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(6);
        if (plateau.colonneRemplie(6) == true) {
            btn_col_7.setEnabled(false);
        }
        joueurSuivant();
    }
    
    public boolean jouerDansColonne(int indice_colonne){
        int colonne = indice_colonne; 
        panneau_grille.repaint();

               if(joueurCourant.nombreDeJetons()>0){//on vérifie que le joueur possede encore des jetons

                Jeton j_joué=joueurCourant.jouerJeton();//cette ligne crée le jeton qui va être joué et le retire du sac de jeton du joueur
                boolean cr=plateau.colonneRemplie(indice_colonne);//on vérifie que la colonne n'est pas remplie
                if(cr==false){//si la colonne n'est pas remplie on place le jeton
                int lig = plateau.ajouterJetonsDansColonne(j_joué, indice_colonne);
                         
                        if(plateau.presenceTrouNoir(lig,indice_colonne)==true){
                            plateau.supprimerTrouNoir(lig,indice_colonne);
                            plateau.supprimerJeton(lig,indice_colonne);
                        }  
                
                        if(plateau.presenceDesintegrateur(lig, indice_colonne)==true){
                            joueurCourant.obtenirDesintegrateur();
                            plateau.supprimerDesintegrateur(lig, indice_colonne);
                            lbl_j1_desint.setText(ListeJoueurs[0].NombreDesintegrateurs()+"");
                            lbl_j2_desint.setText(ListeJoueurs[1].NombreDesintegrateurs()+"");
                        }
                return true;                      
                }
                }
                return true;
    }

    public void joueurSuivant(){
        if (joueurCourant == ListeJoueurs[0]){
            joueurCourant = ListeJoueurs[1];
        } 
        else{
            joueurCourant  = ListeJoueurs[0];
        }
        lbl_jcourant.setText(joueurCourant.nomJoueur);
    }//GEN-LAST:event_btn_col_7ActionPerformed

    public void Rejouer(){
        btn_col_1.setEnabled(false);
        btn_col_2.setEnabled(false);
        btn_col_3.setEnabled(false);
        btn_col_4.setEnabled(false);
        btn_col_5.setEnabled(false);
        btn_col_6.setEnabled(false);
        btn_col_7.setEnabled(false);
        btm_start_partie.setEnabled(true);
    }

    private void btm_start_partieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btm_start_partieActionPerformed
        panneau_info_joueurs.setVisible(true);
        panneau_info_partie.setVisible(true);
        initialiserPartie();
        panneau_grille.repaint();
        //btm_start_partie.setEnabled(false);

    }//GEN-LAST:event_btm_start_partieActionPerformed

    private void nom_joueur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nom_joueur1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nom_joueur1ActionPerformed

    private void btn_col_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_2ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(1);
        if (plateau.colonneRemplie(1) == true) {
            btn_col_2.setEnabled(false);
        }
        joueurSuivant();
    }//GEN-LAST:event_btn_col_2ActionPerformed

    private void btn_col_5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_5ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(4);
        if (plateau.colonneRemplie(4) == true){
            btn_col_5.setEnabled(false);
        }
        joueurSuivant();
    }//GEN-LAST:event_btn_col_5ActionPerformed

    private void btn_col_6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_col_6ActionPerformed
        // TODO add your handling code here:
        jouerDansColonne(5);
        if (plateau.colonneRemplie(5) == true) {
            btn_col_6.setEnabled(false);
        }
        joueurSuivant();
    }//GEN-LAST:event_btn_col_6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btm_start_partie;
    private javax.swing.JButton btn_col_1;
    private javax.swing.JButton btn_col_2;
    private javax.swing.JButton btn_col_3;
    private javax.swing.JButton btn_col_4;
    private javax.swing.JButton btn_col_5;
    private javax.swing.JButton btn_col_6;
    private javax.swing.JButton btn_col_7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_j1_couleur;
    private javax.swing.JLabel lbl_j1_desint;
    private javax.swing.JLabel lbl_j1_nom;
    private javax.swing.JLabel lbl_j2_couleur;
    private javax.swing.JLabel lbl_j2_desint;
    private javax.swing.JLabel lbl_j2_nom;
    private javax.swing.JLabel lbl_jcourant;
    private javax.swing.JTextArea message;
    private javax.swing.JTextField nom_joueur1;
    private javax.swing.JTextField nom_joueur2;
    private javax.swing.JPanel panneau_creation_partie;
    private javax.swing.JPanel panneau_grille;
    private javax.swing.JPanel panneau_info_joueurs;
    private javax.swing.JPanel panneau_info_partie;
    // End of variables declaration//GEN-END:variables
}
