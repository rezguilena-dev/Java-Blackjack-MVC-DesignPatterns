package util;

import java.util.*;

/**
 * Classe abstraite qui implémente l'interface Ecoutable.
 * Cette classe permet à un objet d'avoir une liste d'écouteurs et de notifier ces écouteurs lors d'un changement.
 * Elle gère l'ajout, le retrait et la notification des écouteurs d'un changement.
 */
public abstract class AbstractEcoutable implements Ecoutable {
    private final List<Ecouteur> ecouteurs;

    /**
     * Constructeur de la classe 
     * Initialise une nouvelle liste d'écouteurs.
     */
    public AbstractEcoutable() {
        this.ecouteurs = new ArrayList<>();
    }

    /**
     * Ajoute un écouteur à la liste d'écouteurs.
     * Si l'écouteur est déjà présent, il n'est pas ajouté de nouveau.
     *
     * @param e L'écouteur à ajouter.
     */
    @Override
    public void ajouterEcouteur(Ecouteur e) {
        if (!ecouteurs.contains(e)) {
            ecouteurs.add(e);
        }
    }

    /**
     * Retire un écouteur de la liste d'écouteurs.
     *
     * @param e L'écouteur à retirer.
     */
    @Override
    public void retirerEcouteur(Ecouteur e) {
        ecouteurs.remove(e);
    }

    /**
     * Notifie tous les écouteurs enregistrés d'un changement.
     * Chaque écouteur recevra un appel à sa méthode .
     */
    public void notifierChangement() {
        for (Ecouteur e : this.ecouteurs) {
            e.miseAjour(this);
        }
    }
}
