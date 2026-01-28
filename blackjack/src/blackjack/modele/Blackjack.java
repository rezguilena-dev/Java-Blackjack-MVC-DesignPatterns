package blackjack.modele;


import blackjack.modele.coup.Coup;
import blackjack.modele.joueur.Joueur;
import blackjack.modele.main.*;
import cartes.modele.*;
import java.util.*;
import util.AbstractEcoutable;

/**
 * Classe centrale gérant la logique et le déroulement d'une partie de Blackjack.
 * Elle maintient l'état du jeu, y compris la pioche, les joueurs, la main du croupier,
 * et gère la progression des tours ainsi que la résolution des parties.
 */
public class Blackjack extends AbstractEcoutable {
    
    /**
     * Le solde initial attribué à chaque joueur au début du jeu.
     */
    private final int SOLDE_DEPART = 1000;
    
    /**
     * La liste des joueurs participant à la partie.
     */
    private final List<Joueur> joueurs;
    
    /**
     * La main appartenant au croupier.
     */
    private final MainBasic mainCroupier;
    
    /**
     * Le paquet de cartes (pioche) utilisé pour le jeu.
     */
    private final Paquet pioche;

    /**
     * L'index du joueur dont c'est actuellement le tour.
     */
    private int joueurCourantIndex;
    
    /**
     * L'index de la main courante en train d'être jouée par le joueur actuel.
     */
    private int mainCouranteIndex;

    /**
     * Construit une nouvelle instance du jeu de Blackjack.
     * Initialise la liste des joueurs, crée la main du croupier et génère un paquet de 52 cartes.
     *
     * @param joueurs La liste des joueurs participants.
     * @throws IllegalArgumentException Si la liste des joueurs est nulle ou vide.
     */
    public Blackjack( List<Joueur> joueurs) throws IllegalArgumentException{
        if(joueurs == null || joueurs.isEmpty()){
            throw new IllegalArgumentException("La liste des joueurs ne peut etre vide");
        }
        this.joueurs= joueurs;
        mainCroupier = new MainBasic( null, 0);
        this.pioche = PaquetFactory.creerPaquet52();
    }

    /**
     * Réinitialise l'état du jeu pour une nouvelle session.
     * Remet le solde de tous les joueurs à la valeur de départ.
     */
    public void initJeu(){
        for (Joueur joueur : joueurs){
            joueur.setSolde(SOLDE_DEPART);
        }
        notifierChangement();
    } 

    /**
     * Distribue les deux premières cartes à une main donnée.
     *
     * @param main La main qui doit recevoir les cartes.
     */
    private void premierDeal(MainBasic main){
        Carte carte1 = pioche.retirerPremiereCarte();
        Carte carte2 = pioche.retirerPremiereCarte();
        main.premierDeal(carte1, carte2);
    }

    /**
     * Démarre une nouvelle manche (partie) en utilisant les mises fournies.
     * Cette méthode gère le mélange des cartes si nécessaire, la distribution initiale pour le croupier
     * et les joueurs, ainsi que la déduction des mises des soldes des joueurs.
     *
     * @param mises Une map associant chaque joueur au montant qu'il souhaite miser.
     */
    public void commencerPartie(Map<Joueur,Integer> mises){
        if (jeuTermine()){
            initJeu();
        }
        if(pioche.getCartes().size() <51){
            Paquet nouveauPaquet = PaquetFactory.creerPaquet52();
            for (Carte carte : nouveauPaquet.getCartes()) {
                pioche.ajouterCarteDessus(carte);
            }
        }
        this.pioche.melangerJeu();
        premierDeal(mainCroupier);
        for(Joueur joueur : joueurs){
            joueur.getMains().clear();
        }
        for (Map.Entry<Joueur, Integer> entry: mises.entrySet()){
            Joueur joueur = entry.getKey(); 
            int mise = entry.getValue();
            joueur.setSolde(joueur.getSolde()-mise); 
            MainBasic main = new MainBasic(joueur, mise);
            premierDeal(main); 
            joueur.getMains().add(main);
        }  
        joueurCourantIndex = 0;
        mainCouranteIndex = 0;
        notifierChangement();
    }

    /**
     * Vérifie si tous les joueurs ont terminé leur tour pour la manche en cours.
     *
     * @return true si le dernier joueur a joué, sinon false.
     */
    public boolean partieTermine(){
        return joueurCourantIndex >= joueurs.size();
    }

    /**
     * Vérifie si le joueur courant a terminé de jouer toutes ses mains.
     *
     * @return true si le joueur n'a plus de mains à traiter, sinon false.
     */
    public boolean joueurATermine(){
        return mainCouranteIndex >= getJoueurCourant().getMains().size();
    }

    /**
     * Vérifie si la partie est globalement terminée (game over).
     * C'est le cas si le joueur principal (le premier de la liste) n'a plus de solde.
     *
     * @return true si le solde du premier joueur est inférieur ou égal à 0.
     */
    public boolean jeuTermine(){
        return joueurs.get(0).getSolde() <= 0;
    }

    /**
     * Passe à la main suivante ou au joueur suivant.
     * Cette méthode met à jour les index de main et de joueur courant. Si la main actuelle
     * ne peut plus être jouée, elle avance automatiquement.
     */
    public void mainSuivante(){
        if(getMainCourante() == null || !getMainCourante().peutJouer()){
            mainCouranteIndex++;
            if(joueurATermine()){
                mainCouranteIndex=0;
                joueurCourantIndex++;
            }
        }
        notifierChangement();
    }

    /**
     * Exécute un coup choisi par le joueur sur sa main courante.
     *
     * @param coup Le coup à appliquer.
     */
    public void jouerCoup(Coup coup){
        Joueur joueurCourant = getJoueurCourant();
        joueurCourant.appliquerCoup(getMainCourante(), coup);
        notifierChangement();
    }

    /**
     * Gère le tour du croupier.
     * Le croupier tire des cartes tant que la valeur de sa main est inférieure à 16.
     */
    private void jouerTourCroupier(){
        while (mainCroupier.getValeur()<16){
            mainCroupier.getPaquet().ajouterCarteDessous(pioche.retirerPremiereCarte());
            notifierChangement();
        }
    }

    /**
     * Finalise la manche.
     * Fait jouer le croupier puis résout toutes les mains des joueurs en les comparant
     * à celle du croupier pour déterminer les gains.
     */
    public void finirPartie(){
        this.jouerTourCroupier();
        for (Joueur joueur : joueurs){
            for (Main main : joueur.getMains()){
                main.resoudre(mainCroupier);
            }
        }
        notifierChangement();
    }
    
    /**
     * Retourne la pioche utilisée dans le jeu.
     *
     * @return Le paquet de cartes de la pioche.
     */
    public Paquet getPioche(){
        return pioche;
    }

    /**
     * Retourne la main du croupier.
     *
     * @return La main du croupier.
     */
    public Main getMainCroupier(){
        return mainCroupier;
    }

    /**
     * Retourne la liste des joueurs.
     *
     * @return La liste des joueurs.
     */
    public List<Joueur> getJoueurs(){
        return joueurs;
    }

    /**
     * Retourne le joueur dont c'est actuellement le tour.
     *
     * @return Le joueur courant.
     */
    public Joueur getJoueurCourant(){
        return joueurs.get(joueurCourantIndex %joueurs.size());
    }

    /**
     * Retourne la main actuellement jouée par le joueur courant.
     *
     * @return La main courante, ou null si le joueur n'a pas de mains.
     */
    public Main getMainCourante(){
        if(getJoueurCourant().getMains().isEmpty()){
            return null;
        }
        return getJoueurCourant().getMains().get(mainCouranteIndex);
    }

}