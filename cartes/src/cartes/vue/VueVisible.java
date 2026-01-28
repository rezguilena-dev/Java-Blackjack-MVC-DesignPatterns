package cartes.vue;

import cartes.modele.*;
import javax.swing.*;
import java.awt.*;

/**
 * Une vue qui affiche un paquet de cartes de manière visible, héritant de VueCache.
 * Elle ajoute des boutons de tri (par hauteur et par couleur) au panneau supérieur
 * et remplace la VuePaquetCache par une VuePaquetVisible.
 */
public class VueVisible extends VueCache {
    
    private JButton triHauteurBouton;
    private JButton triCouleurBouton;

    /**
     * Construit une nouvelle VueVisible.
     *
     * @param nom Le titre à afficher au-dessus du paquet.
     * @param paquet Le modèle (Paquet) à afficher et à contrôler.
     */
    public VueVisible(String nom, Paquet paquet) {
        
        super(nom, paquet); 
        this.triHauteurBouton = new JButton("Tri Hauteur");
        this.triCouleurBouton = new JButton("Tri Couleur");
        this.panneauHaut.add(triHauteurBouton);
        this.panneauHaut.add(triCouleurBouton);
        triHauteurBouton.addActionListener(e -> paquet.trierParHauteur());
        triCouleurBouton.addActionListener(e -> paquet.trierParCouleur());
        this.remove(this.panneauCartes);         
        this.panneauCartes = new VuePaquetVisible(paquet);    
        paquet.ajouterEcouteur(panneauCartes);
        this.add(new JScrollPane(this.panneauCartes), BorderLayout.CENTER); 
    }
}
