package blackjack.modele.main;

import cartes.modele.*;

/**
 * Représente une main après l'application du coup "Doubler" (Double Down).
 * Cette classe décore une main existante pour lui ajouter une carte finale, doubler la mise associée,
 * et signaler que le tour est terminé pour cette main.
 */
public class MainDouble extends AbstractMain {

    /**
     * Construit une nouvelle main doublée.
     * Cette opération ajoute la carte fournie à la main, double le montant de la mise actuelle
     * et finalise l'état de la main.
     *
     * @param mainDecore La main d'origine sur laquelle le coup est joué.
     * @param carte La carte unique distribuée suite au coup "Doubler".
     */
    public MainDouble(Main mainDecore, Carte carte) {
        super(mainDecore);
        mainDecore.getPaquet().ajouterCarteDessous(carte);
        mainDecore.setMise(mainDecore.getMise()*2);
    }

    /**
     * Indique si la main peut encore être jouée.
     * Dans le cas d'un "Double Down", le joueur reçoit une seule carte et ne peut plus agir,
     * la méthode retourne donc toujours false.
     *
     * @return false, car le tour s'arrête immédiatement après avoir doublé.
     */
    @Override
    public boolean peutJouer(){
        return false;
    }
    
}