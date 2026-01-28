package blackjack.vue;

import java.awt.*;
import javax.swing.JPanel;

/**
 * Représente la vue graphique d'une pioche de cartes.
 * Ce panneau affiche un symbole générique pour la pioche (dos de carte) dans l'interface de jeu.
 */
public class VuePioche extends JPanel {
    
    /**
     * Construit une nouvelle vue pour la pioche.
     * Initialise la dimension préférée du panneau et le rend transparent.
     */
    public VuePioche() {
        this.setPreferredSize(new Dimension(80, 110));
        this.setOpaque(false);
    }

    /**
     * Dessine la représentation symbolique de la pioche.
     * Affiche un rectangle blanc avec une bordure noire et des diagonales, ainsi que le texte "PIOCHE".
     *
     * @param g L'objet Graphics utilisé pour le dessin.
     */
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 70, 100);
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, 70, 100);
    
        g.drawLine(0, 0, 70, 100);
        g.drawLine(70, 0, 0, 100);
        g.drawString("PIOCHE", 15, 55);
    }
}