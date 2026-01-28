package blackjack.modele.coup;

import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.*;
import cartes.modele.Paquet;

/**
 * Représente le coup "Doubler" (Double Down) au Blackjack.
 * Ce coup permet au joueur de doubler sa mise initiale en échange de recevoir exactement une carte supplémentaire
 * pour compléter sa main.
 */
public class DoubleCoup extends AbstractCoup{

    /**
     * La pioche utilisée pour tirer la carte supplémentaire lors de l'action.
     */
    private final Paquet pioche;

    /**
     * Construit un nouveau coup "Doubler" associé à la pioche donnée.
     * * @param pioche Le paquet de cartes d'où sera tirée la carte unique.
     */
    public DoubleCoup(Paquet pioche){
        this.pioche = pioche;
    }

    /**
     * Exécute le coup "Doubler" sur la main spécifiée.
     * Cette méthode débite le joueur du montant de la mise actuelle (doublant ainsi l'enjeu total),
     * incrémente le nombre de tours et retourne une nouvelle main contenant la carte tirée.
     * * @param main La main actuelle du joueur.
     * @return Une instance de MainDouble représentant la main après avoir doublé.
     */
    @Override
    public Main executer(Main main){
        Joueur joueur = main.getJoueur();
        joueur.setSolde(joueur.getSolde()-main.getMise());
        main.incNbrTours();
        return new MainDouble(main, pioche.retirerPremiereCarte());
    }
    
    /**
     * Vérifie si les conditions spécifiques pour doubler sont remplies.
     * Le coup est valide uniquement si le joueur possède exactement deux cartes,
     * s'il a suffisamment de solde pour égaler sa mise, et si la valeur de sa main est inférieure à 21.
     * * @param main La main à vérifier.
     * @return true si le coup est autorisé, sinon false.
     */
    @Override
    public boolean conditionSpecifique(Main main){
        boolean aDeuxCartes = main.getPaquet().getCartes().size() == 2;
        boolean aAssezArgent = main.getJoueur().getSolde() >= main.getMise();
        boolean valeur = main.getValeur() < 21;
        return aDeuxCartes && aAssezArgent && valeur ;
    }
}