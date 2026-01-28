package blackjack.modele.coup;

import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.*;

/**
 * Représente le coup d'assurance au Blackjack.
 * Ce coup permet au joueur de s'assurer contre un potentiel Blackjack du croupier lorsque la première carte visible
 * de ce dernier est un As.
 */
public class AssuranceCoup extends AbstractCoup{

    /**
     * La main du croupier utilisée pour vérifier la condition d'assurance.
     */
    private final Main mainCroupier;

    /**
     * Construit un nouveau coup d'assurance.
     * * @param mainCroupier La main du croupier, nécessaire pour vérifier si la première carte est un As.
     */
    public AssuranceCoup(Main mainCroupier){
        this.mainCroupier = mainCroupier;
    }

    /**
     * Exécute le coup d'assurance.
     * Cette action déduit la moitié de la mise initiale du solde du joueur et transforme la main courante
     * en une main assurée.
     * * @param main La main du joueur sur laquelle l'assurance est prise.
     * @return Une nouvelle instance de MainAssure décorant la main originale.
     */
    @Override
    public Main executer(Main main){
        Joueur joueur = main.getJoueur();
        joueur.setSolde(joueur.getSolde()-(main.getMise()/2));
        main.incNbrTours();
        return new MainAssure(main,main.getMise());
    }

    /**
     * Vérifie si le coup d'assurance est permis.
     * L'assurance est disponible uniquement au début du tour (nombre de tours égal à 0) et si la première carte
     * visible du croupier est un As.
     * * @param main La main du joueur.
     * @return true si l'assurance est possible, sinon false.
     */
    @Override
    public boolean conditionSpecifique(Main main){
        return main.getNbrTours() == 0 
               && mainCroupier.getPaquet().getCartes().get(0).getHauteur().equals("As");
    }
}