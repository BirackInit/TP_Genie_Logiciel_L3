/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/** Bombe : compte à rebours en tours, puis explosion sur les 8 cases voisines (rayon 1). */
public class Bombe extends Arme {

    private int compteARebours;

    public Bombe(Position position, Combattant proprietaire, boolean visibleParTous, Parametres parametres) {
        super(position, proprietaire, visibleParTous, 1, parametres);
        this.compteARebours = parametres.getDelaiBombe();
    }

    public int getCompteARebours() {
        return compteARebours;
    }

    /** Un tour écoulé. Retourne true quand le compte à rebours atteint zéro. */
    public boolean tick() {
        return --compteARebours <= 0;
    }
}
