/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import tp_genie_logiciel_l3.model.*;
import tp_genie_logiciel_l3.strategie.*;
import tp_genie_logiciel_l3.vue.*;


/**
 *
 * @author BirackInit
 */
/** Point d'entrée graphique : crée les paramètres par la fabrique, assemble le jeu, ouvre les fenêtres. */
public class Lanceur {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 1. Paramètres créés par la fabrique (Factory Method)
            Parametres p = new FactoryParametresFile().creer();

            // 2. Grille et robots, en transmettant les paramètres à chaque objet qui en a besoin
            Grille grille = new GrilleTableau(p.getTailleGrille(), p.getTailleGrille());
            List<Robot> robots = new ArrayList<>();
            robots.add(new Robot("R1", new Position(0, 0), new StrategieAttaque(), p));
            robots.add(new Robot("R2", new Position(0, 0), new StrategieAleatoire(), p));
            robots.add(new Robot("R3", new Position(0, 0), new StrategieAttaque(), p));

            // 3. Moteur du jeu, puis placement initial
            JeuCombat jeu = new JeuCombat(grille, robots, true, p);
            jeu.preparer();

            // 4. Fenêtres : vue complète et une vue par joueur
            new FramePrincipale(jeu, grille).setVisible(true);
            int decalage = 0;
            for (Robot r : robots) {
                FrameJoueur vue = new FrameJoueur(jeu, grille, r);
                vue.setLocation(60 + decalage, 60 + decalage);
                vue.setVisible(true);
                decalage += 40;
            }
        });
    }
}
