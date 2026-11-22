/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package tp_genie_logiciel_l3;

import java.util.ArrayList;
import java.util.List;
import tp_genie_logiciel_l3.model.*;
import tp_genie_logiciel_l3.strategie.*;

/**
 *
 * @author BirackInit
 */
/**
 * Tests en console. Le numéro passé en argument choisit le test :
 * 1 à 5 pour un test précis, sans argument ou 0 pour tous les tests.
 */
public class Main {

    public static void main(String[] args) {
        int numero = args.length > 0 ? Integer.parseInt(args[0]) : 0;
        if (numero == 0 || numero == 1) test1();
        if (numero == 0 || numero == 2) test2();
        if (numero == 0 || numero == 3) test3();
        if (numero == 0 || numero == 4) test4();
        if (numero == 0 || numero == 5) test5();
    }

    /** Test 1 : lecture des paramètres et calcul des cases voisines. */
    static void test1() {
        Parametres p = new FactoryParametresFile().creer();
        System.out.println("Energie initiale : " + p.getEnergieInitiale()); // 100
        Position pos = new Position(2, 2);
        System.out.println("Voisine droite : " + pos.voisine(Direction.DROITE)); // Position[x=3, y=2]
        System.out.println("(3,3) voisine immediate : " + pos.estVoisineImmediate(new Position(3, 3))); // true
    }
    
    
    
    
    

    /** Test 2 : visibilité des armes selon le propriétaire. */
    static void test2() {
        Parametres p = new FactoryParametresFile().creer();
        Robot a = new Robot("A", new Position(0, 0), null, p);
        Robot b = new Robot("B", new Position(5, 5), null, p);
        Mine publique = new Mine(new Position(1, 0), a, true, p);   // visible par tous
        Mine privee = new Mine(new Position(2, 0), a, false, p);    // visible par A seulement
        System.out.println("Mine publique vue par B : " + publique.estVisiblePour(b)); // true
        System.out.println("Mine privee vue par B : " + privee.estVisiblePour(b));     // false
        System.out.println("Mine privee vue par A : " + privee.estVisiblePour(a));     // true
    }
    
    

    /** Test 3 : munitions, bouclier et dégâts d'un combattant. */
    static void test3() {
        Parametres p = new FactoryParametresFile().creer();
        Combattant c = new Combattant(new Position(0, 0), p);
        System.out.println("Munitions de mines : " + c.getMunitionsMine()); // 3
        System.out.println("Utiliser une mine : " + c.utiliserMine());      // true
        c.activerBouclier();                                                 // coûte 5
        System.out.println("Energie apres bouclier : " + c.getEnergie());   // 95
        c.subirDegats(50);                                                   // annulé par le bouclier
        System.out.println("Energie apres attaque bloquee : " + c.getEnergie()); // 95
        c.desactiverBouclier();
        c.subirDegats(100);
        System.out.println("Vivant apres 100 de degats : " + c.estVivant()); // false
    }
    
    
    

    /** Test 4 : le proxy montre une mine privée à son propriétaire seulement. */
    static void test4() {
        Parametres p = new FactoryParametresFile().creer();
        Robot a = new Robot("A", new Position(0, 0), null, p);
        Robot b = new Robot("B", new Position(5, 5), null, p);
        GrilleTableau grille = new GrilleTableau(4, 4);
        grille.setElement(0, 0, new Mine(new Position(0, 0), a, false, p));
        System.out.println("Vue de A voit la mine : " + (new ProxyGrille(grille, a).getElement(0, 0) != null)); // true
        System.out.println("Vue de B voit la mine : " + (new ProxyGrille(grille, b).getElement(0, 0) != null)); // false
    }

    
    
    
    /** Test 5 : partie complète, avec compteur de notifications (Observer). */
    static void test5() {
        Parametres p = new FactoryParametresFile().creer();
        List<Robot> robots = new ArrayList<>();
        robots.add(new Robot("R1", new Position(0, 0), new StrategieAttaque(), p));
        robots.add(new Robot("R2", new Position(0, 0), new StrategieAleatoire(), p));
        JeuCombat jeu = new JeuCombat(new GrilleTableau(8, 8), robots, true, p);
        jeu.preparer();
        int[] notifications = {0};
        jeu.ajouterEcouteur(() -> notifications[0]++);
        for (int i = 0; i < 300 && !jeu.estTermine(); i++) {
            jeu.jouerTour();
        }
        System.out.println("Tours joues : " + jeu.getTour());
        System.out.println("Notifications recues : " + notifications[0]); // égal au nombre de tours
        System.out.println("Gagnant : " + (jeu.getGagnant() == null ? "nul" : jeu.getGagnant().getNom()));
    }
}






















