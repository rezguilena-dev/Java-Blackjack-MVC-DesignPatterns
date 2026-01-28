package blackjack.vue;

import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.Main;
import blackjack.vue.vuesoldes.*;
import cartes.modele.Paquet;
import java.awt.*;
import java.util.List;
import javax.swing.*;

/**
 * Panneau d'affichage des informations de jeu générales dans l'interface de Blackjack.
 * Ce panneau est situé en haut de la fenêtre principale et contient les vues du solde des joueurs,
 * de la main du croupier et de la pioche.
 */
public class VueInfosJeu extends JPanel {

    /**
     * La vue affichant les soldes des joueurs.
     */
    private VueSolde vueSolde;

    /**
     * La vue affichant la main du croupier.
     */
    private VueCroupier vueCroupier;

    /**
     * La vue représentant la pioche.
     */
    private VuePioche vuePioche;

    /**
     * Construit le panneau d'informations du jeu.
     * Initialise et positionne les vues du solde, du croupier et de la pioche.
     * Ajoute également la VueCroupier comme écouteur des changements du paquet du croupier.
     *
     * @param joueurs La liste des joueurs pour l'affichage de leurs soldes.
     * @param mainCroupier La main du croupier dont la vue doit être affichée.
     */
    public VueInfosJeu(List<Joueur> joueurs, Main mainCroupier) {
        this.setLayout(new BorderLayout());
        
        this.setBorder(BorderFactory.createMatteBorder(0, 0, 3, 0, Color.BLACK));

     
        this.vueSolde = new VueSolde(joueurs);
        JPanel wrapperGauche = new JPanel(new FlowLayout(FlowLayout.LEFT));
        wrapperGauche.setOpaque(false);
        wrapperGauche.add(vueSolde);
        this.add(wrapperGauche, BorderLayout.WEST);

 
        this.vueCroupier = new VueCroupier(mainCroupier);
        JPanel wrapperCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        wrapperCentre.setOpaque(false);
        wrapperCentre.add(vueCroupier);
        this.add(wrapperCentre, BorderLayout.CENTER);


        this.vuePioche = new VuePioche();
        JPanel wrapperDroite = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        wrapperDroite.setOpaque(false);
        wrapperDroite.add(vuePioche);
        this.add(wrapperDroite, BorderLayout.EAST);

        mainCroupier.getPaquet().ajouterEcouteur(vueCroupier);
    }

    /**
     * Retourne la vue des soldes des joueurs.
     *
     * @return L'objet VueSolde.
     */
    public VueSolde getVueSolde() {
        return vueSolde;
    }

    /**
     * Retourne la vue de la main du croupier.
     *
     * @return L'objet VueCroupier.
     */
    public VueCroupier getVueCroupier(){
        return vueCroupier;
    }
}