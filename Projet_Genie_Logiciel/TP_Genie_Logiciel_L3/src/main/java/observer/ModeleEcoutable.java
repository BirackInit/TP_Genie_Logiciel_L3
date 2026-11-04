/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package observer;

/**
 *
 * @author birackinit
 * 
 * Cette interface définit les méthodes permettant de gérer les observateurs.
 */
public interface ModeleEcoutable {
    
    void ajouterEcouteur(EcouteurModele ecouteur);

    void retirerEcouteur(EcouteurModele ecouteur);

    void notifierEcouteurs(); 
    
}
