package blackjack.modele.coup;

import blackjack.modele.main.Main;

/**
 * Classe abstraite servant de base pour tous les coups possibles au Blackjack.
 * Elle implémente l'interface Coup et fournit une structure commune pour la vérification
 * de la validité d'une action.
 */
public abstract class AbstractCoup implements Coup {

    /**
     * Définit les conditions spécifiques requises pour que ce coup soit valide.
     * Chaque sous-classe doit implémenter cette méthode pour préciser ses propres règles.
     *
     * @param main La main du joueur sur laquelle le coup est envisagé.
     * @return true si les conditions spécifiques au coup sont remplies, sinon false.
     */
    public abstract boolean conditionSpecifique(Main main);

    /**
     * Vérifie si le coup peut être exécuté sur la main donnée.
     * Un coup est exécutable si la main est en état de jouer et si les conditions spécifiques
     * du coup sont satisfaites.
     *
     * @param main La main sur laquelle le coup est tenté.
     * @return true si le coup est autorisé, sinon false.
     */
    @Override
    public boolean peutExecuter( Main main){
        return main.peutJouer() && conditionSpecifique(main);
    }
}