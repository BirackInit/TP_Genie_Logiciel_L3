/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.vue;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import tp_genie_logiciel_l3.controleur.ControleurSuivant;
import tp_genie_logiciel_l3.model.*;
import tp_genie_logiciel_l3.observer.EcouteurModele;

/**
 *
 * @author BirackInit
 */
/**
 * Vue complète : affiche toutes les armes (y compris celles invisibles pour les joueurs),
 * un tableau des combattants et le bouton « Suivant ».
 */
public class FramePrincipale extends JFrame implements EcouteurModele {

    private final JeuCombat jeu;
    private final JLabel statut = new JLabel("Tour 0");
    // Modèle du tableau : c'est le DefaultTableModel standard de Swing, rempli à chaque notification
    private final DefaultTableModel tableau = new DefaultTableModel(
            new Object[] {"Nom", "Énergie", "Tirs", "Mines", "Bombes", "Bouclier"}, 0);

    public FramePrincipale(JeuCombat jeu, Grille grille) {
        super("Jeu de combat : vue complète");
        this.jeu = jeu;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(new JScrollPane(new VueGrille(jeu, grille)), BorderLayout.CENTER); // grille au centre
        add(new JScrollPane(new JTable(tableau)), BorderLayout.EAST);          // tableau à droite

        JButton suivant = new JButton("Suivant");
        suivant.addActionListener(new ControleurSuivant(jeu)); // le clic joue un tour

        // Bas de la fenêtre : statut (tour ou gagnant) et bouton
        JPanel bas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bas.add(statut);
        bas.add(suivant);
        add(bas, BorderLayout.SOUTH);

        jeu.ajouterEcouteur(this); // la fenêtre se met à jour à chaque tour
        modeleMisAJour();          // affichage initial
        pack();
        setLocationRelativeTo(null);
    }

    /** Recalcule le tableau et le statut à partir du modèle. */
    @Override
    public void modeleMisAJour() {
        tableau.setRowCount(0); // on vide le tableau avant de le remplir
        for (Robot r : jeu.getCombattants()) {
            tableau.addRow(new Object[] {
                r.getNom(),
                r.getEnergie(),
                r.getMunitionsTir(),
                r.getMunitionsMine(),
                r.getMunitionsBombe(),
                r.isBouclierActif() ? "Oui" : "Non"
            });
        }

        if (jeu.estTermine()) {
            Robot gagnant = jeu.getGagnant();
            statut.setText(gagnant == null ? "Partie nulle" : "Gagnant : " + gagnant.getNom());
        } else {
            statut.setText("Tour " + jeu.getTour());
        }
    }
}
