package cartes.vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Graphics;
import cartes.modele.Paquet;
import cartes.modele.Carte;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Représente une vue graphique d'un paquet de cartes dont les faces sont visibles.
 * Cette classe affiche les cartes alignées horizontalement et gère les interactions souris
 * pour identifier la carte survolée.
 */
public class VuePaquetVisible extends VuePaquet {

    private final int CARD_GAP = 2;
    private final int CARD_WIDTH = 70, CARD_HEIGHT = 161; 
    private int mousePosX;

    /**
     * Construit une nouvelle vue pour un paquet de cartes visible.
     * Initialise l'écouteur de mouvement de la souris pour suivre la position du curseur sur le composant.
     *
     * @param paquet Le paquet de cartes à afficher.
     */
    public VuePaquetVisible(Paquet paquet) {
        super(paquet);
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                VuePaquetVisible.this.mousePosX = e.getX();
            }
        });
    }


    /**
     * Calcule l'indice de la carte située sous la position actuelle de la souris.
     * Cette méthode permet de déterminer quelle carte est ciblée par l'utilisateur.
     *
     * @return L'indice de la carte correspondante dans le paquet.
     */
    public int indiceCarteARetirer(){
        int nombreDeCartes =  paquet.getCartes().size();
        int indice =  mousePosX/(CARD_WIDTH+CARD_GAP);
        return indice;
    }

    /**
     * Dessine les cartes du paquet sur le composant.
     * Chaque carte est affichée face visible avec sa hauteur et sa couleur.
     * Les cartes de cœurs et de carreaux sont affichées en rouge, les autres en noir.
     *
     * @param g L'objet Graphics utilisé pour le dessin.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        int posX=0;
        int posY=0;
        int cardGap = CARD_WIDTH+5;

        for(Carte card: paquet.getCartes()){
            g2.setColor(Color.WHITE);
            g2.fillRect(posX, posY, CARD_WIDTH, CARD_HEIGHT);
            if( card.getCouleur().equals("Carreaux")
                || card.getCouleur().equals("Cœurs") ){
                g2.setColor(Color.RED);
            }
            else{
                g2.setColor(Color.BLACK);
            } 
            g2.drawRect(posX, posY, CARD_WIDTH, CARD_HEIGHT); 
            g2.setColor(Color.BLACK);
            int fontSize = 10;
            g2.setFont(new Font("Serif", Font.BOLD, fontSize));
            g2.setColor(Color.BLACK);
            g2.drawString(card.getHauteur(), posX+5, posY+fontSize);
            g2.drawString(card.getCouleur(), posX+5, posY+10+fontSize*2);
            posX+= cardGap;
        }
    }
    
}