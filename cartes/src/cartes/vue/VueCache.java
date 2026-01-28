package cartes.vue;

import cartes.modele.*;
import javax.swing.*;
import java.awt.*;

/**
 * Représente un panneau de base pour afficher un paquet de cartes.
 * Il se compose d'une zone supérieure (panneauHaut) avec un titre
 * et d'une zone centrale (panneauCartes) pour afficher le paquet lui-même.
 * Par défaut, il utilise une VuePaquetCache pour masquer les cartes.
 */
public class VueCache extends JPanel {
    
    /**
     * Le label affichant le titre du panneau.
     */
    protected JLabel titreLabel;
    
    /**
     * Le panneau (VuePaquet) qui affiche les cartes du paquet.
     */
    protected VuePaquet panneauCartes;
    
    /**
     * Le panneau supérieur contenant le titre et potentiellement d'autres contrôles.
     */
    protected JPanel panneauHaut; 

    /**
     * Construit une nouvelle VueCache.
     *
     * @param nom Le titre à afficher dans le panneauHaut.
     * @param paquet Le paquet de cartes à associer à cette vue.
     */
    public VueCache(String nom, Paquet paquet) {

        super(new BorderLayout());
        this.panneauHaut = new JPanel(new FlowLayout(FlowLayout.LEFT)); 
        this.titreLabel = new JLabel(nom);
        this.titreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.panneauHaut.add(titreLabel); 
        this.panneauCartes = new VuePaquetCache(paquet); 
        this.add(panneauHaut, BorderLayout.NORTH);
        this.add(panneauCartes, BorderLayout.CENTER);
        paquet.ajouterEcouteur(panneauCartes);    
        
    }
    
    /**
     * Obtient le composant VuePaquet qui affiche les cartes.
     *
     * @return La VuePaquet (par exemple VuePaquetCache ou VuePaquetVisible) utilisée.
     */
    public VuePaquet getVuePaquet() {
        
        return this.panneauCartes;
    }
}