/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/** Mine : explose sur sa propre case (rayon 0). */
public class Mine extends Arme {

    public Mine(Position position, Combattant proprietaire, boolean visibleParTous, Parametres parametres) {
        super(position, proprietaire, visibleParTous, 0, parametres);
    }
}
