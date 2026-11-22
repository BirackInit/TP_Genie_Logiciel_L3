/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import tp_genie_logiciel_l3.observer.AbstractModeleEcoutable;

/**
 *
 * @author BirackInit
 */
/**
 * Moteur du jeu : prépare la grille, fait jouer les tours, applique les effets des cases,
 * retire les combattants morts et détermine le gagnant.
 * Hérite de AbstractModeleEcoutable : il prévient les vues après chaque tour.
 */
public class JeuCombat extends AbstractModeleEcoutable {

    private static final double PROBA_MUR = 0.1;      // chance qu'une case soit un mur
    private static final double PROBA_PASTILLE = 0.1; // chance qu'une case (non mur) soit une pastille
    private static final int VALEUR_PASTILLE = 10;    // énergie apportée par une pastille

    private final Grille grille;
    private final List<Robot> combattants;  // combattants encore dans la partie
    private final boolean ordreAleatoire;   // true : ordre mélangé à chaque tour
    private final Parametres parametres; // règles du jeu, reçues à la création (injection)
    private final Random rnd = new Random();
    private int tour = 0;                   // numéro du tour courant

    public JeuCombat(Grille grille, List<Robot> combattants, boolean ordreAleatoire, Parametres parametres) {
        this.grille = grille;
        this.parametres = parametres;
        this.combattants = new ArrayList<>(combattants); // copie de la liste reçue
        this.ordreAleatoire = ordreAleatoire;
    }

    public Grille getGrille() { return grille; }
    public Parametres getParametres() { return parametres; }
    public List<Robot> getCombattants() { return combattants; }
    public int getTour() { return tour; }

    /** Place au hasard les murs, les pastilles, puis les combattants sur des cases libres. */
    public void preparer() {
        // 1. Murs et pastilles, case par case
        for (int x = 0; x < grille.getLargeur(); x++) {
            for (int y = 0; y < grille.getHauteur(); y++) {
                Position p = new Position(x, y);
                if (rnd.nextDouble() < PROBA_MUR) {
                    grille.setElement(x, y, new Mur(p));
                } else if (rnd.nextDouble() < PROBA_PASTILLE) {
                    grille.setElement(x, y, new Pastille(p, VALEUR_PASTILLE));
                }
            }
        }
        // 2. Positions de départ : tirées au hasard jusqu'à tomber sur une case vide et non prise
        List<Position> prises = new ArrayList<>();
        for (Robot r : combattants) {
            Position p;
            do {
                p = new Position(rnd.nextInt(grille.getLargeur()), rnd.nextInt(grille.getHauteur()));
            } while (grille.getElement(p.x(), p.y()) != null || prises.contains(p));
            prises.add(p);
            r.seDeplacer(p);
        }
    }

    /** Un tour complet : chaque combattant joue, les bombes avancent, les boucliers expirent, les morts sortent. */
    public void jouerTour() {
        tour++;
        // Un bouclier activé pendant ce tour doit protéger le tour suivant :
        // on ne désactive à la fin que ceux qui étaient déjà actifs au début
        List<Robot> boucliersDebut = combattants.stream().filter(Combattant::isBouclierActif).toList();

        // Ordre de jeu : fixe, ou mélangé si l'option est activée
        List<Robot> ordre = new ArrayList<>(combattants);
        if (ordreAleatoire) {
            Collections.shuffle(ordre, rnd);
        }
        for (Robot r : ordre) {
            if (r.estVivant()) {
                r.jouerUnCoup(this);
            }
        }

        exploserBombes();                                   // avance les compte à rebours
        boucliersDebut.forEach(Combattant::desactiverBouclier); // fin du bouclier
        combattants.removeIf(r -> !r.estVivant());          // retire les morts
        fireChangement();                                // prévient les vues
    }

    /** La partie est finie quand il reste au plus un combattant.
     * @return  */
    public boolean estTermine() {
        return combattants.size() <= 1;
    }

    /** Le dernier survivant, ou null si la partie se termine sans gagnant. */
    public Robot getGagnant() {
        return estTermine() && !combattants.isEmpty() ? combattants.get(0) : null;
    }

    /** Un combattant peut se placer sur la case : elle est dans la grille, sans mur, sans autre combattant. */
    public boolean estLibre(Position p) {
        return grille.estDansLimites(p)
                && !(grille.getElement(p.x(), p.y()) instanceof Mur)
                && combattantEn(p) == null;
    }

    /** Une arme peut être posée sur la case : dans la grille, vide, sans combattant. */
    public boolean estPoseLibre(Position p) {
        return grille.estDansLimites(p)
                && grille.getElement(p.x(), p.y()) == null
                && combattantEn(p) == null;
    }

    /** Le combattant vivant présent sur cette case, ou null. */
    public Robot combattantEn(Position p) {
        return combattants.stream()
                .filter(r -> r.estVivant() && r.getPosition().equals(p))
                .findFirst()
                .orElse(null);
    }

    /** Appelée quand un robot arrive sur une case : pastille ramassée, ou arme déclenchée.
     * @param r */
    public void surCase(Robot r) {
        Position p = r.getPosition();
        Element e = grille.getElement(p.x(), p.y());
        if (e instanceof Pastille pastille) {
            r.gagnerEnergie(pastille.getQuantite());
            grille.setElement(p.x(), p.y(), null); // la pastille disparaît
        } else if (e instanceof Arme arme) {
            arme.exploser(this); // mine ou bombe : explose sous les pieds du robot
        }
    }

    /** Fait avancer chaque bombe d'un tour ; celle dont le compte est à zéro explose. */
    private void exploserBombes() {
        for (int x = 0; x < grille.getLargeur(); x++) {
            for (int y = 0; y < grille.getHauteur(); y++) {
                if (grille.getElement(x, y) instanceof Bombe bombe && bombe.tick()) {
                    bombe.exploser(this);
                }
            }
        }
    }
    
}
