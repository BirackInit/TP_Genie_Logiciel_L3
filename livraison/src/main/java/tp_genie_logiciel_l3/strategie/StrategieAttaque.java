/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.strategie;

import tp_genie_logiciel_l3.model.StrategieRobot;
import tp_genie_logiciel_l3.model.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import tp_genie_logiciel_l3.model.*;

/**
 *
 * @author BirackInit
 */
/**
 * Stratégie plus fine, par ordre de priorité :
 * 1. énergie basse : se protéger avec le bouclier ;
 * 2. ennemi aligné et à portée : tirer ;
 * 3. parfois : poser une mine à côté de soi ;
 * 4. sinon : se rapprocher de l'ennemi le plus proche.
 */
public class StrategieAttaque implements StrategieRobot {

    private static final int SEUIL_ENERGIE = 30; // en dessous, le robot se protège
    private static final int PROBA_MINE = 30;    // probabilité (%) de poser une mine

    private final Random rnd = new Random();

    
    
    @Override
    public Action choisirAction(Robot r, JeuCombat jeu) {
        // 1. Protection si l'énergie est basse
        if (r.getEnergie() < SEUIL_ENERGIE && !r.isBouclierActif()) {
            return Action.bouclier();
        }

        // Sans ennemi vivant, il n'y a rien à faire
        Robot cible = ennemiLePlusProche(r, jeu);
        if (cible == null) {
            return Action.rienFaire();
        }

        Position p = r.getPosition();
        Position q = cible.getPosition();
        int portee = jeu.getParametres().getPorteeTir();

        // 2. Tir horizontal ou vertical si l'ennemi est aligné et à portée
        if (p.y() == q.y() && Math.abs(p.x() - q.x()) <= portee) {
            return Action.tir(q.x() > p.x() ? Direction.DROITE : Direction.GAUCHE);
        }
        if (p.x() == q.x() && Math.abs(p.y() - q.y()) <= portee) {
            return Action.tir(q.y() > p.y() ? Direction.BAS : Direction.HAUT);
        }

        // 3. Pose d'une mine, avec une probabilité de 30 %
        if (r.getMunitionsMine() > 0 && rnd.nextInt(100) < PROBA_MINE) {
            Position voisine = caseVoisineLibre(p, jeu);
            if (voisine != null) {
                return Action.poserMine(voisine, false);
            }
        }

        // 4. Se rapprocher de l'ennemi
        return versEnnemi(p, q);
    }

    /** Le combattant vivant le plus proche (distance de Chebyshev), ou null s'il n'y en a pas. */
    private Robot ennemiLePlusProche(Robot r, JeuCombat jeu) {
        Robot meilleur = null;
        int meilleureDistance = Integer.MAX_VALUE;
        for (Robot autre : jeu.getCombattants()) {
            if (autre == r || !autre.estVivant()) {
                continue;
            }
            int distance = r.getPosition().distanceMax(autre.getPosition());
            if (distance < meilleureDistance) {
                meilleureDistance = distance;
                meilleur = autre;
            }
        }
        return meilleur;
    }

    /** Une case voisine libre, choisie au hasard parmi les 8 possibles, ou null. */
    private Position caseVoisineLibre(Position p, JeuCombat jeu) {
        List<Position> libres = new ArrayList<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) {
                    continue; // la case du robot lui-même
                }
                Position n = new Position(p.x() + dx, p.y() + dy);
                if (jeu.estPoseLibre(n)) {
                    libres.add(n);
                }
            }
        }
        return libres.isEmpty() ? null : libres.get(rnd.nextInt(libres.size()));
    }

    /** Déplacement dans la direction où l'écart est le plus grand (horizontal ou vertical). */
    private Action versEnnemi(Position p, Position q) {
        Direction dir;
        if (Math.abs(q.x() - p.x()) >= Math.abs(q.y() - p.y())) {
            dir = q.x() > p.x() ? Direction.DROITE : Direction.GAUCHE;
        } else {
            dir = q.y() > p.y() ? Direction.BAS : Direction.HAUT;
        }
        return Action.deplacer(dir);
    }
}
