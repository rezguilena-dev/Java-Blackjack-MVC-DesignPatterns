package blackjack.modele.coup;

import blackjack.modele.main.Main;

/**
 * Définit le contrat pour les actions de jeu possibles au Blackjack.
 * Cette interface impose les méthodes nécessaires pour exécuter un coup et vérifier sa validité sur une main donnée.
 */
public interface Coup{

    /**
     * Applique ce coup à la main spécifiée.
     * Cette méthode effectue les modifications nécessaires sur la main (comme ajouter une carte ou changer la mise)
     * et retourne la main résultante, qui peut être une nouvelle instance décorée.
     *
     * @param main La main sur laquelle le coup est joué.
     * @return La main mise à jour après l'exécution du coup.
     */
    public Main executer(Main main);

    /**
     * Détermine si ce coup est autorisé pour la main actuelle.
     * Vérifie les règles du jeu pour savoir si l'action est possible dans l'état actuel de la main.
     *
     * @param main La main à évaluer.
     * @return true si le coup peut être exécuté, sinon false.
     */
    public boolean peutExecuter(Main main);
}