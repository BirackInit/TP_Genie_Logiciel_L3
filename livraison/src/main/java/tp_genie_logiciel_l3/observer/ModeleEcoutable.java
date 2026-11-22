/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package tp_genie_logiciel_l3.observer;

/**
 *
 * @author BirackInit
 */
/** Contrat d'un modèle observable : on peut y ajouter ou retirer des écouteurs. */
public interface ModeleEcoutable {
    void ajouterEcouteur(EcouteurModele e);
    void retirerEcouteur(EcouteurModele e);
}
