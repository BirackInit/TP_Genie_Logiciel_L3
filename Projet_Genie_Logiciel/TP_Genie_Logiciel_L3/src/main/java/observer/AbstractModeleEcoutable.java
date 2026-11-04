/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package observer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author birackinit
 */
public abstract class AbstractModeleEcoutable implements ModeleEcoutable{
    private final List<EcouteurModele> ecouteurs =
            new ArrayList<>();

    @Override
    public void ajouterEcouteur(EcouteurModele ecouteur) {
        if (ecouteur != null && !ecouteurs.contains(ecouteur)) {
            ecouteurs.add(ecouteur);
        }
    }
    

    @Override
    public void retirerEcouteur(EcouteurModele ecouteur) {
        ecouteurs.remove(ecouteur);
    }

    @Override
    public void notifierEcouteurs() {
        for (EcouteurModele ecouteur :
                new ArrayList<>(ecouteurs)) {
            ecouteur.modeleMisAJour();
        }
            
        }
        
}