/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.vue;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import tp_genie_logiciel_l3.model.*;
import tp_genie_logiciel_l3.observer.EcouteurModele;

/**
 *
 * @author BirackInit
 */
/**
 * Vue d'un joueur : la grille passe par un ProxyGrille propre à ce joueur,
 * donc il ne voit que les armes qui le concernent.
 */
public class FrameJoueur extends JFrame implements EcouteurModele {

    private final JeuCombat jeu;
    private final Robot joueur;
    private final JLabel statut = new JLabel();

    public FrameJoueur(JeuCombat jeu, Grille grille, Robot joueur) {
        super("Vue de " + joueur.getNom());
        this.jeu = jeu;
        this.joueur = joueur;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // fermer cette fenêtre n'arrête pas la partie
        setLayout(new BorderLayout());

        Grille vue = new ProxyGrille(grille, joueur); // le filtre des armes pour ce joueur
        add(new JScrollPane(new VueGrille(jeu, vue)), BorderLayout.CENTER);
        add(statut, BorderLayout.SOUTH);

        jeu.ajouterEcouteur((EcouteurModele) this);
        modeleMisAJour();
        pack();
    }

    /** Affiche l'énergie du joueur et le tour, ou un message s'il est éliminé. */
    @Override
    public void modeleMisAJour() {
        if (joueur.estVivant()) {
            statut.setText("Énergie : " + joueur.getEnergie() + " | Tour " + jeu.getTour());
        } else {
            statut.setText("Éliminé au tour " + jeu.getTour());
        }
    }
}
