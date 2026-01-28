package util;

/**
 * Interface représentant un écouteur qui réagit aux changements d'un objet écouté.
 * Un écouteur implémente cette interface pour être notifié lors des changements de l'objet écouté.
 */
public interface Ecouteur {

    /**
     * Méthode appelée pour notifier un changement sur un objet écouté.
     *
     * @param obj L'objet qui a subi un changement. 
     *        
     */
    void miseAjour(Object obj);
}
