package blackjack.modele.coup;

import blackjack.modele.main.*;
import cartes.modele.*;

/**
 * Représente le coup "Hit" (Tirer une carte).
 * Le joueur demande une carte supplémentaire de la pioche.
 */
public class HitCoup extends AbstractCoup {
    
    private final Paquet pioche;

    /**
     * Constructeur pour le coup Hit.
     * @param pioche Le paquet (pioche) d'où tirer la carte.
     */
    public HitCoup(Paquet pioche) {
        this.pioche = pioche;
    }

    
    @Override
    public Main executer(Main main) {
        main.incNbrTours();
        return new MainHit(main,this.pioche.retirerPremiereCarte());
    }
    /**
     * Vérifie si le coup "Hit" est valide.
     * Le coup est valide uniquement si le total des points de la main est
     * strictement inférieur à 21.
     *
     * @return true si le score est < 21, sinon false.
     */
    @Override
    public boolean conditionSpecifique(Main main) {
        return main.getValeur() < 21;
    }
}