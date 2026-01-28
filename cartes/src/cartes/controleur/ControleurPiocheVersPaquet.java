package cartes.controleur ;

import cartes.modele.Carte;
import cartes.modele.Paquet;
import cartes.vue.VuePaquetCache;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Contrôleur gérant le déplacement d'une carte depuis une vue de paquet caché vers un paquet de destination.
 * Cette classe permet de piocher une carte en cliquant sur la vue source et de l'ajouter au paquet cible.
 */
public class ControleurPiocheVersPaquet{

    /**
     * La vue représentant le paquet source (généralement une pioche) dans l'interface graphique.
     */
    private VuePaquetCache vueSource ;

    /**
     * Le paquet de destination où la carte piochée sera ajoutée.
     */
    private Paquet destination;

    /**
     * Construit le contrôleur et configure l'écouteur d'événements souris.
     * Lorsqu'un clic est détecté sur la vue source, la première carte est retirée du paquet source
     * et ajoutée au paquet de destination.
     * * @param vueSource La vue du paquet caché servant de source.
     * @param destination Le paquet modèle recevant la carte déplacée.
     */
    public ControleurPiocheVersPaquet(VuePaquetCache vueSource ,Paquet destination){
        this.vueSource = vueSource;
        this.destination = destination;
        this.vueSource.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
         
                    Carte carte = vueSource.getPaquet().retirerCarte(0);
                    if (carte != null) {
                        destination.ajouterCarteDessus(carte);
                    }
                
            }
        }); 
    }


}