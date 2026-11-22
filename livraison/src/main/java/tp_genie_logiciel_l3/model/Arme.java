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
 * Classe mère des armes posées sur la grille (mine, bombe).
 * Une arme connaît son propriétaire, sa visibilité, son rayon et les paramètres.
 */
public abstract class Arme extends Element {

    private final Combattant proprietaire;
    private final boolean visibleParTous;
    private final int rayon; // 0 = sa case seule, 1 = les 8 voisines
    private final Parametres parametres;

    protected Arme(Position position, Combattant proprietaire, boolean visibleParTous,
                   int rayon, Parametres parametres) {
        super(position);
        this.proprietaire = proprietaire;
        this.visibleParTous = visibleParTous;
        this.rayon = rayon;
        this.parametres = parametres;
    }

    /** Vrai si ce combattant peut voir l'arme (utilisé par le proxy). */
    public boolean estVisiblePour(Combattant c) {
        return visibleParTous || c == proprietaire;
    }

    /** Blesse les combattants dans le rayon, puis l'arme disparaît de la grille. */
    public void exploser(JeuCombat jeu) {
        int degats = parametres.getDegatsExplosion();
        for (Robot r : jeu.getCombattants()) {
            if (r.estVivant() && r.getPosition().distanceMax(position) <= rayon) {
                r.subirDegats(degats);
            }
        }
        jeu.getGrille().setElement(position.x(), position.y(), null);
    }
}
