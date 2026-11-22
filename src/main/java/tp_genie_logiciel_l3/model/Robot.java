/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/** Combattant piloté par l'ordinateur : sa stratégie choisit l'action (Strategy). */
public class Robot extends Combattant {

    private final String nom;
    private final StrategieRobot strategie;

    public Robot(String nom, Position position, StrategieRobot strategie, Parametres parametres) {
        super(position, parametres);
        this.nom = nom;
        this.strategie = strategie;
    }

    public String getNom() {
        return nom;
    }

    /** Demande une action à la stratégie puis l'exécute (Command).
     * @param jeu */
    public void jouerUnCoup(JeuCombat jeu) {
        strategie.choisirAction(this, jeu).executer(jeu, this);
    }
}
