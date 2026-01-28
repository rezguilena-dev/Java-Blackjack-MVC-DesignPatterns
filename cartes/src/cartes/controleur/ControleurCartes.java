package cartes.controleur;

import cartes.modele.*;
import cartes.vue.*;

/**
 * Contrôleur principal pour la gestion des interactions de cartes dans le jeu.
 * Cette classe orchestre les mouvements de cartes entre les différents paquets
 * (pioche, main, défausse) en instanciant des contrôleurs spécifiques pour chaque type de transfert.
 */
public class ControleurCartes {

    /**
     * Contrôleur gérant le déplacement d'une carte choisie depuis la main vers la défausse.
     */
    private final ControleurChoixCarteVersPaquet controleurMainVersDefausse;

    /**
     * Contrôleur gérant le déplacement d'une carte choisie depuis la défausse vers la pioche.
     */
    private final ControleurChoixCarteVersPaquet controleurDefausseVersPioche;

    /**
     * Contrôleur gérant l'action de piocher une carte depuis la pioche vers la main.
     */
    private final ControleurPiocheVersPaquet controleurPiocheVersMain;

    /**
     * La vue principale contenant les composants graphiques des cartes et des paquets.
     */
    private final VueCartes vueCartes;

    /**
     * Constructeur du contrôleur de cartes.
     * Initialise les contrôleurs délégués en liant les vues spécifiques (récupérées via vueCartes)
     * aux modèles de paquets correspondants.
     *
     * @param vueCartes La vue globale contenant les affichages de la pioche, de la main et de la défausse.
     * @param pioche Le modèle de données représentant la pioche.
     * @param main Le modèle de données représentant la main du joueur.
     * @param defausse Le modèle de données représentant la défausse.
     */
    public ControleurCartes(VueCartes vueCartes, Paquet pioche, Paquet main, Paquet defausse) {
        
        this.vueCartes = vueCartes;

        controleurPiocheVersMain = new ControleurPiocheVersPaquet((VuePaquetCache)(vueCartes.getVuePioche().getVuePaquet()), main);
        controleurMainVersDefausse = new ControleurChoixCarteVersPaquet((VuePaquetVisible)(vueCartes.getVueMain().getVuePaquet()), defausse);
        controleurDefausseVersPioche = new ControleurChoixCarteVersPaquet((VuePaquetVisible)(vueCartes.getVueDefausse().getVuePaquet()), pioche);

    }
}