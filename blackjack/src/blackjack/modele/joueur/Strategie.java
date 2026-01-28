package blackjack.modele.joueur;

import blackjack.modele.coup.Coup;
import java.util.*;

/**
 * Définit une stratégie permettant de choisir les actions d'un joueur
 * dans une partie de Blackjack. Une stratégie décide du coup à jouer
 * ainsi que du montant de la mise.
 */
public interface Strategie {

    /**
     * Choisit un coup parmi la liste des coups possibles.
     *
     * @param coups liste des coups disponibles
     * @return le coup choisi
     */
    public Coup choisirCoup(List<Coup> coups);

    /**
     * Détermine la mise à effectuer en fonction du solde du joueur.
     *
     * @param solde solde actuel du joueur
     * @return montant de la mise choisie
     */
    public int choisirMise(int solde);
}
