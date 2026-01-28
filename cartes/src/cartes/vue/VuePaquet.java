package cartes.vue;

import cartes.modele.Paquet;
import javax.swing.JPanel;
import util.Ecouteur;

/**
 * Classe abstraite servant de base pour la représentation graphique d'un paquet de cartes.
 * Elle hérite de JPanel pour l'intégration dans l'interface graphique et implémente l'interface Ecouteur
 * pour se mettre à jour automatiquement lorsque le modèle du paquet change.
 */
public abstract class VuePaquet extends JPanel implements Ecouteur{
    
    /**
     * Le modèle de données (paquet) associé à cette vue.
     */
    protected final Paquet paquet;

    /**
     * Construit une nouvelle vue pour le paquet spécifié.
     *
     * @param paquet Le paquet de cartes à afficher.
     */
    public VuePaquet(Paquet paquet){
        super();
        this.paquet = paquet;
    }

    /**
     * Retourne le paquet associé à cette vue.
     *
     * @return Le modèle de paquet.
     */
    public Paquet getPaquet(){
        return this.paquet;
    }

    /**
     * Met à jour l'affichage du composant lorsque le modèle notifie un changement.
     * Cette implémentation de base efface tous les composants enfants et redemande un dessin.
     *
     * @param obj L'objet ayant déclenché la mise à jour (généralement le paquet observé).
     */
    @Override
    public void miseAjour(Object obj){
        removeAll();
        repaint();
    }
}