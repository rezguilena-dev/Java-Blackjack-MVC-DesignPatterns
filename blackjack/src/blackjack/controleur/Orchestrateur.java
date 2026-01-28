package blackjack.controleur;

import blackjack.modele.Blackjack;
import blackjack.modele.coup.Coup;
import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.Main;
import blackjack.vue.VueBlackjack;
import java.util.*;
import javax.swing.JOptionPane;

/**
 * Orchestre le déroulement global d'une partie de Blackjack.
 * Cette classe fait le lien entre le modèle (Blackjack) et la vue (VueBlackjack).
 * Elle gère la séquence de jeu incluant la phase de mises, les tours de chaque joueur,
 * la résolution des mains et la gestion de la fin de partie.
 */
public class Orchestrateur {

    private final Blackjack jeu;
    private final List<Joueur> joueurs;
    private final VueBlackjack vue;
    private final List<Coup> coupsPossibles; 

    /**
     * Initialise l'orchestrateur avec le jeu, la vue et la liste des coups disponibles.
     * * @param jeu L'instance du modèle de jeu Blackjack.
     * @param vue L'interface graphique principale du jeu.
     * @param coupsPossibles La liste de tous les types de coups (Hit, Stay, etc.) pouvant être joués.
     */
    public Orchestrateur(Blackjack jeu, VueBlackjack vue, List<Coup> coupsPossibles) {
        this.joueurs=jeu.getJoueurs();
        this.jeu = jeu;
        this.vue = vue;
        this.coupsPossibles=coupsPossibles;
    }

    /**
     * Démarre une nouvelle session de jeu.
     * Initialise les paramètres du jeu et lance la phase de demande de mises.
     */
    public void lancerJeu() {
        jeu.initJeu();
        demanderMises(0, new HashMap<>());
    }

    /**
     * Gère récursivement la demande de mises pour chaque joueur.
     * Si tous les joueurs ont misé, la partie commence.
     * Vérifie également la validité de la mise par rapport au solde du joueur.
     * * @param indexJoueur L'index du joueur actuel dans la liste des joueurs.
     * @param mises La carte stockant les mises validées pour chaque joueur.
     */
    private void demanderMises(int indexJoueur, Map<Joueur, Integer> mises) {
        if (indexJoueur >= joueurs.size()) {
            jeu.commencerPartie(mises);
            vue.afficherMainCroupier(false);
            jouerPartie();
            return;
        }

        Joueur joueur = joueurs.get(indexJoueur); 


        if (joueur.peutMiser()) {
            joueur.choisirMise((mise) -> {
                if (mise > joueur.getSolde() || mise <= 0) {              
                    String titre = "Erruer !";
                    String message = "la mise ne peut etre negative ou supperieure a votre solde";
                    vue.afficherMessage(message, titre, JOptionPane.INFORMATION_MESSAGE);
                    demanderMises(indexJoueur, mises); 
                } else {
                    mises.put(joueur, mise);
                    demanderMises(indexJoueur + 1, mises);
                }
            });   
        } else {
            demanderMises(indexJoueur + 1, mises);
        }
    }

    /**
     * Gère la boucle principale du jeu (les tours).
     * Vérifie si la partie est terminée, sinon fait jouer le joueur courant ou passe à la main suivante.
     */
    private void jouerPartie() {
        if (jeu.partieTermine()) {
            finirEtRelancerSiNecessaire();
            return;
        }
        Joueur joueurCourant = jeu.getJoueurCourant();
        if(joueurCourant.peutJouer()){
            Main mainCourante = jeu.getMainCourante();
            List<Coup> coupsAutorises = getCoupsAutorises(mainCourante);
            if(mainCourante.peutJouer()){
                joueurCourant.choisirCoup(mainCourante, coupsAutorises, (coup) -> {
                    jeu.jouerCoup(coup);
                    jeu.mainSuivante();
                    jouerPartie();
                });
            }
            else{
                jeu.mainSuivante();
                jouerPartie();
            }
        }
        else{
            jeu.mainSuivante();
            jouerPartie();
        }
    }


    /**
     * Gère la fin d'une manche.
     * Fait jouer le croupier, résout les mains, affiche les résultats (victoire/défaite)
     * et propose de relancer une manche ou réinitialise le jeu si le solde est épuisé.
     */
    private void finirEtRelancerSiNecessaire() {
        Joueur joueurHumain = joueurs.get(0);
        int soldeAvant = joueurHumain.getSolde();
        int miseTotale = 0;
        for (Main main : joueurHumain.getMains()) {
            miseTotale += main.getMise();
        }
        jeu.finirPartie();
        vue.afficherMainCroupier(true);

        int typeMessage;
        String message;
        String titre;
        int soldeApres = joueurHumain.getSolde();
        int recupere=soldeApres-soldeAvant;
        int gain = recupere -miseTotale;
        if (gain > 0) {
            titre = "Victoire !";
            message = "Bravo ! Vous avez gagné " + gain + " jetons.\nVotre nouveau solde : " + soldeApres;
            typeMessage = JOptionPane.INFORMATION_MESSAGE; 
        } else if (gain < 0) {
            titre = "Défaite";
            message = "Dommage, vous avez perdu " + Math.abs(gain) + " jetons.\nVotre nouveau solde : " + soldeApres;
            typeMessage = JOptionPane.WARNING_MESSAGE; 
        } else {
            titre = "Égalité";
            message = "Push (Égalité). Vous récupérez votre mise.\nVotre solde : " + soldeApres;
            typeMessage = JOptionPane.INFORMATION_MESSAGE; 
        }
        vue.afficherMessage(message, titre, typeMessage);

        if (!jeu.jeuTermine()) {
            demanderMises(0, new HashMap<>());
        } 
        else {
            
            titre = "Solde a 0";
            message = "Le jeu sera relancer";
            typeMessage = JOptionPane.INFORMATION_MESSAGE; 
            vue.afficherMessage(message, titre, typeMessage);
            lancerJeu();
        }
    }
    
    /**
     * Récupère la liste des coups autorisés pour une main donnée.
     * Filtre la liste des coups possibles en vérifiant si chaque coup est exécutable sur la main actuelle.
     * * @param mainCourante La main pour laquelle on vérifie les coups.
     * @return Une liste contenant uniquement les coups valides pour cette main.
     */
    private List<Coup> getCoupsAutorises(Main mainCourante) {
        List<Coup> coupsAutorises = new ArrayList<>();
        for (Coup coup : coupsPossibles) {
            if (coup.peutExecuter(mainCourante)) {
                coupsAutorises.add(coup);
            }
        }
        return coupsAutorises;
    }

    /**
     * Retourne l'instance du jeu de Blackjack.
     * * @return Le modèle du jeu.
     */
    public Blackjack getJeu() {
        return jeu;
    }

    /**
     * Retourne la liste des joueurs participant au jeu.
     * * @return La liste des joueurs.
     */
    public List<Joueur> getJoueurs() {
        return joueurs;
    }
}