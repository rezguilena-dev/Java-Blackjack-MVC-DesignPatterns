package blackjack.modele.main;

import blackjack.modele.joueur.Joueur;
import cartes.modele.Paquet;

/**
 * Classe abstraite implémentant le pattern Décorateur pour l'interface Main.
 * Elle permet d'ajouter dynamiquement des fonctionnalités ou des états supplémentaires à une main existante
 * en déléguant les opérations à l'objet décoré.
 */
public class AbstractMain implements Main{
    
    /**
     * La main sous-jacente qui est décorée.
     */
    protected Main mainDecore;
    
    /**
     * Construit une nouvelle instance de décorateur pour une main donnée.
     *
     * @param mainDecore La main originale à décorer.
     */
    public AbstractMain(Main mainDecore){
        this.mainDecore = mainDecore;
    }

    /**
     * Retourne la mise actuelle placée sur cette main.
     *
     * @return Le montant de la mise.
     */
    @Override
    public int getMise() {
        return mainDecore.getMise();
    }

    /**
     * Retourne le joueur propriétaire de cette main.
     *
     * @return L'objet Joueur associé.
     */
    @Override
    public Joueur getJoueur() {
        return mainDecore.getJoueur();
    }

    /**
     * Calcule la valeur totale des points de la main selon les règles du Blackjack.
     *
     * @return La valeur calculée de la main.
     */
    @Override
    public int getValeur() {
        return mainDecore.getValeur();

    }

    /**
     * Retourne le paquet contenant les cartes de la main.
     *
     * @return Le paquet de cartes.
     */
    @Override
    public Paquet getPaquet() {
        return mainDecore.getPaquet();
    }

    /**
     * Définit un nouveau montant pour la mise de cette main.
     *
     * @param mise La nouvelle valeur de la mise.
     */
    @Override
    public void setMise(int mise) {
        mainDecore.setMise(mise);
    }

    /**
     * Retourne le nombre de tours ou d'actions effectués sur cette main.
     *
     * @return Le nombre de tours.
     */
    @Override
    public int getNbrTours() {
        return mainDecore.getNbrTours();
    }

    /**
     * Incrémente le compteur de tours pour cette main.
     */
    @Override
    public void incNbrTours() {
        mainDecore.incNbrTours();
    }

    /**
     * Vérifie si des actions sont encore possibles sur cette main.
     *
     * @return true si la main peut continuer à jouer, sinon false.
     */
    @Override
    public boolean peutJouer() {
        return mainDecore.peutJouer();
    }

    /**
     * Résout la partie pour cette main en comparant son score à celui du croupier
     * et en ajustant le solde du joueur en conséquence.
     *
     * @param mainCroupier La main du croupier servant de référence.
     */
    @Override
    public void resoudre(Main mainCroupier) {
        mainDecore.resoudre(mainCroupier);
    }

    /**
     * Vérifie l'égalité entre cette main décorée et un autre objet.
     * La comparaison est déléguée à l'objet décoré.
     *
     * @param obj L'objet avec lequel comparer.
     * @return true si les objets sont égaux, sinon false.
     */
    @Override
    public boolean equals(Object obj){
        return mainDecore.equals(obj);
    }

    /**
     * Retourne le code de hachage de cette main.
     * Le calcul est délégué à l'objet décoré.
     *
     * @return Le code de hachage de la main.
     */
    @Override
    public int hashCode(){
        return mainDecore.hashCode();
    }
    
    
}