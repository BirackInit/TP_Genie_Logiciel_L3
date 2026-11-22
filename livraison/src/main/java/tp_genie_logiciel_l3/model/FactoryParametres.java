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
 * Pattern Factory Method : la classe abstraite déclare la méthode de création,
 * chaque sous-classe décide comment construire les paramètres.
 * Même principe que la fabrique de combattants proposée dans le sujet.
 */
public abstract class FactoryParametres {

    /** Crée un objet Parametres. Implémentée par les sous-classes.
     * @return  */
    public abstract Parametres creer();
}
