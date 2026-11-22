/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BirackInit
 */
/**
 * Pattern Observer : gère la liste des écouteurs une seule fois pour toutes les classes du modèle.
 * La sous-classe appelle fireChangement() après chaque changement d'état.
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable {

    private final List<EcouteurModele> ecouteurs = new ArrayList<>();

    @Override
    public void ajouterEcouteur(EcouteurModele e) {
        ecouteurs.add(e);
    }

    @Override
    public void retirerEcouteur(EcouteurModele e) {
        ecouteurs.remove(e);
    }

    /** Prévient chaque écouteur qu'un changement a eu lieu. */
    protected void fireChangement() {
        for (EcouteurModele e : ecouteurs) {
            e.modeleMisAJour();
        }
    }
}
