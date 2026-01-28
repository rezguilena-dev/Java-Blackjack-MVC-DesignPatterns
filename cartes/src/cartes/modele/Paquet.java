package cartes.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import util.AbstractEcoutable;

/**
 * Représente un paquet de cartes avec des opérations telles que l'ajout, le retrait, le mélange et la coupe.
 */
public class Paquet extends AbstractEcoutable {
    
        
    private List<Carte> cartes;

    /**
     * Constructeur qui initialise un paquet de cartes vide.
     */
    public Paquet() {
        cartes = new ArrayList<>();
    }

    /**
     * Ajoute une carte au début du paquet.
     * @param carte La carte à ajouter.
     */
    public void ajouterCarteDessus(Carte carte) {
        cartes.add(0, carte);
        notifierChangement();
    }

    /**
     * Ajoute une carte à la fin du paquet.
     * @param carte La carte à ajouter.
     */
    public void ajouterCarteDessous(Carte carte) {
        cartes.add(carte);
        notifierChangement();
    }

    /**
     * Retire une carte à l'indice spécifié du paquet.
     * @param indice L'indice de la carte à retirer.
     * @return La carte retirée, ou null si l'indice est invalide.
     */
    public Carte retirerCarte(int indice) {
        if (indice >= cartes.size()) {
            return null;
        }
        Carte carte = cartes.remove(indice);
        notifierChangement();
        return carte;
    }

    /**
     * Retire la première carte du paquet.
     * @return La première carte du paquet.
     */
    public Carte retirerPremiereCarte() {
        return this.retirerCarte(0);
    }

    /**
     * Retire une carte aléatoire du paquet.
     * @return Une carte aléatoire retirée du paquet.
     */
    public Carte pickRandom() {
        Random rand = new Random();
        return this.retirerCarte(rand.nextInt(cartes.size()));
    }

    /**
     * Mélange le paquet de manière aléatoire.
     */
    public void melangerJeu() {
        Collections.shuffle(cartes);
    }

    /**
     * Coupe le paquet en deux à un endroit aléatoire.
     * L'endroit de coupe ne doit pas être dans les 3 premieres et dernières cartes.
     */
    public void couperJeu() {
        Random rand = new Random();
        if (cartes.size() <= 6) {
            return;
        }
        int splitIndex = rand.nextInt(cartes.size() - 6) + 3;
        for (int i = 0; i < splitIndex; i++) {
            Carte tmp = cartes.remove(0);
            cartes.add(tmp);
        }
    }

    /**
     * Trie le paquet par hauteur (rang) de carte.
     * Notifie les écouteurs d'un changement.
     */
    public void trierParHauteur() {
        Collections.sort(cartes, (c1, c2) -> {
            int index1 = PaquetFactory.HAUTEURS.indexOf(c1.getHauteur());
            int index2 =  PaquetFactory.HAUTEURS.indexOf(c2.getHauteur());
            return Integer.compare(index1, index2);
        });
        notifierChangement();
    }

    /**
     * Trie le paquet par couleur (suite) de carte.
     * Notifie les écouteurs d'un changement.
     */
    public void trierParCouleur() {
        Collections.sort(cartes, (c1, c2) -> {
            int index1 = PaquetFactory.COULEURS.indexOf(c1.getCouleur());
            int index2 = PaquetFactory.COULEURS.indexOf(c2.getCouleur());
            return Integer.compare(index1, index2);
        });
        notifierChangement();
    }

    /**
     * Retourne la liste des cartes du paquet.
     * @return La liste des cartes.
     */
    public List<Carte> getCartes() {
        return cartes;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du paquet de cartes.
     * @return La représentation sous forme de chaîne.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("Paquet de Cartes:\n");
        for (Carte carte : cartes) {
            res.append(carte).append("\n");
        }
        return res.toString();
    }
}