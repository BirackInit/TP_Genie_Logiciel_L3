/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/**
 * Les 4 directions de déplacement autorisées par le sujet.
 * Chaque direction est un décalage (dx, dy) sur la grille :
 * y augmente vers le bas, donc HAUT a dy = -1.
 */
public enum Direction {
    HAUT(0, -1), BAS(0, 1), GAUCHE(-1, 0), DROITE(1, 0);

    private final int dx; // décalage horizontal
    private final int dy; // décalage vertical

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() { return dx; }
    public int getDy() { return dy; }
}
