package blackjack.controleur.joueur;

import blackjack.modele.coup.Coup;
import blackjack.modele.joueur.AbstractJoueur;
import blackjack.modele.main.Main;
import blackjack.vue.VueBlackjack;
import java.util.List;
import java.util.function.Consumer;

/**
 * Représente un joueur humain interagissant avec le jeu via une interface graphique.
 * Ce joueur délègue les décisions de jeu (choix du coup, mise) à la vue pour que l'utilisateur puisse répondre.
 */
public class JoueurGraphique extends AbstractJoueur{
    
    /**
     * La vue principale du jeu utilisée pour l'interaction avec l'utilisateur.
     */
    private VueBlackjack vue;

    /**
     * Construit un nouveau joueur graphique avec un nom et un solde initial.
     * * @param nom Le nom du joueur.
     * @param solde Le solde de départ du joueur.
     */
    public JoueurGraphique(String nom, int solde){
        super(nom, solde);  
    }

    /**
     * Associe l'interface graphique à ce joueur.
     * Cette vue sera utilisée pour demander les actions à l'utilisateur.
     * * @param vue La vue du Blackjack à associer.
     */
    public void ajouterVue(VueBlackjack vue){
        this.vue = vue;
    }

    /**
     * Demande à la vue d'afficher les coups possibles pour que l'utilisateur en choisisse un.
     * * @param main La main actuelle du joueur.
     * @param coups La liste des coups disponibles.
     * @param callback La fonction à appeler une fois le coup choisi.
     */
    @Override
    public void choisirCoup(Main main, List<Coup> coups, Consumer<Coup> callback) {
        vue.afficherCoups(coups, callback);
    }

    /**
     * Demande à la vue d'afficher l'interface de mise pour que l'utilisateur saisisse un montant.
     * * @param callback La fonction à appeler une fois la mise choisie.
     */
    @Override
    public void choisirMise(Consumer<Integer> callback) {
        vue.afficherMise(callback);
    }
}