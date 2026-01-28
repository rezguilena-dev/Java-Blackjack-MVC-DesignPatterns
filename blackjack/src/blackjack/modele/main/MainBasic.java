package blackjack.modele.main;

import blackjack.modele.joueur.Joueur;
import cartes.modele.Carte;
import cartes.modele.Paquet;

/**
 * Représente une main standard au Blackjack.
 * Cette classe gère les cartes, la mise, et le calcul de la valeur des points
 * pour une main donnée d'un joueur, ainsi que la résolution de la partie face au croupier.
 */
public class MainBasic implements Main {
    private final Paquet paquet;
    private final Joueur joueur;
    private int mise;
    private int nbrTours;

    /**
     * Construit une nouvelle main de base pour un joueur avec une mise initiale.
     *
     * @param joueur Le joueur propriétaire de la main.
     * @param mise Le montant misé sur cette main.
     */
    public MainBasic(Joueur joueur, int mise){
        this.paquet = new Paquet();
        this.mise = mise;
        this.joueur = joueur;
        this.nbrTours = 0;
    }
    
    /**
     * Retourne le montant de la mise actuelle.
     *
     * @return La mise associée à la main.
     */
    @Override
    public int getMise() {
        return mise;
    }

    /**
     * Retourne le joueur possédant cette main.
     *
     * @return L'objet Joueur propriétaire.
     */
    @Override
    public Joueur getJoueur() {
        return joueur;
    }
    
    /**
     * Retourne le paquet contenant les cartes de la main.
     *
     * @return Le paquet de cartes.
     */
    @Override
    public Paquet getPaquet() {
        return paquet;
    }

    /**
     * Calcule et retourne la valeur totale de la main.
     * Les figures valent 10, les As valent 11 ou 1 selon ce qui est le plus avantageux
     * pour ne pas dépasser 21, et les autres cartes conservent leur valeur nominale.
     *
     * @return La valeur en points de la main.
     */
    @Override
    public int getValeur() {
        int valeur=0;
        for(Carte carte: paquet.getCartes()){
            if( carte.getHauteur().equals("Roi") 
                || carte.getHauteur().equals("Dame") 
                || carte.getHauteur().equals("Valet")   ){
                    valeur+=10;   
            }
            else if(!carte.getHauteur().equals("As") ){
                valeur += Integer.parseInt( carte.getHauteur());
            }
        }
        for(Carte carte: paquet.getCartes()){
            if(carte.getHauteur().equals("As") ){
                if( valeur <= 10 ){
                    valeur+=11;
                }
                else{
                    valeur+=1;
                }
            }
        }
        return valeur;

    }

    /**
     * Compare cette main avec celle du croupier pour déterminer l'issue de la manche (gain, perte ou égalité)
     * et met à jour le solde du joueur en conséquence.
     * Gère les cas de Blackjack naturel, de dépassement (bust) et de comparaison de scores.
     *
     * @param mainCroupier La main du croupier à affronter.
     */
    @Override
    public void resoudre(Main mainCroupier) {
    
        int valeurJoueur = getValeur();
        int valeurCroupier = mainCroupier.getValeur();
    
        boolean bjJoueur = (valeurJoueur == 21 && getPaquet().getCartes().size() == 2);
        boolean bjCroupier = (valeurCroupier == 21 && mainCroupier.getNbrTours() == 0);
    
        int gain = 0;
    
        if (bjJoueur || bjCroupier) {
    
            if (bjJoueur && bjCroupier) {
                gain = mise;
            }
            else if (bjJoueur) {
                gain = (int)(mise * 1.5);
            }
            else {
                gain = 0;
            }
        }
    
        else {
            if (valeurJoueur > 21) {
                gain = 0;
            }
            else if (valeurCroupier > 21) {
                gain = mise * 2;
            }
            else if (valeurJoueur > valeurCroupier) {
                gain = mise * 2;
            }
            else if (valeurJoueur == valeurCroupier) {
                gain = mise;
            }
            else {
                gain = 0;
            }
        }
        joueur.setSolde(joueur.getSolde() + gain);
    }

    /**
     * Modifie le montant de la mise.
     *
     * @param mise La nouvelle mise.
     */
    @Override
    public void setMise(int mise) {
        this.mise = mise;
    }

    /**
     * Retourne le nombre de tours joués ou d'actions effectuées sur cette main.
     *
     * @return Le nombre de tours.
     */
    @Override
    public int getNbrTours() {
        return nbrTours;
    }

    /**
     * Incrémente le compteur de tours de la main.
     */
    @Override
    public void incNbrTours() {
        nbrTours++;
    }

    /**
     * Indique si la main peut encore recevoir des cartes ou effectuer des actions.
     * Possible tant que la valeur totale est strictement inférieure à 21.
     *
     * @return true si le joueur peut encore jouer cette main, sinon false.
     */
    @Override
    public boolean peutJouer() {
        return getValeur() < 21;
    }

    /**
     * Initialise la main avec les deux premières cartes distribuées ("deal" initial).
     * Vide le paquet actuel avant d'ajouter les nouvelles cartes.
     *
     * @param carte1 La première carte distribuée.
     * @param carte2 La deuxième carte distribuée.
     */
    public void premierDeal(Carte carte1, Carte carte2){
        paquet.getCartes().clear();
        paquet.ajouterCarteDessous(carte1);
        paquet.ajouterCarteDessous(carte2);
    }
    
}