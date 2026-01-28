package cartes.vue;

import cartes.modele.*;
import javax.swing.*;

/**
 * Fenêtre permettant de visualiser le contenu de la pioche face visible.
 * Cette classe hérite de JFrame et sert généralement à des fins de débogage ou de triche,
 * en affichant les cartes de la pioche qui devraient normalement rester cachées aux joueurs.
 */
public class VuePiocheVisible extends JFrame{
    
    /**
     * Le paquet de cartes représentant la pioche.
     */
    private Paquet pioche;

    /**
     * Construit une nouvelle fenêtre pour afficher la pioche.
     * Initialise un panneau de vue visible avec le paquet fourni et l'ajoute à la fenêtre.
     *
     * @param pioche Le paquet de cartes de la pioche à afficher.
     */
    public VuePiocheVisible(Paquet pioche ){
        super("A ne pas montrer aux joueurs");
        JPanel paneauPiocheVisible = new VueVisible("A ne pas montrer aux joueurs",pioche);
        this.add(paneauPiocheVisible);
        this.setSize(800, 250);
        this.setVisible(true);
        
    }
    
}