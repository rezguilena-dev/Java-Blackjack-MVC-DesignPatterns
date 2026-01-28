package cartes;

import cartes.controleur.ControleurCartes;
import cartes.modele.Paquet;
import cartes.modele.PaquetFactory;
import cartes.vue.VueCartes;
import cartes.vue.VuePiocheVisible;

/**
 * Classe principale servant de point d'entrée à l'application de gestion de cartes.
 * Elle configure les composants initiaux du modèle (paquets), de la vue et du contrôleur
 * pour lancer l'interface graphique et permettre l'interaction avec le jeu de cartes.
 */
public class MainClass {

    /**
     * Méthode principale exécutée au lancement de l'application.
     * Elle initialise une pioche standard de 52 cartes (mélangée), une main vide et une défausse vide.
     * Ensuite, elle instancie la vue principale et le contrôleur pour lier les modèles à l'interface graphique.
     *
     * @param args Les arguments de la ligne de commande (non utilisés ici).
     */
    public static void main(String[] args) {
        
        Paquet pioche = PaquetFactory.creerPaquet52();
        Paquet main = new Paquet();
        Paquet defausse = new Paquet();
        VuePiocheVisible piocheVisible = new VuePiocheVisible(pioche);
        pioche.melangerJeu();
        VueCartes vueCartes = new VueCartes(pioche, main, defausse);
        ControleurCartes controleur = new ControleurCartes(vueCartes,pioche, main, defausse);
    }

}