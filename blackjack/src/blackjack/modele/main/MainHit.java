package blackjack.modele.main;

import cartes.modele.Carte;

/**
 * Représente une main après l'exécution du coup "Hit" (Tirer une carte).
 * Cette classe décore une main existante pour y ajouter une nouvelle carte tirée de la pioche.
 */
public class MainHit extends AbstractMain {

    /**
     * Construit une nouvelle main mise à jour après un coup "Hit".
     * Ajoute immédiatement la carte fournie au paquet de la main décorée.
     *
     * @param mainDecore La main d'origine sur laquelle le coup est joué.
     * @param carte La nouvelle carte à ajouter à la main.
     */
    public MainHit(Main mainDecore, Carte carte) {
        super(mainDecore);
        mainDecore.getPaquet().ajouterCarteDessous(carte);
    }
    
    
}