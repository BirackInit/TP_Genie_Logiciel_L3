/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

/**
 *
 * @author BirackInit
 */
/** Pastille d'énergie : donne sa quantité au combattant qui se place dessus, puis disparaît. */
public class Pastille extends Element {

    private final int quantite; // énergie apportée

    public Pastille(Position position, int quantite) {
        super(position);
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }
}
