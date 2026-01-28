package blackjack.vue;

import blackjack.modele.coup.Coup;
import blackjack.modele.joueur.Joueur;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.function.Consumer;
import javax.swing.*;

/**
 * Panneau de commandes graphique affichant les boutons d'action pour le joueur.
 * Cette classe génère dynamiquement les boutons correspondant aux coups possibles (comme "Hit", "Stay")
 * et gère les interactions utilisateur en relayant les choix via un callback.
 */
public class VueCoup extends VueCommande{

    /**
     * Construit un nouveau panneau de sélection des coups.
     * Configure la mise en page pour centrer les boutons avec un espacement défini.
     */
    public VueCoup(){
        super();
        setLayout (new FlowLayout(FlowLayout.CENTER, 20, 10));
    }    

    /**
     * Initialise ou met à jour les boutons affichés en fonction des coups disponibles.
     * Cette méthode nettoie le panneau, crée un bouton pour chaque coup de la liste fournie,
     * configure l'écouteur d'événements pour exécuter le callback avec le coup choisi,
     * puis rafraîchit l'affichage.
     *
     * @param coups La liste des coups possibles à proposer au joueur.
     * @param callback La fonction à appeler lorsqu'un bouton est cliqué, prenant le coup choisi en paramètre.
     */
    public void initialiserComposant(List<Coup> coups,Consumer<Coup> callback){
        this.removeAll();
        for(Coup coup: coups){
            JButton boutton = new JButton(coup.getClass().getName().substring(22));
            boutton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    callback.accept(coup);
                }
            });
            this.add(boutton);
        }
        this.revalidate(); 
        this.repaint();
    }

    /**
     * Modifie l'état d'activation de tous les boutons du panneau.
     * Permet d'empêcher ou d'autoriser l'interaction du joueur avec les commandes.
     *
     * @param actif true pour activer les boutons, false pour les désactiver.
     */
    public void setBoutonsActifs(boolean actif) {
        for (Component comp : this.getComponents()) {
            if (comp instanceof JButton) {
                comp.setEnabled(actif);
            }
        }
    }
}