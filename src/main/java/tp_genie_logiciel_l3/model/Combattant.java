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
 * Un combattant : position, énergie, bouclier et munitions.
 * Les règles (coûts, dégâts, munitions de départ) viennent des Parametres reçus au constructeur.
 */

public class Combattant {

    private final Parametres parametres;
    private Position position;
    private int energie;
    private boolean bouclierActif = false;
    private int munitionsTir;
    private int munitionsMine;
    private int munitionsBombe;

    public Combattant(Position position, Parametres parametres) {
        this.parametres = parametres;
        this.position = position;
        this.energie = parametres.getEnergieInitiale();
        this.munitionsTir = parametres.getMunitionsTir();
        this.munitionsMine = parametres.getMunitionsMine();
        this.munitionsBombe = parametres.getMunitionsBombe();
    }

    public Position getPosition() { return position; }
     /** @return l'énergie restante du combattant */
    public int getEnergie() { return energie; }
    /** Vivant tant que l'énergie est strictement positive. */
    public boolean estVivant() { return energie > 0; }
    public boolean isBouclierActif() { return bouclierActif; }
    public int getMunitionsTir() { return munitionsTir; }
    public int getMunitionsMine() { return munitionsMine; }
    public int getMunitionsBombe() { return munitionsBombe; }

    public void seDeplacer(Position p) {
        this.position = p;
    }

    /** Tire sur une cible : elle subit les dégâts de tir. */
    public void attaquer(Combattant cible) {
        cible.subirDegats(parametres.getDegatsTir());
    }

    /** Tir ou explosion : le bouclier actif annule les dégâts. */
    public void subirDegats(int degats) {
        if (!bouclierActif) {
            energie -= degats;
        }
    }

    /** Coût direct (déplacement, bouclier), non bloqué par le bouclier. */
    public void perdreEnergie(int cout) {
        energie -= cout;
    }
    /** Ajoute de l'énergie au combattant (pastille ramassée). */
    public void gagnerEnergie(int gain) {
        energie += gain;
    }

    /** Active le bouclier et paie son coût. Sans effet s'il est déjà actif. */
    public void activerBouclier() {
        if (bouclierActif) {
            return;
        }
        perdreEnergie(parametres.getCoutBouclier());
        bouclierActif = true;
    }

    public void desactiverBouclier() {
        bouclierActif = false;
    }

    /** Consomme un tir. Retourne false s'il n'en reste plus. */
    public boolean utiliserTir() {
        if (munitionsTir <= 0) {
            return false;
        }
        munitionsTir--;
        return true;
    }

    /** Consomme une mine. Retourne false s'il n'en reste plus. */
    public boolean utiliserMine() {
        if (munitionsMine <= 0) {
            return false;
        }
        munitionsMine--;
        return true;
    }

    /** Consomme une bombe. Retourne false s'il n'en reste plus. */
    public boolean utiliserBombe() {
        if (munitionsBombe <= 0) {
            return false;
        }
        munitionsBombe--;
        return true;
    }
}
