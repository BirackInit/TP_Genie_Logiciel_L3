/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

/**
 *
 * @author birackinit
 */
public class TestObserver {

    // Modèle de test
    static class MonModele extends AbstractModeleEcoutable {

        private int valeur = 0;

        public void modifierValeur(int nouvelleValeur) {
            this.valeur = nouvelleValeur;
            notifierEcouteurs();
        }

        public int getValeur() {
            return valeur;
        }
    }

    // Observateur de test
    static class MonEcouteur implements EcouteurModele {

        @Override
        public void modeleMisAJour() {
            System.out.println("Notification reçue !");
        }
    }

    public static void main(String[] args) {

        MonModele modele = new MonModele();

        MonEcouteur ecouteur = new MonEcouteur();

        // 1. Ajouter un observateur
        modele.ajouterEcouteur(ecouteur);

        System.out.println("Modification 1 :");
        modele.modifierValeur(10);

        System.out.println("Valeur : " + modele.getValeur());

        // 2. Retirer l'observateur
        modele.retirerEcouteur(ecouteur);

        System.out.println("\nModification 2 :");
        modele.modifierValeur(20);

        System.out.println("Valeur : " + modele.getValeur());
    }
}