package blackjack.modele.joueur;

import blackjack.modele.coup.Coup;
import blackjack.modele.main.Main;
import java.util.*;

/**
 * Classe abstraite fournissant l'implémentation de base d'un joueur de Blackjack.
 * Elle gère les attributs communs tels que le nom, le solde et la liste des mains du joueur,
 * ainsi que la logique d'application des coups sur ces mains.
 */
public abstract class AbstractJoueur implements Joueur {
    
    /**
     * Le nom du joueur.
     */
    protected String nom;
    
    /**
     * Le solde actuel du joueur.
     */
    protected int solde;
    
    /**
     * La liste des mains actuellement détenues par le joueur.
     */
    protected List<Main> mains; 

    /**
     * Construit un nouveau joueur avec un nom et un solde initial.
     * * @param nom Le nom du joueur.
     * @param solde Le montant initial du solde du joueur.
     */
    public AbstractJoueur(String nom, int solde) {
        this.nom = nom;
        this.solde = solde;
        this.mains = new ArrayList<>();
    }

    /**
     * Retourne le nom du joueur.
     * * @return Le nom du joueur.
     */
    public String getNom(){
        return nom;
    }

    /**
     * Retourne la liste des mains du joueur.
     * * @return Une liste contenant les objets Main du joueur.
     */
    @Override
    public List<Main> getMains() {
        return this.mains;
    }

    /**
     * Retourne le solde actuel du joueur.
     * * @return Le solde du joueur.
     */
    @Override
    public int getSolde() {
        return this.solde;
    }

    /**
     * Modifie le solde du joueur.
     * * @param montant Le nouveau montant du solde.
     */
    @Override
    public void setSolde(int montant) {
        this.solde = montant;
    }

    /**
     * Vérifie si le joueur possède un solde suffisant pour miser.
     * * @return true si le solde est strictement positif, sinon false.
     */
    @Override
    public boolean peutMiser(){
        return solde > 0;
    }

    /**
     * Vérifie si le joueur est en mesure de jouer.
     * Un joueur peut jouer s'il possède au moins une main active.
     * * @return true si la liste des mains n'est pas vide, sinon false.
     */
    @Override
    public boolean peutJouer(){
        return !mains.isEmpty();
    }
    
    /**
     * Applique un coup spécifique à une main donnée du joueur.
     * La méthode identifie la main concernée dans la liste, exécute le coup,
     * et remplace l'ancienne main par la nouvelle main résultante (état mis à jour).
     * * @param main La main sur laquelle le coup doit être appliqué.
     * @param coup Le coup à exécuter.
     * @throws IllegalArgumentException Si la main spécifiée n'est pas trouvée dans la liste des mains du joueur.
     */
    @Override
    public void appliquerCoup(Main main, Coup coup) {
        int index = -1;
        int i=0;
        for( Main m : mains){
            if(main == m){
                index=i;
                break;
            }
            i++;
        }
        if (index < 0) {
            throw new IllegalArgumentException("Main pas trouvée dans les mains du joueur");
        }
        Main nouvelleMain = coup.executer(main);
        mains.set(index, nouvelleMain);
    }
    
    /**
     * Retourne une représentation textuelle du joueur.
     * Affiche le nom suivi du solde actuel.
     * * @return Une chaîne de caractères décrivant le joueur.
     */
    @Override
    public String toString(){
        return nom +" solde : "+solde;
    }
}