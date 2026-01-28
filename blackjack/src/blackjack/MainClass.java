package blackjack;

import blackjack.controleur.*;
import blackjack.controleur.joueur.*;
import blackjack.modele.Blackjack;
import blackjack.modele.coup.*;
import blackjack.modele.joueur.*;
import blackjack.vue.VueBlackjack;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe de démonstration et point d'entrée pour l'application de Blackjack.
 * Cette classe configure les composants nécessaires (joueurs, modèle, vue, contrôleur)
 * et lance l'exécution du jeu.
 */
public class MainClass {

    /**
     * Méthode principale exécutée au lancement de l'application.
     * Elle initialise une partie avec un joueur humain et des joueurs IA, configure l'interface graphique
     * et l'orchestrateur, puis démarre le jeu.
     *
     * @param args Les arguments de la ligne de commande (non utilisés).
     */
    public static void main(String[] args) {
        int nbJoueurs = 3;
        List<Joueur> joueurs = new ArrayList<>();

        JoueurGraphique joueurGraphique =new JoueurGraphique("Moi", 1000); 
        joueurs.add(joueurGraphique);
        for (int i = 1; i < nbJoueurs; i++) {
            joueurs.add(new JoueurIA("IA " + i, 1000, new RandomStrategie()));
        }
        
        Blackjack jeu = new Blackjack(joueurs);
        
        VueBlackjack vue =new VueBlackjack(jeu);
        joueurGraphique.ajouterVue(vue);
        jeu.ajouterEcouteur(vue);
        Orchestrateur orch= new Orchestrateur(jeu,vue,getListCoups(jeu));
        orch.lancerJeu();
    }
    
    /**
     * Crée et retourne la liste des coups disponibles pour le jeu.
     * Instancie les différents types de coups (Rester, Tirer, Split, Doubler, Assurance)
     * en leur fournissant les dépendances nécessaires provenant du jeu (pioche, main du croupier).
     *
     * @param jeu L'instance du jeu de Blackjack en cours.
     * @return La liste complète des objets Coup utilisables dans la partie.
     */
    private static List<Coup> getListCoups(Blackjack jeu){
        List<Coup> coupsPossibles = new ArrayList<>();
        Coup stay = new StayCoup();
        Coup hit = new HitCoup(jeu.getPioche());
        Coup split = new SplitCoup(jeu.getPioche());
        Coup doubl = new DoubleCoup(jeu.getPioche());
        Coup assurance = new AssuranceCoup(jeu.getMainCroupier());
        coupsPossibles.add(stay);
        coupsPossibles.add(hit);
        coupsPossibles.add(split);
        coupsPossibles.add(assurance);
        coupsPossibles.add(doubl);
        return coupsPossibles;
    }
    
    
}