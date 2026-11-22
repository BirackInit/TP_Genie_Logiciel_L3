/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.strategie;

import tp_genie_logiciel_l3.model.StrategieRobot;
import tp_genie_logiciel_l3.model.Action;
import java.util.Random;
import tp_genie_logiciel_l3.model.*;

/**
 *
 * @author BirackInit
 */

/** Stratégie de référence : une action au hasard parmi 4 déplacements, le bouclier et l'inaction. */
public class StrategieAleatoire implements StrategieRobot {

    private final Random rnd = new Random();

    /**
     *
     * @param r
     * @param jeu
     * @return
     */
    @Override
    public Action choisirAction(Robot r, JeuCombat jeu) {
        return switch (rnd.nextInt(6)) {
            case 0 -> Action.deplacer(Direction.HAUT);
            case 1 -> Action.deplacer(Direction.BAS);
            case 2 -> Action.deplacer(Direction.GAUCHE);
            case 3 -> Action.deplacer(Direction.DROITE);
            case 4 -> Action.bouclier();
            default -> Action.rienFaire();
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        };
    }
}