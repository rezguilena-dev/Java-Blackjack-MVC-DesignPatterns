package blackjack.vue ;

import java.awt.*;
import javax.swing.*;

/**
 * Classe abstraite servant de base pour les panneaux de commandes de l'interface graphique.
 * Elle hérite de JPanel et définit une dimension standard pour les zones d'interaction utilisateur
 * (comme les boutons d'action ou de mise).
 */
public abstract class VueCommande extends JPanel{
    
    /**
     * La dimension par défaut préférée pour ce type de panneau.
     */
    private static final Dimension  TAILLE_DEFAUT = new Dimension(800, 50); 

    /**
     * Construit un nouveau panneau de commande.
     * Initialise le composant en lui appliquant la taille par défaut définie.
     */
    public VueCommande(){
        super();
        this.setPreferredSize(TAILLE_DEFAUT); 
    }
}