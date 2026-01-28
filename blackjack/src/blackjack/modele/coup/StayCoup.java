package blackjack.modele.coup;

import blackjack.modele.main.*;

/**
 * Représente le coup "Stay" (Rester).
 * Le joueur décide de ne plus prendre de cartes pour cette main.
 */
public class StayCoup extends AbstractCoup {

    
    /**
     * Exécute l'action "Stay".
     * @return Une nouvelle instance de MainStay décorant la main actuelle.
     */
    @Override
    public Main executer(Main main) {
        main.incNbrTours();
        return new MainStay(main);
    }

    /**
     * Vérifie si le coup "Stay" est valide.
     * Un joueur peut toujours choisir de "Rester".
     * @return true, car le coup "Stay" est toujours autorisé.
     */
    @Override
    public boolean conditionSpecifique(Main main) { 
        return true;
    }
     
}