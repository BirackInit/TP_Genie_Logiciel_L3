/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tp_genie_logiciel_l3.vue;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import tp_genie_logiciel_l3.model.*;
import tp_genie_logiciel_l3.observer.EcouteurModele;

/**
 *
 * @author BirackInit
 */
/**
 * Dessine la grille (style Bomberman). Elle est observatrice : elle se redessine
 * à chaque notification. Elle ne modifie jamais le modèle.
 */
public class VueGrille extends JPanel implements EcouteurModele {

    private static final int T = 48; // taille d'une case en pixels

    // Couleurs du sol, de la pierre et de la peau
    private static final Color SOL_1 = new Color(214, 198, 146);
    private static final Color SOL_2 = new Color(200, 184, 130);
    private static final Color CONTOUR_SOL = new Color(170, 150, 100);
    private static final Color PIERRE = new Color(120, 116, 104);
    private static final Color PIERRE_CLAIRE = new Color(165, 160, 146);
    private static final Color PIERRE_OMBRE = new Color(80, 76, 68);
    private static final Color PEAU = new Color(245, 205, 170);

    // Une couleur par robot (le 6e reprend la 1re)
    private static final Color[] COULEURS_ROBOTS = {
        new Color(40, 110, 230),
        new Color(220, 50, 50),
        new Color(60, 170, 80),
        new Color(230, 170, 40),
        new Color(150, 80, 200)
    };

    private final Grille grille;   // peut être la vraie grille ou un proxy
    private final JeuCombat jeu;   // pour lister les combattants

    public VueGrille(JeuCombat jeu, Grille grille) {
        this.jeu = jeu;
        this.grille = grille;
        grille.ajouterEcouteur(this); // se redessine si la grille change
        jeu.ajouterEcouteur(this);    // ou si le jeu avance
        setPreferredSize(new Dimension(grille.getLargeur() * T, grille.getHauteur() * T));
        setBackground(new Color(40, 40, 40));
    }

    @Override
    public void modeleMisAJour() {
        repaint(); // demande à Swing de rappeler paintComponent
    }

    /** Dessin : d'abord le sol et les éléments case par case, puis les robots par-dessus. */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (int x = 0; x < grille.getLargeur(); x++) {
            for (int y = 0; y < grille.getHauteur(); y++) {
                int px = x * T;
                int py = y * T;
                Element e = grille.getElement(x, y);

                dessinerSol(g2, px, py, (x + y) % 2 == 0); // damier

                if (e instanceof Mur) {
                    dessinerMur(g2, px, py);
                } else if (e instanceof Pastille) {
                    dessinerPastille(g2, px, py);
                } else if (e instanceof Mine) {
                    dessinerMine(g2, px, py);
                } else if (e instanceof Bombe bombe) {
                    dessinerBombe(g2, px, py, bombe.getCompteARebours());
                }
            }
        }

        // Robots vivants, avec la couleur de leur rang dans la liste
        for (Robot r : jeu.getCombattants()) {
            if (r.estVivant()) {
                int index = jeu.getCombattants().indexOf(r);
                dessinerRobot(g2, r, index);
            }
        }
    }

    /** Une case du damier. */
    private void dessinerSol(Graphics2D g2, int px, int py, boolean claire) {
        g2.setColor(claire ? SOL_1 : SOL_2);
        g2.fillRect(px, py, T, T);
        g2.setColor(CONTOUR_SOL);
        g2.drawRect(px, py, T - 1, T - 1);
    }

    /** Bloc fixe en pierre : face claire en haut, ombre en bas, bord sombre. */
    private void dessinerMur(Graphics2D g2, int px, int py) {
        g2.setColor(PIERRE);
        g2.fillRoundRect(px + 2, py + 2, T - 4, T - 4, 8, 8);
        g2.setColor(PIERRE_CLAIRE);
        g2.fillRoundRect(px + 5, py + 5, T - 12, (T - 12) / 2, 6, 6);
        g2.setColor(PIERRE_OMBRE);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(px + 2, py + 2, T - 4, T - 4, 8, 8);
    }

    /** Pastille d'énergie : petit cristal jaune avec reflet. */
    private void dessinerPastille(Graphics2D g2, int px, int py) {
        g2.setColor(new Color(255, 215, 0));
        g2.fillOval(px + 12, py + 12, T - 24, T - 24);
        g2.setColor(new Color(200, 140, 0));
        g2.setStroke(new BasicStroke(2));
        g2.drawOval(px + 12, py + 12, T - 24, T - 24);
        g2.setColor(Color.WHITE);
        g2.fillOval(px + 17, py + 16, 6, 6);
    }

    /** Mine : disque sombre entouré de 8 pointes rouges. */
    private void dessinerMine(Graphics2D g2, int px, int py) {
        int cx = px + T / 2;
        int cy = py + T / 2;
        g2.setColor(new Color(200, 30, 30));
        g2.setStroke(new BasicStroke(3));
        for (int i = 0; i < 8; i++) {
            double angle = i * Math.PI / 4; // une pointe tous les 45 degrés
            g2.drawLine(cx, cy,
                    cx + (int) (Math.cos(angle) * 18),
                    cy + (int) (Math.sin(angle) * 18));
        }
        g2.setColor(new Color(60, 60, 60));
        g2.fillOval(cx - 10, cy - 10, 20, 20);
        g2.setColor(new Color(255, 80, 80));
        g2.fillOval(cx - 4, cy - 4, 6, 6);
    }

    /** Bombe : corps noir, mèche et étincelle, compte à rebours écrit au centre. */
    private void dessinerBombe(Graphics2D g2, int px, int py, int tours) {
        g2.setColor(Color.BLACK);
        g2.fillOval(px + 6, py + 12, T - 14, T - 16);
        g2.setColor(new Color(140, 90, 40));
        g2.setStroke(new BasicStroke(3));
        g2.drawLine(px + T / 2 + 6, py + 12, px + T - 10, py + 6);
        g2.setColor(new Color(255, 180, 0));
        g2.fillOval(px + T - 13, py + 3, 8, 8);
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("SansSerif", Font.BOLD, 14));
        g2.drawString(String.valueOf(tours), px + T / 2 - 4, py + T / 2 + 8);
    }

    /** Personnage : ombre au sol, corps coloré, tête avec casquette et yeux. Contour cyan si bouclier actif. */
    private void dessinerRobot(Graphics2D g2, Robot r, int index) {
        int px = r.getPosition().x() * T;
        int py = r.getPosition().y() * T;
        Color couleur = COULEURS_ROBOTS[index % COULEURS_ROBOTS.length];

        g2.setColor(new Color(0, 0, 0, 80));          // ombre portée
        g2.fillOval(px + 12, py + T - 12, T - 24, 8);

        g2.setColor(couleur);                          // corps
        g2.fillRoundRect(px + 12, py + 22, T - 24, T - 30, 10, 10);

        g2.setColor(PEAU);                             // tête
        g2.fillOval(px + 14, py + 6, T - 28, T - 26);

        g2.setColor(couleur.darker());                 // casquette
        g2.fillRect(px + 14, py + 6, T - 28, 10);

        g2.setColor(Color.BLACK);                      // yeux
        g2.fillOval(px + 19, py + 20, 4, 5);
        g2.fillOval(px + T - 23, py + 20, 4, 5);

        g2.setColor(Color.WHITE);                      // dernière lettre du nom (R1 -> 1)
        g2.setFont(new Font("SansSerif", Font.BOLD, 12));
        String nom = r.getNom();
        g2.drawString(nom.substring(nom.length() - 1), px + T / 2 - 4, py + T - 14);

        if (r.isBouclierActif()) {                     // contour cyan : bouclier actif
            g2.setColor(new Color(0, 220, 255));
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(px + 3, py + 3, T - 6, T - 6);
        }
    }
}
