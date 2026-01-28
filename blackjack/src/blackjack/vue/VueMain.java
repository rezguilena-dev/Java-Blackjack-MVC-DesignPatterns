package blackjack.vue;

import blackjack.modele.main.Main; 
import cartes.modele.Carte;
import java.awt.*;
import javax.swing.*;

/**
 * Représente la vue graphique d'une main de Blackjack.
 * Ce panneau affiche les cartes de la main, la mise associée et le score. Il permet également de mettre en évidence la main courante du joueur.
 */
public class VueMain extends JPanel {
    private final int CARD_GAP = 2;
    private final int CARD_WIDTH = 80, CARD_HEIGHT = 130; 
    private int mousePosX;
    private Main main;
    private JLabel labelMise;

    /**
     * Construit une nouvelle vue pour la main spécifiée.
     * Initialise le panneau en ajoutant le label de mise et le score de la main.
     *
     * @param mainModele Le modèle de données Main à afficher.
     */
    public VueMain(Main mainModele) {
        this.main=mainModele;         
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        this.setBackground(Color.GREEN); 
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        labelMise = new JLabel("Valuer: "+main.getValeur()+" Mise : " + main.getMise() + " €");
        labelMise.setHorizontalAlignment(SwingConstants.CENTER); 
        labelMise.setForeground(Color.BLACK); 
        labelMise.setFont(new Font("Arial", Font.BOLD, 12));
        labelMise.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        this.add(labelMise, BorderLayout.SOUTH);
        setBackground(Color.GREEN);
    }

    /**
     * Dessine les cartes de la main sur le panneau.
     * Les cartes sont affichées en superposition diagonale avec leurs hauteur et couleur.
     *
     * @param g L'objet Graphics utilisé pour le dessin.
     */
    @Override
    public void paintComponent(Graphics g){
        
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        int posX=10;
        int posY=10;
        int cardGap = 24;

        for(Carte card: main.getPaquet().getCartes()){
            g2.setColor(Color.WHITE);
            g2.fillRect(posX, posY, CARD_WIDTH, CARD_HEIGHT);
            if( card.getCouleur().equals("Carreaux")
                || card.getCouleur().equals("Cœurs") ){
                g2.setColor(Color.RED);
                g2.drawString(card.getCouleur().equals("Carreaux")?"♦" : "♥", posX+5, posY+30);
            }
            else{
                g2.setColor(Color.BLACK);
                g2.drawString(card.getCouleur().equals("Pique") ? "♠" : "♣", posX+5, posY+30);
            } 
            g2.drawRect(posX, posY, CARD_WIDTH, CARD_HEIGHT); 
            g2.setColor(Color.BLACK);
            int fontSize = 15;
            g2.setFont(new Font("Serif", Font.BOLD, fontSize));
            g2.setColor(Color.BLACK);
            g2.drawString(card.getHauteur(), posX+5, posY+fontSize);
            posX+= cardGap;
            posY+= cardGap;
        }
    }

    /**
     * Active ou désactive la mise en évidence visuelle de la main.
     * Ceci est typiquement utilisé pour indiquer la main courante du joueur.
     *
     * @param highlight true pour mettre en évidence, false sinon.
     */
    public void highlightMain(boolean highlight){
        if(highlight)
        this.setOpaque(highlight);
    }

    /**
     * Calcule et retourne la dimension préférée du composant.
     * La taille est déterminée par le nombre de cartes dans la main.
     *
     * @return La dimension préférée du panneau.
     */
    @Override
    public Dimension getPreferredSize() {
        if (main == null || main.getPaquet().getCartes() == null || main.getPaquet().getCartes().isEmpty()) {
            return new Dimension(CARD_WIDTH, CARD_HEIGHT);
        }
        int nbCartes = main.getPaquet().getCartes().size();
        int largeurTotale = (nbCartes * CARD_WIDTH) + ((nbCartes - 1) * 10);
        return new Dimension(largeurTotale + 2, CARD_HEIGHT + 2);
    }

    /**
     * Retourne le modèle de main associé à cette vue.
     *
     * @return L'objet Main modèle.
     */
    public Main getMain(){
        return main;
    }
}