/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

import tp_genie_logiciel_l3.observer.EcouteurModele;

/**
 *
 * @author BirackInit
 */
/**
 * Proxy (pattern Proxy) : donne à un joueur une vue partielle de la grille.
 * Une arme invisible pour ce joueur est présentée comme une case vide.
 * Le proxy ne copie rien : il lit la grille réelle à chaque appel.
 */
public class ProxyGrille implements Grille {

    private final Grille reelle;      // la vraie grille
    private final Combattant joueur;  // le joueur qui regarde

    public ProxyGrille(Grille reelle, Combattant joueur) {
        this.reelle = reelle;
        this.joueur = joueur;
    }

    @Override
    public int getLargeur() { return reelle.getLargeur(); }

    @Override
    public int getHauteur() { return reelle.getHauteur(); }

    /** Renvoie l'élément réel, sauf si c'est une arme que ce joueur ne doit pas voir (alors null). */
    @Override
    public Element getElement(int x, int y) {
        Element e = reelle.getElement(x, y);
        if (e instanceof Arme arme && !arme.estVisiblePour(joueur)) {
            return null;
        }
        return e;
    }

    /** Lecture seule : un joueur ne peut pas modifier la grille. */
    @Override
    public void setElement(int x, int y, Element element) {
        throw new UnsupportedOperationException("Lecture seule pour un joueur");
    }

    /** Les écouteurs sont enregistrés auprès de la grille réelle. */
    @Override
    public void ajouterEcouteur(EcouteurModele e) { reelle.ajouterEcouteur(e); }

    @Override
    public void retirerEcouteur(EcouteurModele e) { reelle.retirerEcouteur(e); }
}
