/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author BirackInit
 */
/**
 * Crée les Parametres à partir de parametres.properties (dossier courant).
 * Si le fichier est absent, les valeurs par défaut sont utilisées.
 */
public class FactoryParametresFile extends FactoryParametres {

    private final Properties props = new Properties();

    public FactoryParametresFile() {
        try (InputStream in = new FileInputStream("parametres.properties")) {
            props.load(in);
        } catch (IOException e) {
            // Fichier absent : props reste vide, donc les valeurs par défaut s'appliquent
        }
    }

    @Override
    public Parametres creer() {
        return new Parametres(
                lire("energie.initiale", 100),
                lire("cout.deplacement", 1),
                lire("cout.bouclier", 5),
                lire("degats.tir", 10),
                lire("degats.explosion", 30),
                lire("portee.tir", 5),
                lire("delai.bombe", 3),
                lire("taille.grille", 10),
                lire("munitions.tir", 5),
                lire("munitions.mine", 3),
                lire("munitions.bombe", 2));
    }

    /** Lit une clé entière, ou prend la valeur par défaut si elle n'existe pas. */
    private int lire(String cle, int valeurParDefaut) {
        return Integer.parseInt(props.getProperty(cle, String.valueOf(valeurParDefaut)).trim());
    }
}
