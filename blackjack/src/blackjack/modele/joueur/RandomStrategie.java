package blackjack.modele.joueur;

import blackjack.modele.coup.Coup;
import java.util.*;

/**
 * Implémente une stratégie de jeu aléatoire pour le Blackjack.
 * Cette classe sélectionne des coups et des mises de manière arbitraire en utilisant un générateur de nombres aléatoires.
 */
public class RandomStrategie implements Strategie {
    
    /**
     * Le générateur de nombres aléatoires utilisé pour prendre les décisions.
     */
    private final Random rand;

    /**
     * Construit une nouvelle instance de stratégie aléatoire.
     * Initialise le générateur interne nécessaire aux choix futurs.
     */
    public RandomStrategie() {
        this.rand = new Random();
    }

    /**
     * Sélectionne un coup au hasard parmi la liste des coups disponibles.
     *
     * @param coups La liste des coups parmi lesquels le choix doit être fait.
     * @return Le coup sélectionné aléatoirement.
     * @throws IllegalArgumentException Si la liste fournie est vide.
     */
    @Override
    public Coup choisirCoup(List<Coup> coups) {
        if (coups.isEmpty()) {
            throw new IllegalArgumentException("La liste des coups ne peut pas être vide.");
        }
        
        int positionCoup = rand.nextInt(coups.size());
        
        return coups.get(positionCoup);
    }

    /**
     * Détermine un montant de mise aléatoire basé sur le solde disponible.
     * Le montant retourné est compris entre 1 et le solde actuel (exclusif).
     *
     * @param solde Le solde actuel du joueur.
     * @return Un entier représentant la mise choisie.
     * @throws IllegalArgumentException Si le solde est inférieur ou égal à 0.
     */
    @Override
    public int choisirMise(int solde) {
        if (solde <= 0) {
            throw new IllegalArgumentException("Le solde ne peut pas être =< 0.");
        }
        if (solde == 1) {
            return 1;
        }
        int mise = 1 + rand.nextInt(solde-1);
        return mise;
    }
}