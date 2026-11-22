/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/**
 * Pattern Command : une action est un objet qu'on peut choisir, transmettre et exécuter.
 * Les méthodes statiques construisent les actions possibles (fabriques).
 */
@FunctionalInterface
public interface Action {

    /** Exécute l'action pour le robot r dans le jeu. */
    void executer(JeuCombat jeu, Robot r);

    /** Ne rien faire : n'économise rien, ne coûte rien. */
    static Action rienFaire() {
        return (jeu, r) -> { };
    }

    /** Active le bouclier (coûte de l'énergie). */
    static Action bouclier() {
        return (jeu, r) -> r.activerBouclier();
    }

    /** Déplacement d'une case, seulement si la case est libre. Coûte de l'énergie, puis déclenche la case. */
    static Action deplacer(Direction d) {
        return (jeu, r) -> {
            Position cible = r.getPosition().voisine(d);
            if (jeu.estLibre(cible)) {
                r.seDeplacer(cible);
                r.perdreEnergie(jeu.getParametres().getCoutDeplacement());
                jeu.surCase(r); // pastille ramassée ou arme déclenchée
            }
        };
    }

    /**
     * Tir dans une direction. Un tir consomme une munition, même s'il est arrêté.
     * Il s'arrête devant un mur ou au premier combattant touché, dans la limite de la portée.
     */
    static Action tir(Direction d) {
        return (jeu, r) -> {
            if (!r.utiliserTir()) {
                return; // plus de munitions
            }
            Position origine = r.getPosition();
            for (int k = 1; k <= jeu.getParametres().getPorteeTir(); k++) {
                Position cible = new Position(origine.x() + d.getDx() * k, origine.y() + d.getDy() * k);
                if (!jeu.getGrille().estDansLimites(cible)
                        || jeu.getGrille().getElement(cible.x(), cible.y()) instanceof Mur) {
                    return; // hors de la grille ou mur : le tir s'arrête
                }
                Robot touche = jeu.combattantEn(cible);
                if (touche != null) {
                    r.attaquer(touche);
                    return;
                }
            }
        };
    }

    /** Pose une mine sur une case voisine. visibleParTous indique si les autres joueurs la voient.
     * @param cible */
    static Action poserMine(Position cible, boolean visibleParTous) {
        return (jeu, r) -> {
            if (peutPoser(jeu, r, cible) && r.utiliserMine()) {
                jeu.getGrille().setElement(cible.x(), cible.y(), new Mine(cible, r, visibleParTous, jeu.getParametres()));
            }
        };
    }

    /** Pose une bombe sur une case voisine. */
    static Action poserBombe(Position cible, boolean visibleParTous) {
        return (jeu, r) -> {
            if (peutPoser(jeu, r, cible) && r.utiliserBombe()) {
                jeu.getGrille().setElement(cible.x(), cible.y(), new Bombe(cible, r, visibleParTous, jeu.getParametres()));
            }
        };
    }

    /** Une arme ne peut être posée que sur une case voisine du robot, et libre. */
    private static boolean peutPoser(JeuCombat jeu, Robot r, Position cible) {
        return r.getPosition().estVoisineImmediate(cible) && jeu.estPoseLibre(cible);
    }
}
