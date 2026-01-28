package cartes.vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import cartes.modele.Paquet;
import javax.swing.UIManager;

/**
 * Représente la vue graphique d'un paquet de cartes dont les faces sont cachées (comme une pioche).
 * Cette classe hérite de VuePaquet et dessine les cartes face verso, empilées les unes sur les autres.
 */
public class VuePaquetCache extends VuePaquet {

    private final int CARD_GAP = 2;
    private final int CARD_WIDTH = 70, CARD_HEIGHT =140;


    /**
     * Construit une nouvelle vue pour un paquet de cartes caché.
     *
     * @param paquet Le paquet de cartes à représenter graphiquement.
     */
    public VuePaquetCache(Paquet paquet) {
        super(paquet);
    }

    /**
     * Dessine le paquet de cartes sur le composant.
     * Cette méthode efface le fond puis dessine chaque carte du paquet sous forme de rectangle
     * avec un motif en croix pour symboliser le dos de la carte, en les décalant légèrement pour créer un effet de pile.
     *
     * @param g L'objet Graphics utilisé pour le dessin.
     */
    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        int height = this.getHeight();
        int width = this.getWidth();
        Color defaultBackground = UIManager.getColor("Panel.background");
        g2.setColor(defaultBackground);
        g2.fillRect(0, 0, getWidth(), getHeight());
        int posX=0;
        int posY=0;
        for(int i=0; i<paquet.getCartes().size()
                    &&(posX+CARD_GAP < width
                    && posY+CARD_GAP +CARD_HEIGHT < 220
                    ) ; i++){
            g2.setColor(Color.WHITE);
            g2.fillRect(posX, posY, CARD_WIDTH, CARD_HEIGHT); 
            g2.setColor(Color.BLACK);
            g2.drawRect(posX, posY, CARD_WIDTH, CARD_HEIGHT); 
            g2.drawLine(posX+5, posY+5, posX+CARD_WIDTH-5,  posY+CARD_HEIGHT-5);
            g2.drawLine(posX + CARD_WIDTH-5, posY+5, posX+5,  posY+CARD_HEIGHT-5);
            posX+= CARD_GAP;
            posY+= CARD_GAP; 
        }
    }
}