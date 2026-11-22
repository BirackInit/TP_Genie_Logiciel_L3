/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/**
 * Classe mère de tout ce qui peut occuper une case : mur, pastille, mine, bombe.
 * La grille stocke donc un seul type d'objet.
 */
public abstract class Element {

    protected final Position position; // case où se trouve l'élément

    protected Element(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
}
