/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/**
 * Coordonnées (x, y) d'une case. Un record est immuable :
 * une position ne peut pas être modifiée par accident.
 */
public record Position(int x, int y) {

    /** La case voisine dans la direction donnée (un seul pas). */
    public Position voisine(Direction d) {
        return new Position(x + d.getDx(), y + d.getDy());
    }

    /** Nombre minimal de pas entre les deux cases, diagonales comprises (distance de Chebyshev). */
    public int distanceMax(Position autre) {
        return Math.max(Math.abs(x - autre.x()), Math.abs(y - autre.y()));
    }

    /** Vrai si l'autre case est l'une des 8 cases qui entourent celle-ci. */
    public boolean estVoisineImmediate(Position autre) {
        return distanceMax(autre) == 1;
    }
}

