/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

import tp_genie_logiciel_l3.observer.AbstractModeleEcoutable;

/**
 *
 * @author BirackInit
 */
/**
 * Implémentation réelle de la grille : un tableau à deux dimensions.
 * Chaque modification prévient les écouteurs (les vues).
 */
public class GrilleTableau extends AbstractModeleEcoutable implements Grille {

    private final int largeur;
    private final int hauteur;
    private final Element[][] elements; // elements[x][y] : contenu de la case

    public GrilleTableau(int largeur, int hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.elements = new Element[largeur][hauteur]; // toutes les cases sont vides (null)
    }

    @Override
    public int getLargeur() { return largeur; }

    @Override
    public int getHauteur() { return hauteur; }

    @Override
    public Element getElement(int x, int y) {
        return elements[x][y];
    }

    @Override
    public void setElement(int x, int y, Element element) {
        elements[x][y] = element;
        fireChangement(); // les vues se redessinent
    }
}
