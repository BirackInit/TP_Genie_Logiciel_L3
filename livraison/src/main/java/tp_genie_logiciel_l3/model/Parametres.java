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
 * Valeurs des règles du jeu. Simple objet de données : il n'est pas unique
 * et n'a pas de méthode statique. Il est créé par une fabrique puis transmis
 * aux classes qui en ont besoin (injection par le constructeur).
 */
public class Parametres {

    private final int energieInitiale;
    private final int coutDeplacement;
    private final int coutBouclier;
    private final int degatsTir;
    private final int degatsExplosion;
    private final int porteeTir;
    private final int delaiBombe;
    private final int tailleGrille;
    private final int munitionsTir;
    private final int munitionsMine;
    private final int munitionsBombe;

    public Parametres(int energieInitiale, int coutDeplacement, int coutBouclier,
                      int degatsTir, int degatsExplosion, int porteeTir, int delaiBombe,
                      int tailleGrille, int munitionsTir, int munitionsMine, int munitionsBombe) {
        this.energieInitiale = energieInitiale;
        this.coutDeplacement = coutDeplacement;
        this.coutBouclier = coutBouclier;
        this.degatsTir = degatsTir;
        this.degatsExplosion = degatsExplosion;
        this.porteeTir = porteeTir;
        this.delaiBombe = delaiBombe;
        this.tailleGrille = tailleGrille;
        this.munitionsTir = munitionsTir;
        this.munitionsMine = munitionsMine;
        this.munitionsBombe = munitionsBombe;
    }

    public int getEnergieInitiale() { return energieInitiale; }
    public int getCoutDeplacement() { return coutDeplacement; }
    public int getCoutBouclier() { return coutBouclier; }
    public int getDegatsTir() { return degatsTir; }
    public int getDegatsExplosion() { return degatsExplosion; }
    public int getPorteeTir() { return porteeTir; }
    public int getDelaiBombe() { return delaiBombe; }
    public int getTailleGrille() { return tailleGrille; }
    public int getMunitionsTir() { return munitionsTir; }
    public int getMunitionsMine() { return munitionsMine; }
    public int getMunitionsBombe() { return munitionsBombe; }
}
