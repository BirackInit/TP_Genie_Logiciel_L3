/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tp_genie_logiciel_l3.model.JeuCombat;

/**
 *
 * @author BirackInit
 */
/** Contrôleur (MVC) : traduit le clic sur « Suivant » en un tour de jeu. */
public class ControleurSuivant implements ActionListener {

    private final JeuCombat jeu;

    public ControleurSuivant(JeuCombat jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!jeu.estTermine()) { // on ne joue plus une fois la partie finie
            jeu.jouerTour();
        }
    }
}