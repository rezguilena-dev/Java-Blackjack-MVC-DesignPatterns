package cartes.controleur;

import cartes.modele.Carte;
import cartes.modele.Paquet;
import cartes.vue.VuePaquetVisible;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Contrôleur gérant le déplacement d'une carte depuis une vue de paquet visible vers un paquet de destination.
 * Cette classe permet à l'utilisateur de sélectionner une carte par un clic de souris pour la transférer
 * d'un paquet source vers un paquet cible.
 */
public class ControleurChoixCarteVersPaquet {

    /**
     * La vue graphique représentant le paquet source visible.
     */
    private VuePaquetVisible vuePaquetVisible;

    /**
     * Le paquet modèle de destination où les cartes sélectionnées sont ajoutées.
     */
    private Paquet destination;

    /**
     * Construit le contrôleur et associe la vue source au paquet de destination.
     * Installe un écouteur d'événements souris sur la vue pour déclencher le transfert de carte lors d'un clic.
     * * @param vuePaquetVisible La vue du paquet contenant les cartes à sélectionner.
     * @param destination Le paquet recevant les cartes transférées.
     */
    public ControleurChoixCarteVersPaquet(VuePaquetVisible vuePaquetVisible, Paquet destination) {
        this.vuePaquetVisible = vuePaquetVisible;
        this.destination = destination;

        this.vuePaquetVisible.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
         
                int indice = vuePaquetVisible.indiceCarteARetirer();
                if (indice != -1) {
                    Carte carte = vuePaquetVisible.getPaquet().retirerCarte(indice);
                    if (carte != null) {
                        destination.ajouterCarteDessus(carte);
                    }
                }
            }
        });
    }
}