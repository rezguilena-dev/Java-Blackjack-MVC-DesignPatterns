package blackjack.vue;

import blackjack.modele.main.Main;
import cartes.modele.Carte;
import cartes.vue.VuePaquet;
import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * Représente la vue graphique de la main du croupier dans le jeu de Blackjack.
 * Cette classe gère l'affichage des cartes du croupier (dont certaines peuvent être cachées)
 * ainsi que l'affichage du score correspondant.
 */
public class VueCroupier extends VuePaquet {
    private boolean afficheCartes;
    private final Main mainCroupier;
    private final JLabel labelValeur;
    private final int CARD_GAP = 75;
    private final int MARGIN_X = 10;
    private final int MARGIN_Y = 10;
    private final int LABEL_HEIGHT = 30;
    private final int CARD_WIDTH = 80, CARD_HEIGHT = 130;

    /**
     * Construit une nouvelle vue pour le croupier.
     * Initialise le panneau, le label de score et configure les dimensions et la transparence.
     *
     * @param main La main du croupier à associer à cette vue.
     */
    public VueCroupier(Main main) {
        super(main.getPaquet());
        this.mainCroupier = main;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 110));
        this.setOpaque(false);
        afficheCartes =false;
        labelValeur = new JLabel("Valeur : ?");
        labelValeur.setHorizontalAlignment(SwingConstants.CENTER);
        labelValeur.setForeground(Color.BLACK);
        labelValeur.setFont(new Font("Arial", Font.BOLD, 12));
        labelValeur.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        
        this.add(labelValeur, BorderLayout.SOUTH);
        updateValeurLabel();
    }

    /**
     * Définit si les cartes cachées du croupier doivent être révélées.
     * Met à jour le label de valeur et redessine la vue en conséquence.
     *
     * @param afficheCartes true pour afficher toutes les cartes, false pour garder la face cachée.
     */
    public void setAfficheCartes(boolean afficheCartes){
        this.afficheCartes=afficheCartes;
        updateValeurLabel();
        repaint();
    }

    /**
     * Met à jour la vue lorsque le modèle notifie un changement.
     * Actualise le label de score et redessine les composants.
     *
     * @param obj L'objet ayant déclenché la mise à jour.
     */
    public void miseAjour(Object obj) {
        updateValeurLabel();
        revalidate();
        repaint();
    }

    /**
     * Calcule la dimension préférée du composant en fonction du nombre de cartes.
     * Assure que toutes les cartes étalées sont visibles.
     *
     * @return La dimension calculée.
     */
    @Override
    public Dimension getPreferredSize() {
        int nbCartes = paquet.getCartes().size();
        if (nbCartes == 0) nbCartes = 1;

        int width = MARGIN_X + ((nbCartes - 1) * CARD_GAP) + CARD_WIDTH + MARGIN_X;
        int height = MARGIN_Y + CARD_HEIGHT + LABEL_HEIGHT;

        return new Dimension(Math.max(300, width), height);
    }

    private int getValeurCarte(Carte c) {
        String h = c.getHauteur();
        if (h.equals("Roi") || h.equals("Dame") || h.equals("Valet")) {
            return 10;
        }
        if (h.equals("As")) {
            return 11; 
        }
        try {
            return Integer.parseInt(h);
        } catch (NumberFormatException e) {
            return 0; 
        }
    }

    private void updateValeurLabel() {
        List<Carte> cartes = mainCroupier.getPaquet().getCartes();
        
        if (cartes.isEmpty()) {
            labelValeur.setText("Croupier");
        } else if (afficheCartes) {
            labelValeur.setText("Valeur : " + mainCroupier.getValeur());
        } else {
            Carte visible = cartes.get(0);
            int valVisible = getValeurCarte(visible);
            labelValeur.setText("Valeur : " + valVisible);
        }
    }

    /**
     * Dessine les cartes du croupier sur le composant.
     * La première carte est toujours dessinée face visible. Les cartes suivantes sont dessinées
     * soit face visible, soit face cachée (dos de carte rouge) selon l'état de la variable afficheCartes.
     *
     * @param g L'objet Graphics utilisé pour le dessin.
     */
    @Override
    public void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        List<Carte> cartes = paquet.getCartes();
        int x = MARGIN_X; 
        int y = MARGIN_Y;
        int cardGap = 75; 

        for (int i = 0; i < cartes.size(); i++) {
            Carte carte = cartes.get(i);
            
            if (i == 0 || afficheCartes) { 
                g2.setColor(Color.WHITE);
                g2.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
                
                if(carte.getCouleur().equals("Carreaux") || carte.getCouleur().equals("Cœurs")) {
                    g2.setColor(Color.RED);
                } else {
                    g2.setColor(Color.BLACK);
                } 
                
                g2.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT); 

                if( carte.getCouleur().equals("Carreaux")
                || carte.getCouleur().equals("Cœurs") ){
                g2.setColor(Color.RED);
                g2.drawString(carte.getCouleur().equals("Carreaux")?"♦" : "♥", x+5, 25*2);
            }
            else{
                g2.setColor(Color.BLACK);
                g2.drawString(carte.getCouleur().equals("Pique") ? "♠" : "♣", y+5, 25*2);
            }
            int fontSize = 15;
            g2.setFont(new Font("Serif", Font.BOLD, fontSize));
            g2.setColor(Color.BLACK);
            g2.drawString(carte.getHauteur(), x+5, y+fontSize);
                
            } else {
                g2.setColor(new Color(139, 0, 0)); 
                g2.fillRect(x, y, CARD_WIDTH, CARD_HEIGHT);
                
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, CARD_WIDTH, CARD_HEIGHT);
                
                g2.drawLine(x, y, x + CARD_WIDTH, y + CARD_HEIGHT);
                g2.drawLine(x + CARD_WIDTH, y, x, y + CARD_HEIGHT);
            }
            x += cardGap; 
        }
    }
}