package blackjack.modele.joueur;

import blackjack.modele.coup.Coup;
import blackjack.modele.main.Main;
import java.util.*;
import java.util.function.Consumer;

/**
 * Représente un joueur contrôlé par l'ordinateur (Intelligence Artificielle).
 * Ce joueur prend ses décisions de jeu et de mise en déléguant la logique à une stratégie définie.
 */
public class JoueurIA extends AbstractJoueur {
    
    private final Strategie strategie;

    /**
     * Construit un nouveau joueur IA.
     * * @param nom Le nom du joueur.
     * @param solde Le solde initial du joueur.
     * @param strategie La stratégie qui dictera les choix du joueur.
     */
    public JoueurIA(String nom, int solde, Strategie strategie) {
        super(nom, solde);
        this.strategie = strategie;
    }

    /**
     * Choisit un coup à jouer parmi les options disponibles selon la stratégie de l'IA.
     * Cette méthode s'exécute dans un thread séparé avec un délai simulé avant d'invoquer le rappel.
     * * @param main La main sur laquelle le coup doit être joué.
     * @param coups La liste des coups autorisés.
     * @param callback La fonction à appeler une fois le coup sélectionné.
     */
    @Override
    public void choisirCoup(Main main, List<Coup> coups, Consumer<Coup> callback) {
        new Thread(() -> {
            Coup coupChoisi = strategie.choisirCoup(coups);
    
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); 
                e.printStackTrace();
            }
            callback.accept(coupChoisi);
    
        }).start();
    }

    /**
     * Détermine le montant de la mise à placer selon la stratégie de l'IA.
     * * @param callback La fonction à appeler avec le montant de la mise choisie.
     */
    @Override
    public void choisirMise(Consumer<Integer> callback){
        int mise = strategie.choisirMise(solde);
        callback.accept(mise);
    }
}