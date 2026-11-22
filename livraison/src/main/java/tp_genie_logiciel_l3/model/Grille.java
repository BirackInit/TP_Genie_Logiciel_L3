/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tp_genie_logiciel_l3.model;

import tp_genie_logiciel_l3.observer.ModeleEcoutable;

/**
 *
 * @author BirackInit
 */
/**
 * Contrat d'une grille : lire et écrire des éléments par leurs coordonnées.
 * Plusieurs implémentations sont possibles : la grille réelle, et le proxy d'un joueur.
 */
public interface Grille extends ModeleEcoutable {

    int getLargeur();

    int getHauteur();

    /** Élément de la case, ou null si la case est vide. */
    Element getElement(int x, int y);

    /** Place un élément sur la case (null pour vider la case). */
    void setElement(int x, int y, Element element);

    /** Vrai si la position est à l'intérieur de la grille. */
    default boolean estDansLimites(Position p) {
        return p.x() >= 0 && p.x() < getLargeur() && p.y() >= 0 && p.y() < getHauteur();
    }
}
