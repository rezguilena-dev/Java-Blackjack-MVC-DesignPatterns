package blackjack.modele.joueur;

import blackjack.modele.coup.Coup;
import blackjack.modele.main.Main;
import java.util.*;
import java.util.function.Consumer;

/**
 * Représente un joueur de Blackjack.
 * Un joueur possède un solde, une ou plusieurs mains, 
 * choisit des mises et peut décider d'un coup à jouer.
 */
public interface Joueur {

    /**
     * Retourne la liste des mains actuellement possédées par le joueur.
     *
     * @return liste des mains du joueur
     */
    public List<Main> getMains();

    /**
     * Retourne le solde actuel du joueur.
     *
     * @return solde du joueur
     */
    public int getSolde();

    /**
     * Met à jour le solde du joueur.
     *
     * @param montant nouveau solde
     */
    public void setSolde(int montant);

    /**
     * Indique si le joueur peut miser (ex. solde suffisant).
     *
     * @return true si le joueur peut miser, false sinon
     */
    public boolean peutMiser();

    /**
     * Indique si le joueur peut jouer un coup (ex. main encore active).
     *
     * @return true si le joueur peut jouer, false sinon
     */
    public boolean peutJouer();

    /**
     * Applique un coup donné sur une main du joueur.
     *
     * @param main la main concernée
     * @param coup le coup à appliquer
     */
    public void appliquerCoup(Main main, Coup coup);

    /**
     * Permet au joueur de choisir un coup parmi ceux possibles.
     * Le résultat est retourné de manière asynchrone via un callback.
     *
     * @param main la main concernée
     * @param coups la liste des coups possibles
     * @param callback fonction appelée avec le coup choisi
     */
    public void choisirCoup(Main main, List<Coup> coups, Consumer<Coup> callback);

    /**
     * Permet au joueur de choisir une mise.
     * Le résultat est retourné via un callback.
     *
     * @param callback fonction appelée avec la mise choisie
     */
    public void choisirMise(Consumer<Integer> callback);

    /**
     * Retourne le nom du joueur.
     *
     * @return nom du joueur
     */
    public String getNom();
}
