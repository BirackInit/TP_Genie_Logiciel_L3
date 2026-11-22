/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/** Contrat d'une stratégie : à partir de l'état du jeu, choisir l'action d'un robot (pattern Strategy). */
public interface StrategieRobot {
    Action choisirAction(Robot r, JeuCombat jeu);
}
