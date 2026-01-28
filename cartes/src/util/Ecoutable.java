package util;


/**
 * Interface représentant un objet qui peut être écouté par des écouteurs.
 * Cette interface permet d'ajouter et de retirer des écouteurs pour surveiller les événements.
 */
public interface Ecoutable {

    /**
     * Ajoute un écouteur à l'objet écoutable.
     *
     * @param e L'écouteur à ajouter. Il sera notifié des événements de l'objet.
     */
    void ajouterEcouteur(Ecouteur e);

    /**
     * Retire un écouteur de l'objet écoutable.
     *
     * @param e L'écouteur à retirer. Il ne sera plus notifié des événements de l'objet.
     */
    void retirerEcouteur(Ecouteur e);
}
